/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet6SpawnPosition
/*    */   extends Packet {
/*    */   public int xPosition;
/*    */   public int yPosition;
/*    */   
/*    */   public Packet6SpawnPosition(int i, int j, int k) {
/* 12 */     this.xPosition = i;
/* 13 */     this.yPosition = j;
/* 14 */     this.zPosition = k;
/*    */   }
/*    */   public int zPosition;
/*    */   public Packet6SpawnPosition() {}
/*    */   public void readPacketData(DataInput dataInput) {
/* 19 */     this.xPosition = dataInput.readInt();
/* 20 */     this.yPosition = dataInput.readInt();
/* 21 */     this.zPosition = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 26 */     dataOutput.writeInt(this.xPosition);
/* 27 */     dataOutput.writeInt(this.yPosition);
/* 28 */     dataOutput.writeInt(this.zPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 33 */     netHandler.handleSpawnPosition(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 38 */     return 12;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet packet) {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canProcessAsync() {
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet6SpawnPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */