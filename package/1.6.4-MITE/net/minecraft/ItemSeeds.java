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
/*    */ public class ItemSeeds
/*    */   extends ItemFood
/*    */ {
/*    */   private int blockType;
/*    */   private int soilBlockID;
/*    */   
/*    */   public ItemSeeds(int id, int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, int crop_block_id, int soil_block_id, String texture) {
/* 54 */     super(id, Material.seed, satiation, nutrition, has_protein, has_essential_fats, has_phytonutrients, texture);
/* 55 */     this.blockType = crop_block_id;
/* 56 */     this.soilBlockID = soil_block_id;
/* 57 */     setMaxStackSize(64);
/* 58 */     setCraftingDifficultyAsComponent(25.0F);
/* 59 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */     
/* 61 */     setPlantProduct();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 66 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*    */     
/* 68 */     if (rc == null || !rc.isBlock()) {
/* 69 */       return false;
/*    */     }
/* 71 */     if (player.worldObj.areSkillsEnabled() && !player.hasSkill(Skill.FARMING)) {
/* 72 */       return false;
/*    */     }
/* 74 */     if (rc.face_hit.isTop() && rc.getBlockHitID() == this.soilBlockID && rc.isNeighborAirBlock()) {
/* 75 */       return player.tryPlaceHeldItemAsBlock(rc, Block.getBlock(this.blockType));
/*    */     }
/* 77 */     if (rc.getBlockHit() instanceof BlockCrops || rc.getBlockHit() instanceof BlockStem || (rc.face_hit.isTop() && rc.getBlockHit() instanceof BlockFarmland)) {
/*    */       
/* 79 */       player.cancelRightClick();
/* 80 */       return true;
/*    */     } 
/*    */     
/* 83 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCompostingValue() {
/* 93 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSeeds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */