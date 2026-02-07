/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFallingSand
/*     */   extends Entity
/*     */ {
/*     */   public int blockID;
/*     */   public int metadata;
/*     */   public int item_damage;
/*     */   public String custom_name;
/*     */   public int fallTime;
/*     */   public boolean shouldDropItem;
/*     */   private boolean isBreakingAnvil;
/*     */   private boolean isAnvil;
/*     */   private int fallHurtMax;
/*     */   private float fallHurtAmount;
/*     */   public NBTTagCompound fallingBlockTileEntityData;
/*     */   
/*     */   public EntityFallingSand(World par1World) {
/*  30 */     super(par1World);
/*  31 */     this.shouldDropItem = true;
/*  32 */     this.fallHurtMax = 40;
/*  33 */     this.fallHurtAmount = 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFallingSand(World par1World, double par2, double par4, double par6, int par8) {
/*  38 */     this(par1World, par2, par4, par6, par8, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFallingSand(World par1World, double par2, double par4, double par6, int par8, int par9) {
/*  43 */     super(par1World);
/*  44 */     this.shouldDropItem = true;
/*  45 */     this.fallHurtMax = 40;
/*  46 */     this.fallHurtAmount = 2.0F;
/*  47 */     this.blockID = par8;
/*  48 */     this.metadata = par9;
/*  49 */     this.preventEntitySpawning = true;
/*  50 */     setSize(0.98F, 0.98F);
/*  51 */     this.yOffset = this.height / 2.0F;
/*  52 */     setPosition(par2, par4, par6);
/*  53 */     this.motionX = 0.0D;
/*  54 */     this.motionY = 0.0D;
/*  55 */     this.motionZ = 0.0D;
/*  56 */     this.prevPosX = par2;
/*  57 */     this.prevPosY = par4;
/*  58 */     this.prevPosZ = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  77 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canDislodgeOrCrushBlockAt(World world, Block falling_block, int falling_block_metadata, int x, int y, int z) {
/*  83 */     Block block = world.getBlock(x, y, z);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     return block.isDislodgedOrCrushedByFallingBlock(world.getBlockMetadata(x, y, z), falling_block, falling_block_metadata);
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkForBlockOccupyingSameSpace(int x, int y, int z) {
/*  95 */     Block block_occupying_same_space = this.worldObj.getBlock(x, y, z);
/*     */     
/*  97 */     if (block_occupying_same_space == null || block_occupying_same_space.blockMaterial.isLiquid()) {
/*     */       return;
/*     */     }
/* 100 */     if (block_occupying_same_space != null && canDislodgeOrCrushBlockAt(this.worldObj, Block.getBlock(this.blockID), this.metadata, x, y, z)) {
/*     */       
/* 102 */       BlockBreakInfo info = new BlockBreakInfo(this.worldObj, x, y, z);
/*     */       
/* 104 */       if (Block.sand.canFallDownTo(this.worldObj, x, y - 1, z, this.metadata)) {
/*     */ 
/*     */         
/* 107 */         info.setCollidedWith(this);
/*     */       }
/*     */       else {
/*     */         
/* 111 */         info.setCrushed(Block.getBlock(this.blockID));
/*     */       } 
/*     */       
/* 114 */       if (block_occupying_same_space.dropBlockAsEntityItem(info) > 0 && info.wasCollidedWithEntity()) {
/* 115 */         playSound("random.pop", 0.3F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */       }
/* 117 */       this.worldObj.setBlockToAir(x, y, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 126 */     if (this.blockID == 0) {
/*     */       
/* 128 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/* 132 */       this.prevPosX = this.posX;
/* 133 */       this.prevPosY = this.posY;
/* 134 */       this.prevPosZ = this.posZ;
/* 135 */       this.fallTime++;
/* 136 */       this.motionY -= 0.03999999910593033D;
/* 137 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 138 */       this.motionX *= 0.9800000190734863D;
/* 139 */       this.motionY *= 0.9800000190734863D;
/* 140 */       this.motionZ *= 0.9800000190734863D;
/*     */       
/* 142 */       Material material = this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*     */       
/* 144 */       if (material == Material.water) {
/*     */         
/* 146 */         if (!this.inWater && !this.firstUpdate && this.motionY < -0.20000000298023224D && !this.worldObj.isRemote) {
/* 147 */           entityFX(EnumEntityFX.splash);
/*     */         }
/* 149 */         this.inWater = true;
/*     */       } 
/*     */       
/* 152 */       if (!this.worldObj.isRemote) {
/*     */         
/* 154 */         int var1 = MathHelper.floor_double(this.posX);
/* 155 */         int var2 = MathHelper.floor_double(this.posY);
/* 156 */         int var3 = MathHelper.floor_double(this.posZ);
/*     */         
/* 158 */         if (this.fallTime == 1) {
/*     */           
/* 160 */           if (this.worldObj.getBlockId(var1, var2, var3) != this.blockID) {
/*     */             
/* 162 */             setDead();
/*     */             
/*     */             return;
/*     */           } 
/* 166 */           this.worldObj.setBlockToAir(var1, var2, var3);
/*     */         } 
/*     */         
/* 169 */         checkForBlockOccupyingSameSpace(var1, var2, var3);
/*     */         
/* 171 */         if (this.onGround && this.worldObj.getBlockMaterial(var1, var2 - 1, var3) == Material.glass) {
/*     */           
/* 173 */           this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, var1, var2 - 1, var3)).setCollidedWith(this), true);
/*     */         }
/* 175 */         else if (this.onGround) {
/*     */           
/* 177 */           this.motionX *= 0.699999988079071D;
/* 178 */           this.motionZ *= 0.699999988079071D;
/* 179 */           this.motionY *= -0.5D;
/*     */           
/* 181 */           var2 = MathHelper.floor_double(this.posY - this.yOffset);
/*     */           
/* 183 */           checkForBlockOccupyingSameSpace(var1, var2, var3);
/*     */           
/* 185 */           if (this.worldObj.getBlockId(var1, var2, var3) != Block.pistonMoving.blockID) {
/*     */             
/* 187 */             setDead();
/*     */             
/* 189 */             Block block = Block.getBlock(this.blockID);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 198 */             if (!this.isBreakingAnvil && !Block.sand.canFallDownTo(this.worldObj, var1, var2 - 1, var3, this.metadata) && (this.worldObj.isAirBlock(var1, var2, var3) || canDislodgeOrCrushBlockAt(this.worldObj, block, this.metadata, var1, var2, var3)) && this.worldObj.setBlock(var1, var2, var3, this.blockID, this.metadata, 3)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 205 */               if (block != null && !(block instanceof BlockAnvil)) {
/* 206 */                 this.worldObj.playSoundEffect((var1 + 0.5F), (var2 + 0.5F), (var3 + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 8.0F, block.stepSound.getPitch() * 0.8F);
/*     */               }
/* 208 */               if (Block.blocksList[this.blockID] instanceof BlockFalling)
/*     */               {
/*     */                 
/* 211 */                 ((BlockFalling)Block.blocksList[this.blockID]).onFinishFalling(this.worldObj, var1, var2, var3, this.metadata, this);
/*     */               }
/*     */               
/* 214 */               Block block_below = this.worldObj.getBlock(var1, var2 - 1, var3);
/*     */               
/* 216 */               if (block_below != null) {
/* 217 */                 block_below.onEntityWalking(this.worldObj, var1, var2 - 1, var3, this);
/*     */               }
/* 219 */               if (this.fallingBlockTileEntityData != null && Block.blocksList[this.blockID] instanceof ITileEntityProvider) {
/*     */                 
/* 221 */                 TileEntity var4 = this.worldObj.getBlockTileEntity(var1, var2, var3);
/*     */                 
/* 223 */                 if (var4 != null)
/*     */                 {
/* 225 */                   NBTTagCompound var5 = new NBTTagCompound();
/* 226 */                   var4.writeToNBT(var5);
/* 227 */                   Iterator<NBTBase> var6 = this.fallingBlockTileEntityData.getTags().iterator();
/*     */                   
/* 229 */                   while (var6.hasNext()) {
/*     */                     
/* 231 */                     NBTBase var7 = var6.next();
/*     */                     
/* 233 */                     if (!var7.getName().equals("x") && !var7.getName().equals("y") && !var7.getName().equals("z"))
/*     */                     {
/* 235 */                       var5.setTag(var7.getName(), var7.copy());
/*     */                     }
/*     */                   } 
/*     */                   
/* 239 */                   var4.readFromNBT(var5);
/* 240 */                   var4.onInventoryChanged();
/*     */                 }
/*     */               
/*     */               } 
/* 244 */             } else if (this.shouldDropItem && !this.isBreakingAnvil) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 249 */               ItemStack item_stack = new ItemStack(this.blockID, 1, Block.blocksList[this.blockID].getItemSubtype(this.metadata));
/*     */               
/* 251 */               if (this.item_damage != 0) {
/* 252 */                 item_stack.setItemDamage(this.item_damage);
/*     */               }
/* 254 */               if (this.custom_name != null) {
/* 255 */                 item_stack.setItemName(this.custom_name);
/*     */               }
/* 257 */               dropItemStack(item_stack, 0.0F);
/*     */             }
/*     */           
/*     */           } 
/* 261 */         } else if ((this.fallTime > 100 && !this.worldObj.isRemote && (var2 < 1 || var2 > 256)) || this.fallTime > 600) {
/*     */           
/* 263 */           if (this.shouldDropItem) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 268 */             ItemStack item_stack = new ItemStack(this.blockID, 1, Block.blocksList[this.blockID].getItemSubtype(this.metadata));
/*     */             
/* 270 */             if (this.item_damage != 0) {
/* 271 */               item_stack.setItemDamage(this.item_damage);
/*     */             }
/* 273 */             if (this.custom_name != null) {
/* 274 */               item_stack.setItemName(this.custom_name);
/*     */             }
/* 276 */             dropItemStack(item_stack, 0.0F);
/*     */           } 
/*     */           
/* 279 */           setDead();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 284 */     this.firstUpdate = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {
/* 292 */     if (this.isAnvil) {
/*     */       
/* 294 */       int var2 = MathHelper.ceiling_float_int(par1 - 1.0F);
/*     */       
/* 296 */       if (var2 > 0) {
/*     */         
/* 298 */         ArrayList var3 = new ArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox));
/*     */ 
/*     */         
/* 301 */         Block block = Block.blocksList[this.blockID];
/* 302 */         DamageSource var4 = (block instanceof BlockAnvil) ? DamageSource.anvil : DamageSource.fallingBlock;
/* 303 */         Iterator<Entity> var5 = var3.iterator();
/*     */         
/* 305 */         while (var5.hasNext()) {
/*     */           
/* 307 */           Entity var6 = var5.next();
/*     */           
/* 309 */           var6.attackEntityFrom(new Damage(var4, Math.min(MathHelper.floor_float(var2 * this.fallHurtAmount), this.fallHurtMax)));
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 337 */     par1NBTTagCompound.setByte("Tile", (byte)this.blockID);
/* 338 */     par1NBTTagCompound.setInteger("TileID", this.blockID);
/* 339 */     par1NBTTagCompound.setByte("Data", (byte)this.metadata);
/* 340 */     par1NBTTagCompound.setByte("Time", (byte)this.fallTime);
/* 341 */     par1NBTTagCompound.setBoolean("DropItem", this.shouldDropItem);
/* 342 */     par1NBTTagCompound.setBoolean("HurtEntities", this.isAnvil);
/* 343 */     par1NBTTagCompound.setFloat("FallHurtAmount", this.fallHurtAmount);
/* 344 */     par1NBTTagCompound.setInteger("FallHurtMax", this.fallHurtMax);
/*     */     
/* 346 */     if (this.isAnvil) {
/* 347 */       par1NBTTagCompound.setInteger("item_damage", this.item_damage);
/*     */     }
/* 349 */     if (this.custom_name != null) {
/* 350 */       par1NBTTagCompound.setString("custom_name", this.custom_name);
/*     */     }
/* 352 */     if (this.fallingBlockTileEntityData != null)
/*     */     {
/* 354 */       par1NBTTagCompound.setCompoundTag("TileEntityData", this.fallingBlockTileEntityData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 363 */     if (par1NBTTagCompound.hasKey("TileID")) {
/*     */       
/* 365 */       this.blockID = par1NBTTagCompound.getInteger("TileID");
/*     */     }
/*     */     else {
/*     */       
/* 369 */       this.blockID = par1NBTTagCompound.getByte("Tile") & 0xFF;
/*     */     } 
/*     */     
/* 372 */     this.metadata = par1NBTTagCompound.getByte("Data") & 0xFF;
/* 373 */     this.fallTime = par1NBTTagCompound.getByte("Time") & 0xFF;
/*     */     
/* 375 */     if (par1NBTTagCompound.hasKey("HurtEntities")) {
/*     */       
/* 377 */       this.isAnvil = par1NBTTagCompound.getBoolean("HurtEntities");
/* 378 */       this.fallHurtAmount = par1NBTTagCompound.getFloat("FallHurtAmount");
/* 379 */       this.fallHurtMax = par1NBTTagCompound.getInteger("FallHurtMax");
/*     */     
/*     */     }
/* 382 */     else if (Block.blocksList[this.blockID] instanceof BlockAnvil) {
/*     */       
/* 384 */       this.isAnvil = true;
/*     */     } 
/*     */     
/* 387 */     if (this.isAnvil) {
/* 388 */       this.item_damage = par1NBTTagCompound.getInteger("item_damage");
/*     */     }
/* 390 */     if (par1NBTTagCompound.hasKey("custom_name")) {
/* 391 */       this.custom_name = par1NBTTagCompound.getString("custom_name");
/*     */     }
/* 393 */     if (par1NBTTagCompound.hasKey("DropItem"))
/*     */     {
/* 395 */       this.shouldDropItem = par1NBTTagCompound.getBoolean("DropItem");
/*     */     }
/*     */     
/* 398 */     if (par1NBTTagCompound.hasKey("TileEntityData"))
/*     */     {
/* 400 */       this.fallingBlockTileEntityData = par1NBTTagCompound.getCompoundTag("TileEntityData");
/*     */     }
/*     */     
/* 403 */     if (this.blockID == 0)
/*     */     {
/* 405 */       this.blockID = Block.sand.blockID;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 411 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsAnvil(boolean par1) {
/* 421 */     this.isAnvil = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRenderOnFire() {
/* 429 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntityCrashInfo(CrashReportCategory par1CrashReportCategory) {
/* 434 */     super.addEntityCrashInfo(par1CrashReportCategory);
/* 435 */     par1CrashReportCategory.addCrashSection("Immitating block ID", Integer.valueOf(this.blockID));
/* 436 */     par1CrashReportCategory.addCrashSection("Immitating block data", Integer.valueOf(this.metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 441 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 446 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 451 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFallingSand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */