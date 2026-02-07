/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesTools
/*    */ {
/*  7 */   private static final Object[][] tools = new Object[][] { { Block.planks, Item.ingotCopper, Item.ingotSilver, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotGold, Item.ingotAncientMetal, { Character.valueOf('/'), Item.stick } }, { null, Item.pickaxeCopper, Item.pickaxeSilver, Item.pickaxeIron, Item.pickaxeMithril, Item.pickaxeAdamantium, Item.pickaxeGold, Item.pickaxeAncientMetal, { "???", " / ", " / " } }, { Item.shovelWood, Item.shovelCopper, Item.shovelSilver, Item.shovelIron, Item.shovelMithril, Item.shovelAdamantium, Item.shovelGold, Item.shovelAncientMetal, { "?", "/", "/" } }, { null, Item.axeCopper, Item.axeSilver, Item.axeIron, Item.axeMithril, Item.axeAdamantium, Item.axeGold, Item.axeAncientMetal, { "??", "?/", " /" } }, { null, Item.hoeCopper, Item.hoeSilver, Item.hoeIron, Item.hoeMithril, Item.hoeAdamantium, Item.hoeGold, Item.hoeAncientMetal, { "??", " /", " /" } }, { null, Item.mattockCopper, Item.mattockSilver, Item.mattockIron, Item.mattockMithril, Item.mattockAdamantium, Item.mattockGold, Item.mattockAncientMetal, { "???", " /?", " / " } }, { null, Item.battleAxeCopper, Item.battleAxeSilver, Item.battleAxeIron, Item.battleAxeMithril, Item.battleAxeAdamantium, Item.battleAxeGold, Item.battleAxeAncientMetal, { "? ?", "?/?", " / " } }, { null, Item.warHammerCopper, Item.warHammerSilver, Item.warHammerIron, Item.warHammerMithril, Item.warHammerAdamantium, Item.warHammerGold, Item.warHammerAncientMetal, { "???", "?/?", " / " } }, { null, Item.scytheCopper, Item.scytheSilver, Item.scytheIron, Item.scytheMithril, Item.scytheAdamantium, Item.scytheGold, Item.scytheAncientMetal, { "/? ", "/ ?", "/  " } }, { null, Item.hatchetCopper, Item.hatchetSilver, Item.hatchetIron, Item.hatchetMithril, Item.hatchetAdamantium, Item.hatchetGold, Item.hatchetAncientMetal, { "/?", "/ " } }, { null, Item.shearsCopper, Item.shearsSilver, Item.shears, Item.shearsMithril, Item.shearsAdamantium, Item.shearsGold, Item.shearsAncientMetal, { " ?", "? " } } };
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
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/* 24 */     par1CraftingManager.addRecipes(tools, -1);
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
/* 36 */     par1CraftingManager.addRecipe(new ItemStack(Item.shovelFlint), new Object[] { "# ", "- ", "-s", Character.valueOf('#'), Item.flint, Character.valueOf('-'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/* 37 */     par1CraftingManager.addRecipe(new ItemStack(Item.hatchetFlint), new Object[] { "#-", "s-", Character.valueOf('#'), Item.flint, Character.valueOf('-'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/* 38 */     par1CraftingManager.addRecipe(new ItemStack(Item.axeFlint), new Object[] { "##", "#-", "s-", Character.valueOf('#'), Item.flint, Character.valueOf('-'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/*    */     
/* 40 */     par1CraftingManager.addRecipe(new ItemStack(Item.shovelObsidian), new Object[] { "# ", "- ", "-s", Character.valueOf('#'), Block.obsidian, Character.valueOf('-'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/* 41 */     par1CraftingManager.addRecipe(new ItemStack(Item.hatchetObsidian), new Object[] { "#-", "s-", Character.valueOf('#'), Block.obsidian, Character.valueOf('-'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/* 42 */     par1CraftingManager.addRecipe(new ItemStack(Item.axeObsidian), new Object[] { "##", "#-", "s-", Character.valueOf('#'), Block.obsidian, Character.valueOf('-'), Item.stick, Character.valueOf('s'), Item.sinew }).setSkillset(Skill.MASONRY.id);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */