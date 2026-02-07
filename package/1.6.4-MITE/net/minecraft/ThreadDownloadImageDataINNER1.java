/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ class ThreadDownloadImageDataINNER1
/*    */   extends Thread
/*    */ {
/*    */   final ThreadDownloadImageData theThreadDownloadImageData;
/*    */   
/*    */   ThreadDownloadImageDataINNER1(ThreadDownloadImageData par1ThreadDownloadImageData) {
/* 14 */     this.theThreadDownloadImageData = par1ThreadDownloadImageData;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 19 */     HttpURLConnection var1 = null;
/*    */ 
/*    */     
/*    */     try {
/* 23 */       var1 = (HttpURLConnection)(new URL(ThreadDownloadImageData.getImageUrl(this.theThreadDownloadImageData))).openConnection(Minecraft.getMinecraft().getProxy());
/* 24 */       var1.setDoInput(true);
/* 25 */       var1.setDoOutput(false);
/* 26 */       var1.connect();
/*    */       
/* 28 */       if (var1.getResponseCode() / 100 == 2) {
/*    */         
/* 30 */         BufferedImage var2 = ImageIO.read(var1.getInputStream());
/*    */         
/* 32 */         if (ThreadDownloadImageData.getImageBuffer(this.theThreadDownloadImageData) != null)
/*    */         {
/* 34 */           var2 = ThreadDownloadImageData.getImageBuffer(this.theThreadDownloadImageData).parseUserSkin(var2);
/*    */         }
/*    */         
/* 37 */         this.theThreadDownloadImageData.setBufferedImage(var2);
/*    */         
/*    */         return;
/*    */       } 
/* 41 */     } catch (Exception var6) {
/*    */ 
/*    */       
/* 44 */       System.err.println("Unable to connect to " + var1.getURL());
/*    */ 
/*    */       
/*    */       return;
/*    */     } finally {
/* 49 */       if (var1 != null)
/*    */       {
/* 51 */         var1.disconnect();
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadDownloadImageDataINNER1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */