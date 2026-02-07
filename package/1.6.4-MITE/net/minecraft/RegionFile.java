/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class RegionFile
/*     */ {
/*  18 */   private static final byte[] emptySector = new byte[4096];
/*     */   private final File fileName;
/*     */   private RandomAccessFile dataFile;
/*  21 */   private final int[] offsets = new int[1024];
/*  22 */   private final int[] chunkTimestamps = new int[1024];
/*     */   
/*     */   private ArrayList sectorFree;
/*     */   
/*     */   private int sizeDelta;
/*     */   
/*     */   private long lastModified;
/*     */   
/*     */   public RegionFileChunkBuffer chunk_buffer_to_close;
/*     */   
/*     */   public DeflaterOutputStream deflater_output_stream_to_close;
/*     */   private boolean is_data_file_closed;
/*     */   public Thread thread_that_closed_the_data_file;
/*     */   public int times_the_data_file_was_closed;
/*     */   
/*     */   public RegionFile(File par1File) {
/*  38 */     this.fileName = par1File;
/*  39 */     this.sizeDelta = 0;
/*     */ 
/*     */     
/*     */     try {
/*  43 */       if (par1File.exists())
/*     */       {
/*  45 */         this.lastModified = par1File.lastModified();
/*     */       }
/*     */       
/*  48 */       this.dataFile = new RandomAccessFile(par1File, "rw");
/*     */ 
/*     */       
/*  51 */       if (this.dataFile.length() < 4096L) {
/*     */         int i;
/*  53 */         for (i = 0; i < 1024; i++)
/*     */         {
/*  55 */           this.dataFile.writeInt(0);
/*     */         }
/*     */         
/*  58 */         for (i = 0; i < 1024; i++)
/*     */         {
/*  60 */           this.dataFile.writeInt(0);
/*     */         }
/*     */         
/*  63 */         this.sizeDelta += 8192;
/*     */       } 
/*     */       
/*  66 */       if ((this.dataFile.length() & 0xFFFL) != 0L)
/*     */       {
/*  68 */         for (int i = 0; i < (this.dataFile.length() & 0xFFFL); i++)
/*     */         {
/*  70 */           this.dataFile.write(0);
/*     */         }
/*     */       }
/*     */       
/*  74 */       int var2 = (int)this.dataFile.length() / 4096;
/*  75 */       this.sectorFree = new ArrayList(var2);
/*     */       
/*     */       int var3;
/*  78 */       for (var3 = 0; var3 < var2; var3++)
/*     */       {
/*  80 */         this.sectorFree.add(Boolean.valueOf(true));
/*     */       }
/*     */       
/*  83 */       this.sectorFree.set(0, Boolean.valueOf(false));
/*  84 */       this.sectorFree.set(1, Boolean.valueOf(false));
/*  85 */       this.dataFile.seek(0L);
/*     */ 
/*     */       
/*  88 */       for (var3 = 0; var3 < 1024; var3++) {
/*     */         
/*  90 */         int var4 = this.dataFile.readInt();
/*  91 */         this.offsets[var3] = var4;
/*     */         
/*  93 */         if (var4 != 0 && (var4 >> 8) + (var4 & 0xFF) <= this.sectorFree.size())
/*     */         {
/*  95 */           for (int var5 = 0; var5 < (var4 & 0xFF); var5++)
/*     */           {
/*  97 */             this.sectorFree.set((var4 >> 8) + var5, Boolean.valueOf(false));
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/* 102 */       for (var3 = 0; var3 < 1024; var3++)
/*     */       {
/* 104 */         int var4 = this.dataFile.readInt();
/* 105 */         this.chunkTimestamps[var3] = var4;
/*     */       }
/*     */     
/* 108 */     } catch (IOException var6) {
/*     */       
/* 110 */       var6.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized DataInputStream getChunkDataInputStream(int par1, int par2) {
/* 119 */     if (outOfBounds(par1, par2))
/*     */     {
/* 121 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 127 */       int var3 = getOffset(par1, par2);
/*     */       
/* 129 */       if (var3 == 0)
/*     */       {
/* 131 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 135 */       int var4 = var3 >> 8;
/* 136 */       int var5 = var3 & 0xFF;
/*     */       
/* 138 */       if (var4 + var5 > this.sectorFree.size())
/*     */       {
/* 140 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 144 */       this.dataFile.seek((var4 * 4096));
/* 145 */       int var6 = this.dataFile.readInt();
/*     */       
/* 147 */       if (var6 > 4096 * var5)
/*     */       {
/* 149 */         return null;
/*     */       }
/* 151 */       if (var6 <= 0)
/*     */       {
/* 153 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 157 */       byte var7 = this.dataFile.readByte();
/*     */ 
/*     */       
/* 160 */       if (var7 == 1) {
/*     */         
/* 162 */         byte[] var8 = new byte[var6 - 1];
/* 163 */         this.dataFile.read(var8);
/* 164 */         return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(var8))));
/*     */       } 
/* 166 */       if (var7 == 2) {
/*     */         
/* 168 */         byte[] var8 = new byte[var6 - 1];
/* 169 */         this.dataFile.read(var8);
/* 170 */         return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(var8))));
/*     */       } 
/*     */ 
/*     */       
/* 174 */       return null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 180 */     catch (IOException var9) {
/*     */       
/* 182 */       return null;
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
/*     */   public DataOutputStream getChunkDataOutputStream(int par1, int par2) {
/* 194 */     this.chunk_buffer_to_close = new RegionFileChunkBuffer(this, par1, par2);
/* 195 */     this.deflater_output_stream_to_close = new DeflaterOutputStream(this.chunk_buffer_to_close);
/* 196 */     return outOfBounds(par1, par2) ? null : new DataOutputStream(this.deflater_output_stream_to_close);
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
/*     */   protected synchronized void write(int par1, int par2, byte[] par3ArrayOfByte, int par4) {
/*     */     try {
/* 212 */       int var5 = getOffset(par1, par2);
/* 213 */       int var6 = var5 >> 8;
/* 214 */       int var7 = var5 & 0xFF;
/* 215 */       int var8 = (par4 + 5) / 4096 + 1;
/*     */       
/* 217 */       if (var8 >= 256) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 222 */       if (var6 != 0 && var7 == var8) {
/*     */         
/* 224 */         write(var6, par3ArrayOfByte, par4);
/*     */       } else {
/*     */         int var9;
/*     */ 
/*     */ 
/*     */         
/* 230 */         for (var9 = 0; var9 < var7; var9++)
/*     */         {
/* 232 */           this.sectorFree.set(var6 + var9, Boolean.valueOf(true));
/*     */         }
/*     */         
/* 235 */         var9 = this.sectorFree.indexOf(Boolean.valueOf(true));
/* 236 */         int var10 = 0;
/*     */ 
/*     */         
/* 239 */         if (var9 != -1)
/*     */         {
/* 241 */           for (int var11 = var9; var11 < this.sectorFree.size(); var11++) {
/*     */             
/* 243 */             if (var10 != 0) {
/*     */               
/* 245 */               if (((Boolean)this.sectorFree.get(var11)).booleanValue())
/*     */               {
/* 247 */                 var10++;
/*     */               }
/*     */               else
/*     */               {
/* 251 */                 var10 = 0;
/*     */               }
/*     */             
/* 254 */             } else if (((Boolean)this.sectorFree.get(var11)).booleanValue()) {
/*     */               
/* 256 */               var9 = var11;
/* 257 */               var10 = 1;
/*     */             } 
/*     */             
/* 260 */             if (var10 >= var8) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 267 */         if (var10 >= var8) {
/*     */           
/* 269 */           var6 = var9;
/* 270 */           setOffset(par1, par2, var9 << 8 | var8);
/*     */           
/* 272 */           for (int var11 = 0; var11 < var8; var11++)
/*     */           {
/* 274 */             this.sectorFree.set(var6 + var11, Boolean.valueOf(false));
/*     */           }
/*     */           
/* 277 */           write(var6, par3ArrayOfByte, par4);
/*     */         }
/*     */         else {
/*     */           
/* 281 */           this.dataFile.seek(this.dataFile.length());
/* 282 */           var6 = this.sectorFree.size();
/*     */           
/* 284 */           for (int var11 = 0; var11 < var8; var11++) {
/*     */             
/* 286 */             this.dataFile.write(emptySector);
/* 287 */             this.sectorFree.add(Boolean.valueOf(false));
/*     */           } 
/*     */           
/* 290 */           this.sizeDelta += 4096 * var8;
/* 291 */           write(var6, par3ArrayOfByte, par4);
/* 292 */           setOffset(par1, par2, var6 << 8 | var8);
/*     */         } 
/*     */       } 
/*     */       
/* 296 */       setChunkTimestamp(par1, par2, (int)(MinecraftServer.getSystemTimeMillis() / 1000L));
/*     */     }
/* 298 */     catch (IOException var12) {
/*     */       
/* 300 */       var12.printStackTrace();
/* 301 */       Debug.println("Information: Thread that closed the file: " + this.thread_that_closed_the_data_file + ", thread that tried to write to data file: " + Thread.currentThread() + ", times the files was closed: " + this.times_the_data_file_was_closed);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void write(int par1, byte[] par2ArrayOfByte, int par3) throws IOException {
/* 310 */     this.dataFile.seek((par1 * 4096));
/* 311 */     this.dataFile.writeInt(par3 + 1);
/* 312 */     this.dataFile.writeByte(2);
/* 313 */     this.dataFile.write(par2ArrayOfByte, 0, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean outOfBounds(int par1, int par2) {
/* 321 */     return (par1 < 0 || par1 >= 32 || par2 < 0 || par2 >= 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getOffset(int par1, int par2) {
/* 329 */     return this.offsets[par1 + par2 * 32];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChunkSaved(int par1, int par2) {
/* 337 */     return (getOffset(par1, par2) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setOffset(int par1, int par2, int par3) throws IOException {
/* 345 */     this.offsets[par1 + par2 * 32] = par3;
/* 346 */     this.dataFile.seek(((par1 + par2 * 32) * 4));
/* 347 */     this.dataFile.writeInt(par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setChunkTimestamp(int par1, int par2, int par3) throws IOException {
/* 355 */     this.chunkTimestamps[par1 + par2 * 32] = par3;
/* 356 */     this.dataFile.seek((4096 + (par1 + par2 * 32) * 4));
/* 357 */     this.dataFile.writeInt(par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 365 */     if (this.dataFile != null) {
/*     */       
/* 367 */       this.dataFile.close();
/* 368 */       this.is_data_file_closed = true;
/*     */       
/* 370 */       this.thread_that_closed_the_data_file = Thread.currentThread();
/* 371 */       this.times_the_data_file_was_closed++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RegionFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */