/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class PositionTextureVertex
/*    */ {
/*    */   public Vec3 vector3D;
/*    */   public float texturePositionX;
/*    */   public float texturePositionY;
/*    */   
/*    */   public PositionTextureVertex(float f, float g, float h, float i, float j) {
/* 11 */     this(Vec3.createVectorHelper(f, g, h), i, j);
/*    */   }
/*    */   
/*    */   public PositionTextureVertex setTexturePosition(float f, float g) {
/* 15 */     return new PositionTextureVertex(this, f, g);
/*    */   }
/*    */   
/*    */   public PositionTextureVertex(PositionTextureVertex positionTextureVertex, float f, float g) {
/* 19 */     this.vector3D = positionTextureVertex.vector3D;
/* 20 */     this.texturePositionX = f;
/* 21 */     this.texturePositionY = g;
/*    */   }
/*    */   
/*    */   public PositionTextureVertex(Vec3 vec3, float f, float g) {
/* 25 */     this.vector3D = vec3;
/* 26 */     this.texturePositionX = f;
/* 27 */     this.texturePositionY = g;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PositionTextureVertex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */