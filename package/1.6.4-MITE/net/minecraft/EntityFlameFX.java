/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityFlameFX
/*    */   extends EntityFX
/*    */ {
/*    */   private float flameScale;
/*    */   
/*    */   public EntityFlameFX(World world, double d, double e, double f, double g, double h, double i) {
/* 10 */     super(world, d, e, f, g, h, i);
/* 11 */     this.motionX = this.motionX * 0.009999999776482582D + g;
/* 12 */     this.motionY = this.motionY * 0.009999999776482582D + h;
/* 13 */     this.motionZ = this.motionZ * 0.009999999776482582D + i;
/* 14 */     d += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
/* 15 */     e += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
/* 16 */     f += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
/*    */     
/* 18 */     this.flameScale = this.particleScale;
/* 19 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/*    */     
/* 21 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
/* 22 */     this.noClip = true;
/* 23 */     setParticleTextureIndex(48);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 28 */     float f1 = (this.particleAge + f) / this.particleMaxAge;
/* 29 */     this.particleScale = this.flameScale * (1.0F - f1 * f1 * 0.5F);
/* 30 */     super.renderParticle(tessellator, f, g, h, i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float f) {
/* 35 */     float f1 = (this.particleAge + f) / this.particleMaxAge;
/* 36 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 37 */     if (f1 > 1.0F) f1 = 1.0F; 
/* 38 */     int i = super.getBrightnessForRender(f);
/*    */     
/* 40 */     int j = i & 0xFF;
/* 41 */     int k = i >> 16 & 0xFF;
/* 42 */     j += (int)(f1 * 15.0F * 16.0F);
/* 43 */     if (j > 240) j = 240; 
/* 44 */     return j | k << 16;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBrightness(float f) {
/* 49 */     float f1 = (this.particleAge + f) / this.particleMaxAge;
/* 50 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 51 */     if (f1 > 1.0F) f1 = 1.0F; 
/* 52 */     float f2 = super.getBrightness(f);
/*    */     
/* 54 */     return f2 * f1 + 1.0F - f1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 59 */     this.prevPosX = this.posX;
/* 60 */     this.prevPosY = this.posY;
/* 61 */     this.prevPosZ = this.posZ;
/*    */     
/* 63 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 65 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 66 */     this.motionX *= 0.9599999785423279D;
/* 67 */     this.motionY *= 0.9599999785423279D;
/* 68 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 70 */     if (this.onGround) {
/* 71 */       this.motionX *= 0.699999988079071D;
/* 72 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFlameFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */