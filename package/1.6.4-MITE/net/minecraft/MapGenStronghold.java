/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGenStronghold
/*     */   extends MapGenStructure
/*     */ {
/*     */   private BiomeGenBase[] allowedBiomeGenBases;
/*     */   private boolean ranBiomeCheck;
/*     */   private ChunkCoordIntPair[] structureCoords;
/*     */   private double field_82671_h;
/*     */   private int field_82672_i;
/*     */   
/*     */   public MapGenStronghold() {
/*  25 */     this.allowedBiomeGenBases = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.jungle, BiomeGenBase.jungleHills };
/*  26 */     this.structureCoords = new ChunkCoordIntPair[3];
/*  27 */     this.field_82671_h = 32.0D;
/*  28 */     this.field_82671_h *= 16.0D;
/*  29 */     this.field_82672_i = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public MapGenStronghold(Map par1Map) {
/*  34 */     this.allowedBiomeGenBases = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.jungle, BiomeGenBase.jungleHills };
/*  35 */     this.structureCoords = new ChunkCoordIntPair[3];
/*  36 */     this.field_82671_h = 32.0D;
/*  37 */     this.field_82671_h *= 16.0D;
/*  38 */     this.field_82672_i = 3;
/*  39 */     Iterator<Map.Entry> var2 = par1Map.entrySet().iterator();
/*     */     
/*  41 */     while (var2.hasNext()) {
/*     */       
/*  43 */       Map.Entry var3 = var2.next();
/*     */       
/*  45 */       if (((String)var3.getKey()).equals("distance")) {
/*     */         
/*  47 */         this.field_82671_h = MathHelper.func_82713_a((String)var3.getValue(), this.field_82671_h, 1.0D); continue;
/*     */       } 
/*  49 */       if (((String)var3.getKey()).equals("count")) {
/*     */         
/*  51 */         this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.structureCoords.length, 1)]; continue;
/*     */       } 
/*  53 */       if (((String)var3.getKey()).equals("spread"))
/*     */       {
/*  55 */         this.field_82672_i = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82672_i, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_143025_a() {
/*  62 */     return "Stronghold";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int par1, int par2) {
/*  73 */     if (!this.ranBiomeCheck) {
/*     */       
/*  75 */       Random var3 = new Random();
/*  76 */       var3.setSeed(this.worldObj.getSeed());
/*  77 */       double var4 = var3.nextDouble() * Math.PI * 2.0D;
/*  78 */       int var6 = 1;
/*     */       
/*  80 */       for (int var7 = 0; var7 < this.structureCoords.length; var7++) {
/*     */         
/*  82 */         double var8 = (1.25D * var6 + var3.nextDouble()) * this.field_82671_h * var6;
/*  83 */         int var10 = (int)Math.round(Math.cos(var4) * var8);
/*  84 */         int var11 = (int)Math.round(Math.sin(var4) * var8);
/*  85 */         ArrayList<? super BiomeGenBase> var12 = new ArrayList();
/*  86 */         Collections.addAll(var12, this.allowedBiomeGenBases);
/*  87 */         ChunkPosition var13 = this.worldObj.getWorldChunkManager().findBiomePosition((var10 << 4) + 8, (var11 << 4) + 8, 112, var12, var3);
/*     */         
/*  89 */         if (var13 != null) {
/*     */           
/*  91 */           var10 = var13.x >> 4;
/*  92 */           var11 = var13.z >> 4;
/*     */         } 
/*     */         
/*  95 */         this.structureCoords[var7] = new ChunkCoordIntPair(var10, var11);
/*  96 */         var4 += 6.283185307179586D * var6 / this.field_82672_i;
/*     */         
/*  98 */         if (var7 == this.field_82672_i) {
/*     */           
/* 100 */           var6 += 2 + var3.nextInt(5);
/* 101 */           this.field_82672_i += 1 + var3.nextInt(2);
/*     */         } 
/*     */       } 
/*     */       
/* 105 */       this.ranBiomeCheck = true;
/*     */     } 
/*     */     
/* 108 */     ChunkCoordIntPair[] var14 = this.structureCoords;
/* 109 */     int var15 = var14.length;
/*     */     
/* 111 */     for (int var5 = 0; var5 < var15; var5++) {
/*     */       
/* 113 */       ChunkCoordIntPair var16 = var14[var5];
/*     */       
/* 115 */       if (par1 == var16.chunkXPos && par2 == var16.chunkZPos)
/*     */       {
/* 117 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getCoordList() {
/* 130 */     ArrayList<ChunkPosition> var1 = new ArrayList();
/* 131 */     ChunkCoordIntPair[] var2 = this.structureCoords;
/* 132 */     int var3 = var2.length;
/*     */     
/* 134 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 136 */       ChunkCoordIntPair var5 = var2[var4];
/*     */       
/* 138 */       if (var5 != null)
/*     */       {
/* 140 */         var1.add(var5.getChunkPosition(64));
/*     */       }
/*     */     } 
/*     */     
/* 144 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int par1, int par2) {
/*     */     StructureStrongholdStart var3;
/* 151 */     for (var3 = new StructureStrongholdStart(this.worldObj, this.rand, par1, par2); var3.getComponents().isEmpty() || ((ComponentStrongholdStairs2)var3.getComponents().get(0)).strongholdPortalRoom == null; var3 = new StructureStrongholdStart(this.worldObj, this.rand, par1, par2));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenStronghold.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */