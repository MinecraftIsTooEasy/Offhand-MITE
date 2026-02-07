/*    */ package net.minecraft;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class ModelQuadruped
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer leg1;
/*    */   public ModelRenderer leg2;
/*    */   public ModelRenderer leg3;
/*    */   public ModelRenderer leg4;
/* 14 */   protected float field_78145_g = 8.0F;
/* 15 */   protected float field_78151_h = 4.0F;
/*    */   
/*    */   public ModelQuadruped(int i, float f) {
/* 18 */     this.head = new ModelRenderer(this, 0, 0);
/* 19 */     this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, f);
/* 20 */     this.head.setRotationPoint(0.0F, (18 - i), -6.0F);
/*    */     
/* 22 */     this.body = new ModelRenderer(this, 28, 8);
/* 23 */     this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, f);
/* 24 */     this.body.setRotationPoint(0.0F, (17 - i), 2.0F);
/*    */     
/* 26 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 27 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, i, 4, f);
/* 28 */     this.leg1.setRotationPoint(-3.0F, (24 - i), 7.0F);
/*    */     
/* 30 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 31 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, i, 4, f);
/* 32 */     this.leg2.setRotationPoint(3.0F, (24 - i), 7.0F);
/*    */     
/* 34 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 35 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, i, 4, f);
/* 36 */     this.leg3.setRotationPoint(-3.0F, (24 - i), -5.0F);
/*    */     
/* 38 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 39 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, i, 4, f);
/* 40 */     this.leg4.setRotationPoint(3.0F, (24 - i), -5.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 45 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 47 */     if (this.isChild) {
/* 48 */       float f1 = 2.0F;
/* 49 */       GL11.glPushMatrix();
/* 50 */       GL11.glTranslatef(0.0F, this.field_78145_g * k, this.field_78151_h * k);
/* 51 */       this.head.render(k);
/* 52 */       GL11.glPopMatrix();
/* 53 */       GL11.glPushMatrix();
/* 54 */       GL11.glScalef(1.0F / f1, 1.0F / f1, 1.0F / f1);
/* 55 */       GL11.glTranslatef(0.0F, 24.0F * k, 0.0F);
/* 56 */       this.body.render(k);
/* 57 */       this.leg1.render(k);
/* 58 */       this.leg2.render(k);
/* 59 */       this.leg3.render(k);
/* 60 */       this.leg4.render(k);
/* 61 */       GL11.glPopMatrix();
/*    */     } else {
/* 63 */       this.head.render(k);
/* 64 */       this.body.render(k);
/* 65 */       this.leg1.render(k);
/* 66 */       this.leg2.render(k);
/* 67 */       this.leg3.render(k);
/* 68 */       this.leg4.render(k);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 74 */     float f1 = 57.295776F;
/* 75 */     this.head.rotateAngleX = j / 57.295776F;
/* 76 */     this.head.rotateAngleY = i / 57.295776F;
/* 77 */     this.body.rotateAngleX = 1.5707964F;
/*    */     
/* 79 */     this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
/* 80 */     this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
/* 81 */     this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
/* 82 */     this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelQuadruped.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */