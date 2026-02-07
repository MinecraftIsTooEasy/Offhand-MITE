/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemShard
/*    */   extends ItemRock
/*    */ {
/*    */   protected ItemShard(int id, Material material) {
/*  9 */     super(id, material, "shards/" + material.name);
/* 10 */     setMaxStackSize(64);
/* 11 */     setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(material) / ((material == Material.flint) ? 4 : 9));
/* 12 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemShard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */