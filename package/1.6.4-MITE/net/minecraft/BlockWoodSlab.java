/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWoodSlab
/*    */   extends BlockHalfSlab
/*    */ {
/* 11 */   public static final String[] woodType = new String[] { "oak", "spruce", "birch", "jungle" };
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockWoodSlab(int i, boolean bl) {
/* 16 */     super(i, bl, Material.wood);
/* 17 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int i, int j) {
/* 22 */     return Block.C.getIcon(i, j & 0x7);
/*    */   }
/*    */ 
/*    */   
/*    */   public int idDropped(int i, Random random, int j) {
/* 27 */     return Block.bT.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack createStackedBlock(int i) {
/* 32 */     return new ItemStack(Block.bT.blockID, 2, i & 0x7);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFullSlabName(int i) {
/* 37 */     if (i < 0 || i >= woodType.length) {
/* 38 */       i = 0;
/*    */     }
/* 40 */     return getUnlocalizedName() + "." + woodType[i];
/*    */   }
/*    */ 
/*    */   
/*    */   public void getSubBlocks(int i, CreativeTabs creativeTabs, List<ItemStack> list) {
/* 45 */     if (i == Block.bS.blockID)
/*    */       return; 
/* 47 */     for (byte b = 0; b < 4; b++)
/* 48 */       list.add(new ItemStack(i, 1, b)); 
/*    */   }
/*    */   
/*    */   public void registerIcons(IconRegister iconRegister) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockWoodSlab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */