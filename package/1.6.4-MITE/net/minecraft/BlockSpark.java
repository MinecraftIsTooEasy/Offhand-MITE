/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSpark
/*    */   extends BlockFire
/*    */ {
/*    */   protected BlockSpark(int id) {
/* 11 */     super(id);
/* 12 */     setTickRandomly(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 17 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 22 */     return (metadata == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
/* 27 */     if (Block.portal.tryToCreatePortal(par1World, par2, par3, par4)) {
/*    */       return;
/*    */     }
/* 30 */     par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
/* 35 */     if (canNeighborBurn(par1World, par2, par3, par4) || par1World.getBlock(par2, par3 - 1, par4) == Block.netherrack) {
/* 36 */       par1World.setBlock(par2, par3, par4, Block.fire.blockID);
/*    */     } else {
/* 38 */       par1World.setBlockToAir(par2, par3, par4);
/*    */     } 
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 45 */     this.blockIcon = par1IconRegister.registerIcon(getTextureName());
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getFireIcon(int par1) {
/* 50 */     return this.blockIcon;
/*    */   }
/*    */ 
/*    */   
/*    */   public Icon getIcon(int par1, int par2) {
/* 55 */     return this.blockIcon;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSpark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */