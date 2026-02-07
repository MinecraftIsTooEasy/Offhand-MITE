/*     */ package net.minecraft;
/*     */ 
/*     */ public class Path {
/*   4 */   private PathPoint[] pathPoints = new PathPoint[1024];
/*     */   
/*     */   private int count;
/*     */   
/*     */   public PathPoint addPoint(PathPoint pathPoint) {
/*   9 */     if (pathPoint.index >= 0) throw new IllegalStateException("OW KNOWS!");
/*     */     
/*  11 */     if (this.count == this.pathPoints.length) {
/*  12 */       PathPoint[] arrayOfPathPoint = new PathPoint[this.count << 1];
/*  13 */       System.arraycopy(this.pathPoints, 0, arrayOfPathPoint, 0, this.count);
/*  14 */       this.pathPoints = arrayOfPathPoint;
/*     */     } 
/*     */ 
/*     */     
/*  18 */     this.pathPoints[this.count] = pathPoint;
/*  19 */     pathPoint.index = this.count;
/*  20 */     sortBack(this.count++);
/*     */     
/*  22 */     return pathPoint;
/*     */   }
/*     */   
/*     */   public void clearPath() {
/*  26 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint dequeue() {
/*  34 */     PathPoint pathPoint = this.pathPoints[0];
/*  35 */     this.pathPoints[0] = this.pathPoints[--this.count];
/*  36 */     this.pathPoints[this.count] = null;
/*  37 */     if (this.count > 0) sortForward(0); 
/*  38 */     pathPoint.index = -1;
/*  39 */     return pathPoint;
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
/*     */   public void changeDistance(PathPoint pathPoint, float f) {
/*  58 */     float f1 = pathPoint.distanceToTarget;
/*  59 */     pathPoint.distanceToTarget = f;
/*  60 */     if (f < f1) {
/*  61 */       sortBack(pathPoint.index);
/*     */     } else {
/*  63 */       sortForward(pathPoint.index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sortBack(int i) {
/*  72 */     PathPoint pathPoint = this.pathPoints[i];
/*  73 */     float f = pathPoint.distanceToTarget;
/*  74 */     while (i > 0) {
/*  75 */       int j = i - 1 >> 1;
/*  76 */       PathPoint pathPoint1 = this.pathPoints[j];
/*  77 */       if (f < pathPoint1.distanceToTarget) {
/*  78 */         this.pathPoints[i] = pathPoint1;
/*  79 */         pathPoint1.index = i;
/*  80 */         i = j;
/*     */       } 
/*     */     } 
/*  83 */     this.pathPoints[i] = pathPoint;
/*  84 */     pathPoint.index = i;
/*     */   }
/*     */   
/*     */   private void sortForward(int i) {
/*  88 */     PathPoint pathPoint = this.pathPoints[i];
/*  89 */     float f = pathPoint.distanceToTarget; while (true) {
/*     */       PathPoint pathPoint2;
/*     */       float f2;
/*  92 */       int j = 1 + (i << 1);
/*  93 */       int k = j + 1;
/*     */       
/*  95 */       if (j >= this.count) {
/*     */         break;
/*     */       }
/*  98 */       PathPoint pathPoint1 = this.pathPoints[j];
/*  99 */       float f1 = pathPoint1.distanceToTarget;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       if (k >= this.count) {
/*     */         
/* 106 */         pathPoint2 = null;
/* 107 */         f2 = Float.POSITIVE_INFINITY;
/*     */       } else {
/* 109 */         pathPoint2 = this.pathPoints[k];
/* 110 */         f2 = pathPoint2.distanceToTarget;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 115 */       if (f1 < f2) {
/* 116 */         if (f1 < f) {
/* 117 */           this.pathPoints[i] = pathPoint1;
/* 118 */           pathPoint1.index = i;
/* 119 */           i = j; continue;
/*     */         }  break;
/*     */       } 
/* 122 */       if (f2 < f) {
/* 123 */         this.pathPoints[i] = pathPoint2;
/* 124 */         pathPoint2.index = i;
/* 125 */         i = k;
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 130 */     this.pathPoints[i] = pathPoint;
/* 131 */     pathPoint.index = i;
/*     */   }
/*     */   
/*     */   public boolean isPathEmpty() {
/* 135 */     return (this.count == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Path.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */