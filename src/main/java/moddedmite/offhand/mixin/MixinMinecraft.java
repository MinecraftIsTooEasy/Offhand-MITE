package moddedmite.offhand.mixin;

import moddedmite.offhand.api.compat.OffhandCompatRegistry;
import moddedmite.offhand.api.core.EnumHand;
import moddedmite.offhand.api.core.OffhandUsePriority;
import moddedmite.offhand.api.core.OffhandUtils;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.GuiScreen;
import net.minecraft.ItemStack;
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

    @Unique
    private RightClickFilter offhand$mainhandRightClickResult;

    @Unique
    private boolean offhand$mainhandRightClickCancelled;

    @Shadow
    public EntityClientPlayerMP thePlayer;

    @Shadow
    public RaycastCollision objectMouseOver;

    @Shadow
    public PlayerControllerMP playerController;

    @Shadow
    public Timer timer;

    @Inject(method = "clickMouse", at = @At("HEAD"))
    private void offhand$beginMouseClick(int button, CallbackInfo ci) {
        this.offhand$mainhandRightClickResult = null;
        this.offhand$mainhandRightClickCancelled = false;
    }

    @Redirect(
        method = "clickMouse",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/EntityClientPlayerMP;onPlayerRightClickChecked(Lnet/minecraft/RaycastCollision;Lnet/minecraft/RightClickFilter;FZ)Lnet/minecraft/RightClickFilter;"))
    private RightClickFilter offhand$captureMainhandRightClickResult(
        EntityClientPlayerMP player,
        RaycastCollision target,
        RightClickFilter filter,
        float partialTicks,
        boolean ctrlDown) {
        RightClickFilter result = player.onPlayerRightClickChecked(target, filter, partialTicks, ctrlDown);
        this.offhand$mainhandRightClickResult = result;
        return result;
    }

    @Redirect(
        method = "clickMouse",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/EntityClientPlayerMP;rightClickCancelled()Z"))
    private boolean offhand$captureMainhandRightClickCancellation(EntityClientPlayerMP player) {
        boolean cancelled = player.rightClickCancelled();
        this.offhand$mainhandRightClickCancelled = cancelled;
        return cancelled;
    }

    @Inject(method = "clickMouse", at = @At("TAIL"))
    private void offhand$afterRightClick(int button, CallbackInfo ci) {
        RightClickFilter mainhandResult = this.offhand$mainhandRightClickResult;
        boolean mainhandCancelled = this.offhand$mainhandRightClickCancelled;
        this.offhand$mainhandRightClickResult = null;
        this.offhand$mainhandRightClickCancelled = false;

        if (button != 1 || this.thePlayer == null) {
            return;
        }

        if (!OffhandUsePriority.shouldTryOffhand(
                mainhandResult != null,
                mainhandCancelled,
                mainhandResult != null && mainhandResult.allowsNoActions())) {
            return;
        }

        ItemStack mainhandStack = EnumHand.MAIN_HAND.getItem(this.thePlayer);
        ItemStack offhandStack = EnumHand.OFF_HAND.getItem(this.thePlayer);
        if (offhandStack == null || offhandStack.stackSize <= 0 || offhandStack.getItem() == null) {
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

        }

        this.offhand$tryUseOffhandRightClick();
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
        ItemStack mainhandStack = this.thePlayer.inventory.getCurrentItemStack();
        ItemStack offhandStack = OffhandUtils.getOffhandItem(this.thePlayer);

        if (OffhandCompatRegistry.getActionFilter().cancelOffhandRightClick(
                this.thePlayer, mainhandStack, offhandStack)) {
            return false;
        }

        return OffhandUtils.useOffhandItem(this.thePlayer, false, () -> {
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
        });
    }
}
