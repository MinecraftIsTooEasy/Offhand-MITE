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
/*     */ public class ComponentStrongholdCorridor
/*     */   extends ComponentStronghold
/*     */ {
/*     */   private int field_74993_a;
/*     */   
/*     */   public ComponentStrongholdCorridor() {}
/*     */   
/*     */   public ComponentStrongholdCorridor(int i, Random random, StructureBoundingBox structureBoundingBox, int j) {
/* 358 */     super(i);
/*     */     
/* 360 */     this.coordBaseMode = j;
/* 361 */     this.boundingBox = structureBoundingBox;
/* 362 */     this.field_74993_a = (j == 2 || j == 0) ? structureBoundingBox.getZSize() : structureBoundingBox.getXSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound nBTTagCompound) {
/* 367 */     super.func_143012_a(nBTTagCompound);
/* 368 */     nBTTagCompound.setInteger("Steps", this.field_74993_a);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound nBTTagCompound) {
/* 373 */     super.func_143011_b(nBTTagCompound);
/* 374 */     this.field_74993_a = nBTTagCompound.getInteger("Steps");
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox func_74992_a(List list, Random random, int i, int j, int k, int l) {
/* 379 */     byte b = 3;
/*     */     
/* 381 */     StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, -1, -1, 0, 5, 5, 4, l);
/*     */     
/* 383 */     StructureComponent structureComponent = StructureComponent.findIntersecting(list, structureBoundingBox);
/* 384 */     if (structureComponent == null)
/*     */     {
/* 386 */       return null;
/*     */     }
/*     */     
/* 389 */     if ((structureComponent.getBoundingBox()).minY == structureBoundingBox.minY)
/*     */     {
/* 391 */       for (byte b1 = 3; b1 >= 1; b1--) {
/* 392 */         structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, -1, -1, 0, 5, 5, b1 - 1, l);
/* 393 */         if (!structureComponent.getBoundingBox().intersectsWith(structureBoundingBox))
/*     */         {
/*     */           
/* 396 */           return StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, -1, -1, 0, 5, 5, b1, l);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 401 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
/* 406 */     if (isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
/* 407 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 411 */     for (byte b = 0; b < this.field_74993_a; b++) {
/*     */       
/* 413 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 0, 0, b, structureBoundingBox);
/* 414 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 1, 0, b, structureBoundingBox);
/* 415 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 2, 0, b, structureBoundingBox);
/* 416 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 3, 0, b, structureBoundingBox);
/* 417 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 4, 0, b, structureBoundingBox);
/*     */       
/* 419 */       for (byte b1 = 1; b1 <= 3; b1++) {
/* 420 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 0, b1, b, structureBoundingBox);
/* 421 */         placeBlockAtCurrentPosition(world, 0, 0, 1, b1, b, structureBoundingBox);
/* 422 */         placeBlockAtCurrentPosition(world, 0, 0, 2, b1, b, structureBoundingBox);
/* 423 */         placeBlockAtCurrentPosition(world, 0, 0, 3, b1, b, structureBoundingBox);
/* 424 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 4, b1, b, structureBoundingBox);
/*     */       } 
/*     */       
/* 427 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 0, 4, b, structureBoundingBox);
/* 428 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 1, 4, b, structureBoundingBox);
/* 429 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 2, 4, b, structureBoundingBox);
/* 430 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 3, 4, b, structureBoundingBox);
/* 431 */       placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, 4, 4, b, structureBoundingBox);
/*     */     } 
/*     */     
/* 434 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStrongholdCorridor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */