/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.FilenameUtils;
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
/*     */ class GetPublicServers
/*     */   extends Thread
/*     */ {
/*     */   public void run() {
/*  23 */     File dir = new File("MITE/public_servers");
/*     */     
/*  25 */     if (!dir.exists()) {
/*  26 */       dir.mkdir();
/*     */     }
/*     */     
/*  29 */     String url_string = ClientProperties.getPublicServersUpdateURL();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     if (url_string == null || url_string.isEmpty()) {
/*     */       
/*  37 */       if (Minecraft.inDevMode()) {
/*  38 */         System.out.println("Skipping update of public_servers.txt");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  43 */     String filepath = "MITE/public_servers/public_servers.txt";
/*  44 */     String temp_filepath = filepath + ".temp";
/*     */     
/*  46 */     if (!CopyFileFromURL.areMD5sTheSame(filepath, url_string)) {
/*  47 */       CopyFileFromURL.copyFileFromURL(url_string, temp_filepath, 2000, 2000);
/*  48 */     } else if (Minecraft.inDevMode()) {
/*  49 */       System.out.println("Remote copy of public_servers.txt has same MD5 as local copy, skipping download");
/*     */     } 
/*  51 */     File temp_file = new File(temp_filepath);
/*  52 */     File file = new File(filepath);
/*     */     
/*  54 */     if (temp_file.exists()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  60 */       boolean valid_format = true;
/*     */ 
/*     */       
/*     */       try {
/*  64 */         BufferedReader br = new BufferedReader(new FileReader(new File(temp_filepath)));
/*     */         
/*     */         String line;
/*  67 */         while ((line = br.readLine()) != null) {
/*     */           
/*  69 */           line = line.trim();
/*     */           
/*  71 */           if (line.isEmpty() || line.startsWith("#") || line.startsWith("server_address")) {
/*     */             continue;
/*     */           }
/*  74 */           valid_format = false;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  79 */         br.close();
/*     */       }
/*  81 */       catch (Exception e) {
/*     */         
/*  83 */         valid_format = false;
/*     */       } 
/*     */       
/*  86 */       if (valid_format) {
/*     */         
/*  88 */         CopyFileFromURL.overwriteFileIfNotSameAsTemp(file, temp_file);
/*     */       }
/*     */       else {
/*     */         
/*  92 */         FileUtils.deleteQuietly(temp_file);
/*     */         
/*  94 */         if (Minecraft.inDevMode()) {
/*  95 */           System.out.println("Downloaded copy of public_servers.txt has unexpected format, skipping update");
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 101 */     if (file.exists())
/*     */       
/*     */       try {
/*     */         
/* 105 */         BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
/*     */         
/*     */         String line;
/* 108 */         while ((line = br.readLine()) != null) {
/*     */           
/* 110 */           line = line.trim();
/*     */           
/* 112 */           if (line.isEmpty() || line.startsWith("#")) {
/*     */             continue;
/*     */           }
/* 115 */           String image_url = (new KeyedValuesString(line)).getValue("image_url", true);
/*     */           
/* 117 */           if (image_url != null) {
/*     */             
/* 119 */             String filename = FilenameUtils.getName(image_url);
/*     */             
/* 121 */             CopyFileFromURL thread = new CopyFileFromURL(image_url, "MITE/public_servers/" + filename, 2000, 2000, true);
/* 122 */             thread.setDaemon(true);
/* 123 */             thread.setName("CopyFileFromURL: " + filename);
/* 124 */             thread.start();
/*     */           } 
/*     */         } 
/*     */         
/* 128 */         br.close();
/*     */       }
/* 130 */       catch (Exception e) {} 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GetPublicServers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */