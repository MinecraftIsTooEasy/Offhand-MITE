/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldProviderUnderworld
/*    */   extends WorldProvider
/*    */ {
/*    */   public WorldProviderUnderworld() {
/*  9 */     super(-2, "Underworld");
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerWorldChunkManager() {
/* 14 */     this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.underworld, 1.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vec3 getFogColor(float par1, float par2, EntityLivingBase viewer) {
/* 31 */     int day_of_cycle = viewer.worldObj.getDayOfWorld() % 32;
/*    */     
/* 33 */     int distance_from_peak = Math.abs(day_of_cycle - 16);
/*    */     
/* 35 */     float grayscale = distance_from_peak * 0.004F;
/*    */     
/* 37 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(grayscale, grayscale, grayscale);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChunkProvider createChunkGenerator() {
/* 42 */     return new ChunkProviderUnderworld(this.worldObj, this.worldObj.getSeed());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCoordinateBeSpawn(int par1, int par2) {
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float calculateCelestialAngle(long par1, float par3) {
/* 52 */     return 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canRespawnHere() {
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean doesXZShowFog(int x, int y, int z) {
/* 62 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldProviderUnderworld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */