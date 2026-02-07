/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class RecipesMapExtending
/*    */   extends ShapedRecipes
/*    */ {
/*    */   public RecipesMapExtending() {
/*  8 */     super(3, 3, new ItemStack[] { new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.map, 0, 32767), new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.paper) }new ItemStack(Item.emptyMap, 1, 0), true);
/*    */     
/* 10 */     setSkillset(Skill.FINE_ARTS.id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
/* 20 */     if (!super.matches(par1InventoryCrafting, par2World))
/*    */     {
/* 22 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 26 */     ItemStack var3 = null;
/*    */     
/* 28 */     for (int var4 = 0; var4 < par1InventoryCrafting.getSizeInventory() && var3 == null; var4++) {
/*    */       
/* 30 */       ItemStack var5 = par1InventoryCrafting.getStackInSlot(var4);
/*    */       
/* 32 */       if (var5 != null && var5.itemID == Item.map.itemID)
/*    */       {
/* 34 */         var3 = var5;
/*    */       }
/*    */     } 
/*    */     
/* 38 */     if (var3 == null)
/*    */     {
/* 40 */       return false;
/*    */     }
/* 42 */     if (var3.stackSize != 1)
/*    */     {
/* 44 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 48 */     MapData var6 = Item.map.getMapData(var3, par2World);
/* 49 */     return (var6 == null) ? false : ((var6.scale < 4));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CraftingResult getCraftingResult(InventoryCrafting par1InventoryCrafting) {
/* 60 */     ItemStack var2 = null;
/*    */     
/* 62 */     for (int var3 = 0; var3 < par1InventoryCrafting.getSizeInventory() && var2 == null; var3++) {
/*    */       
/* 64 */       ItemStack var4 = par1InventoryCrafting.getStackInSlot(var3);
/*    */       
/* 66 */       if (var4 != null && var4.itemID == Item.map.itemID)
/*    */       {
/* 68 */         var2 = var4;
/*    */       }
/*    */     } 
/*    */     
/* 72 */     var2 = var2.copy();
/* 73 */     var2.stackSize = 1;
/*    */     
/* 75 */     if (var2.getTagCompound() == null)
/*    */     {
/* 77 */       var2.setTagCompound(new NBTTagCompound());
/*    */     }
/*    */     
/* 80 */     var2.getTagCompound().setBoolean("map_is_scaling", true);
/*    */     
/* 82 */     return new CraftingResult(var2, this.difficulty, getSkillsets(), this);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RecipesMapExtending.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */