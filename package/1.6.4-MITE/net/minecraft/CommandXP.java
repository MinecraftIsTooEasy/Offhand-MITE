/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class CommandXP
/*     */   extends CommandBase
/*     */ {
/*     */   public String getCommandName() {
/*  10 */     return "xp";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  18 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/*  23 */     return "commands.xp.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  28 */     notifyAdmins(par1ICommandSender, "Command '" + getCommandName() + "' not available", new Object[0]);
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/*  94 */     return (par2ArrayOfStr.length == 2) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, getAllUsernames()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String[] getAllUsernames() {
/*  99 */     return MinecraftServer.getServer().getAllUsernames();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
/* 107 */     return (par2 == 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandXP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */