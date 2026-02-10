package com.m.offhand.event;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.core.OffhandStateManager;
import com.m.offhand.util.OffhandLog;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedInEvent;
import moddedmite.rustedironcore.api.event.events.PlayerLoggedOutEvent;
import moddedmite.rustedironcore.api.event.listener.IPlayerEventListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;

@Environment(EnvType.SERVER)
public class OffhandPlayerEventListener implements IPlayerEventListener {
    
    public static final OffhandPlayerEventListener INSTANCE = new OffhandPlayerEventListener();
    
    public OffhandPlayerEventListener() {
    }
    
    @Override
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        EntityPlayer player = event.player();
        
        if (!(player instanceof OffhandAccess offhandAccess)) {
            OffhandLog.warn("[OFFHAND] Player does not implement OffhandAccess: {}", player.getEntityName());
            return;
        }
        
        OffhandStateManager.createState(player);
        OffhandStateManager.syncFromMixin(player);
        
        OffhandLog.debug("[OFFHAND] Synced offhand to player {} on login", player.getEntityName());
    }
    
    @Override
    public void onPlayerLoggedOut(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player();
        
        OffhandStateManager.removeState(player);
        
        OffhandLog.debug("[OFFHAND] Removed offhand state for player {} on logout", player.getEntityName());
    }
}
