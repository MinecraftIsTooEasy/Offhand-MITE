/*    */ package net.minecraft;
/*    */ 
/*    */ public class WorldProviderHell
/*    */   extends WorldProvider
/*    */ {
/*    */   public WorldProviderHell() {
/*  7 */     super(-1, "Nether");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerWorldChunkManager() {
/* 15 */     this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
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
/*    */   public Vec3 getFogColor(float par1, float par2, EntityLivingBase viewer) {
/* 27 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void generateLightBrightnessTable() {
/* 35 */     float var1 = 0.1F;
/*    */     
/* 37 */     for (int var2 = 0; var2 <= 15; var2++) {
/*    */       
/* 39 */       float var3 = 1.0F - var2 / 15.0F;
/* 40 */       this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChunkProvider createChunkGenerator() {
/* 49 */     return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
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
/*    */   public boolean canCoordinateBeSpawn(int par1, int par2) {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float calculateCelestialAngle(long par1, float par3) {
/* 73 */     return 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canRespawnHere() {
/* 81 */     return false;
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
/*    */   public boolean doesXZShowFog(int x, int y, int z) {
/* 94 */     return (y < 128);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldProviderHell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */