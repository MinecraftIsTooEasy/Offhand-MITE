/*    */ package net.minecraft;
/*    */ 
/*    */ public abstract class AbstractTexture implements TextureObject {
/*  4 */   protected int glTextureId = -1;
/*    */ 
/*    */   
/*    */   public int getGlTextureId() {
/*  8 */     if (this.glTextureId == -1) {
/*  9 */       this.glTextureId = TextureUtil.glGenTextures();
/*    */     }
/*    */     
/* 12 */     return this.glTextureId;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AbstractTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */