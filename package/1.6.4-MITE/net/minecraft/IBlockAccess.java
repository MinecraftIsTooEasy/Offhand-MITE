package net.minecraft;

public interface IBlockAccess {
  int getBlockId(int paramInt1, int paramInt2, int paramInt3);
  
  Block getBlock(int paramInt1, int paramInt2, int paramInt3);
  
  TileEntity getBlockTileEntity(int paramInt1, int paramInt2, int paramInt3);
  
  int getLightBrightnessForSkyBlocks(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  float getBrightness(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  float getLightBrightness(int paramInt1, int paramInt2, int paramInt3);
  
  int getBlockMetadata(int paramInt1, int paramInt2, int paramInt3);
  
  Material getBlockMaterial(int paramInt1, int paramInt2, int paramInt3);
  
  Material getBlockMaterial(int paramInt);
  
  boolean isBlockStandardFormOpaqueCube(int paramInt1, int paramInt2, int paramInt3);
  
  boolean isBlockNormalCube(int paramInt1, int paramInt2, int paramInt3);
  
  boolean isAirBlock(int paramInt1, int paramInt2, int paramInt3);
  
  BiomeGenBase getBiomeGenForCoords(int paramInt1, int paramInt2);
  
  int getHeight();
  
  boolean extendedLevelsInChunkCache();
  
  boolean isBlockTopFlatAndSolid(int paramInt1, int paramInt2, int paramInt3);
  
  Vec3Pool getWorldVec3Pool();
  
  int isBlockProvidingPowerTo(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  World getWorld();
  
  boolean isBlockSolid(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IBlockAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */