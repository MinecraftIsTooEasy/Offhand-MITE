/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityHeartFX
/*    */   extends EntityFX
/*    */ {
/*    */   float particleScaleOverTime;
/*    */   
/*    */   public EntityHeartFX(World world, double d, double e, double f, double g, double h, double i) {
/* 10 */     this(world, d, e, f, g, h, i, 2.0F);
/*    */   }
/*    */   
/*    */   public EntityHeartFX(World world, double d, double e, double f, double g, double h, double i, float j) {
/* 14 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/* 15 */     this.motionX *= 0.009999999776482582D;
/* 16 */     this.motionY *= 0.009999999776482582D;
/* 17 */     this.motionZ *= 0.009999999776482582D;
/* 18 */     this.motionY += 0.1D;
/*    */     
/* 20 */     this.particleScale *= 0.75F;
/* 21 */     this.particleScale *= j;
/* 22 */     this.particleScaleOverTime = this.particleScale;
/*    */     
/* 24 */     this.particleMaxAge = 16;
/* 25 */     this.noClip = false;
/*    */     
/* 27 */     setParticleTextureIndex(80);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 32 */     float f1 = (this.particleAge + f) / this.particleMaxAge * 32.0F;
/* 33 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 34 */     if (f1 > 1.0F) f1 = 1.0F;
/*    */     
/* 36 */     this.particleScale = this.particleScaleOverTime * f1;
/* 37 */     super.renderParticle(tessellator, f, g, h, i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 42 */     this.prevPosX = this.posX;
/* 43 */     this.prevPosY = this.posY;
/* 44 */     this.prevPosZ = this.posZ;
/*    */     
/* 46 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 48 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 49 */     if (this.posY == this.prevPosY) {
/* 50 */       this.motionX *= 1.1D;
/* 51 */       this.motionZ *= 1.1D;
/*    */     } 
/* 53 */     this.motionX *= 0.8600000143051147D;
/* 54 */     this.motionY *= 0.8600000143051147D;
/* 55 */     this.motionZ *= 0.8600000143051147D;
/*    */     
/* 57 */     if (this.onGround) {
/* 58 */       this.motionX *= 0.699999988079071D;
/* 59 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityHeartFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */