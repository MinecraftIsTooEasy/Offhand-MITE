/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSnowBlock
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemSnowBlock(Block block) {
/*  9 */     super(block);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean tryPlaceAsBlock(RaycastCollision rc, Block block, EntityPlayer player, ItemStack item_stack) {
/* 16 */     if (block != Block.blockSnow) {
/* 17 */       Minecraft.setErrorMessage("tryPlaceAsBlock: block!=Block.blockSnow");
/*    */     }
/*    */     
/* 20 */     if (block != Block.blockSnow || block.canReplaceBlock(block.getMetadataForPlacement(player.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, item_stack, player, EnumFace.TOP, 0.0F, 0.0F, 0.0F), rc.getBlockHit(), rc.block_hit_metadata)) {
/* 21 */       return super.tryPlaceAsBlock(rc, block, player, item_stack);
/*    */     }
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
/* 63 */     return (ItemSnow.tryAddToExistingSnow(rc, player, 8) || super.tryPlaceAsBlock(rc, block, player, item_stack));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSnowBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */