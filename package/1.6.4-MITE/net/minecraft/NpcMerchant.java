/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NpcMerchant
/*    */   implements IMerchant
/*    */ {
/*    */   private InventoryMerchant theMerchantInventory;
/*    */   private EntityPlayer customer;
/*    */   private MerchantRecipeList recipeList;
/*    */   
/*    */   public NpcMerchant(EntityPlayer entityPlayer) {
/* 16 */     this.customer = entityPlayer;
/* 17 */     this.theMerchantInventory = new InventoryMerchant(entityPlayer, this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityPlayer getCustomer() {
/* 26 */     return this.customer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCustomer(EntityPlayer entityPlayer) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public MerchantRecipeList getRecipes(EntityPlayer entityPlayer) {
/* 36 */     return this.recipeList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipes(MerchantRecipeList merchantRecipeList) {
/* 41 */     this.recipeList = merchantRecipeList;
/*    */   }
/*    */   
/*    */   public void useRecipe(MerchantRecipe merchantRecipe) {}
/*    */   
/*    */   public void func_110297_a_(ItemStack itemStack) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NpcMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */