/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Map;
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
/*     */ public class StatsSyncher
/*     */ {
/*     */   private volatile boolean isBusy;
/*     */   private volatile Map field_77430_b;
/*     */   private volatile Map field_77431_c;
/*     */   private StatFileWriter statFileWriter;
/*     */   private File unsentDataFile;
/*     */   private File dataFile;
/*     */   private File unsentTempFile;
/*     */   private File tempFile;
/*     */   private File unsentOldFile;
/*     */   private File oldFile;
/*     */   private Session theSession;
/*     */   private int field_77433_l;
/*     */   private int field_77434_m;
/*     */   
/*     */   public StatsSyncher(Session session, StatFileWriter statFileWriter, File file) {
/*  37 */     String str1 = session.getUsername();
/*  38 */     String str2 = str1.toLowerCase();
/*  39 */     this.unsentDataFile = new File(file, "stats_" + str2 + "_unsent.dat");
/*  40 */     this.dataFile = new File(file, "stats_" + str2 + ".dat");
/*     */     
/*  42 */     this.unsentOldFile = new File(file, "stats_" + str2 + "_unsent.old");
/*  43 */     this.oldFile = new File(file, "stats_" + str2 + ".old");
/*     */     
/*  45 */     this.unsentTempFile = new File(file, "stats_" + str2 + "_unsent.tmp");
/*  46 */     this.tempFile = new File(file, "stats_" + str2 + ".tmp");
/*     */     
/*  48 */     if (!str2.equals(str1)) {
/*  49 */       func_77412_a(file, "stats_" + str1 + "_unsent.dat", this.unsentDataFile);
/*  50 */       func_77412_a(file, "stats_" + str1 + ".dat", this.dataFile);
/*     */       
/*  52 */       func_77412_a(file, "stats_" + str1 + "_unsent.old", this.unsentOldFile);
/*  53 */       func_77412_a(file, "stats_" + str1 + ".old", this.oldFile);
/*     */       
/*  55 */       func_77412_a(file, "stats_" + str1 + "_unsent.tmp", this.unsentTempFile);
/*  56 */       func_77412_a(file, "stats_" + str1 + ".tmp", this.tempFile);
/*     */     } 
/*     */     
/*  59 */     this.statFileWriter = statFileWriter;
/*  60 */     this.theSession = session;
/*     */     
/*  62 */     if (this.unsentDataFile.exists()) {
/*  63 */       statFileWriter.writeStats(func_77417_a(this.unsentDataFile, this.unsentTempFile, this.unsentOldFile));
/*     */     }
/*  65 */     beginReceiveStats();
/*     */   }
/*     */   
/*     */   private void func_77412_a(File file, String string, File file2) {
/*  69 */     File file1 = new File(file, string);
/*  70 */     if (file1.exists() && !file1.isDirectory() && !file2.exists()) {
/*  71 */       file1.renameTo(file2);
/*     */     }
/*     */   }
/*     */   
/*     */   private Map func_77417_a(File file, File file2, File file3) {
/*  76 */     if (file.exists())
/*  77 */       return func_77413_a(file); 
/*  78 */     if (file3.exists())
/*  79 */       return func_77413_a(file3); 
/*  80 */     if (file2.exists()) {
/*  81 */       return func_77413_a(file2);
/*     */     }
/*  83 */     return null;
/*     */   }
/*     */   
/*     */   private Map func_77413_a(File file) {
/*  87 */     BufferedReader bufferedReader = null;
/*     */     try {
/*  89 */       bufferedReader = new BufferedReader(new FileReader(file));
/*  90 */       String str = "";
/*  91 */       StringBuilder stringBuilder = new StringBuilder();
/*  92 */       while ((str = bufferedReader.readLine()) != null) {
/*  93 */         stringBuilder.append(str);
/*     */       }
/*     */       
/*  96 */       return StatFileWriter.func_77453_b(stringBuilder.toString());
/*  97 */     } catch (Exception exception) {
/*  98 */       exception.printStackTrace();
/*     */     } finally {
/* 100 */       if (bufferedReader != null) {
/* 101 */         try { bufferedReader.close(); }
/* 102 */         catch (Exception exception)
/* 103 */         { exception.printStackTrace(); }
/*     */       
/*     */       }
/*     */     } 
/* 107 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_77421_a(Map map, File file, File file2, File file3) {
/* 142 */     PrintWriter printWriter = new PrintWriter(new FileWriter(file2, false));
/*     */     try {
/* 144 */       printWriter.print(StatFileWriter.func_77441_a(this.theSession.getUsername(), "local", map));
/*     */     } finally {
/* 146 */       printWriter.close();
/*     */     } 
/*     */     
/* 149 */     if (file3.exists()) {
/* 150 */       file3.delete();
/*     */     }
/* 152 */     if (file.exists()) {
/* 153 */       file.renameTo(file3);
/*     */     }
/* 155 */     file2.renameTo(file);
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
/*     */   public void beginReceiveStats() {
/* 176 */     if (this.isBusy) throw new IllegalStateException("Can't get stats from server while StatsSyncher is busy!"); 
/* 177 */     this.field_77433_l = 100;
/* 178 */     this.isBusy = true;
/* 179 */     (new ThreadStatSyncherReceive(this)).start();
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
/*     */   public void beginSendStats(Map map) {
/* 198 */     if (this.isBusy) throw new IllegalStateException("Can't save stats while StatsSyncher is busy!"); 
/* 199 */     this.field_77433_l = 100;
/* 200 */     this.isBusy = true;
/* 201 */     (new ThreadStatSyncherSend(this, map)).start();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncStatsFileWithMap(Map map) {
/* 260 */     byte b = 30;
/* 261 */     while (this.isBusy && --b > 0) {
/*     */       try {
/* 263 */         Thread.sleep(100L);
/* 264 */       } catch (InterruptedException interruptedException) {
/* 265 */         interruptedException.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     this.isBusy = true;
/*     */     try {
/* 271 */       func_77421_a(map, this.unsentDataFile, this.unsentTempFile, this.unsentOldFile);
/* 272 */     } catch (Exception exception) {
/* 273 */       exception.printStackTrace();
/*     */     } finally {
/* 275 */       this.isBusy = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_77425_c() {
/* 280 */     if (this.field_77433_l > 0 || this.isBusy || this.field_77431_c != null) return false; 
/* 281 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77422_e() {
/* 290 */     if (this.field_77433_l > 0) this.field_77433_l--; 
/* 291 */     if (this.field_77434_m > 0) this.field_77434_m--;
/*     */     
/* 293 */     if (this.field_77431_c != null) {
/* 294 */       this.statFileWriter.func_77448_c(this.field_77431_c);
/* 295 */       this.field_77431_c = null;
/*     */     } 
/*     */     
/* 298 */     if (this.field_77430_b != null) {
/* 299 */       this.statFileWriter.func_77452_b(this.field_77430_b);
/* 300 */       this.field_77430_b = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatsSyncher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */