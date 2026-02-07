/*     */ package net.minecraft;
/*     */ 
/*     */ public abstract class EntityMinecartContainer
/*     */   extends EntityMinecart implements IInventory {
/*   5 */   private ItemStack[] minecartContainerItems = new ItemStack[36];
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean dropContentsWhenDead = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityMinecartContainer(World par1World) {
/*  15 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartContainer(World par1World, double par2, double par4, double par6) {
/*  20 */     super(par1World, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource par1DamageSource) {
/*  25 */     super.killMinecart(par1DamageSource);
/*     */     
/*  27 */     for (int var2 = 0; var2 < getSizeInventory(); var2++) {
/*     */       
/*  29 */       ItemStack var3 = getStackInSlot(var2);
/*     */       
/*  31 */       if (var3 != null) {
/*     */         
/*  33 */         float var4 = this.rand.nextFloat() * 0.8F + 0.1F;
/*  34 */         float var5 = this.rand.nextFloat() * 0.8F + 0.1F;
/*  35 */         float var6 = this.rand.nextFloat() * 0.8F + 0.1F;
/*     */         
/*  37 */         while (var3.stackSize > 0) {
/*     */           
/*  39 */           int var7 = this.rand.nextInt(21) + 10;
/*     */           
/*  41 */           if (var7 > var3.stackSize)
/*     */           {
/*  43 */             var7 = var3.stackSize;
/*     */           }
/*     */           
/*  46 */           var3.stackSize -= var7;
/*     */           
/*  48 */           EntityItem var8 = new EntityItem(this.worldObj, this.posX + var4, this.posY + var5, this.posZ + var6, new ItemStack(var3.itemID, var7, var3.getItemSubtype()));
/*     */           
/*  50 */           if (var3.isItemDamaged()) {
/*  51 */             var8.getEntityItem().setItemDamage(var3.getItemDamage());
/*     */           }
/*  53 */           float var9 = 0.05F;
/*  54 */           var8.motionX = ((float)this.rand.nextGaussian() * var9);
/*  55 */           var8.motionY = ((float)this.rand.nextGaussian() * var9 + 0.2F);
/*  56 */           var8.motionZ = ((float)this.rand.nextGaussian() * var9);
/*  57 */           this.worldObj.spawnEntityInWorld(var8);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  68 */     return this.minecartContainerItems[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  77 */     if (this.minecartContainerItems[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  81 */       if ((this.minecartContainerItems[par1]).stackSize <= par2) {
/*     */         
/*  83 */         ItemStack itemStack = this.minecartContainerItems[par1];
/*  84 */         this.minecartContainerItems[par1] = null;
/*  85 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  89 */       ItemStack var3 = this.minecartContainerItems[par1].splitStack(par2);
/*     */       
/*  91 */       if ((this.minecartContainerItems[par1]).stackSize == 0)
/*     */       {
/*  93 */         this.minecartContainerItems[par1] = null;
/*     */       }
/*     */       
/*  96 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/* 111 */     if (this.minecartContainerItems[par1] != null) {
/*     */       
/* 113 */       ItemStack var2 = this.minecartContainerItems[par1];
/* 114 */       this.minecartContainerItems[par1] = null;
/* 115 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 128 */     this.minecartContainerItems[par1] = par2ItemStack;
/*     */     
/* 130 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 132 */       par2ItemStack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 146 */     return this.isDead ? false : ((par1EntityPlayer.getDistanceSqToEntity(this) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openChest() {}
/*     */ 
/*     */   
/*     */   public void closeChest() {}
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomNameOrUnlocalized() {
/* 166 */     return hasCustomName() ? func_95999_t() : "container.minecart";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 175 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void travelToDimension(int par1) {
/* 183 */     this.dropContentsWhenDead = false;
/* 184 */     super.travelToDimension(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 192 */     if (this.dropContentsWhenDead)
/*     */     {
/* 194 */       for (int var1 = 0; var1 < getSizeInventory(); var1++) {
/*     */         
/* 196 */         ItemStack var2 = getStackInSlot(var1);
/*     */         
/* 198 */         if (var2 != null) {
/*     */           
/* 200 */           float var3 = this.rand.nextFloat() * 0.8F + 0.1F;
/* 201 */           float var4 = this.rand.nextFloat() * 0.8F + 0.1F;
/* 202 */           float var5 = this.rand.nextFloat() * 0.8F + 0.1F;
/*     */           
/* 204 */           while (var2.stackSize > 0) {
/*     */             
/* 206 */             int var6 = this.rand.nextInt(21) + 10;
/*     */             
/* 208 */             if (var6 > var2.stackSize)
/*     */             {
/* 210 */               var6 = var2.stackSize;
/*     */             }
/*     */             
/* 213 */             var2.stackSize -= var6;
/*     */             
/* 215 */             EntityItem var7 = new EntityItem(this.worldObj, this.posX + var3, this.posY + var4, this.posZ + var5, new ItemStack(var2.itemID, var6, var2.getItemSubtype()));
/*     */             
/* 217 */             if (var2.isItemDamaged()) {
/* 218 */               var7.getEntityItem().setItemDamage(var2.getItemDamage());
/*     */             }
/* 220 */             if (var2.hasTagCompound())
/*     */             {
/* 222 */               var7.getEntityItem().setTagCompound((NBTTagCompound)var2.getTagCompound().copy());
/*     */             }
/*     */             
/* 225 */             float var8 = 0.05F;
/* 226 */             var7.motionX = ((float)this.rand.nextGaussian() * var8);
/* 227 */             var7.motionY = ((float)this.rand.nextGaussian() * var8 + 0.2F);
/* 228 */             var7.motionZ = ((float)this.rand.nextGaussian() * var8);
/* 229 */             this.worldObj.spawnEntityInWorld(var7);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 235 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 243 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 244 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 246 */     for (int var3 = 0; var3 < this.minecartContainerItems.length; var3++) {
/*     */       
/* 248 */       if (this.minecartContainerItems[var3] != null) {
/*     */         
/* 250 */         NBTTagCompound var4 = new NBTTagCompound();
/* 251 */         var4.setByte("Slot", (byte)var3);
/* 252 */         this.minecartContainerItems[var3].writeToNBT(var4);
/* 253 */         var2.appendTag(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     par1NBTTagCompound.setTag("Items", var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 265 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 266 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
/* 267 */     this.minecartContainerItems = new ItemStack[getSizeInventory()];
/*     */     
/* 269 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 271 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 272 */       int var5 = var4.getByte("Slot") & 0xFF;
/*     */       
/* 274 */       if (var5 >= 0 && var5 < this.minecartContainerItems.length)
/*     */       {
/* 276 */         this.minecartContainerItems[var5] = ItemStack.loadItemStackFromNBT(var4);
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 296 */     if (player.onServer())
/*     */     {
/* 298 */       player.displayGUIChestForMinecart(this);
/*     */     }
/* 300 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyDrag() {
/* 305 */     int var1 = 15 - Container.calcRedstoneFromInventory(this);
/* 306 */     float var2 = 0.98F + var1 * 0.001F;
/* 307 */     this.motionX *= var2;
/* 308 */     this.motionY *= 0.0D;
/* 309 */     this.motionZ *= var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 315 */     ItemStack[] item_stacks = this.minecartContainerItems;
/*     */     
/* 317 */     for (int i = 0; i < item_stacks.length; i++)
/* 318 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMinecartContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */