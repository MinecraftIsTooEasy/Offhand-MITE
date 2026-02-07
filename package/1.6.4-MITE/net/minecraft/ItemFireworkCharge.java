/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class ItemFireworkCharge
/*     */   extends Item
/*     */ {
/*     */   private Icon theIcon;
/*     */   
/*     */   public ItemFireworkCharge(int par1) {
/*  12 */     super(par1, new Material[] { Material.gunpowder, Material.blaze, Material.coal }, "fireworks_charge");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Icon getIconFromSubtypeForRenderPass(int par1, int par2) {
/*  20 */     return (par2 > 0) ? this.theIcon : super.getIconFromSubtypeForRenderPass(par1, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/*  25 */     if (par2 != 1)
/*     */     {
/*  27 */       return super.getColorFromItemStack(par1ItemStack, par2);
/*     */     }
/*     */ 
/*     */     
/*  31 */     NBTBase var3 = func_92108_a(par1ItemStack, "Colors");
/*     */     
/*  33 */     if (var3 == null)
/*     */     {
/*  35 */       return 9079434;
/*     */     }
/*     */ 
/*     */     
/*  39 */     NBTTagIntArray var4 = (NBTTagIntArray)var3;
/*     */     
/*  41 */     if (var4.intArray.length == 1)
/*     */     {
/*  43 */       return var4.intArray[0];
/*     */     }
/*     */ 
/*     */     
/*  47 */     int var5 = 0;
/*  48 */     int var6 = 0;
/*  49 */     int var7 = 0;
/*  50 */     int[] var8 = var4.intArray;
/*  51 */     int var9 = var8.length;
/*     */     
/*  53 */     for (int var10 = 0; var10 < var9; var10++) {
/*     */       
/*  55 */       int var11 = var8[var10];
/*  56 */       var5 += (var11 & 0xFF0000) >> 16;
/*  57 */       var6 += (var11 & 0xFF00) >> 8;
/*  58 */       var7 += (var11 & 0xFF) >> 0;
/*     */     } 
/*     */     
/*  61 */     var5 /= var4.intArray.length;
/*  62 */     var6 /= var4.intArray.length;
/*  63 */     var7 /= var4.intArray.length;
/*  64 */     return var5 << 16 | var6 << 8 | var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean requiresMultipleRenderPasses() {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTBase func_92108_a(ItemStack par0ItemStack, String par1Str) {
/*  77 */     if (par0ItemStack.hasTagCompound()) {
/*     */       
/*  79 */       NBTTagCompound var2 = par0ItemStack.getTagCompound().getCompoundTag("Explosion");
/*     */       
/*  81 */       if (var2 != null)
/*     */       {
/*  83 */         return var2.getTag(par1Str);
/*     */       }
/*     */     } 
/*     */     
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4, Slot slot) {
/*  96 */     if (par1ItemStack.hasTagCompound()) {
/*     */       
/*  98 */       NBTTagCompound var5 = par1ItemStack.getTagCompound().getCompoundTag("Explosion");
/*     */       
/* 100 */       if (var5 != null)
/*     */       {
/* 102 */         func_92107_a(var5, par3List);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_92107_a(NBTTagCompound par0NBTTagCompound, List<String> par1List) {
/* 109 */     byte var2 = par0NBTTagCompound.getByte("Type");
/*     */     
/* 111 */     if (var2 >= 0 && var2 <= 4) {
/*     */       
/* 113 */       par1List.add(StatCollector.translateToLocal("item.fireworksCharge.type." + var2).trim());
/*     */     }
/*     */     else {
/*     */       
/* 117 */       par1List.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
/*     */     } 
/*     */     
/* 120 */     int[] var3 = par0NBTTagCompound.getIntArray("Colors");
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (var3.length > 0) {
/*     */       
/* 126 */       boolean var4 = true;
/* 127 */       String var5 = "";
/* 128 */       int[] var6 = var3;
/* 129 */       int var7 = var3.length;
/*     */       
/* 131 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/* 133 */         int var9 = var6[var8];
/*     */         
/* 135 */         if (!var4)
/*     */         {
/* 137 */           var5 = var5 + ", ";
/*     */         }
/*     */         
/* 140 */         var4 = false;
/* 141 */         boolean var10 = false;
/*     */         
/* 143 */         for (int var11 = 0; var11 < 16; var11++) {
/*     */           
/* 145 */           if (var9 == ItemDye.dyeColors[var11]) {
/*     */             
/* 147 */             var10 = true;
/* 148 */             var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.dyeColorNames[var11]);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 153 */         if (!var10)
/*     */         {
/* 155 */           var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
/*     */         }
/*     */       } 
/*     */       
/* 159 */       par1List.add(var5);
/*     */     } 
/*     */     
/* 162 */     int[] var13 = par0NBTTagCompound.getIntArray("FadeColors");
/*     */ 
/*     */     
/* 165 */     if (var13.length > 0) {
/*     */       
/* 167 */       boolean bool = true;
/* 168 */       String var14 = StatCollector.translateToLocal("item.fireworksCharge.fadeTo") + " ";
/* 169 */       int[] var15 = var13;
/* 170 */       int var8 = var13.length;
/*     */       
/* 172 */       for (int var9 = 0; var9 < var8; var9++) {
/*     */         
/* 174 */         int var18 = var15[var9];
/*     */         
/* 176 */         if (!bool)
/*     */         {
/* 178 */           var14 = var14 + ", ";
/*     */         }
/*     */         
/* 181 */         bool = false;
/* 182 */         boolean var19 = false;
/*     */         
/* 184 */         for (int var12 = 0; var12 < 16; var12++) {
/*     */           
/* 186 */           if (var18 == ItemDye.dyeColors[var12]) {
/*     */             
/* 188 */             var19 = true;
/* 189 */             var14 = var14 + StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.dyeColorNames[var12]);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 194 */         if (!var19)
/*     */         {
/* 196 */           var14 = var14 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
/*     */         }
/*     */       } 
/*     */       
/* 200 */       par1List.add(var14);
/*     */     } 
/*     */     
/* 203 */     boolean var16 = par0NBTTagCompound.getBoolean("Trail");
/*     */     
/* 205 */     if (var16)
/*     */     {
/* 207 */       par1List.add(StatCollector.translateToLocal("item.fireworksCharge.trail"));
/*     */     }
/*     */     
/* 210 */     boolean var17 = par0NBTTagCompound.getBoolean("Flicker");
/*     */     
/* 212 */     if (var17)
/*     */     {
/* 214 */       par1List.add(StatCollector.translateToLocal("item.fireworksCharge.flicker"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerIcons(IconRegister par1IconRegister) {
/* 220 */     super.registerIcons(par1IconRegister);
/* 221 */     this.theIcon = par1IconRegister.registerIcon(getIconString() + "_overlay");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemFireworkCharge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */