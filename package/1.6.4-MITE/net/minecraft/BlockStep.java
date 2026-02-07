/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
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
/*    */ public class BlockStep
/*    */   extends BlockHalfSlab
/*    */ {
/* 20 */   public static final String[] blockStepTypes = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz" };
/*    */ 
/*    */   
/*    */   private Icon theIcon;
/*    */ 
/*    */   
/*    */   public BlockStep(int i, boolean bl) {
/* 27 */     super(i, bl, Material.e);
/* 28 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int i, int j) {
/* 33 */     int k = j & 0x7;
/* 34 */     if (this.isDoubleSlab && (j & 0x8) != 0) {
/* 35 */       i = 1;
/*    */     }
/* 37 */     if (k == 0) {
/* 38 */       if (i == 1 || i == 0) return this.blockIcon; 
/* 39 */       return this.theIcon;
/* 40 */     }  if (k == 1)
/* 41 */       return Block.sandStone.getBlockTextureFromSide(i); 
/* 42 */     if (k == 2) return Block.C.getBlockTextureFromSide(i); 
/* 43 */     if (k == 3)
/* 44 */       return Block.cobblestone.getBlockTextureFromSide(i); 
/* 45 */     if (k == 4)
/* 46 */       return Block.brick.getBlockTextureFromSide(i); 
/* 47 */     if (k == 5)
/* 48 */       return Block.stoneBrick.getIcon(i, 0); 
/* 49 */     if (k == 6)
/* 50 */       return Block.netherBrick.getBlockTextureFromSide(1); 
/* 51 */     if (k == 7) {
/* 52 */       return Block.blockNetherQuartz.getBlockTextureFromSide(i);
/*    */     }
/*    */     
/* 55 */     return this.blockIcon;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister iconRegister) {
/* 60 */     this.blockIcon = iconRegister.registerIcon("stone_slab_top");
/* 61 */     this.theIcon = iconRegister.registerIcon("stone_slab_side");
/*    */   }
/*    */ 
/*    */   
/*    */   public int idDropped(int i, Random random, int j) {
/* 66 */     return Block.ap.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack createStackedBlock(int i) {
/* 71 */     return new ItemStack(Block.ap.blockID, 2, i & 0x7);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFullSlabName(int i) {
/* 76 */     if (i < 0 || i >= blockStepTypes.length) {
/* 77 */       i = 0;
/*    */     }
/* 79 */     return getUnlocalizedName() + "." + blockStepTypes[i];
/*    */   }
/*    */ 
/*    */   
/*    */   public void getSubBlocks(int i, CreativeTabs creativeTabs, List<ItemStack> list) {
/* 84 */     if (i == Block.ao.blockID)
/*    */       return; 
/* 86 */     for (byte b = 0; b <= 7; b++) {
/* 87 */       if (b != 2)
/* 88 */         list.add(new ItemStack(i, 1, b)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */