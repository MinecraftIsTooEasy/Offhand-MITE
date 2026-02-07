/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumCreatureType
/*    */ {
/* 10 */   monster(IMob.class, 50, Material.air),
/* 11 */   animal(EntityAnimal.class, 10, Material.air),
/* 12 */   ambient(EntityAmbientCreature.class, 5, Material.air),
/* 13 */   aquatic(EntityWaterMob.class, 5, Material.water);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final Class creatureClass;
/*    */ 
/*    */ 
/*    */   
/*    */   private final int maxNumberOfCreature;
/*    */ 
/*    */ 
/*    */   
/*    */   private final Material creatureMaterial;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   EnumCreatureType(Class par3Class, int par4, Material par5Material) {
/* 32 */     this.creatureClass = par3Class;
/* 33 */     this.maxNumberOfCreature = par4;
/* 34 */     this.creatureMaterial = par5Material;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class getCreatureClass() {
/* 41 */     return this.creatureClass;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxNumberOfCreature() {
/* 46 */     return this.maxNumberOfCreature;
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getCreatureMaterial() {
/* 51 */     return this.creatureMaterial;
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
/*    */   public static EnumCreatureType getCreatureType(EntityLiving entity_living) {
/* 73 */     if (entity_living instanceof EntityAmbientCreature) {
/* 74 */       return ambient;
/*    */     }
/* 76 */     if (entity_living instanceof EntityWaterMob) {
/* 77 */       return aquatic;
/*    */     }
/* 79 */     if (entity_living instanceof IMob) {
/* 80 */       return monster;
/*    */     }
/* 82 */     if (entity_living instanceof EntityAnimal) {
/* 83 */       return animal;
/*    */     }
/* 85 */     Minecraft.setErrorMessage("getCreatureType: unknown creature type for " + entity_living);
/*    */     
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumCreatureType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */