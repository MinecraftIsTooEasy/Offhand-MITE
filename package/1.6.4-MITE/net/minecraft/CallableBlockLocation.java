/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class CallableBlockLocation
/*    */   implements Callable
/*    */ {
/*    */   final int blockXCoord;
/*    */   final int blockYCoord;
/*    */   final int blockZCoord;
/*    */   
/*    */   CallableBlockLocation(int par1, int par2, int par3) {
/* 15 */     this.blockXCoord = par1;
/* 16 */     this.blockYCoord = par2;
/* 17 */     this.blockZCoord = par3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String callBlockLocationInfo() {
/* 22 */     return CrashReportCategory.getLocationInfo(this.blockXCoord, this.blockYCoord, this.blockZCoord);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object call() {
/* 27 */     return callBlockLocationInfo();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getInt(String field) {
/*    */     try {
/* 34 */       return getClass().getDeclaredField(field).getInt(this);
/*    */     }
/* 36 */     catch (Exception e) {
/*    */       
/* 38 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CallableBlockLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */