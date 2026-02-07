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
/*     */ public final class PacketComponentBytes
/*     */   extends PacketComponent
/*     */ {
/*     */   private byte[] bytes_uncompressed;
/*     */   private int size_of_bytes_uncompressed;
/*     */   private boolean is_compressed;
/*     */   private byte[] bytes_compressed;
/*     */   private int size_of_bytes_compressed;
/*     */   private int compression_level;
/*     */   private Packet packet;
/*     */   
/*     */   public PacketComponentBytes(Packet packet) {
/*  26 */     this.packet = packet;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PacketComponentBytes(byte[] bytes_uncompressed, Packet packet) {
/*  32 */     this(bytes_uncompressed, -1, packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketComponentBytes(byte[] bytes_uncompressed, int compression_level, Packet packet) {
/*  37 */     this.bytes_uncompressed = bytes_uncompressed;
/*  38 */     this.size_of_bytes_uncompressed = bytes_uncompressed.length;
/*     */     
/*  40 */     this.compression_level = compression_level;
/*     */     
/*  42 */     this.packet = packet;
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketComponentBytes(String string, int compression_level, Packet packet) {
/*  47 */     this(string.getBytes(), compression_level, packet);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compress() {
/*  53 */     if (this.is_compressed) {
/*     */       
/*  55 */       Minecraft.setErrorMessage("compress: bytes block is already compressed");
/*  56 */       return false;
/*     */     } 
/*     */     
/*  59 */     if (this.compression_level == 0) {
/*  60 */       return false;
/*     */     }
/*  62 */     CompressionResult result = Packet.tryCompress(this.bytes_uncompressed, this.compression_level, this.packet);
/*     */     
/*  64 */     if (result.compressionOccurred()) {
/*     */       
/*  66 */       this.is_compressed = true;
/*     */       
/*  68 */       this.bytes_compressed = result.getOutput();
/*  69 */       this.size_of_bytes_compressed = result.getOutputSize();
/*     */       
/*  71 */       return true;
/*     */     } 
/*     */     
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean decompress() {
/*  80 */     if (!this.is_compressed) {
/*     */       
/*  82 */       Minecraft.setErrorMessage("decompress: payload is not compressed");
/*  83 */       return false;
/*     */     } 
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
/* 117 */     DecompressionResult result = Packet.decompress(this.bytes_compressed, this.size_of_bytes_uncompressed, this.packet);
/*     */     
/* 119 */     this.bytes_uncompressed = result.getOutput();
/*     */     
/* 121 */     return !(this.is_compressed = !result.decompressionOccurredAndMatchedExpectedSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBytesAsString() {
/* 126 */     return new String(this.bytes_uncompressed);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getBytes() {
/* 131 */     return this.bytes_uncompressed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readData(DataInput par1DataInput) throws IOException {
/* 136 */     this.size_of_bytes_uncompressed = par1DataInput.readShort();
/* 137 */     this.is_compressed = par1DataInput.readBoolean();
/*     */     
/* 139 */     if (this.is_compressed) {
/*     */       
/* 141 */       this.size_of_bytes_compressed = par1DataInput.readShort();
/* 142 */       this.bytes_compressed = new byte[this.size_of_bytes_compressed];
/* 143 */       par1DataInput.readFully(this.bytes_compressed);
/*     */       
/* 145 */       decompress();
/*     */     }
/*     */     else {
/*     */       
/* 149 */       this.bytes_uncompressed = new byte[this.size_of_bytes_uncompressed];
/* 150 */       par1DataInput.readFully(this.bytes_uncompressed);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeData(DataOutput par1DataOutput) throws IOException {
/* 156 */     par1DataOutput.writeShort(this.size_of_bytes_uncompressed);
/* 157 */     par1DataOutput.writeBoolean(this.is_compressed);
/*     */     
/* 159 */     if (this.is_compressed) {
/*     */       
/* 161 */       par1DataOutput.writeShort(this.size_of_bytes_compressed);
/* 162 */       par1DataOutput.write(this.bytes_compressed, 0, this.size_of_bytes_compressed);
/*     */     }
/*     */     else {
/*     */       
/* 166 */       par1DataOutput.write(this.bytes_uncompressed);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 172 */     int size = 3;
/*     */     
/* 174 */     if (this.is_compressed) {
/* 175 */       size += 2 + this.size_of_bytes_compressed;
/*     */     } else {
/* 177 */       size += this.size_of_bytes_uncompressed;
/*     */     } 
/* 179 */     return size;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PacketComponentBytes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */