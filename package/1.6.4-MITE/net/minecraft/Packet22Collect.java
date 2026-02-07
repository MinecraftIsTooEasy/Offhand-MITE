/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet22Collect
/*    */   extends Packet
/*    */ {
/*    */   public int collectedEntityId;
/*    */   
/*    */   public Packet22Collect(int i, int j) {
/* 12 */     this.collectedEntityId = i;
/* 13 */     this.collectorEntityId = j;
/*    */   }
/*    */   public int collectorEntityId;
/*    */   public Packet22Collect() {}
/*    */   public void readPacketData(DataInput dataInput) {
/* 18 */     this.collectedEntityId = dataInput.readInt();
/* 19 */     this.collectorEntityId = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 24 */     dataOutput.writeInt(this.collectedEntityId);
/* 25 */     dataOutput.writeInt(this.collectorEntityId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 30 */     netHandler.handleCollect(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 35 */     return 8;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet22Collect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */