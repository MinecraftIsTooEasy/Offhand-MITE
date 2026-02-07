/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrashReportCategory
/*     */ {
/*     */   private final CrashReport theCrashReport;
/*     */   private final String field_85076_b;
/*  13 */   private final List field_85077_c = new ArrayList();
/*  14 */   private StackTraceElement[] stackTrace = new StackTraceElement[0];
/*     */   
/*     */   public CrashReportCategory(CrashReport crashReport, String string) {
/*  17 */     this.theCrashReport = crashReport;
/*  18 */     this.field_85076_b = string;
/*     */   }
/*     */   
/*     */   public static String func_85074_a(double d, double e, double f) {
/*  22 */     return String.format("%.2f,%.2f,%.2f - %s", new Object[] { Double.valueOf(d), Double.valueOf(e), Double.valueOf(f), getLocationInfo(MathHelper.floor_double(d), MathHelper.floor_double(e), MathHelper.floor_double(f)) });
/*     */   }
/*     */   
/*     */   public static String getLocationInfo(int i, int j, int k) {
/*  26 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*     */     try {
/*  29 */       stringBuilder.append(String.format("World: (%d,%d,%d)", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) }));
/*  30 */     } catch (Throwable throwable) {
/*  31 */       stringBuilder.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  34 */     stringBuilder.append(", ");
/*     */     
/*     */     try {
/*  37 */       int m = i >> 4;
/*  38 */       int n = k >> 4;
/*  39 */       int i1 = i & 0xF;
/*  40 */       int i2 = j >> 4;
/*  41 */       int i3 = k & 0xF;
/*  42 */       int i4 = m << 4;
/*  43 */       int i5 = n << 4;
/*  44 */       int i6 = (m + 1 << 4) - 1;
/*  45 */       int i7 = (n + 1 << 4) - 1;
/*  46 */       stringBuilder.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(i1), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7) }));
/*  47 */     } catch (Throwable throwable) {
/*  48 */       stringBuilder.append("(Error finding chunk loc)");
/*     */     } 
/*     */     
/*  51 */     stringBuilder.append(", ");
/*     */     
/*     */     try {
/*  54 */       int m = i >> 9;
/*  55 */       int n = k >> 9;
/*  56 */       int i1 = m << 5;
/*  57 */       int i2 = n << 5;
/*  58 */       int i3 = (m + 1 << 5) - 1;
/*  59 */       int i4 = (n + 1 << 5) - 1;
/*  60 */       int i5 = m << 9;
/*  61 */       int i6 = n << 9;
/*  62 */       int i7 = (m + 1 << 9) - 1;
/*  63 */       int i8 = (n + 1 << 9) - 1;
/*  64 */       stringBuilder.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(m), Integer.valueOf(n), Integer.valueOf(i1), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8) }));
/*  65 */     } catch (Throwable throwable) {
/*  66 */       stringBuilder.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  69 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public void addCrashSectionCallable(String string, Callable callable) {
/*     */     try {
/*  74 */       addCrashSection(string, callable.call());
/*  75 */     } catch (Throwable throwable) {
/*  76 */       addCrashSectionThrowable(string, throwable);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCrashSection(String string, Object object) {
/*  81 */     this.field_85077_c.add(new CrashReportCategoryEntry(string, object));
/*     */   }
/*     */   
/*     */   public void addCrashSectionThrowable(String string, Throwable throwable) {
/*  85 */     addCrashSection(string, throwable);
/*     */   }
/*     */   
/*     */   public int func_85073_a(int i) {
/*  89 */     StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
/*  90 */     this.stackTrace = new StackTraceElement[arrayOfStackTraceElement.length - 3 - i];
/*  91 */     System.arraycopy(arrayOfStackTraceElement, 3 + i, this.stackTrace, 0, this.stackTrace.length);
/*  92 */     return this.stackTrace.length;
/*     */   }
/*     */   
/*     */   public boolean func_85069_a(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
/*  96 */     if (this.stackTrace.length == 0 || stackTraceElement == null) return false;
/*     */     
/*  98 */     StackTraceElement stackTraceElement1 = this.stackTrace[0];
/*     */ 
/*     */     
/* 101 */     if (stackTraceElement1.isNativeMethod() != stackTraceElement.isNativeMethod() || !stackTraceElement1.getClassName().equals(stackTraceElement.getClassName()) || !stackTraceElement1.getFileName().equals(stackTraceElement.getFileName()) || !stackTraceElement1.getMethodName().equals(stackTraceElement.getMethodName()))
/*     */     {
/*     */ 
/*     */       
/* 105 */       return false;
/*     */     }
/* 107 */     if (((stackTraceElement2 != null) ? true : false) != ((this.stackTrace.length > 1) ? true : false)) return false; 
/* 108 */     if (stackTraceElement2 != null && !this.stackTrace[1].equals(stackTraceElement2)) return false;
/*     */     
/* 110 */     this.stackTrace[0] = stackTraceElement;
/*     */     
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public void func_85070_b(int i) {
/* 116 */     StackTraceElement[] arrayOfStackTraceElement = new StackTraceElement[this.stackTrace.length - i];
/* 117 */     System.arraycopy(this.stackTrace, 0, arrayOfStackTraceElement, 0, arrayOfStackTraceElement.length);
/* 118 */     this.stackTrace = arrayOfStackTraceElement;
/*     */   }
/*     */   
/*     */   public void func_85072_a(StringBuilder stringBuilder) {
/* 122 */     stringBuilder.append("-- ").append(this.field_85076_b).append(" --\n");
/* 123 */     stringBuilder.append("Details:");
/*     */     
/* 125 */     for (CrashReportCategoryEntry crashReportCategoryEntry : this.field_85077_c) {
/* 126 */       stringBuilder.append("\n\t");
/* 127 */       stringBuilder.append(crashReportCategoryEntry.func_85089_a());
/* 128 */       stringBuilder.append(": ");
/* 129 */       stringBuilder.append(crashReportCategoryEntry.func_85090_b());
/*     */     } 
/*     */     
/* 132 */     if (this.stackTrace != null && this.stackTrace.length > 0) {
/* 133 */       stringBuilder.append("\nStacktrace:");
/*     */       
/* 135 */       for (StackTraceElement stackTraceElement : this.stackTrace) {
/* 136 */         stringBuilder.append("\n\tat ");
/* 137 */         stringBuilder.append(stackTraceElement.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addBlockCrashInfo(CrashReportCategory crashReportCategory, int i, int j, int k, int l, int m) {
/* 143 */     crashReportCategory.addCrashSectionCallable("Block type", new CallableBlockType(l));
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
/* 154 */     crashReportCategory.addCrashSectionCallable("Block data value", new CallableBlockDataValue(m));
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
/* 165 */     crashReportCategory.addCrashSectionCallable("Block location", new CallableBlockLocation(i, j, k));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CrashReportCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */