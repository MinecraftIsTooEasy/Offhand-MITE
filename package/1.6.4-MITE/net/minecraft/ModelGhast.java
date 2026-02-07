/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelGhast
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer body;
/* 16 */   ModelRenderer[] tentacles = new ModelRenderer[9];
/*    */   
/*    */   public ModelGhast() {
/* 19 */     byte b = -16;
/* 20 */     this.body = new ModelRenderer(this, 0, 0);
/* 21 */     this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
/* 22 */     this.body.rotationPointY += (24 + b);
/*    */     
/* 24 */     Random random = new Random(1660L);
/* 25 */     for (byte b1 = 0; b1 < this.tentacles.length; b1++) {
/* 26 */       this.tentacles[b1] = new ModelRenderer(this, 0, 0);
/*    */       
/* 28 */       float f1 = (((b1 % 3) - (b1 / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
/* 29 */       float f2 = ((b1 / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
/* 30 */       int i = random.nextInt(7) + 8;
/* 31 */       this.tentacles[b1].addBox(-1.0F, 0.0F, -1.0F, 2, i, 2);
/*    */       
/* 33 */       (this.tentacles[b1]).rotationPointX = f1;
/* 34 */       (this.tentacles[b1]).rotationPointZ = f2;
/* 35 */       (this.tentacles[b1]).rotationPointY = (31 + b);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 41 */     for (byte b = 0; b < this.tentacles.length; b++)
/*    */     {
/* 43 */       (this.tentacles[b]).rotateAngleX = 0.2F * MathHelper.sin(h * 0.3F + b) + 0.4F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 49 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 51 */     GL11.glPushMatrix();
/* 52 */     GL11.glTranslatef(0.0F, 0.6F, 0.0F);
/*    */     
/* 54 */     this.body.render(k);
/* 55 */     for (ModelRenderer modelRenderer : this.tentacles) {
/* 56 */       modelRenderer.render(k);
/*    */     }
/*    */     
/* 59 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelGhast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */