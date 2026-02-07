/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMeat
/*    */   extends ItemFood
/*    */ {
/*    */   public boolean is_cooked;
/*    */   
/*    */   public ItemMeat(int id, int satiation, int nutrition, boolean has_essential_fats, boolean is_cooked, String texture) {
/* 12 */     super(id, Material.meat, satiation, nutrition, true, has_essential_fats, false, texture);
/* 13 */     this.is_cooked = is_cooked;
/*    */     
/* 15 */     setAnimalProduct();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNameDisambiguationForReferenceFile(int subtype) {
/* 31 */     if (this == fishLargeRaw || this == fishLargeCooked) {
/* 32 */       return "large";
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCompostingValue() {
/* 39 */     if (this == wormRaw)
/* 40 */       return 0.0F; 
/* 41 */     if (this == wormCooked) {
/* 42 */       return 0.1F;
/*    */     }
/* 44 */     if (this.is_cooked) {
/* 45 */       return Item.getItem(this.itemID - 1).getCompostingValue();
/*    */     }
/* 47 */     return super.getCompostingValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemMeat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */