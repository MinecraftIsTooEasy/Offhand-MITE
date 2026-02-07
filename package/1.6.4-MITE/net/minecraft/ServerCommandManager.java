/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class ServerCommandManager
/*     */   extends CommandHandler
/*     */   implements IAdminCommand {
/*     */   public ServerCommandManager() {
/*  10 */     registerCommand(new CommandTime());
/*  11 */     registerCommand(new CommandGameMode());
/*  12 */     registerCommand(new CommandDifficulty());
/*  13 */     registerCommand(new CommandDefaultGameMode());
/*  14 */     registerCommand(new CommandKill());
/*  15 */     registerCommand(new CommandToggleDownfall());
/*  16 */     registerCommand(new CommandWeather());
/*  17 */     registerCommand(new CommandXP());
/*  18 */     registerCommand(new CommandServerTp());
/*  19 */     registerCommand(new CommandGive());
/*  20 */     registerCommand(new CommandEffect());
/*  21 */     registerCommand(new CommandEnchant());
/*  22 */     registerCommand(new CommandServerEmote());
/*  23 */     registerCommand(new CommandShowSeed());
/*  24 */     registerCommand(new CommandHelp());
/*  25 */     registerCommand(new CommandDebug());
/*  26 */     registerCommand(new CommandServerMessage());
/*  27 */     registerCommand(new CommandServerSay());
/*  28 */     registerCommand(new CommandSetSpawnpoint());
/*  29 */     registerCommand(new CommandGameRule());
/*  30 */     registerCommand(new CommandClearInventory());
/*  31 */     registerCommand(new ServerCommandTestFor());
/*  32 */     registerCommand(new CommandSpreadPlayers());
/*  33 */     registerCommand(new CommandPlaySound());
/*  34 */     registerCommand(new ServerCommandScoreboard());
/*     */     
/*  36 */     if (MinecraftServer.getServer().isDedicatedServer()) {
/*     */       
/*  38 */       registerCommand(new CommandServerOp());
/*  39 */       registerCommand(new CommandServerDeop());
/*  40 */       registerCommand(new CommandServerStop());
/*  41 */       registerCommand(new CommandServerSaveAll());
/*  42 */       registerCommand(new CommandServerSaveOff());
/*  43 */       registerCommand(new CommandServerSaveOn());
/*  44 */       registerCommand(new CommandServerBanIp());
/*  45 */       registerCommand(new CommandServerPardonIp());
/*  46 */       registerCommand(new CommandServerBan());
/*  47 */       registerCommand(new CommandServerBanlist());
/*  48 */       registerCommand(new CommandServerPardon());
/*  49 */       registerCommand(new CommandServerKick());
/*  50 */       registerCommand(new CommandServerList());
/*  51 */       registerCommand(new CommandServerWhitelist());
/*  52 */       registerCommand(new CommandSetPlayerTimeout());
/*     */     }
/*     */     else {
/*     */       
/*  56 */       registerCommand(new CommandServerPublishLocal());
/*     */     } 
/*     */     
/*  59 */     CommandBase.setAdminCommander(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyAdmins(ICommandSender par1ICommandSender, int par2, String par3Str, Object... par4ArrayOfObj) {
/*  68 */     boolean var5 = true;
/*     */     
/*  70 */     if (par1ICommandSender instanceof TileEntityCommandBlock && !(MinecraftServer.getServer()).worldServers[0].getGameRules().getGameRuleBooleanValue("commandBlockOutput"))
/*     */     {
/*  72 */       var5 = false;
/*     */     }
/*     */     
/*  75 */     ChatMessageComponent var6 = ChatMessageComponent.createFromTranslationWithSubstitutions("chat.type.admin", new Object[] { par1ICommandSender.getCommandSenderName(), ChatMessageComponent.createFromTranslationWithSubstitutions(par3Str, par4ArrayOfObj) });
/*  76 */     var6.setColor(EnumChatFormatting.GRAY);
/*  77 */     var6.setItalic(Boolean.valueOf(true));
/*     */     
/*  79 */     if (var5) {
/*     */       
/*  81 */       Iterator<ServerPlayer> var7 = (MinecraftServer.getServer().getConfigurationManager()).playerEntityList.iterator();
/*     */       
/*  83 */       while (var7.hasNext()) {
/*     */         
/*  85 */         ServerPlayer var8 = var7.next();
/*     */         
/*  87 */         if (var8 != par1ICommandSender && MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(var8.getCommandSenderName()))
/*     */         {
/*  89 */           var8.sendChatToPlayer(var6);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     if (par1ICommandSender != MinecraftServer.getServer())
/*     */     {
/*  96 */       MinecraftServer.getServer().sendChatToPlayer(var6);
/*     */     }
/*     */     
/*  99 */     if ((par2 & 0x1) != 1)
/*     */     {
/*     */       
/* 102 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions(par3Str, par4ArrayOfObj).setColor(EnumChatFormatting.YELLOW));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerCommandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */