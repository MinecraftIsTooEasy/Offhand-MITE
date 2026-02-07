/*   */ package net.minecraft;
/*   */ 
/*   */ import java.io.File;
/*   */ import java.io.FileNotFoundException;
/*   */ 
/*   */ public class ResourcePackFileNotFoundException extends FileNotFoundException {
/*   */   public ResourcePackFileNotFoundException(File file, String string) {
/* 8 */     super(String.format("'%s' in ResourcePack '%s'", new Object[] { string, file }));
/*   */   }
/*   */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourcePackFileNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */