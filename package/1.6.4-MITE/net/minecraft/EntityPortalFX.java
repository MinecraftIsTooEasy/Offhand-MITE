/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPortalFX
/*     */   extends EntityFX
/*     */ {
/*     */   private float portalParticleScale;
/*     */   private double portalPosX;
/*     */   private double portalPosY;
/*     */   private double portalPosZ;
/*     */   public static final int TYPE_UNDERWORLD = 0;
/*     */   public static final int TYPE_RUNEGATE = 1;
/*     */   public static final int TYPE_NETHER = 2;
/*     */   
/*     */   public EntityPortalFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int type) {
/*  18 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*  19 */     this.motionX = par8;
/*  20 */     this.motionY = par10;
/*  21 */     this.motionZ = par12;
/*  22 */     this.portalPosX = this.posX = par2;
/*  23 */     this.portalPosY = this.posY = par4;
/*  24 */     this.portalPosZ = this.posZ = par6;
/*  25 */     float var14 = this.rand.nextFloat() * 0.6F + 0.4F;
/*  26 */     this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  32 */     if (type == 1) {
/*     */       
/*  34 */       var14 = (var14 + 1.0F) * 0.5F;
/*     */       
/*  36 */       this.particleRed = 0.03137255F * var14;
/*  37 */       this.particleGreen = 0.4745098F * var14;
/*  38 */       this.particleBlue = 0.6F * var14;
/*     */     }
/*  40 */     else if (type == 2) {
/*     */       
/*  42 */       this.particleRed = 0.74509805F * var14;
/*  43 */       this.particleGreen = 0.14509805F * var14;
/*  44 */       this.particleBlue = 0.043137256F * var14;
/*     */     }
/*     */     else {
/*     */       
/*  48 */       this.particleRed = this.particleGreen = this.particleBlue = 1.0F * var14;
/*  49 */       this.particleGreen *= 0.3F;
/*  50 */       this.particleRed *= 0.9F;
/*     */     } 
/*     */     
/*  53 */     this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
/*  54 */     this.noClip = true;
/*  55 */     setParticleTextureIndex((int)(Math.random() * 8.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  60 */     float var8 = (this.particleAge + par2) / this.particleMaxAge;
/*  61 */     var8 = 1.0F - var8;
/*  62 */     var8 *= var8;
/*  63 */     var8 = 1.0F - var8;
/*  64 */     this.particleScale = this.portalParticleScale * var8;
/*  65 */     super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/*  70 */     int var2 = super.getBrightnessForRender(par1);
/*  71 */     float var3 = this.particleAge / this.particleMaxAge;
/*  72 */     var3 *= var3;
/*  73 */     var3 *= var3;
/*  74 */     int var4 = var2 & 0xFF;
/*  75 */     int var5 = var2 >> 16 & 0xFF;
/*  76 */     var5 += (int)(var3 * 15.0F * 16.0F);
/*     */     
/*  78 */     if (var5 > 240)
/*     */     {
/*  80 */       var5 = 240;
/*     */     }
/*     */     
/*  83 */     return var4 | var5 << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/*  91 */     float var2 = super.getBrightness(par1);
/*  92 */     float var3 = this.particleAge / this.particleMaxAge;
/*  93 */     var3 = var3 * var3 * var3 * var3;
/*  94 */     return var2 * (1.0F - var3) + var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 102 */     this.prevPosX = this.posX;
/* 103 */     this.prevPosY = this.posY;
/* 104 */     this.prevPosZ = this.posZ;
/* 105 */     float var1 = this.particleAge / this.particleMaxAge;
/* 106 */     float var2 = var1;
/* 107 */     var1 = -var1 + var1 * var1 * 2.0F;
/* 108 */     var1 = 1.0F - var1;
/* 109 */     this.posX = this.portalPosX + this.motionX * var1;
/* 110 */     this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
/* 111 */     this.posZ = this.portalPosZ + this.motionZ * var1;
/*     */     
/* 113 */     if (this.particleAge++ >= this.particleMaxAge)
/*     */     {
/* 115 */       setDead();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPortalFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */