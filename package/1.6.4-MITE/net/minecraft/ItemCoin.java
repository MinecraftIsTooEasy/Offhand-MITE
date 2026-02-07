/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCoin
/*    */   extends Item
/*    */ {
/*    */   public ItemCoin(int id, Material material) {
/* 11 */     super(id, material, "coins/" + material.name);
/*    */     
/* 13 */     setMaxStackSize(64);
/*    */     
/* 15 */     setCraftingDifficultyAsComponent(25.0F);
/*    */     
/* 17 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 22 */     Material material = getExclusiveMaterial();
/*    */     
/* 24 */     if (material == Material.ancient_metal)
/* 25 */       return 500; 
/* 26 */     if (material == Material.mithril)
/* 27 */       return 2500; 
/* 28 */     if (material == Material.adamantium) {
/* 29 */       return 10000;
/*    */     }
/*    */     
/* 32 */     return (material == Material.copper) ? 5 : ((material == Material.silver) ? 25 : ((material == Material.gold) ? 100 : 0));
/*    */   }
/*    */ 
/*    */   
/*    */   public static ItemCoin getForMaterial(Material material) {
/* 37 */     if (material == Material.ancient_metal)
/* 38 */       return coinAncientMetal; 
/* 39 */     if (material == Material.mithril)
/* 40 */       return coinMithril; 
/* 41 */     if (material == Material.adamantium) {
/* 42 */       return coinAdamantium;
/*    */     }
/* 44 */     return (material == Material.copper) ? coinCopper : ((material == Material.silver) ? coinSilver : ((material == Material.gold) ? coinGold : null));
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getNuggetPeer() {
/* 49 */     Material material = getExclusiveMaterial();
/*    */     
/* 51 */     if (material == Material.ancient_metal)
/* 52 */       return ancientMetalNugget; 
/* 53 */     if (material == Material.mithril)
/* 54 */       return mithrilNugget; 
/* 55 */     if (material == Material.adamantium) {
/* 56 */       return adamantiumNugget;
/*    */     }
/* 58 */     return (material == Material.copper) ? Item.copperNugget : ((material == Material.silver) ? Item.silverNugget : ((material == Material.gold) ? Item.goldNugget : null));
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCreated(ItemStack item_stack, World world, EntityPlayer player) {
/* 81 */     player.addExperience(-getExperienceValue() * item_stack.stackSize, false, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 86 */     if (extended_info)
/*    */     {
/* 88 */       info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("item.tooltip.XPEach", new Object[] { Integer.valueOf(getExperienceValue()) }));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemCoin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */