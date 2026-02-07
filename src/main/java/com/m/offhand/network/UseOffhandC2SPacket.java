package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;

/**
 * 客户端 → 服务器：请求使用副手物品
 * 这个包完全绕过 MITE 的 filter 检查系统
 */
public class UseOffhandC2SPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.NameSpace, "use_offhand");

    private final boolean ctrlIsDown;

    public UseOffhandC2SPacket(boolean ctrlIsDown) {
        this.ctrlIsDown = ctrlIsDown;
    }

    public UseOffhandC2SPacket(PacketByteBuf buf) {
        this.ctrlIsDown = buf.readBoolean();
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBoolean(this.ctrlIsDown);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        // 只在服务器端执行
        if (entityPlayer.worldObj.isRemote) return;
        
        // 获取副手物品
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(entityPlayer);
        if (offhandAccess == null) return;
        
        // 如果已经正在使用副手物品，不重复处理（防止狂按导致物品丢失）
        if (OffhandUtils.isPlayerBusy(entityPlayer, offhandAccess)) return;
        
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) return;
        
        Item offhandItem = offhand.getItem();
        if (offhandItem == null) return;
        
        // 禁止副手使用钓鱼竿（会导致错误）
        if (offhandItem instanceof ItemFishingRod) {
            return;
        }
        
        // 禁止副手格挡（狂按右键会导致鼠标滚轮滚动物品栏，物品会被错误交换到副手）
        // 检查物品是否会触发格挡动作（如剑、铲子等）
        EnumItemInUseAction preCheckAction = offhand.getItemInUseAction(entityPlayer);
        if (preCheckAction == EnumItemInUseAction.BLOCK) {
            return;
        }
        
        // 检查主手物品
        ItemStack mainhand = entityPlayer.getHeldItemStack();
        
        // 判断是否应该使用副手物品
        boolean shouldUseOffhand = false;
        boolean isOffhandBlock = OffhandUtils.isBlock(offhand);
        boolean isMainhandBlock = OffhandUtils.isBlock(mainhand);
        
        if (mainhand == null) {
            // 主手为空，使用副手物品
            shouldUseOffhand = true;
        } else if (offhandItem instanceof ItemBow) {
            // 副手弓箭优先于主手
            shouldUseOffhand = true;
        } else if (preCheckAction == EnumItemInUseAction.EAT || preCheckAction == EnumItemInUseAction.DRINK) {
            // 副手食物/饮品优先于主手，但主手弓箭优先于副手食物
            if (!(mainhand.getItem() instanceof ItemBow)) {
                shouldUseOffhand = true;
            }
        } else if (isOffhandBlock && !isMainhandBlock) {
            // 主手有物品但不是方块，副手是方块 -> 使用副手放置方块
            // 这允许：主手拿铲子，副手拿沙砾，右键放置沙砾
            shouldUseOffhand = true;
        }
        
        if (!shouldUseOffhand) return;

        // 检查副手物品是否可以摄食
        EnumItemInUseAction itemInUseAction = offhand.getItemInUseAction(entityPlayer);
        boolean isIngestionAction = (itemInUseAction == EnumItemInUseAction.EAT || itemInUseAction == EnumItemInUseAction.DRINK);
        // 检查玩家是否可以摄食这个物品
        boolean canIngest = isIngestionAction && entityPlayer.canIngest(offhand);
        
        // ===== 食物/饮品：直接走 startUsingOffhandItem =====
        // 不走 onItemRightClick，避免 MITE 食物系统与临时交换产生冲突
        // （当主手也是食物/药水时，onItemRightClick 会导致副手物品错误留在主手槽位）
        if (canIngest) {
            String actionDesc = (itemInUseAction == EnumItemInUseAction.EAT) ? "副手食物进食" : "副手药水饮用";
            OffhandLog.info("[OFFHAND] {}: {}", actionDesc, offhand.getItem().getItemDisplayName(offhand));
            startUsingOffhandItem(entityPlayer, offhandAccess, offhand);
            return;
        }
        
        // ===== 非食物物品（弓、方块等）：通过 onItemRightClick 处理 =====
        // 临时将副手物品设置为主手
        int currentSlot = entityPlayer.inventory.currentItem;
        ItemStack originalMainhand = entityPlayer.inventory.mainInventory[currentSlot];
        entityPlayer.inventory.mainInventory[currentSlot] = offhand;
        
        // 保存调用前所有快捷栏槽位的状态，用于检测鼠标滚轮滚动导致的物品错位
        ItemStack[] hotbarBefore = OffhandUtils.copyHotbar(entityPlayer.inventory);
        
        boolean success = offhandItem.onItemRightClick(entityPlayer, OffhandConstants.ITEM_USE_SPEED, this.ctrlIsDown);
        
        // 检查 currentItem 是否在调用期间改变了（玩家滚动了鼠标滚轮）
        int newCurrentSlot = entityPlayer.inventory.currentItem;
        if (newCurrentSlot != currentSlot) {
            // 玩家滚动了鼠标滚轮，currentItem 已改变
            // MITE 的 convertOneItem 使用 this.currentItem，可能把物品放到了错误的槽位
            // 需要修复：把错误槽位的物品移回正确位置
            
            ItemStack wrongSlotItem = entityPlayer.inventory.mainInventory[newCurrentSlot];
            
            // 恢复新槽位为原来的物品
            entityPlayer.inventory.mainInventory[newCurrentSlot] = hotbarBefore[newCurrentSlot];
            
            // 如果 wrongSlotItem 是新产生的物品（如水桶），它应该成为副手物品
            if (wrongSlotItem != null && wrongSlotItem != hotbarBefore[newCurrentSlot]) {
                // 新物品被放到了错误的槽位，把它作为使用后的副手物品
                entityPlayer.inventory.mainInventory[currentSlot] = wrongSlotItem;
            }
            
            // 同步被影响的槽位到客户端
            if (entityPlayer instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, newCurrentSlot, entityPlayer.inventory.mainInventory[newCurrentSlot]);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, entityPlayer.inventory.mainInventory[currentSlot]);
            }
        }

        if (success) {
            // 检查物品是否设置了 itemInUse（如弓等需要持续使用的物品）
            if (entityPlayer.isUsingItem()) {
                // 物品正在使用中，不要恢复主手，等使用完成后在 onItemUseFinish/stopUsingItem 中恢复
                EnumItemInUseAction action = offhand.getItemInUseAction(entityPlayer);
                String actionDesc = getActionDescription(action, offhand);
                OffhandLog.info("[OFFHAND] {}: {}", actionDesc, offhand.getItem().getItemDisplayName(offhand));
                offhandAccess.miteassistant$setUsingOffhand(true);
                offhandAccess.miteassistant$setOriginalMainhand(originalMainhand);
                offhandAccess.miteassistant$setOriginalSlot(currentSlot);
                offhandAccess.miteassistant$setOffhandStack(null);
                
                // 同步副手状态到客户端（副手为空，正在使用副手物品，附带原始主手用于渲染）
                if (entityPlayer instanceof ServerPlayer serverPlayer) {
                    OffhandNetworkHelper.syncOffhandToClient(serverPlayer, null, true, originalMainhand);
                }
                return;
            }
            
            // 物品不需要持续使用，立即处理完成
            ItemStack usedOffhand = entityPlayer.inventory.mainInventory[currentSlot];
            
            // 恢复主手
            entityPlayer.inventory.mainInventory[currentSlot] = originalMainhand;
            
            // 更新副手物品状态
            ItemStack newOffhand = (usedOffhand == null || usedOffhand.stackSize <= 0) ? null : usedOffhand;
            offhandAccess.miteassistant$setOffhandStack(newOffhand);
            
            // 同步到客户端
            if (entityPlayer instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
            }
            return;
        }
        
        // onItemRightClick 返回 false，恢复主手
        entityPlayer.inventory.mainInventory[currentSlot] = originalMainhand;
        
        // 同步主手槽位（即使失败也要同步，因为可能 MITE 已经发送了同步包）
        if (entityPlayer instanceof ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
        }
    }
    
    /**
     * 根据物品使用动作获取描述
     */
    private String getActionDescription(EnumItemInUseAction action, ItemStack item) {
        if (action == EnumItemInUseAction.BOW) {
            return "副手弓射箭";
        } else if (action == EnumItemInUseAction.EAT) {
            return "副手食物进食";
        } else if (action == EnumItemInUseAction.DRINK) {
            return "副手药水饮用";
        } else if (action == EnumItemInUseAction.BLOCK) {
            return "副手盾牌格挡";
        } else if (OffhandUtils.isBlock(item)) {
            return "副手方块放置";
        } else {
            return "副手物品使用";
        }
    }
    
    /**
     * 开始使用副手物品（用于摄食、喝药水等需要持续使用的物品）
     * 需要临时将副手物品放到主手位置，然后调用 setHeldItemInUse
     * 注意：不立即恢复主手，等物品使用完成后在 onItemUseFinish 中恢复
     */
    private void startUsingOffhandItem(EntityPlayer player, OffhandAccess offhandAccess, ItemStack offhand) {
        // 保存原始主手物品
        int currentSlot = player.inventory.currentItem;
        ItemStack originalMainhand = player.inventory.mainInventory[currentSlot];
        
        // 设置标记：正在使用副手物品
        offhandAccess.miteassistant$setUsingOffhand(true);
        offhandAccess.miteassistant$setOriginalMainhand(originalMainhand);
        offhandAccess.miteassistant$setOriginalSlot(currentSlot);  // 保存原始槽位
        
        // 将副手物品放到主手位置
        player.inventory.mainInventory[currentSlot] = offhand;
        
        // 清空副手（因为物品已经在主手了）
        offhandAccess.miteassistant$setOffhandStack(null);
        
        // 调用原版的 setHeldItemInUse 方法
        boolean success = player.setHeldItemInUse();
        
        if (!success) {
            // 如果设置失败，恢复状态
            player.inventory.mainInventory[currentSlot] = originalMainhand;
            offhandAccess.miteassistant$setOffhandStack(offhand);
            offhandAccess.miteassistant$setUsingOffhand(false);
            offhandAccess.miteassistant$setOriginalMainhand(null);
            offhandAccess.miteassistant$setOriginalSlot(-1);
            OffhandLog.warn("[OFFHAND] Failed to start using offhand item: {}", offhand.getItem().getItemDisplayName(offhand));
        } else {
            // 同步副手状态到客户端（副手为空，正在使用副手物品，附带原始主手用于渲染）
            if (player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, null, true, originalMainhand);
            }
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
