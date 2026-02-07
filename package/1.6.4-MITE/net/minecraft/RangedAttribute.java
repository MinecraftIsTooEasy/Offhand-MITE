/*    */ package net.minecraft;
/*    */ 
/*    */ public class RangedAttribute extends BaseAttribute {
/*    */   private final double minimumValue;
/*    */   private final double maximumValue;
/*    */   private String field_111119_c;
/*    */   
/*    */   public RangedAttribute(String string, double d, double e, double f) {
/*  9 */     super(string, d);
/* 10 */     this.minimumValue = e;
/* 11 */     this.maximumValue = f;
/*    */     
/* 13 */     if (e > f) throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!"); 
/* 14 */     if (d < e) throw new IllegalArgumentException("Default value cannot be lower than minimum value!"); 
/* 15 */     if (d > f) throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RangedAttribute func_111117_a(String string) {
/* 27 */     this.field_111119_c = string;
/* 28 */     return this;
/*    */   }
/*    */   
/*    */   public String func_111116_f() {
/* 32 */     return this.field_111119_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public double clampValue(double d) {
/* 37 */     if (d < this.minimumValue) d = this.minimumValue; 
/* 38 */     if (d > this.maximumValue) d = this.maximumValue;
/*    */     
/* 40 */     return d;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RangedAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */