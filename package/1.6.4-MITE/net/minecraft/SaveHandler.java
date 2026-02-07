/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SaveHandler
/*     */   implements ISaveHandler, IPlayerFileData
/*     */ {
/*     */   private final File worldDirectory;
/*     */   private final File playersDirectory;
/*     */   private final File mapDataDir;
/*  27 */   private final long initializationTime = MinecraftServer.getSystemTimeMillis();
/*     */ 
/*     */   
/*     */   private final String saveDirectoryName;
/*     */ 
/*     */   
/*     */   public SaveHandler(File par1File, String par2Str, boolean par3) {
/*  34 */     this.worldDirectory = new File(par1File, par2Str);
/*  35 */     this.worldDirectory.mkdirs();
/*  36 */     this.playersDirectory = new File(this.worldDirectory, "players");
/*  37 */     this.mapDataDir = new File(this.worldDirectory, "data");
/*  38 */     this.mapDataDir.mkdirs();
/*  39 */     this.saveDirectoryName = par2Str;
/*     */     
/*  41 */     if (par3)
/*     */     {
/*  43 */       this.playersDirectory.mkdirs();
/*     */     }
/*     */     
/*  46 */     setSessionLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSessionLock() {
/*     */     try {
/*  56 */       File var1 = new File(this.worldDirectory, "session.lock");
/*  57 */       DataOutputStream var2 = new DataOutputStream(new FileOutputStream(var1));
/*     */ 
/*     */       
/*     */       try {
/*  61 */         var2.writeLong(this.initializationTime);
/*     */       }
/*     */       finally {
/*     */         
/*  65 */         var2.close();
/*     */       }
/*     */     
/*  68 */     } catch (IOException var7) {
/*     */       
/*  70 */       var7.printStackTrace();
/*  71 */       throw new RuntimeException("Failed to check session lock, aborting");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected File getWorldDirectory() {
/*  80 */     return this.worldDirectory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkSessionLock() throws MinecraftException {
/*     */     try {
/*  90 */       File var1 = new File(this.worldDirectory, "session.lock");
/*  91 */       DataInputStream var2 = new DataInputStream(new FileInputStream(var1));
/*     */ 
/*     */       
/*     */       try {
/*  95 */         if (var2.readLong() != this.initializationTime)
/*     */         {
/*  97 */           Minecraft.setErrorMessage("checkSessionLock: aborting world save due to session lock");
/*  98 */           throw new MinecraftException("The save is being accessed from another location, aborting");
/*     */         }
/*     */       
/*     */       } finally {
/*     */         
/* 103 */         var2.close();
/*     */       }
/*     */     
/* 106 */     } catch (IOException var7) {
/*     */       
/* 108 */       throw new MinecraftException("Failed to check session lock, aborting");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkLoader getChunkLoader(WorldProvider par1WorldProvider) {
/* 117 */     throw new RuntimeException("Old Chunk Storage is no longer supported.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldInfo loadWorldInfo() {
/* 125 */     File var1 = new File(this.worldDirectory, "level.dat");
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (var1.exists()) {
/*     */       
/*     */       try {
/*     */         
/* 133 */         NBTTagCompound var2 = CompressedStreamTools.readCompressed(new FileInputStream(var1));
/* 134 */         NBTTagCompound var3 = var2.getCompoundTag("Data");
/* 135 */         return new WorldInfo(var3);
/*     */       }
/* 137 */       catch (Exception var5) {
/*     */         
/* 139 */         NBTBase.loading_world_info = false;
/* 140 */         var5.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 144 */     var1 = new File(this.worldDirectory, "level.dat_old");
/*     */     
/* 146 */     if (var1.exists()) {
/*     */       
/*     */       try {
/*     */         
/* 150 */         NBTTagCompound nBTTagCompound1 = CompressedStreamTools.readCompressed(new FileInputStream(var1));
/* 151 */         NBTTagCompound nBTTagCompound2 = nBTTagCompound1.getCompoundTag("Data");
/* 152 */         return new WorldInfo(nBTTagCompound2);
/*     */       }
/* 154 */       catch (Exception var4) {
/*     */         
/* 156 */         NBTBase.loading_world_info = false;
/* 157 */         var4.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveWorldInfoWithPlayer(WorldInfo par1WorldInfo, NBTTagCompound par2NBTTagCompound) {
/* 169 */     NBTTagCompound var3 = par1WorldInfo.cloneNBTCompound(par2NBTTagCompound);
/* 170 */     NBTTagCompound var4 = new NBTTagCompound();
/* 171 */     var4.setTag("Data", var3);
/*     */ 
/*     */     
/*     */     try {
/* 175 */       File var5 = new File(this.worldDirectory, "level.dat_new");
/* 176 */       File var6 = new File(this.worldDirectory, "level.dat_old");
/* 177 */       File var7 = new File(this.worldDirectory, "level.dat");
/* 178 */       CompressedStreamTools.writeCompressed(var4, new FileOutputStream(var5));
/*     */       
/* 180 */       if (var6.exists())
/*     */       {
/* 182 */         var6.delete();
/*     */       }
/*     */       
/* 185 */       var7.renameTo(var6);
/*     */       
/* 187 */       if (var7.exists())
/*     */       {
/* 189 */         var7.delete();
/*     */       }
/*     */       
/* 192 */       var5.renameTo(var7);
/*     */       
/* 194 */       if (var5.exists())
/*     */       {
/* 196 */         var5.delete();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 201 */       File achievements = new File(this.worldDirectory, "achievements.txt");
/*     */       
/* 203 */       FileWriter fw = new FileWriter(achievements);
/*     */       
/* 205 */       StringBuffer sb = new StringBuffer();
/*     */       
/* 207 */       sb.append("World Achievements" + StringHelper.newline);
/* 208 */       sb.append("------------------" + StringHelper.newline);
/*     */       
/* 210 */       Iterator<Map.Entry> i = par1WorldInfo.getAchievements().entrySet().iterator();
/*     */       
/* 212 */       while (i.hasNext()) {
/*     */         
/* 214 */         Map.Entry entry = i.next();
/*     */         
/* 216 */         WorldAchievement wa = (WorldAchievement)entry.getValue();
/*     */         
/* 218 */         sb.append(wa.achievement);
/* 219 */         sb.append(" taken by ");
/* 220 */         sb.append(wa.username);
/* 221 */         sb.append(" on Day ");
/* 222 */         sb.append(wa.day);
/* 223 */         sb.append(StringHelper.newline);
/*     */       } 
/*     */       
/* 226 */       fw.write(sb.toString());
/* 227 */       fw.close();
/*     */ 
/*     */     
/*     */     }
/* 231 */     catch (Exception var8) {
/*     */       
/* 233 */       var8.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveWorldInfo(WorldInfo par1WorldInfo) {
/* 242 */     NBTTagCompound var2 = par1WorldInfo.getNBTTagCompound();
/* 243 */     NBTTagCompound var3 = new NBTTagCompound();
/* 244 */     var3.setTag("Data", var2);
/*     */ 
/*     */     
/*     */     try {
/* 248 */       File var4 = new File(this.worldDirectory, "level.dat_new");
/* 249 */       File var5 = new File(this.worldDirectory, "level.dat_old");
/* 250 */       File var6 = new File(this.worldDirectory, "level.dat");
/* 251 */       CompressedStreamTools.writeCompressed(var3, new FileOutputStream(var4));
/*     */       
/* 253 */       if (var5.exists())
/*     */       {
/* 255 */         var5.delete();
/*     */       }
/*     */       
/* 258 */       var6.renameTo(var5);
/*     */       
/* 260 */       if (var6.exists())
/*     */       {
/* 262 */         var6.delete();
/*     */       }
/*     */       
/* 265 */       var4.renameTo(var6);
/*     */       
/* 267 */       if (var4.exists())
/*     */       {
/* 269 */         var4.delete();
/*     */       }
/*     */     }
/* 272 */     catch (Exception var7) {
/*     */       
/* 274 */       var7.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePlayerData(EntityPlayer par1EntityPlayer) {
/*     */     try {
/* 285 */       NBTTagCompound var2 = new NBTTagCompound();
/* 286 */       par1EntityPlayer.writeToNBT(var2);
/* 287 */       File var3 = new File(this.playersDirectory, par1EntityPlayer.getCommandSenderName() + ".dat.tmp");
/* 288 */       File var4 = new File(this.playersDirectory, par1EntityPlayer.getCommandSenderName() + ".dat");
/* 289 */       CompressedStreamTools.writeCompressed(var2, new FileOutputStream(var3));
/*     */       
/* 291 */       if (var4.exists())
/*     */       {
/* 293 */         var4.delete();
/*     */       }
/*     */       
/* 296 */       var3.renameTo(var4);
/*     */     }
/* 298 */     catch (Exception var5) {
/*     */       
/* 300 */       MinecraftServer.getServer().getLogAgent().logWarning("Failed to save player data for " + par1EntityPlayer.getCommandSenderName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound readPlayerData(EntityPlayer par1EntityPlayer) {
/* 309 */     NBTTagCompound var2 = getPlayerData(par1EntityPlayer.getCommandSenderName());
/*     */     
/* 311 */     if (var2 != null)
/*     */     {
/* 313 */       par1EntityPlayer.readFromNBT(var2);
/*     */     }
/*     */     
/* 316 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getPlayerData(String par1Str) {
/*     */     try {
/* 326 */       File var2 = new File(this.playersDirectory, par1Str + ".dat");
/*     */       
/* 328 */       if (var2.exists())
/*     */       {
/* 330 */         return CompressedStreamTools.readCompressed(new FileInputStream(var2));
/*     */       }
/*     */     }
/* 333 */     catch (Exception var3) {
/*     */       
/* 335 */       MinecraftServer.getServer().getLogAgent().logWarning("Failed to load player data for " + par1Str);
/*     */     } 
/*     */     
/* 338 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IPlayerFileData getSaveHandler() {
/* 346 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getAvailablePlayerDat() {
/* 354 */     String[] var1 = this.playersDirectory.list();
/*     */     
/* 356 */     for (int var2 = 0; var2 < var1.length; var2++) {
/*     */       
/* 358 */       if (var1[var2].endsWith(".dat"))
/*     */       {
/* 360 */         var1[var2] = var1[var2].substring(0, var1[var2].length() - 4);
/*     */       }
/*     */     } 
/*     */     
/* 364 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getMapFileFromName(String par1Str) {
/* 377 */     return new File(this.mapDataDir, par1Str + ".dat");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWorldDirectoryName() {
/* 385 */     return this.saveDirectoryName;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SaveHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */