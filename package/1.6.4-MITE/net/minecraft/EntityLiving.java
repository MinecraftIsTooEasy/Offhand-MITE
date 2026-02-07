/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class EntityLiving
/*      */   extends EntityLivingBase
/*      */ {
/*      */   public int livingSoundTime;
/*      */   private EntityLookHelper lookHelper;
/*      */   private EntityMoveHelper moveHelper;
/*      */   private EntityJumpHelper jumpHelper;
/*      */   private EntityBodyHelper bodyHelper;
/*      */   private PathNavigate navigator;
/*      */   protected final EntityAITasks tasks;
/*      */   protected final EntityAITasks targetTasks;
/*      */   private EntityLivingBase attackTarget;
/*      */   private EntitySenses senses;
/*   31 */   private ItemStack[] equipment = new ItemStack[5];
/*      */ 
/*      */   
/*   34 */   protected float[] equipmentDropChances = new float[5];
/*      */ 
/*      */   
/*      */   private boolean canPickUpLoot;
/*      */ 
/*      */   
/*      */   private boolean persistenceRequired;
/*      */ 
/*      */   
/*      */   protected float defaultPitch;
/*      */   
/*      */   private Entity currentTarget;
/*      */   
/*      */   protected int numTicksToChaseTarget;
/*      */   
/*      */   private boolean isLeashed;
/*      */   
/*      */   private Entity leashedToEntity;
/*      */   
/*      */   private NBTTagCompound field_110170_bx;
/*      */   
/*      */   public boolean picked_up_a_held_item;
/*      */   
/*      */   public int food_or_repair_item_pickup_cooldown;
/*      */   
/*      */   protected long spooked_until;
/*      */   
/*      */   private boolean is_decoy;
/*      */   
/*      */   protected boolean came_from_spawner;
/*      */   
/*      */   protected boolean came_from_spawn_block;
/*      */   
/*      */   private int spawn_block_x;
/*      */   
/*      */   private int spawn_block_y;
/*      */   
/*      */   private int spawn_block_z;
/*      */   
/*      */   public int ticks_disarmed;
/*      */   
/*      */   private String target_unique_id_string;
/*      */   
/*      */   public EntityLivingBase AI_retarget;
/*      */   
/*      */   public int increased_chance_of_spreading_fire_countdown;
/*      */   
/*      */   public EntityItem target_entity_item;
/*      */   
/*      */   public long last_tick_harmed_by_cactus;
/*      */ 
/*      */   
/*      */   public EntityLiving(World par1World) {
/*   87 */     super(par1World);
/*   88 */     this.tasks = new EntityAITasks((par1World != null && par1World.theProfiler != null) ? par1World.theProfiler : null);
/*   89 */     this.targetTasks = new EntityAITasks((par1World != null && par1World.theProfiler != null) ? par1World.theProfiler : null);
/*   90 */     this.lookHelper = new EntityLookHelper(this);
/*   91 */     this.moveHelper = new EntityMoveHelper(this);
/*   92 */     this.jumpHelper = new EntityJumpHelper(this);
/*   93 */     this.bodyHelper = new EntityBodyHelper(this);
/*   94 */     this.navigator = new PathNavigate(this, par1World);
/*   95 */     this.senses = new EntitySenses(this);
/*      */     
/*   97 */     for (int var2 = 0; var2 < this.equipmentDropChances.length; var2++)
/*      */     {
/*   99 */       this.equipmentDropChances[var2] = 0.085F;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  105 */     super.applyEntityAttributes();
/*      */     
/*  107 */     setEntityAttribute(SharedMonsterAttributes.followRange, 16.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLookHelper getLookHelper() {
/*  113 */     return this.lookHelper;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityMoveHelper getMoveHelper() {
/*  118 */     return this.moveHelper;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityJumpHelper getJumpHelper() {
/*  123 */     return this.jumpHelper;
/*      */   }
/*      */ 
/*      */   
/*      */   public PathNavigate getNavigator() {
/*  128 */     return this.navigator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntitySenses getEntitySenses() {
/*  136 */     return this.senses;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLivingBase getAttackTarget() {
/*  144 */     if (!isAIEnabled()) {
/*  145 */       Minecraft.setErrorMessage("getAttackTarget() called for " + getEntityName());
/*      */     }
/*  147 */     if (this.attackTarget != null && this.attackTarget.isDead) {
/*  148 */       setAttackTarget((EntityLivingBase)null);
/*      */     }
/*  150 */     return this.attackTarget;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
/*  158 */     if (!isAIEnabled()) {
/*  159 */       Minecraft.setErrorMessage("setAttackTarget() called for " + getEntityName());
/*      */     }
/*  161 */     if (par1EntityLivingBase != null && par1EntityLivingBase.isDead) {
/*  162 */       par1EntityLivingBase = null;
/*      */     }
/*  164 */     this.attackTarget = par1EntityLivingBase;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackClass(Class<EntityCreeper> par1Class) {
/*  172 */     return (EntityCreeper.class != par1Class && EntityGhast.class != par1Class);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void eatGrassBonus() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  183 */     super.entityInit();
/*  184 */     this.dataWatcher.addObject(11, Byte.valueOf((byte)0));
/*  185 */     this.dataWatcher.addObject(10, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTalkInterval() {
/*  193 */     return 80;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void makeLivingSound() {
/*  203 */     if (isSpooked() && this.rand.nextInt(2) == 0) {
/*      */       
/*  205 */       String str = getHurtSound();
/*      */       
/*  207 */       if (str != null) {
/*      */         
/*  209 */         makeSound(str);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  215 */     String var1 = getLivingSound();
/*      */     
/*  217 */     if (var1 != null)
/*      */     {
/*      */       
/*  220 */       makeSound(var1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeLongDistanceLivingSound() {
/*  226 */     String var1 = getLongDistanceLivingSound();
/*      */     
/*  228 */     if (var1 != null) {
/*  229 */       makeLongDistanceSound(var1);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isPlayerNearby(double range) {
/*  234 */     return this.worldObj.isPlayerNearby(this.posX, this.posY, this.posZ, range);
/*      */   }
/*      */ 
/*      */   
/*      */   public double distanceToNearestPlayer() {
/*  239 */     return this.worldObj.distanceToNearestPlayer(this.posX, this.posY, this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  247 */     super.onEntityUpdate();
/*  248 */     this.worldObj.theProfiler.startSection("mobBaseTick");
/*      */     
/*  250 */     if (isEntityAlive() && this.rand.nextInt(1000) < this.livingSoundTime++) {
/*      */       
/*  252 */       this.livingSoundTime = -getTalkInterval();
/*      */ 
/*      */ 
/*      */       
/*  256 */       if (this.worldObj.isRemote) {
/*      */         return;
/*      */       }
/*  259 */       double distance_to_nearest_player = distanceToNearestPlayer();
/*      */       
/*  261 */       if (distance_to_nearest_player <= 16.0D) {
/*  262 */         makeLivingSound();
/*  263 */       } else if (distance_to_nearest_player <= 64.0D) {
/*      */         
/*  265 */         makeLongDistanceLivingSound();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  272 */     this.worldObj.theProfiler.endSection();
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
/*      */   public void spawnExplosionParticle() {
/*  306 */     for (int var1 = 0; var1 < 20; var1++) {
/*      */       
/*  308 */       double var2 = this.rand.nextGaussian() * 0.02D;
/*  309 */       double var4 = this.rand.nextGaussian() * 0.02D;
/*  310 */       double var6 = this.rand.nextGaussian() * 0.02D;
/*  311 */       double var8 = 10.0D;
/*      */       
/*  313 */       this.worldObj.spawnParticle(EnumParticle.explode, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width - var2 * var8, this.posY + (this.rand.nextFloat() * this.height) - var4 * var8, this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width - var6 * var8, var2, var4, var6);
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
/*      */   public void onUpdate() {
/*  325 */     this.senses.clearSensingCache();
/*      */     
/*  327 */     super.onUpdate();
/*      */     
/*  329 */     if (!this.worldObj.isRemote) {
/*      */       
/*  331 */       if (isEntityAlive()) {
/*  332 */         func_110159_bB();
/*      */       } else {
/*  334 */         clearLeashed(true, true);
/*      */       } 
/*      */ 
/*      */       
/*  338 */       if (this.increased_chance_of_spreading_fire_countdown > 0) {
/*  339 */         this.increased_chance_of_spreading_fire_countdown--;
/*      */       }
/*      */     } 
/*  342 */     if (!this.worldObj.isRemote && this.target_unique_id_string != null && getTarget() == null && this.ticksExisted % 10 == 0) {
/*      */       
/*  344 */       EntityLivingBase target = (EntityLivingBase)getNearbyEntityByUniqueID(this.target_unique_id_string);
/*      */       
/*  346 */       if (target != null) {
/*      */         
/*  348 */         setTarget(target);
/*  349 */         this.target_unique_id_string = null;
/*      */       }
/*  351 */       else if (this.ticksExisted > 0 && this.ticksExisted % 1000 == 0) {
/*      */         
/*  353 */         this.target_unique_id_string = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected float func_110146_f(float par1, float par2) {
/*  361 */     if (isAIEnabled() || this instanceof EntityOoze) {
/*      */       
/*  363 */       this.bodyHelper.func_75664_a();
/*  364 */       return par2;
/*      */     } 
/*      */ 
/*      */     
/*  368 */     return super.func_110146_f(par1, par2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLivingSound() {
/*  377 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getLongDistanceLivingSound() {
/*  382 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getDropItemId() {
/*  390 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getDropItemSubtype() {
/*  395 */     return 0;
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
/*      */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/*  438 */     int num_drops = this.rand.nextInt(3);
/*      */     
/*  440 */     int fortune = damage_source.getLootingModifier();
/*      */     
/*  442 */     if (fortune > 0) {
/*  443 */       num_drops += this.rand.nextInt(fortune + 1);
/*      */     }
/*  445 */     if (num_drops > 0 && !recently_hit_by_player) {
/*  446 */       num_drops -= this.rand.nextInt(num_drops + 1);
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
/*  464 */     for (int i = 0; i < num_drops; i++) {
/*      */       
/*  466 */       int item_id = getDropItemId();
/*      */       
/*  468 */       if (item_id >= 1) {
/*      */ 
/*      */         
/*  471 */         ItemStack item_stack = new ItemStack(item_id);
/*      */         
/*  473 */         if (item_stack.getHasSubtypes()) {
/*  474 */           item_stack.setItemSubtype(getDropItemSubtype());
/*      */         }
/*  476 */         dropItemStack(item_stack);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean hasEquipment() {
/*  482 */     for (int i = 0; i < this.equipment.length; i++) {
/*      */       
/*  484 */       if (this.equipment[i] != null) {
/*  485 */         return true;
/*      */       }
/*      */     } 
/*  488 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  496 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  497 */     par1NBTTagCompound.setBoolean("CanPickUpLoot", canPickUpLoot());
/*  498 */     par1NBTTagCompound.setBoolean("PersistenceRequired", this.persistenceRequired);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     if (hasEquipment()) {
/*      */       
/*  522 */       NBTTagList var2 = new NBTTagList();
/*      */       
/*  524 */       for (int var3 = 0; var3 < this.equipment.length; var3++) {
/*      */         
/*  526 */         NBTTagCompound var4 = new NBTTagCompound();
/*      */         
/*  528 */         if (this.equipment[var3] != null)
/*      */         {
/*  530 */           this.equipment[var3].writeToNBT(var4);
/*      */         }
/*      */         
/*  533 */         var2.appendTag(var4);
/*      */       } 
/*      */       
/*  536 */       par1NBTTagCompound.setTag("Equipment", var2);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  541 */     NBTTagList var6 = new NBTTagList();
/*      */     
/*  543 */     for (int var7 = 0; var7 < this.equipmentDropChances.length; var7++)
/*      */     {
/*  545 */       var6.appendTag(new NBTTagFloat(var7 + "", this.equipmentDropChances[var7]));
/*      */     }
/*      */     
/*  548 */     par1NBTTagCompound.setTag("DropChances", var6);
/*  549 */     par1NBTTagCompound.setString("CustomName", getCustomNameTag());
/*  550 */     par1NBTTagCompound.setBoolean("CustomNameVisible", getAlwaysRenderNameTag());
/*  551 */     par1NBTTagCompound.setBoolean("Leashed", this.isLeashed);
/*      */     
/*  553 */     if (this.leashedToEntity != null) {
/*      */       
/*  555 */       NBTTagCompound var4 = new NBTTagCompound("Leash");
/*      */       
/*  557 */       if (this.leashedToEntity instanceof EntityLivingBase) {
/*      */         
/*  559 */         var4.setLong("UUIDMost", this.leashedToEntity.getUniqueID().getMostSignificantBits());
/*  560 */         var4.setLong("UUIDLeast", this.leashedToEntity.getUniqueID().getLeastSignificantBits());
/*      */       }
/*  562 */       else if (this.leashedToEntity instanceof EntityHanging) {
/*      */         
/*  564 */         EntityHanging var5 = (EntityHanging)this.leashedToEntity;
/*  565 */         var4.setInteger("X", var5.xPosition);
/*  566 */         var4.setInteger("Y", var5.yPosition);
/*  567 */         var4.setInteger("Z", var5.zPosition);
/*      */       } 
/*      */       
/*  570 */       par1NBTTagCompound.setTag("Leash", var4);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  576 */     par1NBTTagCompound.setBoolean("picked_up_a_held_item", this.picked_up_a_held_item);
/*  577 */     par1NBTTagCompound.setLong("spooked_until", this.spooked_until);
/*      */     
/*  579 */     if (this.is_decoy) {
/*  580 */       par1NBTTagCompound.setBoolean("is_decoy", this.is_decoy);
/*      */     }
/*  582 */     if (this.came_from_spawner) {
/*  583 */       par1NBTTagCompound.setBoolean("came_from_spawner", true);
/*      */     }
/*  585 */     if (this.came_from_spawn_block) {
/*      */       
/*  587 */       par1NBTTagCompound.setBoolean("came_from_spawn_block", true);
/*  588 */       par1NBTTagCompound.setInteger("spawn_block_x", this.spawn_block_x);
/*  589 */       par1NBTTagCompound.setInteger("spawn_block_y", this.spawn_block_y);
/*  590 */       par1NBTTagCompound.setInteger("spawn_block_z", this.spawn_block_z);
/*      */     } 
/*      */     
/*  593 */     if (this.ticks_disarmed > 0) {
/*  594 */       par1NBTTagCompound.setInteger("ticks_disarmed", this.ticks_disarmed);
/*      */     }
/*  596 */     EntityLivingBase target = getTarget();
/*      */     
/*  598 */     if (target != null) {
/*  599 */       par1NBTTagCompound.setString("target_unique_id_string", target.getUniqueID().toString());
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
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  612 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  613 */     setCanPickUpLoot(par1NBTTagCompound.getBoolean("CanPickUpLoot"));
/*  614 */     this.persistenceRequired = par1NBTTagCompound.getBoolean("PersistenceRequired");
/*      */     
/*  616 */     if (par1NBTTagCompound.hasKey("CustomName") && par1NBTTagCompound.getString("CustomName").length() > 0)
/*      */     {
/*  618 */       setCustomNameTag(par1NBTTagCompound.getString("CustomName"));
/*      */     }
/*      */     
/*  621 */     setAlwaysRenderNameTag(par1NBTTagCompound.getBoolean("CustomNameVisible"));
/*      */ 
/*      */ 
/*      */     
/*  625 */     if (par1NBTTagCompound.hasKey("Equipment")) {
/*      */       
/*  627 */       NBTTagList var2 = par1NBTTagCompound.getTagList("Equipment");
/*      */       
/*  629 */       for (int var3 = 0; var3 < this.equipment.length; var3++)
/*      */       {
/*  631 */         this.equipment[var3] = ItemStack.loadItemStackFromNBT((NBTTagCompound)var2.tagAt(var3));
/*      */       }
/*      */     } 
/*      */     
/*  635 */     if (par1NBTTagCompound.hasKey("DropChances")) {
/*      */       
/*  637 */       NBTTagList var2 = par1NBTTagCompound.getTagList("DropChances");
/*      */       
/*  639 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*      */       {
/*  641 */         this.equipmentDropChances[var3] = ((NBTTagFloat)var2.tagAt(var3)).data;
/*      */       }
/*      */     } 
/*      */     
/*  645 */     this.isLeashed = par1NBTTagCompound.getBoolean("Leashed");
/*      */     
/*  647 */     if (this.isLeashed && par1NBTTagCompound.hasKey("Leash"))
/*      */     {
/*  649 */       this.field_110170_bx = par1NBTTagCompound.getCompoundTag("Leash");
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
/*  660 */     if (par1NBTTagCompound.hasKey("picked_up_a_held_item")) {
/*  661 */       this.picked_up_a_held_item = par1NBTTagCompound.getBoolean("picked_up_a_held_item");
/*      */     }
/*  663 */     if (par1NBTTagCompound.hasKey("spooked_until")) {
/*  664 */       this.spooked_until = par1NBTTagCompound.getLong("spooked_until");
/*      */     }
/*  666 */     this.is_decoy = par1NBTTagCompound.getBoolean("is_decoy");
/*      */     
/*  668 */     if (par1NBTTagCompound.hasKey("came_from_spawner")) {
/*  669 */       this.came_from_spawner = true;
/*      */     }
/*  671 */     if (par1NBTTagCompound.hasKey("came_from_spawn_block")) {
/*      */       
/*  673 */       this.came_from_spawn_block = true;
/*  674 */       this.spawn_block_x = par1NBTTagCompound.getInteger("spawn_block_x");
/*  675 */       this.spawn_block_y = par1NBTTagCompound.getInteger("spawn_block_y");
/*  676 */       this.spawn_block_z = par1NBTTagCompound.getInteger("spawn_block_z");
/*      */     } 
/*      */     
/*  679 */     if (par1NBTTagCompound.hasKey("ticks_disarmed")) {
/*  680 */       this.ticks_disarmed = par1NBTTagCompound.getInteger("ticks_disarmed");
/*      */     }
/*  682 */     if (par1NBTTagCompound.hasKey("target_unique_id_string")) {
/*      */       
/*  684 */       this.target_unique_id_string = par1NBTTagCompound.getString("target_unique_id_string");
/*      */       
/*  686 */       if (this.target_unique_id_string.isEmpty()) {
/*  687 */         this.target_unique_id_string = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setMoveForward(float par1) {
/*  693 */     this.moveForward = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAIMoveSpeed(float par1) {
/*  701 */     super.setAIMoveSpeed(par1);
/*  702 */     setMoveForward(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canWearItem(ItemStack item_stack) {
/*  707 */     if (item_stack == null) {
/*  708 */       return false;
/*      */     }
/*  710 */     Item item = item_stack.getItem();
/*      */     
/*  712 */     return (item instanceof ItemArmor || item == Item.getItem(Block.pumpkin));
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getBoundingBoxForItemPickup() {
/*  717 */     return this.boundingBox.expand(1.0D, 0.25D, 1.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public List getItemsWithinPickupDistance() {
/*  722 */     return this.worldObj.getEntitiesWithinAABB(EntityItem.class, getBoundingBoxForItemPickup());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isItemWithinPickupDistance(EntityItem entity_item) {
/*  727 */     List entity_items = getItemsWithinPickupDistance();
/*  728 */     Iterator<EntityItem> iterator = entity_items.iterator();
/*      */     
/*  730 */     while (iterator.hasNext()) {
/*      */       
/*  732 */       if (entity_item == iterator.next()) {
/*  733 */         return true;
/*      */       }
/*      */     } 
/*  736 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canNeverPickUpItem(Item item) {
/*  741 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void tryPickUpItems() {
/*  747 */     if (this.worldObj.isRemote || !canPickUpLoot() || this.isDead || getHealth() <= 0.0F || !this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") || getTicksExistedWithOffset() % 10 != 0) {
/*      */       return;
/*      */     }
/*      */     
/*  751 */     List entity_items = getItemsWithinPickupDistance();
/*  752 */     Iterator<EntityItem> iterator = entity_items.iterator();
/*      */     
/*  754 */     while (iterator.hasNext()) {
/*      */       
/*  756 */       EntityItem entity_item = iterator.next();
/*      */       
/*  758 */       if (entity_item.isDead || entity_item.getEntityItem() == null) {
/*      */         continue;
/*      */       }
/*  761 */       ItemStack item_stack_on_ground = entity_item.getEntityItem();
/*  762 */       Item item_on_ground = item_stack_on_ground.getItem();
/*      */       
/*  764 */       if (canNeverPickUpItem(item_on_ground)) {
/*      */         continue;
/*      */       }
/*  767 */       if (item_on_ground instanceof ItemTool || canWearItem(item_stack_on_ground) || (getHeldItem() instanceof ItemBow && item_on_ground instanceof ItemBow)) {
/*      */ 
/*      */         
/*  770 */         int var5 = getEquipmentPosition(item_stack_on_ground);
/*      */         
/*  772 */         if (var5 > -1) {
/*      */           
/*  774 */           boolean pickup = true;
/*  775 */           ItemStack current_item_stack = getCurrentItemOrArmor(var5);
/*      */           
/*  777 */           if (current_item_stack != null) {
/*      */             
/*  779 */             Item current_item = current_item_stack.getItem();
/*      */             
/*  781 */             if (var5 == 0) {
/*      */               
/*  783 */               if (current_item instanceof ItemBow) {
/*      */                 
/*  785 */                 if (!(item_on_ground instanceof ItemBow)) {
/*      */                   
/*  787 */                   pickup = false;
/*      */ 
/*      */                 
/*      */                 }
/*  791 */                 else if (current_item_stack.isItemEnchanted()) {
/*  792 */                   pickup = false;
/*  793 */                 } else if (item_stack_on_ground.isItemEnchanted()) {
/*  794 */                   pickup = true;
/*      */                 } else {
/*  796 */                   pickup = (item_stack_on_ground.getItemDamage() < current_item_stack.getItemDamage());
/*      */                 }
/*      */               
/*  799 */               } else if (item_on_ground.isTool() && !current_item.isTool()) {
/*      */                 
/*  801 */                 pickup = true;
/*      */               }
/*  803 */               else if (item_on_ground.isTool() && current_item.isTool()) {
/*      */ 
/*      */ 
/*      */                 
/*  807 */                 ItemTool tool_on_ground = (ItemTool)item_on_ground;
/*  808 */                 ItemTool current_tool = (ItemTool)current_item;
/*      */ 
/*      */                 
/*  811 */                 if (tool_on_ground.getCombinedDamageVsEntity() == current_tool.getCombinedDamageVsEntity()) {
/*      */                   
/*  813 */                   if (current_item_stack.isItemEnchanted()) {
/*  814 */                     pickup = false;
/*  815 */                   } else if (item_stack_on_ground.isItemEnchanted() && !current_item_stack.isItemEnchanted()) {
/*  816 */                     pickup = true;
/*      */                   } else {
/*  818 */                     pickup = (item_stack_on_ground.getItemDamage() < current_item_stack.getItemDamage() || (item_stack_on_ground.hasTagCompound() && !current_item_stack.hasTagCompound()));
/*      */                   }
/*      */                 
/*      */                 } else {
/*      */                   
/*  823 */                   pickup = (tool_on_ground.getCombinedDamageVsEntity() > current_tool.getCombinedDamageVsEntity());
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/*  828 */                 pickup = false;
/*      */               }
/*      */             
/*  831 */             } else if (item_on_ground instanceof ItemArmor && !(current_item instanceof ItemArmor)) {
/*      */               
/*  833 */               pickup = true;
/*      */             }
/*  835 */             else if (item_on_ground instanceof ItemArmor && current_item instanceof ItemArmor) {
/*      */               
/*  837 */               ItemArmor armor_on_ground = (ItemArmor)item_on_ground;
/*  838 */               ItemArmor current_armor = (ItemArmor)current_item;
/*      */               
/*  840 */               if (armor_on_ground.getMultipliedProtection(item_stack_on_ground) == current_armor.getMultipliedProtection(current_item_stack)) {
/*      */                 
/*  842 */                 if (item_stack_on_ground.isItemEnchanted() && !current_item_stack.isItemEnchanted()) {
/*  843 */                   pickup = true;
/*      */                 } else {
/*  845 */                   pickup = (item_stack_on_ground.getItemDamage() < current_item_stack.getItemDamage() || (item_stack_on_ground.hasTagCompound() && !current_item_stack.hasTagCompound()));
/*      */                 } 
/*      */               } else {
/*      */                 
/*  849 */                 pickup = (armor_on_ground.getMultipliedProtection(item_stack_on_ground) > current_armor.getMultipliedProtection(current_item_stack));
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  854 */               pickup = false;
/*      */             } 
/*      */           } 
/*      */           
/*  858 */           if (pickup) {
/*      */             boolean set_dead;
/*  860 */             playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */ 
/*      */             
/*  863 */             if (current_item_stack != null && this.rand.nextFloat() < this.equipmentDropChances[var5]) {
/*  864 */               dropItemStack(current_item_stack, 0.0F);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  870 */             if (item_stack_on_ground.stackSize == 1) {
/*      */               
/*  872 */               setCurrentItemOrArmor(var5, item_stack_on_ground);
/*  873 */               set_dead = true;
/*      */             }
/*      */             else {
/*      */               
/*  877 */               setCurrentItemOrArmor(var5, item_stack_on_ground.copy().setStackSize(1));
/*  878 */               item_stack_on_ground.stackSize--;
/*  879 */               set_dead = false;
/*      */             } 
/*      */             
/*  882 */             this.equipmentDropChances[var5] = 2.0F;
/*  883 */             this.persistenceRequired = true;
/*      */ 
/*      */ 
/*      */             
/*  887 */             if (set_dead) {
/*      */               
/*  889 */               onItemPickup(entity_item, 1);
/*  890 */               entity_item.setDead();
/*      */             } 
/*      */             
/*  893 */             if (var5 == 0) {
/*  894 */               this.picked_up_a_held_item = true;
/*      */             }
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getChanceOfCatchingFireFromSunlightThisTick() {
/*  905 */     if (this.worldObj.isRemote) {
/*      */       
/*  907 */       Minecraft.setErrorMessage("getChanceOfCatchingFireFromSunlightThisTick: called on client?");
/*  908 */       return 0.0F;
/*      */     } 
/*      */     
/*  911 */     if (catchesFireInSunlight() && this.worldObj.isDaytime() && !isChild() && !isWearingHelmet(true) && !isInRain() && isEntityAlive() && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.height), MathHelper.floor_double(this.posZ))) {
/*      */       
/*  913 */       float brightness = getBrightness(1.0F);
/*      */       
/*  915 */       if (brightness > 0.5F) {
/*  916 */         return (brightness - 0.4F) * 2.0F / 30.0F;
/*      */       }
/*      */     } 
/*  919 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  928 */     if (getHealth() <= 0.0F)
/*      */     {
/*  930 */       super.onLivingUpdate();
/*      */     }
/*      */ 
/*      */     
/*  934 */     if (!this.worldObj.isRemote && catchesFireInSunlight() && this.worldObj.isDaytime() && !isChild() && !isWearingHelmet(true) && !isInRain() && !this.worldObj.isSkyOvercast(getBlockPosX(), getBlockPosZ()) && isEntityAlive()) {
/*      */       
/*  936 */       float brightness = getBrightness(1.0F);
/*      */       
/*  938 */       if (brightness > 0.5F && this.rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + this.height), MathHelper.floor_double(this.posZ)))
/*      */       {
/*  940 */         if (this.ticks_since_last_wet < 3) {
/*  941 */           causeQuenchEffect();
/*      */         } else {
/*  943 */           setFire(8);
/*      */         } 
/*      */       }
/*      */     } 
/*  947 */     super.onLivingUpdate();
/*  948 */     this.worldObj.theProfiler.startSection("looting");
/*      */     
/*  950 */     if (this.ticks_disarmed > 0) {
/*  951 */       this.ticks_disarmed--;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1081 */     if (!this.worldObj.isRemote && canPickUpLoot()) {
/* 1082 */       tryPickUpItems();
/*      */     }
/*      */ 
/*      */     
/* 1086 */     if (!this.worldObj.isRemote && !this.isDead && getHealth() > 0.0F) {
/*      */       
/* 1088 */       boolean refrains_from_eating = false;
/* 1089 */       boolean refrains_from_pickup = false;
/*      */       
/* 1091 */       if (this instanceof EntityTameable && ((EntityTameable)this).isTamed() && getHealthFraction() > 0.5F) {
/* 1092 */         refrains_from_eating = true;
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
/* 1103 */       if (this.food_or_repair_item_pickup_cooldown > 0) {
/*      */         
/* 1105 */         this.food_or_repair_item_pickup_cooldown--;
/* 1106 */         refrains_from_pickup = true;
/*      */       } 
/*      */ 
/*      */       
/* 1110 */       if (!refrains_from_pickup) {
/*      */ 
/*      */         
/* 1113 */         List items = getItemsWithinPickupDistance();
/*      */         
/* 1115 */         Iterator<EntityItem> i = items.iterator();
/*      */         
/* 1117 */         while (i.hasNext()) {
/*      */           
/* 1119 */           EntityItem entity_item = i.next();
/*      */           
/* 1121 */           if (entity_item.isDead || !entity_item.canBePickedUpBy(this)) {
/*      */             continue;
/*      */           }
/* 1124 */           ItemStack item_stack = entity_item.getEntityItem();
/*      */ 
/*      */ 
/*      */           
/* 1128 */           boolean picked_up = false;
/* 1129 */           boolean picked_up_as_valuable = false;
/*      */           
/* 1131 */           if (!refrains_from_eating && willEat(item_stack)) {
/*      */             
/* 1133 */             onFoodEaten(item_stack);
/*      */ 
/*      */             
/* 1136 */             picked_up = true;
/*      */           }
/* 1138 */           else if (willUseForRepair(item_stack)) {
/*      */             
/* 1140 */             onRepairItemPickup(item_stack);
/* 1141 */             picked_up = true;
/*      */ 
/*      */           
/*      */           }
/* 1145 */           else if (willPickupAsValuable(item_stack)) {
/*      */             
/* 1147 */             addContainedItem(item_stack.getItem());
/* 1148 */             picked_up = true;
/* 1149 */             picked_up_as_valuable = true;
/*      */           } 
/*      */           
/* 1152 */           if (picked_up) {
/*      */             
/* 1154 */             this.food_or_repair_item_pickup_cooldown = 400;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1159 */             if (picked_up_as_valuable) {
/* 1160 */               this.food_or_repair_item_pickup_cooldown = 40;
/*      */             }
/* 1162 */             item_stack.stackSize--;
/* 1163 */             playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */             
/* 1165 */             if (item_stack.stackSize == 0) {
/* 1166 */               entity_item.setDead();
/*      */             } else {
/* 1168 */               entity_item.age = -18000;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1174 */             refreshDespawnCounter(-2400);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
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
/* 1206 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isAIEnabled() {
/* 1214 */     return false;
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
/*      */   public final boolean isConsideredInViewOfPlayerForDespawningPurposes(EntityPlayer player) {
/* 1228 */     if (player.isGhost() || player.isZevimrgvInTournament()) {
/* 1229 */       return false;
/*      */     }
/* 1231 */     Vec3 entity_pos = getFootPos();
/* 1232 */     double foot_pos_y = entity_pos.yCoord;
/*      */     
/* 1234 */     double dx = player.posX - this.posX;
/* 1235 */     double dy = player.posY - this.posY;
/* 1236 */     double dz = player.posZ - this.posZ;
/*      */     
/* 1238 */     double dist_sq = dx * dx + dy * dy + dz * dz;
/*      */ 
/*      */     
/* 1241 */     if (dist_sq > 4096.0D && !isInRangeToRenderDist(dist_sq)) {
/* 1242 */       return false;
/*      */     }
/* 1244 */     Vec3 player_eye_pos = player.getEyePos();
/*      */ 
/*      */     
/* 1247 */     entity_pos.yCoord = foot_pos_y + (this.height * 0.75F);
/* 1248 */     if (this.worldObj.checkForLineOfSight(player_eye_pos, entity_pos, false)) {
/* 1249 */       return true;
/*      */     }
/*      */     
/* 1252 */     entity_pos.yCoord = foot_pos_y + 0.1D;
/* 1253 */     if (this.worldObj.checkForLineOfSight(player_eye_pos, entity_pos, false)) {
/* 1254 */       return true;
/*      */     }
/*      */     
/* 1257 */     entity_pos.yCoord = foot_pos_y + this.height + 0.5D;
/* 1258 */     if (this.worldObj.checkForLineOfSight(player_eye_pos, entity_pos, false)) {
/* 1259 */       return true;
/*      */     }
/*      */     
/* 1262 */     if (this.worldObj.checkForLineOfSight(player_eye_pos.addY(1.0D), entity_pos, false)) {
/* 1263 */       return true;
/*      */     }
/*      */     
/* 1266 */     if (this.inWater);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1304 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isConsideredInViewOfAnyPlayerForDespawningPurposes() {
/* 1310 */     int num_players = this.worldObj.playerEntities.size();
/*      */     
/* 1312 */     for (int i = 0; i < num_players; i++) {
/*      */       
/* 1314 */       if (isConsideredInViewOfPlayerForDespawningPurposes(this.worldObj.playerEntities.get(i))) {
/* 1315 */         return true;
/*      */       }
/*      */     } 
/* 1318 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canDespawn() {
/* 1324 */     if (isConsideredInViewOfAnyPlayerForDespawningPurposes()) {
/*      */       
/* 1326 */       refreshDespawnCounter((this instanceof EntityWaterMob) ? -9600 : -400);
/* 1327 */       return false;
/*      */     } 
/*      */     
/* 1330 */     return true;
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
/*      */   protected void tryDespawnEntity() {
/* 1372 */     if (this.persistenceRequired) {
/*      */       
/* 1374 */       this.despawn_counter = 0;
/*      */       
/*      */       return;
/*      */     } 
/* 1378 */     if (this.despawn_counter < 200) {
/*      */       return;
/*      */     }
/* 1381 */     EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, -1.0D, false);
/*      */     
/* 1383 */     if (player == null) {
/*      */       return;
/*      */     }
/* 1386 */     double dx = player.posX - this.posX;
/* 1387 */     double dy = player.posY - this.posY;
/* 1388 */     double dz = player.posZ - this.posZ;
/*      */     
/* 1390 */     boolean hour_of_grace = (this.worldObj.getHourOfDay() == 5);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1395 */     if (!hour_of_grace) {
/* 1396 */       dy *= 2.0D;
/*      */     }
/* 1398 */     double distance_sq = dx * dx + dy * dy + dz * dz;
/*      */ 
/*      */     
/* 1401 */     if (distance_sq > (hour_of_grace ? 'Ā' : 'Ā') && this.contained_items.isEmpty() && canDespawn()) {
/* 1402 */       setDead();
/*      */     } else {
/* 1404 */       this.despawn_counter = (int)(Math.random() * 100.0D) - 50;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateAITasks() {
/* 1409 */     if (this.is_decoy) {
/*      */       return;
/*      */     }
/*      */     
/* 1413 */     this.worldObj.theProfiler.startSection("checkDespawn");
/* 1414 */     tryDespawnEntity();
/* 1415 */     this.worldObj.theProfiler.endSection();
/*      */ 
/*      */ 
/*      */     
/* 1419 */     this.worldObj.theProfiler.startSection("targetSelector");
/* 1420 */     this.targetTasks.onUpdateTasks();
/* 1421 */     this.worldObj.theProfiler.endSection();
/* 1422 */     this.worldObj.theProfiler.startSection("goalSelector");
/* 1423 */     this.tasks.onUpdateTasks();
/* 1424 */     this.worldObj.theProfiler.endSection();
/* 1425 */     this.worldObj.theProfiler.startSection("navigation");
/* 1426 */     this.navigator.onUpdateNavigation();
/* 1427 */     this.worldObj.theProfiler.endSection();
/* 1428 */     this.worldObj.theProfiler.startSection("mob tick");
/* 1429 */     updateAITick();
/* 1430 */     this.worldObj.theProfiler.endSection();
/* 1431 */     this.worldObj.theProfiler.startSection("controls");
/* 1432 */     this.worldObj.theProfiler.startSection("move");
/* 1433 */     this.moveHelper.onUpdateMoveHelper();
/* 1434 */     this.worldObj.theProfiler.endStartSection("look");
/* 1435 */     this.lookHelper.onUpdateLook();
/* 1436 */     this.worldObj.theProfiler.endStartSection("jump");
/* 1437 */     this.jumpHelper.doJump();
/* 1438 */     this.worldObj.theProfiler.endSection();
/* 1439 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateEntityActionState() {
/* 1444 */     super.updateEntityActionState();
/* 1445 */     this.moveStrafing = 0.0F;
/* 1446 */     this.moveForward = 0.0F;
/* 1447 */     tryDespawnEntity();
/* 1448 */     float var1 = 8.0F;
/*      */     
/* 1450 */     if (this.rand.nextFloat() < 0.02F) {
/*      */ 
/*      */       
/* 1453 */       EntityPlayer var2 = this.worldObj.getClosestPlayerToEntity(this, var1, false);
/*      */       
/* 1455 */       if (var2 != null) {
/*      */         
/* 1457 */         this.currentTarget = var2;
/* 1458 */         this.numTicksToChaseTarget = 10 + this.rand.nextInt(20);
/*      */       }
/*      */       else {
/*      */         
/* 1462 */         this.randomYawVelocity = (this.rand.nextFloat() - 0.5F) * 20.0F;
/*      */       } 
/*      */     } 
/*      */     
/* 1466 */     if (this.currentTarget != null) {
/*      */       
/* 1468 */       faceEntity(this.currentTarget, 10.0F, getVerticalFaceSpeed());
/*      */       
/* 1470 */       if (this.numTicksToChaseTarget-- <= 0 || this.currentTarget.isDead || this.currentTarget.getDistanceSqToEntity(this) > (var1 * var1))
/*      */       {
/* 1472 */         this.currentTarget = null;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1477 */       if (this.rand.nextFloat() < 0.05F)
/*      */       {
/* 1479 */         this.randomYawVelocity = (this.rand.nextFloat() - 0.5F) * 20.0F;
/*      */       }
/*      */       
/* 1482 */       this.rotationYaw += this.randomYawVelocity;
/* 1483 */       this.rotationPitch = this.defaultPitch;
/*      */     } 
/*      */     
/* 1486 */     boolean var4 = isInWater();
/* 1487 */     boolean var3 = handleLavaMovement();
/*      */ 
/*      */     
/* 1490 */     if ((var4 || var3) && !isPreventedFromSwimmingDueToParalyzation())
/*      */     {
/* 1492 */       this.isJumping = (this.rand.nextFloat() < 0.8F);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPreventedFromSwimmingDueToParalyzation() {
/* 1498 */     return (getSpeedBoostVsSlowDown() < -0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVerticalFaceSpeed() {
/* 1507 */     return 40;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void faceEntity(Entity par1Entity, float par2, float par3) {
/* 1515 */     double var6, var4 = par1Entity.posX - this.posX;
/* 1516 */     double var8 = par1Entity.posZ - this.posZ;
/*      */ 
/*      */     
/* 1519 */     if (par1Entity instanceof EntityLivingBase) {
/*      */       
/* 1521 */       EntityLivingBase var10 = (EntityLivingBase)par1Entity;
/* 1522 */       var6 = var10.posY + var10.getEyeHeight() - this.posY + getEyeHeight();
/*      */     }
/*      */     else {
/*      */       
/* 1526 */       var6 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY) / 2.0D - this.posY + getEyeHeight();
/*      */     } 
/*      */     
/* 1529 */     double var14 = MathHelper.sqrt_double(var4 * var4 + var8 * var8);
/* 1530 */     float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
/* 1531 */     float var13 = (float)-(Math.atan2(var6, var14) * 180.0D / Math.PI);
/* 1532 */     this.rotationPitch = updateRotation(this.rotationPitch, var13, par3);
/* 1533 */     this.rotationYaw = updateRotation(this.rotationYaw, var12, par2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float updateRotation(float par1, float par2, float par3) {
/* 1541 */     float var4 = MathHelper.wrapAngleTo180_float(par2 - par1);
/*      */     
/* 1543 */     if (var4 > par3)
/*      */     {
/* 1545 */       var4 = par3;
/*      */     }
/*      */     
/* 1548 */     if (var4 < -par3)
/*      */     {
/* 1550 */       var4 = -par3;
/*      */     }
/*      */     
/* 1553 */     return par1 + var4;
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
/*      */   public final boolean canSpawnOnLeaves() {
/* 1574 */     return (getClass() == EntityWoodSpider.class || getClass() == EntityBlackWidowSpider.class || this instanceof EntityOcelot);
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
/*      */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 1593 */     if (!this.worldObj.checkNoEntityCollision(this.boundingBox)) {
/* 1594 */       return false;
/*      */     }
/* 1596 */     if (!this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) {
/* 1597 */       return false;
/*      */     }
/* 1599 */     if (!canSpawnOnLeaves() && this.worldObj.getBlock(getBlockPosX(), getFootBlockPosY() - 1, getBlockPosZ()) instanceof BlockLeaves) {
/* 1600 */       return false;
/*      */     }
/* 1602 */     if (this instanceof IMob && ((IMob)this).canSpawnInShallowWater()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1611 */       int x = getBlockPosX();
/* 1612 */       int y = getFootBlockPosY();
/* 1613 */       int z = getBlockPosZ();
/*      */       
/* 1615 */       Block block_above = this.worldObj.getBlock(x, y + 1, z);
/*      */       
/* 1617 */       if (block_above != null) {
/*      */         
/* 1619 */         if (block_above.blockMaterial == Material.water) {
/* 1620 */           return false;
/*      */         }
/* 1622 */         if (this.worldObj.getBlockMaterial(x, y, z) == Material.water)
/*      */         {
/* 1624 */           if (block_above.isSolid(this.worldObj, x, y + 1, z))
/*      */           {
/*      */ 
/*      */             
/* 1628 */             return false;
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1635 */       return (block_above != Block.waterlily && !this.worldObj.isAnyLava(this.boundingBox));
/*      */     } 
/*      */ 
/*      */     
/* 1639 */     return !this.worldObj.isAnyLiquid(this.boundingBox);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getRenderSizeModifier() {
/* 1648 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSpawnedInChunk() {
/* 1656 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSafePointTries() {
/* 1665 */     if (getTarget() == null)
/*      */     {
/* 1667 */       return 3;
/*      */     }
/*      */ 
/*      */     
/* 1671 */     int var1 = (int)(getHealth() - getMaxHealth() * 0.33F);
/* 1672 */     var1 -= (3 - this.worldObj.difficultySetting) * 4;
/*      */     
/* 1674 */     if (var1 < 0)
/*      */     {
/* 1676 */       var1 = 0;
/*      */     }
/*      */     
/* 1679 */     return var1 + 3;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeldItemStack(ItemStack item_stack) {
/* 1685 */     this.equipment[0] = item_stack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getHeldItemStack() {
/* 1693 */     return this.equipment[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentItemOrArmor(int par1) {
/* 1702 */     return this.equipment[par1];
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack func_130225_q(int par1) {
/* 1707 */     return this.equipment[par1 + 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
/* 1715 */     this.equipment[par1] = par2ItemStack;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack[] getLastActiveItems() {
/* 1720 */     return this.equipment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropEquipment(boolean recently_hit_by_player, int par2) {
/* 1729 */     for (int var3 = 0; var3 < (getLastActiveItems()).length; var3++) {
/*      */       
/* 1731 */       ItemStack var4 = getCurrentItemOrArmor(var3);
/* 1732 */       boolean var5 = (this.equipmentDropChances[var3] > 1.0F);
/*      */       
/* 1734 */       if (var4 == null) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1740 */       boolean is_held_item = (var3 == 0);
/*      */       
/* 1742 */       if (this.has_taken_massive_fall_damage && this.equipmentDropChances[var3] < 1.0F && this.rand.nextFloat() < 0.9F)
/*      */       {
/* 1744 */         if (!is_held_item || !this.picked_up_a_held_item) {
/*      */           continue;
/*      */         }
/*      */       }
/* 1748 */       if (is_held_item && !this.picked_up_a_held_item && !var4.isItemEnchanted() && var4.isItemDamaged() && var4.getItem().hasQuality() && var4.getQuality().isLowerThan(EnumQuality.average)) {
/*      */         
/* 1750 */         float fraction_damaged = var4.getItemDamage() / var4.getMaxDamage();
/*      */         
/* 1752 */         if (fraction_damaged > 0.7F && this.rand.nextFloat() < 0.75F) {
/*      */           continue;
/*      */         }
/*      */       } 
/* 1756 */       if (this instanceof EntityLongdead)
/*      */       {
/* 1758 */         if (var4.getQuality().isAverageOrHigher())
/*      */         {
/* 1760 */           if (is_held_item)
/*      */           {
/* 1762 */             if (!this.picked_up_a_held_item) {
/* 1763 */               is_held_item = false;
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
/* 1775 */       if ((is_held_item && (recently_hit_by_player || this.picked_up_a_held_item || this.rand.nextFloat() < 0.05F)) || ((recently_hit_by_player || var5) && this.rand.nextFloat() - par2 * 0.01F < this.equipmentDropChances[var3])) {
/*      */         
/* 1777 */         if (var5 || var4.isItemStackDamageable());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1804 */         dropItemStack(var4, 0.0F);
/* 1805 */         setCurrentItemOrArmor(var3, (ItemStack)null);
/*      */       } 
/*      */       continue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addRandomArmor() {
/* 1817 */     if (this.rand.nextFloat() < 0.15F * this.worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ)) {
/*      */       
/* 1819 */       int var1 = this.rand.nextInt(2);
/* 1820 */       float var2 = (this.worldObj.difficultySetting == 3) ? 0.1F : 0.25F;
/*      */       
/* 1822 */       if (this.rand.nextFloat() < 0.095F)
/*      */       {
/* 1824 */         var1++;
/*      */       }
/*      */       
/* 1827 */       if (this.rand.nextFloat() < 0.095F)
/*      */       {
/* 1829 */         var1++;
/*      */       }
/*      */       
/* 1832 */       if (this.rand.nextFloat() < 0.095F)
/*      */       {
/* 1834 */         var1++;
/*      */       }
/*      */       
/* 1837 */       for (int var3 = 3; var3 >= 0; var3--) {
/*      */         
/* 1839 */         ItemStack var4 = func_130225_q(var3);
/*      */         
/* 1841 */         if (var3 < 3 && this.rand.nextFloat() < var2) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/* 1846 */         if (var4 == null) {
/*      */           
/* 1848 */           Item var5 = getArmorItemForSlot(var3 + 1, var1);
/*      */           
/* 1850 */           if (var5 != null)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1862 */             setCurrentItemOrArmor(var3 + 1, (new ItemStack(var5)).randomizeForMob(this, true));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getEquipmentPosition(ItemStack par0ItemStack) {
/* 1873 */     if (par0ItemStack.itemID != Block.pumpkin.blockID && par0ItemStack.itemID != Item.skull.itemID) {
/*      */       
/* 1875 */       if (par0ItemStack.getItem() instanceof ItemArmor)
/*      */       {
/* 1877 */         switch (((ItemArmor)par0ItemStack.getItem()).armorType) {
/*      */           
/*      */           case 0:
/* 1880 */             return 4;
/*      */           
/*      */           case 1:
/* 1883 */             return 3;
/*      */           
/*      */           case 2:
/* 1886 */             return 2;
/*      */           
/*      */           case 3:
/* 1889 */             return 1;
/*      */         } 
/*      */       
/*      */       }
/* 1893 */       return 0;
/*      */     } 
/*      */ 
/*      */     
/* 1897 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Item getArmorItemForSlot(int par0, int par1) {
/* 1906 */     switch (par0) {
/*      */       
/*      */       case 4:
/* 1909 */         if (par1 == 0)
/*      */         {
/* 1911 */           return Item.helmetLeather;
/*      */         }
/* 1913 */         if (par1 == 1)
/*      */         {
/*      */           
/* 1916 */           return Item.helmetChainRustedIron;
/*      */         }
/* 1918 */         if (par1 == 2)
/*      */         {
/*      */           
/* 1921 */           return Item.helmetChainCopper;
/*      */         }
/* 1923 */         if (par1 == 3)
/*      */         {
/*      */           
/* 1926 */           return Item.helmetRustedIron;
/*      */         }
/* 1928 */         if (par1 == 4)
/*      */         {
/*      */           
/* 1931 */           return Item.helmetCopper;
/*      */         }
/*      */       
/*      */       case 3:
/* 1935 */         if (par1 == 0)
/*      */         {
/* 1937 */           return Item.plateLeather;
/*      */         }
/* 1939 */         if (par1 == 1)
/*      */         {
/*      */           
/* 1942 */           return Item.plateChainRustedIron;
/*      */         }
/* 1944 */         if (par1 == 2)
/*      */         {
/*      */           
/* 1947 */           return Item.plateChainCopper;
/*      */         }
/* 1949 */         if (par1 == 3)
/*      */         {
/*      */           
/* 1952 */           return Item.plateRustedIron;
/*      */         }
/* 1954 */         if (par1 == 4)
/*      */         {
/*      */           
/* 1957 */           return Item.plateCopper;
/*      */         }
/*      */       
/*      */       case 2:
/* 1961 */         if (par1 == 0)
/*      */         {
/* 1963 */           return Item.legsLeather;
/*      */         }
/* 1965 */         if (par1 == 1)
/*      */         {
/*      */           
/* 1968 */           return Item.legsChainRustedIron;
/*      */         }
/* 1970 */         if (par1 == 2)
/*      */         {
/*      */           
/* 1973 */           return Item.legsChainCopper;
/*      */         }
/* 1975 */         if (par1 == 3)
/*      */         {
/*      */           
/* 1978 */           return Item.legsRustedIron;
/*      */         }
/* 1980 */         if (par1 == 4)
/*      */         {
/*      */           
/* 1983 */           return Item.legsCopper;
/*      */         }
/*      */       
/*      */       case 1:
/* 1987 */         if (par1 == 0)
/*      */         {
/* 1989 */           return Item.bootsLeather;
/*      */         }
/* 1991 */         if (par1 == 1)
/*      */         {
/*      */           
/* 1994 */           return Item.bootsChainRustedIron;
/*      */         }
/* 1996 */         if (par1 == 2)
/*      */         {
/*      */           
/* 1999 */           return Item.bootsChainCopper;
/*      */         }
/* 2001 */         if (par1 == 3)
/*      */         {
/*      */           
/* 2004 */           return Item.bootsRustedIron;
/*      */         }
/* 2006 */         if (par1 == 4)
/*      */         {
/*      */           
/* 2009 */           return Item.bootsCopper;
/*      */         }
/*      */         break;
/*      */     } 
/* 2013 */     return null;
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
/*      */   protected void enchantEquipment(ItemStack item_stack) {
/* 2045 */     float tension = this.worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ);
/*      */ 
/*      */     
/* 2048 */     if (this.rand.nextFloat() < 0.1F * tension) {
/* 2049 */       EnchantmentHelper.addRandomEnchantment(this.rand, item_stack, (int)(5.0F + tension * this.rand.nextInt(18)));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 2055 */     getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
/* 2056 */     return par1EntityLivingData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeSteered() {
/* 2065 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityName() {
/* 2073 */     return hasCustomNameTag() ? getCustomNameTag() : super.getEntityName();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110163_bv() {
/* 2078 */     this.persistenceRequired = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCustomNameTag(String par1Str) {
/* 2083 */     this.dataWatcher.updateObject(10, par1Str);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCustomNameTag() {
/* 2088 */     return this.dataWatcher.getWatchableObjectString(10);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasCustomNameTag() {
/* 2093 */     return (this.dataWatcher.getWatchableObjectString(10).length() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlwaysRenderNameTag(boolean par1) {
/* 2098 */     this.dataWatcher.updateObject(11, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTag() {
/* 2103 */     return (this.dataWatcher.getWatchableObjectByte(11) == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTagForRender() {
/* 2108 */     return getAlwaysRenderNameTag();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEquipmentDropChance(int par1, float par2) {
/* 2113 */     this.equipmentDropChances[par1] = par2;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canPickUpLoot() {
/* 2118 */     if (this.isDead || getHealth() <= 0.0F) {
/* 2119 */       return false;
/*      */     }
/* 2121 */     if (this.ticks_disarmed > 0) {
/* 2122 */       return false;
/*      */     }
/* 2124 */     return this.canPickUpLoot;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanPickUpLoot(boolean par1) {
/* 2129 */     this.canPickUpLoot = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNoDespawnRequired() {
/* 2134 */     return this.persistenceRequired;
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
/*      */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 2174 */     if (getLeashed() && (!getLeashedToEntity().isEntityPlayer() || getLeashedToEntity() == player)) {
/*      */       
/* 2176 */       if (onClient()) {
/* 2177 */         player.swingArm();
/*      */       } else {
/* 2179 */         clearLeashed(!player.inCreativeMode(), true);
/*      */       } 
/* 2181 */       return true;
/*      */     } 
/*      */     
/* 2184 */     return false;
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
/*      */   protected void func_110159_bB() {
/* 2197 */     if (this.field_110170_bx != null)
/*      */     {
/* 2199 */       recreateLeash();
/*      */     }
/*      */     
/* 2202 */     if (this.isLeashed)
/*      */     {
/* 2204 */       if (this.leashedToEntity == null || this.leashedToEntity.isDead)
/*      */       {
/* 2206 */         clearLeashed(true, true);
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
/*      */   public final void clearLeashed(boolean drop_leash_item, boolean send_packet_to_tracking_players) {
/* 2243 */     if (this.isLeashed) {
/*      */       
/* 2245 */       if (this.leashedToEntity instanceof EntityLeashKnot) {
/*      */         
/* 2247 */         List entities_tied_to_leash_knot = ItemLeash.getEntitiesThatAreLeashedToEntity(this.leashedToEntity);
/*      */         
/* 2249 */         if (entities_tied_to_leash_knot.size() == 1) {
/* 2250 */           this.leashedToEntity.setDead();
/*      */         }
/*      */       } 
/* 2253 */       this.isLeashed = false;
/* 2254 */       this.leashedToEntity = null;
/*      */       
/* 2256 */       if (onServer()) {
/*      */         
/* 2258 */         if (drop_leash_item) {
/* 2259 */           dropItem(Item.leash.itemID, 1);
/*      */         }
/* 2261 */         if (send_packet_to_tracking_players) {
/* 2262 */           sendPacketToAllPlayersTrackingEntity(new Packet39AttachEntity(1, this, null));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean allowLeashing() {
/* 2269 */     return (!getLeashed() && !(this instanceof IMob));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getLeashed() {
/* 2274 */     return this.isLeashed;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getLeashedToEntity() {
/* 2279 */     return this.leashedToEntity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLeashedToEntity(Entity par1Entity, boolean par2) {
/* 2288 */     this.isLeashed = true;
/* 2289 */     this.leashedToEntity = par1Entity;
/*      */     
/* 2291 */     if (!this.worldObj.isRemote && par2 && this.worldObj instanceof WorldServer)
/*      */     {
/* 2293 */       ((WorldServer)this.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, new Packet39AttachEntity(1, this, this.leashedToEntity));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void recreateLeash() {
/* 2299 */     if (this.isLeashed && this.field_110170_bx != null)
/*      */     {
/* 2301 */       if (this.field_110170_bx.hasKey("UUIDMost") && this.field_110170_bx.hasKey("UUIDLeast")) {
/*      */         
/* 2303 */         UUID var5 = new UUID(this.field_110170_bx.getLong("UUIDMost"), this.field_110170_bx.getLong("UUIDLeast"));
/* 2304 */         List var6 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(10.0D, 10.0D, 10.0D));
/* 2305 */         Iterator<EntityLivingBase> var7 = var6.iterator();
/*      */         
/* 2307 */         while (var7.hasNext()) {
/*      */           
/* 2309 */           EntityLivingBase var8 = var7.next();
/*      */           
/* 2311 */           if (var8.getUniqueID().equals(var5)) {
/*      */             
/* 2313 */             this.leashedToEntity = var8;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 2318 */       } else if (this.field_110170_bx.hasKey("X") && this.field_110170_bx.hasKey("Y") && this.field_110170_bx.hasKey("Z")) {
/*      */         
/* 2320 */         int var1 = this.field_110170_bx.getInteger("X");
/* 2321 */         int var2 = this.field_110170_bx.getInteger("Y");
/* 2322 */         int var3 = this.field_110170_bx.getInteger("Z");
/* 2323 */         EntityLeashKnot var4 = EntityLeashKnot.getKnotForBlock(this.worldObj, var1, var2, var3);
/*      */         
/* 2325 */         if (var4 == null)
/*      */         {
/* 2327 */           var4 = EntityLeashKnot.func_110129_a(this.worldObj, var1, var2, var3);
/*      */         }
/*      */ 
/*      */         
/* 2331 */         setLeashedToEntity(var4, true);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2336 */         clearLeashed(true, false);
/*      */       } 
/*      */     }
/*      */     
/* 2340 */     this.field_110170_bx = null;
/*      */   }
/*      */   
/*      */   protected float getMaxTargettingRange() {
/*      */     float max_range;
/* 2345 */     AttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.followRange);
/*      */ 
/*      */ 
/*      */     
/* 2349 */     if (var1 == null) {
/* 2350 */       max_range = 16.0F;
/*      */     } else {
/* 2352 */       max_range = (float)var1.getAttributeValue();
/*      */     } 
/* 2354 */     if (this.recentlyHit > 0) {
/* 2355 */       max_range *= 2.0F;
/*      */     }
/* 2357 */     return max_range;
/*      */   }
/*      */ 
/*      */   
/*      */   protected PathEntity findPathTowardXYZ(int x, int y, int z, int max_path_length, boolean use_navigator) {
/* 2362 */     return this.worldObj.findEntityPathTowardXYZ(this, x, y, z, max_path_length, use_navigator);
/*      */   }
/*      */ 
/*      */   
/*      */   protected PathEntity findPathAwayFromXYZ(int x, int y, int z, int min_distance, int max_path_length, boolean use_navigator) {
/* 2367 */     return this.worldObj.findEntityPathAwayFromXYZ(this, x, y, z, min_distance, max_path_length, use_navigator);
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
/*      */   public boolean isFoodItem(ItemStack item_stack) {
/* 2394 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRepairItem(ItemStack item_stack) {
/* 2400 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canEat() {
/* 2405 */     return (!isChild() && !this.isDead && getHealth() > 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean willEat(ItemStack item_stack) {
/* 2410 */     return (isFoodItem(item_stack) && canEat());
/*      */   }
/*      */ 
/*      */   
/*      */   public void onFoodEaten(ItemStack item_stack) {
/* 2415 */     if (!isEntityUndead()) {
/* 2416 */       healByPercentage(0.5F);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean willUseForRepair(ItemStack item_stack) {
/* 2421 */     return (getHealthFraction() < 1.0F && isRepairItem(item_stack));
/*      */   }
/*      */ 
/*      */   
/*      */   public void onRepairItemPickup(ItemStack item_stack) {
/* 2426 */     healByPercentage(0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean willPickupAsValuable(ItemStack item_stack) {
/* 2432 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void warnPeersOfAttacker(Class peer_class, Entity attacker) {
/* 2437 */     if (attacker == null) {
/*      */       return;
/*      */     }
/* 2440 */     List<EntityLiving> peers = this.worldObj.getEntitiesWithinAABB(peer_class, this.boundingBox.expand(8.0D, 4.0D, 8.0D));
/*      */     
/* 2442 */     for (int i = 0; i < peers.size(); i++) {
/*      */       
/* 2444 */       EntityLiving entity_living = peers.get(i);
/*      */       
/* 2446 */       if (!(entity_living instanceof EntityTameable) || !((EntityTameable)entity_living).isTamed())
/*      */       {
/*      */         
/* 2449 */         if (entity_living.getLastHarmingEntity() == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2456 */           entity_living.setLastHarmingEntity(attacker);
/*      */           
/* 2458 */           entity_living.considerFleeing();
/*      */           
/* 2460 */           if (entity_living.has_decided_to_flee) {
/*      */             
/* 2462 */             if (entity_living instanceof EntityAnimal && ((EntityAnimal)entity_living).isInLove()) {
/* 2463 */               ((EntityAnimal)entity_living).setInLove(0);
/*      */             }
/*      */           } else {
/*      */             
/* 2467 */             entity_living.setLastHarmingEntity((Entity)null);
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isNearLitTorch() {
/* 2475 */     return isNearLitTorch(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNearLitTorch(int x, int y, int z) {
/* 2480 */     for (int dx = -1; dx <= 1; dx++) {
/*      */       
/* 2482 */       for (int dy = -1; dy <= 1 + (int)this.height; dy++) {
/*      */         
/* 2484 */         for (int dz = -1; dz <= 1; dz++) {
/*      */           
/* 2486 */           int block_id = this.worldObj.getBlockId(x + dx, y + dy, z + dz);
/*      */           
/* 2488 */           if (block_id == Block.torchWood.blockID || block_id == Block.torchRedstoneActive.blockID || block_id == Block.pumpkinLantern.blockID)
/*      */           {
/* 2490 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2496 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNearBlock(int x, int y, int z, Block block) {
/* 2501 */     for (int dx = -1; dx <= 1; dx++) {
/*      */       
/* 2503 */       for (int dy = -1; dy <= 1 + (int)this.height; dy++) {
/*      */         
/* 2505 */         for (int dz = -1; dz <= 1; dz++) {
/*      */           
/* 2507 */           if (this.worldObj.getBlock(x + dx, y + dy, z + dz) == block) {
/* 2508 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 2513 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean tryDisableNearbyLightSource() {
/* 2519 */     if (!this.worldObj.isRemote && this.recentlyHit == 0 && distanceToNearestPlayer() > 4.0D) {
/*      */       
/* 2521 */       int x = MathHelper.floor_double(this.posX);
/* 2522 */       int y = MathHelper.floor_double(this.posY);
/* 2523 */       int z = MathHelper.floor_double(this.posZ);
/*      */       
/* 2525 */       for (int dx = -1; dx <= 1; dx++) {
/*      */         
/* 2527 */         for (int dy = -1; dy <= 1 + (int)this.height; dy++) {
/*      */           
/* 2529 */           for (int dz = -1; dz <= 1; dz++) {
/*      */             
/* 2531 */             int block_id = this.worldObj.getBlockId(x + dx, y + dy, z + dz);
/*      */             
/* 2533 */             if (block_id == Block.torchWood.blockID || block_id == Block.torchRedstoneActive.blockID) {
/*      */               
/* 2535 */               BlockBreakInfo info = (new BlockBreakInfo(this.worldObj, x + dx, y + dy, z + dz)).setHarvestedBy(this);
/*      */               
/* 2537 */               if (this.worldObj.setBlockToAir(x + dx, y + dy, z + dz))
/*      */               {
/* 2539 */                 Block.blocksList[block_id].dropBlockAsEntityItem(info);
/* 2540 */                 playSound("random.pop", 0.05F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */                 
/* 2542 */                 return true;
/*      */               }
/*      */             
/* 2545 */             } else if (block_id == Block.pumpkinLantern.blockID) {
/*      */               
/* 2547 */               if (this.worldObj.setBlock(x + dx, y + dy, z + dz, Block.pumpkin.blockID, this.worldObj.getBlockMetadata(x + dx, y + dy, z + dz), 3)) {
/*      */                 
/* 2549 */                 EntityItem entity_item = new EntityItem(this.worldObj, (x + dx), (y + dy), (z + dz), new ItemStack(Block.torchWood));
/* 2550 */                 entity_item.delayBeforeCanPickup = 10;
/*      */                 
/* 2552 */                 this.worldObj.spawnEntityInWorld(entity_item);
/*      */                 
/* 2554 */                 playSound("random.pop", 0.05F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */                 
/* 2556 */                 return true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2564 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSpooked() {
/* 2571 */     boolean is_spooked = (this.spooked_until > this.worldObj.getTotalWorldTime());
/*      */     
/* 2573 */     if (!is_spooked) {
/* 2574 */       this.spooked_until = 0L;
/*      */     }
/* 2576 */     return is_spooked;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack[] getWornItems() {
/* 2581 */     ItemStack[] equipment = getLastActiveItems();
/*      */     
/* 2583 */     ItemStack[] armors = new ItemStack[equipment.length - 1];
/*      */     
/* 2585 */     for (int i = 1; i < equipment.length; i++) {
/* 2586 */       armors[i - 1] = equipment[i];
/*      */     }
/* 2588 */     return armors;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setWornItem(int slot_index, ItemStack item_stack) {
/* 2593 */     slot_index++;
/*      */     
/* 2595 */     if (item_stack == this.equipment[slot_index]) {
/* 2596 */       return false;
/*      */     }
/* 2598 */     this.equipment[slot_index] = item_stack;
/*      */     
/* 2600 */     return true;
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
/*      */   public boolean isWearing(ItemStack item_stack) {
/* 2629 */     ItemStack[] worn_items = getWornItems();
/*      */     
/* 2631 */     for (int i = 0; i < worn_items.length; i++) {
/*      */       
/* 2633 */       if (worn_items[i] == item_stack) {
/* 2634 */         return true;
/*      */       }
/*      */     } 
/* 2637 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canPathTo(int x, int y, int z, int max_distance) {
/* 2642 */     PathEntity path = getNavigator().getPathToXYZ(x, y, z, max_distance);
/*      */     
/* 2644 */     if (path != null) {
/*      */       
/* 2646 */       PathPoint final_point = path.getFinalPathPoint();
/*      */       
/* 2648 */       return (final_point.xCoord == x && final_point.yCoord == y && final_point.zCoord == z);
/*      */     } 
/*      */     
/* 2651 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isValidLightLevel() {
/* 2659 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void moveEntityWithHeading(float par1, float par2) {
/* 2664 */     if (this.is_decoy) {
/*      */       return;
/*      */     }
/* 2667 */     super.moveEntityWithHeading(par1, par2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityInvulnerable() {
/* 2672 */     return this.is_decoy ? true : super.isEntityInvulnerable();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAsDecoy() {
/* 2677 */     this.is_decoy = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDecoy() {
/* 2682 */     return this.is_decoy;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpawnBlock(int x, int y, int z) {
/* 2687 */     this.came_from_spawner = true;
/*      */     
/* 2689 */     this.came_from_spawn_block = true;
/* 2690 */     this.spawn_block_x = x;
/* 2691 */     this.spawn_block_y = y;
/* 2692 */     this.spawn_block_z = z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource par1DamageSource) {
/* 2697 */     if (this.recentlyHit > 0)
/*      */     {
/* 2699 */       if (!this.worldObj.isRemote && this.came_from_spawn_block) {
/* 2700 */         BlockMobSpawner.incrementSpawnsKilled(this.worldObj, this.spawn_block_x, this.spawn_block_y, this.spawn_block_z);
/*      */       }
/*      */     }
/* 2703 */     super.onDeath(par1DamageSource);
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearMatchingEquipmentSlot(ItemStack item_stack) {
/* 2708 */     for (int i = 0; i < this.equipment.length; i++) {
/*      */       
/* 2710 */       if (this.equipment[i] == item_stack) {
/*      */         
/* 2712 */         this.equipment[i] = null;
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void causeBreakingItemEffect(Item item) {
/* 2720 */     if (item.hasBreakingEffect())
/*      */     {
/* 2722 */       entityFX(EnumEntityFX.item_breaking, (new SignalData()).setByte(-1).setShort(item.itemID));
/*      */     }
/*      */   }
/*      */   
/*      */   public final EntityPlayer getClosestVulnerablePlayer(double max_distance) {
/* 2727 */     return this.worldObj.getClosestVulnerablePlayer(this, max_distance, requiresLineOfSightToTargets());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean requiresLineOfSightToTargets() {
/* 2732 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityPlayer findPlayerToAttack(float max_distance) {
/* 2738 */     if (isAIEnabled()) {
/* 2739 */       Minecraft.setErrorMessage(getEntityName() + " using findPlayerToAttack?");
/*      */     }
/* 2741 */     EntityPlayer player = getClosestVulnerablePlayer(max_distance);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2749 */     return player;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Entity findNonPlayerToAttack(float max_distance) {
/* 2755 */     if (isAIEnabled()) {
/* 2756 */       Minecraft.setErrorMessage(getEntityName() + " using findNonPlayerToAttack?");
/*      */     }
/* 2758 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final EntityLivingBase getTarget() {
/* 2764 */     if (isAIEnabled())
/*      */     {
/* 2766 */       return getAttackTarget();
/*      */     }
/* 2768 */     if (this instanceof EntityCreature) {
/*      */       
/* 2770 */       EntityCreature creature = (EntityCreature)this;
/*      */       
/* 2772 */       Entity entity = creature.getEntityToAttack();
/*      */       
/* 2774 */       if (entity == null || entity instanceof EntityLivingBase) {
/* 2775 */         return (EntityLivingBase)entity;
/*      */       }
/* 2777 */       Minecraft.setErrorMessage("getTarget: target is not an EntityLivingBase (" + entity.getEntityName() + ")");
/* 2778 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 2782 */     if (this instanceof EntityCubic || this instanceof EntityGhast || this instanceof EntityDragon)
/*      */     {
/* 2784 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2788 */     Minecraft.setErrorMessage("getTarget: unsure how to handle " + getEntityName());
/* 2789 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTarget(EntityLivingBase target) {
/* 2796 */     if (target != null && target.isDead) {
/* 2797 */       target = null;
/*      */     }
/* 2799 */     if (isAIEnabled()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2813 */       if (getAttackTarget() == null) {
/* 2814 */         this.AI_retarget = target;
/*      */       }
/* 2816 */       setAttackTarget(target);
/*      */     }
/* 2818 */     else if (this instanceof EntityCreature) {
/*      */       
/* 2820 */       EntityCreature creature = (EntityCreature)this;
/*      */       
/* 2822 */       creature.setEntityToAttack(target);
/*      */     }
/* 2824 */     else if (this instanceof EntityCubic || this instanceof EntityGhast || this instanceof EntityDragon) {
/*      */       
/* 2826 */       Minecraft.setErrorMessage("setTarget: cannot set a target for " + getEntityName());
/*      */     }
/*      */     else {
/*      */       
/* 2830 */       Minecraft.setErrorMessage("setTarget: unsure how to handle " + getEntityName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean catchesFireInSunlight() {
/* 2837 */     return isEntityUndead();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getReach() {
/* 2843 */     if (!isAIEnabled()) {
/*      */       
/* 2845 */       Minecraft.setErrorMessage("getReach: doesn't handle old AI mobs yet");
/* 2846 */       return 0.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2854 */     return 1.5F + getHeldItemReachBonus() * 0.6F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List getMeleeAttackPoints() {
/* 2860 */     List<Vec3> melee_attack_points = new ArrayList();
/*      */     
/* 2862 */     melee_attack_points.add(getPrimaryPointOfAttack());
/*      */     
/* 2864 */     return melee_attack_points;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasLineOfStrike(Vec3 target_pos) {
/* 2872 */     Iterator<Vec3> i = getMeleeAttackPoints().iterator();
/*      */     
/* 2874 */     while (i.hasNext()) {
/*      */       
/* 2876 */       if (this.worldObj.checkForLineOfPhysicalReach(i.next(), target_pos)) {
/* 2877 */         return true;
/*      */       }
/*      */     } 
/* 2880 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasLineOfStrike(Entity target) {
/* 2886 */     List target_points = target.getTargetPoints();
/*      */     
/* 2888 */     if (target_points != null) {
/*      */       
/* 2890 */       Iterator<Vec3> i = target_points.iterator();
/*      */       
/* 2892 */       while (i.hasNext()) {
/*      */         
/* 2894 */         if (hasLineOfStrike(i.next())) {
/* 2895 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/* 2899 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTargetWithinStrikingDistance(EntityLivingBase target) {
/* 2905 */     if (!isAIEnabled()) {
/*      */       
/* 2907 */       Minecraft.setErrorMessage("isTargetWithinStrikingDistance: doesn't handle old AI mobs yet");
/* 2908 */       return false;
/*      */     } 
/*      */     
/* 2911 */     if (this instanceof EntityAnimal) {
/*      */       
/* 2913 */       double var2 = (this.width * 1.75F * this.width * 1.75F + target.width);
/*      */       
/* 2915 */       if (getHeldItemStack() != null) {
/* 2916 */         var2 += getHeldItemStack().getItem().getReachBonus();
/*      */       }
/* 2918 */       return (getDistanceSq(target.posX, target.boundingBox.minY, target.posZ) <= var2);
/*      */     } 
/*      */ 
/*      */     
/* 2922 */     return (getDistance(target.posX, target.boundingBox.minY, target.posZ) <= getReach());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasLineOfStrikeAndTargetIsWithinStrikingDistance(EntityLivingBase target) {
/* 2929 */     return (isTargetWithinStrikingDistance(target) && hasLineOfStrike(target));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSeeTarget(boolean ignore_leaves) {
/* 2934 */     return getEntitySenses().canSee(getTarget(), ignore_leaves);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAIBase getEntityAITask(Class _class) {
/* 2940 */     if (!isAIEnabled()) {
/* 2941 */       Minecraft.setErrorMessage("getEntityAITask: being called for " + getEntityName() + " which uses old AI");
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
/* 2955 */     return this.tasks.getTask(_class);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 2960 */     if (damage.getSource() == DamageSource.inWall && this.ticksExisted < 3) {
/*      */       
/* 2962 */       pushOutOfBlocks();
/* 2963 */       return null;
/*      */     } 
/*      */     
/* 2966 */     return super.attackEntityFrom(damage);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onMeleeAttacked(EntityLivingBase attacker, EntityDamageResult result) {
/* 2971 */     super.onMeleeAttacked(attacker, result);
/*      */     
/* 2973 */     if (!attacker.isHoldingItemThatPreventsHandDamage()) {
/*      */       
/* 2975 */       float chance_of_back_damage = 0.0F;
/* 2976 */       float amount_of_back_damage = 1.0F;
/*      */       
/* 2978 */       if (this instanceof EntityCubic) {
/*      */         
/* 2980 */         EntityCubic entity_cubic = (EntityCubic)this;
/*      */         
/* 2982 */         chance_of_back_damage = 1.0F;
/*      */         
/* 2984 */         amount_of_back_damage = entity_cubic.getAttackStrengthMultiplierForType();
/*      */       } 
/* 2986 */       if (this instanceof EntityFireElemental || this instanceof EntityBlaze) {
/*      */         
/* 2988 */         chance_of_back_damage = 1.0F;
/*      */       }
/* 2990 */       else if (getTarget() == attacker && !attacker.canOnlyPerformWeakStrike()) {
/*      */         
/* 2992 */         chance_of_back_damage = 0.125F;
/*      */       } 
/*      */       
/* 2995 */       if (this.rand.nextFloat() < chance_of_back_damage)
/*      */       {
/*      */         
/* 2998 */         attacker.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this).setHandDamage(), amount_of_back_damage));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isTargettingAPlayer() {
/* 3004 */     return getTarget() instanceof EntityPlayer;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumCreatureType getCreatureType() {
/* 3010 */     return EnumCreatureType.getCreatureType(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFrenzied() {
/* 3015 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isComfortableInLava() {
/* 3021 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onSendToClient(Packet24MobSpawn packet) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCurrentSpeed() {
/* 3035 */     return Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLiving.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */