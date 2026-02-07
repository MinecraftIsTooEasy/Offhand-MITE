/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class PropertyManager
/*     */ {
/*  11 */   private final Properties properties = new Properties();
/*     */ 
/*     */   
/*     */   private final ILogAgent logger;
/*     */   
/*     */   private final File associatedFile;
/*     */   
/*     */   private String header;
/*     */ 
/*     */   
/*     */   public PropertyManager(File par1File, ILogAgent par2ILogAgent, String header) {
/*  22 */     this.associatedFile = par1File;
/*  23 */     this.logger = par2ILogAgent;
/*  24 */     this.header = header;
/*     */     
/*  26 */     if (par1File.exists()) {
/*     */       
/*  28 */       FileInputStream var3 = null;
/*     */ 
/*     */       
/*     */       try {
/*  32 */         var3 = new FileInputStream(par1File);
/*  33 */         this.properties.load(var3);
/*     */       }
/*  35 */       catch (Exception var13) {
/*     */         
/*  37 */         par2ILogAgent.logWarningException("Failed to load " + par1File, var13);
/*  38 */         logMessageAndSave();
/*     */       }
/*     */       finally {
/*     */         
/*  42 */         if (var3 != null) {
/*     */           
/*     */           try {
/*     */             
/*  46 */             var3.close();
/*     */           }
/*  48 */           catch (IOException var12) {}
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  57 */       par2ILogAgent.logWarning(par1File + " does not exist");
/*  58 */       logMessageAndSave();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logMessageAndSave() {
/*  68 */     this.logger.logInfo("Generating new properties file");
/*  69 */     saveProperties();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveProperties() {
/*  77 */     FileOutputStream var1 = null;
/*     */ 
/*     */     
/*     */     try {
/*  81 */       var1 = new FileOutputStream(this.associatedFile);
/*     */       
/*  83 */       this.properties.store(var1, this.header);
/*     */     }
/*  85 */     catch (Exception var11) {
/*     */       
/*  87 */       this.logger.logWarningException("Failed to save " + this.associatedFile, var11);
/*  88 */       logMessageAndSave();
/*     */     }
/*     */     finally {
/*     */       
/*  92 */       if (var1 != null) {
/*     */         
/*     */         try {
/*     */           
/*  96 */           var1.close();
/*     */         }
/*  98 */         catch (IOException var10) {}
/*     */       }
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
/*     */   public File getPropertiesFile() {
/* 111 */     return this.associatedFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProperty(String par1Str, String par2Str) {
/* 119 */     if (!this.properties.containsKey(par1Str)) {
/*     */       
/* 121 */       this.properties.setProperty(par1Str, par2Str);
/* 122 */       saveProperties();
/*     */     } 
/*     */     
/* 125 */     return this.properties.getProperty(par1Str, par2Str);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProperty(String par1Str) {
/* 130 */     if (!this.properties.containsKey(par1Str)) {
/* 131 */       return null;
/*     */     }
/* 133 */     return this.properties.getProperty(par1Str, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntProperty(String par1Str, int par2) {
/*     */     try {
/* 143 */       return Integer.parseInt(getProperty(par1Str, "" + par2));
/*     */     }
/* 145 */     catch (Exception var4) {
/*     */       
/* 147 */       this.properties.setProperty(par1Str, "" + par2);
/* 148 */       return par2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBooleanProperty(String par1Str, boolean par2) {
/*     */     try {
/* 159 */       return Boolean.parseBoolean(getProperty(par1Str, "" + par2));
/*     */     }
/* 161 */     catch (Exception var4) {
/*     */       
/* 163 */       this.properties.setProperty(par1Str, "" + par2);
/* 164 */       return par2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperty(String par1Str, Object par2Obj) {
/* 173 */     this.properties.setProperty(par1Str, "" + par2Obj);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PropertyManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */