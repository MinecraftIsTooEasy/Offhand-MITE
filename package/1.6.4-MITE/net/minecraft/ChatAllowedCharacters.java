/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatAllowedCharacters
/*    */ {
/*    */   private static String getAllowedCharacters() {
/* 17 */     String str = "";
/*    */     try {
/* 19 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ChatAllowedCharacters.class.getResourceAsStream("/font.txt"), "UTF-8"));
/* 20 */       String str1 = "";
/* 21 */       while ((str1 = bufferedReader.readLine()) != null) {
/* 22 */         if (!str1.startsWith("#")) {
/* 23 */           str = str + str1;
/*    */         }
/*    */       } 
/* 26 */       bufferedReader.close();
/* 27 */     } catch (Exception exception) {}
/*    */     
/* 29 */     return str;
/*    */   }
/*    */ 
/*    */   
/* 33 */   public static final String allowedCharacters = getAllowedCharacters();
/*    */   
/*    */   public static final boolean isAllowedCharacter(char c) {
/* 36 */     return (c != '§' && (allowedCharacters.indexOf(c) >= 0 || c > ' '));
/*    */   }
/*    */   
/* 39 */   public static final char[] allowedCharactersArray = new char[] { '/', '\n', '\r', '\t', Character.MIN_VALUE, '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':' };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String filerAllowedCharacters(String string) {
/* 50 */     StringBuilder stringBuilder = new StringBuilder();
/*    */     
/* 52 */     for (char c : string.toCharArray()) {
/* 53 */       if (isAllowedCharacter(c)) {
/* 54 */         stringBuilder.append(c);
/*    */       }
/*    */     } 
/*    */     
/* 58 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChatAllowedCharacters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */