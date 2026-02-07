/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet16BlockItemSwitch extends Packet {
/*    */   public int id;
/*    */   
/*    */   public Packet16BlockItemSwitch() {}
/*    */   
/*    */   public Packet16BlockItemSwitch(int i) {
/* 12 */     this.id = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 17 */     this.id = dataInput.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 22 */     dataOutput.writeShort(this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 27 */     netHandler.handleBlockItemSwitch(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 32 */     return 2;
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
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet16BlockItemSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */