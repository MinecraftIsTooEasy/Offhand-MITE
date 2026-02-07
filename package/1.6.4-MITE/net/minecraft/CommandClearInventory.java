/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandClearInventory
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 14 */     return "clear";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender iCommandSender) {
/* 19 */     return "commands.clear.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender iCommandSender, String[] strings) {
/* 29 */     ServerPlayer serverPlayer = (strings.length == 0) ? getCommandSenderAsPlayer(iCommandSender) : getPlayer(iCommandSender, strings[0]);
/*    */     
/* 31 */     boolean bool1 = (strings.length >= 2) ? parseIntWithMin(iCommandSender, strings[1], 1) : true;
/* 32 */     boolean bool2 = (strings.length >= 3) ? parseIntWithMin(iCommandSender, strings[2], 0) : true;
/* 33 */     int i = serverPlayer.inventory.clearInventory(bool1, bool2);
/* 34 */     serverPlayer.inventoryContainer.detectAndSendChanges();
/* 35 */     if (!serverPlayer.capabilities.isCreativeMode) serverPlayer.updateHeldItem();
/*    */     
/* 37 */     if (i == 0) {
/* 38 */       throw new CommandException("commands.clear.failure", new Object[] { serverPlayer.getEntityName() });
/*    */     }
/*    */     
/* 41 */     notifyAdmins(iCommandSender, "commands.clear.success", new Object[] { serverPlayer.getEntityName(), Integer.valueOf(i) });
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
/* 46 */     if (strings.length == 1) {
/* 47 */       return getListOfStringsMatchingLastWord(strings, getAllOnlineUsernames());
/*    */     }
/*    */     
/* 50 */     return null;
/*    */   }
/*    */   
/*    */   protected String[] getAllOnlineUsernames() {
/* 54 */     return MinecraftServer.getServer().getAllUsernames();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] strings, int i) {
/* 59 */     return (i == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandClearInventory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */