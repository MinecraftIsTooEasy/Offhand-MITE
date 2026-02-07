/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityCritFX
/*    */   extends EntityFX {
/*    */   float initialParticleScale;
/*    */   
/*    */   public EntityCritFX(World world, double d, double e, double f, double g, double h, double i) {
/*  8 */     this(world, d, e, f, g, h, i, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityCritFX(World world, double d, double e, double f, double g, double h, double i, float j) {
/* 14 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/* 15 */     this.motionX *= 0.10000000149011612D;
/* 16 */     this.motionY *= 0.10000000149011612D;
/* 17 */     this.motionZ *= 0.10000000149011612D;
/* 18 */     this.motionX += g * 0.4D;
/* 19 */     this.motionY += h * 0.4D;
/* 20 */     this.motionZ += i * 0.4D;
/*    */     
/* 22 */     this.particleRed = this.particleGreen = this.particleBlue = (float)(Math.random() * 0.30000001192092896D + 0.6000000238418579D);
/* 23 */     this.particleScale *= 0.75F;
/* 24 */     this.particleScale *= j;
/* 25 */     this.initialParticleScale = this.particleScale;
/*    */     
/* 27 */     this.particleMaxAge = (int)(6.0D / (Math.random() * 0.8D + 0.6D));
/* 28 */     this.particleMaxAge = (int)(this.particleMaxAge * j);
/* 29 */     this.noClip = false;
/*    */     
/* 31 */     setParticleTextureIndex(65);
/* 32 */     onUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 37 */     float f1 = (this.particleAge + f) / this.particleMaxAge * 32.0F;
/* 38 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 39 */     if (f1 > 1.0F) f1 = 1.0F;
/*    */     
/* 41 */     this.particleScale = this.initialParticleScale * f1;
/* 42 */     super.renderParticle(tessellator, f, g, h, i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 47 */     this.prevPosX = this.posX;
/* 48 */     this.prevPosY = this.posY;
/* 49 */     this.prevPosZ = this.posZ;
/*    */     
/* 51 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 53 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 54 */     this.particleGreen = (float)(this.particleGreen * 0.96D);
/* 55 */     this.particleBlue = (float)(this.particleBlue * 0.9D);
/*    */     
/* 57 */     this.motionX *= 0.699999988079071D;
/* 58 */     this.motionY *= 0.699999988079071D;
/* 59 */     this.motionZ *= 0.699999988079071D;
/* 60 */     this.motionY -= 0.019999999552965164D;
/*    */     
/* 62 */     if (this.onGround) {
/* 63 */       this.motionX *= 0.699999988079071D;
/* 64 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCritFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */