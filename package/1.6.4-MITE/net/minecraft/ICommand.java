package net.minecraft;

import java.util.List;

public interface ICommand extends Comparable {
  String getCommandName();
  
  String getCommandUsage(ICommandSender paramICommandSender);
  
  List getCommandAliases();
  
  void processCommand(ICommandSender paramICommandSender, String[] paramArrayOfString);
  
  boolean canCommandSenderUseCommand(ICommandSender paramICommandSender);
  
  List addTabCompletionOptions(ICommandSender paramICommandSender, String[] paramArrayOfString);
  
  boolean isUsernameIndex(String[] paramArrayOfString, int paramInt);
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ICommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */