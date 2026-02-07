/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet133TileEditorOpen
/*    */   extends Packet
/*    */ {
/*    */   public int field_142037_a;
/*    */   public int field_142035_b;
/*    */   public int field_142036_c;
/*    */   public int field_142034_d;
/*    */   
/*    */   public Packet133TileEditorOpen() {}
/*    */   
/*    */   public Packet133TileEditorOpen(int i, int j, int k, int l) {
/* 17 */     this.field_142037_a = i;
/* 18 */     this.field_142035_b = j;
/* 19 */     this.field_142036_c = k;
/* 20 */     this.field_142034_d = l;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 25 */     netHandler.func_142031_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 30 */     this.field_142037_a = dataInput.readByte();
/* 31 */     this.field_142035_b = dataInput.readInt();
/* 32 */     this.field_142036_c = dataInput.readInt();
/* 33 */     this.field_142034_d = dataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 38 */     dataOutput.writeByte(this.field_142037_a);
/* 39 */     dataOutput.writeInt(this.field_142035_b);
/* 40 */     dataOutput.writeInt(this.field_142036_c);
/* 41 */     dataOutput.writeInt(this.field_142034_d);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 46 */     return 13;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet133TileEditorOpen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */