/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ public class GrassColorReloadListener
/*    */   implements ResourceManagerReloadListener
/*    */ {
/*  9 */   private static final ResourceLocation field_130078_a = new ResourceLocation("textures/colormap/grass.png");
/*    */ 
/*    */   
/*    */   public void onResourceManagerReload(ResourceManager resourceManager) {
/*    */     try {
/* 14 */       ColorizerGrass.setGrassBiomeColorizer(TextureUtil.readImageData(resourceManager, field_130078_a));
/* 15 */     } catch (IOException iOException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GrassColorReloadListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */