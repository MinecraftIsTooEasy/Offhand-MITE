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
/*    */ public class CommandServerKick
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 15 */     return "kick";
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
/* 26 */     return "commands.kick.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 31 */     if (strings.length > 0 && strings[0].length() > 1) {
/* 32 */       ServerPlayer serverPlayer = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(strings[0]);
/* 33 */       String str = "Kicked by an operator.";
/* 34 */       boolean bool = false;
/*    */       
/* 36 */       if (serverPlayer == null) {
/* 37 */         throw new PlayerNotFoundException();
/*    */       }
/*    */       
/* 40 */       if (strings.length >= 2) {
/* 41 */         str = func_82360_a(iCommandSender, strings, 1);
/* 42 */         bool = true;
/*    */       } 
/*    */       
/* 45 */       serverPlayer.playerNetServerHandler.kickPlayerFromServer(str);
/*    */       
/* 47 */       if (bool) {
/* 48 */         notifyAdmins(iCommandSender, "commands.kick.success.reason", new Object[] { serverPlayer.getEntityName(), str });
/*    */       } else {
/* 50 */         notifyAdmins(iCommandSender, "commands.kick.success", new Object[] { serverPlayer.getEntityName() });
/*    */       } 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 56 */     throw new WrongUsageException("commands.kick.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 61 */     if (strings.length >= 1) {
/* 62 */       return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
/*    */     }
/*    */     
/* 65 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerKick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */