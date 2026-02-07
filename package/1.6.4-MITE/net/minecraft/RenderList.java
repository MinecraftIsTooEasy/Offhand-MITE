/*    */ package net.minecraft;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderList
/*    */ {
/*    */   private int field_78429_a;
/*    */   private int field_78427_b;
/*    */   private int field_78428_c;
/* 12 */   private IntBuffer field_78424_g = GLAllocation.createDirectIntBuffer(65536); private double field_78425_d; private double field_78426_e; private double field_78423_f;
/*    */   private boolean field_78430_h;
/*    */   private boolean field_78431_i;
/*    */   
/*    */   public void func_78422_a(int i, int j, int k, double d, double e, double f) {
/* 17 */     this.field_78430_h = true;
/* 18 */     this.field_78424_g.clear();
/* 19 */     this.field_78429_a = i;
/* 20 */     this.field_78427_b = j;
/* 21 */     this.field_78428_c = k;
/*    */     
/* 23 */     this.field_78425_d = d;
/* 24 */     this.field_78426_e = e;
/* 25 */     this.field_78423_f = f;
/*    */   }
/*    */   
/*    */   public boolean func_78418_a(int i, int j, int k) {
/* 29 */     if (!this.field_78430_h) return false; 
/* 30 */     return (i == this.field_78429_a && j == this.field_78427_b && k == this.field_78428_c);
/*    */   }
/*    */   
/*    */   public void func_78420_a(int i) {
/* 34 */     this.field_78424_g.put(i);
/* 35 */     if (this.field_78424_g.remaining() == 0) func_78419_a(); 
/*    */   }
/*    */   
/*    */   public void func_78419_a() {
/* 39 */     if (!this.field_78430_h)
/* 40 */       return;  if (!this.field_78431_i) {
/* 41 */       this.field_78424_g.flip();
/* 42 */       this.field_78431_i = true;
/*    */     } 
/* 44 */     if (this.field_78424_g.remaining() > 0) {
/* 45 */       GL11.glPushMatrix();
/* 46 */       GL11.glTranslatef((float)(this.field_78429_a - this.field_78425_d), (float)(this.field_78427_b - this.field_78426_e), (float)(this.field_78428_c - this.field_78423_f));
/* 47 */       GL11.glCallLists(this.field_78424_g);
/* 48 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void func_78421_b() {
/* 53 */     this.field_78430_h = false;
/* 54 */     this.field_78431_i = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */