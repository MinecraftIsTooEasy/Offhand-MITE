/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet250CustomPayload
/*    */   extends Packet
/*    */ {
/*    */   public String channel;
/*    */   public int length;
/*    */   public byte[] data;
/*    */   
/*    */   public Packet250CustomPayload() {}
/*    */   
/*    */   public Packet250CustomPayload(String string, byte[] bs) {
/* 27 */     this.channel = string;
/* 28 */     this.data = bs;
/*    */     
/* 30 */     if (bs != null) {
/* 31 */       this.length = bs.length;
/*    */       
/* 33 */       if (this.length > 32767) {
/* 34 */         throw new IllegalArgumentException("Payload may not be larger than 32k");
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 41 */     this.channel = readString(dataInput, 20);
/* 42 */     this.length = dataInput.readShort();
/*    */     
/* 44 */     if (this.length > 0 && this.length < 32767) {
/* 45 */       this.data = new byte[this.length];
/* 46 */       dataInput.readFully(this.data);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 52 */     writeString(this.channel, dataOutput);
/* 53 */     dataOutput.writeShort((short)this.length);
/* 54 */     if (this.data != null) {
/* 55 */       dataOutput.write(this.data);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 61 */     netHandler.handleCustomPayload(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 66 */     return 2 + this.channel.length() * 2 + 2 + this.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet250CustomPayload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */