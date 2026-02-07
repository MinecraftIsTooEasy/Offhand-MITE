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
/*     */ 
/*     */ 
/*     */ public class Packet34EntityTeleport
/*     */   extends Packet
/*     */ {
/*     */   public int entity_id;
/*     */   protected int scaled_pos_x;
/*     */   protected int scaled_pos_y;
/*     */   protected int scaled_pos_z;
/*     */   protected byte scaled_yaw;
/*     */   protected byte scaled_pitch;
/*     */   protected boolean quantum_leap;
/*     */   
/*     */   public Packet34EntityTeleport() {}
/*     */   
/*     */   public Packet34EntityTeleport(Entity entity) {
/*  98 */     this.entity_id = entity.entityId;
/*  99 */     this.scaled_pos_x = SpatialScaler.getScaledPosX(entity);
/* 100 */     this.scaled_pos_y = SpatialScaler.getScaledPosY(entity);
/* 101 */     this.scaled_pos_z = SpatialScaler.getScaledPosZ(entity);
/* 102 */     this.scaled_yaw = (byte)SpatialScaler.getScaledRotation(entity.rotationYaw);
/* 103 */     this.scaled_pitch = (byte)SpatialScaler.getScaledRotation(entity.rotationPitch);
/*     */     
/* 105 */     this.quantum_leap = entity.sync_last_tick_pos_on_next_update;
/* 106 */     entity.sync_last_tick_pos_on_next_update = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyToEntity(Entity entity) {
/* 111 */     entity.setPositionAndRotation2(SpatialScaler.getPosX(this.scaled_pos_x), SpatialScaler.getPosY(this.scaled_pos_y), SpatialScaler.getPosZ(this.scaled_pos_z), SpatialScaler.getRotation(this.scaled_yaw), SpatialScaler.getRotation(this.scaled_pitch), 3);
/*     */     
/* 113 */     if (this.quantum_leap) {
/*     */       
/* 115 */       if (entity instanceof EntityLivingBase) {
/*     */         
/* 117 */         EntityLivingBase entity_living_base = (EntityLivingBase)entity;
/*     */         
/* 119 */         entity.posX = entity_living_base.newPosX;
/* 120 */         entity.posY = entity_living_base.newPosY;
/* 121 */         entity.posZ = entity_living_base.newPosZ;
/*     */       } 
/*     */       
/* 124 */       entity.lastTickPosX = entity.posX;
/* 125 */       entity.lastTickPosY = entity.posY;
/* 126 */       entity.lastTickPosZ = entity.posZ;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 132 */     this.entity_id = par1DataInput.readInt();
/* 133 */     this.scaled_pos_x = par1DataInput.readInt();
/* 134 */     this.scaled_pos_y = par1DataInput.readShort();
/* 135 */     this.scaled_pos_z = par1DataInput.readInt();
/* 136 */     this.scaled_yaw = par1DataInput.readByte();
/* 137 */     this.scaled_pitch = par1DataInput.readByte();
/*     */     
/* 139 */     this.quantum_leap = par1DataInput.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 144 */     par1DataOutput.writeInt(this.entity_id);
/* 145 */     par1DataOutput.writeInt(this.scaled_pos_x);
/* 146 */     par1DataOutput.writeShort(this.scaled_pos_y);
/* 147 */     par1DataOutput.writeInt(this.scaled_pos_z);
/* 148 */     par1DataOutput.write(this.scaled_yaw);
/* 149 */     par1DataOutput.write(this.scaled_pitch);
/*     */     
/* 151 */     par1DataOutput.writeBoolean(this.quantum_leap);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 156 */     par1NetHandler.handleEntityTeleport(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 161 */     return 17;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 171 */     Packet34EntityTeleport var2 = (Packet34EntityTeleport)par1Packet;
/* 172 */     return (var2.entity_id == this.entity_id);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet34EntityTeleport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */