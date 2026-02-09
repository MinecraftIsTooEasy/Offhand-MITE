package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import com.m.offhand.util.OffhandValidator;
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
        
        // 检查配置是否启用
        if (!OffhandConfig.enableOffhand.get()) {
            OffhandLog.debug("[OFFHAND] Use failed: offhand is disabled in config");
            return;
        }
        
        // 获取副手物品
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(entityPlayer);
        if (offhandAccess == null) {
            OffhandLog.warn("[OFFHAND] Use failed: player does not implement OffhandAccess");
            return;
        }
        
        // 如果已经正在使用副手物品，不重复处理（防止狂按导致物品丢失）
        if (OffhandUtils.isPlayerBusy(entityPlayer, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Use failed: player is busy");
            return;
        }
        
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) {
            OffhandLog.debug("[OFFHAND] Use failed: offhand is empty or invalid");
            return;
        }
        
        // 严格验证模式下的状态检查
        if (OffhandConfig.isStrictValidationEnabled()) {
            if (!OffhandValidator.validateUse(entityPlayer, offhandAccess)) {
                OffhandLog.warn("[OFFHAND] Use failed: validation failed");
                
                // 尝试恢复状态
                if (OffhandConfig.isAutoRecoveryEnabled()) {
                    OffhandValidator.recoverState(entityPlayer, offhandAccess);
                }
                return;
            }
        }
        
        Item offhandItem = offhand.getItem();
        if (offhandItem == null) {
            OffhandLog.warn("[OFFHAND] Use failed: offhand item has no item object");
            return;
        }
        
        // 检查物品是否被禁用
        String itemId = getItemId(offhand);
        if (OffhandConfig.isItemDisabled(itemId)) {
            OffhandLog.debug("[OFFHAND] Use failed: item {} is disabled", itemId);
            return;
        }
        
        // 禁止副手使用钓鱼竿（会导致错误）
        if (offhandItem instanceof ItemFishingRod) {
            OffhandLog.debug("[OFFHAND] Use failed: fishing rod not allowed in offhand");
            return;
        }
        
        // 禁止副手格挡（狂按右键会导致鼠标滚轮滚动物品栏，物品会被错误交换到副手）
        // 检查物品是否会触发格挡动作（如剑、铲子等）
        EnumItemInUseAction preCheckAction = offhand.getItemInUseAction(entityPlayer);
        if (preCheckAction == EnumItemInUseAction.BLOCK) {
            OffhandLog.debug("[OFFHAND] Use failed: blocking items not allowed in offhand");
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
        } else if (offhandItem instanceof ItemBow && OffhandConfig.enableBowOffhand.get()) {
            // 副手弓箭优先于主手（如果启用）
            shouldUseOffhand = true;
        } else if ((preCheckAction == EnumItemInUseAction.EAT || preCheckAction == EnumItemInUseAction.DRINK) 
                   && OffhandConfig.enableFoodOffhand.get()) {
            // 副手食物/饮品优先于主手，但主手弓箭优先于副手食物（如果启用）
            if (!(mainhand.getItem() instanceof ItemBow)) {
                shouldUseOffhand = true;
            }
        } else if (isOffhandBlock && !isMainhandBlock && OffhandConfig.enableBlockOffhand.get()) {
            // 主手有物品但不是方块，副手是方块 -> 使用副手放置方块（如果启用）
            // 这允许：主手拿铲子，副手拿沙砾，右键放置沙砾
            shouldUseOffhand = true;
        }
        
        if (!shouldUseOffhand) {
            OffhandLog.debug("[OFFHAND] Use skipped: conditions not met");
            return;
        }

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
            
            OffhandLog.warn("[OFFHAND] Player scrolled from slot {} to {} during onItemRightClick", currentSlot, newCurrentSlot);
            
            ItemStack wrongSlotItem = entityPlayer.inventory.mainInventory[newCurrentSlot];
            
            // 恢复新槽位为原来的物品
            entityPlayer.inventory.mainInventory[newCurrentSlot] = hotbarBefore[newCurrentSlot];
            
            // 如果 wrongSlotItem 是新产生的物品（如水桶），它应该成为副手物品
            if (wrongSlotItem != null && wrongSlotItem != hotbarBefore[newCurrentSlot]) {
                // 新物品被放到了错误的槽位，把它作为使用后的副手物品
                entityPlayer.inventory.mainInventory[currentSlot] = wrongSlotItem;
            }
            
            // 更新原始槽位为新槽位，但保留原来的 originalMainhand
            // 因为新槽位可能是空的，我们需要保存的是使用副手前的原始主手物品
            currentSlot = newCurrentSlot;
            // 注意：不更新 originalMainhand，保持原来的值
            
            OffhandLog.info("[OFFHAND] Updated original slot to {} (keeping original mainhand: {})", 
                currentSlot, originalMainhand != null ? 
                originalMainhand.getItem().getItemDisplayName(originalMainhand) : "null");
            
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
                    syncWithRetry(serverPlayer, null, true, originalMainhand);
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
                syncWithRetry(serverPlayer, newOffhand);
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
     * 获取物品ID用于配置检查
     */
    private String getItemId(ItemStack stack) {
        if (stack == null || stack.getItem() == null) {
            return "unknown";
        }
        return stack.getItem().getUnlocalizedName();
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
        
        // 保存调用前所有快捷栏槽位的状态，用于检测鼠标滚轮滚动导致的物品错位
        ItemStack[] hotbarBefore = OffhandUtils.copyHotbar(player.inventory);
        
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
        
        // 检查 currentItem 是否在调用期间改变了（玩家滚动了鼠标滚轮）
        int newCurrentSlot = player.inventory.currentItem;
        if (newCurrentSlot != currentSlot) {
            // 玩家滚动了鼠标滚轮，currentItem 已改变
            // MITE 的 setHeldItemInUse 使用 this.currentItem，可能把物品放到了错误的槽位
            // 需要修复：把物品放回正确的槽位
            
            OffhandLog.warn("[OFFHAND] Player scrolled from slot {} to {} during setHeldItemInUse", currentSlot, newCurrentSlot);
            
            // 获取新槽位的物品（可能是被错误放置的物品）
            ItemStack wrongSlotItem = player.inventory.mainInventory[newCurrentSlot];
            
            // 恢复新槽位为原来的物品
            player.inventory.mainInventory[newCurrentSlot] = hotbarBefore[newCurrentSlot];
            
            // 将正在使用的物品放回原始槽位
            player.inventory.mainInventory[currentSlot] = offhand;
            
            // 更新原始槽位为新槽位，但保留原来的 originalMainhand
            // 因为新槽位可能是空的，我们需要保存的是使用副手前的原始主手物品
            offhandAccess.miteassistant$setOriginalSlot(newCurrentSlot);
            // 注意：不更新 originalMainhand，保持原来的值
            
            OffhandLog.info("[OFFHAND] Updated original slot to {} (keeping original mainhand: {})", 
                newCurrentSlot, originalMainhand != null ? 
                originalMainhand.getItem().getItemDisplayName(originalMainhand) : "null");
            
            // 同步被影响的槽位到客户端
            if (player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, newCurrentSlot, player.inventory.mainInventory[newCurrentSlot]);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, player.inventory.mainInventory[currentSlot]);
            }
        }
        
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
                syncWithRetry(serverPlayer, null, true, offhandAccess.miteassistant$getOriginalMainhand());
            }
        }
    }
    
    /**
     * 带重试机制的同步
     */
    private void syncWithRetry(net.minecraft.ServerPlayer serverPlayer, ItemStack offhand) {
        syncWithRetry(serverPlayer, offhand, false, null);
    }
    
    private void syncWithRetry(net.minecraft.ServerPlayer serverPlayer, ItemStack offhand, boolean isUsingOffhand, ItemStack originalMainhand) {
        int maxRetries = OffhandConfig.getSyncRetries();
        long retryDelay = OffhandConstants.SYNC_RETRY_DELAY_MS;
        
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, offhand, isUsingOffhand, originalMainhand);
                OffhandLog.debug("[OFFHAND] Sync attempt {} successful", attempt + 1);
                return;
            } catch (Exception e) {
                OffhandLog.warn("[OFFHAND] Sync attempt {} failed: {}", attempt + 1, e.getMessage());
                
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        
        OffhandLog.error("[OFFHAND] Failed to sync offhand after {} attempts", maxRetries + 1);
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
