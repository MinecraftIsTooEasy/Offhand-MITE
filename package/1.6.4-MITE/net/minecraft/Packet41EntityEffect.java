/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet41EntityEffect
/*    */   extends Packet
/*    */ {
/*    */   public int entityId;
/*    */   public byte effectId;
/*    */   public byte effectAmplifier;
/*    */   public short duration;
/*    */   
/*    */   public Packet41EntityEffect() {}
/*    */   
/*    */   public Packet41EntityEffect(int i, PotionEffect potionEffect) {
/* 20 */     this.entityId = i;
/* 21 */     this.effectId = (byte)(potionEffect.getPotionID() & 0xFF);
/* 22 */     this.effectAmplifier = (byte)(potionEffect.getAmplifier() & 0xFF);
/* 23 */     if (potionEffect.getDuration() > 32767) {
/* 24 */       this.duration = Short.MAX_VALUE;
/*    */     } else {
/*    */       
/* 27 */       this.duration = (short)potionEffect.getDuration();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 33 */     this.entityId = dataInput.readInt();
/* 34 */     this.effectId = dataInput.readByte();
/* 35 */     this.effectAmplifier = dataInput.readByte();
/* 36 */     this.duration = dataInput.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 41 */     dataOutput.writeInt(this.entityId);
/* 42 */     dataOutput.writeByte(this.effectId);
/* 43 */     dataOutput.writeByte(this.effectAmplifier);
/* 44 */     dataOutput.writeShort(this.duration);
/*    */   }
/*    */   
/*    */   public boolean isDurationMax() {
/* 48 */     return (this.duration == Short.MAX_VALUE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 53 */     netHandler.handleEntityEffect(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 58 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet packet) {
/* 68 */     Packet41EntityEffect packet41EntityEffect = (Packet41EntityEffect)packet;
/* 69 */     return (packet41EntityEffect.entityId == this.entityId && packet41EntityEffect.effectId == this.effectId);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet41EntityEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */