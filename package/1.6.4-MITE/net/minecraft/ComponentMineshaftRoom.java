/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.LinkedList;
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
/*     */ public class ComponentMineshaftRoom
/*     */   extends StructureComponent
/*     */ {
/*  70 */   private List roomsLinkedToTheRoom = new LinkedList();
/*     */ 
/*     */   
/*     */   public ComponentMineshaftRoom() {}
/*     */ 
/*     */   
/*     */   public ComponentMineshaftRoom(int i, Random random, int j, int k) {
/*  77 */     super(i);
/*     */     
/*  79 */     this.boundingBox = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent structureComponent, List list, Random random) {
/*  85 */     int i = getComponentType();
/*     */ 
/*     */ 
/*     */     
/*  89 */     int k = this.boundingBox.getYSize() - 3 - 1;
/*  90 */     if (k <= 0) {
/*  91 */       k = 1;
/*     */     }
/*     */ 
/*     */     
/*  95 */     int j = 0;
/*  96 */     while (j < this.boundingBox.getXSize()) {
/*  97 */       j += random.nextInt(this.boundingBox.getXSize());
/*  98 */       if (j + 3 > this.boundingBox.getXSize()) {
/*     */         break;
/*     */       }
/* 101 */       StructureComponent structureComponent1 = StructureMineshaftPieces.getNextComponent(structureComponent, list, random, this.boundingBox.minX + j, this.boundingBox.minY + random.nextInt(k) + 1, this.boundingBox.minZ - 1, 2, i);
/*     */       
/* 103 */       if (structureComponent1 != null) {
/* 104 */         StructureBoundingBox structureBoundingBox = structureComponent1.getBoundingBox();
/* 105 */         this.roomsLinkedToTheRoom.add(new StructureBoundingBox(structureBoundingBox.minX, structureBoundingBox.minY, this.boundingBox.minZ, structureBoundingBox.maxX, structureBoundingBox.maxY, this.boundingBox.minZ + 1));
/*     */       } 
/* 107 */       j += 4;
/*     */     } 
/*     */     
/* 110 */     j = 0;
/* 111 */     while (j < this.boundingBox.getXSize()) {
/* 112 */       j += random.nextInt(this.boundingBox.getXSize());
/* 113 */       if (j + 3 > this.boundingBox.getXSize()) {
/*     */         break;
/*     */       }
/* 116 */       StructureComponent structureComponent1 = StructureMineshaftPieces.getNextComponent(structureComponent, list, random, this.boundingBox.minX + j, this.boundingBox.minY + random.nextInt(k) + 1, this.boundingBox.maxZ + 1, 0, i);
/*     */       
/* 118 */       if (structureComponent1 != null) {
/* 119 */         StructureBoundingBox structureBoundingBox = structureComponent1.getBoundingBox();
/* 120 */         this.roomsLinkedToTheRoom.add(new StructureBoundingBox(structureBoundingBox.minX, structureBoundingBox.minY, this.boundingBox.maxZ - 1, structureBoundingBox.maxX, structureBoundingBox.maxY, this.boundingBox.maxZ));
/*     */       } 
/* 122 */       j += 4;
/*     */     } 
/*     */     
/* 125 */     j = 0;
/* 126 */     while (j < this.boundingBox.getZSize()) {
/* 127 */       j += random.nextInt(this.boundingBox.getZSize());
/* 128 */       if (j + 3 > this.boundingBox.getZSize()) {
/*     */         break;
/*     */       }
/* 131 */       StructureComponent structureComponent1 = StructureMineshaftPieces.getNextComponent(structureComponent, list, random, this.boundingBox.minX - 1, this.boundingBox.minY + random.nextInt(k) + 1, this.boundingBox.minZ + j, 1, i);
/*     */       
/* 133 */       if (structureComponent1 != null) {
/* 134 */         StructureBoundingBox structureBoundingBox = structureComponent1.getBoundingBox();
/* 135 */         this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.minX, structureBoundingBox.minY, structureBoundingBox.minZ, this.boundingBox.minX + 1, structureBoundingBox.maxY, structureBoundingBox.maxZ));
/*     */       } 
/* 137 */       j += 4;
/*     */     } 
/*     */     
/* 140 */     j = 0;
/* 141 */     while (j < this.boundingBox.getZSize()) {
/* 142 */       j += random.nextInt(this.boundingBox.getZSize());
/* 143 */       if (j + 3 > this.boundingBox.getZSize()) {
/*     */         break;
/*     */       }
/* 146 */       StructureComponent structureComponent1 = StructureMineshaftPieces.getNextComponent(structureComponent, list, random, this.boundingBox.maxX + 1, this.boundingBox.minY + random.nextInt(k) + 1, this.boundingBox.minZ + j, 3, i);
/*     */       
/* 148 */       if (structureComponent1 != null) {
/* 149 */         StructureBoundingBox structureBoundingBox = structureComponent1.getBoundingBox();
/* 150 */         this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.maxX - 1, structureBoundingBox.minY, structureBoundingBox.minZ, this.boundingBox.maxX, structureBoundingBox.maxY, structureBoundingBox.maxZ));
/*     */       } 
/* 152 */       j += 4;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
/* 159 */     if (isLiquidInStructureBoundingBox(world, structureBoundingBox)) {
/* 160 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 164 */     fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, Block.dirt.blockID, 0, true);
/*     */ 
/*     */     
/* 167 */     fillWithBlocks(world, structureBoundingBox, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, 0, 0, false);
/* 168 */     for (StructureBoundingBox structureBoundingBox1 : this.roomsLinkedToTheRoom) {
/* 169 */       fillWithBlocks(world, structureBoundingBox, structureBoundingBox1.minX, structureBoundingBox1.maxY - 2, structureBoundingBox1.minZ, structureBoundingBox1.maxX, structureBoundingBox1.maxY, structureBoundingBox1.maxZ, 0, 0, false);
/*     */     }
/* 171 */     randomlyRareFillWithBlocks(world, structureBoundingBox, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, 0, false);
/*     */     
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound nBTTagCompound) {
/* 178 */     NBTTagList nBTTagList = new NBTTagList("Entrances");
/* 179 */     for (StructureBoundingBox structureBoundingBox : this.roomsLinkedToTheRoom) {
/* 180 */       nBTTagList.appendTag(structureBoundingBox.func_143047_a(""));
/*     */     }
/* 182 */     nBTTagCompound.setTag("Entrances", nBTTagList);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound nBTTagCompound) {
/* 188 */     NBTTagList nBTTagList = nBTTagCompound.getTagList("Entrances");
/* 189 */     for (byte b = 0; b < nBTTagList.tagCount(); b++)
/* 190 */       this.roomsLinkedToTheRoom.add(new StructureBoundingBox(((NBTTagIntArray)nBTTagList.tagAt(b)).intArray)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ComponentMineshaftRoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */