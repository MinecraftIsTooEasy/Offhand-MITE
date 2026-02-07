/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockOreStorage
/*    */   extends Block
/*    */ {
/*    */   public BlockOreStorage(int par1, Material material) {
/* 13 */     super(par1, material, new BlockConstants());
/*    */     
/* 15 */     modifyMinHarvestLevel(1);
/* 16 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */     
/* 18 */     setMaxStackSize(4);
/* 19 */     setHardnessRelativeToWood(BlockHardness.log);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCraftingDifficultyAsComponent(int metadata) {
/* 24 */     if (this.blockMaterial.isMetal()) {
/* 25 */       return ItemIngot.getCraftingDifficultyAsComponent(this.blockMaterial) * 9.0F;
/*    */     }
/* 27 */     return ItemRock.getCraftingDifficultyAsComponent(this.blockMaterial) * ((this.blockMaterial == Material.quartz) ? 4 : 9);
/*    */   }
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 32 */     if (info.wasExploded()) {
/*    */       
/* 34 */       if (this == Block.blockEmerald)
/* 35 */         return dropBlockAsEntityItem(info, Item.shardEmerald.itemID, 0, 9, 1.0F); 
/* 36 */       if (this == Block.blockDiamond) {
/* 37 */         return dropBlockAsEntityItem(info, Item.shardDiamond.itemID, 0, 9, 1.0F);
/*    */       }
/*    */     } 
/* 40 */     return super.dropBlockAsEntityItem(info);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockOreStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */