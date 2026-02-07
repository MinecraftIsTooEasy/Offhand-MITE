/*    */ package net.minecraft;
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
/*    */ class StructureStrongholdPieceWeight
/*    */ {
/*    */   public Class pieceClass;
/*    */   public final int pieceWeight;
/*    */   public int instancesSpawned;
/*    */   public int instancesLimit;
/*    */   
/*    */   public StructureStrongholdPieceWeight(Class class_, int i, int j) {
/* 48 */     this.pieceClass = class_;
/* 49 */     this.pieceWeight = i;
/* 50 */     this.instancesLimit = j;
/*    */   }
/*    */   
/*    */   public boolean canSpawnMoreStructuresOfType(int i) {
/* 54 */     return (this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit);
/*    */   }
/*    */   
/*    */   public boolean canSpawnMoreStructures() {
/* 58 */     return (this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureStrongholdPieceWeight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */