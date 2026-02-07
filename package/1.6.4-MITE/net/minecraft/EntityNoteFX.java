/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityNoteFX
/*    */   extends EntityFX
/*    */ {
/*    */   float noteParticleScale;
/*    */   
/*    */   public EntityNoteFX(World world, double d, double e, double f, double g, double h, double i) {
/* 11 */     this(world, d, e, f, g, h, i, 2.0F);
/*    */   }
/*    */   
/*    */   public EntityNoteFX(World world, double d, double e, double f, double g, double h, double i, float j) {
/* 15 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/* 16 */     this.motionX *= 0.009999999776482582D;
/* 17 */     this.motionY *= 0.009999999776482582D;
/* 18 */     this.motionZ *= 0.009999999776482582D;
/* 19 */     this.motionY += 0.2D;
/*    */     
/* 21 */     this.particleRed = MathHelper.sin(((float)g + 0.0F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
/* 22 */     this.particleGreen = MathHelper.sin(((float)g + 0.33333334F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
/* 23 */     this.particleBlue = MathHelper.sin(((float)g + 0.6666667F) * 3.1415927F * 2.0F) * 0.65F + 0.35F;
/*    */     
/* 25 */     this.particleScale *= 0.75F;
/* 26 */     this.particleScale *= j;
/* 27 */     this.noteParticleScale = this.particleScale;
/*    */     
/* 29 */     this.particleMaxAge = 6;
/* 30 */     this.noClip = false;
/*    */     
/* 32 */     setParticleTextureIndex(64);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 38 */     float f1 = (this.particleAge + f) / this.particleMaxAge * 32.0F;
/* 39 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 40 */     if (f1 > 1.0F) f1 = 1.0F;
/*    */     
/* 42 */     this.particleScale = this.noteParticleScale * f1;
/* 43 */     super.renderParticle(tessellator, f, g, h, i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 48 */     this.prevPosX = this.posX;
/* 49 */     this.prevPosY = this.posY;
/* 50 */     this.prevPosZ = this.posZ;
/*    */     
/* 52 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 54 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 55 */     if (this.posY == this.prevPosY) {
/* 56 */       this.motionX *= 1.1D;
/* 57 */       this.motionZ *= 1.1D;
/*    */     } 
/* 59 */     this.motionX *= 0.6600000262260437D;
/* 60 */     this.motionY *= 0.6600000262260437D;
/* 61 */     this.motionZ *= 0.6600000262260437D;
/*    */     
/* 63 */     if (this.onGround) {
/* 64 */       this.motionX *= 0.699999988079071D;
/* 65 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityNoteFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */