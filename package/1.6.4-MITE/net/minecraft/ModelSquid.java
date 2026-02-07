/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ModelSquid
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer squidBody;
/*  8 */   ModelRenderer[] squidTentacles = new ModelRenderer[8];
/*    */   
/*    */   public ModelSquid() {
/* 11 */     byte b = -16;
/* 12 */     this.squidBody = new ModelRenderer(this, 0, 0);
/* 13 */     this.squidBody.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
/* 14 */     this.squidBody.rotationPointY += (24 + b);
/*    */     
/* 16 */     for (byte b1 = 0; b1 < this.squidTentacles.length; b1++) {
/* 17 */       this.squidTentacles[b1] = new ModelRenderer(this, 48, 0);
/*    */       
/* 19 */       double d = b1 * Math.PI * 2.0D / this.squidTentacles.length;
/* 20 */       float f1 = (float)Math.cos(d) * 5.0F;
/* 21 */       float f2 = (float)Math.sin(d) * 5.0F;
/* 22 */       this.squidTentacles[b1].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
/*    */       
/* 24 */       (this.squidTentacles[b1]).rotationPointX = f1;
/* 25 */       (this.squidTentacles[b1]).rotationPointZ = f2;
/* 26 */       (this.squidTentacles[b1]).rotationPointY = (31 + b);
/*    */       
/* 28 */       d = b1 * Math.PI * -2.0D / this.squidTentacles.length + 1.5707963267948966D;
/* 29 */       (this.squidTentacles[b1]).rotateAngleY = (float)d;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 35 */     for (ModelRenderer modelRenderer : this.squidTentacles)
/*    */     {
/* 37 */       modelRenderer.rotateAngleX = h;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 43 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 45 */     this.squidBody.render(k);
/* 46 */     for (byte b = 0; b < this.squidTentacles.length; b++)
/* 47 */       this.squidTentacles[b].render(k); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSquid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */