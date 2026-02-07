package net.minecraft;

public interface IWorldAccess {
  void markBlockForUpdate(int paramInt1, int paramInt2, int paramInt3);
  
  void markBlockForRenderUpdate(int paramInt1, int paramInt2, int paramInt3);
  
  void markBlockRangeForRenderUpdate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  void playSound(String paramString, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
  
  void playLongDistanceSound(String paramString, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
  
  void playSoundToNearExcept(EntityPlayer paramEntityPlayer, String paramString, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
  
  void spawnParticle(EnumParticle paramEnumParticle, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6);
  
  void spawnParticleEx(EnumParticle paramEnumParticle, int paramInt1, int paramInt2, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6);
  
  void onEntityCreate(Entity paramEntity);
  
  void onEntityDestroy(Entity paramEntity);
  
  void playRecord(String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  void broadcastSound(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  void playAuxSFX(EntityPlayer paramEntityPlayer, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  void destroyBlockPartially(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IWorldAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */