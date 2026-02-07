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
/*    */ public class StructureVillagePieceWeight
/*    */ {
/*    */   public Class villagePieceClass;
/*    */   public final int villagePieceWeight;
/*    */   public int villagePiecesSpawned;
/*    */   public int villagePiecesLimit;
/*    */   
/*    */   public StructureVillagePieceWeight(Class class_, int i, int j) {
/* 48 */     this.villagePieceClass = class_;
/* 49 */     this.villagePieceWeight = i;
/* 50 */     this.villagePiecesLimit = j;
/*    */   }
/*    */   
/*    */   public boolean canSpawnMoreVillagePiecesOfType(int i) {
/* 54 */     return (this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit);
/*    */   }
/*    */   
/*    */   public boolean canSpawnMoreVillagePieces() {
/* 58 */     return (this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureVillagePieceWeight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */