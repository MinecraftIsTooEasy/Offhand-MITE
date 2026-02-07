/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet203AutoComplete
/*    */   extends Packet
/*    */ {
/*    */   private String text;
/*    */   
/*    */   public Packet203AutoComplete() {}
/*    */   
/*    */   public Packet203AutoComplete(String string) {
/* 18 */     this.text = string;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 23 */     this.text = readString(dataInput, 32767);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 28 */     writeString(StringUtils.substring(this.text, 0, 32767), dataOutput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 33 */     netHandler.handleAutoComplete(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 38 */     return 2 + this.text.length() * 2;
/*    */   }
/*    */   
/*    */   public String getText() {
/* 42 */     return this.text;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet packet) {
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet203AutoComplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */