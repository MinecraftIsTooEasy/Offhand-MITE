/*    */ package net.minecraft;
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
/*    */ public class LanServer
/*    */ {
/*    */   private String lanServerMotd;
/*    */   private String lanServerIpPort;
/*    */   private long timeLastSeen;
/*    */   
/*    */   public LanServer(String string, String string2) {
/* 63 */     this.lanServerMotd = string;
/* 64 */     this.lanServerIpPort = string2;
/* 65 */     this.timeLastSeen = Minecraft.getSystemTime();
/*    */   }
/*    */   
/*    */   public String getServerMotd() {
/* 69 */     return this.lanServerMotd;
/*    */   }
/*    */   
/*    */   public String getServerIpPort() {
/* 73 */     return this.lanServerIpPort;
/*    */   }
/*    */   
/*    */   public void updateLastSeen() {
/* 77 */     this.timeLastSeen = Minecraft.getSystemTime();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LanServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */