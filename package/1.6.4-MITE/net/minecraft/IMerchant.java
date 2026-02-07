package net.minecraft;

public interface IMerchant {
  void setCustomer(EntityPlayer paramEntityPlayer);
  
  EntityPlayer getCustomer();
  
  MerchantRecipeList getRecipes(EntityPlayer paramEntityPlayer);
  
  void setRecipes(MerchantRecipeList paramMerchantRecipeList);
  
  void useRecipe(MerchantRecipe paramMerchantRecipe);
  
  void func_110297_a_(ItemStack paramItemStack);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IMerchant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */