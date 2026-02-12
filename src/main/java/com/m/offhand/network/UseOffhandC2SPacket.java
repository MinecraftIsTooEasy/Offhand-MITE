package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.Hand;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;

public class UseOffhandC2SPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.MITEOFFHAND, "use_offhand");
    private static final float ITEM_USE_SPEED = 1.0F;

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
        if (entityPlayer.worldObj.isRemote) return;
        if (!OffhandConfig.enableOffhand.get()) return;
        
        OffhandAccess access = (OffhandAccess) entityPlayer;
        if (OffhandUtils.isPlayerBusy(entityPlayer, access)) return;
        
        ItemStack offhand = access.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) return;
        
        Item offhandItem = offhand.getItem();
        if (offhandItem == null) return;
        if (offhandItem instanceof ItemFishingRod) return;
        
        EnumItemInUseAction action = offhand.getItemInUseAction(entityPlayer);
        if (action == EnumItemInUseAction.BLOCK) return;
        
        ItemStack mainhand = entityPlayer.getHeldItemStack();
        
        boolean shouldUseOffhand = false;
        if (mainhand == null) {
            shouldUseOffhand = true;
        } else if (offhandItem instanceof ItemBow && OffhandConfig.enableBowOffhand.get()) {
            shouldUseOffhand = true;
        } else if ((action == EnumItemInUseAction.EAT || action == EnumItemInUseAction.DRINK) 
                   && OffhandConfig.enableFoodOffhand.get()) {
            if (!(mainhand.getItem() instanceof ItemBow)) {
                shouldUseOffhand = true;
            }
        } else if (OffhandUtils.isBlock(offhand) && !OffhandUtils.isBlock(mainhand) && OffhandConfig.enableBlockOffhand.get()) {
            shouldUseOffhand = true;
        }
        
        if (!shouldUseOffhand) return;

        boolean isIngestionAction = (action == EnumItemInUseAction.EAT || action == EnumItemInUseAction.DRINK);
        boolean canIngest = isIngestionAction && entityPlayer.canIngest(offhand);
        
        if (canIngest) {
            startUsingOffhandItem(entityPlayer, access, offhand);
            return;
        }
        
        int currentSlot = entityPlayer.inventory.currentItem;
        ItemStack originalMainhand = entityPlayer.inventory.mainInventory[currentSlot];
        entityPlayer.inventory.mainInventory[currentSlot] = offhand;
        
        ItemStack[] hotbarBefore = OffhandUtils.copyHotbar(entityPlayer.inventory);
        
        boolean success = offhandItem.onItemRightClick(entityPlayer, ITEM_USE_SPEED, this.ctrlIsDown);
        
        int newCurrentSlot = entityPlayer.inventory.currentItem;
        if (newCurrentSlot != currentSlot) {
            ItemStack wrongSlotItem = entityPlayer.inventory.mainInventory[newCurrentSlot];
            entityPlayer.inventory.mainInventory[newCurrentSlot] = hotbarBefore[newCurrentSlot];
            if (wrongSlotItem != null && wrongSlotItem != hotbarBefore[newCurrentSlot]) {
                entityPlayer.inventory.mainInventory[currentSlot] = wrongSlotItem;
            }
            currentSlot = newCurrentSlot;
            
            if (entityPlayer instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, newCurrentSlot, entityPlayer.inventory.mainInventory[newCurrentSlot]);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, entityPlayer.inventory.mainInventory[currentSlot]);
            }
        }

        if (success) {
            if (entityPlayer.isUsingItem()) {
                access.miteassistant$setUsingOffhand(true);
                access.miteassistant$setActiveHand(Hand.OFF_HAND);
                access.miteassistant$setOriginalMainhand(originalMainhand);
                access.miteassistant$setOriginalSlot(currentSlot);
                access.miteassistant$setOffhandStack(null);
                
                if (entityPlayer instanceof ServerPlayer serverPlayer) {
                    OffhandNetworkHelper.syncOffhandToClient(serverPlayer, null, true, originalMainhand, Hand.OFF_HAND);
                }
                return;
            }
            
            ItemStack usedOffhand = entityPlayer.inventory.mainInventory[currentSlot];
            entityPlayer.inventory.mainInventory[currentSlot] = originalMainhand;
            
            ItemStack newOffhand = (usedOffhand == null || usedOffhand.stackSize <= 0) ? null : usedOffhand;
            access.miteassistant$setOffhandStack(newOffhand);
            
            if (entityPlayer instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
            }
            return;
        }
        
        entityPlayer.inventory.mainInventory[currentSlot] = originalMainhand;
        
        if (entityPlayer instanceof ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
        }
    }
    
    private void startUsingOffhandItem(EntityPlayer player, OffhandAccess access, ItemStack offhand) {
        int currentSlot = player.inventory.currentItem;
        ItemStack originalMainhand = player.inventory.mainInventory[currentSlot];
        
        ItemStack[] hotbarBefore = OffhandUtils.copyHotbar(player.inventory);
        
        access.miteassistant$setUsingOffhand(true);
        access.miteassistant$setActiveHand(Hand.OFF_HAND);
        access.miteassistant$setOriginalMainhand(originalMainhand);
        access.miteassistant$setOriginalSlot(currentSlot);
        
        player.inventory.mainInventory[currentSlot] = offhand;
        access.miteassistant$setOffhandStack(null);
        
        boolean success = player.setHeldItemInUse();
        
        int newCurrentSlot = player.inventory.currentItem;
        if (newCurrentSlot != currentSlot) {
            ItemStack wrongSlotItem = player.inventory.mainInventory[newCurrentSlot];
            player.inventory.mainInventory[newCurrentSlot] = hotbarBefore[newCurrentSlot];
            player.inventory.mainInventory[currentSlot] = offhand;
            access.miteassistant$setOriginalSlot(newCurrentSlot);
            
            if (player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, newCurrentSlot, player.inventory.mainInventory[newCurrentSlot]);
                OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, player.inventory.mainInventory[currentSlot]);
            }
        }
        
        if (!success) {
            player.inventory.mainInventory[currentSlot] = originalMainhand;
            access.miteassistant$setOffhandStack(offhand);
            access.miteassistant$setUsingOffhand(false);
            access.miteassistant$setActiveHand(Hand.MAIN_HAND);
            access.miteassistant$setOriginalMainhand(null);
            access.miteassistant$setOriginalSlot(-1);
        } else {
            if (player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, null, true, access.miteassistant$getOriginalMainhand(), Hand.OFF_HAND);
            }
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
