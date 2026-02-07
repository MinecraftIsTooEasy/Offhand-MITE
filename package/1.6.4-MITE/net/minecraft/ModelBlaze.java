/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBlaze
/*    */   extends ModelBase
/*    */ {
/* 13 */   private ModelRenderer[] blazeSticks = new ModelRenderer[12];
/*    */   public ModelBlaze() {
/* 15 */     for (byte b = 0; b < this.blazeSticks.length; b++) {
/* 16 */       this.blazeSticks[b] = new ModelRenderer(this, 0, 16);
/* 17 */       this.blazeSticks[b].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
/*    */     } 
/*    */     
/* 20 */     this.blazeHead = new ModelRenderer(this, 0, 0);
/* 21 */     this.blazeHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*    */   }
/*    */   private ModelRenderer blazeHead;
/*    */   
/*    */   public int func_78104_a() {
/* 26 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 31 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 33 */     this.blazeHead.render(k);
/* 34 */     for (byte b = 0; b < this.blazeSticks.length; b++) {
/* 35 */       this.blazeSticks[b].render(k);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 41 */     float f1 = h * 3.1415927F * -0.1F; byte b;
/* 42 */     for (b = 0; b < 4; b++) {
/* 43 */       (this.blazeSticks[b]).rotationPointY = -2.0F + MathHelper.cos(((b * 2) + h) * 0.25F);
/* 44 */       (this.blazeSticks[b]).rotationPointX = MathHelper.cos(f1) * 9.0F;
/* 45 */       (this.blazeSticks[b]).rotationPointZ = MathHelper.sin(f1) * 9.0F;
/* 46 */       f1 += 1.5707964F;
/*    */     } 
/*    */     
/* 49 */     f1 = 0.7853982F + h * 3.1415927F * 0.03F;
/* 50 */     for (b = 4; b < 8; b++) {
/* 51 */       (this.blazeSticks[b]).rotationPointY = 2.0F + MathHelper.cos(((b * 2) + h) * 0.25F);
/* 52 */       (this.blazeSticks[b]).rotationPointX = MathHelper.cos(f1) * 7.0F;
/* 53 */       (this.blazeSticks[b]).rotationPointZ = MathHelper.sin(f1) * 7.0F;
/* 54 */       f1 += 1.5707964F;
/*    */     } 
/*    */     
/* 57 */     f1 = 0.47123894F + h * 3.1415927F * -0.05F;
/* 58 */     for (b = 8; b < 12; b++) {
/* 59 */       (this.blazeSticks[b]).rotationPointY = 11.0F + MathHelper.cos((b * 1.5F + h) * 0.5F);
/* 60 */       (this.blazeSticks[b]).rotationPointX = MathHelper.cos(f1) * 5.0F;
/* 61 */       (this.blazeSticks[b]).rotationPointZ = MathHelper.sin(f1) * 5.0F;
/* 62 */       f1 += 1.5707964F;
/*    */     } 
/*    */     
/* 65 */     this.blazeHead.rotateAngleY = i / 57.295776F;
/* 66 */     this.blazeHead.rotateAngleX = j / 57.295776F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBlaze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */