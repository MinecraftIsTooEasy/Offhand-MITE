/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileFilter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class ResourcePackRepositoryFilter
/*    */   implements FileFilter
/*    */ {
/*    */   public boolean accept(File file) {
/* 26 */     boolean bool1 = (file.isFile() && file.getName().endsWith(".zip")) ? true : false;
/* 27 */     boolean bool2 = (file.isDirectory() && (new File(file, "pack.mcmeta")).isFile()) ? true : false;
/*    */     
/* 29 */     return (bool1 || bool2);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourcePackRepositoryFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */