package com.mitemod.miteoffhand.api;

import net.minecraft.ItemStack;

public interface OffhandAccess {
    ItemStack miteassistant$getOffhandStack();

    void miteassistant$setOffhandStack(ItemStack stack);
    
    /**
     * 检查是否正在使用副手物品
     */
    boolean miteassistant$isUsingOffhand();
    
    /**
     * 设置正在使用副手物品的状态
     */
    void miteassistant$setUsingOffhand(boolean using);
    
    /**
     * 获取使用副手时保存的原始主手物品
     */
    ItemStack miteassistant$getOriginalMainhand();
    
    /**
     * 设置原始主手物品
     */
    void miteassistant$setOriginalMainhand(ItemStack stack);
    
    /**
     * 获取使用副手时保存的原始主手槽位索引
     */
    int miteassistant$getOriginalSlot();
    
    /**
     * 设置原始主手槽位索引
     */
    void miteassistant$setOriginalSlot(int slot);
}
