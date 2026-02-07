/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet207SetScore
/*    */   extends Packet
/*    */ {
/* 15 */   public String itemName = "";
/* 16 */   public String scoreName = "";
/*    */   
/*    */   public int value;
/*    */   public int updateOrRemove;
/*    */   
/*    */   public Packet207SetScore() {}
/*    */   
/*    */   public Packet207SetScore(Score score, int i) {
/* 24 */     this.itemName = score.getPlayerName();
/* 25 */     this.scoreName = score.func_96645_d().getName();
/* 26 */     this.value = score.getScorePoints();
/* 27 */     this.updateOrRemove = i;
/*    */   }
/*    */   
/*    */   public Packet207SetScore(String string) {
/* 31 */     this.itemName = string;
/* 32 */     this.scoreName = "";
/* 33 */     this.value = 0;
/* 34 */     this.updateOrRemove = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 39 */     this.itemName = readString(dataInput, 16);
/* 40 */     this.updateOrRemove = dataInput.readByte();
/*    */     
/* 42 */     if (this.updateOrRemove != 1) {
/* 43 */       this.scoreName = readString(dataInput, 16);
/* 44 */       this.value = dataInput.readInt();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 50 */     writeString(this.itemName, dataOutput);
/* 51 */     dataOutput.writeByte(this.updateOrRemove);
/*    */     
/* 53 */     if (this.updateOrRemove != 1) {
/* 54 */       writeString(this.scoreName, dataOutput);
/* 55 */       dataOutput.writeInt(this.value);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 61 */     netHandler.handleSetScore(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 66 */     return 2 + ((this.itemName == null) ? 0 : this.itemName.length()) + 2 + ((this.scoreName == null) ? 0 : this.scoreName.length()) + 4 + 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet207SetScore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */