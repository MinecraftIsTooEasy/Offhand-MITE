/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntitySnowShovelFX
/*    */   extends EntityFX {
/*    */   float snowDigParticleScale;
/*    */   
/*    */   public EntitySnowShovelFX(World world, double d, double e, double f, double g, double h, double i) {
/*  8 */     this(world, d, e, f, g, h, i, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntitySnowShovelFX(World world, double d, double e, double f, double g, double h, double i, float j) {
/* 14 */     super(world, d, e, f, g, h, i);
/* 15 */     this.motionX *= 0.10000000149011612D;
/* 16 */     this.motionY *= 0.10000000149011612D;
/* 17 */     this.motionZ *= 0.10000000149011612D;
/* 18 */     this.motionX += g;
/* 19 */     this.motionY += h;
/* 20 */     this.motionZ += i;
/*    */     
/* 22 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.30000001192092896D);
/* 23 */     this.particleScale *= 0.75F;
/* 24 */     this.particleScale *= j;
/* 25 */     this.snowDigParticleScale = this.particleScale;
/*    */     
/* 27 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 28 */     this.particleMaxAge = (int)(this.particleMaxAge * j);
/* 29 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 34 */     float f1 = (this.particleAge + f) / this.particleMaxAge * 32.0F;
/* 35 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 36 */     if (f1 > 1.0F) f1 = 1.0F;
/*    */     
/* 38 */     this.particleScale = this.snowDigParticleScale * f1;
/* 39 */     super.renderParticle(tessellator, f, g, h, i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 44 */     this.prevPosX = this.posX;
/* 45 */     this.prevPosY = this.posY;
/* 46 */     this.prevPosZ = this.posZ;
/*    */     
/* 48 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 50 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/*    */     
/* 52 */     this.motionY -= 0.03D;
/* 53 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 54 */     this.motionX *= 0.9900000095367432D;
/* 55 */     this.motionY *= 0.9900000095367432D;
/* 56 */     this.motionZ *= 0.9900000095367432D;
/*    */     
/* 58 */     if (this.onGround) {
/* 59 */       this.motionX *= 0.699999988079071D;
/* 60 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySnowShovelFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */