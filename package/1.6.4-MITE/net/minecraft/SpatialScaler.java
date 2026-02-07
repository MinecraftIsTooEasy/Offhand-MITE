/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpatialScaler
/*    */ {
/*    */   private static final int scaling_factor_for_pos_xz = 4095;
/*    */   private static final int scaling_factor_for_pos_y = 127;
/*    */   
/*    */   public static int getScalingFactorForRotation() {
/* 16 */     return 256;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledPosX(Entity entity) {
/* 21 */     return entity.myEntitySize.multiplyByNAndRound(entity.posX, 4095);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledPosX(double pos_x) {
/* 26 */     return MathHelper.floor_double(pos_x * 4095.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getPosX(int scaled_pos_x) {
/* 31 */     return scaled_pos_x / 4095.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledPosY(Entity entity) {
/* 36 */     return MathHelper.floor_double(entity.posY * 127.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledPosY(double pos_y) {
/* 41 */     return MathHelper.floor_double(pos_y * 127.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getPosY(int scaled_pos_y) {
/* 46 */     return scaled_pos_y / 127.0D + 0.015625D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getScaledPosZ(Entity entity) {
/* 52 */     return entity.myEntitySize.multiplyByNAndRound(entity.posZ, 4095);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledPosZ(double pos_z) {
/* 57 */     return MathHelper.floor_double(pos_z * 4095.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getPosZ(int scaled_pos_z) {
/* 62 */     return scaled_pos_z / 4095.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledRotation(float rotation) {
/* 67 */     return MathHelper.floor_float(rotation * getScalingFactorForRotation() / 360.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public static float getRotation(int scaled_rotation) {
/* 72 */     return scaled_rotation * 360.0F / getScalingFactorForRotation();
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getScaledMotion(double motion) {
/* 77 */     double var2 = 3.9D;
/* 78 */     double var4 = motion;
/*    */     
/* 80 */     if (var4 < -var2) {
/* 81 */       var4 = -var2;
/* 82 */     } else if (var4 > var2) {
/* 83 */       var4 = var2;
/*    */     } 
/* 85 */     return (int)(var4 * 8000.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getMotion(int scaled_motion) {
/* 90 */     return scaled_motion / 8000.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SpatialScaler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */