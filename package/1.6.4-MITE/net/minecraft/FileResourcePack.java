/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.base.Splitter;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.io.Closeable;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Enumeration;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ 
/*    */ public class FileResourcePack extends AbstractResourcePack implements Closeable {
/* 19 */   public static final Splitter entryNameSplitter = Splitter.on('/').omitEmptyStrings().limit(3);
/*    */   private ZipFile resourcePackZipFile;
/*    */   
/*    */   public FileResourcePack(File file) {
/* 23 */     super(file);
/*    */   }
/*    */   
/*    */   private ZipFile getResourcePackZipFile() {
/* 27 */     if (this.resourcePackZipFile == null) {
/* 28 */       this.resourcePackZipFile = new ZipFile(this.resourcePackFile);
/*    */     }
/*    */     
/* 31 */     return this.resourcePackZipFile;
/*    */   }
/*    */ 
/*    */   
/*    */   protected InputStream getInputStreamByName(String string) {
/* 36 */     ZipFile zipFile = getResourcePackZipFile();
/* 37 */     ZipEntry zipEntry = zipFile.getEntry(string);
/*    */     
/* 39 */     if (zipEntry == null) {
/* 40 */       throw new ResourcePackFileNotFoundException(this.resourcePackFile, string);
/*    */     }
/*    */     
/* 43 */     return zipFile.getInputStream(zipEntry);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasResourceName(String string) {
/*    */     try {
/* 49 */       return (getResourcePackZipFile().getEntry(string) != null);
/* 50 */     } catch (IOException iOException) {
/* 51 */       return false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getResourceDomains() {
/*    */     ZipFile zipFile;
/*    */     try {
/* 59 */       zipFile = getResourcePackZipFile();
/* 60 */     } catch (IOException iOException) {
/* 61 */       return Collections.emptySet();
/*    */     } 
/*    */     
/* 64 */     Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
/*    */     
/* 66 */     HashSet<String> hashSet = Sets.newHashSet();
/*    */     
/* 68 */     while (enumeration.hasMoreElements()) {
/* 69 */       ZipEntry zipEntry = enumeration.nextElement();
/*    */       
/* 71 */       String str = zipEntry.getName();
/* 72 */       if (str.startsWith("assets/")) {
/* 73 */         ArrayList<String> arrayList = Lists.newArrayList(entryNameSplitter.split(str));
/* 74 */         if (arrayList.size() > 1) {
/* 75 */           String str1 = arrayList.get(1);
/* 76 */           if (!str1.equals(str1.toLowerCase())) {
/* 77 */             logNameNotLowercase(str1); continue;
/*    */           } 
/* 79 */           hashSet.add(str1);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 85 */     return hashSet;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void finalize() {
/* 90 */     close();
/* 91 */     super.finalize();
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 96 */     if (this.resourcePackZipFile != null) {
/* 97 */       this.resourcePackZipFile.close();
/* 98 */       this.resourcePackZipFile = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FileResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */