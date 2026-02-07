/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityExplodeFX
/*    */   extends EntityFX
/*    */ {
/*    */   public EntityExplodeFX(World world, double d, double e, double f, double g, double h, double i) {
/*  7 */     super(world, d, e, f, g, h, i);
/*  8 */     this.motionX = g + ((float)(Math.random() * 2.0D - 1.0D) * 0.05F);
/*  9 */     this.motionY = h + ((float)(Math.random() * 2.0D - 1.0D) * 0.05F);
/* 10 */     this.motionZ = i + ((float)(Math.random() * 2.0D - 1.0D) * 0.05F);
/*    */     
/* 12 */     this.particleRed = this.particleGreen = this.particleBlue = this.rand.nextFloat() * 0.3F + 0.7F;
/* 13 */     this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F;
/*    */     
/* 15 */     this.particleMaxAge = (int)(16.0D / (this.rand.nextFloat() * 0.8D + 0.2D)) + 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 20 */     this.prevPosX = this.posX;
/* 21 */     this.prevPosY = this.posY;
/* 22 */     this.prevPosZ = this.posZ;
/*    */     
/* 24 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 26 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/*    */     
/* 28 */     this.motionY += 0.004D;
/* 29 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 30 */     this.motionX *= 0.8999999761581421D;
/* 31 */     this.motionY *= 0.8999999761581421D;
/* 32 */     this.motionZ *= 0.8999999761581421D;
/*    */     
/* 34 */     if (this.onGround) {
/* 35 */       this.motionX *= 0.699999988079071D;
/* 36 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityExplodeFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */