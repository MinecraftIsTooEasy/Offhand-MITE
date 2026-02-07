/*      */ package net.minecraft;
/*      */ 
/*      */ import com.google.common.base.Charsets;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.math.BigInteger;
/*      */ import java.net.InetAddress;
/*      */ import java.net.Socket;
/*      */ import java.net.URL;
/*      */ import java.net.URLEncoder;
/*      */ import java.security.MessageDigest;
/*      */ import java.security.PublicKey;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import javax.crypto.SecretKey;
/*      */ import net.minecraft.client.ClientBrandRetriever;
/*      */ import org.apache.commons.io.IOUtils;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NetClientHandler
/*      */   extends NetHandler
/*      */ {
/*      */   private boolean disconnected;
/*   40 */   private static String five = "5";
/*      */   
/*      */   private INetworkManager netManager;
/*      */   
/*      */   public String field_72560_a;
/*      */   
/*      */   private Minecraft mc;
/*      */   
/*      */   private WorldClient worldClient;
/*      */   
/*   50 */   private static String em = "M";
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doneLoadingTerrain;
/*      */ 
/*      */   
/*   57 */   public MapStorage mapStorage = new MapStorage((ISaveHandler)null);
/*      */ 
/*      */   
/*   60 */   private Map playerInfoMap = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   public List playerInfoList = new ArrayList();
/*   66 */   public int currentServerMaxPlayers = 20;
/*      */   
/*      */   private GuiScreen field_98183_l;
/*   69 */   private static String dee = "D";
/*      */ 
/*      */   
/*   72 */   Random rand = new Random();
/*      */   
/*   74 */   private static Class[] classes = new Class[] { ContainerPlayer.class, ContainerWorkbench.class, MITEConstant.class, MITEContainerCrafting.class, EntityClientPlayerMP.class, EntityPlayer.class, ClientPlayer.class, FoodStats.class, Minecraft.class, MovementInputFromOptions.class, NetClientHandler.class, PlayerControllerMP.class, Packet13PlayerLookMove.class, Packet27PlayerInput.class, Packet82AddHunger.class, Packet85SimpleSignal.class, Packet202PlayerAbilities.class, PlayerCapabilities.class, TcpConnection.class };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NetClientHandler(Minecraft par1Minecraft, String par2Str, int par3) throws IOException {
/*   99 */     this.mc = par1Minecraft;
/*  100 */     Socket var4 = new Socket(InetAddress.getByName(par2Str), par3);
/*  101 */     this.netManager = new TcpConnection(par1Minecraft.getLogAgent(), var4, "Client", this);
/*      */   }
/*      */ 
/*      */   
/*      */   public NetClientHandler(Minecraft par1Minecraft, String par2Str, int par3, GuiScreen par4GuiScreen) throws IOException {
/*  106 */     this.mc = par1Minecraft;
/*  107 */     this.field_98183_l = par4GuiScreen;
/*  108 */     Socket var5 = new Socket(InetAddress.getByName(par2Str), par3);
/*  109 */     this.netManager = new TcpConnection(par1Minecraft.getLogAgent(), var5, "Client", this);
/*      */   }
/*      */ 
/*      */   
/*      */   public NetClientHandler(Minecraft par1Minecraft, IntegratedServer par2IntegratedServer) throws IOException {
/*  114 */     this.mc = par1Minecraft;
/*  115 */     this.netManager = new MemoryConnection(par1Minecraft.getLogAgent(), this);
/*  116 */     par2IntegratedServer.getServerListeningThread().func_71754_a((MemoryConnection)this.netManager, par1Minecraft.getSession().getUsername());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cleanup() {
/*  124 */     if (this.netManager != null)
/*      */     {
/*  126 */       this.netManager.wakeThreads();
/*      */     }
/*      */     
/*  129 */     this.netManager = null;
/*  130 */     this.worldClient = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processReadPackets() {
/*  138 */     if (!this.disconnected && this.netManager != null)
/*      */     {
/*  140 */       this.netManager.processReadPackets();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleServerAuthData(Packet253ServerAuthData par1Packet253ServerAuthData) {
/*  151 */     Minecraft.soonest_reconnection_time = 0L;
/*  152 */     String var2 = par1Packet253ServerAuthData.getServerId().trim();
/*  153 */     PublicKey var3 = par1Packet253ServerAuthData.getPublicKey();
/*  154 */     SecretKey var4 = CryptManager.createNewSharedKey();
/*      */     
/*  156 */     if (!"-".equals(var2)) {
/*      */       
/*  158 */       String var5 = (new BigInteger(CryptManager.getServerIdHash(var2, var3, var4))).toString(16);
/*  159 */       String var6 = sendSessionRequest(this.mc.getSession().getUsername(), this.mc.getSession().getSessionID(), var5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  174 */       if (!"ok".equalsIgnoreCase(var6)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  189 */         this.netManager.networkShutdown("disconnect.loginFailedInfo", new Object[] { var6 });
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  194 */     addToSendQueue(new Packet252SharedKey(var4, var3, par1Packet253ServerAuthData.getVerifyToken()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String sendSessionRequest(String par1Str, String par2Str, String par3Str) {
/*      */     try {
/*  204 */       URL var4 = new URL("http://session.minecraft.net/game/joinserver.jsp?user=" + urlEncode(par1Str) + "&sessionId=" + urlEncode(par2Str) + "&serverId=" + urlEncode(par3Str));
/*  205 */       InputStream var5 = var4.openConnection(this.mc.getProxy()).getInputStream();
/*  206 */       BufferedReader var6 = new BufferedReader(new InputStreamReader(var5));
/*  207 */       String var7 = var6.readLine();
/*  208 */       var6.close();
/*  209 */       return var7;
/*      */     }
/*  211 */     catch (IOException var8) {
/*      */       
/*  213 */       return var8.toString();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String urlEncode(String par0Str) throws IOException {
/*  222 */     return URLEncoder.encode(par0Str, "UTF-8");
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSharedKey(Packet252SharedKey par1Packet252SharedKey) {
/*  227 */     addToSendQueue(new Packet205ClientCommand(0));
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleLogin(Packet1Login par1Packet1Login) {
/*  232 */     this.mc.playerController = new PlayerControllerMP(this.mc, this);
/*      */ 
/*      */     
/*  235 */     this.worldClient = new WorldClient(this, new WorldSettings(0L, par1Packet1Login.gameType, false, par1Packet1Login.hardcoreMode, par1Packet1Login.terrainType, par1Packet1Login.are_skills_enabled), par1Packet1Login.dimension, par1Packet1Login.difficultySetting, this.mc.mcProfiler, this.mc.getLogAgent(), par1Packet1Login.world_creation_time, par1Packet1Login.total_world_time);
/*      */     
/*  237 */     this.mc.loadWorld(this.worldClient);
/*  238 */     this.mc.thePlayer.dimension = par1Packet1Login.dimension;
/*  239 */     this.mc.displayGuiScreen(new GuiDownloadTerrain(this));
/*  240 */     this.mc.thePlayer.entityId = par1Packet1Login.clientEntityId;
/*  241 */     this.currentServerMaxPlayers = par1Packet1Login.maxPlayers;
/*  242 */     this.mc.playerController.setGameType(par1Packet1Login.gameType);
/*  243 */     this.mc.gameSettings.sendSettingsToServer();
/*  244 */     this.mc.theWorld.worldInfo.setVillageConditions(par1Packet1Login.village_conditions);
/*      */     
/*  246 */     this.mc.theWorld.worldInfo.setAchievements(par1Packet1Login.achievements);
/*      */     
/*  248 */     this.mc.theWorld.worldInfo.setEarliestMITEReleaseRunIn(par1Packet1Login.earliest_MITE_release_run_in);
/*  249 */     this.mc.theWorld.worldInfo.setLatestMITEReleaseRunIn(par1Packet1Login.latest_MITE_release_run_in);
/*  250 */     this.netManager.addToSendQueue(new Packet250CustomPayload("MC|Brand", ClientBrandRetriever.getClientModName().getBytes(Charsets.UTF_8)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleVehicleSpawn(Packet23VehicleSpawn par1Packet23VehicleSpawn) {
/*      */     double var2, var4, var6;
/*  266 */     if (par1Packet23VehicleSpawn.position_set_using_unscaled_integers) {
/*      */ 
/*      */       
/*  269 */       var2 = par1Packet23VehicleSpawn.unscaled_pos_x;
/*  270 */       var4 = par1Packet23VehicleSpawn.unscaled_pos_y;
/*  271 */       var6 = par1Packet23VehicleSpawn.unscaled_pos_z;
/*      */     }
/*      */     else {
/*      */       
/*  275 */       var2 = SpatialScaler.getPosX(par1Packet23VehicleSpawn.scaled_pos_x);
/*  276 */       var4 = SpatialScaler.getPosY(par1Packet23VehicleSpawn.scaled_pos_y);
/*  277 */       var6 = SpatialScaler.getPosZ(par1Packet23VehicleSpawn.scaled_pos_z);
/*      */     } 
/*      */     
/*  280 */     Entity var8 = null;
/*      */     
/*  282 */     if (par1Packet23VehicleSpawn.type == 10) {
/*      */       
/*  284 */       var8 = EntityMinecart.createMinecart(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
/*      */     }
/*  286 */     else if (par1Packet23VehicleSpawn.type == 90) {
/*      */       
/*  288 */       Entity var9 = getEntityByID(par1Packet23VehicleSpawn.throwerEntityId);
/*      */       
/*  290 */       if (var9 instanceof EntityPlayer)
/*      */       {
/*  292 */         var8 = new EntityFishHook(this.worldClient, var2, var4, var6, (EntityPlayer)var9);
/*      */       }
/*      */       
/*  295 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  297 */     else if (par1Packet23VehicleSpawn.type == 60) {
/*      */ 
/*      */ 
/*      */       
/*  301 */       if (par1Packet23VehicleSpawn.arrow_item_id == -1)
/*      */       {
/*  303 */         var8 = null;
/*      */       }
/*      */       else
/*      */       {
/*  307 */         var2 = par1Packet23VehicleSpawn.exact_pos_x;
/*  308 */         var4 = par1Packet23VehicleSpawn.exact_pos_y;
/*  309 */         var6 = par1Packet23VehicleSpawn.exact_pos_z;
/*      */         
/*  311 */         var8 = new EntityArrow(this.worldClient, var2, var4, var6, (ItemArrow)Item.itemsList[par1Packet23VehicleSpawn.arrow_item_id], par1Packet23VehicleSpawn.launcher_was_enchanted);
/*      */         
/*  313 */         if (par1Packet23VehicleSpawn.arrow_stuck_in_block) {
/*  314 */           ((EntityArrow)var8).setInGround();
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  320 */         EntityArrow arrow = (EntityArrow)var8;
/*      */         
/*  322 */         arrow.xTile = par1Packet23VehicleSpawn.arrow_tile_x;
/*  323 */         arrow.yTile = par1Packet23VehicleSpawn.arrow_tile_y;
/*  324 */         arrow.zTile = par1Packet23VehicleSpawn.arrow_tile_z;
/*      */         
/*  326 */         arrow.setInTile(par1Packet23VehicleSpawn.arrow_in_tile);
/*  327 */         arrow.setInData(par1Packet23VehicleSpawn.arrow_in_data);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  336 */     else if (par1Packet23VehicleSpawn.type == 61) {
/*      */       
/*  338 */       var8 = new EntitySnowball(this.worldClient, var2, var4, var6);
/*      */     }
/*  340 */     else if (par1Packet23VehicleSpawn.type == 71) {
/*      */ 
/*      */       
/*  343 */       var8 = new EntityItemFrame(this.worldClient, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z, par1Packet23VehicleSpawn.throwerEntityId);
/*  344 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  346 */     else if (par1Packet23VehicleSpawn.type == 77) {
/*      */ 
/*      */       
/*  349 */       var8 = new EntityLeashKnot(this.worldClient, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z);
/*  350 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  352 */     else if (par1Packet23VehicleSpawn.type == 65) {
/*      */       
/*  354 */       var8 = new EntityEnderPearl(this.worldClient, var2, var4, var6);
/*      */     }
/*  356 */     else if (par1Packet23VehicleSpawn.type == 72) {
/*      */       
/*  358 */       var8 = new EntityEnderEye(this.worldClient, var2, var4, var6);
/*      */     }
/*  360 */     else if (par1Packet23VehicleSpawn.type == 76) {
/*      */       
/*  362 */       var8 = new EntityFireworkRocket(this.worldClient, var2, var4, var6, (ItemStack)null);
/*      */     }
/*  364 */     else if (par1Packet23VehicleSpawn.type == 63) {
/*      */ 
/*      */       
/*  367 */       var8 = new EntityLargeFireball(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
/*  368 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  370 */     else if (par1Packet23VehicleSpawn.type == 64) {
/*      */ 
/*      */       
/*  373 */       var8 = new EntitySmallFireball(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
/*  374 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  376 */     else if (par1Packet23VehicleSpawn.type == 66) {
/*      */ 
/*      */       
/*  379 */       var8 = new EntityWitherSkull(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
/*  380 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  382 */     else if (par1Packet23VehicleSpawn.type == 62) {
/*      */       
/*  384 */       var8 = new EntityEgg(this.worldClient, var2, var4, var6);
/*      */     }
/*  386 */     else if (par1Packet23VehicleSpawn.type == 500) {
/*      */       
/*  388 */       var8 = new EntityBrick(this.worldClient, var2, var4, var6, Item.brick);
/*      */     }
/*  390 */     else if (par1Packet23VehicleSpawn.type == 501) {
/*      */       
/*  392 */       var8 = new EntityBrick(this.worldClient, var2, var4, var6, Item.netherrackBrick);
/*      */     }
/*  394 */     else if (MathHelper.isInRange(par1Packet23VehicleSpawn.type, 600, 699)) {
/*      */       
/*  396 */       var8 = new EntityGelatinousSphere(this.worldClient, var2, var4, var6, Item.slimeBall, par1Packet23VehicleSpawn.type - 600);
/*      */     }
/*  398 */     else if (par1Packet23VehicleSpawn.type == 700) {
/*      */       
/*  400 */       var8 = new EntityWeb(this.worldClient, var2, var4, var6);
/*      */     }
/*  402 */     else if (par1Packet23VehicleSpawn.type == 73) {
/*      */       
/*  404 */       var8 = new EntityPotion(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
/*  405 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  407 */     else if (par1Packet23VehicleSpawn.type == 75) {
/*      */       
/*  409 */       var8 = new EntityExpBottle(this.worldClient, var2, var4, var6);
/*  410 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     }
/*  412 */     else if (par1Packet23VehicleSpawn.type == 1) {
/*      */       
/*  414 */       var8 = new EntityBoat(this.worldClient, var2, var4, var6);
/*      */     }
/*  416 */     else if (par1Packet23VehicleSpawn.type == 50) {
/*      */       
/*  418 */       var8 = new EntityTNTPrimed(this.worldClient, var2, var4, var6, (EntityLivingBase)null);
/*      */     }
/*  420 */     else if (par1Packet23VehicleSpawn.type == 51) {
/*      */       
/*  422 */       var8 = new EntityEnderCrystal(this.worldClient, var2, var4, var6);
/*      */     }
/*  424 */     else if (par1Packet23VehicleSpawn.type == 2) {
/*      */       
/*  426 */       var8 = new EntityItem(this.worldClient, var2, var4, var6);
/*      */     }
/*  428 */     else if (par1Packet23VehicleSpawn.type == 70) {
/*      */ 
/*      */       
/*  431 */       var8 = new EntityFallingSand(this.worldClient, MathHelper.floor_double(var2) + 0.5D, MathHelper.floor_double(var4) + 0.5D, MathHelper.floor_double(var6) + 0.5D, par1Packet23VehicleSpawn.throwerEntityId & 0xFFFF, par1Packet23VehicleSpawn.throwerEntityId >> 16);
/*  432 */       par1Packet23VehicleSpawn.throwerEntityId = 0;
/*      */     } 
/*      */     
/*  435 */     if (var8 != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  442 */       var8.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
/*  443 */       var8.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);
/*      */       
/*  445 */       if (var8 instanceof EntityBoat) {
/*      */         
/*  447 */         ((EntityBoat)var8).setPositionAndRotation2(var8.posX, var8.posY, var8.posZ, var8.rotationYaw, var8.rotationPitch, 3);
/*  448 */         var8.prevRotationYaw = var8.rotationYaw;
/*      */       } 
/*      */       
/*  451 */       Entity[] var12 = var8.getParts();
/*      */       
/*  453 */       if (var12 != null) {
/*      */         
/*  455 */         int var10 = par1Packet23VehicleSpawn.entityId - var8.entityId;
/*      */         
/*  457 */         for (int var11 = 0; var11 < var12.length; var11++)
/*      */         {
/*  459 */           (var12[var11]).entityId += var10;
/*      */         }
/*      */       } 
/*      */       
/*  463 */       var8.entityId = par1Packet23VehicleSpawn.entityId;
/*  464 */       this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, var8);
/*      */       
/*  466 */       if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
/*      */         
/*  468 */         if (par1Packet23VehicleSpawn.type == 60) {
/*      */           
/*  470 */           Entity var13 = getEntityByID(par1Packet23VehicleSpawn.throwerEntityId);
/*      */           
/*  472 */           if (var13 instanceof EntityLivingBase) {
/*      */             
/*  474 */             EntityArrow var14 = (EntityArrow)var8;
/*  475 */             var14.shootingEntity = var13;
/*      */           } 
/*      */           
/*  478 */           var8.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  483 */         var8.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityExpOrb(Packet26EntityExpOrb par1Packet26EntityExpOrb) {
/*  494 */     EntityXPOrb var2 = new EntityXPOrb(this.worldClient, SpatialScaler.getPosX(par1Packet26EntityExpOrb.scaled_pos_x), SpatialScaler.getPosY(par1Packet26EntityExpOrb.scaled_pos_y), SpatialScaler.getPosZ(par1Packet26EntityExpOrb.scaled_pos_z), par1Packet26EntityExpOrb.xp_value);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  500 */     var2.entityId = par1Packet26EntityExpOrb.entity_id;
/*      */     
/*  502 */     var2.setPlayerThisBelongsTo(par1Packet26EntityExpOrb.player_this_belongs_to);
/*      */ 
/*      */     
/*  505 */     var2.setVelocity(SpatialScaler.getMotion(par1Packet26EntityExpOrb.scaled_motion_x), SpatialScaler.getMotion(par1Packet26EntityExpOrb.scaled_motion_y), SpatialScaler.getMotion(par1Packet26EntityExpOrb.scaled_motion_z));
/*  506 */     this.worldClient.addEntityToWorld(par1Packet26EntityExpOrb.entity_id, var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleWeather(Packet71Weather par1Packet71Weather) {
/*  518 */     EntityLightningBolt var8 = null;
/*      */     
/*  520 */     if (par1Packet71Weather.isLightningBolt == 1)
/*      */     {
/*      */       
/*  523 */       var8 = new EntityLightningBolt(this.worldClient, SpatialScaler.getPosX(par1Packet71Weather.scaled_pos_x), SpatialScaler.getPosY(par1Packet71Weather.scaled_pos_y), SpatialScaler.getPosZ(par1Packet71Weather.scaled_pos_z));
/*      */     }
/*      */     
/*  526 */     if (var8 != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  533 */       var8.entityId = par1Packet71Weather.entityID;
/*  534 */       this.worldClient.addWeatherEffect(var8);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityPainting(Packet25EntityPainting par1Packet25EntityPainting) {
/*  543 */     EntityPainting var2 = new EntityPainting(this.worldClient, par1Packet25EntityPainting.xPosition, par1Packet25EntityPainting.yPosition, par1Packet25EntityPainting.zPosition, par1Packet25EntityPainting.direction, par1Packet25EntityPainting.title);
/*  544 */     this.worldClient.addEntityToWorld(par1Packet25EntityPainting.entityId, var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityVelocity(Packet28EntityVelocity par1Packet28EntityVelocity) {
/*  552 */     Entity var2 = getEntityByID(par1Packet28EntityVelocity.entityId);
/*      */     
/*  554 */     if (var2 != null)
/*      */     {
/*      */       
/*  557 */       par1Packet28EntityVelocity.applyToEntity(var2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityMetadata(Packet40EntityMetadata par1Packet40EntityMetadata) {
/*  566 */     Entity var2 = getEntityByID(par1Packet40EntityMetadata.entityId);
/*      */     
/*  568 */     if (var2 != null && par1Packet40EntityMetadata.getMetadata() != null)
/*      */     {
/*  570 */       var2.getDataWatcher().updateWatchedObjectsFromList(par1Packet40EntityMetadata.getMetadata());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleNamedEntitySpawn(Packet20NamedEntitySpawn par1Packet20NamedEntitySpawn) {
/*  581 */     double var2 = SpatialScaler.getPosX(par1Packet20NamedEntitySpawn.scaled_pos_x);
/*  582 */     double var4 = SpatialScaler.getPosY(par1Packet20NamedEntitySpawn.scaled_pos_y);
/*  583 */     double var6 = SpatialScaler.getPosZ(par1Packet20NamedEntitySpawn.scaled_pos_z);
/*  584 */     float var8 = SpatialScaler.getRotation(par1Packet20NamedEntitySpawn.scaled_yaw);
/*  585 */     float var9 = SpatialScaler.getRotation(par1Packet20NamedEntitySpawn.scaled_pitch);
/*  586 */     EntityOtherPlayerMP var10 = new EntityOtherPlayerMP(this.mc.theWorld, par1Packet20NamedEntitySpawn.name);
/*      */ 
/*      */ 
/*      */     
/*  590 */     var10.prevPosX = var10.lastTickPosX = var10.posX = var2;
/*  591 */     var10.prevPosY = var10.lastTickPosY = var10.posY = var4;
/*  592 */     var10.prevPosZ = var10.lastTickPosZ = var10.posZ = var6;
/*  593 */     int var11 = par1Packet20NamedEntitySpawn.currentItem;
/*      */     
/*  595 */     if (var11 == 0) {
/*      */       
/*  597 */       var10.inventory.mainInventory[var10.inventory.currentItem] = null;
/*      */     }
/*      */     else {
/*      */       
/*  601 */       var10.inventory.mainInventory[var10.inventory.currentItem] = new ItemStack(var11, 1, 0);
/*      */     } 
/*      */     
/*  604 */     var10.setPositionAndRotation(var2, var4, var6, var8, var9);
/*  605 */     this.worldClient.addEntityToWorld(par1Packet20NamedEntitySpawn.entityId, var10);
/*  606 */     List var12 = par1Packet20NamedEntitySpawn.getWatchedMetadata();
/*      */     
/*  608 */     if (var12 != null)
/*      */     {
/*  610 */       var10.getDataWatcher().updateWatchedObjectsFromList(var12);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEntityTeleport(Packet34EntityTeleport par1Packet34EntityTeleport) {
/*  616 */     Entity var2 = getEntityByID(par1Packet34EntityTeleport.entity_id);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  656 */     if (var2 != null) {
/*  657 */       par1Packet34EntityTeleport.applyToEntity(var2);
/*      */     }
/*      */   }
/*      */   
/*      */   public void handleBlockItemSwitch(Packet16BlockItemSwitch par1Packet16BlockItemSwitch) {
/*  662 */     if (par1Packet16BlockItemSwitch.id >= 0 && par1Packet16BlockItemSwitch.id < InventoryPlayer.getHotbarSize())
/*      */     {
/*  664 */       this.mc.thePlayer.inventory.currentItem = par1Packet16BlockItemSwitch.id;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityLook(Packet32EntityLook packet) {
/*  705 */     Entity entity = getEntityByID(packet.entity_id);
/*      */     
/*  707 */     if (entity == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  712 */     packet.applyToEntity(entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEntityHeadRotation(Packet35EntityHeadRotation par1Packet35EntityHeadRotation) {
/*  717 */     Entity var2 = getEntityByID(par1Packet35EntityHeadRotation.entityId);
/*      */     
/*  719 */     if (var2 != null) {
/*      */       
/*  721 */       float var3 = (par1Packet35EntityHeadRotation.headRotationYaw * 360) / 256.0F;
/*  722 */       var2.setRotationYawHead(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleDestroyEntity(Packet29DestroyEntity par1Packet29DestroyEntity) {
/*  728 */     for (int var2 = 0; var2 < par1Packet29DestroyEntity.entityId.length; var2++)
/*      */     {
/*  730 */       this.worldClient.removeEntityFromWorld(par1Packet29DestroyEntity.entityId[var2]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleFlying(Packet10Flying par1Packet10Flying) {
/*  737 */     EntityClientPlayerMP var2 = this.mc.thePlayer;
/*  738 */     double var3 = var2.posX;
/*  739 */     double var5 = var2.posY;
/*  740 */     double var7 = var2.posZ;
/*  741 */     float var9 = var2.rotationYaw;
/*  742 */     float var10 = var2.rotationPitch;
/*      */     
/*  744 */     if (par1Packet10Flying.moving) {
/*      */       
/*  746 */       var3 = par1Packet10Flying.xPosition;
/*  747 */       var5 = par1Packet10Flying.yPosition;
/*  748 */       var7 = par1Packet10Flying.zPosition;
/*      */     } 
/*      */     
/*  751 */     if (par1Packet10Flying.rotating) {
/*      */       
/*  753 */       var9 = par1Packet10Flying.yaw;
/*  754 */       var10 = par1Packet10Flying.pitch;
/*      */     } 
/*      */     
/*  757 */     var2.ySize = 0.0F;
/*  758 */     var2.motionX = var2.motionY = var2.motionZ = 0.0D;
/*  759 */     var2.setPositionAndRotation(var3, var5, var7, var9, var10);
/*  760 */     par1Packet10Flying.xPosition = var2.posX;
/*  761 */     par1Packet10Flying.yPosition = var2.boundingBox.minY;
/*  762 */     par1Packet10Flying.zPosition = var2.posZ;
/*  763 */     par1Packet10Flying.stance = var2.posY;
/*  764 */     this.netManager.addToSendQueue(par1Packet10Flying);
/*      */     
/*  766 */     if (!this.doneLoadingTerrain) {
/*      */       
/*  768 */       this.mc.thePlayer.prevPosX = this.mc.thePlayer.posX;
/*  769 */       this.mc.thePlayer.prevPosY = this.mc.thePlayer.posY;
/*  770 */       this.mc.thePlayer.prevPosZ = this.mc.thePlayer.posZ;
/*  771 */       this.doneLoadingTerrain = true;
/*  772 */       this.mc.displayGuiScreen((GuiScreen)null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMultiBlockChange(Packet52MultiBlockChange par1Packet52MultiBlockChange) {
/*  821 */     Debug.println("Handling packet52?");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  838 */     int var2 = par1Packet52MultiBlockChange.xPosition * 16;
/*  839 */     int var3 = par1Packet52MultiBlockChange.zPosition * 16;
/*      */     
/*  841 */     if (par1Packet52MultiBlockChange.metadataArray != null) {
/*      */       
/*  843 */       DataInputStream var4 = new DataInputStream(new ByteArrayInputStream(par1Packet52MultiBlockChange.metadataArray));
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  848 */         long before = System.nanoTime();
/*      */         
/*  850 */         for (int var5 = 0; var5 < par1Packet52MultiBlockChange.size; var5++) {
/*      */           
/*  852 */           short var6 = var4.readShort();
/*  853 */           short var7 = var4.readShort();
/*  854 */           int var8 = var7 >> 4 & 0xFFF;
/*  855 */           int var9 = var7 & 0xF;
/*  856 */           int var10 = var6 >> 12 & 0xF;
/*  857 */           int var11 = var6 >> 8 & 0xF;
/*  858 */           int var12 = var6 & 0xFF;
/*      */           
/*  860 */           this.worldClient.setBlockAndMetadataAndInvalidate(var10 + var2, var12, var11 + var3, var8, var9);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  900 */         int delay = (int)(System.nanoTime() - before) / 10000000;
/*      */         
/*  902 */         if (delay > 0)
/*      */         {
/*  904 */           Minecraft.MITE_log.logInfo("Long time processing handleMultiBlockChange (delay=" + delay + ") #Blocks=" + par1Packet52MultiBlockChange.size);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  925 */       catch (IOException var13) {
/*      */         
/*  927 */         System.out.println("Exception occured, packet52");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMultiBlockChange(Packet97MultiBlockChange packet) {
/*  935 */     byte[] bytes = packet.getBytes();
/*      */     
/*  937 */     int base_x = packet.chunk_x * 16;
/*  938 */     int base_z = packet.chunk_z * 16;
/*      */     
/*  940 */     long before = System.nanoTime();
/*      */     
/*  942 */     for (int var5 = 0; var5 < packet.num_blocks; var5++) {
/*      */ 
/*      */ 
/*      */       
/*  946 */       int offset = var5 * 5;
/*      */       
/*  948 */       int x = base_x + bytes[offset];
/*  949 */       int y = bytes[offset + 1] & 0xFF;
/*  950 */       int z = base_z + bytes[offset + 2];
/*      */       
/*  952 */       int block_id = bytes[offset + 3] & 0xFF;
/*  953 */       int metadata = bytes[offset + 4];
/*      */       
/*  955 */       this.worldClient.setBlockAndMetadataAndInvalidate(x, y, z, block_id, metadata);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  962 */       if (this.worldClient.hasSkylight()) {
/*  963 */         this.worldClient.getChunkFromBlockCoords(x, z).addPendingSkylightUpdate(x, y, z);
/*      */       }
/*      */     } 
/*  966 */     int delay = (int)(System.nanoTime() - before) / 10000000;
/*      */     
/*  968 */     if (delay > 0)
/*      */     {
/*  970 */       Minecraft.MITE_log.logInfo("Long time processing handleMultiBlockChange97 (delay=" + delay + ") #Blocks=" + packet.num_blocks);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMapChunk(Packet51MapChunk par1Packet51MapChunk) {
/*  979 */     if (par1Packet51MapChunk.includeInitialize) {
/*      */       
/*  981 */       if (par1Packet51MapChunk.yChMin == 0) {
/*      */         
/*  983 */         this.worldClient.doPreChunk(par1Packet51MapChunk.xCh, par1Packet51MapChunk.zCh, false);
/*      */         
/*      */         return;
/*      */       } 
/*  987 */       this.worldClient.doPreChunk(par1Packet51MapChunk.xCh, par1Packet51MapChunk.zCh, true);
/*      */     } 
/*      */     
/*  990 */     this.worldClient.invalidateBlockReceiveRegion(par1Packet51MapChunk.xCh << 4, 0, par1Packet51MapChunk.zCh << 4, (par1Packet51MapChunk.xCh << 4) + 15, 256, (par1Packet51MapChunk.zCh << 4) + 15);
/*  991 */     Chunk var2 = this.worldClient.getChunkFromChunkCoords(par1Packet51MapChunk.xCh, par1Packet51MapChunk.zCh);
/*      */     
/*  993 */     if (par1Packet51MapChunk.includeInitialize && var2 == null) {
/*      */       
/*  995 */       this.worldClient.doPreChunk(par1Packet51MapChunk.xCh, par1Packet51MapChunk.zCh, true);
/*  996 */       var2 = this.worldClient.getChunkFromChunkCoords(par1Packet51MapChunk.xCh, par1Packet51MapChunk.zCh);
/*      */     } 
/*      */     
/*  999 */     if (var2 != null) {
/*      */       
/* 1001 */       var2.fillChunk(par1Packet51MapChunk.getUncompressedChunkData(), par1Packet51MapChunk.yChMin, par1Packet51MapChunk.yChMax, par1Packet51MapChunk.includeInitialize);
/* 1002 */       this.worldClient.markBlockRangeForRenderUpdate(par1Packet51MapChunk.xCh << 4, 0, par1Packet51MapChunk.zCh << 4, (par1Packet51MapChunk.xCh << 4) + 15, 256, (par1Packet51MapChunk.zCh << 4) + 15);
/*      */       
/* 1004 */       if (!par1Packet51MapChunk.includeInitialize || !(this.worldClient.provider instanceof WorldProviderSurface))
/*      */       {
/* 1006 */         var2.resetRelightChecks();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1011 */       Minecraft.setErrorMessage("Wasn't able to mark chunk at " + (par1Packet51MapChunk.xCh * 16) + ", " + par1Packet51MapChunk.zCh + " for render update");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleBlockChange(Packet53BlockChange par1Packet53BlockChange) {
/* 1017 */     this.worldClient.setBlockAndMetadataAndInvalidate(par1Packet53BlockChange.xPosition, par1Packet53BlockChange.yPosition, par1Packet53BlockChange.zPosition, par1Packet53BlockChange.type, par1Packet53BlockChange.metadata);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleKickDisconnect(Packet255KickDisconnect par1Packet255KickDisconnect) {
/* 1022 */     this.netManager.networkShutdown("disconnect.kicked", new Object[0]);
/* 1023 */     this.disconnected = true;
/* 1024 */     this.mc.loadWorld((WorldClient)null);
/*      */     
/* 1026 */     if (this.field_98183_l != null) {
/*      */       
/* 1028 */       this.mc.displayGuiScreen(new GuiScreenDisconnectedOnline(this.field_98183_l, "disconnect.disconnected", "disconnect.genericReason", new Object[] { par1Packet255KickDisconnect.reason }));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1033 */       this.mc.displayGuiScreen(new GuiDisconnected(par1Packet255KickDisconnect.playerWasHosting() ? new GuiMainMenu() : new GuiMultiplayer(new GuiMainMenu()), "disconnect.disconnected", "disconnect.genericReason", new Object[] { (par1Packet255KickDisconnect.reason == null) ? null : par1Packet255KickDisconnect.reason.trim() }));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleErrorMessage(String par1Str, Object[] par2ArrayOfObj) {
/* 1039 */     if (!this.disconnected) {
/*      */       
/* 1041 */       this.disconnected = true;
/* 1042 */       this.mc.loadWorld((WorldClient)null);
/*      */       
/* 1044 */       if (this.field_98183_l != null) {
/*      */         
/* 1046 */         this.mc.displayGuiScreen(new GuiScreenDisconnectedOnline(this.field_98183_l, "disconnect.lost", par1Str, par2ArrayOfObj));
/*      */       }
/*      */       else {
/*      */         
/* 1050 */         this.mc.displayGuiScreen(new GuiDisconnected(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", par1Str, par2ArrayOfObj));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void quitWithPacket(Packet par1Packet) {
/* 1057 */     if (!this.disconnected) {
/*      */       
/* 1059 */       this.netManager.addToSendQueue(par1Packet);
/* 1060 */       this.netManager.serverShutdown();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToSendQueue(Packet par1Packet) {
/* 1077 */     if (!this.disconnected)
/*      */     {
/* 1079 */       this.netManager.addToSendQueue(par1Packet);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleCollect(Packet22Collect par1Packet22Collect) {
/* 1085 */     Entity var2 = getEntityByID(par1Packet22Collect.collectedEntityId);
/* 1086 */     Object var3 = getEntityByID(par1Packet22Collect.collectorEntityId);
/*      */     
/* 1088 */     if (var3 == null)
/*      */     {
/* 1090 */       var3 = this.mc.thePlayer;
/*      */     }
/*      */     
/* 1093 */     if (var2 != null) {
/*      */       
/* 1095 */       if (!(var2 instanceof EntityXPOrb))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1101 */         this.worldClient.playSoundAtEntity(var2, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */       }
/*      */       
/* 1104 */       this.mc.effectRenderer.addEffect(new EntityPickupFX(this.mc.theWorld, var2, (Entity)var3, -0.5F));
/* 1105 */       this.worldClient.removeEntityFromWorld(par1Packet22Collect.collectedEntityId);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleChat(Packet3Chat par1Packet3Chat) {
/* 1111 */     this.mc.ingameGUI.getChatGUI().printChatMessage(ChatMessageComponent.createFromJson(par1Packet3Chat.message).toStringWithFormatting(true));
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleAnimation(Packet18Animation par1Packet18Animation) {
/* 1116 */     Entity var2 = getEntityByID(par1Packet18Animation.entityId);
/*      */     
/* 1118 */     if (var2 != null)
/*      */     {
/* 1120 */       if (par1Packet18Animation.animate == 1) {
/*      */         
/* 1122 */         EntityLivingBase var3 = (EntityLivingBase)var2;
/* 1123 */         var3.swingArm();
/*      */       }
/* 1125 */       else if (par1Packet18Animation.animate == 2) {
/*      */         
/* 1127 */         var2.performHurtAnimation();
/*      */       }
/* 1129 */       else if (par1Packet18Animation.animate == 3) {
/*      */         
/* 1131 */         EntityPlayer var4 = (EntityPlayer)var2;
/*      */         
/* 1133 */         var4.wakeUpPlayer(true, getEntityByID(par1Packet18Animation.other_entity_id));
/*      */       }
/* 1135 */       else if (par1Packet18Animation.animate != 4) {
/*      */         
/* 1137 */         if (par1Packet18Animation.animate == 6) {
/*      */           
/* 1139 */           this.mc.effectRenderer.addEffect(new EntityCrit2FX(this.mc.theWorld, var2));
/*      */         }
/* 1141 */         else if (par1Packet18Animation.animate == 7) {
/*      */ 
/*      */           
/* 1144 */           EntityCrit2FX var5 = new EntityCrit2FX(this.mc.theWorld, var2, EnumParticle.magicCrit);
/* 1145 */           this.mc.effectRenderer.addEffect(var5);
/*      */         }
/* 1147 */         else if (par1Packet18Animation.animate != 5 || var2 instanceof EntityOtherPlayerMP) {
/*      */         
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSleep(Packet17Sleep par1Packet17Sleep) {
/* 1157 */     Entity var2 = getEntityByID(par1Packet17Sleep.entityID);
/*      */     
/* 1159 */     if (var2 != null)
/*      */     {
/* 1161 */       if (par1Packet17Sleep.field_73622_e == 0) {
/*      */         
/* 1163 */         EntityPlayer var3 = (EntityPlayer)var2;
/*      */         
/* 1165 */         var3.pos_x_before_bed = par1Packet17Sleep.pos_x_before_bed;
/* 1166 */         var3.pos_y_before_bed = par1Packet17Sleep.pos_y_before_bed;
/* 1167 */         var3.pos_z_before_bed = par1Packet17Sleep.pos_z_before_bed;
/*      */ 
/*      */         
/* 1170 */         var3.getIntoBed(par1Packet17Sleep.bedX, par1Packet17Sleep.bedY, par1Packet17Sleep.bedZ, par1Packet17Sleep.direction);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void disconnect() {
/* 1180 */     this.disconnected = true;
/* 1181 */     this.netManager.wakeThreads();
/* 1182 */     this.netManager.networkShutdown("disconnect.closed", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMobSpawn(Packet24MobSpawn par1Packet24MobSpawn) {
/* 1192 */     double var2 = SpatialScaler.getPosX(par1Packet24MobSpawn.scaled_pos_x);
/* 1193 */     double var4 = SpatialScaler.getPosY(par1Packet24MobSpawn.scaled_pos_y);
/* 1194 */     double var6 = SpatialScaler.getPosZ(par1Packet24MobSpawn.scaled_pos_z);
/* 1195 */     float var8 = SpatialScaler.getRotation(par1Packet24MobSpawn.scaled_yaw);
/* 1196 */     float var9 = SpatialScaler.getRotation(par1Packet24MobSpawn.scaled_pitch);
/*      */     
/* 1198 */     EntityLiving var10 = (EntityLiving)EntityList.createEntityByID(par1Packet24MobSpawn.type, this.mc.theWorld);
/*      */ 
/*      */ 
/*      */     
/* 1202 */     var10.rotationYawHead = (par1Packet24MobSpawn.scaled_head_yaw * 360) / 256.0F;
/* 1203 */     Entity[] var11 = var10.getParts();
/*      */     
/* 1205 */     if (var11 != null) {
/*      */       
/* 1207 */       int var12 = par1Packet24MobSpawn.entity_id - var10.entityId;
/*      */       
/* 1209 */       for (int var13 = 0; var13 < var11.length; var13++)
/*      */       {
/* 1211 */         (var11[var13]).entityId += var12;
/*      */       }
/*      */     } 
/*      */     
/* 1215 */     var10.entityId = par1Packet24MobSpawn.entity_id;
/*      */     
/* 1217 */     var10.getDataWatcher().updateWatchedObjectsFromList(par1Packet24MobSpawn.getMetadata());
/* 1218 */     var10.onSendToClient(par1Packet24MobSpawn);
/*      */     
/* 1220 */     var10.setPositionAndRotation(var2, var4, var6, var8, var9);
/*      */     
/* 1222 */     var10.prevRenderYawOffset = var10.renderYawOffset = var8;
/* 1223 */     var10.prevRotationYawHead = var10.rotationYawHead;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1228 */     var10.motionX = SpatialScaler.getMotion(par1Packet24MobSpawn.scaled_motion_x);
/* 1229 */     var10.motionY = SpatialScaler.getMotion(par1Packet24MobSpawn.scaled_motion_y);
/* 1230 */     var10.motionZ = SpatialScaler.getMotion(par1Packet24MobSpawn.scaled_motion_z);
/*      */     
/* 1232 */     if (par1Packet24MobSpawn.is_decoy && var10 instanceof EntityLiving) {
/* 1233 */       var10.setAsDecoy();
/*      */     }
/*      */ 
/*      */     
/* 1237 */     this.worldClient.addEntityToWorld(par1Packet24MobSpawn.entity_id, var10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateTime(Packet4UpdateTime par1Packet4UpdateTime) {
/* 1252 */     this.mc.theWorld.worldInfo.setTotalWorldTimes(par1Packet4UpdateTime.world_age, this.mc.theWorld);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleUpdateTimeSmall(Packet92UpdateTimeSmall packet) {
/* 1257 */     this.mc.theWorld.worldInfo.setTotalWorldTimes(packet.world_age, this.mc.theWorld);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleWorldAchievement(Packet93WorldAchievement packet) {
/* 1263 */     this.mc.theWorld.worldInfo.unlockAchievement(packet.achievement, packet.username, packet.day, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleCreateFile(Packet94CreateFile packet) {
/* 1268 */     boolean result = packet.writeFile();
/*      */     
/* 1270 */     if (packet.getContext() == 1)
/*      */     {
/* 1272 */       if (result) {
/* 1273 */         this.mc.thePlayer.receiveChatMessage("Stats sent to \"" + packet.getFilepath() + "\"", EnumChatFormatting.YELLOW);
/*      */       } else {
/* 1275 */         this.mc.thePlayer.receiveChatMessage("Failed to send stats to \"" + packet.getFilepath() + "\"", EnumChatFormatting.RED);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnPosition(Packet6SpawnPosition par1Packet6SpawnPosition) {
/* 1286 */     this.mc.thePlayer.setSpawnChunk(new ChunkCoordinates(par1Packet6SpawnPosition.xPosition, par1Packet6SpawnPosition.yPosition, par1Packet6SpawnPosition.zPosition), true);
/* 1287 */     this.mc.theWorld.getWorldInfo().setSpawnPosition(par1Packet6SpawnPosition.xPosition, par1Packet6SpawnPosition.yPosition, par1Packet6SpawnPosition.zPosition);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleAttachEntity(Packet39AttachEntity par1Packet39AttachEntity) {
/* 1295 */     Object var2 = getEntityByID(par1Packet39AttachEntity.ridingEntityId);
/* 1296 */     Entity var3 = getEntityByID(par1Packet39AttachEntity.vehicleEntityId);
/*      */     
/* 1298 */     if (par1Packet39AttachEntity.attachState == 0) {
/*      */       
/* 1300 */       boolean var4 = false;
/*      */       
/* 1302 */       if (par1Packet39AttachEntity.ridingEntityId == this.mc.thePlayer.entityId) {
/*      */         
/* 1304 */         var2 = this.mc.thePlayer;
/*      */         
/* 1306 */         if (var3 instanceof EntityBoat)
/*      */         {
/* 1308 */           ((EntityBoat)var3).func_70270_d(false);
/*      */         }
/*      */         
/* 1311 */         var4 = (((Entity)var2).ridingEntity == null && var3 != null);
/*      */       }
/* 1313 */       else if (var3 instanceof EntityBoat) {
/*      */         
/* 1315 */         ((EntityBoat)var3).func_70270_d(true);
/*      */       } 
/*      */       
/* 1318 */       if (var2 == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1323 */       ((Entity)var2).mountEntity(var3);
/*      */       
/* 1325 */       if (var4)
/*      */       {
/* 1327 */         GameSettings var5 = this.mc.gameSettings;
/* 1328 */         this.mc.ingameGUI.func_110326_a(I18n.getStringParams("mount.onboard", new Object[] { GameSettings.getKeyDisplayString(var5.keyBindSneak.keyCode) }), false);
/*      */       }
/*      */     
/* 1331 */     } else if (par1Packet39AttachEntity.attachState == 1 && var2 != null && var2 instanceof EntityLiving) {
/*      */       
/* 1333 */       if (var3 != null) {
/*      */         
/* 1335 */         ((EntityLiving)var2).setLeashedToEntity(var3, false);
/*      */       }
/*      */       else {
/*      */         
/* 1339 */         ((EntityLiving)var2).clearLeashed(false, false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityStatus(Packet38EntityStatus par1Packet38EntityStatus) {
/* 1349 */     Entity var2 = getEntityByID(par1Packet38EntityStatus.entityId);
/*      */     
/* 1351 */     if (var2 != null) {
/*      */       
/* 1353 */       if (par1Packet38EntityStatus instanceof Packet84EntityStateWithData) {
/*      */         
/* 1355 */         if (par1Packet38EntityStatus.entity_state == EnumEntityState.in_love && var2 instanceof EntityAnimal) {
/*      */           
/* 1357 */           EntityAnimal entity_animal = (EntityAnimal)var2;
/* 1358 */           entity_animal.setInLove(((Packet84EntityStateWithData)par1Packet38EntityStatus).data);
/*      */         } 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 1365 */       var2.handleHealthUpdate(par1Packet38EntityStatus.entity_state);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Entity getEntityByID(int par1) {
/* 1372 */     return (par1 == this.mc.thePlayer.entityId) ? this.mc.thePlayer : this.worldClient.getEntityByID(par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateHealth(Packet8UpdateHealth par1Packet8UpdateHealth) {
/* 1387 */     this.mc.thePlayer.setPlayerSPHealth(par1Packet8UpdateHealth.healthMP);
/* 1388 */     this.mc.thePlayer.getFoodStats().setSatiation(par1Packet8UpdateHealth.satiation, false);
/* 1389 */     this.mc.thePlayer.getFoodStats().setNutrition(par1Packet8UpdateHealth.nutrition, false);
/*      */     
/* 1391 */     if (this.mc.thePlayer.vision_dimming < par1Packet8UpdateHealth.vision_dimming) {
/* 1392 */       this.mc.thePlayer.vision_dimming = par1Packet8UpdateHealth.vision_dimming;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleExperience(Packet43Experience par1Packet43Experience) {
/* 1401 */     this.mc.thePlayer.setXPStats(par1Packet43Experience.experience);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleRespawn(Packet9Respawn par1Packet9Respawn) {
/* 1409 */     if (par1Packet9Respawn.respawnDimension != this.mc.thePlayer.dimension) {
/*      */       
/* 1411 */       this.doneLoadingTerrain = false;
/* 1412 */       Scoreboard var2 = this.worldClient.getScoreboard();
/*      */       
/* 1414 */       this.worldClient = new WorldClient(this, new WorldSettings(0L, par1Packet9Respawn.gameType, false, this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled(), par1Packet9Respawn.terrainType, this.mc.theWorld.areSkillsEnabled()), par1Packet9Respawn.respawnDimension, par1Packet9Respawn.difficulty, this.mc.mcProfiler, this.mc.getLogAgent(), par1Packet9Respawn.world_creation_time, par1Packet9Respawn.total_world_time);
/* 1415 */       this.worldClient.func_96443_a(var2);
/*      */       
/* 1417 */       this.mc.loadWorld(this.worldClient);
/* 1418 */       this.mc.thePlayer.dimension = par1Packet9Respawn.respawnDimension;
/* 1419 */       this.mc.displayGuiScreen(new GuiDownloadTerrain(this));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1424 */     this.mc.setDimensionAndSpawnPlayer(par1Packet9Respawn.respawnDimension);
/* 1425 */     this.mc.playerController.setGameType(par1Packet9Respawn.gameType);
/*      */     
/* 1427 */     this.mc.thePlayer.countdown_to_mark_all_nearby_chunks_for_render_update = 20;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleExplosion(Packet60Explosion par1Packet60Explosion) {
/* 1433 */     Explosion var2 = new Explosion(this.mc.theWorld, (Entity)null, par1Packet60Explosion.explosionX, par1Packet60Explosion.explosionY, par1Packet60Explosion.explosionZ, par1Packet60Explosion.explosion_size_vs_blocks, par1Packet60Explosion.explosion_size_vs_living_entities);
/* 1434 */     var2.affectedBlockPositions = par1Packet60Explosion.chunkPositionRecords;
/* 1435 */     var2.doExplosionB(true);
/* 1436 */     this.mc.thePlayer.motionX += par1Packet60Explosion.getPlayerVelocityX();
/* 1437 */     this.mc.thePlayer.motionY += par1Packet60Explosion.getPlayerVelocityY();
/* 1438 */     this.mc.thePlayer.motionZ += par1Packet60Explosion.getPlayerVelocityZ();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleOpenWindow(Packet100OpenWindow par1Packet100OpenWindow) {
/* 1557 */     par1Packet100OpenWindow.handleOpenWindow(this.mc.thePlayer);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSetSlot(Packet103SetSlot par1Packet103SetSlot) {
/* 1562 */     EntityClientPlayerMP var2 = this.mc.thePlayer;
/*      */     
/* 1564 */     if (par1Packet103SetSlot.windowId == -1) {
/*      */       
/* 1566 */       var2.inventory.setItemStack(par1Packet103SetSlot.myItemStack);
/*      */     }
/*      */     else {
/*      */       
/* 1570 */       boolean var3 = false;
/*      */       
/* 1572 */       if (this.mc.currentScreen instanceof GuiContainerCreative) {
/*      */         
/* 1574 */         GuiContainerCreative var4 = (GuiContainerCreative)this.mc.currentScreen;
/* 1575 */         var3 = (var4.getCurrentTabIndex() != CreativeTabs.tabInventory.getTabIndex());
/*      */       } 
/*      */       
/* 1578 */       if (par1Packet103SetSlot.windowId == 0 && par1Packet103SetSlot.itemSlot >= 36 && par1Packet103SetSlot.itemSlot < 45) {
/*      */         
/* 1580 */         ItemStack var5 = var2.inventoryContainer.getSlot(par1Packet103SetSlot.itemSlot).getStack();
/*      */         
/* 1582 */         if (par1Packet103SetSlot.myItemStack != null && (var5 == null || var5.stackSize < par1Packet103SetSlot.myItemStack.stackSize))
/*      */         {
/* 1584 */           par1Packet103SetSlot.myItemStack.animationsToGo = 5;
/*      */         }
/*      */         
/* 1587 */         var2.inventoryContainer.putStackInSlot(par1Packet103SetSlot.itemSlot, par1Packet103SetSlot.myItemStack);
/*      */       }
/* 1589 */       else if (par1Packet103SetSlot.windowId == var2.openContainer.windowId && (par1Packet103SetSlot.windowId != 0 || !var3)) {
/*      */         
/* 1591 */         var2.openContainer.putStackInSlot(par1Packet103SetSlot.itemSlot, par1Packet103SetSlot.myItemStack);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleTransaction(Packet106Transaction par1Packet106Transaction) {
/* 1598 */     Container var2 = null;
/* 1599 */     EntityClientPlayerMP var3 = this.mc.thePlayer;
/*      */     
/* 1601 */     if (par1Packet106Transaction.windowId == 0) {
/*      */       
/* 1603 */       var2 = var3.inventoryContainer;
/*      */     }
/* 1605 */     else if (par1Packet106Transaction.windowId == var3.openContainer.windowId) {
/*      */       
/* 1607 */       var2 = var3.openContainer;
/*      */     } 
/*      */     
/* 1610 */     if (var2 != null && !par1Packet106Transaction.accepted)
/*      */     {
/* 1612 */       addToSendQueue(new Packet106Transaction(par1Packet106Transaction.windowId, par1Packet106Transaction.shortWindowId, true));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleWindowItems(Packet104WindowItems par1Packet104WindowItems) {
/* 1618 */     EntityClientPlayerMP var2 = this.mc.thePlayer;
/*      */     
/* 1620 */     if (par1Packet104WindowItems.windowId == 0) {
/*      */       
/* 1622 */       var2.inventoryContainer.putStacksInSlots(par1Packet104WindowItems.itemStack);
/*      */     }
/* 1624 */     else if (par1Packet104WindowItems.windowId == var2.openContainer.windowId) {
/*      */       
/* 1626 */       var2.openContainer.putStacksInSlots(par1Packet104WindowItems.itemStack);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_142031_a(Packet133TileEditorOpen par1Packet133TileEditorOpen) {
/* 1632 */     TileEntity var2 = this.worldClient.getBlockTileEntity(par1Packet133TileEditorOpen.field_142035_b, par1Packet133TileEditorOpen.field_142036_c, par1Packet133TileEditorOpen.field_142034_d);
/*      */     
/* 1634 */     if (var2 != null) {
/*      */       
/* 1636 */       this.mc.thePlayer.displayGUIEditSign(var2);
/*      */     }
/* 1638 */     else if (par1Packet133TileEditorOpen.field_142037_a == 0) {
/*      */       
/* 1640 */       TileEntitySign var3 = new TileEntitySign();
/* 1641 */       var3.setWorldObj(this.worldClient);
/* 1642 */       var3.xCoord = par1Packet133TileEditorOpen.field_142035_b;
/* 1643 */       var3.yCoord = par1Packet133TileEditorOpen.field_142036_c;
/* 1644 */       var3.zCoord = par1Packet133TileEditorOpen.field_142034_d;
/* 1645 */       this.mc.thePlayer.displayGUIEditSign(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateSign(Packet130UpdateSign par1Packet130UpdateSign) {
/* 1654 */     boolean var2 = false;
/*      */     
/* 1656 */     if (this.mc.theWorld.blockExists(par1Packet130UpdateSign.xPosition, par1Packet130UpdateSign.yPosition, par1Packet130UpdateSign.zPosition)) {
/*      */       
/* 1658 */       TileEntity var3 = this.mc.theWorld.getBlockTileEntity(par1Packet130UpdateSign.xPosition, par1Packet130UpdateSign.yPosition, par1Packet130UpdateSign.zPosition);
/*      */       
/* 1660 */       if (var3 instanceof TileEntitySign) {
/*      */         
/* 1662 */         TileEntitySign var4 = (TileEntitySign)var3;
/*      */         
/* 1664 */         if (var4.isEditable()) {
/*      */           
/* 1666 */           for (int var5 = 0; var5 < 4; var5++)
/*      */           {
/* 1668 */             var4.signText[var5] = par1Packet130UpdateSign.signLines[var5];
/*      */           }
/*      */           
/* 1671 */           var4.onInventoryChanged();
/*      */         } 
/*      */         
/* 1674 */         var2 = true;
/*      */       } 
/*      */     } 
/*      */     
/* 1678 */     if (!var2 && this.mc.thePlayer != null)
/*      */     {
/* 1680 */       this.mc.thePlayer.sendChatToPlayer(ChatMessageComponent.createFromText("Unable to locate sign at " + par1Packet130UpdateSign.xPosition + ", " + par1Packet130UpdateSign.yPosition + ", " + par1Packet130UpdateSign.zPosition));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleTileEntityData(Packet132TileEntityData par1Packet132TileEntityData) {
/* 1686 */     if (this.mc.theWorld.blockExists(par1Packet132TileEntityData.xPosition, par1Packet132TileEntityData.yPosition, par1Packet132TileEntityData.zPosition)) {
/*      */       
/* 1688 */       TileEntity var2 = this.mc.theWorld.getBlockTileEntity(par1Packet132TileEntityData.xPosition, par1Packet132TileEntityData.yPosition, par1Packet132TileEntityData.zPosition);
/*      */       
/* 1690 */       if (var2 != null)
/*      */       {
/* 1692 */         if (par1Packet132TileEntityData.actionType == 1 && var2 instanceof TileEntityMobSpawner) {
/*      */           
/* 1694 */           var2.readFromNBT(par1Packet132TileEntityData.data);
/*      */         }
/* 1696 */         else if (par1Packet132TileEntityData.actionType == 2 && var2 instanceof TileEntityCommandBlock) {
/*      */           
/* 1698 */           var2.readFromNBT(par1Packet132TileEntityData.data);
/*      */         }
/* 1700 */         else if (par1Packet132TileEntityData.actionType == 3 && var2 instanceof TileEntityBeacon) {
/*      */           
/* 1702 */           var2.readFromNBT(par1Packet132TileEntityData.data);
/*      */         }
/* 1704 */         else if (par1Packet132TileEntityData.actionType == 4 && var2 instanceof TileEntitySkull) {
/*      */           
/* 1706 */           var2.readFromNBT(par1Packet132TileEntityData.data);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleUpdateProgressbar(Packet105UpdateProgressbar par1Packet105UpdateProgressbar) {
/* 1714 */     EntityClientPlayerMP var2 = this.mc.thePlayer;
/* 1715 */     unexpectedPacket(par1Packet105UpdateProgressbar);
/*      */     
/* 1717 */     if (var2.openContainer != null && var2.openContainer.windowId == par1Packet105UpdateProgressbar.windowId)
/*      */     {
/* 1719 */       var2.openContainer.updateProgressBar(par1Packet105UpdateProgressbar.progressBar, par1Packet105UpdateProgressbar.progressBarValue);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handlePlayerInventory(Packet5PlayerInventory par1Packet5PlayerInventory) {
/* 1725 */     Entity var2 = getEntityByID(par1Packet5PlayerInventory.entityID);
/*      */     
/* 1727 */     if (var2 != null) {
/*      */       
/* 1729 */       if (par1Packet5PlayerInventory.full_inventory) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1734 */         int slot_index = par1Packet5PlayerInventory.slot;
/*      */         
/* 1736 */         if (slot_index < this.mc.thePlayer.inventory.mainInventory.length) {
/* 1737 */           this.mc.thePlayer.inventory.mainInventory[slot_index] = par1Packet5PlayerInventory.getItemSlot();
/*      */         } else {
/* 1739 */           this.mc.thePlayer.inventory.armorInventory[slot_index - this.mc.thePlayer.inventory.mainInventory.length] = par1Packet5PlayerInventory.getItemSlot();
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/* 1744 */       var2.setCurrentItemOrArmor(par1Packet5PlayerInventory.slot, par1Packet5PlayerInventory.getItemSlot());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleCloseWindow(Packet101CloseWindow par1Packet101CloseWindow) {
/* 1750 */     this.mc.thePlayer.func_92015_f();
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleBlockEvent(Packet54PlayNoteBlock par1Packet54PlayNoteBlock) {
/* 1755 */     this.mc.theWorld.addBlockEvent(par1Packet54PlayNoteBlock.xLocation, par1Packet54PlayNoteBlock.yLocation, par1Packet54PlayNoteBlock.zLocation, par1Packet54PlayNoteBlock.blockId, par1Packet54PlayNoteBlock.instrumentType, par1Packet54PlayNoteBlock.pitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleBlockDestroy(Packet55BlockDestroy par1Packet55BlockDestroy) {
/* 1760 */     this.mc.theWorld.destroyBlockInWorldPartially(par1Packet55BlockDestroy.getEntityId(), par1Packet55BlockDestroy.getPosX(), par1Packet55BlockDestroy.getPosY(), par1Packet55BlockDestroy.getPosZ(), par1Packet55BlockDestroy.getDestroyedStage());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleMapChunks(Packet56MapChunks par1Packet56MapChunks) {
/* 1765 */     for (int var2 = 0; var2 < par1Packet56MapChunks.getNumberOfChunkInPacket(); var2++) {
/*      */       
/* 1767 */       int var3 = par1Packet56MapChunks.getChunkPosX(var2);
/* 1768 */       int var4 = par1Packet56MapChunks.getChunkPosZ(var2);
/* 1769 */       this.worldClient.doPreChunk(var3, var4, true);
/* 1770 */       this.worldClient.invalidateBlockReceiveRegion(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
/* 1771 */       Chunk var5 = this.worldClient.getChunkFromChunkCoords(var3, var4);
/*      */       
/* 1773 */       if (var5 == null) {
/*      */         
/* 1775 */         this.worldClient.doPreChunk(var3, var4, true);
/* 1776 */         var5 = this.worldClient.getChunkFromChunkCoords(var3, var4);
/*      */       } 
/*      */       
/* 1779 */       if (var5 != null) {
/*      */         
/* 1781 */         var5.fillChunk(par1Packet56MapChunks.getChunkCompressedData(var2), par1Packet56MapChunks.field_73590_a[var2], par1Packet56MapChunks.field_73588_b[var2], true);
/* 1782 */         this.worldClient.markBlockRangeForRenderUpdate(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
/*      */         
/* 1784 */         if (!(this.worldClient.provider instanceof WorldProviderSurface))
/*      */         {
/* 1786 */           var5.resetRelightChecks();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canProcessPacketsAsync() {
/* 1799 */     return (this.mc != null && this.mc.theWorld != null && this.mc.thePlayer != null && this.worldClient != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleGameEvent(Packet70GameEvent par1Packet70GameEvent) {
/* 1804 */     EntityClientPlayerMP var2 = this.mc.thePlayer;
/* 1805 */     int var3 = par1Packet70GameEvent.eventType;
/* 1806 */     int var4 = par1Packet70GameEvent.gameMode;
/*      */     
/* 1808 */     if (var3 >= 0 && var3 < Packet70GameEvent.clientMessage.length && Packet70GameEvent.clientMessage[var3] != null)
/*      */     {
/* 1810 */       var2.addChatMessage(Packet70GameEvent.clientMessage[var3]);
/*      */     }
/*      */     
/* 1813 */     if (var3 == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1832 */       Minecraft.setErrorMessage("handleGameEvent: event type 1 is no longer handled");
/*      */     }
/* 1834 */     else if (var3 == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1839 */       Minecraft.setErrorMessage("handleGameEvent: event type 2 is no longer handled");
/*      */     }
/* 1841 */     else if (var3 == 3) {
/*      */       
/* 1843 */       this.mc.playerController.setGameType(EnumGameType.getByID(var4));
/*      */     }
/* 1845 */     else if (var3 == 4) {
/*      */       
/* 1847 */       this.mc.displayGuiScreen(new GuiWinGame());
/*      */     }
/* 1849 */     else if (var3 == 5) {
/*      */       
/* 1851 */       GameSettings var5 = this.mc.gameSettings;
/*      */       
/* 1853 */       if (var4 == 0)
/*      */       {
/* 1855 */         this.mc.displayGuiScreen(new GuiScreenDemo());
/*      */       }
/* 1857 */       else if (var4 == 101)
/*      */       {
/* 1859 */         this.mc.ingameGUI.getChatGUI().addTranslatedMessage("demo.help.movement", new Object[] { Keyboard.getKeyName(var5.keyBindForward.keyCode), Keyboard.getKeyName(var5.keyBindLeft.keyCode), Keyboard.getKeyName(var5.keyBindBack.keyCode), Keyboard.getKeyName(var5.keyBindRight.keyCode) });
/*      */       }
/* 1861 */       else if (var4 == 102)
/*      */       {
/* 1863 */         this.mc.ingameGUI.getChatGUI().addTranslatedMessage("demo.help.jump", new Object[] { Keyboard.getKeyName(var5.keyBindJump.keyCode) });
/*      */       }
/* 1865 */       else if (var4 == 103)
/*      */       {
/* 1867 */         this.mc.ingameGUI.getChatGUI().addTranslatedMessage("demo.help.inventory", new Object[] { Keyboard.getKeyName(var5.keyBindInventory.keyCode) });
/*      */       }
/*      */     
/* 1870 */     } else if (var3 == 6) {
/*      */       
/* 1872 */       this.worldClient.playSound(var2.posX, var2.posY + var2.getEyeHeight(), var2.posZ, "random.successful_hit", 0.18F, 0.45F, false);
/*      */     }
/* 1874 */     else if (var3 == 7) {
/*      */       
/* 1876 */       this.mc.theWorld.worldInfo.setVillageConditions((byte)var4);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMapData(Packet131MapData par1Packet131MapData) {
/* 1885 */     if (par1Packet131MapData.itemID == Item.map.itemID) {
/*      */       
/* 1887 */       ItemMap.getMPMapData(par1Packet131MapData.uniqueID, this.mc.theWorld).updateMPMapData(par1Packet131MapData.itemData);
/*      */     }
/*      */     else {
/*      */       
/* 1891 */       this.mc.getLogAgent().logWarning("Unknown itemid: " + par1Packet131MapData.uniqueID);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleDoorChange(Packet61DoorChange par1Packet61DoorChange) {
/* 1897 */     if (par1Packet61DoorChange.getRelativeVolumeDisabled()) {
/*      */       
/* 1899 */       this.mc.theWorld.func_82739_e(par1Packet61DoorChange.sfxID, par1Packet61DoorChange.posX, par1Packet61DoorChange.posY, par1Packet61DoorChange.posZ, par1Packet61DoorChange.auxData);
/*      */     }
/*      */     else {
/*      */       
/* 1903 */       this.mc.theWorld.playAuxSFX(par1Packet61DoorChange.sfxID, par1Packet61DoorChange.posX, par1Packet61DoorChange.posY, par1Packet61DoorChange.posZ, par1Packet61DoorChange.auxData);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleStatistic(Packet200Statistic par1Packet200Statistic) {
/* 1912 */     this.mc.thePlayer.incrementStat(StatList.getOneShotStat(par1Packet200Statistic.statisticId), par1Packet200Statistic.amount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityEffect(Packet41EntityEffect par1Packet41EntityEffect) {
/* 1920 */     Entity var2 = getEntityByID(par1Packet41EntityEffect.entityId);
/*      */     
/* 1922 */     if (var2 instanceof EntityLivingBase) {
/*      */       
/* 1924 */       PotionEffect var3 = new PotionEffect(par1Packet41EntityEffect.effectId, par1Packet41EntityEffect.duration, par1Packet41EntityEffect.effectAmplifier);
/* 1925 */       var3.setPotionDurationMax(par1Packet41EntityEffect.isDurationMax());
/* 1926 */       ((EntityLivingBase)var2).addPotionEffect(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleRemoveEntityEffect(Packet42RemoveEntityEffect par1Packet42RemoveEntityEffect) {
/* 1935 */     Entity var2 = getEntityByID(par1Packet42RemoveEntityEffect.entityId);
/*      */     
/* 1937 */     if (var2 instanceof EntityLivingBase)
/*      */     {
/* 1939 */       ((EntityLivingBase)var2).removePotionEffectClient(par1Packet42RemoveEntityEffect.effectId);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isServerHandler() {
/* 1948 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handlePlayerInfo(Packet201PlayerInfo par1Packet201PlayerInfo) {
/* 1956 */     GuiPlayerInfo var2 = (GuiPlayerInfo)this.playerInfoMap.get(par1Packet201PlayerInfo.playerName);
/*      */     
/* 1958 */     if (var2 == null && par1Packet201PlayerInfo.isConnected) {
/*      */ 
/*      */       
/* 1961 */       var2 = new GuiPlayerInfo(par1Packet201PlayerInfo.playerName, par1Packet201PlayerInfo.level);
/* 1962 */       this.playerInfoMap.put(par1Packet201PlayerInfo.playerName, var2);
/* 1963 */       this.playerInfoList.add(var2);
/*      */     } 
/*      */     
/* 1966 */     if (var2 != null && !par1Packet201PlayerInfo.isConnected) {
/*      */       
/* 1968 */       this.playerInfoMap.remove(par1Packet201PlayerInfo.playerName);
/* 1969 */       this.playerInfoList.remove(var2);
/*      */     } 
/*      */     
/* 1972 */     if (par1Packet201PlayerInfo.isConnected && var2 != null) {
/*      */       
/* 1974 */       var2.responseTime = par1Packet201PlayerInfo.ping;
/* 1975 */       var2.level = par1Packet201PlayerInfo.level;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleKeepAlive(Packet0KeepAlive par1Packet0KeepAlive) {
/* 1984 */     addToSendQueue(new Packet0KeepAlive(par1Packet0KeepAlive.randomId));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handlePlayerAbilities(Packet202PlayerAbilities par1Packet202PlayerAbilities) {
/* 1992 */     EntityClientPlayerMP var2 = this.mc.thePlayer;
/* 1993 */     var2.capabilities.isFlying = par1Packet202PlayerAbilities.getFlying();
/* 1994 */     var2.capabilities.isCreativeMode = par1Packet202PlayerAbilities.isCreativeMode();
/* 1995 */     var2.capabilities.disableDamage = par1Packet202PlayerAbilities.getDisableDamage();
/* 1996 */     var2.capabilities.allowFlying = par1Packet202PlayerAbilities.getAllowFlying();
/* 1997 */     var2.capabilities.setFlySpeed(par1Packet202PlayerAbilities.getFlySpeed());
/* 1998 */     var2.capabilities.setPlayerWalkSpeed(par1Packet202PlayerAbilities.getWalkSpeed());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleAutoComplete(Packet203AutoComplete par1Packet203AutoComplete) {
/* 2003 */     String[] var2 = par1Packet203AutoComplete.getText().split("\000");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2016 */     if (this.mc.isAnyChatOpen()) {
/* 2017 */       this.mc.getOpenChatGui().func_73894_a(var2);
/*      */     }
/*      */   }
/*      */   
/*      */   public void handleLevelSound(Packet62LevelSound par1Packet62LevelSound) {
/* 2022 */     this.mc.theWorld.playSound(par1Packet62LevelSound.getEffectX(), par1Packet62LevelSound.getEffectY(), par1Packet62LevelSound.getEffectZ(), par1Packet62LevelSound.getSoundName(), par1Packet62LevelSound.getVolume(), par1Packet62LevelSound.getPitch(), false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleLongDistanceSound(Packet80LongDistanceSound packet) {
/* 2027 */     this.mc.theWorld.playLongDistanceSound(packet.getEffectX(), packet.getEffectY(), packet.getEffectZ(), packet.getSoundName(), packet.getVolume(), packet.getPitch(), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSimpleSignal(Packet85SimpleSignal packet) {
/* 2049 */     EntityClientPlayerMP player = this.mc.thePlayer;
/* 2050 */     WorldClient world = this.mc.theWorld;
/*      */     
/* 2052 */     EnumSignal signal_type = packet.signal_type;
/*      */     
/* 2054 */     if (signal_type == EnumSignal.boolean_test) {
/*      */       
/* 2056 */       if (packet.getBoolean() != true) {
/* 2057 */         Minecraft.setErrorMessage("handleSimpleSignal: boolean test failed");
/*      */       }
/* 2059 */     } else if (signal_type == EnumSignal.byte_test) {
/*      */       
/* 2061 */       if (packet.getByte() != 3) {
/* 2062 */         Minecraft.setErrorMessage("handleSimpleSignal: byte test failed");
/*      */       }
/* 2064 */     } else if (signal_type == EnumSignal.short_test) {
/*      */       
/* 2066 */       if (packet.getShort() != 42) {
/* 2067 */         Minecraft.setErrorMessage("handleSimpleSignal: short test failed");
/*      */       }
/* 2069 */     } else if (signal_type == EnumSignal.integer_test) {
/*      */       
/* 2071 */       if (packet.getInteger() != 101) {
/* 2072 */         Minecraft.setErrorMessage("handleSimpleSignal: integer test failed");
/*      */       }
/* 2074 */     } else if (signal_type == EnumSignal.float_test) {
/*      */       
/* 2076 */       if (packet.getFloat() != 0.2F) {
/* 2077 */         Minecraft.setErrorMessage("handleSimpleSignal: float test failed");
/*      */       }
/* 2079 */     } else if (signal_type == EnumSignal.complex_test) {
/*      */       
/* 2081 */       if (packet.getBoolean() != true || packet.getByte() != 3 || packet.getShort() != 42 || packet.getInteger() != 101 || packet.getFloat() != 0.2F) {
/* 2082 */         Minecraft.setErrorMessage("handleSimpleSignal: complex test failed");
/*      */       } else {
/* 2084 */         System.out.println("Complex test passed");
/*      */       } 
/* 2086 */     } else if (signal_type == EnumSignal.approx_pos_test) {
/*      */       
/* 2088 */       world.spawnParticle(EnumParticle.heart, packet.getApproxPosX(), packet.getApproxPosY(), packet.getApproxPosZ(), 0.0D, 0.0D, 0.0D);
/*      */     }
/* 2090 */     else if (signal_type == EnumSignal.exact_pos_test) {
/*      */       
/* 2092 */       world.spawnParticle(EnumParticle.fireworkSpark, packet.getExactPosX(), packet.getExactPosY(), packet.getExactPosZ(), (Math.random() - 0.5D) * 0.20000000298023224D, 0.20000000298023224D, (Math.random() - 0.5D) * 0.20000000298023224D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2107 */     else if (signal_type == EnumSignal.achievement_unlocked) {
/*      */       
/* 2109 */       if (packet.getInteger() == -2) {
/*      */         
/* 2111 */         AchievementList.clearAchievements();
/*      */       }
/*      */       else {
/*      */         
/* 2115 */         StatBase stat = StatList.getStat(packet.getInteger());
/*      */         
/* 2117 */         if (stat != null && stat.isAchievement()) {
/* 2118 */           this.mc.guiAchievement.queueTakenAchievement((Achievement)stat);
/*      */         } else {
/* 2120 */           Minecraft.setErrorMessage("handleSimpleSignal: invalid achievement id " + packet.getInteger());
/*      */         } 
/*      */       } 
/* 2123 */     } else if (signal_type == EnumSignal.tournament_mode) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2128 */       if (packet.getByte() == -1) {
/* 2129 */         DedicatedServer.tournament_type = null;
/*      */       } else {
/* 2131 */         DedicatedServer.tournament_type = EnumTournamentType.get(packet.getByte());
/*      */       }
/*      */     
/*      */     }
/* 2135 */     else if (signal_type == EnumSignal.unlock_slots) {
/*      */       
/* 2137 */       player.openContainer.unlockNextTick();
/*      */     }
/* 2139 */     else if (signal_type == EnumSignal.start_falling_asleep) {
/*      */       
/* 2141 */       this.mc.thePlayer.conscious_state = EnumConsciousState.falling_asleep;
/*      */     }
/* 2143 */     else if (signal_type == EnumSignal.start_waking_up) {
/*      */       
/* 2145 */       if (player.conscious_state == EnumConsciousState.sleeping) {
/* 2146 */         world._calculateInitialWeather();
/*      */       }
/* 2148 */       player.conscious_state = EnumConsciousState.waking_up;
/*      */     }
/* 2150 */     else if (signal_type == EnumSignal.stop_rain_and_thunder_immediately) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2156 */       Minecraft.setErrorMessage("handleSimpleSignal: stop_rain_and_thunder_immediately is no longer handled");
/*      */     }
/* 2158 */     else if (signal_type == EnumSignal.clear_inventory) {
/*      */       
/* 2160 */       player.inventory.clearInventory(-1, -1);
/*      */     }
/* 2162 */     else if (signal_type == EnumSignal.reconnection_delay) {
/*      */ 
/*      */       
/* 2165 */       GuiDisconnected.message_type = packet.getByte();
/* 2166 */       Minecraft.adjusted_hour_of_disconnection = packet.getShort();
/* 2167 */       Minecraft.soonest_reconnection_time = System.currentTimeMillis() + (packet.getInteger() * 1000);
/*      */     }
/* 2169 */     else if (signal_type == EnumSignal.cpu_overburdened) {
/*      */       
/* 2171 */       GuiIngame.display_overburdened_cpu_icon_until_ms = System.currentTimeMillis() + 5000L;
/*      */     }
/* 2173 */     else if (signal_type == EnumSignal.runegate_start) {
/*      */       
/* 2175 */       player.is_runegate_teleporting = true;
/* 2176 */       player.runegate_counter = 0;
/*      */     }
/* 2178 */     else if (signal_type == EnumSignal.runegate_finished) {
/*      */       
/* 2180 */       player.is_runegate_teleporting = false;
/* 2181 */       player.runegate_counter = 30;
/*      */     }
/* 2183 */     else if (signal_type == EnumSignal.curse_realized) {
/*      */       
/* 2185 */       player.is_cursed = true;
/* 2186 */       player.curse_id = packet.getByte();
/* 2187 */       this.mc.ingameGUI.curse_notification_counter = 100;
/*      */       
/* 2189 */       player.onCurseRealized(packet.getByte());
/*      */     }
/* 2191 */     else if (signal_type == EnumSignal.cursed) {
/*      */       
/* 2193 */       player.is_cursed = true;
/* 2194 */       player.curse_id = packet.getByte();
/*      */     }
/* 2196 */     else if (signal_type == EnumSignal.curse_effect_learned) {
/*      */       
/* 2198 */       player.curse_effect_known = true;
/*      */     }
/* 2200 */     else if (signal_type == EnumSignal.curse_lifted) {
/*      */       
/* 2202 */       player.is_cursed = false;
/* 2203 */       player.curse_id = 0;
/* 2204 */       player.curse_effect_known = false;
/* 2205 */       this.mc.ingameGUI.curse_notification_counter = 100;
/*      */     }
/* 2207 */     else if (signal_type == EnumSignal.damage_taken) {
/*      */       
/* 2209 */       player.crafting_ticks = Math.max(player.crafting_ticks - packet.getShort() * 5, 0);
/*      */     }
/* 2211 */     else if (signal_type == EnumSignal.block_fx) {
/*      */       
/* 2213 */       handleBlockFX(packet);
/*      */     }
/* 2215 */     else if (signal_type == EnumSignal.entity_fx) {
/*      */       
/* 2217 */       handleEntityFX(packet);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2227 */     else if (signal_type == EnumSignal.transfered_to_world) {
/*      */       
/* 2229 */       player.onTransferToWorld();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2242 */     else if (signal_type == EnumSignal.after_respawn) {
/*      */       
/* 2244 */       player.afterRespawn();
/*      */     }
/* 2246 */     else if (signal_type == EnumSignal.take_screenshot_of_world_seed) {
/*      */       
/* 2248 */       this.mc.take_screenshot_next_tick = true;
/* 2249 */       this.mc.gameSettings.gui_mode = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2255 */     else if (signal_type == EnumSignal.block_hit_fx) {
/*      */ 
/*      */       
/* 2258 */       this.mc.effectRenderer.addBlockHitEffects(packet.getBlockX(), packet.getBlockY(), packet.getBlockZ(), EnumFace.get(packet.getByte()));
/*      */     }
/* 2260 */     else if (signal_type == EnumSignal.try_auto_switch_or_restock) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2267 */       Item item = Item.getItem(packet.getShort());
/*      */       
/* 2269 */       if (item.isDamageable()) {
/* 2270 */         this.mc.playerController.setLastUsedItem(item, packet.getByte());
/*      */       } else {
/* 2272 */         this.mc.thePlayer.inventory.trySwitchItemOrRestock(item, packet.getByte(), false);
/*      */       } 
/* 2274 */     } else if (signal_type == EnumSignal.try_auto_switch_or_restock_large_subtype) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2281 */       Item item = Item.getItem(packet.getShort());
/*      */       
/* 2283 */       if (item.isDamageable()) {
/* 2284 */         this.mc.playerController.setLastUsedItem(item, packet.getInteger());
/*      */       } else {
/* 2286 */         this.mc.thePlayer.inventory.trySwitchItemOrRestock(item, packet.getInteger(), false);
/*      */       } 
/* 2288 */     } else if (signal_type == EnumSignal.toggle_night_vision_override) {
/*      */       
/* 2290 */       Minecraft.night_vision_override = !Minecraft.night_vision_override;
/*      */     }
/* 2292 */     else if (signal_type == EnumSignal.update_minecart_fuel) {
/*      */       
/* 2294 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2296 */       if (entity instanceof EntityMinecartFurnace) {
/* 2297 */         ((EntityMinecartFurnace)entity).setFuel(packet.getInteger());
/* 2298 */       } else if (packet.getEntityID() == -100) {
/* 2299 */         EntityMinecart.update(player);
/*      */       } 
/* 2301 */     } else if (signal_type == EnumSignal.confirm_or_cancel_item_in_use) {
/*      */       
/* 2303 */       player.stopUsingItem(false);
/*      */     }
/* 2305 */     else if (signal_type == EnumSignal.malnourished) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2317 */       int bits = packet.getInteger();
/*      */       
/* 2319 */       player.is_malnourished_in_protein = BitHelper.isBitSet(bits, 1);
/*      */       
/* 2321 */       player.is_malnourished_in_phytonutrients = BitHelper.isBitSet(bits, 4);
/*      */       
/* 2323 */       player.setInsulinResistance(bits >> 8);
/* 2324 */       player.insulin_resistance_level = EnumInsulinResistanceLevel.getByTransmittedOrdinal(bits >> 3 & 0x3);
/*      */ 
/*      */     
/*      */     }
/* 2328 */     else if (signal_type == EnumSignal.tournament_score) {
/*      */       
/* 2330 */       if (packet.getInteger() != player.tournament_score)
/*      */       {
/* 2332 */         if (packet.getBoolean()) {
/*      */           
/* 2334 */           player.delta_tournament_score += packet.getInteger() - player.tournament_score;
/* 2335 */           player.delta_tournament_score_opacity = 480;
/*      */         } 
/*      */         
/* 2338 */         player.tournament_score = packet.getInteger();
/*      */       }
/*      */     
/* 2341 */     } else if (signal_type == EnumSignal.prize_key_code) {
/*      */       
/* 2343 */       writePrizeKeyFile(player.username, packet.getInteger());
/*      */     }
/* 2345 */     else if (signal_type == EnumSignal.item_in_use) {
/*      */       
/* 2347 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2349 */       if (entity instanceof EntityOtherPlayerMP) {
/*      */         
/* 2351 */         EntityOtherPlayerMP other_player = (EntityOtherPlayerMP)entity;
/*      */         
/* 2353 */         ItemStack held_item_stack = other_player.getHeldItemStack();
/*      */         
/* 2355 */         if (held_item_stack == null || packet.getInteger() < 1) {
/*      */           
/* 2357 */           other_player.itemInUse = null;
/* 2358 */           other_player.itemInUseCount = 0;
/* 2359 */           other_player.isItemInUse = false;
/*      */         }
/*      */         else {
/*      */           
/* 2363 */           other_player.itemInUse = held_item_stack;
/* 2364 */           other_player.itemInUseCount = packet.getInteger();
/* 2365 */           other_player.isItemInUse = true;
/*      */           
/* 2367 */           if (held_item_stack.getItem() instanceof ItemBow && other_player.nocked_arrow == null) {
/* 2368 */             other_player.nocked_arrow = Item.arrowFlint;
/*      */           }
/*      */         } 
/*      */       } 
/* 2372 */     } else if (signal_type == EnumSignal.nocked_arrow) {
/*      */       
/* 2374 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2376 */       if (entity instanceof EntityOtherPlayerMP) {
/* 2377 */         (entity.getAsPlayer()).nocked_arrow = (ItemArrow)Item.getItem(packet.getShort());
/*      */       }
/* 2379 */     } else if (signal_type == EnumSignal.mh) {
/*      */       
/* 2381 */       sendMasterHash(packet.getInteger());
/*      */     }
/* 2383 */     else if (signal_type == EnumSignal.see) {
/*      */       
/* 2385 */       RenderPlayer.see_zevimrgv_in_tournament = !RenderPlayer.see_zevimrgv_in_tournament;
/*      */     }
/* 2387 */     else if (signal_type == EnumSignal.allotted_time) {
/*      */       
/* 2389 */       GuiIngame.allotted_time = packet.getInteger();
/*      */     }
/* 2391 */     else if (signal_type == EnumSignal.server_load) {
/*      */       
/* 2393 */       GuiIngame.server_load = packet.getShort();
/*      */     }
/* 2395 */     else if (signal_type == EnumSignal.clear_tentative_bounding_box) {
/*      */ 
/*      */       
/* 2398 */       player.setTentativeBoundingBoxCountdownForClearing(packet.getBlockX(), packet.getBlockY(), packet.getBlockZ(), 2);
/*      */     }
/* 2400 */     else if (signal_type == EnumSignal.dedicated_server) {
/*      */       
/* 2402 */       Minecraft.is_dedicated_server_running = true;
/*      */     }
/* 2404 */     else if (signal_type == EnumSignal.sync_pos) {
/*      */       
/* 2406 */       player.setPosition(packet.getExactPosX(), packet.getExactPosY(), packet.getExactPosZ());
/* 2407 */       player.receiveChatMessage("Position synchronized with server");
/*      */     }
/* 2409 */     else if (signal_type == EnumSignal.arrow_hit_block) {
/*      */       
/* 2411 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2413 */       if (entity instanceof EntityArrow)
/*      */       {
/*      */         
/* 2416 */         entity.setPosition(packet.getExactPosX(), packet.getExactPosY(), packet.getExactPosZ());
/*      */         
/* 2418 */         EntityArrow arrow = (EntityArrow)entity;
/*      */ 
/*      */ 
/*      */         
/* 2422 */         if (!arrow.isInGround())
/*      */         {
/* 2424 */           arrow.setInGround();
/* 2425 */           arrow.arrowShake = 7;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2431 */     else if (signal_type == EnumSignal.fish_hook_in_entity) {
/*      */       
/* 2433 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2435 */       if (entity instanceof EntityFishHook)
/*      */       {
/* 2437 */         EntityFishHook fish_hook = (EntityFishHook)entity;
/*      */         
/* 2439 */         fish_hook.bobber = (packet.getInteger() < 0) ? null : world.getEntityByID(packet.getInteger());
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2444 */     else if (signal_type == EnumSignal.fireball_reversal) {
/*      */       
/* 2446 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2448 */       if (entity instanceof EntityFireball)
/*      */       {
/* 2450 */         EntityFireball fireball = (EntityFireball)entity;
/*      */         
/* 2452 */         fireball.motionX = packet.getApproxPosX();
/* 2453 */         fireball.motionY = packet.getApproxPosY();
/* 2454 */         fireball.motionZ = packet.getApproxPosZ();
/*      */         
/* 2456 */         fireball.accelerationX = fireball.motionX * 0.1D;
/* 2457 */         fireball.accelerationY = fireball.motionY * 0.1D;
/* 2458 */         fireball.accelerationZ = fireball.motionZ * 0.1D;
/*      */       }
/*      */     
/* 2461 */     } else if (signal_type == EnumSignal.in_love) {
/*      */       
/* 2463 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 2465 */       if (entity instanceof EntityAnimal) {
/* 2466 */         entity.getAsEntityAnimal().setInLove(packet.getShort(), false);
/*      */       }
/* 2468 */     } else if (signal_type == EnumSignal.update_potion_effect) {
/*      */       
/* 2470 */       int potion_id = packet.getByte();
/* 2471 */       int potion_amplifier = packet.getShort();
/* 2472 */       int potion_duration = packet.getInteger();
/*      */       
/* 2474 */       PotionEffect potion_effect = player.getActivePotionEffect(potion_id);
/*      */       
/* 2476 */       if (potion_effect == null) {
/* 2477 */         player.addPotionEffect(new PotionEffect(potion_id, potion_duration, potion_amplifier));
/*      */       } else {
/* 2479 */         potion_effect.setAmplifier(potion_amplifier).setDuration(potion_duration);
/*      */       } 
/* 2481 */     } else if (signal_type == EnumSignal.toggle_mute) {
/*      */       
/* 2483 */       SoundManager.muted = !SoundManager.muted;
/*      */       
/* 2485 */       player.receiveChatMessage("Sound is now " + (SoundManager.muted ? "" : "un-") + "muted");
/*      */     }
/* 2487 */     else if (signal_type == EnumSignal.skills) {
/*      */ 
/*      */       
/* 2490 */       world.worldInfo.setSkillsEnabled(packet.getBoolean());
/*      */     }
/* 2492 */     else if (signal_type == EnumSignal.skillset) {
/*      */       
/* 2494 */       player.setSkills(packet.getInteger());
/*      */     }
/* 2496 */     else if (signal_type == EnumSignal.respawn_screen) {
/*      */       
/* 2498 */       if (this.mc.currentScreen instanceof GuiGameOver) {
/* 2499 */         ((GuiGameOver)this.mc.currentScreen).setRespawnCountdown(packet.getShort());
/*      */       }
/* 2501 */     } else if (signal_type == EnumSignal.loaded_tile_entities) {
/*      */       
/* 2503 */       TileEntity.printTileEntitiesList("Loaded Entities on Client", world.loadedTileEntityList);
/*      */       
/* 2505 */       System.out.println();
/*      */     }
/* 2507 */     else if (signal_type == EnumSignal.last_issued_map_id) {
/*      */       
/* 2509 */       this.mc.theWorld.setUniqueDataId("map", packet.getShort());
/*      */     }
/* 2511 */     else if (signal_type == EnumSignal.list_commands) {
/*      */       
/* 2513 */       for (int i = 0; i < (EnumCommand.values()).length; i++)
/*      */       {
/* 2515 */         EnumCommand enum_command = EnumCommand.get(i);
/* 2516 */         player.receiveChatMessage("/" + enum_command.text + EnumChatFormatting.LIGHT_GRAY + " " + enum_command.description);
/*      */       }
/*      */     
/* 2519 */     } else if (signal_type == EnumSignal.furnace_heat_level) {
/*      */       
/* 2521 */       if (player.openContainer instanceof ContainerFurnace)
/*      */       {
/* 2523 */         ContainerFurnace container_furnace = (ContainerFurnace)player.openContainer;
/* 2524 */         (container_furnace.getTileEntityFurnace()).heat_level = packet.getByte();
/*      */       }
/*      */     
/* 2527 */     } else if (signal_type == EnumSignal.picked_up_held_item) {
/*      */       
/* 2529 */       if (this.mc.playerController.autoStockEnabled()) {
/* 2530 */         player.prevent_block_placement_due_to_picking_up_held_item_until = System.currentTimeMillis() + 1500L;
/*      */       }
/*      */     } else {
/*      */       
/* 2534 */       Minecraft.setErrorMessage("handleSimpleSignal: unhandled signal (" + signal_type + ")");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleUpdateStrongboxOwner(Packet88UpdateStrongboxOwner packet) {
/* 2540 */     boolean updated = false;
/*      */     
/* 2542 */     if (this.mc.theWorld.blockExists(packet.x, packet.y, packet.z)) {
/*      */       
/* 2544 */       TileEntity tile = this.mc.theWorld.getBlockTileEntity(packet.x, packet.y, packet.z);
/*      */       
/* 2546 */       if (tile instanceof TileEntityStrongbox) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2551 */         ((TileEntityStrongbox)tile).owner_name = packet.getOwnerName();
/* 2552 */         updated = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2559 */     if (!updated && this.mc.thePlayer != null) {
/* 2560 */       this.mc.thePlayer.sendChatToPlayer(ChatMessageComponent.createFromText("Unable to update strongbox owner on client at " + packet.x + ", " + packet.y + ", " + packet.z));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handlePlayerStat(Packet91PlayerStat packet) {
/* 2586 */     StatBase stat = StatList.getStat(packet.id);
/*      */     
/* 2588 */     if (StatList.isEitherZeroOrOne(stat)) {
/* 2589 */       this.mc.thePlayer.stats.put(Integer.valueOf(packet.id), new Byte((byte)(int)packet.value));
/* 2590 */     } else if (StatList.hasLongValue(stat)) {
/* 2591 */       this.mc.thePlayer.stats.put(Integer.valueOf(packet.id), new Long(packet.value));
/*      */     } else {
/* 2593 */       this.mc.thePlayer.stats.put(Integer.valueOf(packet.id), new Integer((int)packet.value));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload) {
/* 2600 */     if ("MC|TrList".equals(par1Packet250CustomPayload.channel)) {
/*      */       
/* 2602 */       DataInputStream var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
/*      */ 
/*      */       
/*      */       try {
/* 2606 */         int var3 = var2.readInt();
/* 2607 */         GuiScreen var4 = this.mc.currentScreen;
/*      */         
/* 2609 */         if (var4 != null && var4 instanceof GuiMerchant && var3 == this.mc.thePlayer.openContainer.windowId)
/*      */         {
/* 2611 */           IMerchant var5 = ((GuiMerchant)var4).getIMerchant();
/* 2612 */           MerchantRecipeList var6 = MerchantRecipeList.readRecipiesFromStream(var2);
/* 2613 */           var5.setRecipes(var6);
/*      */         }
/*      */       
/* 2616 */       } catch (IOException var7) {
/*      */         
/* 2618 */         var7.printStackTrace();
/*      */       }
/*      */     
/* 2621 */     } else if ("MC|Brand".equals(par1Packet250CustomPayload.channel)) {
/*      */       
/* 2623 */       this.mc.thePlayer.func_142020_c(new String(par1Packet250CustomPayload.data, Charsets.UTF_8));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSetObjective(Packet206SetObjective par1Packet206SetObjective) {
/* 2632 */     Scoreboard var2 = this.worldClient.getScoreboard();
/*      */ 
/*      */     
/* 2635 */     if (par1Packet206SetObjective.change == 0) {
/*      */       
/* 2637 */       ScoreObjective var3 = var2.func_96535_a(par1Packet206SetObjective.objectiveName, ScoreObjectiveCriteria.field_96641_b);
/* 2638 */       var3.setDisplayName(par1Packet206SetObjective.objectiveDisplayName);
/*      */     }
/*      */     else {
/*      */       
/* 2642 */       ScoreObjective var3 = var2.getObjective(par1Packet206SetObjective.objectiveName);
/*      */       
/* 2644 */       if (par1Packet206SetObjective.change == 1) {
/*      */         
/* 2646 */         var2.func_96519_k(var3);
/*      */       }
/* 2648 */       else if (par1Packet206SetObjective.change == 2) {
/*      */         
/* 2650 */         var3.setDisplayName(par1Packet206SetObjective.objectiveDisplayName);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSetScore(Packet207SetScore par1Packet207SetScore) {
/* 2660 */     Scoreboard var2 = this.worldClient.getScoreboard();
/* 2661 */     ScoreObjective var3 = var2.getObjective(par1Packet207SetScore.scoreName);
/*      */     
/* 2663 */     if (par1Packet207SetScore.updateOrRemove == 0) {
/*      */       
/* 2665 */       Score var4 = var2.func_96529_a(par1Packet207SetScore.itemName, var3);
/* 2666 */       var4.func_96647_c(par1Packet207SetScore.value);
/*      */     }
/* 2668 */     else if (par1Packet207SetScore.updateOrRemove == 1) {
/*      */       
/* 2670 */       var2.func_96515_c(par1Packet207SetScore.itemName);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSetDisplayObjective(Packet208SetDisplayObjective par1Packet208SetDisplayObjective) {
/* 2679 */     Scoreboard var2 = this.worldClient.getScoreboard();
/*      */     
/* 2681 */     if (par1Packet208SetDisplayObjective.scoreName.length() == 0) {
/*      */       
/* 2683 */       var2.func_96530_a(par1Packet208SetDisplayObjective.scoreboardPosition, (ScoreObjective)null);
/*      */     }
/*      */     else {
/*      */       
/* 2687 */       ScoreObjective var3 = var2.getObjective(par1Packet208SetDisplayObjective.scoreName);
/* 2688 */       var2.func_96530_a(par1Packet208SetDisplayObjective.scoreboardPosition, var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSetPlayerTeam(Packet209SetPlayerTeam par1Packet209SetPlayerTeam) {
/*      */     ScorePlayerTeam var3;
/* 2697 */     Scoreboard var2 = this.worldClient.getScoreboard();
/*      */ 
/*      */     
/* 2700 */     if (par1Packet209SetPlayerTeam.mode == 0) {
/*      */       
/* 2702 */       var3 = var2.createTeam(par1Packet209SetPlayerTeam.teamName);
/*      */     }
/*      */     else {
/*      */       
/* 2706 */       var3 = var2.func_96508_e(par1Packet209SetPlayerTeam.teamName);
/*      */     } 
/*      */     
/* 2709 */     if (par1Packet209SetPlayerTeam.mode == 0 || par1Packet209SetPlayerTeam.mode == 2) {
/*      */       
/* 2711 */       var3.setTeamName(par1Packet209SetPlayerTeam.teamDisplayName);
/* 2712 */       var3.setNamePrefix(par1Packet209SetPlayerTeam.teamPrefix);
/* 2713 */       var3.setNameSuffix(par1Packet209SetPlayerTeam.teamSuffix);
/* 2714 */       var3.func_98298_a(par1Packet209SetPlayerTeam.friendlyFire);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2720 */     if (par1Packet209SetPlayerTeam.mode == 0 || par1Packet209SetPlayerTeam.mode == 3) {
/*      */       
/* 2722 */       Iterator<String> var4 = par1Packet209SetPlayerTeam.playerNames.iterator();
/*      */       
/* 2724 */       while (var4.hasNext()) {
/*      */         
/* 2726 */         String var5 = var4.next();
/* 2727 */         var2.addPlayerToTeam(var5, var3);
/*      */       } 
/*      */     } 
/*      */     
/* 2731 */     if (par1Packet209SetPlayerTeam.mode == 4) {
/*      */       
/* 2733 */       Iterator<String> var4 = par1Packet209SetPlayerTeam.playerNames.iterator();
/*      */       
/* 2735 */       while (var4.hasNext()) {
/*      */         
/* 2737 */         String var5 = var4.next();
/* 2738 */         var2.removePlayerFromTeam(var5, var3);
/*      */       } 
/*      */     } 
/*      */     
/* 2742 */     if (par1Packet209SetPlayerTeam.mode == 1)
/*      */     {
/* 2744 */       var2.func_96511_d(var3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_110773_a(Packet44UpdateAttributes par1Packet44UpdateAttributes) {
/* 2768 */     Entity var2 = getEntityByID(par1Packet44UpdateAttributes.func_111002_d());
/*      */     
/* 2770 */     if (var2 != null) {
/*      */       
/* 2772 */       if (!(var2 instanceof EntityLivingBase))
/*      */       {
/* 2774 */         throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + var2 + ")");
/*      */       }
/*      */ 
/*      */       
/* 2778 */       BaseAttributeMap var3 = ((EntityLivingBase)var2).getAttributeMap();
/* 2779 */       Iterator<Packet44UpdateAttributesSnapshot> var4 = par1Packet44UpdateAttributes.func_111003_f().iterator();
/*      */       
/* 2781 */       while (var4.hasNext()) {
/*      */         
/* 2783 */         Packet44UpdateAttributesSnapshot var5 = var4.next();
/* 2784 */         AttributeInstance var6 = var3.getAttributeInstanceByName(var5.func_142040_a());
/*      */         
/* 2786 */         if (var6 == null)
/*      */         {
/*      */ 
/*      */           
/* 2790 */           var6 = var3.register(new RangedAttribute(var5.func_142040_a(), 0.0D, 0.0D, Double.MAX_VALUE));
/*      */         }
/*      */         
/* 2793 */         var6.setAttribute(var5.func_142041_b());
/* 2794 */         var6.func_142049_d();
/* 2795 */         Iterator<AttributeModifier> var7 = var5.func_142039_c().iterator();
/*      */         
/* 2797 */         while (var7.hasNext()) {
/*      */           
/* 2799 */           AttributeModifier var8 = var7.next();
/* 2800 */           var6.applyModifier(var8);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public INetworkManager getNetManager() {
/* 2812 */     return this.netManager;
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderBrokenItem(EntityLivingBase entity_living_base, Item item) {
/* 2817 */     World world = entity_living_base.worldObj;
/*      */     
/* 2819 */     if (!world.isRemote) {
/*      */       return;
/*      */     }
/* 2822 */     for (int i = 0; i < 5; i++) {
/*      */       
/* 2824 */       Vec3 var3 = world.getWorldVec3Pool().getVecFromPool((this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 2825 */       var3.rotateAroundX(-entity_living_base.rotationPitch * 3.1415927F / 180.0F);
/* 2826 */       var3.rotateAroundY(-entity_living_base.rotationYaw * 3.1415927F / 180.0F);
/* 2827 */       Vec3 var4 = world.getWorldVec3Pool().getVecFromPool((this.rand.nextFloat() - 0.5D) * 0.3D, -this.rand.nextFloat() * 0.6D - 0.3D, 0.6D);
/* 2828 */       var4.rotateAroundX(-entity_living_base.rotationPitch * 3.1415927F / 180.0F);
/* 2829 */       var4.rotateAroundY(-entity_living_base.rotationYaw * 3.1415927F / 180.0F);
/* 2830 */       var4 = var4.addVector(entity_living_base.posX, entity_living_base.posY + entity_living_base.getEyeHeight(), entity_living_base.posZ);
/*      */       
/* 2832 */       world.spawnParticleEx(EnumParticle.iconcrack, item.itemID, 0, var4.xCoord, var4.yCoord, var4.zCoord, var3.xCoord, var3.yCoord + 0.05D, var3.zCoord);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleBlockFX(Packet85SimpleSignal packet) {
/* 2839 */     WorldClient world = this.mc.theWorld;
/*      */     
/* 2841 */     EnumBlockFX kind = (EnumBlockFX)packet.signal_subtype;
/*      */     
/* 2843 */     int x = packet.getBlockX();
/* 2844 */     int y = packet.getBlockY();
/* 2845 */     int z = packet.getBlockZ();
/*      */     
/* 2847 */     if (kind == EnumBlockFX.lava_mixing_with_water) {
/*      */       
/* 2849 */       for (int i = 0; i < 8; i++) {
/* 2850 */         world.spawnParticle(EnumParticle.largesmoke, x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D);
/*      */       }
/*      */       
/* 2853 */       world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F, false);
/*      */     }
/* 2855 */     else if (kind == EnumBlockFX.water_evaporation_in_hell) {
/*      */       
/* 2857 */       for (int i = 0; i < 8; i++) {
/* 2858 */         world.spawnParticle(EnumParticle.largesmoke, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
/*      */       }
/* 2860 */       world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F, false);
/*      */     }
/* 2862 */     else if (kind == EnumBlockFX.steam || kind == EnumBlockFX.steam_particles_only) {
/*      */       
/* 2864 */       for (int i = 0; i < 8; i++) {
/* 2865 */         world.spawnParticle(EnumParticle.explode, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
/*      */       }
/* 2867 */       if (kind == EnumBlockFX.steam) {
/* 2868 */         world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F, false);
/*      */       }
/* 2870 */     } else if (kind == EnumBlockFX.smoke_and_steam) {
/*      */       int i;
/* 2872 */       for (i = 0; i < 6; i++) {
/* 2873 */         world.spawnParticle(EnumParticle.largesmoke, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
/*      */       }
/* 2875 */       for (i = 0; i < 6; i++) {
/* 2876 */         world.spawnParticle(EnumParticle.explode, x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
/*      */       }
/* 2878 */       world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F, false);
/*      */     }
/* 2880 */     else if (kind == EnumBlockFX.manure) {
/*      */       
/* 2882 */       ItemManure.particleEffect(world, x, y, z, 2 + world.rand.nextInt(3));
/*      */     }
/* 2884 */     else if (kind == EnumBlockFX.particle_trail) {
/*      */ 
/*      */ 
/*      */       
/* 2888 */       EnumParticle enum_particle = EnumParticle.get(packet.getByte());
/*      */       
/* 2890 */       int num_particles = packet.getShort();
/*      */       
/* 2892 */       int origin_x = packet.getBlockX();
/* 2893 */       int origin_y = packet.getBlockY();
/* 2894 */       int origin_z = packet.getBlockZ();
/*      */       
/* 2896 */       double destination_x = packet.getApproxPosX();
/* 2897 */       double destination_y = packet.getApproxPosY();
/* 2898 */       double destination_z = packet.getApproxPosZ();
/*      */       
/* 2900 */       double dx = origin_x - destination_x;
/* 2901 */       double dy = origin_y - destination_y;
/* 2902 */       double dz = origin_z - destination_z;
/*      */       
/* 2904 */       for (int i = 0; i < num_particles; i++)
/*      */       {
/* 2906 */         double fraction = world.rand.nextDouble();
/*      */         
/* 2908 */         float motion_x = (world.rand.nextFloat() - 0.5F) * 0.2F;
/* 2909 */         float motion_y = (world.rand.nextFloat() - 0.5F) * 0.2F;
/* 2910 */         float motion_z = (world.rand.nextFloat() - 0.5F) * 0.2F;
/*      */         
/* 2912 */         double pos_x = destination_x + dx * fraction + (world.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
/* 2913 */         double pos_y = destination_y + dy * fraction + world.rand.nextDouble() * 1.0D - 0.5D;
/* 2914 */         double pos_z = destination_z + dz * fraction + (world.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
/*      */ 
/*      */         
/* 2917 */         world.spawnParticle(enum_particle, pos_x, pos_y, pos_z, motion_x, motion_y, motion_z);
/*      */       }
/*      */     
/* 2920 */     } else if (kind == EnumBlockFX.destroy) {
/*      */       
/* 2922 */       int data = packet.getInteger();
/*      */       
/* 2924 */       int block_id = data & 0xFF;
/* 2925 */       int metadata = data >> 8 & 0xF;
/* 2926 */       int successor_block_id = data >> 12 & 0xFF;
/* 2927 */       int successor_metadata = data >> 20 & 0xF;
/*      */ 
/*      */ 
/*      */       
/* 2931 */       this.mc.effectRenderer.addBlockDestroyEffectsForReplace(packet.getBlockX(), packet.getBlockY(), packet.getBlockZ(), block_id, metadata, successor_block_id, successor_metadata);
/*      */     }
/* 2933 */     else if (kind == EnumBlockFX.item_consumed_by_lava) {
/*      */       
/* 2935 */       for (int i = 0; i < 10; i++) {
/* 2936 */         world.spawnParticle(EnumParticle.smoke, x + 0.25D + Math.random() * 0.5D, y + 0.75D + Math.random() * 0.5D, z + 0.25D + Math.random() * 0.5D, 0.0D, 0.0D, 0.0D);
/*      */       }
/* 2938 */       world.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F, false);
/*      */     }
/*      */     else {
/*      */       
/* 2942 */       Minecraft.setErrorMessage("handleBlockFX: no handler for " + kind);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleEntityFX(Packet85SimpleSignal packet) {
/* 2949 */     Entity entity = getEntityByID(packet.getEntityID());
/*      */     
/* 2951 */     if (entity == null) {
/*      */       return;
/*      */     }
/*      */     
/* 2955 */     EnumEntityFX kind = (EnumEntityFX)packet.signal_subtype;
/*      */     
/* 2957 */     WorldClient world = this.mc.theWorld;
/*      */     
/* 2959 */     double posX = entity.posX;
/* 2960 */     double posY = entity.posY;
/* 2961 */     double posZ = entity.posZ;
/*      */     
/* 2963 */     double foot_pos_y = posY;
/*      */     
/* 2965 */     if (entity instanceof EntityLivingBase) {
/*      */       
/* 2967 */       EntityLivingBase entity_living_base = (EntityLivingBase)entity;
/*      */       
/* 2969 */       foot_pos_y = entity_living_base.getFootPosY();
/*      */     } 
/*      */     
/* 2972 */     EntityPlayer entity_player = (entity instanceof EntityPlayer) ? (EntityPlayer)entity : null;
/*      */     
/* 2974 */     double center_pos_y = foot_pos_y + (entity.height / 2.0F);
/*      */     
/* 2976 */     if (kind == EnumEntityFX.steam_with_hiss) {
/*      */       
/* 2978 */       entity.spawnSteamParticles(entity.inWater ? 10 : 5);
/* 2979 */       world.playSound(posX, center_pos_y, posZ, "random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F, false);
/*      */     }
/* 2981 */     else if (kind == EnumEntityFX.single_steam_particle_with_hiss) {
/*      */       
/* 2983 */       entity.spawnSteamParticles(1);
/* 2984 */       world.playSound(posX, center_pos_y, posZ, "random.fizz", 0.4F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F, false);
/*      */     }
/* 2986 */     else if (kind == EnumEntityFX.summoned) {
/*      */       
/* 2988 */       entity.spawnSteamParticles(10);
/*      */     }
/* 2990 */     else if (kind == EnumEntityFX.burned_up_in_lava) {
/*      */       
/* 2992 */       entity.spawnSmokeParticles(10);
/* 2993 */       world.playSound(posX, center_pos_y, posZ, "random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F, false);
/*      */     }
/* 2995 */     else if (kind == EnumEntityFX.smoke) {
/*      */       
/* 2997 */       entity.spawnLargeSmokeParticles(10);
/*      */     }
/* 2999 */     else if (kind == EnumEntityFX.smoke_and_steam_with_hiss || kind == EnumEntityFX.smoke_and_steam) {
/*      */       
/* 3001 */       entity.spawnLargeSmokeParticles(6);
/* 3002 */       entity.spawnParticles(EnumParticle.explode, 6, 0.02F);
/*      */       
/* 3004 */       if (kind == EnumEntityFX.smoke_and_steam_with_hiss) {
/* 3005 */         world.playSound(posX, center_pos_y, posZ, "random.fizz", 0.5F, 2.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.8F, false);
/*      */       }
/* 3007 */     } else if (kind == EnumEntityFX.frags) {
/*      */       
/* 3009 */       entity.spawnFragParticles();
/*      */     }
/* 3011 */     else if (kind == EnumEntityFX.curse_effect_learned) {
/*      */       
/* 3013 */       entity.spawnCurseEffectLearnedParticles((entity == this.mc.thePlayer && this.mc.gameSettings.thirdPersonView == 0) ? 20 : 10);
/*      */     }
/* 3015 */     else if (kind == EnumEntityFX.item_breaking) {
/*      */       
/* 3017 */       Item item = Item.getItem(packet.getShort());
/* 3018 */       renderBrokenItem((EntityLivingBase)entity, item);
/*      */ 
/*      */ 
/*      */       
/* 3022 */       if (item instanceof ItemRock || item instanceof ItemDye) {
/* 3023 */         world.playSound(posX, center_pos_y, posZ, "random.glass", 0.25F, 0.8F + world.rand.nextFloat() * 0.4F, false);
/*      */       } else {
/* 3025 */         world.playSound(posX, center_pos_y, posZ, "random.break", 0.8F, 0.8F + world.rand.nextFloat() * 0.4F, false);
/*      */       } 
/* 3027 */       if (entity == this.mc.thePlayer)
/*      */       {
/* 3029 */         int inventory_slot_index = packet.getByte();
/*      */         
/* 3031 */         if (inventory_slot_index == this.mc.thePlayer.inventory.currentItem)
/*      */         {
/* 3033 */           if (item instanceof ItemFishingRod)
/*      */           {
/* 3035 */             this.mc.thePlayer.inventory.decrementSlotStackSize(inventory_slot_index);
/*      */           }
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 3041 */     else if (kind == EnumEntityFX.splash) {
/*      */       
/* 3043 */       entity.spawnSplashParticles();
/*      */       
/* 3045 */       float volume = MathHelper.sqrt_double(entity.motionX * entity.motionX * 0.20000000298023224D + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ * 0.20000000298023224D) * 0.2F;
/*      */       
/* 3047 */       if (volume > 1.0F) {
/* 3048 */         volume = 1.0F;
/*      */       }
/* 3050 */       if (entity instanceof EntityItem) {
/* 3051 */         volume *= 0.5F;
/* 3052 */       } else if (entity instanceof EntityFallingSand) {
/* 3053 */         volume *= 2.0F;
/*      */       } 
/* 3055 */       float pitch = 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F;
/*      */       
/* 3057 */       world.playSound(posX, center_pos_y, posZ, (entity instanceof EntityFallingSand) ? "imported.liquid.block_splash" : "liquid.splash", volume, pitch, false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 3063 */     else if (kind == EnumEntityFX.heal) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3069 */       entity.spawnParticle(EnumParticle.heart, 0.0F);
/*      */     }
/* 3071 */     else if (kind == EnumEntityFX.vampiric_gain) {
/*      */       
/* 3073 */       entity.spawnParticle(EnumParticle.vampiric_gain, 0.0F);
/*      */     }
/* 3075 */     else if (kind == EnumEntityFX.repair) {
/*      */       
/* 3077 */       int num_particles = Math.round(entity.height * 8.0F);
/*      */       
/* 3079 */       for (int i = 0; i < num_particles; i++) {
/* 3080 */         entity.spawnRandomlyLocatedParticle(EnumParticle.repair, 0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
/*      */       }
/* 3082 */     } else if (kind == EnumEntityFX.item_vanish) {
/*      */       
/* 3084 */       entity.spawnSteamParticles(5);
/*      */     }
/* 3086 */     else if (kind == EnumEntityFX.crafting) {
/*      */       
/* 3088 */       Vec3 var4 = world.getWorldVec3Pool().getVecFromPool((this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 3089 */       var4.rotateAroundX(-entity.rotationPitch * 3.1415927F / 180.0F);
/* 3090 */       var4.rotateAroundY(-entity.rotationYaw * 3.1415927F / 180.0F);
/* 3091 */       Vec3 var5 = world.getWorldVec3Pool().getVecFromPool((this.rand.nextFloat() - 0.5D) * 0.3D, -this.rand.nextFloat() * 0.6D - 0.3D, 0.6D);
/* 3092 */       var5.rotateAroundX(-entity.rotationPitch * 3.1415927F / 180.0F);
/* 3093 */       var5.rotateAroundY(-entity.rotationYaw * 3.1415927F / 180.0F);
/* 3094 */       var5 = var5.addVector(entity.posX, ((EntityLivingBase)entity).getEyePosY(), entity.posZ);
/* 3095 */       world.spawnParticleEx(EnumParticle.crafting, packet.getShort(), 0, var5.xCoord, var5.yCoord, var5.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord);
/*      */     }
/*      */     else {
/*      */       
/* 3099 */       Minecraft.setErrorMessage("handleEntityFX: no handler for " + kind);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writePrizeKeyFile(String username, int key_code) {
/*      */     try {
/* 3107 */       FileWriter fw = new FileWriter("MITE/prize_key.txt");
/*      */       
/* 3109 */       StringBuffer sb = new StringBuffer();
/*      */       
/* 3111 */       sb.append("Tournament Prize Key\n");
/* 3112 */       sb.append("--------------------\n");
/*      */       
/* 3114 */       sb.append("DISCLAIMER: Possession of this prize key does not in itself mean anything. Prizes are awarded solely at the discretion of the organizer of the tournament and are subject to change without notice. All participants agree to have no expectation of a prize.\n\n");
/*      */       
/* 3116 */       sb.append("If you have recently completed a tournament then the winner should be announced shortly at:\n\n");
/*      */       
/* 3118 */       sb.append("www.Minecraft-Is-Too-Easy.com/tournament\n\n");
/*      */       
/* 3120 */       sb.append("If you are the announced winner then claim your prize by private messaging user Avernite on the Minecraft Forums:\n\n");
/*      */       
/* 3122 */       sb.append("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1294284-minecraft-is-too-easy-mite-mod\n\n");
/*      */       
/* 3124 */       sb.append("Your private message to Avernite should contain a copy-paste of the four fields below (some need filling in):\n\n");
/*      */       
/* 3126 */       sb.append("Your full name in real life: ________\n");
/* 3127 */       sb.append("Your personal email address (this is the email address that will receive the prize): ________\n");
/* 3128 */       sb.append("Your in-game username (the one you played the tournament with): " + username + "\n");
/* 3129 */       sb.append("Your prize key code: " + key_code + "\n\n");
/*      */       
/* 3131 */       sb.append("Be careful not to share your prize key code with anyone except for Avernite (don't post it on the forums).");
/*      */       
/* 3133 */       fw.write(sb.toString());
/* 3134 */       fw.close();
/*      */     }
/* 3136 */     catch (Exception e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getHash(Class _class) {
/*      */     try {
/* 3143 */       MessageDigest md = MessageDigest.getInstance(em + dee + five);
/*      */       
/* 3145 */       md.reset();
/*      */ 
/*      */       
/* 3148 */       byte[] bytes = IOUtils.toByteArray(_class.getResourceAsStream(_class.getSimpleName() + ".class"));
/*      */       
/* 3150 */       md.update(bytes, 0, bytes.length);
/*      */       
/* 3152 */       return (new BigInteger(1, md.digest())).intValue();
/*      */     
/*      */     }
/* 3155 */     catch (Exception e) {
/*      */       
/* 3157 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getMasterHash(long world_seed) {
/* 3163 */     int master_hash = (int)world_seed + 1907276;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3168 */     master_hash += class_hash_sum;
/*      */     
/* 3170 */     return master_hash;
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendMasterHash(int SN) {
/* 3175 */     Minecraft.theMinecraft.thePlayer.sendPacket((new Packet85SimpleSignal(EnumSignal.mh)).setInteger(getMasterHash(SN)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3182 */   private static int class_hash_sum = 0;
/*      */   static {
/* 3184 */     for (int i = 0; i < classes.length; i++)
/*      */     {
/*      */       
/* 3187 */       class_hash_sum += getHash(classes[i]);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NetClientHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */