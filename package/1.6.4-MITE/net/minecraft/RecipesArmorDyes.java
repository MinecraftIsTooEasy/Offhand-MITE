/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class RecipesArmorDyes
/*     */   implements IRecipe {
/*   7 */   private float difficulty = 100.0F;
/*     */   
/*   9 */   private int[] skillsets = new int[] { Skill.FINE_ARTS.id };
/*     */ 
/*     */ 
/*     */   
/*     */   private Material material_to_check_tool_bench_hardness_against;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
/*  18 */     ItemStack var3 = null;
/*  19 */     ArrayList<ItemStack> var4 = new ArrayList();
/*     */     
/*  21 */     for (int var5 = 0; var5 < par1InventoryCrafting.getSizeInventory(); var5++) {
/*     */       
/*  23 */       ItemStack var6 = par1InventoryCrafting.getStackInSlot(var5);
/*     */       
/*  25 */       if (var6 != null)
/*     */       {
/*  27 */         if (var6.getItem() instanceof ItemArmor) {
/*     */           
/*  29 */           ItemArmor var7 = (ItemArmor)var6.getItem();
/*     */ 
/*     */           
/*  32 */           if (var7.getArmorMaterial() != Material.leather || var3 != null)
/*     */           {
/*  34 */             return false;
/*     */           }
/*     */           
/*  37 */           var3 = var6;
/*     */         }
/*     */         else {
/*     */           
/*  41 */           if (var6.itemID != Item.dyePowder.itemID)
/*     */           {
/*  43 */             return false;
/*     */           }
/*     */           
/*  46 */           var4.add(var6);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  51 */     return (var3 != null && !var4.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingResult getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/*  60 */     ItemStack var2 = null;
/*  61 */     int[] var3 = new int[3];
/*  62 */     int var4 = 0;
/*  63 */     int var5 = 0;
/*  64 */     ItemArmor var6 = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     EnumQuality quality_override = null;
/*     */     int var7;
/*  73 */     for (var7 = 0; var7 < par1InventoryCrafting.getSizeInventory(); var7++) {
/*     */       
/*  75 */       ItemStack var8 = par1InventoryCrafting.getStackInSlot(var7);
/*     */       
/*  77 */       if (var8 != null)
/*     */       {
/*  79 */         if (var8.getItem() instanceof ItemArmor) {
/*     */           
/*  81 */           var6 = (ItemArmor)var8.getItem();
/*     */ 
/*     */           
/*  84 */           if (var6.getArmorMaterial() != Material.leather || var2 != null)
/*     */           {
/*  86 */             return null;
/*     */           }
/*     */           
/*  89 */           quality_override = var8.getQuality();
/*     */           
/*  91 */           var2 = var8.copy();
/*  92 */           var2.stackSize = 1;
/*     */           
/*  94 */           if (var6.hasColor(var8))
/*     */           {
/*  96 */             int i = var6.getColor(var2);
/*  97 */             float f1 = (i >> 16 & 0xFF) / 255.0F;
/*  98 */             float f2 = (i >> 8 & 0xFF) / 255.0F;
/*  99 */             float var12 = (i & 0xFF) / 255.0F;
/* 100 */             var4 = (int)(var4 + Math.max(f1, Math.max(f2, var12)) * 255.0F);
/* 101 */             var3[0] = (int)(var3[0] + f1 * 255.0F);
/* 102 */             var3[1] = (int)(var3[1] + f2 * 255.0F);
/* 103 */             var3[2] = (int)(var3[2] + var12 * 255.0F);
/* 104 */             var5++;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 109 */           if (var8.itemID != Item.dyePowder.itemID)
/*     */           {
/* 111 */             return null;
/*     */           }
/*     */           
/* 114 */           float[] var14 = EntitySheep.fleeceColorTable[BlockColored.getBlockFromDye(var8.getItemSubtype())];
/* 115 */           int var16 = (int)(var14[0] * 255.0F);
/* 116 */           int var15 = (int)(var14[1] * 255.0F);
/* 117 */           int i = (int)(var14[2] * 255.0F);
/* 118 */           var4 += Math.max(var16, Math.max(var15, i));
/* 119 */           var3[0] = var3[0] + var16;
/* 120 */           var3[1] = var3[1] + var15;
/* 121 */           var3[2] = var3[2] + i;
/* 122 */           var5++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 127 */     if (var6 == null)
/*     */     {
/* 129 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 133 */     var7 = var3[0] / var5;
/* 134 */     int var13 = var3[1] / var5;
/* 135 */     int var9 = var3[2] / var5;
/* 136 */     float var10 = var4 / var5;
/* 137 */     float var11 = Math.max(var7, Math.max(var13, var9));
/* 138 */     var7 = (int)(var7 * var10 / var11);
/* 139 */     var13 = (int)(var13 * var10 / var11);
/* 140 */     var9 = (int)(var9 * var10 / var11);
/* 141 */     int var17 = (var7 << 8) + var13;
/* 142 */     var17 = (var17 << 8) + var9;
/* 143 */     var6.func_82813_b(var2, var17);
/*     */     
/* 145 */     return (new CraftingResult(var2, this.difficulty, this.skillsets, this)).setExperienceCostExempt().setQualityOverride(quality_override);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 154 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getComponents() {
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe setDifficulty(float difficulty) {
/* 169 */     this.difficulty = difficulty;
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe scaleDifficulty(float factor) {
/* 175 */     this.difficulty *= factor;
/* 176 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getUnmodifiedDifficulty() {
/* 181 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludeInLowestCraftingDifficultyDetermination() {}
/*     */   
/*     */   public boolean getIncludeInLowestCraftingDifficultyDetermination() {
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillsets(int[] skillsets) {
/* 193 */     this.skillsets = skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillset(int skillset) {
/* 198 */     (new int[1])[0] = skillset; this.skillsets = (skillset == 0) ? null : new int[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSkillsets() {
/* 203 */     return this.skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterialToCheckToolBenchHardnessAgainst(Material material_to_check_tool_bench_hardness_against) {
/* 208 */     this.material_to_check_tool_bench_hardness_against = material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialToCheckToolBenchHardnessAgainst() {
/* 213 */     return this.material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesArmorDyes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */