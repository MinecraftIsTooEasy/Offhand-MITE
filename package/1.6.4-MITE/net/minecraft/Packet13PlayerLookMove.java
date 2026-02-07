/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet13PlayerLookMove
/*    */   extends Packet10Flying
/*    */ {
/*    */   public Packet13PlayerLookMove() {
/* 11 */     this.rotating = true;
/* 12 */     this.moving = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet13PlayerLookMove(double par1, double par3, double par5, double par7, float par9, float par10, boolean par11) {
/* 17 */     this.xPosition = par1;
/* 18 */     this.yPosition = par3;
/* 19 */     this.stance = par5;
/* 20 */     this.zPosition = par7;
/* 21 */     this.yaw = par9;
/* 22 */     this.pitch = par10;
/* 23 */     this.onGround = par11;
/* 24 */     this.rotating = true;
/* 25 */     this.moving = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 33 */     this.xPosition = par1DataInput.readDouble();
/* 34 */     this.yPosition = par1DataInput.readDouble();
/* 35 */     this.stance = par1DataInput.readDouble();
/* 36 */     this.zPosition = par1DataInput.readDouble();
/* 37 */     this.yaw = par1DataInput.readFloat();
/* 38 */     this.pitch = par1DataInput.readFloat();
/* 39 */     super.readPacketData(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 47 */     par1DataOutput.writeDouble(this.xPosition);
/* 48 */     par1DataOutput.writeDouble(this.yPosition);
/* 49 */     par1DataOutput.writeDouble(this.stance);
/* 50 */     par1DataOutput.writeDouble(this.zPosition);
/* 51 */     par1DataOutput.writeFloat(this.yaw);
/* 52 */     par1DataOutput.writeFloat(this.pitch);
/* 53 */     super.writePacketData(par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 62 */     return 40 + super.getPacketSize();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet13PlayerLookMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */