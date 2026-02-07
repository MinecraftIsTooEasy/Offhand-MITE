/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityMinecartFurnace
/*     */   extends EntityMinecart
/*     */ {
/*     */   private int fuel;
/*     */   public double pushX;
/*     */   public double pushZ;
/*     */   
/*     */   public EntityMinecartFurnace(World par1World) {
/*  11 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartFurnace(World par1World, double par2, double par4, double par6) {
/*  16 */     super(par1World, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinecartType() {
/*  21 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  26 */     super.entityInit();
/*  27 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  35 */     super.onUpdate();
/*     */     
/*  37 */     if (this.fuel > 0) {
/*     */       
/*  39 */       this.fuel--;
/*     */       
/*  41 */       if (onServer())
/*     */       {
/*  43 */         if (this.fuel == 0 || this.ticksExisted == 20 || this.ticksExisted % 200 == 0) {
/*  44 */           sendPacketToAllPlayersTrackingEntity((new Packet85SimpleSignal(EnumSignal.update_minecart_fuel)).setInteger(this.fuel).setEntityID(this));
/*     */         }
/*     */       }
/*     */     } 
/*  48 */     if (this.fuel <= 0)
/*     */     {
/*  50 */       this.pushX = this.pushZ = 0.0D;
/*     */     }
/*     */     
/*  53 */     setMinecartPowered((this.fuel > 0));
/*     */     
/*  55 */     if (isMinecartPowered() && this.rand.nextInt(4) == 0)
/*     */     {
/*     */       
/*  58 */       this.worldObj.spawnParticle(EnumParticle.largesmoke, this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource par1DamageSource) {
/*  64 */     super.killMinecart(par1DamageSource);
/*     */     
/*  66 */     if (!par1DamageSource.isExplosion())
/*     */     {
/*  68 */       dropItemStack(new ItemStack(Block.furnaceIdle, 1), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateOnTrack(int par1, int par2, int par3, double par4, double par6, int par8, int par9) {
/*  74 */     super.updateOnTrack(par1, par2, par3, par4, par6, par8, par9);
/*  75 */     double var10 = this.pushX * this.pushX + this.pushZ * this.pushZ;
/*     */     
/*  77 */     if (var10 > 1.0E-4D && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001D) {
/*     */       
/*  79 */       var10 = MathHelper.sqrt_double(var10);
/*  80 */       this.pushX /= var10;
/*  81 */       this.pushZ /= var10;
/*     */       
/*  83 */       if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0D) {
/*     */         
/*  85 */         this.pushX = 0.0D;
/*  86 */         this.pushZ = 0.0D;
/*     */       }
/*     */       else {
/*     */         
/*  90 */         this.pushX = this.motionX;
/*  91 */         this.pushZ = this.motionZ;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyDrag() {
/*  98 */     double var1 = this.pushX * this.pushX + this.pushZ * this.pushZ;
/*     */     
/* 100 */     if (var1 > 1.0E-4D) {
/*     */       
/* 102 */       var1 = MathHelper.sqrt_double(var1);
/* 103 */       this.pushX /= var1;
/* 104 */       this.pushZ /= var1;
/* 105 */       double var3 = 0.05D;
/* 106 */       this.motionX *= 0.800000011920929D;
/* 107 */       this.motionY *= 0.0D;
/* 108 */       this.motionZ *= 0.800000011920929D;
/* 109 */       this.motionX += this.pushX * var3;
/* 110 */       this.motionZ += this.pushZ * var3;
/*     */     }
/*     */     else {
/*     */       
/* 114 */       this.motionX *= 0.9800000190734863D;
/* 115 */       this.motionY *= 0.0D;
/* 116 */       this.motionZ *= 0.9800000190734863D;
/*     */     } 
/*     */     
/* 119 */     super.applyDrag();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 146 */     if (player.getHeldItem() == Item.coal) {
/*     */       
/* 148 */       this.fuel += 3600;
/*     */       
/* 150 */       if (player.onServer() && !player.inCreativeMode()) {
/* 151 */         player.convertOneOfHeldItem((ItemStack)null);
/*     */       }
/*     */     } 
/* 154 */     this.pushX = this.posX - player.posX;
/* 155 */     this.pushZ = this.posZ - player.posZ;
/*     */     
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 165 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 166 */     par1NBTTagCompound.setDouble("PushX", this.pushX);
/* 167 */     par1NBTTagCompound.setDouble("PushZ", this.pushZ);
/* 168 */     par1NBTTagCompound.setShort("Fuel", (short)this.fuel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 176 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 177 */     this.pushX = par1NBTTagCompound.getDouble("PushX");
/* 178 */     this.pushZ = par1NBTTagCompound.getDouble("PushZ");
/* 179 */     this.fuel = par1NBTTagCompound.getShort("Fuel");
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isMinecartPowered() {
/* 184 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setMinecartPowered(boolean par1) {
/* 189 */     if (par1) {
/*     */       
/* 191 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(this.dataWatcher.getWatchableObjectByte(16) | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 195 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(this.dataWatcher.getWatchableObjectByte(16) & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getDefaultDisplayTile() {
/* 201 */     return Block.furnaceBurning;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultDisplayTileData() {
/* 206 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 211 */     return Item.minecartPowered;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFuel(int fuel) {
/* 216 */     this.fuel = fuel;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */