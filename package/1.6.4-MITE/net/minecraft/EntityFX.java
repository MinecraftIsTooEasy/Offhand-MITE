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
/*     */ public class EntityFX
/*     */   extends Entity
/*     */ {
/*     */   protected int particleTextureIndexX;
/*     */   protected int particleTextureIndexY;
/*     */   protected float particleTextureJitterX;
/*     */   protected float particleTextureJitterY;
/*     */   protected int particleAge;
/*     */   protected int particleMaxAge;
/*     */   protected float particleScale;
/*     */   protected float particleGravity;
/*     */   protected float particleRed;
/*     */   protected float particleGreen;
/*     */   protected float particleBlue;
/*     */   protected float particleAlpha;
/*     */   protected Icon particleIcon;
/*     */   public static double interpPosX;
/*     */   public static double interpPosY;
/*     */   public static double interpPosZ;
/*  38 */   double[] x = new double[4];
/*  39 */   double[] y = new double[4];
/*  40 */   double[] z = new double[4];
/*  41 */   double[] u = new double[4];
/*  42 */   double[] v = new double[4];
/*  43 */   float[] r = new float[4];
/*  44 */   float[] g = new float[4];
/*  45 */   float[] b = new float[4];
/*  46 */   int[] brightness = new int[4];
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
/*     */   private int random_number_index;
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
/*     */   private boolean prev_pos_initialized;
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
/*     */   protected EntityFX(World par1World, double par2, double par4, double par6) {
/*  87 */     super(par1World);
/*  88 */     this.random_number_index = this.rand.nextInt();
/*  89 */     this.particleAlpha = 1.0F;
/*  90 */     setSize(0.2F, 0.2F);
/*  91 */     this.yOffset = this.height / 2.0F;
/*  92 */     setPosition(par2, par4, par6);
/*  93 */     this.lastTickPosX = par2;
/*  94 */     this.lastTickPosY = par4;
/*  95 */     this.lastTickPosZ = par6;
/*  96 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     this.particleTextureJitterX = RNG.float_1[++this.random_number_index & 0x7FFF] * 3.0F;
/* 103 */     this.particleTextureJitterY = RNG.float_1[++this.random_number_index & 0x7FFF] * 3.0F;
/* 104 */     this.particleScale = RNG.float_1[++this.random_number_index & 0x7FFF] + 1.0F;
/* 105 */     this.particleMaxAge = (int)(4.0F / (RNG.float_1[++this.random_number_index & 0x7FFF] * 0.9F + 0.1F));
/*     */     
/* 107 */     this.particleAge = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 112 */     this(par1World, par2, par4, par6);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.motionX = par8 + (RNG.float_1[++this.random_number_index & 0x7FFF] * 0.8F) - 0.4000000059604645D;
/* 118 */     this.motionY = par10 + (RNG.float_1[++this.random_number_index & 0x7FFF] * 0.8F) - 0.4000000059604645D;
/* 119 */     this.motionZ = par12 + (RNG.float_1[++this.random_number_index & 0x7FFF] * 0.8F) - 0.4000000059604645D;
/*     */ 
/*     */ 
/*     */     
/* 123 */     float var14 = (RNG.float_1[++this.random_number_index & 0x7FFF] * (float)Math.random() + 1.0F) * 0.15F;
/* 124 */     float var15 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/* 125 */     this.motionX = this.motionX / var15 * var14 * 0.4000000059604645D;
/* 126 */     this.motionY = this.motionY / var15 * var14 * 0.4000000059604645D + 0.10000000149011612D;
/* 127 */     this.motionZ = this.motionZ / var15 * var14 * 0.4000000059604645D;
/*     */   }
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
/*     */   public final EntityFX multiplyVelocity(float par1) {
/* 146 */     this.motionX *= par1;
/* 147 */     this.motionY = (this.motionY - 0.10000000149011612D) * par1 + 0.10000000149011612D;
/* 148 */     this.motionZ *= par1;
/* 149 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final EntityFX multipleParticleScaleBy(float par1) {
/* 155 */     setSize(0.2F * par1, 0.2F * par1);
/* 156 */     this.particleScale *= par1;
/* 157 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setRBGColorF(float par1, float par2, float par3) {
/* 163 */     this.particleRed = par1;
/* 164 */     this.particleGreen = par2;
/* 165 */     this.particleBlue = par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setAlphaF(float par1) {
/* 174 */     this.particleAlpha = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getRedColorF() {
/* 180 */     return this.particleRed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getGreenColorF() {
/* 186 */     return this.particleGreen;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getBlueColorF() {
/* 192 */     return this.particleBlue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 211 */     this.prevPosX = this.posX;
/* 212 */     this.prevPosY = this.posY;
/* 213 */     this.prevPosZ = this.posZ;
/*     */     
/* 215 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/* 217 */       setDead();
/*     */     }
/*     */     
/* 220 */     this.motionY -= 0.04D * this.particleGravity;
/* 221 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 222 */     this.motionX *= 0.9800000190734863D;
/* 223 */     this.motionY *= 0.9800000190734863D;
/* 224 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/* 226 */     if (this.onGround) {
/*     */       
/* 228 */       this.motionX *= 0.699999988079071D;
/* 229 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 235 */     float var8 = this.particleTextureIndexX / 16.0F;
/* 236 */     float var9 = var8 + 0.0624375F;
/* 237 */     float var10 = this.particleTextureIndexY / 16.0F;
/* 238 */     float var11 = var10 + 0.0624375F;
/* 239 */     float var12 = 0.1F * this.particleScale;
/*     */     
/* 241 */     if (this.particleIcon != null) {
/*     */       
/* 243 */       var8 = this.particleIcon.getMinU();
/* 244 */       var9 = this.particleIcon.getMaxU();
/* 245 */       var10 = this.particleIcon.getMinV();
/* 246 */       var11 = this.particleIcon.getMaxV();
/*     */     } 
/*     */     
/* 249 */     if (!this.prev_pos_initialized) {
/*     */       
/* 251 */       this.prevPosX = this.posX;
/* 252 */       this.prevPosY = this.posY;
/* 253 */       this.prevPosZ = this.posZ;
/*     */       
/* 255 */       this.prev_pos_initialized = true;
/*     */     } 
/*     */ 
/*     */     
/* 259 */     float var13 = (float)(this.posX * par2 + this.prevPosX * (1.0D - par2) - interpPosX);
/* 260 */     float var14 = (float)(this.posY * par2 + this.prevPosY * (1.0D - par2) - interpPosY);
/* 261 */     float var15 = (float)(this.posZ * par2 + this.prevPosZ * (1.0D - par2) - interpPosZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     float var16 = 1.0F;
/* 268 */     par1Tessellator.setColorRGBA_F(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, this.particleAlpha);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     if (RenderingScheme.current == 0) {
/*     */       
/* 278 */       par1Tessellator.addVertexWithUV((var13 - par3 * var12 - par6 * var12), (var14 - par4 * var12), (var15 - par5 * var12 - par7 * var12), var9, var11);
/* 279 */       par1Tessellator.addVertexWithUV((var13 - par3 * var12 + par6 * var12), (var14 + par4 * var12), (var15 - par5 * var12 + par7 * var12), var9, var10);
/* 280 */       par1Tessellator.addVertexWithUV((var13 + par3 * var12 + par6 * var12), (var14 + par4 * var12), (var15 + par5 * var12 + par7 * var12), var8, var10);
/* 281 */       par1Tessellator.addVertexWithUV((var13 + par3 * var12 - par6 * var12), (var14 - par4 * var12), (var15 + par5 * var12 - par7 * var12), var8, var11);
/*     */     }
/*     */     else {
/*     */       
/* 285 */       float par3_times_var12 = par3 * var12;
/* 286 */       float par5_times_var12 = par5 * var12;
/* 287 */       float par6_times_var12 = par6 * var12;
/* 288 */       float par7_times_var12 = par7 * var12;
/*     */       
/* 290 */       this.x[0] = (var13 - par3_times_var12 - par6_times_var12);
/* 291 */       this.y[0] = (var14 - par4 * var12);
/* 292 */       this.z[0] = (var15 - par5_times_var12 - par7_times_var12);
/* 293 */       this.u[0] = var9;
/* 294 */       this.v[0] = var11;
/*     */       
/* 296 */       this.x[1] = (var13 - par3_times_var12 + par6_times_var12);
/* 297 */       this.y[1] = (var14 + par4 * var12);
/* 298 */       this.z[1] = (var15 - par5_times_var12 + par7_times_var12);
/* 299 */       this.u[1] = var9;
/* 300 */       this.v[1] = var10;
/*     */       
/* 302 */       this.x[2] = (var13 + par3_times_var12 + par6_times_var12);
/* 303 */       this.y[2] = this.y[1];
/* 304 */       this.z[2] = (var15 + par5_times_var12 + par7_times_var12);
/* 305 */       this.u[2] = var8;
/* 306 */       this.v[2] = var10;
/*     */       
/* 308 */       this.x[3] = (var13 + par3_times_var12 - par6_times_var12);
/* 309 */       this.y[3] = this.y[0];
/* 310 */       this.z[3] = (var15 + par5_times_var12 - par7_times_var12);
/* 311 */       this.u[3] = var8;
/* 312 */       this.v[3] = var11;
/*     */       
/* 314 */       par1Tessellator.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 322 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParticleIcon(Icon par1Icon) {
/* 337 */     if (getFXLayer() == 1) {
/*     */       
/* 339 */       this.particleIcon = par1Icon;
/*     */     }
/*     */     else {
/*     */       
/* 343 */       if (getFXLayer() != 2)
/*     */       {
/* 345 */         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
/*     */       }
/*     */       
/* 348 */       this.particleIcon = par1Icon;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setParticleTextureIndex(int par1) {
/* 358 */     if (getFXLayer() != 0)
/*     */     {
/* 360 */       throw new RuntimeException("Invalid call to Particle.setMiscTex");
/*     */     }
/*     */ 
/*     */     
/* 364 */     this.particleTextureIndexX = par1 % 16;
/* 365 */     this.particleTextureIndexY = par1 / 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void nextTextureIndexX() {
/* 372 */     this.particleTextureIndexX++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean canAttackWithItem() {
/* 381 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 386 */     return getClass().getSimpleName() + ", Pos (" + this.posX + "," + this.posY + "," + this.posZ + "), RGBA (" + this.particleRed + "," + this.particleGreen + "," + this.particleBlue + "," + this.particleAlpha + "), Age " + this.particleAge;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canCatchFire() {
/* 391 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isHarmedByFire() {
/* 396 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isHarmedByLava() {
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX setMaxAge(int max_age) {
/* 406 */     this.particleMaxAge = max_age;
/* 407 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX setMotion(float motion_x, float motion_y, float motion_z) {
/* 412 */     this.motionX = motion_x;
/* 413 */     this.motionY = motion_y;
/* 414 */     this.motionZ = motion_z;
/*     */     
/* 416 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */