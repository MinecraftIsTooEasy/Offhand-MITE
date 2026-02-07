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
/*     */ abstract class ComponentStronghold
/*     */   extends StructureComponent
/*     */ {
/* 213 */   protected EnumDoor field_143013_d = EnumDoor.OPENING;
/*     */ 
/*     */   
/*     */   public ComponentStronghold() {}
/*     */ 
/*     */   
/*     */   protected ComponentStronghold(int i) {
/* 220 */     super(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound nBTTagCompound) {
/* 229 */     nBTTagCompound.setString("EntryDoor", this.field_143013_d.name());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound nBTTagCompound) {
/* 234 */     this.field_143013_d = EnumDoor.valueOf(nBTTagCompound.getString("EntryDoor"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void placeDoor(World world, Random random, StructureBoundingBox structureBoundingBox, EnumDoor enumDoor, int i, int j, int k) {
/* 239 */     switch (EnumDoorHelper.doorEnum[enumDoor.ordinal()]) {
/*     */       
/*     */       default:
/* 242 */         fillWithBlocks(world, structureBoundingBox, i, j, k, i + 3 - 1, j + 3 - 1, k, 0, 0, false);
/*     */         return;
/*     */       case 2:
/* 245 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i, j, k, structureBoundingBox);
/* 246 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i, j + 1, k, structureBoundingBox);
/* 247 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i, j + 2, k, structureBoundingBox);
/* 248 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 1, j + 2, k, structureBoundingBox);
/* 249 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 2, j + 2, k, structureBoundingBox);
/* 250 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 2, j + 1, k, structureBoundingBox);
/* 251 */         placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 2, j, k, structureBoundingBox);
/* 252 */         placeBlockAtCurrentPosition(world, Block.doorWood.blockID, 0, i + 1, j, k, structureBoundingBox);
/* 253 */         placeBlockAtCurrentPosition(world, Block.doorWood.blockID, 8, i + 1, j + 1, k, structureBoundingBox);
/*     */         return;
/*     */       case 3:
/* 256 */         placeBlockAtCurrentPosition(world, 0, 0, i + 1, j, k, structureBoundingBox);
/* 257 */         placeBlockAtCurrentPosition(world, 0, 0, i + 1, j + 1, k, structureBoundingBox);
/* 258 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i, j, k, structureBoundingBox);
/* 259 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i, j + 1, k, structureBoundingBox);
/* 260 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i, j + 2, k, structureBoundingBox);
/* 261 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i + 1, j + 2, k, structureBoundingBox);
/* 262 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i + 2, j + 2, k, structureBoundingBox);
/* 263 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i + 2, j + 1, k, structureBoundingBox);
/* 264 */         placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, i + 2, j, k, structureBoundingBox); return;
/*     */       case 4:
/*     */         break;
/* 267 */     }  placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i, j, k, structureBoundingBox);
/* 268 */     placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i, j + 1, k, structureBoundingBox);
/* 269 */     placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i, j + 2, k, structureBoundingBox);
/* 270 */     placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 1, j + 2, k, structureBoundingBox);
/* 271 */     placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 2, j + 2, k, structureBoundingBox);
/* 272 */     placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 2, j + 1, k, structureBoundingBox);
/* 273 */     placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, i + 2, j, k, structureBoundingBox);
/* 274 */     placeBlockAtCurrentPosition(world, Block.doorIron.blockID, 0, i + 1, j, k, structureBoundingBox);
/* 275 */     placeBlockAtCurrentPosition(world, Block.doorIron.blockID, 8, i + 1, j + 1, k, structureBoundingBox);
/* 276 */     placeBlockAtCurrentPosition(world, Block.stoneButton.blockID, getMetadataWithOffset(Block.stoneButton.blockID, 4), i + 2, j + 1, k + 1, structureBoundingBox);
/* 277 */     placeBlockAtCurrentPosition(world, Block.stoneButton.blockID, getMetadataWithOffset(Block.stoneButton.blockID, 3), i + 2, j + 1, k - 1, structureBoundingBox);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected EnumDoor getRandomDoor(Random random) {
/* 283 */     int i = random.nextInt(5);
/* 284 */     switch (i)
/*     */     
/*     */     { 
/*     */       default:
/* 288 */         return EnumDoor.OPENING;
/*     */       case 2:
/* 290 */         return EnumDoor.WOOD_DOOR;
/*     */       case 3:
/* 292 */         return EnumDoor.GRATES;
/*     */       case 4:
/* 294 */         break; }  return EnumDoor.IRON_DOOR;
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureComponent getNextComponentNormal(ComponentStrongholdStairs2 componentStrongholdStairs2, List list, Random random, int i, int j) {
/* 299 */     switch (this.coordBaseMode) {
/*     */       case 2:
/* 301 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX + i, this.boundingBox.minY + j, this.boundingBox.minZ - 1, this.coordBaseMode, getComponentType());
/*     */       case 0:
/* 303 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX + i, this.boundingBox.minY + j, this.boundingBox.maxZ + 1, this.coordBaseMode, getComponentType());
/*     */       case 1:
/* 305 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX - 1, this.boundingBox.minY + j, this.boundingBox.minZ + i, this.coordBaseMode, getComponentType());
/*     */       case 3:
/* 307 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY + j, this.boundingBox.minZ + i, this.coordBaseMode, getComponentType());
/*     */     } 
/* 309 */     return null;
/*     */   }
/*     */   
/*     */   protected StructureComponent getNextComponentX(ComponentStrongholdStairs2 componentStrongholdStairs2, List list, Random random, int i, int j) {
/* 313 */     switch (this.coordBaseMode) {
/*     */       case 2:
/* 315 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX - 1, this.boundingBox.minY + i, this.boundingBox.minZ + j, 1, getComponentType());
/*     */       case 0:
/* 317 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX - 1, this.boundingBox.minY + i, this.boundingBox.minZ + j, 1, getComponentType());
/*     */       case 1:
/* 319 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX + j, this.boundingBox.minY + i, this.boundingBox.minZ - 1, 2, getComponentType());
/*     */       case 3:
/* 321 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX + j, this.boundingBox.minY + i, this.boundingBox.minZ - 1, 2, getComponentType());
/*     */     } 
/* 323 */     return null;
/*     */   }
/*     */   
/*     */   protected StructureComponent getNextComponentZ(ComponentStrongholdStairs2 componentStrongholdStairs2, List list, Random random, int i, int j) {
/* 327 */     switch (this.coordBaseMode) {
/*     */       case 2:
/* 329 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY + i, this.boundingBox.minZ + j, 3, getComponentType());
/*     */       case 0:
/* 331 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY + i, this.boundingBox.minZ + j, 3, getComponentType());
/*     */       case 1:
/* 333 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX + j, this.boundingBox.minY + i, this.boundingBox.maxZ + 1, 0, getComponentType());
/*     */       case 3:
/* 335 */         return StructureStrongholdPieces.getNextValidComponentAccess(componentStrongholdStairs2, list, random, this.boundingBox.minX + j, this.boundingBox.minY + i, this.boundingBox.maxZ + 1, 0, getComponentType());
/*     */     } 
/* 337 */     return null;
/*     */   }
/*     */   
/*     */   protected static boolean canStrongholdGoDeeper(StructureBoundingBox structureBoundingBox) {
/* 341 */     return (structureBoundingBox != null && structureBoundingBox.minY > 10);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentStronghold.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */