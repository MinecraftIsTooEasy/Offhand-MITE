/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerCommand
/*    */ {
/*    */   public final String command;
/*    */   public final ICommandSender sender;
/*    */   public boolean permission_override;
/*    */   
/*    */   public ServerCommand(String par1Str, ICommandSender par2ICommandSender, boolean permission_override) {
/* 13 */     this.command = par1Str;
/* 14 */     this.sender = par2ICommandSender;
/* 15 */     this.permission_override = permission_override;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ServerCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */