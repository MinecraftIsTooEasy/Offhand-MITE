/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortalPosition
/*    */   extends ChunkCoordinates
/*    */ {
/*    */   public long lastUpdateTime;
/*    */   final Teleporter teleporterInstance;
/*    */   
/*    */   public PortalPosition(Teleporter par1Teleporter, int par2, int par3, int par4, long par5) {
/* 13 */     super(par2, par3, par4);
/* 14 */     this.teleporterInstance = par1Teleporter;
/* 15 */     this.lastUpdateTime = par5;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getLong(String field) {
/*    */     try {
/* 22 */       return getClass().getDeclaredField(field).getLong(this);
/*    */     }
/* 24 */     catch (Exception e) {
/*    */       
/* 26 */       return 0L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PortalPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */