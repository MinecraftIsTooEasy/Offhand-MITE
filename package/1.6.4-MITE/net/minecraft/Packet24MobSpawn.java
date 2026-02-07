/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
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
/*     */ public class Packet24MobSpawn
/*     */   extends Packet
/*     */ {
/*     */   public int entity_id;
/*     */   public int type;
/*     */   public int scaled_pos_x;
/*     */   public int scaled_pos_y;
/*     */   public int scaled_pos_z;
/*     */   public int scaled_motion_x;
/*     */   public int scaled_motion_y;
/*     */   public int scaled_motion_z;
/*     */   public byte scaled_yaw;
/*     */   public byte scaled_pitch;
/*     */   public byte scaled_head_yaw;
/*     */   private DataWatcher metaData;
/*     */   private List metadata;
/*     */   public boolean is_decoy;
/*     */   
/*     */   public Packet24MobSpawn() {}
/*     */   
/*     */   public Packet24MobSpawn(EntityLiving par1EntityLivingBase) {
/* 156 */     this.entity_id = par1EntityLivingBase.entityId;
/* 157 */     this.type = (short)EntityList.getEntityID(par1EntityLivingBase);
/*     */     
/* 159 */     this.scaled_pos_x = SpatialScaler.getScaledPosX(par1EntityLivingBase);
/* 160 */     this.scaled_pos_y = SpatialScaler.getScaledPosY(par1EntityLivingBase);
/* 161 */     this.scaled_pos_z = SpatialScaler.getScaledPosZ(par1EntityLivingBase);
/*     */     
/* 163 */     this.scaled_motion_x = SpatialScaler.getScaledMotion(par1EntityLivingBase.motionX);
/* 164 */     this.scaled_motion_y = SpatialScaler.getScaledMotion(par1EntityLivingBase.motionY);
/* 165 */     this.scaled_motion_z = SpatialScaler.getScaledMotion(par1EntityLivingBase.motionZ);
/*     */     
/* 167 */     this.scaled_yaw = (byte)SpatialScaler.getScaledRotation(par1EntityLivingBase.rotationYaw);
/* 168 */     this.scaled_pitch = (byte)SpatialScaler.getScaledRotation(par1EntityLivingBase.rotationPitch);
/* 169 */     this.scaled_head_yaw = (byte)SpatialScaler.getScaledRotation(par1EntityLivingBase.getRotationYawHead());
/*     */     
/* 171 */     this.metaData = par1EntityLivingBase.getDataWatcher();
/*     */     
/* 173 */     this.is_decoy = par1EntityLivingBase.isDecoy();
/*     */     
/* 175 */     par1EntityLivingBase.onSendToClient(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 183 */     this.entity_id = par1DataInput.readInt();
/* 184 */     this.type = par1DataInput.readShort() & 0xFFFF;
/* 185 */     this.scaled_pos_x = par1DataInput.readInt();
/* 186 */     this.scaled_pos_y = par1DataInput.readInt();
/* 187 */     this.scaled_pos_z = par1DataInput.readInt();
/* 188 */     this.scaled_yaw = par1DataInput.readByte();
/* 189 */     this.scaled_pitch = par1DataInput.readByte();
/* 190 */     this.scaled_head_yaw = par1DataInput.readByte();
/* 191 */     this.scaled_motion_x = par1DataInput.readShort();
/* 192 */     this.scaled_motion_y = par1DataInput.readShort();
/* 193 */     this.scaled_motion_z = par1DataInput.readShort();
/* 194 */     this.metadata = DataWatcher.readWatchableObjects(par1DataInput);
/* 195 */     this.is_decoy = par1DataInput.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 201 */     par1DataOutput.writeInt(this.entity_id);
/* 202 */     par1DataOutput.writeShort(this.type & 0xFFFF);
/* 203 */     par1DataOutput.writeInt(this.scaled_pos_x);
/* 204 */     par1DataOutput.writeInt(this.scaled_pos_y);
/* 205 */     par1DataOutput.writeInt(this.scaled_pos_z);
/* 206 */     par1DataOutput.writeByte(this.scaled_yaw);
/* 207 */     par1DataOutput.writeByte(this.scaled_pitch);
/* 208 */     par1DataOutput.writeByte(this.scaled_head_yaw);
/* 209 */     par1DataOutput.writeShort(this.scaled_motion_x);
/* 210 */     par1DataOutput.writeShort(this.scaled_motion_y);
/* 211 */     par1DataOutput.writeShort(this.scaled_motion_z);
/* 212 */     this.metaData.writeWatchableObjects(par1DataOutput);
/* 213 */     par1DataOutput.writeBoolean(this.is_decoy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 219 */     par1NetHandler.handleMobSpawn(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getPacketSizeOfWatchableObjects() {
/* 224 */     if (this.metaData != null)
/* 225 */       return this.metaData.getPacketSizeOfWatchableObjects(); 
/* 226 */     if (this.metadata != null) {
/* 227 */       return DataWatcher.getPacketSizeOfObjectsInListToStream(this.metadata);
/*     */     }
/* 229 */     Minecraft.setErrorMessage("getPacketSizeOfWatchableObjects: both metadata and metadataWatchableObjects are null " + this);
/*     */     
/* 231 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 237 */     return 28 + getPacketSizeOfWatchableObjects();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getMetadata() {
/* 243 */     if (this.metadata == null) {
/* 244 */       this.metadata = this.metaData.getAllWatched();
/*     */     }
/* 246 */     return this.metadata;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet24MobSpawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */