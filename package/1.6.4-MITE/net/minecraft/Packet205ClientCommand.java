/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ public class Packet205ClientCommand
/*    */   extends Packet
/*    */ {
/*    */   public int forceRespawn;
/*    */   
/*    */   public Packet205ClientCommand() {}
/*    */   
/*    */   public Packet205ClientCommand(int i) {
/* 15 */     this.forceRespawn = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 20 */     this.forceRespawn = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 25 */     dataOutput.writeByte(this.forceRespawn & 0xFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 30 */     netHandler.handleClientCommand(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 35 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet205ClientCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */