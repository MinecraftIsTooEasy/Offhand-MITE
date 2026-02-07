/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public abstract class ItemTool
/*     */   extends Item
/*     */   implements IDamageableItem
/*     */ {
/*     */   private Material effective_material;
/*  91 */   protected List materials_effective_against = new ArrayList();
/*  92 */   protected List blocks_effective_against = new ArrayList();
/*     */   protected float damageVsEntity;
/*  94 */   protected static Class[] weapon_classes = new Class[] { ItemSword.class, ItemBattleAxe.class, ItemWarHammer.class, ItemDagger.class, ItemClub.class, ItemPickaxe.class, ItemCudgel.class, ItemKnife.class };
/*     */ 
/*     */   
/*     */   protected ItemTool(int par1, Material material) {
/*  98 */     super(par1, material, (String)null);
/*     */     
/* 100 */     if (hasWoodenHandle() && !hasMaterial(Material.wood)) {
/* 101 */       addMaterial(new Material[] { Material.wood });
/*     */     }
/* 103 */     this.effective_material = material;
/*     */     
/* 105 */     setTextureName("tools/" + material.name + "_" + getToolType());
/* 106 */     setMaxStackSize(1);
/* 107 */     setMaxDamage(getMultipliedDurability());
/* 108 */     this.damageVsEntity = getCombinedDamageVsEntity();
/* 109 */     setCreativeTab(CreativeTabs.tabTools);
/*     */ 
/*     */     
/* 112 */     setReachBonus(0.75F);
/*     */     
/* 114 */     setSkillsetThatCanRepairThis(hasMaterial(Material.wood, true) ? Skill.CARPENTRY.id : (containsRockyMineral() ? Skill.MASONRY.id : (containsMetal() ? Skill.BLACKSMITHING.id : -1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public final float getBaseDurability() {
/* 119 */     return 4.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMultipliedDurability() {
/* 125 */     return (int)(getBaseDurability() * getNumComponentsForDurability() * this.effective_material.durability * 100.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBaseHarvestEfficiency(Block block) {
/* 131 */     return 4.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract float getBaseDamageVsEntity();
/*     */   
/*     */   public float getCombinedDamageVsEntity() {
/* 138 */     return getBaseDamageVsEntity() + getMaterialDamageVsEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlock() {
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getMultipliedHarvestEfficiency(Block block) {
/* 149 */     return getBaseHarvestEfficiency(block) * getMaterialHarvestEfficiency();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaterialHarvestLevel() {
/* 154 */     return (this.effective_material.min_harvest_level == 0) ? 0 : (this.effective_material.isMetal() ? this.effective_material.min_harvest_level : (this.effective_material.min_harvest_level - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaterialHarvestEfficiency() {
/* 159 */     if (this.effective_material == Material.wood)
/* 160 */       return 1.0F; 
/* 161 */     if (this.effective_material == Material.flint)
/* 162 */       return 1.25F; 
/* 163 */     if (this.effective_material == Material.obsidian)
/* 164 */       return 1.5F; 
/* 165 */     if (this.effective_material == Material.rusted_iron)
/* 166 */       return 1.25F; 
/* 167 */     if (this.effective_material == Material.copper)
/* 168 */       return 1.75F; 
/* 169 */     if (this.effective_material == Material.silver)
/* 170 */       return 1.75F; 
/* 171 */     if (this.effective_material == Material.gold)
/* 172 */       return 1.75F; 
/* 173 */     if (this.effective_material == Material.iron)
/* 174 */       return 2.0F; 
/* 175 */     if (this.effective_material == Material.mithril)
/* 176 */       return 2.5F; 
/* 177 */     if (this.effective_material == Material.adamantium)
/* 178 */       return 3.0F; 
/* 179 */     if (this.effective_material == Material.diamond)
/* 180 */       return 2.5F; 
/* 181 */     if (this.effective_material == Material.ancient_metal) {
/* 182 */       return 2.0F;
/*     */     }
/* 184 */     Minecraft.setErrorMessage("getMaterialHarvestEfficiency: tool material not handled");
/* 185 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaterialDamageVsEntity() {
/* 190 */     return this.effective_material.getDamageVsEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMaterialsEffectiveAgainst(Material[] materials_effective_against) {
/* 195 */     for (int i = 0; i < materials_effective_against.length; i++) {
/* 196 */       if (!this.materials_effective_against.contains(materials_effective_against[i]))
/* 197 */         this.materials_effective_against.add(materials_effective_against[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addBlocksEffectiveAgainst(Block[] blocks_effective_against) {
/* 202 */     for (int i = 0; i < blocks_effective_against.length; i++) {
/* 203 */       if (!this.blocks_effective_against.contains(blocks_effective_against[i]))
/* 204 */         this.blocks_effective_against.add(blocks_effective_against[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEffectiveAgainstBlock(Block block, int metadata) {
/* 209 */     if (block instanceof BlockSlab) {
/*     */       
/* 211 */       Block model_block = ((BlockSlab)block).getModelBlock(metadata);
/*     */       
/* 213 */       if (model_block == Block.sandStone && this instanceof ItemAxe) {
/* 214 */         return true;
/*     */       }
/*     */     } 
/* 217 */     return ((this.materials_effective_against.contains(block.blockMaterial) || this.blocks_effective_against.contains(block)) && getMaterialHarvestLevel() >= block.getMinHarvestLevel(metadata));
/*     */   }
/*     */ 
/*     */   
/*     */   public final float getStrVsBlock(Block block, int metadata) {
/* 222 */     if (isEffectiveAgainstBlock(block, metadata)) {
/* 223 */       return getMultipliedHarvestEfficiency(block);
/*     */     }
/* 225 */     return super.getStrVsBlock(block, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float getBaseDecayRateForBreakingBlock(Block paramBlock);
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float getBaseDecayRateForAttackingEntity(ItemStack paramItemStack);
/*     */ 
/*     */   
/*     */   public final int getToolDecayFromBreakingBlock(BlockBreakInfo info) {
/* 238 */     float block_hardness = info.getBlockHardness();
/*     */ 
/*     */     
/* 241 */     if (block_hardness == 0.0F) {
/* 242 */       return 0;
/*     */     }
/* 244 */     float decay = 100.0F * getBaseDecayRateForBreakingBlock(info.block);
/*     */ 
/*     */     
/* 247 */     return Math.max(Math.max((int)(block_hardness * decay), (int)(decay / 20.0F)), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getToolDecayFromAttackingEntity(ItemStack item_stack, EntityLivingBase entity_living_base) {
/* 252 */     return Math.max((int)(100.0F * getBaseDecayRateForAttackingEntity(item_stack)), 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
/* 257 */     if (par2EntityLivingBase.onClient()) {
/* 258 */       Minecraft.setErrorMessage("ItemTool.hitEntity: called on client?");
/*     */     }
/* 260 */     if (par3EntityLivingBase instanceof EntityPlayer && ((EntityPlayer)par3EntityLivingBase).capabilities.isCreativeMode) {
/* 261 */       return false;
/*     */     }
/* 263 */     par1ItemStack.tryDamageItem(DamageSource.generic, getToolDecayFromAttackingEntity(par1ItemStack, par2EntityLivingBase), par3EntityLivingBase);
/* 264 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockDestroyed(BlockBreakInfo info) {
/* 269 */     if (info.world.isRemote) {
/* 270 */       Minecraft.setErrorMessage("ItemTool.onBlockDestroyed: called on client?");
/*     */     }
/* 272 */     Block block = info.block;
/* 273 */     ItemStack item_stack = info.getHarvesterItemStack();
/*     */     
/* 275 */     if (!item_stack.isItemStackDamageable() || block.isPortable(info.world, info.getHarvester(), info.x, info.y, info.z) || info.isResponsiblePlayerInCreativeMode() || info.getBlockHardness() <= 0.0F || getStrVsBlock(block, info.getMetadata()) <= 1.0F) {
/* 276 */       return false;
/*     */     }
/* 278 */     info.getHarvesterItemStack().tryDamageItem(DamageSource.generic, getToolDecayFromBreakingBlock(info), info.getHarvester());
/*     */     
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 285 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemInUseAction getItemInUseAction(ItemStack par1ItemStack, EntityPlayer player) {
/* 290 */     return EnumItemInUseAction.BLOCK;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 295 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 300 */     if (canBlock()) {
/*     */ 
/*     */       
/* 303 */       player.setHeldItemInUse();
/* 304 */       return true;
/*     */     } 
/*     */     
/* 307 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 312 */     return (getMaterialForEnchantment()).enchantability;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToolMaterialName() {
/* 318 */     return (getToolMaterial()).name;
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap getItemAttributeModifiers() {
/* 323 */     Multimap var1 = super.getItemAttributeModifiers();
/* 324 */     var1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));
/* 325 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getToolMaterial() {
/* 331 */     return this.effective_material;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getMaterialForEnchantment() {
/* 337 */     return getToolMaterial();
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
/*     */   public boolean similarToItemsOfSameClass() {
/* 353 */     return true;
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
/*     */   public abstract String getToolType();
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
/*     */   public boolean hasQuality() {
/* 390 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasWoodenHandle() {
/* 395 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBurnTime(ItemStack item_stack) {
/* 400 */     return (this.effective_material == Material.wood) ? 200 : (hasMaterial(Material.wood) ? 100 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialForDurability() {
/* 405 */     return getToolMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getRepairCost() {
/* 410 */     return getNumComponentsForDurability() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMeleeDamageBonus() {
/* 415 */     return getCombinedDamageVsEntity();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemTool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */