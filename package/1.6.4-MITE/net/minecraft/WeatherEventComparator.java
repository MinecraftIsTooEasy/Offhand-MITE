/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeatherEventComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compareWeatherEvents(WeatherEvent a, WeatherEvent b) {
/* 11 */     return (a.start < b.start) ? -1 : ((a.start > b.start) ? 1 : ((a.end < b.end) ? -1 : ((a.end > b.end) ? 1 : 0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public int compare(Object a, Object b) {
/* 16 */     return compareWeatherEvents((WeatherEvent)a, (WeatherEvent)b);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WeatherEventComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */