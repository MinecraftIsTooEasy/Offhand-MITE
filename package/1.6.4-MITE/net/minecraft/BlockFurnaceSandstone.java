/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFurnaceSandstone
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockFurnaceSandstone(int par1, boolean par2) {
/*  9 */     super(par1, Material.sand, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 14 */     this.blockIcon = par1IconRegister.registerIcon("furnace/sandstone/side");
/* 15 */     this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "furnace/sandstone/front_on" : "furnace/sandstone/front_off");
/* 16 */     this.furnaceIconTop = par1IconRegister.registerIcon("furnace/sandstone/top");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdleBlockID() {
/* 21 */     return Block.furnaceSandstoneIdle.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getActiveBlockID() {
/* 26 */     return Block.furnaceSandstoneBurning.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxHeatLevel() {
/* 31 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOven() {
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFurnaceSandstone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */