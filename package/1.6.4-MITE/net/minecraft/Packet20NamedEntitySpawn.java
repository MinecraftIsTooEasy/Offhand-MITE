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
/*     */ public class Packet20NamedEntitySpawn
/*     */   extends Packet
/*     */ {
/*     */   public int entityId;
/*     */   public String name;
/*     */   public int scaled_pos_x;
/*     */   public int scaled_pos_y;
/*     */   public int scaled_pos_z;
/*     */   public byte scaled_yaw;
/*     */   public byte scaled_pitch;
/*     */   public int currentItem;
/*     */   private DataWatcher metadata;
/*     */   private List metadataWatchableObjects;
/*     */   
/*     */   public Packet20NamedEntitySpawn() {}
/*     */   
/*     */   public Packet20NamedEntitySpawn(EntityPlayer par1EntityPlayer) {
/*  37 */     this.entityId = par1EntityPlayer.entityId;
/*  38 */     this.name = par1EntityPlayer.getCommandSenderName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.scaled_pos_x = SpatialScaler.getScaledPosX(par1EntityPlayer);
/*  45 */     this.scaled_pos_y = SpatialScaler.getScaledPosY(par1EntityPlayer);
/*  46 */     this.scaled_pos_z = SpatialScaler.getScaledPosZ(par1EntityPlayer);
/*  47 */     this.scaled_yaw = (byte)SpatialScaler.getScaledRotation(par1EntityPlayer.rotationYaw);
/*  48 */     this.scaled_pitch = (byte)SpatialScaler.getScaledRotation(par1EntityPlayer.rotationPitch);
/*  49 */     ItemStack var2 = par1EntityPlayer.inventory.getCurrentItemStack();
/*  50 */     this.currentItem = (var2 == null) ? 0 : var2.itemID;
/*  51 */     this.metadata = par1EntityPlayer.getDataWatcher();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  59 */     this.entityId = par1DataInput.readInt();
/*  60 */     this.name = readString(par1DataInput, 16);
/*  61 */     this.scaled_pos_x = par1DataInput.readInt();
/*  62 */     this.scaled_pos_y = par1DataInput.readInt();
/*  63 */     this.scaled_pos_z = par1DataInput.readInt();
/*  64 */     this.scaled_yaw = par1DataInput.readByte();
/*  65 */     this.scaled_pitch = par1DataInput.readByte();
/*  66 */     this.currentItem = par1DataInput.readShort();
/*  67 */     this.metadataWatchableObjects = DataWatcher.readWatchableObjects(par1DataInput);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/*  75 */     par1DataOutput.writeInt(this.entityId);
/*  76 */     writeString(this.name, par1DataOutput);
/*  77 */     par1DataOutput.writeInt(this.scaled_pos_x);
/*  78 */     par1DataOutput.writeInt(this.scaled_pos_y);
/*  79 */     par1DataOutput.writeInt(this.scaled_pos_z);
/*  80 */     par1DataOutput.writeByte(this.scaled_yaw);
/*  81 */     par1DataOutput.writeByte(this.scaled_pitch);
/*  82 */     par1DataOutput.writeShort(this.currentItem);
/*  83 */     this.metadata.writeWatchableObjects(par1DataOutput);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/*  91 */     par1NetHandler.handleNamedEntitySpawn(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getPacketSizeOfWatchableObjects() {
/*  96 */     if (this.metadata != null)
/*  97 */       return this.metadata.getPacketSizeOfWatchableObjects(); 
/*  98 */     if (this.metadataWatchableObjects != null) {
/*  99 */       return DataWatcher.getPacketSizeOfObjectsInListToStream(this.metadataWatchableObjects);
/*     */     }
/* 101 */     Minecraft.setErrorMessage("getPacketSizeOfWatchableObjects: both metadata and metadataWatchableObjects are null " + this);
/*     */     
/* 103 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 112 */     return 4 + Packet.getPacketSizeOfString(this.name) + 16 + getPacketSizeOfWatchableObjects();
/*     */   }
/*     */ 
/*     */   
/*     */   public List getWatchedMetadata() {
/* 117 */     if (this.metadataWatchableObjects == null)
/*     */     {
/* 119 */       this.metadataWatchableObjects = this.metadata.getAllWatched();
/*     */     }
/*     */     
/* 122 */     return this.metadataWatchableObjects;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet20NamedEntitySpawn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */