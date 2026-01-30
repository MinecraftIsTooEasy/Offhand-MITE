package com.mitemod.miteoffhand.mixin;

import com.mitemod.miteoffhand.api.OffhandAccess;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class OffhandEntityPlayerMixin implements OffhandAccess {
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
    }
    
    @Override
    public boolean miteassistant$isUsingOffhand() {
        return this.miteassistant$usingOffhand;
    }
    
    @Override
    public void miteassistant$setUsingOffhand(boolean using) {
        this.miteassistant$usingOffhand = using;
    }
    
    @Override
    public ItemStack miteassistant$getOriginalMainhand() {
        return this.miteassistant$originalMainhand;
    }
    
    @Override
    public void miteassistant$setOriginalMainhand(ItemStack stack) {
        this.miteassistant$originalMainhand = stack;
    }
    
    @Override
    public int miteassistant$getOriginalSlot() {
        return this.miteassistant$originalSlot;
    }
    
    @Override
    public void miteassistant$setOriginalSlot(int slot) {
        this.miteassistant$originalSlot = slot;
    }

    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    private void miteassistant$writeOffhand(NBTTagCompound tag, CallbackInfo ci) {
        if (this.miteassistant$offhand != null) {
            NBTTagCompound stackTag = new NBTTagCompound();
            this.miteassistant$offhand.writeToNBT(stackTag);
            tag.setCompoundTag("MiteAssistantOffhand", stackTag);
        }
    }

    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    private void miteassistant$readOffhand(NBTTagCompound tag, CallbackInfo ci) {
        if (tag.hasKey("MiteAssistantOffhand")) {
            NBTTagCompound stackTag = tag.getCompoundTag("MiteAssistantOffhand");
            this.miteassistant$offhand = ItemStack.loadItemStackFromNBT(stackTag);
        } else {
            this.miteassistant$offhand = null;
        }
    }

    @Inject(method = "clonePlayer", at = @At("RETURN"))
    private void miteassistant$cloneOffhand(EntityPlayer oldPlayer, boolean par2, CallbackInfo ci) {
        if (oldPlayer instanceof OffhandAccess old) {
            this.miteassistant$offhand = old.miteassistant$getOffhandStack();
        }
    }
}

