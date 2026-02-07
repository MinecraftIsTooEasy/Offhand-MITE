/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Debug
/*     */ {
/*     */   public static boolean is_active;
/*     */   public static String general_info;
/*     */   public static String general_info_client;
/*     */   public static String general_info_server;
/*     */   public static int general_counter;
/*     */   public static String biome_info;
/*     */   public static String selected_object_info;
/*     */   public static String equipped_item_info;
/*     */   public static boolean flag;
/*     */   private static long t;
/*     */   public static boolean timer_enabled = true;
/*     */   public static Object object;
/*     */   
/*     */   public static void println(String text) {
/*  23 */     if (Minecraft.inDevMode()) {
/*  24 */       System.out.println(text);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setErrorMessage(String text) {
/*  30 */     setErrorMessage(text, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setErrorMessage(String text, boolean print_stack_trace) {
/*  36 */     if (Minecraft.inDevMode()) {
/*     */       
/*  38 */       Minecraft.setErrorMessage(text);
/*     */       
/*  40 */       if (print_stack_trace) {
/*  41 */         printStackTrace();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printStackTrace() {
/*  48 */     if (Minecraft.inDevMode()) {
/*  49 */       (new Exception()).printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void startTiming() {
/*  54 */     if (timer_enabled) {
/*  55 */       t = System.nanoTime();
/*     */     }
/*     */   }
/*     */   
/*     */   public static long stopTiming() {
/*  60 */     if (timer_enabled) {
/*     */       
/*  62 */       long elapsed_microseconds = (System.nanoTime() - t) / 1000L;
/*  63 */       System.out.println(elapsed_microseconds);
/*  64 */       return elapsed_microseconds;
/*     */     } 
/*     */     
/*  67 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long stopTiming(String text) {
/*  72 */     if (timer_enabled) {
/*     */       
/*  74 */       long elapsed_microseconds = (System.nanoTime() - t) / 1000L;
/*  75 */       System.out.println(elapsed_microseconds + ": " + text);
/*  76 */       return elapsed_microseconds;
/*     */     } 
/*     */     
/*  79 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long stopTiming(String text, int minimum_delay_microseconds) {
/*  84 */     if (timer_enabled) {
/*     */       
/*  86 */       long elapsed_microseconds = (System.nanoTime() - t) / 1000L;
/*     */       
/*  88 */       if (elapsed_microseconds >= minimum_delay_microseconds) {
/*  89 */         System.out.println(elapsed_microseconds + ": " + text);
/*     */       }
/*  91 */       return elapsed_microseconds;
/*     */     } 
/*     */     
/*  94 */     return 0L;
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
/*     */   public static int getBlockInQuestionX() {
/* 108 */     return -32;
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
/*     */   public static int getBlockInQuestionY() {
/* 122 */     return 67;
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
/*     */   public static int getBlockInQuestionZ() {
/* 136 */     return -936;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBlockInQuestion(int x, int y, int z) {
/* 141 */     return (x == getBlockInQuestionX() && y == getBlockInQuestionY() && z == getBlockInQuestionZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBlockInQuestion(Chunk chunk, int local_x, int y, int local_z) {
/* 146 */     return isBlockInQuestion(chunk.xPosition * 16 + local_x, y, chunk.zPosition * 16 + local_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isChunkInQuestion(Chunk chunk) {
/* 151 */     return (chunk.xPosition == getBlockInQuestionX() >> 4 && chunk.zPosition == getBlockInQuestionZ() >> 4);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Debug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */