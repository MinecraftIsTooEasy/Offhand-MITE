/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySpellParticleFX
/*    */   extends EntityFX
/*    */ {
/*  8 */   private int baseSpellTextureIndex = 128;
/*    */   
/*    */   public EntitySpellParticleFX(World world, double d, double e, double f, double g, double h, double i) {
/* 11 */     super(world, d, e, f, g, h, i);
/* 12 */     this.motionY *= 0.20000000298023224D;
/* 13 */     if (g == 0.0D && i == 0.0D) {
/* 14 */       this.motionX *= 0.10000000149011612D;
/* 15 */       this.motionZ *= 0.10000000149011612D;
/*    */     } 
/*    */     
/* 18 */     this.particleScale *= 0.75F;
/*    */     
/* 20 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/* 21 */     this.noClip = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {
/* 26 */     float f1 = (this.particleAge + f) / this.particleMaxAge * 32.0F;
/* 27 */     if (f1 < 0.0F) f1 = 0.0F; 
/* 28 */     if (f1 > 1.0F) f1 = 1.0F;
/*    */     
/* 30 */     super.renderParticle(tessellator, f, g, h, i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 35 */     this.prevPosX = this.posX;
/* 36 */     this.prevPosY = this.posY;
/* 37 */     this.prevPosZ = this.posZ;
/*    */     
/* 39 */     if (this.particleAge++ >= this.particleMaxAge) setDead();
/*    */     
/* 41 */     setParticleTextureIndex(this.baseSpellTextureIndex + 7 - this.particleAge * 8 / this.particleMaxAge);
/*    */     
/* 43 */     this.motionY += 0.004D;
/* 44 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 45 */     if (this.posY == this.prevPosY) {
/* 46 */       this.motionX *= 1.1D;
/* 47 */       this.motionZ *= 1.1D;
/*    */     } 
/* 49 */     this.motionX *= 0.9599999785423279D;
/* 50 */     this.motionY *= 0.9599999785423279D;
/* 51 */     this.motionZ *= 0.9599999785423279D;
/*    */     
/* 53 */     if (this.onGround) {
/* 54 */       this.motionX *= 0.699999988079071D;
/* 55 */       this.motionZ *= 0.699999988079071D;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setBaseSpellTextureIndex(int i) {
/* 60 */     this.baseSpellTextureIndex = i;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySpellParticleFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */