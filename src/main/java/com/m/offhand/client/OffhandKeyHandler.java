package com.m.offhand.client;

import com.m.offhand.network.OffhandSwapRequestPacket;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.network.Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.*;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class OffhandKeyHandler implements IKeybindingListener, ITickListener {

    public static final KeyBinding SWAP_KEY = new KeyBinding("key.offhand.swap", Keyboard.KEY_F);

    private boolean swapKeyDownLastTick = false;

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
        registry.accept(SWAP_KEY);
    }

    @Override
    public void onClientTick(Minecraft client) {
        if (client.thePlayer == null) {
            this.swapKeyDownLastTick = false;
            return;
        }

        boolean isSwapKeyDown = Keyboard.isKeyDown(SWAP_KEY.keyCode);
        if (client.currentScreen != null) {
            // GUI 内由 GuiContainer 的按键处理执行槽位交换，避免与全局换手逻辑重复触发。
            this.swapKeyDownLastTick = isSwapKeyDown;
            return;
        }

        if (isSwapKeyDown && !this.swapKeyDownLastTick) {
            requestSwap();
        }
        this.swapKeyDownLastTick = isSwapKeyDown;
    }

    private void requestSwap() {
        Network.sendToServer(new OffhandSwapRequestPacket());
    }
}
