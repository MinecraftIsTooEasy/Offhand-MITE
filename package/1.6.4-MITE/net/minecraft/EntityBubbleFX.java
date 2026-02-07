/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBubbleFX
/*    */   extends EntityFX
/*    */ {
/*    */   public EntityBubbleFX(World world, double d, double e, double f, double g, double h, double i) {
/*  9 */     super(world, d, e, f, g, h, i);
/*    */     
/* 11 */     this.particleRed = 1.0F;
/* 12 */     this.particleGreen = 1.0F;
/* 13 */     this.particleBlue = 1.0F;
/* 14 */     setParticleTextureIndex(32);
/* 15 */     setSize(0.02F, 0.02F);
/*    */     
/* 17 */     this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
/*    */     
/* 19 */     this.motionX = g * 0.20000000298023224D + ((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/* 20 */     this.motionY = h * 0.20000000298023224D + ((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/* 21 */     this.motionZ = i * 0.20000000298023224D + ((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
/*    */     
/* 23 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 28 */     this.prevPosX = this.posX;
/* 29 */     this.prevPosY = this.posY;
/* 30 */     this.prevPosZ = this.posZ;
/*    */     
/* 32 */     this.motionY += 0.002D;
/* 33 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 34 */     this.motionX *= 0.8500000238418579D;
/* 35 */     this.motionY *= 0.8500000238418579D;
/* 36 */     this.motionZ *= 0.8500000238418579D;
/*    */     
/* 38 */     if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) != Material.water) setDead();
/*    */     
/* 40 */     if (this.particleMaxAge-- <= 0) setDead(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBubbleFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */