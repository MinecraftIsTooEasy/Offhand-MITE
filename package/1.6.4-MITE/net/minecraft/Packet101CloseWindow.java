/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet101CloseWindow extends Packet {
/*    */   public int windowId;
/*    */   
/*    */   public Packet101CloseWindow() {}
/*    */   
/*    */   public Packet101CloseWindow(int i) {
/* 12 */     this.windowId = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 17 */     netHandler.handleCloseWindow(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 22 */     this.windowId = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 27 */     dataOutput.writeByte(this.windowId);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 32 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet101CloseWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */