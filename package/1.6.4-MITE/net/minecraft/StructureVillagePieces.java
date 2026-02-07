/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StructureVillagePieces
/*     */ {
/*     */   public static void func_143016_a() {
/*  26 */     MapGenStructureIO.func_143031_a(ComponentVillageHouse1.class, "ViBH");
/*  27 */     MapGenStructureIO.func_143031_a(ComponentVillageField.class, "ViDF");
/*  28 */     MapGenStructureIO.func_143031_a(ComponentVillageField2.class, "ViF");
/*  29 */     MapGenStructureIO.func_143031_a(ComponentVillageTorch.class, "ViL");
/*  30 */     MapGenStructureIO.func_143031_a(ComponentVillageHall.class, "ViPH");
/*  31 */     MapGenStructureIO.func_143031_a(ComponentVillageHouse4_Garden.class, "ViSH");
/*  32 */     MapGenStructureIO.func_143031_a(ComponentVillageWoodHut.class, "ViSmH");
/*  33 */     MapGenStructureIO.func_143031_a(ComponentVillageChurch.class, "ViST");
/*  34 */     MapGenStructureIO.func_143031_a(ComponentVillageHouse2.class, "ViS");
/*  35 */     MapGenStructureIO.func_143031_a(ComponentVillageStartPiece.class, "ViStart");
/*  36 */     MapGenStructureIO.func_143031_a(ComponentVillagePathGen.class, "ViSR");
/*  37 */     MapGenStructureIO.func_143031_a(ComponentVillageHouse3.class, "ViTRH");
/*  38 */     MapGenStructureIO.func_143031_a(ComponentVillageWell.class, "ViW");
/*     */   }
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
/*     */   public static List getStructureVillageWeightedPieceList(Random random, int i) {
/*  63 */     ArrayList<StructureVillagePieceWeight> arrayList = new ArrayList();
/*     */     
/*  65 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageHouse4_Garden.class, 4, MathHelper.getRandomIntegerInRange(random, 2 + i, 4 + i * 2)));
/*  66 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageChurch.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + i, 1 + i)));
/*  67 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageHouse1.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + i, 2 + i)));
/*  68 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageWoodHut.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + i, 5 + i * 3)));
/*  69 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageHall.class, 15, MathHelper.getRandomIntegerInRange(random, 0 + i, 2 + i)));
/*  70 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageField.class, 3, MathHelper.getRandomIntegerInRange(random, 1 + i, 4 + i)));
/*  71 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageField2.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + i, 4 + i * 2)));
/*  72 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageHouse2.class, 15, MathHelper.getRandomIntegerInRange(random, 0, 1 + i)));
/*  73 */     arrayList.add(new StructureVillagePieceWeight(ComponentVillageHouse3.class, 8, MathHelper.getRandomIntegerInRange(random, 0 + i, 3 + i * 2)));
/*     */ 
/*     */     
/*  76 */     Iterator<StructureVillagePieceWeight> iterator = arrayList.iterator();
/*  77 */     while (iterator.hasNext()) {
/*  78 */       if (((StructureVillagePieceWeight)iterator.next()).villagePiecesLimit == 0) {
/*  79 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */     
/*  83 */     return arrayList;
/*     */   }
/*     */   
/*     */   private static int func_75079_a(List list) {
/*  87 */     boolean bool = false;
/*  88 */     int i = 0;
/*  89 */     for (StructureVillagePieceWeight structureVillagePieceWeight : list) {
/*  90 */       if (structureVillagePieceWeight.villagePiecesLimit > 0 && structureVillagePieceWeight.villagePiecesSpawned < structureVillagePieceWeight.villagePiecesLimit) {
/*  91 */         bool = true;
/*     */       }
/*  93 */       i += structureVillagePieceWeight.villagePieceWeight;
/*     */     } 
/*  95 */     return bool ? i : -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ComponentVillage func_75083_a(ComponentVillageStartPiece componentVillageStartPiece, StructureVillagePieceWeight structureVillagePieceWeight, List list, Random random, int i, int j, int k, int l, int m) {
/*     */     ComponentVillageHouse3 componentVillageHouse3;
/* 101 */     Class<ComponentVillageHouse4_Garden> clazz = structureVillagePieceWeight.villagePieceClass;
/* 102 */     ComponentVillageHouse4_Garden componentVillageHouse4_Garden = null;
/*     */     
/* 104 */     if (clazz == ComponentVillageHouse4_Garden.class) {
/* 105 */       componentVillageHouse4_Garden = ComponentVillageHouse4_Garden.func_74912_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 106 */     } else if (clazz == ComponentVillageChurch.class) {
/* 107 */       ComponentVillageChurch componentVillageChurch = ComponentVillageChurch.func_74919_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 108 */     } else if (clazz == ComponentVillageHouse1.class) {
/* 109 */       ComponentVillageHouse1 componentVillageHouse1 = ComponentVillageHouse1.func_74898_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 110 */     } else if (clazz == ComponentVillageWoodHut.class) {
/* 111 */       ComponentVillageWoodHut componentVillageWoodHut = ComponentVillageWoodHut.func_74908_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 112 */     } else if (clazz == ComponentVillageHall.class) {
/* 113 */       ComponentVillageHall componentVillageHall = ComponentVillageHall.func_74906_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 114 */     } else if (clazz == ComponentVillageField.class) {
/* 115 */       ComponentVillageField componentVillageField = ComponentVillageField.func_74900_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 116 */     } else if (clazz == ComponentVillageField2.class) {
/* 117 */       ComponentVillageField2 componentVillageField2 = ComponentVillageField2.func_74902_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 118 */     } else if (clazz == ComponentVillageHouse2.class) {
/* 119 */       ComponentVillageHouse2 componentVillageHouse2 = ComponentVillageHouse2.func_74915_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/* 120 */     } else if (clazz == ComponentVillageHouse3.class) {
/* 121 */       componentVillageHouse3 = ComponentVillageHouse3.func_74921_a(componentVillageStartPiece, list, random, i, j, k, l, m);
/*     */     } 
/*     */     
/* 124 */     return componentVillageHouse3;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ComponentVillage getNextVillageComponent(ComponentVillageStartPiece componentVillageStartPiece, List list, Random random, int i, int j, int k, int l, int m) {
/* 129 */     int n = func_75079_a(componentVillageStartPiece.structureVillageWeightedPieceList);
/* 130 */     if (n <= 0) {
/* 131 */       return null;
/*     */     }
/*     */     
/* 134 */     byte b = 0;
/* 135 */     while (b < 5) {
/* 136 */       b++;
/*     */       
/* 138 */       int i1 = random.nextInt(n);
/* 139 */       for (StructureVillagePieceWeight structureVillagePieceWeight : componentVillageStartPiece.structureVillageWeightedPieceList) {
/* 140 */         i1 -= structureVillagePieceWeight.villagePieceWeight;
/* 141 */         if (i1 < 0) {
/*     */           
/* 143 */           if (!structureVillagePieceWeight.canSpawnMoreVillagePiecesOfType(m) || (structureVillagePieceWeight == componentVillageStartPiece.structVillagePieceWeight && componentVillageStartPiece.structureVillageWeightedPieceList.size() > 1)) {
/*     */             break;
/*     */           }
/*     */           
/* 147 */           ComponentVillage componentVillage = func_75083_a(componentVillageStartPiece, structureVillagePieceWeight, list, random, i, j, k, l, m);
/* 148 */           if (componentVillage != null) {
/* 149 */             structureVillagePieceWeight.villagePiecesSpawned++;
/* 150 */             componentVillageStartPiece.structVillagePieceWeight = structureVillagePieceWeight;
/*     */             
/* 152 */             if (!structureVillagePieceWeight.canSpawnMoreVillagePieces()) {
/* 153 */               componentVillageStartPiece.structureVillageWeightedPieceList.remove(structureVillagePieceWeight);
/*     */             }
/* 155 */             return componentVillage;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 163 */     StructureBoundingBox structureBoundingBox = ComponentVillageTorch.func_74904_a(componentVillageStartPiece, list, random, i, j, k, l);
/* 164 */     if (structureBoundingBox != null) {
/* 165 */       return new ComponentVillageTorch(componentVillageStartPiece, m, random, structureBoundingBox, l);
/*     */     }
/*     */     
/* 168 */     return null;
/*     */   }
/*     */   
/*     */   private static StructureComponent getNextVillageStructureComponent(ComponentVillageStartPiece componentVillageStartPiece, List<ComponentVillage> list, Random random, int i, int j, int k, int l, int m) {
/* 172 */     if (m > 50) {
/* 173 */       return null;
/*     */     }
/* 175 */     if (Math.abs(i - (componentVillageStartPiece.getBoundingBox()).minX) > 112 || Math.abs(k - (componentVillageStartPiece.getBoundingBox()).minZ) > 112) {
/* 176 */       return null;
/*     */     }
/*     */     
/* 179 */     ComponentVillage componentVillage = getNextVillageComponent(componentVillageStartPiece, list, random, i, j, k, l, m + 1);
/* 180 */     if (componentVillage != null) {
/* 181 */       int n = (componentVillage.boundingBox.minX + componentVillage.boundingBox.maxX) / 2;
/* 182 */       int i1 = (componentVillage.boundingBox.minZ + componentVillage.boundingBox.maxZ) / 2;
/* 183 */       int i2 = componentVillage.boundingBox.maxX - componentVillage.boundingBox.minX;
/* 184 */       int i3 = componentVillage.boundingBox.maxZ - componentVillage.boundingBox.minZ;
/* 185 */       int i4 = (i2 > i3) ? i2 : i3;
/* 186 */       if (componentVillageStartPiece.getWorldChunkManager().areBiomesViable(n, i1, i4 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
/* 187 */         list.add(componentVillage);
/* 188 */         componentVillageStartPiece.field_74932_i.add(componentVillage);
/* 189 */         return componentVillage;
/*     */       } 
/*     */     } 
/* 192 */     return null;
/*     */   }
/*     */   
/*     */   private static StructureComponent getNextComponentVillagePath(ComponentVillageStartPiece componentVillageStartPiece, List<ComponentVillagePathGen> list, Random random, int i, int j, int k, int l, int m) {
/* 196 */     if (m > 3 + componentVillageStartPiece.terrainType) {
/* 197 */       return null;
/*     */     }
/* 199 */     if (Math.abs(i - (componentVillageStartPiece.getBoundingBox()).minX) > 112 || Math.abs(k - (componentVillageStartPiece.getBoundingBox()).minZ) > 112) {
/* 200 */       return null;
/*     */     }
/*     */     
/* 203 */     StructureBoundingBox structureBoundingBox = ComponentVillagePathGen.func_74933_a(componentVillageStartPiece, list, random, i, j, k, l);
/* 204 */     if (structureBoundingBox != null && structureBoundingBox.minY > 10) {
/* 205 */       ComponentVillagePathGen componentVillagePathGen = new ComponentVillagePathGen(componentVillageStartPiece, m, random, structureBoundingBox, l);
/* 206 */       int n = (componentVillagePathGen.boundingBox.minX + componentVillagePathGen.boundingBox.maxX) / 2;
/* 207 */       int i1 = (componentVillagePathGen.boundingBox.minZ + componentVillagePathGen.boundingBox.maxZ) / 2;
/* 208 */       int i2 = componentVillagePathGen.boundingBox.maxX - componentVillagePathGen.boundingBox.minX;
/* 209 */       int i3 = componentVillagePathGen.boundingBox.maxZ - componentVillagePathGen.boundingBox.minZ;
/* 210 */       int i4 = (i2 > i3) ? i2 : i3;
/* 211 */       if (componentVillageStartPiece.getWorldChunkManager().areBiomesViable(n, i1, i4 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
/* 212 */         list.add(componentVillagePathGen);
/* 213 */         componentVillageStartPiece.field_74930_j.add(componentVillagePathGen);
/* 214 */         return componentVillagePathGen;
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureVillagePieces.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */