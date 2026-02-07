/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class KeyedValuesString
/*    */ {
/*    */   private final String key_value_separator;
/*    */   private final String[] pairs;
/*    */   
/*    */   public KeyedValuesString(String keyed_values, String pairs_separator, String key_value_separator) {
/* 19 */     this.key_value_separator = key_value_separator;
/*    */     
/* 21 */     this.pairs = getPairs(keyed_values, pairs_separator);
/*    */   }
/*    */ 
/*    */   
/*    */   public KeyedValuesString(String keyed_values) {
/* 26 */     this(keyed_values, Pattern.quote("|"), "=");
/*    */   }
/*    */ 
/*    */   
/*    */   private String[] getPairs(String keyed_values, String pairs_separator) {
/* 31 */     if (keyed_values == null || keyed_values.trim().isEmpty()) {
/* 32 */       return null;
/*    */     }
/*    */     
/* 35 */     return keyed_values.split(pairs_separator);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue(String key, boolean return_null_if_empty) {
/* 40 */     for (int i = 0; i < this.pairs.length; i++) {
/*    */       
/* 42 */       String pair = this.pairs[i].trim();
/*    */       
/* 44 */       if (!pair.isEmpty() && pair.indexOf(this.key_value_separator) > 0) {
/*    */ 
/*    */         
/* 47 */         String[] arr = pair.split(this.key_value_separator);
/*    */         
/* 49 */         if (arr[0].trim().equals(key)) {
/*    */ 
/*    */           
/* 52 */           if (arr.length == 1) {
/* 53 */             return return_null_if_empty ? null : "";
/*    */           }
/* 55 */           String value = arr[1].trim();
/*    */           
/* 57 */           return (return_null_if_empty && value.isEmpty()) ? null : value;
/*    */         } 
/*    */       } 
/* 60 */     }  return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\KeyedValuesString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */