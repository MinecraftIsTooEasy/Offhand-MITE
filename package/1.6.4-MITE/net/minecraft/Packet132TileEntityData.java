/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet132TileEntityData
/*    */   extends Packet
/*    */ {
/*    */   public int xPosition;
/*    */   public int yPosition;
/*    */   public int zPosition;
/*    */   public int actionType;
/*    */   public NBTTagCompound data;
/*    */   
/*    */   public Packet132TileEntityData() {
/* 19 */     this.isChunkDataPacket = true;
/*    */   }
/*    */   
/*    */   public Packet132TileEntityData(int i, int j, int k, int l, NBTTagCompound nBTTagCompound) {
/* 23 */     this.isChunkDataPacket = true;
/* 24 */     this.xPosition = i;
/* 25 */     this.yPosition = j;
/* 26 */     this.zPosition = k;
/* 27 */     this.actionType = l;
/* 28 */     this.data = nBTTagCompound;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 33 */     this.xPosition = dataInput.readInt();
/* 34 */     this.yPosition = dataInput.readShort();
/* 35 */     this.zPosition = dataInput.readInt();
/* 36 */     this.actionType = dataInput.readByte();
/* 37 */     this.data = readNBTTagCompound(dataInput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 42 */     dataOutput.writeInt(this.xPosition);
/* 43 */     dataOutput.writeShort(this.yPosition);
/* 44 */     dataOutput.writeInt(this.zPosition);
/* 45 */     dataOutput.writeByte((byte)this.actionType);
/* 46 */     writeNBTTagCompound(this.data, dataOutput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 51 */     netHandler.handleTileEntityData(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 56 */     return 25;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet132TileEntityData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */