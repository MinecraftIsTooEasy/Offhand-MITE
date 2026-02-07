/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFurnaceNetherrack
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockFurnaceNetherrack(int par1, boolean par2) {
/*  9 */     super(par1, Material.netherrack, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 14 */     this.blockIcon = par1IconRegister.registerIcon("furnace/netherrack/side");
/* 15 */     this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "furnace/netherrack/front_on" : "furnace/netherrack/front_off");
/* 16 */     this.furnaceIconTop = par1IconRegister.registerIcon("furnace/netherrack/top");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdleBlockID() {
/* 21 */     return Block.furnaceNetherrackIdle.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getActiveBlockID() {
/* 26 */     return Block.furnaceNetherrackBurning.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxHeatLevel() {
/* 31 */     return 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFurnaceNetherrack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */