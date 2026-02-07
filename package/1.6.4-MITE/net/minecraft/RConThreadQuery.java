/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.PortUnreachableException;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketException;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ public class RConThreadQuery
/*     */   extends RConThreadBase
/*     */ {
/*     */   private long lastAuthCheckTime;
/*     */   private int queryPort;
/*     */   private int serverPort;
/*  25 */   private byte[] buffer = new byte[1460]; private int maxPlayers; private String serverMotd;
/*     */   private String worldName;
/*     */   private DatagramSocket querySocket;
/*     */   private DatagramPacket incomingPacket;
/*     */   private Map field_72644_p;
/*     */   private String queryHostname;
/*     */   private String serverHostname;
/*     */   private Map queryClients;
/*     */   private long time;
/*     */   private RConOutputStream output;
/*     */   private long lastQueryResponseTime;
/*     */   
/*     */   public RConThreadQuery(IServer iServer) {
/*  38 */     super(iServer);
/*     */     
/*  40 */     this.queryPort = iServer.getIntProperty("query.port", 0);
/*  41 */     this.serverHostname = iServer.getHostname();
/*  42 */     this.serverPort = iServer.getPort();
/*  43 */     this.serverMotd = iServer.getServerMOTD();
/*  44 */     this.maxPlayers = iServer.getMaxPlayers();
/*  45 */     this.worldName = iServer.getFolderName();
/*     */ 
/*     */     
/*  48 */     this.lastQueryResponseTime = 0L;
/*     */     
/*  50 */     this.queryHostname = "0.0.0.0";
/*     */ 
/*     */     
/*  53 */     if (0 == this.serverHostname.length() || this.queryHostname.equals(this.serverHostname)) {
/*     */       
/*  55 */       this.serverHostname = "0.0.0.0";
/*     */       try {
/*  57 */         InetAddress inetAddress = InetAddress.getLocalHost();
/*  58 */         this.queryHostname = inetAddress.getHostAddress();
/*  59 */       } catch (UnknownHostException unknownHostException) {
/*  60 */         logWarning("Unable to determine local host IP, please set server-ip in '" + iServer.getSettingsFilename() + "' : " + unknownHostException.getMessage());
/*     */       } 
/*     */     } else {
/*  63 */       this.queryHostname = this.serverHostname;
/*     */     } 
/*     */ 
/*     */     
/*  67 */     if (0 == this.queryPort) {
/*     */       
/*  69 */       this.queryPort = this.serverPort;
/*  70 */       logInfo("Setting default query port to " + this.queryPort);
/*  71 */       iServer.setProperty("query.port", Integer.valueOf(this.queryPort));
/*  72 */       iServer.setProperty("debug", Boolean.valueOf(false));
/*  73 */       iServer.saveProperties();
/*     */     } 
/*     */     
/*  76 */     this.field_72644_p = new HashMap<Object, Object>();
/*  77 */     this.output = new RConOutputStream(1460);
/*  78 */     this.queryClients = new HashMap<Object, Object>();
/*  79 */     this.time = (new Date()).getTime();
/*     */   }
/*     */   
/*     */   private void sendResponsePacket(byte[] bs, DatagramPacket datagramPacket) {
/*  83 */     this.querySocket.send(new DatagramPacket(bs, bs.length, datagramPacket.getSocketAddress()));
/*     */   }
/*     */   private boolean parseIncomingPacket(DatagramPacket datagramPacket) {
/*     */     RConOutputStream rConOutputStream;
/*  87 */     byte[] arrayOfByte = datagramPacket.getData();
/*  88 */     int i = datagramPacket.getLength();
/*  89 */     SocketAddress socketAddress = datagramPacket.getSocketAddress();
/*  90 */     logDebug("Packet len " + i + " [" + socketAddress + "]");
/*  91 */     if (3 > i || -2 != arrayOfByte[0] || -3 != arrayOfByte[1]) {
/*     */       
/*  93 */       logDebug("Invalid packet [" + socketAddress + "]");
/*  94 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     logDebug("Packet '" + RConUtils.getByteAsHexString(arrayOfByte[2]) + "' [" + socketAddress + "]");
/*  99 */     switch (arrayOfByte[2]) {
/*     */       
/*     */       case 9:
/* 102 */         sendAuthChallenge(datagramPacket);
/* 103 */         logDebug("Challenge [" + socketAddress + "]");
/* 104 */         return true;
/*     */ 
/*     */       
/*     */       case 0:
/* 108 */         if (!verifyClientAuth(datagramPacket).booleanValue()) {
/* 109 */           logDebug("Invalid challenge [" + socketAddress + "]");
/* 110 */           return false;
/*     */         } 
/*     */         
/* 113 */         if (15 == i) {
/*     */           
/* 115 */           sendResponsePacket(createQueryResponse(datagramPacket), datagramPacket);
/* 116 */           logDebug("Rules [" + socketAddress + "]");
/*     */           break;
/*     */         } 
/* 119 */         rConOutputStream = new RConOutputStream(1460);
/* 120 */         rConOutputStream.writeInt(0);
/* 121 */         rConOutputStream.writeByteArray(getRequestID(datagramPacket.getSocketAddress()));
/* 122 */         rConOutputStream.writeString(this.serverMotd);
/* 123 */         rConOutputStream.writeString("SMP");
/* 124 */         rConOutputStream.writeString(this.worldName);
/* 125 */         rConOutputStream.writeString(Integer.toString(getNumberOfPlayers()));
/* 126 */         rConOutputStream.writeString(Integer.toString(this.maxPlayers));
/* 127 */         rConOutputStream.writeShort((short)this.serverPort);
/* 128 */         rConOutputStream.writeString(this.queryHostname);
/*     */         
/* 130 */         sendResponsePacket(rConOutputStream.toByteArray(), datagramPacket);
/* 131 */         logDebug("Status [" + socketAddress + "]");
/*     */         break;
/*     */     } 
/*     */     
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   private byte[] createQueryResponse(DatagramPacket datagramPacket) {
/* 139 */     long l = MinecraftServer.getSystemTimeMillis();
/* 140 */     if (l < this.lastQueryResponseTime + 5000L) {
/*     */       
/* 142 */       byte[] arrayOfByte1 = this.output.toByteArray();
/* 143 */       byte[] arrayOfByte2 = getRequestID(datagramPacket.getSocketAddress());
/* 144 */       arrayOfByte1[1] = arrayOfByte2[0];
/* 145 */       arrayOfByte1[2] = arrayOfByte2[1];
/* 146 */       arrayOfByte1[3] = arrayOfByte2[2];
/* 147 */       arrayOfByte1[4] = arrayOfByte2[3];
/*     */       
/* 149 */       return arrayOfByte1;
/*     */     } 
/*     */     
/* 152 */     this.lastQueryResponseTime = l;
/*     */     
/* 154 */     this.output.reset();
/* 155 */     this.output.writeInt(0);
/* 156 */     this.output.writeByteArray(getRequestID(datagramPacket.getSocketAddress()));
/* 157 */     this.output.writeString("splitnum");
/* 158 */     this.output.writeInt(128);
/* 159 */     this.output.writeInt(0);
/*     */ 
/*     */     
/* 162 */     this.output.writeString("hostname");
/* 163 */     this.output.writeString(this.serverMotd);
/* 164 */     this.output.writeString("gametype");
/* 165 */     this.output.writeString("SMP");
/* 166 */     this.output.writeString("game_id");
/* 167 */     this.output.writeString("MINECRAFT");
/* 168 */     this.output.writeString("version");
/* 169 */     this.output.writeString(this.server.getMinecraftVersion());
/* 170 */     this.output.writeString("plugins");
/* 171 */     this.output.writeString(this.server.getPlugins());
/* 172 */     this.output.writeString("map");
/* 173 */     this.output.writeString(this.worldName);
/* 174 */     this.output.writeString("numplayers");
/* 175 */     this.output.writeString("" + getNumberOfPlayers());
/* 176 */     this.output.writeString("maxplayers");
/* 177 */     this.output.writeString("" + this.maxPlayers);
/* 178 */     this.output.writeString("hostport");
/* 179 */     this.output.writeString("" + this.serverPort);
/* 180 */     this.output.writeString("hostip");
/* 181 */     this.output.writeString(this.queryHostname);
/* 182 */     this.output.writeInt(0);
/* 183 */     this.output.writeInt(1);
/*     */ 
/*     */ 
/*     */     
/* 187 */     this.output.writeString("player_");
/* 188 */     this.output.writeInt(0);
/*     */     
/* 190 */     String[] arrayOfString = this.server.getAllUsernames();
/* 191 */     byte b1 = (byte)arrayOfString.length; byte b2;
/* 192 */     for (b2 = (byte)(b1 - 1); b2 >= 0; b2 = (byte)(b2 - 1)) {
/* 193 */       this.output.writeString(arrayOfString[b2]);
/*     */     }
/* 195 */     this.output.writeInt(0);
/*     */     
/* 197 */     return this.output.toByteArray();
/*     */   }
/*     */   
/*     */   private byte[] getRequestID(SocketAddress socketAddress) {
/* 201 */     return ((RConThreadQueryAuth)this.queryClients.get(socketAddress)).getRequestId();
/*     */   }
/*     */   
/*     */   private Boolean verifyClientAuth(DatagramPacket datagramPacket) {
/* 205 */     SocketAddress socketAddress = datagramPacket.getSocketAddress();
/* 206 */     if (!this.queryClients.containsKey(socketAddress))
/*     */     {
/* 208 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/* 211 */     byte[] arrayOfByte = datagramPacket.getData();
/* 212 */     if (((RConThreadQueryAuth)this.queryClients.get(socketAddress)).getRandomChallenge() != RConUtils.getBytesAsBEint(arrayOfByte, 7, datagramPacket.getLength()))
/*     */     {
/* 214 */       return Boolean.valueOf(false);
/*     */     }
/*     */ 
/*     */     
/* 218 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */   private void sendAuthChallenge(DatagramPacket datagramPacket) {
/* 222 */     RConThreadQueryAuth rConThreadQueryAuth = new RConThreadQueryAuth(this, datagramPacket);
/* 223 */     this.queryClients.put(datagramPacket.getSocketAddress(), rConThreadQueryAuth);
/*     */     
/* 225 */     sendResponsePacket(rConThreadQueryAuth.getChallengeValue(), datagramPacket);
/*     */   }
/*     */   
/*     */   private void cleanQueryClientsMap() {
/* 229 */     if (!this.running) {
/*     */       return;
/*     */     }
/*     */     
/* 233 */     long l = MinecraftServer.getSystemTimeMillis();
/* 234 */     if (l < this.lastAuthCheckTime + 30000L) {
/*     */       return;
/*     */     }
/* 237 */     this.lastAuthCheckTime = l;
/*     */     
/* 239 */     Iterator<Map.Entry> iterator = this.queryClients.entrySet().iterator();
/* 240 */     while (iterator.hasNext()) {
/* 241 */       Map.Entry entry = iterator.next();
/* 242 */       if (((RConThreadQueryAuth)entry.getValue()).hasExpired(l).booleanValue()) {
/* 243 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 250 */     logInfo("Query running on " + this.serverHostname + ":" + this.queryPort);
/* 251 */     this.lastAuthCheckTime = MinecraftServer.getSystemTimeMillis();
/* 252 */     this.incomingPacket = new DatagramPacket(this.buffer, this.buffer.length);
/*     */     
/*     */     try {
/* 255 */       while (this.running) {
/*     */         try {
/* 257 */           this.querySocket.receive(this.incomingPacket);
/*     */ 
/*     */           
/* 260 */           cleanQueryClientsMap();
/*     */ 
/*     */           
/* 263 */           parseIncomingPacket(this.incomingPacket);
/* 264 */         } catch (SocketTimeoutException socketTimeoutException) {
/*     */           
/* 266 */           cleanQueryClientsMap();
/* 267 */         } catch (PortUnreachableException portUnreachableException) {
/*     */         
/* 269 */         } catch (IOException iOException) {
/*     */           
/* 271 */           stopWithException(iOException);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 275 */       closeAllSockets();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startThread() {
/* 281 */     if (this.running) {
/*     */       return;
/*     */     }
/*     */     
/* 285 */     if (0 >= this.queryPort || 65535 < this.queryPort) {
/* 286 */       logWarning("Invalid query port " + this.queryPort + " found in '" + this.server.getSettingsFilename() + "' (queries disabled)");
/*     */       
/*     */       return;
/*     */     } 
/* 290 */     if (initQuerySystem()) {
/* 291 */       super.startThread();
/*     */     }
/*     */   }
/*     */   
/*     */   private void stopWithException(Exception exception) {
/* 296 */     if (!this.running) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 301 */     logWarning("Unexpected exception, buggy JRE? (" + exception.toString() + ")");
/*     */ 
/*     */     
/* 304 */     if (!initQuerySystem()) {
/* 305 */       logSevere("Failed to recover from buggy JRE, shutting down!");
/* 306 */       this.running = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean initQuerySystem() {
/*     */     try {
/* 312 */       this.querySocket = new DatagramSocket(this.queryPort, InetAddress.getByName(this.serverHostname));
/* 313 */       registerSocket(this.querySocket);
/* 314 */       this.querySocket.setSoTimeout(500);
/* 315 */       return true;
/* 316 */     } catch (SocketException socketException) {
/* 317 */       logWarning("Unable to initialise query system on " + this.serverHostname + ":" + this.queryPort + " (Socket): " + socketException.getMessage());
/* 318 */     } catch (UnknownHostException unknownHostException) {
/* 319 */       logWarning("Unable to initialise query system on " + this.serverHostname + ":" + this.queryPort + " (Unknown Host): " + unknownHostException.getMessage());
/* 320 */     } catch (Exception exception) {
/* 321 */       logWarning("Unable to initialise query system on " + this.serverHostname + ":" + this.queryPort + " (E): " + exception.getMessage());
/*     */     } 
/*     */     
/* 324 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RConThreadQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */