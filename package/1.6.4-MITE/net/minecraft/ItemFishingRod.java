/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class ItemFishingRod
/*     */   extends Item
/*     */   implements IDamageableItem
/*     */ {
/*     */   private Icon castIcon;
/*  10 */   private Icon[] uncastIcons = new Icon[9];
/*     */ 
/*     */   
/*     */   private Material hook_material;
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemFishingRod(int par1, Material hook_material) {
/*  18 */     super(par1, Material.wood, "fishing_rod");
/*  19 */     addMaterial(new Material[] { this.hook_material = hook_material });
/*     */     
/*  21 */     setMaxDamage((int)(2.0F * hook_material.durability + ((hook_material == Material.flint) ? true : false)));
/*  22 */     setMaxStackSize(1);
/*  23 */     setCreativeTab(CreativeTabs.tabTools);
/*     */     
/*  25 */     setSkillsetThatCanRepairThis(Skill.FISHING.id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  33 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRotateAroundWhenRendering() {
/*  42 */     return true;
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  73 */     if (player.fishEntity == null && !player.canCastFishingRod()) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (player.onClient()) {
/*     */       
/*  78 */       player.swingArm();
/*     */ 
/*     */     
/*     */     }
/*  82 */     else if (player.fishEntity != null) {
/*     */       
/*  84 */       player.getHeldItemStack().tryDamageItem(DamageSource.generic, player.fishEntity.catchFish(), player);
/*     */     }
/*     */     else {
/*     */       
/*  88 */       WorldServer world = player.getWorldServer();
/*     */       
/*  90 */       world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*  91 */       world.spawnEntityInWorld(new EntityFishHook(world, player));
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMaterialOrdinal() {
/* 100 */     if (this.hook_material == Material.flint)
/* 101 */       return 0; 
/* 102 */     if (this.hook_material == Material.obsidian)
/* 103 */       return 1; 
/* 104 */     if (this.hook_material == Material.copper)
/* 105 */       return 2; 
/* 106 */     if (this.hook_material == Material.silver)
/* 107 */       return 3; 
/* 108 */     if (this.hook_material == Material.gold)
/* 109 */       return 4; 
/* 110 */     if (this.hook_material == Material.iron)
/* 111 */       return 5; 
/* 112 */     if (this.hook_material == Material.mithril)
/* 113 */       return 6; 
/* 114 */     if (this.hook_material == Material.adamantium)
/* 115 */       return 7; 
/* 116 */     if (this.hook_material == Material.ancient_metal) {
/* 117 */       return 8;
/*     */     }
/* 119 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private Material getMaterialByOrdinal(int ordinal) {
/* 124 */     if (ordinal == 0)
/* 125 */       return Material.flint; 
/* 126 */     if (ordinal == 1)
/* 127 */       return Material.obsidian; 
/* 128 */     if (ordinal == 2)
/* 129 */       return Material.copper; 
/* 130 */     if (ordinal == 3)
/* 131 */       return Material.silver; 
/* 132 */     if (ordinal == 4)
/* 133 */       return Material.gold; 
/* 134 */     if (ordinal == 5)
/* 135 */       return Material.iron; 
/* 136 */     if (ordinal == 6)
/* 137 */       return Material.mithril; 
/* 138 */     if (ordinal == 7)
/* 139 */       return Material.adamantium; 
/* 140 */     if (ordinal == 8) {
/* 141 */       return Material.ancient_metal;
/*     */     }
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 151 */     this.castIcon = par1IconRegister.registerIcon(getIconString() + "_cast");
/*     */     
/* 153 */     for (int i = 0; i < this.uncastIcons.length; i++)
/*     */     {
/* 155 */       this.uncastIcons[i] = par1IconRegister.registerIcon("fishing_rods/" + (getMaterialByOrdinal(i)).name + "_uncast");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon func_94597_g() {
/* 161 */     return this.castIcon;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtype(int par1) {
/* 166 */     return this.uncastIcons[getMaterialOrdinal()];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 171 */     return (getMaterialForEnchantment()).enchantability;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialForDurability() {
/* 176 */     return getHookMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialForEnchantment() {
/* 181 */     return getHookMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getHookMaterial() {
/* 186 */     return this.hook_material;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumComponentsForDurability() {
/* 191 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRepairCost() {
/* 196 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformationBeforeEnchantments(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/* 207 */     par3List.add(Translator.getFormatted("item.tooltip.fishingRodHook", new Object[] { ((ItemFishingRod)par1ItemStack.getItem()).getHookMaterial().getLocalizedName() }));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 212 */     return this.hook_material.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemFishingRod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */