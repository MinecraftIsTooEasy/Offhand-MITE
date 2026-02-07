/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntitySacredFX
/*     */   extends EntityFX
/*     */ {
/*     */   float reddustParticleScale;
/*     */   private static final int random_motion_table_entries = 255;
/*  15 */   private static double[] random_motion_x = new double[255];
/*  16 */   private static double[] random_motion_y = new double[255];
/*  17 */   private static double[] random_motion_z = new double[255];
/*     */   static {
/*  19 */     int random_number_index = 0;
/*     */     
/*  21 */     for (int i = 0; i < 255; i++) {
/*     */       
/*  23 */       double motion_x = (RNG.float_1[++random_number_index & 0x7FFF] * 0.8F - 0.4F);
/*  24 */       double motion_y = (RNG.float_1[++random_number_index & 0x7FFF] * 0.8F - 0.4F);
/*  25 */       double motion_z = (RNG.float_1[++random_number_index & 0x7FFF] * 0.8F - 0.4F);
/*     */       
/*  27 */       float var14 = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
/*     */       
/*  29 */       float var15 = MathHelper.sqrt_double(motion_x * motion_x + motion_y * motion_y + motion_z * motion_z);
/*     */       
/*  31 */       motion_x = motion_x / var15 * var14 * 0.4000000059604645D;
/*  32 */       motion_y = motion_y / var15 * var14 * 0.4000000059604645D + 0.10000000149011612D;
/*  33 */       motion_z = motion_z / var15 * var14 * 0.4000000059604645D;
/*     */ 
/*     */ 
/*     */       
/*  37 */       motion_x *= 0.10000000149011612D;
/*  38 */       motion_y *= 0.10000000149011612D;
/*  39 */       motion_z *= 0.10000000149011612D;
/*     */       
/*  41 */       while (motion_x * motion_x + motion_y * motion_y + motion_z * motion_z > 9.999999747378752E-6D) {
/*     */         
/*  43 */         motion_x *= 0.800000011920929D;
/*  44 */         motion_y *= 0.800000011920929D;
/*  45 */         motion_z *= 0.800000011920929D;
/*     */       } 
/*     */       
/*  48 */       random_motion_x[i] = motion_x;
/*  49 */       random_motion_y[i] = motion_y;
/*  50 */       random_motion_z[i] = motion_z;
/*     */     } 
/*     */   }
/*     */   private static int next_random_motion_index;
/*     */   
/*     */   public EntitySacredFX(World par1World, double par2, double par4, double par6) {
/*  56 */     this(par1World, par2, par4, par6, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntitySacredFX(World par1World, double par2, double par4, double par6, float par8) {
/*  74 */     super(par1World, par2, par4, par6);
/*     */     
/*  76 */     this.motionX = random_motion_x[next_random_motion_index];
/*  77 */     this.motionY = random_motion_y[next_random_motion_index];
/*  78 */     this.motionZ = random_motion_z[next_random_motion_index];
/*     */     
/*  80 */     if (++next_random_motion_index >= 255) {
/*  81 */       next_random_motion_index = 0;
/*     */     }
/*  83 */     this.particleRed = 1.0F;
/*  84 */     this.particleGreen = 1.0F;
/*  85 */     this.particleBlue = 0.9F;
/*  86 */     this.particleAlpha = 0.15F;
/*  87 */     this.particleScale *= 0.25F;
/*  88 */     this.particleScale *= par8;
/*  89 */     this.reddustParticleScale = this.particleScale;
/*  90 */     this.particleMaxAge = 60 + RNG.int_32[++RNG.random_number_index & 0x7FFF];
/*  91 */     this.noClip = false;
/*     */     
/*  93 */     setParticleTextureIndex(100);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/*  98 */     float var2 = (this.particleAge + par1) / this.particleMaxAge;
/*     */     
/* 100 */     if (var2 < 0.0F)
/*     */     {
/* 102 */       var2 = 0.0F;
/*     */     }
/*     */     
/* 105 */     if (var2 > 1.0F)
/*     */     {
/* 107 */       var2 = 1.0F;
/*     */     }
/*     */     
/* 110 */     int var3 = super.getBrightnessForRender(par1);
/* 111 */     short var4 = 240;
/* 112 */     int var5 = var3 >> 16 & 0xFF;
/* 113 */     return var4 | var5 << 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/* 118 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 123 */     float var8 = (this.particleAge + par2) / this.particleMaxAge * 32.0F;
/*     */     
/* 125 */     if (var8 < 0.0F)
/*     */     {
/* 127 */       var8 = 0.0F;
/*     */     }
/*     */     
/* 130 */     if (var8 > 1.0F)
/*     */     {
/* 132 */       var8 = 1.0F;
/*     */     }
/*     */     
/* 135 */     this.particleScale = this.reddustParticleScale * var8;
/* 136 */     super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 144 */     this.prevPosX = this.posX;
/* 145 */     this.prevPosY = this.posY;
/* 146 */     this.prevPosZ = this.posZ;
/*     */     
/* 148 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/* 150 */       setDead();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     this.particleAlpha = (this.particleMaxAge - this.particleAge) / this.particleMaxAge * 0.1F;
/*     */     
/* 158 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */     
/* 160 */     if (this.posY == this.prevPosY) {
/*     */       
/* 162 */       this.motionX *= 1.1D;
/* 163 */       this.motionZ *= 1.1D;
/*     */     } 
/*     */     
/* 166 */     this.motionX *= 0.9599999785423279D;
/* 167 */     this.motionY *= 0.9599999785423279D;
/* 168 */     this.motionZ *= 0.9599999785423279D;
/*     */     
/* 170 */     if (this.onGround) {
/*     */       
/* 172 */       this.motionX *= 0.699999988079071D;
/* 173 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySacredFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */