/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FurnaceRecipes
/*     */ {
/*   8 */   private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
/*     */ 
/*     */   
/*  11 */   private Map smeltingList = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final FurnaceRecipes smelting() {
/*  19 */     return smeltingBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FurnaceRecipes() {
/*  45 */     RecipesMITE.addFurnaceRecipes(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSmelting(int input_item_id, ItemStack output_item_stack) {
/*  59 */     this.smeltingList.put(Integer.valueOf(input_item_id), output_item_stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getSmeltingResult(ItemStack input_item_stack, int heat_level) {
/*     */     ItemStack result_item_stack;
/*  74 */     if (input_item_stack == null) {
/*  75 */       return null;
/*     */     }
/*  77 */     int input_item_id = input_item_stack.itemID;
/*     */     
/*  79 */     if (heat_level == -1) {
/*  80 */       return (ItemStack)this.smeltingList.get(Integer.valueOf(input_item_id));
/*     */     }
/*     */ 
/*     */     
/*  84 */     if (input_item_id == Block.sand.blockID) {
/*     */       
/*  86 */       result_item_stack = ((heat_level == 1 && input_item_stack.stackSize < 4) || input_item_stack.stackSize < 4) ? null : new ItemStack((heat_level == 1) ? Block.sandStone : Block.glass);
/*     */     } else {
/*  88 */       result_item_stack = (ItemStack)this.smeltingList.get(Integer.valueOf(input_item_id));
/*     */     } 
/*  90 */     return (heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID)) ? null : result_item_stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map getSmeltingList() {
/*  95 */     return this.smeltingList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesSmeltingRecipeExistFor(ItemStack input_item_stack) {
/* 105 */     return (this.smeltingList.get(Integer.valueOf(input_item_stack.itemID)) != null);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FurnaceRecipes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */