/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityStrongbox
/*    */   extends TileEntityChest
/*    */ {
/*    */   public String owner_name;
/*    */   
/*    */   public TileEntityStrongbox() {}
/*    */   
/*    */   public TileEntityStrongbox(EnumChestType chest_type, Block block) {
/* 16 */     super(chest_type, block);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 21 */     super.readFromNBT(par1NBTTagCompound);
/*    */     
/* 23 */     if (par1NBTTagCompound.hasKey("owner_name")) {
/* 24 */       this.owner_name = par1NBTTagCompound.getString("owner_name");
/*    */     }
/*    */   }
/*    */   
/*    */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 29 */     super.writeToNBT(par1NBTTagCompound);
/*    */     
/* 31 */     if (this.owner_name != null) {
/* 32 */       par1NBTTagCompound.setString("owner_name", this.owner_name);
/*    */     }
/*    */   }
/*    */   
/*    */   public Packet getDescriptionPacket() {
/* 37 */     return new Packet88UpdateStrongboxOwner(this.xCoord, this.yCoord, this.zCoord, this.owner_name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOwner(EntityPlayer player) {
/* 44 */     this.owner_name = player.username;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOwner(EntityPlayer player) {
/* 49 */     return player.username.equals(this.owner_name);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUnlocalizedInvName() {
/* 54 */     return "container.strongbox." + (getBlockMaterial()).name;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityStrongbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */