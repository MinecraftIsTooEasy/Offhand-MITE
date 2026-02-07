/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeHelper
/*     */ {
/*     */   public static void addRecipe(IRecipe recipe, boolean include_in_lowest_crafting_difficulty_determination) {
/*   9 */     if (include_in_lowest_crafting_difficulty_determination) {
/*  10 */       recipe.setIncludeInLowestCraftingDifficultyDetermination();
/*     */     }
/*  12 */     ItemStack[] recipe_items = recipe.getComponents();
/*     */     
/*  14 */     ItemStack recipe_output = recipe.getRecipeOutput();
/*     */     
/*  16 */     Item output_item = recipe_output.getItem();
/*     */     
/*  18 */     output_item.setAsCraftingProduct();
/*     */     
/*  20 */     output_item.recipes[output_item.num_recipes++] = recipe;
/*     */     
/*  22 */     float difficulty = 0.0F;
/*     */     
/*  24 */     for (int i = 0; i < recipe_items.length; i++) {
/*     */       
/*  26 */       ItemStack item_stack = recipe_items[i];
/*     */       
/*  28 */       if (item_stack != null) {
/*     */         
/*  30 */         Item item = item_stack.getItem();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  37 */         if (!item.getHasSubtypes() || item_stack.getItemSubtype() == 32767) {
/*  38 */           item.setAsComponentOfCraftingProduct(recipe_output);
/*     */         } else {
/*  40 */           item_stack.setAsComponentOfCraftingProduct(recipe_output);
/*     */         } 
/*     */         
/*  43 */         float component_difficulty = item_stack.getCraftingDifficultyAsComponent();
/*     */         
/*  45 */         if (component_difficulty < 0.0F) {
/*     */           
/*  47 */           float lowest_crafting_difficulty_to_produce = item_stack.getItem().getLowestCraftingDifficultyToProduce();
/*     */           
/*  49 */           if (lowest_crafting_difficulty_to_produce != Float.MAX_VALUE) {
/*     */             
/*  51 */             item_stack.getItem().setCraftingDifficultyAsComponent(lowest_crafting_difficulty_to_produce);
/*  52 */             component_difficulty = lowest_crafting_difficulty_to_produce;
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*  57 */           else if (item.hasMaterial(Material.rusted_iron)) {
/*     */             
/*  59 */             Item peer = Item.getMatchingItem(item.getClass(), Material.copper);
/*     */ 
/*     */             
/*  62 */             if (peer != null) {
/*     */               
/*  64 */               if (item.getMaterialForDurability() == null) {
/*  65 */                 Minecraft.setErrorMessage("addRecipe: getMaterialForDurability()==null for component " + item);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*  70 */               item.setCraftingDifficultyAsComponent(peer.getCraftingDifficultyAsComponent(null) * (item.getMaterialForDurability()).durability / (peer.getMaterialForDurability()).durability);
/*  71 */               component_difficulty = item.getCraftingDifficultyAsComponent(null);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/*  77 */         if (component_difficulty < 0.0F) {
/*  78 */           Minecraft.setErrorMessage("Warning: recipe for " + recipe_output.getDisplayName() + ", component crafting difficulty not set: " + item_stack.getItem().getItemDisplayName(item_stack) + " [" + (item_stack.itemID - 256) + "]");
/*     */         } else {
/*  80 */           difficulty += component_difficulty;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (difficulty < 0.0F) {
/*  93 */       Minecraft.setErrorMessage("addRecipe: recipe output cannot have a crafting difficulty < 0.0F");
/*     */     }
/*  95 */     recipe.setDifficulty(difficulty);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (recipe_output.stackSize < 1) {
/*     */       
/* 103 */       Minecraft.setErrorMessage("stackSize is 0 for recipe output (" + recipe_output.getDisplayName() + ")");
/* 104 */       float difficulty_per_unit = difficulty;
/*     */     }
/*     */     else {
/*     */       
/* 108 */       float difficulty_per_unit = difficulty / recipe_output.stackSize;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     ItemStack output_item_stack = recipe.getRecipeOutput();
/*     */     
/* 115 */     if (output_item_stack != null) {
/*     */       
/* 117 */       output_item = output_item_stack.getItem();
/*     */       
/* 119 */       if (output_item.containsMetal()) {
/*     */ 
/*     */ 
/*     */         
/* 123 */         if (output_item instanceof ItemIngot) {
/*     */           return;
/*     */         }
/* 126 */         if (output_item.getClass() == ItemKnife.class) {
/*     */           return;
/*     */         }
/* 129 */         if (output_item.getClass() == ItemBucket.class && hasAsComponent(recipe, ItemBucket.class)) {
/*     */           return;
/*     */         }
/* 132 */         recipe.setMaterialToCheckToolBenchHardnessAgainst(output_item.getHardestMetalMaterial());
/*     */       }
/* 134 */       else if (output_item.itemID == Block.planks.blockID) {
/*     */         
/* 136 */         recipe.setMaterialToCheckToolBenchHardnessAgainst(Material.wood);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 142 */       else if (output_item.itemID == Block.workbench.blockID) {
/*     */         
/* 144 */         Material tool_material = BlockWorkbench.getToolMaterial(output_item_stack.getItemSubtype());
/*     */ 
/*     */         
/* 147 */         if (tool_material.isMetal()) {
/*     */           
/* 149 */           Material next_strongest_material = null;
/* 150 */           float highest_durability_that_is_less_than_tool_material = 0.0F;
/*     */           
/* 152 */           for (int j = 0; j < BlockWorkbench.tool_materials.length; j++) {
/*     */             
/* 154 */             Material material = BlockWorkbench.tool_materials[j];
/*     */             
/* 156 */             if (material != Material.obsidian)
/*     */             {
/*     */               
/* 159 */               if (material.durability < tool_material.durability && material.durability > highest_durability_that_is_less_than_tool_material) {
/*     */                 
/* 161 */                 next_strongest_material = material;
/* 162 */                 highest_durability_that_is_less_than_tool_material = material.durability;
/*     */               } 
/*     */             }
/*     */           } 
/* 166 */           recipe.setMaterialToCheckToolBenchHardnessAgainst(next_strongest_material);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasAsComponent(IRecipe recipe, ItemStack item_stack) {
/* 176 */     ItemStack[] components = recipe.getComponents();
/*     */     
/* 178 */     for (int i = 0; i < components.length; i++) {
/*     */       
/* 180 */       if (ItemStack.areItemStacksEqual(components[i], item_stack, true, true, true, true)) {
/* 181 */         return true;
/*     */       }
/*     */     } 
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasAsComponent(IRecipe recipe, Class<?> item_class) {
/* 189 */     ItemStack[] components = recipe.getComponents();
/*     */     
/* 191 */     for (int i = 0; i < components.length; i++) {
/*     */       
/* 193 */       ItemStack item_stack = components[i];
/*     */       
/* 195 */       if (item_stack != null && item_stack.getItem().getClass() == item_class) {
/* 196 */         return true;
/*     */       }
/*     */     } 
/* 199 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipeHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */