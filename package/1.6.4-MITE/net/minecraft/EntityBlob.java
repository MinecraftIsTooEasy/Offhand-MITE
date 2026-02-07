/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EntityBlob
/*    */   extends EntityGelatinousCube
/*    */ {
/*    */   public EntityBlob(World world) {
/*  9 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCubic createInstance() {
/* 14 */     return new EntityBlob(this.worldObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumParticle getSquishParticle() {
/* 19 */     return EnumParticle.crimson_blob;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getDropItemSubtype() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isValidLightLevel() {
/* 29 */     return EntityMob.isValidLightLevel(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getDamageTypeVsItems() {
/* 34 */     return DamageSource.pepsin;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAttackStrengthMultiplierForType() {
/* 39 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */