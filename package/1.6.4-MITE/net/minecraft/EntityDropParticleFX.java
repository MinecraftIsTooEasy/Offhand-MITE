/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDropParticleFX
/*     */   extends EntityFX
/*     */ {
/*     */   private Material materialType;
/*     */   private int bobTimer;
/*     */   
/*     */   public EntityDropParticleFX(World par1World, double par2, double par4, double par6, Material par8Material) {
/*  13 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*  14 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*     */     
/*  16 */     if (par8Material == Material.water) {
/*     */       
/*  18 */       this.particleRed = 0.0F;
/*  19 */       this.particleGreen = 0.0F;
/*  20 */       this.particleBlue = 1.0F;
/*     */     }
/*     */     else {
/*     */       
/*  24 */       this.particleRed = 1.0F;
/*  25 */       this.particleGreen = 0.0F;
/*  26 */       this.particleBlue = 0.0F;
/*     */     } 
/*     */     
/*  29 */     setParticleTextureIndex(113);
/*  30 */     setSize(0.01F, 0.01F);
/*  31 */     this.particleGravity = 0.06F;
/*  32 */     this.materialType = par8Material;
/*  33 */     this.bobTimer = 40;
/*  34 */     this.particleMaxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
/*  35 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/*  40 */     return (this.materialType == Material.water) ? super.getBrightnessForRender(par1) : 257;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/*  48 */     return (this.materialType == Material.water) ? super.getBrightness(par1) : 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  56 */     this.prevPosX = this.posX;
/*  57 */     this.prevPosY = this.posY;
/*  58 */     this.prevPosZ = this.posZ;
/*     */     
/*  60 */     if (this.materialType == Material.water) {
/*     */       
/*  62 */       this.particleRed = 0.2F;
/*  63 */       this.particleGreen = 0.3F;
/*  64 */       this.particleBlue = 1.0F;
/*     */     }
/*     */     else {
/*     */       
/*  68 */       this.particleRed = 1.0F;
/*  69 */       this.particleGreen = 16.0F / (40 - this.bobTimer + 16);
/*  70 */       this.particleBlue = 4.0F / (40 - this.bobTimer + 8);
/*     */     } 
/*     */     
/*  73 */     this.motionY -= this.particleGravity;
/*     */     
/*  75 */     if (this.bobTimer-- > 0) {
/*     */       
/*  77 */       this.motionX *= 0.02D;
/*  78 */       this.motionY *= 0.02D;
/*  79 */       this.motionZ *= 0.02D;
/*  80 */       setParticleTextureIndex(113);
/*     */     }
/*     */     else {
/*     */       
/*  84 */       setParticleTextureIndex(112);
/*     */     } 
/*     */     
/*  87 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  88 */     this.motionX *= 0.9800000190734863D;
/*  89 */     this.motionY *= 0.9800000190734863D;
/*  90 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/*  92 */     if (this.particleMaxAge-- <= 0)
/*     */     {
/*  94 */       setDead();
/*     */     }
/*     */     
/*  97 */     if (this.onGround) {
/*     */       
/*  99 */       if (this.materialType == Material.water) {
/*     */         
/* 101 */         setDead();
/*     */         
/* 103 */         this.worldObj.spawnParticle(EnumParticle.splash, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */       else {
/*     */         
/* 107 */         setParticleTextureIndex(114);
/*     */       } 
/*     */       
/* 110 */       this.motionX *= 0.699999988079071D;
/* 111 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */     
/* 114 */     Material var1 = this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*     */     
/* 116 */     if (var1.isLiquid()) {
/*     */       
/* 118 */       double var2 = ((MathHelper.floor_double(this.posY) + 1) - BlockFluid.getFluidHeightPercent(this.worldObj.getBlockMetadata(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))));
/*     */       
/* 120 */       if (this.posY < var2)
/*     */       {
/* 122 */         setDead();
/*     */       }
/*     */     }
/* 125 */     else if (this.worldObj.isPointInsideBlockCollisionBounds(getCenterPoint())) {
/*     */       
/* 127 */       setDead();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDropParticleFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */