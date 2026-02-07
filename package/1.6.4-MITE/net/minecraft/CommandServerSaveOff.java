/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerSaveOff
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 11 */     return "save-off";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 16 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 21 */     return "commands.save-off.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 26 */     MinecraftServer minecraftServer = MinecraftServer.getServer();
/* 27 */     boolean bool = false;
/*    */     
/* 29 */     for (byte b = 0; b < minecraftServer.worldServers.length; b++) {
/* 30 */       if (minecraftServer.worldServers[b] != null) {
/* 31 */         WorldServer worldServer = minecraftServer.worldServers[b];
/* 32 */         if (!worldServer.canNotSave) {
/* 33 */           worldServer.canNotSave = true;
/* 34 */           bool = true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 39 */     if (bool) {
/* 40 */       notifyAdmins(iCommandSender, "commands.save.disabled", new Object[0]);
/*    */     } else {
/* 42 */       throw new CommandException("commands.save-off.alreadyOff", new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerSaveOff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */