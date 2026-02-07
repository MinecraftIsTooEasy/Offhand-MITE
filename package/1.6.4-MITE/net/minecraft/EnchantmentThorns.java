/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnchantmentThorns
/*     */   extends Enchantment
/*     */ {
/*     */   public EnchantmentThorns(int id, EnumRarity rarity, int difficulty) {
/*  15 */     super(id, rarity, difficulty);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumLevels() {
/*  20 */     return 3;
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
/*     */   public String getNameSuffix() {
/*  49 */     return "thorns";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean func_92094_a(int par0, Random par1Random) {
/*  59 */     return (par0 <= 0) ? false : ((par1Random.nextFloat() < 0.15F * par0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_92095_b(int par0, Random par1Random) {
/*  64 */     return (par0 > 10) ? (par0 - 10) : (1 + par1Random.nextInt(4));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_92096_a(Entity par0Entity, EntityLivingBase par1EntityLivingBase, Random par2Random) {
/*  69 */     if (par1EntityLivingBase.onClient())
/*     */     {
/*  71 */       if (Minecraft.theMinecraft.thePlayer == null || !Minecraft.theMinecraft.thePlayer.isMITEmigo()) {
/*  72 */         Minecraft.setErrorMessage("Thorns.func_92096_a: called on client? (" + ((par0Entity == null) ? "null" : par0Entity.getEntityName()) + " vs " + ((par1EntityLivingBase == null) ? "null" : par1EntityLivingBase.getEntityName()) + ", " + Minecraft.temp_debug + ")");
/*     */       }
/*     */     }
/*  75 */     int var3 = EnchantmentHelper.func_92098_i(par1EntityLivingBase);
/*  76 */     ItemStack var4 = EnchantmentHelper.func_92099_a(Enchantment.thorns, par1EntityLivingBase);
/*     */     
/*  78 */     if (func_92094_a(var3, par2Random)) {
/*     */ 
/*     */       
/*  81 */       par0Entity.attackEntityFrom(new Damage(DamageSource.causeThornsDamage(par1EntityLivingBase), func_92095_b(var3, par2Random)));
/*     */ 
/*     */       
/*  84 */       if (var4 != null)
/*     */       {
/*     */         
/*  87 */         var4.tryDamageItem(DamageSource.generic, 3, par1EntityLivingBase);
/*     */       }
/*     */     }
/*  90 */     else if (var4 != null) {
/*     */ 
/*     */       
/*  93 */       var4.tryDamageItem(DamageSource.generic, 1, par1EntityLivingBase);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEnchantItem(Item item) {
/*  99 */     return item instanceof ItemCuirass;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnCreativeTab(CreativeTabs creative_tab) {
/* 104 */     return (creative_tab == CreativeTabs.tabCombat);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnchantmentThorns.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */