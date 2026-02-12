package com.m.offhand.api;

import net.minecraft.ItemStack;

public interface OffhandAccess {
    ItemStack miteassistant$getOffhandStack();

    void miteassistant$setOffhandStack(ItemStack stack);
    
    ItemStack miteassistant$getStackInHand(Hand hand);
    
    void miteassistant$setStackInHand(Hand hand, ItemStack stack);
    
    boolean miteassistant$isUsingOffhand();
    
    void miteassistant$setUsingOffhand(boolean using);
    
    ItemStack miteassistant$getOriginalMainhand();
    
    void miteassistant$setOriginalMainhand(ItemStack stack);
    
    int miteassistant$getOriginalSlot();
    
    void miteassistant$setOriginalSlot(int slot);
    
    Hand miteassistant$getActiveHand();
    
    void miteassistant$setActiveHand(Hand hand);
}
