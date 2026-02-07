/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet19EntityAction
/*    */   extends Packet
/*    */ {
/*    */   public int entityId;
/*    */   public int action;
/*    */   public int auxData;
/*    */   
/*    */   public Packet19EntityAction() {}
/*    */   
/*    */   public Packet19EntityAction(Entity entity, int i) {
/* 24 */     this(entity, i, 0);
/*    */   }
/*    */   
/*    */   public Packet19EntityAction(Entity entity, int i, int j) {
/* 28 */     this.entityId = entity.entityId;
/* 29 */     this.action = i;
/* 30 */     this.auxData = j;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 35 */     this.entityId = dataInput.readInt();
/* 36 */     this.action = dataInput.readByte();
/* 37 */     this.auxData = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 42 */     dataOutput.writeInt(this.entityId);
/* 43 */     dataOutput.writeByte(this.action);
/* 44 */     dataOutput.writeInt(this.auxData);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 49 */     netHandler.handleEntityAction(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 54 */     return 9;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet19EntityAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */