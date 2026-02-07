package net.minecraft;

public interface IServer {
  int getIntProperty(String paramString, int paramInt);
  
  String getStringProperty(String paramString1, String paramString2);
  
  void setProperty(String paramString, Object paramObject);
  
  void saveProperties();
  
  String getSettingsFilename();
  
  String getHostname();
  
  int getPort();
  
  String getServerMOTD();
  
  String getMinecraftVersion();
  
  int getCurrentPlayerCount();
  
  int getMaxPlayers();
  
  String[] getAllUsernames();
  
  String getFolderName();
  
  String getPlugins();
  
  String executeCommand(String paramString, boolean paramBoolean);
  
  boolean isDebuggingEnabled();
  
  void logInfo(String paramString);
  
  void logWarning(String paramString);
  
  void logSevere(String paramString);
  
  void logDebug(String paramString);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */