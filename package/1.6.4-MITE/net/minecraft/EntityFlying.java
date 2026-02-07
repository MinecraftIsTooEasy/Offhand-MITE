/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityFlying
/*    */   extends EntityLiving
/*    */ {
/*    */   public EntityFlying(World world) {
/*  9 */     super(world);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void fall(float f) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void updateFallState(double d, boolean bl) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void moveEntityWithHeading(float f, float g) {
/* 24 */     if (isInWater()) {
/* 25 */       moveFlying(f, g, 0.02F);
/* 26 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */       
/* 28 */       this.motionX *= 0.800000011920929D;
/* 29 */       this.motionY *= 0.800000011920929D;
/* 30 */       this.motionZ *= 0.800000011920929D;
/* 31 */     } else if (handleLavaMovement()) {
/* 32 */       moveFlying(f, g, 0.02F);
/* 33 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 34 */       this.motionX *= 0.5D;
/* 35 */       this.motionY *= 0.5D;
/* 36 */       this.motionZ *= 0.5D;
/*    */     } else {
/* 38 */       float f2 = 0.91F;
/* 39 */       if (this.onGround) {
/* 40 */         f2 = 0.54600006F;
/* 41 */         int i = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
/* 42 */         if (i > 0) {
/* 43 */           f2 = (Block.blocksList[i]).slipperiness * 0.91F;
/*    */         }
/*    */       } 
/*    */       
/* 47 */       float f3 = 0.16277136F / f2 * f2 * f2;
/* 48 */       moveFlying(f, g, this.onGround ? (0.1F * f3) : 0.02F);
/*    */       
/* 50 */       f2 = 0.91F;
/* 51 */       if (this.onGround) {
/* 52 */         f2 = 0.54600006F;
/* 53 */         int i = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
/* 54 */         if (i > 0) {
/* 55 */           f2 = (Block.blocksList[i]).slipperiness * 0.91F;
/*    */         }
/*    */       } 
/*    */       
/* 59 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */       
/* 61 */       this.motionX *= f2;
/* 62 */       this.motionY *= f2;
/* 63 */       this.motionZ *= f2;
/*    */     } 
/* 65 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/* 66 */     double d1 = this.posX - this.prevPosX;
/* 67 */     double d2 = this.posZ - this.prevPosZ;
/* 68 */     float f1 = MathHelper.sqrt_double(d1 * d1 + d2 * d2) * 4.0F;
/* 69 */     if (f1 > 1.0F) f1 = 1.0F; 
/* 70 */     this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
/* 71 */     this.limbSwing += this.limbSwingAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnLadder() {
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFlying.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */