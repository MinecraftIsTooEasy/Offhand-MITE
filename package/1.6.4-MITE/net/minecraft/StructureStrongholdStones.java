/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class StructureStrongholdStones
/*    */   extends StructurePieceBlockSelector
/*    */ {
/*    */   private StructureStrongholdStones() {}
/*    */   
/*    */   public void selectBlocks(Random par1Random, int par2, int par3, int par4, boolean par5) {
/* 14 */     if (par5) {
/*    */       
/* 16 */       this.selectedBlockId = Block.stoneBrick.blockID;
/* 17 */       float var6 = par1Random.nextFloat();
/*    */       
/* 19 */       if (var6 < 0.2F)
/*    */       {
/* 21 */         this.selectedBlockMetaData = 2;
/*    */       }
/* 23 */       else if (var6 < 0.5F)
/*    */       {
/* 25 */         this.selectedBlockMetaData = 1;
/*    */       }
/* 27 */       else if (var6 < 0.55F)
/*    */       {
/* 29 */         this.selectedBlockId = Block.silverfish.blockID;
/* 30 */         this.selectedBlockMetaData = 2;
/*    */       }
/*    */       else
/*    */       {
/* 34 */         this.selectedBlockMetaData = 0;
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 39 */       this.selectedBlockId = 0;
/* 40 */       this.selectedBlockMetaData = 0;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   StructureStrongholdStones(StructureStrongholdPieceWeight2 par1StructureStrongholdPieceWeight2) {
/* 46 */     this();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureStrongholdStones.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */