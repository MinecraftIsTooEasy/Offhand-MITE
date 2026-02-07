/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet131MapData
/*    */   extends Packet {
/*    */   public short itemID;
/*    */   public short uniqueID;
/*    */   public byte[] itemData;
/*    */   
/*    */   public Packet131MapData() {
/* 13 */     this.isChunkDataPacket = true;
/*    */   }
/*    */   
/*    */   public Packet131MapData(short s, short t, byte[] bs) {
/* 17 */     this.isChunkDataPacket = true;
/* 18 */     this.itemID = s;
/* 19 */     this.uniqueID = t;
/* 20 */     this.itemData = bs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 25 */     this.itemID = dataInput.readShort();
/* 26 */     this.uniqueID = dataInput.readShort();
/* 27 */     this.itemData = new byte[dataInput.readUnsignedShort()];
/* 28 */     dataInput.readFully(this.itemData);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 33 */     dataOutput.writeShort(this.itemID);
/* 34 */     dataOutput.writeShort(this.uniqueID);
/* 35 */     dataOutput.writeShort(this.itemData.length);
/* 36 */     dataOutput.write(this.itemData);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 41 */     netHandler.handleMapData(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 46 */     return 4 + this.itemData.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet131MapData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */