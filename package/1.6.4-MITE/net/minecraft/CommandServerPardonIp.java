/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerPardonIp
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 13 */     return "pardon-ip";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 18 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
/* 24 */     return (MinecraftServer.getServer().getConfigurationManager().getBannedIPs().isListActive() && super.canCommandSenderUseCommand(iCommandSender));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 29 */     return "commands.unbanip.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 34 */     if (strings.length == 1 && strings[0].length() > 1) {
/* 35 */       Matcher matcher = CommandServerBanIp.IPv4Pattern.matcher(strings[0]);
/*    */       
/* 37 */       if (matcher.matches()) {
/* 38 */         MinecraftServer.getServer().getConfigurationManager().getBannedIPs().remove(strings[0]);
/* 39 */         notifyAdmins(iCommandSender, "commands.unbanip.success", new Object[] { strings[0] });
/*    */         return;
/*    */       } 
/* 42 */       throw new SyntaxErrorException("commands.unbanip.invalid", new Object[0]);
/*    */     } 
/*    */ 
/*    */     
/* 46 */     throw new WrongUsageException("commands.unbanip.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 51 */     if (strings.length == 1) {
/* 52 */       return getListOfStringsFromIterableMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getBannedIPs().getBannedList().keySet());
/*    */     }
/*    */     
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerPardonIp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */