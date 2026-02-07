/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemChain
/*    */   extends Item
/*    */ {
/*    */   protected ItemChain(int id, Material material) {
/*  9 */     super(id, material, "chains/" + material.name);
/* 10 */     setMaxStackSize(8);
/*    */     
/* 12 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemChain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */