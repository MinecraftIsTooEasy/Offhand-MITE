/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.nio.IntBuffer;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import javax.imageio.ImageIO;
/*    */ import org.lwjgl.BufferUtils;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScreenShotHelper
/*    */ {
/* 22 */   private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
/*    */   private static IntBuffer field_74293_b;
/*    */   private static int[] field_74294_c;
/*    */   
/*    */   public static String saveScreenshot(File file, int i, int j) {
/* 27 */     return func_74292_a(file, null, i, j);
/*    */   }
/*    */   
/*    */   public static String func_74292_a(File file, String string, int i, int j) {
/*    */     try {
/* 32 */       File file2, file1 = new File(file, "screenshots");
/* 33 */       file1.mkdir();
/*    */       
/* 35 */       int k = i * j;
/* 36 */       if (field_74293_b == null || field_74293_b.capacity() < k) {
/* 37 */         field_74293_b = BufferUtils.createIntBuffer(k);
/* 38 */         field_74294_c = new int[k];
/*    */       } 
/*    */       
/* 41 */       GL11.glPixelStorei(3333, 1);
/* 42 */       GL11.glPixelStorei(3317, 1);
/*    */       
/* 44 */       field_74293_b.clear();
/* 45 */       GL11.glReadPixels(0, 0, i, j, 32993, 33639, field_74293_b);
/*    */       
/* 47 */       field_74293_b.get(field_74294_c);
/*    */       
/* 49 */       func_74289_a(field_74294_c, i, j);
/*    */       
/* 51 */       BufferedImage bufferedImage = new BufferedImage(i, j, 1);
/* 52 */       bufferedImage.setRGB(0, 0, i, j, field_74294_c, 0, i);
/*    */ 
/*    */       
/* 55 */       if (string == null) {
/* 56 */         file2 = func_74290_a(file1);
/*    */       } else {
/* 58 */         file2 = new File(file1, string);
/*    */       } 
/*    */       
/* 61 */       ImageIO.write(bufferedImage, "png", file2);
/*    */       
/* 63 */       return "Saved screenshot as " + file2.getName();
/* 64 */     } catch (Exception exception) {
/* 65 */       exception.printStackTrace();
/* 66 */       return "Failed to save: " + exception;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static File func_74290_a(File file) {
/* 72 */     String str = dateFormat.format(new Date()).toString();
/*    */     
/* 74 */     for (byte b = 1;; b++) {
/* 75 */       File file1 = new File(file, str + ((b == 1) ? "" : ("_" + b)) + ".png");
/* 76 */       if (!file1.exists()) {
/* 77 */         return file1;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void func_74289_a(int[] is, int i, int j) {
/* 83 */     int[] arrayOfInt = new int[i];
/* 84 */     int k = j / 2;
/* 85 */     for (byte b = 0; b < k; b++) {
/* 86 */       System.arraycopy(is, b * i, arrayOfInt, 0, i);
/* 87 */       System.arraycopy(is, (j - 1 - b) * i, is, b * i, i);
/* 88 */       System.arraycopy(arrayOfInt, 0, is, (j - 1 - b) * i, i);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ScreenShotHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */