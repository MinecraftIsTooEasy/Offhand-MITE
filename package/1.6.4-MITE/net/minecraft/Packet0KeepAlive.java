/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet0KeepAlive extends Packet {
/*    */   public int randomId;
/*    */   
/*    */   public Packet0KeepAlive() {}
/*    */   
/*    */   public Packet0KeepAlive(int i) {
/* 12 */     this.randomId = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 17 */     netHandler.handleKeepAlive(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 22 */     this.randomId = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 27 */     dataOutput.writeInt(this.randomId);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 32 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet packet) {
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canProcessAsync() {
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet0KeepAlive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */