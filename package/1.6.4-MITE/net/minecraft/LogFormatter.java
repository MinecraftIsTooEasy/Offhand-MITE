/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.LogRecord;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LogFormatter
/*     */   extends Formatter
/*     */ {
/* 122 */   private SimpleDateFormat field_98228_b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */ 
/*     */   
/*     */   public String format(LogRecord logRecord) {
/* 126 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/* 128 */     stringBuilder.append(this.field_98228_b.format(Long.valueOf(logRecord.getMillis())));
/* 129 */     if (LogAgent.func_98237_a(this.field_98229_a) != null) stringBuilder.append(LogAgent.func_98237_a(this.field_98229_a)); 
/* 130 */     stringBuilder.append(" [").append(logRecord.getLevel().getName()).append("] ");
/* 131 */     stringBuilder.append(formatMessage(logRecord));
/* 132 */     stringBuilder.append('\n');
/*     */     
/* 134 */     Throwable throwable = logRecord.getThrown();
/*     */     
/* 136 */     if (throwable != null) {
/* 137 */       StringWriter stringWriter = new StringWriter();
/* 138 */       throwable.printStackTrace(new PrintWriter(stringWriter));
/* 139 */       stringBuilder.append(stringWriter.toString());
/*     */     } 
/*     */     
/* 142 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   private LogFormatter(LogAgent logAgent) {}
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LogFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */