/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class StructureScatteredFeatureStart
/*    */   extends StructureStart
/*    */ {
/*    */   public StructureScatteredFeatureStart() {}
/*    */   
/*    */   public StructureScatteredFeatureStart(World par1World, Random par2Random, int par3, int par4) {
/* 11 */     super(par3, par4);
/* 12 */     BiomeGenBase var5 = par1World.getBiomeGenForCoords(par3 * 16 + 8, par4 * 16 + 8);
/*    */     
/* 14 */     if (MITEConstant.forced_biome_for_scattered_feature_testing != null) {
/* 15 */       var5 = MITEConstant.forced_biome_for_scattered_feature_testing;
/*    */     }
/* 17 */     double distance_from_world_spawn = par1World.getDistanceFromWorldSpawn(par3 * 16, par4 * 16);
/*    */     
/* 19 */     if (var5 != BiomeGenBase.jungle && var5 != BiomeGenBase.jungleHills) {
/*    */       
/* 21 */       if (var5 == BiomeGenBase.swampland) {
/*    */         
/* 23 */         ComponentScatteredFeatureSwampHut var8 = new ComponentScatteredFeatureSwampHut(par2Random, par3 * 16, par4 * 16);
/* 24 */         this.components.add(var8);
/*    */       }
/*    */       else {
/*    */         
/* 28 */         ComponentScatteredFeatureDesertPyramid var7 = new ComponentScatteredFeatureDesertPyramid(par2Random, par3 * 16, par4 * 16);
/*    */ 
/*    */         
/* 31 */         if (distance_from_world_spawn >= 2000.0D) {
/* 32 */           this.components.add(var7);
/*    */         }
/*    */       } 
/*    */     } else {
/*    */       
/* 37 */       ComponentScatteredFeatureJunglePyramid var6 = new ComponentScatteredFeatureJunglePyramid(par2Random, par3 * 16, par4 * 16);
/*    */ 
/*    */       
/* 40 */       if (distance_from_world_spawn >= 2000.0D) {
/* 41 */         this.components.add(var6);
/*    */       }
/*    */     } 
/* 44 */     updateBoundingBox();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureScatteredFeatureStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */