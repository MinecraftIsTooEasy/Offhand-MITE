/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet86EntityTeleportWithMotion
/*    */   extends Packet34EntityTeleport
/*    */ {
/*    */   float motion_x;
/*    */   float motion_y;
/*    */   float motion_z;
/*    */   
/*    */   public Packet86EntityTeleportWithMotion() {}
/*    */   
/*    */   public Packet86EntityTeleportWithMotion(Entity entity) {
/* 19 */     super(entity);
/*    */     
/* 21 */     this.motion_x = (float)entity.motionX;
/* 22 */     this.motion_y = (float)entity.motionY;
/* 23 */     this.motion_z = (float)entity.motionZ;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyToEntity(Entity entity) {
/* 28 */     super.applyToEntity(entity);
/*    */     
/* 30 */     entity.motionX = this.motion_x;
/* 31 */     entity.motionY = this.motion_y;
/* 32 */     entity.motionZ = this.motion_z;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 37 */     super.readPacketData(par1DataInput);
/*    */     
/* 39 */     this.motion_x = par1DataInput.readFloat();
/* 40 */     this.motion_y = par1DataInput.readFloat();
/* 41 */     this.motion_z = par1DataInput.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 46 */     super.writePacketData(par1DataOutput);
/*    */     
/* 48 */     par1DataOutput.writeFloat(this.motion_x);
/* 49 */     par1DataOutput.writeFloat(this.motion_y);
/* 50 */     par1DataOutput.writeFloat(this.motion_z);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 55 */     return super.getPacketSize() + 12;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet86EntityTeleportWithMotion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */