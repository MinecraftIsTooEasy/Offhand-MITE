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
/*     */ public class ModelBat
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer batHead;
/*     */   private ModelRenderer batBody;
/*     */   private ModelRenderer batRightWing;
/*     */   private ModelRenderer batLeftWing;
/*     */   private ModelRenderer batOuterRightWing;
/*     */   private ModelRenderer batOuterLeftWing;
/*     */   
/*     */   public ModelBat() {
/*  24 */     this.textureWidth = 64;
/*  25 */     this.textureHeight = 64;
/*  26 */     this.batHead = new ModelRenderer(this, 0, 0);
/*  27 */     this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
/*  28 */     ModelRenderer var1 = new ModelRenderer(this, 24, 0);
/*  29 */     var1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
/*  30 */     this.batHead.addChild(var1);
/*  31 */     ModelRenderer var2 = new ModelRenderer(this, 24, 0);
/*  32 */     var2.mirror = true;
/*  33 */     var2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
/*  34 */     this.batHead.addChild(var2);
/*  35 */     this.batBody = new ModelRenderer(this, 0, 16);
/*  36 */     this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
/*  37 */     this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
/*  38 */     this.batRightWing = new ModelRenderer(this, 42, 0);
/*  39 */     this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
/*  40 */     this.batOuterRightWing = new ModelRenderer(this, 24, 16);
/*  41 */     this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
/*  42 */     this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
/*  43 */     this.batLeftWing = new ModelRenderer(this, 42, 0);
/*  44 */     this.batLeftWing.mirror = true;
/*  45 */     this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
/*  46 */     this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
/*  47 */     this.batOuterLeftWing.mirror = true;
/*  48 */     this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
/*  49 */     this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
/*  50 */     this.batBody.addChild(this.batRightWing);
/*  51 */     this.batBody.addChild(this.batLeftWing);
/*  52 */     this.batRightWing.addChild(this.batOuterRightWing);
/*  53 */     this.batLeftWing.addChild(this.batOuterLeftWing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBatSize() {
/*  62 */     return 36;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  70 */     EntityBat var8 = (EntityBat)par1Entity;
/*     */ 
/*     */     
/*  73 */     float original_bat_head_offset_y = this.batHead.offsetY;
/*  74 */     float original_right_bat_wing_offset_y = this.batRightWing.offsetY;
/*  75 */     float original_left_bat_wing_offset_y = this.batLeftWing.offsetY;
/*     */     
/*  77 */     if (var8.getIsBatHanging()) {
/*     */       
/*  79 */       this.batHead.offsetY += 0.0625F;
/*  80 */       this.batRightWing.offsetY -= 0.0625F;
/*  81 */       this.batLeftWing.offsetY -= 0.0625F;
/*     */       
/*  83 */       float var9 = 57.295776F;
/*  84 */       this.batHead.rotateAngleX = par6 / 57.295776F;
/*  85 */       this.batHead.rotateAngleY = 3.1415927F - par5 / 57.295776F;
/*  86 */       this.batHead.rotateAngleZ = 3.1415927F;
/*  87 */       this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
/*  88 */       this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
/*  89 */       this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
/*  90 */       this.batBody.rotateAngleX = 3.1415927F;
/*  91 */       this.batRightWing.rotateAngleX = -0.15707964F;
/*  92 */       this.batRightWing.rotateAngleY = -1.2566371F;
/*  93 */       this.batOuterRightWing.rotateAngleY = -1.7278761F;
/*  94 */       this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
/*  95 */       this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
/*  96 */       this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
/*     */     }
/*     */     else {
/*     */       
/* 100 */       float var9 = 57.295776F;
/* 101 */       this.batHead.rotateAngleX = par6 / 57.295776F;
/* 102 */       this.batHead.rotateAngleY = par5 / 57.295776F;
/* 103 */       this.batHead.rotateAngleZ = 0.0F;
/* 104 */       this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 105 */       this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 106 */       this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 107 */       this.batBody.rotateAngleX = 0.7853982F + MathHelper.cos(par4 * 0.1F) * 0.15F;
/* 108 */       this.batBody.rotateAngleY = 0.0F;
/* 109 */       this.batRightWing.rotateAngleY = MathHelper.cos(par4 * 1.3F) * 3.1415927F * 0.25F;
/* 110 */       this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
/* 111 */       this.batRightWing.rotateAngleY *= 0.5F;
/* 112 */       this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
/*     */     } 
/*     */     
/* 115 */     this.batHead.render(par7);
/* 116 */     this.batBody.render(par7);
/*     */     
/* 118 */     if (var8.getIsBatHanging()) {
/*     */       
/* 120 */       this.batHead.offsetY = original_bat_head_offset_y;
/* 121 */       this.batRightWing.offsetY = original_right_bat_wing_offset_y;
/* 122 */       this.batLeftWing.offsetY = original_left_bat_wing_offset_y;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */