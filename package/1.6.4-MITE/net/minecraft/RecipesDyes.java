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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesDyes
/*    */ {
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/*    */     int var2;
/* 42 */     for (var2 = 0; var2 < 16; var2++) {
/*    */       
/* 44 */       par1CraftingManager.addShapelessRecipe(new ItemStack(Block.cloth, 1, BlockColored.getDyeFromBlock(var2)), new Object[] { new ItemStack(Item.dyePowder, 1, var2), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0) }).setSkillset(Skill.FINE_ARTS.id);
/* 45 */       par1CraftingManager.addRecipe(new ItemStack(Block.stainedClay, 8, BlockColored.getDyeFromBlock(var2)), new Object[] { "###", "#X#", "###", Character.valueOf('#'), new ItemStack(Block.hardenedClay), Character.valueOf('X'), new ItemStack(Item.dyePowder, 1, var2) }).setSkillset(Skill.FINE_ARTS.id);
/*    */     } 
/*    */     
/* 48 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 11), new Object[] { Block.plantYellow }).setSkillset(Skill.FINE_ARTS.id);
/* 49 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), new Object[] { Block.plantRed }).setSkillset(Skill.FINE_ARTS.id);
/* 50 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 12), new Object[] { new ItemStack(Block.plantRed, 1, 1) }).setSkillset(Skill.FINE_ARTS.id);
/* 51 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 13), new Object[] { new ItemStack(Block.plantRed, 1, 2) }).setSkillset(Skill.FINE_ARTS.id);
/* 52 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 14), new Object[] { new ItemStack(Block.plantRed, 1, 5) }).setSkillset(Skill.FINE_ARTS.id);
/* 53 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 9), new Object[] { new ItemStack(Block.plantRed, 1, 7) }).setSkillset(Skill.FINE_ARTS.id);
/* 54 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), new Object[] { new ItemStack(Block.plantRed, 1, 8) }).setSkillset(Skill.FINE_ARTS.id);
/* 55 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 15), new Object[] { Item.bone });
/* 56 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] { new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/* 57 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] { new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 11) }).setSkillset(Skill.FINE_ARTS.id);
/* 58 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 10), new Object[] { new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/* 59 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] { new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/* 60 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] { new ItemStack(Item.dyePowder, 1, 8), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/* 61 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, 7), new Object[] { new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/* 62 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/* 63 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 2) }).setSkillset(Skill.FINE_ARTS.id);
/* 64 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 1) }).setSkillset(Skill.FINE_ARTS.id);
/* 65 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 13), new Object[] { new ItemStack(Item.dyePowder, 1, 5), new ItemStack(Item.dyePowder, 1, 9) }).setSkillset(Skill.FINE_ARTS.id);
/* 66 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 3, 13), new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 9) }).setSkillset(Skill.FINE_ARTS.id);
/* 67 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] { new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 15) }).setSkillset(Skill.FINE_ARTS.id);
/*    */ 
/*    */     
/* 70 */     par1CraftingManager.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 14), new Object[] { Block.plantYellow, Block.plantRed }).setSkillset(Skill.FINE_ARTS.id);
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
/* 92 */     for (var2 = 0; var2 < 16; var2++) {
/*    */       
/* 94 */       par1CraftingManager.addRecipe(new ItemStack(Block.carpet, 3, var2), new Object[] { "##", Character.valueOf('#'), new ItemStack(Block.cloth, 1, var2) }).setSkillset(Skill.FINE_ARTS.id);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesDyes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */