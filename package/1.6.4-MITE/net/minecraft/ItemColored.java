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
/*    */ public class ItemColored
/*    */   extends ItemBlock
/*    */ {
/*    */   private String[] blockNames;
/*    */   
/*    */   public ItemColored(Block block) {
/* 34 */     super(block);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
/* 40 */     return getBlock().getRenderColor(par1ItemStack.getItemSubtype());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIconFromSubtype(int par1) {
/* 49 */     return getBlock().getIcon(0, par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int par1) {
/* 57 */     return par1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemColored setBlockNames(String[] par1ArrayOfStr) {
/* 65 */     this.blockNames = par1ArrayOfStr;
/* 66 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 75 */     if (par1ItemStack == null) {
/* 76 */       return getUnlocalizedName();
/*    */     }
/* 78 */     if (this.blockNames == null)
/*    */     {
/* 80 */       return super.getUnlocalizedName(par1ItemStack);
/*    */     }
/*    */ 
/*    */     
/* 84 */     int var2 = par1ItemStack.getItemSubtype();
/* 85 */     return (var2 >= 0 && var2 < this.blockNames.length) ? (super.getUnlocalizedName(par1ItemStack) + "." + this.blockNames[var2]) : super.getUnlocalizedName(par1ItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemColored.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */