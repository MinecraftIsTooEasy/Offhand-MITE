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
/*     */ public class ItemFood
/*     */   extends Item
/*     */ {
/*     */   public final int itemUseDuration;
/*     */   private int potionId;
/*     */   private int potionDuration;
/*     */   private int potionAmplifier;
/*     */   private float potionEffectProbability;
/*     */   private ItemFood uncooked_item;
/*     */   private ItemFood cooked_item;
/*     */   
/*     */   public ItemFood() {
/*  40 */     this.itemUseDuration = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemFood(int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients) {
/*  46 */     this(satiation, nutrition, 0, has_protein, has_essential_fats, has_phytonutrients);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFood(int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients) {
/*  51 */     this.itemUseDuration = 0;
/*  52 */     setFoodValue(satiation, nutrition, sugar_content, has_protein, has_essential_fats, has_phytonutrients);
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
/*     */   public ItemFood(int id, Material material, int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture) {
/*  68 */     this(id, material, satiation, nutrition, 0, has_protein, has_essential_fats, has_phytonutrients, texture);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFood(int id, Material material, int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture) {
/*  73 */     super(id, material, "food/" + texture);
/*  74 */     this.itemUseDuration = 32;
/*  75 */     setFoodValue(satiation, nutrition, sugar_content, has_protein, has_essential_fats, has_phytonutrients);
/*     */     
/*  77 */     setCraftingDifficultyAsComponent(25.0F);
/*  78 */     setCreativeTab(CreativeTabs.tabFood);
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
/*     */   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
/* 101 */     if (player.onServer()) {
/*     */       
/* 103 */       player.addFoodValue(this);
/* 104 */       world.playSoundAtEntity(player, "random.burp", 0.5F, player.rand.nextFloat() * 0.1F + 0.9F);
/* 105 */       onEaten(item_stack, world, player);
/*     */     } 
/*     */     
/* 108 */     super.onItemUseFinish(item_stack, world, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 114 */     if (!par2World.isRemote && this.potionId > 0 && par2World.rand.nextFloat() < this.potionEffectProbability)
/*     */     {
/* 116 */       par3EntityPlayer.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 125 */     return 32;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEatable(int item_subtype) {
/* 130 */     return true;
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
/*     */   public ItemFood setPotionEffect(int par1, int par2, int par3, float par4) {
/* 194 */     this.potionId = par1;
/* 195 */     this.potionDuration = par2;
/* 196 */     this.potionAmplifier = par3;
/* 197 */     this.potionEffectProbability = par4;
/* 198 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemFood setAlwaysEdible() {
/* 207 */     super.setAlwaysEdible();
/* 208 */     return this;
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
/*     */   public ItemFood setAnimalProduct() {
/* 266 */     super.setAnimalProduct();
/* 267 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFood setPlantProduct() {
/* 272 */     super.setPlantProduct();
/* 273 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCraftingEffect() {
/* 278 */     return (this == cheese) ? false : super.hasCraftingEffect();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCookingResult(ItemFood uncooked_item, ItemFood cooked_item, int xp_reward) {
/* 284 */     uncooked_item.cooked_item = cooked_item;
/* 285 */     cooked_item.uncooked_item = uncooked_item;
/*     */     
/* 287 */     cooked_item.setXPReward(xp_reward);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFood getUncookedItem() {
/* 292 */     return this.uncooked_item;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFood getCookedItem() {
/* 297 */     return this.cooked_item;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemProducedWhenDestroyed(ItemStack item_stack, DamageSource damage_source) {
/* 302 */     if (damage_source.isFireDamage()) {
/*     */       
/* 304 */       Item cooked_item = getCookedItem();
/*     */       
/* 306 */       if (cooked_item != null) {
/* 307 */         return new ItemStack(cooked_item, item_stack.stackSize);
/*     */       }
/*     */     } 
/* 310 */     return super.getItemProducedWhenDestroyed(item_stack, damage_source);
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
/*     */   public float getCompostingValue() {
/* 329 */     if (this == appleGold || this == goldenCarrot) {
/* 330 */       return 0.0F;
/*     */     }
/* 332 */     return super.getCompostingValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 337 */     if (this == melon) {
/* 338 */       return "slice";
/*     */     }
/* 340 */     return super.getNameDisambiguationForReferenceFile(subtype);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemFood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */