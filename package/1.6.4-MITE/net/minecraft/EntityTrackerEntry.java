/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
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
/*     */ public class EntityTrackerEntry
/*     */ {
/*     */   public Entity myEntity;
/*     */   public int blocksDistanceThreshold;
/*     */   public int updateFrequency;
/*     */   public int lastScaledXPosition;
/*     */   public int lastScaledYPosition;
/*     */   public int lastScaledZPosition;
/*     */   private double last_pos_x;
/*     */   private double last_pos_y;
/*     */   private double last_pos_z;
/*     */   public int lastYaw;
/*     */   public int lastPitch;
/*     */   public int lastHeadMotion;
/*     */   public double motionX;
/*     */   public double motionY;
/*     */   public double motionZ;
/*     */   public int ticks;
/*     */   private double posX;
/*     */   private double posY;
/*     */   private double posZ;
/*     */   private boolean isDataInitialized;
/*     */   private boolean sendVelocityUpdates;
/*     */   private Entity field_85178_v;
/*     */   private boolean ridingEntity;
/*     */   public boolean playerEntitiesUpdated;
/*  49 */   public Set trackingPlayers = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityTrackerEntry(Entity par1Entity, int par2, int par3, boolean par4) {
/*  55 */     this.myEntity = par1Entity;
/*  56 */     this.blocksDistanceThreshold = par2;
/*  57 */     this.updateFrequency = par3;
/*  58 */     this.sendVelocityUpdates = par4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     this.lastScaledXPosition = SpatialScaler.getScaledPosX(par1Entity);
/*  64 */     this.lastScaledYPosition = SpatialScaler.getScaledPosY(par1Entity);
/*  65 */     this.lastScaledZPosition = SpatialScaler.getScaledPosZ(par1Entity);
/*     */     
/*  67 */     this.last_pos_x = par1Entity.posX;
/*  68 */     this.last_pos_y = par1Entity.posY;
/*  69 */     this.last_pos_z = par1Entity.posZ;
/*  70 */     this.lastYaw = MathHelper.floor_float(par1Entity.rotationYaw * 256.0F / 360.0F);
/*  71 */     this.lastPitch = MathHelper.floor_float(par1Entity.rotationPitch * 256.0F / 360.0F);
/*  72 */     this.lastHeadMotion = MathHelper.floor_float(par1Entity.getRotationYawHead() * 256.0F / 360.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/*  77 */     return (par1Obj instanceof EntityTrackerEntry) ? ((((EntityTrackerEntry)par1Obj).myEntity.entityId == this.myEntity.entityId)) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  82 */     return this.myEntity.entityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendLocationToAllClients(List par1List) {
/*  90 */     this.playerEntitiesUpdated = false;
/*  91 */     boolean velocity_update_sent_to_every_tracking_player = false;
/*     */     
/*  93 */     if (!this.isDataInitialized || this.myEntity.getDistanceSq(this.posX, this.posY, this.posZ) > 16.0D) {
/*     */       
/*  95 */       this.posX = this.myEntity.posX;
/*  96 */       this.posY = this.myEntity.posY;
/*  97 */       this.posZ = this.myEntity.posZ;
/*  98 */       this.isDataInitialized = true;
/*  99 */       this.playerEntitiesUpdated = true;
/* 100 */       sendEventsToPlayers(par1List);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 105 */     if (this.myEntity.velocityChanged) {
/*     */       
/* 107 */       sendPacketToAllAssociatedPlayers(new Packet28EntityVelocity(this));
/* 108 */       this.myEntity.velocityChanged = false;
/*     */       
/* 110 */       velocity_update_sent_to_every_tracking_player = true;
/*     */     } 
/*     */     
/* 113 */     if (this.field_85178_v != this.myEntity.ridingEntity || (this.myEntity.ridingEntity != null && this.ticks % 60 == 0)) {
/*     */       
/* 115 */       this.field_85178_v = this.myEntity.ridingEntity;
/* 116 */       sendPacketToAllTrackingPlayers(new Packet39AttachEntity(0, this.myEntity, this.myEntity.ridingEntity));
/*     */     } 
/*     */     
/* 119 */     if (this.myEntity instanceof EntityPlayer) {
/* 120 */       this.myEntity.send_position_update_immediately = true;
/*     */     }
/* 122 */     boolean prevent_update = false;
/*     */     
/* 124 */     if (this.myEntity instanceof EntityArrow)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       prevent_update = true;
/*     */     }
/*     */ 
/*     */     
/* 137 */     if (prevent_update) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       if (this.myEntity.getDataWatcher().hasChanges()) {
/* 143 */         func_111190_b();
/*     */       }
/*     */     }
/* 146 */     else if (this.myEntity instanceof EntityItemFrame && this.ticks % 10 == 0) {
/*     */       
/* 148 */       EntityItemFrame var23 = (EntityItemFrame)this.myEntity;
/* 149 */       ItemStack var24 = var23.getDisplayedItem();
/*     */       
/* 151 */       if (var24 != null && var24.getItem() instanceof ItemMap) {
/*     */         
/* 153 */         MapData var26 = Item.map.getMapData(var24, this.myEntity.worldObj);
/* 154 */         Iterator<EntityPlayer> var27 = par1List.iterator();
/*     */         
/* 156 */         while (var27.hasNext()) {
/*     */           
/* 158 */           EntityPlayer var28 = var27.next();
/* 159 */           ServerPlayer var29 = (ServerPlayer)var28;
/* 160 */           var26.updateVisiblePlayers(var29, var24);
/*     */           
/* 162 */           if (var29.playerNetServerHandler.packetSize() <= 5) {
/*     */             
/* 164 */             Packet var30 = Item.map.createMapDataPacket(var24, this.myEntity.worldObj, var29);
/*     */             
/* 166 */             if (var30 != null)
/*     */             {
/* 168 */               var29.playerNetServerHandler.sendPacketToPlayer(var30);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 174 */       func_111190_b();
/*     */     
/*     */     }
/* 177 */     else if (this.myEntity.send_position_update_immediately || this.ticks % this.updateFrequency == 0 || this.myEntity.isAirBorne || this.myEntity.getDataWatcher().hasChanges()) {
/*     */       
/* 179 */       this.myEntity.send_position_update_immediately = false;
/*     */ 
/*     */ 
/*     */       
/* 183 */       int scaled_yaw = SpatialScaler.getScaledRotation(this.myEntity.rotationYaw);
/* 184 */       int scaled_pitch = SpatialScaler.getScaledRotation(this.myEntity.rotationPitch);
/*     */       
/* 186 */       if (this.myEntity.ridingEntity == null) {
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
/* 226 */         if (this.ticks > 0 || this.myEntity instanceof EntityArrow) {
/*     */           
/* 228 */           int scaled_pos_x = SpatialScaler.getScaledPosX(this.myEntity);
/* 229 */           int scaled_pos_y = SpatialScaler.getScaledPosY(this.myEntity);
/* 230 */           int scaled_pos_z = SpatialScaler.getScaledPosZ(this.myEntity);
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
/* 245 */           Packet var10 = null;
/*     */           
/* 247 */           if (this.myEntity instanceof EntityPlayer || scaled_pos_x != this.lastScaledXPosition || scaled_pos_y != this.lastScaledYPosition || scaled_pos_z != this.lastScaledZPosition) {
/*     */             
/* 249 */             if (scaled_pos_x < -32000 || scaled_pos_x > 32000 || scaled_pos_z < -32000 || scaled_pos_z > 32000 || this.myEntity.sync_last_tick_pos_on_next_update) {
/* 250 */               var10 = new Packet34EntityTeleport(this.myEntity);
/*     */             } else {
/* 252 */               var10 = new Packet83EntityTeleportCompact(this.myEntity);
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 262 */           else if (scaled_yaw != this.lastYaw || scaled_pitch != this.lastPitch) {
/*     */             
/* 264 */             var10 = new Packet32EntityLook(this.myEntity);
/*     */           } 
/*     */           
/* 267 */           if (var10 != null) {
/* 268 */             sendPacketToAllTrackingPlayers(var10);
/*     */           }
/* 270 */           this.lastScaledXPosition = scaled_pos_x;
/* 271 */           this.lastScaledYPosition = scaled_pos_y;
/* 272 */           this.lastScaledZPosition = scaled_pos_z;
/* 273 */           this.lastYaw = scaled_yaw;
/* 274 */           this.lastPitch = scaled_pitch;
/* 275 */           this.last_pos_x = this.myEntity.posX;
/* 276 */           this.last_pos_y = this.myEntity.posY;
/* 277 */           this.last_pos_z = this.myEntity.posZ;
/*     */         } 
/*     */ 
/*     */         
/* 281 */         if (!velocity_update_sent_to_every_tracking_player && this.sendVelocityUpdates) {
/*     */           
/* 283 */           double var13 = this.myEntity.motionX - this.motionX;
/* 284 */           double var15 = this.myEntity.motionY - this.motionY;
/* 285 */           double var17 = this.myEntity.motionZ - this.motionZ;
/* 286 */           double var19 = 0.02D;
/* 287 */           double var21 = var13 * var13 + var15 * var15 + var17 * var17;
/*     */           
/* 289 */           if (var21 > var19 * var19 || (var21 > 0.0D && this.myEntity.motionX == 0.0D && this.myEntity.motionY == 0.0D && this.myEntity.motionZ == 0.0D))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 296 */             sendPacketToAllTrackingPlayers(new Packet28EntityVelocity(this));
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 305 */         func_111190_b();
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
/* 320 */         this.ridingEntity = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 338 */         if (Math.abs(scaled_yaw - this.lastYaw) >= 2 || Math.abs(scaled_pitch - this.lastPitch) >= 2) {
/*     */           
/* 340 */           sendPacketToAllTrackingPlayers(new Packet32EntityLook(this.myEntity));
/* 341 */           this.lastYaw = scaled_yaw;
/* 342 */           this.lastPitch = scaled_pitch;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 350 */         this.lastScaledXPosition = SpatialScaler.getScaledPosX(this.myEntity);
/* 351 */         this.lastScaledYPosition = SpatialScaler.getScaledPosY(this.myEntity);
/* 352 */         this.lastScaledZPosition = SpatialScaler.getScaledPosZ(this.myEntity);
/*     */         
/* 354 */         this.last_pos_x = this.myEntity.posX;
/* 355 */         this.last_pos_y = this.myEntity.posY;
/* 356 */         this.last_pos_z = this.myEntity.posZ;
/* 357 */         func_111190_b();
/* 358 */         this.ridingEntity = true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 369 */       int scaled_head_yaw = SpatialScaler.getScaledRotation(this.myEntity.getRotationYawHead());
/*     */       
/* 371 */       if (Math.abs(scaled_head_yaw - this.lastHeadMotion) >= 2) {
/*     */         
/* 373 */         sendPacketToAllTrackingPlayers(new Packet35EntityHeadRotation(this.myEntity.entityId, (byte)scaled_head_yaw));
/* 374 */         this.lastHeadMotion = scaled_head_yaw;
/*     */       } 
/*     */       
/* 377 */       this.myEntity.isAirBorne = false;
/*     */     } 
/*     */     
/* 380 */     this.ticks++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_111190_b() {
/* 391 */     DataWatcher var1 = this.myEntity.getDataWatcher();
/*     */     
/* 393 */     if (var1.hasChanges())
/*     */     {
/* 395 */       sendPacketToAllAssociatedPlayers(new Packet40EntityMetadata(this.myEntity.entityId, var1, false));
/*     */     }
/*     */     
/* 398 */     if (this.myEntity instanceof EntityLivingBase) {
/*     */       
/* 400 */       ServersideAttributeMap var2 = (ServersideAttributeMap)((EntityLivingBase)this.myEntity).getAttributeMap();
/* 401 */       Set var3 = var2.func_111161_b();
/*     */       
/* 403 */       if (!var3.isEmpty())
/*     */       {
/* 405 */         sendPacketToAllAssociatedPlayers(new Packet44UpdateAttributes(this.myEntity.entityId, var3));
/*     */       }
/*     */       
/* 408 */       var3.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketToAllTrackingPlayers(Packet par1Packet) {
/* 417 */     Iterator<ServerPlayer> var2 = this.trackingPlayers.iterator();
/*     */     
/* 419 */     while (var2.hasNext()) {
/*     */       
/* 421 */       ServerPlayer var3 = var2.next();
/* 422 */       var3.playerNetServerHandler.sendPacketToPlayer(par1Packet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketToAllAssociatedPlayers(Packet par1Packet) {
/* 431 */     sendPacketToAllTrackingPlayers(par1Packet);
/*     */     
/* 433 */     if (this.myEntity instanceof ServerPlayer)
/*     */     {
/* 435 */       ((ServerPlayer)this.myEntity).playerNetServerHandler.sendPacketToPlayer(par1Packet);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void informAllAssociatedPlayersOfItemDestruction() {
/* 441 */     Iterator<ServerPlayer> var1 = this.trackingPlayers.iterator();
/*     */     
/* 443 */     while (var1.hasNext()) {
/*     */       
/* 445 */       ServerPlayer var2 = var1.next();
/* 446 */       var2.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFromWatchingList(ServerPlayer par1EntityPlayerMP) {
/* 452 */     if (this.trackingPlayers.contains(par1EntityPlayerMP)) {
/*     */       
/* 454 */       par1EntityPlayerMP.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
/* 455 */       this.trackingPlayers.remove(par1EntityPlayerMP);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tryStartWachingThis(ServerPlayer par1EntityPlayerMP) {
/* 464 */     if (par1EntityPlayerMP != this.myEntity) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 469 */       double var2 = par1EntityPlayerMP.posX - this.last_pos_x;
/* 470 */       double var4 = par1EntityPlayerMP.posZ - this.last_pos_z;
/*     */       
/* 472 */       if (var2 >= -this.blocksDistanceThreshold && var2 <= this.blocksDistanceThreshold && var4 >= -this.blocksDistanceThreshold && var4 <= this.blocksDistanceThreshold) {
/*     */         
/* 474 */         if (!this.trackingPlayers.contains(par1EntityPlayerMP) && (isPlayerWatchingThisChunk(par1EntityPlayerMP) || this.myEntity.forceSpawn)) {
/*     */           
/* 476 */           boolean velocity_update_sent_to_this_player = false;
/*     */           
/* 478 */           this.trackingPlayers.add(par1EntityPlayerMP);
/* 479 */           Packet var6 = getPacketForThisEntity();
/* 480 */           par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(var6);
/*     */           
/* 482 */           if (var6 instanceof Packet23VehicleSpawn) {
/*     */             
/* 484 */             Packet23VehicleSpawn packet23 = (Packet23VehicleSpawn)var6;
/*     */             
/* 486 */             if (packet23.throwerEntityId > 0)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 492 */               velocity_update_sent_to_this_player = true;
/*     */             }
/*     */           }
/* 495 */           else if (var6 instanceof Packet26EntityExpOrb) {
/*     */             
/* 497 */             velocity_update_sent_to_this_player = true;
/*     */           } 
/*     */           
/* 500 */           if (velocity_update_sent_to_this_player) {
/*     */             
/* 502 */             this.motionX = this.myEntity.motionX;
/* 503 */             this.motionY = this.myEntity.motionY;
/* 504 */             this.motionZ = this.myEntity.motionZ;
/*     */           } 
/*     */           
/* 507 */           if (!this.myEntity.getDataWatcher().getIsBlank())
/*     */           {
/* 509 */             par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet40EntityMetadata(this.myEntity.entityId, this.myEntity.getDataWatcher(), true));
/*     */           }
/*     */           
/* 512 */           if (this.myEntity instanceof EntityLivingBase) {
/*     */             
/* 514 */             ServersideAttributeMap var7 = (ServersideAttributeMap)((EntityLivingBase)this.myEntity).getAttributeMap();
/* 515 */             Collection var8 = var7.func_111160_c();
/*     */             
/* 517 */             if (!var8.isEmpty())
/*     */             {
/* 519 */               par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet44UpdateAttributes(this.myEntity.entityId, var8));
/*     */             }
/*     */           } 
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
/* 532 */           if (!velocity_update_sent_to_this_player && this.sendVelocityUpdates && !(var6 instanceof Packet24MobSpawn)) {
/*     */             
/* 534 */             par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet28EntityVelocity(this));
/* 535 */             velocity_update_sent_to_this_player = true;
/*     */           } 
/*     */           
/* 538 */           if (this.myEntity.ridingEntity != null)
/*     */           {
/* 540 */             par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet39AttachEntity(0, this.myEntity, this.myEntity.ridingEntity));
/*     */           }
/*     */           
/* 543 */           if (this.myEntity instanceof EntityLiving && ((EntityLiving)this.myEntity).getLeashedToEntity() != null)
/*     */           {
/* 545 */             par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet39AttachEntity(1, this.myEntity, ((EntityLiving)this.myEntity).getLeashedToEntity()));
/*     */           }
/*     */           
/* 548 */           if (this.myEntity instanceof EntityLivingBase)
/*     */           {
/* 550 */             for (int var10 = 0; var10 < 5; var10++) {
/*     */               
/* 552 */               ItemStack var13 = ((EntityLivingBase)this.myEntity).getCurrentItemOrArmor(var10);
/*     */               
/* 554 */               if (var13 != null)
/*     */               {
/* 556 */                 par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet5PlayerInventory(this.myEntity.entityId, var10, var13));
/*     */               }
/*     */             } 
/*     */           }
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
/* 578 */           if (this.myEntity instanceof EntityLivingBase) {
/*     */             
/* 580 */             EntityLivingBase var14 = (EntityLivingBase)this.myEntity;
/* 581 */             Iterator<PotionEffect> var12 = var14.getActivePotionEffects().iterator();
/*     */             
/* 583 */             while (var12.hasNext())
/*     */             {
/* 585 */               PotionEffect var9 = var12.next();
/* 586 */               par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.myEntity.entityId, var9));
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 591 */       } else if (this.trackingPlayers.contains(par1EntityPlayerMP)) {
/*     */         
/* 593 */         this.trackingPlayers.remove(par1EntityPlayerMP);
/* 594 */         par1EntityPlayerMP.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPlayerWatchingThisChunk(ServerPlayer par1EntityPlayerMP) {
/* 605 */     if (!this.myEntity.isAddedToAChunk()) {
/* 606 */       return false;
/*     */     }
/* 608 */     Chunk chunk = this.myEntity.getChunkAddedTo();
/*     */     
/* 610 */     return par1EntityPlayerMP.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(par1EntityPlayerMP, chunk.xPosition, chunk.zPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendEventsToPlayers(List<ServerPlayer> par1List) {
/* 615 */     for (int var2 = 0; var2 < par1List.size(); var2++)
/*     */     {
/* 617 */       tryStartWachingThis(par1List.get(var2));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private Packet getPacketForThisEntity() {
/* 623 */     if (this.myEntity.isDead)
/*     */     {
/* 625 */       this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
/*     */     }
/*     */     
/* 628 */     if (this.myEntity instanceof EntityItem)
/*     */     {
/* 630 */       return new Packet23VehicleSpawn(this.myEntity, 2, 1);
/*     */     }
/* 632 */     if (this.myEntity instanceof ServerPlayer)
/*     */     {
/* 634 */       return new Packet20NamedEntitySpawn((EntityPlayer)this.myEntity);
/*     */     }
/* 636 */     if (this.myEntity instanceof EntityMinecart) {
/*     */       
/* 638 */       EntityMinecart var9 = (EntityMinecart)this.myEntity;
/* 639 */       return new Packet23VehicleSpawn(this.myEntity, 10, var9.getMinecartType());
/*     */     } 
/* 641 */     if (this.myEntity instanceof EntityBoat)
/*     */     {
/* 643 */       return new Packet23VehicleSpawn(this.myEntity, 1);
/*     */     }
/* 645 */     if (!(this.myEntity instanceof IAnimals) && !(this.myEntity instanceof EntityDragon)) {
/*     */       
/* 647 */       if (this.myEntity instanceof EntityFishHook) {
/*     */         
/* 649 */         EntityPlayer var8 = ((EntityFishHook)this.myEntity).angler;
/* 650 */         return new Packet23VehicleSpawn(this.myEntity, 90, (var8 != null) ? var8.entityId : this.myEntity.entityId);
/*     */       } 
/* 652 */       if (this.myEntity instanceof EntityArrow) {
/*     */         
/* 654 */         Entity var7 = ((EntityArrow)this.myEntity).shootingEntity;
/*     */ 
/*     */         
/* 657 */         Packet23VehicleSpawn packet = new Packet23VehicleSpawn(this.myEntity, 60, (var7 != null) ? var7.entityId : this.myEntity.entityId);
/*     */ 
/*     */         
/* 660 */         packet.arrow_item_id = ((EntityArrow)this.myEntity).item_arrow.itemID;
/* 661 */         packet.launcher_was_enchanted = ((EntityArrow)this.myEntity).launcher_was_enchanted;
/* 662 */         packet.arrow_stuck_in_block = ((EntityArrow)this.myEntity).isInGround();
/*     */         
/* 664 */         return packet;
/*     */       } 
/* 666 */       if (this.myEntity instanceof EntitySnowball)
/*     */       {
/* 668 */         return new Packet23VehicleSpawn(this.myEntity, 61);
/*     */       }
/* 670 */       if (this.myEntity instanceof EntityPotion)
/*     */       {
/* 672 */         return new Packet23VehicleSpawn(this.myEntity, 73, ((EntityPotion)this.myEntity).getPotionType());
/*     */       }
/* 674 */       if (this.myEntity instanceof EntityExpBottle)
/*     */       {
/* 676 */         return new Packet23VehicleSpawn(this.myEntity, 75);
/*     */       }
/* 678 */       if (this.myEntity instanceof EntityEnderPearl)
/*     */       {
/* 680 */         return new Packet23VehicleSpawn(this.myEntity, 65);
/*     */       }
/* 682 */       if (this.myEntity instanceof EntityEnderEye)
/*     */       {
/* 684 */         return new Packet23VehicleSpawn(this.myEntity, 72);
/*     */       }
/* 686 */       if (this.myEntity instanceof EntityFireworkRocket)
/*     */       {
/* 688 */         return new Packet23VehicleSpawn(this.myEntity, 76);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 694 */       if (this.myEntity instanceof EntityFireball) {
/*     */         
/* 696 */         EntityFireball var6 = (EntityFireball)this.myEntity;
/* 697 */         Packet23VehicleSpawn var2 = null;
/* 698 */         byte var3 = 63;
/*     */         
/* 700 */         if (this.myEntity instanceof EntitySmallFireball) {
/*     */           
/* 702 */           var3 = 64;
/*     */         }
/* 704 */         else if (this.myEntity instanceof EntityWitherSkull) {
/*     */           
/* 706 */           var3 = 66;
/*     */         } 
/*     */         
/* 709 */         if (var6.shootingEntity != null) {
/*     */           
/* 711 */           var2 = new Packet23VehicleSpawn(this.myEntity, var3, ((EntityFireball)this.myEntity).shootingEntity.entityId);
/*     */         }
/*     */         else {
/*     */           
/* 715 */           var2 = new Packet23VehicleSpawn(this.myEntity, var3, 0);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 722 */         var2.approx_motion_x = (float)var6.accelerationX;
/* 723 */         var2.approx_motion_y = (float)var6.accelerationY;
/* 724 */         var2.approx_motion_z = (float)var6.accelerationZ;
/*     */         
/* 726 */         return var2;
/*     */       } 
/* 728 */       if (this.myEntity instanceof EntityEgg)
/*     */       {
/* 730 */         return new Packet23VehicleSpawn(this.myEntity, 62);
/*     */       }
/* 732 */       if (this.myEntity instanceof EntityBrick)
/*     */       {
/* 734 */         return new Packet23VehicleSpawn(this.myEntity, (((EntityBrick)this.myEntity).getModelItem() == Item.netherrackBrick) ? 501 : 500);
/*     */       }
/* 736 */       if (this.myEntity instanceof EntityGelatinousSphere) {
/*     */         
/* 738 */         EntityGelatinousSphere sphere = (EntityGelatinousSphere)this.myEntity;
/*     */         
/* 740 */         return new Packet23VehicleSpawn(this.myEntity, 600 + sphere.getModelSubtype());
/*     */       } 
/* 742 */       if (this.myEntity instanceof EntityWeb)
/*     */       {
/* 744 */         return new Packet23VehicleSpawn(this.myEntity, 700);
/*     */       }
/* 746 */       if (this.myEntity instanceof EntityTNTPrimed)
/*     */       {
/* 748 */         return new Packet23VehicleSpawn(this.myEntity, 50);
/*     */       }
/* 750 */       if (this.myEntity instanceof EntityEnderCrystal)
/*     */       {
/* 752 */         return new Packet23VehicleSpawn(this.myEntity, 51);
/*     */       }
/* 754 */       if (this.myEntity instanceof EntityFallingSand) {
/*     */         
/* 756 */         EntityFallingSand var5 = (EntityFallingSand)this.myEntity;
/* 757 */         return new Packet23VehicleSpawn(this.myEntity, 70, var5.blockID | var5.metadata << 16);
/*     */       } 
/* 759 */       if (this.myEntity instanceof EntityPainting)
/*     */       {
/* 761 */         return new Packet25EntityPainting((EntityPainting)this.myEntity);
/*     */       }
/* 763 */       if (this.myEntity instanceof EntityItemFrame) {
/*     */         
/* 765 */         EntityItemFrame var4 = (EntityItemFrame)this.myEntity;
/* 766 */         Packet23VehicleSpawn var2 = new Packet23VehicleSpawn(this.myEntity, 71, var4.hangingDirection);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 771 */         var2.setUnscaledPositionWithIntegers(var4.xPosition, var4.yPosition, var4.zPosition);
/*     */         
/* 773 */         return var2;
/*     */       } 
/* 775 */       if (this.myEntity instanceof EntityLeashKnot) {
/*     */         
/* 777 */         EntityLeashKnot var1 = (EntityLeashKnot)this.myEntity;
/* 778 */         Packet23VehicleSpawn var2 = new Packet23VehicleSpawn(this.myEntity, 77);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 783 */         var2.setUnscaledPositionWithIntegers(var1.xPosition, var1.yPosition, var1.zPosition);
/*     */         
/* 785 */         return var2;
/*     */       } 
/* 787 */       if (this.myEntity instanceof EntityXPOrb)
/*     */       {
/* 789 */         return new Packet26EntityExpOrb((EntityXPOrb)this.myEntity);
/*     */       }
/*     */ 
/*     */       
/* 793 */       throw new IllegalArgumentException("Don't know how to add " + this.myEntity.getClass() + "!");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 798 */     if (this.myEntity instanceof EntityLiving) {
/*     */       
/* 800 */       this.lastHeadMotion = MathHelper.floor_float(this.myEntity.getRotationYawHead() * 256.0F / 360.0F);
/*     */       
/* 802 */       return new Packet24MobSpawn((EntityLiving)this.myEntity);
/*     */     } 
/*     */ 
/*     */     
/* 806 */     Minecraft.setErrorMessage("getPacketForThisEntity: entity not handled: " + this.myEntity);
/* 807 */     throw new IllegalArgumentException("Don't know how to add " + this.myEntity.getClass() + "!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayerFromTracker(ServerPlayer par1EntityPlayerMP) {
/* 813 */     if (this.trackingPlayers.contains(par1EntityPlayerMP)) {
/*     */       
/* 815 */       this.trackingPlayers.remove(par1EntityPlayerMP);
/* 816 */       par1EntityPlayerMP.destroyedItemsNetCache.add(Integer.valueOf(this.myEntity.entityId));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityTrackerEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */