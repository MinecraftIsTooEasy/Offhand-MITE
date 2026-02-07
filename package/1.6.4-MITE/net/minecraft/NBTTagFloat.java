/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NBTTagFloat
/*    */   extends NBTBase
/*    */ {
/*    */   public float data;
/*    */   
/*    */   public NBTTagFloat(String par1Str) {
/* 16 */     super(5, par1Str);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagFloat(String par1Str, float par2) {
/* 22 */     super(5, par1Str);
/* 23 */     this.data = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput par1DataOutput) throws IOException {
/* 31 */     par1DataOutput.writeFloat(this.data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void load(DataInput par1DataInput, int par2) throws IOException {
/* 39 */     this.data = par1DataInput.readFloat();
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
/* 53 */     return "" + this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 61 */     return new NBTTagFloat(getName(), this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object par1Obj) {
/* 66 */     if (super.equals(par1Obj)) {
/*    */       
/* 68 */       NBTTagFloat var2 = (NBTTagFloat)par1Obj;
/* 69 */       return (this.data == var2.data);
/*    */     } 
/*    */ 
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 79 */     return super.hashCode() ^ Float.floatToIntBits(this.data);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NBTTagFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */