/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RConConsoleSource
/*    */   implements ICommandSender
/*    */ {
/* 10 */   public static final RConConsoleSource consoleBuffer = new RConConsoleSource();
/*    */   
/* 12 */   private StringBuffer buffer = new StringBuffer();
/*    */   
/*    */   public void resetLog() {
/* 15 */     this.buffer.setLength(0);
/*    */   }
/*    */   
/*    */   public String getChatBuffer() {
/* 19 */     return this.buffer.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandSenderName() {
/* 24 */     return "Rcon";
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendChatToPlayer(ChatMessageComponent chatMessageComponent) {
/* 29 */     this.buffer.append(chatMessageComponent.toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canCommandSenderUseCommand(int i, String string) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChunkCoordinates getPlayerCoordinates() {
/* 39 */     return new ChunkCoordinates(0, 0, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public World getEntityWorld() {
/* 44 */     return MinecraftServer.getServer().getEntityWorld();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConConsoleSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */