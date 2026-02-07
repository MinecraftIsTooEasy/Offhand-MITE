/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class MapGenVillage
/*     */   extends MapGenStructure
/*     */ {
/*  13 */   public static final List villageSpawnBiomes = Arrays.asList(new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.desert });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int terrainType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private int field_82665_g = 40;
/*  27 */   private int field_82666_h = 20;
/*     */   
/*     */   public MapGenVillage() {}
/*     */   
/*     */   public MapGenVillage(Map par1Map) {
/*  32 */     this();
/*  33 */     Iterator<Map.Entry> var2 = par1Map.entrySet().iterator();
/*     */     
/*  35 */     while (var2.hasNext()) {
/*     */       
/*  37 */       Map.Entry var3 = var2.next();
/*     */       
/*  39 */       if (((String)var3.getKey()).equals("size")) {
/*     */         
/*  41 */         this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.terrainType, 0); continue;
/*     */       } 
/*  43 */       if (((String)var3.getKey()).equals("distance"))
/*     */       {
/*  45 */         this.field_82665_g = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82665_g, this.field_82666_h + 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_143025_a() {
/*  52 */     return "Village";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int par1, int par2) {
/*  62 */     if (Minecraft.isInTournamentMode()) {
/*  63 */       return false;
/*     */     }
/*  65 */     if (this.worldObj.getDayOfWorld() < 60) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     byte required_village_conditions = WorldInfo.getVillagePrerequisites();
/*     */     
/*  71 */     if (this.worldObj.worldInfo.getVillageConditions() < required_village_conditions) {
/*  72 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  76 */     int var3 = par1;
/*  77 */     int var4 = par2;
/*     */     
/*  79 */     if (par1 < 0)
/*     */     {
/*  81 */       par1 -= this.field_82665_g - 1;
/*     */     }
/*     */     
/*  84 */     if (par2 < 0)
/*     */     {
/*  86 */       par2 -= this.field_82665_g - 1;
/*     */     }
/*     */     
/*  89 */     int var5 = par1 / this.field_82665_g;
/*  90 */     int var6 = par2 / this.field_82665_g;
/*     */     
/*  92 */     Random var7 = new Random(var5 * 341873128712L + var6 * 132897987541L + this.worldObj.getWorldInfo().getSeed() + 10387312L);
/*  93 */     var5 *= this.field_82665_g;
/*  94 */     var6 *= this.field_82665_g;
/*  95 */     var5 += var7.nextInt(this.field_82665_g - this.field_82666_h);
/*  96 */     var6 += var7.nextInt(this.field_82665_g - this.field_82666_h);
/*     */     
/*  98 */     if (var3 == var5 && var4 == var6) {
/*     */       
/* 100 */       boolean var8 = this.worldObj.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 0, villageSpawnBiomes);
/*     */       
/* 102 */       if (var8)
/*     */       {
/* 104 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int par1, int par2) {
/* 113 */     return new StructureVillageStart(this.worldObj, this.rand, par1, par2, this.terrainType);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenVillage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */