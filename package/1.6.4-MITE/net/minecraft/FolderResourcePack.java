/*    */ package net.minecraft;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.File;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class FolderResourcePack extends AbstractResourcePack {
/*    */   public FolderResourcePack(File file) {
/* 11 */     super(file);
/*    */   }
/*    */ 
/*    */   
/*    */   protected InputStream getInputStreamByName(String string) {
/* 16 */     return new BufferedInputStream(new FileInputStream(new File(this.resourcePackFile, string)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean hasResourceName(String string) {
/* 21 */     return (new File(this.resourcePackFile, string)).isFile();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getResourceDomains() {
/* 26 */     HashSet<String> hashSet = Sets.newHashSet();
/* 27 */     File file = new File(this.resourcePackFile, "assets/");
/*    */     
/* 29 */     if (file.isDirectory()) {
/* 30 */       for (File file1 : file.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY)) {
/* 31 */         String str = getRelativeName(file, file1);
/*    */         
/* 33 */         if (!str.equals(str.toLowerCase())) {
/* 34 */           logNameNotLowercase(str);
/*    */         } else {
/* 36 */           hashSet.add(str.substring(0, str.length() - 1));
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 41 */     return hashSet;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FolderResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */