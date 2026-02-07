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
/*     */ public class Packet26EntityExpOrb
/*     */   extends Packet
/*     */ {
/*     */   public int entity_id;
/*     */   public int scaled_pos_x;
/*     */   public int scaled_pos_y;
/*     */   public int scaled_pos_z;
/*     */   public int scaled_motion_x;
/*     */   public int scaled_motion_y;
/*     */   public int scaled_motion_z;
/*     */   public int xp_value;
/*     */   public String player_this_belongs_to;
/*     */   
/*     */   public Packet26EntityExpOrb() {}
/*     */   
/*     */   public Packet26EntityExpOrb(EntityXPOrb par1EntityXPOrb) {
/*  75 */     this.entity_id = par1EntityXPOrb.entityId;
/*  76 */     this.scaled_pos_x = SpatialScaler.getScaledPosX(par1EntityXPOrb);
/*  77 */     this.scaled_pos_y = SpatialScaler.getScaledPosY(par1EntityXPOrb);
/*  78 */     this.scaled_pos_z = SpatialScaler.getScaledPosZ(par1EntityXPOrb);
/*  79 */     this.scaled_motion_x = SpatialScaler.getScaledMotion(par1EntityXPOrb.motionX);
/*  80 */     this.scaled_motion_y = SpatialScaler.getScaledMotion(par1EntityXPOrb.motionY);
/*  81 */     this.scaled_motion_z = SpatialScaler.getScaledMotion(par1EntityXPOrb.motionZ);
/*  82 */     this.xp_value = par1EntityXPOrb.getXpValue();
/*  83 */     this.player_this_belongs_to = (par1EntityXPOrb.player_this_belongs_to == null) ? "" : par1EntityXPOrb.player_this_belongs_to;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*  88 */     this.entity_id = par1DataInput.readInt();
/*  89 */     this.scaled_pos_x = par1DataInput.readInt();
/*  90 */     this.scaled_pos_y = par1DataInput.readInt();
/*  91 */     this.scaled_pos_z = par1DataInput.readInt();
/*  92 */     this.scaled_motion_x = par1DataInput.readShort();
/*  93 */     this.scaled_motion_y = par1DataInput.readShort();
/*  94 */     this.scaled_motion_z = par1DataInput.readShort();
/*  95 */     this.xp_value = par1DataInput.readShort();
/*     */     
/*  97 */     this.player_this_belongs_to = readString(par1DataInput, 32);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 102 */     par1DataOutput.writeInt(this.entity_id);
/* 103 */     par1DataOutput.writeInt(this.scaled_pos_x);
/* 104 */     par1DataOutput.writeInt(this.scaled_pos_y);
/* 105 */     par1DataOutput.writeInt(this.scaled_pos_z);
/* 106 */     par1DataOutput.writeShort(this.scaled_motion_x);
/* 107 */     par1DataOutput.writeShort(this.scaled_motion_y);
/* 108 */     par1DataOutput.writeShort(this.scaled_motion_z);
/* 109 */     par1DataOutput.writeShort(this.xp_value);
/* 110 */     writeString(this.player_this_belongs_to, par1DataOutput);
/*     */   }
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 115 */     par1NetHandler.handleEntityExpOrb(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 120 */     return 24 + this.player_this_belongs_to.length();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet26EntityExpOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */