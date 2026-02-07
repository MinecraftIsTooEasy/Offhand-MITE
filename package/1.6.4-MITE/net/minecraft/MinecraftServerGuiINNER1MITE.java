/*    */ package net.minecraft;
/*    */ 
/*    */ import java.awt.event.WindowAdapter;
/*    */ import java.awt.event.WindowEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class MinecraftServerGuiINNER1MITE
/*    */   extends WindowAdapter
/*    */ {
/*    */   final DedicatedServer field_120023_a;
/*    */   
/*    */   MinecraftServerGuiINNER1MITE(DedicatedServer par1DedicatedServer) {
/* 14 */     this.field_120023_a = par1DedicatedServer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void windowClosing(WindowEvent par1WindowEvent) {
/* 19 */     this.field_120023_a.save_world_maps_on_shutdown = this.field_120023_a.isServerSideMappingEnabled();
/* 20 */     this.field_120023_a.initiateShutdown();
/*    */     
/* 22 */     while (!this.field_120023_a.isServerStopped()) {
/*    */ 
/*    */       
/*    */       try {
/* 26 */         Thread.sleep(100L);
/*    */       }
/* 28 */       catch (InterruptedException var3) {
/*    */         
/* 30 */         var3.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     
/* 34 */     ThreadedFileIOBase.reportErrorIfNotFinished();
/*    */     
/* 36 */     System.exit(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MinecraftServerGuiINNER1MITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */