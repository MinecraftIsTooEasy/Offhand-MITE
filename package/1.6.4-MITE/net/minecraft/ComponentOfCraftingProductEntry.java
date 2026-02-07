/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComponentOfCraftingProductEntry
/*    */ {
/*    */   int subtype_of_component_or_0;
/*    */   ItemStack crafting_product;
/*    */   
/*    */   public ComponentOfCraftingProductEntry(int subtype_of_component_or_0, ItemStack crafting_product) {
/* 12 */     this.subtype_of_component_or_0 = subtype_of_component_or_0;
/* 13 */     this.crafting_product = crafting_product;
/*    */   }
/*    */ 
/*    */   
/*    */   public ComponentOfCraftingProductEntry matchAllSubtypes() {
/* 18 */     this.subtype_of_component_or_0 = -1;
/* 19 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentOfCraftingProductEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */