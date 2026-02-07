/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntitySuspendFX
/*    */   extends EntityFX
/*    */ {
/*    */   public EntitySuspendFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  7 */     super(par1World, par2, par4 - 0.125D, par6, par8, par10, par12);
/*  8 */     this.particleRed = 0.4F;
/*  9 */     this.particleGreen = 0.4F;
/* 10 */     this.particleBlue = 0.7F;
/* 11 */     setParticleTextureIndex(0);
/* 12 */     setSize(0.01F, 0.01F);
/* 13 */     this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
/* 14 */     this.motionX = par8 * 0.0D;
/* 15 */     this.motionY = par10 * 0.0D;
/* 16 */     this.motionZ = par12 * 0.0D;
/*    */     
/* 18 */     this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 26 */     this.prevPosX = this.posX;
/* 27 */     this.prevPosY = this.posY;
/* 28 */     this.prevPosZ = this.posZ;
/* 29 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */ 
/*    */     
/* 32 */     if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + 0.15000000596046448D), MathHelper.floor_double(this.posZ)) != Material.water)
/*    */     {
/* 34 */       setDead();
/*    */     }
/*    */     
/* 37 */     if (this.particleMaxAge-- <= 0)
/*    */     {
/* 39 */       setDead();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySuspendFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */