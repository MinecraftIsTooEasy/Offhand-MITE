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
/*     */ public class StructureVillageStart
/*     */   extends StructureStart
/*     */ {
/*     */   private boolean hasMoreThanTwoComponents;
/*     */   
/*     */   public StructureVillageStart() {}
/*     */   
/*     */   public StructureVillageStart(World world, Random random, int i, int j, int k) {
/*  84 */     super(i, j);
/*     */     
/*  86 */     List list = StructureVillagePieces.getStructureVillageWeightedPieceList(random, k);
/*     */     
/*  88 */     ComponentVillageStartPiece componentVillageStartPiece = new ComponentVillageStartPiece(world.getWorldChunkManager(), 0, random, (i << 4) + 2, (j << 4) + 2, list, k);
/*  89 */     this.components.add(componentVillageStartPiece);
/*  90 */     componentVillageStartPiece.buildComponent(componentVillageStartPiece, this.components, random);
/*     */     
/*  92 */     List<StructureComponent> list1 = componentVillageStartPiece.field_74930_j;
/*  93 */     List<StructureComponent> list2 = componentVillageStartPiece.field_74932_i;
/*  94 */     while (!list1.isEmpty() || !list2.isEmpty()) {
/*     */ 
/*     */       
/*  97 */       if (list1.isEmpty()) {
/*  98 */         int n = random.nextInt(list2.size());
/*  99 */         StructureComponent structureComponent1 = list2.remove(n);
/* 100 */         structureComponent1.buildComponent(componentVillageStartPiece, this.components, random); continue;
/*     */       } 
/* 102 */       int m = random.nextInt(list1.size());
/* 103 */       StructureComponent structureComponent = list1.remove(m);
/* 104 */       structureComponent.buildComponent(componentVillageStartPiece, this.components, random);
/*     */     } 
/*     */ 
/*     */     
/* 108 */     updateBoundingBox();
/*     */     
/* 110 */     byte b = 0;
/* 111 */     for (StructureComponent structureComponent : this.components) {
/* 112 */       if (!(structureComponent instanceof ComponentVillageRoadPiece)) {
/* 113 */         b++;
/*     */       }
/*     */     } 
/* 116 */     this.hasMoreThanTwoComponents = (b > 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSizeableStructure() {
/* 121 */     return this.hasMoreThanTwoComponents;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_143022_a(NBTTagCompound nBTTagCompound) {
/* 126 */     super.func_143022_a(nBTTagCompound);
/*     */     
/* 128 */     nBTTagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_143017_b(NBTTagCompound nBTTagCompound) {
/* 133 */     super.func_143017_b(nBTTagCompound);
/* 134 */     this.hasMoreThanTwoComponents = nBTTagCompound.getBoolean("Valid");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureVillageStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */