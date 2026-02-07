/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet82AddHunger
/*    */   extends Packet
/*    */ {
/*    */   public float hunger;
/*    */   
/*    */   public Packet82AddHunger() {}
/*    */   
/*    */   public Packet82AddHunger(float hunger) {
/* 17 */     this.hunger = hunger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 22 */     this.hunger = par1DataInput.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 27 */     par1DataOutput.writeFloat(this.hunger);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 32 */     net_handler.handleAddHunger(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 37 */     return 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet82AddHunger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */