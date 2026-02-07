/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityRepairFX
/*    */   extends EntityFX
/*    */ {
/*    */   protected static int random_number_index;
/*    */   
/*    */   public EntityRepairFX(World world, double pos_x, double pos_y, double pos_z, double vel_x, double vel_y, double vel_z) {
/* 11 */     super(world, pos_x, pos_y, pos_z, vel_x, vel_y, vel_z);
/*    */     
/* 13 */     setParticleTextureIndex(0);
/* 14 */     setSize(0.02F, 0.02F);
/*    */ 
/*    */     
/* 17 */     this.particleScale = 1.0F + this.rand.nextFloat() * 0.2F;
/*    */     
/* 19 */     this.motionX = vel_x;
/* 20 */     this.motionY = vel_y;
/* 21 */     this.motionZ = vel_z;
/*    */ 
/*    */     
/* 24 */     this.particleMaxAge = (int)(15.0D / (RNG.float_1[++random_number_index & 0x7FFF] * 0.6D + 0.4D));
/* 25 */     this.noClip = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 30 */     this.prevPosX = this.posX;
/* 31 */     this.prevPosY = this.posY;
/* 32 */     this.prevPosZ = this.posZ;
/*    */     
/* 34 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 36 */     this.motionX *= 0.9900000095367432D;
/* 37 */     this.motionY *= 0.9900000095367432D;
/* 38 */     this.motionZ *= 0.9900000095367432D;
/*    */     
/* 40 */     if (++this.particleAge > this.particleMaxAge)
/* 41 */       setDead(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityRepairFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */