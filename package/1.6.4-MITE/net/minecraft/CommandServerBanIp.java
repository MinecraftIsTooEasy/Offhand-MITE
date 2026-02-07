/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerBanIp
/*    */   extends CommandBase
/*    */ {
/* 16 */   public static final Pattern IPv4Pattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCommandName() {
/* 21 */     return "ban-ip";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 26 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
/* 31 */     return (MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isListActive() && super.canCommandSenderUseCommand(iCommandSender));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 36 */     return "commands.banip.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 41 */     if (strings.length >= 1 && strings[0].length() > 1) {
/* 42 */       Matcher matcher = IPv4Pattern.matcher(strings[0]);
/* 43 */       String str = null;
/*    */       
/* 45 */       if (strings.length >= 2) {
/* 46 */         str = func_82360_a(iCommandSender, strings, 1);
/*    */       }
/*    */       
/* 49 */       if (matcher.matches()) {
/* 50 */         banIP(iCommandSender, strings[0], str);
/*    */       } else {
/* 52 */         ServerPlayer serverPlayer = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(strings[0]);
/*    */         
/* 54 */         if (serverPlayer == null) {
/* 55 */           throw new PlayerNotFoundException("commands.banip.invalid", new Object[0]);
/*    */         }
/*    */         
/* 58 */         banIP(iCommandSender, serverPlayer.getPlayerIP(), str);
/*    */       } 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 64 */     throw new WrongUsageException("commands.banip.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 69 */     if (strings.length == 1) {
/* 70 */       return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
/*    */     }
/*    */     
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   protected void banIP(ICommandSender iCommandSender, String string, String string2) {
/* 77 */     BanEntry banEntry = new BanEntry(string);
/*    */     
/* 79 */     banEntry.setBannedBy(iCommandSender.getCommandSenderName());
/* 80 */     if (string2 != null) banEntry.setBanReason(string2);
/*    */     
/* 82 */     MinecraftServer.getServer().getConfigurationManager().getBannedIPs().put(banEntry);
/*    */     
/* 84 */     List list = MinecraftServer.getServer().getConfigurationManager().getPlayerList(string);
/* 85 */     String[] arrayOfString = new String[list.size()];
/* 86 */     byte b = 0;
/*    */     
/* 88 */     for (ServerPlayer serverPlayer : list) {
/* 89 */       serverPlayer.playerNetServerHandler.kickPlayerFromServer("You have been IP banned.");
/* 90 */       arrayOfString[b++] = serverPlayer.getEntityName();
/*    */     } 
/*    */     
/* 93 */     if (list.isEmpty()) {
/* 94 */       notifyAdmins(iCommandSender, "commands.banip.success", new Object[] { string });
/*    */     } else {
/* 96 */       notifyAdmins(iCommandSender, "commands.banip.success.players", new Object[] { string, joinNiceString((Object[])arrayOfString) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerBanIp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */