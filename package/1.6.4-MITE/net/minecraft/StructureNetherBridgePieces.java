/*     */ package net.minecraft;
/*     */ 
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
/*     */ public class StructureNetherBridgePieces
/*     */ {
/*     */   public static void func_143049_a() {
/*  20 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCrossing3.class, "NeBCr");
/*  21 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeEnd.class, "NeBEF");
/*  22 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeStraight.class, "NeBS");
/*  23 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCorridor3.class, "NeCCS");
/*  24 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCorridor4.class, "NeCTB");
/*  25 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeEntrance.class, "NeCE");
/*  26 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCrossing2.class, "NeSCSC");
/*  27 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCorridor.class, "NeSCLT");
/*  28 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCorridor5.class, "NeSC");
/*  29 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCorridor2.class, "NeSCRT");
/*  30 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeNetherStalkRoom.class, "NeCSR");
/*  31 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeThrone.class, "NeMT");
/*  32 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeCrossing.class, "NeRC");
/*  33 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeStairs.class, "NeSR");
/*  34 */     MapGenStructureIO.func_143031_a(ComponentNetherBridgeStartPiece.class, "NeStart");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private static final StructureNetherBridgePieceWeight[] primaryComponents = new StructureNetherBridgePieceWeight[] { new StructureNetherBridgePieceWeight(ComponentNetherBridgeStraight.class, 30, 0, true), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCrossing3.class, 10, 4), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCrossing.class, 10, 4), new StructureNetherBridgePieceWeight(ComponentNetherBridgeStairs.class, 10, 3), new StructureNetherBridgePieceWeight(ComponentNetherBridgeThrone.class, 5, 2), new StructureNetherBridgePieceWeight(ComponentNetherBridgeEntrance.class, 5, 1) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static final StructureNetherBridgePieceWeight[] secondaryComponents = new StructureNetherBridgePieceWeight[] { new StructureNetherBridgePieceWeight(ComponentNetherBridgeCorridor5.class, 25, 0, true), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCrossing2.class, 15, 5), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCorridor2.class, 5, 10), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCorridor.class, 5, 10), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCorridor3.class, 10, 3, true), new StructureNetherBridgePieceWeight(ComponentNetherBridgeCorridor4.class, 7, 2), new StructureNetherBridgePieceWeight(ComponentNetherBridgeNetherStalkRoom.class, 5, 2) };
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
/*     */   private static ComponentNetherBridgePiece createNextComponentRandom(StructureNetherBridgePieceWeight structureNetherBridgePieceWeight, List list, Random random, int i, int j, int k, int l, int m) {
/*     */     ComponentNetherBridgeNetherStalkRoom componentNetherBridgeNetherStalkRoom;
/*  86 */     Class<ComponentNetherBridgeStraight> clazz = structureNetherBridgePieceWeight.weightClass;
/*  87 */     ComponentNetherBridgeStraight componentNetherBridgeStraight = null;
/*     */     
/*  89 */     if (clazz == ComponentNetherBridgeStraight.class) {
/*  90 */       componentNetherBridgeStraight = ComponentNetherBridgeStraight.createValidComponent(list, random, i, j, k, l, m);
/*  91 */     } else if (clazz == ComponentNetherBridgeCrossing3.class) {
/*  92 */       ComponentNetherBridgeCrossing3 componentNetherBridgeCrossing3 = ComponentNetherBridgeCrossing3.createValidComponent(list, random, i, j, k, l, m);
/*  93 */     } else if (clazz == ComponentNetherBridgeCrossing.class) {
/*  94 */       ComponentNetherBridgeCrossing componentNetherBridgeCrossing = ComponentNetherBridgeCrossing.createValidComponent(list, random, i, j, k, l, m);
/*  95 */     } else if (clazz == ComponentNetherBridgeStairs.class) {
/*  96 */       ComponentNetherBridgeStairs componentNetherBridgeStairs = ComponentNetherBridgeStairs.createValidComponent(list, random, i, j, k, l, m);
/*  97 */     } else if (clazz == ComponentNetherBridgeThrone.class) {
/*  98 */       ComponentNetherBridgeThrone componentNetherBridgeThrone = ComponentNetherBridgeThrone.createValidComponent(list, random, i, j, k, l, m);
/*  99 */     } else if (clazz == ComponentNetherBridgeEntrance.class) {
/* 100 */       ComponentNetherBridgeEntrance componentNetherBridgeEntrance = ComponentNetherBridgeEntrance.createValidComponent(list, random, i, j, k, l, m);
/* 101 */     } else if (clazz == ComponentNetherBridgeCorridor5.class) {
/* 102 */       ComponentNetherBridgeCorridor5 componentNetherBridgeCorridor5 = ComponentNetherBridgeCorridor5.createValidComponent(list, random, i, j, k, l, m);
/* 103 */     } else if (clazz == ComponentNetherBridgeCorridor2.class) {
/* 104 */       ComponentNetherBridgeCorridor2 componentNetherBridgeCorridor2 = ComponentNetherBridgeCorridor2.createValidComponent(list, random, i, j, k, l, m);
/* 105 */     } else if (clazz == ComponentNetherBridgeCorridor.class) {
/* 106 */       ComponentNetherBridgeCorridor componentNetherBridgeCorridor = ComponentNetherBridgeCorridor.createValidComponent(list, random, i, j, k, l, m);
/* 107 */     } else if (clazz == ComponentNetherBridgeCorridor3.class) {
/* 108 */       ComponentNetherBridgeCorridor3 componentNetherBridgeCorridor3 = ComponentNetherBridgeCorridor3.createValidComponent(list, random, i, j, k, l, m);
/* 109 */     } else if (clazz == ComponentNetherBridgeCorridor4.class) {
/* 110 */       ComponentNetherBridgeCorridor4 componentNetherBridgeCorridor4 = ComponentNetherBridgeCorridor4.createValidComponent(list, random, i, j, k, l, m);
/* 111 */     } else if (clazz == ComponentNetherBridgeCrossing2.class) {
/* 112 */       ComponentNetherBridgeCrossing2 componentNetherBridgeCrossing2 = ComponentNetherBridgeCrossing2.createValidComponent(list, random, i, j, k, l, m);
/* 113 */     } else if (clazz == ComponentNetherBridgeNetherStalkRoom.class) {
/* 114 */       componentNetherBridgeNetherStalkRoom = ComponentNetherBridgeNetherStalkRoom.createValidComponent(list, random, i, j, k, l, m);
/*     */     } 
/* 116 */     return componentNetherBridgeNetherStalkRoom;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureNetherBridgePieces.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */