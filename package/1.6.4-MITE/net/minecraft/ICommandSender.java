package net.minecraft;

public interface ICommandSender {
  String getCommandSenderName();
  
  void sendChatToPlayer(ChatMessageComponent paramChatMessageComponent);
  
  boolean canCommandSenderUseCommand(int paramInt, String paramString);
  
  ChunkCoordinates getPlayerCoordinates();
  
  World getEntityWorld();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ICommandSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */