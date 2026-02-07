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
/*     */ public class ModelBiped
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer bipedHead;
/*     */   public ModelRenderer bipedHeadwear;
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public ModelRenderer bipedEars;
/*     */   public ModelRenderer bipedCloak;
/*     */   public int heldItemLeft;
/*     */   public int heldItemRight;
/*     */   public boolean isSneak;
/*     */   public boolean aimedBow;
/*     */   
/*     */   public ModelBiped() {
/*  33 */     this(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBiped(float par1) {
/*  38 */     this(par1, 0.0F, 64, 32);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBiped(float par1, float par2, int par3, int par4) {
/*  43 */     this.textureWidth = par3;
/*  44 */     this.textureHeight = par4;
/*  45 */     this.bipedCloak = new ModelRenderer(this, 0, 0);
/*  46 */     this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, par1);
/*  47 */     this.bipedEars = new ModelRenderer(this, 24, 0);
/*  48 */     this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, par1);
/*  49 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  50 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
/*  51 */     this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
/*  52 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/*  53 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
/*  54 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
/*  55 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  56 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
/*  57 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
/*  58 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  59 */     this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
/*  60 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
/*  61 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  62 */     this.bipedLeftArm.mirror = true;
/*  63 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
/*  64 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
/*  65 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  66 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
/*  67 */     this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
/*  68 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  69 */     this.bipedLeftLeg.mirror = true;
/*  70 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
/*  71 */     this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  79 */     setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
/*     */     
/*  81 */     if (this.isChild) {
/*     */       
/*  83 */       float var8 = 2.0F;
/*  84 */       GL11.glPushMatrix();
/*  85 */       GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
/*  86 */       GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
/*  87 */       this.bipedHead.render(par7);
/*  88 */       GL11.glPopMatrix();
/*  89 */       GL11.glPushMatrix();
/*  90 */       GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  91 */       GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
/*  92 */       this.bipedBody.render(par7);
/*  93 */       this.bipedRightArm.render(par7);
/*  94 */       this.bipedLeftArm.render(par7);
/*  95 */       this.bipedRightLeg.render(par7);
/*  96 */       this.bipedLeftLeg.render(par7);
/*  97 */       this.bipedHeadwear.render(par7);
/*  98 */       GL11.glPopMatrix();
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.bipedHead.render(par7);
/* 103 */       this.bipedBody.render(par7);
/* 104 */       this.bipedRightArm.render(par7);
/* 105 */       this.bipedLeftArm.render(par7);
/* 106 */       this.bipedRightLeg.render(par7);
/* 107 */       this.bipedLeftLeg.render(par7);
/* 108 */       this.bipedHeadwear.render(par7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
/* 119 */     this.bipedHead.rotateAngleY = par4 / 57.295776F;
/* 120 */     this.bipedHead.rotateAngleX = par5 / 57.295776F;
/* 121 */     this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
/* 122 */     this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
/* 123 */     this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
/* 124 */     this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
/* 125 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 126 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 127 */     this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
/* 128 */     this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
/* 129 */     this.bipedRightLeg.rotateAngleY = 0.0F;
/* 130 */     this.bipedLeftLeg.rotateAngleY = 0.0F;
/*     */ 
/*     */     
/* 133 */     boolean adjust_cuirass_animation = (par7Entity instanceof EntitySkeleton && par7Entity.getAsEntityLiving().isWearingCuirass(true));
/*     */     
/* 135 */     if (adjust_cuirass_animation) {
/*     */       
/* 137 */       this.bipedLeftArm.rotateAngleX = 0.0F;
/* 138 */       this.bipedRightArm.rotateAngleX = 0.0F;
/*     */     } 
/*     */     
/* 141 */     this.isRiding = par7Entity.isRiding();
/*     */     
/* 143 */     if (this.isRiding) {
/*     */ 
/*     */       
/* 146 */       this.bipedRightArm.rotateAngleX += -0.62831855F;
/* 147 */       this.bipedLeftArm.rotateAngleX += -0.62831855F;
/* 148 */       this.bipedRightLeg.rotateAngleX = -1.2566371F;
/* 149 */       this.bipedLeftLeg.rotateAngleX = -1.2566371F;
/* 150 */       this.bipedRightLeg.rotateAngleY = 0.31415927F;
/* 151 */       this.bipedLeftLeg.rotateAngleY = -0.31415927F;
/*     */     } 
/*     */     
/* 154 */     if (this.heldItemLeft != 0)
/*     */     {
/* 156 */       this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.31415927F * this.heldItemLeft;
/*     */     }
/*     */     
/* 159 */     if (this.heldItemRight != 0)
/*     */     {
/* 161 */       this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F * this.heldItemRight;
/*     */     }
/*     */     
/* 164 */     this.bipedRightArm.rotateAngleY = 0.0F;
/* 165 */     this.bipedLeftArm.rotateAngleY = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 169 */     if (this.onGround > -9990.0F) {
/*     */       
/* 171 */       float var8 = this.onGround;
/* 172 */       this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var8) * 3.1415927F * 2.0F) * 0.2F;
/* 173 */       this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 174 */       this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 175 */       this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 176 */       this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 177 */       this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 178 */       this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 179 */       this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
/* 180 */       var8 = 1.0F - this.onGround;
/* 181 */       var8 *= var8;
/* 182 */       var8 *= var8;
/* 183 */       var8 = 1.0F - var8;
/* 184 */       float var9 = MathHelper.sin(var8 * 3.1415927F);
/* 185 */       float var10 = MathHelper.sin(this.onGround * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 186 */       this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - var9 * 1.2D + var10);
/* 187 */       this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 188 */       this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.1415927F) * -0.4F;
/*     */       
/* 190 */       if (adjust_cuirass_animation) {
/*     */         
/* 192 */         this.bipedLeftArm.rotateAngleX -= 1.6F;
/*     */         
/* 194 */         this.bipedRightArm.rotateAngleX = this.bipedLeftArm.rotateAngleX;
/* 195 */         this.bipedRightArm.rotateAngleY = -this.bipedLeftArm.rotateAngleY;
/* 196 */         this.bipedRightArm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     if (this.isSneak) {
/*     */       
/* 202 */       this.bipedBody.rotateAngleX = 0.5F;
/* 203 */       this.bipedRightArm.rotateAngleX += 0.4F;
/* 204 */       this.bipedLeftArm.rotateAngleX += 0.4F;
/* 205 */       this.bipedRightLeg.rotationPointZ = 4.0F;
/* 206 */       this.bipedLeftLeg.rotationPointZ = 4.0F;
/* 207 */       this.bipedRightLeg.rotationPointY = 9.0F;
/* 208 */       this.bipedLeftLeg.rotationPointY = 9.0F;
/* 209 */       this.bipedHead.rotationPointY = 1.0F;
/* 210 */       this.bipedHeadwear.rotationPointY = 1.0F;
/*     */     }
/*     */     else {
/*     */       
/* 214 */       this.bipedBody.rotateAngleX = 0.0F;
/* 215 */       this.bipedRightLeg.rotationPointZ = 0.1F;
/* 216 */       this.bipedLeftLeg.rotationPointZ = 0.1F;
/* 217 */       this.bipedRightLeg.rotationPointY = 12.0F;
/* 218 */       this.bipedLeftLeg.rotationPointY = 12.0F;
/* 219 */       this.bipedHead.rotationPointY = 0.0F;
/* 220 */       this.bipedHeadwear.rotationPointY = 0.0F;
/*     */     } 
/*     */     
/* 223 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 224 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 225 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
/* 226 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
/*     */     
/* 228 */     if (this.aimedBow) {
/*     */       
/* 230 */       float var8 = 0.0F;
/* 231 */       float var9 = 0.0F;
/* 232 */       this.bipedRightArm.rotateAngleZ = 0.0F;
/* 233 */       this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 234 */       this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F) + this.bipedHead.rotateAngleY;
/* 235 */       this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
/* 236 */       this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/* 237 */       this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/* 238 */       this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 239 */       this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 240 */       this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 241 */       this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
/* 242 */       this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
/* 243 */       this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderEars(float par1) {
/* 252 */     this.bipedEars.rotateAngleY = this.bipedHead.rotateAngleY;
/* 253 */     this.bipedEars.rotateAngleX = this.bipedHead.rotateAngleX;
/* 254 */     this.bipedEars.rotationPointX = 0.0F;
/* 255 */     this.bipedEars.rotationPointY = 0.0F;
/* 256 */     this.bipedEars.render(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderCloak(float par1) {
/* 264 */     this.bipedCloak.render(par1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ModelBiped.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */