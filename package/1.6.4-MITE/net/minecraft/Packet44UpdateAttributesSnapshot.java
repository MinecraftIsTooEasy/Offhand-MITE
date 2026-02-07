/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Packet44UpdateAttributesSnapshot
/*     */ {
/*     */   private final String field_142043_b;
/*     */   private final double field_142044_c;
/*     */   private final Collection field_142042_d;
/*     */   
/*     */   public Packet44UpdateAttributesSnapshot(Packet44UpdateAttributes packet44UpdateAttributes, String string, double d, Collection collection) {
/*  90 */     this.field_142043_b = string;
/*  91 */     this.field_142044_c = d;
/*  92 */     this.field_142042_d = collection;
/*     */   }
/*     */   
/*     */   public String func_142040_a() {
/*  96 */     return this.field_142043_b;
/*     */   }
/*     */   
/*     */   public double func_142041_b() {
/* 100 */     return this.field_142044_c;
/*     */   }
/*     */   
/*     */   public Collection func_142039_c() {
/* 104 */     return this.field_142042_d;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet44UpdateAttributesSnapshot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */