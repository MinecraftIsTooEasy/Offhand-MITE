/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemNugget
/*    */   extends ItemIngot
/*    */ {
/*    */   protected ItemNugget(int id, Material material) {
/*  9 */     super(id, material);
/* 10 */     setTextureName("nuggets/" + material.name);
/* 11 */     setMaxStackSize(64);
/* 12 */     setCraftingDifficultyAsComponent(ItemIngot.getCraftingDifficultyAsComponent(material) / 9.0F);
/* 13 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemNugget getForMaterial(Material material) {
/* 18 */     return (material == Material.copper) ? copperNugget : ((material == Material.silver) ? silverNugget : ((material == Material.gold) ? goldNugget : ((material == Material.iron) ? ironNugget : ((material == Material.mithril) ? mithrilNugget : ((material == Material.adamantium) ? adamantiumNugget : ((material == Material.ancient_metal) ? ancientMetalNugget : null))))));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemNugget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */