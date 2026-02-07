/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class ItemArrow
/*     */   extends Item
/*     */ {
/*  10 */   public static final Material[] material_types = new Material[] { Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal };
/*     */   
/*     */   public final int type_index;
/*     */   
/*     */   public final Material arrowhead_material;
/*     */   
/*     */   public ItemArrow(int par1, Material arrowhead_material) {
/*  17 */     super(par1, Material.wood, "arrows/" + arrowhead_material.name + "_arrow");
/*  18 */     addMaterial(new Material[] { this.arrowhead_material = arrowhead_material });
/*     */     
/*  20 */     this.type_index = getArrowIndex(arrowhead_material);
/*     */     
/*  22 */     setCreativeTab(CreativeTabs.tabCombat);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getArrowIndex(Material arrowhead_material) {
/*  28 */     for (int i = 0; i < material_types.length; i++) {
/*     */       
/*  30 */       if (material_types[i] == arrowhead_material) {
/*  31 */         return i;
/*     */       }
/*     */     } 
/*  34 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getArrowIndex() {
/*  39 */     return this.type_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getChanceOfRecovery() {
/*  46 */     if (this == arrowFlint)
/*  47 */       return 0.3F; 
/*  48 */     if (this == arrowObsidian)
/*  49 */       return 0.4F; 
/*  50 */     if (this == arrowCopper)
/*  51 */       return 0.6F; 
/*  52 */     if (this == arrowSilver)
/*  53 */       return 0.6F; 
/*  54 */     if (this == arrowRustedIron)
/*  55 */       return 0.5F; 
/*  56 */     if (this == arrowGold)
/*  57 */       return 0.5F; 
/*  58 */     if (this == arrowIron)
/*  59 */       return 0.7F; 
/*  60 */     if (this == arrowMithril || this == arrowAncientMetal)
/*  61 */       return 0.8F; 
/*  62 */     if (this == arrowAdamantium) {
/*  63 */       return 0.9F;
/*     */     }
/*  65 */     return 0.3F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addToEntityContainedItemsWithChance(Random rand, EntityLivingBase entity) {
/*  70 */     if (rand.nextFloat() < getChanceOfRecovery()) {
/*     */       
/*  72 */       entity.addContainedItem(this);
/*  73 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaterialDamageVsEntity() {
/*  83 */     return this.arrowhead_material.getDamageVsEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getArrowheadMaterial() {
/*  88 */     return this.arrowhead_material;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDamage() {
/*  93 */     return 0.5F + getMaterialDamageVsEntity() * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/*  98 */     if (extended_info) {
/*     */       
/* 100 */       info.add("");
/*     */       
/* 102 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[] { Integer.valueOf((int)getMaterialDamageVsEntity()) }));
/*     */ 
/*     */       
/* 105 */       if (this.arrowhead_material == Material.silver)
/*     */       {
/*     */         
/* 108 */         info.add(EnumChatFormatting.WHITE + Translator.get("item.tooltip.bonusVsUndead"));
/*     */       }
/*     */       
/* 111 */       info.add(EnumChatFormatting.GRAY + Translator.getFormatted("item.tooltip.missileRecovery", new Object[] { Integer.valueOf((int)(getChanceOfRecovery() * 100.0F)) }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBehaviorDispenseItem getDispenserBehavior() {
/* 117 */     return new DispenserBehaviorArrow(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemArrow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */