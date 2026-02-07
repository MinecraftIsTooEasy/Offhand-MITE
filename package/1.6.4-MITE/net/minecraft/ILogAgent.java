package net.minecraft;

import java.util.logging.Logger;

public interface ILogAgent {
  Logger func_120013_a();
  
  void logInfo(String paramString);
  
  void logWarning(String paramString);
  
  void logWarningFormatted(String paramString, Object... paramVarArgs);
  
  void logWarningException(String paramString, Throwable paramThrowable);
  
  void logSevere(String paramString);
  
  void logSevereException(String paramString, Throwable paramThrowable);
  
  void logFine(String paramString);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ILogAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */