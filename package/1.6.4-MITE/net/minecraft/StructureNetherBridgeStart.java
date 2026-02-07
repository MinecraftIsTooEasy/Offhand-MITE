/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
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
/*    */ public class StructureNetherBridgeStart
/*    */   extends StructureStart
/*    */ {
/*    */   public StructureNetherBridgeStart() {}
/*    */   
/*    */   public StructureNetherBridgeStart(World world, Random random, int i, int j) {
/* 70 */     super(i, j);
/*    */     
/* 72 */     ComponentNetherBridgeStartPiece componentNetherBridgeStartPiece = new ComponentNetherBridgeStartPiece(random, (i << 4) + 2, (j << 4) + 2);
/* 73 */     this.components.add(componentNetherBridgeStartPiece);
/* 74 */     componentNetherBridgeStartPiece.buildComponent(componentNetherBridgeStartPiece, this.components, random);
/*    */     
/* 76 */     ArrayList<StructureComponent> arrayList = componentNetherBridgeStartPiece.field_74967_d;
/* 77 */     while (!arrayList.isEmpty()) {
/* 78 */       int k = random.nextInt(arrayList.size());
/* 79 */       StructureComponent structureComponent = arrayList.remove(k);
/* 80 */       structureComponent.buildComponent(componentNetherBridgeStartPiece, this.components, random);
/*    */     } 
/*    */     
/* 83 */     updateBoundingBox();
/* 84 */     setRandomHeight(world, random, 48, 70);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureNetherBridgeStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */