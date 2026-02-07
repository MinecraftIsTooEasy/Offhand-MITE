/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ItemCarrotOnAStick
/*     */   extends Item
/*     */   implements IDamageableItem
/*     */ {
/*     */   protected Material hook_material;
/*     */   
/*     */   public ItemCarrotOnAStick(int par1, Material hook_material) {
/*  13 */     super(par1, Material.wood, "carrot_on_a_stick");
/*  14 */     addMaterial(new Material[] { this.hook_material = hook_material });
/*  15 */     setCreativeTab(CreativeTabs.tabTransport);
/*  16 */     setMaxStackSize(1);
/*  17 */     setMaxDamage(25);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  25 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRotateAroundWhenRendering() {
/*  34 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFishingRod getFishingRodItem() {
/*  39 */     if (this.hook_material == Material.flint)
/*  40 */       return Item.fishingRodFlint; 
/*  41 */     if (this.hook_material == Material.obsidian)
/*  42 */       return Item.fishingRodObsidian; 
/*  43 */     if (this.hook_material == Material.copper)
/*  44 */       return Item.fishingRodCopper; 
/*  45 */     if (this.hook_material == Material.silver)
/*  46 */       return Item.fishingRodSilver; 
/*  47 */     if (this.hook_material == Material.gold)
/*  48 */       return Item.fishingRodGold; 
/*  49 */     if (this.hook_material == Material.iron)
/*  50 */       return Item.fishingRodIron; 
/*  51 */     if (this.hook_material == Material.mithril)
/*  52 */       return Item.fishingRodMithril; 
/*  53 */     if (this.hook_material == Material.adamantium)
/*  54 */       return Item.fishingRodAdamantium; 
/*  55 */     if (this.hook_material == Material.ancient_metal) {
/*  56 */       return Item.fishingRodAncientMetal;
/*     */     }
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComponentsForDurability() {
/*  63 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRepairCost() {
/*  68 */     return 0;
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 100 */     if (player.isRiding() && player.ridingEntity instanceof EntityPig) {
/*     */       
/* 102 */       EntityPig pig = (EntityPig)player.ridingEntity;
/*     */       
/* 104 */       ItemStack item_stack = player.getHeldItemStack();
/*     */ 
/*     */       
/* 107 */       if (item_stack.getMaxDamage() - item_stack.getItemDamage() >= 7) {
/*     */         
/* 109 */         if (player.onServer() && pig.getAIControlledByPlayer().isControlledByPlayer()) {
/*     */           
/* 111 */           pig.getAIControlledByPlayer().boostSpeed();
/* 112 */           item_stack.tryDamageItem(DamageSource.pig_nibble, 7, player);
/*     */         } 
/*     */         
/* 115 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInformationBeforeEnchantments(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4, Slot slot) {
/* 124 */     ItemFishingRod item_fishing_rod = ((ItemCarrotOnAStick)par1ItemStack.getItem()).getFishingRodItem();
/*     */     
/* 126 */     item_fishing_rod.addInformationBeforeEnchantments(new ItemStack(item_fishing_rod), par2EntityPlayer, par3List, par4, slot);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemProducedWhenDestroyed(ItemStack item_stack, DamageSource damage_source) {
/* 131 */     return (damage_source == DamageSource.pig_nibble) ? (new ItemStack(getFishingRodItem())).setTagCompound(item_stack.stackTagCompound) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 136 */     return this.hook_material.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemCarrotOnAStick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */