package moddedMite.offhand.mixin;

import moddedMite.offhand.config.OffhandManyLibConfig;
import moddedMite.offhand.api.core.OffhandActionHelper;
import moddedMite.offhand.api.core.OffhandActionHelper.DualMiningBlock;
import moddedMite.offhand.api.core.OffhandUtils;
import net.minecraft.BlockBreakInfo;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemInWorldManager;
import net.minecraft.ItemStack;
import net.minecraft.ServerPlayer;
import net.minecraft.StatList;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemInWorldManager.class)
public abstract class MixinItemInWorldManager {

    @Shadow
    public World theWorld;

    @Shadow
    public ServerPlayer thisPlayerMP;

    @Shadow
    public abstract boolean isCreative();

    @Inject(method = "tryHarvestBlock", at = @At("RETURN"))
    private void offhand$damageOffhandToolAfterDualMining(
        int x,
        int y,
        int z,
        CallbackInfoReturnable<Boolean> cir) {
        if (this.thisPlayerMP == null) {
            return;
        }

        EntityPlayer player = this.thisPlayerMP;
        ItemStack offhand = OffhandUtils.getOffhandItem(player);
        DualMiningBlock minedBlock = OffhandActionHelper.consumeDualMiningBlock(player, x, y, z, offhand);
        if (minedBlock == null
            || !Boolean.TRUE.equals(cir.getReturnValue())
            || !OffhandManyLibConfig.OFFHAND_BREAK_BLOCKS.getBooleanValue()
            || this.isCreative()
            || this.theWorld == null) {
            return;
        }

        OffhandUtils.useOffhandItem(player, false, () -> {
            BlockBreakInfo info = new BlockBreakInfo(this.theWorld, x, y, z)
                .setBlock(minedBlock.block, minedBlock.metadata)
                .setHarvestedBy(player);
            Item item = offhand.getItem();
            if (item.onBlockDestroyed(info)) {
                player.addStat(StatList.objectUseStats[item.itemID], 1);
            }
        });
    }
}
