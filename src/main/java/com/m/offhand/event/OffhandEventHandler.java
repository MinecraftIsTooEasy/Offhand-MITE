package com.m.offhand.event;

import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.network.OffhandSyncPacket;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedInEvent;
import moddedmite.rustedironcore.api.event.listener.IPlayerEventListener;
import net.minecraft.*;

public class OffhandEventHandler implements IPlayerEventListener {

    public static void init() {
        Handlers.PlayerEvent.register(new OffhandEventHandler());
    }

    @Override
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        ServerPlayer newPlayer = event.player();
        syncAllPlayersToNewPlayer(newPlayer);
    }

    private void syncAllPlayersToNewPlayer(ServerPlayer newPlayer) {
        for (Object obj : newPlayer.worldObj.playerEntities) {
            if (obj instanceof EntityPlayer otherPlayer && otherPlayer != newPlayer) {
                OffhandSyncPacket.sendToClient(newPlayer, otherPlayer);
            }
        }
    }
}
