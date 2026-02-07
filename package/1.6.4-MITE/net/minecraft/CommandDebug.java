/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandDebug
/*     */   extends CommandBase
/*     */ {
/*     */   private long startTime;
/*     */   private int startTicks;
/*     */   
/*     */   public String getCommandName() {
/*  23 */     return "debug";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  28 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender iCommandSender) {
/*  33 */     return "commands.debug.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/*  38 */     if (strings.length == 1) {
/*  39 */       if (strings[0].equals("start")) {
/*  40 */         notifyAdmins(iCommandSender, "commands.debug.start", new Object[0]);
/*     */         
/*  42 */         MinecraftServer.getServer().enableProfiling();
/*  43 */         this.startTime = MinecraftServer.getSystemTimeMillis();
/*  44 */         this.startTicks = MinecraftServer.getServer().getTickCounter(); return;
/*     */       } 
/*  46 */       if (strings[0].equals("stop")) {
/*  47 */         if (!(MinecraftServer.getServer()).theProfiler.profilingEnabled) {
/*  48 */           throw new CommandException("commands.debug.notStarted", new Object[0]);
/*     */         }
/*     */         
/*  51 */         long l1 = MinecraftServer.getSystemTimeMillis();
/*  52 */         int i = MinecraftServer.getServer().getTickCounter();
/*     */         
/*  54 */         long l2 = l1 - this.startTime;
/*  55 */         int j = i - this.startTicks;
/*     */         
/*  57 */         saveProfilerResults(l2, j);
/*     */         
/*  59 */         (MinecraftServer.getServer()).theProfiler.profilingEnabled = false;
/*  60 */         notifyAdmins(iCommandSender, "commands.debug.stop", new Object[] { Float.valueOf((float)l2 / 1000.0F), Integer.valueOf(j) });
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  65 */     throw new WrongUsageException("commands.debug.usage", new Object[0]);
/*     */   }
/*     */   
/*     */   private void saveProfilerResults(long l, int i) {
/*  69 */     File file = new File(MinecraftServer.getServer().getFile("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
/*     */     
/*  71 */     file.getParentFile().mkdirs();
/*     */     
/*     */     try {
/*  74 */       FileWriter fileWriter = new FileWriter(file);
/*  75 */       fileWriter.write(getProfilerResults(l, i));
/*  76 */       fileWriter.close();
/*  77 */     } catch (Throwable throwable) {
/*  78 */       MinecraftServer.getServer().getLogAgent().logSevereException("Could not save profiler results to " + file, throwable);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getProfilerResults(long l, int i) {
/*  83 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*  85 */     stringBuilder.append("---- Minecraft Profiler Results ----\n");
/*  86 */     stringBuilder.append("// ");
/*  87 */     stringBuilder.append(getWittyComment());
/*  88 */     stringBuilder.append("\n\n");
/*     */     
/*  90 */     stringBuilder.append("Time span: ").append(l).append(" ms\n");
/*  91 */     stringBuilder.append("Tick span: ").append(i).append(" ticks\n");
/*  92 */     stringBuilder.append("// This is approximately ").append(String.format("%.2f", new Object[] { Float.valueOf(i / (float)l / 1000.0F) })).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
/*     */ 
/*     */     
/*  95 */     stringBuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
/*     */     
/*  97 */     getProfileDump(0, "root", stringBuilder);
/*     */     
/*  99 */     stringBuilder.append("--- END PROFILE DUMP ---\n\n");
/*     */     
/* 101 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   private void getProfileDump(int i, String string, StringBuilder stringBuilder) {
/* 105 */     List<ProfilerResult> list = (MinecraftServer.getServer()).theProfiler.getProfilingData(string);
/* 106 */     if (list == null || list.size() < 3)
/*     */       return; 
/* 108 */     for (byte b = 1; b < list.size(); b++) {
/* 109 */       ProfilerResult profilerResult = list.get(b);
/*     */       
/* 111 */       stringBuilder.append(String.format("[%02d] ", new Object[] { Integer.valueOf(i) }));
/* 112 */       for (byte b1 = 0; b1 < i; b1++)
/* 113 */         stringBuilder.append(" "); 
/* 114 */       stringBuilder.append(profilerResult.field_76331_c);
/* 115 */       stringBuilder.append(" - ");
/* 116 */       stringBuilder.append(String.format("%.2f", new Object[] { Double.valueOf(profilerResult.field_76332_a) }));
/* 117 */       stringBuilder.append("%/");
/* 118 */       stringBuilder.append(String.format("%.2f", new Object[] { Double.valueOf(profilerResult.field_76330_b) }));
/* 119 */       stringBuilder.append("%\n");
/*     */       
/* 121 */       if (!profilerResult.field_76331_c.equals("unspecified")) {
/*     */         try {
/* 123 */           getProfileDump(i + 1, string + "." + profilerResult.field_76331_c, stringBuilder);
/* 124 */         } catch (Exception exception) {
/* 125 */           stringBuilder.append("[[ EXCEPTION " + exception + " ]]");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getWittyComment() {
/* 133 */     String[] arrayOfString = { "Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server." };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 140 */       return arrayOfString[(int)(System.nanoTime() % arrayOfString.length)];
/* 141 */     } catch (Throwable throwable) {
/* 142 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 148 */     if (strings.length == 1) return getListOfStringsMatchingLastWord(strings, new String[] { "start", "stop" });
/*     */     
/* 150 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandDebug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */