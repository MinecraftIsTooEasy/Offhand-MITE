/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EntitySlime
/*    */   extends EntityGelatinousCube
/*    */ {
/*    */   public EntitySlime(World world) {
/*  9 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCubic createInstance() {
/* 14 */     return new EntitySlime(this.worldObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumParticle getSquishParticle() {
/* 19 */     return EnumParticle.slime;
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getDamageTypeVsItems() {
/* 24 */     return DamageSource.pepsin;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAttackStrengthMultiplierForType() {
/* 29 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySlime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */