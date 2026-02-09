package com.m.offhand.offhand;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.network.SwapOffhandC2SPacket;
import com.m.offhand.network.UseOffhandC2SPacket;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.network.Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiScreen;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemStack;
import net.minecraft.KeyBinding;
import net.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class OffhandKeybindListener implements IKeybindingListener, ITickListener {
    public static final OffhandKeybindListener INSTANCE = new OffhandKeybindListener();

    private KeyBinding swapKey;
    private boolean lastSwapKeyState = false; // 用于检测按键按下（而不是持续按住）
    private boolean lastUseKeyState = false;  // 用于检测右键按下
    
    // 冷却时间（防止狂按导致问题）
    private long lastSwapTime = 0;
    private long lastUseTime = 0;
    
    // 槽位变化检测（防止快速切换槽位导致物品错位）
    private int lastCurrentSlot = -1;
    private long lastSlotChangeTime = 0;
    
    // 状态验证
    private ItemStack lastMainhand = null;
    private ItemStack lastOffhand = null;

    private OffhandKeybindListener() {
    }

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
        // F 键：与现代版一致，交换主/副手
        this.swapKey = new KeyBinding("key.miteassistant.swap_offhand", Keyboard.KEY_F);
        registry.accept(this.swapKey);
    }

    @Override
    public void onClientTick(Minecraft client) {
        if (client.thePlayer != null) {
            trackSlotChanges(client.thePlayer);
        }
        handleSwapKey(client);
        handleUseOffhand(client);
    }
    
    /**
     * 跟踪槽位变化，防止快速切换导致物品错位
     */
    private void trackSlotChanges(EntityPlayer player) {
        int currentSlot = player.inventory.currentItem;
        
        if (currentSlot != lastCurrentSlot) {
            long now = System.currentTimeMillis();
            
            // 检测到槽位变化
            if (lastCurrentSlot != -1) {
                OffhandLog.debug("[OFFHAND] Slot changed from {} to {}", lastCurrentSlot, currentSlot);
                
                // 如果在短时间内频繁切换槽位，记录时间戳
                if (now - lastSlotChangeTime < OffhandConstants.SLOT_CHANGE_DEBOUNCE_MS) {
                    OffhandLog.warn("[OFFHAND] Rapid slot change detected, may cause item misalignment");
                }
            }
            
            lastCurrentSlot = currentSlot;
            lastSlotChangeTime = now;
        }
    }
    
    /**
     * 处理副手交换按键
     */
    private void handleSwapKey(Minecraft client) {
        if (this.swapKey == null) return;
        
        // 检查配置是否启用
        if (!OffhandConfig.enableOffhand.get()) return;
        
        // 检测按键按下（只在从 false 变为 true 时触发，避免重复触发）
        boolean currentState = this.swapKey.pressed;
        if (!currentState || this.lastSwapKeyState) {
            // 按键未按下，或上次已经是按下状态（避免重复触发）
            this.lastSwapKeyState = currentState;
            return;
        }
        
        // 按键刚刚按下（从 false 变为 true）
        this.lastSwapKeyState = true;
        this.swapKey.pressed = false; // consume press

        // 只在客户端有玩家且不在 GUI 中时才交换
        EntityPlayer player = client.thePlayer;
        if (player == null) return;
        if (client.currentScreen != null) return; // 在 GUI 中时不交换

        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        // 如果正在使用副手物品，不允许交换（防止物品丢失）
        if (OffhandUtils.isPlayerBusy(player, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Cannot swap while using offhand item");
            return;
        }
        
        // 检查最近是否有槽位变化（防止在槽位切换时交换）
        long now = System.currentTimeMillis();
        if (now - lastSlotChangeTime < OffhandConstants.SLOT_CHANGE_DEBOUNCE_MS) {
            OffhandLog.debug("[OFFHAND] Cannot swap during slot change debounce");
            return;
        }
        
        // 冷却检查（使用配置中的冷却时间）
        if (now - lastSwapTime < OffhandConfig.getSwapCooldown()) {
            OffhandLog.debug("[OFFHAND] Swap on cooldown");
            return;
        }
        lastSwapTime = now;

        // 保存当前状态用于验证
        lastMainhand = player.getHeldItemStack();
        lastOffhand = offhandAccess.miteassistant$getOffhandStack();
        
        OffhandLog.debug("[OFFHAND] Sending swap request to server");
        // 直接发送请求到服务器，不做客户端预测（防止状态不同步）
        Network.sendToServer(new SwapOffhandC2SPacket());
    }
    
    /**
     * 处理副手物品使用（右键）
     * 注意：当主手有工具且副手有方块时，由 OffhandMinecraftMixin 处理，以便拦截原版格挡逻辑
     * 这里只处理主手为空的情况
     */
    private void handleUseOffhand(Minecraft client) {
        EntityPlayer player = client.thePlayer;
        if (player == null) return;
        if (client.currentScreen != null) return; // 在 GUI 中时不处理
        
        // 检查配置是否启用
        if (!OffhandConfig.enableOffhand.get()) return;
        
        // 检测右键是否按下
        boolean useKeyPressed = client.gameSettings.keyBindUseItem.pressed;
        
        // 只在右键刚按下时触发（从 false 变为 true）
        if (!useKeyPressed || this.lastUseKeyState) {
            this.lastUseKeyState = useKeyPressed;
            return;
        }
        this.lastUseKeyState = true;
        
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        // 如果正在使用副手物品，不处理
        if (OffhandUtils.isPlayerBusy(player, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Cannot use offhand while busy");
            return;
        }
        
        // 检查最近是否有槽位变化
        long now = System.currentTimeMillis();
        if (now - lastSlotChangeTime < OffhandConstants.SLOT_CHANGE_DEBOUNCE_MS) {
            OffhandLog.debug("[OFFHAND] Cannot use offhand during slot change debounce");
            return;
        }
        
        // 检查主手是否为空（只有主手为空时才由这里处理）
        // 主手有工具且副手有方块的情况由 OffhandMinecraftMixin 处理
        ItemStack mainhand = player.getHeldItemStack();
        if (mainhand != null) return;
        
        // 检查副手是否有物品
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) return;
        
        // 冷却检查（使用配置中的冷却时间）
        if (now - lastUseTime < OffhandConfig.getUseCooldown()) {
            OffhandLog.debug("[OFFHAND] Use offhand on cooldown");
            return;
        }
        lastUseTime = now;
        
        // 保存当前状态用于验证
        lastMainhand = mainhand;
        lastOffhand = offhand;
        
        OffhandLog.debug("[OFFHAND] Sending use offhand request to server");
        // 发送副手使用请求到服务器
        boolean ctrlIsDown = GuiScreen.isCtrlKeyDown();
        Network.sendToServer(new UseOffhandC2SPacket(ctrlIsDown));
    }
}

