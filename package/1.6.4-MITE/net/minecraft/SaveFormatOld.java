/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaveFormatOld
/*     */   implements ISaveFormat
/*     */ {
/*     */   protected final File savesDirectory;
/*     */   
/*     */   public SaveFormatOld(File par1File) {
/*  18 */     if (!par1File.exists())
/*     */     {
/*  20 */       par1File.mkdirs();
/*     */     }
/*     */     
/*  23 */     this.savesDirectory = par1File;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSaveList() throws AnvilConverterException {
/*  28 */     ArrayList<SaveFormatComparator> var1 = new ArrayList();
/*     */     
/*  30 */     for (int var2 = 0; var2 < 5; var2++) {
/*     */       
/*  32 */       String var3 = "World" + (var2 + 1);
/*  33 */       WorldInfo var4 = getWorldInfo(var3);
/*     */       
/*  35 */       if (var4 != null)
/*     */       {
/*     */         
/*  38 */         var1.add(new SaveFormatComparator(var3, "", var4.getLastTimePlayed(), var4.getSizeOnDisk(), var4.getGameType(), false, var4.isHardcoreModeEnabled(), var4.areCommandsAllowed(), var4.areSkillsEnabled(), var4.isValidMITEWorld(), var4.getIsNotValidReason()));
/*     */       }
/*     */     } 
/*     */     
/*  42 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushCache() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldInfo getWorldInfo(String par1Str) {
/*  52 */     File var2 = new File(this.savesDirectory, par1Str);
/*     */     
/*  54 */     if (!var2.exists())
/*     */     {
/*  56 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  60 */     File var3 = new File(var2, "level.dat");
/*     */ 
/*     */ 
/*     */     
/*  64 */     if (var3.exists()) {
/*     */       
/*     */       try {
/*     */         
/*  68 */         NBTTagCompound var4 = CompressedStreamTools.readCompressed(new FileInputStream(var3));
/*  69 */         NBTTagCompound var5 = var4.getCompoundTag("Data");
/*  70 */         return new WorldInfo(var5);
/*     */       }
/*  72 */       catch (Exception var7) {
/*     */         
/*  74 */         NBTBase.loading_world_info = false;
/*  75 */         return null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  81 */     var3 = new File(var2, "level.dat_old");
/*     */     
/*  83 */     if (var3.exists()) {
/*     */       
/*     */       try {
/*     */         
/*  87 */         NBTTagCompound nBTTagCompound1 = CompressedStreamTools.readCompressed(new FileInputStream(var3));
/*  88 */         NBTTagCompound nBTTagCompound2 = nBTTagCompound1.getCompoundTag("Data");
/*  89 */         return new WorldInfo(nBTTagCompound2);
/*     */       }
/*  91 */       catch (Exception var6) {
/*     */         
/*  93 */         NBTBase.loading_world_info = false;
/*  94 */         return null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renameWorld(String par1Str, String par2Str) {
/* 111 */     File var3 = new File(this.savesDirectory, par1Str);
/*     */     
/* 113 */     if (var3.exists()) {
/*     */       
/* 115 */       File var4 = new File(var3, "level.dat");
/*     */       
/* 117 */       if (var4.exists()) {
/*     */         
/*     */         try {
/*     */           
/* 121 */           NBTTagCompound var5 = CompressedStreamTools.readCompressed(new FileInputStream(var4));
/* 122 */           NBTTagCompound var6 = var5.getCompoundTag("Data");
/* 123 */           var6.setString("LevelName", par2Str);
/* 124 */           CompressedStreamTools.writeCompressed(var5, new FileOutputStream(var4));
/*     */         }
/* 126 */         catch (Exception var7) {
/*     */           
/* 128 */           var7.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deleteWorldDirectory(String par1Str) {
/* 140 */     File var2 = new File(this.savesDirectory, par1Str);
/*     */     
/* 142 */     if (!var2.exists())
/*     */     {
/* 144 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 148 */     System.out.println("Deleting level " + par1Str);
/*     */     
/* 150 */     for (int var3 = 1; var3 <= 5; var3++) {
/*     */       
/* 152 */       System.out.println("Attempt " + var3 + "...");
/*     */       
/* 154 */       if (deleteFiles(var2.listFiles())) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 159 */       System.out.println("Unsuccessful in deleting contents.");
/*     */       
/* 161 */       if (var3 < 5) {
/*     */         
/*     */         try {
/*     */           
/* 165 */           Thread.sleep(500L);
/*     */         }
/* 167 */         catch (InterruptedException var5) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     return var2.delete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean deleteFiles(File[] par0ArrayOfFile) {
/* 184 */     for (int var1 = 0; var1 < par0ArrayOfFile.length; var1++) {
/*     */       
/* 186 */       File var2 = par0ArrayOfFile[var1];
/* 187 */       System.out.println("Deleting " + var2);
/*     */       
/* 189 */       if (var2.isDirectory() && !deleteFiles(var2.listFiles())) {
/*     */         
/* 191 */         System.out.println("Couldn't delete directory " + var2);
/* 192 */         return false;
/*     */       } 
/*     */       
/* 195 */       if (!var2.delete()) {
/*     */         
/* 197 */         System.out.println("Couldn't delete file " + var2);
/* 198 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 202 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISaveHandler getSaveLoader(String par1Str, boolean par2) {
/* 210 */     return new SaveHandler(this.savesDirectory, par1Str, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOldMapFormat(String par1Str) {
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean convertMapFormat(String par1Str, IProgressUpdate par2IProgressUpdate) {
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canLoadWorld(String par1Str) {
/* 234 */     File var2 = new File(this.savesDirectory, par1Str);
/* 235 */     return var2.isDirectory();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SaveFormatOld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */