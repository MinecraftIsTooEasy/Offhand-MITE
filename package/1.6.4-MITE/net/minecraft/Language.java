/*    */ package net.minecraft;
/*    */ 
/*    */ public class Language implements Comparable {
/*    */   private final String languageCode;
/*    */   private final String region;
/*    */   private final String name;
/*    */   private final boolean bidirectional;
/*    */   
/*    */   public Language(String string, String string2, String string3, boolean bl) {
/* 10 */     this.languageCode = string;
/* 11 */     this.region = string2;
/* 12 */     this.name = string3;
/* 13 */     this.bidirectional = bl;
/*    */   }
/*    */   
/*    */   public String getLanguageCode() {
/* 17 */     return this.languageCode;
/*    */   }
/*    */   
/*    */   public boolean isBidirectional() {
/* 21 */     return this.bidirectional;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return String.format("%s (%s)", new Object[] { this.name, this.region });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 32 */     if (this == object) return true; 
/* 33 */     if (!(object instanceof Language)) return false;
/*    */     
/* 35 */     return this.languageCode.equals(((Language)object).languageCode);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 40 */     return this.languageCode.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_135033_a(Language language) {
/* 45 */     return this.languageCode.compareTo(language.languageCode);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Language.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */