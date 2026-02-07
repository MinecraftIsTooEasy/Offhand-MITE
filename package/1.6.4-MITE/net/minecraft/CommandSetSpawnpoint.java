/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class CommandSetSpawnpoint
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 10 */     return "spawnpoint";
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
/* 23 */     return "commands.spawnpoint.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 28 */     ServerPlayer var3 = (par2ArrayOfStr.length == 0) ? getCommandSenderAsPlayer(par1ICommandSender) : getPlayer(par1ICommandSender, par2ArrayOfStr[0]);
/*    */     
/* 30 */     if (par2ArrayOfStr.length == 4) {
/*    */       
/* 32 */       if (var3.worldObj != null)
/*    */       {
/* 34 */         byte var4 = 1;
/*    */ 
/*    */ 
/*    */         
/* 38 */         int var10 = var4 + 1;
/*    */         
/* 40 */         int min_xz = MathHelper.floor_double(var3.worldObj.min_entity_pos_xz);
/* 41 */         int max_xz = MathHelper.floor_double(var3.worldObj.max_entity_pos_xz);
/*    */ 
/*    */         
/* 44 */         int var6 = parseIntBounded(par1ICommandSender, par2ArrayOfStr[var4], min_xz, max_xz);
/* 45 */         int var7 = parseIntBounded(par1ICommandSender, par2ArrayOfStr[var10++], 0, 256);
/*    */         
/* 47 */         int var8 = parseIntBounded(par1ICommandSender, par2ArrayOfStr[var10++], min_xz, max_xz);
/* 48 */         var3.setSpawnChunk(new ChunkCoordinates(var6, var7, var8), true);
/* 49 */         notifyAdmins(par1ICommandSender, "commands.spawnpoint.success", new Object[] { var3.getEntityName(), Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8) });
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 54 */       if (par2ArrayOfStr.length > 1)
/*    */       {
/* 56 */         throw new WrongUsageException("commands.spawnpoint.usage", new Object[0]);
/*    */       }
/*    */       
/* 59 */       ChunkCoordinates var11 = var3.getPlayerCoordinates();
/* 60 */       var3.setSpawnChunk(var11, true);
/* 61 */       notifyAdmins(par1ICommandSender, "commands.spawnpoint.success", new Object[] { var3.getEntityName(), Integer.valueOf(var11.posX), Integer.valueOf(var11.posY), Integer.valueOf(var11.posZ) });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 70 */     return (par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2) ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
/* 78 */     return (par2 == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandSetSpawnpoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */