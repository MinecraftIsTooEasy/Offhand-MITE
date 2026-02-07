/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TileEntityBeacon
/*     */   extends TileEntity
/*     */   implements IInventory {
/*   9 */   public static final Potion[][] effectsList = new Potion[][] { { Potion.moveSpeed, Potion.digSpeed }, { Potion.resistance, Potion.jump }, { Potion.damageBoost }, { Potion.regeneration } };
/*     */   
/*     */   private long field_82137_b;
/*     */   
/*     */   private float field_82138_c;
/*     */   private boolean isBeaconActive;
/*  15 */   private int levels = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   private int primaryEffect;
/*     */ 
/*     */ 
/*     */   
/*     */   private int secondaryEffect;
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack payment;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateEntity() {
/*  33 */     if (this.worldObj.getTotalWorldTime() % 80L == 0L) {
/*     */       
/*  35 */       updateState();
/*  36 */       addEffectsToPlayers();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addEffectsToPlayers() {
/*  42 */     if (this.isBeaconActive && this.levels > 0 && !this.worldObj.isRemote && this.primaryEffect > 0) {
/*     */       
/*  44 */       double var1 = (this.levels * 10 + 10);
/*  45 */       byte var3 = 0;
/*     */       
/*  47 */       if (this.levels >= 4 && this.primaryEffect == this.secondaryEffect)
/*     */       {
/*  49 */         var3 = 1;
/*     */       }
/*     */       
/*  52 */       AxisAlignedBB var4 = AxisAlignedBB.getAABBPool().getAABB(this.xCoord, this.yCoord, this.zCoord, (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1)).expand(var1, var1, var1);
/*  53 */       var4.maxY = this.worldObj.getHeight();
/*  54 */       List<EntityPlayer> var5 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, var4);
/*  55 */       Iterator<EntityPlayer> var6 = var5.iterator();
/*     */ 
/*     */       
/*  58 */       while (var6.hasNext()) {
/*     */         
/*  60 */         EntityPlayer var7 = var6.next();
/*  61 */         var7.addPotionEffect(new PotionEffect(this.primaryEffect, 180, var3, true));
/*     */       } 
/*     */       
/*  64 */       if (this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect > 0) {
/*     */         
/*  66 */         var6 = var5.iterator();
/*     */         
/*  68 */         while (var6.hasNext()) {
/*     */           
/*  70 */           EntityPlayer var7 = var6.next();
/*  71 */           var7.addPotionEffect(new PotionEffect(this.secondaryEffect, 180, 0, true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateState() {
/*  82 */     if (!this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) {
/*     */       
/*  84 */       this.isBeaconActive = false;
/*  85 */       this.levels = 0;
/*     */     }
/*     */     else {
/*     */       
/*  89 */       this.isBeaconActive = true;
/*  90 */       this.levels = 0;
/*     */       
/*  92 */       for (int var1 = 1; var1 <= 4; this.levels = var1++) {
/*     */         
/*  94 */         int var2 = this.yCoord - var1;
/*     */         
/*  96 */         if (var2 < 0) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 101 */         boolean var3 = true;
/*     */         
/* 103 */         for (int var4 = this.xCoord - var1; var4 <= this.xCoord + var1 && var3; var4++) {
/*     */           
/* 105 */           for (int var5 = this.zCoord - var1; var5 <= this.zCoord + var1; var5++) {
/*     */             
/* 107 */             int var6 = this.worldObj.getBlockId(var4, var2, var5);
/*     */ 
/*     */             
/* 110 */             if (var6 != Block.blockEmerald.blockID && var6 != Block.blockDiamond.blockID && var6 != Block.blockCopper.blockID && var6 != Block.blockSilver.blockID && var6 != Block.blockGold.blockID && var6 != Block.blockIron.blockID && var6 != Block.blockMithril.blockID && var6 != Block.blockAdamantium.blockID) {
/*     */               
/* 112 */               var3 = false;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 118 */         if (!var3) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 124 */       if (this.levels == 0)
/*     */       {
/* 126 */         this.isBeaconActive = false;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_82125_v_() {
/* 133 */     if (!this.isBeaconActive)
/*     */     {
/* 135 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     
/* 139 */     int var1 = (int)(this.worldObj.getTotalWorldTime() - this.field_82137_b);
/* 140 */     this.field_82137_b = this.worldObj.getTotalWorldTime();
/*     */     
/* 142 */     if (var1 > 1) {
/*     */       
/* 144 */       this.field_82138_c -= var1 / 40.0F;
/*     */       
/* 146 */       if (this.field_82138_c < 0.0F)
/*     */       {
/* 148 */         this.field_82138_c = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 152 */     this.field_82138_c += 0.025F;
/*     */     
/* 154 */     if (this.field_82138_c > 1.0F)
/*     */     {
/* 156 */       this.field_82138_c = 1.0F;
/*     */     }
/*     */     
/* 159 */     return this.field_82138_c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPrimaryEffect() {
/* 168 */     return this.primaryEffect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSecondaryEffect() {
/* 176 */     return this.secondaryEffect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevels() {
/* 184 */     return this.levels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevels(int par1) {
/* 192 */     this.levels = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrimaryEffect(int par1) {
/* 197 */     this.primaryEffect = 0;
/*     */     
/* 199 */     for (int var2 = 0; var2 < this.levels && var2 < 3; var2++) {
/*     */       
/* 201 */       Potion[] var3 = effectsList[var2];
/* 202 */       int var4 = var3.length;
/*     */       
/* 204 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/* 206 */         Potion var6 = var3[var5];
/*     */         
/* 208 */         if (var6.id == par1) {
/*     */           
/* 210 */           this.primaryEffect = par1;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecondaryEffect(int par1) {
/* 219 */     this.secondaryEffect = 0;
/*     */     
/* 221 */     if (this.levels >= 4)
/*     */     {
/* 223 */       for (int var2 = 0; var2 < 4; var2++) {
/*     */         
/* 225 */         Potion[] var3 = effectsList[var2];
/* 226 */         int var4 = var3.length;
/*     */         
/* 228 */         for (int var5 = 0; var5 < var4; var5++) {
/*     */           
/* 230 */           Potion var6 = var3[var5];
/*     */           
/* 232 */           if (var6.id == par1) {
/*     */             
/* 234 */             this.secondaryEffect = par1;
/*     */             return;
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
/*     */   public Packet getDescriptionPacket() {
/* 247 */     NBTTagCompound var1 = new NBTTagCompound();
/* 248 */     writeToNBT(var1);
/* 249 */     return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 3, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxRenderDistanceSquared() {
/* 254 */     return 65536.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 262 */     super.readFromNBT(par1NBTTagCompound);
/* 263 */     this.primaryEffect = par1NBTTagCompound.getInteger("Primary");
/* 264 */     this.secondaryEffect = par1NBTTagCompound.getInteger("Secondary");
/* 265 */     this.levels = par1NBTTagCompound.getInteger("Levels");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 273 */     super.writeToNBT(par1NBTTagCompound);
/* 274 */     par1NBTTagCompound.setInteger("Primary", this.primaryEffect);
/* 275 */     par1NBTTagCompound.setInteger("Secondary", this.secondaryEffect);
/* 276 */     par1NBTTagCompound.setInteger("Levels", this.levels);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/* 284 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/* 292 */     return (par1 == 0) ? this.payment : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/* 301 */     if (par1 == 0 && this.payment != null) {
/*     */       
/* 303 */       if (par2 >= this.payment.stackSize) {
/*     */         
/* 305 */         ItemStack var3 = this.payment;
/* 306 */         this.payment = null;
/* 307 */         return var3;
/*     */       } 
/*     */ 
/*     */       
/* 311 */       this.payment.stackSize -= par2;
/*     */ 
/*     */       
/* 314 */       ItemStack item_stack = new ItemStack(this.payment.itemID, par2, this.payment.getItemSubtype());
/*     */       
/* 316 */       if (this.payment.isItemDamaged()) {
/* 317 */         item_stack.setItemDamage(this.payment.getItemDamage());
/*     */       }
/* 319 */       return item_stack;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/* 334 */     if (par1 == 0 && this.payment != null) {
/*     */       
/* 336 */       ItemStack var2 = this.payment;
/* 337 */       this.payment = null;
/* 338 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 342 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 351 */     if (par1 == 0)
/*     */     {
/* 353 */       this.payment = par2ItemStack;
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
/*     */   public String getUnlocalizedInvName() {
/* 367 */     return "container.beacon";
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
/*     */   public int getInventoryStackLimit() {
/* 390 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 398 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
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
/* 410 */     return (par2ItemStack.itemID == Item.emerald.itemID || par2ItemStack.itemID == Item.diamond.itemID || par2ItemStack.itemID == Item.ingotGold.itemID || par2ItemStack.itemID == Item.ingotIron.itemID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 416 */     this.payment = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityBeacon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */