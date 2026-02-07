/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class CommandSetPlayerTimeout
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 10 */     return "setidletimeout";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 15 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 20 */     return "commands.setidletimeout.usage";
/*    */   }
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 24 */     if (strings.length == 1) {
/* 25 */       int i = parseIntWithMin(iCommandSender, strings[0], 0);
/* 26 */       MinecraftServer.getServer().func_143006_e(i);
/* 27 */       notifyAdmins(iCommandSender, "commands.setidletimeout.success", new Object[] { Integer.valueOf(i) });
/*    */       
/*    */       return;
/*    */     } 
/* 31 */     throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandSetPlayerTimeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */