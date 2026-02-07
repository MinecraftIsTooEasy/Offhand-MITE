/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerBan
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 15 */     return "ban";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 20 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 26 */     return "commands.ban.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
/* 31 */     return (MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isListActive() && super.canCommandSenderUseCommand(iCommandSender));
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 36 */     if (strings.length >= 1 && strings[0].length() > 0) {
/* 37 */       ServerPlayer serverPlayer = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(strings[0]);
/* 38 */       BanEntry banEntry = new BanEntry(strings[0]);
/*    */       
/* 40 */       banEntry.setBannedBy(iCommandSender.getCommandSenderName());
/* 41 */       if (strings.length >= 2) {
/* 42 */         banEntry.setBanReason(func_82360_a(iCommandSender, strings, 1));
/*    */       }
/*    */       
/* 45 */       MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().put(banEntry);
/*    */       
/* 47 */       if (serverPlayer != null) {
/* 48 */         serverPlayer.playerNetServerHandler.kickPlayerFromServer("You are banned from this server.");
/*    */       }
/*    */       
/* 51 */       notifyAdmins(iCommandSender, "commands.ban.success", new Object[] { strings[0] });
/*    */       
/*    */       return;
/*    */     } 
/* 55 */     throw new WrongUsageException("commands.ban.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 60 */     if (strings.length >= 1) {
/* 61 */       return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
/*    */     }
/*    */     
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerBan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */