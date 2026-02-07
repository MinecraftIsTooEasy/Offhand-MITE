/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityWoodSpider
/*    */   extends EntityArachnid
/*    */ {
/*    */   public EntityWoodSpider(World par1World) {
/*  9 */     super(par1World, 0.6F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 19 */     super.applyEntityAttributes();
/*    */     
/* 21 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 6.0D);
/* 22 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.800000011920929D);
/* 23 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getSoundVolume(String sound) {
/* 28 */     return super.getSoundVolume(sound) * 0.6F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getSoundPitch(String sound) {
/* 33 */     return super.getSoundPitch(sound) * 1.2F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean peacefulDuringDay() {
/* 38 */     return false;
/*    */   }
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
/*    */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 61 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*    */     
/* 63 */     if (result == null || result.entityWasDestroyed()) {
/* 64 */       return result;
/*    */     }
/* 66 */     if (result.entityLostHealth() && target instanceof EntityLivingBase)
/*    */     {
/*    */ 
/*    */       
/* 70 */       if (getClass() == EntityWoodSpider.class) {
/* 71 */         target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 240, 0));
/* 72 */       } else if (isBlackWidowSpider()) {
/* 73 */         target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 960, 0));
/*    */       } 
/*    */     }
/* 76 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getDropItemId() {
/* 81 */     if (this.rand.nextFloat() < 0.4F) {
/* 82 */       return Item.silk.itemID;
/*    */     }
/* 84 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getMaxTargettingRange() {
/* 89 */     return (getBrightness(1.0F) < 0.65F) ? super.getMaxTargettingRange() : (super.getMaxTargettingRange() * 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 94 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWoodSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */