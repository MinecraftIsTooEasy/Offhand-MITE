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
/*     */ public class EntityFireworkSparkFX
/*     */   extends EntityFX
/*     */ {
/* 280 */   private int baseTextureIndex = 160;
/*     */   
/*     */   private boolean field_92054_ax;
/*     */   private boolean field_92048_ay;
/*     */   private final EffectRenderer field_92047_az;
/*     */   private float fadeColourRed;
/*     */   private float fadeColourGreen;
/*     */   private float fadeColourBlue;
/*     */   private boolean hasFadeColour;
/*     */   
/*     */   public EntityFireworkSparkFX(World world, double d, double e, double f, double g, double h, double i, EffectRenderer effectRenderer) {
/* 291 */     super(world, d, e, f);
/* 292 */     this.motionX = g;
/* 293 */     this.motionY = h;
/* 294 */     this.motionZ = i;
/* 295 */     this.field_92047_az = effectRenderer;
/*     */     
/* 297 */     this.particleScale *= 0.75F;
/*     */     
/* 299 */     this.particleMaxAge = 48 + this.rand.nextInt(12);
/* 300 */     this.noClip = false;
/*     */   }
/*     */   
/*     */   public void setTrail(boolean bl) {
/* 304 */     this.field_92054_ax = bl;
/*     */   }
/*     */   
/*     */   public void setTwinkle(boolean bl) {
/* 308 */     this.field_92048_ay = bl;
/*     */   }
/*     */   
/*     */   public void setColour(int i) {
/* 312 */     float f1 = ((i & 0xFF0000) >> 16) / 255.0F;
/* 313 */     float f2 = ((i & 0xFF00) >> 8) / 255.0F;
/* 314 */     float f3 = ((i & 0xFF) >> 0) / 255.0F;
/* 315 */     float f4 = 1.0F;
/* 316 */     setRBGColorF(f1 * f4, f2 * f4, f3 * f4);
/*     */   }
/*     */   
/*     */   public void setFadeColour(int i) {
/* 320 */     this.fadeColourRed = ((i & 0xFF0000) >> 16) / 255.0F;
/* 321 */     this.fadeColourGreen = ((i & 0xFF00) >> 8) / 255.0F;
/* 322 */     this.fadeColourBlue = ((i & 0xFF) >> 0) / 255.0F;
/* 323 */     this.hasFadeColour = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/* 328 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 338 */     if (!this.field_92048_ay || this.particleAge < this.particleMaxAge / 3 || (this.particleAge + this.particleMaxAge) / 3 % 2 == 0) {
/* 339 */       super.renderParticle(tessellator, f, g, h, i, j, k);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 345 */     this.prevPosX = this.posX;
/* 346 */     this.prevPosY = this.posY;
/* 347 */     this.prevPosZ = this.posZ;
/*     */     
/* 349 */     if (this.particleAge++ >= this.particleMaxAge) setDead(); 
/* 350 */     if (this.particleAge > this.particleMaxAge / 2) {
/* 351 */       setAlphaF(1.0F - (this.particleAge - (this.particleMaxAge / 2)) / this.particleMaxAge);
/*     */       
/* 353 */       if (this.hasFadeColour) {
/* 354 */         this.particleRed += (this.fadeColourRed - this.particleRed) * 0.2F;
/* 355 */         this.particleGreen += (this.fadeColourGreen - this.particleGreen) * 0.2F;
/* 356 */         this.particleBlue += (this.fadeColourBlue - this.particleBlue) * 0.2F;
/*     */       } 
/*     */     } 
/*     */     
/* 360 */     setParticleTextureIndex(this.baseTextureIndex + 7 - this.particleAge * 8 / this.particleMaxAge);
/*     */     
/* 362 */     this.motionY -= 0.004D;
/* 363 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 364 */     this.motionX *= 0.9100000262260437D;
/* 365 */     this.motionY *= 0.9100000262260437D;
/* 366 */     this.motionZ *= 0.9100000262260437D;
/*     */     
/* 368 */     if (this.onGround) {
/* 369 */       this.motionX *= 0.699999988079071D;
/* 370 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */     
/* 373 */     if (this.field_92054_ax && this.particleAge < this.particleMaxAge / 2 && (this.particleAge + this.particleMaxAge) % 2 == 0) {
/* 374 */       EntityFireworkSparkFX entityFireworkSparkFX = new EntityFireworkSparkFX(this.worldObj, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, this.field_92047_az);
/* 375 */       entityFireworkSparkFX.setRBGColorF(this.particleRed, this.particleGreen, this.particleBlue);
/* 376 */       entityFireworkSparkFX.particleAge = entityFireworkSparkFX.particleMaxAge / 2;
/* 377 */       if (this.hasFadeColour) {
/* 378 */         entityFireworkSparkFX.hasFadeColour = true;
/* 379 */         entityFireworkSparkFX.fadeColourRed = this.fadeColourRed;
/* 380 */         entityFireworkSparkFX.fadeColourGreen = this.fadeColourGreen;
/* 381 */         entityFireworkSparkFX.fadeColourBlue = this.fadeColourBlue;
/*     */       } 
/* 383 */       entityFireworkSparkFX.field_92048_ay = this.field_92048_ay;
/* 384 */       this.field_92047_az.addEffect(entityFireworkSparkFX);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float f) {
/* 394 */     return 15728880;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBrightness(float f) {
/* 399 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFireworkSparkFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */