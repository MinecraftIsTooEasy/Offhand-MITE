/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet5PlayerInventory
/*    */   extends Packet
/*    */ {
/*    */   public int entityID;
/*    */   public int slot;
/*    */   private ItemStack itemSlot;
/*    */   public boolean full_inventory;
/*    */   
/*    */   public Packet5PlayerInventory() {}
/*    */   
/*    */   public Packet5PlayerInventory(int par1, int par2, ItemStack par3ItemStack) {
/* 24 */     this.entityID = par1;
/* 25 */     this.slot = par2;
/* 26 */     this.itemSlot = (par3ItemStack == null) ? null : par3ItemStack.copy();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 34 */     this.entityID = par1DataInput.readInt();
/* 35 */     this.slot = par1DataInput.readShort();
/* 36 */     this.full_inventory = par1DataInput.readBoolean();
/* 37 */     this.itemSlot = readItemStack(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 45 */     par1DataOutput.writeInt(this.entityID);
/* 46 */     par1DataOutput.writeShort(this.slot);
/* 47 */     par1DataOutput.writeBoolean(this.full_inventory);
/* 48 */     writeItemStack(this.itemSlot, par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 56 */     par1NetHandler.handlePlayerInventory(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 66 */     return 7 + Packet.getPacketSizeOfItemStack(this.itemSlot);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemSlot() {
/* 74 */     return this.itemSlot;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isRealPacket() {
/* 82 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsSameEntityIDAs(Packet par1Packet) {
/* 91 */     Packet5PlayerInventory var2 = (Packet5PlayerInventory)par1Packet;
/* 92 */     return (var2.entityID == this.entityID && var2.slot == this.slot);
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet5PlayerInventory setFullInventory() {
/* 97 */     this.full_inventory = true;
/* 98 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet5PlayerInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */