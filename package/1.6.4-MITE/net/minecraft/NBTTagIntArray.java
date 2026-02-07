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
/*    */ public final class NBTTagIntArray
/*    */   extends NBTBase
/*    */ {
/*    */   public int[] intArray;
/*    */   
/*    */   public NBTTagIntArray(String par1Str) {
/* 17 */     super(11, par1Str);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagIntArray(String par1Str, int[] par2ArrayOfInteger) {
/* 23 */     super(11, par1Str);
/* 24 */     this.intArray = par2ArrayOfInteger;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput par1DataOutput) throws IOException {
/* 32 */     par1DataOutput.writeInt(this.intArray.length);
/*    */     
/* 34 */     for (int var2 = 0; var2 < this.intArray.length; var2++)
/*    */     {
/* 36 */       par1DataOutput.writeInt(this.intArray[var2]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void load(DataInput par1DataInput, int par2) throws IOException {
/* 45 */     int var3 = par1DataInput.readInt();
/* 46 */     this.intArray = new int[var3];
/*    */     
/* 48 */     for (int var4 = 0; var4 < var3; var4++)
/*    */     {
/* 50 */       this.intArray[var4] = par1DataInput.readInt();
/*    */     }
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
/* 65 */     return "[" + this.intArray.length + " bytes]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 73 */     int[] var1 = new int[this.intArray.length];
/* 74 */     System.arraycopy(this.intArray, 0, var1, 0, this.intArray.length);
/* 75 */     return new NBTTagIntArray(getName(), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object par1Obj) {
/* 80 */     if (!super.equals(par1Obj))
/*    */     {
/* 82 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 86 */     NBTTagIntArray var2 = (NBTTagIntArray)par1Obj;
/* 87 */     return ((this.intArray == null && var2.intArray == null) || (this.intArray != null && Arrays.equals(this.intArray, var2.intArray)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 93 */     return super.hashCode() ^ Arrays.hashCode(this.intArray);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTTagIntArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */