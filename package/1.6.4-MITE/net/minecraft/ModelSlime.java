/*    */ package net.minecraft;
/*    */ 
/*    */ public class ModelSlime
/*    */   extends ModelBase {
/*    */   ModelRenderer slimeBodies;
/*    */   ModelRenderer slimeRightEye;
/*    */   ModelRenderer slimeLeftEye;
/*    */   ModelRenderer slimeMouth;
/*    */   
/*    */   public ModelSlime(int i) {
/* 11 */     this.slimeBodies = new ModelRenderer(this, 0, i);
/* 12 */     this.slimeBodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
/* 13 */     if (i > 0) {
/* 14 */       this.slimeBodies = new ModelRenderer(this, 0, i);
/* 15 */       this.slimeBodies.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
/*    */       
/* 17 */       this.slimeRightEye = new ModelRenderer(this, 32, 0);
/* 18 */       this.slimeRightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
/*    */       
/* 20 */       this.slimeLeftEye = new ModelRenderer(this, 32, 4);
/* 21 */       this.slimeLeftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
/*    */       
/* 23 */       this.slimeMouth = new ModelRenderer(this, 32, 8);
/* 24 */       this.slimeMouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 30 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 32 */     this.slimeBodies.render(k);
/* 33 */     if (this.slimeRightEye != null) {
/* 34 */       this.slimeRightEye.render(k);
/* 35 */       this.slimeLeftEye.render(k);
/* 36 */       this.slimeMouth.render(k);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */