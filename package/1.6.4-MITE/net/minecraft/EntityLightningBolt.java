/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class EntityLightningBolt
/*     */   extends EntityWeatherEffect
/*     */ {
/*     */   private int lightningState;
/*     */   public long boltVertex;
/*     */   private int boltLivingTime;
/*     */   
/*     */   public EntityLightningBolt(World par1World, double par2, double par4, double par6) {
/*  24 */     super(par1World);
/*  25 */     setLocationAndAngles(par2, par4, par6, 0.0F, 0.0F);
/*  26 */     this.lightningState = 2;
/*  27 */     this.boltVertex = this.rand.nextLong();
/*  28 */     this.boltLivingTime = this.rand.nextInt(3) + 1;
/*     */     
/*  30 */     if (!par1World.isRemote && par1World.getGameRules().getGameRuleBooleanValue("doFireTick") && par1World.difficultySetting >= 2 && par1World.doChunksNearChunkExist(MathHelper.floor_double(par2), MathHelper.floor_double(par4), MathHelper.floor_double(par6), 10)) {
/*     */       
/*  32 */       int var8 = MathHelper.floor_double(par2);
/*  33 */       int var9 = MathHelper.floor_double(par4);
/*  34 */       int var10 = MathHelper.floor_double(par6);
/*     */ 
/*     */       
/*  37 */       if (par1World.getBlockId(var8, var9, var10) == 0 && Block.fire.isLegalAt(par1World, var8, var9, var10, 0))
/*     */       {
/*  39 */         par1World.setBlock(var8, var9, var10, Block.fire.blockID);
/*     */       }
/*     */       
/*  42 */       for (var8 = 0; var8 < 4; var8++) {
/*     */         
/*  44 */         var9 = MathHelper.floor_double(par2) + this.rand.nextInt(3) - 1;
/*  45 */         var10 = MathHelper.floor_double(par4) + this.rand.nextInt(3) - 1;
/*  46 */         int var11 = MathHelper.floor_double(par6) + this.rand.nextInt(3) - 1;
/*     */ 
/*     */         
/*  49 */         if (par1World.getBlockId(var9, var10, var11) == 0 && Block.fire.isLegalAt(par1World, var9, var10, var11, 0))
/*     */         {
/*  51 */           par1World.setBlock(var9, var10, var11, Block.fire.blockID);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  62 */     super.onUpdate();
/*     */     
/*  64 */     if (this.lightningState == 2) {
/*     */       
/*  66 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
/*  67 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
/*     */     } 
/*     */     
/*  70 */     this.lightningState--;
/*     */     
/*  72 */     if (this.lightningState < 0)
/*     */     {
/*  74 */       if (this.boltLivingTime == 0) {
/*     */         
/*  76 */         setDead();
/*     */       }
/*  78 */       else if (this.lightningState < -this.rand.nextInt(10)) {
/*     */         
/*  80 */         this.boltLivingTime--;
/*  81 */         this.lightningState = 1;
/*  82 */         this.boltVertex = this.rand.nextLong();
/*     */         
/*  84 */         if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("doFireTick") && this.worldObj.doChunksNearChunkExist(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 10)) {
/*     */           
/*  86 */           int var1 = MathHelper.floor_double(this.posX);
/*  87 */           int var2 = MathHelper.floor_double(this.posY);
/*  88 */           int var3 = MathHelper.floor_double(this.posZ);
/*     */ 
/*     */           
/*  91 */           if (this.worldObj.getBlockId(var1, var2, var3) == 0 && Block.fire.isLegalAt(this.worldObj, var1, var2, var3, 0))
/*     */           {
/*  93 */             this.worldObj.setBlock(var1, var2, var3, Block.fire.blockID);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  99 */     if (this.lightningState >= 0)
/*     */     {
/* 101 */       if (this.worldObj.isRemote) {
/*     */         
/* 103 */         this.worldObj.lastLightningBolt = 2;
/*     */       }
/*     */       else {
/*     */         
/* 107 */         double var6 = 3.0D;
/* 108 */         List<Entity> var7 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB(this.posX - var6, this.posY - var6, this.posZ - var6, this.posX + var6, this.posY + 6.0D + var6, this.posZ + var6));
/*     */         
/* 110 */         for (int var4 = 0; var4 < var7.size(); var4++) {
/*     */           
/* 112 */           Entity var5 = var7.get(var4);
/* 113 */           var5.onStruckByLightning(this);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderVec3D(Vec3 par1Vec3) {
/* 136 */     return (this.lightningState >= 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLightningBolt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */