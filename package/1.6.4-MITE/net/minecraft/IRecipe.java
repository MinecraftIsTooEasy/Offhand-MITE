package net.minecraft;

public interface IRecipe {
  boolean matches(InventoryCrafting paramInventoryCrafting, World paramWorld);
  
  CraftingResult getCraftingResult(InventoryCrafting paramInventoryCrafting);
  
  int getRecipeSize();
  
  ItemStack getRecipeOutput();
  
  ItemStack[] getComponents();
  
  IRecipe setDifficulty(float paramFloat);
  
  IRecipe scaleDifficulty(float paramFloat);
  
  float getUnmodifiedDifficulty();
  
  void setIncludeInLowestCraftingDifficultyDetermination();
  
  boolean getIncludeInLowestCraftingDifficultyDetermination();
  
  void setSkillsets(int[] paramArrayOfint);
  
  void setSkillset(int paramInt);
  
  int[] getSkillsets();
  
  void setMaterialToCheckToolBenchHardnessAgainst(Material paramMaterial);
  
  Material getMaterialToCheckToolBenchHardnessAgainst();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\IRecipe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */