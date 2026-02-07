/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerManager
/*     */ {
/*     */   private final WorldServer theWorldServer;
/*  11 */   private final List players = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  16 */   private final LongHashMap playerInstances = new LongHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   private final List chunkWatcherWithPlayers = new ArrayList();
/*     */ 
/*     */   
/*  25 */   private final List playerInstanceList = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private final int playerViewRadius;
/*     */ 
/*     */ 
/*     */   
/*     */   private long previousTotalWorldTime;
/*     */ 
/*     */   
/*  36 */   private final int[][] xzDirectionsConst = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
/*     */ 
/*     */   
/*     */   public PlayerManager(WorldServer par1WorldServer, int par2) {
/*  40 */     if (par2 > 15)
/*     */     {
/*  42 */       throw new IllegalArgumentException("Too big view radius!");
/*     */     }
/*  44 */     if (par2 < 3)
/*     */     {
/*  46 */       throw new IllegalArgumentException("Too small view radius!");
/*     */     }
/*     */ 
/*     */     
/*  50 */     this.playerViewRadius = par2;
/*  51 */     this.theWorldServer = par1WorldServer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldServer getWorldServer() {
/*  57 */     return this.theWorldServer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePlayerInstances() {
/*  65 */     long var1 = this.theWorldServer.getTotalWorldTime();
/*     */ 
/*     */ 
/*     */     
/*  69 */     if (var1 - this.previousTotalWorldTime > 8000L) {
/*     */       
/*  71 */       this.previousTotalWorldTime = var1;
/*     */       
/*  73 */       for (int var3 = 0; var3 < this.playerInstanceList.size(); var3++)
/*     */       {
/*  75 */         PlayerInstance var4 = this.playerInstanceList.get(var3);
/*     */         
/*  77 */         var4.sendChunkUpdate();
/*  78 */         var4.processChunk();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  83 */       for (int var3 = 0; var3 < this.chunkWatcherWithPlayers.size(); var3++) {
/*     */         
/*  85 */         PlayerInstance var4 = this.chunkWatcherWithPlayers.get(var3);
/*     */         
/*  87 */         var4.sendChunkUpdate();
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     this.chunkWatcherWithPlayers.clear();
/*     */     
/*  93 */     if (this.players.isEmpty()) {
/*     */       
/*  95 */       WorldProvider var5 = this.theWorldServer.provider;
/*     */       
/*  97 */       if (!var5.canRespawnHere())
/*     */       {
/*  99 */         this.theWorldServer.theChunkProviderServer.unloadAllChunks();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private PlayerInstance getOrCreateChunkWatcher(int par1, int par2, boolean par3) {
/* 106 */     long var4 = par1 + 2147483647L | par2 + 2147483647L << 32L;
/* 107 */     PlayerInstance var6 = (PlayerInstance)this.playerInstances.getValueByKey(var4);
/*     */     
/* 109 */     if (var6 == null && par3) {
/*     */       
/* 111 */       var6 = new PlayerInstance(this, par1, par2);
/* 112 */       this.playerInstances.add(var4, var6);
/* 113 */       this.playerInstanceList.add(var6);
/*     */     } 
/*     */     
/* 116 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markBlockForUpdate(int par1, int par2, int par3) {
/* 124 */     int var4 = par1 >> 4;
/* 125 */     int var5 = par3 >> 4;
/* 126 */     PlayerInstance var6 = getOrCreateChunkWatcher(var4, var5, false);
/*     */     
/* 128 */     if (var6 != null)
/*     */     {
/* 130 */       var6.flagChunkForUpdate(par1 & 0xF, par2, par3 & 0xF);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPlayer(ServerPlayer par1EntityPlayerMP) {
/* 139 */     int var2 = (int)par1EntityPlayerMP.posX >> 4;
/* 140 */     int var3 = (int)par1EntityPlayerMP.posZ >> 4;
/* 141 */     par1EntityPlayerMP.managedPosX = par1EntityPlayerMP.posX;
/* 142 */     par1EntityPlayerMP.managedPosZ = par1EntityPlayerMP.posZ;
/*     */     
/* 144 */     for (int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; var4++) {
/*     */       
/* 146 */       for (int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; var5++)
/*     */       {
/* 148 */         getOrCreateChunkWatcher(var4, var5, true).addPlayer(par1EntityPlayerMP);
/*     */       }
/*     */     } 
/*     */     
/* 152 */     this.players.add(par1EntityPlayerMP);
/* 153 */     filterChunkLoadQueue(par1EntityPlayerMP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void filterChunkLoadQueue(ServerPlayer par1EntityPlayerMP) {
/* 161 */     ArrayList var2 = new ArrayList(par1EntityPlayerMP.loadedChunks);
/* 162 */     int var3 = 0;
/* 163 */     int var4 = this.playerViewRadius;
/* 164 */     int var5 = (int)par1EntityPlayerMP.posX >> 4;
/* 165 */     int var6 = (int)par1EntityPlayerMP.posZ >> 4;
/* 166 */     int var7 = 0;
/* 167 */     int var8 = 0;
/* 168 */     ChunkCoordIntPair var9 = PlayerInstance.getChunkLocation(getOrCreateChunkWatcher(var5, var6, true));
/* 169 */     par1EntityPlayerMP.loadedChunks.clear();
/*     */     
/* 171 */     if (var2.contains(var9))
/*     */     {
/* 173 */       par1EntityPlayerMP.loadedChunks.add(var9);
/*     */     }
/*     */     
/*     */     int var10;
/*     */     
/* 178 */     for (var10 = 1; var10 <= var4 * 2; var10++) {
/*     */       
/* 180 */       for (int var11 = 0; var11 < 2; var11++) {
/*     */         
/* 182 */         int[] var12 = this.xzDirectionsConst[var3++ % 4];
/*     */         
/* 184 */         for (int var13 = 0; var13 < var10; var13++) {
/*     */           
/* 186 */           var7 += var12[0];
/* 187 */           var8 += var12[1];
/* 188 */           var9 = PlayerInstance.getChunkLocation(getOrCreateChunkWatcher(var5 + var7, var6 + var8, true));
/*     */           
/* 190 */           if (var2.contains(var9))
/*     */           {
/* 192 */             par1EntityPlayerMP.loadedChunks.add(var9);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     var3 %= 4;
/*     */     
/* 200 */     for (var10 = 0; var10 < var4 * 2; var10++) {
/*     */       
/* 202 */       var7 += this.xzDirectionsConst[var3][0];
/* 203 */       var8 += this.xzDirectionsConst[var3][1];
/* 204 */       var9 = PlayerInstance.getChunkLocation(getOrCreateChunkWatcher(var5 + var7, var6 + var8, true));
/*     */       
/* 206 */       if (var2.contains(var9))
/*     */       {
/* 208 */         par1EntityPlayerMP.loadedChunks.add(var9);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayer(ServerPlayer par1EntityPlayerMP) {
/* 218 */     int var2 = (int)par1EntityPlayerMP.managedPosX >> 4;
/* 219 */     int var3 = (int)par1EntityPlayerMP.managedPosZ >> 4;
/*     */     
/* 221 */     for (int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; var4++) {
/*     */       
/* 223 */       for (int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; var5++) {
/*     */         
/* 225 */         PlayerInstance var6 = getOrCreateChunkWatcher(var4, var5, false);
/*     */         
/* 227 */         if (var6 != null)
/*     */         {
/* 229 */           var6.removePlayer(par1EntityPlayerMP);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     this.players.remove(par1EntityPlayerMP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean overlaps(int par1, int par2, int par3, int par4, int par5) {
/* 243 */     int var6 = par1 - par3;
/* 244 */     int var7 = par2 - par4;
/* 245 */     return (var6 >= -par5 && var6 <= par5) ? ((var7 >= -par5 && var7 <= par5)) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateMountedMovingPlayer(ServerPlayer par1EntityPlayerMP) {
/* 253 */     int var2 = (int)par1EntityPlayerMP.posX >> 4;
/* 254 */     int var3 = (int)par1EntityPlayerMP.posZ >> 4;
/* 255 */     double var4 = par1EntityPlayerMP.managedPosX - par1EntityPlayerMP.posX;
/* 256 */     double var6 = par1EntityPlayerMP.managedPosZ - par1EntityPlayerMP.posZ;
/* 257 */     double var8 = var4 * var4 + var6 * var6;
/*     */     
/* 259 */     if (var8 >= 64.0D) {
/*     */       
/* 261 */       int var10 = (int)par1EntityPlayerMP.managedPosX >> 4;
/* 262 */       int var11 = (int)par1EntityPlayerMP.managedPosZ >> 4;
/* 263 */       int var12 = this.playerViewRadius;
/* 264 */       int var13 = var2 - var10;
/* 265 */       int var14 = var3 - var11;
/*     */       
/* 267 */       if (var13 != 0 || var14 != 0) {
/*     */         
/* 269 */         long t = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */         
/* 273 */         for (int var15 = var2 - var12; var15 <= var2 + var12; var15++) {
/*     */           
/* 275 */           for (int var16 = var3 - var12; var16 <= var3 + var12; var16++) {
/*     */             
/* 277 */             if (!overlaps(var15, var16, var10, var11, var12))
/*     */             {
/* 279 */               getOrCreateChunkWatcher(var15, var16, true).addPlayer(par1EntityPlayerMP);
/*     */             }
/*     */             
/* 282 */             if (!overlaps(var15 - var13, var16 - var14, var2, var3, var12)) {
/*     */               
/* 284 */               PlayerInstance var17 = getOrCreateChunkWatcher(var15 - var13, var16 - var14, false);
/*     */               
/* 286 */               if (var17 != null)
/*     */               {
/* 288 */                 var17.removePlayer(par1EntityPlayerMP);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 294 */         if (System.currentTimeMillis() - t > 10L) {
/* 295 */           System.out.println(par1EntityPlayerMP.worldObj.isRemote ? "[Client]" : ("[Server] Long delay caught: " + (System.currentTimeMillis() - t) + "ms"));
/*     */         }
/* 297 */         filterChunkLoadQueue(par1EntityPlayerMP);
/* 298 */         par1EntityPlayerMP.managedPosX = par1EntityPlayerMP.posX;
/* 299 */         par1EntityPlayerMP.managedPosZ = par1EntityPlayerMP.posZ;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayerWatchingChunk(ServerPlayer par1EntityPlayerMP, int par2, int par3) {
/* 308 */     PlayerInstance var4 = getOrCreateChunkWatcher(par2, par3, false);
/* 309 */     return (var4 == null) ? false : ((PlayerInstance.getPlayersInChunk(var4).contains(par1EntityPlayerMP) && !par1EntityPlayerMP.loadedChunks.contains(PlayerInstance.getChunkLocation(var4))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFurthestViewableBlock(int par0) {
/* 317 */     return par0 * 16 - 16;
/*     */   }
/*     */ 
/*     */   
/*     */   static WorldServer getWorldServer(PlayerManager par0PlayerManager) {
/* 322 */     return par0PlayerManager.theWorldServer;
/*     */   }
/*     */ 
/*     */   
/*     */   static LongHashMap getChunkWatchers(PlayerManager par0PlayerManager) {
/* 327 */     return par0PlayerManager.playerInstances;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List getChunkWatcherList(PlayerManager par0PlayerManager) {
/* 335 */     return par0PlayerManager.playerInstanceList;
/*     */   }
/*     */ 
/*     */   
/*     */   static List getChunkWatchersWithPlayers(PlayerManager par0PlayerManager) {
/* 340 */     return par0PlayerManager.chunkWatcherWithPlayers;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */