/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipesIngots
/*    */ {
/*  7 */   private static final Object[][] ingot_based_items = new Object[][] { { Item.ingotCopper, Item.ingotSilver, Item.ingotGold, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotAncientMetal }, { Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, null, Integer.valueOf(9) } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 13 */   private static final Object[][] nugget_based_items = new Object[][] { { Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Item.mithrilNugget, Item.adamantiumNugget, Item.ancientMetalNugget, null }, { Item.ingotCopper, Item.ingotSilver, Item.ingotGold, Item.ingotIron, Item.ingotMithril, Item.ingotAdamantium, Item.ingotAncientMetal, { "???", "???", "???" } } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   private static final Object[][] shard_3x3_based_items = new Object[][] { { Item.shardEmerald, Item.shardDiamond, Item.shardNetherQuartz, Item.shardGlass, Item.shardObsidian, null }, { Item.emerald, Item.diamond, Item.netherQuartz, Block.thinGlass, Block.obsidian, { "???", "???", "???" } } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   private static final Object[][] block_or_crystal_dismantling_based_items = new Object[][] { { Item.emerald, Item.diamond, Item.netherQuartz, Block.thinGlass, Block.obsidian }, { Item.shardEmerald, Item.shardDiamond, Item.shardNetherQuartz, Item.shardGlass, Item.shardObsidian, null, Integer.valueOf(9) } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   private Object[][] recipeItems = new Object[][] { { Block.blockCopper, new ItemStack(Item.ingotCopper, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockSilver, new ItemStack(Item.ingotSilver, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockGold, new ItemStack(Item.ingotGold, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockIron, new ItemStack(Item.ingotIron, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockMithril, new ItemStack(Item.ingotMithril, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockAdamantium, new ItemStack(Item.ingotAdamantium, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockAncientMetal, new ItemStack(Item.ingotAncientMetal, 9), Integer.valueOf(Skill.BLACKSMITHING.id) }, { Block.blockDiamond, new ItemStack(Item.diamond, 9), Integer.valueOf(Skill.MASONRY.id + Skill.FINE_ARTS.id) }, { Block.blockEmerald, new ItemStack(Item.emerald, 9), Integer.valueOf(Skill.MASONRY.id + Skill.FINE_ARTS.id) }, { Block.blockLapis, new ItemStack(Item.dyePowder, 9, 4), Integer.valueOf(Skill.MASONRY.id + Skill.FINE_ARTS.id) }, { Block.blockRedstone, new ItemStack(Item.redstone, 9), Integer.valueOf(Skill.MASONRY.id) }, { Block.coalBlock, new ItemStack(Item.coal, 9, 0), Integer.valueOf(0) }, { Block.hay, new ItemStack(Item.wheat, 9), Integer.valueOf(Skill.FARMING.id) }, { Block.blockNetherQuartz, new ItemStack(Item.netherQuartz, 4), Integer.valueOf(Skill.MASONRY.id + Skill.FINE_ARTS.id), Boolean.valueOf(true) }, { Item.flint, new ItemStack(Item.chipFlint, 4), Integer.valueOf(0) } };
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
/*    */   public void addRecipes(CraftingManager par1CraftingManager) {
/* 57 */     for (int var2 = 0; var2 < this.recipeItems.length; var2++) {
/*    */       
/* 59 */       Object var3 = this.recipeItems[var2][0];
/*    */       
/* 61 */       ItemStack var4 = (ItemStack)this.recipeItems[var2][1];
/* 62 */       int skillset = ((Integer)this.recipeItems[var2][2]).intValue();
/*    */       
/* 64 */       (new Object[4])[0] = "##"; (new Object[4])[1] = "##"; (new Object[4])[2] = Character.valueOf('#'); (new Object[4])[3] = var4; (new Object[5])[0] = "###"; (new Object[5])[1] = "###"; (new Object[5])[2] = "###"; (new Object[5])[3] = Character.valueOf('#'); (new Object[5])[4] = var4; par1CraftingManager.addRecipe((var3 instanceof Block) ? new ItemStack((Block)var3) : ((var3 instanceof Item) ? new ItemStack((Item)var3) : (ItemStack)var3), (var4.stackSize == 4) ? new Object[4] : new Object[5]).setSkillset(skillset);
/*    */       
/* 66 */       if ((this.recipeItems[var2]).length == 4) {
/*    */         
/* 68 */         Object object = this.recipeItems[var2][3];
/* 69 */         par1CraftingManager.addRecipe(var4, new Object[] { "#", Character.valueOf('#'), var3 }).setSkillset((object instanceof Boolean) ? skillset : ((Integer)object).intValue());
/*    */       }
/*    */       else {
/*    */         
/* 73 */         par1CraftingManager.addRecipe(var4, new Object[] { "#", Character.valueOf('#'), var3 });
/*    */       } 
/*    */     } 
/*    */     
/* 77 */     par1CraftingManager.addShapelessRecipes(ingot_based_items, -1, false);
/* 78 */     par1CraftingManager.addRecipes(nugget_based_items, -1);
/*    */     
/* 80 */     par1CraftingManager.addRecipes(shard_3x3_based_items, -1);
/*    */     
/* 82 */     par1CraftingManager.addShapelessRecipes(block_or_crystal_dismantling_based_items, -1, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesIngots.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */