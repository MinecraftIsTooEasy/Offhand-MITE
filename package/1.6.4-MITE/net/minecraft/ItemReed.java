/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemReed
/*     */   extends Item
/*     */ {
/*     */   private int spawnID;
/*     */   
/*     */   public ItemReed(int par1, Block par2Block, String texture) {
/*  12 */     super(par1, par2Block.blockMaterial, texture);
/*  13 */     this.spawnID = par2Block.blockID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 137 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/* 139 */     if (rc == null || !rc.isBlock()) {
/* 140 */       return false;
/*     */     }
/* 142 */     if (player.worldObj.areSkillsEnabled() && this.spawnID == Block.reed.blockID && !player.hasSkill(Skill.FARMING)) {
/* 143 */       return false;
/*     */     }
/* 145 */     return player.tryPlaceHeldItemAsBlock(rc, Block.getBlock(this.spawnID));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryPlaceAsBlock(RaycastCollision rc, Block block, EntityPlayer player, ItemStack item_stack) {
/* 195 */     if (block == Block.reed) {
/*     */ 
/*     */ 
/*     */       
/* 199 */       if (rc.getBlockHit() == block) {
/*     */         
/* 201 */         int i = rc.block_hit_x;
/* 202 */         int j = rc.block_hit_y;
/* 203 */         int k = rc.block_hit_z;
/*     */         
/* 205 */         if (Block.reed.tryPlaceBlock(rc.world, i, ++j, k, EnumFace.TOP, 0, player, true, true)) {
/* 206 */           return true;
/*     */         }
/*     */       } 
/* 209 */       int x = rc.neighbor_block_x;
/* 210 */       int y = rc.neighbor_block_y;
/* 211 */       int z = rc.neighbor_block_z;
/*     */       
/* 213 */       if (rc.world.getBlock(x, y, z) != block) {
/* 214 */         return super.tryPlaceAsBlock(rc, block, player, item_stack);
/*     */       }
/* 216 */       if (block.isLegalAt(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, 0) && block.canReplaceBlock(0, rc.getBlockHit(), rc.block_hit_metadata)) {
/* 217 */         return super.tryPlaceAsBlock(rc, block, player, item_stack);
/*     */       }
/* 219 */       if (Block.reed.tryPlaceBlock(rc.world, x, ++y, z, EnumFace.TOP, 0, player, true, true)) {
/* 220 */         return true;
/*     */       }
/*     */     } 
/* 223 */     return super.tryPlaceAsBlock(rc, block, player, item_stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemReed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */