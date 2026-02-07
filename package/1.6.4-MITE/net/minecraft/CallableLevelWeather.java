/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ 
/*    */ 
/*    */ class CallableLevelWeather
/*    */   implements Callable
/*    */ {
/*    */   final WorldInfo worldInfoInstance;
/*    */   
/*    */   CallableLevelWeather(WorldInfo par1WorldInfo) {
/* 13 */     this.worldInfoInstance = par1WorldInfo;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String callLevelWeatherInfo() {
/* 19 */     return "Information removed as of R132";
/*    */   }
/*    */ 
/*    */   
/*    */   public Object call() {
/* 24 */     return callLevelWeatherInfo();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableLevelWeather.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */