/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class ModelWolf
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer wolfHeadMain;
/*     */   public ModelRenderer wolfBody;
/*     */   public ModelRenderer wolfLeg1;
/*     */   public ModelRenderer wolfLeg2;
/*     */   public ModelRenderer wolfLeg3;
/*     */   public ModelRenderer wolfLeg4;
/*     */   ModelRenderer wolfTail;
/*     */   ModelRenderer wolfMane;
/*     */   
/*     */   public ModelWolf() {
/*  33 */     float var1 = 0.0F;
/*  34 */     float var2 = 13.5F;
/*  35 */     this.wolfHeadMain = new ModelRenderer(this, 0, 0);
/*  36 */     this.wolfHeadMain.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, var1);
/*  37 */     this.wolfHeadMain.setRotationPoint(-1.0F, var2, -7.0F);
/*  38 */     this.wolfBody = new ModelRenderer(this, 18, 14);
/*  39 */     this.wolfBody.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, var1);
/*  40 */     this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
/*  41 */     this.wolfMane = new ModelRenderer(this, 21, 0);
/*  42 */     this.wolfMane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, var1);
/*  43 */     this.wolfMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
/*  44 */     this.wolfLeg1 = new ModelRenderer(this, 0, 18);
/*  45 */     this.wolfLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  46 */     this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
/*  47 */     this.wolfLeg2 = new ModelRenderer(this, 0, 18);
/*  48 */     this.wolfLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  49 */     this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
/*  50 */     this.wolfLeg3 = new ModelRenderer(this, 0, 18);
/*  51 */     this.wolfLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  52 */     this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
/*  53 */     this.wolfLeg4 = new ModelRenderer(this, 0, 18);
/*  54 */     this.wolfLeg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  55 */     this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
/*  56 */     this.wolfTail = new ModelRenderer(this, 9, 18);
/*  57 */     this.wolfTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  58 */     this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
/*  59 */     this.wolfHeadMain.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, var1);
/*  60 */     this.wolfHeadMain.setTextureOffset(16, 14).addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, var1);
/*  61 */     this.wolfHeadMain.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3, 3, 4, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  69 */     super.render(par1Entity, par2, par3, par4, par5, par6, par7);
/*  70 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/*     */     
/*  72 */     if (this.isChild) {
/*     */       
/*  74 */       float var8 = 2.0F;
/*  75 */       GL11.glPushMatrix();
/*  76 */       GL11.glTranslatef(0.0F, 5.0F * par7, 2.0F * par7);
/*  77 */       this.wolfHeadMain.renderWithRotation(par7);
/*  78 */       GL11.glPopMatrix();
/*  79 */       GL11.glPushMatrix();
/*  80 */       GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  81 */       GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
/*  82 */       this.wolfBody.render(par7);
/*  83 */       this.wolfLeg1.render(par7);
/*  84 */       this.wolfLeg2.render(par7);
/*  85 */       this.wolfLeg3.render(par7);
/*  86 */       this.wolfLeg4.render(par7);
/*  87 */       this.wolfTail.renderWithRotation(par7);
/*  88 */       this.wolfMane.render(par7);
/*  89 */       GL11.glPopMatrix();
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.wolfHeadMain.renderWithRotation(par7);
/*  94 */       this.wolfBody.render(par7);
/*  95 */       this.wolfLeg1.render(par7);
/*  96 */       this.wolfLeg2.render(par7);
/*  97 */       this.wolfLeg3.render(par7);
/*  98 */       this.wolfLeg4.render(par7);
/*  99 */       this.wolfTail.renderWithRotation(par7);
/* 100 */       this.wolfMane.render(par7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 110 */     EntityWolf var5 = (EntityWolf)par1EntityLivingBase;
/*     */ 
/*     */     
/* 113 */     if (var5.isAttacking() || var5.isHostileToPlayers()) {
/*     */       
/* 115 */       this.wolfTail.rotateAngleY = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 119 */       this.wolfTail.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
/*     */     } 
/*     */     
/* 122 */     if (var5.isSitting()) {
/*     */       
/* 124 */       this.wolfMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
/* 125 */       this.wolfMane.rotateAngleX = 1.2566371F;
/* 126 */       this.wolfMane.rotateAngleY = 0.0F;
/* 127 */       this.wolfBody.setRotationPoint(0.0F, 18.0F, 0.0F);
/* 128 */       this.wolfBody.rotateAngleX = 0.7853982F;
/* 129 */       this.wolfTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
/* 130 */       this.wolfLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
/* 131 */       this.wolfLeg1.rotateAngleX = 4.712389F;
/* 132 */       this.wolfLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
/* 133 */       this.wolfLeg2.rotateAngleX = 4.712389F;
/* 134 */       this.wolfLeg3.rotateAngleX = 5.811947F;
/* 135 */       this.wolfLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
/* 136 */       this.wolfLeg4.rotateAngleX = 5.811947F;
/* 137 */       this.wolfLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);
/*     */     }
/*     */     else {
/*     */       
/* 141 */       this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
/* 142 */       this.wolfBody.rotateAngleX = 1.5707964F;
/* 143 */       this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
/* 144 */       this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
/* 145 */       this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
/* 146 */       this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
/* 147 */       this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
/* 148 */       this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
/* 149 */       this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
/* 150 */       this.wolfLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
/* 151 */       this.wolfLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + 3.1415927F) * 1.4F * par3;
/* 152 */       this.wolfLeg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F + 3.1415927F) * 1.4F * par3;
/* 153 */       this.wolfLeg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
/*     */     } 
/*     */     
/* 156 */     this.wolfHeadMain.rotateAngleZ = var5.getInterestedAngle(par4) + var5.getShakeAngle(par4, 0.0F);
/* 157 */     this.wolfMane.rotateAngleZ = var5.getShakeAngle(par4, -0.08F);
/* 158 */     this.wolfBody.rotateAngleZ = var5.getShakeAngle(par4, -0.16F);
/* 159 */     this.wolfTail.rotateAngleZ = var5.getShakeAngle(par4, -0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
/* 169 */     super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
/* 170 */     this.wolfHeadMain.rotateAngleX = par5 / 57.295776F;
/* 171 */     this.wolfHeadMain.rotateAngleY = par4 / 57.295776F;
/* 172 */     this.wolfTail.rotateAngleX = par3;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */