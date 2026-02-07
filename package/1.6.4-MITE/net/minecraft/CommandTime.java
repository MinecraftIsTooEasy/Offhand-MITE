/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class CommandTime
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 10 */     return "time";
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
/* 23 */     return "commands.time.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 28 */     notifyAdmins(par1ICommandSender, "Command '" + getCommandName() + "' not available", new Object[0]);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 71 */     return (par2ArrayOfStr.length == 1) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] { "set", "add" }) : ((par2ArrayOfStr.length == 2 && par2ArrayOfStr[0].equals("set")) ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[] { "day", "night" }) : null);
/*    */   }
/*    */   
/*    */   protected void setTime(ICommandSender par1ICommandSender, int par2) {}
/*    */   
/*    */   protected void addTime(ICommandSender par1ICommandSender, int par2) {}
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */