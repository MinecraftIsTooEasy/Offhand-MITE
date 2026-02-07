/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityLavaFX
/*    */   extends EntityFX
/*    */ {
/*    */   private float lavaParticleScale;
/*    */   
/*    */   public EntityLavaFX(World par1World, double par2, double par4, double par6) {
/*  9 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/* 10 */     this.motionX *= 0.800000011920929D;
/* 11 */     this.motionY *= 0.800000011920929D;
/* 12 */     this.motionZ *= 0.800000011920929D;
/* 13 */     this.motionY = (this.rand.nextFloat() * 0.4F + 0.05F);
/* 14 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
/* 15 */     this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
/* 16 */     this.lavaParticleScale = this.particleScale;
/* 17 */     this.particleMaxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
/* 18 */     this.noClip = false;
/* 19 */     setParticleTextureIndex(49);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float par1) {
/* 24 */     float var2 = (this.particleAge + par1) / this.particleMaxAge;
/*    */     
/* 26 */     if (var2 < 0.0F)
/*    */     {
/* 28 */       var2 = 0.0F;
/*    */     }
/*    */     
/* 31 */     if (var2 > 1.0F)
/*    */     {
/* 33 */       var2 = 1.0F;
/*    */     }
/*    */     
/* 36 */     int var3 = super.getBrightnessForRender(par1);
/* 37 */     short var4 = 240;
/* 38 */     int var5 = var3 >> 16 & 0xFF;
/* 39 */     return var4 | var5 << 16;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getBrightness(float par1) {
/* 47 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 52 */     float var8 = (this.particleAge + par2) / this.particleMaxAge;
/* 53 */     this.particleScale = this.lavaParticleScale * (1.0F - var8 * var8);
/* 54 */     super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 62 */     this.prevPosX = this.posX;
/* 63 */     this.prevPosY = this.posY;
/* 64 */     this.prevPosZ = this.posZ;
/*    */     
/* 66 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 68 */       setDead();
/*    */     }
/*    */     
/* 71 */     float var1 = this.particleAge / this.particleMaxAge;
/*    */     
/* 73 */     if (this.rand.nextFloat() > var1)
/*    */     {
/*    */       
/* 76 */       this.worldObj.spawnParticle(EnumParticle.smoke, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
/*    */     }
/*    */     
/* 79 */     this.motionY -= 0.03D;
/* 80 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 81 */     this.motionX *= 0.9990000128746033D;
/* 82 */     this.motionY *= 0.9990000128746033D;
/* 83 */     this.motionZ *= 0.9990000128746033D;
/*    */     
/* 85 */     if (this.onGround) {
/*    */       
/* 87 */       this.motionX *= 0.699999988079071D;
/* 88 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLavaFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */