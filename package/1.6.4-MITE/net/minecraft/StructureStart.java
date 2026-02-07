/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public abstract class StructureStart
/*     */ {
/*  10 */   protected LinkedList components = new LinkedList();
/*     */   
/*     */   protected StructureBoundingBox boundingBox;
/*     */   
/*     */   private int field_143024_c;
/*     */   private int field_143023_d;
/*     */   
/*     */   public StructureStart() {}
/*     */   
/*     */   public StructureStart(int i, int j) {
/*  20 */     this.field_143024_c = i;
/*  21 */     this.field_143023_d = j;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox getBoundingBox() {
/*  25 */     return this.boundingBox;
/*     */   }
/*     */   
/*     */   public LinkedList getComponents() {
/*  29 */     return this.components;
/*     */   }
/*     */   
/*     */   public void generateStructure(World world, Random random, StructureBoundingBox structureBoundingBox) {
/*  33 */     Iterator<StructureComponent> iterator = this.components.iterator();
/*  34 */     while (iterator.hasNext()) {
/*  35 */       StructureComponent structureComponent = iterator.next();
/*  36 */       if (structureComponent.getBoundingBox().intersectsWith(structureBoundingBox) && 
/*  37 */         !structureComponent.addComponentParts(world, random, structureBoundingBox)) {
/*  38 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateBoundingBox() {
/*  45 */     this.boundingBox = StructureBoundingBox.getNewBoundingBox();
/*     */     
/*  47 */     for (StructureComponent structureComponent : this.components) {
/*  48 */       this.boundingBox.expandTo(structureComponent.getBoundingBox());
/*     */     }
/*     */   }
/*     */   
/*     */   public NBTTagCompound func_143021_a(int i, int j) {
/*  53 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */     
/*  55 */     nBTTagCompound.setString("id", MapGenStructureIO.func_143033_a(this));
/*  56 */     nBTTagCompound.setInteger("ChunkX", i);
/*  57 */     nBTTagCompound.setInteger("ChunkZ", j);
/*  58 */     nBTTagCompound.setTag("BB", this.boundingBox.func_143047_a("BB"));
/*     */     
/*  60 */     NBTTagList nBTTagList = new NBTTagList("Children");
/*  61 */     for (StructureComponent structureComponent : this.components) {
/*  62 */       nBTTagList.appendTag(structureComponent.func_143010_b());
/*     */     }
/*  64 */     nBTTagCompound.setTag("Children", nBTTagList);
/*     */     
/*  66 */     func_143022_a(nBTTagCompound);
/*     */     
/*  68 */     return nBTTagCompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_143022_a(NBTTagCompound nBTTagCompound) {}
/*     */ 
/*     */   
/*     */   public void func_143020_a(World world, NBTTagCompound nBTTagCompound) {
/*  77 */     this.field_143024_c = nBTTagCompound.getInteger("ChunkX");
/*  78 */     this.field_143023_d = nBTTagCompound.getInteger("ChunkZ");
/*  79 */     if (nBTTagCompound.hasKey("BB")) {
/*  80 */       this.boundingBox = new StructureBoundingBox(nBTTagCompound.getIntArray("BB"));
/*     */     }
/*     */ 
/*     */     
/*  84 */     NBTTagList nBTTagList = nBTTagCompound.getTagList("Children");
/*  85 */     for (byte b = 0; b < nBTTagList.tagCount(); b++) {
/*  86 */       this.components.add(MapGenStructureIO.func_143032_b((NBTTagCompound)nBTTagList.tagAt(b), world));
/*     */     }
/*     */     
/*  89 */     func_143017_b(nBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_143017_b(NBTTagCompound nBTTagCompound) {}
/*     */ 
/*     */   
/*     */   protected void markAvailableHeight(World world, Random random, int i) {
/*  97 */     int j = 63 - i;
/*     */ 
/*     */     
/* 100 */     int k = this.boundingBox.getYSize() + 1;
/*     */     
/* 102 */     if (k < j) {
/* 103 */       k += random.nextInt(j - k);
/*     */     }
/*     */ 
/*     */     
/* 107 */     int m = k - this.boundingBox.maxY;
/* 108 */     this.boundingBox.offset(0, m, 0);
/* 109 */     for (StructureComponent structureComponent : this.components) {
/* 110 */       structureComponent.getBoundingBox().offset(0, m, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setRandomHeight(World world, Random random, int i, int j) {
/* 116 */     int k = j - i + 1 - this.boundingBox.getYSize();
/* 117 */     int m = 1;
/*     */     
/* 119 */     if (k > 1) {
/* 120 */       m = i + random.nextInt(k);
/*     */     } else {
/* 122 */       m = i;
/*     */     } 
/*     */ 
/*     */     
/* 126 */     int n = m - this.boundingBox.minY;
/* 127 */     this.boundingBox.offset(0, n, 0);
/* 128 */     for (StructureComponent structureComponent : this.components) {
/* 129 */       structureComponent.getBoundingBox().offset(0, n, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSizeableStructure() {
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public int func_143019_e() {
/* 138 */     return this.field_143024_c;
/*     */   }
/*     */   
/*     */   public int func_143018_f() {
/* 142 */     return this.field_143023_d;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureStart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */