/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet200Statistic
/*    */   extends Packet
/*    */ {
/*    */   public int statisticId;
/*    */   public int amount;
/*    */   
/*    */   public Packet200Statistic() {}
/*    */   
/*    */   public Packet200Statistic(int i, int j) {
/* 15 */     this.statisticId = i;
/* 16 */     this.amount = j;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 21 */     netHandler.handleStatistic(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 26 */     this.statisticId = dataInput.readInt();
/* 27 */     this.amount = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 32 */     dataOutput.writeInt(this.statisticId);
/* 33 */     dataOutput.writeInt(this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 38 */     return 6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canProcessAsync() {
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet200Statistic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */