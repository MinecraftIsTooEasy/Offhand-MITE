/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Explosion
/*     */ {
/*     */   public boolean isFlaming;
/*     */   public boolean isSmoking = true;
/*  18 */   private int field_77289_h = 16;
/*  19 */   private Random explosionRNG = new Random();
/*     */   
/*     */   private World worldObj;
/*     */   
/*     */   public double explosionX;
/*     */   public double explosionY;
/*     */   public double explosionZ;
/*     */   public Entity exploder;
/*     */   public float explosion_size_vs_blocks;
/*     */   public float explosion_size_vs_living_entities;
/*  29 */   public List affectedBlockPositions = new ArrayList();
/*  30 */   private Map field_77288_k = new HashMap<Object, Object>();
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
/*     */   public Explosion(World world, Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
/*  44 */     this.worldObj = world;
/*  45 */     this.exploder = exploder;
/*  46 */     this.explosionX = posX;
/*  47 */     this.explosionY = posY;
/*  48 */     this.explosionZ = posZ;
/*  49 */     this.explosion_size_vs_blocks = explosion_size_vs_blocks;
/*  50 */     this.explosion_size_vs_living_entities = explosion_size_vs_living_entities;
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
/*     */   private void doPreExplosionA() {
/*  64 */     for (int var3 = 0; var3 < this.field_77289_h; var3++) {
/*     */       
/*  66 */       for (int var4 = 0; var4 < this.field_77289_h; var4++) {
/*     */         
/*  68 */         for (int var5 = 0; var5 < this.field_77289_h; var5++) {
/*     */           
/*  70 */           if (var3 == 0 || var3 == this.field_77289_h - 1 || var4 == 0 || var4 == this.field_77289_h - 1 || var5 == 0 || var5 == this.field_77289_h - 1) {
/*     */             
/*  72 */             double var6 = (var3 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F);
/*  73 */             double var8 = (var4 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F);
/*  74 */             double var10 = (var5 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F);
/*  75 */             double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
/*  76 */             var6 /= var12;
/*  77 */             var8 /= var12;
/*  78 */             var10 /= var12;
/*     */             
/*  80 */             float var14 = this.explosion_size_vs_blocks * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
/*  81 */             var14 *= 2.0F;
/*  82 */             var14 *= 1.25F;
/*  83 */             double var15 = this.explosionX;
/*  84 */             double var17 = this.explosionY;
/*  85 */             double var19 = this.explosionZ;
/*     */             
/*  87 */             for (float var21 = 0.3F; var14 > 0.0F; var14 -= var21 * 0.75F) {
/*     */ 
/*     */               
/*  90 */               int var22 = MathHelper.floor_double(var15);
/*  91 */               int var23 = MathHelper.floor_double(var17);
/*  92 */               int var24 = MathHelper.floor_double(var19);
/*  93 */               int var25 = this.worldObj.getBlockId(var22, var23, var24);
/*     */               
/*  95 */               if (var25 > 0) {
/*     */                 
/*  97 */                 Block var26 = Block.blocksList[var25];
/*  98 */                 float var27 = var26.getExplosionResistance(this);
/*     */                 
/* 100 */                 if (var27 < 0.0F || var27 > 0.1F) {
/* 101 */                   var14 = 0.0F;
/*     */                 } else {
/* 103 */                   var14 -= (var27 + 0.3F) * var21;
/*     */                 } 
/*     */               } 
/* 106 */               if (var14 > 0.0F && (this.exploder == null || this.exploder.shouldExplodeBlock(this, this.worldObj, var22, var23, var24, var25, var14))) {
/*     */                 
/* 108 */                 if (var25 > 0)
/*     */                 {
/*     */                   
/* 111 */                   Block.blocksList[var25].dropBlockAsEntityItem((new BlockBreakInfo(this.worldObj, var22, var23, var24)).setExploded(this));
/*     */                 }
/*     */                 
/* 114 */                 this.worldObj.setBlockToAir(var22, var23, var24);
/*     */               
/*     */               }
/* 117 */               else if (var14 >= 0.0F && this.explosionY > var23 && this.worldObj.getBlock(var22, var23, var24) == Block.mycelium) {
/*     */                 
/* 119 */                 this.worldObj.setBlock(var22, var23, var24, Block.dirt.blockID);
/* 120 */                 Block.dirt.onUnderminedByPlayer(this.worldObj, null, var22, var23, var24);
/*     */               } 
/*     */               
/* 123 */               var15 += var6 * var21;
/* 124 */               var17 += var8 * var21;
/* 125 */               var19 += var10 * var21;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExplosionA() {
/* 138 */     doPreExplosionA();
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (this.exploder instanceof EntityInfernalCreeper || this.exploder instanceof EntityNetherspawn) {
/* 143 */       this.isFlaming = true;
/*     */     }
/*     */     
/* 146 */     HashSet<ChunkPosition> var2 = new HashSet();
/*     */ 
/*     */ 
/*     */     
/*     */     int var3;
/*     */ 
/*     */ 
/*     */     
/* 154 */     for (var3 = 0; var3 < this.field_77289_h; var3++) {
/*     */       
/* 156 */       for (int j = 0; j < this.field_77289_h; j++) {
/*     */         
/* 158 */         for (int k = 0; k < this.field_77289_h; k++) {
/*     */           
/* 160 */           if (var3 == 0 || var3 == this.field_77289_h - 1 || j == 0 || j == this.field_77289_h - 1 || k == 0 || k == this.field_77289_h - 1) {
/*     */             
/* 162 */             double var6 = (var3 / (this.field_77289_h - 1.0F) * 2.0F - 1.0F);
/* 163 */             double var8 = (j / (this.field_77289_h - 1.0F) * 2.0F - 1.0F);
/* 164 */             double var10 = (k / (this.field_77289_h - 1.0F) * 2.0F - 1.0F);
/* 165 */             double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
/* 166 */             var6 /= var12;
/* 167 */             var8 /= var12;
/* 168 */             var10 /= var12;
/*     */             
/* 170 */             float var14 = this.explosion_size_vs_blocks * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
/* 171 */             double var15 = this.explosionX;
/* 172 */             double var17 = this.explosionY;
/* 173 */             double var19 = this.explosionZ;
/*     */             
/* 175 */             for (float var21 = 0.3F; var14 > 0.0F; var14 -= var21 * 0.75F) {
/*     */               
/* 177 */               int var22 = MathHelper.floor_double(var15);
/* 178 */               int var23 = MathHelper.floor_double(var17);
/* 179 */               int var24 = MathHelper.floor_double(var19);
/* 180 */               int var25 = this.worldObj.getBlockId(var22, var23, var24);
/*     */               
/* 182 */               if (var25 > 0) {
/*     */                 
/* 184 */                 Block var26 = Block.blocksList[var25];
/*     */ 
/*     */                 
/* 187 */                 float var27 = var26.getExplosionResistance(this);
/*     */                 
/* 189 */                 if (var27 > 0.8F && this.exploder instanceof EntityWitherSkull) {
/*     */                   
/* 191 */                   EntityWitherSkull wither_skull = (EntityWitherSkull)this.exploder;
/*     */                   
/* 193 */                   if (wither_skull.isInvulnerable() && var26 != Block.bedrock && var26 != Block.endPortal && var26 != Block.endPortalFrame && var26 != Block.mantleOrCore) {
/* 194 */                     var27 = 0.8F;
/*     */                   }
/*     */                 } 
/*     */ 
/*     */                 
/* 199 */                 if (var27 < 0.0F) {
/* 200 */                   var14 = 0.0F;
/*     */                 } else {
/* 202 */                   var14 -= (var27 + 0.3F) * var21;
/*     */                 } 
/*     */               } 
/* 205 */               if (var14 > 0.0F && (this.exploder == null || this.exploder.shouldExplodeBlock(this, this.worldObj, var22, var23, var24, var25, var14)))
/*     */               {
/* 207 */                 var2.add(new ChunkPosition(var22, var23, var24));
/*     */               }
/*     */               
/* 210 */               var15 += var6 * var21;
/* 211 */               var17 += var8 * var21;
/* 212 */               var19 += var10 * var21;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 219 */     float effective_explosion_size_vs_entities = this.explosion_size_vs_living_entities * 4.0F;
/*     */     
/* 221 */     this.affectedBlockPositions.addAll(var2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     var3 = MathHelper.floor_double(this.explosionX - effective_explosion_size_vs_entities - 1.0D);
/* 230 */     int var4 = MathHelper.floor_double(this.explosionX + effective_explosion_size_vs_entities + 1.0D);
/* 231 */     int var5 = MathHelper.floor_double(this.explosionY - effective_explosion_size_vs_entities - 1.0D);
/* 232 */     int var29 = MathHelper.floor_double(this.explosionY + effective_explosion_size_vs_entities + 1.0D);
/* 233 */     int var7 = MathHelper.floor_double(this.explosionZ - effective_explosion_size_vs_entities - 1.0D);
/* 234 */     int var30 = MathHelper.floor_double(this.explosionZ + effective_explosion_size_vs_entities + 1.0D);
/*     */     
/* 236 */     List<Entity> var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getAABBPool().getAABB(var3, var5, var7, var4, var29, var30));
/* 237 */     Vec3 var31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.explosionX, this.explosionY, this.explosionZ);
/*     */     
/* 239 */     for (int var11 = 0; var11 < var9.size(); var11++) {
/*     */       
/* 241 */       Entity var32 = var9.get(var11);
/*     */       
/* 243 */       if (!var32.handleExplosion(this)) {
/*     */ 
/*     */ 
/*     */         
/* 247 */         double var13 = var32.getDistance(this.explosionX, this.explosionY, this.explosionZ) / effective_explosion_size_vs_entities;
/*     */         
/* 249 */         if (var13 <= 1.0D) {
/*     */           
/* 251 */           double var15 = var32.posX - this.explosionX;
/* 252 */           double var17 = var32.posY + var32.getEyeHeight() - this.explosionY;
/* 253 */           double var19 = var32.posZ - this.explosionZ;
/* 254 */           double var34 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
/*     */           
/* 256 */           if (var34 != 0.0D) {
/*     */             
/* 258 */             var15 /= var34;
/* 259 */             var17 /= var34;
/* 260 */             var19 /= var34;
/* 261 */             double var33 = this.worldObj.getBlockDensity(var31, var32.boundingBox);
/* 262 */             double var35 = (1.0D - var13) * var33;
/*     */ 
/*     */ 
/*     */             
/* 266 */             float damage = effective_explosion_size_vs_entities * (float)var35 * 4.0F;
/*     */ 
/*     */ 
/*     */             
/* 270 */             damage *= 0.6666667F;
/*     */             
/* 272 */             if (damage >= 0.5F) {
/* 273 */               var32.attackEntityFrom(new Damage(DamageSource.setExplosionSource(this), damage));
/*     */             }
/* 275 */             double var36 = EnchantmentProtection.func_92092_a(var32, var35);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 280 */             if (!(var32 instanceof EntityItem)) {
/*     */               
/* 282 */               var32.motionX += var15 * var36;
/* 283 */               var32.motionY += var17 * var36;
/* 284 */               var32.motionZ += var19 * var36;
/*     */             } 
/*     */             
/* 287 */             if (var32 instanceof EntityPlayer)
/*     */             {
/* 289 */               this.field_77288_k.put((EntityPlayer)var32, this.worldObj.getWorldVec3Pool().getVecFromPool(var15 * var35, var17 * var35, var19 * var35));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     double minX = this.explosionX - (effective_explosion_size_vs_entities - 1.0D) * 4.0D;
/* 300 */     double maxX = this.explosionX + (effective_explosion_size_vs_entities + 1.0D) * 4.0D;
/* 301 */     double minY = this.explosionY - (effective_explosion_size_vs_entities - 1.0D) * 4.0D;
/* 302 */     double maxY = this.explosionY + (effective_explosion_size_vs_entities + 1.0D) * 4.0D;
/* 303 */     double minZ = this.explosionZ - (effective_explosion_size_vs_entities - 1.0D) * 4.0D;
/* 304 */     double maxZ = this.explosionZ + (effective_explosion_size_vs_entities + 1.0D) * 4.0D;
/*     */     
/* 306 */     List nearby_livestock = this.worldObj.getEntitiesWithinAABB(EntityLivestock.class, AxisAlignedBB.getAABBPool().getAABB(minX, minY, minZ, maxX, maxY, maxZ));
/*     */     
/* 308 */     Iterator<EntityLivestock> i = nearby_livestock.iterator();
/*     */     
/* 310 */     while (i.hasNext()) {
/*     */       
/* 312 */       EntityLivestock livestock = i.next();
/*     */       
/* 314 */       if (!livestock.isDead) {
/* 315 */         livestock.spooked_until = this.worldObj.getTotalWorldTime() + 400L + this.worldObj.rand.nextInt(400);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExplosionB(boolean par1) {
/* 326 */     this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/*     */ 
/*     */     
/* 329 */     if (this.explosion_size_vs_blocks >= 2.0F && this.isSmoking) {
/*     */ 
/*     */       
/* 332 */       this.worldObj.spawnParticle(EnumParticle.hugeexplosion, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 337 */       this.worldObj.spawnParticle(EnumParticle.largeexplode, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 347 */     if (this.isSmoking) {
/*     */       
/* 349 */       Iterator<ChunkPosition> var2 = this.affectedBlockPositions.iterator();
/*     */       
/* 351 */       while (var2.hasNext()) {
/*     */         
/* 353 */         ChunkPosition var3 = var2.next();
/* 354 */         int var4 = var3.x;
/* 355 */         int var5 = var3.y;
/* 356 */         int var6 = var3.z;
/* 357 */         int var7 = this.worldObj.getBlockId(var4, var5, var6);
/*     */         
/* 359 */         if (par1) {
/*     */           
/* 361 */           double var8 = (var4 + this.worldObj.rand.nextFloat());
/* 362 */           double var10 = (var5 + this.worldObj.rand.nextFloat());
/* 363 */           double var12 = (var6 + this.worldObj.rand.nextFloat());
/* 364 */           double var14 = var8 - this.explosionX;
/* 365 */           double var16 = var10 - this.explosionY;
/* 366 */           double var18 = var12 - this.explosionZ;
/* 367 */           double var20 = MathHelper.sqrt_double(var14 * var14 + var16 * var16 + var18 * var18);
/* 368 */           var14 /= var20;
/* 369 */           var16 /= var20;
/* 370 */           var18 /= var20;
/*     */           
/* 372 */           double var22 = 0.5D / (var20 / this.explosion_size_vs_blocks + 0.1D);
/* 373 */           var22 *= (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
/* 374 */           var14 *= var22;
/* 375 */           var16 *= var22;
/* 376 */           var18 *= var22;
/*     */ 
/*     */ 
/*     */           
/* 380 */           this.worldObj.spawnParticle(EnumParticle.explode, (var8 + this.explosionX * 1.0D) / 2.0D, (var10 + this.explosionY * 1.0D) / 2.0D, (var12 + this.explosionZ * 1.0D) / 2.0D, var14, var16, var18);
/* 381 */           this.worldObj.spawnParticle(EnumParticle.smoke, var8, var10, var12, var14, var16, var18);
/*     */           
/* 383 */           this.worldObj.setBlock(var4, var5, var6, 0, 0, 2);
/*     */         } 
/*     */ 
/*     */         
/* 387 */         if (var7 > 0 && !this.worldObj.isRemote) {
/*     */           
/* 389 */           Block var25 = Block.blocksList[var7];
/*     */           
/* 391 */           BlockBreakInfo info = (new BlockBreakInfo(this.worldObj, var4, var5, var6)).setExploded(this);
/* 392 */           var25.onBlockAboutToBeBroken(info);
/*     */           
/* 394 */           if (var25.canDropFromExplosion(this))
/*     */           {
/*     */             
/* 397 */             var25.dropBlockAsEntityItem(info);
/*     */           }
/*     */           
/* 400 */           this.worldObj.setBlock(var4, var5, var6, 0, 0, 3);
/* 401 */           var25.onBlockDestroyedByExplosion(this.worldObj, var4, var5, var6, this);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 406 */     if (this.isFlaming) {
/*     */       
/* 408 */       Iterator<ChunkPosition> var2 = this.affectedBlockPositions.iterator();
/*     */       
/* 410 */       while (var2.hasNext()) {
/*     */         
/* 412 */         ChunkPosition var3 = var2.next();
/* 413 */         int var4 = var3.x;
/* 414 */         int var5 = var3.y;
/* 415 */         int var6 = var3.z;
/* 416 */         int var7 = this.worldObj.getBlockId(var4, var5, var6);
/* 417 */         int var24 = this.worldObj.getBlockId(var4, var5 - 1, var6);
/*     */         
/* 419 */         if (var7 == 0 && Block.opaqueCubeLookup[var24] && this.explosionRNG.nextInt(3) == 0)
/*     */         {
/* 421 */           this.worldObj.setBlock(var4, var5, var6, Block.fire.blockID);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_77277_b() {
/* 429 */     return this.field_77288_k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase getExplosivePlacedBy() {
/* 437 */     return (this.exploder == null) ? null : ((this.exploder instanceof EntityTNTPrimed) ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : ((this.exploder instanceof EntityLivingBase) ? (EntityLivingBase)this.exploder : null));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Explosion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */