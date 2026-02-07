/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class CommandDifficulty
/*    */   extends CommandBase
/*    */ {
/*  8 */   private static final String[] difficulties = new String[] { "options.difficulty.peaceful", "options.difficulty.easy", "options.difficulty.normal", "options.difficulty.hard" };
/*    */ 
/*    */   
/*    */   public String getCommandName() {
/* 12 */     return "difficulty";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 20 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 25 */     return "commands.difficulty.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 30 */     notifyAdmins(par1ICommandSender, "Command '" + getCommandName() + "' not available", new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getDifficultyForName(ICommandSender par1ICommandSender, String par2Str) {
/* 49 */     return (!par2Str.equalsIgnoreCase("peaceful") && !par2Str.equalsIgnoreCase("p")) ? ((!par2Str.equalsIgnoreCase("easy") && !par2Str.equalsIgnoreCase("e")) ? ((!par2Str.equalsIgnoreCase("normal") && !par2Str.equalsIgnoreCase("n")) ? ((!par2Str.equalsIgnoreCase("hard") && !par2Str.equalsIgnoreCase("h")) ? parseIntBounded(par1ICommandSender, par2Str, 0, 3) : 3) : 2) : 1) : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 57 */     return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] { "peaceful", "easy", "normal", "hard" }) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandDifficulty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */