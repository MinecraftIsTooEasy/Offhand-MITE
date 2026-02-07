package net.minecraft;

import java.io.File;

public interface ISaveHandler {
  WorldInfo loadWorldInfo();
  
  void checkSessionLock();
  
  IChunkLoader getChunkLoader(WorldProvider paramWorldProvider);
  
  void saveWorldInfoWithPlayer(WorldInfo paramWorldInfo, NBTTagCompound paramNBTTagCompound);
  
  void saveWorldInfo(WorldInfo paramWorldInfo);
  
  IPlayerFileData getSaveHandler();
  
  void flush();
  
  File getMapFileFromName(String paramString);
  
  String getWorldDirectoryName();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ISaveHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */