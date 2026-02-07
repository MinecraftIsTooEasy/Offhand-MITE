package net.minecraft;

public interface Icon {
  int getIconWidth();
  
  int getIconHeight();
  
  float getMinU();
  
  float getMaxU();
  
  float getInterpolatedU(double paramDouble);
  
  float getMinV();
  
  float getMaxV();
  
  float getInterpolatedV(double paramDouble);
  
  String getIconName();
  
  boolean isGreenGrassSide();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Icon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */