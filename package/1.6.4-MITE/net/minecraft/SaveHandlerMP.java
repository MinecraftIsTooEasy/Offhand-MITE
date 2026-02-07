/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveHandlerMP
/*    */   implements ISaveHandler
/*    */ {
/*    */   public WorldInfo loadWorldInfo() {
/* 14 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void checkSessionLock() {}
/*    */ 
/*    */   
/*    */   public IChunkLoader getChunkLoader(WorldProvider worldProvider) {
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveWorldInfoWithPlayer(WorldInfo worldInfo, NBTTagCompound nBTTagCompound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveWorldInfo(WorldInfo worldInfo) {}
/*    */ 
/*    */   
/*    */   public IPlayerFileData getSaveHandler() {
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() {}
/*    */ 
/*    */   
/*    */   public File getMapFileFromName(String string) {
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getWorldDirectoryName() {
/* 50 */     return "none";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SaveHandlerMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */