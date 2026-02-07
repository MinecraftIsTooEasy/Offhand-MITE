/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StatCollector
/*    */ {
/*  7 */   private static StringTranslate localizedName = StringTranslate.getInstance();
/*    */ 
/*    */   
/*    */   public static String translateToLocal(String string) {
/* 11 */     return localizedName.translateKey(string);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String translateToLocalFormatted(String string, Object... objects) {
/* 16 */     return localizedName.translateKeyFormat(string, objects);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean func_94522_b(String string) {
/* 21 */     return localizedName.containsTranslateKey(string);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */