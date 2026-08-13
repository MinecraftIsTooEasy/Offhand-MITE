package moddedMite.offhand.mixin;

import moddedMite.offhand.api.core.OffhandUtils;
import net.minecraft.EntityPlayer;
import net.minecraft.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayerClient {

    @Redirect(
        method = "onPlayerRightClick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/PlayerControllerMP;syncCurrentPlayItem()V"))
    private void offhand$skipTemporaryOffhandSlotSync(PlayerControllerMP controller) {
        if (!OffhandUtils.isClientOffhandUseContext()) {
            controller.syncCurrentPlayItem();
        }
    }
}
