/*    */ package net.minecraft;
/*    */ 
/*    */ public class ExceptionMcoService extends Exception {
/*    */   public final int field_96392_a;
/*    */   public final String field_96391_b;
/*    */   public final int field_130097_c;
/*    */   
/*    */   public ExceptionMcoService(int i, String string, int j) {
/*  9 */     super(string);
/* 10 */     this.field_96392_a = i;
/* 11 */     this.field_96391_b = string;
/* 12 */     this.field_130097_c = j;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     if (this.field_130097_c != -1) {
/* 18 */       return "Realms ( ErrorCode: " + this.field_130097_c + " )";
/*    */     }
/* 20 */     return "Realms: " + this.field_96391_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ExceptionMcoService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */