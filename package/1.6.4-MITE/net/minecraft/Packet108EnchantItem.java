/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ 
/*    */ public class Packet108EnchantItem extends Packet {
/*    */   public int windowId;
/*    */   public int enchantment;
/*    */   
/*    */   public Packet108EnchantItem() {}
/*    */   
/*    */   public Packet108EnchantItem(int i, int j) {
/* 13 */     this.windowId = i;
/* 14 */     this.enchantment = j;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 19 */     netHandler.handleEnchantItem(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 24 */     this.windowId = dataInput.readByte();
/* 25 */     this.enchantment = dataInput.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 30 */     dataOutput.writeByte(this.windowId);
/* 31 */     dataOutput.writeByte(this.enchantment);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 36 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet108EnchantItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */