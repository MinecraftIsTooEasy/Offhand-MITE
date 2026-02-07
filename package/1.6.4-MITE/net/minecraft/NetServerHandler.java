/*      */ package net.minecraft;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import org.apache.commons.lang3.StringUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NetServerHandler
/*      */   extends NetHandler
/*      */ {
/*      */   public final INetworkManager netManager;
/*      */   private final MinecraftServer mcServer;
/*      */   public boolean connectionClosed;
/*      */   public ServerPlayer playerEntity;
/*      */   private int currentTicks;
/*      */   private int ticksForFloatKick;
/*      */   private boolean field_72584_h;
/*      */   private int keepAliveRandomID;
/*      */   private long keepAliveTimeSent;
/*   37 */   private static Random randomGenerator = new Random();
/*      */   
/*      */   private long ticksOfLastKeepAlive;
/*      */   
/*      */   private int chatSpamThresholdCount;
/*      */   
/*      */   private int creativeItemCreationSpamThresholdTally;
/*      */   
/*      */   private double lastPosX;
/*      */   
/*      */   private double lastPosY;
/*      */   
/*      */   private double lastPosZ;
/*      */   
/*      */   private boolean hasMoved = true;
/*      */   
/*   53 */   private IntHashMap field_72586_s = new IntHashMap();
/*      */ 
/*      */   
/*      */   public NetServerHandler(MinecraftServer par1MinecraftServer, INetworkManager par2INetworkManager, ServerPlayer par3EntityPlayerMP) {
/*   57 */     this.mcServer = par1MinecraftServer;
/*   58 */     this.netManager = par2INetworkManager;
/*   59 */     par2INetworkManager.setNetHandler(this);
/*   60 */     this.playerEntity = par3EntityPlayerMP;
/*   61 */     par3EntityPlayerMP.playerNetServerHandler = this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void networkTick() {
/*   69 */     this.field_72584_h = false;
/*   70 */     this.currentTicks++;
/*   71 */     this.mcServer.theProfiler.startSection("packetflow");
/*   72 */     this.netManager.processReadPackets();
/*   73 */     this.mcServer.theProfiler.endStartSection("keepAlive");
/*      */     
/*   75 */     if (this.currentTicks - this.ticksOfLastKeepAlive > 20L) {
/*      */       
/*   77 */       this.ticksOfLastKeepAlive = this.currentTicks;
/*   78 */       this.keepAliveTimeSent = System.nanoTime() / 1000000L;
/*   79 */       this.keepAliveRandomID = randomGenerator.nextInt();
/*   80 */       sendPacketToPlayer(new Packet0KeepAlive(this.keepAliveRandomID));
/*      */     } 
/*      */     
/*   83 */     if (this.chatSpamThresholdCount > 0)
/*      */     {
/*   85 */       this.chatSpamThresholdCount--;
/*      */     }
/*      */     
/*   88 */     if (this.creativeItemCreationSpamThresholdTally > 0)
/*      */     {
/*   90 */       this.creativeItemCreationSpamThresholdTally--;
/*      */     }
/*      */     
/*   93 */     this.mcServer.theProfiler.endStartSection("playerTick");
/*   94 */     this.mcServer.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public void kickPlayerFromServer(String par1Str) {
/*   99 */     if (!this.connectionClosed) {
/*      */       
/*  101 */       this.playerEntity.is_disconnecting_while_in_bed = this.playerEntity.inBed();
/*      */       
/*  103 */       this.playerEntity.mountEntityAndWakeUp();
/*      */ 
/*      */       
/*  106 */       sendPacketToPlayer(new Packet255KickDisconnect(par1Str, MinecraftServer.isPlayerHostingGame(this.playerEntity)));
/*  107 */       this.netManager.serverShutdown();
/*      */ 
/*      */       
/*  110 */       if (!this.playerEntity.isZevimrgvInTournament()) {
/*  111 */         this.mcServer.getConfigurationManager().sendChatMsg(ChatMessageComponent.createFromTranslationWithSubstitutions("multiplayer.player.left", new Object[] { this.playerEntity.getTranslatedEntityName() }).setColor(EnumChatFormatting.YELLOW));
/*      */       }
/*  113 */       this.mcServer.getConfigurationManager().playerLoggedOut(this.playerEntity);
/*  114 */       this.connectionClosed = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110774_a(Packet27PlayerInput par1Packet27PlayerInput) {
/*  120 */     this.playerEntity.setEntityActionState(par1Packet27PlayerInput.getMoveStrafing(), par1Packet27PlayerInput.getMoveForward(), par1Packet27PlayerInput.getJumping(), par1Packet27PlayerInput.getSneaking());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleFlying(Packet10Flying par1Packet10Flying) {
/*  131 */     WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
/*  132 */     this.field_72584_h = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  137 */     if (this.playerEntity.block_placement_world == var2 && this.playerEntity.block_placement_tick == var2.getTotalWorldTime())
/*      */     {
/*  139 */       this.playerEntity.setPosition(this.playerEntity.block_placement_pos_x, this.playerEntity.block_placement_pos_y, this.playerEntity.block_placement_pos_z);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  148 */     if (this.playerEntity.block_placement_world != var2 || var2.getTotalWorldTime() > this.playerEntity.block_placement_tick) {
/*  149 */       this.playerEntity.block_placement_world = null;
/*      */     }
/*  151 */     if (par1Packet10Flying instanceof Packet13PlayerLookMove && par1Packet10Flying.yPosition == -999.0D && par1Packet10Flying.stance == -999.0D) {
/*      */       
/*  153 */       this.playerEntity.last_received_motion_x = par1Packet10Flying.xPosition;
/*  154 */       this.playerEntity.last_received_motion_z = par1Packet10Flying.zPosition;
/*      */     }
/*  156 */     else if (par1Packet10Flying instanceof Packet11PlayerPosition || par1Packet10Flying instanceof Packet13PlayerLookMove) {
/*      */       
/*  158 */       this.playerEntity.last_received_motion_x = par1Packet10Flying.xPosition - this.playerEntity.posX;
/*  159 */       this.playerEntity.last_received_motion_z = par1Packet10Flying.zPosition - this.playerEntity.posZ;
/*      */     }
/*      */     else {
/*      */       
/*  163 */       this.playerEntity.last_received_motion_x = 0.0D;
/*  164 */       this.playerEntity.last_received_motion_z = 0.0D;
/*      */     } 
/*      */     
/*  167 */     if (!this.playerEntity.playerConqueredTheEnd) {
/*      */ 
/*      */ 
/*      */       
/*  171 */       if (!this.hasMoved) {
/*      */         
/*  173 */         double var3 = par1Packet10Flying.yPosition - this.lastPosY;
/*      */         
/*  175 */         if (par1Packet10Flying.xPosition == this.lastPosX && var3 * var3 < 0.01D && par1Packet10Flying.zPosition == this.lastPosZ)
/*      */         {
/*  177 */           this.hasMoved = true;
/*      */         }
/*      */       } 
/*      */       
/*  181 */       if (this.hasMoved) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  187 */         if (this.playerEntity.ridingEntity != null) {
/*      */           
/*  189 */           float var34 = this.playerEntity.rotationYaw;
/*  190 */           float var4 = this.playerEntity.rotationPitch;
/*  191 */           this.playerEntity.ridingEntity.updateRiderPosition();
/*  192 */           double d1 = this.playerEntity.posX;
/*  193 */           double d2 = this.playerEntity.posY;
/*  194 */           double d3 = this.playerEntity.posZ;
/*      */           
/*  196 */           if (par1Packet10Flying.rotating) {
/*      */             
/*  198 */             var34 = par1Packet10Flying.yaw;
/*  199 */             var4 = par1Packet10Flying.pitch;
/*      */           } 
/*      */           
/*  202 */           this.playerEntity.onGround = par1Packet10Flying.onGround;
/*  203 */           this.playerEntity.onUpdateEntity();
/*  204 */           this.playerEntity.ySize = 0.0F;
/*  205 */           this.playerEntity.setPositionAndRotation(d1, d2, d3, var34, var4);
/*      */           
/*  207 */           if (this.playerEntity.ridingEntity != null)
/*      */           {
/*  209 */             this.playerEntity.ridingEntity.updateRiderPosition();
/*      */           }
/*      */           
/*  212 */           this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
/*      */           
/*  214 */           if (this.hasMoved) {
/*      */             
/*  216 */             this.lastPosX = this.playerEntity.posX;
/*  217 */             this.lastPosY = this.playerEntity.posY;
/*  218 */             this.lastPosZ = this.playerEntity.posZ;
/*      */           } 
/*      */           
/*  221 */           var2.updateEntity(this.playerEntity);
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  226 */         if (this.playerEntity.inBed()) {
/*      */           
/*  228 */           this.playerEntity.onUpdateEntity();
/*  229 */           this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*  230 */           var2.updateEntity(this.playerEntity);
/*      */           
/*      */           return;
/*      */         } 
/*  234 */         double var3 = this.playerEntity.posY;
/*  235 */         this.lastPosX = this.playerEntity.posX;
/*  236 */         this.lastPosY = this.playerEntity.posY;
/*  237 */         this.lastPosZ = this.playerEntity.posZ;
/*  238 */         double var5 = this.playerEntity.posX;
/*  239 */         double var7 = this.playerEntity.posY;
/*  240 */         double var9 = this.playerEntity.posZ;
/*  241 */         float var11 = this.playerEntity.rotationYaw;
/*  242 */         float var12 = this.playerEntity.rotationPitch;
/*      */         
/*  244 */         if (par1Packet10Flying.moving && par1Packet10Flying.yPosition == -999.0D && par1Packet10Flying.stance == -999.0D)
/*      */         {
/*  246 */           par1Packet10Flying.moving = false;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  251 */         if (par1Packet10Flying.moving) {
/*      */           
/*  253 */           var5 = par1Packet10Flying.xPosition;
/*  254 */           var7 = par1Packet10Flying.yPosition;
/*  255 */           var9 = par1Packet10Flying.zPosition;
/*  256 */           double d = par1Packet10Flying.stance - par1Packet10Flying.yPosition;
/*      */ 
/*      */           
/*  259 */           if (!this.playerEntity.inBed() && (d > 1.65D || d < 0.1D)) {
/*      */             
/*  261 */             kickPlayerFromServer("Illegal stance");
/*  262 */             this.mcServer.getLogAgent().logWarning(this.playerEntity.getCommandSenderName() + " had an illegal stance: " + d);
/*      */             
/*      */             return;
/*      */           } 
/*  266 */           if (Math.abs(par1Packet10Flying.xPosition) > 3.2E7D || Math.abs(par1Packet10Flying.zPosition) > 3.2E7D) {
/*      */             
/*  268 */             kickPlayerFromServer("Illegal position");
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*  273 */         if (par1Packet10Flying.rotating) {
/*      */           
/*  275 */           var11 = par1Packet10Flying.yaw;
/*  276 */           var12 = par1Packet10Flying.pitch;
/*      */         } 
/*      */         
/*  279 */         this.playerEntity.onUpdateEntity();
/*  280 */         this.playerEntity.ySize = 0.0F;
/*  281 */         this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, var11, var12);
/*      */         
/*  283 */         if (!this.hasMoved) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  288 */         double var13 = var5 - this.playerEntity.posX;
/*  289 */         double var15 = var7 - this.playerEntity.posY;
/*  290 */         double var17 = var9 - this.playerEntity.posZ;
/*  291 */         double var19 = Math.min(Math.abs(var13), Math.abs(this.playerEntity.motionX));
/*  292 */         double var21 = Math.min(Math.abs(var15), Math.abs(this.playerEntity.motionY));
/*  293 */         double var23 = Math.min(Math.abs(var17), Math.abs(this.playerEntity.motionZ));
/*  294 */         double var25 = var19 * var19 + var21 * var21 + var23 * var23;
/*      */         
/*  296 */         if (var25 > 100.0D && (!this.mcServer.isSinglePlayer() || !this.mcServer.getServerOwner().equals(this.playerEntity.getCommandSenderName()))) {
/*      */           
/*  298 */           this.mcServer.getLogAgent().logWarning(this.playerEntity.getCommandSenderName() + " moved too quickly! " + var13 + "," + var15 + "," + var17 + " (" + var19 + ", " + var21 + ", " + var23 + ")");
/*  299 */           setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*      */           
/*      */           return;
/*      */         } 
/*  303 */         float var27 = 0.0625F;
/*  304 */         boolean var28 = var2.getCollidingBoundingBoxes(this.playerEntity, this.playerEntity.boundingBox.copy().contract(var27, var27, var27)).isEmpty();
/*      */         
/*  306 */         if (this.playerEntity.onGround && !par1Packet10Flying.onGround && var15 > 0.0D)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  311 */           this.playerEntity.addHungerServerSide(this.playerEntity.isSprinting() ? 0.8F : 0.2F);
/*      */         }
/*      */         
/*  314 */         this.playerEntity.moveEntity(var13, var15, var17);
/*  315 */         this.playerEntity.onGround = par1Packet10Flying.onGround;
/*  316 */         this.playerEntity.addMovementStat(var13, var15, var17);
/*  317 */         double var29 = var15;
/*  318 */         var13 = var5 - this.playerEntity.posX;
/*  319 */         var15 = var7 - this.playerEntity.posY;
/*      */         
/*  321 */         if (var15 > -0.5D || var15 < 0.5D)
/*      */         {
/*  323 */           var15 = 0.0D;
/*      */         }
/*      */         
/*  326 */         var17 = var9 - this.playerEntity.posZ;
/*  327 */         var25 = var13 * var13 + var15 * var15 + var17 * var17;
/*  328 */         boolean var31 = false;
/*      */ 
/*      */         
/*  331 */         if (var25 > 0.0625D && !this.playerEntity.inBed() && !this.playerEntity.theItemInWorldManager.isCreative()) {
/*      */           
/*  333 */           var31 = true;
/*  334 */           this.mcServer.getLogAgent().logWarning(this.playerEntity.getCommandSenderName() + " moved wrongly!");
/*      */         } 
/*      */         
/*  337 */         this.playerEntity.setPositionAndRotation(var5, var7, var9, var11, var12);
/*  338 */         boolean var32 = var2.getCollidingBoundingBoxes(this.playerEntity, this.playerEntity.boundingBox.copy().contract(var27, var27, var27)).isEmpty();
/*      */ 
/*      */         
/*  341 */         if (var28 && (var31 || !var32) && !this.playerEntity.inBed()) {
/*      */           
/*  343 */           setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, var11, var12);
/*      */           
/*      */           return;
/*      */         } 
/*  347 */         AxisAlignedBB var33 = this.playerEntity.boundingBox.copy().expand(var27, var27, var27).addCoord(0.0D, -0.55D, 0.0D);
/*      */ 
/*      */         
/*  350 */         if (!this.mcServer.isFlightAllowed() && !this.playerEntity.theItemInWorldManager.isCreative() && !this.playerEntity.isZevimrgvInTournament() && !var2.checkBlockCollision(var33)) {
/*      */           
/*  352 */           if (var29 >= -0.03125D) {
/*      */             
/*  354 */             this.ticksForFloatKick++;
/*      */             
/*  356 */             if (this.ticksForFloatKick > 80) {
/*      */               
/*  358 */               this.mcServer.getLogAgent().logWarning(this.playerEntity.getCommandSenderName() + " was kicked for floating too long!");
/*  359 */               kickPlayerFromServer("Flying is not enabled on this server");
/*      */ 
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } else {
/*  366 */           this.ticksForFloatKick = 0;
/*      */         } 
/*      */         
/*  369 */         this.playerEntity.onGround = par1Packet10Flying.onGround;
/*      */         
/*  371 */         this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
/*      */ 
/*      */ 
/*      */         
/*  375 */         this.playerEntity.updateFlyingState(this.playerEntity.posY - var3, par1Packet10Flying.onGround);
/*      */       }
/*  377 */       else if (this.currentTicks % 20 == 0) {
/*      */         
/*  379 */         setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerLocation(double par1, double par3, double par5, float par7, float par8) {
/*  389 */     this.hasMoved = false;
/*  390 */     this.lastPosX = par1;
/*  391 */     this.lastPosY = par3;
/*  392 */     this.lastPosZ = par5;
/*  393 */     this.playerEntity.setPositionAndRotation(par1, par3, par5, par7, par8);
/*  394 */     this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet13PlayerLookMove(par1, par3 + 1.6200000047683716D, par3, par5, par7, par8, false));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleDiggingPacket(Packet85SimpleSignal packet) {
/*  405 */     WorldServer world = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
/*  406 */     this.playerEntity.func_143004_u();
/*      */     
/*  408 */     int x = packet.getBlockX();
/*  409 */     int y = packet.getBlockY();
/*  410 */     int z = packet.getBlockZ();
/*      */     
/*  412 */     if (y >= this.mcServer.getBuildLimit()) {
/*      */       return;
/*      */     }
/*  415 */     EnumSignal signal_type = packet.signal_type;
/*      */     
/*  417 */     if (signal_type != EnumSignal.digging_block_cancel) {
/*      */       
/*  419 */       double dx = this.playerEntity.posX - x + 0.5D;
/*  420 */       double dy = this.playerEntity.posY + 1.5D - y + 0.5D;
/*  421 */       double dz = this.playerEntity.posZ - z + 0.5D;
/*      */       
/*  423 */       double distance_sq = dx * dx + dy * dy + dz * dz;
/*      */       
/*  425 */       if (distance_sq > 256.0D)
/*      */       {
/*  427 */         Minecraft.setErrorMessage("handleDiggingPacket: player is too far from target block (" + signal_type + ")");
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  434 */     if (signal_type == EnumSignal.digging_block_start) {
/*      */       
/*  436 */       if (!this.mcServer.isBlockProtected(world, x, y, z, this.playerEntity))
/*      */       {
/*  438 */         Block block = world.getBlock(x, y, z);
/*      */         
/*  440 */         if (block == Block.tnt)
/*      */         {
/*  442 */           if (Enchantment.fireAspect.getLevel(this.playerEntity.getHeldItemStack()) > 0 || (!this.playerEntity.hasHeldItem() && this.playerEntity.isBurning())) {
/*      */             
/*  444 */             BlockTNT.primeTnt(world, x, y, z, 1, this.playerEntity);
/*  445 */             world.setBlockToAir(x, y, z);
/*      */           } 
/*      */         }
/*      */         
/*  449 */         this.playerEntity.theItemInWorldManager.onBlockClicked(x, y, z, EnumFace.get(packet.getByte()));
/*      */       }
/*      */       else
/*      */       {
/*  453 */         this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(x, y, z, world));
/*      */       }
/*      */     
/*  456 */     } else if (signal_type == EnumSignal.digging_block_complete) {
/*      */ 
/*      */ 
/*      */       
/*  460 */       world.destroyBlockInWorldPartially(this.playerEntity.entityId, x, y, z, -1);
/*      */       
/*  462 */       if (world.getBlock(x, y, z) != null) {
/*  463 */         this.playerEntity.theItemInWorldManager.tryHarvestBlock(x, y, z);
/*      */       }
/*  465 */       if (world.getBlockId(x, y, z) != 0) {
/*  466 */         this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(x, y, z, world));
/*      */       }
/*  468 */     } else if (signal_type == EnumSignal.digging_block_cancel) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  473 */       world.destroyBlockInWorldPartially(this.playerEntity.entityId, x, y, z, -1);
/*      */       
/*  475 */       if (world.getBlockId(x, y, z) != 0) {
/*  476 */         this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(x, y, z, world));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void XXXhandlePlace(Packet15Place par1Packet15Place) {
/*  579 */     WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
/*  580 */     ItemStack var3 = this.playerEntity.inventory.getCurrentItemStack();
/*  581 */     boolean var4 = false;
/*  582 */     int var5 = par1Packet15Place.getXPosition();
/*  583 */     int var6 = par1Packet15Place.getYPosition();
/*  584 */     int var7 = par1Packet15Place.getZPosition();
/*      */     
/*  586 */     EnumFace face = par1Packet15Place.getFace();
/*  587 */     this.playerEntity.func_143004_u();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  599 */     if (face == null) {
/*      */       
/*  601 */       Minecraft.setErrorMessage("handlePlace: face is null");
/*      */       
/*      */       return;
/*      */     } 
/*  605 */     if (par1Packet15Place.getYPosition() >= this.mcServer.getBuildLimit() - 1 && (face.isTop() || par1Packet15Place.getYPosition() >= this.mcServer.getBuildLimit())) {
/*      */       
/*  607 */       this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromTranslationWithSubstitutions("build.tooHigh", new Object[] { Integer.valueOf(this.mcServer.getBuildLimit()) }).setColor(EnumChatFormatting.RED)));
/*  608 */       var4 = true;
/*      */     }
/*      */     else {
/*      */       
/*  612 */       if (!this.hasMoved || this.playerEntity.getDistanceSq(var5 + 0.5D, var6 + 0.5D, var7 + 0.5D) >= 64.0D || !this.mcServer.isBlockProtected(var2, var5, var6, var7, this.playerEntity));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  619 */       var4 = true;
/*      */     } 
/*      */     
/*  622 */     if (var4) {
/*      */       
/*  624 */       this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var5, var6, var7, var2));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  656 */       var5 = face.getNeighborX(var5);
/*  657 */       var6 = face.getNeighborY(var6);
/*  658 */       var7 = face.getNeighborZ(var7);
/*      */       
/*  660 */       this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(var5, var6, var7, var2));
/*      */     } 
/*      */     
/*  663 */     var3 = this.playerEntity.inventory.getCurrentItemStack();
/*      */     
/*  665 */     if (var3 != null && var3.stackSize == 0) {
/*      */       
/*  667 */       this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = null;
/*  668 */       var3 = null;
/*      */     } 
/*      */     
/*  671 */     if (var3 == null || var3.getMaxItemUseDuration() == 0) {
/*      */       
/*  673 */       this.playerEntity.playerInventoryBeingManipulated = true;
/*  674 */       this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = ItemStack.copyItemStack(this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem]);
/*  675 */       Slot var9 = this.playerEntity.openContainer.getSlotFromInventory(this.playerEntity.inventory, this.playerEntity.inventory.currentItem);
/*  676 */       this.playerEntity.openContainer.detectAndSendChanges();
/*  677 */       this.playerEntity.playerInventoryBeingManipulated = false;
/*      */       
/*  679 */       if (!ItemStack.areItemStacksEqual(this.playerEntity.inventory.getCurrentItemStack(), par1Packet15Place.getItemStack()))
/*      */       {
/*  681 */         sendPacketToPlayer(new Packet103SetSlot(this.playerEntity.openContainer.windowId, var9.slotNumber, this.playerEntity.inventory.getCurrentItemStack()));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleErrorMessage(String par1Str, Object[] par2ArrayOfObj) {
/*  688 */     this.mcServer.getLogAgent().logInfo(this.playerEntity.getCommandSenderName() + " lost connection: " + par1Str);
/*      */ 
/*      */     
/*  691 */     if (!this.playerEntity.isZevimrgvInTournament()) {
/*  692 */       this.mcServer.getConfigurationManager().sendChatMsg(ChatMessageComponent.createFromTranslationWithSubstitutions("multiplayer.player.left", new Object[] { this.playerEntity.getTranslatedEntityName() }).setColor(EnumChatFormatting.YELLOW));
/*      */     }
/*  694 */     this.playerEntity.is_disconnecting_while_in_bed = this.playerEntity.inBed();
/*      */     
/*  696 */     this.mcServer.getConfigurationManager().playerLoggedOut(this.playerEntity);
/*  697 */     this.connectionClosed = true;
/*      */     
/*  699 */     if (this.mcServer.isSinglePlayer() && this.playerEntity.getCommandSenderName().equals(this.mcServer.getServerOwner())) {
/*      */       
/*  701 */       this.mcServer.getLogAgent().logInfo("Stopping singleplayer server as player logged out");
/*  702 */       this.mcServer.initiateShutdown();
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
/*      */   public void unexpectedPacket(Packet par1Packet) {
/*  727 */     this.mcServer.getLogAgent().logWarning(getClass() + " wasn't prepared to deal with a " + par1Packet.getClass());
/*  728 */     kickPlayerFromServer("Protocol error, unexpected packet");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacketToPlayer(Packet par1Packet) {
/*  736 */     if (par1Packet instanceof Packet3Chat) {
/*      */       
/*  738 */       Packet3Chat var2 = (Packet3Chat)par1Packet;
/*  739 */       int var3 = this.playerEntity.getChatVisibility();
/*      */       
/*  741 */       if (var3 == 2) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  746 */       if (var3 == 1 && !var2.getIsServer()) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  754 */       this.netManager.addToSendQueue(par1Packet);
/*      */     }
/*  756 */     catch (Throwable var5) {
/*      */       
/*  758 */       CrashReport var6 = CrashReport.makeCrashReport(var5, "Sending packet");
/*  759 */       CrashReportCategory var4 = var6.makeCategory("Packet being sent");
/*  760 */       var4.addCrashSectionCallable("Packet ID", new CallablePacketID(this, par1Packet));
/*  761 */       var4.addCrashSectionCallable("Packet class", new CallablePacketClass(this, par1Packet));
/*  762 */       throw new ReportedException(var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleBlockItemSwitch(Packet16BlockItemSwitch par1Packet16BlockItemSwitch) {
/*  768 */     if (par1Packet16BlockItemSwitch.id >= 0 && par1Packet16BlockItemSwitch.id < InventoryPlayer.getHotbarSize()) {
/*      */       
/*  770 */       this.playerEntity.inventory.currentItem = par1Packet16BlockItemSwitch.id;
/*  771 */       this.playerEntity.func_143004_u();
/*      */     }
/*      */     else {
/*      */       
/*  775 */       this.mcServer.getLogAgent().logWarning(this.playerEntity.getCommandSenderName() + " tried to set an invalid carried item");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleChat(Packet3Chat par1Packet3Chat) {
/*  781 */     if (this.playerEntity.getChatVisibility() == 2) {
/*      */       
/*  783 */       sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromTranslationKey("chat.cannotSend").setColor(EnumChatFormatting.RED)));
/*      */     }
/*      */     else {
/*      */       
/*  787 */       this.playerEntity.func_143004_u();
/*  788 */       String var2 = par1Packet3Chat.message;
/*      */       
/*  790 */       if (var2.length() > 100) {
/*      */         
/*  792 */         kickPlayerFromServer("Chat message too long");
/*      */       }
/*      */       else {
/*      */         
/*  796 */         var2 = StringUtils.normalizeSpace(var2);
/*      */         
/*  798 */         for (int var3 = 0; var3 < var2.length(); var3++) {
/*      */           
/*  800 */           if (!ChatAllowedCharacters.isAllowedCharacter(var2.charAt(var3))) {
/*      */             
/*  802 */             kickPlayerFromServer("Illegal characters in chat");
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*  807 */         if (var2.startsWith("/")) {
/*      */ 
/*      */           
/*  810 */           handleSlashCommand(var2, par1Packet3Chat.permission_override);
/*      */         }
/*      */         else {
/*      */           
/*  814 */           if (this.playerEntity.getChatVisibility() == 1) {
/*      */             
/*  816 */             sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromTranslationKey("chat.cannotSend").setColor(EnumChatFormatting.RED)));
/*      */             
/*      */             return;
/*      */           } 
/*  820 */           ChatMessageComponent var4 = ChatMessageComponent.createFromTranslationWithSubstitutions("chat.type.text", new Object[] { this.playerEntity.getTranslatedEntityName(), var2 });
/*  821 */           this.mcServer.getConfigurationManager().func_110459_a(var4, false);
/*      */         } 
/*      */         
/*  824 */         this.chatSpamThresholdCount += 20;
/*      */         
/*  826 */         if (this.chatSpamThresholdCount > 200 && !this.mcServer.getConfigurationManager().isPlayerOpped(this.playerEntity.getCommandSenderName()))
/*      */         {
/*      */ 
/*      */           
/*  830 */           if (MinecraftServer.getServer() instanceof DedicatedServer) {
/*  831 */             kickPlayerFromServer("disconnect.spam");
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void handlePlayerInventory(Packet5PlayerInventory packet) {
/*  839 */     this.playerEntity.inventory.setInventorySlotContents(packet.slot, packet.getItemSlot());
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
/*      */   private void handleSlashCommand(String par1Str, boolean permission_override) {
/*  852 */     this.mcServer.getCommandManager().executeCommand(this.playerEntity, par1Str, permission_override);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleAnimation(Packet18Animation par1Packet18Animation) {
/*  857 */     this.playerEntity.func_143004_u();
/*      */     
/*  859 */     if (par1Packet18Animation.animate == 1)
/*      */     {
/*  861 */       this.playerEntity.swingArm();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityAction(Packet19EntityAction par1Packet19EntityAction) {
/*  870 */     this.playerEntity.func_143004_u();
/*      */     
/*  872 */     if (par1Packet19EntityAction.action == 1) {
/*      */       
/*  874 */       this.playerEntity.setSneaking(true);
/*      */     }
/*  876 */     else if (par1Packet19EntityAction.action == 2) {
/*      */       
/*  878 */       this.playerEntity.setSneaking(false);
/*      */     }
/*  880 */     else if (par1Packet19EntityAction.action == 4) {
/*      */       
/*  882 */       this.playerEntity.setSprinting(true);
/*      */     }
/*  884 */     else if (par1Packet19EntityAction.action == 5) {
/*      */       
/*  886 */       this.playerEntity.setSprinting(false);
/*      */     }
/*  888 */     else if (par1Packet19EntityAction.action == 3) {
/*      */ 
/*      */       
/*  891 */       this.playerEntity.wakeUpPlayer(true, (Entity)null);
/*  892 */       this.hasMoved = false;
/*      */     }
/*  894 */     else if (par1Packet19EntityAction.action == 6) {
/*      */       
/*  896 */       if (this.playerEntity.ridingEntity != null && this.playerEntity.ridingEntity instanceof EntityHorse)
/*      */       {
/*  898 */         ((EntityHorse)this.playerEntity.ridingEntity).setJumpPower(par1Packet19EntityAction.auxData);
/*      */       }
/*      */     }
/*  901 */     else if (par1Packet19EntityAction.action == 7 && this.playerEntity.ridingEntity != null && this.playerEntity.ridingEntity instanceof EntityHorse) {
/*      */       
/*  903 */       ((EntityHorse)this.playerEntity.ridingEntity).tryOpenGUI(this.playerEntity);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleKickDisconnect(Packet255KickDisconnect par1Packet255KickDisconnect) {
/*  909 */     this.netManager.networkShutdown("disconnect.quitting", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int packetSize() {
/*  917 */     return this.netManager.packetSize();
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
/*      */   public void handleBlockDestroy(Packet55BlockDestroy par1Packet55BlockDestroy) {
/*  963 */     int x = par1Packet55BlockDestroy.getPosX();
/*  964 */     int y = par1Packet55BlockDestroy.getPosY();
/*  965 */     int z = par1Packet55BlockDestroy.getPosZ();
/*      */     
/*  967 */     Block block = this.playerEntity.worldObj.getBlock(x, y, z);
/*      */     
/*  969 */     if (block != null) {
/*  970 */       block.onBlockDamageStageChange(x, y, z, this.playerEntity.worldObj.getEntityByID(par1Packet55BlockDestroy.getEntityId()), par1Packet55BlockDestroy.getDestroyedStage());
/*      */     }
/*  972 */     this.playerEntity.worldObj.destroyBlockInWorldPartially(par1Packet55BlockDestroy.getEntityId(), par1Packet55BlockDestroy.getPosX(), par1Packet55BlockDestroy.getPosY(), par1Packet55BlockDestroy.getPosZ(), par1Packet55BlockDestroy.getDestroyedStage());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleClientCommand(Packet205ClientCommand par1Packet205ClientCommand) {
/*  977 */     this.playerEntity.func_143004_u();
/*      */     
/*  979 */     if (par1Packet205ClientCommand.forceRespawn == 1)
/*      */     {
/*  981 */       if (this.playerEntity.playerConqueredTheEnd) {
/*      */         
/*  983 */         this.playerEntity = this.mcServer.getConfigurationManager().respawnPlayer(this.playerEntity, 0, true);
/*      */       }
/*  985 */       else if (this.playerEntity.getServerForPlayer().getWorldInfo().isHardcoreModeEnabled()) {
/*      */         
/*  987 */         if (this.mcServer.isSinglePlayer() && this.playerEntity.getCommandSenderName().equals(this.mcServer.getServerOwner()))
/*      */         {
/*  989 */           this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
/*  990 */           this.mcServer.deleteWorldAndStopServer();
/*      */         }
/*      */         else
/*      */         {
/*  994 */           BanEntry var2 = new BanEntry(this.playerEntity.getCommandSenderName());
/*  995 */           var2.setBanReason("Death in Hardcore");
/*  996 */           this.mcServer.getConfigurationManager().getBannedPlayers().put(var2);
/*  997 */           this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1002 */         if (this.playerEntity.getHealth() > 0.0F) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1007 */         this.playerEntity = this.mcServer.getConfigurationManager().respawnPlayer(this.playerEntity, 0, false);
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
/* 1019 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleRespawn(Packet9Respawn par1Packet9Respawn) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleCloseWindow(Packet101CloseWindow par1Packet101CloseWindow) {
/* 1029 */     this.playerEntity.closeContainer();
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleWindowClick(Packet102WindowClick par1Packet102WindowClick) {
/* 1034 */     this.playerEntity.func_143004_u();
/*      */     
/* 1036 */     if (this.playerEntity.openContainer.windowId == par1Packet102WindowClick.window_Id && this.playerEntity.openContainer.isPlayerNotUsingContainer(this.playerEntity)) {
/*      */ 
/*      */       
/* 1039 */       ItemStack var2 = this.playerEntity.openContainer.slotClick(par1Packet102WindowClick.inventorySlot, par1Packet102WindowClick.mouseClick, par1Packet102WindowClick.holdingShift, par1Packet102WindowClick.holding_shift, this.playerEntity);
/*      */       
/* 1041 */       if (ItemStack.areItemStacksEqual(par1Packet102WindowClick.itemStack, var2)) {
/*      */         
/* 1043 */         this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet106Transaction(par1Packet102WindowClick.window_Id, par1Packet102WindowClick.action, true));
/* 1044 */         this.playerEntity.playerInventoryBeingManipulated = true;
/* 1045 */         this.playerEntity.openContainer.detectAndSendChanges();
/* 1046 */         this.playerEntity.updateHeldItem();
/* 1047 */         this.playerEntity.playerInventoryBeingManipulated = false;
/*      */       }
/*      */       else {
/*      */         
/* 1051 */         this.field_72586_s.addKey(this.playerEntity.openContainer.windowId, Short.valueOf(par1Packet102WindowClick.action));
/* 1052 */         this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet106Transaction(par1Packet102WindowClick.window_Id, par1Packet102WindowClick.action, false));
/* 1053 */         this.playerEntity.openContainer.setPlayerIsPresent(this.playerEntity, false);
/* 1054 */         ArrayList<ItemStack> var3 = new ArrayList();
/*      */         
/* 1056 */         for (int var4 = 0; var4 < this.playerEntity.openContainer.inventorySlots.size(); var4++)
/*      */         {
/* 1058 */           var3.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(var4)).getStack());
/*      */         }
/*      */ 
/*      */         
/* 1062 */         this.playerEntity.sendContainerAndContentsToPlayer(this.playerEntity.openContainer, var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEnchantItem(Packet108EnchantItem par1Packet108EnchantItem) {
/* 1069 */     this.playerEntity.func_143004_u();
/*      */     
/* 1071 */     if (this.playerEntity.openContainer.windowId == par1Packet108EnchantItem.windowId && this.playerEntity.openContainer.isPlayerNotUsingContainer(this.playerEntity)) {
/*      */       
/* 1073 */       this.playerEntity.openContainer.enchantItem(this.playerEntity, par1Packet108EnchantItem.enchantment);
/* 1074 */       this.playerEntity.openContainer.detectAndSendChanges();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleCreativeSetSlot(Packet107CreativeSetSlot par1Packet107CreativeSetSlot) {
/* 1083 */     if (this.playerEntity.theItemInWorldManager.isCreative()) {
/*      */       
/* 1085 */       boolean var2 = (par1Packet107CreativeSetSlot.slot < 0);
/* 1086 */       ItemStack var3 = par1Packet107CreativeSetSlot.itemStack;
/* 1087 */       boolean var4 = (par1Packet107CreativeSetSlot.slot >= 1 && par1Packet107CreativeSetSlot.slot < 36 + InventoryPlayer.getHotbarSize());
/* 1088 */       boolean var5 = (var3 == null || (var3.itemID < Item.itemsList.length && var3.itemID >= 0 && Item.itemsList[var3.itemID] != null));
/*      */       
/* 1090 */       boolean var6 = (var3 == null || (var3.getItemSubtype() >= 0 && var3.getItemDamage() >= 0 && var3.stackSize <= 64 && var3.stackSize > 0));
/*      */       
/* 1092 */       if (var4 && var5 && var6) {
/*      */         
/* 1094 */         if (var3 == null) {
/*      */           
/* 1096 */           this.playerEntity.inventoryContainer.putStackInSlot(par1Packet107CreativeSetSlot.slot, (ItemStack)null);
/*      */         }
/*      */         else {
/*      */           
/* 1100 */           this.playerEntity.inventoryContainer.putStackInSlot(par1Packet107CreativeSetSlot.slot, var3);
/*      */         } 
/*      */         
/* 1103 */         this.playerEntity.inventoryContainer.setPlayerIsPresent(this.playerEntity, true);
/*      */       }
/* 1105 */       else if (var2 && var5 && var6 && this.creativeItemCreationSpamThresholdTally < 200) {
/*      */         
/* 1107 */         this.creativeItemCreationSpamThresholdTally += 20;
/* 1108 */         EntityItem var7 = this.playerEntity.dropPlayerItem(var3);
/*      */         
/* 1110 */         if (var7 != null)
/*      */         {
/* 1112 */           var7.setAgeToCreativeDespawnTime();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleTransaction(Packet106Transaction par1Packet106Transaction) {
/* 1120 */     Short var2 = (Short)this.field_72586_s.lookup(this.playerEntity.openContainer.windowId);
/*      */     
/* 1122 */     if (var2 != null && par1Packet106Transaction.shortWindowId == var2.shortValue() && this.playerEntity.openContainer.windowId == par1Packet106Transaction.windowId && !this.playerEntity.openContainer.isPlayerNotUsingContainer(this.playerEntity))
/*      */     {
/* 1124 */       this.playerEntity.openContainer.setPlayerIsPresent(this.playerEntity, true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateSign(Packet130UpdateSign par1Packet130UpdateSign) {
/* 1133 */     this.playerEntity.func_143004_u();
/* 1134 */     WorldServer var2 = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
/*      */     
/* 1136 */     if (var2.blockExists(par1Packet130UpdateSign.xPosition, par1Packet130UpdateSign.yPosition, par1Packet130UpdateSign.zPosition)) {
/*      */       
/* 1138 */       TileEntity var3 = var2.getBlockTileEntity(par1Packet130UpdateSign.xPosition, par1Packet130UpdateSign.yPosition, par1Packet130UpdateSign.zPosition);
/*      */       
/* 1140 */       if (var3 instanceof TileEntitySign) {
/*      */         
/* 1142 */         TileEntitySign var4 = (TileEntitySign)var3;
/*      */         
/* 1144 */         if (!var4.isEditable() || var4.func_142009_b() != this.playerEntity) {
/*      */           
/* 1146 */           this.mcServer.logWarning("Player " + this.playerEntity.getCommandSenderName() + " just tried to change non-editable sign");
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       
/*      */       int var8;
/*      */       
/* 1154 */       for (var8 = 0; var8 < 4; var8++) {
/*      */         
/* 1156 */         boolean var5 = true;
/*      */         
/* 1158 */         if (par1Packet130UpdateSign.signLines[var8].length() > 15) {
/*      */           
/* 1160 */           var5 = false;
/*      */         }
/*      */         else {
/*      */           
/* 1164 */           for (int var6 = 0; var6 < par1Packet130UpdateSign.signLines[var8].length(); var6++) {
/*      */             
/* 1166 */             if (ChatAllowedCharacters.allowedCharacters.indexOf(par1Packet130UpdateSign.signLines[var8].charAt(var6)) < 0)
/*      */             {
/* 1168 */               var5 = false;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 1173 */         if (!var5)
/*      */         {
/* 1175 */           par1Packet130UpdateSign.signLines[var8] = "!?";
/*      */         }
/*      */       } 
/*      */       
/* 1179 */       if (var3 instanceof TileEntitySign) {
/*      */         
/* 1181 */         var8 = par1Packet130UpdateSign.xPosition;
/* 1182 */         int var9 = par1Packet130UpdateSign.yPosition;
/* 1183 */         int var6 = par1Packet130UpdateSign.zPosition;
/* 1184 */         TileEntitySign var7 = (TileEntitySign)var3;
/* 1185 */         System.arraycopy(par1Packet130UpdateSign.signLines, 0, var7.signText, 0, 4);
/* 1186 */         var7.onInventoryChanged();
/* 1187 */         var2.markBlockForUpdate(var8, var9, var6);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleKeepAlive(Packet0KeepAlive par1Packet0KeepAlive) {
/* 1197 */     if (par1Packet0KeepAlive.randomId == this.keepAliveRandomID) {
/*      */       
/* 1199 */       int var2 = (int)(System.nanoTime() / 1000000L - this.keepAliveTimeSent);
/* 1200 */       this.playerEntity.ping = (this.playerEntity.ping * 3 + var2) / 4;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isServerHandler() {
/* 1209 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handlePlayerAbilities(Packet202PlayerAbilities par1Packet202PlayerAbilities) {
/* 1217 */     this.playerEntity.capabilities.isFlying = (par1Packet202PlayerAbilities.getFlying() && this.playerEntity.capabilities.allowFlying);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleAutoComplete(Packet203AutoComplete par1Packet203AutoComplete) {
/* 1222 */     StringBuilder var2 = new StringBuilder();
/*      */ 
/*      */     
/* 1225 */     for (Iterator<String> var3 = this.mcServer.getPossibleCompletions(this.playerEntity, par1Packet203AutoComplete.getText()).iterator(); var3.hasNext(); var2.append(var4)) {
/*      */       
/* 1227 */       String var4 = var3.next();
/*      */       
/* 1229 */       if (var2.length() > 0)
/*      */       {
/* 1231 */         var2.append("\000");
/*      */       }
/*      */     } 
/*      */     
/* 1235 */     this.playerEntity.playerNetServerHandler.sendPacketToPlayer(new Packet203AutoComplete(var2.toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleClientInfo(Packet204ClientInfo par1Packet204ClientInfo) {
/* 1240 */     this.playerEntity.updateClientInfo(par1Packet204ClientInfo);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleCustomPayload(Packet250CustomPayload par1Packet250CustomPayload) {
/* 1249 */     if ("MC|BEdit".equals(par1Packet250CustomPayload.channel)) {
/*      */       
/*      */       try
/*      */       {
/* 1253 */         DataInputStream var2 = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
/* 1254 */         ItemStack var3 = Packet.readItemStack(var2);
/*      */         
/* 1256 */         if (!ItemWritableBook.validBookTagPages(var3.getTagCompound()))
/*      */         {
/* 1258 */           throw new IOException("Invalid book tag!");
/*      */         }
/*      */         
/* 1261 */         ItemStack var4 = this.playerEntity.inventory.getCurrentItemStack();
/*      */         
/* 1263 */         if (var3 != null && var3.itemID == Item.writableBook.itemID && var3.itemID == var4.itemID)
/*      */         {
/* 1265 */           var4.setTagInfo("pages", var3.getTagCompound().getTagList("pages"));
/*      */         }
/*      */       }
/* 1268 */       catch (Exception var12)
/*      */       {
/* 1270 */         var12.printStackTrace();
/*      */       }
/*      */     
/* 1273 */     } else if ("MC|BSign".equals(par1Packet250CustomPayload.channel)) {
/*      */ 
/*      */       
/*      */       try {
/* 1277 */         DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
/* 1278 */         ItemStack itemStack1 = Packet.readItemStack(dataInputStream);
/*      */         
/* 1280 */         if (!ItemEditableBook.validBookTagContents(itemStack1.getTagCompound()))
/*      */         {
/* 1282 */           throw new IOException("Invalid book tag!");
/*      */         }
/*      */         
/* 1285 */         ItemStack itemStack2 = this.playerEntity.inventory.getCurrentItemStack();
/*      */         
/* 1287 */         if (itemStack1 != null && itemStack1.itemID == Item.writtenBook.itemID && itemStack2.itemID == Item.writableBook.itemID)
/*      */         {
/* 1289 */           itemStack2.setTagInfo("author", new NBTTagString("author", this.playerEntity.getCommandSenderName()));
/* 1290 */           itemStack2.setTagInfo("title", new NBTTagString("title", itemStack1.getTagCompound().getString("title")));
/* 1291 */           itemStack2.setTagInfo("pages", itemStack1.getTagCompound().getTagList("pages"));
/* 1292 */           itemStack2.itemID = Item.writtenBook.itemID;
/*      */         }
/*      */       
/* 1295 */       } catch (Exception var11) {
/*      */         
/* 1297 */         var11.printStackTrace();
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1304 */     else if ("MC|TrSel".equals(par1Packet250CustomPayload.channel)) {
/*      */       
/*      */       try
/*      */       {
/* 1308 */         DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
/* 1309 */         int var14 = dataInputStream.readInt();
/* 1310 */         Container var16 = this.playerEntity.openContainer;
/*      */         
/* 1312 */         if (var16 instanceof ContainerMerchant)
/*      */         {
/* 1314 */           ((ContainerMerchant)var16).setCurrentRecipeIndex(var14);
/*      */         }
/*      */       }
/* 1317 */       catch (Exception var10)
/*      */       {
/* 1319 */         var10.printStackTrace();
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1326 */     else if ("MC|AdvCdm".equals(par1Packet250CustomPayload.channel)) {
/*      */       
/* 1328 */       if (!this.mcServer.isCommandBlockEnabled()) {
/*      */         
/* 1330 */         this.playerEntity.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("advMode.notEnabled"));
/*      */       }
/* 1332 */       else if (this.playerEntity.canCommandSenderUseCommand(2, "") && this.playerEntity.capabilities.isCreativeMode) {
/*      */ 
/*      */         
/*      */         try {
/* 1336 */           DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
/* 1337 */           int i = dataInputStream.readInt();
/* 1338 */           int var18 = dataInputStream.readInt();
/* 1339 */           int var5 = dataInputStream.readInt();
/* 1340 */           String var6 = Packet.readString(dataInputStream, 256);
/* 1341 */           TileEntity var7 = this.playerEntity.worldObj.getBlockTileEntity(i, var18, var5);
/*      */           
/* 1343 */           if (var7 != null && var7 instanceof TileEntityCommandBlock)
/*      */           {
/* 1345 */             ((TileEntityCommandBlock)var7).setCommand(var6);
/* 1346 */             this.playerEntity.worldObj.markBlockForUpdate(i, var18, var5);
/* 1347 */             this.playerEntity.sendChatToPlayer(ChatMessageComponent.createFromTranslationWithSubstitutions("advMode.setCommand.success", new Object[] { var6 }));
/*      */           }
/*      */         
/* 1350 */         } catch (Exception var9) {
/*      */           
/* 1352 */           var9.printStackTrace();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1357 */         this.playerEntity.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("advMode.notAllowed"));
/*      */       }
/*      */     
/* 1360 */     } else if ("MC|Beacon".equals(par1Packet250CustomPayload.channel)) {
/*      */       
/* 1362 */       if (this.playerEntity.openContainer instanceof ContainerBeacon) {
/*      */         
/*      */         try {
/*      */           
/* 1366 */           DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(par1Packet250CustomPayload.data));
/* 1367 */           int i = dataInputStream.readInt();
/* 1368 */           int j = dataInputStream.readInt();
/* 1369 */           ContainerBeacon var17 = (ContainerBeacon)this.playerEntity.openContainer;
/* 1370 */           Slot var19 = var17.getSlot(0);
/*      */           
/* 1372 */           if (var19.getHasStack())
/*      */           {
/* 1374 */             var19.decrStackSize(1);
/* 1375 */             TileEntityBeacon var20 = var17.getBeacon();
/* 1376 */             var20.setPrimaryEffect(i);
/* 1377 */             var20.setSecondaryEffect(j);
/* 1378 */             var20.onInventoryChanged();
/*      */           }
/*      */         
/* 1381 */         } catch (Exception var8) {
/*      */           
/* 1383 */           var8.printStackTrace();
/*      */         }
/*      */       
/*      */       }
/* 1387 */     } else if ("MC|ItemName".equals(par1Packet250CustomPayload.channel) && this.playerEntity.openContainer instanceof ContainerRepair) {
/*      */       
/* 1389 */       ContainerRepair var13 = (ContainerRepair)this.playerEntity.openContainer;
/*      */       
/* 1391 */       if (par1Packet250CustomPayload.data != null && par1Packet250CustomPayload.data.length >= 1) {
/*      */         
/* 1393 */         String var15 = ChatAllowedCharacters.filerAllowedCharacters(new String(par1Packet250CustomPayload.data));
/*      */         
/* 1395 */         if (var15.length() <= 30)
/*      */         {
/* 1397 */           var13.updateItemName(var15);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1402 */         var13.updateItemName("");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityTeleport(Packet34EntityTeleport par1Packet34EntityTeleport) {
/* 1411 */     WorldServer world_server = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
/*      */     
/* 1413 */     Entity var2 = world_server.getEntityByID(par1Packet34EntityTeleport.entity_id);
/*      */     
/* 1415 */     if (var2 != null) {
/*      */       
/* 1417 */       par1Packet34EntityTeleport.applyToEntity(var2);
/*      */       
/* 1419 */       if (var2 instanceof EntityPlayer)
/*      */       {
/*      */         
/* 1422 */         sendPacketToPlayer(new Packet51MapChunk(var2.getChunkFromPosition(), false, 15));
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleRightClick(Packet81RightClick packet) {
/*      */     RightClickFilter filter;
/* 1438 */     if (packet.slot_index != this.playerEntity.inventory.currentItem || packet.item_id != this.playerEntity.getHeldItemID()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1453 */     if (packet.requiresRaycasting()) {
/*      */       
/* 1455 */       double pos_x = this.playerEntity.posX;
/* 1456 */       double pos_y = this.playerEntity.posY;
/* 1457 */       double pos_z = this.playerEntity.posZ;
/*      */       
/* 1459 */       float rotation_yaw = this.playerEntity.rotationYaw;
/* 1460 */       float rotation_pitch = this.playerEntity.rotationPitch;
/*      */       
/* 1462 */       double prev_pos_x = this.playerEntity.prevPosX;
/* 1463 */       double prev_pos_y = this.playerEntity.prevPosY;
/* 1464 */       double prev_pos_z = this.playerEntity.prevPosZ;
/*      */       
/* 1466 */       float prev_rotation_yaw = this.playerEntity.prevRotationYaw;
/* 1467 */       float prev_rotation_pitch = this.playerEntity.prevRotationPitch;
/*      */       
/* 1469 */       float y_size = this.playerEntity.ySize;
/*      */       
/* 1471 */       AxisAlignedBB bb = this.playerEntity.boundingBox.copy();
/*      */       
/* 1473 */       this.playerEntity.posX = packet.pos_x;
/* 1474 */       this.playerEntity.posY = packet.pos_y;
/* 1475 */       this.playerEntity.posZ = packet.pos_z;
/*      */       
/* 1477 */       this.playerEntity.rotationYaw = packet.rotation_yaw;
/* 1478 */       this.playerEntity.rotationPitch = packet.rotation_pitch;
/*      */       
/* 1480 */       this.playerEntity.prevPosX = packet.prev_pos_x;
/* 1481 */       this.playerEntity.prevPosY = packet.prev_pos_y;
/* 1482 */       this.playerEntity.prevPosZ = packet.prev_pos_z;
/*      */       
/* 1484 */       this.playerEntity.prevRotationYaw = packet.prev_rotation_yaw;
/* 1485 */       this.playerEntity.prevRotationPitch = packet.prev_rotation_pitch;
/*      */       
/* 1487 */       this.playerEntity.ySize = packet.y_size;
/* 1488 */       this.playerEntity.boundingBox.setBB(packet.bb);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1495 */       filter = this.playerEntity.onPlayerRightClickChecked(this.playerEntity.getSelectedObject(packet.partial_tick, false, false, (EnumEntityReachContext)null), packet.filter, packet.partial_tick, packet.ctrl_is_down);
/*      */       
/* 1497 */       this.playerEntity.posX = pos_x;
/* 1498 */       this.playerEntity.posY = pos_y;
/* 1499 */       this.playerEntity.posZ = pos_z;
/*      */       
/* 1501 */       this.playerEntity.rotationYaw = rotation_yaw;
/* 1502 */       this.playerEntity.rotationPitch = rotation_pitch;
/*      */       
/* 1504 */       this.playerEntity.prevPosX = prev_pos_x;
/* 1505 */       this.playerEntity.prevPosY = prev_pos_y;
/* 1506 */       this.playerEntity.prevPosZ = prev_pos_z;
/*      */       
/* 1508 */       this.playerEntity.prevRotationYaw = prev_rotation_yaw;
/* 1509 */       this.playerEntity.prevRotationPitch = prev_rotation_pitch;
/*      */       
/* 1511 */       this.playerEntity.ySize = y_size;
/* 1512 */       this.playerEntity.boundingBox.setBB(bb);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1517 */     else if (packet.filter.allowsEntityInteractionOnly()) {
/*      */       
/* 1519 */       Entity entity = this.playerEntity.worldObj.getEntityByID(packet.entity_id);
/*      */       
/* 1521 */       if (entity == null || entity.isDead) {
/*      */         return;
/*      */       }
/* 1524 */       filter = this.playerEntity.onPlayerRightClickChecked(new RaycastCollision(entity), packet.filter, 1.0F, packet.ctrl_is_down);
/*      */     }
/*      */     else {
/*      */       
/* 1528 */       filter = this.playerEntity.onPlayerRightClickChecked((RaycastCollision)null, packet.filter, 1.0F, packet.ctrl_is_down);
/*      */     } 
/*      */     
/* 1531 */     if (filter.getAllowedActions() != packet.filter.getAllowedActions()) {
/* 1532 */       Minecraft.setErrorMessage("handleRightClick: filter discrepency");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleAddHunger(Packet82AddHunger packet) {
/* 1543 */     this.playerEntity.addHungerServerSide(packet.hunger);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSimpleSignal(Packet85SimpleSignal packet) {
/* 1548 */     EnumSignal signal_type = packet.signal_type;
/*      */     
/* 1550 */     WorldServer world = (WorldServer)this.playerEntity.worldObj;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1557 */     if (signal_type == EnumSignal.achievement_unlocked) {
/*      */       
/* 1559 */       if (this.mcServer.isDedicatedServer()) {
/* 1560 */         DedicatedServer.logAchievement(this.playerEntity, StatList.getOneShotStat(packet.getInteger()));
/*      */       }
/* 1562 */     } else if (signal_type == EnumSignal.increment_stat_for_this_world_only) {
/*      */ 
/*      */       
/* 1565 */       this.playerEntity.incrementStatForThisWorldOnServer(packet.getInteger());
/*      */     }
/* 1567 */     else if (signal_type == EnumSignal.crafting_completed) {
/*      */       
/* 1569 */       Slot slot = this.playerEntity.openContainer.getSlot(0);
/*      */       
/* 1571 */       if (slot instanceof SlotCrafting)
/*      */       {
/* 1573 */         ItemStack item_stack = slot.getStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1582 */         slot.onPickupFromSlot(this.playerEntity, item_stack);
/*      */         
/* 1584 */         this.playerEntity.addExperience(-packet.getInteger());
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1594 */     else if (signal_type == EnumSignal.sleeping) {
/*      */ 
/*      */ 
/*      */       
/* 1598 */       this.playerEntity.conscious_state = EnumConsciousState.sleeping;
/*      */     }
/* 1600 */     else if (signal_type == EnumSignal.fully_awake) {
/*      */       
/* 1602 */       this.playerEntity.conscious_state = EnumConsciousState.fully_awake;
/*      */     }
/* 1604 */     else if (signal_type == EnumSignal.send_nearby_chunk_report) {
/*      */       
/* 1606 */       PlayerManager player_manager = this.playerEntity.getServerForPlayer().getPlayerManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1615 */       int radius = 5;
/*      */       
/* 1617 */       for (int chunk_dy = -radius; chunk_dy <= radius; chunk_dy++) {
/*      */ 
/*      */         
/* 1620 */         int chunk_y = this.playerEntity.getChunkCurrentlyInSectionIndex() + chunk_dy;
/* 1621 */         int y = chunk_y * 16;
/*      */         
/* 1623 */         if (y >= 0 || y <= 255)
/*      */         {
/*      */           
/* 1626 */           for (int chunk_dx = -radius; chunk_dx <= radius; chunk_dx++)
/*      */           {
/*      */             
/* 1629 */             int chunk_x = this.playerEntity.getChunkPosX() + chunk_dx;
/* 1630 */             int x = chunk_x * 16;
/*      */             
/* 1632 */             for (int chunk_dz = -radius; chunk_dz <= radius; chunk_dz++)
/*      */             {
/*      */               
/* 1635 */               int chunk_z = this.playerEntity.getChunkPosZ() + chunk_dz;
/* 1636 */               int z = chunk_z * 16;
/*      */               
/* 1638 */               ChunkCoordIntPair chunk_coords = new ChunkCoordIntPair(chunk_x, chunk_z);
/*      */               
/* 1640 */               if (!player_manager.isPlayerWatchingChunk(this.playerEntity, chunk_x, chunk_z)) {
/* 1641 */                 sendChatToPlayer("[Server] Player is not watching chunk @ " + x + ", " + z + " (cond #1=" + this.playerEntity.loadedChunks.contains(chunk_coords) + ")");
/* 1642 */               } else if (!this.playerEntity.worldObj.chunkExists(chunk_x, chunk_z)) {
/* 1643 */                 sendChatToPlayer("[Server] chunk does not exist @ " + x + ", " + z);
/*      */               } 
/*      */ 
/*      */               
/* 1647 */               Chunk chunk = this.playerEntity.getServerForPlayer().getChunkFromChunkCoords(chunk_x, chunk_z);
/*      */               
/* 1649 */               if (chunk != null) {
/* 1650 */                 chunk.setChunkModified();
/*      */               }
/* 1652 */               player_manager.filterChunkLoadQueue(this.playerEntity);
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/* 1659 */     } else if (signal_type == EnumSignal.terraform) {
/*      */ 
/*      */ 
/*      */       
/* 1663 */       int x = this.playerEntity.getBlockPosX();
/* 1664 */       int y = this.playerEntity.getBlockPosY();
/* 1665 */       int z = this.playerEntity.getBlockPosZ();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       int dx;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1681 */       for (dx = -128; dx <= 128; dx++) {
/*      */         
/* 1683 */         for (int dy = 0; dy <= 64; dy++) {
/*      */           
/* 1685 */           for (int dz = -128; dz <= 128; dz++) {
/*      */             
/* 1687 */             if (world.blockExists(x + dx, y + dy, z + dz)) {
/*      */ 
/*      */               
/* 1690 */               Block block = world.getBlock(x + dx, y + dy, z + dz);
/*      */ 
/*      */               
/* 1693 */               world.setBlockToAir(x + dx, y + dy, z + dz);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1698 */       for (dx = -32; dx <= 32; dx++) {
/*      */         
/* 1700 */         for (int dy = -2; dy < 0; dy++) {
/*      */           
/* 1702 */           for (int dz = -32; dz <= 32; dz++)
/*      */           {
/* 1704 */             world.setBlock(x + dx, y + dy, z + dz, Block.dirt.blockID);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1709 */       for (dx = -12; dx <= 18; dx += 6) {
/*      */         
/* 1711 */         for (int dz = -4; dz < 4; dz++) {
/*      */           int block_id;
/* 1713 */           world.setBlock(x + dx - 1, y - 1, z + dz, Block.tilledField.blockID, 7, 3);
/* 1714 */           world.setBlockToAir(x + dx, y - 1, z + dz);
/* 1715 */           world.setBlock(x + dx + 1, y - 1, z + dz, Block.tilledField.blockID, 7, 3);
/*      */ 
/*      */ 
/*      */           
/* 1719 */           if (dx == -12) {
/* 1720 */             block_id = Block.crops.blockID;
/* 1721 */           } else if (dx == -6) {
/* 1722 */             block_id = Block.carrot.blockID;
/* 1723 */           } else if (dx == 0) {
/* 1724 */             block_id = Block.potato.blockID;
/* 1725 */           } else if (dx == 6) {
/* 1726 */             block_id = Block.onions.blockID;
/* 1727 */           } else if (dx == 12) {
/* 1728 */             block_id = Block.pumpkinStem.blockID;
/*      */           } else {
/* 1730 */             block_id = Block.melonStem.blockID;
/*      */           } 
/* 1732 */           world.setBlock(x + dx - 1, y, z + dz, block_id);
/* 1733 */           world.setBlock(x + dx + 1, y, z + dz, block_id);
/*      */         } 
/*      */         
/* 1736 */         world.setBlock(x + dx, y - 1, z - 4, Block.waterMoving.blockID);
/* 1737 */         world.setBlock(x + dx, y, z - 5, Block.torchWood.blockID);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1742 */       for (dx = -12; dx <= 18; dx += 6) {
/*      */         
/* 1744 */         for (int dz = -4; dz < 4; dz++) {
/*      */           int block_id;
/* 1746 */           world.setBlock(x + dx - 1, y - 1, z + dz + 12, Block.tilledField.blockID, 7, 3);
/* 1747 */           world.setBlockToAir(x + dx, y - 1, z + dz + 12);
/* 1748 */           world.setBlock(x + dx + 1, y - 1, z + dz + 12, Block.tilledField.blockID, 7, 3);
/*      */ 
/*      */ 
/*      */           
/* 1752 */           if (dx == -12) {
/* 1753 */             block_id = Block.crops.blockID;
/* 1754 */           } else if (dx == -6) {
/* 1755 */             block_id = Block.carrot.blockID;
/* 1756 */           } else if (dx == 0) {
/* 1757 */             block_id = Block.potato.blockID;
/* 1758 */           } else if (dx == 6) {
/* 1759 */             block_id = Block.onions.blockID;
/* 1760 */           } else if (dx == 12) {
/* 1761 */             block_id = Block.pumpkinStem.blockID;
/*      */           } else {
/* 1763 */             block_id = Block.melonStem.blockID;
/*      */           } 
/* 1765 */           world.setBlock(x + dx - 1, y, z + dz + 12, block_id);
/* 1766 */           world.setBlock(x + dx + 1, y, z + dz + 12, block_id);
/*      */         } 
/*      */         
/* 1769 */         world.setBlock(x + dx, y - 1, z - 4 + 12, Block.waterMoving.blockID);
/* 1770 */         world.setBlock(x + dx, y, z - 5 + 12, Block.torchWood.blockID);
/*      */       } 
/*      */       
/* 1773 */       List entity_items = world.getEntitiesWithinAABB(EntityItem.class, this.playerEntity.boundingBox.expand(128.0D, 16.0D, 128.0D));
/*      */       
/* 1775 */       Iterator<EntityItem> i = entity_items.iterator();
/*      */       
/* 1777 */       while (i.hasNext()) {
/* 1778 */         ((EntityItem)i.next()).setDead();
/*      */       }
/* 1780 */     } else if (signal_type == EnumSignal.save_world_maps) {
/*      */       
/* 1782 */       this.mcServer.saveWorldMaps();
/*      */     }
/* 1784 */     else if (signal_type == EnumSignal.runegate_execute) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1793 */       double distance_travelled = World.getDistanceFromDeltas((this.playerEntity.runegate_destination_coords[0] + 0.5F) - this.playerEntity.posX, (this.playerEntity.runegate_destination_coords[2] + 0.5F) - this.playerEntity.posZ);
/*      */ 
/*      */ 
/*      */       
/* 1797 */       this.playerEntity.travelInsideDimension((this.playerEntity.runegate_destination_coords[0] + 0.5F), (this.playerEntity.runegate_destination_coords[1] + 0.1F), (this.playerEntity.runegate_destination_coords[2] + 0.5F));
/*      */       
/* 1799 */       this.playerEntity.is_runegate_teleporting = false;
/*      */ 
/*      */       
/* 1802 */       if (this.playerEntity.prevent_runegate_achievement) {
/* 1803 */         this.playerEntity.prevent_runegate_achievement = false;
/*      */       } else {
/* 1805 */         this.playerEntity.triggerAchievement(AchievementList.runegate);
/*      */       } 
/* 1807 */       sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.runegate_finished));
/*      */     }
/* 1809 */     else if (signal_type == EnumSignal.curse_effect_learned) {
/*      */       
/* 1811 */       (this.playerEntity.getCurse()).effect_known = true;
/*      */     }
/* 1813 */     else if (signal_type == EnumSignal.transfered_to_world) {
/*      */ 
/*      */       
/* 1816 */       this.playerEntity.portal_grace_ticks = Math.min(this.playerEntity.portal_grace_ticks, 60);
/*      */     }
/* 1818 */     else if (signal_type == EnumSignal.change_world_time) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1848 */       if (packet.getBoolean()) {
/* 1849 */         this.mcServer.addTotalTimeForAllWorlds(packet.getInteger());
/*      */       } else {
/*      */         
/* 1852 */         world.addTotalWorldTime(packet.getInteger(), true);
/*      */       } 
/* 1854 */     } else if (signal_type == EnumSignal.slot_locked) {
/*      */       
/* 1856 */       sendPacketToPlayer(new Packet85SimpleSignal(EnumSignal.unlock_slots));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1865 */     else if (signal_type == EnumSignal.drop_one_item) {
/*      */       
/* 1867 */       this.playerEntity.dropOneItem(packet.getBoolean());
/*      */     }
/* 1869 */     else if (signal_type == EnumSignal.stopped_using_item) {
/*      */       
/* 1871 */       this.playerEntity.stopUsingItem();
/*      */     }
/* 1873 */     else if (signal_type == EnumSignal.digging_block_start || signal_type == EnumSignal.digging_block_cancel || signal_type == EnumSignal.digging_block_complete) {
/*      */       
/* 1875 */       handleDiggingPacket(packet);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1909 */     else if (signal_type == EnumSignal.update_minecart_fuel) {
/*      */       
/* 1911 */       handleMinecartFuelUpdate(packet);
/*      */     }
/* 1913 */     else if (signal_type == EnumSignal.confirm_or_cancel_item_in_use) {
/*      */       
/* 1915 */       if (this.playerEntity.itemInUse == null) {
/* 1916 */         this.playerEntity.sendPacket(new Packet85SimpleSignal(EnumSignal.confirm_or_cancel_item_in_use));
/*      */       }
/* 1918 */     } else if (signal_type == EnumSignal.left_click_entity) {
/*      */       
/* 1920 */       Entity target = world.getEntityByID(packet.getEntityID());
/* 1921 */       this.playerEntity.func_143004_u();
/*      */       
/* 1923 */       if (target != null && this.playerEntity.getDistanceSqToEntity(target) < 256.0D)
/*      */       {
/* 1925 */         if (target instanceof EntityItem || target instanceof EntityXPOrb || target instanceof EntityArrow || target == this.playerEntity) {
/*      */           
/* 1927 */           kickPlayerFromServer("Attempting to attack an invalid entity");
/* 1928 */           this.mcServer.logWarning("Player " + this.playerEntity.getCommandSenderName() + " tried to attack an invalid entity");
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1933 */         this.playerEntity.attackTargetEntityWithCurrentItem(target);
/*      */       }
/*      */     
/* 1936 */     } else if (signal_type == EnumSignal.put_out_fire) {
/*      */       
/* 1938 */       RaycastCollision rc = this.playerEntity.getSelectedObject(1.0F, true);
/*      */       
/* 1940 */       if (rc != null && rc.isBlock() && rc.getNeighborOfBlockHit() == Block.fire) {
/* 1941 */         world.extinguishFire(null, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, rc.face_hit);
/*      */       }
/* 1943 */     } else if (signal_type == EnumSignal.mh) {
/*      */       
/* 1945 */       int master_hash = NetClientHandler.getMasterHash(this.playerEntity.worldObj.getSeed());
/*      */       
/* 1947 */       this.playerEntity.master_hash_received = true;
/* 1948 */       this.playerEntity.master_hash_validated = (master_hash == packet.getInteger());
/*      */       
/* 1950 */       if (!this.playerEntity.master_hash_validated) {
/* 1951 */         this.mcServer.getLogAgent().logWarning(this.playerEntity.username + " sent a master hash that did not validate!");
/* 1952 */       } else if (Minecraft.inDevMode()) {
/* 1953 */         System.out.println(this.playerEntity.username + " sent a master hash that validated");
/*      */       }
/*      */     
/* 1956 */     } else if (signal_type == EnumSignal.block_hit_sound) {
/*      */       
/* 1958 */       Block block = world.getBlock(packet.getBlockX(), packet.getBlockY(), packet.getBlockZ());
/*      */       
/* 1960 */       if (block != null && block.stepSound != null) {
/* 1961 */         world.playSoundToNearExcept(this.playerEntity, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 8.0F, block.stepSound.getPitch() * 0.5F);
/*      */       }
/* 1963 */     } else if (signal_type == EnumSignal.tag_entity) {
/*      */       
/* 1965 */       Entity entity = world.getEntityByID(packet.getEntityID());
/*      */       
/* 1967 */       if (entity == null)
/*      */       {
/* 1969 */         sendChatToPlayer("Was not able to " + (packet.getBoolean() ? "" : "un-") + "tag entity on server", EnumChatFormatting.RED);
/*      */       }
/*      */       else
/*      */       {
/* 1973 */         entity.tagged = packet.getBoolean();
/* 1974 */         sendChatToPlayer(entity.getEntityName() + " is now " + (entity.tagged ? "" : "un-") + "tagged");
/*      */       }
/*      */     
/* 1977 */     } else if (signal_type == EnumSignal.respawn_screen) {
/*      */ 
/*      */       
/* 1980 */       this.playerEntity.sendPacket((new Packet85SimpleSignal(EnumSignal.respawn_screen)).setShort(this.playerEntity.respawn_countdown));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2001 */     else if (signal_type == EnumSignal.vision_dimming_to_server) {
/*      */       
/* 2003 */       this.playerEntity.vision_dimming_on_client = (packet.getFloat() < 0.2F) ? 0.0F : packet.getFloat();
/*      */ 
/*      */     
/*      */     }
/* 2007 */     else if (signal_type == EnumSignal.entity_stats_dump) {
/*      */       
/* 2009 */       Entity entity = this.playerEntity.worldObj.getEntityByID(packet.getEntityID());
/*      */       
/* 2011 */       if (entity instanceof EntityLivingBase) {
/* 2012 */         this.playerEntity.sendPacket(EntityStatsDump.generatePacketFor(entity.getAsEntityLivingBase()));
/*      */       }
/* 2014 */     } else if (signal_type == EnumSignal.delete_selection) {
/*      */       
/* 2016 */       RaycastCollision rc = this.playerEntity.getSelectedObject(1.0F, false);
/*      */       
/* 2018 */       if (rc != null)
/*      */       {
/* 2020 */         if (rc.isBlock()) {
/* 2021 */           world.destroyBlockWithoutDroppingItem(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
/* 2022 */         } else if (rc.isEntity()) {
/* 2023 */           rc.getEntityHit().setDead();
/*      */         } 
/*      */       }
/* 2026 */     } else if (signal_type == EnumSignal.teleport_away) {
/*      */       
/* 2028 */       Entity entity = this.playerEntity.worldObj.getEntityByID(packet.getEntityID());
/*      */       
/* 2030 */       if (entity instanceof EntityPhaseSpider) {
/* 2031 */         ((EntityPhaseSpider)entity).tryTeleportAwayFrom(this.playerEntity, 3.0D);
/*      */       }
/*      */     } else {
/*      */       
/* 2035 */       Minecraft.setErrorMessage("handleSimpleSignal: unhandled signal (" + packet.signal_type + ")");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSetDespawnCounters(Packet87SetDespawnCounters packet) {
/* 2041 */     WorldServer world_server = this.mcServer.worldServerForDimension(this.playerEntity.dimension);
/*      */     
/* 2043 */     for (int i = 0; i < packet.entries; i++) {
/*      */       
/* 2045 */       Entity entity = world_server.getEntityByID(packet.entity_id[i]);
/*      */       
/* 2047 */       if (entity != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2053 */         entity.refreshDespawnCounter(packet.despawn_counter[i]);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handlePlaySoundOnServerAtEntity(Packet89PlaySoundOnServerAtEntity packet) {
/* 2061 */     Entity entity = this.playerEntity.worldObj.getEntityByID(packet.entity_id);
/*      */     
/* 2063 */     if (entity == null) {
/*      */       return;
/*      */     }
/* 2066 */     entity.handlePacket89(packet);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleBroadcastToAssociatedPlayers(Packet90BroadcastToAssociatedPlayers packet) {
/* 2073 */     WorldServer world_server = (WorldServer)this.playerEntity.worldObj;
/* 2074 */     EntityTracker entity_tracker = world_server.getEntityTracker();
/*      */     
/* 2076 */     if (packet.include_sender) {
/* 2077 */       entity_tracker.sendPacketToAllAssociatedPlayers(this.playerEntity, packet.packet);
/*      */     } else {
/* 2079 */       entity_tracker.sendPacketToAllPlayersTrackingEntity(this.playerEntity, packet.packet);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void handleMinecartFuelUpdate(Packet85SimpleSignal packet) {
/* 2085 */     int i = -packet.getEntityID() - 100;
/*      */     
/* 2087 */     if (i >= 0 && i < EntityMinecart.c.length) {
/*      */       
/* 2089 */       if (!this.playerEntity.Sr[i])
/*      */       {
/* 2091 */         if (packet.getInteger() != EntityMinecart.S[i]) {
/* 2092 */           EntityMinecart.updateFuel(this.playerEntity, packet, i);
/*      */         }
/* 2094 */         this.playerEntity.Sr[i] = true;
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2100 */       System.out.println("handleMinecartFuelUpdate: invalid index!");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isConnectionClosed() {
/* 2106 */     return this.connectionClosed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendChatToPlayer(String message) {
/* 2112 */     sendChatToPlayer(message, EnumChatFormatting.YELLOW);
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendChatToPlayer(String message, EnumChatFormatting color) {
/* 2117 */     sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromText(message).setColor(color)));
/*      */   }
/*      */ 
/*      */   
/*      */   public INetworkManager getNetManager() {
/* 2122 */     return this.netManager;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\NetServerHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */