/*    */ package net.minecraft;
/*    */ 
/*    */ public class MouseFilter {
/*    */   private float field_76336_a;
/*    */   private float field_76334_b;
/*    */   private float field_76335_c;
/*    */   
/*    */   public float smooth(float f, float g) {
/*  9 */     this.field_76336_a += f;
/*    */     
/* 11 */     f = (this.field_76336_a - this.field_76334_b) * g;
/* 12 */     this.field_76335_c += (f - this.field_76335_c) * 0.5F;
/* 13 */     if ((f > 0.0F && f > this.field_76335_c) || (f < 0.0F && f < this.field_76335_c)) {
/* 14 */       f = this.field_76335_c;
/*    */     }
/* 16 */     this.field_76334_b += f;
/*    */     
/* 18 */     return f;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MouseFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */