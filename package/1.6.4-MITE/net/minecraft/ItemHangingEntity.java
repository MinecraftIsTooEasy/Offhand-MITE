/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemHangingEntity
/*    */   extends Item
/*    */ {
/*    */   private final Class hangingEntityClass;
/*    */   
/*    */   public ItemHangingEntity(int par1, Class par2Class, String texture) {
/* 11 */     super(par1, texture);
/* 12 */     this.hangingEntityClass = par2Class;
/* 13 */     setCreativeTab(CreativeTabs.tabDecorations);
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
/* 58 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*    */     
/* 60 */     if (rc != null && rc.isBlock() && rc.face_hit.isSide() && rc.isNeighborAirBlock() && rc.canPlayerEditBlockHit(player, player.getHeldItemStack())) {
/*    */       
/* 62 */       World world = player.getWorld();
/*    */       
/* 64 */       EntityHanging hanging_entity = createHangingEntity(world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, Direction.facingToDirection[rc.face_hit.ordinal()]);
/*    */       
/* 66 */       if (hanging_entity == null || !hanging_entity.onValidSurface()) {
/* 67 */         return false;
/*    */       }
/* 69 */       if (player.onClient()) {
/*    */         
/* 71 */         player.swingArm();
/*    */       }
/*    */       else {
/*    */         
/* 75 */         world.spawnEntityInWorld(hanging_entity);
/*    */         
/* 77 */         if (!player.inCreativeMode()) {
/* 78 */           player.convertOneOfHeldItem((ItemStack)null);
/*    */         }
/*    */       } 
/* 81 */       return true;
/*    */     } 
/*    */     
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private EntityHanging createHangingEntity(World par1World, int par2, int par3, int par4, int par5) {
/* 92 */     return (this.hangingEntityClass == EntityPainting.class) ? new EntityPainting(par1World, par2, par3, par4, par5) : ((this.hangingEntityClass == EntityItemFrame.class) ? new EntityItemFrame(par1World, par2, par3, par4, par5) : null);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemHangingEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */