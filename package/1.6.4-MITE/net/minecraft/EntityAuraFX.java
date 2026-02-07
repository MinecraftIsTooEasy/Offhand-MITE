/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityAuraFX
/*    */   extends EntityFX
/*    */ {
/*    */   protected static int random_number_index;
/*    */   
/*    */   public EntityAuraFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  9 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*    */     
/* 11 */     float var14 = RNG.float_1[++random_number_index & 0x7FFF] * 0.1F + 0.2F;
/* 12 */     this.particleRed = var14;
/* 13 */     this.particleGreen = var14;
/* 14 */     this.particleBlue = var14;
/* 15 */     setParticleTextureIndex(0);
/* 16 */     setSize(0.02F, 0.02F);
/* 17 */     this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
/* 18 */     this.motionX *= 0.019999999552965164D;
/* 19 */     this.motionY *= 0.019999999552965164D;
/* 20 */     this.motionZ *= 0.019999999552965164D;
/*    */     
/* 22 */     this.particleMaxAge = (int)(20.0D / (RNG.float_1[++random_number_index & 0x7FFF] * 0.8D + 0.2D));
/* 23 */     this.noClip = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 31 */     this.prevPosX = this.posX;
/* 32 */     this.prevPosY = this.posY;
/* 33 */     this.prevPosZ = this.posZ;
/* 34 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 35 */     this.motionX *= 0.99D;
/* 36 */     this.motionY *= 0.99D;
/* 37 */     this.motionZ *= 0.99D;
/*    */ 
/*    */     
/* 40 */     if (++this.particleAge > this.particleMaxAge)
/*    */     {
/* 42 */       setDead();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAuraFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */