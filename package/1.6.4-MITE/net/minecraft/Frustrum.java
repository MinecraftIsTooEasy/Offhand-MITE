/*    */ package net.minecraft;
/*    */ 
/*    */ public final class Frustrum
/*    */   implements ICamera
/*    */ {
/*  6 */   private ClippingHelper clippingHelper = ClippingHelperImpl.getInstance();
/*    */   
/*    */   private double xPosition;
/*    */   private double yPosition;
/*    */   private double zPosition;
/*    */   
/*    */   public void setPosition(double par1, double par3, double par5) {
/* 13 */     this.xPosition = par1;
/* 14 */     this.yPosition = par3;
/* 15 */     this.zPosition = par5;
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
/*    */   public boolean isBoxInFrustum(double par1, double par3, double par5, double par7, double par9, double par11) {
/* 30 */     return this.clippingHelper.isBoxInFrustumMITE(par1 - this.xPosition, par3 - this.yPosition, par5 - this.zPosition, par7 - this.xPosition, par9 - this.yPosition, par11 - this.zPosition);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBoundingBoxInFrustum(AxisAlignedBB par1AxisAlignedBB) {
/* 38 */     return isBoxInFrustum(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ, par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Frustrum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */