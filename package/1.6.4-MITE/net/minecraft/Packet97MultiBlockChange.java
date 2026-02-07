/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet97MultiBlockChange
/*    */   extends Packet
/*    */ {
/*    */   public int chunk_x;
/*    */   public int chunk_z;
/*    */   public int num_blocks;
/*    */   private PacketComponentBytes bytes;
/*    */   
/*    */   public Packet97MultiBlockChange() {
/* 20 */     this.isChunkDataPacket = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet97MultiBlockChange(int chunk_x, int chunk_z, short[] local_coords, int num_blocks, World world) {
/* 25 */     this.isChunkDataPacket = true;
/*    */     
/* 27 */     this.chunk_x = chunk_x;
/* 28 */     this.chunk_z = chunk_z;
/*    */     
/* 30 */     this.num_blocks = num_blocks;
/*    */     
/* 32 */     Chunk chunk = world.getChunkFromChunkCoords(chunk_x, chunk_z);
/*    */     
/* 34 */     byte[] bytes = new byte[num_blocks * 5];
/*    */     
/* 36 */     for (int i = 0; i < num_blocks; i++) {
/*    */       
/* 38 */       int offset = i * 5;
/*    */       
/* 40 */       int x = local_coords[i] >> 12 & 0xF;
/* 41 */       int y = local_coords[i] & 0xFF;
/* 42 */       int z = local_coords[i] >> 8 & 0xF;
/*    */       
/* 44 */       int block_id = chunk.getBlockID(x, y, z);
/* 45 */       int metadata = chunk.getBlockMetadata(x, y, z);
/*    */       
/* 47 */       bytes[offset] = (byte)x;
/* 48 */       bytes[offset + 1] = (byte)y;
/* 49 */       bytes[offset + 2] = (byte)z;
/*    */       
/* 51 */       bytes[offset + 3] = (byte)block_id;
/* 52 */       bytes[offset + 4] = (byte)metadata;
/*    */     } 
/*    */     
/* 55 */     this.bytes = new PacketComponentBytes(bytes, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void compressPayload() {
/* 60 */     this.bytes.compress();
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 65 */     this.chunk_x = par1DataInput.readInt();
/* 66 */     this.chunk_z = par1DataInput.readInt();
/*    */     
/* 68 */     this.num_blocks = par1DataInput.readShort() & 0xFFFF;
/*    */     
/* 70 */     this.bytes = new PacketComponentBytes(this);
/* 71 */     this.bytes.readData(par1DataInput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 76 */     par1DataOutput.writeInt(this.chunk_x);
/* 77 */     par1DataOutput.writeInt(this.chunk_z);
/*    */     
/* 79 */     par1DataOutput.writeShort((short)this.num_blocks);
/*    */     
/* 81 */     this.bytes.writeData(par1DataOutput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 86 */     net_handler.handleMultiBlockChange(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 91 */     return 10 + this.bytes.getSize();
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getBytes() {
/* 96 */     return this.bytes.getBytes();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet97MultiBlockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */