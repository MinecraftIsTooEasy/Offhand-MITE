package com.m.offhand.offhand;

import com.m.offhand.api.Hand;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.network.OffhandPacketHandler;
import com.m.offhand.network.SwapOffhandC2SPacket;
import com.m.offhand.network.UseOffhandC2SPacket;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.*;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class OffhandKeybindListener implements IKeybindingListener, ITickListener {
    public static final OffhandKeybindListener INSTANCE = new OffhandKeybindListener();

    private KeyBinding swapKey;
    private boolean lastSwapKeyState;
    private boolean lastUseKeyState;
    private long lastSwapTime;
    private long lastUseTime;

    private OffhandKeybindListener() {
    }

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
        this.swapKey = new KeyBinding("key.miteassistant.swap_offhand", Keyboard.KEY_F);
        registry.accept(this.swapKey);
    }

    @Override
    public void onClientTick(Minecraft client) {
        handleSwapKey(client);
        handleUseOffhand(client);
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
        if (player == null || client.currentScreen != null) return;

        OffhandAccess access = (OffhandAccess) player;
        if (OffhandUtils.isPlayerBusy(player, access)) return;
        
        long now = System.currentTimeMillis();
        if (now - lastSwapTime < OffhandConfig.getSwapCooldown()) return;
        lastSwapTime = now;

        OffhandPacketHandler.sendToServer(new SwapOffhandC2SPacket());
    }
    
    private void handleUseOffhand(Minecraft client) {
        EntityPlayer player = client.thePlayer;
        if (player == null || client.currentScreen != null) return;
        if (!OffhandConfig.enableOffhand.get()) return;
        
        boolean useKeyPressed = client.gameSettings.keyBindUseItem.pressed;
        
        if (!useKeyPressed || this.lastUseKeyState) {
            this.lastUseKeyState = useKeyPressed;
            return;
        }
        this.lastUseKeyState = true;
        
        OffhandAccess access = (OffhandAccess) player;
        if (OffhandUtils.isPlayerBusy(player, access)) return;
        
        ItemStack mainhand = access.miteassistant$getStackInHand(Hand.MAIN_HAND);
        if (mainhand != null) return;
        
        ItemStack offhand = access.miteassistant$getStackInHand(Hand.OFF_HAND);
        if (!OffhandUtils.isValidOffhand(offhand)) return;
        
        long now = System.currentTimeMillis();
        if (now - lastUseTime < OffhandConfig.getUseCooldown()) return;
        lastUseTime = now;
        
        boolean ctrlIsDown = GuiScreen.isCtrlKeyDown();
        OffhandPacketHandler.sendToServer(new UseOffhandC2SPacket(ctrlIsDown));
    }
}
