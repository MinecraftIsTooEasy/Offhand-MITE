/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet70GameEvent
/*    */   extends Packet
/*    */ {
/* 13 */   public static final String[] clientMessage = new String[] { "tile.bed.notValid", null, null, Minecraft.inDevMode() ? "gameMode.changed" : "gameMode.cannotBeChanged" };
/*    */ 
/*    */   
/*    */   public int eventType;
/*    */ 
/*    */   
/*    */   public int gameMode;
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet70GameEvent() {}
/*    */ 
/*    */   
/*    */   public Packet70GameEvent(int par1, int par2) {
/* 27 */     if (par1 == 3 && !Minecraft.inDevMode()) {
/* 28 */       par2 = EnumGameType.SURVIVAL.id;
/*    */     }
/* 30 */     this.eventType = par1;
/* 31 */     this.gameMode = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 39 */     this.eventType = par1DataInput.readByte();
/* 40 */     this.gameMode = par1DataInput.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 48 */     par1DataOutput.writeByte(this.eventType);
/* 49 */     par1DataOutput.writeByte(this.gameMode);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 57 */     par1NetHandler.handleGameEvent(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 65 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet70GameEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */