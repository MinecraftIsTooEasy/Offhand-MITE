/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ public class ShapelessRecipes
/*     */   implements IRecipe
/*     */ {
/*     */   private final ItemStack recipeOutput;
/*     */   private final List recipeItems;
/*     */   private float difficulty;
/*     */   private boolean include_in_lowest_crafting_difficulty_determination;
/*     */   private int[] skillsets;
/*     */   private boolean propagate_tag_compound;
/*     */   private Material material_to_check_tool_bench_hardness_against;
/*     */   
/*     */   public ShapelessRecipes(ItemStack recipe_output, List recipe_items, boolean include_in_lowest_crafting_difficulty_determination) {
/*  33 */     this.recipeOutput = recipe_output;
/*  34 */     this.recipeItems = recipe_items;
/*     */     
/*  36 */     RecipeHelper.addRecipe(this, include_in_lowest_crafting_difficulty_determination);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/*  41 */     return this.recipeOutput;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
/*  51 */     boolean contains_only_buckets_of_milk = false;
/*  52 */     int num_buckets_of_milk = 0;
/*     */     
/*  54 */     Iterator<ItemStack> i = this.recipeItems.iterator();
/*     */     
/*  56 */     while (i.hasNext()) {
/*     */       
/*  58 */       ItemStack item_stack = i.next();
/*     */       
/*  60 */       if (item_stack.getItem() instanceof ItemBucketMilk) {
/*     */         
/*  62 */         contains_only_buckets_of_milk = true;
/*  63 */         num_buckets_of_milk++;
/*     */         
/*     */         continue;
/*     */       } 
/*  67 */       contains_only_buckets_of_milk = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (contains_only_buckets_of_milk) {
/*     */       
/*  74 */       contains_only_buckets_of_milk = false;
/*     */       
/*  76 */       for (int row = 0; row < 3; row++) {
/*     */         
/*  78 */         for (int col = 0; col < 3; col++) {
/*     */           
/*  80 */           ItemStack item_stack = par1InventoryCrafting.getStackInRowAndColumn(row, col);
/*     */           
/*  82 */           if (item_stack != null) {
/*     */ 
/*     */             
/*  85 */             Item item = item_stack.getItem();
/*     */             
/*  87 */             if (item instanceof ItemBucketMilk) {
/*     */               
/*  89 */               contains_only_buckets_of_milk = true;
/*  90 */               num_buckets_of_milk--;
/*     */             }
/*     */             else {
/*     */               
/*  94 */               contains_only_buckets_of_milk = false;
/*  95 */               row = 3;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 101 */       if (contains_only_buckets_of_milk && num_buckets_of_milk == 0) {
/* 102 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 107 */     ArrayList var3 = new ArrayList(this.recipeItems);
/*     */     
/* 109 */     for (int var4 = 0; var4 < 3; var4++) {
/*     */       
/* 111 */       for (int var5 = 0; var5 < 3; var5++) {
/*     */         
/* 113 */         ItemStack var6 = par1InventoryCrafting.getStackInRowAndColumn(var5, var4);
/*     */         
/* 115 */         if (var6 != null) {
/*     */           
/* 117 */           boolean var7 = false;
/* 118 */           Iterator<ItemStack> var8 = var3.iterator();
/*     */           
/* 120 */           while (var8.hasNext()) {
/*     */             
/* 122 */             ItemStack var9 = var8.next();
/*     */             
/* 124 */             if (var6.itemID == var9.itemID && (var9.getItemSubtype() == 32767 || var6.getItemSubtype() == var9.getItemSubtype())) {
/*     */               
/* 126 */               var7 = true;
/* 127 */               var3.remove(var9);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 132 */           if (!var7)
/*     */           {
/* 134 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     return var3.isEmpty();
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
/*     */   public CraftingResult getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/* 155 */     ItemStack output = this.recipeOutput.copy();
/*     */     
/* 157 */     if (this.propagate_tag_compound)
/*     */     {
/* 159 */       for (int var3 = 0; var3 < par1InventoryCrafting.getSizeInventory(); var3++) {
/*     */         
/* 161 */         ItemStack var4 = par1InventoryCrafting.getStackInSlot(var3);
/*     */         
/* 163 */         if (var4 != null && var4.hasTagCompound())
/*     */         {
/* 165 */           output.setTagCompound((NBTTagCompound)var4.stackTagCompound.copy());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 170 */     return new CraftingResult(output, this.difficulty, this.skillsets, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 178 */     return this.recipeItems.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ShapelessRecipes propagateTagCompound() {
/* 184 */     this.propagate_tag_compound = true;
/* 185 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ShapelessRecipes setDifficulty(int difficulty) {
/* 190 */     this.difficulty = difficulty;
/* 191 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getComponents() {
/* 196 */     ItemStack[] recipe_items = new ItemStack[this.recipeItems.size()];
/*     */     
/* 198 */     for (int i = 0; i < this.recipeItems.size(); i++) {
/* 199 */       recipe_items[i] = this.recipeItems.get(i);
/*     */     }
/* 201 */     return recipe_items;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe setDifficulty(float difficulty) {
/* 206 */     this.difficulty = difficulty;
/* 207 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe scaleDifficulty(float factor) {
/* 212 */     this.difficulty *= factor;
/* 213 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getUnmodifiedDifficulty() {
/* 218 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIncludeInLowestCraftingDifficultyDetermination() {
/* 223 */     this.include_in_lowest_crafting_difficulty_determination = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIncludeInLowestCraftingDifficultyDetermination() {
/* 228 */     return this.include_in_lowest_crafting_difficulty_determination;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillsets(int[] skillsets) {
/* 233 */     this.skillsets = skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillset(int skillset) {
/* 238 */     (new int[1])[0] = skillset; this.skillsets = (skillset == 0) ? null : new int[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSkillsets() {
/* 243 */     return this.skillsets;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaterialToCheckToolBenchHardnessAgainst(Material material_to_check_tool_bench_hardness_against) {
/* 248 */     this.material_to_check_tool_bench_hardness_against = material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterialToCheckToolBenchHardnessAgainst() {
/* 253 */     return this.material_to_check_tool_bench_hardness_against;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ShapelessRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */