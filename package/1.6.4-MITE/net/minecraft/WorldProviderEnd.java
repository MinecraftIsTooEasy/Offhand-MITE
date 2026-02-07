/*     */ package net.minecraft;
/*     */ 
/*     */ public class WorldProviderEnd
/*     */   extends WorldProvider
/*     */ {
/*     */   public boolean heal_ender_dragon;
/*     */   
/*     */   public WorldProviderEnd() {
/*   9 */     super(1, "The End");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerWorldChunkManager() {
/*  17 */     this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.5F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider createChunkGenerator() {
/*  27 */     return new ChunkProviderEnd(this.worldObj, this.worldObj.getSeed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float calculateCelestialAngle(long par1, float par3) {
/*  35 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] calcSunriseSunsetColors(float par1, float par2) {
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getFogColor(float par1, float par2, EntityLivingBase viewer) {
/*  52 */     int var3 = 10518688;
/*  53 */     float var4 = MathHelper.cos(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*     */     
/*  55 */     if (var4 < 0.0F)
/*     */     {
/*  57 */       var4 = 0.0F;
/*     */     }
/*     */     
/*  60 */     if (var4 > 1.0F)
/*     */     {
/*  62 */       var4 = 1.0F;
/*     */     }
/*     */     
/*  65 */     float var5 = (var3 >> 16 & 0xFF) / 255.0F;
/*  66 */     float var6 = (var3 >> 8 & 0xFF) / 255.0F;
/*  67 */     float var7 = (var3 & 0xFF) / 255.0F;
/*  68 */     var5 *= var4 * 0.0F + 0.15F;
/*  69 */     var6 *= var4 * 0.0F + 0.15F;
/*  70 */     var7 *= var4 * 0.0F + 0.15F;
/*  71 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(var5, var6, var7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkyColored() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRespawnHere() {
/*  84 */     return false;
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
/*     */   public float getCloudHeight() {
/* 100 */     return 8.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCoordinateBeSpawn(int par1, int par2) {
/* 108 */     int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
/*     */     
/* 110 */     return (var3 == 0) ? false : (Block.blocksList[var3]).is_always_solid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getEntrancePortalLocation() {
/* 118 */     return new ChunkCoordinates(100, 50, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAverageGroundLevel() {
/* 123 */     return 50;
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
/*     */   public boolean doesXZShowFog(int x, int y, int z) {
/* 136 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldProviderEnd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */