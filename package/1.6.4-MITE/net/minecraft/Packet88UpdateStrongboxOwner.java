/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Packet88UpdateStrongboxOwner
/*    */   extends Packet
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */   private String owner_name;
/*    */   
/*    */   public Packet88UpdateStrongboxOwner() {}
/*    */   
/*    */   public Packet88UpdateStrongboxOwner(int x, int y, int z, String owner_name) {
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.z = z;
/*    */     
/* 24 */     this.owner_name = (owner_name == null) ? "" : owner_name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput par1DataInput) throws IOException {
/* 29 */     this.x = par1DataInput.readInt();
/* 30 */     this.y = par1DataInput.readInt();
/* 31 */     this.z = par1DataInput.readInt();
/*    */ 
/*    */     
/* 34 */     this.owner_name = readString(par1DataInput, 32);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 39 */     par1DataOutput.writeInt(this.x);
/* 40 */     par1DataOutput.writeInt(this.y);
/* 41 */     par1DataOutput.writeInt(this.z);
/*    */     
/* 43 */     writeString(this.owner_name, par1DataOutput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler net_handler) {
/* 48 */     net_handler.handleUpdateStrongboxOwner(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 53 */     return 12 + this.owner_name.length();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOwnerName() {
/* 58 */     return this.owner_name.equals("") ? null : this.owner_name;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet88UpdateStrongboxOwner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */