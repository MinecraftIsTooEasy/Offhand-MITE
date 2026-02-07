/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class WorldGenHellLava
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int hellLavaID;
/*    */   private boolean field_94524_b;
/*    */   
/*    */   public WorldGenHellLava(int par1, boolean par2) {
/* 13 */     this.hellLavaID = par1;
/* 14 */     this.field_94524_b = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 19 */     if (par1World.getBlockId(par3, par4 + 1, par5) != Block.netherrack.blockID)
/*    */     {
/* 21 */       return false;
/*    */     }
/* 23 */     if (par1World.getBlockId(par3, par4, par5) != 0 && par1World.getBlockId(par3, par4, par5) != Block.netherrack.blockID)
/*    */     {
/* 25 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 29 */     int var6 = 0;
/*    */     
/* 31 */     if (par1World.getBlockId(par3 - 1, par4, par5) == Block.netherrack.blockID)
/*    */     {
/* 33 */       var6++;
/*    */     }
/*    */     
/* 36 */     if (par1World.getBlockId(par3 + 1, par4, par5) == Block.netherrack.blockID)
/*    */     {
/* 38 */       var6++;
/*    */     }
/*    */     
/* 41 */     if (par1World.getBlockId(par3, par4, par5 - 1) == Block.netherrack.blockID)
/*    */     {
/* 43 */       var6++;
/*    */     }
/*    */     
/* 46 */     if (par1World.getBlockId(par3, par4, par5 + 1) == Block.netherrack.blockID)
/*    */     {
/* 48 */       var6++;
/*    */     }
/*    */     
/* 51 */     if (par1World.getBlockId(par3, par4 - 1, par5) == Block.netherrack.blockID)
/*    */     {
/* 53 */       var6++;
/*    */     }
/*    */     
/* 56 */     int var7 = 0;
/*    */     
/* 58 */     if (par1World.isAirBlock(par3 - 1, par4, par5))
/*    */     {
/* 60 */       var7++;
/*    */     }
/*    */     
/* 63 */     if (par1World.isAirBlock(par3 + 1, par4, par5))
/*    */     {
/* 65 */       var7++;
/*    */     }
/*    */     
/* 68 */     if (par1World.isAirBlock(par3, par4, par5 - 1))
/*    */     {
/* 70 */       var7++;
/*    */     }
/*    */     
/* 73 */     if (par1World.isAirBlock(par3, par4, par5 + 1))
/*    */     {
/* 75 */       var7++;
/*    */     }
/*    */     
/* 78 */     if (par1World.isAirBlock(par3, par4 - 1, par5))
/*    */     {
/* 80 */       var7++;
/*    */     }
/*    */     
/* 83 */     if ((!this.field_94524_b && var6 == 4 && var7 == 1) || var6 == 5) {
/*    */       
/* 85 */       par1World.setBlock(par3, par4, par5, this.hellLavaID, 0, 2);
/* 86 */       par1World.scheduledUpdatesAreImmediate = true;
/* 87 */       Block.blocksList[this.hellLavaID].updateTick(par1World, par3, par4, par5, par2Random);
/* 88 */       par1World.scheduledUpdatesAreImmediate = false;
/*    */     } 
/*    */     
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenHellLava.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */