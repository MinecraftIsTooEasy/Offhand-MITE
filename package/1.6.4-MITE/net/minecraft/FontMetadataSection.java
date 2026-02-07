/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FontMetadataSection
/*    */   implements MetadataSection
/*    */ {
/*    */   private final float[] charWidths;
/*    */   private final float[] charLefts;
/*    */   private final float[] charSpacings;
/*    */   
/*    */   public FontMetadataSection(float[] fs, float[] gs, float[] hs) {
/* 18 */     this.charWidths = fs;
/* 19 */     this.charLefts = gs;
/* 20 */     this.charSpacings = hs;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FontMetadataSection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */