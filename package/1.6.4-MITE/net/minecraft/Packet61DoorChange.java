/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ public class Packet61DoorChange
/*    */   extends Packet
/*    */ {
/*    */   public int sfxID;
/*    */   public int auxData;
/*    */   public int posX;
/*    */   
/*    */   public Packet61DoorChange(int i, int j, int k, int l, int m, boolean bl) {
/* 15 */     this.sfxID = i;
/* 16 */     this.posX = j;
/* 17 */     this.posY = k;
/* 18 */     this.posZ = l;
/* 19 */     this.auxData = m;
/* 20 */     this.disableRelativeVolume = bl;
/*    */   }
/*    */   public int posY; public int posZ; private boolean disableRelativeVolume;
/*    */   public Packet61DoorChange() {}
/*    */   public void readPacketData(DataInput dataInput) {
/* 25 */     this.sfxID = dataInput.readInt();
/* 26 */     this.posX = dataInput.readInt();
/* 27 */     this.posY = dataInput.readByte() & 0xFF;
/* 28 */     this.posZ = dataInput.readInt();
/* 29 */     this.auxData = dataInput.readInt();
/* 30 */     this.disableRelativeVolume = dataInput.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 35 */     dataOutput.writeInt(this.sfxID);
/* 36 */     dataOutput.writeInt(this.posX);
/* 37 */     dataOutput.writeByte(this.posY & 0xFF);
/* 38 */     dataOutput.writeInt(this.posZ);
/* 39 */     dataOutput.writeInt(this.auxData);
/* 40 */     dataOutput.writeBoolean(this.disableRelativeVolume);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 45 */     netHandler.handleDoorChange(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 50 */     return 21;
/*    */   }
/*    */   
/*    */   public boolean getRelativeVolumeDisabled() {
/* 54 */     return this.disableRelativeVolume;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet61DoorChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */