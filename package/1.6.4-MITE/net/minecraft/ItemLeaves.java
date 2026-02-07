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
/*    */ public class ItemLeaves
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemLeaves(Block block) {
/* 15 */     super(block);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int par1) {
/* 23 */     return par1 | 0x4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIconFromSubtype(int par1) {
/* 31 */     return Block.leaves.getIcon(0, par1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/* 36 */     int var3 = par1ItemStack.getItemSubtype();
/* 37 */     return ((var3 & 0x1) == 1) ? ColorizerFoliage.getFoliageColorPine() : (((var3 & 0x2) == 2) ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 46 */     if (par1ItemStack == null) {
/* 47 */       return getUnlocalizedName();
/*    */     }
/* 49 */     int var2 = par1ItemStack.getItemSubtype();
/*    */     
/* 51 */     if (var2 < 0 || var2 >= BlockLeaves.LEAF_TYPES.length)
/*    */     {
/* 53 */       var2 = 0;
/*    */     }
/*    */     
/* 56 */     return getUnlocalizedName() + "." + BlockLeaves.LEAF_TYPES[var2];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemLeaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */