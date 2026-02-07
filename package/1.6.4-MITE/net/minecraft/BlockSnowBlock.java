/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class BlockSnowBlock
/*    */   extends Block
/*    */ {
/*    */   protected BlockSnowBlock(int par1) {
/* 10 */     super(par1, Material.craftedSnow, new BlockConstants());
/* 11 */     setTickRandomly(true);
/* 12 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */     
/* 14 */     setHardness(BlockHardness.blockSnow);
/* 15 */     setCushioning(0.8F);
/*    */   }
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
/*    */   public boolean melt(World world, int x, int y, int z) {
/* 36 */     return world.setBlock(x, y, z, Block.snow.blockID, 6, 3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 45 */     if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
/* 46 */       return true;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     if ((!par1World.isFreezing(par2, par4) || par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3 + 1, par4) > 11) && BlockSnow.canMelt(par1World, par2, par3, par4)) {
/*    */       
/* 56 */       melt(par1World, par2, par3, par4);
/* 57 */       return true;
/*    */     } 
/*    */     
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int dropBlockAsEntityItem(BlockBreakInfo info) {
/* 65 */     return dropBlockAsEntityItem(info, Item.snowball.itemID, 0, 8, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameDisambiguationForReferenceFile(int metadata) {
/* 70 */     return ReferenceFileWriter.running ? "block" : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName() {
/* 75 */     return super.getUnlocalizedName() + (ReferenceFileWriter.running ? "" : ".block");
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSnowBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */