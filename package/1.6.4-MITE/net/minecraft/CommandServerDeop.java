/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandServerDeop
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 12 */     return "deop";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 17 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 23 */     return "commands.deop.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 28 */     if (strings.length == 1 && strings[0].length() > 0) {
/* 29 */       MinecraftServer.getServer().getConfigurationManager().removeOp(strings[0]);
/*    */       
/* 31 */       notifyAdmins(iCommandSender, "commands.deop.success", new Object[] { strings[0] });
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     throw new WrongUsageException("commands.deop.usage", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 40 */     if (strings.length == 1) {
/* 41 */       return getListOfStringsFromIterableMatchingLastWord(strings, MinecraftServer.getServer().getConfigurationManager().getOps());
/*    */     }
/*    */     
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerDeop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */