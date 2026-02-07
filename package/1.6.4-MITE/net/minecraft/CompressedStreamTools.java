/*    */ package net.minecraft;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ 
/*    */ public class CompressedStreamTools {
/*    */   public static NBTTagCompound readCompressed(InputStream inputStream) {
/*  9 */     DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputStream)));
/*    */     try {
/* 11 */       return read(dataInputStream);
/*    */     } finally {
/* 13 */       dataInputStream.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void writeCompressed(NBTTagCompound nBTTagCompound, OutputStream outputStream) {
/* 18 */     DataOutputStream dataOutputStream = new DataOutputStream(new GZIPOutputStream(outputStream));
/*    */     try {
/* 20 */       write(nBTTagCompound, dataOutputStream);
/*    */     } finally {
/* 22 */       dataOutputStream.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static NBTTagCompound decompress(byte[] bs) {
/* 27 */     DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bs))));
/*    */     try {
/* 29 */       return read(dataInputStream);
/*    */     } finally {
/* 31 */       dataInputStream.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static byte[] compress(NBTTagCompound nBTTagCompound) {
/* 36 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 37 */     DataOutputStream dataOutputStream = new DataOutputStream(new GZIPOutputStream(byteArrayOutputStream));
/*    */     try {
/* 39 */       write(nBTTagCompound, dataOutputStream);
/*    */     } finally {
/* 41 */       dataOutputStream.close();
/*    */     } 
/* 43 */     return byteArrayOutputStream.toByteArray();
/*    */   }
/*    */   
/*    */   public static void safeWrite(NBTTagCompound nBTTagCompound, File file) {
/* 47 */     File file1 = new File(file.getAbsolutePath() + "_tmp");
/* 48 */     if (file1.exists()) file1.delete(); 
/* 49 */     write(nBTTagCompound, file1);
/* 50 */     if (file.exists()) file.delete(); 
/* 51 */     if (file.exists()) throw new IOException("Failed to delete " + file); 
/* 52 */     file1.renameTo(file);
/*    */   }
/*    */   
/*    */   public static void write(NBTTagCompound nBTTagCompound, File file) {
/* 56 */     DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
/*    */     try {
/* 58 */       write(nBTTagCompound, dataOutputStream);
/*    */     } finally {
/* 60 */       dataOutputStream.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static NBTTagCompound read(File file) {
/* 65 */     if (!file.exists()) return null; 
/* 66 */     DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
/*    */     try {
/* 68 */       return read(dataInputStream);
/*    */     } finally {
/* 70 */       dataInputStream.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static NBTTagCompound read(DataInput dataInput) {
/* 75 */     NBTBase nBTBase = NBTBase.readNamedTag(dataInput);
/* 76 */     if (nBTBase instanceof NBTTagCompound) return (NBTTagCompound)nBTBase; 
/* 77 */     throw new IOException("Root tag must be a named compound tag");
/*    */   }
/*    */   
/*    */   public static void write(NBTTagCompound nBTTagCompound, DataOutput dataOutput) {
/* 81 */     NBTBase.writeNamedTag(nBTTagCompound, dataOutput);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CompressedStreamTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */