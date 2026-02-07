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
/*    */ public class Packet103SetSlot
/*    */   extends Packet
/*    */ {
/*    */   public int windowId;
/*    */   public int itemSlot;
/*    */   public ItemStack myItemStack;
/*    */   
/*    */   public Packet103SetSlot() {}
/*    */   
/*    */   public Packet103SetSlot(int par1, int par2, ItemStack par3ItemStack) {
/* 22 */     this.windowId = par1;
/* 23 */     this.itemSlot = par2;
/* 24 */     this.myItemStack = (par3ItemStack == null) ? par3ItemStack : par3ItemStack.copy();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 32 */     par1NetHandler.handleSetSlot(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 40 */     this.windowId = par1DataInput.readByte();
/* 41 */     this.itemSlot = par1DataInput.readShort();
/* 42 */     this.myItemStack = readItemStack(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 50 */     par1DataOutput.writeByte(this.windowId);
/* 51 */     par1DataOutput.writeShort(this.itemSlot);
/* 52 */     writeItemStack(this.myItemStack, par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 61 */     return 3 + Packet.getPacketSizeOfItemStack(this.myItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet103SetSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */