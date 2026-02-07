/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet54PlayNoteBlock
/*    */   extends Packet
/*    */ {
/*    */   public int xLocation;
/*    */   public int yLocation;
/*    */   public int zLocation;
/*    */   
/*    */   public Packet54PlayNoteBlock(int i, int j, int k, int l, int m, int n) {
/* 14 */     this.xLocation = i;
/* 15 */     this.yLocation = j;
/* 16 */     this.zLocation = k;
/* 17 */     this.instrumentType = m;
/* 18 */     this.pitch = n;
/* 19 */     this.blockId = l;
/*    */   }
/*    */   public int instrumentType; public int pitch; public int blockId;
/*    */   public Packet54PlayNoteBlock() {}
/*    */   public void readPacketData(DataInput dataInput) {
/* 24 */     this.xLocation = dataInput.readInt();
/* 25 */     this.yLocation = dataInput.readShort();
/* 26 */     this.zLocation = dataInput.readInt();
/* 27 */     this.instrumentType = dataInput.readUnsignedByte();
/* 28 */     this.pitch = dataInput.readUnsignedByte();
/* 29 */     this.blockId = dataInput.readShort() & 0xFFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 34 */     dataOutput.writeInt(this.xLocation);
/* 35 */     dataOutput.writeShort(this.yLocation);
/* 36 */     dataOutput.writeInt(this.zLocation);
/* 37 */     dataOutput.write(this.instrumentType);
/* 38 */     dataOutput.write(this.pitch);
/* 39 */     dataOutput.writeShort(this.blockId & 0xFFF);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 44 */     netHandler.handleBlockEvent(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 49 */     return 14;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet54PlayNoteBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */