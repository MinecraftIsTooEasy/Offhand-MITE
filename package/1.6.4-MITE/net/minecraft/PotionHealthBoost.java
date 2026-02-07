/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class PotionHealthBoost
/*    */   extends Potion
/*    */ {
/*    */   public PotionHealthBoost(int i, boolean bl, int j) {
/*  8 */     super(i, bl, j);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBase, BaseAttributeMap baseAttributeMap, int i) {
/* 13 */     super.removeAttributesModifiersFromEntity(entityLivingBase, baseAttributeMap, i);
/* 14 */     if (entityLivingBase.getHealth() > entityLivingBase.getMaxHealth())
/* 15 */       entityLivingBase.setHealth(entityLivingBase.getMaxHealth()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PotionHealthBoost.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */