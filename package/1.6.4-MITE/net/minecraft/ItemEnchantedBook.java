/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemEnchantedBook
/*     */   extends Item
/*     */ {
/*     */   public ItemEnchantedBook(int par1) {
/*  13 */     super(par1, new Material[] { Material.paper, Material.leather }, "book_enchanted");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect(ItemStack par1ItemStack) {
/*  18 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemTool(ItemStack par1ItemStack) {
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack par1ItemStack) {
/*  35 */     return par1ItemStack.hasStoredEnchantments() ? EnumRarity.uncommon : super.getRarity(par1ItemStack);
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
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4, Slot slot) {
/*  50 */     super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4, slot);
/*     */     
/*  52 */     NBTTagList var5 = par1ItemStack.getStoredEnchantmentTagList();
/*     */     
/*  54 */     if (var5 != null)
/*     */     {
/*  56 */       for (int var6 = 0; var6 < var5.tagCount(); var6++) {
/*     */         
/*  58 */         short var7 = ((NBTTagCompound)var5.tagAt(var6)).getShort("id");
/*  59 */         short var8 = ((NBTTagCompound)var5.tagAt(var6)).getShort("lvl");
/*     */         
/*  61 */         if (Enchantment.enchantmentsList[var7] != null)
/*     */         {
/*     */           
/*  64 */           par3List.add(Enchantment.enchantmentsList[var7].getTranslatedName(var8, par1ItemStack));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEnchantment(ItemStack par1ItemStack, EnchantmentData par2EnchantmentData) {
/*  76 */     NBTTagList var3 = par1ItemStack.getStoredEnchantmentTagList();
/*     */     
/*  78 */     if (var3 == null) {
/*  79 */       var3 = new NBTTagList();
/*     */     }
/*  81 */     boolean var4 = true;
/*     */     
/*  83 */     for (int var5 = 0; var5 < var3.tagCount(); var5++) {
/*     */       
/*  85 */       NBTTagCompound var6 = (NBTTagCompound)var3.tagAt(var5);
/*     */       
/*  87 */       if (var6.getShort("id") == par2EnchantmentData.enchantmentobj.effectId) {
/*     */         
/*  89 */         if (var6.getShort("lvl") < par2EnchantmentData.enchantmentLevel)
/*     */         {
/*  91 */           var6.setShort("lvl", (short)par2EnchantmentData.enchantmentLevel);
/*     */         }
/*     */         
/*  94 */         var4 = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  99 */     if (var4) {
/*     */       
/* 101 */       NBTTagCompound var7 = new NBTTagCompound();
/* 102 */       var7.setShort("id", (short)par2EnchantmentData.enchantmentobj.effectId);
/* 103 */       var7.setShort("lvl", (short)par2EnchantmentData.enchantmentLevel);
/* 104 */       var3.appendTag(var7);
/*     */     } 
/*     */     
/* 107 */     if (!par1ItemStack.hasTagCompound())
/*     */     {
/* 109 */       par1ItemStack.setTagCompound(new NBTTagCompound());
/*     */     }
/*     */     
/* 112 */     par1ItemStack.getTagCompound().setTag("StoredEnchantments", var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getEnchantedItemStack(EnchantmentData par1EnchantmentData) {
/* 120 */     ItemStack var2 = new ItemStack(this);
/* 121 */     addEnchantment(var2, par1EnchantmentData);
/* 122 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_92113_a(Enchantment par1Enchantment, List<ItemStack> par2List) {
/* 128 */     for (int var3 = 1; var3 <= par1Enchantment.getNumLevels(); var3++)
/*     */     {
/* 130 */       par2List.add(getEnchantedItemStack(new EnchantmentData(par1Enchantment, var3)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public WeightedRandomChestContent func_92114_b(Random par1Random) {
/* 136 */     return func_92112_a(par1Random, 1, 1, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public WeightedRandomChestContent func_92112_a(Random par1Random, int par2, int par3, int par4) {
/* 141 */     Enchantment var5 = Enchantment.enchantmentsBookList[par1Random.nextInt(Enchantment.enchantmentsBookList.length)];
/* 142 */     ItemStack var6 = new ItemStack(this.itemID, 1, 0);
/*     */     
/* 144 */     int var7 = MathHelper.getRandomIntegerInRange(par1Random, 1, var5.getNumLevels());
/* 145 */     addEnchantment(var6, new EnchantmentData(var5, var7));
/* 146 */     return new WeightedRandomChestContent(var6, par2, par3, par4);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemEnchantedBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */