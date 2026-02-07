/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ public class Packet254ServerPing
/*    */   extends Packet
/*    */ {
/* 10 */   private static final int field_140051_d = (new Packet250CustomPayload()).getPacketId();
/*    */   
/*    */   public int readSuccessfully;
/*    */   
/*    */   public String field_140052_b;
/*    */   
/*    */   public int field_140053_c;
/*    */   
/*    */   public Packet254ServerPing() {}
/*    */   
/*    */   public Packet254ServerPing(int i, String string, int j) {
/* 21 */     this.readSuccessfully = i;
/* 22 */     this.field_140052_b = string;
/* 23 */     this.field_140053_c = j;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/*    */     try {
/* 32 */       this.readSuccessfully = dataInput.readByte();
/*    */ 
/*    */       
/*    */       try {
/* 36 */         dataInput.readByte();
/* 37 */         readString(dataInput, 255);
/* 38 */         dataInput.readShort();
/*    */         
/* 40 */         this.readSuccessfully = dataInput.readByte();
/* 41 */         if (this.readSuccessfully >= 73) {
/* 42 */           this.field_140052_b = readString(dataInput, 255);
/* 43 */           this.field_140053_c = dataInput.readInt();
/*    */         } 
/* 45 */       } catch (Throwable throwable) {
/* 46 */         this.field_140052_b = "";
/*    */       } 
/* 48 */     } catch (Throwable throwable) {
/* 49 */       this.readSuccessfully = 0;
/* 50 */       this.field_140052_b = "";
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 57 */     dataOutput.writeByte(1);
/*    */ 
/*    */     
/* 60 */     dataOutput.writeByte(field_140051_d);
/* 61 */     Packet.writeString("MC|PingHost", dataOutput);
/* 62 */     dataOutput.writeShort(3 + 2 * this.field_140052_b.length() + 4);
/*    */ 
/*    */     
/* 65 */     dataOutput.writeByte(this.readSuccessfully);
/* 66 */     Packet.writeString(this.field_140052_b, dataOutput);
/* 67 */     dataOutput.writeInt(this.field_140053_c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 72 */     netHandler.handleServerPing(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 77 */     return 3 + this.field_140052_b.length() * 2 + 4;
/*    */   }
/*    */   
/*    */   public boolean func_140050_d() {
/* 81 */     return (this.readSuccessfully == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet254ServerPing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */