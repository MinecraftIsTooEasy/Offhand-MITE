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
/*    */ public class RecipesArmor
/*    */ {
/*    */   private CraftingManager crafting_manager;
/*    */   
/*    */   private void addHelmetRecipe(Item helmet, Item component) {
/* 17 */     this.crafting_manager.addRecipe(new ItemStack(helmet, 1), new Object[] { "XXX", "X X", Character.valueOf('X'), component }).setSkillset(component.containsMetal() ? Skill.BLACKSMITHING.id : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private void addCuirassRecipe(Item cuirass, Item component) {
/* 22 */     this.crafting_manager.addRecipe(new ItemStack(cuirass, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), component }).setSkillset(component.containsMetal() ? Skill.BLACKSMITHING.id : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private void addLeggingsRecipe(Item leggings, Item component) {
/* 27 */     this.crafting_manager.addRecipe(new ItemStack(leggings, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), component }).setSkillset(component.containsMetal() ? Skill.BLACKSMITHING.id : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private void addBootsRecipe(Item boots, Item component) {
/* 32 */     this.crafting_manager.addRecipe(new ItemStack(boots, 1), new Object[] { "X X", "X X", Character.valueOf('X'), component }).setSkillset(component.containsMetal() ? Skill.BLACKSMITHING.id : 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/* 40 */     this.crafting_manager = par1CraftingManager;
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
/* 57 */     Item[] components = { Item.leather, Item.chainCopper, Item.chainSilver, Item.chainGold, Item.chainRustedIron, Item.chainIron, Item.chainMithril, Item.chainAdamantium, Item.chainAncientMetal, Item.ingotCopper, Item.ingotSilver, Item.ingotGold, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotAncientMetal };
/*    */     
/* 59 */     for (int i = 0; i < components.length; i++) {
/*    */       
/* 61 */       Item component = components[i];
/*    */       
/* 63 */       if (component.materials.size() != 1) {
/* 64 */         Minecraft.setErrorMessage("addRecipes: armor components can have only 1 material (" + component + ")");
/*    */       }
/* 66 */       addHelmetRecipe(ItemArmor.getMatchingArmor(ItemHelmet.class, component.getExclusiveMaterial(), component instanceof ItemChain), component);
/* 67 */       addCuirassRecipe(ItemArmor.getMatchingArmor(ItemCuirass.class, component.getExclusiveMaterial(), component instanceof ItemChain), component);
/* 68 */       addLeggingsRecipe(ItemArmor.getMatchingArmor(ItemLeggings.class, component.getExclusiveMaterial(), component instanceof ItemChain), component);
/* 69 */       addBootsRecipe(ItemArmor.getMatchingArmor(ItemBoots.class, component.getExclusiveMaterial(), component instanceof ItemChain), component);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */