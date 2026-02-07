package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.network.UseOffhandC2SPacket;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin 拦截 Minecraft 的右键处理
 * 当副手持有弓箭、食物或方块（且主手非方块）时，优先使用副手物品
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
        
        // 如果正在使用副手物品，必须取消右键
        // 原因：服务端已将副手食物/药水临时放入主手槽位，
        // 若不取消，clickMouse 会让游戏对主手槽位中的副手物品触发重复进食/喝药水
        if (offhandAccess.miteassistant$isUsingOffhand()) {
            ci.cancel();
            return;
        }
        
        // 如果玩家正在使用任何物品，不处理
        if (player.isUsingItem()) return;
        
        // 检查副手物品
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (offhand == null || offhand.stackSize <= 0) return;
        
        // 检查主手物品
        ItemStack mainhand = player.getHeldItemStack();
        if (mainhand == null) return; // 主手为空，由 OffhandKeybindListener 处理
        
        // 判断副手是否应该优先于主手
        Item offhandItem = offhand.getItem();
        Item mainhandItem = mainhand.getItem();
        
        boolean isOffhandBlock = offhandItem instanceof ItemBlock;
        boolean isMainhandBlock = mainhandItem instanceof ItemBlock;
        
        // 检查副手是否持有弓
        boolean isOffhandBow = offhandItem instanceof ItemBow;
        
        // 检查主手是否持有弓
        boolean isMainhandBow = mainhandItem instanceof ItemBow;
        
        // 检查副手是否持有食物/饮品
        EnumItemInUseAction offhandAction = offhand.getItemInUseAction(player);
        boolean isOffhandFood = offhandAction == EnumItemInUseAction.EAT 
                             || offhandAction == EnumItemInUseAction.DRINK;
        
        // 副手优先条件：
        // 1. 副手是弓箭 → 射击优先
        // 2. 副手是食物/饮品 → 进食优先（但主手弓箭优先于副手食物）
        // 3. 副手是方块且主手不是方块 → 放置方块优先
        boolean shouldUseOffhand = isOffhandBow 
                || (isOffhandFood && !isMainhandBow)
                || (isOffhandBlock && !isMainhandBlock);
        if (!shouldUseOffhand) return;
        
        // 副手应该优先 → 必须取消主手的右键处理（无论是否在冷却中）
        ci.cancel();
        
        // 冷却检查（仅控制是否发送数据包，不影响取消逻辑）
        long now = System.currentTimeMillis();
        if (now - miteassistant$lastOffhandBlockTime < OFFHAND_BLOCK_COOLDOWN_MS) return;
        miteassistant$lastOffhandBlockTime = now;
        
        // 调试输出
        System.out.println("[OFFHAND] 拦截右键：主手=" + mainhand.getItem().getItemDisplayName(mainhand) + 
            ", 副手=" + offhand.getItem().getItemDisplayName(offhand) + " -> 使用副手物品");
        
        // 发送副手使用请求到服务器
        boolean ctrlIsDown = GuiScreen.isCtrlKeyDown();
        Network.sendToServer(new UseOffhandC2SPacket(ctrlIsDown));
    }
}
