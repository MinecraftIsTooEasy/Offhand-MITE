/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumMaterialHardness
/*    */ {
/*  8 */   stone(Material.stone, 2.4F),
/*    */   
/* 10 */   obsidian(Material.obsidian, 2.4F),
/*    */ 
/*    */   
/* 13 */   wood(Material.wood, 1.2F),
/* 14 */   tree_leaves(Material.tree_leaves, 0.2F),
/* 15 */   dirt(Material.dirt, 0.5F),
/* 16 */   sand(Material.sand, 0.4F),
/*    */ 
/*    */ 
/*    */   
/* 20 */   snow(Material.snow, 0.4F),
/* 21 */   crafted_snow(Material.craftedSnow, 0.4F),
/* 22 */   netherrack(Material.netherrack, 1.6F),
/*    */   
/* 24 */   pumpkin(Material.pumpkin, 0.6F),
/* 25 */   quartz(Material.quartz, 0.8F);
/*    */   
/*    */   final Material material;
/*    */   
/*    */   final float hardness;
/*    */   
/*    */   EnumMaterialHardness(Material material, float hardness) {
/* 32 */     this.material = material;
/* 33 */     this.hardness = hardness;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static float getHardnessFor(Material material) {
/* 39 */     EnumMaterialHardness[] values = values();
/*    */     
/* 41 */     for (int i = 0; i < values.length; i++) {
/*    */       
/* 43 */       EnumMaterialHardness enum_material_hardness = values[i];
/*    */       
/* 45 */       if (enum_material_hardness.material == material) {
/* 46 */         return enum_material_hardness.hardness;
/*    */       }
/*    */     } 
/* 49 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumMaterialHardness.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */