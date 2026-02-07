/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.PublicKey;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.crypto.SecretKey;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class NetLoginHandler
/*     */   extends NetHandler
/*     */ {
/*  19 */   private static Random rand = new Random();
/*     */   
/*     */   private byte[] verifyToken;
/*     */   
/*     */   private final MinecraftServer mcServer;
/*     */   
/*     */   public final TcpConnection myTCPConnection;
/*     */   
/*     */   public boolean connectionComplete;
/*     */   
/*     */   private int connectionTimer;
/*     */   
/*     */   private String clientUsername;
/*     */   private volatile boolean field_72544_i;
/*  33 */   private String loginServerId = "";
/*     */   
/*     */   private boolean field_92079_k;
/*     */   
/*     */   private SecretKey sharedKey;
/*     */ 
/*     */   
/*     */   public NetLoginHandler(MinecraftServer par1MinecraftServer, Socket par2Socket, String par3Str) throws IOException {
/*  41 */     this.mcServer = par1MinecraftServer;
/*  42 */     this.myTCPConnection = new TcpConnection(par1MinecraftServer.getLogAgent(), par2Socket, par3Str, this, par1MinecraftServer.getKeyPair().getPrivate());
/*  43 */     this.myTCPConnection.field_74468_e = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tryLogin() {
/*  52 */     if (this.field_72544_i)
/*     */     {
/*  54 */       initializePlayerConnection();
/*     */     }
/*     */     
/*  57 */     if (this.connectionTimer++ == 600) {
/*     */       
/*  59 */       raiseErrorAndDisconnect("Took too long to log in");
/*     */     }
/*     */     else {
/*     */       
/*  63 */       this.myTCPConnection.processReadPackets();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void raiseErrorAndDisconnect(String par1Str) {
/*     */     try {
/*  71 */       this.mcServer.getLogAgent().logInfo("Disconnecting " + getUsernameAndAddress() + ": " + par1Str);
/*     */       
/*  73 */       this.myTCPConnection.addToSendQueue(new Packet255KickDisconnect(par1Str, false));
/*  74 */       this.myTCPConnection.serverShutdown();
/*  75 */       this.connectionComplete = true;
/*     */     }
/*  77 */     catch (Exception var3) {
/*     */       
/*  79 */       var3.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleClientProtocol(Packet2ClientProtocol par1Packet2ClientProtocol) {
/* 173 */     if (this.clientUsername != null) {
/*     */       
/* 175 */       raiseErrorAndDisconnect("Quit repeating yourself!");
/*     */       
/*     */       return;
/*     */     } 
/* 179 */     this.clientUsername = par1Packet2ClientProtocol.getUsername();
/*     */     
/* 181 */     if (!this.clientUsername.equals(StringUtils.stripControlCodes(this.clientUsername))) {
/*     */       
/* 183 */       raiseErrorAndDisconnect("Invalid username!");
/*     */       
/*     */       return;
/*     */     } 
/* 187 */     PublicKey var2 = this.mcServer.getKeyPair().getPublic();
/*     */     
/* 189 */     if (par1Packet2ClientProtocol.getProtocolVersion() != 78) {
/*     */       
/* 191 */       if (par1Packet2ClientProtocol.getProtocolVersion() > 78) {
/* 192 */         raiseErrorAndDisconnect("Outdated server!");
/*     */       } else {
/* 194 */         raiseErrorAndDisconnect("Outdated client!");
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 199 */     if (!"1.6.4".equals(par1Packet2ClientProtocol.MC_version) || !"R196".equals(par1Packet2ClientProtocol.MITE_release_number)) {
/*     */       
/* 201 */       raiseErrorAndDisconnect("This server requires a 1.6.4-MITE R196 client.");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if (!this.mcServer.getConfigurationManager().isAllowedToLogin(this.clientUsername)) {
/*     */       
/* 217 */       raiseErrorAndDisconnect("You are not white-listed on this server!");
/*     */       
/*     */       return;
/*     */     } 
/* 221 */     if (DedicatedServer.isTournamentThatUsesAllottedTimes()) {
/*     */       
/* 223 */       Long tick_of_disconnection = (Long)DedicatedServer.players_kicked_for_depleted_time_shares.get(this.clientUsername);
/*     */       
/* 225 */       if (tick_of_disconnection != null) {
/*     */         
/* 227 */         long current_tick = DedicatedServer.getServer().worldServerForDimension(0).getTotalWorldTime();
/*     */         
/* 229 */         if (current_tick - tick_of_disconnection.longValue() < 72000L) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 234 */           raiseErrorAndDisconnect("Please wait at least an hour for your time share to replenish");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 240 */     if (DedicatedServer.disconnection_penalty_enabled) {
/*     */       
/* 242 */       SoonestReconnectionTime srt = DedicatedServer.getSoonestReconnectionTime(this.clientUsername);
/*     */       
/* 244 */       if (srt != null) {
/*     */         
/* 246 */         World world = DedicatedServer.getServer().worldServerForDimension(0);
/*     */         
/* 248 */         long current_tick = world.getTotalWorldTime();
/*     */ 
/*     */ 
/*     */         
/* 252 */         srt.ticks_disconnected += Math.max(current_tick - srt.tick_of_disconnection, 0L);
/*     */         
/* 254 */         boolean reconnection_prevented = true;
/*     */         
/* 256 */         int hour_of_latest_reconnection = World.getHourOfLatestReconnection();
/*     */ 
/*     */         
/* 259 */         if (world.getHourOfDay() == hour_of_latest_reconnection) {
/*     */           
/* 261 */           reconnection_prevented = false;
/* 262 */           srt.ticks_disconnected = 0L;
/*     */         
/*     */         }
/* 265 */         else if (srt.ticks_disconnected <= 600L) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 272 */           reconnection_prevented = false;
/*     */         }
/* 274 */         else if (current_tick >= srt.soonest_reconnection_tick) {
/*     */           
/* 276 */           reconnection_prevented = (world.getHourOfDay() < srt.adjusted_hour_of_disconnection || world.getHourOfDay() > hour_of_latest_reconnection);
/*     */           
/* 278 */           if (!reconnection_prevented)
/*     */           {
/* 280 */             srt.ticks_disconnected = 0L;
/*     */           }
/*     */         } 
/* 283 */         if (reconnection_prevented) {
/*     */           
/* 285 */           int message_type = 1;
/*     */           
/* 287 */           int ticks_to_wait = (int)(srt.soonest_reconnection_tick - current_tick);
/*     */           
/* 289 */           while (ticks_to_wait <= 0) {
/* 290 */             ticks_to_wait += 24000;
/*     */           }
/* 292 */           int ticks_until_hour_of_latest_reconnection = hour_of_latest_reconnection * 1000 - world.getAdjustedTimeOfDay();
/*     */           
/* 294 */           if (ticks_until_hour_of_latest_reconnection < 0) {
/* 295 */             ticks_until_hour_of_latest_reconnection += 24000;
/*     */           }
/* 297 */           if (ticks_until_hour_of_latest_reconnection < ticks_to_wait) {
/*     */             
/* 299 */             ticks_to_wait = ticks_until_hour_of_latest_reconnection;
/* 300 */             message_type = 2;
/*     */           } 
/*     */           
/* 303 */           int seconds_delay = ticks_to_wait / 20;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 317 */           this.myTCPConnection.addToSendQueue((new Packet85SimpleSignal(EnumSignal.reconnection_delay)).setByte(message_type).setShort(srt.adjusted_hour_of_disconnection).setInteger(seconds_delay));
/*     */           
/* 319 */           raiseErrorAndDisconnect("");
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 325 */     this.loginServerId = this.mcServer.isServerInOnlineMode() ? Long.toString(rand.nextLong(), 16) : "-";
/* 326 */     this.verifyToken = new byte[4];
/* 327 */     rand.nextBytes(this.verifyToken);
/* 328 */     this.myTCPConnection.addToSendQueue(new Packet253ServerAuthData(this.loginServerId, var2, this.verifyToken));
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleSharedKey(Packet252SharedKey par1Packet252SharedKey) {
/* 333 */     PrivateKey var2 = this.mcServer.getKeyPair().getPrivate();
/* 334 */     this.sharedKey = par1Packet252SharedKey.getSharedKey(var2);
/*     */     
/* 336 */     if (!Arrays.equals(this.verifyToken, par1Packet252SharedKey.getVerifyToken(var2)))
/*     */     {
/* 338 */       raiseErrorAndDisconnect("Invalid client reply");
/*     */     }
/*     */     
/* 341 */     this.myTCPConnection.addToSendQueue(new Packet252SharedKey());
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleClientCommand(Packet205ClientCommand par1Packet205ClientCommand) {
/* 346 */     if (par1Packet205ClientCommand.forceRespawn == 0) {
/*     */       
/* 348 */       if (this.field_92079_k) {
/*     */         
/* 350 */         raiseErrorAndDisconnect("Duplicate login");
/*     */         
/*     */         return;
/*     */       } 
/* 354 */       this.field_92079_k = true;
/*     */       
/* 356 */       if (this.mcServer.isServerInOnlineMode()) {
/*     */         
/* 358 */         (new ThreadLoginVerifier(this)).start();
/*     */       }
/*     */       else {
/*     */         
/* 362 */         this.field_72544_i = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleLogin(Packet1Login par1Packet1Login) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializePlayerConnection() {
/* 374 */     String var1 = this.mcServer.getConfigurationManager().allowUserToConnect(this.myTCPConnection.getSocketAddress(), this.clientUsername);
/*     */     
/* 376 */     if (var1 != null) {
/*     */       
/* 378 */       raiseErrorAndDisconnect(var1);
/*     */     }
/*     */     else {
/*     */       
/* 382 */       ServerPlayer var2 = this.mcServer.getConfigurationManager().createPlayerForUser(this.clientUsername);
/*     */       
/* 384 */       if (var2 != null)
/*     */       {
/* 386 */         this.mcServer.getConfigurationManager().initializeConnectionToPlayer(this.myTCPConnection, var2);
/*     */       }
/*     */     } 
/*     */     
/* 390 */     this.connectionComplete = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleErrorMessage(String par1Str, Object[] par2ArrayOfObj) {
/* 395 */     this.mcServer.getLogAgent().logInfo(getUsernameAndAddress() + " lost connection");
/* 396 */     this.connectionComplete = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleServerPing(Packet254ServerPing par1Packet254ServerPing) {
/*     */     try {
/* 406 */       ServerConfigurationManager var2 = this.mcServer.getConfigurationManager();
/* 407 */       String var3 = null;
/*     */       
/* 409 */       int current_player_count = var2.getCurrentPlayerCount();
/*     */       
/* 411 */       if (DedicatedServer.isTournament() && this.mcServer.isZevimrgvOnServer()) {
/* 412 */         current_player_count--;
/*     */       }
/* 414 */       if (par1Packet254ServerPing.func_140050_d()) {
/*     */ 
/*     */         
/* 417 */         var3 = this.mcServer.getMOTD() + "§" + current_player_count + "§" + var2.getMaxPlayers();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 422 */         List<Serializable> var4 = Arrays.asList(new Serializable[] { Integer.valueOf(1), Integer.valueOf(78), this.mcServer.getMinecraftVersion(), this.mcServer.getMOTD(), Integer.valueOf(current_player_count), Integer.valueOf(var2.getMaxPlayers()) });
/*     */ 
/*     */         
/* 425 */         for (Iterator<Serializable> var5 = var4.iterator(); var5.hasNext(); var3 = var3 + var6.toString().replaceAll("\000", "")) {
/*     */           
/* 427 */           Object var6 = var5.next();
/*     */           
/* 429 */           if (var3 == null) {
/*     */             
/* 431 */             var3 = "§";
/*     */           }
/*     */           else {
/*     */             
/* 435 */             var3 = var3 + "\000";
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 440 */       InetAddress var8 = null;
/*     */       
/* 442 */       if (this.myTCPConnection.getSocket() != null)
/*     */       {
/* 444 */         var8 = this.myTCPConnection.getSocket().getInetAddress();
/*     */       }
/*     */ 
/*     */       
/* 448 */       this.myTCPConnection.addToSendQueue(new Packet255KickDisconnect(var3, false));
/* 449 */       this.myTCPConnection.serverShutdown();
/*     */       
/* 451 */       if (var8 != null && this.mcServer.getNetworkThread() instanceof DedicatedServerListenThread)
/*     */       {
/* 453 */         ((DedicatedServerListenThread)this.mcServer.getNetworkThread()).func_71761_a(var8);
/*     */       }
/*     */       
/* 456 */       this.connectionComplete = true;
/*     */     }
/* 458 */     catch (Exception var7) {
/*     */       
/* 460 */       var7.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unexpectedPacket(Packet par1Packet) {
/* 470 */     raiseErrorAndDisconnect("Protocol error");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsernameAndAddress() {
/* 475 */     return (this.clientUsername != null) ? (this.clientUsername + " [" + this.myTCPConnection.getSocketAddress().toString() + "]") : this.myTCPConnection.getSocketAddress().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isServerHandler() {
/* 483 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConnectionClosed() {
/* 488 */     return this.connectionComplete;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String getServerId(NetLoginHandler par0NetLoginHandler) {
/* 496 */     return par0NetLoginHandler.loginServerId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static MinecraftServer getLoginMinecraftServer(NetLoginHandler par0NetLoginHandler) {
/* 504 */     return par0NetLoginHandler.mcServer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static SecretKey getSharedKey(NetLoginHandler par0NetLoginHandler) {
/* 512 */     return par0NetLoginHandler.sharedKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String getClientUsername(NetLoginHandler par0NetLoginHandler) {
/* 520 */     return par0NetLoginHandler.clientUsername;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean func_72531_a(NetLoginHandler par0NetLoginHandler, boolean par1) {
/* 525 */     return par0NetLoginHandler.field_72544_i = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public INetworkManager getNetManager() {
/* 530 */     return this.myTCPConnection;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NetLoginHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */