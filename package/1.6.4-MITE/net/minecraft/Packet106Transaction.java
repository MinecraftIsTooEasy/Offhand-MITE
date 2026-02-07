/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet106Transaction
/*    */   extends Packet
/*    */ {
/*    */   public int windowId;
/*    */   public short shortWindowId;
/*    */   public boolean accepted;
/*    */   
/*    */   public Packet106Transaction() {}
/*    */   
/*    */   public Packet106Transaction(int i, short s, boolean bl) {
/* 16 */     this.windowId = i;
/* 17 */     this.shortWindowId = s;
/* 18 */     this.accepted = bl;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 23 */     netHandler.handleTransaction(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 28 */     this.windowId = dataInput.readByte();
/* 29 */     this.shortWindowId = dataInput.readShort();
/* 30 */     this.accepted = (dataInput.readByte() != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 35 */     dataOutput.writeByte(this.windowId);
/* 36 */     dataOutput.writeShort(this.shortWindowId);
/* 37 */     dataOutput.writeByte(this.accepted ? 1 : 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 42 */     return 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet106Transaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */