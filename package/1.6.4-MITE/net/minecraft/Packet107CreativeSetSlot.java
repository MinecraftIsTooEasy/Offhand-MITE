/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class Packet107CreativeSetSlot
/*    */   extends Packet
/*    */ {
/*    */   public int slot;
/*    */   public ItemStack itemStack;
/*    */   
/*    */   public Packet107CreativeSetSlot() {}
/*    */   
/*    */   public Packet107CreativeSetSlot(int par1, ItemStack par2ItemStack) {
/* 16 */     this.slot = par1;
/* 17 */     this.itemStack = (par2ItemStack != null) ? par2ItemStack.copy() : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 25 */     par1NetHandler.handleCreativeSetSlot(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 33 */     this.slot = par1DataInput.readShort();
/* 34 */     this.itemStack = readItemStack(par1DataInput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 42 */     par1DataOutput.writeShort(this.slot);
/* 43 */     writeItemStack(this.itemStack, par1DataOutput);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 52 */     return 2 + Packet.getPacketSizeOfItemStack(this.itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet107CreativeSetSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */