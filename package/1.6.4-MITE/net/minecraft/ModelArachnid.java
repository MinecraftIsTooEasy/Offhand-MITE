/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelArachnid
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer neck;
/*     */   public ModelRenderer body;
/*     */   public ModelRenderer leg1;
/*     */   public ModelRenderer leg2;
/*     */   public ModelRenderer leg3;
/*     */   public ModelRenderer leg4;
/*     */   public ModelRenderer leg5;
/*     */   public ModelRenderer leg6;
/*     */   public ModelRenderer leg7;
/*     */   public ModelRenderer leg8;
/*     */   
/*     */   public ModelArachnid() {
/*  42 */     float var1 = 0.0F;
/*  43 */     byte var2 = 15;
/*  44 */     this.head = new ModelRenderer(this, 32, 4);
/*  45 */     this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, var1);
/*  46 */     this.head.setRotationPoint(0.0F, var2, -3.0F);
/*  47 */     this.neck = new ModelRenderer(this, 0, 0);
/*  48 */     this.neck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, var1);
/*  49 */     this.neck.setRotationPoint(0.0F, var2, 0.0F);
/*  50 */     this.body = new ModelRenderer(this, 0, 12);
/*  51 */     this.body.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, var1);
/*  52 */     this.body.setRotationPoint(0.0F, var2, 9.0F);
/*  53 */     this.leg1 = new ModelRenderer(this, 18, 0);
/*  54 */     this.leg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  55 */     this.leg1.setRotationPoint(-4.0F, var2, 2.0F);
/*  56 */     this.leg2 = new ModelRenderer(this, 18, 0);
/*  57 */     this.leg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  58 */     this.leg2.setRotationPoint(4.0F, var2, 2.0F);
/*  59 */     this.leg3 = new ModelRenderer(this, 18, 0);
/*  60 */     this.leg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  61 */     this.leg3.setRotationPoint(-4.0F, var2, 1.0F);
/*  62 */     this.leg4 = new ModelRenderer(this, 18, 0);
/*  63 */     this.leg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  64 */     this.leg4.setRotationPoint(4.0F, var2, 1.0F);
/*  65 */     this.leg5 = new ModelRenderer(this, 18, 0);
/*  66 */     this.leg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  67 */     this.leg5.setRotationPoint(-4.0F, var2, 0.0F);
/*  68 */     this.leg6 = new ModelRenderer(this, 18, 0);
/*  69 */     this.leg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  70 */     this.leg6.setRotationPoint(4.0F, var2, 0.0F);
/*  71 */     this.leg7 = new ModelRenderer(this, 18, 0);
/*  72 */     this.leg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  73 */     this.leg7.setRotationPoint(-4.0F, var2, -1.0F);
/*  74 */     this.leg8 = new ModelRenderer(this, 18, 0);
/*  75 */     this.leg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  76 */     this.leg8.setRotationPoint(4.0F, var2, -1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  84 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/*  85 */     this.head.render(par7);
/*  86 */     this.neck.render(par7);
/*  87 */     this.body.render(par7);
/*  88 */     this.leg1.render(par7);
/*  89 */     this.leg2.render(par7);
/*  90 */     this.leg3.render(par7);
/*  91 */     this.leg4.render(par7);
/*  92 */     this.leg5.render(par7);
/*  93 */     this.leg6.render(par7);
/*  94 */     this.leg7.render(par7);
/*  95 */     this.leg8.render(par7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
/* 105 */     this.head.rotateAngleY = par4 / 57.295776F;
/* 106 */     this.head.rotateAngleX = par5 / 57.295776F;
/* 107 */     float var8 = 0.7853982F;
/* 108 */     this.leg1.rotateAngleZ = -var8;
/* 109 */     this.leg2.rotateAngleZ = var8;
/* 110 */     this.leg3.rotateAngleZ = -var8 * 0.74F;
/* 111 */     this.leg4.rotateAngleZ = var8 * 0.74F;
/* 112 */     this.leg5.rotateAngleZ = -var8 * 0.74F;
/* 113 */     this.leg6.rotateAngleZ = var8 * 0.74F;
/* 114 */     this.leg7.rotateAngleZ = -var8;
/* 115 */     this.leg8.rotateAngleZ = var8;
/* 116 */     float var9 = -0.0F;
/* 117 */     float var10 = 0.3926991F;
/* 118 */     this.leg1.rotateAngleY = var10 * 2.0F + var9;
/* 119 */     this.leg2.rotateAngleY = -var10 * 2.0F - var9;
/* 120 */     this.leg3.rotateAngleY = var10 * 1.0F + var9;
/* 121 */     this.leg4.rotateAngleY = -var10 * 1.0F - var9;
/* 122 */     this.leg5.rotateAngleY = -var10 * 1.0F + var9;
/* 123 */     this.leg6.rotateAngleY = var10 * 1.0F - var9;
/* 124 */     this.leg7.rotateAngleY = -var10 * 2.0F + var9;
/* 125 */     this.leg8.rotateAngleY = var10 * 2.0F - var9;
/* 126 */     float var11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
/* 127 */     float var12 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * par2;
/* 128 */     float var13 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * par2;
/* 129 */     float var14 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 4.712389F) * 0.4F) * par2;
/* 130 */     float var15 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
/* 131 */     float var16 = Math.abs(MathHelper.sin(par1 * 0.6662F + 3.1415927F) * 0.4F) * par2;
/* 132 */     float var17 = Math.abs(MathHelper.sin(par1 * 0.6662F + 1.5707964F) * 0.4F) * par2;
/* 133 */     float var18 = Math.abs(MathHelper.sin(par1 * 0.6662F + 4.712389F) * 0.4F) * par2;
/* 134 */     this.leg1.rotateAngleY += var11;
/* 135 */     this.leg2.rotateAngleY += -var11;
/* 136 */     this.leg3.rotateAngleY += var12;
/* 137 */     this.leg4.rotateAngleY += -var12;
/* 138 */     this.leg5.rotateAngleY += var13;
/* 139 */     this.leg6.rotateAngleY += -var13;
/* 140 */     this.leg7.rotateAngleY += var14;
/* 141 */     this.leg8.rotateAngleY += -var14;
/* 142 */     this.leg1.rotateAngleZ += var15;
/* 143 */     this.leg2.rotateAngleZ += -var15;
/* 144 */     this.leg3.rotateAngleZ += var16;
/* 145 */     this.leg4.rotateAngleZ += -var16;
/* 146 */     this.leg5.rotateAngleZ += var17;
/* 147 */     this.leg6.rotateAngleZ += -var17;
/* 148 */     this.leg7.rotateAngleZ += var18;
/* 149 */     this.leg8.rotateAngleZ += -var18;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelArachnid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */