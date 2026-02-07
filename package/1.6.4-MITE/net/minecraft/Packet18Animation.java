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
/*    */ public class Packet18Animation
/*    */   extends Packet
/*    */ {
/*    */   public int entityId;
/*    */   public int animate;
/*    */   public int other_entity_id;
/*    */   
/*    */   public Packet18Animation() {}
/*    */   
/*    */   public Packet18Animation(Entity par1Entity, int par2) {
/* 21 */     this(par1Entity, par2, null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet18Animation(Entity par1Entity, int par2, Entity other_entity) {
/* 27 */     this.entityId = par1Entity.entityId;
/* 28 */     this.animate = par2;
/*    */     
/* 30 */     this.other_entity_id = (other_entity == null) ? -1 : other_entity.entityId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 38 */     this.entityId = par1DataInput.readInt();
/* 39 */     this.animate = par1DataInput.readByte();
/*    */     
/* 41 */     if (this.animate == 3) {
/* 42 */       this.other_entity_id = par1DataInput.readInt();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 50 */     par1DataOutput.writeInt(this.entityId);
/* 51 */     par1DataOutput.writeByte(this.animate);
/*    */     
/* 53 */     if (this.animate == 3) {
/* 54 */       par1DataOutput.writeInt(this.other_entity_id);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 62 */     par1NetHandler.handleAnimation(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 72 */     return (this.animate == 3) ? 9 : 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet18Animation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */