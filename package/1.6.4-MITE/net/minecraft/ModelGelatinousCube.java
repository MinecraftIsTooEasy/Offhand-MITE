/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelGelatinousCube
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer bodies;
/*    */   ModelRenderer right_eye;
/*    */   ModelRenderer left_eye;
/*    */   ModelRenderer mouth;
/*    */   
/*    */   public ModelGelatinousCube(int par1) {
/* 15 */     this.bodies = new ModelRenderer(this, 0, par1);
/* 16 */     this.bodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
/*    */     
/* 18 */     if (par1 > 0) {
/*    */       
/* 20 */       this.bodies = new ModelRenderer(this, 0, par1);
/* 21 */       this.bodies.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
/* 22 */       this.right_eye = new ModelRenderer(this, 32, 0);
/* 23 */       this.right_eye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
/* 24 */       this.left_eye = new ModelRenderer(this, 32, 4);
/* 25 */       this.left_eye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
/* 26 */       this.mouth = new ModelRenderer(this, 32, 8);
/* 27 */       this.mouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity par1Entity, float limb_swing_fraction, float limb_swing_extent, float ticks_existed_plus_partial_tick, float yaw_difference, float pitch, float par7) {
/* 36 */     setRotationAngles(limb_swing_fraction, limb_swing_extent, ticks_existed_plus_partial_tick, yaw_difference, pitch, par7, par1Entity);
/* 37 */     this.bodies.render(par7);
/*    */     
/* 39 */     if (this.right_eye != null) {
/*    */       
/* 41 */       this.right_eye.render(par7);
/* 42 */       this.left_eye.render(par7);
/* 43 */       this.mouth.render(par7);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelGelatinousCube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */