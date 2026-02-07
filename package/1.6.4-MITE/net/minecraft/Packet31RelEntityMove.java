/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet31RelEntityMove
/*    */   extends Packet30Entity
/*    */ {
/*    */   public Packet31RelEntityMove() {}
/*    */   
/*    */   public Packet31RelEntityMove(Entity entity, byte par2, byte par3, byte par4) {
/* 15 */     super(entity);
/* 16 */     this.xPosition = par2;
/* 17 */     this.yPosition = par3;
/* 18 */     this.zPosition = par4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 26 */     super.readPacketData(par1DataInput);
/* 27 */     this.xPosition = par1DataInput.readByte();
/* 28 */     this.yPosition = par1DataInput.readByte();
/* 29 */     this.zPosition = par1DataInput.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 37 */     super.writePacketData(par1DataOutput);
/* 38 */     par1DataOutput.writeByte(this.xPosition);
/* 39 */     par1DataOutput.writeByte(this.yPosition);
/* 40 */     par1DataOutput.writeByte(this.zPosition);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 49 */     return super.getPacketSize() + 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet31RelEntityMove.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */