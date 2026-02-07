/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ProfilerResult
/*    */   implements Comparable
/*    */ {
/*    */   public double field_76332_a;
/*    */   public double field_76330_b;
/*    */   public String field_76331_c;
/*    */   
/*    */   public ProfilerResult(String string, double d, double e) {
/* 18 */     this.field_76331_c = string;
/* 19 */     this.field_76332_a = d;
/* 20 */     this.field_76330_b = e;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_76328_a(ProfilerResult profilerResult) {
/* 25 */     if (profilerResult.field_76332_a < this.field_76332_a) return -1; 
/* 26 */     if (profilerResult.field_76332_a > this.field_76332_a) return 1; 
/* 27 */     return profilerResult.field_76331_c.compareTo(this.field_76331_c);
/*    */   }
/*    */   
/*    */   public int func_76329_a() {
/* 31 */     return (this.field_76331_c.hashCode() & 0xAAAAAA) + 4473924;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ProfilerResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */