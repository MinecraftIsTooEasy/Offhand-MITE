/*    */ package net.minecraft;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.lang.management.RuntimeMXBean;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CallableJVMFlags
/*    */   implements Callable
/*    */ {
/*    */   CallableJVMFlags(CrashReport crashReport) {}
/*    */   
/*    */   public String getJVMFlagsAsString() {
/* 78 */     RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
/* 79 */     List<String> list = runtimeMXBean.getInputArguments();
/* 80 */     byte b = 0;
/* 81 */     StringBuilder stringBuilder = new StringBuilder();
/*    */     
/* 83 */     for (String str : list) {
/* 84 */       if (str.startsWith("-X")) {
/* 85 */         if (b++ > 0) {
/* 86 */           stringBuilder.append(" ");
/*    */         }
/*    */         
/* 89 */         stringBuilder.append(str);
/*    */       } 
/*    */     } 
/*    */     
/* 93 */     return String.format("%d total; %s", new Object[] { Integer.valueOf(b), stringBuilder.toString() });
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableJVMFlags.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */