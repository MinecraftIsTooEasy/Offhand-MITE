package com.m.offhand.core;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class OffhandStateManager {
    
    private static final Map<String, OffhandState> PLAYER_STATES = new ConcurrentHashMap<>();
    private static final long STATE_TIMEOUT_MS = 300000;
    private static final long CLEANUP_INTERVAL_MS = 60000;
    private static long lastCleanupTime = System.currentTimeMillis();
    
    private OffhandStateManager() {
    }
    
    private static String getPlayerKey(EntityPlayer player) {
        if (player == null) {
            return null;
        }
        
        UUID uuid = player.getUniqueIDSilent();
        if (uuid != null) {
            return uuid.toString();
        }
        
        return "entity_" + player.entityId;
    }
    
    public static OffhandState createState(EntityPlayer player) {
        if (player == null) {
            return null;
        }
        
        String key = getPlayerKey(player);
        if (key == null) {
            OffhandLog.warn("[OFFHAND] Cannot create state for null player");
            return null;
        }
        
        OffhandState state = new OffhandState(key);
        PLAYER_STATES.put(key, state);
        state.markSynced();
        
        OffhandLog.debug("[OFFHAND] Created new state for player {}", player.getEntityName());
        return state;
    }
    
    public static OffhandState getState(EntityPlayer player) {
        if (player == null) {
            return null;
        }
        
        String key = getPlayerKey(player);
        if (key == null) {
            OffhandLog.warn("[OFFHAND] Cannot get state for player with null key");
            return null;
        }
        
        return PLAYER_STATES.get(key);
    }
    
    public static void removeState(EntityPlayer player) {
        if (player == null) {
            return;
        }
        
        String key = getPlayerKey(player);
        if (key == null) {
            return;
        }
        
        OffhandState state = PLAYER_STATES.remove(key);
        
        if (state != null) {
            state.clear();
            OffhandLog.debug("[OFFHAND] Removed state for player {}", player.getEntityName());
        }
    }
    
    public static void clearAllStates() {
        int size = PLAYER_STATES.size();
        for (OffhandState state : PLAYER_STATES.values()) {
            state.clear();
        }
        PLAYER_STATES.clear();
        OffhandLog.info("[OFFHAND] Cleared {} player states", size);
    }
    
    public static void cleanupStaleStates() {
        long currentTime = System.currentTimeMillis();
        
        if (currentTime - lastCleanupTime < CLEANUP_INTERVAL_MS) {
            return;
        }
        
        lastCleanupTime = currentTime;
        
        int removedCount = 0;
        Map.Entry<String, OffhandState> staleEntry = null;
        
        for (Map.Entry<String, OffhandState> entry : PLAYER_STATES.entrySet()) {
            OffhandState state = entry.getValue();
            if (state.isStale(STATE_TIMEOUT_MS)) {
                staleEntry = entry;
                break;
            }
        }
        
        if (staleEntry != null) {
            staleEntry.getValue().clear();
            PLAYER_STATES.remove(staleEntry.getKey());
            removedCount++;
            OffhandLog.debug("[OFFHAND] Removed stale state for key {}", staleEntry.getKey());
        }
        
        if (removedCount > 0) {
            OffhandLog.info("[OFFHAND] Cleaned up {} stale states", removedCount);
        }
    }
    
    public static void updateOffhand(EntityPlayer player, ItemStack offhand) {
        OffhandState state = getState(player);
        if (state != null) {
            state.setOffhandStack(offhand);
        }
    }
    
    public static void updateUsingOffhand(EntityPlayer player, boolean using) {
        OffhandState state = getState(player);
        if (state != null) {
            state.setUsingOffhand(using);
        }
    }
    
    public static void updateOriginalMainhand(EntityPlayer player, ItemStack mainhand) {
        OffhandState state = getState(player);
        if (state != null) {
            state.setOriginalMainhand(mainhand);
        }
    }
    
    public static void updateOriginalSlot(EntityPlayer player, int slot) {
        OffhandState state = getState(player);
        if (state != null) {
            state.setOriginalSlot(slot);
        }
    }
    
    public static void saveStateToNBT(EntityPlayer player, NBTTagCompound tag) {
        OffhandState state = getState(player);
        if (state == null) {
            return;
        }
        
        NBTTagCompound stateTag = new NBTTagCompound();
        state.writeToNBT(stateTag);
        tag.setCompoundTag("OffhandState", stateTag);
        
        OffhandLog.debug("[OFFHAND] Saved state for player {}", player.getEntityName());
    }
    
    public static void loadStateFromNBT(EntityPlayer player, NBTTagCompound tag) {
        if (!tag.hasKey("OffhandState")) {
            OffhandLog.debug("[OFFHAND] No offhand state found for player {}", player.getEntityName());
            return;
        }
        
        OffhandState state = getState(player);
        if (state == null) {
            state = createState(player);
        }
        
        NBTTagCompound stateTag = tag.getCompoundTag("OffhandState");
        state.readFromNBT(stateTag);
        
        OffhandLog.debug("[OFFHAND] Loaded state for player {}", player.getEntityName());
    }
    
    public static void syncFromMixin(EntityPlayer player) {
        if (!(player instanceof OffhandAccess access)) {
            return;
        }
        
        OffhandState state = getState(player);
        if (state == null) {
            return;
        }
        
        state.setOffhandStack(access.miteassistant$getOffhandStack());
        state.setUsingOffhand(access.miteassistant$isUsingOffhand());
        state.setOriginalMainhand(access.miteassistant$getOriginalMainhand());
        state.setOriginalSlot(access.miteassistant$getOriginalSlot());
    }
    
    public static void syncToMixin(EntityPlayer player) {
        if (!(player instanceof OffhandAccess access)) {
            return;
        }
        
        OffhandState state = getState(player);
        if (state == null) {
            return;
        }
        
        access.miteassistant$setOffhandStack(state.getOffhandStack());
        access.miteassistant$setUsingOffhand(state.isUsingOffhand());
        access.miteassistant$setOriginalMainhand(state.getOriginalMainhand());
        access.miteassistant$setOriginalSlot(state.getOriginalSlot());
    }
    
    public static int getActiveStateCount() {
        return PLAYER_STATES.size();
    }
    
    public static class OffhandState {
        private final String playerKey;
        private ItemStack offhandStack;
        private boolean usingOffhand;
        private ItemStack originalMainhand;
        private int originalSlot;
        private long lastActionTime;
        private long lastSyncTime;
        private int clientVersion;
        private int serverVersion;
        
        public OffhandState(String playerKey) {
            this.playerKey = playerKey;
            this.originalSlot = -1;
            this.lastActionTime = System.currentTimeMillis();
            this.lastSyncTime = System.currentTimeMillis();
        }
        
        public ItemStack getOffhandStack() {
            return this.offhandStack;
        }
        
        public void setOffhandStack(ItemStack stack) {
            if (!ItemStack.areItemStacksEqual(this.offhandStack, stack)) {
                this.offhandStack = stack;
                this.lastActionTime = System.currentTimeMillis();
                OffhandLog.debug("[OFFHAND] Offhand stack changed for player {}", this.playerKey);
            }
        }
        
        public boolean isUsingOffhand() {
            return this.usingOffhand;
        }
        
        public void setUsingOffhand(boolean using) {
            if (this.usingOffhand != using) {
                this.usingOffhand = using;
                this.lastActionTime = System.currentTimeMillis();
                OffhandLog.debug("[OFFHAND] Using offhand changed to {} for player {}", using, this.playerKey);
            }
        }
        
        public ItemStack getOriginalMainhand() {
            return this.originalMainhand;
        }
        
        public void setOriginalMainhand(ItemStack stack) {
            this.originalMainhand = stack;
        }
        
        public int getOriginalSlot() {
            return this.originalSlot;
        }
        
        public void setOriginalSlot(int slot) {
            this.originalSlot = slot;
        }
        
        public long getLastActionTime() {
            return this.lastActionTime;
        }
        
        public long getLastSyncTime() {
            return this.lastSyncTime;
        }
        
        public void markSynced() {
            this.lastSyncTime = System.currentTimeMillis();
            this.serverVersion++;
        }
        
        public void markClientSynced() {
            this.clientVersion = this.serverVersion;
        }
        
        public boolean isSyncNeeded() {
            return this.clientVersion != this.serverVersion;
        }
        
        public boolean isStale(long timeoutMs) {
            return System.currentTimeMillis() - this.lastSyncTime > timeoutMs;
        }
        
        public void writeToNBT(NBTTagCompound tag) {
            if (this.offhandStack != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                this.offhandStack.writeToNBT(stackTag);
                tag.setCompoundTag("OffhandStack", stackTag);
            }
            
            tag.setBoolean("UsingOffhand", this.usingOffhand);
            
            if (this.originalMainhand != null) {
                NBTTagCompound originalTag = new NBTTagCompound();
                this.originalMainhand.writeToNBT(originalTag);
                tag.setCompoundTag("OriginalMainhand", originalTag);
            }
            
            tag.setInteger("OriginalSlot", this.originalSlot);
            tag.setLong("LastActionTime", this.lastActionTime);
        }
        
        public void readFromNBT(NBTTagCompound tag) {
            if (tag.hasKey("OffhandStack")) {
                NBTTagCompound stackTag = tag.getCompoundTag("OffhandStack");
                this.offhandStack = ItemStack.loadItemStackFromNBT(stackTag);
            }
            
            this.usingOffhand = tag.getBoolean("UsingOffhand");
            
            if (tag.hasKey("OriginalMainhand")) {
                NBTTagCompound originalTag = tag.getCompoundTag("OriginalMainhand");
                this.originalMainhand = ItemStack.loadItemStackFromNBT(originalTag);
            }
            
            this.originalSlot = tag.getInteger("OriginalSlot");
            this.lastActionTime = tag.getLong("LastActionTime");
            this.lastSyncTime = System.currentTimeMillis();
        }
        
        public boolean validate() {
            if (this.originalSlot < -1 || this.originalSlot >= OffhandConstants.HOTBAR_SIZE) {
                OffhandLog.warn("[OFFHAND] Invalid original slot: {}", this.originalSlot);
                return false;
            }
            
            if (this.usingOffhand && this.originalMainhand == null) {
                OffhandLog.warn("[OFFHAND] Using offhand but no original mainhand saved");
                return false;
            }
            
            return true;
        }
        
        public void clear() {
            this.offhandStack = null;
            this.usingOffhand = false;
            this.originalMainhand = null;
            this.originalSlot = -1;
            this.lastActionTime = System.currentTimeMillis();
            OffhandLog.debug("[OFFHAND] Cleared state for player {}", this.playerKey);
        }
    }
}
