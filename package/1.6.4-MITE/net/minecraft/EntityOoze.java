/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityOoze
/*    */   extends EntityGelatinousCube
/*    */ {
/*    */   private int data_object_id_is_climbing;
/*    */   
/*    */   public EntityOoze(World world) {
/* 11 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void entityInit() {
/* 16 */     super.entityInit();
/*    */     
/* 18 */     this.data_object_id_is_climbing = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setClimbing(boolean is_climbing) {
/* 23 */     this.dataWatcher.updateObject(this.data_object_id_is_climbing, Byte.valueOf((byte)(is_climbing ? -1 : 0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCubic createInstance() {
/* 28 */     return new EntityOoze(this.worldObj);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumParticle getSquishParticle() {
/* 33 */     return EnumParticle.gray_ooze;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getDropItemSubtype() {
/* 38 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isValidLightLevel() {
/* 43 */     return EntityMob.isValidLightLevel(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getDamageTypeVsItems() {
/* 48 */     return DamageSource.acid;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAttackStrengthMultiplierForType() {
/* 53 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isImmuneTo(DamageSource damage_source) {
/* 58 */     if (damage_source.isLavaDamage() || damage_source.hasMagicAspect() || damage_source.isSnowball()) {
/* 59 */       return false;
/*    */     }
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setSize(int size) {
/* 71 */     super.setSize((size < 2) ? size : 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnLadder() {
/* 76 */     return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_climbing) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 81 */     super.onUpdate();
/*    */     
/* 83 */     if (onServer())
/* 84 */       setClimbing(this.isCollidedHorizontally); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityOoze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */