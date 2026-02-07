/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class EntityDepthSuspendParticle
/*    */   extends EntityAuraFX
/*    */ {
/*  7 */   public static EntityDepthSuspendParticle[] cached_objects = new EntityDepthSuspendParticle[256];
/*    */   
/*    */   public static int num_cached_objects;
/*    */   
/*    */   public EntityDepthSuspendParticle(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 12 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*    */     
/* 14 */     this.motionX = 0.0D;
/* 15 */     this.motionY = 0.006000000052154064D;
/* 16 */     this.motionZ = 0.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDead() {
/* 21 */     if (num_cached_objects < cached_objects.length) {
/* 22 */       cached_objects[num_cached_objects++] = this;
/*    */     }
/* 24 */     super.setDead();
/*    */   }
/*    */ 
/*    */   
/*    */   public static EntityAuraFX getCachedOrCreate(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 29 */     if (num_cached_objects == 0)
/*    */     {
/* 31 */       return new EntityDepthSuspendParticle(par1World, par2, par4, par6, par8, par10, par12);
/*    */     }
/*    */ 
/*    */     
/* 35 */     EntityAuraFX fx = cached_objects[--num_cached_objects];
/*    */     
/* 37 */     fx.worldObj = par1World;
/*    */     
/* 39 */     fx.setPosition(par2, par4, par6);
/*    */     
/* 41 */     fx.lastTickPosX = par2;
/* 42 */     fx.lastTickPosY = par4;
/* 43 */     fx.lastTickPosZ = par6;
/*    */     
/* 45 */     fx.motionX = 0.0D;
/* 46 */     fx.motionY = 0.006000000052154064D;
/* 47 */     fx.motionZ = 0.0D;
/*    */     
/* 49 */     fx.particleAge = 0;
/*    */ 
/*    */     
/* 52 */     fx.isDead = false;
/*    */     
/* 54 */     return fx;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDepthSuspendParticle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */