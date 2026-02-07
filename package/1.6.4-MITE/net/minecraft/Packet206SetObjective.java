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
/*    */ public class Packet206SetObjective
/*    */   extends Packet
/*    */ {
/*    */   public String objectiveName;
/*    */   public String objectiveDisplayName;
/*    */   public int change;
/*    */   
/*    */   public Packet206SetObjective() {}
/*    */   
/*    */   public Packet206SetObjective(ScoreObjective scoreObjective, int i) {
/* 22 */     this.objectiveName = scoreObjective.getName();
/* 23 */     this.objectiveDisplayName = scoreObjective.getDisplayName();
/* 24 */     this.change = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 29 */     this.objectiveName = readString(dataInput, 16);
/* 30 */     this.objectiveDisplayName = readString(dataInput, 32);
/* 31 */     this.change = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 36 */     writeString(this.objectiveName, dataOutput);
/* 37 */     writeString(this.objectiveDisplayName, dataOutput);
/* 38 */     dataOutput.writeByte(this.change);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 43 */     netHandler.handleSetObjective(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 48 */     return 2 + this.objectiveName.length() + 2 + this.objectiveDisplayName.length() + 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet206SetObjective.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */