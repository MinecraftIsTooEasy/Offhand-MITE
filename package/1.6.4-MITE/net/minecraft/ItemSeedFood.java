/*    */ package net.minecraft;
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
/*    */ public class ItemSeedFood
/*    */   extends ItemFood
/*    */ {
/*    */   private int cropId;
/*    */   private int soilId;
/*    */   
/*    */   public ItemSeedFood(int id, int satiation, int nutrition, boolean has_protein, boolean has_phytonutrients, int crop_block_id, int soil_block_id, String texture) {
/* 21 */     super(id, Material.seed, satiation, nutrition, has_protein, false, has_phytonutrients, texture);
/* 22 */     this.cropId = crop_block_id;
/* 23 */     this.soilId = soil_block_id;
/*    */     
/* 25 */     setPlantProduct();
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
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 61 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*    */     
/* 63 */     if (rc == null || !rc.isBlock()) {
/* 64 */       return false;
/*    */     }
/* 66 */     if (player.worldObj.areSkillsEnabled() && !player.hasSkill(Skill.FARMING)) {
/* 67 */       return false;
/*    */     }
/* 69 */     if (rc.face_hit.isTop() && rc.getBlockHitID() == this.soilId && rc.isNeighborAirBlock()) {
/* 70 */       return player.tryPlaceHeldItemAsBlock(rc, Block.getBlock(this.cropId));
/*    */     }
/* 72 */     if (rc.getBlockHit() instanceof BlockCrops || rc.getBlockHit() instanceof BlockStem || (rc.face_hit.isTop() && rc.getBlockHit() instanceof BlockFarmland)) {
/*    */       
/* 74 */       player.cancelRightClick();
/* 75 */       return true;
/*    */     } 
/*    */     
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/* 83 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSeedFood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */