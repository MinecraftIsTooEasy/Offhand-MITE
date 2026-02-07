/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFlowers
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int plantBlockId;
/*    */   private Block block;
/*    */   private int metadata;
/*    */   
/*    */   public WorldGenFlowers(int par1) {
/* 15 */     this.plantBlockId = par1;
/* 16 */     this.block = Block.getBlock(this.plantBlockId);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 21 */     int attempts = (this.block instanceof BlockFlower) ? ((BlockFlower)this.block).getPatchSize(par1World.getBiomeGenForCoords(par3, par5)) : 64;
/*    */ 
/*    */     
/* 24 */     for (int var6 = 0; var6 < attempts; var6++) {
/*    */       
/* 26 */       int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
/*    */       
/* 28 */       if (var8 >= 0 && var8 <= 255) {
/*    */ 
/*    */         
/* 31 */         int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
/*    */         
/* 33 */         int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 38 */         if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && this.block.canOccurAt(par1World, var7, var8, var9, this.metadata))
/*    */         {
/*    */           
/* 41 */           par1World.setBlock(var7, var8, var9, this.plantBlockId, this.metadata, 2);
/*    */         }
/*    */       } 
/*    */     } 
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMetadata(int metadata) {
/* 50 */     if (this.block != Block.plantRed) {
/*    */       
/* 52 */       Minecraft.setErrorMessage("setMetadata: only allowed for plantRed block");
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     this.metadata = metadata;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenFlowers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */