/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PathPoint
/*     */ {
/*     */   public final int xCoord;
/*     */   public final int yCoord;
/*     */   public final int zCoord;
/*     */   private final int hash;
/*  18 */   int index = -1;
/*     */ 
/*     */   
/*     */   float totalPathDistance;
/*     */ 
/*     */   
/*     */   float distanceToNext;
/*     */ 
/*     */   
/*     */   float distanceToTarget;
/*     */ 
/*     */   
/*     */   PathPoint previous;
/*     */ 
/*     */   
/*     */   public boolean isFirst;
/*     */ 
/*     */   
/*     */   public PathPoint(int par1, int par2, int par3) {
/*  37 */     this.xCoord = par1;
/*  38 */     this.yCoord = par2;
/*  39 */     this.zCoord = par3;
/*  40 */     this.hash = makeHash(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int makeHash(int par0, int par1, int par2) {
/*  45 */     return par1 & 0xFF | (par0 & 0x7FFF) << 8 | (par2 & 0x7FFF) << 24 | ((par0 < 0) ? Integer.MIN_VALUE : 0) | ((par2 < 0) ? 32768 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(PathPoint par1PathPoint) {
/*  53 */     float var2 = (par1PathPoint.xCoord - this.xCoord);
/*  54 */     float var3 = (par1PathPoint.yCoord - this.yCoord);
/*  55 */     float var4 = (par1PathPoint.zCoord - this.zCoord);
/*  56 */     return MathHelper.sqrt_float(var2 * var2 + var3 * var3 + var4 * var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public float distanceToSq(PathPoint path_point) {
/*  61 */     float var2 = (path_point.xCoord - this.xCoord);
/*  62 */     float var3 = (path_point.yCoord - this.yCoord);
/*  63 */     float var4 = (path_point.zCoord - this.zCoord);
/*     */     
/*  65 */     return var2 * var2 + var3 * var3 + var4 * var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public float distanceSqTo(Entity entity) {
/*  70 */     double dx = entity.posX - this.xCoord + 0.5D;
/*  71 */     double dy = entity.posY - this.yCoord;
/*  72 */     double dz = entity.posZ - this.zCoord + 0.5D;
/*     */     
/*  74 */     return (float)(dx * dx + dy * dy + dz * dz);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_75832_b(PathPoint par1PathPoint) {
/*  79 */     float var2 = (par1PathPoint.xCoord - this.xCoord);
/*  80 */     float var3 = (par1PathPoint.yCoord - this.yCoord);
/*  81 */     float var4 = (par1PathPoint.zCoord - this.zCoord);
/*  82 */     return var2 * var2 + var3 * var3 + var4 * var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/*  87 */     if (!(par1Obj instanceof PathPoint))
/*     */     {
/*  89 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  93 */     PathPoint var2 = (PathPoint)par1Obj;
/*  94 */     return (this.hash == var2.hash && this.xCoord == var2.xCoord && this.yCoord == var2.yCoord && this.zCoord == var2.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 100 */     return this.hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAssigned() {
/* 108 */     return (this.index >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     return this.xCoord + ", " + this.yCoord + ", " + this.zCoord;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PathPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */