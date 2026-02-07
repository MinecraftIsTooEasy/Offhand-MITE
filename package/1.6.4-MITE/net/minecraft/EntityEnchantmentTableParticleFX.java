/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityEnchantmentTableParticleFX
/*    */   extends EntityFX {
/*    */   private float field_70565_a;
/*    */   private double field_70568_aq;
/*    */   private double field_70567_ar;
/*    */   private double field_70566_as;
/*    */   
/*    */   public EntityEnchantmentTableParticleFX(World world, double d, double e, double f, double g, double h, double i) {
/* 11 */     super(world, d, e, f, g, h, i);
/*    */     
/* 13 */     this.motionX = g;
/* 14 */     this.motionY = h;
/* 15 */     this.motionZ = i;
/* 16 */     this.field_70568_aq = this.posX = d;
/* 17 */     this.field_70567_ar = this.posY = e;
/* 18 */     this.field_70566_as = this.posZ = f;
/*    */     
/* 20 */     float f1 = this.rand.nextFloat() * 0.6F + 0.4F;
/* 21 */     this.field_70565_a = this.particleScale = this.rand.nextFloat() * 0.5F + 0.2F;
/* 22 */     this.particleRed = this.particleGreen = this.particleBlue = 1.0F * f1;
/* 23 */     this.particleGreen *= 0.9F;
/* 24 */     this.particleRed *= 0.9F;
/*    */     
/* 26 */     this.particleMaxAge = (int)(Math.random() * 10.0D) + 30;
/* 27 */     this.noClip = true;
/* 28 */     setParticleTextureIndex((int)(Math.random() * 26.0D + 1.0D + 224.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float f) {
/* 33 */     int i = super.getBrightnessForRender(f);
/*    */     
/* 35 */     float f1 = this.particleAge / this.particleMaxAge;
/* 36 */     f1 *= f1;
/* 37 */     f1 *= f1;
/*    */     
/* 39 */     int j = i & 0xFF;
/* 40 */     int k = i >> 16 & 0xFF;
/* 41 */     k += (int)(f1 * 15.0F * 16.0F);
/* 42 */     if (k > 240) k = 240; 
/* 43 */     return j | k << 16;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBrightness(float f) {
/* 48 */     float f1 = super.getBrightness(f);
/* 49 */     float f2 = this.particleAge / this.particleMaxAge;
/* 50 */     f2 *= f2;
/* 51 */     f2 *= f2;
/* 52 */     return f1 * (1.0F - f2) + f2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 57 */     this.prevPosX = this.posX;
/* 58 */     this.prevPosY = this.posY;
/* 59 */     this.prevPosZ = this.posZ;
/*    */     
/* 61 */     float f1 = this.particleAge / this.particleMaxAge;
/* 62 */     f1 = 1.0F - f1;
/*    */     
/* 64 */     float f2 = 1.0F - f1;
/* 65 */     f2 *= f2;
/* 66 */     f2 *= f2;
/* 67 */     this.posX = this.field_70568_aq + this.motionX * f1;
/* 68 */     this.posY = this.field_70567_ar + this.motionY * f1 - (f2 * 1.2F);
/* 69 */     this.posZ = this.field_70566_as + this.motionZ * f1;
/*    */     
/* 71 */     if (this.particleAge++ >= this.particleMaxAge) setDead(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEnchantmentTableParticleFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */