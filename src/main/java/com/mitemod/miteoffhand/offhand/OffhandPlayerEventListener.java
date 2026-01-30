package com.mitemod.miteoffhand.offhand;

import com.mitemod.miteoffhand.api.OffhandAccess;
import com.mitemod.miteoffhand.network.SyncOffhandS2CPacket;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedInEvent;
import moddedmite.rustedironcore.api.event.listener.IPlayerEventListener;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.ServerPlayer;

public class OffhandPlayerEventListener implements IPlayerEventListener {
    @Override
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        ServerPlayer player = event.player();
        Object playerObj = player;
        if (!(playerObj instanceof OffhandAccess offhandAccess)) return;
        Network.sendToClient(player, new SyncOffhandS2CPacket(offhandAccess.miteassistant$getOffhandStack()));
    }
}

