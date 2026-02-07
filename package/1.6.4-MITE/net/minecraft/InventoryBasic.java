/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class InventoryBasic
/*     */   implements IInventory
/*     */ {
/*     */   private String inventoryTitle;
/*     */   private int slotsCount;
/*     */   private ItemStack[] inventoryContents;
/*     */   private List field_70480_d;
/*     */   private boolean field_94051_e;
/*     */   
/*     */   public InventoryBasic(String par1Str, boolean par2, int par3) {
/*  16 */     this.inventoryTitle = par1Str;
/*  17 */     this.field_94051_e = par2;
/*  18 */     this.slotsCount = par3;
/*  19 */     this.inventoryContents = new ItemStack[par3];
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110134_a(IInvBasic par1IInvBasic) {
/*  24 */     if (this.field_70480_d == null)
/*     */     {
/*  26 */       this.field_70480_d = new ArrayList();
/*     */     }
/*     */     
/*  29 */     this.field_70480_d.add(par1IInvBasic);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110132_b(IInvBasic par1IInvBasic) {
/*  34 */     this.field_70480_d.remove(par1IInvBasic);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int par1) {
/*  42 */     return this.inventoryContents[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int par1, int par2) {
/*  51 */     if (this.inventoryContents[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/*  55 */       if ((this.inventoryContents[par1]).stackSize <= par2) {
/*     */         
/*  57 */         ItemStack itemStack = this.inventoryContents[par1];
/*  58 */         this.inventoryContents[par1] = null;
/*  59 */         onInventoryChanged();
/*  60 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  64 */       ItemStack var3 = this.inventoryContents[par1].splitStack(par2);
/*     */       
/*  66 */       if ((this.inventoryContents[par1]).stackSize == 0)
/*     */       {
/*  68 */         this.inventoryContents[par1] = null;
/*     */       }
/*     */       
/*  71 */       onInventoryChanged();
/*  72 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int par1) {
/*  87 */     if (this.inventoryContents[par1] != null) {
/*     */       
/*  89 */       ItemStack var2 = this.inventoryContents[par1];
/*  90 */       this.inventoryContents[par1] = null;
/*  91 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
/* 104 */     this.inventoryContents[par1] = par2ItemStack;
/*     */     
/* 106 */     if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
/*     */     {
/* 108 */       par2ItemStack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 111 */     onInventoryChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/* 119 */     return this.slotsCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomNameOrUnlocalized() {
/* 127 */     return this.inventoryTitle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 136 */     return this.field_94051_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110133_a(String par1Str) {
/* 141 */     this.field_94051_e = true;
/* 142 */     this.inventoryTitle = par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 151 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onInventoryChanged() {
/* 159 */     if (this.field_70480_d != null)
/*     */     {
/* 161 */       for (int var1 = 0; var1 < this.field_70480_d.size(); var1++)
/*     */       {
/* 163 */         ((IInvBasic)this.field_70480_d.get(var1)).onInventoryChanged(this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
/* 173 */     return true;
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
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyInventory() {
/* 191 */     ItemStack[] item_stacks = this.inventoryContents;
/*     */     
/* 193 */     for (int i = 0; i < item_stacks.length; i++)
/* 194 */       item_stacks[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\InventoryBasic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */