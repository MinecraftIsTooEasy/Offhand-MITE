/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThreadDownloadImageData
/*    */   extends AbstractTexture
/*    */ {
/*    */   private final String imageUrl;
/*    */   private final IImageBuffer imageBuffer;
/*    */   private BufferedImage bufferedImage;
/*    */   private Thread imageThread;
/*    */   private SimpleTexture imageLocation;
/*    */   private boolean textureUploaded;
/*    */   
/*    */   public ThreadDownloadImageData(String string, ResourceLocation resourceLocation, IImageBuffer iImageBuffer) {
/* 25 */     this.imageUrl = string;
/* 26 */     this.imageBuffer = iImageBuffer;
/*    */     
/* 28 */     this.imageLocation = (resourceLocation != null) ? new SimpleTexture(resourceLocation) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGlTextureId() {
/* 33 */     int i = super.getGlTextureId();
/*    */     
/* 35 */     if (!this.textureUploaded && this.bufferedImage != null) {
/* 36 */       TextureUtil.uploadTextureImage(i, this.bufferedImage);
/* 37 */       this.textureUploaded = true;
/*    */     } 
/* 39 */     return i;
/*    */   }
/*    */   
/*    */   public void setBufferedImage(BufferedImage bufferedImage) {
/* 43 */     this.bufferedImage = bufferedImage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadTexture(ResourceManager resourceManager) {
/* 50 */     if (this.bufferedImage == null) {
/* 51 */       if (this.imageLocation != null) {
/* 52 */         this.imageLocation.loadTexture(resourceManager);
/* 53 */         this.glTextureId = this.imageLocation.getGlTextureId();
/*    */       } 
/*    */     } else {
/* 56 */       TextureUtil.uploadTextureImage(getGlTextureId(), this.bufferedImage);
/*    */     } 
/*    */     
/* 59 */     if (this.imageThread == null) {
/* 60 */       this.imageThread = new ThreadDownloadImageDataINNER1(this);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 89 */       this.imageThread.setDaemon(true);
/* 90 */       this.imageThread.setName("Skin downloader: " + this.imageUrl);
/* 91 */       this.imageThread.start();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isTextureUploaded() {
/* 96 */     getGlTextureId();
/* 97 */     return this.textureUploaded;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ThreadDownloadImageData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */