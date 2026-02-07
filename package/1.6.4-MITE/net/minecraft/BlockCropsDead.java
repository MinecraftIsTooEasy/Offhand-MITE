/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCropsDead
/*    */   extends BlockCrops
/*    */ {
/*    */   protected BlockCropsDead(int block_id, int num_growth_stages) {
/* 11 */     super(block_id, num_growth_stages);
/* 12 */     setTickRandomly(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 17 */     return "Bits 1, 2, and 4 used to track growth";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 22 */     return (metadata >= 0 && metadata < 8);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean fertilize(World world, int x, int y, int z, ItemStack item_stack) {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int side, int metadata) {
/* 32 */     return this.iconArray[getGrowthStage(metadata)];
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 37 */     this.iconArray = new Icon[this.num_growth_stages];
/*    */     
/* 39 */     for (int i = 0; i < this.num_growth_stages; i++) {
/* 40 */       this.iconArray[i] = par1IconRegister.registerIcon("crops/" + getTextureName() + "/dead/" + i);
/*    */     }
/*    */   }
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 45 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDead() {
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockCropsDead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */