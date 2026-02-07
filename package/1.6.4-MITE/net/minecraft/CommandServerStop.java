/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class CommandServerStop
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/*  9 */     return "stop";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 17 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 22 */     return "commands.stop.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 27 */     notifyAdmins(par1ICommandSender, "commands.stop.start", new Object[0]);
/* 28 */     (MinecraftServer.getServer()).save_world_maps_on_shutdown = MinecraftServer.getServer().isServerSideMappingEnabled();
/* 29 */     MinecraftServer.getServer().initiateShutdown();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerStop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */