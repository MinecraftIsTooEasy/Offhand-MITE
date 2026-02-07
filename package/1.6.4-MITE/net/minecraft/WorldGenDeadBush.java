/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class WorldGenDeadBush
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int deadBushID;
/*    */   private Block block;
/*    */   
/*    */   public WorldGenDeadBush(int par1) {
/* 13 */     this.deadBushID = par1;
/* 14 */     this.block = Block.getBlock(this.deadBushID);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 19 */     boolean is_nether_world = par1World.isTheNether();
/* 20 */     int metadata = is_nether_world ? 1 : 0;
/*    */     
/*    */     int var11;
/*    */     
/* 24 */     for (boolean var6 = false; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; par4--);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     for (int var7 = 0; var7 < 4; var7++) {
/*    */       
/* 31 */       int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
/* 32 */       int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 33 */       int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
/*    */ 
/*    */ 
/*    */       
/* 37 */       if (par1World.isAirBlock(var8, var9, var10) && this.block.canOccurAt(par1World, var8, var9, var10, metadata))
/*    */       {
/* 39 */         par1World.setBlock(var8, var9, var10, this.deadBushID, metadata, 2);
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenDeadBush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */