/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesFood
/*    */ {
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/* 22 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.bowlMushroomStew), new Object[] { Block.mushroomBrown, Block.mushroomRed, Item.bowlWater }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 23 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.cookie, 4), new Object[] { Item.dough, new ItemStack(Item.dyePowder, 1, 3), Item.sugar }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 24 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.cookie, 4), new Object[] { Item.dough, Item.chocolate }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 25 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.cookie, 4), new Object[] { Item.flour, Item.bowlWater, new ItemStack(Item.dyePowder, 1, 3), Item.sugar }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 26 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.cookie, 4), new Object[] { Item.flour, Item.bowlWater, Item.chocolate }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 27 */     par1CraftingManager.addRecipe(new ItemStack(Item.melonSeeds), new Object[] { "MM", "MM", Character.valueOf('M'), Item.melon });
/* 28 */     par1CraftingManager.addRecipe(new ItemStack(Item.pumpkinSeeds, 1), new Object[] { "M", Character.valueOf('M'), Block.pumpkin });
/* 29 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.pumpkinPie), new Object[] { Block.pumpkin, Item.sugar, Item.egg, Item.flour }).setSkillset(Skill.FOOD_PREPARATION.id);
/* 30 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.fermentedSpiderEye), new Object[] { Item.spiderEye, Block.mushroomBrown, Item.sugar }).setSkillset(Skill.BREWING.id);
/* 31 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.blazePowder, 2), new Object[] { Item.blazeRod }).setSkillset(Skill.FINE_ARTS.id);
/* 32 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.magmaCream), new Object[] { Item.blazePowder, Item.slimeBall }).setSkillset(Skill.FINE_ARTS.id);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesFood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */