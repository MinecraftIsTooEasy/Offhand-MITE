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
/*    */ public class RecipesCrafting
/*    */ {
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/* 32 */     par1CraftingManager.addRecipe(new ItemStack(Block.chestTrapped), new Object[] { "#-", Character.valueOf('#'), Block.chest, Character.valueOf('-'), Block.tripWireSource });
/* 33 */     par1CraftingManager.addRecipe(new ItemStack(Block.furnaceIdle), new Object[] { "###", "# #", "###", Character.valueOf('#'), Block.cobblestone }).setSkillset(Skill.MASONRY.id);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     par1CraftingManager.addRecipe(new ItemStack(Block.sandStone, 4, 2), new Object[] { "##", "##", Character.valueOf('#'), Block.sandStone }).setSkillset(Skill.MASONRY.id);
/* 39 */     par1CraftingManager.addRecipe(new ItemStack(Block.sandStone, 1, 1), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack(Block.stoneSingleSlab, 1, 1) }).setSkillset(Skill.MASONRY.id);
/* 40 */     par1CraftingManager.addRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack(Block.stoneSingleSlab, 1, 7) }).setSkillset(Skill.MASONRY.id);
/*    */     
/* 42 */     par1CraftingManager.addRecipe(new ItemStack(Block.blockNetherQuartz, 2, 2), new Object[] { "#", "#", Character.valueOf('#'), new ItemStack(Block.blockNetherQuartz, 1, 0) }).setSkillset(Skill.MASONRY.id);
/* 43 */     par1CraftingManager.addRecipe(new ItemStack(Block.stoneBrick, 2), new Object[] { "##", "##", Character.valueOf('#'), Block.stone }).setSkillset(Skill.MASONRY.id);
/*    */     
/* 45 */     par1CraftingManager.addRecipe(new ItemStack(Block.thinGlass, 6), new Object[] { "#", Character.valueOf('#'), Block.glass }).setSkillset(Skill.MASONRY.id + Skill.FINE_ARTS.id);
/* 46 */     par1CraftingManager.addRecipe(new ItemStack(Block.redstoneLampIdle, 1), new Object[] { " R ", "RGR", " R ", Character.valueOf('R'), Item.redstone, Character.valueOf('G'), Block.glowStone }).setSkillset(Skill.MASONRY.id + Skill.TINKERING.id);
/* 47 */     par1CraftingManager.addRecipe(new ItemStack(Block.beacon, 1), new Object[] { "GGG", "GSG", "OOO", Character.valueOf('G'), Block.glass, Character.valueOf('S'), Item.netherStar, Character.valueOf('O'), Block.obsidian }).setSkillset(Skill.MASONRY.id);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesCrafting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */