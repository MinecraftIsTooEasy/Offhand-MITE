/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAILookAtTradePlayer
/*    */   extends EntityAIWatchClosest
/*    */ {
/*    */   private final EntityVillager theMerchant;
/*    */   
/*    */   public EntityAILookAtTradePlayer(EntityVillager entityVillager) {
/* 11 */     super(entityVillager, EntityPlayer.class, 8.0F);
/* 12 */     this.theMerchant = entityVillager;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 17 */     if (this.theMerchant.isTrading()) {
/* 18 */       this.closestEntity = this.theMerchant.getCustomer();
/* 19 */       return true;
/*    */     } 
/* 21 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAILookAtTradePlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */