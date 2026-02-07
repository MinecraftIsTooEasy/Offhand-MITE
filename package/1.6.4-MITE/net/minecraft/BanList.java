/*     */ package net.minecraft;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class BanList {
/*  15 */   private final LowerStringMap theBanList = new LowerStringMap();
/*     */   private final File fileName;
/*     */   private boolean listActive = true;
/*     */   
/*     */   public BanList(File file) {
/*  20 */     this.fileName = file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isListActive() {
/*  28 */     return this.listActive;
/*     */   }
/*     */   
/*     */   public void setListActive(boolean bl) {
/*  32 */     this.listActive = bl;
/*     */   }
/*     */   
/*     */   public Map getBannedList() {
/*  36 */     removeExpiredBans();
/*  37 */     return this.theBanList;
/*     */   }
/*     */   
/*     */   public boolean isBanned(String string) {
/*  41 */     if (!isListActive()) return false;
/*     */     
/*  43 */     removeExpiredBans();
/*  44 */     return this.theBanList.containsKey(string);
/*     */   }
/*     */   
/*     */   public void put(BanEntry banEntry) {
/*  48 */     this.theBanList.putLower(banEntry.getBannedUsername(), banEntry);
/*  49 */     saveToFileWithHeader();
/*     */   }
/*     */   
/*     */   public void remove(String string) {
/*  53 */     this.theBanList.remove(string);
/*  54 */     saveToFileWithHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeExpiredBans() {
/*  62 */     Iterator<BanEntry> iterator = this.theBanList.values().iterator();
/*     */     
/*  64 */     while (iterator.hasNext()) {
/*  65 */       BanEntry banEntry = iterator.next();
/*     */       
/*  67 */       if (banEntry.hasBanExpired())
/*  68 */         iterator.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadBanList() {
/*     */     BufferedReader bufferedReader;
/*  74 */     if (!this.fileName.isFile()) {
/*     */       return;
/*     */     }
/*     */     try {
/*  78 */       bufferedReader = new BufferedReader(new FileReader(this.fileName));
/*  79 */     } catch (FileNotFoundException fileNotFoundException) {
/*  80 */       throw new Error();
/*     */     } 
/*     */     
/*     */     try {
/*     */       String str;
/*     */       
/*  86 */       while ((str = bufferedReader.readLine()) != null) {
/*  87 */         if (!str.startsWith("#")) {
/*  88 */           BanEntry banEntry = BanEntry.parse(str);
/*     */           
/*  90 */           if (banEntry != null) {
/*  91 */             this.theBanList.putLower(banEntry.getBannedUsername(), banEntry);
/*     */           }
/*     */         } 
/*     */       } 
/*  95 */     } catch (IOException iOException) {
/*  96 */       MinecraftServer.getServer().getLogAgent().logSevereException("Could not load ban list", iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveToFileWithHeader() {
/* 101 */     saveToFile(true);
/*     */   }
/*     */   
/*     */   public void saveToFile(boolean bl) {
/* 105 */     removeExpiredBans();
/*     */     
/*     */     try {
/* 108 */       PrintWriter printWriter = new PrintWriter(new FileWriter(this.fileName, false));
/*     */       
/* 110 */       if (bl) {
/* 111 */         printWriter.println("# Updated " + (new SimpleDateFormat()).format(new Date()) + " by Minecraft " + "1.6.4");
/* 112 */         printWriter.println("# victim name | ban date | banned by | banned until | reason");
/* 113 */         printWriter.println();
/*     */       } 
/*     */       
/* 116 */       for (BanEntry banEntry : this.theBanList.values()) {
/* 117 */         printWriter.println(banEntry.buildBanString());
/*     */       }
/*     */       
/* 120 */       printWriter.close();
/* 121 */     } catch (IOException iOException) {
/* 122 */       MinecraftServer.getServer().getLogAgent().logSevereException("Could not save ban list", iOException);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BanList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */