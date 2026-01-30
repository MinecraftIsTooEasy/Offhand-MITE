package com.mitemod.miteoffhand.mixin;

import com.mitemod.miteoffhand.api.OffhandAccess;
import com.mitemod.miteoffhand.network.UseOffhandC2SPacket;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin 拦截 Minecraft 的右键处理
 * 当主手是工具（非方块）且副手是方块时，优先使用副手放置方块
 */
@Mixin(Minecraft.class)
public abstract class OffhandMinecraftMixin {
    
    @Shadow
    public EntityClientPlayerMP thePlayer;
    
    @Shadow
    public GameSettings gameSettings;
    
    // 冷却时间，防止重复触发
    private long miteassistant$lastOffhandBlockTime = 0;
    private static final long OFFHAND_BLOCK_COOLDOWN_MS = 200;
    
    /**
     * 在 clickMouse 方法开始时检查是否应该优先使用副手方块
     * 如果是，发送副手使用请求并取消原版右键处理
     */
    @Inject(method = "clickMouse", at = @At("HEAD"), cancellable = true)
    private void miteassistant$onClickMouse(int button, CallbackInfo ci) {
        // 只处理右键（button == 1）
        if (button != 1) return;
        
        EntityPlayer player = this.thePlayer;
        if (player == null) return;
        
        if (!(player instanceof OffhandAccess offhandAccess)) return;
        
        // 如果正在使用副手物品，不处理
        if (offhandAccess.miteassistant$isUsingOffhand()) return;
        
        // 如果玩家正在使用任何物品，不处理
        if (player.isUsingItem()) return;
        
        // 检查副手物品
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (offhand == null || offhand.stackSize <= 0) return;
        
        // 检查主手物品
        ItemStack mainhand = player.getHeldItemStack();
        if (mainhand == null) return; // 主手为空，让原有逻辑处理
        
        // 判断是否应该使用副手方块放置
        Item offhandItem = offhand.getItem();
        Item mainhandItem = mainhand.getItem();
        
        boolean isOffhandBlock = offhandItem instanceof ItemBlock;
        boolean isMainhandBlock = mainhandItem instanceof ItemBlock;
        
        // 只有当副手是方块且主手不是方块时，才优先使用副手
        if (!isOffhandBlock || isMainhandBlock) return;
        
        // 冷却检查
        long now = System.currentTimeMillis();
        if (now - miteassistant$lastOffhandBlockTime < OFFHAND_BLOCK_COOLDOWN_MS) return;
        miteassistant$lastOffhandBlockTime = now;
        
        // 调试输出
        System.out.println("[OFFHAND] 拦截右键：主手=" + mainhand.getItem().getItemDisplayName(mainhand) + 
            ", 副手=" + offhand.getItem().getItemDisplayName(offhand) + " -> 使用副手放置方块");
        
        // 发送副手使用请求到服务器
        boolean ctrlIsDown = GuiScreen.isCtrlKeyDown();
        Network.sendToServer(new UseOffhandC2SPacket(ctrlIsDown));
        
        // 取消原版右键处理，防止主手工具格挡
        ci.cancel();
    }
}
