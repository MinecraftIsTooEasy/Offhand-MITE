/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutputStream;
/*    */ 
/*    */ public class RConOutputStream {
/*    */   private ByteArrayOutputStream byteArrayOutput;
/*    */   
/*    */   public RConOutputStream(int i) {
/* 10 */     this.byteArrayOutput = new ByteArrayOutputStream(i);
/* 11 */     this.output = new DataOutputStream(this.byteArrayOutput);
/*    */   }
/*    */   private DataOutputStream output;
/*    */   public void writeByteArray(byte[] bs) {
/* 15 */     this.output.write(bs, 0, bs.length);
/*    */   }
/*    */   
/*    */   public void writeString(String string) {
/* 19 */     this.output.writeBytes(string);
/* 20 */     this.output.write(0);
/*    */   }
/*    */   
/*    */   public void writeInt(int i) {
/* 24 */     this.output.write(i);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeShort(short s) {
/* 29 */     this.output.writeShort(Short.reverseBytes(s));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] toByteArray() {
/* 41 */     return this.byteArrayOutput.toByteArray();
/*    */   }
/*    */   
/*    */   public void reset() {
/* 45 */     this.byteArrayOutput.reset();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */