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
/*     */ public class ShapedRecipes
/*     */   implements IRecipe
/*     */ {
/*     */   private int recipeWidth;
/*     */   private int recipeHeight;
/*     */   private ItemStack[] recipeItems;
/*     */   private ItemStack recipeOutput;
/*     */   public final int recipeOutputItemID;
/*     */   private boolean field_92101_f;
/*     */   protected float difficulty;
/*     */   private boolean include_in_lowest_crafting_difficulty_determination;
/*     */   private int[] skillsets;
/*     */   private Material material_to_check_tool_bench_hardness_against;
/*     */   private static boolean prevent_adding_of_next_recipe;
/*     */   private static boolean skip_next_string_or_sinews_that_can_be_anywhere_check;
/*     */   
/*     */   public ShapedRecipes(int recipe_width, int recipe_height, ItemStack[] recipe_items, ItemStack recipe_output, boolean include_in_lowest_crafting_difficulty_determinations) {
/*  42 */     this.recipeOutputItemID = recipe_output.itemID;
/*  43 */     this.recipeWidth = recipe_width;
/*  44 */     this.recipeHeight = recipe_height;
/*  45 */     this.recipeItems = recipe_items;
/*  46 */     this.recipeOutput = recipe_output;
/*     */ 
/*     */ 
/*     */     
/*  50 */     if (prevent_adding_of_next_recipe) {
/*  51 */       prevent_adding_of_next_recipe = false;
/*     */     } else {
/*  53 */       RecipeHelper.addRecipe(this, include_in_lowest_crafting_difficulty_determinations);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/*  58 */     return this.recipeOutput;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
/*  66 */     for (int var3 = 0; var3 <= 3 - this.recipeWidth; var3++) {
/*     */       
/*  68 */       for (int var4 = 0; var4 <= 3 - this.recipeHeight; var4++) {
/*     */         
/*  70 */         if (checkMatch(par1InventoryCrafting, var3, var4, true))
/*     */         {
/*  72 */           return true;
/*     */         }
/*     */         
/*  75 */         if (checkMatch(par1InventoryCrafting, var3, var4, false))
/*     */         {
/*  77 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkMatch(InventoryCrafting par1InventoryCrafting, int par2, int par3, boolean par4) {
/*  90 */     boolean has_strings_or_sinews_that_can_be_anywhere = (this.recipeOutputItemID == Item.knifeFlint.itemID || this.recipeOutputItemID == Item.hatchetFlint.itemID || this.recipeOutputItemID == Item.shovelFlint.itemID || this.recipeOutputItemID == Item.axeFlint.itemID || this.recipeOutputItemID == Item.knifeObsidian.itemID || this.recipeOutputItemID == Item.hatchetObsidian.itemID || this.recipeOutputItemID == Item.shovelObsidian.itemID || this.recipeOutputItemID == Item.axeObsidian.itemID);
/*     */     
/*  92 */     if (skip_next_string_or_sinews_that_can_be_anywhere_check) {
/*     */       
/*  94 */       skip_next_string_or_sinews_that_can_be_anywhere_check = false;
/*     */     }
/*  96 */     else if (has_strings_or_sinews_that_can_be_anywhere) {
/*     */       
/*  98 */       int num_strings_or_sinews_that_can_be_anywhere = 0;
/*     */       
/* 100 */       ItemStack[] recipe_items = new ItemStack[this.recipeWidth * this.recipeHeight];
/*     */       
/* 102 */       for (int index_width = 0; index_width < this.recipeWidth; index_width++) {
/*     */         
/* 104 */         for (int index_height = 0; index_height < this.recipeHeight; index_height++) {
/*     */           
/* 106 */           int index = index_width + index_height * this.recipeWidth;
/*     */           
/* 108 */           ItemStack item_stack = this.recipeItems[index];
/*     */           
/* 110 */           if (item_stack != null)
/*     */           {
/*     */             
/* 113 */             if (item_stack.itemID == Item.sinew.itemID || item_stack.itemID == Item.sinew.itemID) {
/*     */               
/* 115 */               num_strings_or_sinews_that_can_be_anywhere++;
/*     */             }
/*     */             else {
/*     */               
/* 119 */               recipe_items[index] = this.recipeItems[index];
/*     */             }  } 
/*     */         } 
/*     */       } 
/* 123 */       int num_strings_or_sinews_found = 0;
/*     */       
/* 125 */       ItemStack[] item_stacks = new ItemStack[par1InventoryCrafting.getSizeInventory()];
/*     */       
/* 127 */       for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
/*     */         
/* 129 */         ItemStack item_stack = par1InventoryCrafting.getStackInSlot(i);
/*     */         
/* 131 */         if (item_stack != null) {
/*     */ 
/*     */           
/* 134 */           Item item = item_stack.getItem();
/*     */           
/* 136 */           if (item == Item.silk || item == Item.sinew) {
/* 137 */             num_strings_or_sinews_found++;
/*     */           } else {
/* 139 */             item_stacks[i] = item_stack;
/*     */           } 
/*     */         } 
/* 142 */       }  if (num_strings_or_sinews_found != num_strings_or_sinews_that_can_be_anywhere) {
/* 143 */         return false;
/*     */       }
/* 145 */       prevent_adding_of_next_recipe = true;
/*     */       
/* 147 */       ShapedRecipes shaped_recipe = new ShapedRecipes(this.recipeWidth, this.recipeHeight, recipe_items, this.recipeOutput, this.include_in_lowest_crafting_difficulty_determination);
/*     */       
/* 149 */       skip_next_string_or_sinews_that_can_be_anywhere_check = true;
/*     */       
/* 151 */       ItemStack[] original_item_stacks = par1InventoryCrafting.getInventory();
/*     */       
/* 153 */       par1InventoryCrafting.setInventory(item_stacks);
/*     */       
/* 155 */       boolean result = shaped_recipe.checkMatch(par1InventoryCrafting, par2, par3, par4);
/*     */       
/* 157 */       par1InventoryCrafting.setInventory(original_item_stacks);
/*     */       
/* 159 */       return result;
/*     */     } 
/*     */     
/* 162 */     for (int var5 = 0; var5 < 3; var5++) {
/*     */       
/* 164 */       for (int var6 = 0; var6 < 3; var6++) {
/*     */         
/* 166 */         int var7 = var5 - par2;
/* 167 */         int var8 = var6 - par3;
/* 168 */         ItemStack var9 = null;
/*     */         
/* 170 */         if (var7 >= 0 && var8 >= 0 && var7 < this.recipeWidth && var8 < this.recipeHeight)
/*     */         {
/* 172 */           if (par4) {
/*     */             
/* 174 */             var9 = this.recipeItems[this.recipeWidth - var7 - 1 + var8 * this.recipeWidth];
/*     */           }
/*     */           else {
/*     */             
/* 178 */             var9 = this.recipeItems[var7 + var8 * this.recipeWidth];
/*     */           } 
/*     */         }
/*     */         
/* 182 */         ItemStack var10 = par1InventoryCrafting.getStackInRowAndColumn(var5, var6);
/*     */         
/* 184 */         if (var10 != null || var9 != null) {
/*     */           
/* 186 */           if ((var10 == null && var9 != null) || (var10 != null && var9 == null))
/*     */           {
/* 188 */             return false;
/*     */           }
/*     */           
/* 191 */           if (var9.itemID != var10.itemID)
/*     */           {
/* 193 */             return false;
/*     */           }
/*     */           
/* 196 */           if (var9.getItemSubtype() != 32767 && var9.getItemSubtype() != var10.getItemSubtype())
/*     */           {
/* 198 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingResult getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/* 213 */     ItemStack var2 = getRecipeOutput().copy();
/*     */     
/* 215 */     if (this.field_92101_f)
/*     */     {
/* 217 */       for (int var3 = 0; var3 < par1InventoryCrafting.getSizeInventory(); var3++) {
/*     */         
/* 219 */         ItemStack var4 = par1InventoryCrafting.getStackInSlot(var3);
/*     */         
/* 221 */         if (var4 != null && var4.hasTagCompound())
/*     */         {
/* 223 */           var2.setTagCompound((NBTTagCompound)var4.stackTagCompound.copy());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 229 */     return new CraftingResult(var2, this.difficulty, this.skillsets, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 237 */     return this.recipeWidth * this.recipeHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ShapedRecipes func_92100_c() {
/* 243 */     this.field_92101_f = true;
/* 244 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack[] getComponents() {
/* 255 */     return this.recipeItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe setDifficulty(float difficulty) {
/* 260 */     this.difficulty = difficulty;
/* 261 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe scaleDifficulty(float factor) {
/* 266 */     this.difficulty *= factor;
/* 267 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getUnmodifiedDifficulty() {
/* 272 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludeInLowestCraftingDifficultyDetermination() {
/* 277 */     this.include_in_lowest_crafting_difficulty_determination = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIncludeInLowestCraftingDifficultyDetermination() {
/* 282 */     return this.include_in_lowest_crafting_difficulty_determination;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillsets(int[] skillsets) {
/* 287 */     this.skillsets = skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillset(int skillset) {
/* 292 */     (new int[1])[0] = skillset; this.skillsets = (skillset == 0) ? null : new int[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSkillsets() {
/* 297 */     return this.skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterialToCheckToolBenchHardnessAgainst(Material material_to_check_tool_bench_hardness_against) {
/* 302 */     this.material_to_check_tool_bench_hardness_against = material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialToCheckToolBenchHardnessAgainst() {
/* 307 */     return this.material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ShapedRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */