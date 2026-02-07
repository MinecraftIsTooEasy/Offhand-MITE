package net.minecraft;

public interface IBlockSource extends ILocatableSource {
  double getX();
  
  double getY();
  
  double getZ();
  
  int getXInt();
  
  int getYInt();
  
  int getZInt();
  
  int getBlockMetadata();
  
  TileEntity getBlockTileEntity();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IBlockSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */