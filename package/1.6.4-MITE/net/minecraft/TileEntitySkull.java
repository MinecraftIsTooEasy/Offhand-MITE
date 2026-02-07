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
/*    */ public class TileEntitySkull
/*    */   extends TileEntity
/*    */ {
/*    */   private int skullType;
/*    */   private int skullRotation;
/* 16 */   private String extraType = "";
/*    */ 
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 20 */     super.writeToNBT(nBTTagCompound);
/* 21 */     nBTTagCompound.setByte("SkullType", (byte)(this.skullType & 0xFF));
/* 22 */     nBTTagCompound.setByte("Rot", (byte)(this.skullRotation & 0xFF));
/* 23 */     nBTTagCompound.setString("ExtraType", this.extraType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 28 */     super.readFromNBT(nBTTagCompound);
/* 29 */     this.skullType = nBTTagCompound.getByte("SkullType");
/* 30 */     this.skullRotation = nBTTagCompound.getByte("Rot");
/* 31 */     if (nBTTagCompound.hasKey("ExtraType")) this.extraType = nBTTagCompound.getString("ExtraType");
/*    */   
/*    */   }
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 36 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 37 */     writeToNBT(nBTTagCompound);
/* 38 */     return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, nBTTagCompound);
/*    */   }
/*    */   
/*    */   public void setSkullType(int i, String string) {
/* 42 */     this.skullType = i;
/* 43 */     this.extraType = string;
/*    */   }
/*    */   
/*    */   public int getSkullType() {
/* 47 */     return this.skullType;
/*    */   }
/*    */   
/*    */   public int func_82119_b() {
/* 51 */     return this.skullRotation;
/*    */   }
/*    */   
/*    */   public void setSkullRotation(int i) {
/* 55 */     this.skullRotation = i;
/*    */   }
/*    */   
/*    */   public String getExtraType() {
/* 59 */     return this.extraType;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntitySkull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */