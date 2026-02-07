/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet12PlayerLook
/*    */   extends Packet10Flying
/*    */ {
/*    */   public Packet12PlayerLook() {
/* 11 */     this.rotating = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet12PlayerLook(float par1, float par2, boolean par3) {
/* 16 */     this.yaw = par1;
/* 17 */     this.pitch = par2;
/* 18 */     this.onGround = par3;
/* 19 */     this.rotating = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 27 */     this.yaw = par1DataInput.readFloat();
/* 28 */     this.pitch = par1DataInput.readFloat();
/* 29 */     super.readPacketData(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 37 */     par1DataOutput.writeFloat(this.yaw);
/* 38 */     par1DataOutput.writeFloat(this.pitch);
/* 39 */     super.writePacketData(par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 48 */     return 8 + super.getPacketSize();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet12PlayerLook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */