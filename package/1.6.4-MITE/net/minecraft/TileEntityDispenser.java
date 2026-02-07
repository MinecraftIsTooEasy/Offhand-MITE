/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class TileEntityDispenser
/*     */   extends TileEntity implements IInventory {
/*   7 */   private ItemStack[] dispenserContents = new ItemStack[9];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  12 */   private Random dispenserRandom = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  20 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  28 */     return this.dispenserContents[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  37 */     if (this.dispenserContents[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  41 */       if ((this.dispenserContents[par1]).stackSize <= par2) {
/*     */         
/*  43 */         ItemStack itemStack = this.dispenserContents[par1];
/*  44 */         this.dispenserContents[par1] = null;
/*  45 */         onInventoryChanged();
/*  46 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  50 */       ItemStack var3 = this.dispenserContents[par1].splitStack(par2);
/*     */       
/*  52 */       if ((this.dispenserContents[par1]).stackSize == 0)
/*     */       {
/*  54 */         this.dispenserContents[par1] = null;
/*     */       }
/*     */       
/*  57 */       onInventoryChanged();
/*  58 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  73 */     if (this.dispenserContents[par1] != null) {
/*     */       
/*  75 */       ItemStack var2 = this.dispenserContents[par1];
/*  76 */       this.dispenserContents[par1] = null;
/*  77 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRandomStackFromInventory() {
/*  87 */     int var1 = -1;
/*  88 */     int var2 = 1;
/*     */     
/*  90 */     for (int var3 = 0; var3 < this.dispenserContents.length; var3++) {
/*     */       
/*  92 */       if (this.dispenserContents[var3] != null && this.dispenserRandom.nextInt(var2++) == 0)
/*     */       {
/*  94 */         var1 = var3;
/*     */       }
/*     */     } 
/*     */     
/*  98 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 106 */     this.dispenserContents[par1] = par2ItemStack;
/*     */     
/* 108 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 110 */       par2ItemStack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 113 */     onInventoryChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addItem(ItemStack par1ItemStack) {
/* 121 */     for (int var2 = 0; var2 < this.dispenserContents.length; var2++) {
/*     */       
/* 123 */       if (this.dispenserContents[var2] == null || (this.dispenserContents[var2]).itemID == 0) {
/*     */         
/* 125 */         setInventorySlotContents(var2, par1ItemStack);
/* 126 */         return var2;
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     return -1;
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
/* 143 */     return "container.dispenser";
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
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 165 */     super.readFromNBT(par1NBTTagCompound);
/* 166 */     NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
/* 167 */     this.dispenserContents = new ItemStack[getSizeInventory()];
/*     */     
/* 169 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 171 */       NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
/* 172 */       int var5 = var4.getByte("Slot") & 0xFF;
/*     */       
/* 174 */       if (var5 >= 0 && var5 < this.dispenserContents.length)
/*     */       {
/* 176 */         this.dispenserContents[var5] = ItemStack.loadItemStackFromNBT(var4);
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
/*     */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 191 */     super.writeToNBT(par1NBTTagCompound);
/* 192 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 194 */     for (int var3 = 0; var3 < this.dispenserContents.length; var3++) {
/*     */       
/* 196 */       if (this.dispenserContents[var3] != null) {
/*     */         
/* 198 */         NBTTagCompound var4 = new NBTTagCompound();
/* 199 */         var4.setByte("Slot", (byte)var3);
/* 200 */         this.dispenserContents[var3].writeToNBT(var4);
/* 201 */         var2.appendTag(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 205 */     par1NBTTagCompound.setTag("Items", var2);
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
/*     */   public int getInventoryStackLimit() {
/* 219 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 227 */     return (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) ? false : ((par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D));
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
/* 239 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 245 */     ItemStack[] item_stacks = this.dispenserContents;
/*     */     
/* 247 */     for (int i = 0; i < item_stacks.length; i++)
/* 248 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityDispenser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */