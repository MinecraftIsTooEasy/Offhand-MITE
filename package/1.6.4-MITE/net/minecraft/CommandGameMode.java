/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class CommandGameMode
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 10 */     return "gamemode";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 18 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 23 */     return "commands.gamemode.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 28 */     if (par2ArrayOfStr.length > 0) {
/*    */       
/* 30 */       EnumGameType var3 = getGameModeFromCommand(par1ICommandSender, par2ArrayOfStr[0]);
/*    */       
/* 32 */       ServerPlayer var4 = (par2ArrayOfStr.length >= 2) ? getPlayer(par1ICommandSender, par2ArrayOfStr[1]) : getCommandSenderAsPlayer(par1ICommandSender);
/*    */       
/* 34 */       if (!Minecraft.inDevMode() && var3 != EnumGameType.SURVIVAL) {
/*    */         
/* 36 */         ChatMessageComponent chatMessageComponent = ChatMessageComponent.createFromTranslationKey("gameMode." + var3.getName());
/*    */         
/* 38 */         if (var4 != par1ICommandSender) {
/*    */           
/* 40 */           notifyAdmins(par1ICommandSender, 1, "commands.gamemode.fail.other", new Object[] { var4.getEntityName(), chatMessageComponent });
/*    */         }
/*    */         else {
/*    */           
/* 44 */           notifyAdmins(par1ICommandSender, 1, "commands.gamemode.fail.self", new Object[] { chatMessageComponent });
/*    */         } 
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 50 */       var4.setGameType(var3);
/* 51 */       var4.fallDistance = 0.0F;
/* 52 */       ChatMessageComponent var5 = ChatMessageComponent.createFromTranslationKey("gameMode." + var3.getName());
/*    */       
/* 54 */       if (var4 != par1ICommandSender)
/*    */       {
/* 56 */         notifyAdmins(par1ICommandSender, 1, "commands.gamemode.success.other", new Object[] { var4.getEntityName(), var5 });
/*    */       }
/*    */       else
/*    */       {
/* 60 */         notifyAdmins(par1ICommandSender, 1, "commands.gamemode.success.self", new Object[] { var5 });
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 65 */       throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected EnumGameType getGameModeFromCommand(ICommandSender par1ICommandSender, String par2Str) {
/* 74 */     return (!par2Str.equalsIgnoreCase(EnumGameType.SURVIVAL.getName()) && !par2Str.equalsIgnoreCase("s")) ? ((!par2Str.equalsIgnoreCase(EnumGameType.CREATIVE.getName()) && !par2Str.equalsIgnoreCase("c")) ? ((!par2Str.equalsIgnoreCase(EnumGameType.ADVENTURE.getName()) && !par2Str.equalsIgnoreCase("a")) ? WorldSettings.getGameTypeById(parseIntBounded(par1ICommandSender, par2Str, 0, (EnumGameType.values()).length - 2)) : EnumGameType.ADVENTURE) : EnumGameType.CREATIVE) : EnumGameType.SURVIVAL;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 82 */     return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] { "survival", "creative", "adventure" }) : ((par2ArrayOfStr.length == 2) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, getListOfPlayerUsernames()) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String[] getListOfPlayerUsernames() {
/* 90 */     return MinecraftServer.getServer().getAllUsernames();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
/* 98 */     return (par2 == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandGameMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */