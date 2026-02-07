/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemHorseArmor
/*    */   extends Item
/*    */ {
/*    */   protected Material effective_material;
/*    */   
/*    */   protected ItemHorseArmor(int id, Material material) {
/* 13 */     super(id, material, "armor/horse/" + material.name);
/* 14 */     this.effective_material = material;
/* 15 */     setMaxStackSize(1);
/* 16 */     setCreativeTab(CreativeTabs.tabMisc);
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getArmorMaterial() {
/* 21 */     return this.effective_material;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProtection() {
/* 26 */     if (this.effective_material == Material.copper)
/* 27 */       return 4; 
/* 28 */     if (this.effective_material == Material.silver)
/* 29 */       return 4; 
/* 30 */     if (this.effective_material == Material.gold)
/* 31 */       return 3; 
/* 32 */     if (this.effective_material == Material.iron || this.effective_material == Material.ancient_metal)
/* 33 */       return 5; 
/* 34 */     if (this.effective_material == Material.mithril)
/* 35 */       return 6; 
/* 36 */     if (this.effective_material == Material.adamantium) {
/* 37 */       return 7;
/*    */     }
/* 39 */     Minecraft.setErrorMessage("getProtection: unhandled armor type");
/* 40 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCoverage() {
/* 45 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
/* 50 */     if (extended_info) {
/*    */       
/* 52 */       info.add("");
/*    */       
/* 54 */       float protection = getProtection();
/* 55 */       int decimal_places = (protection < 1.0F) ? 2 : 1;
/*    */ 
/*    */       
/* 58 */       info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.protectionBonus", new Object[] { StringHelper.formatFloat(protection, decimal_places, decimal_places) }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemHorseArmor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */