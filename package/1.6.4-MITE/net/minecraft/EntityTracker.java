/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityTracker
/*     */ {
/*     */   private final WorldServer theWorld;
/*  15 */   private Set trackedEntities = new HashSet();
/*  16 */   private IntHashMap trackedEntityIDs = new IntHashMap();
/*     */   
/*     */   private int entityViewDistance;
/*     */   
/*     */   public EntityTracker(WorldServer par1WorldServer) {
/*  21 */     this.theWorld = par1WorldServer;
/*  22 */     this.entityViewDistance = par1WorldServer.getMinecraftServer().getConfigurationManager().getEntityViewDistance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntityToTracker(Entity par1Entity) {
/*  31 */     if (par1Entity instanceof ServerPlayer) {
/*     */       
/*  33 */       addEntityToTracker(par1Entity, 512, 2);
/*  34 */       ServerPlayer var2 = (ServerPlayer)par1Entity;
/*  35 */       Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
/*     */       
/*  37 */       while (var3.hasNext())
/*     */       {
/*  39 */         EntityTrackerEntry var4 = var3.next();
/*     */         
/*  41 */         if (var4.myEntity != var2)
/*     */         {
/*  43 */           var4.tryStartWachingThis(var2);
/*     */         }
/*     */       }
/*     */     
/*  47 */     } else if (par1Entity instanceof EntityFishHook) {
/*     */       
/*  49 */       addEntityToTracker(par1Entity, 64, 5, true);
/*     */     }
/*  51 */     else if (par1Entity instanceof EntityArrow) {
/*     */       
/*  53 */       addEntityToTracker(par1Entity, 64, 20, false);
/*     */     }
/*  55 */     else if (par1Entity instanceof EntitySmallFireball) {
/*     */       
/*  57 */       addEntityToTracker(par1Entity, 64, 10, false);
/*     */     }
/*  59 */     else if (par1Entity instanceof EntityFireball) {
/*     */       
/*  61 */       addEntityToTracker(par1Entity, 64, 10, false);
/*     */     }
/*  63 */     else if (par1Entity instanceof EntitySnowball) {
/*     */       
/*  65 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  67 */     else if (par1Entity instanceof EntityEnderPearl) {
/*     */       
/*  69 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  71 */     else if (par1Entity instanceof EntityEnderEye) {
/*     */       
/*  73 */       addEntityToTracker(par1Entity, 64, 4, true);
/*     */     }
/*  75 */     else if (par1Entity instanceof EntityEgg) {
/*     */       
/*  77 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  79 */     else if (par1Entity instanceof EntityBrick) {
/*     */       
/*  81 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  83 */     else if (par1Entity instanceof EntityWeb) {
/*     */       
/*  85 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  87 */     else if (par1Entity instanceof EntityGelatinousSphere) {
/*     */       
/*  89 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  91 */     else if (par1Entity instanceof EntityPotion) {
/*     */       
/*  93 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  95 */     else if (par1Entity instanceof EntityExpBottle) {
/*     */       
/*  97 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/*  99 */     else if (par1Entity instanceof EntityFireworkRocket) {
/*     */       
/* 101 */       addEntityToTracker(par1Entity, 64, 10, true);
/*     */     }
/* 103 */     else if (par1Entity instanceof EntityItem) {
/*     */       
/* 105 */       addEntityToTracker(par1Entity, 64, 20, true);
/*     */     }
/* 107 */     else if (par1Entity instanceof EntityMinecart) {
/*     */       
/* 109 */       addEntityToTracker(par1Entity, 80, 3, true);
/*     */     }
/* 111 */     else if (par1Entity instanceof EntityBoat) {
/*     */       
/* 113 */       addEntityToTracker(par1Entity, 80, 3, true);
/*     */     }
/* 115 */     else if (par1Entity instanceof EntitySquid) {
/*     */       
/* 117 */       addEntityToTracker(par1Entity, 64, 3, true);
/*     */     }
/* 119 */     else if (par1Entity instanceof EntityWither) {
/*     */       
/* 121 */       addEntityToTracker(par1Entity, 80, 3, false);
/*     */     }
/* 123 */     else if (par1Entity instanceof EntityBat) {
/*     */       
/* 125 */       addEntityToTracker(par1Entity, 80, 3, false);
/*     */     }
/* 127 */     else if (par1Entity instanceof IAnimals) {
/*     */       
/* 129 */       addEntityToTracker(par1Entity, 80, 3, true);
/*     */     }
/* 131 */     else if (par1Entity instanceof EntityDragon) {
/*     */       
/* 133 */       addEntityToTracker(par1Entity, 160, 3, true);
/*     */     }
/* 135 */     else if (par1Entity instanceof EntityTNTPrimed) {
/*     */       
/* 137 */       addEntityToTracker(par1Entity, 160, 10, true);
/*     */     }
/* 139 */     else if (par1Entity instanceof EntityFallingSand) {
/*     */       
/* 141 */       addEntityToTracker(par1Entity, 160, 20, true);
/*     */     }
/* 143 */     else if (par1Entity instanceof EntityHanging) {
/*     */       
/* 145 */       addEntityToTracker(par1Entity, 160, 2147483647, false);
/*     */     }
/* 147 */     else if (par1Entity instanceof EntityXPOrb) {
/*     */       
/* 149 */       addEntityToTracker(par1Entity, 160, 20, true);
/*     */     }
/* 151 */     else if (par1Entity instanceof EntityEnderCrystal) {
/*     */       
/* 153 */       addEntityToTracker(par1Entity, 256, 2147483647, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntityToTracker(Entity par1Entity, int par2, int par3) {
/* 159 */     addEntityToTracker(par1Entity, par2, par3, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntityToTracker(Entity par1Entity, int par2, int par3, boolean par4) {
/* 164 */     if (par2 > this.entityViewDistance)
/*     */     {
/* 166 */       par2 = this.entityViewDistance;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 171 */       if (this.trackedEntityIDs.containsItem(par1Entity.entityId))
/*     */       {
/* 173 */         throw new IllegalStateException("Entity is already tracked!");
/*     */       }
/*     */       
/* 176 */       EntityTrackerEntry var5 = new EntityTrackerEntry(par1Entity, par2, par3, par4);
/* 177 */       this.trackedEntities.add(var5);
/* 178 */       this.trackedEntityIDs.addKey(par1Entity.entityId, var5);
/* 179 */       var5.sendEventsToPlayers(this.theWorld.playerEntities);
/*     */     }
/* 181 */     catch (Throwable var11) {
/*     */       
/* 183 */       CrashReport var6 = CrashReport.makeCrashReport(var11, "Adding entity to track");
/* 184 */       CrashReportCategory var7 = var6.makeCategory("Entity To Track");
/* 185 */       var7.addCrashSection("Tracking range", par2 + " blocks");
/* 186 */       var7.addCrashSectionCallable("Update interval", new CallableEntityTracker(this, par3));
/* 187 */       par1Entity.addEntityCrashInfo(var7);
/* 188 */       CrashReportCategory var8 = var6.makeCategory("Entity That Is Already Tracked");
/* 189 */       ((EntityTrackerEntry)this.trackedEntityIDs.lookup(par1Entity.entityId)).myEntity.addEntityCrashInfo(var8);
/*     */ 
/*     */       
/*     */       try {
/* 193 */         throw new ReportedException(var6);
/*     */       }
/* 195 */       catch (ReportedException var10) {
/*     */         
/* 197 */         System.err.println("\"Silently\" catching entity tracking error.");
/* 198 */         var10.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEntityFromAllTrackingPlayers(Entity par1Entity) {
/* 205 */     if (par1Entity instanceof ServerPlayer) {
/*     */       
/* 207 */       ServerPlayer var2 = (ServerPlayer)par1Entity;
/* 208 */       Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
/*     */       
/* 210 */       while (var3.hasNext()) {
/*     */         
/* 212 */         EntityTrackerEntry var4 = var3.next();
/* 213 */         var4.removeFromWatchingList(var2);
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     EntityTrackerEntry var5 = (EntityTrackerEntry)this.trackedEntityIDs.removeObject(par1Entity.entityId);
/*     */     
/* 219 */     if (var5 != null) {
/*     */       
/* 221 */       this.trackedEntities.remove(var5);
/* 222 */       var5.informAllAssociatedPlayersOfItemDestruction();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTrackedEntities() {
/* 228 */     ArrayList<ServerPlayer> var1 = new ArrayList();
/* 229 */     Iterator<EntityTrackerEntry> var2 = this.trackedEntities.iterator();
/*     */     
/* 231 */     while (var2.hasNext()) {
/*     */       
/* 233 */       EntityTrackerEntry var3 = var2.next();
/* 234 */       var3.sendLocationToAllClients(this.theWorld.playerEntities);
/*     */       
/* 236 */       if (var3.playerEntitiesUpdated && var3.myEntity instanceof ServerPlayer)
/*     */       {
/* 238 */         var1.add((ServerPlayer)var3.myEntity);
/*     */       }
/*     */     } 
/*     */     
/* 242 */     for (int var6 = 0; var6 < var1.size(); var6++) {
/*     */       
/* 244 */       ServerPlayer var7 = var1.get(var6);
/* 245 */       Iterator<EntityTrackerEntry> var4 = this.trackedEntities.iterator();
/*     */       
/* 247 */       while (var4.hasNext()) {
/*     */         
/* 249 */         EntityTrackerEntry var5 = var4.next();
/*     */         
/* 251 */         if (var5.myEntity != var7)
/*     */         {
/* 253 */           var5.tryStartWachingThis(var7);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketToAllPlayersTrackingEntity(Entity par1Entity, Packet par2Packet) {
/* 264 */     EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityIDs.lookup(par1Entity.entityId);
/*     */     
/* 266 */     if (var3 != null)
/*     */     {
/* 268 */       var3.sendPacketToAllTrackingPlayers(par2Packet);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketToAllAssociatedPlayers(Entity par1Entity, Packet par2Packet) {
/* 277 */     EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityIDs.lookup(par1Entity.entityId);
/*     */     
/* 279 */     if (var3 != null)
/*     */     {
/* 281 */       var3.sendPacketToAllAssociatedPlayers(par2Packet);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayerFromTrackers(ServerPlayer par1EntityPlayerMP) {
/* 287 */     Iterator<EntityTrackerEntry> var2 = this.trackedEntities.iterator();
/*     */     
/* 289 */     while (var2.hasNext()) {
/*     */       
/* 291 */       EntityTrackerEntry var3 = var2.next();
/* 292 */       var3.removePlayerFromTracker(par1EntityPlayerMP);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_85172_a(ServerPlayer par1EntityPlayerMP, Chunk par2Chunk) {
/* 298 */     Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
/*     */     
/* 300 */     while (var3.hasNext()) {
/*     */       
/* 302 */       EntityTrackerEntry var4 = var3.next();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 311 */       if (var4.myEntity != par1EntityPlayerMP && var4.myEntity.isAddedToAChunk()) {
/*     */         
/* 313 */         Chunk chunk = var4.myEntity.getChunkAddedTo();
/*     */         
/* 315 */         if (chunk.xPosition == par2Chunk.xPosition && chunk.zPosition == par2Chunk.zPosition)
/* 316 */           var4.tryStartWachingThis(par1EntityPlayerMP); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */