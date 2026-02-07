/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMattock
/*    */   extends ItemShovel
/*    */ {
/*    */   protected ItemMattock(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 14 */     return "mattock";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHarvestEfficiency(Block block) {
/* 19 */     return super.getBaseHarvestEfficiency(block) * 0.75F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlock() {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 29 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDecayRateForBreakingBlock(Block block) {
/* 34 */     return super.getBaseDecayRateForBreakingBlock(block) * 0.8F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 39 */     RaycastCollision rc = player.getSelectedObject(partial_tick, true);
/*    */     
/* 41 */     if (rc == null || !rc.isBlock()) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (rc.face_hit.isTop()) {
/* 45 */       return ItemHoe.tryTillSoil(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit, player, player.getHeldItemStack());
/*    */     }
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onBlockDestroyed(BlockBreakInfo info) {
/* 52 */     if (!info.world.isRemote) {
/*    */       
/* 54 */       Block block = Block.getBlock(info.block_id);
/*    */       
/* 56 */       if (block instanceof BlockCrops && isEffectiveAgainstBlock(block, info.getMetadata())) {
/*    */         
/* 58 */         BlockCrops crops = (BlockCrops)block;
/*    */         
/* 60 */         if (!crops.isDead() && crops.isMature(info.getMetadata()) && Math.random() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.fertility, info.getHarvesterItemStack())) {
/* 61 */           BlockFarmland.setFertilized(info.world, info.x, info.y - 1, info.z, true);
/*    */         }
/*    */       } 
/*    */     } 
/* 65 */     return super.onBlockDestroyed(info);
/*    */   }
/*    */ 
/*    */   
/*    */   public Class[] getSimilarClasses() {
/* 70 */     return new Class[] { ItemHoe.class, ItemShovel.class };
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMattock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */