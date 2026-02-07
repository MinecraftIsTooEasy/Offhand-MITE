package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
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

    /**
     * 当副手物品正在使用时，让 isUsingItem() 返回 true。
     * 这样 MITE 的 runTick 就不会在副手进食/喝药水期间反复调用 clickMouse(1)，
     * 从根源上防止主手对槽位中的副手物品触发重复使用。
     * 
     * 注意：必须同时检查主手槽位有物品，否则物品被消耗后（如牛奶桶→空桶）
     * isUsingItem()==true 但 getItemInUse()==null，导致 getFOVMultiplier 等处 NPE。
     */
    @Inject(method = "isUsingItem", at = @At("HEAD"), cancellable = true)
    private void miteassistant$isUsingItem(CallbackInfoReturnable<Boolean> cir) {
        if (this.miteassistant$usingOffhand && this.inventory.getCurrentItemStack() != null) {
            cir.setReturnValue(true);
        }
    }

    /**
     * 当副手物品正在使用时，让 getItemInUse() 返回主手槽位的物品（即副手食物/药水）。
     * MITE 的多处代码在 isUsingItem()==true 后会直接调用 getItemInUse().getItem()，
     * 若返回 null 则会崩溃（如 ClientPlayer.getFOVMultiplier）。
     */
    @Inject(method = "getItemInUse", at = @At("HEAD"), cancellable = true)
    private void miteassistant$getItemInUse(CallbackInfoReturnable<ItemStack> cir) {
        if (this.miteassistant$usingOffhand) {
            ItemStack held = this.inventory.getCurrentItemStack();
            if (held != null) {
                cir.setReturnValue(held);
            }
        }
    }

    /**
     * 当副手物品正在使用时，返回 0。
     * 这样 MITE 的 renderItemInFirstPerson 中 "if (getItemInUseCount() > 0)" 为 false，
     * 不会对右手的 itemToRender（原始主手物品）施加进食/喝药水动画。
     * 左手动画由我们自己的渲染代码用 tick 计算驱动，不依赖此值。
     */
    @Inject(method = "getItemInUseCount", at = @At("HEAD"), cancellable = true)
    private void miteassistant$getItemInUseCount(CallbackInfoReturnable<Integer> cir) {
        if (this.miteassistant$usingOffhand) {
            cir.setReturnValue(0);
        }
    }

    /**
     * 防止 getItemInUseDuration 在 isUsingItem()==true 但 itemInUse==null 时 NPE。
     */
    @Inject(method = "getItemInUseDuration", at = @At("HEAD"), cancellable = true)
    private void miteassistant$getItemInUseDuration(CallbackInfoReturnable<Integer> cir) {
        if (this.miteassistant$usingOffhand) {
            cir.setReturnValue(0);
        }
    }

    /**
     * 防止 isBlocking 在 isUsingItem()==true 但 itemInUse==null 时 NPE。
     */
    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
    private void miteassistant$isBlocking(CallbackInfoReturnable<Boolean> cir) {
        if (this.miteassistant$usingOffhand) {
            cir.setReturnValue(false);
        }
    }

//    /**
//     * 客户端副手进食/喝药水音效。
//     * 原版 EntityPlayer.playSound 使用 playSoundToNearExcept（排除自己），
//     * 正常情况下客户端 onUpdate 中 updateItemUse 会为本地玩家播放音效，
//     * 但副手使用时客户端 itemInUse 为 null，音效不会播放。
//     * 这里在客户端 onUpdate 末尾手动播放，每4tick一次（与原版节奏一致）。
//     */
//    @Inject(method = "onUpdate", at = @At("TAIL"))
//    private void miteassistant$playOffhandUseSound(CallbackInfo ci) {
//        EntityPlayer self = (EntityPlayer) (Object) this;
//        if (!self.worldObj.isRemote) return; // 仅客户端
//        if (!this.miteassistant$usingOffhand) return;
//
//        ItemStack held = this.inventory.getCurrentItemStack();
//        if (held == null) return;
//
//        // 每4tick播放一次音效（与原版 updateItemUse 的调用频率一致）
//        if (self.ticksExisted % 4 == 0) {
//            EnumItemInUseAction action = held.getItemInUseAction(self);
//            if (action == EnumItemInUseAction.DRINK) {
//                self.worldObj.playSound(self.posX, self.posY, self.posZ,
//                        "random.drink", 0.5F, self.worldObj.rand.nextFloat() * 0.1F + 0.9F);
//            } else if (action == EnumItemInUseAction.EAT) {
//                self.worldObj.playSound(self.posX, self.posY, self.posZ,
//                        "random.eat", 0.5F + 0.5F * self.rand.nextInt(2),
//                        (self.rand.nextFloat() - self.rand.nextFloat()) * 0.2F + 1.0F);
//            }
//        }
//    }

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

