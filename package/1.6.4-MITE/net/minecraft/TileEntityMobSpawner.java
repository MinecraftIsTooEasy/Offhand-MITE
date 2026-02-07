/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityMobSpawner
/*    */   extends TileEntity
/*    */ {
/* 11 */   private final MobSpawnerBaseLogic field_98050_a = new TileEntityMobSpawnerLogic(this);
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
/*    */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 49 */     super.readFromNBT(nBTTagCompound);
/* 50 */     this.field_98050_a.readFromNBT(nBTTagCompound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 55 */     super.writeToNBT(nBTTagCompound);
/* 56 */     this.field_98050_a.writeToNBT(nBTTagCompound);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateEntity() {
/* 61 */     this.field_98050_a.updateSpawner();
/* 62 */     super.updateEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 67 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 68 */     writeToNBT(nBTTagCompound);
/* 69 */     nBTTagCompound.removeTag("SpawnPotentials");
/* 70 */     return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nBTTagCompound);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean receiveClientEvent(int i, int j) {
/* 75 */     if (this.field_98050_a.setDelayToMin(i)) return true; 
/* 76 */     return super.receiveClientEvent(i, j);
/*    */   }
/*    */   
/*    */   public MobSpawnerBaseLogic getSpawnerLogic() {
/* 80 */     return this.field_98050_a;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityMobSpawner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */