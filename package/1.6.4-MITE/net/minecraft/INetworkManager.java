package net.minecraft;

import java.net.SocketAddress;

public interface INetworkManager {
  void setNetHandler(NetHandler paramNetHandler);
  
  void addToSendQueue(Packet paramPacket);
  
  void wakeThreads();
  
  void processReadPackets();
  
  int clearReceivedPackets();
  
  SocketAddress getSocketAddress();
  
  void serverShutdown();
  
  int packetSize();
  
  void networkShutdown(String paramString, Object... paramVarArgs);
  
  void closeConnections();
}


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\INetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */