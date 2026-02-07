/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerMessage
/*    */   extends CommandBase
/*    */ {
/*    */   public List getCommandAliases() {
/* 18 */     return Arrays.asList(new String[] { "w", "msg" });
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandName() {
/* 23 */     return "tell";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 28 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 33 */     return "commands.message.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 38 */     if (strings.length < 2) throw new WrongUsageException("commands.message.usage", new Object[0]);
/*    */     
/* 40 */     ServerPlayer serverPlayer = getPlayer(iCommandSender, strings[0]);
/*    */     
/* 42 */     if (serverPlayer == null) throw new PlayerNotFoundException(); 
/* 43 */     if (serverPlayer == iCommandSender) throw new PlayerNotFoundException("commands.message.sameTarget", new Object[0]);
/*    */     
/* 45 */     String str = func_82361_a(iCommandSender, strings, 1, !(iCommandSender instanceof EntityPlayer));
/* 46 */     serverPlayer.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.message.display.incoming", new Object[] { iCommandSender.getCommandSenderName(), str }).setColor(EnumChatFormatting.GRAY).setItalic(Boolean.valueOf(true)));
/* 47 */     iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.message.display.outgoing", new Object[] { serverPlayer.getCommandSenderName(), str }).setColor(EnumChatFormatting.GRAY).setItalic(Boolean.valueOf(true)));
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 52 */     return getListOfStringsMatchingLastWord(strings, MinecraftServer.getServer().getAllUsernames());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] strings, int i) {
/* 57 */     return (i == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */