/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.logging.ConsoleHandler;
/*    */ import java.util.logging.FileHandler;
/*    */ import java.util.logging.Handler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public class LogAgent
/*    */   implements ILogAgent
/*    */ {
/*    */   private final Logger serverLogger;
/*    */   private final String logFile;
/*    */   private final String loggerName;
/*    */   private final String loggerPrefix;
/*    */   
/*    */   public LogAgent(String par1Str, String par2Str, String par3Str) {
/* 18 */     this.serverLogger = Logger.getLogger(par1Str);
/* 19 */     this.loggerName = par1Str;
/* 20 */     this.loggerPrefix = par2Str;
/* 21 */     this.logFile = par3Str;
/* 22 */     setupLogger();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setupLogger() {
/* 30 */     this.serverLogger.setUseParentHandlers(false);
/* 31 */     Handler[] var1 = this.serverLogger.getHandlers();
/* 32 */     int var2 = var1.length;
/*    */     
/* 34 */     for (int var3 = 0; var3 < var2; var3++) {
/*    */       
/* 36 */       Handler var4 = var1[var3];
/* 37 */       this.serverLogger.removeHandler(var4);
/*    */     } 
/*    */     
/* 40 */     LogFormatter var6 = new LogFormatter(this, (LogAgentEmptyAnon)null);
/* 41 */     ConsoleHandler var7 = new ConsoleHandler();
/* 42 */     var7.setFormatter(var6);
/* 43 */     this.serverLogger.addHandler(var7);
/*    */ 
/*    */     
/*    */     try {
/* 47 */       FileHandler var8 = new FileHandler(this.logFile, true);
/* 48 */       var8.setFormatter(var6);
/* 49 */       this.serverLogger.addHandler(var8);
/*    */     }
/* 51 */     catch (Exception var5) {
/*    */       
/* 53 */       this.serverLogger.log(Level.WARNING, "Failed to log " + this.loggerName + " to " + this.logFile, var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Logger func_120013_a() {
/* 59 */     return this.serverLogger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void logInfo(String par1Str) {
/* 64 */     this.serverLogger.log(Level.INFO, par1Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logWarning(String par1Str) {
/* 69 */     this.serverLogger.log(Level.WARNING, par1Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logWarningFormatted(String par1Str, Object... par2ArrayOfObj) {
/* 74 */     this.serverLogger.log(Level.WARNING, par1Str, par2ArrayOfObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logWarningException(String par1Str, Throwable par2Throwable) {
/* 79 */     this.serverLogger.log(Level.WARNING, par1Str, par2Throwable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logSevere(String par1Str) {
/* 84 */     this.serverLogger.log(Level.SEVERE, par1Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logSevereException(String par1Str, Throwable par2Throwable) {
/* 89 */     this.serverLogger.log(Level.SEVERE, par1Str, par2Throwable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void logFine(String par1Str) {
/* 94 */     this.serverLogger.log(Level.FINE, par1Str);
/*    */   }
/*    */ 
/*    */   
/*    */   static String func_98237_a(LogAgent par0LogAgent) {
/* 99 */     return par0LogAgent.loggerPrefix;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LogAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */