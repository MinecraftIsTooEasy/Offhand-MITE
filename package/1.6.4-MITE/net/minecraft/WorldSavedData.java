/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public abstract class WorldSavedData
/*    */ {
/*    */   public final String mapName;
/*    */   private boolean dirty;
/*    */   
/*    */   public WorldSavedData(String string) {
/* 10 */     this.mapName = string;
/*    */   }
/*    */   
/*    */   public abstract void readFromNBT(NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   public abstract void writeToNBT(NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   public void markDirty() {
/* 18 */     setDirty(true);
/*    */   }
/*    */   
/*    */   public void setDirty(boolean bl) {
/* 22 */     this.dirty = bl;
/*    */   }
/*    */   
/*    */   public boolean isDirty() {
/* 26 */     return this.dirty;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldSavedData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */