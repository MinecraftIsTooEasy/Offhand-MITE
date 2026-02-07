/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemAppleGold
/*     */   extends ItemFood
/*     */ {
/*     */   public ItemAppleGold(int id, int satiation, int nutrition, String texture) {
/*  15 */     super(id, Material.fruit, satiation, nutrition, 1000, false, false, true, texture);
/*  16 */     addMaterial(new Material[] { Material.gold });
/*     */     
/*  18 */     setPlantProduct();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect(ItemStack par1ItemStack) {
/*  23 */     return (par1ItemStack.getItemSubtype() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack par1ItemStack) {
/*  31 */     return (par1ItemStack.getItemSubtype() == 0) ? EnumRarity.rare : EnumRarity.epic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/*  37 */     if (!par2World.isRemote);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     if (par1ItemStack.getItemSubtype() > 0) {
/*     */       
/*  45 */       if (!par2World.isRemote)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  51 */         par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 1));
/*  52 */         par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 1200, 0));
/*  53 */         par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1200, 0));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  59 */       super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/*  68 */     par3List.add(new ItemStack(par1, 1, 0));
/*  69 */     par3List.add(new ItemStack(par1, 1, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
/*  74 */     if (entity instanceof EntityZombie) {
/*     */       
/*  76 */       EntityZombie zombie = (EntityZombie)entity;
/*     */       
/*  78 */       if (player.onClient()) {
/*  79 */         return (zombie.isVillager() && !zombie.isConverting());
/*     */       }
/*  81 */       if (zombie.isVillager() && !zombie.isConverting() && zombie.isPotionActive(Potion.weakness)) {
/*     */         
/*  83 */         if (player.onServer()) {
/*     */ 
/*     */           
/*  86 */           zombie.startConversion((item_stack.getItemSubtype() == 1) ? 1 : (zombie.rand.nextInt(2401) + 3600));
/*     */           
/*  88 */           if (!player.inCreativeMode()) {
/*  89 */             player.convertOneOfHeldItem((ItemStack)null);
/*     */           }
/*     */         } 
/*  92 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack item_stack) {
/* 101 */     return isEnchantedGoldenApple(item_stack) ? "item.appleGold.enchanted" : super.getUnlocalizedName(item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isGoldenApple(ItemStack item_stack) {
/* 106 */     return (item_stack != null && item_stack.itemID == appleGold.itemID);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isUnenchantedGoldenApple(ItemStack item_stack) {
/* 111 */     return (isGoldenApple(item_stack) && item_stack.getItemSubtype() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEnchantedGoldenApple(ItemStack item_stack) {
/* 116 */     return (isGoldenApple(item_stack) && item_stack.getItemSubtype() > 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemAppleGold.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */