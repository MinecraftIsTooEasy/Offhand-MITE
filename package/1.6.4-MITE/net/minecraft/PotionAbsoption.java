/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class PotionAbsoption
/*    */   extends Potion
/*    */ {
/*    */   protected PotionAbsoption(int i, boolean bl, int j) {
/*  8 */     super(i, bl, j);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBase, BaseAttributeMap baseAttributeMap, int i) {
/* 13 */     entityLivingBase.setAbsorptionAmount(entityLivingBase.getAbsorptionAmount() - (4 * (i + 1)));
/* 14 */     super.removeAttributesModifiersFromEntity(entityLivingBase, baseAttributeMap, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBase, BaseAttributeMap baseAttributeMap, int i) {
/* 19 */     entityLivingBase.setAbsorptionAmount(entityLivingBase.getAbsorptionAmount() + (4 * (i + 1)));
/* 20 */     super.applyAttributesModifiersToEntity(entityLivingBase, baseAttributeMap, i);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PotionAbsoption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */