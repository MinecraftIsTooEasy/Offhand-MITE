/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public final class TextureMetadataSection
/*    */   implements MetadataSection
/*    */ {
/*    */   private final boolean textureBlur;
/*    */   private final boolean textureClamp;
/*    */   
/*    */   public TextureMetadataSection(boolean par1, boolean par2) {
/* 11 */     this.textureBlur = par1;
/* 12 */     this.textureClamp = par2;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getTextureBlur() {
/* 17 */     return this.textureBlur;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getTextureClamp() {
/* 22 */     return this.textureClamp;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureMetadataSection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */