/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CallableMemoryInfo
/*    */   implements Callable
/*    */ {
/*    */   CallableMemoryInfo(CrashReport crashReport) {}
/*    */   
/*    */   public String getMemoryInfoAsString() {
/* 63 */     Runtime runtime = Runtime.getRuntime();
/* 64 */     long l1 = runtime.maxMemory();
/* 65 */     long l2 = runtime.totalMemory();
/* 66 */     long l3 = runtime.freeMemory();
/* 67 */     long l4 = l1 / 1024L / 1024L;
/* 68 */     long l5 = l2 / 1024L / 1024L;
/* 69 */     long l6 = l3 / 1024L / 1024L;
/*    */     
/* 71 */     return l3 + " bytes (" + l6 + " MB) / " + l2 + " bytes (" + l5 + " MB) up to " + l1 + " bytes (" + l4 + " MB)";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableMemoryInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */