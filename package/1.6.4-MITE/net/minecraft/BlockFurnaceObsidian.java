/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFurnaceObsidian
/*    */   extends BlockFurnace
/*    */ {
/*    */   protected BlockFurnaceObsidian(int par1, boolean par2) {
/*  9 */     super(par1, Material.obsidian, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerIcons(IconRegister par1IconRegister) {
/* 14 */     this.blockIcon = par1IconRegister.registerIcon("furnace/obsidian/side");
/* 15 */     this.furnaceIconFront = par1IconRegister.registerIcon(this.isActive ? "furnace/obsidian/front_on" : "furnace/obsidian/front_off");
/* 16 */     this.furnaceIconTop = par1IconRegister.registerIcon("furnace/obsidian/top");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getIdleBlockID() {
/* 21 */     return Block.furnaceObsidianIdle.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getActiveBlockID() {
/* 26 */     return Block.furnaceObsidianBurning.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxHeatLevel() {
/* 31 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockFurnaceObsidian.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */