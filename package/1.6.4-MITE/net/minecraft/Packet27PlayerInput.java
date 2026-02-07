/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet27PlayerInput
/*    */   extends Packet
/*    */ {
/*    */   private float move_strafing;
/*    */   private float move_forward;
/*    */   private boolean jumping;
/*    */   private boolean sneaking;
/*    */   
/*    */   public Packet27PlayerInput() {}
/*    */   
/*    */   public Packet27PlayerInput(float move_strafing, float move_forward, boolean jumping, boolean sneaking) {
/* 20 */     this.move_strafing = move_strafing;
/* 21 */     this.move_forward = move_forward;
/* 22 */     this.jumping = jumping;
/* 23 */     this.sneaking = sneaking;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 31 */     this.move_strafing = par1DataInput.readFloat();
/* 32 */     this.move_forward = par1DataInput.readFloat();
/* 33 */     this.jumping = par1DataInput.readBoolean();
/* 34 */     this.sneaking = par1DataInput.readBoolean();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 42 */     par1DataOutput.writeFloat(this.move_strafing);
/* 43 */     par1DataOutput.writeFloat(this.move_forward);
/* 44 */     par1DataOutput.writeBoolean(this.jumping);
/* 45 */     par1DataOutput.writeBoolean(this.sneaking);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 53 */     par1NetHandler.func_110774_a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 61 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMoveStrafing() {
/* 66 */     return this.move_strafing;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMoveForward() {
/* 71 */     return this.move_forward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getJumping() {
/* 76 */     return this.jumping;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getSneaking() {
/* 81 */     return this.sneaking;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet27PlayerInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */