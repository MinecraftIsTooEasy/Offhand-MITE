/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class Packet87SetDespawnCounters
/*    */   extends Packet
/*    */ {
/*    */   public int entries;
/* 12 */   public int[] entity_id = new int[64];
/* 13 */   public short[] despawn_counter = new short[64];
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int entity_id, short despawn_counter) {
/* 19 */     if (this.entries >= 64) {
/*    */       return;
/*    */     }
/* 22 */     this.entity_id[this.entries] = entity_id;
/* 23 */     this.despawn_counter[this.entries] = despawn_counter;
/*    */     
/* 25 */     this.entries++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 30 */     this.entries = par1DataInput.readByte();
/*    */     
/* 32 */     for (int i = 0; i < this.entries; i++) {
/*    */       
/* 34 */       this.entity_id[i] = par1DataInput.readInt();
/* 35 */       this.despawn_counter[i] = par1DataInput.readShort();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 41 */     par1DataOutput.writeByte(this.entries);
/*    */     
/* 43 */     for (int i = 0; i < this.entries; i++) {
/*    */       
/* 45 */       par1DataOutput.writeInt(this.entity_id[i]);
/* 46 */       par1DataOutput.writeShort(this.despawn_counter[i]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 52 */     net_handler.handleSetDespawnCounters(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 57 */     return 1 + this.entries * 6;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet87SetDespawnCounters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */