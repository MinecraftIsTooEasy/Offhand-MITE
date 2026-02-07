/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.lang3.StringUtils;
/*      */ 
/*      */ public class ContainerRepair
/*      */   extends Container {
/*    9 */   private IInventory outputSlot = new InventoryCraftResult();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   14 */   private IInventory inputSlots = new ContainerRepairINNER1(this, "Repair", true, 2);
/*      */ 
/*      */   
/*      */   private int field_82861_i;
/*      */ 
/*      */   
/*      */   private int field_82858_j;
/*      */ 
/*      */   
/*      */   private int field_82859_k;
/*      */ 
/*      */   
/*      */   private int stackSizeToBeUsedInRepair;
/*      */ 
/*      */   
/*      */   private String repairedItemName;
/*      */ 
/*      */   
/*      */   public Block block;
/*      */ 
/*      */   
/*      */   public boolean play_anvil_sound_on_pickup;
/*      */   
/*      */   public static final int SLOT_INDEX_INPUT_1 = 0;
/*      */   
/*      */   public static final int SLOT_INDEX_INPUT_2 = 1;
/*      */   
/*      */   public static final int SLOT_INDEX_OUTPUT = 2;
/*      */ 
/*      */   
/*      */   public ContainerRepair(EntityPlayer player, int par3, int par4, int par5) {
/*   45 */     super(player);
/*      */     
/*   47 */     this.field_82861_i = par3;
/*   48 */     this.field_82858_j = par4;
/*   49 */     this.field_82859_k = par5;
/*   50 */     this.block = player.worldObj.getBlock(par3, par4, par5);
/*      */ 
/*      */ 
/*      */     
/*   54 */     addSlotToContainer(new SlotRepair(this.inputSlots, 0, 27, 47));
/*      */ 
/*      */     
/*   57 */     addSlotToContainer(new SlotRepairOrEnchantConsumable(this.inputSlots, 1, 76, 47, getBlockAnvil()));
/*      */     
/*   59 */     addSlotToContainer(new ContainerRepairINNER2(this, this.outputSlot, 2, 134, 47, par3, par4, par5));
/*      */     
/*      */     int var7;
/*   62 */     for (var7 = 0; var7 < 3; var7++) {
/*      */       
/*   64 */       for (int var8 = 0; var8 < 9; var8++)
/*      */       {
/*      */         
/*   67 */         addSlotToContainer(new Slot(player.inventory, var8 + var7 * 9 + 9, 8 + var8 * 18, 84 + var7 * 18));
/*      */       }
/*      */     } 
/*      */     
/*   71 */     for (var7 = 0; var7 < 9; var7++)
/*      */     {
/*      */       
/*   74 */       addSlotToContainer(new Slot(player.inventory, var7, 8 + var7 * 18, 142));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockAnvil getBlockAnvil() {
/*   82 */     return (BlockAnvil)this.block;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCraftMatrixChanged(IInventory par1IInventory) {
/*   90 */     super.onCraftMatrixChanged(par1IInventory);
/*      */     
/*   92 */     if (par1IInventory == this.inputSlots)
/*      */     {
/*   94 */       updateRepairOutput();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRepairing(boolean by_consumable) {
/*  392 */     this.repair_fail_condition = 0;
/*      */     
/*  394 */     ItemStack item_stack_in_first_slot = this.inputSlots.getStackInSlot(0);
/*      */     
/*  396 */     if (item_stack_in_first_slot == null) {
/*      */       
/*  398 */       this.outputSlot.setInventorySlotContents(0, null);
/*  399 */       return false;
/*      */     } 
/*      */     
/*  402 */     ItemStack item_stack_in_second_slot = this.inputSlots.getStackInSlot(1);
/*      */     
/*  404 */     Item item_in_first_slot = item_stack_in_first_slot.getItem();
/*  405 */     Item item_in_second_slot = (item_stack_in_second_slot == null) ? null : item_stack_in_second_slot.getItem();
/*      */     
/*  407 */     boolean is_repairing_by_combination = (ItemStack.areItemStacksEqual(item_stack_in_first_slot, item_stack_in_second_slot, true, false, true, true) && item_stack_in_first_slot.isItemDamaged() && !item_stack_in_second_slot.isItemEnchanted());
/*      */     
/*  409 */     if (is_repairing_by_combination) {
/*      */ 
/*      */       
/*  412 */       if (!item_stack_in_first_slot.isItemEnchanted() && !item_stack_in_second_slot.isItemEnchanted() && (!item_stack_in_first_slot.isItemDamaged() || !item_stack_in_second_slot.isItemDamaged())) {
/*  413 */         is_repairing_by_combination = false;
/*      */       }
/*  415 */       Material material_for_repairs = item_in_first_slot.getMaterialForRepairs();
/*      */       
/*  417 */       if (material_for_repairs != null && material_for_repairs.isMetal() && material_for_repairs.durability > (getBlockAnvil().getMetalType()).durability) {
/*  418 */         this.repair_fail_condition = 2;
/*      */       }
/*      */     } 
/*      */     
/*  422 */     boolean is_repairing_by_consumable = (item_stack_in_first_slot.isItemDamaged() && item_stack_in_first_slot.hasRepairCost() && item_stack_in_first_slot.getRepairItem() == item_in_second_slot && getBlockAnvil().getIsRepairable(item_stack_in_first_slot, item_stack_in_second_slot));
/*  423 */     boolean is_repairing = (is_repairing_by_combination || is_repairing_by_consumable);
/*      */     
/*  425 */     if (is_repairing_by_consumable) {
/*      */       
/*  427 */       Material material_for_repairs = item_in_second_slot.getMaterialForRepairs();
/*      */       
/*  429 */       if (material_for_repairs != null && material_for_repairs.isMetal() && material_for_repairs.durability > (getBlockAnvil().getMetalType()).durability) {
/*  430 */         this.repair_fail_condition = 2;
/*      */       }
/*      */     } 
/*  433 */     if (is_repairing)
/*      */     {
/*      */ 
/*      */       
/*  437 */       if (item_stack_in_first_slot.getItem().hasQuality() && item_stack_in_first_slot.getQuality().isHigherThan(this.player.getMaxCraftingQuality(item_in_first_slot.getLowestCraftingDifficultyToProduce(), item_in_first_slot, item_in_first_slot.getSkillsetsThatCanRepairThis()))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  443 */         if (this.repair_fail_condition == 0) {
/*  444 */           this.repair_fail_condition = 1;
/*      */         }
/*  446 */       } else if (this.world.areSkillsEnabled() && !this.player.hasAnyOfTheseSkillsets(item_stack_in_first_slot.getItem().getSkillsetsThatCanRepairThis())) {
/*      */         
/*  448 */         is_repairing_by_combination = false;
/*  449 */         is_repairing_by_consumable = false;
/*  450 */         is_repairing = false;
/*      */       } 
/*      */     }
/*      */     
/*  454 */     return by_consumable ? is_repairing_by_consumable : is_repairing;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRepairOutput() {
/*  462 */     ItemStack item_stack_in_first_slot = this.inputSlots.getStackInSlot(0);
/*      */     
/*  464 */     if (item_stack_in_first_slot == null) {
/*      */       
/*  466 */       this.outputSlot.setInventorySlotContents(0, null);
/*      */       
/*      */       return;
/*      */     } 
/*  470 */     ItemStack item_stack_in_second_slot = this.inputSlots.getStackInSlot(1);
/*      */     
/*  472 */     Item item_in_first_slot = item_stack_in_first_slot.getItem();
/*  473 */     Item item_in_second_slot = (item_stack_in_second_slot == null) ? null : item_stack_in_second_slot.getItem();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     boolean is_repairing = isRepairing(false);
/*  507 */     boolean is_repairing_by_consumable = isRepairing(true);
/*  508 */     boolean is_repairing_by_combination = (is_repairing && !is_repairing_by_consumable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  535 */     boolean is_enchanting = false;
/*  536 */     Map enchantments_on_item_stack_in_second_slot = null;
/*      */     
/*  538 */     if (item_stack_in_first_slot.isEnchantable() && !item_stack_in_first_slot.isItemEnchanted() && item_in_second_slot == Item.enchantedBook) {
/*      */       
/*  540 */       NBTTagList stored_enchantments = item_stack_in_second_slot.getStoredEnchantmentTagList();
/*      */       
/*  542 */       if (stored_enchantments != null && EnchantmentHelper.hasValidEnchantmentForItem(stored_enchantments, item_in_first_slot)) {
/*      */         
/*  544 */         is_enchanting = true;
/*  545 */         enchantments_on_item_stack_in_second_slot = EnchantmentHelper.getEnchantmentsMapFromTags(stored_enchantments);
/*      */       } 
/*      */     } 
/*      */     
/*  549 */     boolean is_disenchanting = (item_stack_in_first_slot.isItemEnchanted() && item_stack_in_second_slot != null && item_stack_in_second_slot.getItem() == Item.bottleOfDisenchanting);
/*  550 */     boolean is_renaming = ((!StringUtils.isBlank(this.repairedItemName) || item_stack_in_first_slot.hasDisplayName()) && !this.repairedItemName.equals(item_stack_in_first_slot.getDisplayName()) && (item_stack_in_second_slot == null || is_repairing || is_enchanting || is_disenchanting));
/*      */ 
/*      */ 
/*      */     
/*  554 */     this.play_anvil_sound_on_pickup = true;
/*      */     
/*  556 */     if (!is_repairing && !is_enchanting && !is_disenchanting && !is_renaming) {
/*      */       
/*  558 */       this.outputSlot.setInventorySlotContents(0, null);
/*      */       
/*      */       return;
/*      */     } 
/*  562 */     ItemStack copy_of_item_stack_in_first_slot = item_stack_in_first_slot.copy();
/*      */     
/*  564 */     this.stackSizeToBeUsedInRepair = 0;
/*      */     
/*  566 */     if (is_repairing) {
/*      */       
/*  568 */       if (is_repairing_by_combination) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  594 */         copy_of_item_stack_in_first_slot.setItemDamage(CraftingManager.getResultingDurabilityFromCombiningItems(item_stack_in_first_slot, item_stack_in_second_slot));
/*      */       } else {
/*      */         int repair_amount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  602 */         if (copy_of_item_stack_in_first_slot.isChainMail()) {
/*  603 */           repair_amount = Math.min(copy_of_item_stack_in_first_slot.getItemDamageForDisplay(), copy_of_item_stack_in_first_slot.getMaxDamage() * 2 / copy_of_item_stack_in_first_slot.getItemAsArmor().getRepairCost(false));
/*      */         } else {
/*  605 */           repair_amount = Math.min(copy_of_item_stack_in_first_slot.getItemDamageForDisplay(), copy_of_item_stack_in_first_slot.getMaxDamage() / copy_of_item_stack_in_first_slot.getRepairCost());
/*      */         } 
/*  607 */         if (repair_amount <= 0) {
/*      */           
/*  609 */           this.outputSlot.setInventorySlotContents(0, null);
/*      */           
/*      */           return;
/*      */         } 
/*  613 */         int initial_repair_amount = repair_amount;
/*      */ 
/*      */         
/*      */         int var10;
/*      */         
/*  618 */         for (var10 = 0; repair_amount > 0 && repair_amount == initial_repair_amount && var10 < item_stack_in_second_slot.stackSize; var10++) {
/*      */           
/*  620 */           int item_damage_after_repair = copy_of_item_stack_in_first_slot.getItemDamageForDisplay() - repair_amount;
/*  621 */           copy_of_item_stack_in_first_slot.setItemDamage(item_damage_after_repair);
/*      */ 
/*      */           
/*  624 */           if (copy_of_item_stack_in_first_slot.isChainMail()) {
/*  625 */             repair_amount = Math.min(copy_of_item_stack_in_first_slot.getItemDamageForDisplay(), copy_of_item_stack_in_first_slot.getMaxDamage() * 2 / copy_of_item_stack_in_first_slot.getItemAsArmor().getRepairCost(false));
/*      */           } else {
/*  627 */             repair_amount = Math.min(copy_of_item_stack_in_first_slot.getItemDamageForDisplay(), copy_of_item_stack_in_first_slot.getMaxDamage() / copy_of_item_stack_in_first_slot.getRepairCost());
/*      */           } 
/*      */         } 
/*  630 */         this.stackSizeToBeUsedInRepair = var10;
/*      */       }
/*      */     
/*  633 */     } else if (is_enchanting) {
/*      */       
/*  635 */       Map<Integer, Integer> enchantments_on_copy_of_item_stack_in_first_slot = EnchantmentHelper.getEnchantmentsMap(copy_of_item_stack_in_first_slot);
/*      */       
/*  637 */       Iterator<Integer> i = enchantments_on_item_stack_in_second_slot.keySet().iterator();
/*      */       
/*  639 */       while (i.hasNext()) {
/*      */         
/*  641 */         int var10000, index = ((Integer)i.next()).intValue();
/*  642 */         Enchantment enchantment = Enchantment.enchantmentsList[index];
/*  643 */         int var13 = enchantments_on_copy_of_item_stack_in_first_slot.containsKey(Integer.valueOf(index)) ? ((Integer)enchantments_on_copy_of_item_stack_in_first_slot.get(Integer.valueOf(index))).intValue() : 0;
/*  644 */         int var14 = ((Integer)enchantments_on_item_stack_in_second_slot.get(Integer.valueOf(index))).intValue();
/*      */ 
/*      */         
/*  647 */         if (var13 == var14) {
/*      */ 
/*      */           
/*  650 */           var10000 = ++var14;
/*      */         }
/*      */         else {
/*      */           
/*  654 */           var10000 = Math.max(var14, var13);
/*      */         } 
/*      */         
/*  657 */         var14 = var10000;
/*  658 */         int var15 = var14 - var13;
/*      */         
/*  660 */         boolean var16 = enchantment.canEnchantItem(item_in_first_slot);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  678 */         if (var16) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  685 */           if (var14 > enchantment.getNumLevels()) {
/*  686 */             var14 = enchantment.getNumLevels();
/*      */           }
/*  688 */           enchantments_on_copy_of_item_stack_in_first_slot.put(Integer.valueOf(index), Integer.valueOf(var14));
/*  689 */           int var23 = 0;
/*      */           
/*  691 */           switch (enchantment.getWeight()) {
/*      */             
/*      */             case 1:
/*  694 */               var23 = 8;
/*      */               break;
/*      */             
/*      */             case 2:
/*  698 */               var23 = 4;
/*      */               break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 5:
/*  710 */               var23 = 2;
/*      */               break;
/*      */             
/*      */             case 10:
/*  714 */               var23 = 1;
/*      */               break;
/*      */           } 
/*  717 */           var23 = Math.max(1, var23 / 2);
/*      */         } 
/*      */       } 
/*      */       
/*  721 */       EnchantmentHelper.setEnchantments(enchantments_on_copy_of_item_stack_in_first_slot, copy_of_item_stack_in_first_slot);
/*      */     }
/*  723 */     else if (is_disenchanting) {
/*      */       
/*  725 */       copy_of_item_stack_in_first_slot.clearEnchantTagList();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  882 */     if (StringUtils.isBlank(this.repairedItemName)) {
/*      */       
/*  884 */       if (item_stack_in_first_slot.hasDisplayName()) {
/*  885 */         copy_of_item_stack_in_first_slot.func_135074_t();
/*      */       }
/*  887 */     } else if (!this.repairedItemName.equals(item_stack_in_first_slot.getDisplayName())) {
/*      */       
/*  889 */       copy_of_item_stack_in_first_slot.setItemName(this.repairedItemName);
/*      */     } 
/*      */     
/*  892 */     this.outputSlot.setInventorySlotContents(0, copy_of_item_stack_in_first_slot);
/*  893 */     detectAndSendChanges();
/*      */   }
/*      */ 
/*      */   
/*      */   public void addCraftingToCrafters(ICrafting par1ICrafting) {
/*  898 */     super.addCraftingToCrafters(par1ICrafting);
/*      */     
/*  900 */     par1ICrafting.sendProgressBarUpdate(this, 0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateProgressBar(int par1, int par2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
/*  916 */     super.onContainerClosed(par1EntityPlayer);
/*      */ 
/*      */     
/*  919 */     if (!this.world.isRemote)
/*      */     {
/*  921 */       for (int var2 = 0; var2 < this.inputSlots.getSizeInventory(); var2++) {
/*      */         
/*  923 */         ItemStack var3 = this.inputSlots.getStackInSlotOnClosing(var2);
/*      */         
/*  925 */         if (var3 != null)
/*      */         {
/*  927 */           par1EntityPlayer.dropPlayerItem(var3);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
/*  941 */     if (this.world.getBlock(this.field_82861_i, this.field_82858_j, this.field_82859_k) instanceof BlockAnvil && this.world.getBlockTileEntity(this.field_82861_i, this.field_82858_j, this.field_82859_k) instanceof TileEntityAnvil) {
/*  942 */       return (par1EntityPlayer.getDistanceSq(this.field_82861_i + 0.5D, this.field_82858_j + 0.5D, this.field_82859_k + 0.5D) <= 64.0D);
/*      */     }
/*  944 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
/*  952 */     ItemStack var3 = null;
/*  953 */     Slot var4 = this.inventorySlots.get(par2);
/*      */     
/*  955 */     if (var4 != null && var4.getHasStack()) {
/*      */       
/*  957 */       ItemStack var5 = var4.getStack();
/*  958 */       var3 = var5.copy();
/*      */       
/*  960 */       if (par2 == 2) {
/*      */         
/*  962 */         if (!mergeItemStack(var5, 3, 39, true))
/*      */         {
/*  964 */           return null;
/*      */         }
/*      */         
/*  967 */         var4.onSlotChange(var5, var3);
/*      */       }
/*  969 */       else if (par2 != 0 && par2 != 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  976 */         if (par2 >= 3 && par2 < 39)
/*      */         {
/*  978 */           if (var5.isRepairItem() || var5.getItem() == Item.enchantedBook || var5.getItem() == Item.bottleOfDisenchanting) {
/*      */             
/*  980 */             if (!mergeItemStack(var5, 1, 2, false) && !mergeItemStack(var5, 0, 2, false)) {
/*  981 */               return null;
/*      */             }
/*  983 */           } else if (!mergeItemStack(var5, 0, 2, false)) {
/*      */             
/*  985 */             return null;
/*      */           }
/*      */         
/*      */         }
/*  989 */       } else if (!mergeItemStack(var5, 3, 39, false)) {
/*      */         
/*  991 */         return null;
/*      */       } 
/*      */       
/*  994 */       if (var5.stackSize == 0) {
/*      */         
/*  996 */         var4.putStack((ItemStack)null);
/*      */       }
/*      */       else {
/*      */         
/* 1000 */         var4.onSlotChanged();
/*      */       } 
/*      */       
/* 1003 */       if (var5.stackSize == var3.stackSize)
/*      */       {
/* 1005 */         return null;
/*      */       }
/*      */       
/* 1008 */       var4.onPickupFromSlot(par1EntityPlayer, var5);
/*      */     } 
/*      */     
/* 1011 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateItemName(String par1Str) {
/* 1019 */     par1Str = par1Str.trim();
/*      */     
/* 1021 */     this.repairedItemName = par1Str;
/*      */     
/* 1023 */     if (getSlot(2).getHasStack()) {
/*      */       
/* 1025 */       ItemStack var2 = getSlot(2).getStack();
/*      */       
/* 1027 */       if (StringUtils.isBlank(par1Str)) {
/*      */         
/* 1029 */         var2.func_135074_t();
/*      */       }
/*      */       else {
/*      */         
/* 1033 */         var2.setItemName(this.repairedItemName);
/*      */       } 
/*      */     } 
/*      */     
/* 1037 */     updateRepairOutput();
/*      */   }
/*      */ 
/*      */   
/*      */   static IInventory getRepairInputInventory(ContainerRepair par0ContainerRepair) {
/* 1042 */     return par0ContainerRepair.inputSlots;
/*      */   }
/*      */ 
/*      */   
/*      */   static int getStackSizeUsedInRepair(ContainerRepair par0ContainerRepair) {
/* 1047 */     return par0ContainerRepair.stackSizeToBeUsedInRepair;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/* 1052 */     if (this.player instanceof EntityOtherPlayerMP) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1057 */     updateRepairOutput();
/*      */     
/* 1059 */     super.onUpdate();
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ContainerRepair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */