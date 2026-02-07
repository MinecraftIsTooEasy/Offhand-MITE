/*    */ package net.minecraft;
/*    */ 
/*    */ public class ClippingHelper
/*    */ {
/*  5 */   public float[][] frustum = new float[16][16];
/*  6 */   public float[] projectionMatrix = new float[16];
/*  7 */   public float[] modelviewMatrix = new float[16];
/*  8 */   public float[] clippingMatrix = new float[16];
/*    */   public float frustum_0_0;
/*    */   public float frustum_0_1;
/*    */   public float frustum_0_2;
/*    */   public float frustum_0_3;
/*    */   public float frustum_1_0;
/*    */   public float frustum_1_1;
/*    */   public float frustum_1_2;
/*    */   public float frustum_1_3;
/*    */   public float frustum_2_0;
/*    */   public float frustum_2_1;
/*    */   public float frustum_2_2;
/*    */   public float frustum_2_3;
/*    */   public float frustum_3_0;
/*    */   public float frustum_3_1;
/*    */   public float frustum_3_2;
/*    */   public float frustum_3_3;
/*    */   public float frustum_4_0;
/*    */   public float frustum_4_1;
/*    */   public float frustum_4_2;
/*    */   public float frustum_4_3;
/*    */   public float frustum_5_0;
/*    */   public float frustum_5_1;
/*    */   public float frustum_5_2;
/*    */   public float frustum_5_3;
/*    */   
/*    */   public final boolean isBoxInFrustumMITE(double par1, double par3, double par5, double par7, double par9, double par11) {
/* 35 */     if (this.frustum_0_0 * par1 + this.frustum_0_1 * par3 + this.frustum_0_2 * par5 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par7 + this.frustum_0_1 * par3 + this.frustum_0_2 * par5 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par1 + this.frustum_0_1 * par9 + this.frustum_0_2 * par5 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par7 + this.frustum_0_1 * par9 + this.frustum_0_2 * par5 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par1 + this.frustum_0_1 * par3 + this.frustum_0_2 * par11 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par7 + this.frustum_0_1 * par3 + this.frustum_0_2 * par11 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par1 + this.frustum_0_1 * par9 + this.frustum_0_2 * par11 + this.frustum_0_3 <= 0.0D && this.frustum_0_0 * par7 + this.frustum_0_1 * par9 + this.frustum_0_2 * par11 + this.frustum_0_3 <= 0.0D) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (this.frustum_1_0 * par1 + this.frustum_1_1 * par3 + this.frustum_1_2 * par5 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par7 + this.frustum_1_1 * par3 + this.frustum_1_2 * par5 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par1 + this.frustum_1_1 * par9 + this.frustum_1_2 * par5 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par7 + this.frustum_1_1 * par9 + this.frustum_1_2 * par5 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par1 + this.frustum_1_1 * par3 + this.frustum_1_2 * par11 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par7 + this.frustum_1_1 * par3 + this.frustum_1_2 * par11 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par1 + this.frustum_1_1 * par9 + this.frustum_1_2 * par11 + this.frustum_1_3 <= 0.0D && this.frustum_1_0 * par7 + this.frustum_1_1 * par9 + this.frustum_1_2 * par11 + this.frustum_1_3 <= 0.0D) {
/* 39 */       return false;
/*    */     }
/* 41 */     if (this.frustum_2_0 * par1 + this.frustum_2_1 * par3 + this.frustum_2_2 * par5 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par7 + this.frustum_2_1 * par3 + this.frustum_2_2 * par5 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par1 + this.frustum_2_1 * par9 + this.frustum_2_2 * par5 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par7 + this.frustum_2_1 * par9 + this.frustum_2_2 * par5 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par1 + this.frustum_2_1 * par3 + this.frustum_2_2 * par11 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par7 + this.frustum_2_1 * par3 + this.frustum_2_2 * par11 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par1 + this.frustum_2_1 * par9 + this.frustum_2_2 * par11 + this.frustum_2_3 <= 0.0D && this.frustum_2_0 * par7 + this.frustum_2_1 * par9 + this.frustum_2_2 * par11 + this.frustum_2_3 <= 0.0D) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (this.frustum_3_0 * par1 + this.frustum_3_1 * par3 + this.frustum_3_2 * par5 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par7 + this.frustum_3_1 * par3 + this.frustum_3_2 * par5 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par1 + this.frustum_3_1 * par9 + this.frustum_3_2 * par5 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par7 + this.frustum_3_1 * par9 + this.frustum_3_2 * par5 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par1 + this.frustum_3_1 * par3 + this.frustum_3_2 * par11 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par7 + this.frustum_3_1 * par3 + this.frustum_3_2 * par11 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par1 + this.frustum_3_1 * par9 + this.frustum_3_2 * par11 + this.frustum_3_3 <= 0.0D && this.frustum_3_0 * par7 + this.frustum_3_1 * par9 + this.frustum_3_2 * par11 + this.frustum_3_3 <= 0.0D) {
/* 45 */       return false;
/*    */     }
/* 47 */     if (this.frustum_4_0 * par1 + this.frustum_4_1 * par3 + this.frustum_4_2 * par5 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par7 + this.frustum_4_1 * par3 + this.frustum_4_2 * par5 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par1 + this.frustum_4_1 * par9 + this.frustum_4_2 * par5 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par7 + this.frustum_4_1 * par9 + this.frustum_4_2 * par5 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par1 + this.frustum_4_1 * par3 + this.frustum_4_2 * par11 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par7 + this.frustum_4_1 * par3 + this.frustum_4_2 * par11 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par1 + this.frustum_4_1 * par9 + this.frustum_4_2 * par11 + this.frustum_4_3 <= 0.0D && this.frustum_4_0 * par7 + this.frustum_4_1 * par9 + this.frustum_4_2 * par11 + this.frustum_4_3 <= 0.0D) {
/* 48 */       return false;
/*    */     }
/* 50 */     if (this.frustum_5_0 * par1 + this.frustum_5_1 * par3 + this.frustum_5_2 * par5 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par7 + this.frustum_5_1 * par3 + this.frustum_5_2 * par5 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par1 + this.frustum_5_1 * par9 + this.frustum_5_2 * par5 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par7 + this.frustum_5_1 * par9 + this.frustum_5_2 * par5 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par1 + this.frustum_5_1 * par3 + this.frustum_5_2 * par11 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par7 + this.frustum_5_1 * par3 + this.frustum_5_2 * par11 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par1 + this.frustum_5_1 * par9 + this.frustum_5_2 * par11 + this.frustum_5_3 <= 0.0D && this.frustum_5_0 * par7 + this.frustum_5_1 * par9 + this.frustum_5_2 * par11 + this.frustum_5_3 <= 0.0D) {
/* 51 */       return false;
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getFloat(String field) {
/*    */     try {
/* 60 */       return getClass().getDeclaredField(field).getFloat(this);
/*    */     }
/* 62 */     catch (Exception e) {
/*    */       
/* 64 */       return 0.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ClippingHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */