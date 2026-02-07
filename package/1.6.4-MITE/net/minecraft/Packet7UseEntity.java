/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet7UseEntity
/*    */   extends Packet {
/*    */   public int playerEntityId;
/*    */   public int targetEntity;
/*    */   public int isLeftClick;
/*    */   
/*    */   public Packet7UseEntity() {}
/*    */   
/*    */   public Packet7UseEntity(int i, int j, int k) {
/* 15 */     this.playerEntityId = i;
/* 16 */     this.targetEntity = j;
/* 17 */     this.isLeftClick = k;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 22 */     this.playerEntityId = dataInput.readInt();
/* 23 */     this.targetEntity = dataInput.readInt();
/* 24 */     this.isLeftClick = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 29 */     dataOutput.writeInt(this.playerEntityId);
/* 30 */     dataOutput.writeInt(this.targetEntity);
/* 31 */     dataOutput.writeByte(this.isLeftClick);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 36 */     netHandler.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 41 */     return 9;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet7UseEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */