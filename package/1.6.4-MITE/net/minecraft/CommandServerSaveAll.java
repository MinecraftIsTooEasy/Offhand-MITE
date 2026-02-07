/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerSaveAll
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 12 */     return "save-all";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 17 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 22 */     return "commands.save.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 27 */     MinecraftServer minecraftServer = MinecraftServer.getServer();
/*    */     
/* 29 */     iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.save.start"));
/*    */     
/* 31 */     if (minecraftServer.getConfigurationManager() != null) {
/* 32 */       minecraftServer.getConfigurationManager().saveAllPlayerData();
/*    */     }
/*    */     try {
/*    */       byte b;
/* 36 */       for (b = 0; b < minecraftServer.worldServers.length; b++) {
/* 37 */         if (minecraftServer.worldServers[b] != null) {
/* 38 */           WorldServer worldServer = minecraftServer.worldServers[b];
/* 39 */           boolean bool = worldServer.canNotSave;
/* 40 */           worldServer.canNotSave = false;
/* 41 */           worldServer.saveAllChunks(true, null);
/* 42 */           worldServer.canNotSave = bool;
/*    */         } 
/*    */       } 
/* 45 */       if (strings.length > 0 && "flush".equals(strings[0])) {
/* 46 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.save.flushStart"));
/* 47 */         for (b = 0; b < minecraftServer.worldServers.length; b++) {
/* 48 */           if (minecraftServer.worldServers[b] != null) {
/* 49 */             WorldServer worldServer = minecraftServer.worldServers[b];
/* 50 */             boolean bool = worldServer.canNotSave;
/* 51 */             worldServer.canNotSave = false;
/* 52 */             worldServer.saveChunkData();
/* 53 */             worldServer.canNotSave = bool;
/*    */           } 
/*    */         } 
/* 56 */         iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.save.flushEnd"));
/*    */       } 
/* 58 */     } catch (MinecraftException minecraftException) {
/* 59 */       notifyAdmins(iCommandSender, "commands.save.failed", new Object[] { minecraftException.getMessage() });
/*    */       
/*    */       return;
/*    */     } 
/* 63 */     notifyAdmins(iCommandSender, "commands.save.success", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerSaveAll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */