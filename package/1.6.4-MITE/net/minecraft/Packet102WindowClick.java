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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet102WindowClick
/*    */   extends Packet
/*    */ {
/*    */   public int window_Id;
/*    */   public int inventorySlot;
/*    */   public int mouseClick;
/*    */   public short action;
/*    */   public ItemStack itemStack;
/*    */   public int holdingShift;
/*    */   public boolean holding_shift;
/*    */   
/*    */   public Packet102WindowClick() {}
/*    */   
/*    */   public Packet102WindowClick(int par1, int par2, int par3, int par4, ItemStack par5ItemStack, short par6) {
/* 31 */     this.window_Id = par1;
/* 32 */     this.inventorySlot = par2;
/* 33 */     this.mouseClick = par3;
/* 34 */     this.itemStack = (par5ItemStack != null) ? par5ItemStack.copy() : null;
/* 35 */     this.action = par6;
/* 36 */     this.holdingShift = par4;
/*    */   }
/*    */ 
/*    */   
/*    */   public Packet102WindowClick setHoldingShift(boolean holding_shift) {
/* 41 */     this.holding_shift = holding_shift;
/* 42 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 50 */     par1NetHandler.handleWindowClick(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 58 */     this.window_Id = par1DataInput.readByte();
/* 59 */     this.inventorySlot = par1DataInput.readShort();
/* 60 */     this.mouseClick = par1DataInput.readByte();
/* 61 */     this.action = par1DataInput.readShort();
/* 62 */     this.holdingShift = par1DataInput.readByte();
/* 63 */     this.holding_shift = par1DataInput.readBoolean();
/* 64 */     this.itemStack = readItemStack(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 72 */     par1DataOutput.writeByte(this.window_Id);
/* 73 */     par1DataOutput.writeShort(this.inventorySlot);
/* 74 */     par1DataOutput.writeByte(this.mouseClick);
/* 75 */     par1DataOutput.writeShort(this.action);
/* 76 */     par1DataOutput.writeByte(this.holdingShift);
/* 77 */     par1DataOutput.writeBoolean(this.holding_shift);
/* 78 */     writeItemStack(this.itemStack, par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 88 */     return 8 + Packet.getPacketSizeOfItemStack(this.itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet102WindowClick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */