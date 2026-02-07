/*    */ package net.minecraft;
/*    */ 
/*    */ public abstract class BaseAttribute implements Attribute {
/*    */   private final String field_111115_a;
/*    */   private final double defaultValue;
/*    */   private boolean shouldWatch;
/*    */   
/*    */   protected BaseAttribute(String string, double d) {
/*  9 */     this.field_111115_a = string;
/* 10 */     this.defaultValue = d;
/*    */     
/* 12 */     if (string == null) throw new IllegalArgumentException("Name cannot be null!");
/*    */   
/*    */   }
/*    */   
/*    */   public String getAttributeUnlocalizedName() {
/* 17 */     return this.field_111115_a;
/*    */   }
/*    */   
/*    */   public double getDefaultValue() {
/* 21 */     return this.defaultValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getShouldWatch() {
/* 26 */     return this.shouldWatch;
/*    */   }
/*    */   
/*    */   public BaseAttribute setShouldWatch(boolean bl) {
/* 30 */     this.shouldWatch = bl;
/* 31 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 36 */     return this.field_111115_a.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BaseAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */