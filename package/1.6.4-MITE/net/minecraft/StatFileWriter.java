/*     */ package net.minecraft;
/*     */ 
/*     */ import argo.jdom.JdomParser;
/*     */ import argo.jdom.JsonNode;
/*     */ import argo.jdom.JsonRootNode;
/*     */ import argo.jdom.JsonStringNode;
/*     */ import argo.saj.InvalidSyntaxException;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatFileWriter
/*     */ {
/*  18 */   private Map field_77457_a = new HashMap<Object, Object>();
/*  19 */   private Map field_77455_b = new HashMap<Object, Object>();
/*     */   
/*     */   private boolean field_77456_c;
/*     */   private StatsSyncher statsSyncher;
/*     */   
/*     */   public StatFileWriter(Session par1Session, File par2File) {
/*  25 */     File var3 = new File(par2File, "stats");
/*     */     
/*  27 */     if (!var3.exists())
/*     */     {
/*  29 */       var3.mkdir();
/*     */     }
/*     */     
/*  32 */     File[] var4 = par2File.listFiles();
/*  33 */     int var5 = var4.length;
/*     */     
/*  35 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  37 */       File var7 = var4[var6];
/*     */       
/*  39 */       if (var7.getName().startsWith("stats_") && var7.getName().endsWith(".dat")) {
/*     */         
/*  41 */         File var8 = new File(var3, var7.getName());
/*     */         
/*  43 */         if (!var8.exists()) {
/*     */           
/*  45 */           System.out.println("Relocating " + var7.getName());
/*  46 */           var7.renameTo(var8);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  51 */     this.statsSyncher = new StatsSyncher(par1Session, this, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readStat(StatBase par1StatBase, int par2) {
/*  56 */     writeStatToMap(this.field_77455_b, par1StatBase, par2);
/*  57 */     writeStatToMap(this.field_77457_a, par1StatBase, par2);
/*  58 */     this.field_77456_c = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeStatToMap(Map<StatBase, Integer> par1Map, StatBase par2StatBase, int par3) {
/*  63 */     Integer var4 = (Integer)par1Map.get(par2StatBase);
/*  64 */     int var5 = (var4 == null) ? 0 : var4.intValue();
/*  65 */     par1Map.put(par2StatBase, Integer.valueOf(var5 + par3));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_77445_b() {
/*  70 */     return new HashMap<Object, Object>(this.field_77455_b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeStats(Map par1Map) {
/*  78 */     if (par1Map != null) {
/*     */       
/*  80 */       this.field_77456_c = true;
/*  81 */       Iterator<StatBase> var2 = par1Map.keySet().iterator();
/*     */       
/*  83 */       while (var2.hasNext()) {
/*     */         
/*  85 */         StatBase var3 = var2.next();
/*  86 */         writeStatToMap(this.field_77455_b, var3, ((Integer)par1Map.get(var3)).intValue());
/*  87 */         writeStatToMap(this.field_77457_a, var3, ((Integer)par1Map.get(var3)).intValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77452_b(Map par1Map) {
/*  94 */     if (par1Map != null) {
/*     */       
/*  96 */       Iterator<StatBase> var2 = par1Map.keySet().iterator();
/*     */       
/*  98 */       while (var2.hasNext()) {
/*     */         
/* 100 */         StatBase var3 = var2.next();
/* 101 */         Integer var4 = (Integer)this.field_77455_b.get(var3);
/* 102 */         int var5 = (var4 == null) ? 0 : var4.intValue();
/* 103 */         this.field_77457_a.put(var3, Integer.valueOf(((Integer)par1Map.get(var3)).intValue() + var5));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77448_c(Map par1Map) {
/* 110 */     if (par1Map != null) {
/*     */       
/* 112 */       this.field_77456_c = true;
/* 113 */       Iterator<StatBase> var2 = par1Map.keySet().iterator();
/*     */       
/* 115 */       while (var2.hasNext()) {
/*     */         
/* 117 */         StatBase var3 = var2.next();
/* 118 */         writeStatToMap(this.field_77455_b, var3, ((Integer)par1Map.get(var3)).intValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map func_77453_b(String par0Str) {
/* 125 */     HashMap<Object, Object> var1 = new HashMap<Object, Object>();
/*     */ 
/*     */     
/*     */     try {
/* 129 */       String var2 = "local";
/* 130 */       StringBuilder var3 = new StringBuilder();
/* 131 */       JsonRootNode var4 = (new JdomParser()).parse(par0Str);
/* 132 */       List var5 = var4.getArrayNode(new Object[] { "stats-change" });
/* 133 */       Iterator<JsonNode> var6 = var5.iterator();
/*     */       
/* 135 */       while (var6.hasNext()) {
/*     */         
/* 137 */         JsonNode var7 = var6.next();
/* 138 */         Map var8 = var7.getFields();
/* 139 */         Map.Entry var9 = var8.entrySet().iterator().next();
/* 140 */         int var10 = Integer.parseInt(((JsonStringNode)var9.getKey()).getText());
/* 141 */         int var11 = Integer.parseInt(((JsonNode)var9.getValue()).getText());
/* 142 */         boolean var12 = true;
/* 143 */         StatBase var13 = StatList.getOneShotStat(var10);
/*     */         
/* 145 */         if (var13 == null) {
/*     */           
/* 147 */           var12 = false;
/* 148 */           var13 = (new StatPlaceholder(var10)).registerStat();
/*     */         } 
/*     */         
/* 151 */         var3.append((StatList.getOneShotStat(var10)).statGuid).append(",");
/* 152 */         var3.append(var11).append(",");
/*     */         
/* 154 */         if (var12)
/*     */         {
/* 156 */           var1.put(var13, Integer.valueOf(var11));
/*     */         }
/*     */       } 
/*     */       
/* 160 */       MD5String var15 = new MD5String(var2);
/* 161 */       String var16 = var15.getMD5String(var3.toString());
/*     */       
/* 163 */       if (!var16.equals(var4.getStringValue(new Object[] { "checksum" })))
/*     */       {
/* 165 */         System.out.println("CHECKSUM MISMATCH");
/* 166 */         return null;
/*     */       }
/*     */     
/* 169 */     } catch (InvalidSyntaxException var14) {
/*     */       
/* 171 */       var14.printStackTrace();
/*     */     } 
/*     */     
/* 174 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_77441_a(String par0Str, String par1Str, Map par2Map) {
/* 179 */     StringBuilder var3 = new StringBuilder();
/* 180 */     StringBuilder var4 = new StringBuilder();
/* 181 */     boolean var5 = true;
/* 182 */     var3.append("{\r\n");
/*     */     
/* 184 */     if (par0Str != null && par1Str != null) {
/*     */       
/* 186 */       var3.append("  \"user\":{\r\n");
/* 187 */       var3.append("    \"name\":\"").append(par0Str).append("\",\r\n");
/* 188 */       var3.append("    \"sessionid\":\"").append(par1Str).append("\"\r\n");
/* 189 */       var3.append("  },\r\n");
/*     */     } 
/*     */     
/* 192 */     var3.append("  \"stats-change\":[");
/* 193 */     Iterator<StatBase> var6 = par2Map.keySet().iterator();
/*     */     
/* 195 */     while (var6.hasNext()) {
/*     */       
/* 197 */       StatBase var7 = var6.next();
/*     */       
/* 199 */       if (var5) {
/*     */         
/* 201 */         var5 = false;
/*     */       }
/*     */       else {
/*     */         
/* 205 */         var3.append("},");
/*     */       } 
/*     */       
/* 208 */       var3.append("\r\n    {\"").append(var7.statId).append("\":").append(par2Map.get(var7));
/* 209 */       var4.append(var7.statGuid).append(",");
/* 210 */       var4.append(par2Map.get(var7)).append(",");
/*     */     } 
/*     */     
/* 213 */     if (!var5)
/*     */     {
/* 215 */       var3.append("}");
/*     */     }
/*     */     
/* 218 */     MD5String var8 = new MD5String(par1Str);
/* 219 */     var3.append("\r\n  ],\r\n");
/* 220 */     var3.append("  \"checksum\":\"").append(var8.getMD5String(var4.toString())).append("\"\r\n");
/* 221 */     var3.append("}");
/* 222 */     return var3.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAchievementUnlocked(Achievement par1Achievement) {
/* 230 */     return this.field_77457_a.containsKey(par1Achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAchievement(Achievement achievement) {
/* 235 */     this.field_77457_a.remove(achievement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUnlockAchievement(Achievement par1Achievement) {
/* 244 */     return haveAllParentAchievementsBeenUnlocked(par1Achievement);
/*     */   }
/*     */ 
/*     */   
/*     */   public int writeStat(StatBase par1StatBase) {
/* 249 */     Integer var2 = (Integer)this.field_77457_a.get(par1StatBase);
/* 250 */     return (var2 == null) ? 0 : var2.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncStats() {
/* 255 */     this.statsSyncher.syncStatsFileWithMap(func_77445_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77449_e() {
/* 260 */     if (this.field_77456_c && this.statsSyncher.func_77425_c())
/*     */     {
/* 262 */       this.statsSyncher.beginSendStats(func_77445_b());
/*     */     }
/*     */     
/* 265 */     this.statsSyncher.func_77422_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean haveAllParentAchievementsBeenUnlocked(Achievement achievement) {
/* 270 */     while (achievement.parentAchievement != null) {
/*     */       
/* 272 */       achievement = achievement.parentAchievement;
/*     */       
/* 274 */       if (!hasAchievementUnlocked(achievement)) {
/* 275 */         return false;
/*     */       }
/*     */     } 
/* 278 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StatFileWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */