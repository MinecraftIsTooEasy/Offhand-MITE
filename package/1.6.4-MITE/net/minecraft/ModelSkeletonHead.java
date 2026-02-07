/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelSkeletonHead
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer skeletonHead;
/*    */   
/*    */   public ModelSkeletonHead() {
/* 12 */     this(0, 35, 64, 64);
/*    */   }
/*    */   
/*    */   public ModelSkeletonHead(int i, int j, int k, int l) {
/* 16 */     this.textureWidth = k;
/* 17 */     this.textureHeight = l;
/* 18 */     this.skeletonHead = new ModelRenderer(this, i, j);
/* 19 */     this.skeletonHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
/* 20 */     this.skeletonHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 25 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 27 */     this.skeletonHead.render(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 32 */     super.setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 34 */     this.skeletonHead.rotateAngleY = i / 57.295776F;
/* 35 */     this.skeletonHead.rotateAngleX = j / 57.295776F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSkeletonHead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */