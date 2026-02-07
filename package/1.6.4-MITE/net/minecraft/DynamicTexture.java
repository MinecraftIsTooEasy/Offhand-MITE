/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ public class DynamicTexture
/*    */   extends AbstractTexture
/*    */ {
/*    */   private final int[] dynamicTextureData;
/*    */   private final int width;
/*    */   private final int height;
/*    */   
/*    */   public DynamicTexture(BufferedImage bufferedImage) {
/* 14 */     this(bufferedImage.getWidth(), bufferedImage.getHeight());
/*    */     
/* 16 */     bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.dynamicTextureData, 0, bufferedImage.getWidth());
/*    */     
/* 18 */     updateDynamicTexture();
/*    */   }
/*    */   
/*    */   public DynamicTexture(int i, int j) {
/* 22 */     this.width = i;
/* 23 */     this.height = j;
/* 24 */     this.dynamicTextureData = new int[i * j];
/*    */     
/* 26 */     TextureUtil.allocateTexture(getGlTextureId(), i, j);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadTexture(ResourceManager resourceManager) {}
/*    */ 
/*    */   
/*    */   public void updateDynamicTexture() {
/* 35 */     TextureUtil.uploadTexture(getGlTextureId(), this.dynamicTextureData, this.width, this.height);
/*    */   }
/*    */   
/*    */   public int[] getTextureData() {
/* 39 */     return this.dynamicTextureData;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DynamicTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */