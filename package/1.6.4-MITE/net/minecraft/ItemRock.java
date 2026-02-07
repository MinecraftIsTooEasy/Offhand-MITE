/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemRock
/*    */   extends Item
/*    */ {
/*    */   protected ItemRock(int id, Material material, String texture) {
/* 11 */     super(id, material, texture);
/* 12 */     setMaxStackSize(32);
/* 13 */     setCraftingDifficultyAsComponent(getCraftingDifficultyAsComponent(material));
/* 14 */     setCreativeTab(CreativeTabs.tabMaterials);
/*    */   }
/*    */ 
/*    */   
/*    */   public static float getCraftingDifficultyAsComponent(Material material) {
/* 19 */     return material.durability * ((material == Material.quartz) ? 'á' : 100);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getExperienceValueWhenSacrificed(ItemStack item_stack) {
/* 24 */     Item item = item_stack.getItem();
/*    */     
/* 26 */     if (item == Item.dyePowder && item_stack.getItemSubtype() == 4) {
/* 27 */       return 25;
/*    */     }
/* 29 */     return (item == netherQuartz) ? 50 : ((item == emerald) ? 250 : ((item == diamond) ? 500 : 0));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
/* 34 */     return onItemRightClick(player, player.getHeldItemStack(), partial_tick, ctrl_is_down);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean onItemRightClick(EntityPlayer player, ItemStack item_stack, float partial_tick, boolean ctrl_is_down) {
/* 39 */     int xp_value = getExperienceValueWhenSacrificed(item_stack);
/*    */     
/* 41 */     if (xp_value < 1) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (player.onServer()) {
/*    */       
/* 46 */       player.causeBreakingItemEffect(item_stack.getItem(), player.inventory.currentItem);
/*    */       
/* 48 */       player.convertOneOfHeldItem((ItemStack)null);
/* 49 */       player.addExperience(xp_value);
/*    */     }
/*    */     else {
/*    */       
/* 53 */       player.bobItem();
/*    */     } 
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 61 */     int xp_value = getExperienceValueWhenSacrificed(item_stack);
/*    */     
/* 63 */     if (extended_info && xp_value > 0)
/*    */     {
/* 65 */       info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("item.tooltip.XPEach", new Object[] { Integer.valueOf(xp_value) }));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemRock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */