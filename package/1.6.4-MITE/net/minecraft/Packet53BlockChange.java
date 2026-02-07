/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet53BlockChange
/*    */   extends Packet {
/*    */   public int xPosition;
/*    */   public int yPosition;
/*    */   
/*    */   public Packet53BlockChange() {
/* 12 */     this.isChunkDataPacket = true;
/*    */   }
/*    */   public int zPosition; public int type; public int metadata;
/*    */   public Packet53BlockChange(int i, int j, int k, World world) {
/* 16 */     this.isChunkDataPacket = true;
/* 17 */     this.xPosition = i;
/* 18 */     this.yPosition = j;
/* 19 */     this.zPosition = k;
/* 20 */     this.type = world.getBlockId(i, j, k);
/* 21 */     this.metadata = world.getBlockMetadata(i, j, k);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 26 */     this.xPosition = dataInput.readInt();
/* 27 */     this.yPosition = dataInput.readUnsignedByte();
/* 28 */     this.zPosition = dataInput.readInt();
/* 29 */     this.type = dataInput.readShort();
/* 30 */     this.metadata = dataInput.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 35 */     dataOutput.writeInt(this.xPosition);
/* 36 */     dataOutput.write(this.yPosition);
/* 37 */     dataOutput.writeInt(this.zPosition);
/* 38 */     dataOutput.writeShort(this.type);
/* 39 */     dataOutput.write(this.metadata);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 44 */     netHandler.handleBlockChange(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 49 */     return 11;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet53BlockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */