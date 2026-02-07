/*    */ package net.minecraft;
/*    */ 
/*    */ public class WorldType
/*    */ {
/*  5 */   public static final WorldType[] worldTypes = new WorldType[16];
/*    */   
/*  7 */   public static final WorldType DEFAULT = (new WorldType(0, "default", 1)).setVersioned();
/*  8 */   public static final WorldType FLAT = new WorldType(1, "flat");
/*  9 */   public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
/*    */   
/* 11 */   public static final WorldType DEFAULT_1_1 = (new WorldType(8, "default_1_1", 0)).setCanBeCreated(false);
/*    */   
/*    */   private final int worldTypeId;
/*    */   private final String worldType;
/*    */   private final int generatorVersion;
/*    */   private boolean canBeCreated;
/*    */   private boolean isWorldTypeVersioned;
/*    */   
/*    */   private WorldType(int i, String string) {
/* 20 */     this(i, string, 0);
/*    */   }
/*    */   
/*    */   private WorldType(int i, String string, int j) {
/* 24 */     this.worldType = string;
/* 25 */     this.generatorVersion = j;
/* 26 */     this.canBeCreated = true;
/* 27 */     this.worldTypeId = i;
/* 28 */     worldTypes[i] = this;
/*    */   }
/*    */   
/*    */   public String getWorldTypeName() {
/* 32 */     return this.worldType;
/*    */   }
/*    */   
/*    */   public String getTranslateName() {
/* 36 */     return "generator." + this.worldType;
/*    */   }
/*    */   
/*    */   public int getGeneratorVersion() {
/* 40 */     return this.generatorVersion;
/*    */   }
/*    */   
/*    */   public WorldType getWorldTypeForGeneratorVersion(int i) {
/* 44 */     if (this == DEFAULT && i == 0) {
/* 45 */       return DEFAULT_1_1;
/*    */     }
/* 47 */     return this;
/*    */   }
/*    */   
/*    */   private WorldType setCanBeCreated(boolean bl) {
/* 51 */     this.canBeCreated = bl;
/* 52 */     return this;
/*    */   }
/*    */   
/*    */   public boolean getCanBeCreated() {
/* 56 */     return this.canBeCreated;
/*    */   }
/*    */   
/*    */   private WorldType setVersioned() {
/* 60 */     this.isWorldTypeVersioned = true;
/* 61 */     return this;
/*    */   }
/*    */   
/*    */   public boolean isVersioned() {
/* 65 */     return this.isWorldTypeVersioned;
/*    */   }
/*    */   
/*    */   public static WorldType parseWorldType(String string) {
/* 69 */     for (byte b = 0; b < worldTypes.length; b++) {
/* 70 */       if (worldTypes[b] != null && (worldTypes[b]).worldType.equalsIgnoreCase(string)) {
/* 71 */         return worldTypes[b];
/*    */       }
/*    */     } 
/* 74 */     return null;
/*    */   }
/*    */   
/*    */   public int getWorldTypeID() {
/* 78 */     return this.worldTypeId;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */