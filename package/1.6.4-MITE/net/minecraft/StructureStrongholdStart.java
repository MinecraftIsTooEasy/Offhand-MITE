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
/*     */ public class StructureStrongholdStart
/*     */   extends StructureStart
/*     */ {
/*     */   public StructureStrongholdStart() {}
/*     */   
/*     */   public StructureStrongholdStart(World world, Random random, int i, int j) {
/* 120 */     super(i, j);
/*     */     
/* 122 */     StructureStrongholdPieces.prepareStructurePieces();
/*     */     
/* 124 */     ComponentStrongholdStairs2 componentStrongholdStairs2 = new ComponentStrongholdStairs2(0, random, (i << 4) + 2, (j << 4) + 2);
/* 125 */     this.components.add(componentStrongholdStairs2);
/* 126 */     componentStrongholdStairs2.buildComponent(componentStrongholdStairs2, this.components, random);
/*     */     
/* 128 */     List<StructureComponent> list = componentStrongholdStairs2.field_75026_c;
/* 129 */     while (!list.isEmpty()) {
/* 130 */       int k = random.nextInt(list.size());
/* 131 */       StructureComponent structureComponent = list.remove(k);
/* 132 */       structureComponent.buildComponent(componentStrongholdStairs2, this.components, random);
/*     */     } 
/*     */     
/* 135 */     updateBoundingBox();
/* 136 */     markAvailableHeight(world, random, 10);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureStrongholdStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */