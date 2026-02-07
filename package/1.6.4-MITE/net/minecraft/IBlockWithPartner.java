package net.minecraft;

public interface IBlockWithPartner {
  int getPartnerOffsetX(int paramInt);
  
  int getPartnerOffsetY(int paramInt);
  
  int getPartnerOffsetZ(int paramInt);
  
  boolean requiresPartner(int paramInt);
  
  boolean isPartner(int paramInt1, Block paramBlock, int paramInt2);
  
  boolean isPartnerPresent(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  boolean partnerDropsAsItem(int paramInt);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IBlockWithPartner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */