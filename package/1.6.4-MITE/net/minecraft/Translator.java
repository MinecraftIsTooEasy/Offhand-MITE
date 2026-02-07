/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Translator
/*    */ {
/*    */   public static String get(String key) {
/* 11 */     return StatCollector.translateToLocal(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getFormatted(String key, Object... par1ArrayOfObj) {
/* 16 */     return StatCollector.translateToLocalFormatted(key, par1ArrayOfObj);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addToList(EnumChatFormatting enum_chat_formatting, String key, List<String> list) {
/* 22 */     String s = get(key);
/*    */     
/* 24 */     if (s.contains("|")) {
/*    */       
/* 26 */       String[] arr = StringHelper.explode(s, "\\|");
/*    */       
/* 28 */       for (int i = 0; i < arr.length; i++) {
/* 29 */         list.add((enum_chat_formatting == null) ? arr[i] : (enum_chat_formatting + arr[i]));
/*    */       }
/*    */     } else {
/*    */       
/* 33 */       list.add((enum_chat_formatting == null) ? s : (enum_chat_formatting + s));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addToListFormatted(EnumChatFormatting enum_chat_formatting, String key, List<String> list, Object... objects) {
/* 40 */     String s = getFormatted(key, objects);
/*    */     
/* 42 */     if (s.contains("|")) {
/*    */       
/* 44 */       String[] arr = StringHelper.explode(s, "\\|");
/*    */       
/* 46 */       for (int i = 0; i < arr.length; i++) {
/* 47 */         list.add((enum_chat_formatting == null) ? arr[i] : (enum_chat_formatting + arr[i]));
/*    */       }
/*    */     } else {
/*    */       
/* 51 */       list.add((enum_chat_formatting == null) ? s : (enum_chat_formatting + s));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Translator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */