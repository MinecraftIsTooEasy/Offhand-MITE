/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ public class Timer
/*    */ {
/*    */   float ticksPerSecond;
/*    */   private double lastHRTime;
/*    */   public int elapsedTicks;
/*    */   public float renderPartialTicks;
/* 10 */   public float timerSpeed = 1.0F;
/*    */   
/*    */   public float elapsedPartialTicks;
/*    */   private long lastSyncSysClock;
/*    */   private long lastSyncHRClock;
/*    */   private long field_74285_i;
/* 16 */   private double timeSyncAdjustment = 1.0D;
/*    */   
/*    */   public Timer(float f) {
/* 19 */     this.ticksPerSecond = f;
/* 20 */     this.lastSyncSysClock = Minecraft.getSystemTime();
/* 21 */     this.lastSyncHRClock = System.nanoTime() / 1000000L;
/*    */   }
/*    */   
/*    */   public void updateTimer() {
/* 25 */     long l1 = Minecraft.getSystemTime();
/* 26 */     long l2 = l1 - this.lastSyncSysClock;
/* 27 */     long l3 = System.nanoTime() / 1000000L;
/* 28 */     double d1 = l3 / 1000.0D;
/*    */     
/* 30 */     if (l2 > 1000L || l2 < 0L) {
/* 31 */       this.lastHRTime = d1;
/*    */     } else {
/* 33 */       this.field_74285_i += l2;
/* 34 */       if (this.field_74285_i > 1000L) {
/* 35 */         long l = l3 - this.lastSyncHRClock;
/*    */         
/* 37 */         double d = this.field_74285_i / l;
/* 38 */         this.timeSyncAdjustment += (d - this.timeSyncAdjustment) * 0.20000000298023224D;
/*    */         
/* 40 */         this.lastSyncHRClock = l3;
/* 41 */         this.field_74285_i = 0L;
/*    */       } 
/* 43 */       if (this.field_74285_i < 0L) {
/* 44 */         this.lastSyncHRClock = l3;
/*    */       }
/*    */     } 
/* 47 */     this.lastSyncSysClock = l1;
/*    */     
/* 49 */     double d2 = (d1 - this.lastHRTime) * this.timeSyncAdjustment;
/* 50 */     this.lastHRTime = d1;
/*    */     
/* 52 */     if (d2 < 0.0D) d2 = 0.0D; 
/* 53 */     if (d2 > 1.0D) d2 = 1.0D;
/*    */     
/* 55 */     this.elapsedPartialTicks = (float)(this.elapsedPartialTicks + d2 * this.timerSpeed * this.ticksPerSecond);
/*    */     
/* 57 */     this.elapsedTicks = (int)this.elapsedPartialTicks;
/* 58 */     this.elapsedPartialTicks -= this.elapsedTicks;
/* 59 */     if (this.elapsedTicks > 10) this.elapsedTicks = 10; 
/* 60 */     this.renderPartialTicks = this.elapsedPartialTicks;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Timer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */