/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.List;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LayeredTexture
/*    */   extends AbstractTexture
/*    */ {
/*    */   public final List layeredTextureNames;
/*    */   
/*    */   public LayeredTexture(String... strings) {
/* 19 */     this.layeredTextureNames = Lists.newArrayList((Object[])strings);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture(ResourceManager resourceManager) {
/* 24 */     BufferedImage bufferedImage = null;
/*    */     
/*    */     try {
/* 27 */       for (String str : this.layeredTextureNames) {
/* 28 */         if (str == null) {
/*    */           continue;
/*    */         }
/* 31 */         InputStream inputStream = resourceManager.getResource(new ResourceLocation(str)).getInputStream();
/* 32 */         BufferedImage bufferedImage1 = ImageIO.read(inputStream);
/*    */ 
/*    */         
/* 35 */         if (bufferedImage == null) {
/* 36 */           bufferedImage = new BufferedImage(bufferedImage1.getWidth(), bufferedImage1.getHeight(), 2);
/*    */         }
/* 38 */         bufferedImage.getGraphics().drawImage(bufferedImage1, 0, 0, null);
/*    */       } 
/* 40 */     } catch (IOException iOException) {
/* 41 */       iOException.printStackTrace();
/*    */       
/*    */       return;
/*    */     } 
/* 45 */     TextureUtil.uploadTextureImage(getGlTextureId(), bufferedImage);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LayeredTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */