/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityLongdead
/*    */   extends EntitySkeleton
/*    */ {
/*    */   public EntityLongdead(World world) {
/* 12 */     super(world);
/*    */     
/* 14 */     for (int i = 1; i < this.equipmentDropChances.length; i++) {
/* 15 */       this.equipmentDropChances[i] = this.equipmentDropChances[i] * 0.25F;
/*    */     }
/*    */   }
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 20 */     super.applyEntityAttributes();
/*    */     
/* 22 */     setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
/* 23 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, isGuardian() ? 24.0D : 12.0D);
/* 24 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.28999999165534973D);
/* 25 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, isGuardian() ? 8.0D : 6.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addRandomWeapon() {
/* 30 */     setHeldItemStack((new ItemStack((getSkeletonType() == 2) ? Item.swordAncientMetal : Item.bowAncientMetal)).randomizeForMob(this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void addRandomEquipment() {
/* 35 */     addRandomWeapon();
/*    */     
/* 37 */     setBoots((new ItemStack(Item.bootsChainAncientMetal)).randomizeForMob(this, true));
/* 38 */     setLeggings((new ItemStack(Item.legsChainAncientMetal)).randomizeForMob(this, true));
/* 39 */     setCuirass((new ItemStack(Item.plateChainAncientMetal)).randomizeForMob(this, true));
/* 40 */     setHelmet((new ItemStack(Item.helmetChainAncientMetal)).randomizeForMob(this, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLongdead() {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGuardian() {
/* 50 */     return this instanceof EntityLongdeadGuardian;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getNaturalDefense(DamageSource damage_source) {
/* 55 */     return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : (isGuardian() ? 2.0F : 1.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 60 */     return super.getExperienceValue() * (isGuardian() ? 5 : 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLongdead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */