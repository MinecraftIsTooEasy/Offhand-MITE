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
/*    */ public class ItemMultiTextureTile
/*    */   extends ItemBlock
/*    */ {
/*    */   private final String[] field_82804_b;
/*    */   
/*    */   public ItemMultiTextureTile(Block block, String[] names) {
/* 32 */     super(block);
/* 33 */     this.field_82804_b = names;
/*    */     
/* 35 */     if (getBlock().getNumSubBlocks() != StringHelper.getNumNonNullStrings(names)) {
/* 36 */       Debug.setErrorMessage("ItemMultiTextureTile: getNumSubBlocks()=" + getBlock().getNumSubBlocks() + ", but length of array is " + names.length);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIconFromSubtype(int par1) {
/* 45 */     return getBlock().getIcon(2, par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int par1) {
/* 53 */     return par1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 62 */     if (par1ItemStack == null) {
/* 63 */       return getUnlocalizedName();
/*    */     }
/* 65 */     int var2 = par1ItemStack.getItemSubtype();
/*    */     
/* 67 */     if (var2 < 0 || var2 >= this.field_82804_b.length)
/*    */     {
/* 69 */       var2 = 0;
/*    */     }
/*    */     
/* 72 */     return getUnlocalizedName() + "." + this.field_82804_b[var2];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMultiTextureTile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */