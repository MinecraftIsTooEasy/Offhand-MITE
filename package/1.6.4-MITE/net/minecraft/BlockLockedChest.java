/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLockedChest
/*    */   extends Block
/*    */ {
/*    */   protected BlockLockedChest(int i) {
/* 11 */     super(i, Material.wood);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlaceBlockAt(World world, int i, int j, int k) {
/* 16 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTick(World world, int i, int j, int k, Random random) {
/* 21 */     world.setBlockToAir(i, j, k);
/*    */   }
/*    */   
/*    */   public void registerIcons(IconRegister iconRegister) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockLockedChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */