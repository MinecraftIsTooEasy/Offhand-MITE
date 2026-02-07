/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.security.PublicKey;
/*    */ 
/*    */ 
/*    */ public class Packet253ServerAuthData
/*    */   extends Packet
/*    */ {
/*    */   private String serverId;
/*    */   private PublicKey publicKey;
/* 13 */   private byte[] verifyToken = new byte[0];
/*    */ 
/*    */   
/*    */   public Packet253ServerAuthData() {}
/*    */ 
/*    */   
/*    */   public Packet253ServerAuthData(String string, PublicKey publicKey, byte[] bs) {
/* 20 */     this.serverId = string;
/* 21 */     this.publicKey = publicKey;
/* 22 */     this.verifyToken = bs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 27 */     this.serverId = readString(dataInput, 20);
/* 28 */     this.publicKey = CryptManager.decodePublicKey(readBytesFromStream(dataInput));
/* 29 */     this.verifyToken = readBytesFromStream(dataInput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 34 */     writeString(this.serverId, dataOutput);
/* 35 */     writeByteArray(dataOutput, this.publicKey.getEncoded());
/* 36 */     writeByteArray(dataOutput, this.verifyToken);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 41 */     netHandler.handleServerAuthData(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 46 */     return 2 + this.serverId.length() * 2 + 2 + (this.publicKey.getEncoded()).length + 2 + this.verifyToken.length;
/*    */   }
/*    */   
/*    */   public String getServerId() {
/* 50 */     return this.serverId;
/*    */   }
/*    */   
/*    */   public PublicKey getPublicKey() {
/* 54 */     return this.publicKey;
/*    */   }
/*    */   
/*    */   public byte[] getVerifyToken() {
/* 58 */     return this.verifyToken;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet253ServerAuthData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */