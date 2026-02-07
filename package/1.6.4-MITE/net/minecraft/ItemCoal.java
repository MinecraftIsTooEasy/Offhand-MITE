/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCoal
/*    */   extends Item
/*    */ {
/*    */   private Icon field_111220_a;
/*    */   
/*    */   public ItemCoal(int par1) {
/* 13 */     super(par1, Material.coal, "coal");
/*    */ 
/*    */ 
/*    */     
/* 17 */     setMaxStackSize(16);
/* 18 */     setCraftingDifficultyAsComponent(25.0F);
/* 19 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack par1ItemStack) {
/* 29 */     return (par1ItemStack != null && par1ItemStack.getItemSubtype() == 1) ? "item.charcoal" : "item.coal";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 37 */     par3List.add(new ItemStack(par1, 1, 0));
/* 38 */     par3List.add(new ItemStack(par1, 1, 1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Icon getIconFromSubtype(int par1) {
/* 46 */     return (par1 == 1) ? this.field_111220_a : super.getIconFromSubtype(par1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 51 */     super.registerIcons(par1IconRegister);
/* 52 */     this.field_111220_a = par1IconRegister.registerIcon("charcoal");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBurnTime(ItemStack item_stack) {
/* 57 */     return 1600;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeatLevel(ItemStack item_stack) {
/* 62 */     if (item_stack.getItemSubtype() == 1) {
/* 63 */       return 1;
/*    */     }
/* 65 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemCoal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */