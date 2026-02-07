/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderEarthElemental
/*    */   extends RenderBiped
/*    */ {
/*    */   public static final int texture_stone_normal = 0;
/*    */   public static final int texture_stone_magma = 1;
/*    */   public static final int texture_obsidian_normal = 2;
/*    */   public static final int texture_obsidian_magma = 3;
/*    */   public static final int texture_netherrack_normal = 4;
/*    */   public static final int texture_netherrack_magma = 5;
/*    */   public static final int texture_end_stone_normal = 6;
/*    */   public static final int texture_end_stone_magma = 7;
/*    */   public static final int texture_clay_normal = 8;
/*    */   public static final int texture_clay_hardened = 9;
/*    */   
/*    */   public RenderEarthElemental() {
/* 24 */     super(new ModelInvisibleStalker(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setTexture(int index, String name, boolean magma) {
/* 29 */     if (index == 9) {
/*    */       
/* 31 */       setTexture(index, "textures/entity/earth_elemental/" + name + "/earth_elemental_" + name + "_hardened", "textures/entity/earth_elemental/earth_elemental");
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     setTexture(index, "textures/entity/earth_elemental/" + name + "/earth_elemental_" + name + (magma ? "_magma" : ""), "textures/entity/earth_elemental/" + (magma ? "earth_elemental_magma" : "earth_elemental"));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setTextures() {
/* 40 */     setTexture(0, "stone", false);
/* 41 */     setTexture(1, "stone", true);
/*    */     
/* 43 */     setTexture(2, "obsidian", false);
/* 44 */     setTexture(3, "obsidian", true);
/*    */     
/* 46 */     setTexture(4, "netherrack", false);
/* 47 */     setTexture(5, "netherrack", true);
/*    */     
/* 49 */     setTexture(6, "end_stone", false);
/* 50 */     setTexture(7, "end_stone", true);
/*    */     
/* 52 */     setTexture(8, "clay", false);
/* 53 */     setTexture(9, "clay", false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getTextures(EntityEarthElemental earth_elemental) {
/* 58 */     int type = earth_elemental.getType();
/*    */     
/* 60 */     if (type == EntityEarthElemental.CLAY_NORMAL)
/* 61 */       return this.textures[8]; 
/* 62 */     if (type == EntityEarthElemental.CLAY_HARDENED) {
/* 63 */       return this.textures[9];
/*    */     }
/* 65 */     return this.textures[(type == EntityEarthElemental.STONE_NORMAL) ? 0 : ((type == EntityEarthElemental.STONE_MAGMA) ? 1 : ((type == EntityEarthElemental.OBSIDIAN_NORMAL) ? 2 : ((type == EntityEarthElemental.OBSIDIAN_MAGMA) ? 3 : ((type == EntityEarthElemental.NETHERRACK_NORMAL) ? 4 : ((type == EntityEarthElemental.NETHERRACK_MAGMA) ? 5 : ((type == EntityEarthElemental.END_STONE_NORMAL) ? 6 : 7))))))];
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity entity) {
/* 70 */     return getTextures((EntityEarthElemental)entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderEarthElemental.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */