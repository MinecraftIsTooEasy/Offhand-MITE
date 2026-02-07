/*     */ package net.minecraft;
/*     */ 
/*     */ public class RecipesMapCloning
/*     */   implements IRecipe {
/*   5 */   private float difficulty = 100.0F;
/*     */   
/*   7 */   private int[] skillsets = new int[] { Skill.FINE_ARTS.id };
/*     */ 
/*     */ 
/*     */   
/*     */   private Material material_to_check_tool_bench_hardness_against;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
/*  16 */     int var3 = 0;
/*  17 */     ItemStack var4 = null;
/*     */     
/*  19 */     for (int var5 = 0; var5 < par1InventoryCrafting.getSizeInventory(); var5++) {
/*     */       
/*  21 */       ItemStack var6 = par1InventoryCrafting.getStackInSlot(var5);
/*     */       
/*  23 */       if (var6 != null)
/*     */       {
/*  25 */         if (var6.itemID == Item.map.itemID) {
/*     */           
/*  27 */           if (var4 != null)
/*     */           {
/*  29 */             return false;
/*     */           }
/*     */           
/*  32 */           var4 = var6;
/*     */         }
/*     */         else {
/*     */           
/*  36 */           if (var6.itemID != Item.emptyMap.itemID)
/*     */           {
/*  38 */             return false;
/*     */           }
/*     */           
/*  41 */           var3++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  46 */     return (var4 != null && var3 > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingResult getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/*  55 */     int var2 = 0;
/*  56 */     ItemStack var3 = null;
/*     */     
/*  58 */     for (int var4 = 0; var4 < par1InventoryCrafting.getSizeInventory(); var4++) {
/*     */       
/*  60 */       ItemStack var5 = par1InventoryCrafting.getStackInSlot(var4);
/*     */       
/*  62 */       if (var5 != null)
/*     */       {
/*  64 */         if (var5.itemID == Item.map.itemID) {
/*     */           
/*  66 */           if (var3 != null)
/*     */           {
/*  68 */             return null;
/*     */           }
/*     */           
/*  71 */           var3 = var5;
/*     */         }
/*     */         else {
/*     */           
/*  75 */           if (var5.itemID != Item.emptyMap.itemID)
/*     */           {
/*  77 */             return null;
/*     */           }
/*     */           
/*  80 */           var2++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  85 */     if (var3 != null && var2 >= 1) {
/*     */ 
/*     */ 
/*     */       
/*  89 */       ItemStack var6 = new ItemStack(Item.map, var2 + 1, var3.getItemSubtype());
/*     */       
/*  91 */       if (var3.isItemDamaged()) {
/*  92 */         var6.setItemDamage(var3.getItemDamage());
/*     */       }
/*  94 */       if (var3.hasDisplayName())
/*     */       {
/*  96 */         var6.setItemName(var3.getDisplayName());
/*     */       }
/*     */ 
/*     */       
/* 100 */       return new CraftingResult(var6, this.difficulty, this.skillsets, this);
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 113 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getComponents() {
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe setDifficulty(float difficulty) {
/* 128 */     this.difficulty = difficulty;
/* 129 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe scaleDifficulty(float factor) {
/* 134 */     this.difficulty *= factor;
/* 135 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getUnmodifiedDifficulty() {
/* 140 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludeInLowestCraftingDifficultyDetermination() {}
/*     */   
/*     */   public boolean getIncludeInLowestCraftingDifficultyDetermination() {
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillsets(int[] skillsets) {
/* 152 */     this.skillsets = skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillset(int skillset) {
/* 157 */     (new int[1])[0] = skillset; this.skillsets = (skillset == 0) ? null : new int[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSkillsets() {
/* 162 */     return this.skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterialToCheckToolBenchHardnessAgainst(Material material_to_check_tool_bench_hardness_against) {
/* 167 */     this.material_to_check_tool_bench_hardness_against = material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialToCheckToolBenchHardnessAgainst() {
/* 172 */     return this.material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesMapCloning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */