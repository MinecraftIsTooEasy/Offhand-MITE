/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet17Sleep
/*    */   extends Packet
/*    */ {
/*    */   public int entityID;
/*    */   public int bedX;
/*    */   public int bedY;
/*    */   public int bedZ;
/*    */   public int field_73622_e;
/*    */   public int direction;
/*    */   public double pos_x_before_bed;
/*    */   public double pos_y_before_bed;
/*    */   public double pos_z_before_bed;
/*    */   
/*    */   public Packet17Sleep() {}
/*    */   
/*    */   public Packet17Sleep(EntityPlayer par1Entity, int par2, int par3, int par4, int par5, int direction) {
/* 23 */     this.field_73622_e = par2;
/* 24 */     this.bedX = par3;
/* 25 */     this.bedY = par4;
/* 26 */     this.bedZ = par5;
/* 27 */     this.entityID = par1Entity.entityId;
/* 28 */     this.direction = direction;
/*    */     
/* 30 */     this.pos_x_before_bed = par1Entity.pos_x_before_bed;
/* 31 */     this.pos_y_before_bed = par1Entity.pos_y_before_bed;
/* 32 */     this.pos_z_before_bed = par1Entity.pos_z_before_bed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 40 */     this.entityID = par1DataInput.readInt();
/* 41 */     this.field_73622_e = par1DataInput.readByte();
/* 42 */     this.bedX = par1DataInput.readInt();
/* 43 */     this.bedY = par1DataInput.readByte();
/* 44 */     this.bedZ = par1DataInput.readInt();
/* 45 */     this.direction = par1DataInput.readByte();
/*    */     
/* 47 */     this.pos_x_before_bed = par1DataInput.readDouble();
/* 48 */     this.pos_y_before_bed = par1DataInput.readDouble();
/* 49 */     this.pos_z_before_bed = par1DataInput.readDouble();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 57 */     par1DataOutput.writeInt(this.entityID);
/* 58 */     par1DataOutput.writeByte(this.field_73622_e);
/* 59 */     par1DataOutput.writeInt(this.bedX);
/* 60 */     par1DataOutput.writeByte(this.bedY);
/* 61 */     par1DataOutput.writeInt(this.bedZ);
/* 62 */     par1DataOutput.writeByte(this.direction);
/*    */     
/* 64 */     par1DataOutput.writeDouble(this.pos_x_before_bed);
/* 65 */     par1DataOutput.writeDouble(this.pos_y_before_bed);
/* 66 */     par1DataOutput.writeDouble(this.pos_z_before_bed);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 74 */     par1NetHandler.handleSleep(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 84 */     return 39;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet17Sleep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */