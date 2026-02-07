/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class CombatEntry
/*    */ {
/*    */   private final DamageSource damageSrc;
/*    */   private final int field_94567_b;
/*    */   private final float field_94568_c;
/*    */   private final float field_94565_d;
/*    */   private final String field_94566_e;
/*    */   private final float field_94564_f;
/*    */   
/*    */   public CombatEntry(DamageSource par1DamageSource, int par2, float par3, float par4, String par5Str, float par6) {
/* 14 */     this.damageSrc = par1DamageSource;
/* 15 */     this.field_94567_b = par2;
/* 16 */     this.field_94568_c = par4;
/* 17 */     this.field_94565_d = par3;
/* 18 */     this.field_94566_e = par5Str;
/* 19 */     this.field_94564_f = par6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DamageSource getDamageSrc() {
/* 27 */     return this.damageSrc;
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_94563_c() {
/* 32 */     return this.field_94568_c;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_94559_f() {
/* 38 */     return this.damageSrc.getResponsibleEntity() instanceof EntityLivingBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_94562_g() {
/* 43 */     return this.field_94566_e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_94558_h() {
/* 49 */     return (getDamageSrc().getResponsibleEntity() == null) ? null : getDamageSrc().getResponsibleEntity().getTranslatedEntityName();
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_94561_i() {
/* 54 */     return (this.damageSrc == DamageSource.outOfWorld) ? Float.MAX_VALUE : this.field_94564_f;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CombatEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */