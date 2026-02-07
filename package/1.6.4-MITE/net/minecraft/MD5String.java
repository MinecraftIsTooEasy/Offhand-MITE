/*    */ package net.minecraft;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public class MD5String {
/*    */   private String salt;
/*    */   
/*    */   public MD5String(String string) {
/* 11 */     this.salt = string;
/*    */   }
/*    */   
/*    */   public String getMD5String(String string) {
/*    */     try {
/* 16 */       String str = this.salt + string;
/*    */       
/* 18 */       MessageDigest messageDigest = MessageDigest.getInstance("MD5");
/* 19 */       messageDigest.update(str.getBytes(), 0, str.length());
/* 20 */       return (new BigInteger(1, messageDigest.digest())).toString(16);
/* 21 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 22 */       throw new RuntimeException(noSuchAlgorithmException);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MD5String.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */