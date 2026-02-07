/*     */ package net.minecraft;
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
/*     */ public class ContainerFurnace
/*     */   extends Container
/*     */ {
/*     */   private TileEntityFurnace furnace;
/*     */   private int lastCookTime;
/*     */   private int lastBurnTime;
/*     */   private int lastItemBurnTime;
/*     */   private int last_sent_heat_level;
/*     */   private boolean prevent_sizzle_sound = true;
/*     */   private int sizzle_sound_cooldown;
/*     */   public static final int SLOT_INDEX_INPUT = 0;
/*     */   public static final int SLOT_INDEX_FUEL = 1;
/*     */   
/*     */   public ContainerFurnace(EntityPlayer player, TileEntityFurnace par2TileEntityFurnace) {
/*  42 */     super(player);
/*     */     
/*  44 */     this.furnace = par2TileEntityFurnace;
/*     */ 
/*     */     
/*  47 */     addSlotToContainer(new Slot(par2TileEntityFurnace, 0, 56, 17, this.furnace.acceptsLargeItems()));
/*  48 */     addSlotToContainer(new SlotFuel(par2TileEntityFurnace, 1, 56, 53, this.furnace));
/*  49 */     addSlotToContainer(new SlotFurnace(player, par2TileEntityFurnace, 2, 116, 35));
/*     */ 
/*     */ 
/*     */     
/*  53 */     for (int row = 0; row < 3; row++) {
/*     */       
/*  55 */       for (int i = 0; i < 9; i++) {
/*  56 */         addSlotToContainer(new Slot(player.inventory, i + row * 9 + 9, 8 + i * 18, 84 + row * 18));
/*     */       }
/*     */     } 
/*  59 */     for (int col = 0; col < 9; col++) {
/*  60 */       addSlotToContainer(new Slot(player.inventory, col, 8 + col * 18, 142));
/*     */     }
/*     */   }
/*     */   
/*     */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  65 */     super.addCraftingToCrafters(par1ICrafting);
/*  66 */     par1ICrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
/*  67 */     par1ICrafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
/*  68 */     par1ICrafting.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detectAndSendChanges() {
/*  76 */     super.detectAndSendChanges();
/*     */     
/*  78 */     for (int var1 = 0; var1 < this.crafters.size(); var1++) {
/*     */       
/*  80 */       ICrafting var2 = this.crafters.get(var1);
/*     */       
/*  82 */       if (this.lastCookTime != this.furnace.furnaceCookTime)
/*     */       {
/*  84 */         var2.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
/*     */       }
/*     */       
/*  87 */       if (this.lastBurnTime != this.furnace.furnaceBurnTime)
/*     */       {
/*  89 */         var2.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
/*     */       }
/*     */       
/*  92 */       if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
/*     */       {
/*  94 */         var2.sendProgressBarUpdate(this, 2, this.furnace.currentItemBurnTime);
/*     */       }
/*     */       
/*  97 */       if (this.last_sent_heat_level != this.furnace.heat_level) {
/*  98 */         this.player.sendPacket((new Packet85SimpleSignal(EnumSignal.furnace_heat_level)).setByte(this.furnace.heat_level));
/*     */       }
/*     */     } 
/* 101 */     this.lastCookTime = this.furnace.furnaceCookTime;
/* 102 */     this.lastBurnTime = this.furnace.furnaceBurnTime;
/* 103 */     this.lastItemBurnTime = this.furnace.currentItemBurnTime;
/*     */     
/* 105 */     this.last_sent_heat_level = this.furnace.heat_level;
/*     */     
/* 107 */     if (this.sizzle_sound_cooldown == 0) {
/*     */       
/* 109 */       if (this.furnace.getStackInSlot(0) == null || ((this.furnace.getStackInSlot(1) == null || this.furnace.getStackInSlot(2) == null) && this.furnace.furnaceBurnTime == 0)) {
/* 110 */         this.prevent_sizzle_sound = false;
/*     */       }
/* 112 */     } else if (this.sizzle_sound_cooldown > 0) {
/*     */       
/* 114 */       this.sizzle_sound_cooldown--;
/*     */     } 
/*     */     
/* 117 */     if (this.furnace.getStackInSlot(0) != null && this.furnace.furnaceBurnTime > 0 && this.furnace.furnaceCookTime == 1) {
/*     */       
/* 119 */       Item cooking_item = this.furnace.getStackInSlot(0).getItem();
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (cooking_item instanceof ItemMeat && !this.prevent_sizzle_sound) {
/*     */         
/* 125 */         this.furnace.worldObj.playSoundEffect((this.furnace.xCoord + 0.5F), (this.furnace.yCoord + 0.5F), (this.furnace.zCoord + 0.5F), "imported.random.sizzle");
/*     */         
/* 127 */         this.prevent_sizzle_sound = true;
/* 128 */         this.sizzle_sound_cooldown = 100;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgressBar(int par1, int par2) {
/* 135 */     if (par1 == 0)
/*     */     {
/* 137 */       this.furnace.furnaceCookTime = par2;
/*     */     }
/*     */     
/* 140 */     if (par1 == 1)
/*     */     {
/* 142 */       this.furnace.furnaceBurnTime = par2;
/*     */     }
/*     */     
/* 145 */     if (par1 == 2)
/*     */     {
/* 147 */       this.furnace.currentItemBurnTime = par2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/* 153 */     return this.furnace.isUseableByPlayer(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlayerAddItemToSmelt(Item item) {
/* 158 */     if (this.player.worldObj.areSkillsEnabled()) {
/* 159 */       return this.player.hasAnyOfTheseSkillsets(TileEntityFurnace.getSkillsetsThatCanSmelt(item));
/*     */     }
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/* 169 */     ItemStack var3 = null;
/* 170 */     Slot var4 = this.inventorySlots.get(par2);
/*     */     
/* 172 */     if (var4 != null && var4.getHasStack()) {
/*     */       
/* 174 */       ItemStack var5 = var4.getStack();
/* 175 */       var3 = var5.copy();
/*     */       
/* 177 */       if (par2 == 2) {
/*     */         
/* 179 */         if (!mergeItemStack(var5, 3, 39, true))
/*     */         {
/* 181 */           return null;
/*     */         }
/*     */         
/* 184 */         var4.onSlotChange(var5, var3);
/*     */       }
/* 186 */       else if (par2 != 1 && par2 != 0) {
/*     */ 
/*     */ 
/*     */         
/* 190 */         if (FurnaceRecipes.smelting().getSmeltingResult(var5, -1) != null && canPlayerAddItemToSmelt(var5.getItem()))
/*     */         {
/* 192 */           if (!mergeItemStack(var5, 0, 1, false))
/*     */           {
/* 194 */             return null;
/*     */           
/*     */           }
/*     */         }
/* 198 */         else if (this.furnace.isItemFuel(var5))
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 203 */           if (!mergeItemStack(var5, 1, 2, false))
/*     */           {
/* 205 */             return null;
/*     */           }
/*     */         }
/* 208 */         else if (par2 >= 3 && par2 < 30)
/*     */         {
/* 210 */           if (!mergeItemStack(var5, 30, 39, false))
/*     */           {
/* 212 */             return null;
/*     */           }
/*     */         }
/* 215 */         else if (par2 >= 30 && par2 < 39 && !mergeItemStack(var5, 3, 30, false))
/*     */         {
/* 217 */           return null;
/*     */         }
/*     */       
/* 220 */       } else if (!mergeItemStack(var5, 3, 39, false)) {
/*     */         
/* 222 */         return null;
/*     */       } 
/*     */       
/* 225 */       if (var5.stackSize == 0) {
/*     */         
/* 227 */         var4.putStack((ItemStack)null);
/*     */       }
/*     */       else {
/*     */         
/* 231 */         var4.onSlotChanged();
/*     */       } 
/*     */       
/* 234 */       if (var5.stackSize == var3.stackSize)
/*     */       {
/* 236 */         return null;
/*     */       }
/*     */       
/* 239 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*     */     } 
/*     */     
/* 242 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityFurnace getTileEntityFurnace() {
/* 247 */     return this.furnace;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerFurnace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */