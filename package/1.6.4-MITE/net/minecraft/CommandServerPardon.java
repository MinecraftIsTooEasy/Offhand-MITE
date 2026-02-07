/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerPardon
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 12 */     return "pardon";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 17 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 23 */     return "commands.unban.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
/* 28 */     return (MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().isListActive() && super.canCommandSenderUseCommand(iCommandSender));
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 33 */     if (strings.length == 1 && strings[0].length() > 0) {
/* 34 */       MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().remove(strings[0]);
/*    */       
/* 36 */       notifyAdmins(iCommandSender, "commands.unban.success", new Object[] { strings[0] });
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     throw new WrongUsageException("commands.unban.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 45 */     if (strings.length == 1) {
/* 46 */       return getListOfStringsFromIterableMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().getBannedList().keySet());
/*    */     }
/*    */     
/* 49 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerPardon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */