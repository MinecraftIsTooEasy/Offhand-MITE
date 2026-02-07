/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityRainFX
/*     */   extends EntityFX
/*     */ {
/*     */   public EntityRainFX(World par1World, double par2, double par4, double par6) {
/*   7 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*   8 */     this.motionX *= 0.30000001192092896D;
/*   9 */     this.motionY = ((float)Math.random() * 0.2F + 0.1F);
/*  10 */     this.motionZ *= 0.30000001192092896D;
/*  11 */     this.particleRed = 1.0F;
/*  12 */     this.particleGreen = 1.0F;
/*  13 */     this.particleBlue = 1.0F;
/*  14 */     setParticleTextureIndex(19 + this.rand.nextInt(4));
/*  15 */     setSize(0.01F, 0.01F);
/*  16 */     this.particleGravity = 0.06F;
/*  17 */     this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  25 */     this.prevPosX = this.posX;
/*  26 */     this.prevPosY = this.posY;
/*  27 */     this.prevPosZ = this.posZ;
/*  28 */     this.motionY -= this.particleGravity;
/*  29 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  30 */     this.motionX *= 0.9800000190734863D;
/*  31 */     this.motionY *= 0.9800000190734863D;
/*  32 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/*  34 */     if (this.particleMaxAge-- <= 0)
/*     */     {
/*  36 */       setDead();
/*     */     }
/*     */     
/*  39 */     if (this.onGround) {
/*     */       
/*  41 */       if (Math.random() < 0.5D)
/*     */       {
/*  43 */         setDead();
/*     */       }
/*     */       
/*  46 */       this.motionX *= 0.699999988079071D;
/*  47 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
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
/*  64 */     if (this.motionY > 0.0D) {
/*     */       return;
/*     */     }
/*  67 */     int x = getBlockPosX();
/*  68 */     int y = getBlockPosY();
/*  69 */     int z = getBlockPosZ();
/*     */     
/*  71 */     int precipitation_height = this.worldObj.getPrecipitationHeight(x, z);
/*     */     
/*  73 */     if (MathHelper.floor_double(this.posY + this.motionY) < precipitation_height - 1) {
/*     */       
/*  75 */       setDead();
/*     */       
/*     */       return;
/*     */     } 
/*  79 */     int block_id = this.worldObj.getBlockId(x, y, z);
/*     */     
/*  81 */     if (block_id > 0) {
/*     */       
/*  83 */       Block block = Block.getBlock(block_id);
/*     */       
/*  85 */       if (block.blockMaterial.isSolid()) {
/*     */         
/*  87 */         if (block.isAlwaysStandardFormCube()) {
/*     */           
/*  89 */           setDead();
/*     */         }
/*     */         else {
/*     */           
/*  93 */           block.setBlockBoundsBasedOnStateAndNeighbors(this.worldObj, x, y, z);
/*     */           
/*  95 */           if (this.posY < block.maxY[Minecraft.getThreadIndex()]) {
/*  96 */             setDead();
/*     */           }
/*     */         } 
/*  99 */       } else if (block.blockMaterial.isLiquid()) {
/*     */         
/* 101 */         double var2 = ((y + 1) - BlockFluid.getFluidHeightPercent(this.worldObj.getBlockMetadata(x, y, z)));
/*     */         
/* 103 */         if (this.posY + this.motionY < var2)
/*     */         {
/* 105 */           setDead();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityRainFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */