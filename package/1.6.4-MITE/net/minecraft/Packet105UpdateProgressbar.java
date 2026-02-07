/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet105UpdateProgressbar
/*    */   extends Packet
/*    */ {
/*    */   public int windowId;
/*    */   public int progressBar;
/*    */   public int progressBarValue;
/*    */   
/*    */   public Packet105UpdateProgressbar() {}
/*    */   
/*    */   public Packet105UpdateProgressbar(int i, int j, int k) {
/* 16 */     this.windowId = i;
/* 17 */     this.progressBar = j;
/* 18 */     this.progressBarValue = k;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 23 */     netHandler.handleUpdateProgressbar(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 28 */     this.windowId = dataInput.readByte();
/* 29 */     this.progressBar = dataInput.readShort();
/* 30 */     this.progressBarValue = dataInput.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 35 */     dataOutput.writeByte(this.windowId);
/* 36 */     dataOutput.writeShort(this.progressBar);
/* 37 */     dataOutput.writeShort(this.progressBarValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 42 */     return 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet105UpdateProgressbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */