/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelBox
/*    */ {
/*    */   private PositionTextureVertex[] vertexPositions;
/*    */   private TexturedQuad[] quadList;
/*    */   public final float posX1;
/*    */   public final float posY1;
/*    */   
/*    */   public ModelBox(ModelRenderer modelRenderer, int i, int j, float f, float g, float h, int k, int l, int m, float n) {
/* 14 */     this.posX1 = f;
/* 15 */     this.posY1 = g;
/* 16 */     this.posZ1 = h;
/* 17 */     this.posX2 = f + k;
/* 18 */     this.posY2 = g + l;
/* 19 */     this.posZ2 = h + m;
/* 20 */     this.vertexPositions = new PositionTextureVertex[8];
/* 21 */     this.quadList = new TexturedQuad[6];
/*    */     
/* 23 */     float f1 = f + k;
/* 24 */     float f2 = g + l;
/* 25 */     float f3 = h + m;
/*    */     
/* 27 */     f -= n;
/* 28 */     g -= n;
/* 29 */     h -= n;
/* 30 */     f1 += n;
/* 31 */     f2 += n;
/* 32 */     f3 += n;
/*    */     
/* 34 */     if (modelRenderer.mirror) {
/* 35 */       float f4 = f1;
/* 36 */       f1 = f;
/* 37 */       f = f4;
/*    */     } 
/*    */     
/* 40 */     PositionTextureVertex positionTextureVertex1 = new PositionTextureVertex(f, g, h, 0.0F, 0.0F);
/* 41 */     PositionTextureVertex positionTextureVertex2 = new PositionTextureVertex(f1, g, h, 0.0F, 8.0F);
/* 42 */     PositionTextureVertex positionTextureVertex3 = new PositionTextureVertex(f1, f2, h, 8.0F, 8.0F);
/* 43 */     PositionTextureVertex positionTextureVertex4 = new PositionTextureVertex(f, f2, h, 8.0F, 0.0F);
/*    */     
/* 45 */     PositionTextureVertex positionTextureVertex5 = new PositionTextureVertex(f, g, f3, 0.0F, 0.0F);
/* 46 */     PositionTextureVertex positionTextureVertex6 = new PositionTextureVertex(f1, g, f3, 0.0F, 8.0F);
/* 47 */     PositionTextureVertex positionTextureVertex7 = new PositionTextureVertex(f1, f2, f3, 8.0F, 8.0F);
/* 48 */     PositionTextureVertex positionTextureVertex8 = new PositionTextureVertex(f, f2, f3, 8.0F, 0.0F);
/*    */     
/* 50 */     this.vertexPositions[0] = positionTextureVertex1;
/* 51 */     this.vertexPositions[1] = positionTextureVertex2;
/* 52 */     this.vertexPositions[2] = positionTextureVertex3;
/* 53 */     this.vertexPositions[3] = positionTextureVertex4;
/* 54 */     this.vertexPositions[4] = positionTextureVertex5;
/* 55 */     this.vertexPositions[5] = positionTextureVertex6;
/* 56 */     this.vertexPositions[6] = positionTextureVertex7;
/* 57 */     this.vertexPositions[7] = positionTextureVertex8;
/*    */     
/* 59 */     this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { positionTextureVertex6, positionTextureVertex2, positionTextureVertex3, positionTextureVertex7 }, i + m + k, j + m, i + m + k + m, j + m + l, modelRenderer.textureWidth, modelRenderer.textureHeight);
/*    */ 
/*    */     
/* 62 */     this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] { positionTextureVertex1, positionTextureVertex5, positionTextureVertex8, positionTextureVertex4 }, i, j + m, i + m, j + m + l, modelRenderer.textureWidth, modelRenderer.textureHeight);
/*    */ 
/*    */ 
/*    */     
/* 66 */     this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] { positionTextureVertex6, positionTextureVertex5, positionTextureVertex1, positionTextureVertex2 }, i + m, j, i + m + k, j + m, modelRenderer.textureWidth, modelRenderer.textureHeight);
/*    */ 
/*    */     
/* 69 */     this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] { positionTextureVertex3, positionTextureVertex4, positionTextureVertex8, positionTextureVertex7 }, i + m + k, j + m, i + m + k + k, j, modelRenderer.textureWidth, modelRenderer.textureHeight);
/*    */ 
/*    */ 
/*    */     
/* 73 */     this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] { positionTextureVertex2, positionTextureVertex1, positionTextureVertex4, positionTextureVertex3 }, i + m, j + m, i + m + k, j + m + l, modelRenderer.textureWidth, modelRenderer.textureHeight);
/*    */ 
/*    */     
/* 76 */     this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] { positionTextureVertex5, positionTextureVertex6, positionTextureVertex7, positionTextureVertex8 }, i + m + k + m, j + m, i + m + k + m + k, j + m + l, modelRenderer.textureWidth, modelRenderer.textureHeight);
/*    */ 
/*    */ 
/*    */     
/* 80 */     if (modelRenderer.mirror)
/* 81 */       for (byte b = 0; b < this.quadList.length; b++)
/* 82 */         this.quadList[b].flipFace();  
/*    */   }
/*    */   public final float posZ1; public final float posX2; public final float posY2; public final float posZ2; public String field_78247_g;
/*    */   
/*    */   public void render(Tessellator tessellator, float f) {
/* 87 */     for (byte b = 0; b < this.quadList.length; b++) {
/* 88 */       this.quadList[b].draw(tessellator, f);
/*    */     }
/*    */   }
/*    */   
/*    */   public ModelBox func_78244_a(String string) {
/* 93 */     this.field_78247_g = string;
/* 94 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */