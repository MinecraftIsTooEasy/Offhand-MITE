/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NBTTagString
/*    */   extends NBTBase
/*    */ {
/*    */   public String data;
/*    */   
/*    */   public NBTTagString(String par1Str) {
/* 16 */     super(8, par1Str);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagString(String par1Str, String par2Str) {
/* 22 */     super(8, par1Str);
/* 23 */     this.data = par2Str;
/*    */     
/* 25 */     if (par2Str == null)
/*    */     {
/* 27 */       throw new IllegalArgumentException("Empty string not allowed");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput par1DataOutput) throws IOException {
/* 36 */     par1DataOutput.writeUTF(this.data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void load(DataInput par1DataInput, int par2) throws IOException {
/* 44 */     this.data = par1DataInput.readUTF();
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
/* 58 */     return "" + this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 66 */     return new NBTTagString(getName(), this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object par1Obj) {
/* 71 */     if (!super.equals(par1Obj))
/*    */     {
/* 73 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 77 */     NBTTagString var2 = (NBTTagString)par1Obj;
/* 78 */     return ((this.data == null && var2.data == null) || (this.data != null && this.data.equals(var2.data)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 84 */     return super.hashCode() ^ this.data.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTTagString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */