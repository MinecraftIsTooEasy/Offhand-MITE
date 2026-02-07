/*    */ package net.minecraft;
/*    */ 
/*    */ public class ItemSpade
/*    */   extends ItemTool
/*    */ {
/*  6 */   private static Block[] blocksEffectiveAgainst = new Block[] { Block.grass, Block.dirt, Block.J, Block.K, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium };
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemSpade(int i, EnumToolMaterial enumToolMaterial) {
/* 11 */     super(i, 1.0F, enumToolMaterial, blocksEffectiveAgainst);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHarvestBlock(Block block) {
/* 16 */     if (block == Block.snow) return true; 
/* 17 */     if (block == Block.blockSnow) return true; 
/* 18 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemSpade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */