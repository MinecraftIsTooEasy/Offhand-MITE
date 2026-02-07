/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandShowSeed
/*    */   extends CommandBase
/*    */ {
/*    */   public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
/* 12 */     return (MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(par1ICommandSender));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandName() {
/* 17 */     return "seed";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 25 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 30 */     return "commands.seed.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 35 */     Object var3 = (par1ICommandSender instanceof EntityPlayer) ? ((EntityPlayer)par1ICommandSender).worldObj : MinecraftServer.getServer().worldServerForDimension(0);
/* 36 */     par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("commands.seed.success", new Object[] { Long.valueOf(((World)var3).getSeed()) }));
/*    */     
/* 38 */     if (par1ICommandSender instanceof ServerPlayer)
/* 39 */       ((ServerPlayer)par1ICommandSender).sendPacket(new Packet85SimpleSignal(EnumSignal.take_screenshot_of_world_seed)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandShowSeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */