/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet33RelEntityMoveLook
/*    */   extends Packet30Entity
/*    */ {
/*    */   public Packet33RelEntityMoveLook() {
/* 11 */     this.rotating = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet33RelEntityMoveLook(Entity entity, byte par2, byte par3, byte par4, byte par5, byte par6) {
/* 18 */     super(entity);
/* 19 */     this.xPosition = par2;
/* 20 */     this.yPosition = par3;
/* 21 */     this.zPosition = par4;
/* 22 */     this.yaw = par5;
/* 23 */     this.pitch = par6;
/* 24 */     this.rotating = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 32 */     super.readPacketData(par1DataInput);
/* 33 */     this.xPosition = par1DataInput.readByte();
/* 34 */     this.yPosition = par1DataInput.readByte();
/* 35 */     this.zPosition = par1DataInput.readByte();
/* 36 */     this.yaw = par1DataInput.readByte();
/* 37 */     this.pitch = par1DataInput.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 45 */     super.writePacketData(par1DataOutput);
/* 46 */     par1DataOutput.writeByte(this.xPosition);
/* 47 */     par1DataOutput.writeByte(this.yPosition);
/* 48 */     par1DataOutput.writeByte(this.zPosition);
/* 49 */     par1DataOutput.writeByte(this.yaw);
/* 50 */     par1DataOutput.writeByte(this.pitch);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 59 */     return super.getPacketSize() + 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet33RelEntityMoveLook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */