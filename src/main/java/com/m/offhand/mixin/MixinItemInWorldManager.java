package com.m.offhand.mixin;

import com.m.offhand.OffhandManyLibConfig;
import com.m.offhand.api.core.OffhandActionHelper;
import com.m.offhand.api.core.OffhandActionHelper.DualMiningBlock;
import com.m.offhand.api.core.OffhandUtils;
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
        if (!Boolean.TRUE.equals(cir.getReturnValue())
            || !OffhandManyLibConfig.OFFHAND_BREAK_BLOCKS.getBooleanValue()
            || this.isCreative()
            || this.theWorld == null
            || this.thisPlayerMP == null) {
            return;
        }

        EntityPlayer player = this.thisPlayerMP;
        ItemStack offhand = OffhandUtils.getOffhandItem(player);
        DualMiningBlock minedBlock = OffhandActionHelper.consumeDualMiningBlock(player, x, y, z, offhand);
        if (minedBlock == null) {
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
