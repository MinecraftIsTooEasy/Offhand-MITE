/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet93WorldAchievement
/*    */   extends Packet
/*    */ {
/*    */   public Achievement achievement;
/*    */   public String username;
/*    */   public int day;
/*    */   
/*    */   public Packet93WorldAchievement() {}
/*    */   
/*    */   public Packet93WorldAchievement(Achievement achievement, String username, int day) {
/* 19 */     this.achievement = achievement;
/* 20 */     this.username = username;
/* 21 */     this.day = day;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet93WorldAchievement(Achievement achievement, EntityPlayer player) {
/* 26 */     this(achievement, player.username, player.worldObj.getDayOfWorld());
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 31 */     this.achievement = (Achievement)StatList.getStat(par1DataInput.readInt());
/* 32 */     this.username = readString(par1DataInput, 16);
/* 33 */     this.day = par1DataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 38 */     par1DataOutput.writeInt(this.achievement.statId);
/* 39 */     writeString(this.username, par1DataOutput);
/* 40 */     par1DataOutput.writeInt(this.day);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 45 */     net_handler.handleWorldAchievement(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 50 */     return 4 + 2 * this.username.length() + 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet93WorldAchievement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */