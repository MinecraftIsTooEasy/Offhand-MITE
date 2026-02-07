/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityPudding
/*    */   extends EntityGelatinousCube
/*    */ {
/*    */   public EntityPudding(World world) {
/*  9 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCubic createInstance() {
/* 14 */     return new EntityPudding(this.worldObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumParticle getSquishParticle() {
/* 19 */     return EnumParticle.black_pudding;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getDropItemSubtype() {
/* 24 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isValidLightLevel() {
/* 29 */     return EntityMob.isValidLightLevel(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getDamageTypeVsItems() {
/* 34 */     return DamageSource.acid;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAttackStrengthMultiplierForType() {
/* 39 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isImmuneTo(DamageSource damage_source) {
/* 44 */     if (damage_source.hasFireAspect() || damage_source.isLavaDamage() || damage_source.hasMagicAspect() || damage_source.isSnowball()) {
/* 45 */       return false;
/*    */     }
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPudding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */