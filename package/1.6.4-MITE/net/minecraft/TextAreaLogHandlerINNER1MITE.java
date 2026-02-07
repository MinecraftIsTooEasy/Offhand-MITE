/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import java.util.logging.Formatter;
/*    */ import java.util.logging.LogRecord;
/*    */ 
/*    */ 
/*    */ 
/*    */ class TextAreaLogHandlerINNER1MITE
/*    */   extends Formatter
/*    */ {
/*    */   final TextAreaLogHandlerMITE field_120031_a;
/*    */   
/*    */   TextAreaLogHandlerINNER1MITE(TextAreaLogHandlerMITE par1TextAreaLogHandler) {
/* 16 */     this.field_120031_a = par1TextAreaLogHandler;
/*    */   }
/*    */ 
/*    */   
/*    */   public String format(LogRecord par1LogRecord) {
/* 21 */     StringBuilder var2 = new StringBuilder();
/* 22 */     var2.append(" [").append(par1LogRecord.getLevel().getName()).append("] ");
/* 23 */     var2.append(formatMessage(par1LogRecord));
/* 24 */     var2.append('\n');
/* 25 */     Throwable var3 = par1LogRecord.getThrown();
/*    */     
/* 27 */     if (var3 != null) {
/*    */       
/* 29 */       StringWriter var4 = new StringWriter();
/* 30 */       var3.printStackTrace(new PrintWriter(var4));
/* 31 */       var2.append(var4.toString());
/*    */     } 
/*    */     
/* 34 */     return var2.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextAreaLogHandlerINNER1MITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */