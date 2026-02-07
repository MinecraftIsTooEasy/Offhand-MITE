/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathEntity
/*     */ {
/*     */   private final PathPoint[] points;
/*     */   private int currentPathIndex;
/*     */   private int pathLength;
/*     */   public boolean include_last_point;
/*     */   
/*     */   public PathEntity(PathPoint[] par1ArrayOfPathPoint) {
/*  18 */     this.points = par1ArrayOfPathPoint;
/*  19 */     this.pathLength = par1ArrayOfPathPoint.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementPathIndex() {
/*  27 */     this.currentPathIndex++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFinished() {
/*  35 */     return (this.currentPathIndex >= this.pathLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint getFinalPathPoint() {
/*  43 */     return (this.pathLength > 0) ? this.points[this.pathLength - 1] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint getPathPointFromIndex(int par1) {
/*  51 */     return this.points[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumRemainingPathPoints() {
/*  56 */     return this.pathLength - this.currentPathIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathPoint getPathPointFromCurrentIndex(int index_from_current) {
/*  61 */     return this.points[this.currentPathIndex + index_from_current];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentPathLength() {
/*  66 */     return this.pathLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentPathLength(int par1) {
/*  71 */     this.pathLength = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentPathIndex() {
/*  76 */     return this.currentPathIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentPathIndex(int par1) {
/*  81 */     this.currentPathIndex = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getVectorFromIndex(Entity par1Entity, int par2) {
/*  89 */     double var3 = (this.points[par2]).xCoord + (int)(par1Entity.width + 1.0F) * 0.5D;
/*  90 */     double var5 = (this.points[par2]).yCoord;
/*  91 */     double var7 = (this.points[par2]).zCoord + (int)(par1Entity.width + 1.0F) * 0.5D;
/*  92 */     return par1Entity.worldObj.getWorldVec3Pool().getVecFromPool(var3, var5, var7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getPosition(Entity par1Entity) {
/* 100 */     return getVectorFromIndex(par1Entity, this.currentPathIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSamePath(PathEntity par1PathEntity) {
/* 108 */     if (par1PathEntity == null)
/*     */     {
/* 110 */       return false;
/*     */     }
/* 112 */     if (par1PathEntity.points.length != this.points.length)
/*     */     {
/* 114 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 118 */     for (int var2 = 0; var2 < this.points.length; var2++) {
/*     */       
/* 120 */       if ((this.points[var2]).xCoord != (par1PathEntity.points[var2]).xCoord || (this.points[var2]).yCoord != (par1PathEntity.points[var2]).yCoord || (this.points[var2]).zCoord != (par1PathEntity.points[var2]).zCoord)
/*     */       {
/* 122 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDestinationSame(Vec3 par1Vec3) {
/* 135 */     PathPoint var2 = getFinalPathPoint();
/* 136 */     return (var2 == null) ? false : ((var2.xCoord == (int)par1Vec3.xCoord && var2.zCoord == (int)par1Vec3.zCoord));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PathEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */