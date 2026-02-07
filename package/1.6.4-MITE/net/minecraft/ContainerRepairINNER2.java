/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ContainerRepairINNER2
/*     */   extends Slot
/*     */ {
/*     */   final World field_135071_a;
/*     */   final int field_135069_b;
/*     */   final int field_135070_c;
/*     */   final int field_135067_d;
/*     */   final ContainerRepair repairContainer;
/*     */   
/*     */   ContainerRepairINNER2(ContainerRepair par1ContainerRepair, IInventory par2IInventory, int par3, int par4, int par5, int par7, int par8, int par9) {
/*  18 */     super(par2IInventory, par3, par4, par5);
/*  19 */     this.repairContainer = par1ContainerRepair;
/*     */     
/*  21 */     this.field_135071_a = par1ContainerRepair.world;
/*  22 */     this.field_135069_b = par7;
/*  23 */     this.field_135070_c = par8;
/*  24 */     this.field_135067_d = par9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack par1ItemStack) {
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
/*  42 */     return getHasStack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
/*  52 */     if (!this.field_135071_a.isRemote && this.repairContainer.isRepairing(true)) {
/*     */       
/*  54 */       int total_amount_repaired = this.repairContainer.getSlot(0).getStack().getItemDamage() - par2ItemStack.getItemDamage();
/*     */       
/*  56 */       int anvil_damage_from_repair = total_amount_repaired;
/*     */       
/*  58 */       Item item = par2ItemStack.getItem();
/*     */       
/*  60 */       int ratio_of_tool_to_armor = Item.mattockIron.getMaxDamage(EnumQuality.average) / Item.bootsIron.getMaxDamage(EnumQuality.average);
/*  61 */       int ratio_of_tool_to_bow = Item.shovelMithril.getMaxDamage(EnumQuality.average) / Item.bowMithril.getMaxDamage(EnumQuality.average);
/*     */       
/*  63 */       if (item instanceof ItemArmor) {
/*  64 */         anvil_damage_from_repair *= ratio_of_tool_to_armor;
/*  65 */       } else if (item instanceof ItemBow) {
/*  66 */         anvil_damage_from_repair *= ratio_of_tool_to_bow;
/*  67 */       } else if (item instanceof ItemFishingRod) {
/*  68 */         anvil_damage_from_repair *= ratio_of_tool_to_armor / 9;
/*  69 */       } else if (!(item instanceof ItemTool)) {
/*  70 */         Minecraft.setErrorMessage("Anvil damage not handled for " + item);
/*     */       } 
/*  72 */       if (Minecraft.inDevMode()) {
/*  73 */         System.out.println("Causing " + anvil_damage_from_repair + " damage to anvil @ " + StringHelper.getCoordsAsString(this.field_135069_b, this.field_135070_c, this.field_135067_d));
/*     */       }
/*  75 */       ((TileEntityAnvil)this.field_135071_a.getBlockTileEntity(this.field_135069_b, this.field_135070_c, this.field_135067_d)).addDamage(this.field_135071_a, this.field_135069_b, this.field_135070_c, this.field_135067_d, anvil_damage_from_repair);
/*     */     } 
/*     */     
/*  78 */     ContainerRepair.getRepairInputInventory(this.repairContainer).setInventorySlotContents(0, (ItemStack)null);
/*     */     
/*  80 */     if (ContainerRepair.getStackSizeUsedInRepair(this.repairContainer) > 0) {
/*     */       
/*  82 */       ItemStack var3 = ContainerRepair.getRepairInputInventory(this.repairContainer).getStackInSlot(1);
/*     */       
/*  84 */       if (var3 != null && var3.stackSize > ContainerRepair.getStackSizeUsedInRepair(this.repairContainer))
/*     */       {
/*  86 */         var3.stackSize -= ContainerRepair.getStackSizeUsedInRepair(this.repairContainer);
/*  87 */         ContainerRepair.getRepairInputInventory(this.repairContainer).setInventorySlotContents(1, var3);
/*     */       }
/*     */       else
/*     */       {
/*  91 */         ContainerRepair.getRepairInputInventory(this.repairContainer).setInventorySlotContents(1, (ItemStack)null);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  96 */       ContainerRepair.getRepairInputInventory(this.repairContainer).setInventorySlotContents(1, (ItemStack)null);
/*     */     } 
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
/* 124 */     if (!this.field_135071_a.isRemote && this.repairContainer.play_anvil_sound_on_pickup)
/* 125 */       this.field_135071_a.playAuxSFX(1021, this.field_135069_b, this.field_135070_c, this.field_135067_d, 0); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerRepairINNER2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */