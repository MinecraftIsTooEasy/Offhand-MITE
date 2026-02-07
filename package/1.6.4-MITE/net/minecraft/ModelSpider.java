/*     */ package net.minecraft;
/*     */ 
/*     */ public class ModelSpider extends ModelBase {
/*     */   public ModelRenderer spiderHead;
/*     */   public ModelRenderer spiderNeck;
/*     */   public ModelRenderer spiderBody;
/*     */   public ModelRenderer spiderLeg1;
/*     */   public ModelRenderer spiderLeg2;
/*     */   
/*     */   public ModelSpider() {
/*  11 */     float f = 0.0F;
/*     */     
/*  13 */     byte b = 15;
/*     */     
/*  15 */     this.spiderHead = new ModelRenderer(this, 32, 4);
/*  16 */     this.spiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, f);
/*  17 */     this.spiderHead.setRotationPoint(0.0F, b, -3.0F);
/*     */     
/*  19 */     this.spiderNeck = new ModelRenderer(this, 0, 0);
/*  20 */     this.spiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, f);
/*  21 */     this.spiderNeck.setRotationPoint(0.0F, b, 0.0F);
/*     */     
/*  23 */     this.spiderBody = new ModelRenderer(this, 0, 12);
/*  24 */     this.spiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, f);
/*  25 */     this.spiderBody.setRotationPoint(0.0F, b, 9.0F);
/*     */     
/*  27 */     this.spiderLeg1 = new ModelRenderer(this, 18, 0);
/*  28 */     this.spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  29 */     this.spiderLeg1.setRotationPoint(-4.0F, b, 2.0F);
/*     */     
/*  31 */     this.spiderLeg2 = new ModelRenderer(this, 18, 0);
/*  32 */     this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  33 */     this.spiderLeg2.setRotationPoint(4.0F, b, 2.0F);
/*     */     
/*  35 */     this.spiderLeg3 = new ModelRenderer(this, 18, 0);
/*  36 */     this.spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  37 */     this.spiderLeg3.setRotationPoint(-4.0F, b, 1.0F);
/*     */     
/*  39 */     this.spiderLeg4 = new ModelRenderer(this, 18, 0);
/*  40 */     this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  41 */     this.spiderLeg4.setRotationPoint(4.0F, b, 1.0F);
/*     */     
/*  43 */     this.spiderLeg5 = new ModelRenderer(this, 18, 0);
/*  44 */     this.spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  45 */     this.spiderLeg5.setRotationPoint(-4.0F, b, 0.0F);
/*     */     
/*  47 */     this.spiderLeg6 = new ModelRenderer(this, 18, 0);
/*  48 */     this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  49 */     this.spiderLeg6.setRotationPoint(4.0F, b, 0.0F);
/*     */     
/*  51 */     this.spiderLeg7 = new ModelRenderer(this, 18, 0);
/*  52 */     this.spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  53 */     this.spiderLeg7.setRotationPoint(-4.0F, b, -1.0F);
/*     */     
/*  55 */     this.spiderLeg8 = new ModelRenderer(this, 18, 0);
/*  56 */     this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
/*  57 */     this.spiderLeg8.setRotationPoint(4.0F, b, -1.0F);
/*     */   }
/*     */   public ModelRenderer spiderLeg3; public ModelRenderer spiderLeg4; public ModelRenderer spiderLeg5; public ModelRenderer spiderLeg6; public ModelRenderer spiderLeg7; public ModelRenderer spiderLeg8;
/*     */   
/*     */   public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
/*  62 */     setRotationAngles(f, g, h, i, j, k, entity);
/*     */     
/*  64 */     this.spiderHead.render(k);
/*  65 */     this.spiderNeck.render(k);
/*  66 */     this.spiderBody.render(k);
/*  67 */     this.spiderLeg1.render(k);
/*  68 */     this.spiderLeg2.render(k);
/*  69 */     this.spiderLeg3.render(k);
/*  70 */     this.spiderLeg4.render(k);
/*  71 */     this.spiderLeg5.render(k);
/*  72 */     this.spiderLeg6.render(k);
/*  73 */     this.spiderLeg7.render(k);
/*  74 */     this.spiderLeg8.render(k);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float g, float h, float i, float j, float k, Entity entity) {
/*  79 */     this.spiderHead.rotateAngleY = i / 57.295776F;
/*  80 */     this.spiderHead.rotateAngleX = j / 57.295776F;
/*     */     
/*  82 */     float f1 = 0.7853982F;
/*  83 */     this.spiderLeg1.rotateAngleZ = -f1;
/*  84 */     this.spiderLeg2.rotateAngleZ = f1;
/*     */     
/*  86 */     this.spiderLeg3.rotateAngleZ = -f1 * 0.74F;
/*  87 */     this.spiderLeg4.rotateAngleZ = f1 * 0.74F;
/*     */     
/*  89 */     this.spiderLeg5.rotateAngleZ = -f1 * 0.74F;
/*  90 */     this.spiderLeg6.rotateAngleZ = f1 * 0.74F;
/*     */     
/*  92 */     this.spiderLeg7.rotateAngleZ = -f1;
/*  93 */     this.spiderLeg8.rotateAngleZ = f1;
/*     */     
/*  95 */     float f2 = -0.0F;
/*  96 */     float f3 = 0.3926991F;
/*  97 */     this.spiderLeg1.rotateAngleY = f3 * 2.0F + f2;
/*  98 */     this.spiderLeg2.rotateAngleY = -f3 * 2.0F - f2;
/*  99 */     this.spiderLeg3.rotateAngleY = f3 * 1.0F + f2;
/* 100 */     this.spiderLeg4.rotateAngleY = -f3 * 1.0F - f2;
/* 101 */     this.spiderLeg5.rotateAngleY = -f3 * 1.0F + f2;
/* 102 */     this.spiderLeg6.rotateAngleY = f3 * 1.0F - f2;
/* 103 */     this.spiderLeg7.rotateAngleY = -f3 * 2.0F + f2;
/* 104 */     this.spiderLeg8.rotateAngleY = f3 * 2.0F - f2;
/*     */     
/* 106 */     float f4 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * g;
/* 107 */     float f5 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * g;
/* 108 */     float f6 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * g;
/* 109 */     float f7 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * g;
/*     */     
/* 111 */     float f8 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * g;
/* 112 */     float f9 = Math.abs(MathHelper.sin(f * 0.6662F + 3.1415927F) * 0.4F) * g;
/* 113 */     float f10 = Math.abs(MathHelper.sin(f * 0.6662F + 1.5707964F) * 0.4F) * g;
/* 114 */     float f11 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * g;
/*     */     
/* 116 */     this.spiderLeg1.rotateAngleY += f4;
/* 117 */     this.spiderLeg2.rotateAngleY += -f4;
/* 118 */     this.spiderLeg3.rotateAngleY += f5;
/* 119 */     this.spiderLeg4.rotateAngleY += -f5;
/* 120 */     this.spiderLeg5.rotateAngleY += f6;
/* 121 */     this.spiderLeg6.rotateAngleY += -f6;
/* 122 */     this.spiderLeg7.rotateAngleY += f7;
/* 123 */     this.spiderLeg8.rotateAngleY += -f7;
/*     */     
/* 125 */     this.spiderLeg1.rotateAngleZ += f8;
/* 126 */     this.spiderLeg2.rotateAngleZ += -f8;
/* 127 */     this.spiderLeg3.rotateAngleZ += f9;
/* 128 */     this.spiderLeg4.rotateAngleZ += -f9;
/* 129 */     this.spiderLeg5.rotateAngleZ += f10;
/* 130 */     this.spiderLeg6.rotateAngleZ += -f10;
/* 131 */     this.spiderLeg7.rotateAngleZ += f11;
/* 132 */     this.spiderLeg8.rotateAngleZ += -f11;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */