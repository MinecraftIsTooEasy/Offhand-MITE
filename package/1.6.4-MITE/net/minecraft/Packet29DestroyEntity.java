/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet29DestroyEntity
/*    */   extends Packet
/*    */ {
/*    */   public int[] entityId;
/*    */   
/*    */   public Packet29DestroyEntity() {}
/*    */   
/*    */   public Packet29DestroyEntity(int... is) {
/* 16 */     this.entityId = is;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 21 */     this.entityId = new int[dataInput.readByte()];
/*    */     
/* 23 */     for (byte b = 0; b < this.entityId.length; b++) {
/* 24 */       this.entityId[b] = dataInput.readInt();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 30 */     dataOutput.writeByte(this.entityId.length);
/*    */     
/* 32 */     for (byte b = 0; b < this.entityId.length; b++) {
/* 33 */       dataOutput.writeInt(this.entityId[b]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 39 */     netHandler.handleDestroyEntity(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 44 */     return 1 + this.entityId.length * 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet29DestroyEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */