/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet91PlayerStat
/*    */   extends Packet
/*    */ {
/*    */   private static final byte INTEGER = 0;
/*    */   private static final byte ZERO_OR_ONE = 1;
/*    */   private static final byte LONG = 2;
/*    */   private byte type;
/*    */   int id;
/*    */   long value;
/*    */   
/*    */   public Packet91PlayerStat() {}
/*    */   
/*    */   private Packet91PlayerStat(int id, long value) {
/* 23 */     StatBase stat = StatList.getStat(id);
/*    */     
/* 25 */     determineType(stat);
/*    */     
/* 27 */     if (this.type == 1 && value != 0L) {
/* 28 */       value = 1L;
/*    */     }
/* 30 */     this.id = id;
/* 31 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet91PlayerStat(StatBase stat_base, long value) {
/* 36 */     this(stat_base.statId, value);
/*    */   }
/*    */ 
/*    */   
/*    */   private byte determineType(StatBase stat_base) {
/* 41 */     this.type = StatList.isEitherZeroOrOne(stat_base) ? 1 : (StatList.hasLongValue(stat_base) ? 2 : 0);
/* 42 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 47 */     this.id = par1DataInput.readInt();
/*    */     
/* 49 */     StatBase stat = StatList.getStat(this.id);
/*    */     
/* 51 */     determineType(stat);
/*    */     
/* 53 */     this.value = (this.type == 1) ? par1DataInput.readByte() : ((this.type == 2) ? par1DataInput.readLong() : par1DataInput.readInt());
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 58 */     par1DataOutput.writeInt(this.id);
/*    */     
/* 60 */     StatBase stat = StatList.getStat(this.id);
/*    */     
/* 62 */     if (this.type == 1) {
/* 63 */       par1DataOutput.writeByte((this.value == 0L) ? 0 : 1);
/* 64 */     } else if (this.type == 2) {
/* 65 */       par1DataOutput.writeLong(this.value);
/*    */     } else {
/* 67 */       par1DataOutput.writeInt((int)this.value);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 72 */     net_handler.handlePlayerStat(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 77 */     return (this.type == 1) ? 5 : ((this.type == 2) ? 12 : 8);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet91PlayerStat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */