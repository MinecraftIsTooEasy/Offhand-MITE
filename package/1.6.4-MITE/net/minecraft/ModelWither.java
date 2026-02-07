/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelWither
/*    */   extends ModelBase
/*    */ {
/*    */   private ModelRenderer[] field_82905_a;
/*    */   private ModelRenderer[] field_82904_b;
/*    */   
/*    */   public ModelWither() {
/* 14 */     this.textureWidth = 64;
/* 15 */     this.textureHeight = 64;
/*    */     
/* 17 */     this.field_82905_a = new ModelRenderer[3];
/*    */     
/* 19 */     this.field_82905_a[0] = new ModelRenderer(this, 0, 16);
/* 20 */     this.field_82905_a[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3);
/*    */     
/* 22 */     this.field_82905_a[1] = (new ModelRenderer(this)).setTextureSize(this.textureWidth, this.textureHeight);
/* 23 */     this.field_82905_a[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
/* 24 */     this.field_82905_a[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3);
/* 25 */     this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2);
/* 26 */     this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2);
/* 27 */     this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2);
/*    */     
/* 29 */     this.field_82905_a[2] = new ModelRenderer(this, 12, 22);
/* 30 */     this.field_82905_a[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3);
/*    */     
/* 32 */     this.field_82904_b = new ModelRenderer[3];
/* 33 */     this.field_82904_b[0] = new ModelRenderer(this, 0, 0);
/* 34 */     this.field_82904_b[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/* 35 */     this.field_82904_b[1] = new ModelRenderer(this, 32, 0);
/* 36 */     this.field_82904_b[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6);
/* 37 */     (this.field_82904_b[1]).rotationPointX = -8.0F;
/* 38 */     (this.field_82904_b[1]).rotationPointY = 4.0F;
/* 39 */     this.field_82904_b[2] = new ModelRenderer(this, 32, 0);
/* 40 */     this.field_82904_b[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6);
/* 41 */     (this.field_82904_b[2]).rotationPointX = 10.0F;
/* 42 */     (this.field_82904_b[2]).rotationPointY = 4.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_82903_a() {
/* 47 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/* 52 */     setRotationAngles(f, g, h, i, j, k, entity);
/*    */     
/* 54 */     for (ModelRenderer modelRenderer : this.field_82904_b) {
/* 55 */       modelRenderer.render(k);
/*    */     }
/* 57 */     for (ModelRenderer modelRenderer : this.field_82905_a) {
/* 58 */       modelRenderer.render(k);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/* 65 */     float f1 = MathHelper.cos(h * 0.1F);
/* 66 */     (this.field_82905_a[1]).rotateAngleX = (0.065F + 0.05F * f1) * 3.1415927F;
/*    */     
/* 68 */     this.field_82905_a[2].setRotationPoint(-2.0F, 6.9F + MathHelper.cos((this.field_82905_a[1]).rotateAngleX) * 10.0F, -0.5F + MathHelper.sin((this.field_82905_a[1]).rotateAngleX) * 10.0F);
/* 69 */     (this.field_82905_a[2]).rotateAngleX = (0.265F + 0.1F * f1) * 3.1415927F;
/*    */     
/* 71 */     (this.field_82904_b[0]).rotateAngleY = i / 57.295776F;
/* 72 */     (this.field_82904_b[0]).rotateAngleX = j / 57.295776F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase entityLivingBase, float f, float g, float h) {
/* 77 */     EntityWither entityWither = (EntityWither)entityLivingBase;
/*    */     
/* 79 */     for (byte b = 1; b < 3; b++) {
/* 80 */       (this.field_82904_b[b]).rotateAngleY = (entityWither.func_82207_a(b - 1) - entityLivingBase.renderYawOffset) / 57.295776F;
/* 81 */       (this.field_82904_b[b]).rotateAngleX = entityWither.func_82210_r(b - 1) / 57.295776F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelWither.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */