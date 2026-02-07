/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityItemFrame
/*     */   extends EntityHanging
/*     */ {
/*   6 */   private float itemDropChance = 1.0F;
/*     */ 
/*     */   
/*     */   public EntityItemFrame(World par1World) {
/*  10 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItemFrame(World par1World, int par2, int par3, int par4, int par5) {
/*  15 */     super(par1World, par2, par3, par4, par5);
/*  16 */     setDirection(par5);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  21 */     getDataWatcher().addObjectByDataType(2, 5);
/*  22 */     getDataWatcher().addObject(3, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidthPixels() {
/*  27 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeightPixels() {
/*  32 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double par1) {
/*  41 */     double var3 = 16.0D;
/*  42 */     var3 *= 64.0D * this.renderDistanceWeight;
/*  43 */     return (par1 < var3 * var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBroken(Entity par1Entity) {
/*  51 */     ItemStack var2 = getDisplayedItem();
/*     */     
/*  53 */     if (par1Entity instanceof EntityPlayer) {
/*     */       
/*  55 */       EntityPlayer var3 = (EntityPlayer)par1Entity;
/*     */       
/*  57 */       if (var3.capabilities.isCreativeMode) {
/*     */         
/*  59 */         removeFrameFromMap(var2);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  64 */     dropItemStack(new ItemStack(Item.itemFrame), 0.0F);
/*     */     
/*  66 */     if (var2 != null && this.rand.nextFloat() < this.itemDropChance) {
/*     */       
/*  68 */       var2 = var2.copy();
/*  69 */       removeFrameFromMap(var2);
/*  70 */       dropItemStack(var2, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeFrameFromMap(ItemStack par1ItemStack) {
/*  79 */     if (par1ItemStack != null) {
/*     */       
/*  81 */       if (par1ItemStack.itemID == Item.map.itemID) {
/*     */         
/*  83 */         MapData var2 = ((ItemMap)par1ItemStack.getItem()).getMapData(par1ItemStack, this.worldObj);
/*  84 */         var2.playersVisibleOnMap.remove("frame-" + this.entityId);
/*     */       } 
/*     */       
/*  87 */       par1ItemStack.setItemFrame((EntityItemFrame)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getDisplayedItem() {
/*  93 */     return getDataWatcher().getWatchableObjectItemStack(2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayedItem(ItemStack par1ItemStack) {
/*  98 */     par1ItemStack = par1ItemStack.copy();
/*  99 */     par1ItemStack.stackSize = 1;
/* 100 */     par1ItemStack.setItemFrame(this);
/* 101 */     getDataWatcher().updateObject(2, par1ItemStack);
/* 102 */     getDataWatcher().setObjectWatched(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRotation() {
/* 110 */     return getDataWatcher().getWatchableObjectByte(3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemRotation(int par1) {
/* 115 */     getDataWatcher().updateObject(3, Byte.valueOf((byte)(par1 % 4)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 123 */     if (getDisplayedItem() != null) {
/*     */       
/* 125 */       par1NBTTagCompound.setCompoundTag("Item", getDisplayedItem().writeToNBT(new NBTTagCompound()));
/* 126 */       par1NBTTagCompound.setByte("ItemRotation", (byte)getRotation());
/* 127 */       par1NBTTagCompound.setFloat("ItemDropChance", this.itemDropChance);
/*     */     } 
/*     */     
/* 130 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 138 */     NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Item");
/*     */     
/* 140 */     if (var2 != null && !var2.hasNoTags()) {
/*     */       
/* 142 */       setDisplayedItem(ItemStack.loadItemStackFromNBT(var2));
/* 143 */       setItemRotation(par1NBTTagCompound.getByte("ItemRotation"));
/*     */       
/* 145 */       if (par1NBTTagCompound.hasKey("ItemDropChance"))
/*     */       {
/* 147 */         this.itemDropChance = par1NBTTagCompound.getFloat("ItemDropChance");
/*     */       }
/*     */     } 
/*     */     
/* 151 */     super.readEntityFromNBT(par1NBTTagCompound);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 183 */     if (getDisplayedItem() == null) {
/*     */       
/* 185 */       if (item_stack == null) {
/* 186 */         return super.onEntityRightClicked(player, item_stack);
/*     */       }
/* 188 */       if (player.onServer()) {
/*     */         
/* 190 */         setDisplayedItem(item_stack);
/*     */         
/* 192 */         if (!player.inCreativeMode()) {
/* 193 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 196 */     } else if (player.onServer()) {
/*     */       
/* 198 */       setItemRotation(getRotation() + 1);
/*     */     } 
/*     */     
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 206 */     return Item.itemFrame;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 211 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityItemFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */