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
/*    */ public class CommandServerSay
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 15 */     return "say";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 20 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 25 */     return "commands.say.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 30 */     if (strings.length > 0 && strings[0].length() > 0) {
/* 31 */       String str = func_82361_a(iCommandSender, strings, 0, true);
/* 32 */       MinecraftServer.getServer().getConfigurationManager().sendChatMsg(ChatMessageComponent.createFromTranslationWithSubstitutions("chat.type.announcement", new Object[] { iCommandSender.getCommandSenderName(), str }));
/*    */       
/*    */       return;
/*    */     } 
/* 36 */     throw new WrongUsageException("commands.say.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 41 */     if (strings.length >= 1) {
/* 42 */       return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
/*    */     }
/*    */     
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerSay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */