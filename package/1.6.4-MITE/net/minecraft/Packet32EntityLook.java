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
/*     */ public class Packet32EntityLook
/*     */   extends Packet
/*     */ {
/*     */   public int entity_id;
/*     */   public byte scaled_yaw;
/*     */   public byte scaled_pitch;
/*     */   
/*     */   public Packet32EntityLook() {}
/*     */   
/*     */   public Packet32EntityLook(Entity entity) {
/*  54 */     this.entity_id = entity.entityId;
/*  55 */     this.scaled_yaw = (byte)SpatialScaler.getScaledRotation(entity.rotationYaw);
/*  56 */     this.scaled_pitch = (byte)SpatialScaler.getScaledRotation(entity.rotationPitch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyToEntity(Entity entity) {
/*  62 */     entity.setPositionAndRotation2(entity.posX, entity.posY, entity.posZ, SpatialScaler.getRotation(this.scaled_yaw), SpatialScaler.getRotation(this.scaled_pitch), 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  72 */     this.entity_id = par1DataInput.readInt();
/*  73 */     this.scaled_yaw = par1DataInput.readByte();
/*  74 */     this.scaled_pitch = par1DataInput.readByte();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  79 */     par1DataOutput.writeInt(this.entity_id);
/*  80 */     par1DataOutput.writeByte(this.scaled_yaw);
/*  81 */     par1DataOutput.writeByte(this.scaled_pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  86 */     par1NetHandler.handleEntityLook(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/*  91 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet packet) {
/* 101 */     Packet32EntityLook packet32 = (Packet32EntityLook)packet;
/* 102 */     return (packet32.entity_id == this.entity_id);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet32EntityLook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */