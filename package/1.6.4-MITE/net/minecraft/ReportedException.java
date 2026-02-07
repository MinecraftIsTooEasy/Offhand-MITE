/*    */ package net.minecraft;
/*    */ 
/*    */ public class ReportedException
/*    */   extends RuntimeException
/*    */ {
/*    */   private final CrashReport theReportedExceptionCrashReport;
/*    */   
/*    */   public ReportedException(CrashReport crashReport) {
/*  9 */     this.theReportedExceptionCrashReport = crashReport;
/*    */   }
/*    */   
/*    */   public CrashReport getCrashReport() {
/* 13 */     return this.theReportedExceptionCrashReport;
/*    */   }
/*    */ 
/*    */   
/*    */   public Throwable getCause() {
/* 18 */     return this.theReportedExceptionCrashReport.getCrashCause();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 23 */     return this.theReportedExceptionCrashReport.getDescription();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ReportedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */