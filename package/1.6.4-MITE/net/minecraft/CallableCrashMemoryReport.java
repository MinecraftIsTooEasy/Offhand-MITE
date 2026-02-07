/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CallableCrashMemoryReport
/*     */   implements Callable
/*     */ {
/*     */   CallableCrashMemoryReport(CrashReport crashReport) {}
/*     */   
/*     */   public String getMemoryReport() {
/* 100 */     int i = AxisAlignedBB.getAABBPool().getlistAABBsize();
/* 101 */     int j = 56 * i;
/* 102 */     int k = j / 1024 / 1024;
/* 103 */     int m = AxisAlignedBB.getAABBPool().getnextPoolIndex();
/* 104 */     int n = 56 * m;
/* 105 */     int i1 = n / 1024 / 1024;
/*     */     
/* 107 */     return i + " (" + j + " bytes; " + k + " MB) allocated, " + m + " (" + n + " bytes; " + i1 + " MB) used";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableCrashMemoryReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */