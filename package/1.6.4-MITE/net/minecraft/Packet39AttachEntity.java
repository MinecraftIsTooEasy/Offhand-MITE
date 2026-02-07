/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet39AttachEntity
/*    */   extends Packet
/*    */ {
/*    */   public int attachState;
/*    */   public int ridingEntityId;
/*    */   public int vehicleEntityId;
/*    */   
/*    */   public Packet39AttachEntity() {}
/*    */   
/*    */   public Packet39AttachEntity(int i, Entity entity, Entity entity2) {
/* 19 */     this.attachState = i;
/* 20 */     this.ridingEntityId = entity.entityId;
/* 21 */     this.vehicleEntityId = (entity2 != null) ? entity2.entityId : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 26 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 31 */     this.ridingEntityId = dataInput.readInt();
/* 32 */     this.vehicleEntityId = dataInput.readInt();
/* 33 */     this.attachState = dataInput.readUnsignedByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 38 */     dataOutput.writeInt(this.ridingEntityId);
/* 39 */     dataOutput.writeInt(this.vehicleEntityId);
/* 40 */     dataOutput.writeByte(this.attachState);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 45 */     netHandler.handleAttachEntity(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet packet) {
/* 55 */     Packet39AttachEntity packet39AttachEntity = (Packet39AttachEntity)packet;
/* 56 */     return (packet39AttachEntity.ridingEntityId == this.ridingEntityId);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet39AttachEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */