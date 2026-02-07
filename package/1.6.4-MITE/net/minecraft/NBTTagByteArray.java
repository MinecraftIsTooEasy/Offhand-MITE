/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NBTTagByteArray
/*    */   extends NBTBase
/*    */ {
/*    */   public byte[] byteArray;
/*    */   
/*    */   public NBTTagByteArray(String par1Str) {
/* 17 */     super(7, par1Str);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagByteArray(String par1Str, byte[] par2ArrayOfByte) {
/* 23 */     super(7, par1Str);
/* 24 */     this.byteArray = par2ArrayOfByte;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput par1DataOutput) throws IOException {
/* 32 */     par1DataOutput.writeInt(this.byteArray.length);
/* 33 */     par1DataOutput.write(this.byteArray);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void load(DataInput par1DataInput, int par2) throws IOException {
/* 41 */     int var3 = par1DataInput.readInt();
/* 42 */     this.byteArray = new byte[var3];
/* 43 */     par1DataInput.readFully(this.byteArray);
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
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return "[" + this.byteArray.length + " bytes]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 65 */     byte[] var1 = new byte[this.byteArray.length];
/* 66 */     System.arraycopy(this.byteArray, 0, var1, 0, this.byteArray.length);
/* 67 */     return new NBTTagByteArray(getName(), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object par1Obj) {
/* 72 */     return super.equals(par1Obj) ? Arrays.equals(this.byteArray, ((NBTTagByteArray)par1Obj).byteArray) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 77 */     return super.hashCode() ^ Arrays.hashCode(this.byteArray);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTTagByteArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */