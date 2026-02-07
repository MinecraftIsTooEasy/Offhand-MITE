/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandHelp
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 15 */     return "help";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 20 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 26 */     return "commands.help.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public List getCommandAliases() {
/* 31 */     return Arrays.asList(new String[] { "?" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 36 */     List<ICommand> list = getSortedPossibleCommands(iCommandSender);
/* 37 */     byte b1 = 7;
/* 38 */     int i = (list.size() - 1) / b1;
/* 39 */     byte b2 = 0;
/*    */     
/*    */     try {
/* 42 */       b2 = (strings.length == 0) ? 0 : (parseIntBounded(iCommandSender, strings[0], 1, i + 1) - 1);
/* 43 */     } catch (NumberInvalidException numberInvalidException) {
/*    */       
/* 45 */       Map map = getCommands();
/* 46 */       ICommand iCommand = (ICommand)map.get(strings[0]);
/*    */       
/* 48 */       if (iCommand != null)
/*    */       {
/* 50 */         throw new WrongUsageException(iCommand.getCommandUsage(iCommandSender), new Object[0]);
/*    */       }
/* 52 */       throw new CommandNotFoundException();
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 57 */     int j = Math.min((b2 + 1) * b1, list.size());
/*    */     
/* 59 */     iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.help.header", new Object[] { Integer.valueOf(b2 + 1), Integer.valueOf(i + 1) }).setColor(EnumChatFormatting.DARK_GREEN));
/*    */     
/* 61 */     for (int k = b2 * b1; k < j; k++) {
/* 62 */       ICommand iCommand = list.get(k);
/*    */       
/* 64 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(iCommand.getCommandUsage(iCommandSender)));
/*    */     } 
/*    */     
/* 67 */     if (b2 == 0 && iCommandSender instanceof EntityPlayer) {
/* 68 */       iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.help.footer").setColor(EnumChatFormatting.GREEN));
/*    */     }
/*    */   }
/*    */   
/*    */   protected List getSortedPossibleCommands(ICommandSender iCommandSender) {
/* 73 */     List<Comparable> list = MinecraftServer.getServer().getCommandManager().getPossibleCommands(iCommandSender);
/* 74 */     Collections.sort(list);
/* 75 */     return list;
/*    */   }
/*    */   
/*    */   protected Map getCommands() {
/* 79 */     return MinecraftServer.getServer().getCommandManager().getCommands();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandHelp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */