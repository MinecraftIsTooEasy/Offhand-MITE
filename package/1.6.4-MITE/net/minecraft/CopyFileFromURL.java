/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.file.Files;
/*     */ import java.security.MessageDigest;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CopyFileFromURL
/*     */   extends Thread
/*     */ {
/*     */   private final String url_string;
/*     */   private final String destination_path;
/*     */   private final boolean compare_md5s_first;
/*     */   private int connection_timeout_ms;
/*     */   private int read_timeout_ms;
/*     */   
/*     */   public CopyFileFromURL(String url_string, String destination_path, int connection_timeout_ms, int read_timeout_ms, boolean compare_md5s_first) {
/*  36 */     this.url_string = url_string;
/*  37 */     this.destination_path = destination_path;
/*     */     
/*  39 */     this.compare_md5s_first = compare_md5s_first;
/*     */     
/*  41 */     this.connection_timeout_ms = connection_timeout_ms;
/*  42 */     this.read_timeout_ms = read_timeout_ms;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFileDataIdentical(File file1, File file2) {
/*  47 */     if (!file1.exists() && !file2.exists()) {
/*  48 */       return true;
/*     */     }
/*  50 */     if (!file1.exists() || !file2.exists()) {
/*  51 */       return false;
/*     */     }
/*  53 */     if (file1.length() != file2.length()) {
/*  54 */       return false;
/*     */     }
/*     */     
/*     */     try {
/*  58 */       byte[] bytes1 = Files.readAllBytes(file1.toPath());
/*  59 */       byte[] bytes2 = Files.readAllBytes(file2.toPath());
/*     */       
/*  61 */       if (bytes1.length != bytes2.length) {
/*  62 */         return false;
/*     */       }
/*  64 */       for (int i = 0; i < bytes1.length; i++) {
/*     */         
/*  66 */         if (bytes1[i] != bytes2[i]) {
/*  67 */           return false;
/*     */         }
/*     */       } 
/*  70 */       return true;
/*     */     }
/*  72 */     catch (IOException e) {
/*     */       
/*  74 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean overwriteFileIfNotSameAsTemp(File file, File temp_file) {
/*  81 */     if (!temp_file.exists()) {
/*     */       
/*  83 */       Minecraft.setErrorMessage("overwriteFileIfNotSameAsTemp: temp_file does not exist");
/*  84 */       (new Exception()).printStackTrace();
/*     */       
/*  86 */       return false;
/*     */     } 
/*     */     
/*  89 */     if (file.exists()) {
/*     */       
/*  91 */       if (isFileDataIdentical(file, temp_file)) {
/*     */         
/*  93 */         FileUtils.deleteQuietly(temp_file);
/*  94 */         return false;
/*     */       } 
/*     */ 
/*     */       
/*  98 */       FileUtils.deleteQuietly(file);
/*     */       
/* 100 */       if (file.exists()) {
/*     */         
/* 102 */         FileUtils.deleteQuietly(temp_file);
/* 103 */         return false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 108 */     if (temp_file.renameTo(file)) {
/* 109 */       return true;
/*     */     }
/*     */     
/*     */     try {
/* 113 */       FileUtils.moveFile(temp_file, file);
/* 114 */       return true;
/*     */     }
/* 116 */     catch (IOException e) {
/*     */       
/* 118 */       e.printStackTrace();
/*     */       
/* 120 */       FileUtils.deleteQuietly(temp_file);
/* 121 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMD5(File file) {
/* 127 */     if (file != null && file.exists()) {
/*     */       
/*     */       try {
/*     */         
/* 131 */         FileInputStream fis = new FileInputStream(file);
/*     */         
/* 133 */         byte[] bytes = IOUtils.toByteArray(fis);
/*     */         
/* 135 */         MessageDigest md = MessageDigest.getInstance("MD5");
/* 136 */         md.update(bytes, 0, bytes.length);
/*     */         
/* 138 */         String md5_string = (new BigInteger(1, md.digest())).toString(16);
/*     */         
/* 140 */         fis.close();
/*     */         
/* 142 */         return md5_string;
/*     */       }
/* 144 */       catch (Exception e) {
/*     */         
/* 146 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean areMD5sTheSame(String local_file_path, String remote_file_url) {
/* 156 */     File file = new File(local_file_path);
/*     */     
/* 158 */     if (file.exists()) {
/*     */       
/* 160 */       String md5_string = null;
/*     */       
/* 162 */       if (remote_file_url.startsWith("http://")) {
/* 163 */         md5_string = HttpUtil.performGetRequest(FilenameUtils.getPath(remote_file_url) + "md5.php?f=" + FilenameUtils.getName(remote_file_url), 2000, 2000);
/* 164 */       } else if (remote_file_url.startsWith("file:/")) {
/* 165 */         md5_string = getMD5(new File(remote_file_url.substring("file:/".length())));
/*     */       } 
/* 167 */       if (md5_string != null && md5_string.equals(getMD5(file))) {
/* 168 */         return true;
/*     */       }
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 176 */     if (this.compare_md5s_first && areMD5sTheSame(this.destination_path, this.url_string)) {
/*     */       
/* 178 */       if (Minecraft.inDevMode())
/*     */       {
/* 180 */         System.out.println("Skipping download of " + this.url_string + " (identical MD5)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 185 */     String temp_path = this.destination_path + ".temp";
/*     */     
/* 187 */     if (copyFileFromURL(this.url_string, temp_path, this.connection_timeout_ms, this.read_timeout_ms)) {
/* 188 */       overwriteFileIfNotSameAsTemp(new File(this.destination_path), new File(temp_path));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean copyFileFromURL(String url_string, String destination_path, int connection_timeout_ms, int read_timeout_ms) {
/*     */     try {
/* 219 */       URLConnection c = (new URL(url_string)).openConnection();
/*     */       
/* 221 */       c.setConnectTimeout(connection_timeout_ms);
/* 222 */       c.setReadTimeout(read_timeout_ms);
/*     */       
/* 224 */       ReadableByteChannel rbc = Channels.newChannel(c.getInputStream());
/* 225 */       FileOutputStream fos = new FileOutputStream(destination_path);
/* 226 */       fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
/* 227 */       fos.close();
/*     */       
/* 229 */       if (Minecraft.inDevMode()) {
/* 230 */         System.out.println("Successfully downloaded " + url_string);
/*     */       }
/* 232 */       return true;
/*     */     }
/* 234 */     catch (Exception e) {
/*     */       
/* 236 */       if (Minecraft.inDevMode()) {
/* 237 */         System.out.println("copyFileFromURL(" + url_string + "): " + e.toString());
/*     */       }
/* 239 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CopyFileFromURL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */