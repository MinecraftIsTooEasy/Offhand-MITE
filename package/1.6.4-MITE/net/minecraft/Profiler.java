/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class Profiler
/*     */ {
/*  13 */   private final List sectionList = new ArrayList();
/*     */ 
/*     */   
/*  16 */   private final List timestampList = new ArrayList();
/*     */ 
/*     */   
/*     */   public boolean profilingEnabled;
/*     */ 
/*     */   
/*  22 */   private String profilingSection = "";
/*     */ 
/*     */   
/*  25 */   private final Map profilingMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearProfiling() {
/*  32 */     this.profilingMap.clear();
/*  33 */     this.profilingSection = "";
/*  34 */     this.sectionList.clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startSection(String par1Str) {}
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
/*     */   public void endSection() {}
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
/*     */   public List getProfilingData(String par1Str) {
/*  90 */     if (!this.profilingEnabled)
/*     */     {
/*  92 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  96 */     long var3 = this.profilingMap.containsKey("root") ? ((Long)this.profilingMap.get("root")).longValue() : 0L;
/*  97 */     long var5 = this.profilingMap.containsKey(par1Str) ? ((Long)this.profilingMap.get(par1Str)).longValue() : -1L;
/*  98 */     ArrayList<ProfilerResult> var7 = new ArrayList();
/*     */     
/* 100 */     if (par1Str.length() > 0)
/*     */     {
/* 102 */       par1Str = par1Str + ".";
/*     */     }
/*     */     
/* 105 */     long var8 = 0L;
/* 106 */     Iterator<String> var10 = this.profilingMap.keySet().iterator();
/*     */     
/* 108 */     while (var10.hasNext()) {
/*     */       
/* 110 */       String var11 = var10.next();
/*     */       
/* 112 */       if (var11.length() > par1Str.length() && var11.startsWith(par1Str) && var11.indexOf(".", par1Str.length() + 1) < 0)
/*     */       {
/* 114 */         var8 += ((Long)this.profilingMap.get(var11)).longValue();
/*     */       }
/*     */     } 
/*     */     
/* 118 */     float var21 = (float)var8;
/*     */     
/* 120 */     if (var8 < var5)
/*     */     {
/* 122 */       var8 = var5;
/*     */     }
/*     */     
/* 125 */     if (var3 < var8)
/*     */     {
/* 127 */       var3 = var8;
/*     */     }
/*     */     
/* 130 */     Iterator<String> var20 = this.profilingMap.keySet().iterator();
/*     */ 
/*     */     
/* 133 */     while (var20.hasNext()) {
/*     */       
/* 135 */       String var12 = var20.next();
/*     */       
/* 137 */       if (var12.length() > par1Str.length() && var12.startsWith(par1Str) && var12.indexOf(".", par1Str.length() + 1) < 0) {
/*     */         
/* 139 */         long var13 = ((Long)this.profilingMap.get(var12)).longValue();
/* 140 */         double var15 = var13 * 100.0D / var8;
/* 141 */         double var17 = var13 * 100.0D / var3;
/* 142 */         String var19 = var12.substring(par1Str.length());
/* 143 */         var7.add(new ProfilerResult(var19, var15, var17));
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     var20 = this.profilingMap.keySet().iterator();
/*     */     
/* 149 */     while (var20.hasNext()) {
/*     */       
/* 151 */       String var12 = var20.next();
/* 152 */       this.profilingMap.put(var12, Long.valueOf(((Long)this.profilingMap.get(var12)).longValue() * 999L / 1000L));
/*     */     } 
/*     */     
/* 155 */     if ((float)var8 > var21)
/*     */     {
/* 157 */       var7.add(new ProfilerResult("unspecified", ((float)var8 - var21) * 100.0D / var8, ((float)var8 - var21) * 100.0D / var3));
/*     */     }
/*     */     
/* 160 */     Collections.sort(var7);
/* 161 */     var7.add(0, new ProfilerResult(par1Str, 100.0D, var8 * 100.0D / var3));
/* 162 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endStartSection(String par1Str) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameOfLastSection() {
/* 177 */     return (this.sectionList.size() == 0) ? "[UNKNOWN]" : this.sectionList.get(this.sectionList.size() - 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Profiler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */