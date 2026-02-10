package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.core.OffhandStateManager;
import com.m.offhand.util.OffhandUtils;
import net.minecraft.EnumItemInUseAction;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class OffhandEntityPlayerMixin implements OffhandAccess {
    @Shadow
    public InventoryPlayer inventory;

    @Unique
    private ItemStack miteassistant$offhand;
    
    @Unique
    private boolean miteassistant$usingOffhand = false;
    
    @Unique
    private ItemStack miteassistant$originalMainhand = null;
    
    @Unique
    private int miteassistant$originalSlot = -1;

    @Override
    public ItemStack miteassistant$getOffhandStack() {
        return this.miteassistant$offhand;
    }

    @Override
    public void miteassistant$setOffhandStack(ItemStack stack) {
        this.miteassistant$offhand = stack;
        OffhandStateManager.syncFromMixin((EntityPlayer) (Object) this);
    }
    
    @Override
    public boolean miteassistant$isUsingOffhand() {
        return this.miteassistant$usingOffhand;
    }
    
    @Override
    public void miteassistant$setUsingOffhand(boolean using) {
        this.miteassistant$usingOffhand = using;
        OffhandStateManager.syncFromMixin((EntityPlayer) (Object) this);
    }
    
    @Override
    public ItemStack miteassistant$getOriginalMainhand() {
        return this.miteassistant$originalMainhand;
    }
    
    @Override
    public void miteassistant$setOriginalMainhand(ItemStack stack) {
        this.miteassistant$originalMainhand = stack;
        OffhandStateManager.syncFromMixin((EntityPlayer) (Object) this);
    }
    
    @Override
    public int miteassistant$getOriginalSlot() {
        return this.miteassistant$originalSlot;
    }
    
    @Override
    public void miteassistant$setOriginalSlot(int slot) {
        this.miteassistant$originalSlot = slot;
        OffhandStateManager.syncFromMixin((EntityPlayer) (Object) this);
    }

    @Unique
    private boolean miteassistant$shouldInterceptOffhand() {
        return this.miteassistant$usingOffhand && this.inventory.getCurrentItemStack() != null;
    }

    @Inject(method = "isUsingItem", at = @At("HEAD"), cancellable = true)
    private void miteassistant$isUsingItem(CallbackInfoReturnable<Boolean> cir) {
        if (this.miteassistant$shouldInterceptOffhand()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getItemInUse", at = @At("HEAD"), cancellable = true)
    private void miteassistant$getItemInUse(CallbackInfoReturnable<ItemStack> cir) {
        if (this.miteassistant$usingOffhand) {
            ItemStack held = this.inventory.getCurrentItemStack();
            if (held != null) {
                cir.setReturnValue(held);
            }
        }
    }

    @Inject(method = "getItemInUseCount", at = @At("HEAD"), cancellable = true)
    private void miteassistant$getItemInUseCount(CallbackInfoReturnable<Integer> cir) {
        if (this.miteassistant$usingOffhand) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "getItemInUseDuration", at = @At("HEAD"), cancellable = true)
    private void miteassistant$getItemInUseDuration(CallbackInfoReturnable<Integer> cir) {
        if (this.miteassistant$usingOffhand) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
    private void miteassistant$isBlocking(CallbackInfoReturnable<Boolean> cir) {
        if (this.miteassistant$usingOffhand) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void miteassistant$playOffhandUseSound(CallbackInfo ci) {
        EntityPlayer self = (EntityPlayer) (Object) this;
        if (!self.worldObj.isRemote || !this.miteassistant$usingOffhand) {
            return;
        }

        ItemStack held = this.inventory.getCurrentItemStack();
        if (held == null || self.ticksExisted % 4 != 0) {
            return;
        }

        EnumItemInUseAction action = held.getItemInUseAction(self);
        if (action == EnumItemInUseAction.DRINK) {
            self.worldObj.playSound(self.posX, self.posY, self.posZ,
                    "random.drink", 0.5F, self.worldObj.rand.nextFloat() * 0.1F + 0.9F, false);
        } else if (action == EnumItemInUseAction.EAT) {
            self.worldObj.playSound(self.posX, self.posY, self.posZ,
                    "random.eat", 0.5F + 0.5F * self.worldObj.rand.nextInt(2),
                    (self.worldObj.rand.nextFloat() - self.worldObj.rand.nextFloat()) * 0.2F + 1.0F, false);
        }
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void miteassistant$writeOffhand(NBTTagCompound tag, CallbackInfo ci) {
        if (this.miteassistant$offhand != null) {
            NBTTagCompound stackTag = new NBTTagCompound();
            this.miteassistant$offhand.writeToNBT(stackTag);
            tag.setCompoundTag("MiteAssistantOffhand", stackTag);
        }
        
        OffhandStateManager.saveStateToNBT((EntityPlayer) (Object) this, tag);
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void miteassistant$readOffhand(NBTTagCompound tag, CallbackInfo ci) {
        if (tag.hasKey("MiteAssistantOffhand")) {
            NBTTagCompound stackTag = tag.getCompoundTag("MiteAssistantOffhand");
            this.miteassistant$offhand = ItemStack.loadItemStackFromNBT(stackTag);
        } else {
            this.miteassistant$offhand = null;
        }
        
        OffhandStateManager.loadStateFromNBT((EntityPlayer) (Object) this, tag);
    }

    @Inject(method = "clonePlayer", at = @At("RETURN"))
    private void miteassistant$cloneOffhand(EntityPlayer oldPlayer, boolean par2, CallbackInfo ci) {
        if (oldPlayer instanceof OffhandAccess old) {
            this.miteassistant$offhand = old.miteassistant$getOffhandStack();
            OffhandStateManager.syncFromMixin((EntityPlayer) (Object) this);
        }
    }
}

