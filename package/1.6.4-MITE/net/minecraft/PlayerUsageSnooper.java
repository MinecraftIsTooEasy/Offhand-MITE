/*     */ package net.minecraft;
/*     */ 
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ public class PlayerUsageSnooper
/*     */ {
/*  17 */   private Map dataMap = new HashMap<Object, Object>();
/*     */   
/*  19 */   private final String uniqueID = UUID.randomUUID().toString();
/*     */   private final URL serverUrl;
/*     */   private final IPlayerUsage playerStatsCollector;
/*  22 */   private final Timer threadTrigger = new Timer("Snooper Timer", true);
/*  23 */   private final Object syncLock = new Object();
/*     */   private final long field_98224_g;
/*     */   private boolean isRunning;
/*     */   private int selfCounter;
/*     */   
/*     */   public PlayerUsageSnooper(String string, IPlayerUsage iPlayerUsage, long l) {
/*     */     try {
/*  30 */       this.serverUrl = new URL("http://snoop.minecraft.net/" + string + "?version=" + '\001');
/*  31 */     } catch (MalformedURLException malformedURLException) {
/*  32 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     
/*  35 */     this.playerStatsCollector = iPlayerUsage;
/*  36 */     this.field_98224_g = l;
/*     */   }
/*     */   
/*     */   public void startSnooper() {
/*  40 */     if (this.isRunning)
/*  41 */       return;  this.isRunning = true;
/*     */     
/*  43 */     addBaseDataToSnooper();
/*     */     
/*  45 */     this.threadTrigger.schedule(new PlayerUsageSnooperThread(this), 0L, 900000L);
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
/*     */   private void addBaseDataToSnooper() {
/*  62 */     addJvmArgsToSnooper();
/*     */     
/*  64 */     addData("snooper_token", this.uniqueID);
/*  65 */     addData("os_name", System.getProperty("os.name"));
/*  66 */     addData("os_version", System.getProperty("os.version"));
/*  67 */     addData("os_architecture", System.getProperty("os.arch"));
/*  68 */     addData("java_version", System.getProperty("java.version"));
/*  69 */     addData("version", "1.6.4");
/*     */     
/*  71 */     this.playerStatsCollector.addServerTypeToSnooper(this);
/*     */   }
/*     */   
/*     */   private void addJvmArgsToSnooper() {
/*  75 */     RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
/*  76 */     List<String> list = runtimeMXBean.getInputArguments();
/*  77 */     byte b = 0;
/*     */     
/*  79 */     for (String str : list) {
/*  80 */       if (str.startsWith("-X")) {
/*  81 */         addData("jvm_arg[" + b++ + "]", str);
/*     */       }
/*     */     } 
/*     */     
/*  85 */     addData("jvm_args", Integer.valueOf(b));
/*     */   }
/*     */   
/*     */   public void addMemoryStatsToSnooper() {
/*  89 */     addData("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
/*  90 */     addData("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
/*  91 */     addData("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
/*  92 */     addData("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
/*     */     
/*  94 */     this.playerStatsCollector.addServerStatsToSnooper(this);
/*     */   }
/*     */   
/*     */   public void addData(String string, Object object) {
/*  98 */     synchronized (this.syncLock) {
/*  99 */       this.dataMap.put(string, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map getCurrentStats() {
/* 104 */     LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
/*     */     
/* 106 */     synchronized (this.syncLock) {
/* 107 */       addMemoryStatsToSnooper();
/*     */       
/* 109 */       for (Map.Entry entry : this.dataMap.entrySet()) {
/* 110 */         linkedHashMap.put(entry.getKey(), entry.getValue().toString());
/*     */       }
/*     */     } 
/*     */     
/* 114 */     return linkedHashMap;
/*     */   }
/*     */   
/*     */   public boolean isSnooperRunning() {
/* 118 */     return this.isRunning;
/*     */   }
/*     */   
/*     */   public void stopSnooper() {
/* 122 */     this.threadTrigger.cancel();
/*     */   }
/*     */   
/*     */   public String getUniqueID() {
/* 126 */     return this.uniqueID;
/*     */   }
/*     */   
/*     */   public long func_130105_g() {
/* 130 */     return this.field_98224_g;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerUsageSnooper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */