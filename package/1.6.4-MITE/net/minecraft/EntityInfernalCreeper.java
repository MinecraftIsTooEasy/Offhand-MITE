/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityInfernalCreeper
/*    */   extends EntityCreeper
/*    */ {
/*    */   public EntityInfernalCreeper(World world) {
/*  9 */     super(world);
/* 10 */     setSize(this.width * getScale(), this.height * getScale());
/*    */     
/* 12 */     this.explosionRadius *= 2.0F;
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
/*    */   public float getNaturalDefense(DamageSource damage_source) {
/* 25 */     return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 2.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 35 */     int num_drops = this.rand.nextInt(4);
/*    */     
/* 37 */     if (num_drops == 0) {
/* 38 */       num_drops = this.rand.nextInt(3);
/*    */     }
/* 40 */     int fortune = damage_source.getLootingModifier();
/*    */     
/* 42 */     if (fortune > 0) {
/* 43 */       num_drops += this.rand.nextInt(fortune + 1);
/*    */     }
/* 45 */     if (num_drops > 0 && !recently_hit_by_player) {
/* 46 */       num_drops -= this.rand.nextInt(num_drops + 1);
/*    */     }
/* 48 */     for (int i = 0; i < num_drops; i++) {
/* 49 */       dropItem(getDropItemId(), 1);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getFragParticle() {
/* 54 */     return Item.fragsInfernalCreeper.itemID;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByFire() {
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isHarmedByLava() {
/* 64 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 69 */     return super.getExperienceValue() * 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityInfernalCreeper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */