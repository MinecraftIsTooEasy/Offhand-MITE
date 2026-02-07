/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.DataBufferInt;
/*    */ 
/*    */ public class ImageBufferDownload
/*    */   implements IImageBuffer {
/*    */   private int[] imageData;
/*    */   
/*    */   public BufferedImage parseUserSkin(BufferedImage bufferedImage) {
/* 12 */     if (bufferedImage == null) return null;
/*    */     
/* 14 */     this.imageWidth = 64;
/* 15 */     this.imageHeight = 32;
/*    */     
/* 17 */     BufferedImage bufferedImage1 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
/* 18 */     Graphics graphics = bufferedImage1.getGraphics();
/* 19 */     graphics.drawImage(bufferedImage, 0, 0, null);
/* 20 */     graphics.dispose();
/*    */     
/* 22 */     this.imageData = ((DataBufferInt)bufferedImage1.getRaster().getDataBuffer()).getData();
/*    */     
/* 24 */     setAreaOpaque(0, 0, 32, 16);
/* 25 */     setAreaTransparent(32, 0, 64, 32);
/* 26 */     setAreaOpaque(0, 16, 64, 32);
/*    */     
/* 28 */     return bufferedImage1;
/*    */   }
/*    */   private int imageWidth; private int imageHeight;
/*    */   private void setAreaTransparent(int i, int j, int k, int l) {
/* 32 */     if (hasTransparency(i, j, k, l))
/*    */       return; 
/* 34 */     for (int m = i; m < k; m++) {
/* 35 */       for (int n = j; n < l; n++) {
/* 36 */         this.imageData[m + n * this.imageWidth] = this.imageData[m + n * this.imageWidth] & 0xFFFFFF;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setAreaOpaque(int i, int j, int k, int l) {
/* 42 */     for (int m = i; m < k; m++) {
/* 43 */       for (int n = j; n < l; n++) {
/* 44 */         this.imageData[m + n * this.imageWidth] = this.imageData[m + n * this.imageWidth] | 0xFF000000;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean hasTransparency(int i, int j, int k, int l) {
/* 50 */     for (int m = i; m < k; m++) {
/* 51 */       for (int n = j; n < l; n++) {
/* 52 */         int i1 = this.imageData[m + n * this.imageWidth];
/* 53 */         if ((i1 >> 24 & 0xFF) < 128) return true; 
/*    */       } 
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ImageBufferDownload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */