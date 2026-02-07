/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet104WindowItems
/*    */   extends Packet
/*    */ {
/*    */   public int windowId;
/*    */   public ItemStack[] itemStack;
/*    */   
/*    */   public Packet104WindowItems() {}
/*    */   
/*    */   public Packet104WindowItems(int par1, List<ItemStack> par2List) {
/* 22 */     this.windowId = par1;
/* 23 */     this.itemStack = new ItemStack[par2List.size()];
/*    */     
/* 25 */     for (int var3 = 0; var3 < this.itemStack.length; var3++) {
/*    */       
/* 27 */       ItemStack var4 = par2List.get(var3);
/* 28 */       this.itemStack[var3] = (var4 == null) ? null : var4.copy();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 37 */     this.windowId = par1DataInput.readByte();
/* 38 */     short var2 = par1DataInput.readShort();
/* 39 */     this.itemStack = new ItemStack[var2];
/*    */     
/* 41 */     for (int var3 = 0; var3 < var2; var3++)
/*    */     {
/* 43 */       this.itemStack[var3] = readItemStack(par1DataInput);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 52 */     par1DataOutput.writeByte(this.windowId);
/* 53 */     par1DataOutput.writeShort(this.itemStack.length);
/*    */     
/* 55 */     for (int var2 = 0; var2 < this.itemStack.length; var2++)
/*    */     {
/* 57 */       writeItemStack(this.itemStack[var2], par1DataOutput);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler par1NetHandler) {
/* 66 */     par1NetHandler.handleWindowItems(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 76 */     int bytes = 3;
/*    */     
/* 78 */     for (int i = 0; i < this.itemStack.length; i++) {
/* 79 */       bytes += Packet.getPacketSizeOfItemStack(this.itemStack[i]);
/*    */     }
/* 81 */     return bytes;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet104WindowItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */