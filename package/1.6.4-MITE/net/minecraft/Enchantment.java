/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public abstract class Enchantment
/*     */ {
/*   7 */   public static final Enchantment[] enchantmentsList = new Enchantment[256];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Enchantment[] enchantmentsBookList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final EnchantmentProtection protection = new EnchantmentProtection(0, EnumRarity.common, 10);
/*  94 */   public static final EnchantmentProtection fireProtection = new EnchantmentProtection(1, EnumRarity.uncommon, 10);
/*  95 */   public static final EnchantmentProtection featherFalling = new EnchantmentProtection(2, EnumRarity.uncommon, 10);
/*  96 */   public static final EnchantmentProtection blastProtection = new EnchantmentProtection(3, EnumRarity.uncommon, 10);
/*  97 */   public static final EnchantmentProtection projectileProtection = new EnchantmentProtection(4, EnumRarity.uncommon, 10);
/*  98 */   public static final Enchantment respiration = new EnchantmentOxygen(5, EnumRarity.rare, 10);
/*  99 */   public static final Enchantment aquaAffinity = new EnchantmentWaterWorker(6, EnumRarity.rare, 10);
/* 100 */   public static final Enchantment thorns = new EnchantmentThorns(7, EnumRarity.rare, 20);
/* 101 */   public static final Enchantment sharpness = new EnchantmentDamage(16, EnumRarity.common, 10);
/* 102 */   public static final Enchantment smite = new EnchantmentDamage(17, EnumRarity.uncommon, 10);
/* 103 */   public static final Enchantment baneOfArthropods = new EnchantmentDamage(18, EnumRarity.uncommon, 10);
/* 104 */   public static final Enchantment knockback = new EnchantmentKnockback(19, EnumRarity.uncommon, 10);
/* 105 */   public static final Enchantment fireAspect = new EnchantmentFireAspect(20, EnumRarity.rare, 20);
/* 106 */   public static final Enchantment looting = new EnchantmentLootBonus(21, EnumRarity.uncommon, 10);
/* 107 */   public static final Enchantment efficiency = new EnchantmentDigging(32, EnumRarity.common, 10);
/* 108 */   public static final Enchantment silkTouch = new EnchantmentUntouching(33, EnumRarity.rare, 10);
/* 109 */   public static final Enchantment unbreaking = new EnchantmentDurability(34, EnumRarity.uncommon, 10);
/* 110 */   public static final Enchantment fortune = new EnchantmentLootBonus(35, EnumRarity.rare, 10);
/* 111 */   public static final Enchantment power = new EnchantmentArrowDamage(48, EnumRarity.common, 10);
/* 112 */   public static final Enchantment punch = new EnchantmentArrowKnockback(49, EnumRarity.uncommon, 10);
/* 113 */   public static final Enchantment flame = new EnchantmentArrowFire(50, EnumRarity.rare, 20);
/*     */   
/* 115 */   public static final Enchantment arrow_recovery = new EnchantmentArrowRecovery(51, EnumRarity.uncommon, 10);
/* 116 */   public static final Enchantment stun = new EnchantmentStun(80, EnumRarity.uncommon, 15);
/* 117 */   public static final Enchantment fishing_fortune = new EnchantmentFishingFortune(81, EnumRarity.common, 10);
/* 118 */   public static final Enchantment fertility = new EnchantmentFertility(82, EnumRarity.uncommon, 10);
/* 119 */   public static final Enchantment tree_felling = new EnchantmentTreeFelling(83, EnumRarity.uncommon, 10);
/* 120 */   public static final Enchantment vampiric = new EnchantmentVampiric(84, EnumRarity.epic, 20);
/* 121 */   public static final Enchantment speed = new EnchantmentSpeed(85, EnumRarity.rare, 10);
/* 122 */   public static final Enchantment regeneration = new EnchantmentRegeneration(86, EnumRarity.rare, 20);
/* 123 */   public static final Enchantment free_action = new EnchantmentFreeAction(87, EnumRarity.uncommon, 10);
/* 124 */   public static final Enchantment quickness = new EnchantmentQuickness(88, EnumRarity.uncommon, 10);
/* 125 */   public static final Enchantment true_flight = new EnchantmentTrueFlight(89, EnumRarity.common, 10);
/* 126 */   public static final Enchantment poison = new EnchantmentPoison(90, EnumRarity.rare, 10);
/* 127 */   public static final Enchantment disarming = new EnchantmentDisarming(91, EnumRarity.rare, 10);
/* 128 */   public static final Enchantment piercing = new EnchantmentPiercing(92, EnumRarity.rare, 10);
/* 129 */   public static final Enchantment harvesting = new EnchantmentHarvesting(93, EnumRarity.uncommon, 10);
/* 130 */   public static final Enchantment butchering = new EnchantmentButchering(94, EnumRarity.uncommon, 10);
/* 131 */   public static final Enchantment endurance = new EnchantmentEndurance(95, EnumRarity.uncommon, 10);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int effectId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int weight;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity rarity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int difficulty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Enchantment(int id, EnumRarity rarity, int difficulty) {
/* 165 */     this.effectId = id;
/* 166 */     this.rarity = rarity;
/* 167 */     this.weight = rarity.standard_weight;
/* 168 */     this.difficulty = difficulty;
/*     */ 
/*     */     
/* 171 */     if (enchantmentsList[id] != null) {
/* 172 */       throw new IllegalArgumentException("Duplicate enchantment id!");
/*     */     }
/* 174 */     enchantmentsList[id] = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeight() {
/* 179 */     return this.weight;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumLevels() {
/* 185 */     return 5;
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
/*     */   public boolean hasLevels() {
/* 206 */     return (getNumLevels() > 1);
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
/*     */   public boolean canApplyTogether(Enchantment par1Enchantment) {
/* 246 */     return (this != par1Enchantment);
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
/*     */   public final String getName() {
/* 268 */     return "enchantment." + getNameSuffix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getNameSuffix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTranslatedName(Item item) {
/* 285 */     return StatCollector.translateToLocal(getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslatedName(int level, ItemStack item_stack) {
/* 290 */     String var2 = getTranslatedName((item_stack == null) ? null : item_stack.getItem());
/*     */     
/* 292 */     if (!hasLevels()) {
/* 293 */       return var2;
/*     */     }
/* 295 */     return var2 + " " + StatCollector.translateToLocal("enchantment.level." + level);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean canEnchantItem(Item paramItem);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getExperienceCost(int enchantment_levels) {
/* 312 */     return enchantment_levels * 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean isOnCreativeTab(CreativeTabs paramCreativeTabs);
/*     */   
/*     */   public static Enchantment get(int id) {
/* 319 */     return enchantmentsList[id];
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 324 */     return StatCollector.translateToLocal(getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevelFromEnchantmentLevels(int enchantment_levels) {
/* 330 */     if (hasLevels()) {
/*     */       
/* 332 */       if (enchantment_levels <= this.difficulty - 10) {
/* 333 */         return 0;
/*     */       }
/* 335 */       int level = (enchantment_levels + this.difficulty - 1) / this.difficulty;
/*     */       
/* 337 */       return (level < 1 || level > getNumLevels()) ? 0 : level;
/*     */     } 
/*     */ 
/*     */     
/* 341 */     return (enchantment_levels < this.difficulty * 2 + 1 || enchantment_levels > this.difficulty * 3) ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinEnchantmentLevelsCost(int level) {
/* 348 */     if (level < 1) {
/*     */       
/* 350 */       Minecraft.setErrorMessage("getMinEnchantmentLevelsCost: level < 1 for " + this);
/* 351 */       return 0;
/*     */     } 
/* 353 */     if (!hasLevels()) {
/*     */       
/* 355 */       if (level != 3)
/*     */       {
/* 357 */         Minecraft.setErrorMessage("getMinEnchantmentLevelsCost: level must be 3 for " + this);
/* 358 */         return 0;
/*     */       }
/*     */     
/* 361 */     } else if (level > getNumLevels()) {
/*     */       
/* 363 */       Minecraft.setErrorMessage("getMinEnchantmentLevelsCost: level too high for " + this);
/* 364 */       return 0;
/*     */     } 
/*     */     
/* 367 */     return Math.max(this.difficulty - 10, 0) + this.difficulty * (level - 1) + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinEnchantmentLevelsCost() {
/* 373 */     return getMinEnchantmentLevelsCost(3);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel(ItemStack item_stack) {
/* 378 */     return EnchantmentHelper.getEnchantmentLevel(this.effectId, item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLevelFraction(ItemStack item_stack) {
/* 383 */     return EnchantmentHelper.getEnchantmentLevelFraction(this, item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 388 */     ArrayList<Enchantment> var0 = new ArrayList();
/* 389 */     Enchantment[] var1 = enchantmentsList;
/* 390 */     int var2 = var1.length;
/*     */     
/* 392 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 394 */       Enchantment var4 = var1[var3];
/*     */       
/* 396 */       if (var4 != null)
/*     */       {
/* 398 */         var0.add(var4);
/*     */       }
/*     */     } 
/*     */     
/* 402 */     enchantmentsBookList = var0.<Enchantment>toArray(new Enchantment[0]);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Enchantment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */