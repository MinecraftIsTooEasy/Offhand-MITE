/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet35EntityHeadRotation extends Packet {
/*    */   public int entityId;
/*    */   public byte headRotationYaw;
/*    */   
/*    */   public Packet35EntityHeadRotation() {}
/*    */   
/*    */   public Packet35EntityHeadRotation(int i, byte b) {
/* 13 */     this.entityId = i;
/* 14 */     this.headRotationYaw = b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 19 */     this.entityId = dataInput.readInt();
/* 20 */     this.headRotationYaw = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 25 */     dataOutput.writeInt(this.entityId);
/* 26 */     dataOutput.writeByte(this.headRotationYaw);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 31 */     netHandler.handleEntityHeadRotation(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 36 */     return 5;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet packet) {
/* 51 */     Packet35EntityHeadRotation packet35EntityHeadRotation = (Packet35EntityHeadRotation)packet;
/* 52 */     return (packet35EntityHeadRotation.entityId == this.entityId);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canProcessAsync() {
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet35EntityHeadRotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */