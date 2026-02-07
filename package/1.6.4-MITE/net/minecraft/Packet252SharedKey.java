/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.security.PrivateKey;
/*    */ import java.security.PublicKey;
/*    */ import javax.crypto.SecretKey;
/*    */ 
/*    */ public class Packet252SharedKey
/*    */   extends Packet {
/* 11 */   private byte[] sharedSecret = new byte[0];
/* 12 */   private byte[] verifyToken = new byte[0];
/*    */   
/*    */   private SecretKey sharedKey;
/*    */ 
/*    */   
/*    */   public Packet252SharedKey() {}
/*    */ 
/*    */   
/*    */   public Packet252SharedKey(SecretKey secretKey, PublicKey publicKey, byte[] bs) {
/* 21 */     this.sharedKey = secretKey;
/* 22 */     this.sharedSecret = CryptManager.encryptData(publicKey, secretKey.getEncoded());
/* 23 */     this.verifyToken = CryptManager.encryptData(publicKey, bs);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readPacketData(DataInput dataInput) {
/* 28 */     this.sharedSecret = readBytesFromStream(dataInput);
/* 29 */     this.verifyToken = readBytesFromStream(dataInput);
/*    */   }
/*    */ 
/*    */   
/*    */   public void writePacketData(DataOutput dataOutput) {
/* 34 */     writeByteArray(dataOutput, this.sharedSecret);
/* 35 */     writeByteArray(dataOutput, this.verifyToken);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processPacket(NetHandler netHandler) {
/* 40 */     netHandler.handleSharedKey(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPacketSize() {
/* 45 */     return 2 + this.sharedSecret.length + 2 + this.verifyToken.length;
/*    */   }
/*    */   
/*    */   public SecretKey getSharedKey(PrivateKey privateKey) {
/* 49 */     if (privateKey == null) {
/* 50 */       return this.sharedKey;
/*    */     }
/* 52 */     return this.sharedKey = CryptManager.decryptSharedKey(privateKey, this.sharedSecret);
/*    */   }
/*    */   
/*    */   public SecretKey getSharedKey() {
/* 56 */     return getSharedKey((PrivateKey)null);
/*    */   }
/*    */   
/*    */   public byte[] getVerifyToken(PrivateKey privateKey) {
/* 60 */     if (privateKey == null) {
/* 61 */       return this.verifyToken;
/*    */     }
/* 63 */     return CryptManager.decryptData(privateKey, this.verifyToken);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet252SharedKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */