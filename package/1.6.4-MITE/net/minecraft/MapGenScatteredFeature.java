/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class MapGenScatteredFeature
/*     */   extends MapGenStructure
/*     */ {
/*  13 */   private static List biomelist = Arrays.asList(new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private List scatteredFeatureSpawnList = new ArrayList();
/*     */ 
/*     */   
/*  29 */   private int maxDistanceBetweenScatteredFeatures = 40;
/*  30 */   private int minDistanceBetweenScatteredFeatures = 20;
/*     */ 
/*     */   
/*     */   public MapGenScatteredFeature() {}
/*     */   
/*     */   public MapGenScatteredFeature(Map par1Map) {
/*  36 */     this();
/*  37 */     Iterator<Map.Entry> var2 = par1Map.entrySet().iterator();
/*     */     
/*  39 */     while (var2.hasNext()) {
/*     */       
/*  41 */       Map.Entry var3 = var2.next();
/*     */       
/*  43 */       if (((String)var3.getKey()).equals("distance"))
/*     */       {
/*  45 */         this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_143025_a() {
/*  52 */     return "Temple";
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int par1, int par2) {
/*  57 */     int var3 = par1;
/*  58 */     int var4 = par2;
/*     */     
/*  60 */     if (par1 < 0)
/*     */     {
/*  62 */       par1 -= this.maxDistanceBetweenScatteredFeatures - 1;
/*     */     }
/*     */     
/*  65 */     if (par2 < 0)
/*     */     {
/*  67 */       par2 -= this.maxDistanceBetweenScatteredFeatures - 1;
/*     */     }
/*     */     
/*  70 */     int var5 = par1 / this.maxDistanceBetweenScatteredFeatures;
/*  71 */     int var6 = par2 / this.maxDistanceBetweenScatteredFeatures;
/*     */     
/*  73 */     Random var7 = new Random(var5 * 341873128712L + var6 * 132897987541L + this.worldObj.getWorldInfo().getSeed() + 14357617L);
/*  74 */     var5 *= this.maxDistanceBetweenScatteredFeatures;
/*  75 */     var6 *= this.maxDistanceBetweenScatteredFeatures;
/*  76 */     var5 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
/*  77 */     var6 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
/*     */     
/*  79 */     if (var3 == var5 && var4 == var6) {
/*     */       
/*  81 */       BiomeGenBase var8 = this.worldObj.getWorldChunkManager().getBiomeGenAt(var3 * 16 + 8, var4 * 16 + 8);
/*  82 */       Iterator<BiomeGenBase> var9 = biomelist.iterator();
/*     */       
/*  84 */       while (var9.hasNext()) {
/*     */         
/*  86 */         BiomeGenBase var10 = var9.next();
/*     */         
/*  88 */         if (var8 == var10)
/*     */         {
/*  90 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int par1, int par2) {
/* 100 */     return new StructureScatteredFeatureStart(this.worldObj, this.rand, par1, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_143030_a(int par1, int par2, int par3) {
/* 105 */     StructureStart var4 = func_143028_c(par1, par2, par3);
/*     */     
/* 107 */     if (var4 != null && var4 instanceof StructureScatteredFeatureStart && !var4.components.isEmpty()) {
/*     */       
/* 109 */       StructureComponent var5 = var4.components.getFirst();
/* 110 */       return var5 instanceof ComponentScatteredFeatureSwampHut;
/*     */     } 
/*     */ 
/*     */     
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getScatteredFeatureSpawnList() {
/* 123 */     return this.scatteredFeatureSpawnList;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenScatteredFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */