/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class I18n
/*    */ {
/*    */   private static Locale i18nLocale;
/*    */   
/*    */   static void setLocale(Locale locale) {
/* 11 */     i18nLocale = locale;
/*    */   }
/*    */   
/*    */   public static String getString(String string) {
/* 15 */     return i18nLocale.translateKey(string);
/*    */   }
/*    */   
/*    */   public static String getStringParams(String string, Object... objects) {
/* 19 */     return i18nLocale.formatMessage(string, objects);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\I18n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */