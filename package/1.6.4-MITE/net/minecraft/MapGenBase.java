/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class MapGenBase
/*    */ {
/*  8 */   protected int range = 8;
/*    */ 
/*    */   
/* 11 */   protected Random rand = new Random();
/*    */ 
/*    */   
/*    */   protected World worldObj;
/*    */   
/*    */   protected int random_number_index;
/*    */ 
/*    */   
/*    */   public void generate(IChunkProvider par1IChunkProvider, World par2World, int par3, int par4, byte[] par5ArrayOfByte) {
/* 20 */     if (!isGenAllowedInChunk(par2World, par3, par4)) {
/*    */       return;
/*    */     }
/* 23 */     int var6 = this.range;
/* 24 */     this.worldObj = par2World;
/* 25 */     this.rand.setSeed(par2World.getSeed());
/* 26 */     long var7 = this.rand.nextLong();
/* 27 */     long var9 = this.rand.nextLong();
/*    */     
/* 29 */     for (int var11 = par3 - var6; var11 <= par3 + var6; var11++) {
/*    */       
/* 31 */       for (int var12 = par4 - var6; var12 <= par4 + var6; var12++) {
/*    */         
/* 33 */         if (isGenAllowedInChunk(par2World, var11, var12)) {
/*    */ 
/*    */           
/* 36 */           long var13 = var11 * var7;
/* 37 */           long var15 = var12 * var9;
/* 38 */           this.rand.setSeed(var13 ^ var15 ^ par2World.getSeed());
/* 39 */           this.random_number_index = (int)(var13 ^ var15 ^ par2World.getSeed()) & 0x7FFF;
/* 40 */           recursiveGenerate(par2World, var11, var12, par3, par4, par5ArrayOfByte);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {}
/*    */ 
/*    */   
/*    */   public boolean isGenAllowedInChunk(World world, int chunk_x, int chunk_z) {
/* 52 */     return isGenAllowedInBiome(world.getBiomeGenForCoords(chunk_x * 16, chunk_z * 16));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGenAllowedInBiome(BiomeGenBase biome) {
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */