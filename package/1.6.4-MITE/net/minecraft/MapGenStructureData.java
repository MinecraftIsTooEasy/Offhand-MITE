/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MapGenStructureData
/*    */   extends WorldSavedData
/*    */ {
/*    */   private NBTTagCompound field_143044_a;
/*    */   
/*    */   public MapGenStructureData(String string) {
/* 12 */     super(string);
/* 13 */     this.field_143044_a = new NBTTagCompound("Features");
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 18 */     this.field_143044_a = nBTTagCompound.getCompoundTag("Features");
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 23 */     nBTTagCompound.setTag("Features", this.field_143044_a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_143043_a(NBTTagCompound nBTTagCompound, int i, int j) {
/* 31 */     String str = func_143042_b(i, j);
/* 32 */     nBTTagCompound.setName(str);
/* 33 */     this.field_143044_a.setTag(str, nBTTagCompound);
/*    */   }
/*    */   
/*    */   public String func_143042_b(int i, int j) {
/* 37 */     return "[" + i + "," + j + "]";
/*    */   }
/*    */   
/*    */   public NBTTagCompound func_143041_a() {
/* 41 */     return this.field_143044_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenStructureData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */