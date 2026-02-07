/*    */ package net.minecraft;
/*    */ 
/*    */ public class ItemMinecart
/*    */   extends Item {
/*  5 */   private static final IBehaviorDispenseItem dispenserMinecartBehavior = new BehaviorDispenseMinecart();
/*    */ 
/*    */   
/*    */   public int minecartType;
/*    */ 
/*    */   
/*    */   public ItemMinecart(int par1, int par2, String texture) {
/* 12 */     super(par1, Material.iron, texture);
/*    */     
/* 14 */     setMaxStackSize(1);
/* 15 */     this.minecartType = par2;
/* 16 */     setCreativeTab(CreativeTabs.tabTransport);
/* 17 */     BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserMinecartBehavior);
/*    */     
/* 19 */     if (par2 == 1) {
/* 20 */       addMaterial(new Material[] { Material.wood });
/* 21 */     } else if (par2 == 2) {
/* 22 */       addMaterial(new Material[] { Material.stone });
/* 23 */     } else if (par2 == 3) {
/* 24 */       addMaterial(new Material[] { Material.tnt });
/*    */     } 
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
/* 63 */     if (rc != null && rc.isBlock() && BlockRailBase.isRailBlock(rc.getBlockHitID()) && rc.canPlayerEditBlockHit(player, player.getHeldItemStack())) {
/*    */       
/* 65 */       if (player.onClient()) {
/*    */         
/* 67 */         player.swingArm();
/*    */       }
/*    */       else {
/*    */         
/* 71 */         World world = player.getWorld();
/*    */         
/* 73 */         EntityMinecart minecart = EntityMinecart.createMinecart(world, (rc.block_hit_x + 0.5F), (rc.block_hit_y + 0.5F), (rc.block_hit_z + 0.5F), this.minecartType);
/*    */         
/* 75 */         ItemStack item_stack = player.getHeldItemStack();
/*    */         
/* 77 */         if (item_stack.hasDisplayName()) {
/* 78 */           minecart.setMinecartName(item_stack.getDisplayName());
/*    */         }
/* 80 */         world.spawnEntityInWorld(minecart);
/*    */         
/* 82 */         Block.rail.makeSoundWhenPlaced(world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, 0);
/*    */         
/* 84 */         if (!player.inCreativeMode()) {
/* 85 */           player.convertOneOfHeldItem((ItemStack)null);
/*    */         }
/*    */       } 
/* 88 */       return true;
/*    */     } 
/*    */     
/* 91 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 96 */     return (this.minecartType == 0) ? "empty" : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */