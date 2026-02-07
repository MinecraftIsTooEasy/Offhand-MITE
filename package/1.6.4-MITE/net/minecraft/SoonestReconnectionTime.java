/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoonestReconnectionTime
/*    */ {
/*    */   public String username;
/*    */   public long tick_of_disconnection;
/*    */   public int adjusted_hour_of_disconnection;
/*    */   public long soonest_reconnection_tick;
/*    */   public long ticks_disconnected;
/*    */   
/*    */   public SoonestReconnectionTime(EntityPlayer player) {
/* 20 */     update(player);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update(EntityPlayer player) {
/* 25 */     World world = player.worldObj;
/*    */     
/* 27 */     this.username = player.username;
/* 28 */     this.tick_of_disconnection = world.getTotalWorldTime();
/*    */     
/* 30 */     int hour_of_sunrise = World.getTimeOfSunrise() / 1000;
/* 31 */     int hour_of_latest_reconnection = World.getHourOfLatestReconnection();
/*    */     
/* 33 */     this.adjusted_hour_of_disconnection = world.getHourOfDay();
/*    */     
/* 35 */     if (this.adjusted_hour_of_disconnection < hour_of_sunrise || this.adjusted_hour_of_disconnection > hour_of_latest_reconnection) {
/* 36 */       this.adjusted_hour_of_disconnection = hour_of_latest_reconnection;
/*    */     }
/* 38 */     int ticks_until_midnight = 24000 - world.getAdjustedTimeOfDay();
/* 39 */     int ticks_to_wait = ticks_until_midnight + this.adjusted_hour_of_disconnection * 1000;
/*    */     
/* 41 */     this.soonest_reconnection_tick = this.tick_of_disconnection + ticks_to_wait;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SoonestReconnectionTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */