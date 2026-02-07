/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class AnvilSaveHandler
/*    */   extends SaveHandler
/*    */ {
/*    */   public AnvilSaveHandler(File par1File, String par2Str, boolean par3) {
/*  9 */     super(par1File, par2Str, par3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChunkLoader getChunkLoader(WorldProvider par1WorldProvider) {
/* 17 */     File var2 = getWorldDirectory();
/*    */ 
/*    */     
/* 20 */     if (par1WorldProvider instanceof WorldProviderUnderworld) {
/*    */       
/* 22 */       File var3 = new File(var2, "DIM-2");
/* 23 */       var3.mkdirs();
/* 24 */       return new AnvilChunkLoader(var3);
/*    */     } 
/*    */     
/* 27 */     if (par1WorldProvider instanceof WorldProviderHell) {
/*    */       
/* 29 */       File var3 = new File(var2, "DIM-1");
/* 30 */       var3.mkdirs();
/* 31 */       return new AnvilChunkLoader(var3);
/*    */     } 
/* 33 */     if (par1WorldProvider instanceof WorldProviderEnd) {
/*    */       
/* 35 */       File var3 = new File(var2, "DIM1");
/* 36 */       var3.mkdirs();
/* 37 */       return new AnvilChunkLoader(var3);
/*    */     } 
/*    */ 
/*    */     
/* 41 */     return new AnvilChunkLoader(var2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveWorldInfoWithPlayer(WorldInfo par1WorldInfo, NBTTagCompound par2NBTTagCompound) {
/* 50 */     par1WorldInfo.setSaveVersion(19133);
/* 51 */     super.saveWorldInfoWithPlayer(par1WorldInfo, par2NBTTagCompound);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() {
/* 68 */     ThreadedFileIOBase.waitForFinish();
/*    */     
/* 70 */     RegionFileCache.clearRegionFileReferences();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AnvilSaveHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */