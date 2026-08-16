package moddedmite.offhand.mixin;

import moddedmite.offhand.client.OffhandKeyHandler;
import net.minecraft.NetClientHandler;
import net.minecraft.Packet103SetSlot;
import net.minecraft.Packet104WindowItems;
import net.minecraft.Packet5PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public abstract class MixinNetClientHandler {

    @Inject(method = "handleSetSlot", at = @At("HEAD"))
    private void offhand$animateConfirmedSwap(Packet103SetSlot packet, CallbackInfo ci) {
        OffhandKeyHandler.applyPendingSwapAnimations(packet);
    }

    @Inject(method = "handlePlayerInventory", at = @At("HEAD"))
    private void offhand$animateConfirmedInventorySwap(Packet5PlayerInventory packet, CallbackInfo ci) {
        OffhandKeyHandler.applyPendingSwapAnimations(packet);
    }

    @Inject(method = "handleWindowItems", at = @At("HEAD"))
    private void offhand$animateConfirmedWindowSwap(Packet104WindowItems packet, CallbackInfo ci) {
        OffhandKeyHandler.applyPendingSwapAnimations(packet);
    }
}
