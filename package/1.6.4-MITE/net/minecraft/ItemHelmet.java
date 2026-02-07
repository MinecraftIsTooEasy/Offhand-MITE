/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemHelmet
/*    */   extends ItemArmor
/*    */ {
/*    */   public ItemHelmet(int item_id, Material material, boolean is_chain_mail) {
/*  9 */     super(item_id, material, 0, is_chain_mail);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getArmorType() {
/* 14 */     return "helmet";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumComponentsForDurability() {
/* 19 */     return 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemHelmet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */