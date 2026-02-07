/*    */ package net.minecraft;
/*    */ 
/*    */ public abstract class EntityWaterMob
/*    */   extends EntityCreature
/*    */   implements IAnimals {
/*    */   public EntityWaterMob(World par1World) {
/*  7 */     super(par1World);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBreatheUnderwater() {
/* 12 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 21 */     return this.worldObj.checkNoEntityCollision(this.boundingBox);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTalkInterval() {
/* 29 */     return 120;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canDespawn() {
/* 38 */     return super.canDespawn();
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
/*    */   public void onEntityUpdate() {
/* 54 */     int var1 = getAir();
/* 55 */     super.onEntityUpdate();
/*    */     
/* 57 */     if (isEntityAlive() && !isInWater()) {
/*    */       
/* 59 */       var1--;
/* 60 */       setAir(var1);
/*    */       
/* 62 */       if (getAir() == -20) {
/*    */         
/* 64 */         setAir(0);
/*    */ 
/*    */         
/* 67 */         if (onServer()) {
/* 68 */           attackEntityFrom(new Damage(DamageSource.reverse_drown, 2.0F));
/*    */         }
/*    */       } 
/*    */     } else {
/*    */       
/* 73 */       setAir(300);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWaterMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */