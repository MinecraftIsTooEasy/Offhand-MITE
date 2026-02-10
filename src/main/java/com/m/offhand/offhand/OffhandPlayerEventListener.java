package com.m.offhand.offhand;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.core.OffhandStateManager;
import com.m.offhand.network.OffhandPacketHandler;
import com.m.offhand.network.SyncOffhandS2CPacket;
import com.m.offhand.util.OffhandLog;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedInEvent;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedOutEvent;
import moddedmite.rustedironcore.api.event.listener.IPlayerEventListener;
import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;

public class OffhandPlayerEventListener implements IPlayerEventListener {
    @Override
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        EntityPlayer player = event.player();
        
        if (!(player instanceof OffhandAccess offhandAccess)) {
            OffhandLog.warn("[OFFHAND] Player does not implement OffhandAccess: {}", player.getEntityName());
            return;
        }
        
        OffhandStateManager.syncFromMixin(player);
        
        OffhandPacketHandler.sendToClient((ServerPlayer) player, new SyncOffhandS2CPacket(offhandAccess.miteassistant$getOffhandStack()));
        
        OffhandLog.debug("[OFFHAND] Synced offhand to player {} on login", player.getEntityName());
    }
    
    @Override
    public void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player();
        
        OffhandStateManager.removeState(player);
        
        OffhandLog.debug("[OFFHAND] Removed offhand state for player {} on logout", player.getEntityName());
    }
}

