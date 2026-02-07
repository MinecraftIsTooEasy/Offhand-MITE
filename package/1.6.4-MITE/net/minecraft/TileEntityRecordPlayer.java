/*    */ package net.minecraft;
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
/*    */ 
/*    */ public class TileEntityRecordPlayer
/*    */   extends TileEntity
/*    */ {
/*    */   private ItemStack record;
/*    */   
/*    */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 21 */     super.readFromNBT(nBTTagCompound);
/*    */     
/* 23 */     if (nBTTagCompound.hasKey("RecordItem")) {
/* 24 */       func_96098_a(ItemStack.loadItemStackFromNBT(nBTTagCompound.getCompoundTag("RecordItem")));
/* 25 */     } else if (nBTTagCompound.getInteger("Record") > 0) {
/* 26 */       func_96098_a(new ItemStack(nBTTagCompound.getInteger("Record"), 1, 0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 32 */     super.writeToNBT(nBTTagCompound);
/*    */     
/* 34 */     if (func_96097_a() != null) {
/* 35 */       nBTTagCompound.setCompoundTag("RecordItem", func_96097_a().writeToNBT(new NBTTagCompound()));
/*    */       
/* 37 */       nBTTagCompound.setInteger("Record", (func_96097_a()).itemID);
/*    */     } 
/*    */   }
/*    */   
/*    */   public ItemStack func_96097_a() {
/* 42 */     return this.record;
/*    */   }
/*    */   
/*    */   public void func_96098_a(ItemStack itemStack) {
/* 46 */     this.record = itemStack;
/* 47 */     onInventoryChanged();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityRecordPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */