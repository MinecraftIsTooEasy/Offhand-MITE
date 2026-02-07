/*     */ package net.minecraft;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Key;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.KeyPair;
/*     */ import java.security.KeyPairGenerator;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.Provider;
/*     */ import java.security.PublicKey;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.Security;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.bouncycastle.crypto.BlockCipher;
/*     */ import org.bouncycastle.crypto.BufferedBlockCipher;
/*     */ import org.bouncycastle.crypto.CipherKeyGenerator;
/*     */ import org.bouncycastle.crypto.CipherParameters;
/*     */ import org.bouncycastle.crypto.engines.AESFastEngine;
/*     */ import org.bouncycastle.crypto.io.CipherInputStream;
/*     */ import org.bouncycastle.crypto.io.CipherOutputStream;
/*     */ import org.bouncycastle.crypto.modes.CFBBlockCipher;
/*     */ import org.bouncycastle.crypto.params.KeyParameter;
/*     */ import org.bouncycastle.crypto.params.ParametersWithIV;
/*     */ import org.bouncycastle.jce.provider.BouncyCastleProvider;
/*     */ 
/*     */ public class CryptManager {
/*     */   static {
/*  39 */     Security.addProvider((Provider)new BouncyCastleProvider());
/*     */   }
/*     */   
/*     */   public static SecretKey createNewSharedKey() {
/*  43 */     CipherKeyGenerator cipherKeyGenerator = new CipherKeyGenerator();
/*  44 */     cipherKeyGenerator.init(new KeyGenerationParameters(new SecureRandom(), 128));
/*     */     
/*  46 */     return new SecretKeySpec(cipherKeyGenerator.generateKey(), "AES");
/*     */   }
/*     */   
/*     */   public static KeyPair createNewKeyPair() {
/*     */     try {
/*  51 */       KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
/*  52 */       keyPairGenerator.initialize(1024);
/*     */       
/*  54 */       return keyPairGenerator.generateKeyPair();
/*  55 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  56 */       noSuchAlgorithmException.printStackTrace();
/*     */       
/*  58 */       System.err.println("Key pair generation failed!");
/*  59 */       return null;
/*     */     } 
/*     */   }
/*     */   public static byte[] getServerIdHash(String string, PublicKey publicKey, SecretKey secretKey) {
/*     */     try {
/*  64 */       return digestOperation("SHA-1", new byte[][] { string.getBytes("ISO_8859_1"), secretKey.getEncoded(), publicKey.getEncoded() });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  70 */     catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  71 */       unsupportedEncodingException.printStackTrace();
/*     */ 
/*     */       
/*  74 */       return null;
/*     */     } 
/*     */   }
/*     */   private static byte[] digestOperation(String string, byte[]... bs) {
/*     */     try {
/*  79 */       MessageDigest messageDigest = MessageDigest.getInstance(string);
/*  80 */       for (byte[] arrayOfByte : bs) {
/*  81 */         messageDigest.update(arrayOfByte);
/*     */       }
/*  83 */       return messageDigest.digest();
/*  84 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  85 */       noSuchAlgorithmException.printStackTrace();
/*     */ 
/*     */       
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */   public static PublicKey decodePublicKey(byte[] bs) {
/*     */     try {
/*  93 */       X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bs);
/*  94 */       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
/*  95 */       return keyFactory.generatePublic(x509EncodedKeySpec);
/*  96 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  97 */       noSuchAlgorithmException.printStackTrace();
/*  98 */     } catch (InvalidKeySpecException invalidKeySpecException) {
/*  99 */       invalidKeySpecException.printStackTrace();
/*     */     } 
/* 101 */     System.err.println("Public key reconstitute failed!");
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public static SecretKey decryptSharedKey(PrivateKey privateKey, byte[] bs) {
/* 106 */     return new SecretKeySpec(decryptData(privateKey, bs), "AES");
/*     */   }
/*     */   
/*     */   public static byte[] encryptData(Key key, byte[] bs) {
/* 110 */     return cipherOperation(1, key, bs);
/*     */   }
/*     */   
/*     */   public static byte[] decryptData(Key key, byte[] bs) {
/* 114 */     return cipherOperation(2, key, bs);
/*     */   }
/*     */   
/*     */   private static byte[] cipherOperation(int i, Key key, byte[] bs) {
/*     */     try {
/* 119 */       return createTheCipherInstance(i, key.getAlgorithm(), key).doFinal(bs);
/* 120 */     } catch (IllegalBlockSizeException illegalBlockSizeException) {
/* 121 */       illegalBlockSizeException.printStackTrace();
/* 122 */     } catch (BadPaddingException badPaddingException) {
/* 123 */       badPaddingException.printStackTrace();
/*     */     } 
/* 125 */     System.err.println("Cipher data failed!");
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   private static Cipher createTheCipherInstance(int i, String string, Key key) {
/*     */     try {
/* 131 */       Cipher cipher = Cipher.getInstance(string);
/* 132 */       cipher.init(i, key);
/* 133 */       return cipher;
/* 134 */     } catch (InvalidKeyException invalidKeyException) {
/* 135 */       invalidKeyException.printStackTrace();
/* 136 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 137 */       noSuchAlgorithmException.printStackTrace();
/* 138 */     } catch (NoSuchPaddingException noSuchPaddingException) {
/* 139 */       noSuchPaddingException.printStackTrace();
/*     */     } 
/* 141 */     System.err.println("Cipher creation failed!");
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   private static BufferedBlockCipher createBufferedBlockCipher(boolean bl, Key key) {
/* 146 */     BufferedBlockCipher bufferedBlockCipher = new BufferedBlockCipher((BlockCipher)new CFBBlockCipher((BlockCipher)new AESFastEngine(), 8));
/* 147 */     bufferedBlockCipher.init(bl, (CipherParameters)new ParametersWithIV((CipherParameters)new KeyParameter(key.getEncoded()), key.getEncoded(), 0, 16));
/* 148 */     return bufferedBlockCipher;
/*     */   }
/*     */   
/*     */   public static OutputStream encryptOuputStream(SecretKey secretKey, OutputStream outputStream) {
/* 152 */     return (OutputStream)new CipherOutputStream(outputStream, createBufferedBlockCipher(true, secretKey));
/*     */   }
/*     */   
/*     */   public static InputStream decryptInputStream(SecretKey secretKey, InputStream inputStream) {
/* 156 */     return (InputStream)new CipherInputStream(inputStream, createBufferedBlockCipher(false, secretKey));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CryptManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */