/*     */ package net.minecraft;
/*     */ 
/*     */ public class StructureBoundingBox
/*     */ {
/*     */   public int minX;
/*     */   public int minY;
/*     */   public int minZ;
/*     */   public int maxX;
/*     */   public int maxY;
/*     */   public int maxZ;
/*     */   
/*     */   public StructureBoundingBox() {}
/*     */   
/*     */   public StructureBoundingBox(int[] is) {
/*  15 */     if (is.length == 6) {
/*  16 */       this.minX = is[0];
/*  17 */       this.minY = is[1];
/*  18 */       this.minZ = is[2];
/*  19 */       this.maxX = is[3];
/*  20 */       this.maxY = is[4];
/*  21 */       this.maxZ = is[5];
/*     */     } 
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox getNewBoundingBox() {
/*  26 */     return new StructureBoundingBox(2147483647, 2147483647, 2147483647, -2147483648, -2147483648, -2147483648);
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox getComponentToAddBoundingBox(int i, int j, int k, int l, int m, int n, int o, int p, int q, int r) {
/*  30 */     switch (r) {
/*     */       default:
/*  32 */         return new StructureBoundingBox(i + l, j + m, k + n, i + o - 1 + l, j + p - 1 + m, k + q - 1 + n);
/*     */       
/*     */       case 2:
/*  35 */         return new StructureBoundingBox(i + l, j + m, k - q + 1 + n, i + o - 1 + l, j + p - 1 + m, k + n);
/*     */       
/*     */       case 0:
/*  38 */         return new StructureBoundingBox(i + l, j + m, k + n, i + o - 1 + l, j + p - 1 + m, k + q - 1 + n);
/*     */       
/*     */       case 1:
/*  41 */         return new StructureBoundingBox(i - q + 1 + n, j + m, k + l, i + n, j + p - 1 + m, k + o - 1 + l);
/*     */       case 3:
/*     */         break;
/*  44 */     }  return new StructureBoundingBox(i + n, j + m, k + l, i + q - 1 + n, j + p - 1 + m, k + o - 1 + l);
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox(StructureBoundingBox structureBoundingBox) {
/*  49 */     this.minX = structureBoundingBox.minX;
/*  50 */     this.minY = structureBoundingBox.minY;
/*  51 */     this.minZ = structureBoundingBox.minZ;
/*  52 */     this.maxX = structureBoundingBox.maxX;
/*  53 */     this.maxY = structureBoundingBox.maxY;
/*  54 */     this.maxZ = structureBoundingBox.maxZ;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox(int i, int j, int k, int l, int m, int n) {
/*  58 */     this.minX = i;
/*  59 */     this.minY = j;
/*  60 */     this.minZ = k;
/*  61 */     this.maxX = l;
/*  62 */     this.maxY = m;
/*  63 */     this.maxZ = n;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox(int i, int j, int k, int l) {
/*  67 */     this.minX = i;
/*  68 */     this.minZ = j;
/*  69 */     this.maxX = k;
/*  70 */     this.maxZ = l;
/*     */ 
/*     */     
/*  73 */     this.minY = 1;
/*  74 */     this.maxY = 512;
/*     */   }
/*     */   
/*     */   public boolean intersectsWith(StructureBoundingBox structureBoundingBox) {
/*  78 */     return (this.maxX >= structureBoundingBox.minX && this.minX <= structureBoundingBox.maxX && this.maxZ >= structureBoundingBox.minZ && this.minZ <= structureBoundingBox.maxZ && this.maxY >= structureBoundingBox.minY && this.minY <= structureBoundingBox.maxY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersectsWith(int i, int j, int k, int l) {
/*  86 */     return (this.maxX >= i && this.minX <= k && this.maxZ >= j && this.minZ <= l);
/*     */   }
/*     */   
/*     */   public void expandTo(StructureBoundingBox structureBoundingBox) {
/*  90 */     this.minX = Math.min(this.minX, structureBoundingBox.minX);
/*  91 */     this.minY = Math.min(this.minY, structureBoundingBox.minY);
/*  92 */     this.minZ = Math.min(this.minZ, structureBoundingBox.minZ);
/*  93 */     this.maxX = Math.max(this.maxX, structureBoundingBox.maxX);
/*  94 */     this.maxY = Math.max(this.maxY, structureBoundingBox.maxY);
/*  95 */     this.maxZ = Math.max(this.maxZ, structureBoundingBox.maxZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void offset(int i, int j, int k) {
/* 114 */     this.minX += i;
/* 115 */     this.minY += j;
/* 116 */     this.minZ += k;
/* 117 */     this.maxX += i;
/* 118 */     this.maxY += j;
/* 119 */     this.maxZ += k;
/*     */   }
/*     */   
/*     */   public boolean isVecInside(int i, int j, int k) {
/* 123 */     return (i >= this.minX && i <= this.maxX && k >= this.minZ && k <= this.maxZ && j >= this.minY && j <= this.maxY);
/*     */   }
/*     */   
/*     */   public int getXSize() {
/* 127 */     return this.maxX - this.minX + 1;
/*     */   }
/*     */   
/*     */   public int getYSize() {
/* 131 */     return this.maxY - this.minY + 1;
/*     */   }
/*     */   
/*     */   public int getZSize() {
/* 135 */     return this.maxZ - this.minZ + 1;
/*     */   }
/*     */   
/*     */   public int getCenterX() {
/* 139 */     return this.minX + (this.maxX - this.minX + 1) / 2;
/*     */   }
/*     */   
/*     */   public int getCenterY() {
/* 143 */     return this.minY + (this.maxY - this.minY + 1) / 2;
/*     */   }
/*     */   
/*     */   public int getCenterZ() {
/* 147 */     return this.minZ + (this.maxZ - this.minZ + 1) / 2;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 151 */     return "(" + this.minX + ", " + this.minY + ", " + this.minZ + "; " + this.maxX + ", " + this.maxY + ", " + this.maxZ + ")";
/*     */   }
/*     */   
/*     */   public NBTTagIntArray func_143047_a(String string) {
/* 155 */     return new NBTTagIntArray(string, new int[] { this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StructureBoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */