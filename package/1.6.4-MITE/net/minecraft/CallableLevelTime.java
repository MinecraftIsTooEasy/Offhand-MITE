/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ 
/*    */ 
/*    */ class CallableLevelTime
/*    */   implements Callable
/*    */ {
/*    */   final WorldInfo worldInfoInstance;
/*    */   
/*    */   CallableLevelTime(WorldInfo par1WorldInfo) {
/* 13 */     this.worldInfoInstance = par1WorldInfo;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String callLevelTime() {
/* 20 */     return String.format("%d game time, %d day time", new Object[] { Long.valueOf(WorldInfo.func_85126_g(this.worldInfoInstance)), Long.valueOf(WorldInfo.getWorldTimeOfDay(this.worldInfoInstance, 0)) });
/*    */   }
/*    */ 
/*    */   
/*    */   public Object call() {
/* 25 */     return callLevelTime();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableLevelTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */