/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFlower
/*    */   extends BlockPlant
/*    */ {
/*    */   protected BlockFlower(int id, Material material) {
/* 11 */     super(id, material);
/*    */     
/* 13 */     float size = 0.2F;
/* 14 */     setBlockBoundsForAllThreads((0.5F - size), 0.0D, (0.5F - size), (0.5F + size), (size * 3.0F), (0.5F + size));
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockFlower(int id) {
/* 19 */     this(id, Material.plants);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMinAllowedLightValue() {
/* 24 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxAllowedLightValue() {
/* 29 */     return 15;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean dropsAsSelfWhenTrampled(Entity entity) {
/* 39 */     return (!(entity instanceof EntityPig) && !(entity instanceof EntitySheep) && !(entity instanceof EntityCow));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBiomeSuitable(BiomeGenBase biome, int metadata) {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPatchSize(BiomeGenBase biome) {
/* 49 */     return biome.isSwampBiome() ? 16 : 32;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFlower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */