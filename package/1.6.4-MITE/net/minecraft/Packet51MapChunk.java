/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public class Packet51MapChunk
/*     */   extends Packet
/*     */ {
/*     */   public int xCh;
/*     */   public int zCh;
/*     */   public int yChMin;
/*     */   public int yChMax;
/*     */   private byte[] compressed_chunk_data;
/*     */   private byte[] uncompressed_chunk_data;
/*     */   public boolean includeInitialize;
/*     */   private int compressed_chunk_data_length;
/*  43 */   private static byte[] temp = new byte[196864];
/*     */ 
/*     */   
/*     */   private static boolean lock_temp;
/*     */ 
/*     */   
/*     */   public Packet51MapChunk() {
/*  50 */     this.isChunkDataPacket = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet51MapChunk(Chunk par1Chunk, boolean par2, int par3) {
/*  55 */     this.isChunkDataPacket = true;
/*  56 */     this.xCh = par1Chunk.xPosition;
/*  57 */     this.zCh = par1Chunk.zPosition;
/*  58 */     this.includeInitialize = par2;
/*  59 */     Packet51MapChunkData var4 = getMapChunkData(par1Chunk, par2, par3);
/*     */     
/*  61 */     this.yChMax = var4.chunkHasAddSectionFlag;
/*  62 */     this.yChMin = var4.chunkExistFlag;
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
/*  79 */     this.uncompressed_chunk_data = var4.uncompressed_data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void compressPayload() {
/*  85 */     CompressionResult result = tryCompress(this.uncompressed_chunk_data, -1, this);
/*     */     
/*  87 */     this.compressed_chunk_data = result.getOutput();
/*  88 */     this.compressed_chunk_data_length = result.getOutputSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*     */     try {
/*  98 */       lockTemp();
/*     */       
/* 100 */       this.xCh = par1DataInput.readInt();
/* 101 */       this.zCh = par1DataInput.readInt();
/* 102 */       this.includeInitialize = par1DataInput.readBoolean();
/* 103 */       this.yChMin = par1DataInput.readShort();
/* 104 */       this.yChMax = par1DataInput.readShort();
/* 105 */       int uncompressed_chunk_data_length = par1DataInput.readInt();
/* 106 */       this.compressed_chunk_data_length = par1DataInput.readInt();
/*     */       
/* 108 */       if (temp.length < this.compressed_chunk_data_length)
/*     */       {
/* 110 */         temp = new byte[this.compressed_chunk_data_length];
/*     */       }
/*     */       
/* 113 */       par1DataInput.readFully(temp, 0, this.compressed_chunk_data_length);
/* 114 */       int var2 = 0;
/*     */       
/*     */       int var3;
/* 117 */       for (var3 = 0; var3 < 16; var3++)
/*     */       {
/* 119 */         var2 += this.yChMin >> var3 & 0x1;
/*     */       }
/*     */       
/* 122 */       var3 = 12288 * var2;
/*     */       
/* 124 */       if (this.includeInitialize)
/*     */       {
/* 126 */         var3 += 256;
/*     */       }
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
/* 153 */       DecompressionResult result = decompress(temp, 0, this.compressed_chunk_data_length, uncompressed_chunk_data_length, this);
/*     */       
/* 155 */       this.uncompressed_chunk_data = result.getOutput();
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 160 */       unlockTemp();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 169 */     par1DataOutput.writeInt(this.xCh);
/* 170 */     par1DataOutput.writeInt(this.zCh);
/* 171 */     par1DataOutput.writeBoolean(this.includeInitialize);
/* 172 */     par1DataOutput.writeShort((short)(this.yChMin & 0xFFFF));
/* 173 */     par1DataOutput.writeShort((short)(this.yChMax & 0xFFFF));
/* 174 */     par1DataOutput.writeInt(this.uncompressed_chunk_data.length);
/* 175 */     par1DataOutput.writeInt(this.compressed_chunk_data_length);
/* 176 */     par1DataOutput.write(this.compressed_chunk_data, 0, this.compressed_chunk_data_length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 184 */     par1NetHandler.handleMapChunk(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 192 */     return 21 + this.compressed_chunk_data_length;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getUncompressedChunkData() {
/* 197 */     return this.uncompressed_chunk_data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Packet51MapChunkData getMapChunkData(Chunk par0Chunk, boolean par1, int par2) {
/*     */     try {
/* 205 */       lockTemp();
/*     */       
/* 207 */       int var3 = 0;
/* 208 */       ExtendedBlockStorage[] var4 = par0Chunk.getBlockStorageArray();
/* 209 */       int var5 = 0;
/* 210 */       Packet51MapChunkData var6 = new Packet51MapChunkData();
/* 211 */       byte[] var7 = temp;
/*     */       
/* 213 */       if (par1)
/*     */       {
/* 215 */         par0Chunk.sendUpdates = true;
/*     */       }
/*     */       
/*     */       int var8;
/*     */       
/* 220 */       for (var8 = 0; var8 < var4.length; var8++) {
/*     */ 
/*     */         
/* 223 */         if (var4[var8] != null && (par2 & 1 << var8) != 0) {
/*     */           
/* 225 */           var6.chunkExistFlag |= 1 << var8;
/*     */           
/* 227 */           if (var4[var8].getBlockMSBArray() != null) {
/*     */             
/* 229 */             var6.chunkHasAddSectionFlag |= 1 << var8;
/* 230 */             var5++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 235 */       for (var8 = 0; var8 < var4.length; var8++) {
/*     */ 
/*     */         
/* 238 */         if (var4[var8] != null && (par2 & 1 << var8) != 0) {
/*     */           
/* 240 */           byte[] var9 = var4[var8].getBlockLSBArray();
/* 241 */           System.arraycopy(var9, 0, var7, var3, var9.length);
/* 242 */           var3 += var9.length;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 248 */       for (var8 = 0; var8 < var4.length; var8++) {
/*     */ 
/*     */         
/* 251 */         if (var4[var8] != null && (par2 & 1 << var8) != 0) {
/*     */           
/* 253 */           NibbleArray var10 = var4[var8].getMetadataArray();
/* 254 */           System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
/* 255 */           var3 += var10.data.length;
/*     */         } 
/*     */       } 
/*     */       
/* 259 */       for (var8 = 0; var8 < var4.length; var8++) {
/*     */ 
/*     */         
/* 262 */         if (var4[var8] != null && (par2 & 1 << var8) != 0) {
/*     */           
/* 264 */           NibbleArray var10 = var4[var8].getBlocklightArray();
/* 265 */           System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
/* 266 */           var3 += var10.data.length;
/*     */         } 
/*     */       } 
/*     */       
/* 270 */       if (!par0Chunk.worldObj.provider.hasNoSky)
/*     */       {
/* 272 */         for (var8 = 0; var8 < var4.length; var8++) {
/*     */ 
/*     */           
/* 275 */           if (var4[var8] != null && (par2 & 1 << var8) != 0) {
/*     */             
/* 277 */             NibbleArray var10 = var4[var8].getSkylightArray();
/* 278 */             System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
/* 279 */             var3 += var10.data.length;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 284 */       if (var5 > 0)
/*     */       {
/* 286 */         for (var8 = 0; var8 < var4.length; var8++) {
/*     */ 
/*     */           
/* 289 */           if (var4[var8] != null && var4[var8].getBlockMSBArray() != null && (par2 & 1 << var8) != 0) {
/*     */             
/* 291 */             NibbleArray var10 = var4[var8].getBlockMSBArray();
/* 292 */             System.arraycopy(var10.data, 0, var7, var3, var10.data.length);
/* 293 */             var3 += var10.data.length;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 298 */       if (par1) {
/*     */         
/* 300 */         byte[] var11 = par0Chunk.getBiomeArray();
/* 301 */         System.arraycopy(var11, 0, var7, var3, var11.length);
/* 302 */         var3 += var11.length;
/*     */       } 
/*     */ 
/*     */       
/* 306 */       if (par0Chunk.worldObj.hasSkylight()) {
/*     */         
/* 308 */         int[] skylight_bottom = par0Chunk.skylight_bottom;
/*     */         
/* 310 */         for (int i = 0; i < skylight_bottom.length; i++) {
/* 311 */           var7[var3 + i] = (byte)skylight_bottom[i];
/*     */         }
/* 313 */         var3 += par0Chunk.skylight_bottom.length;
/*     */       } 
/*     */       
/* 316 */       if (var3 > var7.length) {
/* 317 */         Minecraft.setErrorMessage("getMapChunkData: var3>var7.length");
/*     */       }
/* 319 */       var6.uncompressed_data = new byte[var3];
/* 320 */       System.arraycopy(var7, 0, var6.uncompressed_data, 0, var3);
/* 321 */       return var6;
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 326 */       unlockTemp();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void lockTemp() {
/* 332 */     if (lock_temp) {
/* 333 */       Minecraft.setErrorMessage("lockTemp: Already locked!");
/*     */     } else {
/* 335 */       lock_temp = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void unlockTemp() {
/* 340 */     if (lock_temp) {
/* 341 */       lock_temp = false;
/*     */     } else {
/* 343 */       Minecraft.setErrorMessage("unlockTemp: Already unlocked!");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet51MapChunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */