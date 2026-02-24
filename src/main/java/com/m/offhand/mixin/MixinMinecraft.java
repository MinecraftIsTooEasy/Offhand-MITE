package com.m.offhand.mixin;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.event.TorchHandler;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiScreen;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemHoe;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;
import net.minecraft.ItemTool;
import net.minecraft.Minecraft;
import net.minecraft.Packet81RightClick;
import net.minecraft.PlayerControllerMP;
import net.minecraft.RaycastCollision;
import net.minecraft.RightClickFilter;
import net.minecraft.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow
    public EntityClientPlayerMP thePlayer;

    @Shadow
    public RaycastCollision objectMouseOver;

    @Shadow
    public PlayerControllerMP playerController;

    @Shadow
    public Timer timer;

    @Inject(method = "clickMouse", at = @At("TAIL"))
    private void offhand$afterRightClick(int button, CallbackInfo ci) {
        if (button != 1 || this.thePlayer == null) {
            return;
        }

        ItemStack mainhandStack = this.thePlayer.inventory.getCurrentItemStack();
        ItemStack offhandStack = OffhandUtils.getOffhandItem(this.thePlayer);
        if (offhandStack == null || offhandStack.stackSize <= 0 || offhandStack.getItem() == null) {
            return;
        }

        if (!this.offhand$shouldTryOffhand(mainhandStack)) {
            return;
        }

        if (this.objectMouseOver != null && this.objectMouseOver.isBlock()) {
            if (!OffhandCompatRegistry.getInteractionPolicy().canUseOffhandForBlockInteraction(
                this.thePlayer,
                mainhandStack,
                offhandStack,
                this.objectMouseOver)) {
                return;
            }

            if (offhandStack.getItem() instanceof ItemBlock && !TorchHandler.shouldPlace(mainhandStack, offhandStack)) {
                return;
            }
        }

        if (this.offhand$tryUseOffhandRightClick()) {
            ((IOffhandPlayer) (EntityPlayer) this.thePlayer).swingOffItem();
        }
    }

    @Unique
    private boolean offhand$shouldTryOffhand(ItemStack mainhandStack) {
        if (mainhandStack == null || mainhandStack.stackSize <= 0) {
            return true;
        }

        Item mainItem = mainhandStack.getItem();
        if (mainItem == null) {
            return true;
        }

        // Mainhand has an explicit usable item: do not auto-trigger offhand to avoid double use.
        // We only yield to offhand when mainhand is a "utility/attack hand" item.
        if (mainItem instanceof ItemTool || mainItem instanceof ItemSword || mainItem instanceof ItemHoe) {
            return true;
        }

        for (Class<? extends Item> type : OffhandUtils.deprioritizedMainhand) {
            if (type != null && type.isInstance(mainItem)) {
                return true;
            }
        }

        return false;
    }

    @Redirect(
        method = "clickMiddleMouseButton",
        at = @At(value = "INVOKE", target = "Ljava/util/List;size()I"))
    private int offhand$fixCreativePickBlockGhost(List<?> list) {
        int size = list.size();
        if (this.thePlayer != null
            && this.thePlayer.inventoryContainer != null
            && list == this.thePlayer.inventoryContainer.inventorySlots
            && size > 0) {
            return size - 1;
        }
        return size;
    }

    @Unique
    private boolean offhand$tryUseOffhandRightClick() {
        int oldSlot = this.thePlayer.inventory.currentItem;
        int offhandSlot = OffhandUtils.getOffhandSlot(this.thePlayer);
        if (offhandSlot < 0 || offhandSlot >= this.thePlayer.inventory.mainInventory.length) {
            return false;
        }

        try {
            this.thePlayer.inventory.currentItem = offhandSlot;
            this.playerController.syncCurrentPlayItem();

            RightClickFilter filter = new RightClickFilter();
            OffhandUtils.beginClientOffhandUseContext();
            try {
                filter = this.thePlayer.onPlayerRightClickChecked(
                    this.objectMouseOver,
                    filter,
                    this.timer.renderPartialTicks,
                    GuiScreen.isCtrlKeyDown());
            } finally {
                OffhandUtils.endClientOffhandUseContext();
            }

            if (this.thePlayer.rightClickCancelled()) {
                this.thePlayer.clearRightClickCancelled();
                return false;
            }

            if (filter.allowsNoActions()) {
                return false;
            }

            if (this.thePlayer.isBlocking()) {
                this.playerController.setUseButtonDelayOverride(10);
            } else {
                this.playerController.setUseButtonDelay();
            }

            if (filter.allowsEntityInteraction() && this.objectMouseOver != null && this.objectMouseOver.getEntityHit() != null) {
                this.thePlayer.sendPacket(new Packet81RightClick(this.thePlayer, this.objectMouseOver.getEntityHit()));
            } else {
                this.thePlayer.sendPacket(new Packet81RightClick(this.thePlayer, this.timer.renderPartialTicks, filter));
            }

            // Suppress main-hand first-person swing for a short window so offhand tool animation is not doubled.
            OffhandUtils.markClientSuppressMainhandRender(this.thePlayer, 2);

            return true;
        } finally {
            this.thePlayer.inventory.currentItem = oldSlot;
            this.playerController.syncCurrentPlayItem();
        }
    }
}
