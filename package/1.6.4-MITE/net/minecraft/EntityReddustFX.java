/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityReddustFX
/*    */   extends EntityFX
/*    */ {
/*    */   float reddustParticleScale;
/*    */   
/*    */   public EntityReddustFX(World world, double d, double e, double f, float g, float h, float i) {
/* 10 */     this(world, d, e, f, 1.0F, g, h, i);
/*    */   }
/*    */   
/*    */   public EntityReddustFX(World world, double d, double e, double f, float g, float h, float i, float j) {
/* 14 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/* 15 */     this.motionX *= 0.10000000149011612D;
/* 16 */     this.motionY *= 0.10000000149011612D;
/* 17 */     this.motionZ *= 0.10000000149011612D;
/*    */     
/* 19 */     if (h == 0.0F) {
/* 20 */       h = 1.0F;
/*    */     }
/* 22 */     float f1 = (float)Math.random() * 0.4F + 0.6F;
/* 23 */     this.particleRed = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * h * f1;
/* 24 */     this.particleGreen = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * i * f1;
/* 25 */     this.particleBlue = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * j * f1;
/* 26 */     this.particleScale *= 0.75F;
/* 27 */     this.particleScale *= g;
/* 28 */     this.reddustParticleScale = this.particleScale;
/*    */     
/* 30 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 31 */     this.particleMaxAge = (int)(this.particleMaxAge * g);
/* 32 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 37 */     float f1 = (this.particleAge + f) / this.particleMaxAge * 32.0F;
/* 38 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 39 */     if (f1 > 1.0F) f1 = 1.0F;
/*    */     
/* 41 */     this.particleScale = this.reddustParticleScale * f1;
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
/* 53 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/*    */     
/* 55 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 56 */     if (this.posY == this.prevPosY) {
/* 57 */       this.motionX *= 1.1D;
/* 58 */       this.motionZ *= 1.1D;
/*    */     } 
/* 60 */     this.motionX *= 0.9599999785423279D;
/* 61 */     this.motionY *= 0.9599999785423279D;
/* 62 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 64 */     if (this.onGround) {
/* 65 */       this.motionX *= 0.699999988079071D;
/* 66 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityReddustFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */