/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFurnaceHardenedClay
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockFurnaceHardenedClay(int par1, boolean par2) {
/*  9 */     super(par1, Material.hardened_clay, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 14 */     this.blockIcon = par1IconRegister.registerIcon("furnace/hardened_clay/side");
/* 15 */     this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "furnace/hardened_clay/front_on" : "furnace/hardened_clay/front_off");
/* 16 */     this.furnaceIconTop = par1IconRegister.registerIcon("furnace/hardened_clay/top");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdleBlockID() {
/* 21 */     return Block.furnaceHardenedClayIdle.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getActiveBlockID() {
/* 26 */     return Block.furnaceHardenedClayBurning.blockID;
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


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFurnaceHardenedClay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */