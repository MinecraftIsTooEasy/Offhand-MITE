/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet208SetDisplayObjective
/*    */   extends Packet
/*    */ {
/*    */   public int scoreboardPosition;
/*    */   public String scoreName;
/*    */   
/*    */   public Packet208SetDisplayObjective() {}
/*    */   
/*    */   public Packet208SetDisplayObjective(int i, ScoreObjective scoreObjective) {
/* 17 */     this.scoreboardPosition = i;
/*    */     
/* 19 */     if (scoreObjective == null) {
/* 20 */       this.scoreName = "";
/*    */     } else {
/* 22 */       this.scoreName = scoreObjective.getName();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 28 */     this.scoreboardPosition = dataInput.readByte();
/* 29 */     this.scoreName = readString(dataInput, 16);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 34 */     dataOutput.writeByte(this.scoreboardPosition);
/* 35 */     writeString(this.scoreName, dataOutput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 40 */     netHandler.handleSetDisplayObjective(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 45 */     return 3 + this.scoreName.length();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet208SetDisplayObjective.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */