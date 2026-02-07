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
/*     */ public class Packet28EntityVelocity
/*     */   extends Packet
/*     */ {
/*     */   public int entityId;
/*     */   public int motionX;
/*     */   public int motionY;
/*     */   public int motionZ;
/*     */   
/*     */   public Packet28EntityVelocity() {}
/*     */   
/*     */   public Packet28EntityVelocity(EntityTrackerEntry entity_tracker_entry) {
/*  63 */     Entity entity = entity_tracker_entry.myEntity;
/*     */     
/*  65 */     this.entityId = entity.entityId;
/*     */     
/*  67 */     double motion_x = entity.motionX;
/*  68 */     double motion_y = entity.motionY;
/*  69 */     double motion_z = entity.motionZ;
/*     */     
/*  71 */     double var8 = 3.9D;
/*     */     
/*  73 */     boolean overspeed = false;
/*     */     
/*  75 */     if (motion_x < -var8) {
/*     */       
/*  77 */       motion_x = -var8;
/*  78 */       overspeed = true;
/*     */     }
/*  80 */     else if (motion_x > var8) {
/*     */       
/*  82 */       motion_x = var8;
/*  83 */       overspeed = true;
/*     */     } 
/*     */     
/*  86 */     if (motion_y < -var8) {
/*     */       
/*  88 */       motion_y = -var8;
/*  89 */       overspeed = true;
/*     */     }
/*  91 */     else if (motion_y > var8) {
/*     */       
/*  93 */       motion_y = var8;
/*  94 */       overspeed = true;
/*     */     } 
/*     */     
/*  97 */     if (motion_z < -var8) {
/*     */       
/*  99 */       motion_z = -var8;
/* 100 */       overspeed = true;
/*     */     }
/* 102 */     else if (motion_z > var8) {
/*     */       
/* 104 */       motion_z = var8;
/* 105 */       overspeed = true;
/*     */     } 
/*     */     
/* 108 */     this.motionX = (int)(motion_x * 8000.0D);
/* 109 */     this.motionY = (int)(motion_y * 8000.0D);
/* 110 */     this.motionZ = (int)(motion_z * 8000.0D);
/*     */     
/* 112 */     if (!overspeed) {
/* 113 */       applyToEntity(entity);
/*     */     }
/* 115 */     entity_tracker_entry.motionX = entity.motionX;
/* 116 */     entity_tracker_entry.motionY = entity.motionY;
/* 117 */     entity_tracker_entry.motionZ = entity.motionZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyToEntity(Entity entity) {
/* 122 */     entity.setVelocity(this.motionX / 8000.0D, this.motionY / 8000.0D, this.motionZ / 8000.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 130 */     this.entityId = par1DataInput.readInt();
/* 131 */     this.motionX = par1DataInput.readShort();
/* 132 */     this.motionY = par1DataInput.readShort();
/* 133 */     this.motionZ = par1DataInput.readShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 141 */     par1DataOutput.writeInt(this.entityId);
/* 142 */     par1DataOutput.writeShort(this.motionX);
/* 143 */     par1DataOutput.writeShort(this.motionY);
/* 144 */     par1DataOutput.writeShort(this.motionZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 152 */     par1NetHandler.handleEntityVelocity(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 160 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRealPacket() {
/* 169 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 178 */     Packet28EntityVelocity var2 = (Packet28EntityVelocity)par1Packet;
/* 179 */     return (var2.entityId == this.entityId);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet28EntityVelocity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */