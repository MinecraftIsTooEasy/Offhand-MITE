/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet42RemoveEntityEffect
/*    */   extends Packet
/*    */ {
/*    */   public int entityId;
/*    */   public byte effectId;
/*    */   
/*    */   public Packet42RemoveEntityEffect() {}
/*    */   
/*    */   public Packet42RemoveEntityEffect(int i, PotionEffect potionEffect) {
/* 18 */     this.entityId = i;
/* 19 */     this.effectId = (byte)(potionEffect.getPotionID() & 0xFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 24 */     this.entityId = dataInput.readInt();
/* 25 */     this.effectId = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 30 */     dataOutput.writeInt(this.entityId);
/* 31 */     dataOutput.writeByte(this.effectId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 36 */     netHandler.handleRemoveEntityEffect(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 41 */     return 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet42RemoveEntityEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */