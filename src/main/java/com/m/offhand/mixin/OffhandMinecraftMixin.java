package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.network.OffhandPacketHandler;
import com.m.offhand.network.UseOffhandC2SPacket;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class OffhandMinecraftMixin {
    
    @Shadow
    public EntityClientPlayerMP thePlayer;
    
    @Shadow
    public GameSettings gameSettings;
    
    private long miteassistant$lastOffhandBlockTime = 0;
    private static final long OFFHAND_BLOCK_COOLDOWN_MS = 200;
    
    @Inject(method = "clickMouse", at = @At("HEAD"), cancellable = true)
    private void miteassistant$onClickMouse(int button, CallbackInfo ci) {
        if (button != 1) return;
        
        EntityPlayer player = this.thePlayer;
        if (player == null) return;
        
        if (!(player instanceof OffhandAccess offhandAccess)) return;
        
        if (offhandAccess.miteassistant$isUsingOffhand()) {
            ci.cancel();
            return;
        }
        
        if (player.isUsingItem()) return;
        
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (offhand == null || offhand.stackSize <= 0) return;
        
        ItemStack mainhand = player.getHeldItemStack();
        if (mainhand == null) return;
        
        Item offhandItem = offhand.getItem();
        Item mainhandItem = mainhand.getItem();
        
        boolean isOffhandBlock = offhandItem instanceof ItemBlock;
        boolean isMainhandBlock = mainhandItem instanceof ItemBlock;
        
        boolean isOffhandBow = offhandItem instanceof ItemBow;
        
        boolean isMainhandBow = mainhandItem instanceof ItemBow;
        
        EnumItemInUseAction offhandAction = offhand.getItemInUseAction(player);
        boolean isOffhandFood = offhandAction == EnumItemInUseAction.EAT 
                             || offhandAction == EnumItemInUseAction.DRINK;
        
        boolean shouldUseOffhand = isOffhandBow 
                || (isOffhandFood && !isMainhandBow)
                || (isOffhandBlock && !isMainhandBlock);
        if (!shouldUseOffhand) return;
        
        ci.cancel();
        
        long now = System.currentTimeMillis();
        if (now - miteassistant$lastOffhandBlockTime < OFFHAND_BLOCK_COOLDOWN_MS) return;
        miteassistant$lastOffhandBlockTime = now;
        
        System.out.println("[OFFHAND] 拦截右键：主手=" + mainhand.getItem().getItemDisplayName(mainhand) + 
            ", 副手=" + offhand.getItem().getItemDisplayName(offhand) + " -> 使用副手物品");
        
        boolean ctrlIsDown = GuiScreen.isCtrlKeyDown();
        OffhandPacketHandler.sendToServer(new UseOffhandC2SPacket(ctrlIsDown));
    }
}
