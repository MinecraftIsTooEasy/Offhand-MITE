package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.renderer.OffhandRenderer;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class OffhandGuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;

    @Unique
    private ItemStack miteassistant$tempSwappedItem = null;
    @Unique
    private int miteassistant$tempSwappedSlot = -1;

    @Inject(method = "renderInventorySlot", at = @At("HEAD"))
    private void miteassistant$beforeRenderSlot(int slotIndex, int x, int y, float partialTicks, CallbackInfo ci) {
        if (this.mc == null || this.mc.thePlayer == null) return;
        
        Object playerObj = this.mc.thePlayer;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;

        if (offhandAccess.miteassistant$isUsingOffhand() && slotIndex == this.mc.thePlayer.inventory.currentItem) {
            miteassistant$tempSwappedSlot = slotIndex;
            miteassistant$tempSwappedItem = this.mc.thePlayer.inventory.mainInventory[slotIndex];
            this.mc.thePlayer.inventory.mainInventory[slotIndex] = offhandAccess.miteassistant$getOriginalMainhand();
        }
    }

    @Inject(method = "renderInventorySlot", at = @At("TAIL"))
    private void miteassistant$afterRenderSlot(int slotIndex, int x, int y, float partialTicks, CallbackInfo ci) {
        if (miteassistant$tempSwappedSlot >= 0 && miteassistant$tempSwappedSlot == slotIndex) {
            this.mc.thePlayer.inventory.mainInventory[miteassistant$tempSwappedSlot] = miteassistant$tempSwappedItem;
            miteassistant$tempSwappedItem = null;
            miteassistant$tempSwappedSlot = -1;
        }
    }

    @Inject(method = "renderGameOverlay(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z", shift = At.Shift.AFTER))
    private void miteassistant$renderOffhandSlot(float partialTicks, boolean par2, int par3, int par4, CallbackInfo ci) {
        OffhandRenderer.renderOffhandSlot(this.mc, partialTicks);
    }
}
