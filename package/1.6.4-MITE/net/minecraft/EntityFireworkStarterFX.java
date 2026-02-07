/*     */ package net.minecraft;
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
/*     */ public class EntityFireworkStarterFX
/*     */   extends EntityFX
/*     */ {
/*     */   private int fireworkAge;
/*     */   private final EffectRenderer theEffectRenderer;
/*     */   private NBTTagList fireworkExplosions;
/*     */   boolean twinkle;
/*     */   
/*     */   public EntityFireworkStarterFX(World world, double d, double e, double f, double g, double h, double i, EffectRenderer effectRenderer, NBTTagCompound nBTTagCompound) {
/*  22 */     super(world, d, e, f, 0.0D, 0.0D, 0.0D);
/*  23 */     this.motionX = g;
/*  24 */     this.motionY = h;
/*  25 */     this.motionZ = i;
/*  26 */     this.theEffectRenderer = effectRenderer;
/*  27 */     this.particleMaxAge = 8;
/*     */     
/*  29 */     if (nBTTagCompound != null) {
/*  30 */       this.fireworkExplosions = nBTTagCompound.getTagList("Explosions");
/*  31 */       if (this.fireworkExplosions.tagCount() == 0) {
/*  32 */         this.fireworkExplosions = null;
/*     */       } else {
/*  34 */         this.particleMaxAge = this.fireworkExplosions.tagCount() * 2 - 1;
/*     */ 
/*     */         
/*  37 */         for (byte b = 0; b < this.fireworkExplosions.tagCount(); b++) {
/*  38 */           NBTTagCompound nBTTagCompound1 = (NBTTagCompound)this.fireworkExplosions.tagAt(b);
/*  39 */           if (nBTTagCompound1.getBoolean("Flicker")) {
/*  40 */             this.twinkle = true;
/*  41 */             this.particleMaxAge += 15;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderParticle(Tessellator tessellator, float f, float g, float h, float i, float j, float k) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  57 */     if (this.fireworkAge == 0 && this.fireworkExplosions != null) {
/*  58 */       boolean bool = func_92037_i();
/*     */       
/*  60 */       boolean bool1 = false;
/*  61 */       if (this.fireworkExplosions.tagCount() >= 3) {
/*  62 */         bool1 = true;
/*     */       } else {
/*  64 */         for (byte b = 0; b < this.fireworkExplosions.tagCount(); b++) {
/*  65 */           NBTTagCompound nBTTagCompound = (NBTTagCompound)this.fireworkExplosions.tagAt(b);
/*  66 */           if (nBTTagCompound.getByte("Type") == 1) {
/*  67 */             bool1 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*  73 */       String str = "fireworks." + (bool1 ? "largeBlast" : "blast") + (bool ? "_far" : "");
/*  74 */       this.worldObj.playSound(this.posX, this.posY, this.posZ, str, 20.0F, 0.95F + this.rand.nextFloat() * 0.1F, true);
/*     */     } 
/*     */     
/*  77 */     if (this.fireworkAge % 2 == 0 && this.fireworkExplosions != null && this.fireworkAge / 2 < this.fireworkExplosions.tagCount()) {
/*     */ 
/*     */       
/*  80 */       int i = this.fireworkAge / 2;
/*  81 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)this.fireworkExplosions.tagAt(i);
/*     */       
/*  83 */       byte b = nBTTagCompound.getByte("Type");
/*  84 */       boolean bool1 = nBTTagCompound.getBoolean("Trail");
/*  85 */       boolean bool2 = nBTTagCompound.getBoolean("Flicker");
/*  86 */       int[] arrayOfInt1 = nBTTagCompound.getIntArray("Colors");
/*  87 */       int[] arrayOfInt2 = nBTTagCompound.getIntArray("FadeColors");
/*     */       
/*  89 */       if (b == 1) {
/*     */         
/*  91 */         createBall(0.5D, 4, arrayOfInt1, arrayOfInt2, bool1, bool2);
/*  92 */       } else if (b == 2) {
/*     */         
/*  94 */         createShaped(0.5D, new double[][] { { 0.0D, 1.0D }, , { 0.3455D, 0.309D }, , { 0.9511D, 0.309D }, , { 0.3795918367346939D, -0.12653061224489795D }, , { 0.6122448979591837D, -0.8040816326530612D }, , { 0.0D, -0.35918367346938773D },  }, arrayOfInt1, arrayOfInt2, bool1, bool2, false);
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
/*     */       }
/* 109 */       else if (b == 3) {
/*     */         
/* 111 */         createShaped(0.5D, new double[][] { { 0.0D, 0.2D }, , { 0.2D, 0.2D }, , { 0.2D, 0.6D }, , { 0.6D, 0.6D }, , { 0.6D, 0.2D }, , { 0.2D, 0.2D }, , { 0.2D, 0.0D }, , { 0.4D, 0.0D }, , { 0.4D, -0.6D }, , { 0.2D, -0.6D }, , { 0.2D, -0.4D }, , { 0.0D, -0.4D },  }, arrayOfInt1, arrayOfInt2, bool1, bool2, true);
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
/*     */       }
/* 138 */       else if (b == 4) {
/* 139 */         createBurst(arrayOfInt1, arrayOfInt2, bool1, bool2);
/*     */       } else {
/*     */         
/* 142 */         createBall(0.25D, 2, arrayOfInt1, arrayOfInt2, bool1, bool2);
/*     */       } 
/*     */       
/* 145 */       int j = arrayOfInt1[0];
/* 146 */       float f1 = ((j & 0xFF0000) >> 16) / 255.0F;
/* 147 */       float f2 = ((j & 0xFF00) >> 8) / 255.0F;
/* 148 */       float f3 = ((j & 0xFF) >> 0) / 255.0F;
/* 149 */       EntityFireworkOverlayFX entityFireworkOverlayFX = new EntityFireworkOverlayFX(this.worldObj, this.posX, this.posY, this.posZ);
/* 150 */       entityFireworkOverlayFX.setRBGColorF(f1, f2, f3);
/* 151 */       this.theEffectRenderer.addEffect(entityFireworkOverlayFX);
/*     */     } 
/*     */     
/* 154 */     this.fireworkAge++;
/* 155 */     if (this.fireworkAge > this.particleMaxAge) {
/* 156 */       if (this.twinkle) {
/* 157 */         boolean bool = func_92037_i();
/* 158 */         String str = "fireworks." + (bool ? "twinkle_far" : "twinkle");
/* 159 */         this.worldObj.playSound(this.posX, this.posY, this.posZ, str, 20.0F, 0.9F + this.rand.nextFloat() * 0.15F, true);
/*     */       } 
/*     */       
/* 162 */       setDead();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean func_92037_i() {
/* 167 */     Minecraft minecraft = Minecraft.getMinecraft();
/* 168 */     if (minecraft != null && minecraft.renderViewEntity != null && 
/* 169 */       minecraft.renderViewEntity.getDistanceSq(this.posX, this.posY, this.posZ) < 256.0D) {
/* 170 */       return false;
/*     */     }
/*     */     
/* 173 */     return true;
/*     */   }
/*     */   
/*     */   private void createParticle(double d, double e, double f, double g, double h, double i, int[] is, int[] js, boolean bl, boolean bl2) {
/* 177 */     EntityFireworkSparkFX entityFireworkSparkFX = new EntityFireworkSparkFX(this.worldObj, d, e, f, g, h, i, this.theEffectRenderer);
/* 178 */     entityFireworkSparkFX.setTrail(bl);
/* 179 */     entityFireworkSparkFX.setTwinkle(bl2);
/*     */     
/* 181 */     int j = this.rand.nextInt(is.length);
/* 182 */     entityFireworkSparkFX.setColour(is[j]);
/* 183 */     if (js != null && js.length > 0) {
/* 184 */       entityFireworkSparkFX.setFadeColour(js[this.rand.nextInt(js.length)]);
/*     */     }
/* 186 */     this.theEffectRenderer.addEffect(entityFireworkSparkFX);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createBall(double d, int i, int[] is, int[] js, boolean bl, boolean bl2) {
/* 191 */     double d1 = this.posX;
/* 192 */     double d2 = this.posY;
/* 193 */     double d3 = this.posZ;
/*     */     
/* 195 */     for (int j = -i; j <= i; j++) {
/* 196 */       for (int k = -i; k <= i; k++) {
/* 197 */         for (int m = -i; m <= i; m++) {
/* 198 */           double d4 = k + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
/* 199 */           double d5 = j + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
/* 200 */           double d6 = m + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5D;
/* 201 */           double d7 = MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6) / d + this.rand.nextGaussian() * 0.05D;
/*     */           
/* 203 */           createParticle(d1, d2, d3, d4 / d7, d5 / d7, d6 / d7, is, js, bl, bl2);
/*     */           
/* 205 */           if (j != -i && j != i && k != -i && k != i) {
/* 206 */             m += i * 2 - 1;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createShaped(double d, double[][] ds, int[] is, int[] js, boolean bl, boolean bl2, boolean bl3) {
/* 215 */     double d1 = ds[0][0];
/* 216 */     double d2 = ds[0][1];
/*     */ 
/*     */     
/* 219 */     createParticle(this.posX, this.posY, this.posZ, d1 * d, d2 * d, 0.0D, is, js, bl, bl2);
/*     */ 
/*     */     
/* 222 */     float f = this.rand.nextFloat() * 3.1415927F;
/* 223 */     double d3 = bl3 ? 0.034D : 0.34D;
/* 224 */     for (byte b = 0; b < 3; b++) {
/* 225 */       double d4 = f + (b * 3.1415927F) * d3;
/*     */       
/* 227 */       double d5 = d1;
/* 228 */       double d6 = d2;
/*     */       
/* 230 */       for (byte b1 = 1; b1 < ds.length; b1++) {
/*     */         
/* 232 */         double d7 = ds[b1][0];
/* 233 */         double d8 = ds[b1][1];
/*     */         double d9;
/* 235 */         for (d9 = 0.25D; d9 <= 1.0D; d9 += 0.25D) {
/*     */           
/* 237 */           double d10 = (d5 + (d7 - d5) * d9) * d;
/* 238 */           double d11 = (d6 + (d8 - d6) * d9) * d;
/*     */           
/* 240 */           double d12 = d10 * Math.sin(d4);
/* 241 */           d10 *= Math.cos(d4);
/*     */           double d13;
/* 243 */           for (d13 = -1.0D; d13 <= 1.0D; d13 += 2.0D) {
/* 244 */             createParticle(this.posX, this.posY, this.posZ, d10 * d13, d11, d12 * d13, is, js, bl, bl2);
/*     */           }
/*     */         } 
/* 247 */         d5 = d7;
/* 248 */         d6 = d8;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createBurst(int[] is, int[] js, boolean bl, boolean bl2) {
/* 257 */     double d1 = this.rand.nextGaussian() * 0.05D;
/* 258 */     double d2 = this.rand.nextGaussian() * 0.05D;
/*     */     
/* 260 */     for (byte b = 0; b < 70; b++) {
/*     */       
/* 262 */       double d3 = this.motionX * 0.5D + this.rand.nextGaussian() * 0.15D + d1;
/* 263 */       double d4 = this.motionZ * 0.5D + this.rand.nextGaussian() * 0.15D + d2;
/* 264 */       double d5 = this.motionY * 0.5D + this.rand.nextDouble() * 0.5D;
/*     */       
/* 266 */       createParticle(this.posX, this.posY, this.posZ, d3, d5, d4, is, js, bl, bl2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 274 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFireworkStarterFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */