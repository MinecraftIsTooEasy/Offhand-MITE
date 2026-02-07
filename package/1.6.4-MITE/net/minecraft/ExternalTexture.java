/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ExternalTexture
/*    */   extends AbstractTexture
/*    */ {
/*    */   private final File file;
/*    */   
/*    */   public ExternalTexture(File file) {
/* 23 */     this.file = file;
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture(ResourceManager par1ResourceManager) throws IOException {
/* 28 */     InputStream var2 = null;
/*    */ 
/*    */     
/*    */     try {
/* 32 */       FileInputStream fis = new FileInputStream(this.file);
/*    */       
/* 34 */       var2 = fis;
/*    */       
/* 36 */       BufferedImage var4 = ImageIO.read(var2);
/* 37 */       boolean var5 = false;
/* 38 */       boolean var6 = false;
/*    */       
/* 40 */       TextureUtil.uploadTextureImageAllocate(getGlTextureId(), var4, var5, var6);
/*    */     }
/*    */     finally {
/*    */       
/* 44 */       if (var2 != null)
/*    */       {
/* 46 */         var2.close();
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ExternalTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */