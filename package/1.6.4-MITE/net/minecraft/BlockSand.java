/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BlockSand
/*    */   extends BlockFalling
/*    */ {
/*    */   public BlockSand(int id) {
/*  9 */     super(id, Material.sand, (new BlockConstants()).setNeverConnectsWithFence().setUseNewSandPhysics());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMetadataNotes() {
/* 14 */     return "Cactus kill count is kept in the sand beneath it, with a maximum value of 7";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValidMetadata(int metadata) {
/* 19 */     return (metadata >= 0 && metadata <= BlockCactus.getKillCountBits());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockSand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */