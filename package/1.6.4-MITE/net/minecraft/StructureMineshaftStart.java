/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StructureMineshaftStart
/*    */   extends StructureStart
/*    */ {
/*    */   public StructureMineshaftStart() {}
/*    */   
/*    */   public StructureMineshaftStart(World world, Random random, int i, int j) {
/* 16 */     super(i, j);
/*    */     
/* 18 */     ComponentMineshaftRoom componentMineshaftRoom = new ComponentMineshaftRoom(0, random, (i << 4) + 2, (j << 4) + 2);
/* 19 */     this.components.add(componentMineshaftRoom);
/* 20 */     componentMineshaftRoom.buildComponent(componentMineshaftRoom, this.components, random);
/*    */     
/* 22 */     updateBoundingBox();
/* 23 */     markAvailableHeight(world, random, 10);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureMineshaftStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */