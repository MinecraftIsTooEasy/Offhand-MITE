/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class CommandDefaultGameMode
/*    */   extends CommandGameMode
/*    */ {
/*    */   public String getCommandName() {
/* 10 */     return "defaultgamemode";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 15 */     return "commands.defaultgamemode.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 20 */     notifyAdmins(par1ICommandSender, "Command '" + getCommandName() + "' not available", new Object[0]);
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
/*    */   protected void setGameType(EnumGameType par1EnumGameType) {
/* 36 */     MinecraftServer var2 = MinecraftServer.getServer();
/* 37 */     var2.setGameType(par1EnumGameType);
/*    */ 
/*    */     
/* 40 */     if (var2.getForceGamemode())
/*    */     {
/* 42 */       for (Iterator<ServerPlayer> var3 = (MinecraftServer.getServer().getConfigurationManager()).playerEntityList.iterator(); var3.hasNext(); var4.fallDistance = 0.0F) {
/*    */         
/* 44 */         ServerPlayer var4 = var3.next();
/* 45 */         var4.setGameType(par1EnumGameType);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandDefaultGameMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */