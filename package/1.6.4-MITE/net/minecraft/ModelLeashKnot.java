/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelLeashKnot
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer field_110723_a;
/*    */   
/*    */   public ModelLeashKnot() {
/* 12 */     this(0, 0, 32, 32);
/*    */   }
/*    */   
/*    */   public ModelLeashKnot(int i, int j, int k, int l) {
/* 16 */     this.textureWidth = k;
/* 17 */     this.textureHeight = l;
/* 18 */     this.field_110723_a = new ModelRenderer(this, i, j);
/* 19 */     this.field_110723_a.addBox(-3.0F, -6.0F, -3.0F, 6, 8, 6, 0.0F);
/* 20 */     this.field_110723_a.setRotationPoint(0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 25 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 27 */     this.field_110723_a.render(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 32 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 34 */     this.field_110723_a.rotateAngleY = i / 57.295776F;
/* 35 */     this.field_110723_a.rotateAngleX = j / 57.295776F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelLeashKnot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */