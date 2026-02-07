/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityCloudFX
/*    */   extends EntityFX
/*    */ {
/*    */   float field_70569_a;
/*    */   
/*    */   public EntityCloudFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  9 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/* 10 */     float var14 = 2.5F;
/* 11 */     this.motionX *= 0.10000000149011612D;
/* 12 */     this.motionY *= 0.10000000149011612D;
/* 13 */     this.motionZ *= 0.10000000149011612D;
/* 14 */     this.motionX += par8;
/* 15 */     this.motionY += par10;
/* 16 */     this.motionZ += par12;
/* 17 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.30000001192092896D);
/* 18 */     this.particleScale *= 0.75F;
/* 19 */     this.particleScale *= var14;
/* 20 */     this.field_70569_a = this.particleScale;
/* 21 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.3D));
/* 22 */     this.particleMaxAge = (int)(this.particleMaxAge * var14);
/* 23 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 28 */     float var8 = (this.particleAge + par2) / this.particleMaxAge * 32.0F;
/*    */     
/* 30 */     if (var8 < 0.0F)
/*    */     {
/* 32 */       var8 = 0.0F;
/*    */     }
/*    */     
/* 35 */     if (var8 > 1.0F)
/*    */     {
/* 37 */       var8 = 1.0F;
/*    */     }
/*    */     
/* 40 */     this.particleScale = this.field_70569_a * var8;
/* 41 */     super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 49 */     this.prevPosX = this.posX;
/* 50 */     this.prevPosY = this.posY;
/* 51 */     this.prevPosZ = this.posZ;
/*    */     
/* 53 */     if (this.particleAge++ >= this.particleMaxAge)
/*    */     {
/* 55 */       setDead();
/*    */     }
/*    */     
/* 58 */     setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
/* 59 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 60 */     this.motionX *= 0.9599999785423279D;
/* 61 */     this.motionY *= 0.9599999785423279D;
/* 62 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 64 */     EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity(this, 2.0D, false);
/*    */     
/* 66 */     if (var1 != null && this.posY > var1.boundingBox.minY) {
/*    */       
/* 68 */       this.posY += (var1.boundingBox.minY - this.posY) * 0.2D;
/* 69 */       this.motionY += (var1.motionY - this.motionY) * 0.2D;
/* 70 */       setPosition(this.posX, this.posY, this.posZ);
/*    */     } 
/*    */     
/* 73 */     if (this.onGround) {
/*    */       
/* 75 */       this.motionX *= 0.699999988079071D;
/* 76 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCloudFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */