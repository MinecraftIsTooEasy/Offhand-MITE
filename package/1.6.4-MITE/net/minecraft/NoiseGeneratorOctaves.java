/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoiseGeneratorOctaves
/*    */   extends NoiseGenerator
/*    */ {
/*    */   private NoiseGeneratorPerlin[] generatorCollection;
/*    */   private int octaves;
/*    */   
/*    */   public NoiseGeneratorOctaves(Random par1Random, int par2) {
/* 15 */     this.octaves = par2;
/* 16 */     this.generatorCollection = new NoiseGeneratorPerlin[par2];
/*    */     
/* 18 */     for (int var3 = 0; var3 < par2; var3++)
/*    */     {
/* 20 */       this.generatorCollection[var3] = new NoiseGeneratorPerlin(par1Random);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] generateNoiseOctaves(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7, double par8, double par10, double par12) {
/* 30 */     if (par1ArrayOfDouble == null) {
/*    */       
/* 32 */       par1ArrayOfDouble = new double[par5 * par6 * par7];
/*    */     }
/*    */     else {
/*    */       
/* 36 */       for (int var14 = 0; var14 < par1ArrayOfDouble.length; var14++)
/*    */       {
/* 38 */         par1ArrayOfDouble[var14] = 0.0D;
/*    */       }
/*    */     } 
/*    */     
/* 42 */     double var27 = 1.0D;
/*    */     
/* 44 */     for (int var16 = 0; var16 < this.octaves; var16++) {
/*    */       
/* 46 */       double var17 = par2 * var27 * par8;
/* 47 */       double var19 = par3 * var27 * par10;
/* 48 */       double var21 = par4 * var27 * par12;
/* 49 */       long var23 = MathHelper.floor_double_long(var17);
/* 50 */       long var25 = MathHelper.floor_double_long(var21);
/* 51 */       var17 -= var23;
/* 52 */       var21 -= var25;
/* 53 */       var23 %= 16777216L;
/* 54 */       var25 %= 16777216L;
/*    */ 
/*    */       
/* 57 */       var17 += var23;
/* 58 */       var21 += var25;
/* 59 */       this.generatorCollection[var16].populateNoiseArray(par1ArrayOfDouble, var17, var19, var21, par5, par6, par7, par8 * var27, par10 * var27, par12 * var27, var27);
/* 60 */       var27 /= 2.0D;
/*    */     } 
/*    */     
/* 63 */     return par1ArrayOfDouble;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] generateNoiseOctaves(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, double par6, double par8, double par10) {
/* 71 */     return generateNoiseOctaves(par1ArrayOfDouble, par2, 10, par3, par4, 1, par5, par6, 1.0D, par8);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NoiseGeneratorOctaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */