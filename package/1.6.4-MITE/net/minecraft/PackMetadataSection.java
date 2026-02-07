/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PackMetadataSection
/*    */   implements MetadataSection
/*    */ {
/*    */   private final String packDescription;
/*    */   private final int packFormat;
/*    */   
/*    */   public PackMetadataSection(String string, int i) {
/* 12 */     this.packDescription = string;
/* 13 */     this.packFormat = i;
/*    */   }
/*    */   
/*    */   public String getPackDescription() {
/* 17 */     return this.packDescription;
/*    */   }
/*    */   
/*    */   public int getPackFormat() {
/* 21 */     return this.packFormat;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PackMetadataSection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */