/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WeatherEvent
/*     */ {
/*     */   public int type;
/*     */   public long start;
/*     */   public int duration;
/*     */   public long end;
/*     */   public boolean removed;
/*     */   public long start_of_storm;
/*     */   public int duration_of_storm;
/*     */   public long end_of_storm;
/*     */   
/*     */   public WeatherEvent(long start, int duration) {
/*  24 */     this.start = start;
/*  25 */     this.duration = duration;
/*  26 */     this.end = start + duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartAndEnd(long start, long end) {
/*  31 */     this.start = start;
/*  32 */     this.end = end;
/*     */     
/*  34 */     this.duration = (int)(end - start);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomizeType() {
/*  40 */     if (hasStorm()) {
/*     */       
/*  42 */       this.type = 3;
/*     */       
/*     */       return;
/*     */     } 
/*  46 */     Random random = new Random(this.start);
/*     */     
/*  48 */     random.nextInt();
/*     */     
/*  50 */     this.type = random.nextInt(3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorm(long start_of_storm, long end_of_storm) {
/*  55 */     this.start_of_storm = start_of_storm;
/*  56 */     this.end_of_storm = end_of_storm;
/*  57 */     this.duration_of_storm = (int)(end_of_storm - start_of_storm);
/*     */     
/*  59 */     if (hasStorm()) {
/*  60 */       this.type = 3;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addStorm() {
/*  66 */     if (hasStorm()) {
/*     */       return;
/*     */     }
/*  69 */     Random random = new Random(this.start);
/*     */     
/*  71 */     random.nextInt();
/*     */     
/*  73 */     if (random.nextInt(4) > 0) {
/*     */       return;
/*     */     }
/*  76 */     this.duration_of_storm = Math.min(random.nextInt(4000) + 2000, this.duration);
/*     */     
/*  78 */     if (random.nextInt(4) == 0) {
/*     */       
/*  80 */       if (random.nextBoolean()) {
/*  81 */         this.start_of_storm = this.start;
/*     */       } else {
/*  83 */         this.start_of_storm = this.end - this.duration_of_storm;
/*     */       } 
/*     */     } else {
/*     */       
/*  87 */       this.start_of_storm = random.nextInt(this.duration - this.duration_of_storm + 1) + this.start;
/*     */     } 
/*     */     
/*  90 */     this.end_of_storm = this.start_of_storm + this.duration_of_storm;
/*     */     
/*  92 */     this.type = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStorm() {
/*  97 */     return (this.start_of_storm > 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOccurringAt(long unadjusted_tick) {
/* 102 */     return (this.start <= unadjusted_tick && this.end > unadjusted_tick);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPrecipitatingAt(long unadjusted_tick) {
/* 108 */     return isOccurringAt(unadjusted_tick);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startsPrecipitating(long unadjusted_tick_from, long unadjusted_tick_to) {
/* 114 */     return (this.start >= unadjusted_tick_from && this.start <= unadjusted_tick_to);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStormingAt(long unadjusted_tick) {
/* 119 */     return (hasStorm() && this.start_of_storm <= unadjusted_tick && this.end_of_storm > unadjusted_tick);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startsStorming(long unadjusted_tick_from, long unadjusted_tick_to) {
/* 125 */     return (hasStorm() && this.start_of_storm >= unadjusted_tick_from && this.start_of_storm <= unadjusted_tick_to);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printWeatherEvents(List<WeatherEvent> list) {
/* 130 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 132 */       WeatherEvent event = list.get(i);
/* 133 */       Debug.println("[" + i + "] " + event);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 139 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 141 */     sb.append("WeatherEvent on Day ");
/*     */     
/* 143 */     int start_day = World.getDayOfWorld(this.start);
/* 144 */     int end_day = World.getDayOfWorld(this.end);
/*     */     
/* 146 */     sb.append(start_day);
/*     */     
/* 148 */     if (end_day != start_day) {
/* 149 */       sb.append(" and " + end_day);
/*     */     }
/* 151 */     sb.append(": Rain from " + this.start + " to " + this.end + " (duration=" + this.duration + ")");
/*     */     
/* 153 */     if (hasStorm()) {
/* 154 */       sb.append(" and Storm from " + this.start_of_storm + " to " + this.end_of_storm + " (duration=" + this.duration_of_storm + ")");
/*     */     }
/* 156 */     sb.append(", Wind=" + this.type);
/*     */     
/* 158 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WeatherEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */