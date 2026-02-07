/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelInvisibleStalker
/*    */   extends ModelBiped
/*    */ {
/*    */   public ModelInvisibleStalker() {
/*  9 */     super(0.0F, 0.0F, 64, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
/* 19 */     super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
/* 20 */     float var8 = MathHelper.sin(this.onGround * 3.1415927F);
/* 21 */     float var9 = MathHelper.sin((1.0F - (1.0F - this.onGround) * (1.0F - this.onGround)) * 3.1415927F);
/* 22 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 23 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 24 */     this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F);
/* 25 */     this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F;
/* 26 */     this.bipedRightArm.rotateAngleX = -1.5707964F;
/* 27 */     this.bipedLeftArm.rotateAngleX = -1.5707964F;
/* 28 */     this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 29 */     this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 30 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 31 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 32 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
/* 33 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelInvisibleStalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */