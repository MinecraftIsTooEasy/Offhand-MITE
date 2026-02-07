/*    */ package net.minecraft;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class StructureNetherBridgePieceWeight
/*    */ {
/*    */   public Class weightClass;
/*    */   public final int field_78826_b;
/*    */   public int field_78827_c;
/*    */   public int field_78824_d;
/*    */   public boolean field_78825_e;
/*    */   
/*    */   public StructureNetherBridgePieceWeight(Class class_, int i, int j, boolean bl) {
/* 45 */     this.weightClass = class_;
/* 46 */     this.field_78826_b = i;
/* 47 */     this.field_78824_d = j;
/* 48 */     this.field_78825_e = bl;
/*    */   }
/*    */   
/*    */   public StructureNetherBridgePieceWeight(Class class_, int i, int j) {
/* 52 */     this(class_, i, j, false);
/*    */   }
/*    */   
/*    */   public boolean func_78822_a(int i) {
/* 56 */     return (this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d);
/*    */   }
/*    */   
/*    */   public boolean func_78823_a() {
/* 60 */     return (this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureNetherBridgePieceWeight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */