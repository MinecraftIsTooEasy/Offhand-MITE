/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Packet30Entity
/*     */   extends Packet
/*     */ {
/*     */   public int entityId;
/*     */   public byte xPosition;
/*     */   public byte yPosition;
/*     */   public byte zPosition;
/*     */   public byte yaw;
/*     */   public byte pitch;
/*     */   public boolean rotating;
/*     */   public boolean sync_last_tick_pos;
/*     */   
/*     */   public Packet30Entity() {}
/*     */   
/*     */   public Packet30Entity(Entity entity) {
/*  41 */     this.entityId = entity.entityId;
/*  42 */     this.sync_last_tick_pos = entity.sync_last_tick_pos_on_next_update;
/*  43 */     entity.sync_last_tick_pos_on_next_update = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  51 */     this.entityId = par1DataInput.readInt();
/*  52 */     this.sync_last_tick_pos = par1DataInput.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  60 */     par1DataOutput.writeInt(this.entityId);
/*  61 */     par1DataOutput.writeBoolean(this.sync_last_tick_pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  69 */     par1NetHandler.handleEntity(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  78 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  83 */     return "Entity_" + super.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 100 */     Packet30Entity var2 = (Packet30Entity)par1Packet;
/* 101 */     return (var2.entityId == this.entityId);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet30Entity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */