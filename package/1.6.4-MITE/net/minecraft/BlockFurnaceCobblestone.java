/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFurnaceCobblestone
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockFurnaceCobblestone(int par1, boolean par2) {
/*  9 */     super(par1, Material.stone, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 14 */     this.blockIcon = par1IconRegister.registerIcon("furnace/cobblestone/side");
/* 15 */     this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "furnace/cobblestone/front_on" : "furnace/cobblestone/front_off");
/* 16 */     this.furnaceIconTop = par1IconRegister.registerIcon("furnace/cobblestone/top");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdleBlockID() {
/* 21 */     return Block.furnaceIdle.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getActiveBlockID() {
/* 26 */     return Block.furnaceBurning.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxHeatLevel() {
/* 31 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFurnaceCobblestone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */