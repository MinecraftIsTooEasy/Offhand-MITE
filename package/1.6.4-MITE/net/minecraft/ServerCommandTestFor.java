/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerCommandTestFor
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 12 */     return "testfor";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 17 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 22 */     return "commands.testfor.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 27 */     if (strings.length != 1) throw new WrongUsageException("commands.testfor.usage", new Object[0]); 
/* 28 */     if (!(iCommandSender instanceof TileEntityCommandBlock)) throw new CommandException("commands.testfor.failed", new Object[0]); 
/* 29 */     getPlayer(iCommandSender, strings[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] strings, int i) {
/* 34 */     return (i == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerCommandTestFor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */