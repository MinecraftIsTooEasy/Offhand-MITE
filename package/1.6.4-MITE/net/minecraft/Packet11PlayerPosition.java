/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet11PlayerPosition
/*    */   extends Packet10Flying
/*    */ {
/*    */   public Packet11PlayerPosition() {
/* 11 */     this.moving = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet11PlayerPosition(double par1, double par3, double par5, double par7, boolean par9) {
/* 16 */     this.xPosition = par1;
/* 17 */     this.yPosition = par3;
/* 18 */     this.stance = par5;
/* 19 */     this.zPosition = par7;
/* 20 */     this.onGround = par9;
/* 21 */     this.moving = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 29 */     this.xPosition = par1DataInput.readDouble();
/* 30 */     this.yPosition = par1DataInput.readDouble();
/* 31 */     this.stance = par1DataInput.readDouble();
/* 32 */     this.zPosition = par1DataInput.readDouble();
/* 33 */     super.readPacketData(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 41 */     par1DataOutput.writeDouble(this.xPosition);
/* 42 */     par1DataOutput.writeDouble(this.yPosition);
/* 43 */     par1DataOutput.writeDouble(this.stance);
/* 44 */     par1DataOutput.writeDouble(this.zPosition);
/* 45 */     super.writePacketData(par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 54 */     return 32 + super.getPacketSize();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet11PlayerPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */