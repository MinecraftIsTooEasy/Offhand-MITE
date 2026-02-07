/*    */ package net.minecraft;
/*    */ 
/*    */ public class CommandKill
/*    */   extends CommandBase
/*    */ {
/*    */   public String getCommandName() {
/*  7 */     return "kill";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 15 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender par1ICommandSender) {
/* 20 */     return "commands.kill.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 25 */     ServerPlayer var3 = getCommandSenderAsPlayer(par1ICommandSender);
/*    */     
/* 27 */     if (var3.isGhost()) {
/*    */       
/* 29 */       par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Dedicated_Server cannot use this command"));
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 34 */     var3.attackEntityFrom(new Damage(DamageSource.absolute, 3276.0F));
/* 35 */     par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.kill.success"));
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CommandKill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */