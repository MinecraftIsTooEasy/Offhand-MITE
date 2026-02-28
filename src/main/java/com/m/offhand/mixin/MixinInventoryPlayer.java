package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandInventory;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import net.minecraft.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(InventoryPlayer.class)
public abstract class MixinInventoryPlayer implements IOffhandInventory {

    @Shadow
    @Mutable
    public ItemStack[] mainInventory;

    @Shadow
    public EntityPlayer player;

    @Unique
    private int offhand$offhandSlot = 36;

    @Unique
    private ItemStack offhand$pendingOffhandFromNbt;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void offhand$ensureOffhandSlotInCtor(EntityPlayer player, CallbackInfo ci) {
        this.offhand$ensureOffhandSlot(false);
    }

    @Inject(method = "readFromNBT", at = @At("HEAD"))
    private void offhand$captureOffhandFromRead(NBTTagList tagList, CallbackInfo ci) {
        this.offhand$pendingOffhandFromNbt = null;
        if (tagList == null) {
            return;
        }

        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
            int slot = tag.getByte("Slot") & 255;
            if (slot == 36) {
                ItemStack stack = ItemStack.loadItemStackFromNBT(tag);
                if (stack != null && stack.stackSize > 0) {
                    this.offhand$pendingOffhandFromNbt = stack;
                }
                return;
            }
        }
    }

    @Inject(method = "readFromNBT", at = @At("TAIL"))
    private void offhand$ensureOffhandSlotAfterRead(NBTTagList tagList, CallbackInfo ci) {
        this.offhand$ensureOffhandSlot(true);
        if (this.offhand$pendingOffhandFromNbt != null
            && this.offhand$offhandSlot >= 0
            && this.offhand$offhandSlot < this.mainInventory.length) {
            this.mainInventory[this.offhand$offhandSlot] = this.offhand$pendingOffhandFromNbt;
        }
        this.offhand$pendingOffhandFromNbt = null;
    }

    @Unique
    private void offhand$ensureOffhandSlot(boolean resetSlotIndex) {
        if (this.mainInventory == null) {
            return;
        }

        if (resetSlotIndex || this.offhand$offhandSlot < 0 || this.offhand$offhandSlot >= this.mainInventory.length) {
            this.offhand$offhandSlot = this.mainInventory.length;
        }

        if (this.mainInventory.length <= this.offhand$offhandSlot) {
            this.mainInventory = Arrays.copyOf(this.mainInventory, this.offhand$offhandSlot + 1);
        }
    }

    @Inject(method = "getHotbarSlotContainArrow", at = @At("HEAD"), cancellable = true)
    private void offhand$getHotbarSlotContainArrow(CallbackInfoReturnable<Integer> cir) {
        ItemStack offhandStack = this.getOffhandItem();
        if (offhandStack != null && offhandStack.getItem() instanceof ItemArrow && offhandStack.stackSize > 0) {
            cir.setReturnValue(this.offhand$offhandSlot);
        }
    }

    @Inject(method = "getCurrentItemStack", at = @At("HEAD"), cancellable = true)
    private void offhand$getCurrentItemStack(CallbackInfoReturnable<ItemStack> cir) {
        if (this.player == null) {
            return;
        }

        int current = this.player.inventory.currentItem;
        if (current == this.offhand$offhandSlot && current >= 0 && current < this.mainInventory.length) {
            cir.setReturnValue(this.mainInventory[current]);
        }
    }

    @Inject(
        method = "getNextHotbarOrInventorySlotContainingMostSimilarItem",
        at = @At("RETURN"),
        cancellable = true)
    private void offhand$excludeOffhandFromAutoRestock(
        Item item,
        int itemSubtype,
        int hotbarSlotIndex,
        CallbackInfoReturnable<Integer> cir) {
        Integer slot = cir.getReturnValue();
        if (slot == null) {
            return;
        }

        if (slot.intValue() == this.offhand$offhandSlot) {
            // Keep offhand slot isolated so vanilla auto-restock never pulls it into mainhand.
            cir.setReturnValue(Integer.valueOf(-1));
        }
    }

    @Override
    public ItemStack getOffhandItem() {
        return this.offhand$offhandSlot >= 0 && this.offhand$offhandSlot < this.mainInventory.length
            ? this.mainInventory[this.offhand$offhandSlot]
            : null;
    }

    @Override
    public void setOffhandItem(ItemStack stack) {
        if (this.offhand$offhandSlot >= 0 && this.offhand$offhandSlot < this.mainInventory.length) {
            this.mainInventory[this.offhand$offhandSlot] = stack;
        }
    }

    @Override
    public int getOffhandSlot() {
        if (this.offhand$offhandSlot < 0 || this.offhand$offhandSlot >= this.mainInventory.length) {
            this.offhand$offhandSlot = Math.max(0, this.mainInventory.length - 1);
        }
        return this.offhand$offhandSlot;
    }
}
