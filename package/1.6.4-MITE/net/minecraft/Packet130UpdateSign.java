/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet130UpdateSign
/*    */   extends Packet {
/*    */   public int xPosition;
/*    */   public int yPosition;
/*    */   
/*    */   public Packet130UpdateSign() {
/* 12 */     this.isChunkDataPacket = true;
/*    */   }
/*    */   public int zPosition; public String[] signLines;
/*    */   public Packet130UpdateSign(int i, int j, int k, String[] strings) {
/* 16 */     this.isChunkDataPacket = true;
/* 17 */     this.xPosition = i;
/* 18 */     this.yPosition = j;
/* 19 */     this.zPosition = k;
/* 20 */     this.signLines = new String[] { strings[0], strings[1], strings[2], strings[3] };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 27 */     this.xPosition = dataInput.readInt();
/* 28 */     this.yPosition = dataInput.readShort();
/* 29 */     this.zPosition = dataInput.readInt();
/* 30 */     this.signLines = new String[4];
/* 31 */     for (byte b = 0; b < 4; b++) {
/* 32 */       this.signLines[b] = readString(dataInput, 15);
/*    */     }
/*    */   }
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 37 */     dataOutput.writeInt(this.xPosition);
/* 38 */     dataOutput.writeShort(this.yPosition);
/* 39 */     dataOutput.writeInt(this.zPosition);
/* 40 */     for (byte b = 0; b < 4; b++) {
/* 41 */       writeString(this.signLines[b], dataOutput);
/*    */     }
/*    */   }
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 46 */     netHandler.handleUpdateSign(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 51 */     int i = 0;
/* 52 */     for (byte b = 0; b < 4; b++)
/* 53 */       i += this.signLines[b].length(); 
/* 54 */     return i;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet130UpdateSign.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */