package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * MITE 副手物品使用 Mixin
 * 处理副手物品使用完成后的状态更新
 */
@Mixin(EntityPlayer.class)
public abstract class OffhandEntityPlayerUseMixin {
    
    @Shadow
    public InventoryPlayer inventory;
    
    /**
     * 在物品使用完成后，恢复副手物品状态
     */
    @Inject(method = "onItemUseFinish", at = @At("TAIL"))
    protected void miteassistant$afterItemUseFinish(CallbackInfo ci) {
        Object thisPlayer = this;
        if (!(thisPlayer instanceof EntityPlayer player)) return;
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        // 检查是否是副手物品使用完成（仅服务端处理，客户端等待服务端同步）
        if (!player.worldObj.isRemote && offhandAccess.miteassistant$isUsingOffhand()) {
            OffhandLog.info("[OFFHAND] 副手物品使用完成");
            restoreAfterOffhandUse(player, offhandAccess);
        }
    }
    
    /**
     * 在玩家停止使用物品时（中途停止，如弓箭射击），恢复副手物品状态
     */
    @Inject(method = "stopUsingItem(Z)V", at = @At("TAIL"))
    protected void miteassistant$afterStopUsingItem(boolean inform_server, CallbackInfo ci) {
        Object thisPlayer = this;
        if (!(thisPlayer instanceof EntityPlayer player)) return;
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        // 检查是否是副手物品停止使用（仅服务端处理，客户端等待服务端同步）
        if (!player.worldObj.isRemote && offhandAccess.miteassistant$isUsingOffhand()) {
            OffhandLog.info("[OFFHAND] 副手物品停止使用（中途停止）");
            restoreAfterOffhandUse(player, offhandAccess);
        }
    }
    
    /**
     * 恢复副手物品使用后的状态
     */
    private void restoreAfterOffhandUse(EntityPlayer player, OffhandAccess offhandAccess) {
        // 获取保存的原始槽位（而不是当前槽位，防止滚轮滚动导致错乱）
        int originalSlot = offhandAccess.miteassistant$getOriginalSlot();
        if (originalSlot < 0 || originalSlot >= this.inventory.mainInventory.length) {
            // 如果没有保存有效的槽位，使用当前槽位（兼容旧版本）
            originalSlot = this.inventory.currentItem;
        }
        
        // 获取原始槽位的物品（可能已被消耗或改变）
        ItemStack usedItem = this.inventory.mainInventory[originalSlot];
        
        // 恢复原始主手物品
        ItemStack originalMainhand = offhandAccess.miteassistant$getOriginalMainhand();
        this.inventory.mainInventory[originalSlot] = originalMainhand;
        
        // 将使用后的物品放回副手（如果还有剩余）
        ItemStack newOffhand = (usedItem == null || usedItem.stackSize <= 0) ? null : usedItem;
        offhandAccess.miteassistant$setOffhandStack(newOffhand);
        
        // 清除标记
        offhandAccess.miteassistant$setUsingOffhand(false);
        offhandAccess.miteassistant$setOriginalMainhand(null);
        offhandAccess.miteassistant$setOriginalSlot(-1);
        
        // 如果在服务器端，同步到客户端
        if (!player.worldObj.isRemote && player instanceof ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
        }
        
        OffhandLog.info("[OFFHAND] 恢复完成，副手物品: {}", newOffhand);
    }
}
