package com.m.offhand.offhand;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.network.OffhandPacketHandler;
import com.m.offhand.network.SwapOffhandC2SPacket;
import com.m.offhand.network.UseOffhandC2SPacket;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiScreen;
import net.minecraft.ItemStack;
import net.minecraft.KeyBinding;
import net.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class OffhandKeybindListener implements IKeybindingListener, ITickListener {
    public static final OffhandKeybindListener INSTANCE = new OffhandKeybindListener();

    private KeyBinding swapKey;
    private boolean lastSwapKeyState = false;
    private boolean lastUseKeyState = false;
    
    private long lastSwapTime = 0;
    private long lastUseTime = 0;
    
    private int lastCurrentSlot = -1;
    private long lastSlotChangeTime = 0;
    
    private ItemStack lastMainhand = null;
    private ItemStack lastOffhand = null;

    private OffhandKeybindListener() {
    }

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
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
    
    private void trackSlotChanges(EntityPlayer player) {
        int currentSlot = player.inventory.currentItem;
        
        if (currentSlot != lastCurrentSlot) {
            long now = System.currentTimeMillis();
            
            if (lastCurrentSlot != -1) {
                OffhandLog.debug("[OFFHAND] Slot changed from {} to {}", lastCurrentSlot, currentSlot);
                
                if (now - lastSlotChangeTime < OffhandConstants.SLOT_CHANGE_DEBOUNCE_MS) {
                    OffhandLog.warn("[OFFHAND] Rapid slot change detected, may cause item misalignment");
                }
            }
            
            lastCurrentSlot = currentSlot;
            lastSlotChangeTime = now;
        }
    }
    
    private void handleSwapKey(Minecraft client) {
        if (this.swapKey == null) return;
        
        if (!OffhandConfig.enableOffhand.get()) return;
        
        boolean currentState = this.swapKey.pressed;
        if (!currentState || this.lastSwapKeyState) {
            this.lastSwapKeyState = currentState;
            return;
        }
        
        this.lastSwapKeyState = true;
        this.swapKey.pressed = false;

        EntityPlayer player = client.thePlayer;
        if (player == null) return;
        if (client.currentScreen != null) return;

        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        if (OffhandUtils.isPlayerBusy(player, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Cannot swap while using offhand item");
            return;
        }
        
        long now = System.currentTimeMillis();
        if (now - lastSlotChangeTime < OffhandConstants.SLOT_CHANGE_DEBOUNCE_MS) {
            OffhandLog.debug("[OFFHAND] Cannot swap during slot change debounce");
            return;
        }
        
        if (now - lastSwapTime < OffhandConfig.getSwapCooldown()) {
            OffhandLog.debug("[OFFHAND] Swap on cooldown");
            return;
        }
        lastSwapTime = now;

        lastMainhand = player.getHeldItemStack();
        lastOffhand = offhandAccess.miteassistant$getOffhandStack();
        
        OffhandLog.debug("[OFFHAND] Sending swap request to server");
        OffhandPacketHandler.sendToServer(new SwapOffhandC2SPacket());
    }
    
    private void handleUseOffhand(Minecraft client) {
        EntityPlayer player = client.thePlayer;
        if (player == null) return;
        if (client.currentScreen != null) return;
        
        if (!OffhandConfig.enableOffhand.get()) return;
        
        boolean useKeyPressed = client.gameSettings.keyBindUseItem.pressed;
        
        if (!useKeyPressed || this.lastUseKeyState) {
            this.lastUseKeyState = useKeyPressed;
            return;
        }
        this.lastUseKeyState = true;
        
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        if (OffhandUtils.isPlayerBusy(player, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Cannot use offhand while busy");
            return;
        }
        
        long now = System.currentTimeMillis();
        if (now - lastSlotChangeTime < OffhandConstants.SLOT_CHANGE_DEBOUNCE_MS) {
            OffhandLog.debug("[OFFHAND] Cannot use offhand during slot change debounce");
            return;
        }
        
        ItemStack mainhand = player.getHeldItemStack();
        if (mainhand != null) return;
        
        ItemStack offhand = offhandAccess.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) return;
        
        if (now - lastUseTime < OffhandConfig.getUseCooldown()) {
            OffhandLog.debug("[OFFHAND] Use offhand on cooldown");
            return;
        }
        lastUseTime = now;
        
        lastMainhand = mainhand;
        lastOffhand = offhand;
        
        OffhandLog.debug("[OFFHAND] Sending use offhand request to server");
        boolean ctrlIsDown = GuiScreen.isCtrlKeyDown();
        OffhandPacketHandler.sendToServer(new UseOffhandC2SPacket(ctrlIsDown));
    }
}

