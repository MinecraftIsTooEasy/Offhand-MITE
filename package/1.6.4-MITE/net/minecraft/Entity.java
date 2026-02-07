/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Entity
/*      */ {
/*      */   private static int nextEntityID;
/*      */   public int entityId;
/*      */   public double renderDistanceWeight;
/*      */   public boolean preventEntitySpawning;
/*      */   public Entity riddenByEntity;
/*      */   public Entity ridingEntity;
/*      */   public boolean forceSpawn;
/*      */   public World worldObj;
/*      */   public double prevPosX;
/*      */   public double prevPosY;
/*      */   public double prevPosZ;
/*      */   public double posX;
/*      */   public double posY;
/*      */   public double posZ;
/*      */   public double motionX;
/*      */   public double motionY;
/*      */   public double motionZ;
/*      */   public float rotationYaw;
/*      */   public float rotationPitch;
/*      */   public float prevRotationYaw;
/*      */   public float prevRotationPitch;
/*      */   public final AxisAlignedBB boundingBox;
/*      */   public boolean onGround;
/*      */   public boolean isCollidedHorizontally;
/*      */   public boolean isCollidedVertically;
/*      */   public boolean isCollided;
/*      */   public boolean velocityChanged;
/*      */   public boolean send_position_update_immediately;
/*      */   protected boolean isInWeb;
/*      */   public boolean field_70135_K;
/*      */   public boolean isDead;
/*      */   public boolean is_unwanted_duplicate;
/*      */   public float yOffset;
/*      */   public float width;
/*      */   public float height;
/*      */   public float prevDistanceWalkedModified;
/*      */   public float distanceWalkedModified;
/*      */   public float distanceWalkedOnStepModified;
/*      */   public float fallDistance;
/*      */   private int nextStepDistance;
/*      */   public double lastTickPosX;
/*      */   public double lastTickPosY;
/*      */   public double lastTickPosZ;
/*      */   public float ySize;
/*      */   public float stepHeight;
/*      */   public boolean noClip;
/*      */   public float entityCollisionReduction;
/*      */   protected Random rand;
/*      */   public int ticksExisted;
/*      */   private int fire;
/*      */   protected boolean inWater;
/*      */   public int hurtResistantTime;
/*      */   public boolean firstUpdate;
/*      */   protected DataWatcher dataWatcher;
/*      */   private double entityRiderPitchDelta;
/*      */   private double entityRiderYawDelta;
/*      */   private Chunk chunk_added_to;
/*      */   public int chunk_added_to_section_index;
/*      */   public Chunk last_chunk_saved_to;
/*      */   public int last_chunk_saved_to_entity_list_index;
/*      */   public Chunk last_chunk_loaded_from;
/*      */   public int last_chunk_loaded_from_entity_list_index;
/*      */   public boolean ignoreFrustumCheck;
/*      */   public boolean isAirBorne;
/*      */   public int timeUntilPortal;
/*  216 */   public int ticks_since_portal_teleport = 24000;
/*      */   
/*      */   protected boolean inPortal;
/*      */   
/*      */   protected int portal_destination_dimension_id;
/*      */   
/*      */   protected int portalCounter;
/*      */   
/*      */   public int dimension;
/*      */   
/*      */   protected int teleportDirection;
/*      */   
/*      */   private boolean invulnerable;
/*      */   
/*      */   private UUID entityUniqueID;
/*      */   
/*      */   public EnumEntitySize myEntitySize;
/*      */   public int spawn_x;
/*      */   public int spawn_y;
/*      */   public int spawn_z;
/*  236 */   public int despawn_counter = 0;
/*      */ 
/*      */   
/*      */   public boolean sync_last_tick_pos_on_next_update;
/*      */ 
/*      */   
/*      */   public boolean disable_shadow;
/*      */ 
/*      */   
/*      */   private int ticks_on_ground;
/*      */   
/*      */   protected int seen_by_bat_countdown;
/*      */   
/*      */   public int index_of_last_applicable_world_renderer;
/*      */   
/*      */   int ticks_since_last_wet;
/*      */   
/*  253 */   int raycast_seed_offset = 0;
/*      */ 
/*      */   
/*      */   public static Class entity_look_helper_class;
/*      */ 
/*      */   
/*      */   public boolean tagged;
/*      */   
/*      */   public static boolean apply_MITE_bb_limits_checking = true;
/*      */ 
/*      */   
/*      */   public Entity(World par1World) {
/*  265 */     this.entityId = nextEntityID++;
/*  266 */     this.renderDistanceWeight = 1.0D;
/*  267 */     this.boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  268 */     this.field_70135_K = true;
/*  269 */     this.width = 0.6F;
/*  270 */     this.height = 1.8F;
/*  271 */     this.nextStepDistance = 1;
/*  272 */     this.rand = new Random();
/*      */     
/*  274 */     this.firstUpdate = true;
/*  275 */     this.dataWatcher = new DataWatcher();
/*      */     
/*  277 */     this.myEntitySize = EnumEntitySize.SIZE_2;
/*  278 */     this.worldObj = par1World;
/*  279 */     this.entityUniqueID = isExpectedToHaveUUID() ? UUID.randomUUID() : null;
/*  280 */     setPosition(0.0D, 0.0D, 0.0D);
/*      */     
/*  282 */     if (par1World != null)
/*      */     {
/*  284 */       this.dimension = par1World.provider.dimensionId;
/*      */     }
/*      */     
/*  287 */     this.dataWatcher.addObject(0, Byte.valueOf((byte)0));
/*  288 */     this.dataWatcher.addObject(1, Short.valueOf((short)300));
/*  289 */     entityInit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resetEntityIds() {
/*  298 */     if (nextEntityID < 0) {
/*  299 */       nextEntityID = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public static int peekNextEntityID() {
/*  304 */     return nextEntityID;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int obtainNextEntityID() {
/*  309 */     return nextEntityID++;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onSpawned() {
/*  314 */     this.prevPosX = this.posX;
/*  315 */     this.prevPosY = this.posY;
/*  316 */     this.prevPosZ = this.posZ;
/*      */   }
/*      */ 
/*      */   
/*      */   public DataWatcher getDataWatcher() {
/*  321 */     return this.dataWatcher;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object par1Obj) {
/*  326 */     return (par1Obj instanceof Entity) ? ((((Entity)par1Obj).entityId == this.entityId)) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  331 */     return this.entityId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void preparePlayerToSpawn() {
/*  340 */     if (this.worldObj != null) {
/*      */       
/*  342 */       while (this.posY > 0.0D) {
/*      */         
/*  344 */         setPosition(this.posX, this.posY, this.posZ);
/*      */         
/*  346 */         if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  351 */         this.posY++;
/*      */       } 
/*      */       
/*  354 */       this.motionX = this.motionY = this.motionZ = 0.0D;
/*  355 */       this.rotationPitch = 0.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDead() {
/*  364 */     if (!this.isDead)
/*      */     {
/*  366 */       if (this instanceof EntityItem) {
/*      */         
/*  368 */         Chunk chunk = getChunkAddedTo();
/*      */         
/*  370 */         if (chunk != null && chunk.doesEntityObjectExistInEntityLists(this)) {
/*  371 */           chunk.setChunkModified();
/*      */         }
/*      */       } 
/*      */     }
/*  375 */     this.isDead = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setSize(float par1, float par2) {
/*  385 */     if (par1 != this.width || par2 != this.height) {
/*      */       
/*  387 */       boolean was_on_ground = this.onGround;
/*      */       
/*  389 */       float f = this.width;
/*  390 */       this.width = par1;
/*  391 */       this.height = par2;
/*  392 */       this.boundingBox.maxX = this.boundingBox.minX + this.width;
/*  393 */       this.boundingBox.maxZ = this.boundingBox.minZ + this.width;
/*  394 */       this.boundingBox.maxY = this.boundingBox.minY + this.height;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  406 */       if (this.width > f && !this.firstUpdate && !this.worldObj.isRemote) {
/*      */         
/*  408 */         moveEntity((f - this.width), 0.0D, (f - this.width));
/*      */         
/*  410 */         if (was_on_ground) {
/*  411 */           this.onGround = true;
/*      */         }
/*      */       } 
/*      */     } 
/*  415 */     float var3 = par1 % 2.0F;
/*      */     
/*  417 */     if (var3 < 0.375D) {
/*      */       
/*  419 */       this.myEntitySize = EnumEntitySize.SIZE_1;
/*      */     }
/*  421 */     else if (var3 < 0.75D) {
/*      */       
/*  423 */       this.myEntitySize = EnumEntitySize.SIZE_2;
/*      */     }
/*  425 */     else if (var3 < 1.0D) {
/*      */       
/*  427 */       this.myEntitySize = EnumEntitySize.SIZE_3;
/*      */     }
/*  429 */     else if (var3 < 1.375D) {
/*      */       
/*  431 */       this.myEntitySize = EnumEntitySize.SIZE_4;
/*      */     }
/*  433 */     else if (var3 < 1.75D) {
/*      */       
/*  435 */       this.myEntitySize = EnumEntitySize.SIZE_5;
/*      */     }
/*      */     else {
/*      */       
/*  439 */       this.myEntitySize = EnumEntitySize.SIZE_6;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setRotation(float par1, float par2) {
/*  448 */     this.rotationYaw = par1 % 360.0F;
/*  449 */     this.rotationPitch = par2 % 360.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPosition(double par1, double par3, double par5) {
/*  454 */     setPosition(par1, par3, par5, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPosition(double par1, double par3, double par5, boolean bypass_in_bed_check) {
/*  463 */     if (!bypass_in_bed_check && this instanceof EntityPlayer) {
/*      */       
/*  465 */       EntityPlayer player = (EntityPlayer)this;
/*      */       
/*  467 */       if (player.inBed()) {
/*      */         return;
/*      */       }
/*      */     } 
/*  471 */     this.posX = par1;
/*  472 */     this.posY = par3;
/*  473 */     this.posZ = par5;
/*  474 */     float var7 = this.width / 2.0F;
/*  475 */     float var8 = this.height;
/*  476 */     this.boundingBox.setBounds(par1 - var7, par3 - this.yOffset + this.ySize, par5 - var7, par1 + var7, par3 - this.yOffset + this.ySize + var8, par5 + var7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAngles(float par1, float par2) {
/*  485 */     float var3 = this.rotationPitch;
/*  486 */     float var4 = this.rotationYaw;
/*  487 */     this.rotationYaw = (float)(this.rotationYaw + par1 * 0.15D);
/*  488 */     this.rotationPitch = (float)(this.rotationPitch - par2 * 0.15D);
/*      */     
/*  490 */     if (this.rotationPitch < -90.0F)
/*      */     {
/*  492 */       this.rotationPitch = -90.0F;
/*      */     }
/*      */     
/*  495 */     if (this.rotationPitch > 90.0F)
/*      */     {
/*  497 */       this.rotationPitch = 90.0F;
/*      */     }
/*      */     
/*  500 */     this.prevRotationPitch += this.rotationPitch - var3;
/*  501 */     this.prevRotationYaw += this.rotationYaw - var4;
/*      */   }
/*      */ 
/*      */   
/*      */   public void spentTickInWater() {}
/*      */ 
/*      */   
/*      */   public void spentTickInFire() {
/*  509 */     if (this.isDead) {
/*      */       return;
/*      */     }
/*  512 */     dealFireDamage(1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     if (onServer() && canDouseFire())
/*      */     {
/*  522 */       if (!(this instanceof EntityItem) || this.isDead)
/*      */       {
/*  524 */         if (this.worldObj.extinguishAllFireBlocksInBoundingBox(this.boundingBox) && !this.isDead) {
/*  525 */           causeQuenchEffect();
/*      */         }
/*      */       }
/*      */     }
/*  529 */     if (!isWet() && canCatchFire())
/*      */     {
/*  531 */       if (++this.fire == 0) {
/*  532 */         setFire(8);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void spentTickInLava() {
/*  538 */     if (this.isDead) {
/*      */       return;
/*      */     }
/*  541 */     setOnFireFromLava();
/*      */     
/*  543 */     if (this.isDead)
/*      */     {
/*  545 */       if (!this.worldObj.isRemote) {
/*      */         
/*  547 */         if (canDouseFire()) {
/*  548 */           causeQuenchEffect();
/*      */         }
/*  550 */         entityFX(EnumEntityFX.burned_up_in_lava);
/*      */       } 
/*      */     }
/*      */     
/*  554 */     this.fallDistance *= 0.5F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkForContactWithFireAndLava() {
/*  559 */     if (handleLavaMovement()) {
/*      */       
/*  561 */       spentTickInLava();
/*      */ 
/*      */     
/*      */     }
/*  565 */     else if (!this.worldObj.isRemote) {
/*      */       
/*  567 */       if (isInFire()) {
/*  568 */         spentTickInFire();
/*  569 */       } else if (this.fire <= 0) {
/*  570 */         this.fire = -getFireResistance();
/*      */       } 
/*  572 */       if (isWet() && this.fire > 0) {
/*      */         
/*  574 */         this.fire = -getFireResistance();
/*  575 */         causeQuenchEffect();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getTicksExistedWithOffset() {
/*  583 */     return this.ticksExisted + this.entityId * 47;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void detectAndRemoveDuplicateEntities() {
/*  589 */     WorldServer world = this.worldObj.getAsWorldServer();
/*      */     
/*  591 */     int num_times_in_list = 0;
/*      */     
/*  593 */     for (int i = 0; i < world.loadedEntityList.size(); i++) {
/*      */       
/*  595 */       Entity entity = world.loadedEntityList.get(i);
/*      */       
/*  597 */       if (entity.getClass() == getClass())
/*      */       {
/*      */         
/*  600 */         if (entity == this) {
/*      */           
/*  602 */           num_times_in_list++;
/*      */ 
/*      */         
/*      */         }
/*  606 */         else if (entity.getUniqueID().equals(getUniqueID())) {
/*      */           
/*  608 */           if (world.isEntityObjectInUnloadedEntityList(entity) || entity.isDead) {
/*      */             
/*  610 */             if (!entity.isDead);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  618 */             entity.setAsUnwantedDuplicate();
/*      */           }
/*      */           else {
/*      */             
/*  622 */             String msg = "Duplicate " + getEntityName() + " detected, removing (" + entity.getBlockPosX() + "," + entity.getBlockPosY() + "," + entity.getBlockPosZ() + " vs " + getBlockPosX() + "," + getBlockPosY() + "," + getBlockPosZ() + ") ticksExisted: " + entity.ticksExisted + " vs " + this.ticksExisted;
/*      */             
/*  624 */             if (Minecraft.inDevMode()) {
/*  625 */               Minecraft.setErrorMessage(msg);
/*      */             } else {
/*  627 */               System.out.println(msg);
/*      */             } 
/*  629 */             System.out.println("  Chunk loaded from: " + entity.last_chunk_loaded_from + " ELI:" + entity.last_chunk_loaded_from_entity_list_index + " vs " + this.last_chunk_loaded_from + " ELI:" + this.last_chunk_loaded_from_entity_list_index);
/*      */ 
/*      */ 
/*      */             
/*  633 */             entity.setAsUnwantedDuplicate();
/*      */             
/*  635 */             (new Exception()).printStackTrace();
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*  640 */     if (num_times_in_list > 1) {
/*  641 */       Minecraft.setErrorMessage("detectAndRemoveDuplicateEntities: " + getEntityName() + " in loadedEntityList " + num_times_in_list + " times!");
/*      */     }
/*      */   }
/*      */   
/*      */   public void setAsUnwantedDuplicate() {
/*  646 */     this.is_unwanted_duplicate = true;
/*      */     
/*  648 */     setDead();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  659 */     if (onServer() && getTicksExistedWithOffset() % 200 == 0) {
/*  660 */       detectAndRemoveDuplicateEntities();
/*      */     }
/*  662 */     if (onServer() && this instanceof IProjectile) {
/*      */       
/*  664 */       boolean in_flight = true;
/*      */       
/*  666 */       if (this instanceof EntityArrow) {
/*      */         
/*  668 */         EntityArrow entity_arrow = (EntityArrow)this;
/*      */         
/*  670 */         if (entity_arrow.isInGround()) {
/*  671 */           in_flight = false;
/*      */         }
/*      */       } 
/*  674 */       if (in_flight) {
/*      */         
/*  676 */         List nearby_entities = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.boundingBox.expand(4.0D, 4.0D, 4.0D));
/*      */         
/*  678 */         Iterator<EntityLiving> i = nearby_entities.iterator();
/*      */         
/*  680 */         while (i.hasNext()) {
/*      */           
/*  682 */           EntityLiving entity_living = i.next();
/*      */           
/*  684 */           if (entity_living instanceof EntityBat) {
/*      */             
/*  686 */             EntityBat entity_bat = (EntityBat)entity_living;
/*      */             
/*  688 */             if (entity_bat.getIsBatHanging() && entity_bat.canSeeEntity(this, true))
/*      */             {
/*  690 */               entity_bat.hurtResistantTime = 2;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  697 */     onEntityUpdate();
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean doesBlockShowSprintingParticles(Block block) {
/*  702 */     Material material = block.blockMaterial;
/*      */     
/*  704 */     if (material == Material.anvil || material == Material.diamond || material == Material.emerald || material == Material.hardened_clay || material == Material.glass || material == Material.ice || material == Material.netherrack || material == Material.obsidian || material == Material.stone || material == Material.wood || material.isMetal()) {
/*  705 */       return false;
/*      */     }
/*  707 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  715 */     if (isExpectedToHaveUUID()) {
/*      */       
/*  717 */       if (this.entityUniqueID == null) {
/*  718 */         Minecraft.setErrorMessage("onEntityUpdate: UUID null for " + this);
/*      */       
/*      */       }
/*      */     }
/*  722 */     else if (this.entityUniqueID != null) {
/*  723 */       Minecraft.setErrorMessage("onEntityUpdate: UUID not null for " + this);
/*      */     } 
/*      */     
/*  726 */     if (this.ticks_since_portal_teleport < 24000) {
/*  727 */       this.ticks_since_portal_teleport++;
/*      */     }
/*  729 */     if (this.onGround) {
/*  730 */       this.ticks_on_ground++;
/*      */     } else {
/*  732 */       this.ticks_on_ground = 0;
/*      */     } 
/*  734 */     if (this.seen_by_bat_countdown > 0) {
/*  735 */       this.seen_by_bat_countdown--;
/*      */     }
/*  737 */     this.worldObj.theProfiler.startSection("entityBaseTick");
/*      */     
/*  739 */     if (this.ridingEntity != null && this.ridingEntity.isDead)
/*      */     {
/*  741 */       this.ridingEntity = null;
/*      */     }
/*      */     
/*  744 */     this.prevDistanceWalkedModified = this.distanceWalkedModified;
/*  745 */     this.prevPosX = this.posX;
/*  746 */     this.prevPosY = this.posY;
/*  747 */     this.prevPosZ = this.posZ;
/*  748 */     this.prevRotationPitch = this.rotationPitch;
/*  749 */     this.prevRotationYaw = this.rotationYaw;
/*      */ 
/*      */     
/*  752 */     if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
/*      */       
/*  754 */       this.worldObj.theProfiler.startSection("portal");
/*  755 */       MinecraftServer var1 = ((WorldServer)this.worldObj).getMinecraftServer();
/*  756 */       int var2 = getMaxInPortalTime();
/*      */       
/*  758 */       if (this.inPortal) {
/*      */         
/*  760 */         if (var1.getAllowNether())
/*      */         {
/*  762 */           if (this.ridingEntity == null && this.portalCounter++ >= var2) {
/*      */             
/*  764 */             this.portalCounter = var2;
/*  765 */             this.timeUntilPortal = getPortalCooldown();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  777 */             byte var3 = (byte)this.portal_destination_dimension_id;
/*      */             
/*  779 */             travelToDimension(var3);
/*      */           } 
/*      */           
/*  782 */           this.inPortal = false;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  787 */         if (this.portalCounter > 0)
/*      */         {
/*  789 */           this.portalCounter -= 4;
/*      */         }
/*      */         
/*  792 */         if (this.portalCounter < 0)
/*      */         {
/*  794 */           this.portalCounter = 0;
/*      */         }
/*      */       } 
/*      */       
/*  798 */       if (this.timeUntilPortal > 0)
/*      */       {
/*  800 */         this.timeUntilPortal--;
/*      */       }
/*      */       
/*  803 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */     
/*  806 */     boolean render_sprinting_particles = false;
/*      */     
/*  808 */     if (this.onGround && !isInWater() && !isZevimrgvInTournament())
/*      */     {
/*  810 */       if (isSprinting())
/*      */       {
/*  812 */         render_sprinting_particles = true;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  823 */     if (render_sprinting_particles) {
/*      */       
/*  825 */       int var5 = MathHelper.floor_double(this.posX);
/*  826 */       int var2 = MathHelper.floor_double(this.posY - 0.20000000298023224D - this.yOffset);
/*  827 */       int var6 = MathHelper.floor_double(this.posZ);
/*  828 */       int var4 = this.worldObj.getBlockId(var5, var2, var6);
/*      */       
/*  830 */       if (var4 > 0) {
/*      */         
/*  832 */         if (this.worldObj.getBlock(var5, var2 + 1, var6) == Block.snow) {
/*      */           
/*  834 */           var4 = Block.snow.blockID;
/*  835 */           var2++;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  841 */         if (doesBlockShowSprintingParticles(Block.getBlock(var4))) {
/*  842 */           this.worldObj.spawnParticleEx(EnumParticle.tilecrack, var4, this.worldObj.getBlockMetadata(var5, var2, var6), this.posX + (this.rand.nextFloat() - 0.5D) * this.width, this.boundingBox.minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, -this.motionX * 4.0D, 1.5D, -this.motionZ * 4.0D);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  848 */     if (handleWaterMovement()) {
/*  849 */       spentTickInWater();
/*      */     }
/*  851 */     if (isWet()) {
/*  852 */       this.ticks_since_last_wet = 0;
/*      */     } else {
/*  854 */       this.ticks_since_last_wet++;
/*      */     } 
/*  856 */     if (this.worldObj.isRemote) {
/*      */       
/*  858 */       this.fire = 0;
/*      */     }
/*  860 */     else if (this.fire > 0) {
/*      */ 
/*      */ 
/*      */       
/*  864 */       if (!canCatchFire() || !isHarmedByFire()) {
/*      */         
/*  866 */         this.fire -= 4;
/*      */         
/*  868 */         if (this.fire < 0)
/*      */         {
/*  870 */           this.fire = 0;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  875 */         if (this.fire % 20 == 0)
/*      */         {
/*      */           
/*  878 */           attackEntityFrom(new Damage(DamageSource.onFire, 1.0F));
/*      */         }
/*      */         
/*  881 */         this.fire--;
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  912 */     checkForContactWithFireAndLava();
/*      */     
/*  914 */     if (this.posY < -64.0D)
/*      */     {
/*  916 */       kill();
/*      */     }
/*      */     
/*  919 */     if (!this.worldObj.isRemote)
/*      */     {
/*  921 */       setFlag(0, (this.fire > 0));
/*      */     }
/*      */     
/*  924 */     if (!this.worldObj.isRemote) {
/*      */       float chance_to_set_surroundings_on_fire;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  930 */       if (this instanceof EntityFireElemental) {
/*  931 */         chance_to_set_surroundings_on_fire = 0.05F;
/*  932 */       } else if (this instanceof EntityEarthElemental && ((EntityEarthElemental)this).isMagma()) {
/*  933 */         chance_to_set_surroundings_on_fire = 0.04F;
/*  934 */       } else if (this instanceof EntityMagmaCube) {
/*  935 */         chance_to_set_surroundings_on_fire = 0.04F;
/*  936 */       } else if (isBurning()) {
/*  937 */         chance_to_set_surroundings_on_fire = (this instanceof EntityLiving && (getAsEntityLiving()).increased_chance_of_spreading_fire_countdown > 0) ? 0.01F : 0.00125F;
/*      */       } else {
/*  939 */         chance_to_set_surroundings_on_fire = 0.0F;
/*      */       } 
/*      */ 
/*      */       
/*  943 */       if (chance_to_set_surroundings_on_fire > 0.0F && this.rand.nextFloat() < chance_to_set_surroundings_on_fire) {
/*      */         
/*  945 */         int x = MathHelper.floor_double(this.posX);
/*  946 */         int y = MathHelper.floor_double(this.posY);
/*  947 */         int z = MathHelper.floor_double(this.posZ);
/*      */         
/*  949 */         int fire_height = (int)this.height + 1;
/*      */         
/*  951 */         for (int dy = 0; dy < fire_height; dy++) {
/*      */           
/*  953 */           if (this.worldObj.isAirBlock(x, y + dy, z)) {
/*      */             
/*  955 */             if (this.rand.nextInt(10) == 0 && Block.fire.canNeighborBurn(this.worldObj, x, y + dy, z))
/*      */             {
/*  957 */               this.worldObj.setBlock(x, y + dy, z, Block.fire.blockID);
/*      */             }
/*  959 */             else if (dy == 0)
/*      */             {
/*  961 */               BlockInfo info = getBlockRestingOn(0.1F);
/*      */               
/*  963 */               if (info != null) {
/*  964 */                 this.worldObj.tryToMeltBlock(info.x, info.y, info.z);
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  971 */           else if (!Block.fire.tryToCatchBlockOnFire(this.worldObj, x, y + dy, z, 100, this.rand, 1)) {
/*  972 */             this.worldObj.tryToMeltBlock(x, y + dy, z);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  978 */     this.firstUpdate = false;
/*  979 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxInPortalTime() {
/*  987 */     return 0;
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
/*      */   protected void setOnFireFromLava() {
/* 1001 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1003 */       if (isHarmedByLava()) {
/* 1004 */         attackEntityFrom(new Damage(DamageSource.lava, 4.0F));
/*      */       }
/* 1006 */       if (canCatchFire()) {
/* 1007 */         setFire(15);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFire(int par1) {
/* 1017 */     if (par1 < 1 || !canCatchFire()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1022 */     int var2 = par1 * 20;
/* 1023 */     var2 += 10;
/* 1024 */     var2 = EnchantmentProtection.getFireTimeForEntity(this, var2);
/*      */     
/* 1026 */     if (this.fire < var2)
/*      */     {
/* 1028 */       this.fire = var2;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void extinguish(boolean do_quench_effect_even_if_not_wet) {
/* 1039 */     if (!this.worldObj.isRemote && this.fire > 0 && (do_quench_effect_even_if_not_wet || isWet())) {
/* 1040 */       causeQuenchEffect();
/*      */     }
/*      */     
/* 1043 */     this.fire = -getFireResistance();
/*      */   }
/*      */ 
/*      */   
/*      */   public void extinguish() {
/* 1048 */     extinguish(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void kill() {
/* 1056 */     setDead();
/*      */     
/* 1058 */     if (onServer())
/*      */     {
/* 1060 */       if (this instanceof EntityItem) {
/* 1061 */         ((EntityItem)this).tryRemoveFromWorldUniques();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOffsetPositionInLiquid(double par1, double par3, double par5) {
/* 1070 */     AxisAlignedBB var7 = this.boundingBox.getOffsetBoundingBox(par1, par3, par5);
/* 1071 */     List var8 = this.worldObj.getCollidingBoundingBoxes(this, var7);
/* 1072 */     return !var8.isEmpty() ? false : (!this.worldObj.isAnyLiquid(var7));
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticles(EnumParticle kind, int num_particles, float random_motion) {
/*      */     double effective_pos_y;
/* 1078 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1080 */       Minecraft.setErrorMessage("spawnParticles: only valid on client");
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1086 */     if (this instanceof EntityLivingBase) {
/* 1087 */       effective_pos_y = ((EntityLivingBase)this).getFootPosY();
/*      */     } else {
/* 1089 */       effective_pos_y = this.posY;
/*      */     } 
/* 1091 */     double dx = 0.0D;
/* 1092 */     double dy = 0.0D;
/* 1093 */     double dz = 0.0D;
/*      */     
/* 1095 */     for (int i = 0; i < num_particles; i++) {
/*      */       
/* 1097 */       if (random_motion > 0.0F) {
/*      */         
/* 1099 */         dx = this.rand.nextGaussian() * random_motion;
/* 1100 */         dy = this.rand.nextGaussian() * random_motion;
/* 1101 */         dz = this.rand.nextGaussian() * random_motion;
/*      */       } 
/*      */       
/* 1104 */       this.worldObj.spawnParticle(kind, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, effective_pos_y + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, dx, dy, dz);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnParticles(EnumParticle kind, int num_particles) {
/* 1111 */     spawnParticles(kind, num_particles, 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(EnumParticle kind, float random_motion) {
/*      */     double effective_pos_y;
/* 1117 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1119 */       Minecraft.setErrorMessage("spawnParticles: only valid on client");
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1125 */     if (this instanceof EntityLivingBase) {
/* 1126 */       effective_pos_y = ((EntityLivingBase)this).getFootPosY();
/*      */     } else {
/* 1128 */       effective_pos_y = this.posY;
/*      */     } 
/* 1130 */     double dx = 0.0D;
/* 1131 */     double dy = 0.0D;
/* 1132 */     double dz = 0.0D;
/*      */     
/* 1134 */     if (random_motion > 0.0F) {
/*      */       
/* 1136 */       dx = this.rand.nextGaussian() * random_motion;
/* 1137 */       dy = this.rand.nextGaussian() * random_motion;
/* 1138 */       dz = this.rand.nextGaussian() * random_motion;
/*      */     } 
/*      */     
/* 1141 */     this.worldObj.spawnParticle(kind, this.posX, effective_pos_y + this.height + (this.rand.nextFloat() * 0.2F), this.posZ, dx, dy, dz);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnSteamParticles(int num_particles) {
/* 1148 */     spawnParticles(EnumParticle.explode, num_particles, 0.02F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnSmokeParticles(int num_particles) {
/* 1155 */     spawnParticles(EnumParticle.smoke, num_particles, 0.02F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnLargeSmokeParticles(int num_particles) {
/* 1161 */     spawnParticles(EnumParticle.largesmoke, num_particles, 0.02F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnCurseEffectLearnedParticles(int num_particles) {
/* 1169 */     spawnParticles(EnumParticle.mobSpell, num_particles);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnRandomlyLocatedParticle(EnumParticle kind, double vel_x_or_red, double vel_y_or_green, double vel_z_or_blue) {
/* 1174 */     double foot_pos_y = this.posY;
/*      */     
/* 1176 */     if (this instanceof EntityLivingBase) {
/*      */       
/* 1178 */       EntityLivingBase entity_living_base = (EntityLivingBase)this;
/*      */       
/* 1180 */       foot_pos_y = entity_living_base.getFootPosY();
/*      */     } 
/*      */     
/* 1183 */     this.worldObj.spawnParticle(kind, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, foot_pos_y + 0.10000000149011612D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, vel_x_or_red, vel_y_or_green, vel_z_or_blue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void entityFX(EnumEntityFX kind, SignalData data) {
/* 1191 */     if (this.worldObj.isRemote) {
/*      */       
/* 1193 */       Minecraft.setErrorMessage("entityFX: only valid on server (" + kind + ")");
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1198 */     if (isEntityPlayer() && ((getAsPlayer()).username == null || isZevimrgvInTournament())) {
/*      */       return;
/*      */     }
/*      */     
/* 1202 */     Packet85SimpleSignal packet = (new Packet85SimpleSignal(EnumSignal.entity_fx, kind)).setEntityID(this.entityId);
/*      */     
/* 1204 */     if (data != null) {
/* 1205 */       packet.addData(data);
/*      */     }
/* 1207 */     ((WorldServer)this.worldObj).sendPacketToAllAssociatedPlayers(this, packet);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void entityFX(EnumEntityFX kind) {
/* 1214 */     entityFX(kind, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacketToAllAssociatedPlayers(Packet packet) {
/* 1220 */     if (this.worldObj.isRemote) {
/*      */       
/* 1222 */       Minecraft.setErrorMessage("sendPacketToAllAssociatedPlayers: cannot be called on client");
/*      */       
/*      */       return;
/*      */     } 
/* 1226 */     ((WorldServer)this.worldObj).sendPacketToAllAssociatedPlayers(this, packet);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayersTrackingEntity(Packet packet) {
/* 1232 */     if (onServer()) {
/* 1233 */       getWorldServer().sendPacketToAllPlayersTrackingEntity(this, packet);
/*      */     } else {
/* 1235 */       Minecraft.setErrorMessage("sendPacketToAllPlayersTrackingEntity: cannot be called on client");
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isDecoy() {
/* 1240 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityPlayer() {
/* 1245 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityFX() {
/* 1250 */     return this instanceof EntityFX;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInFire() {
/* 1255 */     if ((this.worldObj.isUnderworld() && this.boundingBox.minY <= 3.0D) || (this.worldObj.isTheNether() && (this.boundingBox.minY <= 1.0D || this.boundingBox.maxY >= 123.0D)))
/*      */     {
/* 1257 */       if (this.worldObj.doesBoundingBoxContainBlock(this.boundingBox.expand(0.001D, 0.001D, 0.001D), Block.mantleOrCore.blockID, -1)) {
/* 1258 */         return true;
/*      */       }
/*      */     }
/* 1261 */     return this.worldObj.isBoundingBoxBurning(this.boundingBox.contract(0.001D, 0.001D, 0.001D), false);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClientOrServerString() {
/* 1266 */     return onClient() ? "client" : (onServer() ? "server" : "unknown");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean applyLimitsForX(double[] limits) {
/* 1272 */     if (!apply_MITE_bb_limits_checking) {
/* 1273 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1278 */     if (Double.isNaN(limits[0]) && Double.isNaN(limits[1])) {
/* 1279 */       return false;
/*      */     }
/* 1281 */     if (!Double.isNaN(limits[0])) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1286 */       limits[2] = limits[0] - this.boundingBox.maxX;
/*      */       
/* 1288 */       this.boundingBox.maxX = limits[0];
/* 1289 */       this.boundingBox.minX = limits[0] - this.width;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1296 */       limits[2] = limits[1] - this.boundingBox.minX;
/*      */       
/* 1298 */       this.boundingBox.minX = limits[1];
/* 1299 */       this.boundingBox.maxX = limits[1] + this.width;
/*      */     } 
/*      */     
/* 1302 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean applyLimitsForY(double[] limits) {
/* 1308 */     if (!apply_MITE_bb_limits_checking) {
/* 1309 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1314 */     if (Double.isNaN(limits[0]) && Double.isNaN(limits[1])) {
/* 1315 */       return false;
/*      */     }
/* 1317 */     if (!Double.isNaN(limits[0])) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1322 */       limits[2] = limits[0] - this.boundingBox.maxY;
/*      */       
/* 1324 */       this.boundingBox.maxY = limits[0];
/* 1325 */       this.boundingBox.minY = limits[0] - this.height;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1332 */       limits[2] = limits[1] - this.boundingBox.minY;
/*      */       
/* 1334 */       this.boundingBox.minY = limits[1];
/* 1335 */       this.boundingBox.maxY = limits[1] + this.height;
/*      */     } 
/*      */     
/* 1338 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean applyLimitsForZ(double[] limits) {
/* 1344 */     if (!apply_MITE_bb_limits_checking) {
/* 1345 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1350 */     if (Double.isNaN(limits[0]) && Double.isNaN(limits[1])) {
/* 1351 */       return false;
/*      */     }
/* 1353 */     if (!Double.isNaN(limits[0])) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1358 */       limits[2] = limits[0] - this.boundingBox.maxZ;
/*      */       
/* 1360 */       this.boundingBox.maxZ = limits[0];
/* 1361 */       this.boundingBox.minZ = limits[0] - this.width;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1368 */       limits[2] = limits[1] - this.boundingBox.minZ;
/*      */       
/* 1370 */       this.boundingBox.minZ = limits[1];
/* 1371 */       this.boundingBox.maxZ = limits[1] + this.width;
/*      */     } 
/*      */     
/* 1374 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntity(double par1, double par3, double par5) {
/* 1382 */     if (isDecoy()) {
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1404 */     if (!isEntityFX() && this.worldObj.getChunkFromBlockCoords(getBlockPosX(), getBlockPosZ()).isEmpty()) {
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
/* 1419 */     if (this.posX + par1 < this.worldObj.min_entity_pos_xz) {
/* 1420 */       par1 = this.worldObj.min_entity_pos_xz - this.posX;
/* 1421 */     } else if (this.posX + par1 > this.worldObj.max_entity_pos_xz) {
/* 1422 */       par1 = this.worldObj.max_entity_pos_xz - this.posX;
/*      */     } 
/* 1424 */     if (this.posY + par3 < -255.0D) {
/* 1425 */       par3 = -255.0D - this.posY;
/* 1426 */     } else if (this.posY + par3 > 255.0D) {
/* 1427 */       par3 = 255.0D - this.posY;
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
/* 1439 */     if (this.posZ + par5 < this.worldObj.min_entity_pos_xz) {
/* 1440 */       par5 = this.worldObj.min_entity_pos_xz - this.posZ;
/* 1441 */     } else if (this.posZ + par5 > this.worldObj.max_entity_pos_xz) {
/* 1442 */       par5 = this.worldObj.max_entity_pos_xz - this.posZ;
/*      */     } 
/*      */     
/* 1445 */     if (DedicatedServer.getTournamentArenaRadius() > 0 && this instanceof EntityPlayer) {
/*      */       
/* 1447 */       int domain_radius = DedicatedServer.getTournamentArenaRadius();
/*      */ 
/*      */       
/* 1450 */       int spawn_x = this.worldObj.worldInfo.getSpawnX();
/* 1451 */       int spawn_z = this.worldObj.worldInfo.getSpawnZ();
/*      */       
/* 1453 */       float min_x = (spawn_x - domain_radius);
/* 1454 */       float max_x = (spawn_x + domain_radius);
/*      */       
/* 1456 */       float min_z = (spawn_z - domain_radius);
/* 1457 */       float max_z = (spawn_z + domain_radius);
/*      */       
/* 1459 */       if (this.posX + par1 < min_x) {
/* 1460 */         par1 = min_x - this.posX;
/* 1461 */       } else if (this.posX + par1 > max_x) {
/* 1462 */         par1 = max_x - this.posX;
/*      */       } 
/* 1464 */       if (this.posZ + par5 < min_z) {
/* 1465 */         par5 = min_z - this.posZ;
/* 1466 */       } else if (this.posZ + par5 > max_z) {
/* 1467 */         par5 = max_z - this.posZ;
/*      */       } 
/*      */     } 
/* 1470 */     if (this.noClip) {
/*      */       
/* 1472 */       this.boundingBox.offset(par1, par3, par5);
/* 1473 */       this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
/* 1474 */       this.posY = this.boundingBox.minY + this.yOffset - this.ySize;
/* 1475 */       this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;
/*      */     }
/*      */     else {
/*      */       
/* 1479 */       this.worldObj.theProfiler.startSection("move");
/* 1480 */       this.ySize *= 0.4F;
/* 1481 */       double var7 = this.posX;
/* 1482 */       double var9 = this.posY;
/* 1483 */       double var11 = this.posZ;
/*      */ 
/*      */       
/* 1486 */       if (this.isInWeb && !(this instanceof EntityFallingSand)) {
/*      */         
/* 1488 */         this.isInWeb = false;
/* 1489 */         par1 *= 0.25D;
/* 1490 */         par3 *= 0.05000000074505806D;
/* 1491 */         par5 *= 0.25D;
/* 1492 */         this.motionX = 0.0D;
/* 1493 */         this.motionY = 0.0D;
/* 1494 */         this.motionZ = 0.0D;
/*      */       } 
/*      */       
/* 1497 */       double var13 = par1;
/* 1498 */       double var15 = par3;
/* 1499 */       double var17 = par5;
/* 1500 */       AxisAlignedBB var19 = this.boundingBox.copy();
/* 1501 */       boolean var20 = (this.onGround && isSneaking() && this instanceof EntityPlayer);
/*      */       
/* 1503 */       if (var20) {
/*      */         double var21;
/*      */ 
/*      */         
/* 1507 */         for (var21 = 0.05D; par1 != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.getOffsetBoundingBox(par1, -1.0D, 0.0D)).isEmpty(); var13 = par1) {
/*      */           
/* 1509 */           if (par1 < var21 && par1 >= -var21) {
/*      */             
/* 1511 */             par1 = 0.0D;
/*      */           }
/* 1513 */           else if (par1 > 0.0D) {
/*      */             
/* 1515 */             par1 -= var21;
/*      */           }
/*      */           else {
/*      */             
/* 1519 */             par1 += var21;
/*      */           } 
/*      */         } 
/*      */         
/* 1523 */         for (; par5 != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.getOffsetBoundingBox(0.0D, -1.0D, par5)).isEmpty(); var17 = par5) {
/*      */           
/* 1525 */           if (par5 < var21 && par5 >= -var21) {
/*      */             
/* 1527 */             par5 = 0.0D;
/*      */           }
/* 1529 */           else if (par5 > 0.0D) {
/*      */             
/* 1531 */             par5 -= var21;
/*      */           }
/*      */           else {
/*      */             
/* 1535 */             par5 += var21;
/*      */           } 
/*      */         } 
/*      */         
/* 1539 */         while (par1 != 0.0D && par5 != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.getOffsetBoundingBox(par1, -1.0D, par5)).isEmpty()) {
/*      */           
/* 1541 */           if (par1 < var21 && par1 >= -var21) {
/*      */             
/* 1543 */             par1 = 0.0D;
/*      */           }
/* 1545 */           else if (par1 > 0.0D) {
/*      */             
/* 1547 */             par1 -= var21;
/*      */           }
/*      */           else {
/*      */             
/* 1551 */             par1 += var21;
/*      */           } 
/*      */           
/* 1554 */           if (par5 < var21 && par5 >= -var21) {
/*      */             
/* 1556 */             par5 = 0.0D;
/*      */           }
/* 1558 */           else if (par5 > 0.0D) {
/*      */             
/* 1560 */             par5 -= var21;
/*      */           }
/*      */           else {
/*      */             
/* 1564 */             par5 += var21;
/*      */           } 
/*      */           
/* 1567 */           var13 = par1;
/* 1568 */           var17 = par5;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1580 */       List<AxisAlignedBB> var36 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.addCoord(par1, par3, par5));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1585 */       double[] limits = new double[3];
/*      */       
/* 1587 */       limits[0] = Double.NaN;
/* 1588 */       limits[1] = Double.NaN;
/*      */       
/* 1590 */       for (int var22 = 0; var22 < var36.size(); var22++)
/*      */       {
/*      */         
/* 1593 */         par3 = ((AxisAlignedBB)var36.get(var22)).calculateYOffset(this.boundingBox, par3, limits);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1598 */       if (!applyLimitsForY(limits)) {
/* 1599 */         this.boundingBox.offset(0.0D, par3, 0.0D);
/* 1600 */       } else if (Minecraft.inDevMode() && limits[2] != par3) {
/* 1601 */         Minecraft.setErrorMessage("moveEntity: par3=" + par3 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */       } 
/* 1603 */       if (!this.field_70135_K && var15 != par3) {
/*      */         
/* 1605 */         par5 = 0.0D;
/* 1606 */         par3 = 0.0D;
/* 1607 */         par1 = 0.0D;
/*      */       } 
/*      */       
/* 1610 */       boolean var35 = (this.onGround || (var15 != par3 && var15 < 0.0D));
/*      */ 
/*      */       
/* 1613 */       limits[0] = Double.NaN;
/* 1614 */       limits[1] = Double.NaN;
/*      */       int var23;
/* 1616 */       for (var23 = 0; var23 < var36.size(); var23++)
/*      */       {
/*      */         
/* 1619 */         par1 = ((AxisAlignedBB)var36.get(var23)).calculateXOffset(this.boundingBox, par1, limits);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1624 */       if (!applyLimitsForX(limits)) {
/* 1625 */         this.boundingBox.offset(par1, 0.0D, 0.0D);
/* 1626 */       } else if (Minecraft.inDevMode() && limits[2] != par1) {
/* 1627 */         Minecraft.setErrorMessage("moveEntity: par1=" + par1 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */       } 
/* 1629 */       if (!this.field_70135_K && var13 != par1) {
/*      */         
/* 1631 */         par5 = 0.0D;
/* 1632 */         par3 = 0.0D;
/* 1633 */         par1 = 0.0D;
/*      */       } 
/*      */       
/* 1636 */       limits[0] = Double.NaN;
/* 1637 */       limits[1] = Double.NaN;
/*      */       
/* 1639 */       for (var23 = 0; var23 < var36.size(); var23++)
/*      */       {
/*      */         
/* 1642 */         par5 = ((AxisAlignedBB)var36.get(var23)).calculateZOffset(this.boundingBox, par5, limits);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1647 */       if (!applyLimitsForZ(limits)) {
/* 1648 */         this.boundingBox.offset(0.0D, 0.0D, par5);
/* 1649 */       } else if (Minecraft.inDevMode() && limits[2] != par5) {
/* 1650 */         Minecraft.setErrorMessage("moveEntity: par5=" + par5 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */       } 
/* 1652 */       if (!this.field_70135_K && var17 != par5) {
/*      */         
/* 1654 */         par5 = 0.0D;
/* 1655 */         par3 = 0.0D;
/* 1656 */         par1 = 0.0D;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1664 */       if (this.stepHeight > 0.0F && var35 && (var20 || this.ySize < 0.05F) && (var13 != par1 || var17 != par5)) {
/*      */         
/* 1666 */         double d3 = par1;
/* 1667 */         double d1 = par3;
/* 1668 */         double d2 = par5;
/* 1669 */         par1 = var13;
/* 1670 */         par3 = this.stepHeight;
/* 1671 */         par5 = var17;
/* 1672 */         AxisAlignedBB var29 = this.boundingBox.copy();
/* 1673 */         this.boundingBox.setBB(var19);
/* 1674 */         var36 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.addCoord(var13, par3, var17));
/*      */         
/* 1676 */         limits[0] = Double.NaN;
/* 1677 */         limits[1] = Double.NaN;
/*      */         int var30;
/* 1679 */         for (var30 = 0; var30 < var36.size(); var30++)
/*      */         {
/*      */           
/* 1682 */           par3 = ((AxisAlignedBB)var36.get(var30)).calculateYOffset(this.boundingBox, par3, limits);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1687 */         if (!applyLimitsForY(limits)) {
/* 1688 */           this.boundingBox.offset(0.0D, par3, 0.0D);
/* 1689 */         } else if (Minecraft.inDevMode() && limits[2] != par3) {
/* 1690 */           Minecraft.setErrorMessage("moveEntity: par3=" + par3 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */         } 
/* 1692 */         if (!this.field_70135_K && var15 != par3) {
/*      */           
/* 1694 */           par5 = 0.0D;
/* 1695 */           par3 = 0.0D;
/* 1696 */           par1 = 0.0D;
/*      */         } 
/*      */         
/* 1699 */         limits[0] = Double.NaN;
/* 1700 */         limits[1] = Double.NaN;
/*      */         
/* 1702 */         for (var30 = 0; var30 < var36.size(); var30++)
/*      */         {
/*      */           
/* 1705 */           par1 = ((AxisAlignedBB)var36.get(var30)).calculateXOffset(this.boundingBox, par1, limits);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1710 */         if (!applyLimitsForX(limits)) {
/* 1711 */           this.boundingBox.offset(par1, 0.0D, 0.0D);
/* 1712 */         } else if (Minecraft.inDevMode() && limits[2] != par1) {
/* 1713 */           Minecraft.setErrorMessage("moveEntity: par1=" + par1 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */         } 
/* 1715 */         if (!this.field_70135_K && var13 != par1) {
/*      */           
/* 1717 */           par5 = 0.0D;
/* 1718 */           par3 = 0.0D;
/* 1719 */           par1 = 0.0D;
/*      */         } 
/*      */         
/* 1722 */         limits[0] = Double.NaN;
/* 1723 */         limits[1] = Double.NaN;
/*      */         
/* 1725 */         for (var30 = 0; var30 < var36.size(); var30++)
/*      */         {
/*      */           
/* 1728 */           par5 = ((AxisAlignedBB)var36.get(var30)).calculateZOffset(this.boundingBox, par5, limits);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1733 */         if (!applyLimitsForZ(limits)) {
/* 1734 */           this.boundingBox.offset(0.0D, 0.0D, par5);
/* 1735 */         } else if (Minecraft.inDevMode() && limits[2] != par5) {
/* 1736 */           Minecraft.setErrorMessage("moveEntity: par5=" + par5 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */         } 
/* 1738 */         if (!this.field_70135_K && var17 != par5) {
/*      */           
/* 1740 */           par5 = 0.0D;
/* 1741 */           par3 = 0.0D;
/* 1742 */           par1 = 0.0D;
/*      */         } 
/*      */         
/* 1745 */         if (!this.field_70135_K && var15 != par3) {
/*      */           
/* 1747 */           par5 = 0.0D;
/* 1748 */           par3 = 0.0D;
/* 1749 */           par1 = 0.0D;
/*      */         }
/*      */         else {
/*      */           
/* 1753 */           par3 = -this.stepHeight;
/*      */ 
/*      */           
/* 1756 */           var36 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.addCoord(par1, par3, par5));
/*      */           
/* 1758 */           limits[0] = Double.NaN;
/* 1759 */           limits[1] = Double.NaN;
/*      */           
/* 1761 */           for (var30 = 0; var30 < var36.size(); var30++)
/*      */           {
/*      */             
/* 1764 */             par3 = ((AxisAlignedBB)var36.get(var30)).calculateYOffset(this.boundingBox, par3, limits);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1769 */           if (!applyLimitsForY(limits)) {
/* 1770 */             this.boundingBox.offset(0.0D, par3, 0.0D);
/* 1771 */           } else if (Minecraft.inDevMode() && limits[2] != par3) {
/* 1772 */             Minecraft.setErrorMessage("moveEntity: par3=" + par3 + " vs limits[2]=" + limits[2] + " (" + getEntityName() + ")");
/*      */           } 
/*      */         } 
/* 1775 */         if (d3 * d3 + d2 * d2 >= par1 * par1 + par5 * par5) {
/*      */           
/* 1777 */           par1 = d3;
/* 1778 */           par3 = d1;
/* 1779 */           par5 = d2;
/* 1780 */           this.boundingBox.setBB(var29);
/*      */         } 
/*      */       } 
/*      */       
/* 1784 */       this.worldObj.theProfiler.endSection();
/* 1785 */       this.worldObj.theProfiler.startSection("rest");
/* 1786 */       this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
/* 1787 */       this.posY = this.boundingBox.minY + this.yOffset - this.ySize;
/* 1788 */       this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;
/* 1789 */       this.isCollidedHorizontally = (var13 != par1 || var17 != par5);
/* 1790 */       this.isCollidedVertically = (var15 != par3);
/* 1791 */       this.onGround = (var15 != par3 && var15 < 0.0D);
/* 1792 */       this.isCollided = (this.isCollidedHorizontally || this.isCollidedVertically);
/* 1793 */       updateFallState(par3, this.onGround);
/*      */       
/* 1795 */       if (var13 != par1)
/*      */       {
/* 1797 */         this.motionX = 0.0D;
/*      */       }
/*      */       
/* 1800 */       if (var15 != par3)
/*      */       {
/* 1802 */         this.motionY = 0.0D;
/*      */       }
/*      */       
/* 1805 */       if (var17 != par5)
/*      */       {
/* 1807 */         this.motionZ = 0.0D;
/*      */       }
/*      */       
/* 1810 */       double var37 = this.posX - var7;
/* 1811 */       double var25 = this.posY - var9;
/* 1812 */       double var27 = this.posZ - var11;
/*      */       
/* 1814 */       if (canTriggerWalking() && !var20 && this.ridingEntity == null) {
/*      */         
/* 1816 */         int var39 = MathHelper.floor_double(this.posX);
/* 1817 */         int var30 = MathHelper.floor_double(this.posY - 0.20000000298023224D - this.yOffset);
/* 1818 */         int var31 = MathHelper.floor_double(this.posZ);
/* 1819 */         int var32 = this.worldObj.getBlockId(var39, var30, var31);
/*      */         
/* 1821 */         if (var32 == 0) {
/*      */           
/* 1823 */           int var33 = this.worldObj.blockGetRenderType(var39, var30 - 1, var31);
/*      */           
/* 1825 */           if (var33 == 11 || var33 == 32 || var33 == 21)
/*      */           {
/* 1827 */             var32 = this.worldObj.getBlockId(var39, var30 - 1, var31);
/*      */           }
/*      */         } 
/*      */         
/* 1831 */         if (var32 != Block.ladder.blockID)
/*      */         {
/* 1833 */           var25 = 0.0D;
/*      */         }
/*      */         
/* 1836 */         this.distanceWalkedModified = (float)(this.distanceWalkedModified + MathHelper.sqrt_double(var37 * var37 + var27 * var27) * 0.6D);
/* 1837 */         this.distanceWalkedOnStepModified = (float)(this.distanceWalkedOnStepModified + MathHelper.sqrt_double(var37 * var37 + var25 * var25 + var27 * var27) * 0.6D);
/*      */         
/* 1839 */         if (this.distanceWalkedOnStepModified > this.nextStepDistance && var32 > 0) {
/*      */           
/* 1841 */           this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
/*      */           
/* 1843 */           if (!isZevimrgvInTournament()) {
/* 1844 */             if (isInWater()) {
/*      */               
/* 1846 */               float var42 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.35F;
/*      */               
/* 1848 */               if (var42 > 1.0F)
/*      */               {
/* 1850 */                 var42 = 1.0F;
/*      */               }
/*      */               
/* 1853 */               playSound("liquid.swim", var42, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/*      */             }
/* 1855 */             else if (!(this instanceof EntityPlayer) || !((EntityPlayer)this).inBed()) {
/*      */               
/* 1857 */               if (this.ticks_on_ground > 5 && !(this instanceof EntityGelatinousCube)) {
/* 1858 */                 playStepSound(var39, var30, var31, var32);
/*      */               }
/*      */             } 
/*      */           }
/* 1862 */           Block.blocksList[var32].onEntityWalking(this.worldObj, var39, var30, var31, this);
/*      */         }
/*      */         else {
/*      */           
/* 1866 */           Block block = Block.blocksList[var32];
/*      */           
/* 1868 */           if (block instanceof BlockUnderminable) {
/*      */             
/* 1870 */             block.onEntityWalking(this.worldObj, var39, var30, var31, this);
/*      */           }
/*      */           else {
/*      */             
/* 1874 */             block = Block.blocksList[this.worldObj.getBlockId(var39, var30 + 1, var31)];
/*      */             
/* 1876 */             if (block == Block.snow)
/*      */             {
/* 1878 */               block.onEntityWalking(this.worldObj, var39, var30 + 1, var31, this);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1891 */         doBlockCollisions();
/*      */       }
/* 1893 */       catch (Throwable var34) {
/*      */         
/* 1895 */         CrashReport var41 = CrashReport.makeCrashReport(var34, "Checking entity tile collision");
/* 1896 */         CrashReportCategory var38 = var41.makeCategory("Entity being checked for collision");
/* 1897 */         addEntityCrashInfo(var38);
/* 1898 */         throw new ReportedException(var41);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1929 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void causeQuenchEffect() {
/* 1935 */     entityFX(EnumEntityFX.steam_with_hiss);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnSingleSteamParticle(boolean with_hiss_sound) {
/* 1940 */     if (with_hiss_sound) {
/* 1941 */       entityFX(EnumEntityFX.single_steam_particle_with_hiss);
/*      */     } else {
/* 1943 */       Minecraft.setErrorMessage("spawnSingleSmokeParticle: signal without hiss not handled yet");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void doBlockCollisions() {
/* 1951 */     int var1 = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
/* 1952 */     int var2 = MathHelper.floor_double(this.boundingBox.minY + 0.001D);
/* 1953 */     int var3 = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
/* 1954 */     int var4 = MathHelper.floor_double(this.boundingBox.maxX - 0.001D);
/* 1955 */     int var5 = MathHelper.floor_double(this.boundingBox.maxY - 0.001D);
/* 1956 */     int var6 = MathHelper.floor_double(this.boundingBox.maxZ - 0.001D);
/*      */     
/* 1958 */     if (var2 > 255 || var5 < 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1964 */     for (int var7 = var1; var7 <= var4; var7++) {
/*      */       
/* 1966 */       for (int var8 = var2; var8 <= var5; var8++) {
/*      */         
/* 1968 */         for (int var9 = var3; var9 <= var6; var9++) {
/*      */           
/* 1970 */           int var10 = this.worldObj.getBlockId(var7, var8, var9);
/*      */           
/* 1972 */           if (var10 > 0) {
/*      */             
/*      */             try {
/*      */               
/* 1976 */               Block.blocksList[var10].onEntityCollidedWithBlock(this.worldObj, var7, var8, var9, this);
/* 1977 */               onCollidedWithBlock(this.worldObj, Block.blocksList[var10], var7, var8, var9);
/*      */             }
/* 1979 */             catch (Throwable var14) {
/*      */               
/* 1981 */               CrashReport var12 = CrashReport.makeCrashReport(var14, "Colliding entity with tile");
/* 1982 */               CrashReportCategory var13 = var12.makeCategory("Tile being collided with");
/* 1983 */               CrashReportCategory.addBlockCrashInfo(var13, var7, var8, var9, var10, this.worldObj.getBlockMetadata(var7, var8, var9));
/* 1984 */               throw new ReportedException(var12);
/*      */             } 
/*      */           }
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
/*      */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 1998 */     if (isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 2001 */     StepSound var5 = (Block.blocksList[par4]).stepSound;
/*      */     
/* 2003 */     if (this.worldObj.getBlockId(par1, par2 + 1, par3) == Block.snow.blockID) {
/*      */       
/* 2005 */       var5 = Block.snow.stepSound;
/* 2006 */       playSound(var5.getStepSound(), var5.getVolume() * 0.15F, var5.getPitch());
/*      */     }
/* 2008 */     else if (!(Block.blocksList[par4]).blockMaterial.isLiquid()) {
/*      */       
/* 2010 */       playSound(var5.getStepSound(), var5.getVolume() * 0.15F, var5.getPitch());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(String par1Str, float par2, float par3) {
/* 2017 */     if (isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 2020 */     this.worldObj.playSoundAtEntity(this, par1Str, par2, par3);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playLongDistanceSound(String par1Str, float par2, float par3) {
/* 2025 */     this.worldObj.playLongDistanceSoundAtEntity(this, par1Str, par2, par3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/* 2034 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateFallState(double par1, boolean par3) {
/* 2043 */     if (par3) {
/*      */       
/* 2045 */       if (this.fallDistance > 0.0F)
/*      */       {
/* 2047 */         fall(this.fallDistance);
/* 2048 */         this.fallDistance = 0.0F;
/*      */       }
/*      */     
/* 2051 */     } else if (par1 < 0.0D) {
/*      */       
/* 2053 */       this.fallDistance = (float)(this.fallDistance - par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getBoundingBox() {
/* 2062 */     return null;
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
/*      */   protected void dealFireDamage(int par1) {
/* 2074 */     if (isHarmedByFire() && (this instanceof EntityLivingBase || this.ticksExisted % 10 == 0))
/*      */     {
/*      */       
/* 2077 */       attackEntityFrom(new Damage(DamageSource.inFire, par1));
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
/*      */   public boolean canDouseFire() {
/* 2089 */     if (hasModelItem()) {
/* 2090 */       return getModelItem().canDouseFire();
/*      */     }
/* 2092 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCatchFire() {
/* 2098 */     if (hasModelItem()) {
/* 2099 */       return getModelItem().canCatchFire();
/*      */     }
/* 2101 */     Minecraft.setErrorMessage("canCatchFire: entity must override this function or have a model item (" + getEntityName() + ")");
/* 2102 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHarmedByFire() {
/* 2108 */     if (hasModelItem()) {
/* 2109 */       return getModelItem().isHarmedByFire();
/*      */     }
/* 2111 */     Minecraft.setErrorMessage("isHarmedByFire: entity must override this function or have a model item (" + getEntityName() + ")");
/* 2112 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHarmedByLava() {
/* 2118 */     if (hasModelItem()) {
/* 2119 */       return getModelItem().isHarmedByLava();
/*      */     }
/* 2121 */     Minecraft.setErrorMessage("isHarmedByLava: entity must override this function or have a model item (" + getEntityName() + ")");
/* 2122 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByPepsin() {
/* 2127 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByAcid() {
/* 2132 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fall(float par1) {
/* 2140 */     if (this.riddenByEntity != null)
/*      */     {
/* 2142 */       this.riddenByEntity.fall(par1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWet() {
/* 2151 */     return (this.inWater || this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) || this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.height), MathHelper.floor_double(this.posZ)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInWater() {
/* 2160 */     return this.inWater;
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnSplashParticles() {
/* 2165 */     float var2 = MathHelper.floor_double(this.boundingBox.minY);
/*      */ 
/*      */     
/*      */     int var3;
/*      */     
/* 2170 */     for (var3 = 0; var3 < 1.0F + this.width * 20.0F; var3++) {
/*      */       
/* 2172 */       float var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 2173 */       float var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 2174 */       this.worldObj.spawnParticle(EnumParticle.bubble, this.posX + var4, (var2 + 1.0F), this.posZ + var5, this.motionX, this.motionY - (this.rand.nextFloat() * 0.2F), this.motionZ);
/*      */     } 
/*      */     
/* 2177 */     for (var3 = 0; var3 < 1.0F + this.width * 20.0F; var3++) {
/*      */       
/* 2179 */       float var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 2180 */       float var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 2181 */       this.worldObj.spawnParticle(EnumParticle.splash, this.posX + var4, (var2 + 1.0F), this.posZ + var5, this.motionX, this.motionY, this.motionZ);
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
/*      */   public boolean handleWaterMovement() {
/* 2204 */     double expansion_y = -0.4D;
/*      */     
/* 2206 */     if (this.boundingBox.maxY - this.boundingBox.minY < 0.4D) {
/* 2207 */       expansion_y = (this.boundingBox.maxY - this.boundingBox.minY) * -0.5D;
/*      */     }
/*      */     
/* 2210 */     if (this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, expansion_y, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.water, this)) {
/*      */       
/* 2212 */       if (!this.inWater && !this.firstUpdate)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2243 */         if (!this.worldObj.isRemote) {
/* 2244 */           entityFX(EnumEntityFX.splash);
/*      */         }
/*      */       }
/* 2247 */       this.fallDistance = 0.0F;
/* 2248 */       this.inWater = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2256 */       extinguish();
/*      */ 
/*      */       
/* 2259 */       this.ticks_since_last_wet = 0;
/*      */     }
/*      */     else {
/*      */       
/* 2263 */       this.inWater = false;
/*      */     } 
/*      */     
/* 2266 */     return this.inWater;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInsideOfMaterial(Material par1Material) {
/* 2274 */     double var2 = this.posY + getEyeHeight();
/* 2275 */     int var4 = MathHelper.floor_double(this.posX);
/* 2276 */     int var5 = MathHelper.floor_float(MathHelper.floor_double(var2));
/* 2277 */     int var6 = MathHelper.floor_double(this.posZ);
/* 2278 */     int var7 = this.worldObj.getBlockId(var4, var5, var6);
/*      */     
/* 2280 */     if (var7 != 0 && (Block.blocksList[var7]).blockMaterial == par1Material) {
/*      */       
/* 2282 */       float var8 = BlockFluid.getFluidHeightPercent(this.worldObj.getBlockMetadata(var4, var5, var6)) - 0.11111111F;
/* 2283 */       float var9 = (var5 + 1) - var8;
/* 2284 */       return (var2 < var9);
/*      */     } 
/*      */ 
/*      */     
/* 2288 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInsideOfMaterial(Material par1Material, float offset_y) {
/* 2295 */     double var2 = this.posY + getEyeHeight() + offset_y;
/* 2296 */     int var4 = MathHelper.floor_double(this.posX);
/* 2297 */     int var5 = MathHelper.floor_float(MathHelper.floor_double(var2));
/* 2298 */     int var6 = MathHelper.floor_double(this.posZ);
/* 2299 */     int var7 = this.worldObj.getBlockId(var4, var5, var6);
/*      */     
/* 2301 */     if (var7 != 0 && (Block.blocksList[var7]).blockMaterial == par1Material) {
/*      */       
/* 2303 */       float var8 = BlockFluid.getFluidHeightPercent(this.worldObj.getBlockMetadata(var4, var5, var6)) - 0.11111111F;
/* 2304 */       float var9 = (var5 + 1) - var8;
/* 2305 */       return (var2 < var9);
/*      */     } 
/*      */ 
/*      */     
/* 2309 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/* 2315 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handleLavaMovement() {
/* 2323 */     return this.worldObj.isMaterialInBB(this.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveFlying(float par1, float par2, float par3) {
/* 2331 */     float var4 = par1 * par1 + par2 * par2;
/*      */     
/* 2333 */     if (var4 >= 1.0E-4F) {
/*      */       
/* 2335 */       var4 = MathHelper.sqrt_float(var4);
/*      */       
/* 2337 */       if (var4 < 1.0F)
/*      */       {
/* 2339 */         var4 = 1.0F;
/*      */       }
/*      */       
/* 2342 */       var4 = par3 / var4;
/* 2343 */       par1 *= var4;
/* 2344 */       par2 *= var4;
/* 2345 */       float var5 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
/* 2346 */       float var6 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/* 2347 */       this.motionX += (par1 * var6 - par2 * var5);
/* 2348 */       this.motionZ += (par2 * var6 + par1 * var5);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBrightnessForRender(float par1) {
/* 2354 */     int var2 = MathHelper.floor_double(this.posX);
/* 2355 */     int var3 = MathHelper.floor_double(this.posZ);
/*      */     
/* 2357 */     if (this.worldObj.blockExists(var2, 0, var3)) {
/*      */       
/* 2359 */       double var4 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
/* 2360 */       int var6 = MathHelper.floor_double(this.posY - this.yOffset + var4);
/* 2361 */       return this.worldObj.getLightBrightnessForSkyBlocks(var2, var6, var3, 0);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2366 */     return 0;
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
/*      */   public float getBrightness(float par1) {
/* 2392 */     int var2 = MathHelper.floor_double(this.posX);
/* 2393 */     int var3 = MathHelper.floor_double(this.posZ);
/*      */     
/* 2395 */     if (this.worldObj.blockExists(var2, 0, var3)) {
/*      */       
/* 2397 */       double var4 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
/* 2398 */       int var6 = MathHelper.floor_double(this.posY - this.yOffset + var4);
/*      */       
/* 2400 */       if (!this.worldObj.isRemote && (this.worldObj.getBiomeGenForCoords(var2, var3)).rainfall == 0.0F && !this.worldObj.isBloodMoon24HourPeriod()) {
/* 2401 */         this.worldObj.ignore_rain_and_thunder_for_next_BLV = true;
/*      */       }
/* 2403 */       return this.worldObj.getLightBrightness(var2, var6, var3);
/*      */     } 
/*      */ 
/*      */     
/* 2407 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorld(World par1World) {
/* 2416 */     this.worldObj = par1World;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotation(double par1, double par3, double par5, float par7, float par8) {
/* 2424 */     this.prevPosX = this.posX = par1;
/* 2425 */     this.prevPosY = this.posY = par3;
/* 2426 */     this.prevPosZ = this.posZ = par5;
/* 2427 */     this.prevRotationYaw = this.rotationYaw = par7;
/* 2428 */     this.prevRotationPitch = this.rotationPitch = par8;
/* 2429 */     this.ySize = 0.0F;
/* 2430 */     double var9 = (this.prevRotationYaw - par7);
/*      */     
/* 2432 */     if (var9 < -180.0D)
/*      */     {
/* 2434 */       this.prevRotationYaw += 360.0F;
/*      */     }
/*      */     
/* 2437 */     if (var9 >= 180.0D)
/*      */     {
/* 2439 */       this.prevRotationYaw -= 360.0F;
/*      */     }
/*      */     
/* 2442 */     setPosition(this.posX, this.posY, this.posZ);
/* 2443 */     setRotation(par7, par8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocationAndAngles(double par1, double par3, double par5, float par7, float par8) {
/* 2451 */     this.lastTickPosX = this.prevPosX = this.posX = par1;
/* 2452 */     this.lastTickPosY = this.prevPosY = this.posY = par3 + this.yOffset;
/* 2453 */     this.lastTickPosZ = this.prevPosZ = this.posZ = par5;
/* 2454 */     this.rotationYaw = par7;
/* 2455 */     this.rotationPitch = par8;
/* 2456 */     setPosition(this.posX, this.posY, this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDistanceToEntity(Entity par1Entity) {
/* 2464 */     float var2 = (float)(this.posX - par1Entity.posX);
/* 2465 */     float var3 = (float)(this.posY - par1Entity.posY);
/* 2466 */     float var4 = (float)(this.posZ - par1Entity.posZ);
/* 2467 */     return MathHelper.sqrt_float(var2 * var2 + var3 * var3 + var4 * var4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistanceSq(double par1, double par3, double par5) {
/* 2475 */     double var7 = this.posX - par1;
/* 2476 */     double var9 = this.posY - par3;
/* 2477 */     double var11 = this.posZ - par5;
/* 2478 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistance(double par1, double par3, double par5) {
/* 2486 */     double var7 = this.posX - par1;
/* 2487 */     double var9 = this.posY - par3;
/* 2488 */     double var11 = this.posZ - par5;
/* 2489 */     return MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDistanceSqToBlock(int x, int y, int z) {
/* 2494 */     double dx = this.posX - x;
/* 2495 */     double dy = this.posY - y;
/* 2496 */     double dz = this.posZ - z;
/*      */     
/* 2498 */     return dx * dx + dy * dy + dz * dz;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistanceSqToBlock(int x, int z) {
/* 2504 */     double dx = this.posX - x;
/* 2505 */     double dz = this.posZ - z;
/*      */     
/* 2507 */     return dx * dx + dz * dz;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistanceSqToEntity(Entity par1Entity) {
/* 2515 */     if (this.worldObj.isRemote) {
/*      */       
/* 2517 */       double effective_dy = ((this instanceof EntityLivingBase) ? ((EntityLivingBase)this).getFootPosY() : this.posY) - ((par1Entity instanceof EntityLivingBase) ? ((EntityLivingBase)par1Entity).getFootPosY() : par1Entity.posY);
/*      */       
/* 2519 */       double dx = this.posX - par1Entity.posX;
/* 2520 */       double dz = this.posZ - par1Entity.posZ;
/*      */       
/* 2522 */       return dx * dx + effective_dy * effective_dy + dz * dz;
/*      */     } 
/*      */     
/* 2525 */     double var2 = this.posX - par1Entity.posX;
/* 2526 */     double var4 = this.posY - par1Entity.posY;
/* 2527 */     double var6 = this.posZ - par1Entity.posZ;
/* 2528 */     return var2 * var2 + var4 * var4 + var6 * var6;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRepelledByCollisionWithPlayer() {
/* 2533 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyEntityCollision(Entity par1Entity) {
/* 2546 */     if (this instanceof EntityPlayer || par1Entity instanceof EntityPlayer) {
/*      */ 
/*      */       
/* 2549 */       if (isEntityPlayer() && (((EntityPlayer)this).isGhost() || isZevimrgvInTournament())) {
/*      */         return;
/*      */       }
/* 2552 */       if (par1Entity instanceof EntityPlayer && (((EntityPlayer)par1Entity).isGhost() || par1Entity.isZevimrgvInTournament())) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2558 */       if (!isRepelledByCollisionWithPlayer() || !par1Entity.isRepelledByCollisionWithPlayer()) {
/*      */         return;
/*      */       }
/* 2561 */       if (this instanceof EntityWoodSpider && isInsideOfMaterial(Material.tree_leaves))
/*      */         return; 
/* 2563 */       if (par1Entity instanceof EntityWoodSpider && par1Entity.isInsideOfMaterial(Material.tree_leaves)) {
/*      */         return;
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2588 */     if (this instanceof EntityCubic && ((EntityCubic)this).triesToDamageOnContact(par1Entity) && !isRepelledByCollisionWithPlayer()) {
/*      */       return;
/*      */     }
/* 2591 */     if (par1Entity instanceof EntityCubic && ((EntityCubic)par1Entity).triesToDamageOnContact(this) && !par1Entity.isRepelledByCollisionWithPlayer()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2596 */     if (par1Entity.riddenByEntity != this && par1Entity.ridingEntity != this) {
/*      */       
/* 2598 */       double var2 = par1Entity.posX - this.posX;
/* 2599 */       double var4 = par1Entity.posZ - this.posZ;
/* 2600 */       double var6 = MathHelper.abs_max(var2, var4);
/*      */       
/* 2602 */       if (var6 >= 0.009999999776482582D) {
/*      */         
/* 2604 */         var6 = MathHelper.sqrt_double(var6);
/* 2605 */         var2 /= var6;
/* 2606 */         var4 /= var6;
/* 2607 */         double var8 = 1.0D / var6;
/*      */         
/* 2609 */         if (var8 > 1.0D)
/*      */         {
/* 2611 */           var8 = 1.0D;
/*      */         }
/*      */         
/* 2614 */         var2 *= var8;
/* 2615 */         var4 *= var8;
/* 2616 */         var2 *= 0.05000000074505806D;
/* 2617 */         var4 *= 0.05000000074505806D;
/* 2618 */         var2 *= (1.0F - this.entityCollisionReduction);
/* 2619 */         var4 *= (1.0F - this.entityCollisionReduction);
/* 2620 */         addVelocity(-var2, 0.0D, -var4);
/* 2621 */         par1Entity.addVelocity(var2, 0.0D, var4);
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
/*      */   public void addVelocity(double par1, double par3, double par5) {
/* 2640 */     this.motionX += par1;
/* 2641 */     this.motionY += par3;
/* 2642 */     this.motionZ += par5;
/* 2643 */     this.isAirBorne = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBeenAttacked() {
/* 2651 */     this.velocityChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isImmuneTo(DamageSource damage_source) {
/* 2657 */     if (isEntityInvulnerable() || this.isDead) {
/* 2658 */       return true;
/*      */     }
/* 2660 */     if (damage_source.isGelatinousSphereDamage()) {
/*      */       
/* 2662 */       EntityGelatinousSphere sphere = (EntityGelatinousSphere)damage_source.getImmediateEntity();
/*      */       
/* 2664 */       if (isImmuneTo(sphere.getDamageType())) {
/* 2665 */         return true;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2671 */     if (damage_source.isFireDamage() && !isHarmedByFire()) {
/* 2672 */       return true;
/*      */     }
/* 2674 */     if (damage_source.isLavaDamage() && !isHarmedByLava()) {
/* 2675 */       return true;
/*      */     }
/* 2677 */     if (damage_source.isPepsinDamage() && !isHarmedByPepsin()) {
/* 2678 */       return true;
/*      */     }
/* 2680 */     if (damage_source.isAcidDamage() && !isHarmedByAcid()) {
/* 2681 */       return true;
/*      */     }
/* 2683 */     if (damage_source.isExplosion() && isImmuneToExplosion()) {
/* 2684 */       return true;
/*      */     }
/* 2686 */     if (damage_source.isDrowning() && !isHarmedByDrowning()) {
/* 2687 */       return true;
/*      */     }
/* 2689 */     if (damage_source.isCactus() && !canBeDamagedByCacti()) {
/* 2690 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2695 */     if ((damage_source.isAnvil() || damage_source.isFallingBlock()) && !(this instanceof EntityLivingBase)) {
/* 2696 */       return true;
/*      */     }
/* 2698 */     if (damage_source == DamageSource.wither)
/*      */     {
/* 2700 */       if (!isEntityLivingBase() || !getAsEntityLivingBase().isEntityBiologicallyAlive()) {
/* 2701 */         return true;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 2706 */     return false;
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
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 2728 */     if (onClient()) {
/*      */       
/* 2730 */       Minecraft.setErrorMessage("attackEntityFrom: not meant to be called on client (" + damage + " vs " + getEntityName() + ")");
/*      */       
/* 2732 */       return new EntityDamageResult(this);
/*      */     } 
/*      */     
/* 2735 */     EntityDamageResult result = new EntityDamageResult(this);
/*      */     
/* 2737 */     if (damage.isNil())
/*      */     {
/*      */       
/* 2740 */       return null;
/*      */     }
/* 2742 */     if (isEntityInvulnerable() || this.isDead)
/*      */     {
/*      */ 
/*      */       
/* 2746 */       return null;
/*      */     }
/* 2748 */     if (!damage.isAbsolute() && !damage.ignoreSpecificImmunities() && isImmuneTo(damage.getSource()))
/*      */     {
/*      */ 
/*      */       
/* 2752 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2757 */     if (!canBeKnockedBack()) {
/*      */       
/* 2759 */       if (damage.isKnockbackOnly()) {
/* 2760 */         return null;
/*      */       }
/* 2762 */       if (damage.isPlayerThrownSnowball() && !canTakeDamageFromPlayerThrownSnowballs()) {
/* 2763 */         return null;
/*      */       }
/*      */     } 
/* 2766 */     DebugAttack.start(this, damage);
/*      */     
/* 2768 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityDamaged(DamageSource damage_source, float amount) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isCorporeal() {
/* 2786 */     if (this instanceof EntityFX || this instanceof EntityWeatherEffect) {
/* 2787 */       return false;
/*      */     }
/* 2789 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/* 2797 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/* 2805 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToPlayerScore(Entity par1Entity, int par2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInRangeToRenderVec3D(Vec3 par1Vec3) {
/* 2819 */     double var2 = this.posX - par1Vec3.xCoord;
/* 2820 */     double var4 = this.posY - par1Vec3.yCoord;
/* 2821 */     double var6 = this.posZ - par1Vec3.zCoord;
/* 2822 */     double var8 = var2 * var2 + var4 * var4 + var6 * var6;
/* 2823 */     return isInRangeToRenderDist(var8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInRangeToRenderDist(double par1) {
/* 2834 */     int render_distance = (Minecraft.theMinecraft == null) ? 0 : Minecraft.theMinecraft.gameSettings.getRenderDistance();
/* 2835 */     float threshold_distance_sq = (render_distance == 0) ? 2048.0F : ((render_distance == 1) ? 1024.0F : 512.0F);
/*      */     
/* 2837 */     boolean longer_distance_candidate = this instanceof EntityLivingBase;
/*      */     
/* 2839 */     if (longer_distance_candidate && par1 < threshold_distance_sq)
/* 2840 */       return true; 
/* 2841 */     if ((this instanceof EntityItem || this instanceof EntityXPOrb) && par1 < (threshold_distance_sq / 2.0F)) {
/* 2842 */       return true;
/*      */     }
/* 2844 */     double var3 = this.boundingBox.getAverageEdgeLength();
/* 2845 */     var3 *= 64.0D * this.renderDistanceWeight;
/*      */     
/* 2847 */     if (longer_distance_candidate) {
/* 2848 */       var3 *= 1.25D;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2853 */     return (par1 < var3 * var3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean writeMountToNBT(NBTTagCompound par1NBTTagCompound) {
/* 2862 */     String var2 = getEntityString();
/*      */     
/* 2864 */     if (!this.isDead && var2 != null) {
/*      */       
/* 2866 */       par1NBTTagCompound.setString("id", var2);
/*      */       
/* 2868 */       par1NBTTagCompound.setInteger("despawn_counter", this.despawn_counter);
/* 2869 */       writeToNBT(par1NBTTagCompound);
/* 2870 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2874 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWrittenToChunkNBT() {
/* 2881 */     return (!this.isDead && getEntityString() != null && this.riddenByEntity == null);
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
/*      */   public final boolean writeToNBTOptional(NBTTagCompound par1NBTTagCompound) {
/* 2895 */     if (isWrittenToChunkNBT()) {
/*      */       
/* 2897 */       String var2 = getEntityString();
/* 2898 */       par1NBTTagCompound.setString("id", var2);
/* 2899 */       writeToNBT(par1NBTTagCompound);
/* 2900 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2904 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
/*      */     try {
/* 2915 */       par1NBTTagCompound.setTag("Pos", newDoubleNBTList(new double[] { this.posX, this.posY + this.ySize, this.posZ }));
/* 2916 */       par1NBTTagCompound.setTag("Motion", newDoubleNBTList(new double[] { this.motionX, this.motionY, this.motionZ }));
/* 2917 */       par1NBTTagCompound.setTag("Rotation", newFloatNBTList(new float[] { this.rotationYaw, this.rotationPitch }));
/* 2918 */       par1NBTTagCompound.setFloat("FallDistance", this.fallDistance);
/* 2919 */       par1NBTTagCompound.setShort("Fire", (short)this.fire);
/* 2920 */       par1NBTTagCompound.setShort("Air", (short)getAir());
/* 2921 */       par1NBTTagCompound.setBoolean("OnGround", this.onGround);
/* 2922 */       par1NBTTagCompound.setInteger("Dimension", this.dimension);
/* 2923 */       par1NBTTagCompound.setBoolean("Invulnerable", this.invulnerable);
/* 2924 */       par1NBTTagCompound.setInteger("PortalCooldown", this.timeUntilPortal);
/* 2925 */       par1NBTTagCompound.setLong("UUIDMost", this.entityUniqueID.getMostSignificantBits());
/* 2926 */       par1NBTTagCompound.setLong("UUIDLeast", this.entityUniqueID.getLeastSignificantBits());
/* 2927 */       writeEntityToNBT(par1NBTTagCompound);
/*      */       
/* 2929 */       if (this.ridingEntity != null) {
/*      */         
/* 2931 */         NBTTagCompound var2 = new NBTTagCompound("Riding");
/*      */         
/* 2933 */         if (this.ridingEntity.writeMountToNBT(var2))
/*      */         {
/* 2935 */           par1NBTTagCompound.setTag("Riding", var2);
/*      */         }
/*      */       } 
/*      */       
/* 2939 */       par1NBTTagCompound.setInteger("spawn_x", this.spawn_x);
/* 2940 */       par1NBTTagCompound.setInteger("spawn_y", this.spawn_y);
/* 2941 */       par1NBTTagCompound.setInteger("spawn_z", this.spawn_z);
/*      */       
/* 2943 */       par1NBTTagCompound.setInteger("despawn_counter", this.despawn_counter);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2959 */     catch (Throwable var5) {
/*      */       
/* 2961 */       CrashReport var3 = CrashReport.makeCrashReport(var5, "Saving entity NBT");
/* 2962 */       CrashReportCategory var4 = var3.makeCategory("Entity being saved");
/* 2963 */       addEntityCrashInfo(var4);
/* 2964 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/*      */     try {
/* 2975 */       NBTTagList var2 = par1NBTTagCompound.getTagList("Pos");
/* 2976 */       NBTTagList var6 = par1NBTTagCompound.getTagList("Motion");
/* 2977 */       NBTTagList var7 = par1NBTTagCompound.getTagList("Rotation");
/* 2978 */       this.motionX = ((NBTTagDouble)var6.tagAt(0)).data;
/* 2979 */       this.motionY = ((NBTTagDouble)var6.tagAt(1)).data;
/* 2980 */       this.motionZ = ((NBTTagDouble)var6.tagAt(2)).data;
/*      */       
/* 2982 */       if (Math.abs(this.motionX) > 10.0D)
/*      */       {
/* 2984 */         this.motionX = 0.0D;
/*      */       }
/*      */       
/* 2987 */       if (Math.abs(this.motionY) > 10.0D)
/*      */       {
/* 2989 */         this.motionY = 0.0D;
/*      */       }
/*      */       
/* 2992 */       if (Math.abs(this.motionZ) > 10.0D)
/*      */       {
/* 2994 */         this.motionZ = 0.0D;
/*      */       }
/*      */       
/* 2997 */       this.prevPosX = this.lastTickPosX = this.posX = ((NBTTagDouble)var2.tagAt(0)).data;
/* 2998 */       this.prevPosY = this.lastTickPosY = this.posY = ((NBTTagDouble)var2.tagAt(1)).data;
/* 2999 */       this.prevPosZ = this.lastTickPosZ = this.posZ = ((NBTTagDouble)var2.tagAt(2)).data;
/* 3000 */       this.prevRotationYaw = this.rotationYaw = ((NBTTagFloat)var7.tagAt(0)).data;
/* 3001 */       this.prevRotationPitch = this.rotationPitch = ((NBTTagFloat)var7.tagAt(1)).data;
/* 3002 */       this.fallDistance = par1NBTTagCompound.getFloat("FallDistance");
/* 3003 */       this.fire = par1NBTTagCompound.getShort("Fire");
/* 3004 */       setAir(par1NBTTagCompound.getShort("Air"));
/* 3005 */       this.onGround = par1NBTTagCompound.getBoolean("OnGround");
/* 3006 */       this.dimension = par1NBTTagCompound.getInteger("Dimension");
/* 3007 */       this.invulnerable = par1NBTTagCompound.getBoolean("Invulnerable");
/* 3008 */       this.timeUntilPortal = par1NBTTagCompound.getInteger("PortalCooldown");
/*      */       
/* 3010 */       if (par1NBTTagCompound.hasKey("UUIDMost") && par1NBTTagCompound.hasKey("UUIDLeast"))
/*      */       {
/* 3012 */         this.entityUniqueID = new UUID(par1NBTTagCompound.getLong("UUIDMost"), par1NBTTagCompound.getLong("UUIDLeast"));
/*      */       }
/*      */       
/* 3015 */       setPosition(this.posX, this.posY, this.posZ);
/* 3016 */       setRotation(this.rotationYaw, this.rotationPitch);
/*      */       
/* 3018 */       this.spawn_x = par1NBTTagCompound.getInteger("spawn_x");
/* 3019 */       this.spawn_y = par1NBTTagCompound.getInteger("spawn_y");
/* 3020 */       this.spawn_z = par1NBTTagCompound.getInteger("spawn_z");
/*      */       
/* 3022 */       this.despawn_counter = par1NBTTagCompound.getInteger("despawn_counter");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3038 */       readEntityFromNBT(par1NBTTagCompound);
/*      */       
/* 3040 */       if (shouldSetPosAfterLoading())
/*      */       {
/* 3042 */         setPosition(this.posX, this.posY, this.posZ);
/*      */       }
/*      */     }
/* 3045 */     catch (Throwable var5) {
/*      */       
/* 3047 */       CrashReport var3 = CrashReport.makeCrashReport(var5, "Loading entity NBT");
/* 3048 */       CrashReportCategory var4 = var3.makeCategory("Entity being loaded");
/* 3049 */       addEntityCrashInfo(var4);
/* 3050 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean shouldSetPosAfterLoading() {
/* 3056 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String getEntityString() {
/* 3064 */     return EntityList.getEntityString(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onChunkLoad() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NBTTagList newDoubleNBTList(double... par1ArrayOfDouble) {
/* 3084 */     NBTTagList var2 = new NBTTagList();
/* 3085 */     double[] var3 = par1ArrayOfDouble;
/* 3086 */     int var4 = par1ArrayOfDouble.length;
/*      */     
/* 3088 */     for (int var5 = 0; var5 < var4; var5++) {
/*      */       
/* 3090 */       double var6 = var3[var5];
/* 3091 */       var2.appendTag(new NBTTagDouble((String)null, var6));
/*      */     } 
/*      */     
/* 3094 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NBTTagList newFloatNBTList(float... par1ArrayOfFloat) {
/* 3102 */     NBTTagList var2 = new NBTTagList();
/* 3103 */     float[] var3 = par1ArrayOfFloat;
/* 3104 */     int var4 = par1ArrayOfFloat.length;
/*      */     
/* 3106 */     for (int var5 = 0; var5 < var4; var5++) {
/*      */       
/* 3108 */       float var6 = var3[var5];
/* 3109 */       var2.appendTag(new NBTTagFloat((String)null, var6));
/*      */     } 
/*      */     
/* 3112 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getShadowSize() {
/* 3118 */     return this.height / 2.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropItem(Item item) {
/* 3124 */     return dropItem(item, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropItem(Item item, int quantity) {
/* 3130 */     return (item == null || quantity < 1) ? null : dropItem(item.itemID, quantity);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropItem(int item_id, int quantity) {
/* 3136 */     if (item_id <= 0) {
/* 3137 */       return null;
/*      */     }
/*      */     
/* 3140 */     return dropItem(item_id, quantity, this.height * 0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropItem(int item_id, int quantity, float y_offset) {
/* 3146 */     if (item_id <= 0 || quantity < 1) {
/* 3147 */       return null;
/*      */     }
/* 3149 */     return dropItemStack(new ItemStack(item_id, quantity, 0), y_offset);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropItemStack(ItemStack item_stack) {
/* 3155 */     return dropItemStack(item_stack, this.height * 0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropItemStack(ItemStack item_stack, float y_offset) {
/* 3161 */     if (item_stack == null || item_stack.stackSize == 0) {
/* 3162 */       return null;
/*      */     }
/* 3164 */     EntityItem entity_item = new EntityItem(this.worldObj, this.posX, this.posY + y_offset, this.posZ, item_stack);
/* 3165 */     entity_item.delayBeforeCanPickup = 10;
/*      */     
/* 3167 */     if (isEntityPlayer()) {
/*      */       
/* 3169 */       entity_item.age = -18000;
/* 3170 */       entity_item.dropped_by_player = true;
/*      */     } 
/*      */     
/* 3173 */     this.worldObj.spawnEntityInWorld(entity_item);
/*      */     
/* 3175 */     if (isBurning()) {
/* 3176 */       entity_item.setFire(this.rand.nextInt(7) + 2);
/*      */     }
/* 3178 */     return entity_item;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityAlive() {
/* 3186 */     return !this.isDead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInsideOpaqueBlock() {
/* 3194 */     for (int var1 = 0; var1 < 8; var1++) {
/*      */       
/* 3196 */       float var2 = (((var1 >> 0) % 2) - 0.5F) * this.width * 0.8F;
/* 3197 */       float var3 = (((var1 >> 1) % 2) - 0.5F) * 0.1F;
/* 3198 */       float var4 = (((var1 >> 2) % 2) - 0.5F) * this.width * 0.8F;
/* 3199 */       int var5 = MathHelper.floor_double(this.posX + var2);
/* 3200 */       int var6 = MathHelper.floor_double(this.posY + getEyeHeight() + var3);
/* 3201 */       int var7 = MathHelper.floor_double(this.posZ + var4);
/*      */       
/* 3203 */       if (this.worldObj.isBlockNormalCube(var5, var6, var7))
/*      */       {
/* 3205 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 3209 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSilverfishInsideDamagingOpaqueBlock() {
/* 3214 */     for (int var1 = 0; var1 < 8; var1++) {
/*      */       
/* 3216 */       float var2 = (((var1 >> 0) % 2) - 0.5F) * this.width * 0.8F;
/* 3217 */       float var3 = (((var1 >> 1) % 2) - 0.5F) * 0.1F;
/* 3218 */       float var4 = (((var1 >> 2) % 2) - 0.5F) * this.width * 0.8F;
/* 3219 */       int var5 = MathHelper.floor_double(this.posX + var2);
/* 3220 */       int var6 = MathHelper.floor_double(this.posY + getEyeHeight() + var3);
/* 3221 */       int var7 = MathHelper.floor_double(this.posZ + var4);
/*      */       
/* 3223 */       Block block = this.worldObj.getBlock(var5, var6, var7);
/*      */       
/* 3225 */       if (Block.isNormalCube(block))
/*      */       {
/* 3227 */         if (block != Block.sand && block != Block.dirt && block != Block.gravel && block != Block.slowSand)
/*      */         {
/*      */           
/* 3230 */           return true;
/*      */         }
/*      */       }
/*      */     } 
/* 3234 */     return false;
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
/*      */   public AxisAlignedBB getCollisionBox(Entity par1Entity) {
/* 3251 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRidden() {
/* 3259 */     if (this.ridingEntity.isDead) {
/*      */       
/* 3261 */       this.ridingEntity = null;
/*      */     }
/*      */     else {
/*      */       
/* 3265 */       this.motionX = 0.0D;
/* 3266 */       this.motionY = 0.0D;
/* 3267 */       this.motionZ = 0.0D;
/* 3268 */       onUpdate();
/*      */       
/* 3270 */       if (this.ridingEntity != null) {
/*      */         
/* 3272 */         this.ridingEntity.updateRiderPosition();
/* 3273 */         this.entityRiderYawDelta += (this.ridingEntity.rotationYaw - this.ridingEntity.prevRotationYaw);
/*      */         
/* 3275 */         for (this.entityRiderPitchDelta += (this.ridingEntity.rotationPitch - this.ridingEntity.prevRotationPitch); this.entityRiderYawDelta >= 180.0D; this.entityRiderYawDelta -= 360.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3280 */         while (this.entityRiderYawDelta < -180.0D)
/*      */         {
/* 3282 */           this.entityRiderYawDelta += 360.0D;
/*      */         }
/*      */         
/* 3285 */         while (this.entityRiderPitchDelta >= 180.0D)
/*      */         {
/* 3287 */           this.entityRiderPitchDelta -= 360.0D;
/*      */         }
/*      */         
/* 3290 */         while (this.entityRiderPitchDelta < -180.0D)
/*      */         {
/* 3292 */           this.entityRiderPitchDelta += 360.0D;
/*      */         }
/*      */         
/* 3295 */         double var1 = this.entityRiderYawDelta * 0.5D;
/* 3296 */         double var3 = this.entityRiderPitchDelta * 0.5D;
/* 3297 */         float var5 = 10.0F;
/*      */         
/* 3299 */         if (var1 > var5)
/*      */         {
/* 3301 */           var1 = var5;
/*      */         }
/*      */         
/* 3304 */         if (var1 < -var5)
/*      */         {
/* 3306 */           var1 = -var5;
/*      */         }
/*      */         
/* 3309 */         if (var3 > var5)
/*      */         {
/* 3311 */           var3 = var5;
/*      */         }
/*      */         
/* 3314 */         if (var3 < -var5)
/*      */         {
/* 3316 */           var3 = -var5;
/*      */         }
/*      */         
/* 3319 */         this.entityRiderYawDelta -= var1;
/* 3320 */         this.entityRiderPitchDelta -= var3;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateRiderPosition() {
/* 3327 */     if (this.riddenByEntity != null)
/*      */     {
/* 3329 */       this.riddenByEntity.setPosition(this.posX, this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getYOffset() {
/* 3338 */     return this.yOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMountedYOffset() {
/* 3346 */     return this.height * 0.75D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mountEntity(Entity par1Entity) {
/* 3354 */     this.entityRiderPitchDelta = 0.0D;
/* 3355 */     this.entityRiderYawDelta = 0.0D;
/*      */     
/* 3357 */     if (par1Entity == null) {
/*      */       
/* 3359 */       if (this.ridingEntity != null) {
/*      */         
/* 3361 */         setLocationAndAngles(this.ridingEntity.posX, this.ridingEntity.boundingBox.minY + this.ridingEntity.height, this.ridingEntity.posZ, this.rotationYaw, this.rotationPitch);
/* 3362 */         this.ridingEntity.riddenByEntity = null;
/*      */       } 
/*      */       
/* 3365 */       this.ridingEntity = null;
/*      */     }
/*      */     else {
/*      */       
/* 3369 */       if (this.ridingEntity != null)
/*      */       {
/* 3371 */         this.ridingEntity.riddenByEntity = null;
/*      */       }
/*      */       
/* 3374 */       this.ridingEntity = par1Entity;
/* 3375 */       par1Entity.riddenByEntity = this;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 3385 */     setPosition(par1, par3, par5);
/* 3386 */     setRotation(par7, par8);
/* 3387 */     List<AxisAlignedBB> var10 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.contract(0.03125D, 0.0D, 0.03125D));
/*      */     
/* 3389 */     if (!var10.isEmpty()) {
/*      */       
/* 3391 */       double var11 = 0.0D;
/*      */       
/* 3393 */       for (int var13 = 0; var13 < var10.size(); var13++) {
/*      */         
/* 3395 */         AxisAlignedBB var14 = var10.get(var13);
/*      */         
/* 3397 */         if (var14.maxY > var11)
/*      */         {
/* 3399 */           var11 = var14.maxY;
/*      */         }
/*      */       } 
/*      */       
/* 3403 */       par3 += var11 - this.boundingBox.minY;
/* 3404 */       setPosition(par1, par3, par5);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getCollisionBorderSize(Entity for_raycast_from_this_entity) {
/* 3415 */     if (for_raycast_from_this_entity instanceof IProjectile || for_raycast_from_this_entity instanceof EntityFireball || for_raycast_from_this_entity instanceof EntityFishHook) {
/* 3416 */       return 0.3F;
/*      */     }
/* 3418 */     return 0.1F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLookVec() {
/* 3426 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInPortal(int destination_dimension_id) {
/* 3435 */     if (this.timeUntilPortal > 0) {
/*      */       
/* 3437 */       this.timeUntilPortal = getPortalCooldown();
/*      */     }
/*      */     else {
/*      */       
/* 3441 */       double var1 = this.prevPosX - this.posX;
/* 3442 */       double var3 = this.prevPosZ - this.posZ;
/*      */       
/* 3444 */       if (!this.worldObj.isRemote && !this.inPortal)
/*      */       {
/* 3446 */         this.teleportDirection = Direction.getMovementDirection(var1, var3);
/*      */       }
/*      */       
/* 3449 */       this.inPortal = true;
/* 3450 */       this.portal_destination_dimension_id = destination_dimension_id;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPortalCooldown() {
/* 3459 */     return 900;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVelocity(double par1, double par3, double par5) {
/* 3467 */     this.motionX = par1;
/* 3468 */     this.motionY = par3;
/* 3469 */     this.motionZ = par5;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(EnumEntityState par1) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void performHurtAnimation() {}
/*      */ 
/*      */   
/*      */   public ItemStack[] getLastActiveItems() {
/* 3482 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBurning() {
/* 3496 */     return (canCatchFire() && (this.fire > 0 || getFlag(0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRiding() {
/* 3505 */     return (this.ridingEntity != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSneaking() {
/* 3513 */     return getFlag(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSneaking(boolean par1) {
/* 3521 */     setFlag(1, par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSprinting() {
/* 3529 */     return getFlag(3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSprinting(boolean par1) {
/* 3537 */     setFlag(3, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInvisible() {
/* 3542 */     return getFlag(5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInvisibleToPlayer(EntityPlayer par1EntityPlayer) {
/* 3552 */     return isInvisible();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInvisible(boolean par1) {
/* 3557 */     setFlag(5, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEating() {
/* 3562 */     return getFlag(4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEating(boolean par1) {
/* 3567 */     setFlag(4, par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getFlag(int par1) {
/* 3576 */     return ((this.dataWatcher.getWatchableObjectByte(0) & 1 << par1) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setFlag(int par1, boolean par2) {
/* 3584 */     byte var3 = this.dataWatcher.getWatchableObjectByte(0);
/*      */     
/* 3586 */     if (par2) {
/*      */       
/* 3588 */       this.dataWatcher.updateObject(0, Byte.valueOf((byte)(var3 | 1 << par1)));
/*      */     }
/*      */     else {
/*      */       
/* 3592 */       this.dataWatcher.updateObject(0, Byte.valueOf((byte)(var3 & (1 << par1 ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAir() {
/* 3598 */     return this.dataWatcher.getWatchableObjectShort(1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAir(int par1) {
/* 3603 */     this.dataWatcher.updateObject(1, Short.valueOf((short)par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {
/* 3611 */     dealFireDamage(5);
/* 3612 */     this.fire++;
/*      */     
/* 3614 */     if (this.fire == 0)
/*      */     {
/* 3616 */       setFire(8);
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
/*      */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int pushOutOfBlocks() {
/* 3736 */     if (this.worldObj.isRemote || this.ticksExisted < 2) {
/* 3737 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 3741 */     double original_center_y = (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D;
/*      */     
/* 3743 */     double center_x = this.posX;
/* 3744 */     double center_y = original_center_y;
/* 3745 */     double center_z = this.posZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3751 */     int x = (center_x < 0.0D) ? ((int)center_x - 1) : (int)center_x;
/* 3752 */     int y = (center_y < 0.0D) ? ((int)center_y - 1) : (int)center_y;
/* 3753 */     int z = (center_z < 0.0D) ? ((int)center_z - 1) : (int)center_z;
/*      */     
/* 3755 */     if (!this.worldObj.isBlockFullSolidCube(x, y, z) || this.worldObj.getBlock(x, y, z) == Block.cauldron) {
/*      */       
/* 3757 */       List collisions = this.worldObj.getCollidingBlockBounds(this.boundingBox, this);
/*      */       
/* 3759 */       if (collisions.isEmpty()) {
/* 3760 */         return 0;
/*      */       }
/*      */     } 
/* 3763 */     if (this instanceof EntityItem && this.worldObj.getBlock(x, y, z) instanceof BlockLeaves) {
/*      */       
/* 3765 */       List collisions = this.worldObj.getCollidingBlockBounds(this.boundingBox, this);
/*      */       
/* 3767 */       if (collisions.isEmpty()) {
/* 3768 */         return 0;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3773 */     int max_escape_range = (this instanceof EntityXPOrb) ? 2 : 1;
/*      */     
/* 3775 */     if (this instanceof EntityLivestock || isEntityPlayer()) {
/* 3776 */       max_escape_range = 3;
/*      */     }
/* 3778 */     int matrix_size = max_escape_range * 2 + 1;
/* 3779 */     int matrix_size_sq = matrix_size * matrix_size;
/*      */     
/* 3781 */     boolean can_escape = false;
/* 3782 */     boolean[] is_candidate_block = new boolean[matrix_size * matrix_size * matrix_size];
/*      */     
/* 3784 */     for (int dx = -max_escape_range; dx <= max_escape_range; dx++) {
/*      */       
/* 3786 */       for (int dy = -max_escape_range; dy <= max_escape_range; dy++) {
/*      */         
/* 3788 */         for (int dz = -max_escape_range; dz <= max_escape_range; dz++) {
/*      */           
/* 3790 */           if (this.worldObj.blockExists(x + dx, y + dy, z + dz) && !this.worldObj.isBlockFullSolidCube(x + dx, y + dy, z + dz)) {
/*      */             
/* 3792 */             can_escape = true;
/* 3793 */             is_candidate_block[dx + max_escape_range + (dy + max_escape_range) * matrix_size + (dz + max_escape_range) * matrix_size_sq] = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3799 */     if (!can_escape) {
/* 3800 */       return -1;
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
/* 3812 */     AxisAlignedBB trial_bounding_box = this.boundingBox.copy();
/*      */     
/* 3814 */     int random_number_index = (int)(Math.random() * 2.147483647E9D);
/*      */     
/* 3816 */     float range = 0.1F;
/*      */     
/* 3818 */     while ((range += 0.001F) < (max_escape_range + 1)) {
/*      */       
/* 3820 */       double dPosX = RNG.double_1[++random_number_index & 0x7FFF] * range * 2.0D - range;
/* 3821 */       double dPosY = RNG.double_1[++random_number_index & 0x7FFF] * range * 2.0D - range;
/* 3822 */       double dPosZ = RNG.double_1[++random_number_index & 0x7FFF] * range * 2.0D - range;
/*      */       
/* 3824 */       center_x = this.posX + dPosX;
/* 3825 */       center_y = original_center_y + dPosY;
/* 3826 */       center_z = this.posZ + dPosZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3832 */       int trial_x = (center_x < 0.0D) ? ((int)center_x - 1) : (int)center_x;
/* 3833 */       int trial_y = (center_y < 0.0D) ? ((int)center_y - 1) : (int)center_y;
/* 3834 */       int trial_z = (center_z < 0.0D) ? ((int)center_z - 1) : (int)center_z;
/*      */       
/* 3836 */       int i = trial_x - x;
/* 3837 */       int dy = trial_y - y;
/* 3838 */       int dz = trial_z - z;
/*      */       
/* 3840 */       if (i < -max_escape_range || i > max_escape_range) {
/*      */         continue;
/*      */       }
/* 3843 */       if (dy < -max_escape_range || dy > max_escape_range) {
/*      */         continue;
/*      */       }
/* 3846 */       if (dz < -max_escape_range || dz > max_escape_range) {
/*      */         continue;
/*      */       }
/*      */       
/* 3850 */       if (!is_candidate_block[i + max_escape_range + (dy + max_escape_range) * matrix_size + (dz + max_escape_range) * matrix_size_sq]) {
/*      */         continue;
/*      */       }
/* 3853 */       trial_bounding_box.setBounds(this.boundingBox.minX + dPosX, this.boundingBox.minY + dPosY, this.boundingBox.minZ + dPosZ, this.boundingBox.maxX + dPosX, this.boundingBox.maxY + dPosY, this.boundingBox.maxZ + dPosZ);
/*      */       
/* 3855 */       List collisions = this.worldObj.getCollidingBlockBounds(trial_bounding_box, this);
/*      */       
/* 3857 */       if (collisions.isEmpty()) {
/*      */ 
/*      */         
/* 3860 */         setPosition(this.posX + dPosX, this.posY + dPosY, this.posZ + dPosZ);
/* 3861 */         this.send_position_update_immediately = true;
/*      */         
/* 3863 */         this.motionX = this.motionY = this.motionZ = 0.0D;
/* 3864 */         this.sync_last_tick_pos_on_next_update = true;
/* 3865 */         return 1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3875 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInWeb() {
/* 3883 */     this.isInWeb = true;
/* 3884 */     this.fallDistance = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityName() {
/* 3892 */     String var1 = EntityList.getEntityString(this);
/*      */     
/* 3894 */     if (var1 == null)
/*      */     {
/* 3896 */       var1 = "generic";
/*      */     }
/*      */     
/* 3899 */     return StatCollector.translateToLocal("entity." + var1 + ".name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity[] getParts() {
/* 3907 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityEqual(Entity par1Entity) {
/* 3915 */     return (this == par1Entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRotationYawHead() {
/* 3920 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationYawHead(float par1) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackWithItem() {
/* 3933 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityUndead() {
/* 3938 */     return false;
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
/*      */   public String toString() {
/* 3951 */     return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", new Object[] { getClass().getSimpleName(), getEntityName(), Integer.valueOf(this.entityId), (this.worldObj == null) ? "~NULL~" : this.worldObj.getWorldInfo().getWorldName(), Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInvulnerable() {
/* 3959 */     return this.invulnerable;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEntityInvulnerable(boolean invulnerable) {
/* 3964 */     this.invulnerable = invulnerable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copyLocationAndAnglesFrom(Entity par1Entity) {
/* 3972 */     setLocationAndAngles(par1Entity.posX, par1Entity.posY, par1Entity.posZ, par1Entity.rotationYaw, par1Entity.rotationPitch);
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
/*      */   public void copyDataFrom(Entity par1Entity, boolean par2) {
/* 3984 */     NBTTagCompound var3 = new NBTTagCompound();
/* 3985 */     par1Entity.writeToNBT(var3);
/* 3986 */     readFromNBT(var3);
/* 3987 */     this.timeUntilPortal = par1Entity.timeUntilPortal;
/* 3988 */     this.teleportDirection = par1Entity.teleportDirection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void travelToDimension(int par1) {
/* 3996 */     if (!this.worldObj.isRemote && !this.isDead) {
/*      */       
/* 3998 */       this.worldObj.theProfiler.startSection("changeDimension");
/* 3999 */       MinecraftServer var2 = MinecraftServer.getServer();
/* 4000 */       int var3 = this.dimension;
/* 4001 */       WorldServer var4 = var2.worldServerForDimension(var3);
/* 4002 */       WorldServer var5 = var2.worldServerForDimension(par1);
/* 4003 */       this.dimension = par1;
/*      */       
/* 4005 */       if (var3 == 1 && par1 == 1) {
/*      */         
/* 4007 */         var5 = var2.worldServerForDimension(0);
/* 4008 */         this.dimension = 0;
/*      */       } 
/*      */       
/* 4011 */       this.worldObj.removeEntity(this);
/* 4012 */       this.isDead = false;
/* 4013 */       this.worldObj.theProfiler.startSection("reposition");
/* 4014 */       var2.getConfigurationManager().transferEntityToWorld(this, var3, var4, var5);
/* 4015 */       this.worldObj.theProfiler.endStartSection("reloading");
/* 4016 */       Entity var6 = EntityList.createEntityByName(EntityList.getEntityString(this), var5);
/*      */ 
/*      */       
/* 4019 */       setDead();
/*      */       
/* 4021 */       if (this.chunk_added_to != null) {
/*      */         
/* 4023 */         if (Minecraft.inDevMode()) {
/* 4024 */           System.out.println("travelToDimension: Removed " + getEntityName() + " (UUID=" + getUniqueID() + ") from chunk in " + this.chunk_added_to.worldObj.getDimensionName());
/*      */         }
/* 4026 */         removeFromChunk();
/*      */       } 
/*      */       
/* 4029 */       if (var6 != null) {
/*      */         
/* 4031 */         var6.copyDataFrom(this, true);
/*      */         
/* 4033 */         if (var3 == 1 && par1 == 1) {
/*      */           
/* 4035 */           ChunkCoordinates var7 = var5.getSpawnPoint();
/* 4036 */           var7.posY = this.worldObj.getTopSolidOrLiquidBlock(var7.posX, var7.posZ);
/* 4037 */           var6.setLocationAndAngles(var7.posX, var7.posY, var7.posZ, var6.rotationYaw, var6.rotationPitch);
/*      */         } 
/*      */         
/* 4040 */         if (Minecraft.inDevMode()) {
/* 4041 */           System.out.println("travelToDimension: Spawning " + var6.getEntityName() + " in " + var5.getDimensionName() + " (UUID=" + var6.getUniqueID() + ")");
/*      */         }
/* 4043 */         var5.spawnEntityInWorld(var6);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4049 */       this.worldObj.theProfiler.endSection();
/* 4050 */       var4.resetUpdateEntityTick();
/* 4051 */       var5.resetUpdateEntityTick();
/* 4052 */       this.worldObj.theProfiler.endSection();
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
/*      */   public boolean shouldExplodeBlock(Explosion par1Explosion, World par2World, int par3, int par4, int par5, int par6, float par7) {
/* 4068 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSafePointTries() {
/* 4076 */     return 3;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTeleportDirection() {
/* 4081 */     return this.teleportDirection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean doesEntityNotTriggerPressurePlate() {
/* 4089 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addEntityCrashInfo(CrashReportCategory par1CrashReportCategory) {
/* 4094 */     par1CrashReportCategory.addCrashSectionCallable("Entity Type", new CallableEntityType(this));
/* 4095 */     par1CrashReportCategory.addCrashSection("Entity ID", Integer.valueOf(this.entityId));
/* 4096 */     par1CrashReportCategory.addCrashSectionCallable("Entity Name", new CallableEntityName(this));
/* 4097 */     par1CrashReportCategory.addCrashSection("Entity's Exact location", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ) }));
/* 4098 */     par1CrashReportCategory.addCrashSection("Entity's Block location", CrashReportCategory.getLocationInfo(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));
/* 4099 */     par1CrashReportCategory.addCrashSection("Entity's Momentum", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ) }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canRenderOnFire() {
/* 4107 */     return isBurning();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isExpectedToHaveUUID() {
/* 4115 */     if (this.worldObj == null) {
/*      */       
/* 4117 */       boolean is_server_thread = Minecraft.isServerThread();
/*      */       
/* 4119 */       if (is_server_thread) {
/*      */         
/* 4121 */         Debug.setErrorMessage("isExpectedToHaveUUID: worldObj was null for " + getClass() + " on server thread");
/* 4122 */         Debug.printStackTrace();
/*      */       } 
/*      */       
/* 4125 */       return is_server_thread;
/*      */     } 
/*      */ 
/*      */     
/* 4129 */     return onServer();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public UUID getUniqueID() {
/* 4135 */     if (!isExpectedToHaveUUID()) {
/*      */       
/* 4137 */       Minecraft.setErrorMessage("getUniqueID: entity not expected to have a UUID " + this);
/*      */       
/* 4139 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 4142 */     if (this.entityUniqueID == null) {
/*      */       
/* 4144 */       Minecraft.setErrorMessage("getUniqueID: was null for " + this);
/*      */       
/* 4146 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 4149 */     return this.entityUniqueID;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public UUID getUniqueIDSilent() {
/* 4155 */     return this.entityUniqueID;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPushedByWater() {
/* 4160 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTranslatedEntityName() {
/* 4168 */     return getEntityName();
/*      */   }
/*      */ 
/*      */   
/*      */   static {
/* 4173 */     entity_look_helper_class = EntityLookHelper.getTheClass();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasSkyAbove() {
/* 4178 */     return this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOutdoors() {
/* 4183 */     return this.worldObj.isOutdoors(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInSunlight() {
/* 4188 */     return this.worldObj.isInSunlight(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInRain() {
/* 4194 */     return this.worldObj.isInRain(getBlockPosX(), getBlockPosY(), getBlockPosZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInPrecipitation() {
/* 4200 */     return this.worldObj.isPrecipitatingAt(getBlockPosX(), getBlockPosY(), getBlockPosZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistanceSqToSpawnPoint() {
/* 4206 */     return World.getDistanceSqFromDeltas(this.posX - this.spawn_x, this.posY - this.spawn_y, this.posZ - this.spawn_z);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final double getDistanceSqToWorldSpawnPoint(boolean include_delta_y) {
/* 4212 */     return World.getDistanceSqFromDeltas(this.posX - this.worldObj.getSpawnX(), include_delta_y ? (this.posY - this.worldObj.getSpawnY()) : 0.0D, this.posZ - this.worldObj.getSpawnZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getDistanceToWorldSpawnPoint(boolean include_delta_y) {
/* 4217 */     return MathHelper.sqrt_double(getDistanceSqToWorldSpawnPoint(include_delta_y));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNearToBlock(Block block, int max_horizontal_distance, int max_vertical_distance) {
/* 4222 */     return this.worldObj.blockTypeIsNearTo(block.blockID, this.posX, this.posY, this.posZ, max_horizontal_distance, max_vertical_distance);
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getNearbyEntityByUniqueID(String unique_id_string) {
/* 4227 */     if (unique_id_string == null || unique_id_string.isEmpty()) {
/* 4228 */       return null;
/*      */     }
/* 4230 */     List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 8.0D, 32.0D));
/*      */     
/* 4232 */     Iterator<Entity> i = entities.iterator();
/*      */     
/* 4234 */     while (i.hasNext()) {
/*      */       
/* 4236 */       Entity entity = i.next();
/*      */       
/* 4238 */       if (entity.getUniqueID().toString().equals(unique_id_string)) {
/* 4239 */         return entity;
/*      */       }
/*      */     } 
/* 4242 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getNearbyEntities(float max_horizontal_distance, float max_vertical_distance) {
/* 4247 */     return this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(max_horizontal_distance, max_vertical_distance, max_horizontal_distance));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockPosX() {
/* 4257 */     return MathHelper.floor_double(this.posX);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlockPosY() {
/* 4265 */     return MathHelper.floor_double(this.posY);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getBlockPosZ() {
/* 4270 */     return MathHelper.floor_double(this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getChunkPosX() {
/* 4281 */     return getBlockPosX() >> 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getChunkPosZ() {
/* 4292 */     return getBlockPosZ() >> 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChunkCurrentlyInSectionIndex() {
/*      */     double effective_pos_y;
/* 4302 */     if (this instanceof EntityLivingBase) {
/* 4303 */       effective_pos_y = getAsEntityLivingBase().getFootPosY();
/*      */     } else {
/* 4305 */       effective_pos_y = this.posY;
/*      */     } 
/*      */     
/* 4308 */     int index = MathHelper.floor_double(effective_pos_y / 16.0D);
/*      */     
/* 4310 */     if (index < 0) {
/* 4311 */       index = 0;
/* 4312 */     } else if (index >= 16) {
/* 4313 */       index = 15;
/*      */     } 
/* 4315 */     return index;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isRestingOnBlock(int x, int y, int z, double test_y, int[] dy) {
/* 4320 */     dy[0] = 0;
/*      */     
/* 4322 */     if (!this.worldObj.isAirOrPassableBlock(x, y, z, true)) {
/* 4323 */       return true;
/*      */     }
/* 4325 */     Block block = this.worldObj.getBlock(x, y, z);
/*      */     
/* 4327 */     if (block == Block.snow) {
/*      */       
/* 4329 */       block.setBlockBoundsBasedOnStateAndNeighbors(this.worldObj, x, y, z);
/*      */       
/* 4331 */       if (test_y < y + block.maxY[Minecraft.getThreadIndex()]) {
/* 4332 */         return true;
/*      */       }
/*      */     } 
/* 4335 */     block = this.worldObj.getBlock(x, y - 1, z);
/*      */     
/* 4337 */     if (block != null) {
/*      */       
/* 4339 */       block.setBlockBoundsBasedOnStateAndNeighbors(this.worldObj, x, y - 1, z);
/*      */       
/* 4341 */       double maxY = block.maxY[Minecraft.getThreadIndex()];
/*      */       
/* 4343 */       if (block instanceof BlockFence) {
/* 4344 */         maxY = 1.5D;
/*      */       }
/* 4346 */       if (test_y < (y - 1) + maxY && !this.worldObj.isAirOrPassableBlock(x, y - 1, z, true)) {
/*      */         
/* 4348 */         dy[0] = -1;
/* 4349 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 4353 */     return false;
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
/*      */   public BlockInfo getBlockRestingOn(float y_allowance) {
/* 4468 */     return getBlockRestingOn3();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final BlockInfo getBlockRestingOn3() {
/* 4474 */     if (!this.onGround || this.ridingEntity != null) {
/* 4475 */       return null;
/*      */     }
/* 4477 */     BlockInfo info = null;
/*      */     
/* 4479 */     double highest_max_y = 0.0D;
/* 4480 */     double shortest_distance_sq = 0.0D;
/*      */     
/* 4482 */     int min_x = this.boundingBox.getBlockCoordForMinX();
/* 4483 */     int max_x = this.boundingBox.getBlockCoordForMaxX();
/* 4484 */     int min_y = this.boundingBox.getBlockCoordForMinY();
/* 4485 */     int max_y = this.boundingBox.getBlockCoordForMaxY();
/* 4486 */     int min_z = this.boundingBox.getBlockCoordForMinZ();
/* 4487 */     int max_z = this.boundingBox.getBlockCoordForMaxZ();
/*      */     
/* 4489 */     for (int x = min_x; x <= max_x; x++) {
/*      */       
/* 4491 */       for (int z = min_z; z <= max_z; z++) {
/*      */         
/* 4493 */         for (int y = min_y - 1; y <= max_y; y++) {
/*      */           
/* 4495 */           Block block = this.worldObj.getBlock(x, y, z);
/*      */           
/* 4497 */           if (block != null) {
/*      */             
/* 4499 */             AxisAlignedBB bb = block.getCollisionBoundsCombined(this.worldObj, x, y, z, this, true);
/*      */             
/* 4501 */             if (bb != null && bb.maxY == this.boundingBox.minY)
/*      */             {
/*      */               
/* 4504 */               if (info == null || bb.maxY > highest_max_y) {
/*      */                 
/* 4506 */                 info = new BlockInfo(block, x, y, z, this.worldObj.getBlockMetadata(x, y, z));
/* 4507 */                 highest_max_y = bb.maxY;
/*      */                 
/* 4509 */                 double dx = x + 0.5D - this.posX;
/* 4510 */                 double dz = z + 0.5D - this.posZ;
/*      */                 
/* 4512 */                 shortest_distance_sq = dx * dx + dz * dz;
/*      */               }
/* 4514 */               else if (bb.maxY == highest_max_y) {
/*      */                 
/* 4516 */                 double dx = x + 0.5D - this.posX;
/* 4517 */                 double dz = z + 0.5D - this.posZ;
/*      */                 
/* 4519 */                 double distance_sq = dx * dx + dz * dz;
/*      */                 
/* 4521 */                 if (distance_sq < shortest_distance_sq) {
/*      */                   
/* 4523 */                   info = new BlockInfo(block, x, y, z, this.worldObj.getBlockMetadata(x, y, z));
/* 4524 */                   shortest_distance_sq = distance_sq;
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 4532 */     return info;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSuspendedInLiquid() {
/* 4538 */     if (this.ridingEntity != null || this.onGround) {
/* 4539 */       return false;
/*      */     }
/* 4541 */     if (!isInWater() && !handleLavaMovement()) {
/* 4542 */       return false;
/*      */     }
/* 4544 */     return (getBlockRestingOn(0.2F) == null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isImmuneToExplosion() {
/* 4549 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean handleExplosion(Explosion explosion) {
/* 4554 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void handlePacket89(Packet89PlaySoundOnServerAtEntity packet) {}
/*      */   
/*      */   public int getFragParticle() {
/* 4561 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumFragParticles() {
/* 4566 */     return 40;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean spawnFragParticles() {
/* 4571 */     if (this.worldObj.isRemote) {
/*      */       
/* 4573 */       int frag_item_id = getFragParticle();
/*      */       
/* 4575 */       if (frag_item_id < 0) {
/* 4576 */         return false;
/*      */       }
/* 4578 */       int frag_particles = getNumFragParticles();
/*      */       
/* 4580 */       for (int i = 0; i < frag_particles; i++) {
/*      */         
/* 4582 */         float vel_x = this.rand.nextFloat() * 0.2F - 0.1F;
/* 4583 */         float vel_y = this.rand.nextFloat() * 0.2F - 0.1F;
/* 4584 */         float vel_z = this.rand.nextFloat() * 0.2F - 0.1F;
/*      */         
/* 4586 */         while (vel_x * vel_x + vel_y * vel_y + vel_z * vel_z < 0.25F) {
/*      */           
/* 4588 */           vel_x *= 2.0F;
/* 4589 */           vel_y *= 2.0F;
/* 4590 */           vel_z *= 2.0F;
/*      */         } 
/*      */         
/* 4593 */         while (vel_x * vel_x + vel_y * vel_y + vel_z * vel_z < 1.0F) {
/*      */           
/* 4595 */           vel_x *= 1.1F;
/* 4596 */           vel_y *= 1.1F;
/* 4597 */           vel_z *= 1.1F;
/*      */         } 
/*      */         
/* 4600 */         this.worldObj.spawnParticleEx(EnumParticle.iconcrack, frag_item_id, 0, this.posX + (this.rand.nextFloat() * this.width) - (this.width / 2.0F), this.posY + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width) - (this.width / 2.0F), vel_x, vel_y, vel_z);
/*      */       } 
/*      */       
/* 4603 */       return (frag_particles > 0);
/*      */     } 
/*      */     
/* 4606 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean drawBackFaces() {
/* 4611 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeDamagedByCacti() {
/* 4616 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isEntityLivingBase() {
/* 4621 */     return this instanceof EntityLivingBase;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isEntityLiving() {
/* 4626 */     return this instanceof EntityLiving;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeAttackedBy(EntityLivingBase attacker) {
/* 4631 */     if (!this.worldObj.isRemote) {
/* 4632 */       Minecraft.setErrorMessage("canBeAttackedBy: to be called on client only");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4641 */     if (isEntityUndead() && this.rand.nextInt(4) > 0 && attacker.hasCurse(Curse.fear_of_undead, true)) {
/* 4642 */       return false;
/*      */     }
/* 4644 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onTransferToWorld() {}
/*      */ 
/*      */   
/*      */   public final boolean hasModelItem() {
/* 4652 */     return (getModelItem() != null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Item getModelItem() {
/* 4658 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFireResistance() {
/* 4664 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void refreshDespawnCounter(int value) {
/* 4670 */     if (this.despawn_counter > value) {
/* 4671 */       this.despawn_counter = value;
/*      */     }
/*      */   }
/*      */   
/*      */   public final EnumDirection getDirectionFromYaw() {
/* 4676 */     return EnumDirection.getDirectionFromYaw(this.rotationYaw);
/*      */   }
/*      */ 
/*      */   
/*      */   public final EnumDirection getDirectionFromPitch() {
/* 4681 */     return EnumDirection.getDirectionFromPitch(this.rotationPitch);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean onClient() {
/* 4687 */     return this.worldObj.isRemote;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean onServer() {
/* 4693 */     return !this.worldObj.isRemote;
/*      */   }
/*      */ 
/*      */   
/*      */   public final WorldClient getWorldClient() {
/* 4698 */     return (WorldClient)this.worldObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public final WorldServer getWorldServer() {
/* 4703 */     return (WorldServer)this.worldObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getRotationYawAsSixteenths() {
/* 4708 */     return Math.round(this.rotationYaw / 22.5F) & 0xF;
/*      */   }
/*      */ 
/*      */   
/*      */   public final World getWorld() {
/* 4713 */     return this.worldObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public final EntityPlayer getAsPlayer() {
/* 4718 */     return (EntityPlayer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public final ServerPlayer getAsEntityPlayerMP() {
/* 4723 */     return (ServerPlayer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public final EntityLivingBase getAsEntityLivingBase() {
/* 4728 */     return (EntityLivingBase)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public final EntityLiving getAsEntityLiving() {
/* 4733 */     return (EntityLiving)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public final EntityAnimal getAsEntityAnimal() {
/* 4738 */     return (EntityAnimal)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public final EntityTameable getAsEntityTameable() {
/* 4743 */     return (EntityTameable)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public float adjustPlayerReachForAttacking(EntityPlayer player, float reach) {
/* 4748 */     return reach;
/*      */   }
/*      */ 
/*      */   
/*      */   public float adjustPlayerReachForInteraction(EntityPlayer player, float reach) {
/* 4753 */     return reach;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 4760 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doesYawHaveNorthComponent() {
/* 4766 */     return EnumDirection.doesYawHaveNorthComponent(this.rotationYaw);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doesYawHaveSouthComponent() {
/* 4772 */     return EnumDirection.doesYawHaveSouthComponent(this.rotationYaw);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doesYawHaveWestComponent() {
/* 4778 */     return EnumDirection.doesYawHaveWestComponent(this.rotationYaw);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doesYawHaveEastComponent() {
/* 4784 */     return EnumDirection.doesYawHaveEastComponent(this.rotationYaw);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPosString() {
/* 4789 */     return this.posX + "," + this.posY + "," + this.posZ;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBlockPosString() {
/* 4795 */     return getBlockPosX() + "," + getBlockPosY() + "," + getBlockPosZ();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWithinTournamentSafeZone() {
/* 4800 */     return this.worldObj.isWithinTournamentSafeZone(getBlockPosX(), (this instanceof EntityLivingBase) ? getAsEntityLivingBase().getFootBlockPosY() : getBlockPosY(), getBlockPosZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isZevimrgvInTournament() {
/* 4805 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isArrow() {
/* 4810 */     return this instanceof EntityArrow;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByDrowning() {
/* 4815 */     return false;
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
/*      */   public boolean canBeKnockedBack() {
/* 4865 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 4871 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void transferToChunk(Chunk chunk) {
/* 4877 */     if (chunk == null && this.chunk_added_to == null) {
/*      */       
/* 4879 */       Minecraft.setErrorMessage("transferToChunk: from null to null?");
/* 4880 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 4883 */     if (this.chunk_added_to != null) {
/* 4884 */       this.chunk_added_to.removeEntity(this);
/*      */     }
/* 4886 */     if (chunk != null) {
/* 4887 */       chunk.addEntity(this);
/*      */     }
/*      */   }
/*      */   
/*      */   public void removeFromChunk() {
/* 4892 */     if (this.chunk_added_to == null) {
/* 4893 */       Minecraft.setErrorMessage("removeFromChunk: " + getEntityName() + " hasn't been added to a chunk");
/*      */     } else {
/* 4895 */       transferToChunk(null);
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
/*      */   public Chunk getChunkAddedTo() {
/* 4914 */     return this.chunk_added_to;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAddedToAChunk() {
/* 4922 */     return (this.chunk_added_to != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChunkAddedToUnchecked(Chunk chunk, int chunk_added_to_section_index) {
/* 4929 */     this.chunk_added_to = chunk;
/*      */     
/* 4931 */     if (chunk == null && chunk_added_to_section_index != -1) {
/* 4932 */       Minecraft.setErrorMessage("setChunkAddedToUnchecked: setting to null but section index!=-1");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4939 */     this.chunk_added_to_section_index = chunk_added_to_section_index;
/*      */   }
/*      */ 
/*      */   
/*      */   public void transferToChunkCurrentlyIn() {
/* 4944 */     Chunk chunk = getChunkFromPosition();
/*      */     
/* 4946 */     if (chunk == null) {
/* 4947 */       Minecraft.setErrorMessage("transferToChunkCurrentlyIn: chunk currently in does not exist");
/*      */     } else {
/* 4949 */       transferToChunk(chunk);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public List getTargetPoints() {
/* 4955 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getCenterPoint() {
/* 4960 */     return this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY + (this.height * 0.5F), this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onMeleeAttacked(EntityLivingBase attacker, EntityDamageResult result) {}
/*      */ 
/*      */   
/*      */   public boolean cannotRaycastCollideWith(Entity entity) {
/* 4969 */     return (entity == this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void modifyEffectiveCollisionBoxForRaycastFromEntity(AxisAlignedBB effective_collision_box, Entity entity) {
/* 4974 */     if (entity == this.riddenByEntity) {
/* 4975 */       effective_collision_box.translate(0.0D, -0.5D, 0.0D);
/*      */     }
/*      */   }
/*      */   
/*      */   public final boolean canEntityBeSeenFrom(double x, double y, double z, double max_range_sq) {
/* 4980 */     return canEntityBeSeenFrom(x, y, z, max_range_sq, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canEntityBeSeenFrom(double x, double y, double z, double max_range_sq, boolean ignore_leaves) {
/* 4986 */     Vec3 origin = this.worldObj.getVec3(x, y, z);
/* 4987 */     Vec3 limit = getCenterPoint();
/*      */     
/* 4989 */     if (origin.squareDistanceTo(limit) > max_range_sq) {
/* 4990 */       return false;
/*      */     }
/* 4992 */     return this.worldObj.checkForNoBlockCollision(origin, limit, RaycastPolicies.for_vision(ignore_leaves));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isUnderOpenSky() {
/* 4999 */     return (this.worldObj.isOverworld() && this.worldObj.canBlockSeeTheSky(getBlockPosX(), getBlockPosY(), getBlockPosZ()));
/*      */   }
/*      */ 
/*      */   
/*      */   public BiomeGenBase getBiome() {
/* 5004 */     return this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final List getCollidingBlockBounds() {
/* 5010 */     return this.worldObj.getCollidingBlockBounds(this.boundingBox, this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPlayerInCreative() {
/* 5015 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Chunk getChunkFromPosition() {
/* 5021 */     return this.worldObj.getChunkFromPosition(this.posX, this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isInUnderworld() {
/* 5027 */     return this.worldObj.isUnderworld();
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isInNether() {
/* 5032 */     return this.worldObj.isTheNether();
/*      */   }
/*      */ 
/*      */   
/*      */   public void onCollidedWithBlock(World world, Block block, int x, int y, int z) {}
/*      */   
/*      */   public BlockInfo[] getBlocksBelow() {
/* 5039 */     int min_x = this.boundingBox.getBlockCoordForMinX();
/* 5040 */     int max_x = this.boundingBox.getBlockCoordForMaxX();
/*      */     
/* 5042 */     int min_z = this.boundingBox.getBlockCoordForMinZ();
/* 5043 */     int max_z = this.boundingBox.getBlockCoordForMaxZ();
/*      */     
/* 5045 */     int num_blocks_x = max_x - min_x + 1;
/* 5046 */     int num_blocks_z = max_z - min_z + 1;
/*      */     
/* 5048 */     int num_blocks = num_blocks_x * num_blocks_z;
/*      */     
/* 5050 */     BlockInfo[] infos = new BlockInfo[num_blocks];
/*      */     
/* 5052 */     int y = (isEntityLivingBase() ? getAsEntityLivingBase().getFootBlockPosY() : getBlockPosY()) - 1;
/*      */     
/* 5054 */     int index = -1;
/*      */     
/* 5056 */     for (int x = min_x; x <= max_x; x++) {
/*      */       
/* 5058 */       for (int z = min_z; z <= max_z; z++) {
/*      */ 
/*      */         
/* 5061 */         index++;
/*      */         
/* 5063 */         Block block = this.worldObj.getBlock(x, y, z);
/*      */         
/* 5065 */         if (block != null)
/*      */         {
/*      */           
/* 5068 */           infos[index] = new BlockInfo(this.worldObj, block, x, y, z);
/*      */         }
/*      */       } 
/*      */     } 
/* 5072 */     return infos;
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
/*      */   public BlockInfo[] getBlocksOccupied(float expansion_x, float expansion_y_down, float expansion_y_up, float expansion_z, boolean must_intersect) {
/* 5090 */     AxisAlignedBB bb = this.boundingBox.copy();
/*      */     
/* 5092 */     bb.minX -= expansion_x;
/* 5093 */     bb.maxX += expansion_x;
/*      */     
/* 5095 */     bb.minY -= expansion_y_down;
/* 5096 */     bb.maxY += expansion_y_up;
/*      */     
/* 5098 */     bb.minZ -= expansion_z;
/* 5099 */     bb.maxZ += expansion_z;
/*      */     
/* 5101 */     int min_x = bb.getBlockCoordForMinX();
/* 5102 */     int max_x = bb.getBlockCoordForMaxX();
/*      */     
/* 5104 */     int min_y = bb.getBlockCoordForMinY();
/* 5105 */     int max_y = bb.getBlockCoordForMaxY();
/*      */     
/* 5107 */     int min_z = bb.getBlockCoordForMinZ();
/* 5108 */     int max_z = bb.getBlockCoordForMaxZ();
/*      */ 
/*      */ 
/*      */     
/* 5112 */     int num_blocks_x = max_x - min_x + 1;
/* 5113 */     int num_blocks_y = max_y - min_y + 1;
/* 5114 */     int num_blocks_z = max_z - min_z + 1;
/*      */     
/* 5116 */     int num_blocks = num_blocks_x * num_blocks_y * num_blocks_z;
/*      */     
/* 5118 */     BlockInfo[] infos = new BlockInfo[num_blocks];
/*      */     
/* 5120 */     int index = -1;
/*      */     
/* 5122 */     for (int x = min_x; x <= max_x; x++) {
/*      */       
/* 5124 */       for (int z = min_z; z <= max_z; z++) {
/*      */         
/* 5126 */         for (int y = min_y; y <= max_y; y++) {
/*      */           
/* 5128 */           index++;
/*      */           
/* 5130 */           Block block = this.worldObj.getBlock(x, y, z);
/*      */           
/* 5132 */           if (block == null) {
/*      */             continue;
/*      */           }
/* 5135 */           if (must_intersect)
/*      */           {
/* 5137 */             if (!block.doRenderBoundsIntersectWith(this.worldObj, x, y, z, bb)) {
/*      */               continue;
/*      */             }
/*      */           }
/* 5141 */           infos[index] = new BlockInfo(this.worldObj, block, x, y, z);
/*      */           continue;
/*      */         } 
/*      */       } 
/*      */     } 
/* 5146 */     return infos;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isTrueAnimal() {
/* 5152 */     if (this instanceof EntityAnimal) {
/*      */       
/* 5154 */       if (this instanceof EntityHellhound) {
/* 5155 */         return false;
/*      */       }
/* 5157 */       return true;
/*      */     } 
/*      */     
/* 5160 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getPredictedPosX(float lead) {
/* 5165 */     if (this instanceof ServerPlayer) {
/*      */       
/* 5167 */       double last_received_motion_x = (getAsEntityPlayerMP()).last_received_motion_x;
/*      */       
/* 5169 */       if (Math.abs(last_received_motion_x) <= 1.0D) {
/* 5170 */         return this.posX + last_received_motion_x * lead;
/*      */       }
/*      */     } 
/* 5173 */     return this.posX + this.motionX * lead;
/*      */   }
/*      */ 
/*      */   
/*      */   public final double getPredictedPosZ(float lead) {
/* 5178 */     if (this instanceof ServerPlayer) {
/*      */       
/* 5180 */       double last_received_motion_z = (getAsEntityPlayerMP()).last_received_motion_z;
/*      */       
/* 5182 */       if (Math.abs(last_received_motion_z) <= 1.0D) {
/* 5183 */         return this.posZ + last_received_motion_z * lead;
/*      */       }
/*      */     } 
/* 5186 */     return this.posZ + this.motionZ * lead;
/*      */   }
/*      */ 
/*      */   
/*      */   public static final boolean isClass(Entity entity, Class<?> _class) {
/* 5191 */     return (entity != null && entity.getClass() == _class);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isAtCoordsInQuestion() {
/* 5196 */     return (getBlockPosX() == -605 && getBlockPosY() == 5 && getBlockPosZ() == 198);
/*      */   }
/*      */   
/*      */   protected abstract void entityInit();
/*      */   
/*      */   protected abstract void readEntityFromNBT(NBTTagCompound paramNBTTagCompound);
/*      */   
/*      */   protected abstract void writeEntityToNBT(NBTTagCompound paramNBTTagCompound);
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Entity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */