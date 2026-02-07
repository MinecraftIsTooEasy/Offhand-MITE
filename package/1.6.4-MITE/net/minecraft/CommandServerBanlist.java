/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerBanlist
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 12 */     return "banlist";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 17 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
/* 23 */     return ((MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isListActive() || MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isListActive()) && super.canCommandSenderUseCommand(iCommandSender));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 28 */     return "commands.banlist.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 33 */     if (strings.length >= 1 && strings[0].equalsIgnoreCase("ips")) {
/* 34 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.banlist.ips", new Object[] { Integer.valueOf(MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getBannedList().size()) }));
/* 35 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(joinNiceString(MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getBannedList().keySet().toArray())));
/*    */     } else {
/* 37 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.banlist.players", new Object[] { Integer.valueOf(MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getBannedList().size()) }));
/* 38 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(joinNiceString(MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getBannedList().keySet().toArray())));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 44 */     if (strings.length == 1) {
/* 45 */       return getListOfStringsMatchingLastWord(strings, new String[] { "players", "ips" });
/*    */     }
/*    */     
/* 48 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerBanlist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */