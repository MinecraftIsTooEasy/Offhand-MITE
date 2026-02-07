/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAITradePlayer
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityVillager villager;
/*    */   
/*    */   public EntityAITradePlayer(EntityVillager entityVillager) {
/* 13 */     this.villager = entityVillager;
/* 14 */     setMutexBits(5);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 19 */     if (!this.villager.isEntityAlive()) return false; 
/* 20 */     if (this.villager.isInWater()) return false; 
/* 21 */     if (!this.villager.onGround) return false; 
/* 22 */     if (this.villager.velocityChanged) return false;
/*    */     
/* 24 */     EntityPlayer entityPlayer = this.villager.getCustomer();
/* 25 */     if (entityPlayer == null)
/*    */     {
/* 27 */       return false;
/*    */     }
/*    */     
/* 30 */     if (this.villager.getDistanceSqToEntity(entityPlayer) > 16.0D)
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */     
/* 35 */     if (!(entityPlayer.openContainer instanceof Container))
/*    */     {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 45 */     this.villager.getNavigator().clearPathEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 50 */     this.villager.setCustomer((EntityPlayer)null);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITradePlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */