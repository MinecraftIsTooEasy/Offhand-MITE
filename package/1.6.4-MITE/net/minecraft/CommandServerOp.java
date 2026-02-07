/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class CommandServerOp
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/* 11 */     return "op";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 19 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 24 */     return "commands.op.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 29 */     notifyAdmins(par1ICommandSender, "Command '" + getCommandName() + "' not available", new Object[0]);
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
/*    */   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 47 */     if (par2ArrayOfStr.length == 1) {
/*    */       
/* 49 */       String var3 = par2ArrayOfStr[par2ArrayOfStr.length - 1];
/* 50 */       ArrayList<String> var4 = new ArrayList();
/* 51 */       String[] var5 = MinecraftServer.getServer().getAllUsernames();
/* 52 */       int var6 = var5.length;
/*    */       
/* 54 */       for (int var7 = 0; var7 < var6; var7++) {
/*    */         
/* 56 */         String var8 = var5[var7];
/*    */         
/* 58 */         if (!MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(var8) && doesStringStartWith(var3, var8))
/*    */         {
/* 60 */           var4.add(var8);
/*    */         }
/*    */       } 
/*    */       
/* 64 */       return var4;
/*    */     } 
/*    */ 
/*    */     
/* 68 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandServerOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */