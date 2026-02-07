/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.logging.Formatter;
/*    */ import java.util.logging.Handler;
/*    */ import java.util.logging.LogRecord;
/*    */ import javax.swing.JTextArea;
/*    */ 
/*    */ 
/*    */ public class TextAreaLogHandlerMITE
/*    */   extends Handler
/*    */ {
/* 12 */   private int[] field_120027_b = new int[1024];
/*    */   private int field_120028_c;
/* 14 */   Formatter field_120029_a = new TextAreaLogHandlerINNER1MITE(this);
/*    */   
/*    */   private JTextArea field_120026_d;
/*    */   
/*    */   public TextAreaLogHandlerMITE(JTextArea par1JTextArea) {
/* 19 */     setFormatter(this.field_120029_a);
/* 20 */     this.field_120026_d = par1JTextArea;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {}
/*    */   
/*    */   public void flush() {}
/*    */   
/*    */   public void publish(LogRecord par1LogRecord) {
/* 29 */     int var2 = this.field_120026_d.getDocument().getLength();
/* 30 */     this.field_120026_d.append(this.field_120029_a.format(par1LogRecord));
/* 31 */     this.field_120026_d.setCaretPosition(this.field_120026_d.getDocument().getLength());
/* 32 */     int var3 = this.field_120026_d.getDocument().getLength() - var2;
/*    */     
/* 34 */     if (this.field_120027_b[this.field_120028_c] != 0)
/*    */     {
/* 36 */       this.field_120026_d.replaceRange("", 0, this.field_120027_b[this.field_120028_c]);
/*    */     }
/*    */     
/* 39 */     this.field_120027_b[this.field_120028_c] = var3;
/* 40 */     this.field_120028_c = (this.field_120028_c + 1) % 1024;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextAreaLogHandlerMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */