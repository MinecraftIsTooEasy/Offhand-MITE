/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerStatsHelper
/*     */ {
/*     */   public static long getValueAsByte(int id, HashMap stats) {
/*  13 */     StatBase stat = StatList.getStat(id);
/*     */     
/*  15 */     if (!StatList.isEitherZeroOrOne(stat)) {
/*     */       
/*  17 */       Minecraft.setErrorMessage("getValueAsByte: stat isn't supposed to have byte value");
/*  18 */       return 0L;
/*     */     } 
/*     */     
/*  21 */     if (stats.containsKey(Integer.valueOf(id))) {
/*  22 */       return ((Byte)stats.get(Integer.valueOf(id))).byteValue();
/*     */     }
/*  24 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueAsInt(int id, HashMap stats) {
/*  30 */     StatBase stat = StatList.getStat(id);
/*     */     
/*  32 */     if (StatList.isEitherZeroOrOne(stat) || StatList.hasLongValue(stat)) {
/*     */       
/*  34 */       Minecraft.setErrorMessage("getValueAsInt: stat isn't supposed to have int value");
/*  35 */       return 0L;
/*     */     } 
/*     */     
/*  38 */     if (stats.containsKey(Integer.valueOf(id))) {
/*  39 */       return ((Integer)stats.get(Integer.valueOf(id))).intValue();
/*     */     }
/*  41 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueAsLong(int id, HashMap stats) {
/*  47 */     StatBase stat = StatList.getStat(id);
/*     */     
/*  49 */     if (!StatList.hasLongValue(stat)) {
/*     */       
/*  51 */       Minecraft.setErrorMessage("getValueAsLong: stat isn't supposed to have long value");
/*  52 */       return 0L;
/*     */     } 
/*     */     
/*  55 */     if (stats.containsKey(Integer.valueOf(id))) {
/*  56 */       return ((Long)stats.get(Integer.valueOf(id))).longValue();
/*     */     }
/*  58 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValue(int id, HashMap stats) {
/*  64 */     StatBase stat = StatList.getStat(id);
/*     */     
/*  66 */     if (stats.containsKey(Integer.valueOf(id))) {
/*     */       
/*  68 */       if (StatList.isEitherZeroOrOne(stat))
/*  69 */         return ((Byte)stats.get(Integer.valueOf(id))).byteValue(); 
/*  70 */       if (StatList.hasLongValue(stat)) {
/*  71 */         return ((Long)stats.get(Integer.valueOf(id))).longValue();
/*     */       }
/*  73 */       return ((Integer)stats.get(Integer.valueOf(id))).intValue();
/*     */     } 
/*     */     
/*  76 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueOnClientAsByte(int id) {
/*  82 */     return getValueAsByte(id, Minecraft.theMinecraft.thePlayer.stats);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueOnClientAsInt(int id) {
/*  88 */     return getValueAsInt(id, Minecraft.theMinecraft.thePlayer.stats);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueOnClientAsLong(int id) {
/*  94 */     return getValueAsLong(id, Minecraft.theMinecraft.thePlayer.stats);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueOnClient(int id) {
/* 100 */     return getValue(id, Minecraft.theMinecraft.thePlayer.stats);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getValueOnClient(StatBase stat) {
/* 106 */     return getValue(stat.statId, Minecraft.theMinecraft.thePlayer.stats);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasAchievementUnlocked(Achievement achievement) {
/* 112 */     return (getValueOnClient(achievement) == 1L);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerStatsHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */