package net.minecraft;

import java.util.List;

public interface ISaveFormat {
  ISaveHandler getSaveLoader(String paramString, boolean paramBoolean);
  
  List getSaveList();
  
  void flushCache();
  
  WorldInfo getWorldInfo(String paramString);
  
  boolean deleteWorldDirectory(String paramString);
  
  void renameWorld(String paramString1, String paramString2);
  
  boolean isOldMapFormat(String paramString);
  
  boolean convertMapFormat(String paramString, IProgressUpdate paramIProgressUpdate);
  
  boolean canLoadWorld(String paramString);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ISaveFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */