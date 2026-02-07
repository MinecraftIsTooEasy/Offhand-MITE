/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class CommandServerWhitelist
/*     */   extends CommandBase
/*     */ {
/*     */   public String getCommandName() {
/*  12 */     return "whitelist";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  20 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/*  25 */     return "commands.whitelist.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  30 */     if (par2ArrayOfStr.length >= 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  46 */       if (par2ArrayOfStr[0].equals("list")) {
/*     */         
/*  48 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.whitelist.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().getConfigurationManager().getWhiteListedPlayers().size()), Integer.valueOf((MinecraftServer.getServer().getConfigurationManager().getAvailablePlayerDat()).length) }));
/*  49 */         Set var3 = MinecraftServer.getServer().getConfigurationManager().getWhiteListedPlayers();
/*  50 */         par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(joinNiceString(var3.toArray((Object[])new String[var3.size()]))));
/*     */         
/*     */         return;
/*     */       } 
/*  54 */       if (par2ArrayOfStr[0].equals("add")) {
/*     */         
/*  56 */         if (par2ArrayOfStr.length < 2)
/*     */         {
/*  58 */           throw new WrongUsageException("commands.whitelist.add.usage", new Object[0]);
/*     */         }
/*     */         
/*  61 */         MinecraftServer.getServer().getConfigurationManager().addToWhiteList(par2ArrayOfStr[1]);
/*  62 */         notifyAdmins(par1ICommandSender, "commands.whitelist.add.success", new Object[] { par2ArrayOfStr[1] });
/*     */         
/*     */         return;
/*     */       } 
/*  66 */       if (par2ArrayOfStr[0].equals("remove")) {
/*     */         
/*  68 */         if (par2ArrayOfStr.length < 2)
/*     */         {
/*  70 */           throw new WrongUsageException("commands.whitelist.remove.usage", new Object[0]);
/*     */         }
/*     */         
/*  73 */         MinecraftServer.getServer().getConfigurationManager().removeFromWhitelist(par2ArrayOfStr[1]);
/*  74 */         notifyAdmins(par1ICommandSender, "commands.whitelist.remove.success", new Object[] { par2ArrayOfStr[1] });
/*     */         
/*     */         return;
/*     */       } 
/*  78 */       if (par2ArrayOfStr[0].equals("reload")) {
/*     */         
/*  80 */         MinecraftServer.getServer().getConfigurationManager().loadWhiteList();
/*  81 */         notifyAdmins(par1ICommandSender, "commands.whitelist.reloaded", new Object[0]);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  86 */     throw new WrongUsageException("commands.whitelist.usage", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  94 */     if (par2ArrayOfStr.length == 1)
/*     */     {
/*  96 */       return getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] { "on", "off", "list", "add", "remove", "reload" });
/*     */     }
/*     */ 
/*     */     
/* 100 */     if (par2ArrayOfStr.length == 2) {
/*     */       
/* 102 */       if (par2ArrayOfStr[0].equals("add")) {
/*     */         
/* 104 */         String[] var3 = MinecraftServer.getServer().getConfigurationManager().getAvailablePlayerDat();
/* 105 */         ArrayList<String> var4 = new ArrayList();
/* 106 */         String var5 = par2ArrayOfStr[par2ArrayOfStr.length - 1];
/* 107 */         String[] var6 = var3;
/* 108 */         int var7 = var3.length;
/*     */         
/* 110 */         for (int var8 = 0; var8 < var7; var8++) {
/*     */           
/* 112 */           String var9 = var6[var8];
/*     */           
/* 114 */           if (doesStringStartWith(var5, var9) && !MinecraftServer.getServer().getConfigurationManager().getWhiteListedPlayers().contains(var9))
/*     */           {
/* 116 */             var4.add(var9);
/*     */           }
/*     */         } 
/*     */         
/* 120 */         return var4;
/*     */       } 
/*     */       
/* 123 */       if (par2ArrayOfStr[0].equals("remove"))
/*     */       {
/* 125 */         return getListOfStringsFromIterableMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getConfigurationManager().getWhiteListedPlayers());
/*     */       }
/*     */     } 
/*     */     
/* 129 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerWhitelist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */