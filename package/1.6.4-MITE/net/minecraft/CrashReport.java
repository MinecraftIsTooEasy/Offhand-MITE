/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrashReport
/*     */ {
/*     */   private final String description;
/*     */   private final Throwable cause;
/*  21 */   private final CrashReportCategory field_85061_c = new CrashReportCategory(this, "System Details");
/*     */ 
/*     */   
/*  24 */   private final List crashReportSections = new ArrayList();
/*     */   
/*     */   private File crashReportFile;
/*     */   
/*     */   private boolean field_85059_f = true;
/*  29 */   private StackTraceElement[] field_85060_g = new StackTraceElement[0];
/*     */ 
/*     */   
/*     */   public CrashReport(String par1Str, Throwable par2Throwable) {
/*  33 */     this.description = par1Str;
/*  34 */     this.cause = par2Throwable;
/*  35 */     populateEnvironment();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void populateEnvironment() {
/*  44 */     this.field_85061_c.addCrashSectionCallable("Minecraft Version", new CallableMinecraftVersion(this));
/*  45 */     this.field_85061_c.addCrashSectionCallable("Operating System", new CallableOSInfo(this));
/*  46 */     this.field_85061_c.addCrashSectionCallable("Java Version", new CallableJavaInfo(this));
/*  47 */     this.field_85061_c.addCrashSectionCallable("Java VM Version", new CallableJavaInfo2(this));
/*  48 */     this.field_85061_c.addCrashSectionCallable("Memory", new CallableMemoryInfo(this));
/*  49 */     this.field_85061_c.addCrashSectionCallable("JVM Flags", new CallableJVMFlags(this));
/*  50 */     this.field_85061_c.addCrashSectionCallable("AABB Pool Size", new CallableCrashMemoryReport(this));
/*  51 */     this.field_85061_c.addCrashSectionCallable("Suspicious classes", new CallableSuspiciousClasses(this));
/*  52 */     this.field_85061_c.addCrashSectionCallable("IntCache", new CallableIntCache(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  60 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getCrashCause() {
/*  68 */     return this.cause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSectionsInStringBuilder(StringBuilder par1StringBuilder) {
/*  76 */     if (this.field_85060_g != null && this.field_85060_g.length > 0) {
/*     */       
/*  78 */       par1StringBuilder.append("-- Head --\n");
/*  79 */       par1StringBuilder.append("Stacktrace:\n");
/*  80 */       StackTraceElement[] var2 = this.field_85060_g;
/*  81 */       int var3 = var2.length;
/*     */       
/*  83 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/*  85 */         StackTraceElement var5 = var2[var4];
/*  86 */         par1StringBuilder.append("\t").append("at ").append(var5.toString());
/*  87 */         par1StringBuilder.append("\n");
/*     */       } 
/*     */       
/*  90 */       par1StringBuilder.append("\n");
/*     */     } 
/*     */     
/*  93 */     Iterator<CrashReportCategory> var6 = this.crashReportSections.iterator();
/*     */     
/*  95 */     while (var6.hasNext()) {
/*     */       
/*  97 */       CrashReportCategory var7 = var6.next();
/*  98 */       var7.func_85072_a(par1StringBuilder);
/*  99 */       par1StringBuilder.append("\n\n");
/*     */     } 
/*     */     
/* 102 */     this.field_85061_c.func_85072_a(par1StringBuilder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCauseStackTraceOrString() {
/* 110 */     StringWriter var1 = null;
/* 111 */     PrintWriter var2 = null;
/* 112 */     String var3 = this.cause.toString();
/*     */ 
/*     */     
/*     */     try {
/* 116 */       var1 = new StringWriter();
/* 117 */       var2 = new PrintWriter(var1);
/* 118 */       this.cause.printStackTrace(var2);
/* 119 */       var3 = var1.toString();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 125 */         if (var1 != null)
/*     */         {
/* 127 */           var1.close();
/*     */         }
/*     */         
/* 130 */         if (var2 != null)
/*     */         {
/* 132 */           var2.close();
/*     */         }
/*     */       }
/* 135 */       catch (IOException var10) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCompleteReport() {
/* 149 */     StringBuilder var1 = new StringBuilder();
/* 150 */     var1.append("---- Minecraft Crash Report ----\n");
/* 151 */     var1.append("// ");
/* 152 */     var1.append(getWittyComment());
/* 153 */     var1.append("\n\n");
/* 154 */     var1.append("Time: ");
/* 155 */     var1.append((new SimpleDateFormat()).format(new Date()));
/* 156 */     var1.append("\nMITE Release: 1.6.4 R196\n");
/* 157 */     var1.append("\n");
/* 158 */     var1.append("Description: ");
/* 159 */     var1.append(this.description);
/* 160 */     var1.append("\n\n");
/* 161 */     var1.append(getCauseStackTraceOrString());
/* 162 */     var1.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
/*     */     
/* 164 */     for (int var2 = 0; var2 < 87; var2++)
/*     */     {
/* 166 */       var1.append("-");
/*     */     }
/*     */     
/* 169 */     var1.append("\n\n");
/* 170 */     getSectionsInStringBuilder(var1);
/* 171 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile() {
/* 179 */     return this.crashReportFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveToFile(File par1File, ILogAgent par2ILogAgent) {
/* 187 */     if (this.crashReportFile != null)
/*     */     {
/* 189 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 193 */     if (par1File.getParentFile() != null)
/*     */     {
/* 195 */       par1File.getParentFile().mkdirs();
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 200 */       FileWriter var3 = new FileWriter(par1File);
/* 201 */       var3.write(getCompleteReport());
/* 202 */       var3.close();
/* 203 */       this.crashReportFile = par1File;
/* 204 */       return true;
/*     */     }
/* 206 */     catch (Throwable var4) {
/*     */       
/* 208 */       par2ILogAgent.logSevereException("Could not save crash report to " + par1File, var4);
/* 209 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory getCategory() {
/* 216 */     return this.field_85061_c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory makeCategory(String par1Str) {
/* 224 */     return makeCategoryDepth(par1Str, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory makeCategoryDepth(String par1Str, int par2) {
/* 232 */     CrashReportCategory var3 = new CrashReportCategory(this, par1Str);
/*     */     
/* 234 */     if (this.field_85059_f) {
/*     */       
/* 236 */       int var4 = var3.func_85073_a(par2);
/* 237 */       StackTraceElement[] var5 = this.cause.getStackTrace();
/* 238 */       StackTraceElement var6 = null;
/* 239 */       StackTraceElement var7 = null;
/*     */       
/* 241 */       if (var5 != null && var5.length - var4 < var5.length) {
/*     */         
/* 243 */         var6 = var5[var5.length - var4];
/*     */         
/* 245 */         if (var5.length + 1 - var4 < var5.length)
/*     */         {
/* 247 */           var7 = var5[var5.length + 1 - var4];
/*     */         }
/*     */       } 
/*     */       
/* 251 */       this.field_85059_f = var3.func_85069_a(var6, var7);
/*     */       
/* 253 */       if (var4 > 0 && !this.crashReportSections.isEmpty()) {
/*     */         
/* 255 */         CrashReportCategory var8 = this.crashReportSections.get(this.crashReportSections.size() - 1);
/* 256 */         var8.func_85070_b(var4);
/*     */       }
/* 258 */       else if (var5 != null && var5.length >= var4) {
/*     */         
/* 260 */         this.field_85060_g = new StackTraceElement[var5.length - var4];
/* 261 */         System.arraycopy(var5, 0, this.field_85060_g, 0, this.field_85060_g.length);
/*     */       }
/*     */       else {
/*     */         
/* 265 */         this.field_85059_f = false;
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     this.crashReportSections.add(var3);
/* 270 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getWittyComment() {
/* 278 */     String[] var0 = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!" };
/*     */ 
/*     */     
/*     */     try {
/* 282 */       return var0[(int)(System.nanoTime() % var0.length)];
/*     */     }
/* 284 */     catch (Throwable var2) {
/*     */       
/* 286 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CrashReport makeCrashReport(Throwable par0Throwable, String par1Str) {
/*     */     CrashReport var2;
/* 297 */     if (par0Throwable instanceof ReportedException) {
/*     */       
/* 299 */       var2 = ((ReportedException)par0Throwable).getCrashReport();
/*     */     }
/*     */     else {
/*     */       
/* 303 */       var2 = new CrashReport(par1Str, par0Throwable);
/*     */     } 
/*     */     
/* 306 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CrashReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */