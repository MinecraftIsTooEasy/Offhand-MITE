/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet14BlockDig
/*    */   extends Packet
/*    */ {
/*    */   public int xPosition;
/*    */   public int yPosition;
/*    */   public int zPosition;
/*    */   public EnumFace face;
/*    */   public int status;
/*    */   
/*    */   public Packet14BlockDig() {}
/*    */   
/*    */   public Packet14BlockDig(int par1, int par2, int par3, int par4, EnumFace face) {
/* 32 */     this.status = par1;
/* 33 */     this.xPosition = par2;
/* 34 */     this.yPosition = par3;
/* 35 */     this.zPosition = par4;
/*    */     
/* 37 */     this.face = face;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 45 */     this.status = par1DataInput.readUnsignedByte();
/* 46 */     this.xPosition = par1DataInput.readInt();
/* 47 */     this.yPosition = par1DataInput.readUnsignedByte();
/* 48 */     this.zPosition = par1DataInput.readInt();
/*    */ 
/*    */     
/* 51 */     int face_ordinal = par1DataInput.readUnsignedByte();
/*    */     
/* 53 */     this.face = EnumFace.isValidOrdinal(face_ordinal) ? EnumFace.get(face_ordinal) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 61 */     par1DataOutput.write(this.status);
/* 62 */     par1DataOutput.writeInt(this.xPosition);
/* 63 */     par1DataOutput.write(this.yPosition);
/* 64 */     par1DataOutput.writeInt(this.zPosition);
/*    */     
/* 66 */     par1DataOutput.write((this.face == null) ? -1 : this.face.ordinal());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 74 */     par1NetHandler.handleBlockDig(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 82 */     return 11;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet14BlockDig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */