/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class AnvilSaveConverter
/*     */   extends SaveFormatOld
/*     */ {
/*     */   public AnvilSaveConverter(File par1File) {
/*  18 */     super(par1File);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSaveList() throws AnvilConverterException {
/*  23 */     if (this.savesDirectory != null && this.savesDirectory.exists() && this.savesDirectory.isDirectory()) {
/*     */       
/*  25 */       ArrayList<SaveFormatComparator> var1 = new ArrayList();
/*  26 */       File[] var2 = this.savesDirectory.listFiles();
/*  27 */       File[] var3 = var2;
/*  28 */       int var4 = var2.length;
/*     */       
/*  30 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/*  32 */         File var6 = var3[var5];
/*     */         
/*  34 */         if (var6.isDirectory()) {
/*     */           
/*  36 */           String var7 = var6.getName();
/*  37 */           WorldInfo var8 = getWorldInfo(var7);
/*     */           
/*  39 */           if (var8 != null && (var8.getSaveVersion() == 19132 || var8.getSaveVersion() == 19133)) {
/*     */             
/*  41 */             boolean var9 = (var8.getSaveVersion() != getSaveVersion());
/*  42 */             String var10 = var8.getWorldName();
/*     */             
/*  44 */             if (var10 == null || MathHelper.stringNullOrLengthZero(var10))
/*     */             {
/*  46 */               var10 = var7;
/*     */             }
/*     */             
/*  49 */             long var11 = 0L;
/*     */             
/*  51 */             var1.add(new SaveFormatComparator(var7, var10, var8.getLastTimePlayed(), var11, var8.getGameType(), var9, var8.isHardcoreModeEnabled(), var8.areCommandsAllowed(), var8.areSkillsEnabled(), var8.isValidMITEWorld(), var8.getIsNotValidReason()));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  56 */       return var1;
/*     */     } 
/*     */ 
/*     */     
/*  60 */     throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getSaveVersion() {
/*  66 */     return 19133;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flushCache() {
/*  71 */     RegionFileCache.clearRegionFileReferences();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISaveHandler getSaveLoader(String par1Str, boolean par2) {
/*  79 */     return new AnvilSaveHandler(this.savesDirectory, par1Str, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOldMapFormat(String par1Str) {
/*  87 */     WorldInfo var2 = getWorldInfo(par1Str);
/*  88 */     return (var2 != null && var2.getSaveVersion() != getSaveVersion());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean convertMapFormat(String par1Str, IProgressUpdate par2IProgressUpdate) {
/*  96 */     par2IProgressUpdate.setLoadingProgress(0);
/*  97 */     ArrayList var3 = new ArrayList();
/*  98 */     ArrayList list_underworld = new ArrayList();
/*  99 */     ArrayList var4 = new ArrayList();
/* 100 */     ArrayList var5 = new ArrayList();
/* 101 */     File var6 = new File(this.savesDirectory, par1Str);
/* 102 */     File dir_underworld = new File(var6, "DIM-2");
/* 103 */     File var7 = new File(var6, "DIM-1");
/* 104 */     File var8 = new File(var6, "DIM1");
/* 105 */     MinecraftServer.getServer().getLogAgent().logInfo("Scanning folders...");
/* 106 */     addRegionFilesToCollection(var6, var3);
/*     */     
/* 108 */     if (dir_underworld.exists()) {
/* 109 */       addRegionFilesToCollection(dir_underworld, list_underworld);
/*     */     }
/* 111 */     if (var7.exists())
/*     */     {
/* 113 */       addRegionFilesToCollection(var7, var4);
/*     */     }
/*     */     
/* 116 */     if (var8.exists())
/*     */     {
/* 118 */       addRegionFilesToCollection(var8, var5);
/*     */     }
/*     */ 
/*     */     
/* 122 */     int var9 = var3.size() + list_underworld.size() + var4.size() + var5.size();
/* 123 */     MinecraftServer.getServer().getLogAgent().logInfo("Total conversion count is " + var9);
/* 124 */     WorldInfo var10 = getWorldInfo(par1Str);
/* 125 */     Object var11 = null;
/*     */     
/* 127 */     if (var10.getTerrainType() == WorldType.FLAT) {
/*     */       
/* 129 */       var11 = new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F, 0.5F);
/*     */     }
/*     */     else {
/*     */       
/* 133 */       var11 = new WorldChunkManager(var10.getSeed(), var10.getTerrainType());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     int offset = 0;
/*     */     
/* 144 */     convertFile(new File(var6, "region"), var3, (WorldChunkManager)var11, offset, var9, par2IProgressUpdate);
/* 145 */     convertFile(new File(dir_underworld, "region"), list_underworld, new WorldChunkManagerHell(BiomeGenBase.underworld, 1.0F, 0.0F), offset += var3.size(), var9, par2IProgressUpdate);
/* 146 */     convertFile(new File(var7, "region"), var4, new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F), offset += list_underworld.size(), var9, par2IProgressUpdate);
/* 147 */     convertFile(new File(var8, "region"), var5, new WorldChunkManagerHell(BiomeGenBase.sky, 0.5F, 0.0F), offset += var4.size(), var9, par2IProgressUpdate);
/*     */ 
/*     */ 
/*     */     
/* 151 */     var10.setSaveVersion(19133);
/*     */     
/* 153 */     if (var10.getTerrainType() == WorldType.DEFAULT_1_1)
/*     */     {
/* 155 */       var10.setTerrainType(WorldType.DEFAULT);
/*     */     }
/*     */     
/* 158 */     createFile(par1Str);
/* 159 */     ISaveHandler var12 = getSaveLoader(par1Str, false);
/* 160 */     var12.saveWorldInfo(var10);
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createFile(String par1Str) {
/* 169 */     File var2 = new File(this.savesDirectory, par1Str);
/*     */     
/* 171 */     if (!var2.exists()) {
/*     */       
/* 173 */       System.out.println("Warning: Unable to create level.dat_mcr backup");
/*     */     }
/*     */     else {
/*     */       
/* 177 */       File var3 = new File(var2, "level.dat");
/*     */       
/* 179 */       if (!var3.exists()) {
/*     */         
/* 181 */         System.out.println("Warning: Unable to create level.dat_mcr backup");
/*     */       }
/*     */       else {
/*     */         
/* 185 */         File var4 = new File(var2, "level.dat_mcr");
/*     */         
/* 187 */         if (!var3.renameTo(var4))
/*     */         {
/* 189 */           System.out.println("Warning: Unable to create level.dat_mcr backup");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void convertFile(File par1File, Iterable par2Iterable, WorldChunkManager par3WorldChunkManager, int par4, int par5, IProgressUpdate par6IProgressUpdate) {
/* 197 */     Iterator<File> var7 = par2Iterable.iterator();
/*     */     
/* 199 */     while (var7.hasNext()) {
/*     */       
/* 201 */       File var8 = var7.next();
/* 202 */       convertChunks(par1File, var8, par3WorldChunkManager, par4, par5, par6IProgressUpdate);
/* 203 */       par4++;
/* 204 */       int var9 = (int)Math.round(100.0D * par4 / par5);
/* 205 */       par6IProgressUpdate.setLoadingProgress(var9);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertChunks(File par1File, File par2File, WorldChunkManager par3WorldChunkManager, int par4, int par5, IProgressUpdate par6IProgressUpdate) {
/*     */     try {
/* 216 */       String var7 = par2File.getName();
/* 217 */       RegionFile var8 = new RegionFile(par2File);
/* 218 */       RegionFile var9 = new RegionFile(new File(par1File, var7.substring(0, var7.length() - ".mcr".length()) + ".mca"));
/*     */       
/* 220 */       for (int var10 = 0; var10 < 32; var10++) {
/*     */         int var11;
/*     */ 
/*     */         
/* 224 */         for (var11 = 0; var11 < 32; var11++) {
/*     */           
/* 226 */           if (var8.isChunkSaved(var10, var11) && !var9.isChunkSaved(var10, var11)) {
/*     */             
/* 228 */             DataInputStream var12 = var8.getChunkDataInputStream(var10, var11);
/*     */             
/* 230 */             if (var12 == null) {
/*     */               
/* 232 */               MinecraftServer.getServer().getLogAgent().logWarning("Failed to fetch input stream");
/*     */             }
/*     */             else {
/*     */               
/* 236 */               NBTTagCompound var13 = CompressedStreamTools.read(var12);
/* 237 */               var12.close();
/* 238 */               NBTTagCompound var14 = var13.getCompoundTag("Level");
/* 239 */               AnvilConverterData var15 = ChunkLoader.load(var14);
/* 240 */               NBTTagCompound var16 = new NBTTagCompound();
/* 241 */               NBTTagCompound var17 = new NBTTagCompound();
/* 242 */               var16.setTag("Level", var17);
/* 243 */               ChunkLoader.convertToAnvilFormat(var15, var17, par3WorldChunkManager);
/* 244 */               DataOutputStream var18 = var9.getChunkDataOutputStream(var10, var11);
/* 245 */               CompressedStreamTools.write(var16, var18);
/* 246 */               var18.close();
/* 247 */               var9.deflater_output_stream_to_close.close();
/* 248 */               var9.chunk_buffer_to_close.close();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 253 */         var11 = (int)Math.round(100.0D * (par4 * 1024) / (par5 * 1024));
/* 254 */         int var20 = (int)Math.round(100.0D * ((var10 + 1) * 32 + par4 * 1024) / (par5 * 1024));
/*     */         
/* 256 */         if (var20 > var11)
/*     */         {
/* 258 */           par6IProgressUpdate.setLoadingProgress(var20);
/*     */         }
/*     */       } 
/*     */       
/* 262 */       var8.close();
/* 263 */       var9.close();
/*     */     }
/* 265 */     catch (IOException var19) {
/*     */       
/* 267 */       Debug.println("Did you see this?");
/* 268 */       var19.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addRegionFilesToCollection(File par1File, Collection<? super File> par2Collection) {
/* 277 */     File var3 = new File(par1File, "region");
/* 278 */     File[] var4 = var3.listFiles(new AnvilSaveConverterFileFilter(this));
/*     */     
/* 280 */     if (var4 != null)
/*     */     {
/* 282 */       Collections.addAll(par2Collection, var4);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AnvilSaveConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */