/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityMinecartTNT
/*     */   extends EntityMinecart {
/*   5 */   private int minecartTNTFuse = -1;
/*     */ 
/*     */   
/*     */   public EntityMinecartTNT(World par1World) {
/*   9 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartTNT(World par1World, double par2, double par4, double par6) {
/*  14 */     super(par1World, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinecartType() {
/*  19 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getDefaultDisplayTile() {
/*  24 */     return Block.tnt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  32 */     super.onUpdate();
/*     */     
/*  34 */     if (this.minecartTNTFuse > 0) {
/*     */       
/*  36 */       this.minecartTNTFuse--;
/*     */       
/*  38 */       this.worldObj.spawnParticle(EnumParticle.smoke, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/*  40 */     else if (this.minecartTNTFuse == 0) {
/*     */       
/*  42 */       explodeCart(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */     } 
/*     */     
/*  45 */     if (this.isCollidedHorizontally) {
/*     */       
/*  47 */       double var1 = this.motionX * this.motionX + this.motionZ * this.motionZ;
/*     */       
/*  49 */       if (var1 >= 0.009999999776482582D)
/*     */       {
/*  51 */         explodeCart(var1);
/*     */       }
/*     */     } 
/*     */     
/*  55 */     if (isBurning()) {
/*  56 */       ignite();
/*     */     }
/*     */   }
/*     */   
/*     */   public void killMinecart(DamageSource par1DamageSource) {
/*  61 */     super.killMinecart(par1DamageSource);
/*  62 */     double var2 = this.motionX * this.motionX + this.motionZ * this.motionZ;
/*     */     
/*  64 */     if (!par1DamageSource.isExplosion())
/*     */     {
/*  66 */       dropItemStack(new ItemStack(Block.tnt, 1), 0.0F);
/*     */     }
/*     */ 
/*     */     
/*  70 */     if (par1DamageSource.hasFireAspect() || par1DamageSource.isExplosion() || var2 >= 0.009999999776482582D)
/*     */     {
/*  72 */       explodeCart(var2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void explodeCart(double par1) {
/*  81 */     if (!this.worldObj.isRemote) {
/*     */       
/*  83 */       double var3 = Math.sqrt(par1);
/*     */       
/*  85 */       if (var3 > 5.0D)
/*     */       {
/*  87 */         var3 = 5.0D;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  92 */       float explosion_size = (float)(4.0D + this.rand.nextDouble() * 1.5D * var3);
/*  93 */       this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, explosion_size, explosion_size, true);
/*     */       
/*  95 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {
/* 104 */     if (par1 >= 3.0F) {
/*     */       
/* 106 */       float var2 = par1 / 10.0F;
/* 107 */       explodeCart((var2 * var2));
/*     */     } 
/*     */     
/* 110 */     super.fall(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onActivatorRailPass(int par1, int par2, int par3, boolean par4) {
/* 118 */     if (par4 && this.minecartTNTFuse < 0)
/*     */     {
/* 120 */       ignite();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 128 */     if (par1 == EnumEntityState.tnt_ignite_or_eating_grass) {
/*     */       
/* 130 */       ignite();
/*     */     }
/*     */     else {
/*     */       
/* 134 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignite() {
/* 143 */     if (this.minecartTNTFuse >= 0) {
/*     */       return;
/*     */     }
/* 146 */     this.minecartTNTFuse = 80;
/*     */     
/* 148 */     if (!this.worldObj.isRemote) {
/*     */ 
/*     */       
/* 151 */       this.worldObj.setEntityState(this, EnumEntityState.tnt_ignite_or_eating_grass);
/* 152 */       this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_94104_d() {
/* 158 */     return this.minecartTNTFuse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIgnited() {
/* 166 */     return (this.minecartTNTFuse > -1);
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
/*     */   public boolean shouldExplodeBlock(Explosion par1Explosion, World par2World, int par3, int par4, int par5, int par6, float par7) {
/* 180 */     return (isIgnited() && (BlockRailBase.isRailBlock(par6) || BlockRailBase.isRailBlockAt(par2World, par3, par4 + 1, par5))) ? false : super.shouldExplodeBlock(par1Explosion, par2World, par3, par4, par5, par6, par7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 188 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 190 */     if (par1NBTTagCompound.hasKey("TNTFuse"))
/*     */     {
/* 192 */       this.minecartTNTFuse = par1NBTTagCompound.getInteger("TNTFuse");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 201 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 202 */     par1NBTTagCompound.setInteger("TNTFuse", this.minecartTNTFuse);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 207 */     return Item.minecartTnt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void spentTickInFire() {
/* 212 */     ignite();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartTNT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */