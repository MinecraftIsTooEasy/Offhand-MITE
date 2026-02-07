/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumEquipmentMaterial
/*    */ {
/*  8 */   leather(1.0F, 10, EnumQuality.fine, "leather"),
/*  9 */   wood(0.5F, 10, EnumQuality.fine, "wood"),
/* 10 */   flint(1.0F, 0, EnumQuality.fine, "flint"),
/*    */   
/* 12 */   obsidian(2.0F, 0, EnumQuality.fine, "obsidian"),
/* 13 */   rusted_iron(4.0F, 0, EnumQuality.poor, "rusted_iron"),
/* 14 */   copper(4.0F, 30, EnumQuality.excellent, "copper"),
/* 15 */   silver(4.0F, 30, EnumQuality.excellent, "silver"),
/* 16 */   gold(4.0F, 50, EnumQuality.superb, "gold"),
/* 17 */   iron(8.0F, 30, EnumQuality.masterwork, "iron"),
/* 18 */   ancient_metal(16.0F, 40, EnumQuality.masterwork, "ancient_metal"),
/* 19 */   mithril(64.0F, 100, EnumQuality.legendary, "mithril"),
/* 20 */   adamantium(256.0F, 40, EnumQuality.legendary, "adamantium"),
/*    */   
/* 22 */   netherrack(4.0F, 0, EnumQuality.average, "netherrack"),
/*    */   
/* 24 */   glass(2.0F, 0, EnumQuality.average, "glass"),
/* 25 */   quartz(4.0F, 40, EnumQuality.fine, "quartz"),
/* 26 */   emerald(8.0F, 70, EnumQuality.excellent, "emerald"),
/* 27 */   diamond(16.0F, 100, EnumQuality.superb, "diamond");
/*    */   
/*    */   public final float durability;
/*    */   
/*    */   public final int enchantability;
/*    */   
/*    */   public final EnumQuality max_quality;
/*    */   public final String name;
/*    */   
/*    */   EnumEquipmentMaterial(float durability, int enchantability, EnumQuality max_quality, String name) {
/* 37 */     this.durability = durability;
/* 38 */     this.enchantability = enchantability;
/* 39 */     this.max_quality = max_quality;
/* 40 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumEquipmentMaterial.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */