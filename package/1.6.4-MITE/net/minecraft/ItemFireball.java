/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class ItemFireball
/*     */   extends Item
/*     */ {
/*     */   public ItemFireball(int par1) {
/*   8 */     super(par1, new Material[] { Material.gunpowder, Material.blaze, Material.coal }, "fireball");
/*   9 */     setCreativeTab(CreativeTabs.tabMisc);
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
/*     */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/*  80 */     RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*     */     
/*  82 */     if (rc != null && rc.isBlock() && rc.isNeighborAirBlock()) {
/*     */       
/*  84 */       if (!rc.canPlayerEditNeighborOfBlockHit(player, player.getHeldItemStack())) {
/*  85 */         return false;
/*     */       }
/*  87 */       if (player.onClient()) {
/*     */         
/*  89 */         player.swingArm();
/*     */       }
/*     */       else {
/*     */         
/*  93 */         World world = player.getWorld();
/*     */         
/*  95 */         int x = rc.neighbor_block_x;
/*  96 */         int y = rc.neighbor_block_y;
/*  97 */         int z = rc.neighbor_block_z;
/*     */         
/*  99 */         world.playSoundAtBlock(x, y, z, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
/*     */         
/* 101 */         world.setBlock(x, y, z, Block.fire.blockID);
/*     */         
/* 103 */         if (!player.inCreativeMode()) {
/* 104 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 107 */       return true;
/*     */     } 
/*     */     
/* 110 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemFireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */