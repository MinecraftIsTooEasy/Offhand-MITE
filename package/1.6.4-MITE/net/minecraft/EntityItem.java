/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EntityItem
/*      */   extends Entity
/*      */ {
/*      */   public int age;
/*      */   public int delayBeforeCanPickup;
/*      */   private int health;
/*      */   public float hoverStart;
/*      */   public boolean dropped_by_player;
/*      */   private float cooking_progress;
/*      */   
/*      */   public EntityItem(World par1World, double par2, double par4, double par6) {
/*   31 */     super(par1World);
/*   32 */     this.health = 5;
/*   33 */     this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
/*   34 */     setSize(0.25F, 0.25F);
/*   35 */     this.yOffset = this.height / 2.0F;
/*   36 */     setPosition(par2, par4, par6);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   42 */     if (!this.worldObj.isRemote) {
/*      */       
/*   44 */       this.rotationYaw = (float)(Math.random() * 360.0D);
/*   45 */       this.motionX = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*   46 */       this.motionY = 0.20000000298023224D;
/*   47 */       this.motionZ = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
/*   53 */     this(par1World, par2, par4, par6);
/*   54 */     setEntityItemStack(par8ItemStack);
/*      */     
/*   56 */     if (par8ItemStack.itemID == Item.manure.itemID) {
/*   57 */       this.motionX = this.motionY = this.motionZ = 0.0D;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/*   66 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityItem(World par1World) {
/*   71 */     super(par1World);
/*   72 */     this.health = 5;
/*   73 */     this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
/*   74 */     setSize(0.25F, 0.25F);
/*   75 */     this.yOffset = this.height / 2.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*   80 */     getDataWatcher().addObjectByDataType(10, 5);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean handleLavaMovement() {
/*   85 */     return (this.worldObj.getBlockMaterial(getBlockPosX(), getBlockPosY(), getBlockPosZ()) == Material.lava);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*   93 */     super.onUpdate();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  102 */     if (this.delayBeforeCanPickup > 0)
/*      */     {
/*  104 */       this.delayBeforeCanPickup--;
/*      */     }
/*      */     
/*  107 */     this.prevPosX = this.posX;
/*  108 */     this.prevPosY = this.posY;
/*  109 */     this.prevPosZ = this.posZ;
/*  110 */     this.motionY -= 0.03999999910593033D;
/*      */     
/*  112 */     if (getEntityItem().getItem() == Item.feather) {
/*      */       
/*  114 */       if (this.motionY < -0.10000000149011612D) {
/*  115 */         this.motionY = -0.10000000149011612D;
/*      */       }
/*  117 */       this.motionX *= 0.949999988079071D;
/*  118 */       this.motionZ *= 0.949999988079071D;
/*      */     } 
/*      */     
/*  121 */     if ((isInsideOfMaterial(Material.water, this.height) || isInsideOfMaterial(Material.lava, this.height)) && this.motionY < 0.0D) {
/*      */       
/*  123 */       this.motionY *= (getEntityItem().getItem() == Item.feather) ? 0.4000000059604645D : 0.699999988079071D;
/*      */       
/*  125 */       this.motionX *= 0.8999999761581421D;
/*  126 */       this.motionZ *= 0.8999999761581421D;
/*      */     } 
/*      */ 
/*      */     
/*  130 */     pushOutOfBlocks();
/*  131 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  132 */     boolean var1 = ((int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  150 */     float var2 = 0.98F;
/*      */     
/*  152 */     if (this.onGround) {
/*      */       
/*  154 */       var2 = 0.58800006F;
/*      */ 
/*      */       
/*  157 */       BlockInfo block_info = getBlockRestingOn(0.1F);
/*  158 */       int var3 = (block_info == null) ? 0 : block_info.block.blockID;
/*      */       
/*  160 */       if (block_info != null && this.worldObj.getBlock(block_info.x, block_info.y + 1, block_info.z) == Block.snow && BlockSnow.getDepth(this.worldObj.getBlockMetadata(block_info.x, block_info.y + 1, block_info.z)) == 1) {
/*  161 */         var3 = Block.snow.blockID;
/*      */       }
/*  163 */       if (var3 > 0)
/*      */       {
/*  165 */         var2 = (Block.blocksList[var3]).slipperiness * 0.98F;
/*      */       }
/*      */     } 
/*      */     
/*  169 */     this.motionX *= var2;
/*  170 */     this.motionY *= 0.9800000190734863D;
/*  171 */     this.motionZ *= var2;
/*      */     
/*  173 */     if (this.onGround)
/*      */     {
/*  175 */       this.motionY *= -0.5D;
/*      */     }
/*      */     
/*  178 */     this.age++;
/*      */ 
/*      */     
/*  181 */     if (!this.worldObj.isRemote && this.age >= 6000 && (!this.dropped_by_player || DedicatedServer.tournament_type != EnumTournamentType.score) && !isArtifact()) {
/*      */       
/*  183 */       setDead();
/*  184 */       tryRemoveFromWorldUniques();
/*      */     } 
/*      */     
/*  187 */     if (!this.isDead && onServer()) {
/*      */       
/*  189 */       float chance_of_snow_items_melting = Item.getChanceOfSnowAndIceItemsMelting((getBiome()).temperature);
/*      */       
/*  191 */       if (chance_of_snow_items_melting > 0.0F) {
/*      */         
/*  193 */         ItemStack item_stack = getEntityItem();
/*      */         
/*  195 */         if (item_stack.hasMaterial(Material.snow, true) || item_stack.hasMaterial(Material.craftedSnow, true) || item_stack.hasMaterial(Material.ice, true))
/*      */         {
/*  197 */           if ((item_stack.subjectToChanceOfDisappearing(chance_of_snow_items_melting, this.rand)).stackSize < 1) {
/*      */             
/*  199 */             entityFX(EnumEntityFX.item_vanish);
/*  200 */             setDead();
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void tryRemoveFromWorldUniques() {
/*  210 */     if (onClient()) {
/*      */       
/*  212 */       Minecraft.setErrorMessage("tryRemoveFromWorldUniques: called on client");
/*      */       
/*      */       return;
/*      */     } 
/*  216 */     if (!this.isDead) {
/*      */       
/*  218 */       Minecraft.setErrorMessage("tryRemoveFromWorldUniques: not marked dead " + this);
/*      */       
/*      */       return;
/*      */     } 
/*  222 */     ItemStack item_stack = getEntityItem();
/*      */     
/*  224 */     if (item_stack.hasSignature()) {
/*  225 */       this.worldObj.worldInfo.removeSignature(item_stack.getSignature());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void searchForOtherItemsNearby() {
/*  233 */     Iterator<EntityItem> var1 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(0.5D, 0.0D, 0.5D)).iterator();
/*      */     
/*  235 */     while (var1.hasNext()) {
/*      */       
/*  237 */       EntityItem var2 = var1.next();
/*  238 */       combineItems(var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean combineItems(EntityItem par1EntityItem) {
/*  248 */     if (par1EntityItem == this)
/*      */     {
/*  250 */       return false;
/*      */     }
/*  252 */     if (par1EntityItem.isEntityAlive() && isEntityAlive()) {
/*      */       
/*  254 */       ItemStack var2 = getEntityItem();
/*  255 */       ItemStack var3 = par1EntityItem.getEntityItem();
/*      */       
/*  257 */       if (var3.getItem() != var2.getItem())
/*      */       {
/*  259 */         return false;
/*      */       }
/*  261 */       if ((var3.hasTagCompound() ^ var2.hasTagCompound()) != 0)
/*      */       {
/*  263 */         return false;
/*      */       }
/*  265 */       if (var3.hasTagCompound() && !var3.getTagCompound().equals(var2.getTagCompound()))
/*      */       {
/*  267 */         return false;
/*      */       }
/*  269 */       if (var3.getItem().getHasSubtypes() && var3.getItemSubtype() != var2.getItemSubtype())
/*      */       {
/*  271 */         return false;
/*      */       }
/*  273 */       if (var3.stackSize < var2.stackSize)
/*      */       {
/*  275 */         return par1EntityItem.combineItems(this);
/*      */       }
/*  277 */       if (var3.stackSize + var2.stackSize > var3.getMaxStackSize())
/*      */       {
/*  279 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  283 */       var3.stackSize += var2.stackSize;
/*  284 */       par1EntityItem.delayBeforeCanPickup = Math.max(par1EntityItem.delayBeforeCanPickup, this.delayBeforeCanPickup);
/*  285 */       par1EntityItem.age = Math.min(par1EntityItem.age, this.age);
/*  286 */       par1EntityItem.setEntityItemStack(var3);
/*  287 */       setDead();
/*  288 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  293 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePickedUpBy(EntityLivingBase entity_living_base) {
/*  300 */     if (entity_living_base instanceof EntityPlayer) {
/*      */       
/*  302 */       EntityPlayer player = (EntityPlayer)entity_living_base;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  308 */       if ((player.getAsEntityPlayerMP()).portal_grace_ticks > 0) {
/*  309 */         return false;
/*      */       }
/*  311 */       if (!player.hasHeldItem() && System.currentTimeMillis() < (player.getAsEntityPlayerMP()).prevent_item_pickup_due_to_held_item_breaking_until) {
/*  312 */         return false;
/*      */       }
/*      */     } 
/*  315 */     if (this.delayBeforeCanPickup > 0) {
/*  316 */       return false;
/*      */     }
/*  318 */     if (isBurning() && entity_living_base.isHarmedByFire()) {
/*  319 */       return false;
/*      */     }
/*  321 */     if (getEntityItem().isBlock() && !getEntityItem().getItemAsBlock().getBlock().canBeCarried()) {
/*      */       
/*  323 */       Minecraft.setErrorMessage("canBePickedUpBy: block is not carriable " + getEntityItem());
/*      */       
/*  325 */       if (!entity_living_base.isPlayerInCreative()) {
/*  326 */         return false;
/*      */       }
/*      */     } 
/*  329 */     return canRaycastToEntity(entity_living_base);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAgeToCreativeDespawnTime() {
/*  339 */     this.age = 0;
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
/*      */   public boolean handleWaterMovement() {
/*  352 */     if (this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this)) {
/*      */       
/*  354 */       if (!this.inWater && !this.firstUpdate && this.motionY < -0.10000000149011612D && !this.worldObj.isRemote) {
/*  355 */         entityFX(EnumEntityFX.splash);
/*      */       }
/*  357 */       this.inWater = true;
/*      */       
/*  359 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  363 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getStackSize() {
/*  369 */     return (getEntityItem()).stackSize;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void convertItem(Item item) {
/*  375 */     if (this.worldObj.isRemote) {
/*      */       
/*  377 */       Minecraft.setErrorMessage("convertItem: not meant to be called on client");
/*      */       
/*      */       return;
/*      */     } 
/*  381 */     setEntityItemStack(new ItemStack(item, getStackSize()));
/*      */     
/*  383 */     int max_stack_size = item.getItemStackLimit(0, 0);
/*      */     
/*  385 */     while (getStackSize() > max_stack_size) {
/*      */       
/*  387 */       int stack_size = Math.min(getStackSize() - max_stack_size, max_stack_size);
/*      */       
/*  389 */       EntityItem entity_item = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(item, stack_size));
/*      */       
/*  391 */       entity_item.motionX = this.motionX;
/*  392 */       entity_item.motionY = this.motionY;
/*  393 */       entity_item.motionZ = this.motionZ;
/*      */       
/*  395 */       entity_item.age = this.age;
/*  396 */       entity_item.delayBeforeCanPickup = this.delayBeforeCanPickup;
/*      */       
/*  398 */       this.worldObj.spawnEntityInWorld(entity_item);
/*      */       
/*  400 */       (getEntityItem()).stackSize -= stack_size;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void spentTickInWater() {
/*  406 */     Item item = getEntityItem().getItem();
/*      */     
/*  408 */     if (item instanceof ItemVessel) {
/*      */       
/*  410 */       ItemVessel vessel = (ItemVessel)item;
/*      */       
/*  412 */       if (vessel.contains(Material.lava)) {
/*      */         
/*  414 */         if (!this.worldObj.isRemote) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  419 */           entityFX(EnumEntityFX.steam_with_hiss);
/*  420 */           convertItem(vessel.getPeerForContents(Material.stone));
/*      */         } 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  427 */       if (!this.worldObj.isRemote && !vessel.contains(Material.stone)) {
/*  428 */         convertItem(vessel.getPeerForContents(Material.water));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  459 */     else if (onServer() && item.hasMaterial(Material.water, true)) {
/*      */       
/*  461 */       if (!this.isDead)
/*      */       {
/*      */         
/*  464 */         setDead();
/*      */       }
/*      */     }
/*  467 */     else if (onServer() && item.isDissolvedByWater()) {
/*      */       
/*  469 */       if (!this.isDead && this.ticksExisted % 20 == 0) {
/*      */         
/*  471 */         attackEntityFrom(new Damage(DamageSource.melt, 1.0F));
/*      */         
/*  473 */         if (this.isDead) {
/*  474 */           entityFX(EnumEntityFX.item_vanish);
/*      */         }
/*      */       } 
/*      */     } 
/*  478 */     super.spentTickInWater();
/*      */   }
/*      */ 
/*      */   
/*      */   public void spentTickInLava() {
/*  483 */     if (!this.isDead) {
/*      */       
/*  485 */       Item item = getEntityItem().getItem();
/*      */       
/*  487 */       if (item instanceof ItemBucket || item instanceof ItemBucketMilk) {
/*      */         
/*  489 */         ItemVessel vessel = (ItemVessel)item;
/*      */         
/*  491 */         if (vessel.canContentsDouseFire()) {
/*      */           
/*  493 */           if (!this.worldObj.isRemote)
/*      */           {
/*  495 */             if (this.worldObj.tryConvertLavaToCobblestoneOrObsidian(getBlockPosX(), getBlockPosY(), getBlockPosZ())) {
/*  496 */               convertItem(vessel.getEmptyVessel());
/*      */             }
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  503 */         if (!this.worldObj.isRemote) {
/*  504 */           convertItem(vessel.getPeerForContents(Material.lava));
/*      */         }
/*      */       }
/*  507 */       else if (item instanceof ItemBlock) {
/*      */         
/*  509 */         ItemBlock item_block = (ItemBlock)item;
/*      */         
/*  511 */         Block block = item_block.getBlock();
/*      */         
/*  513 */         if (block == Block.blockSnow || block == Block.ice)
/*      */         {
/*  515 */           if (!this.worldObj.isRemote)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  522 */             this.worldObj.tryConvertLavaToCobblestoneOrObsidian(getBlockPosX(), getBlockPosY(), getBlockPosZ());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  560 */     super.spentTickInLava();
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
/*      */   private boolean destroyItem(DamageSource damage_source) {
/*  575 */     if (this.worldObj.isRemote) {
/*  576 */       Minecraft.setErrorMessage("destroyItem: called on client?");
/*      */     }
/*      */     
/*  579 */     ItemStack item_stack = getEntityItem().getItem().getItemProducedWhenDestroyed(getEntityItem(), damage_source);
/*      */ 
/*      */     
/*  582 */     if (item_stack == null) {
/*      */       
/*  584 */       setDead();
/*      */     }
/*      */     else {
/*      */       
/*  588 */       if (!this.worldObj.isRemote)
/*      */       {
/*  590 */         if (damage_source.isFireDamage()) {
/*      */           
/*  592 */           if (!this.worldObj.isRemote) {
/*  593 */             this.worldObj.douseFire(getBlockPosX(), getBlockPosY(), getBlockPosZ(), this);
/*      */           }
/*  595 */         } else if (damage_source.isLavaDamage()) {
/*      */           
/*  597 */           if (canDouseFire()) {
/*  598 */             causeQuenchEffect();
/*      */           }
/*  600 */           entityFX(EnumEntityFX.burned_up_in_lava);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  605 */       setEntityItemStack(item_stack);
/*      */     } 
/*      */     
/*  608 */     if (this.isDead) {
/*  609 */       tryRemoveFromWorldUniques();
/*      */     }
/*  611 */     return this.isDead;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  821 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*      */ 
/*      */ 
/*      */     
/*  825 */     if (result == null || result.entityWasDestroyed()) {
/*  826 */       return result;
/*      */     }
/*  828 */     ItemStack item_stack = getEntityItem();
/*      */     
/*  830 */     if (item_stack == null) {
/*      */       
/*  832 */       Minecraft.setErrorMessage("attackEntityFrom: EntityItem had null item_stack");
/*      */ 
/*      */       
/*  835 */       return null;
/*      */     } 
/*      */     
/*  838 */     Item item = item_stack.getItem();
/*      */     
/*  840 */     if (item == null) {
/*      */       
/*  842 */       Minecraft.setErrorMessage("attackEntityFrom: EntityItem had null item");
/*      */ 
/*      */       
/*  845 */       return null;
/*      */     } 
/*      */     
/*  848 */     if (item == Item.netherStar && damage.isExplosion())
/*      */     {
/*      */       
/*  851 */       return null;
/*      */     }
/*  853 */     if (damage.isLavaDamage() && isHarmedByLava()) {
/*  854 */       return destroyItem(damage.getSource()) ? result.setEntityWasDestroyed() : result.setEntityWasAffected();
/*      */     }
/*  856 */     if (damage.isFireDamage() && getEntityItem().canDouseFire()) {
/*  857 */       return destroyItem(damage.getSource()) ? result.setEntityWasDestroyed() : result.setEntityWasAffected();
/*      */     }
/*  859 */     if (damage.getSource() == DamageSource.pepsin && !isHarmedByPepsin()) {
/*  860 */       return null;
/*      */     }
/*  862 */     if (damage.getSource() == DamageSource.acid && !isHarmedByAcid()) {
/*  863 */       return null;
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
/*  882 */     setBeenAttacked();
/*      */     
/*  884 */     if (item_stack.isItemStackDamageable()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  893 */       float scaled_damage = damage.getAmount() * 20.0F * 5.0F;
/*      */       
/*  895 */       if (item instanceof ItemArmor) {
/*  896 */         scaled_damage *= Item.plateIron.getMaxDamage(EnumQuality.average) / Item.swordIron.getMaxDamage(EnumQuality.average);
/*  897 */       } else if (!(item instanceof ItemTool)) {
/*  898 */         scaled_damage = damage.getAmount();
/*      */       } 
/*      */ 
/*      */       
/*  902 */       if (scaled_damage < 1.0F) {
/*  903 */         scaled_damage = 1.0F;
/*      */       }
/*      */ 
/*      */       
/*  907 */       result.startTrackingHealth(item_stack.getRemainingDurability());
/*  908 */       ItemDamageResult idr = item_stack.tryDamageItem(this.worldObj, Math.round(scaled_damage), false);
/*  909 */       result.finishTrackingHealth(item_stack.getRemainingDurability());
/*      */ 
/*      */ 
/*      */       
/*  913 */       if (idr != null && idr.itemWasDestroyed())
/*      */       {
/*  915 */         this.health = 0;
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  920 */         this.health = 5 * item_stack.getItemDamage() / item_stack.getMaxDamage();
/*      */         
/*  922 */         if (this.health < 1) {
/*  923 */           this.health = 1;
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  930 */       if (damage.isFireDamage())
/*      */       {
/*  932 */         if (item instanceof ItemFood) {
/*      */           
/*  934 */           ItemFood item_food = (ItemFood)item;
/*      */           
/*  936 */           if (item_food.getCookedItem() != null || item_food.getUncookedItem() != null) {
/*      */ 
/*      */ 
/*      */             
/*  940 */             if (item_food.getCookedItem() != null) {
/*      */               
/*  942 */               int x = getBlockPosX();
/*  943 */               int y = getBlockPosY();
/*  944 */               int z = getBlockPosZ();
/*      */               
/*  946 */               for (int dx = -1; dx <= 1; dx++) {
/*      */                 
/*  948 */                 for (int dz = -1; dz <= 1; dz++) {
/*      */                   
/*  950 */                   Block block = this.worldObj.getBlock(x + dx, y, z + dz);
/*      */                   
/*  952 */                   if (block == Block.fire) {
/*  953 */                     this.worldObj.getAsWorldServer().addScheduledBlockOperation(EnumBlockOperation.try_extinguish_by_items, x + dx, y, z + dz, (this.worldObj.getTotalWorldTime() / 10L + 1L) * 10L, false);
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*  958 */             this.cooking_progress += damage.getAmount() * 3.0F;
/*      */             
/*  960 */             if (this.cooking_progress >= 100.0F) {
/*      */               
/*  962 */               ItemStack cooked_item_stack = item.getItemProducedWhenDestroyed(item_stack, damage.getSource());
/*      */               
/*  964 */               if (cooked_item_stack == null) {
/*      */                 
/*  966 */                 setDead();
/*  967 */                 return result.setEntityWasDestroyed();
/*      */               } 
/*      */               
/*  970 */               if (item instanceof ItemMeat) {
/*  971 */                 playSound("imported.random.sizzle", 1.0F, 1.0F);
/*      */               }
/*  973 */               setEntityItemStack(cooked_item_stack);
/*      */ 
/*      */               
/*  976 */               int xp_reward = cooked_item_stack.getExperienceReward();
/*      */               
/*  978 */               while (xp_reward > 0) {
/*      */                 
/*  980 */                 int xp_share = EntityXPOrb.getXPSplit(xp_reward);
/*  981 */                 xp_reward -= xp_share;
/*  982 */                 this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY + 0.5D, this.posZ + 0.5D, xp_share));
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  987 */             return result.setEntityWasAffected();
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  994 */       result.startTrackingHealth(this.health);
/*  995 */       this.health = (int)(this.health - damage.getAmount());
/*  996 */       result.finishTrackingHealth(this.health);
/*      */     } 
/*      */ 
/*      */     
/* 1000 */     if (result.entityWasNegativelyAffected())
/*      */     {
/* 1002 */       if (damage.isPepsinDamage() || damage.isAcidDamage())
/*      */       {
/* 1004 */         if (this.health <= 0) {
/*      */           
/* 1006 */           entityFX(damage.isAcidDamage() ? EnumEntityFX.smoke_and_steam_with_hiss : EnumEntityFX.steam_with_hiss);
/*      */         } else {
/* 1008 */           entityFX(EnumEntityFX.item_vanish);
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/* 1013 */     if (this.health <= 0) {
/*      */       
/* 1015 */       if (damage.isFireDamage()) {
/* 1016 */         entityFX(EnumEntityFX.smoke);
/*      */       }
/* 1018 */       if (!getEntityItem().hasSignature() && getEntityItem().getItem().hasContainerItem()) {
/*      */         
/* 1020 */         Item container = getEntityItem().getItem().getContainerItem();
/*      */         
/* 1022 */         if (!container.isHarmedBy(damage.getSource())) {
/*      */           
/* 1024 */           convertItem(container);
/* 1025 */           return result;
/*      */         } 
/*      */       } 
/*      */       
/* 1029 */       setDead();
/*      */ 
/*      */       
/* 1032 */       if (item_stack.hasSignatureThatHasBeenAddedToWorld(this.worldObj)) {
/* 1033 */         tryRemoveFromWorldUniques();
/*      */       }
/* 1035 */       result.setEntityWasDestroyed();
/*      */     } 
/*      */     
/* 1038 */     return result;
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
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 1060 */     par1NBTTagCompound.setShort("Health", (short)(byte)this.health);
/* 1061 */     par1NBTTagCompound.setShort("Age", (short)this.age);
/*      */     
/* 1063 */     if (getEntityItem() != null)
/*      */     {
/* 1065 */       par1NBTTagCompound.setCompoundTag("Item", getEntityItem().writeToNBT(new NBTTagCompound()));
/*      */     }
/*      */     
/* 1068 */     par1NBTTagCompound.setBoolean("dropped_by_player", this.dropped_by_player);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 1078 */     this.health = par1NBTTagCompound.getShort("Health") & 0xFF;
/* 1079 */     this.age = par1NBTTagCompound.getShort("Age");
/* 1080 */     NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Item");
/* 1081 */     setEntityItemStack(ItemStack.loadItemStackFromNBT(var2));
/*      */     
/* 1083 */     if (getEntityItem() == null)
/*      */     {
/* 1085 */       setDead();
/*      */     }
/*      */     
/* 1088 */     this.dropped_by_player = par1NBTTagCompound.getBoolean("dropped_by_player");
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
/*      */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
/* 1102 */     if (par1EntityPlayer.isGhost() || par1EntityPlayer.isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 1105 */     if (par1EntityPlayer.ridingEntity instanceof EntityHorse && this.posY - par1EntityPlayer.getFootPosY() < -0.5D) {
/*      */       return;
/*      */     }
/* 1108 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1110 */       boolean was_empty_handed_before = !par1EntityPlayer.hasHeldItem();
/*      */       
/* 1112 */       ItemStack var2 = getEntityItem();
/* 1113 */       int var3 = var2.stackSize;
/*      */ 
/*      */       
/* 1116 */       if (canBePickedUpBy(par1EntityPlayer) && par1EntityPlayer.inventory.addItemStackToInventory(var2)) {
/*      */         
/* 1118 */         if (var2.itemID == Block.wood.blockID)
/*      */         {
/* 1120 */           par1EntityPlayer.triggerAchievement(AchievementList.mineWood);
/*      */         }
/*      */         
/* 1123 */         if (var2.itemID == Item.leather.itemID)
/*      */         {
/* 1125 */           par1EntityPlayer.triggerAchievement(AchievementList.killCow);
/*      */         }
/*      */         
/* 1128 */         if (var2.itemID == Item.diamond.itemID)
/*      */         {
/* 1130 */           par1EntityPlayer.triggerAchievement(AchievementList.diamonds);
/*      */         }
/*      */         
/* 1133 */         if (var2.itemID == Item.emerald.itemID) {
/* 1134 */           par1EntityPlayer.triggerAchievement(AchievementList.emeralds);
/*      */         }
/* 1136 */         if (var2.itemID == Item.blazeRod.itemID)
/*      */         {
/* 1138 */           par1EntityPlayer.triggerAchievement(AchievementList.blazeRod);
/*      */         }
/*      */         
/* 1141 */         if (var2.itemID == Item.seeds.itemID || var2.itemID == Item.blueberries.itemID || var2.itemID == Item.wormRaw.itemID)
/*      */         {
/* 1143 */           par1EntityPlayer.triggerAchievement(AchievementList.seeds);
/*      */         }
/*      */         
/* 1146 */         if (var2.itemID == Item.stick.itemID)
/*      */         {
/* 1148 */           par1EntityPlayer.triggerAchievement(AchievementList.stickPicker);
/*      */         }
/*      */         
/* 1151 */         if (var2.itemID == Item.copperNugget.itemID || var2.itemID == Item.silverNugget.itemID || var2.itemID == Item.goldNugget.itemID || var2.itemID == Item.ironNugget.itemID || var2.itemID == Item.mithrilNugget.itemID || var2.itemID == Item.adamantiumNugget.itemID)
/*      */         {
/* 1153 */           par1EntityPlayer.triggerAchievement(AchievementList.nuggets);
/*      */         }
/*      */         
/* 1156 */         if (var2.itemID == Item.wheat.itemID) {
/* 1157 */           this.worldObj.worldInfo.fullfillVillageCondition(1, (WorldServer)this.worldObj);
/*      */         }
/* 1159 */         if (var2.itemID == Item.carrot.itemID) {
/* 1160 */           this.worldObj.worldInfo.fullfillVillageCondition(2, (WorldServer)this.worldObj);
/*      */         }
/* 1162 */         if (var2.itemID == Item.potato.itemID) {
/* 1163 */           this.worldObj.worldInfo.fullfillVillageCondition(4, (WorldServer)this.worldObj);
/*      */         }
/* 1165 */         if (var2.itemID == Item.onion.itemID) {
/* 1166 */           this.worldObj.worldInfo.fullfillVillageCondition(8, (WorldServer)this.worldObj);
/*      */         }
/* 1168 */         playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 1169 */         par1EntityPlayer.onItemPickup(this, var3);
/*      */         
/* 1171 */         if (var2.stackSize <= 0)
/*      */         {
/* 1173 */           setDead();
/*      */         }
/*      */         
/* 1176 */         if (was_empty_handed_before && par1EntityPlayer.hasHeldItem()) {
/* 1177 */           par1EntityPlayer.sendPacket(new Packet85SimpleSignal(EnumSignal.picked_up_held_item));
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
/*      */ 
/*      */   
/*      */   public String getEntityName() {
/* 1192 */     return "EntityItem: " + getEntityItem().getDisplayName() + " x " + getStackSize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackWithItem() {
/* 1200 */     return false;
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
/*      */   public ItemStack getEntityItem() {
/* 1222 */     ItemStack var1 = getDataWatcher().getWatchableObjectItemStack(10);
/*      */     
/* 1224 */     if (var1 == null) {
/*      */       
/* 1226 */       if (this.worldObj != null)
/*      */       {
/* 1228 */         this.worldObj.getWorldLogAgent().logSevere("Item entity " + this.entityId + " has no item?!");
/*      */       }
/*      */       
/* 1231 */       return new ItemStack(Block.stone);
/*      */     } 
/*      */ 
/*      */     
/* 1235 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEntityItemStack(ItemStack par1ItemStack) {
/* 1244 */     if (par1ItemStack != null && par1ItemStack.isBlock() && !par1ItemStack.getItemAsBlock().getBlock().canBeCarried()) {
/* 1245 */       Minecraft.setErrorMessage("setEntityItemStack: the block " + par1ItemStack + " is not carriable");
/*      */     }
/* 1247 */     getDataWatcher().updateObject(10, par1ItemStack);
/* 1248 */     getDataWatcher().setObjectWatched(10);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1253 */     this.health = 5;
/* 1254 */     this.cooking_progress = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isImmuneToExplosion() {
/* 1264 */     ItemStack item_stack = getEntityItem();
/*      */     
/* 1266 */     if (item_stack == null || item_stack.itemID < 256 || item_stack.isItemStackDamageable()) {
/* 1267 */       return false;
/*      */     }
/* 1269 */     Item item = item_stack.getItem();
/*      */     
/* 1271 */     if (item == null) {
/* 1272 */       return false;
/*      */     }
/* 1274 */     if (item.isCompletelyMetal()) {
/* 1275 */       return true;
/*      */     }
/* 1277 */     return (item instanceof ItemNugget || item instanceof ItemIngot || item == Item.redstone);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean handleExplosion(Explosion explosion) {
/* 1282 */     applyExplosionMotion(explosion);
/*      */     
/* 1284 */     if (isImmuneToExplosion()) {
/* 1285 */       return true;
/*      */     }
/* 1287 */     ItemStack item_stack = getEntityItem();
/*      */     
/* 1289 */     if (item_stack == null) {
/* 1290 */       return false;
/*      */     }
/* 1292 */     Item item = item_stack.getItem();
/*      */     
/* 1294 */     if (item == null) {
/* 1295 */       return false;
/*      */     }
/* 1297 */     double dx = this.posX - explosion.explosionX;
/* 1298 */     double dy = this.posY - explosion.explosionY;
/* 1299 */     double dz = this.posZ - explosion.explosionZ;
/*      */     
/* 1301 */     float explosion_force = calcExplosionForce(explosion.explosion_size_vs_blocks, World.getDistanceSqFromDeltas(dx, dy * 0.5D, dz));
/*      */     
/* 1303 */     if (item.itemID < 256) {
/*      */       
/* 1305 */       Block block = Block.getBlock(item.itemID);
/*      */       
/* 1307 */       if (block == null) {
/* 1308 */         return false;
/*      */       }
/* 1310 */       double distance_sq = World.getDistanceSqFromDeltas(this.posX - explosion.explosionX, this.posY - explosion.explosionY, this.posZ - explosion.explosionZ);
/*      */       
/* 1312 */       if (distance_sq < 1.0D) {
/* 1313 */         distance_sq = 1.0D;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1322 */       if (explosion_force * 2.0F < block.getExplosionResistance(explosion)) {
/* 1323 */         return true;
/*      */       }
/* 1325 */       int metadata = item_stack.getItemSubtype();
/*      */       
/* 1327 */       if (!block.isValidMetadata(metadata))
/*      */       {
/* 1329 */         for (int j = 0; j < 16; j++) {
/*      */           
/* 1331 */           if (block.isValidMetadata(j)) {
/*      */             
/* 1333 */             metadata = j;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/* 1339 */       for (int i = 0; i < item_stack.stackSize; i++)
/* 1340 */         block.dropBlockAsEntityItem((new BlockBreakInfo(this.worldObj, getBlockPosX(), getBlockPosY(), getBlockPosZ())).setBlock(block, metadata).setExploded(explosion)); 
/*      */     } else {
/* 1342 */       if (item_stack.isItemStackDamageable()) {
/*      */         
/* 1344 */         attackEntityFrom(new Damage(DamageSource.setExplosionSource(explosion), explosion_force * 64.0F));
/* 1345 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 1349 */       if (explosion_force < 0.2F) {
/* 1350 */         return true;
/*      */       }
/* 1352 */       Block block = null;
/*      */       
/* 1354 */       if (item == Item.bed) {
/* 1355 */         block = Block.bed;
/* 1356 */       } else if (item == Item.doorWood) {
/* 1357 */         block = Block.doorWood;
/*      */       } 
/* 1359 */       if (block != null) {
/*      */         
/* 1361 */         block.dropBlockAsEntityItem((new BlockBreakInfo(this.worldObj, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))).setBlock(Block.bed, 0).setExploded(explosion));
/* 1362 */         setDead();
/*      */         
/* 1364 */         tryRemoveFromWorldUniques();
/*      */         
/* 1366 */         return true;
/*      */       } 
/*      */       
/* 1369 */       if (item == Item.flint) {
/* 1370 */         item = Item.chipFlint;
/* 1371 */       } else if (item == Item.emerald) {
/* 1372 */         item = Item.shardEmerald;
/* 1373 */       } else if (item == Item.diamond) {
/* 1374 */         item = Item.shardDiamond;
/*      */       } else {
/* 1376 */         item = null;
/*      */       } 
/* 1378 */       if (item != null && !this.worldObj.isRemote)
/*      */       {
/* 1380 */         for (int i = 0; i < item_stack.stackSize; i++) {
/*      */           
/* 1382 */           EntityItem entity_item = (new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(item))).applyExplosionMotion(explosion);
/* 1383 */           this.worldObj.spawnEntityInWorld(entity_item);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1388 */     setDead();
/*      */     
/* 1390 */     tryRemoveFromWorldUniques();
/*      */     
/* 1392 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float calcExplosionForce(float explosion_size, double distance_sq) {
/* 1398 */     return (float)(explosion_size / Math.pow(distance_sq, 0.75D));
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityItem applyExplosionMotion(Explosion explosion) {
/* 1403 */     float size = explosion.explosion_size_vs_blocks;
/*      */ 
/*      */ 
/*      */     
/* 1407 */     double dx = this.posX - explosion.explosionX;
/* 1408 */     double dy = this.posY - explosion.explosionY;
/* 1409 */     double dz = this.posZ - explosion.explosionZ;
/*      */     
/* 1411 */     double distance_sq = World.getDistanceSqFromDeltas(dx, dy * 0.5D, dz);
/*      */     
/* 1413 */     if (distance_sq < 1.0D) {
/* 1414 */       distance_sq = 1.0D;
/*      */     }
/* 1416 */     float force = calcExplosionForce(explosion.explosion_size_vs_blocks, distance_sq);
/*      */     
/* 1418 */     this.motionX = dx * force * 0.17499999701976776D;
/* 1419 */     this.motionY = 0.4000000059604645D * Math.sqrt(size / Math.pow(distance_sq, 0.75D));
/* 1420 */     this.motionZ = dz * force * 0.17499999701976776D;
/*      */     
/* 1422 */     this.motionX *= (0.8F + this.rand.nextFloat() * 0.4F);
/* 1423 */     this.motionY *= (0.8F + this.rand.nextFloat() * 0.4F);
/* 1424 */     this.motionZ *= (0.8F + this.rand.nextFloat() * 0.4F);
/*      */     
/* 1426 */     this.send_position_update_immediately = true;
/*      */     
/* 1428 */     return this;
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
/*      */   public boolean canDouseFire() {
/* 1443 */     return getEntityItem().canDouseFire();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canCatchFire() {
/* 1448 */     return getEntityItem().canCatchFire();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByFire() {
/* 1453 */     return getEntityItem().isHarmedByFire();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByLava() {
/* 1458 */     return getEntityItem().isHarmedByLava();
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
/*      */   public boolean canRaycastToEntity(EntityLivingBase elb) {
/* 1472 */     Raycast raycast = (new Raycast(this.worldObj, getCenterPoint())).setPolicies(RaycastPolicies.for_entity_item_pickup);
/*      */     
/* 1474 */     if (raycast.checkForNoBlockCollision(elb.getFootPosPlusFractionOfHeight(0.25F))) {
/* 1475 */       return true;
/*      */     }
/* 1477 */     if (raycast.checkForNoBlockCollision(elb.getFootPosPlusFractionOfHeight(0.5F))) {
/* 1478 */       return true;
/*      */     }
/* 1480 */     return raycast.checkForNoBlockCollision(elb.getFootPosPlusFractionOfHeight(0.75F));
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
/*      */   public boolean isArtifact() {
/* 1523 */     return (getEntityItem() != null && getEntityItem().isArtifact());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEntityInvulnerable() {
/* 1528 */     return (isArtifact() || super.isEntityInvulnerable());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByPepsin() {
/* 1533 */     return getEntityItem().isHarmedByPepsin();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByAcid() {
/* 1538 */     return getEntityItem().isHarmedByAcid();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedBy(DamageSource damage_source) {
/* 1543 */     return getEntityItem().isHarmedBy(damage_source);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isVessel() {
/* 1548 */     return getEntityItem().getItem() instanceof ItemVessel;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHealth() {
/* 1553 */     return this.health;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */