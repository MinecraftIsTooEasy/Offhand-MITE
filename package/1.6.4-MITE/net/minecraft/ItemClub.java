/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemClub
/*    */   extends ItemCudgel
/*    */ {
/*    */   protected ItemClub(int par1, Material material) {
/*  9 */     super(par1, material);
/*    */     
/* 11 */     setReachBonus(0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToolType() {
/* 16 */     return "club";
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseDamageVsEntity() {
/* 21 */     return super.getBaseDamageVsEntity() + 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canBlock() {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 31 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemClub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */