/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class TileEntityComparator
/*    */   extends TileEntity
/*    */ {
/*    */   private int outputSignal;
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 10 */     super.writeToNBT(nBTTagCompound);
/* 11 */     nBTTagCompound.setInteger("OutputSignal", this.outputSignal);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 16 */     super.readFromNBT(nBTTagCompound);
/* 17 */     this.outputSignal = nBTTagCompound.getInteger("OutputSignal");
/*    */   }
/*    */   
/*    */   public int getOutputSignal() {
/* 21 */     return this.outputSignal;
/*    */   }
/*    */   
/*    */   public void setOutputSignal(int i) {
/* 25 */     this.outputSignal = i;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */