/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldManager
/*     */   implements IWorldAccess
/*     */ {
/*     */   private MinecraftServer mcServer;
/*     */   private WorldServer theWorldServer;
/*     */   
/*     */   public WorldManager(MinecraftServer par1MinecraftServer, WorldServer par2WorldServer) {
/*  16 */     this.mcServer = par1MinecraftServer;
/*  17 */     this.theWorldServer = par2WorldServer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnParticle(EnumParticle enum_paticle, double par2, double par4, double par6, double par8, double par10, double par12) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnParticleEx(EnumParticle enum_paticle, int index, int data, double par2, double par4, double par6, double par8, double par10, double par12) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCreate(Entity par1Entity) {
/*  33 */     this.theWorldServer.getEntityTracker().addEntityToTracker(par1Entity);
/*     */     
/*  35 */     par1Entity.detectAndRemoveDuplicateEntities();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityDestroy(Entity par1Entity) {
/*  44 */     this.theWorldServer.getEntityTracker().removeEntityFromAllTrackingPlayers(par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(String par1Str, double par2, double par4, double par6, float par8, float par9) {
/*  52 */     this.mcServer.getConfigurationManager().sendToAllNear(par2, par4, par6, (par8 > 1.0F) ? (16.0F * par8) : 16.0D, this.theWorldServer.provider.dimensionId, new Packet62LevelSound(par1Str, par2, par4, par6, par8, par9));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playLongDistanceSound(String par1Str, double par2, double par4, double par6, float par8, float par9) {
/*  58 */     this.mcServer.getConfigurationManager().sendToAllOutdoorsNear(par2, par4, par6, 64.0D, this.theWorldServer.provider.dimensionId, new Packet80LongDistanceSound(par1Str, par2, par4, par6, par8, par9));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSoundToNearExcept(EntityPlayer par1EntityPlayer, String par2Str, double par3, double par5, double par7, float par9, float par10) {
/*  66 */     this.mcServer.getConfigurationManager().sendToAllNearExcept(par1EntityPlayer, par3, par5, par7, (par9 > 1.0F) ? (16.0F * par9) : 16.0D, this.theWorldServer.provider.dimensionId, new Packet62LevelSound(par2Str, par3, par5, par7, par9, par10));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markBlockRangeForRenderUpdate(int par1, int par2, int par3, int par4, int par5, int par6) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markBlockForUpdate(int par1, int par2, int par3) {
/*  81 */     this.theWorldServer.getPlayerManager().markBlockForUpdate(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markBlockForRenderUpdate(int par1, int par2, int par3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playRecord(String par1Str, int par2, int par3, int par4) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playAuxSFX(EntityPlayer par1EntityPlayer, int par2, int par3, int par4, int par5, int par6) {
/*  99 */     this.mcServer.getConfigurationManager().sendToAllNearExcept(par1EntityPlayer, par3, par4, par5, 64.0D, this.theWorldServer.provider.dimensionId, new Packet61DoorChange(par2, par3, par4, par5, par6, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void broadcastSound(int par1, int par2, int par3, int par4, int par5) {
/* 104 */     this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new Packet61DoorChange(par1, par2, par3, par4, par5, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyBlockPartially(int destroyer_entity_id, int x, int y, int z, int tenths_destroyed) {
/* 137 */     Iterator<ServerPlayer> i = (this.mcServer.getConfigurationManager()).playerEntityList.iterator();
/*     */     
/* 139 */     while (i.hasNext()) {
/*     */       
/* 141 */       ServerPlayer player = i.next();
/*     */ 
/*     */       
/* 144 */       if (player != null && player.worldObj == this.theWorldServer && player.entityId != destroyer_entity_id) {
/*     */         
/* 146 */         double dx = (x + 0.5F) - player.posX;
/* 147 */         double dy = (y + 0.5F) - player.posY;
/* 148 */         double dz = (z + 0.5F) - player.posZ;
/*     */         
/* 150 */         if (dx * dx + dy * dy + dz * dz < 1024.0D)
/* 151 */           player.playerNetServerHandler.sendPacketToPlayer(new Packet55BlockDestroy(destroyer_entity_id, x, y, z, tenths_destroyed)); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */