/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet201PlayerInfo
/*    */   extends Packet
/*    */ {
/*    */   public String playerName;
/*    */   public boolean isConnected;
/*    */   public int ping;
/*    */   public int level;
/*    */   
/*    */   public Packet201PlayerInfo() {}
/*    */   
/*    */   public Packet201PlayerInfo(String par1Str, boolean par2, int par3, int level) {
/* 22 */     this.playerName = par1Str;
/* 23 */     this.isConnected = par2;
/* 24 */     this.ping = par3;
/* 25 */     this.level = level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 33 */     this.playerName = readString(par1DataInput, 16);
/* 34 */     this.isConnected = (par1DataInput.readByte() != 0);
/* 35 */     this.ping = par1DataInput.readShort();
/*    */     
/* 37 */     this.level = par1DataInput.readShort();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 45 */     writeString(this.playerName, par1DataOutput);
/* 46 */     par1DataOutput.writeByte(this.isConnected ? 1 : 0);
/* 47 */     par1DataOutput.writeShort(this.ping);
/*    */     
/* 49 */     par1DataOutput.writeShort(this.level);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 57 */     par1NetHandler.handlePlayerInfo(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 66 */     return this.playerName.length() + 2 + 1 + 2 + 1 + 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet201PlayerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */