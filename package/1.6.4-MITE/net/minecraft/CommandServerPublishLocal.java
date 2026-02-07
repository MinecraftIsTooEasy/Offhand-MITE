/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class CommandServerPublishLocal
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 10 */     return "publish";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 15 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 20 */     return "commands.publish.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 25 */     String str = MinecraftServer.getServer().shareToLAN(EnumGameType.SURVIVAL, false);
/*    */     
/* 27 */     if (str != null) {
/* 28 */       notifyAdmins(iCommandSender, "commands.publish.started", new Object[] { str });
/*    */     } else {
/* 30 */       notifyAdmins(iCommandSender, "commands.publish.failed", new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerPublishLocal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */