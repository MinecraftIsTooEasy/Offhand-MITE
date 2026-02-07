/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*  7 */   private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String ticksToElapsedTime(int par0) {
/* 22 */     int var1 = par0 / 20;
/* 23 */     int var2 = var1 / 60;
/* 24 */     var1 %= 60;
/*    */     
/* 26 */     int hours = var2 / 60;
/*    */     
/* 28 */     if (hours > 0) {
/*    */       
/* 30 */       var2 %= 60;
/*    */       
/* 32 */       return hours + ":" + ((var2 < 10) ? "0" : "") + ((var1 < 10) ? (var2 + ":0" + var1) : (var2 + ":" + var1));
/*    */     } 
/*    */     
/* 35 */     return (var1 < 10) ? (var2 + ":0" + var1) : (var2 + ":" + var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String stripControlCodes(String par0Str) {
/* 40 */     return patternControlCode.matcher(par0Str).replaceAll("");
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */