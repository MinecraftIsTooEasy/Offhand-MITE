/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntitySign
/*    */   extends TileEntity
/*    */ {
/* 11 */   public String[] signText = new String[] { "", "", "", "" };
/*    */ 
/*    */   
/* 14 */   public int lineBeingEdited = -1;
/*    */   
/*    */   private boolean isEditable = true;
/*    */   private EntityPlayer field_142011_d;
/*    */   
/*    */   public void writeToNBT(NBTTagCompound nBTTagCompound) {
/* 20 */     super.writeToNBT(nBTTagCompound);
/* 21 */     nBTTagCompound.setString("Text1", this.signText[0]);
/* 22 */     nBTTagCompound.setString("Text2", this.signText[1]);
/* 23 */     nBTTagCompound.setString("Text3", this.signText[2]);
/* 24 */     nBTTagCompound.setString("Text4", this.signText[3]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound nBTTagCompound) {
/* 29 */     this.isEditable = false;
/* 30 */     super.readFromNBT(nBTTagCompound);
/* 31 */     for (byte b = 0; b < 4; b++) {
/* 32 */       this.signText[b] = nBTTagCompound.getString("Text" + (b + 1));
/* 33 */       if (this.signText[b].length() > 15) this.signText[b] = this.signText[b].substring(0, 15);
/*    */     
/*    */     } 
/*    */   }
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 39 */     String[] arrayOfString = new String[4];
/* 40 */     System.arraycopy(this.signText, 0, arrayOfString, 0, 4);
/* 41 */     return new Packet130UpdateSign(this.xCoord, this.yCoord, this.zCoord, arrayOfString);
/*    */   }
/*    */   
/*    */   public boolean isEditable() {
/* 45 */     return this.isEditable;
/*    */   }
/*    */   
/*    */   public void setEditable(boolean bl) {
/* 49 */     this.isEditable = bl;
/* 50 */     if (!bl) {
/* 51 */       this.field_142011_d = null;
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_142010_a(EntityPlayer entityPlayer) {
/* 56 */     this.field_142011_d = entityPlayer;
/*    */   }
/*    */   
/*    */   public EntityPlayer func_142009_b() {
/* 60 */     return this.field_142011_d;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntitySign.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */