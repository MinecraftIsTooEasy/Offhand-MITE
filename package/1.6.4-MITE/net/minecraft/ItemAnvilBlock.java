/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class ItemAnvilBlock
/*    */   extends ItemMultiTextureTile
/*    */   implements IDamageableItem
/*    */ {
/*    */   public ItemAnvilBlock(BlockAnvil par1Block) {
/*  9 */     super(par1Block, BlockAnvil.statuses);
/* 10 */     setMaxDamage(par1Block.getDurability());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int par1) {
/* 18 */     return par1 << 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockAnvil getBlock() {
/* 23 */     return (BlockAnvil)super.getBlock();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 28 */     return 31;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateSubtypeForDamage(ItemStack item_stack) {
/* 33 */     int damage_stage = getBlock().getDamageStage(item_stack.getItemDamage());
/*    */     
/* 35 */     item_stack.setItemSubtype(damage_stage);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemAnvilBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */