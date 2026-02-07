/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemIngot
/*    */   extends Item
/*    */ {
/*    */   protected ItemIngot(int id, Material material) {
/*  9 */     super(id, material, "ingots/" + material.name);
/* 10 */     setMaxStackSize(8);
/* 11 */     setCraftingDifficultyAsComponent(getCraftingDifficultyAsComponent(material));
/* 12 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ 
/*    */   
/*    */   public static float getCraftingDifficultyAsComponent(Material material) {
/* 17 */     return material.durability * 100.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemIngot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */