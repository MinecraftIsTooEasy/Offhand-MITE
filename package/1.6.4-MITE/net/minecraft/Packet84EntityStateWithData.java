/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet84EntityStateWithData
/*    */   extends Packet38EntityStatus
/*    */ {
/*    */   public int data;
/*    */   
/*    */   public Packet84EntityStateWithData() {}
/*    */   
/*    */   public Packet84EntityStateWithData(int entity_id, EnumEntityState entity_state, int data) {
/* 17 */     super(entity_id, entity_state);
/* 18 */     this.data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 23 */     super.readPacketData(par1DataInput);
/* 24 */     this.data = par1DataInput.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 29 */     super.writePacketData(par1DataOutput);
/* 30 */     par1DataOutput.writeInt(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 35 */     return 9;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet84EntityStateWithData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */