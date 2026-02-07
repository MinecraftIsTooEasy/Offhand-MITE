/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet38EntityStatus
/*    */   extends Packet
/*    */ {
/*    */   public int entityId;
/*    */   public EnumEntityState entity_state;
/*    */   
/*    */   public Packet38EntityStatus() {}
/*    */   
/*    */   public Packet38EntityStatus(int par1, EnumEntityState par2) {
/* 25 */     this.entityId = par1;
/*    */     
/* 27 */     this.entity_state = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 35 */     this.entityId = par1DataInput.readInt();
/*    */     
/* 37 */     this.entity_state = EnumEntityState.values()[par1DataInput.readByte()];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 45 */     par1DataOutput.writeInt(this.entityId);
/*    */     
/* 47 */     par1DataOutput.writeByte(this.entity_state.ordinal());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 55 */     par1NetHandler.handleEntityStatus(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 63 */     return 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet38EntityStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */