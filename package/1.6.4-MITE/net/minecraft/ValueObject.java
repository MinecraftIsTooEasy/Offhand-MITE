/*    */ package net.minecraft;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Modifier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ValueObject
/*    */ {
/*    */   public String toString() {
/* 13 */     StringBuilder stringBuilder = new StringBuilder("{");
/* 14 */     for (Field field : getClass().getFields()) {
/* 15 */       if (!func_96394_a(field)) {
/*    */         try {
/* 17 */           stringBuilder.append(field.getName()).append("=").append(field.get(this)).append(" ");
/* 18 */         } catch (IllegalAccessException illegalAccessException) {}
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 23 */     stringBuilder.deleteCharAt(stringBuilder.length() - 1);
/* 24 */     stringBuilder.append('}');
/* 25 */     return stringBuilder.toString();
/*    */   }
/*    */   
/*    */   private static boolean func_96394_a(Field field) {
/* 29 */     return Modifier.isStatic(field.getModifiers());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ValueObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */