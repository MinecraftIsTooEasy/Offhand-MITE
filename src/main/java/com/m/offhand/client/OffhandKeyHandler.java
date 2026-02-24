package com.m.offhand.client;

import com.m.offhand.network.OffhandSwapRequestPacket;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.network.Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.*;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class OffhandKeyHandler implements IKeybindingListener, ITickListener {

    public static final KeyBinding SWAP_KEY = new KeyBinding("key.offhand.swap", 33);

    private long lastSwapTime = 0;
    private static final long SWAP_COOLDOWN = 300;

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
        registry.accept(SWAP_KEY);
    }

    @Override
    public void onClientTick(Minecraft client) {
        if (client.thePlayer == null) return;
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSwapTime < SWAP_COOLDOWN) return;
        
        if (SWAP_KEY.isPressed()) {
            lastSwapTime = currentTime;
            requestSwap();
        }
    }

    private void requestSwap() {
        Network.sendToServer(new OffhandSwapRequestPacket());
    }
}
