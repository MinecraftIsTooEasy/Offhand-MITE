/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ 
/*      */ public class EntityHorse
/*      */   extends EntityAnimal implements IInvBasic {
/*    8 */   private static final IEntitySelector horseBreedingSelector = new EntityHorseBredSelector();
/*    9 */   private static final Attribute horseJumpStrength = (new RangedAttribute("horse.jumpStrength", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump Strength").setShouldWatch(true);
/*      */   
/*      */   private static final String[] horseArmorTextures;
/*      */   
/*   13 */   private static final String[] field_110273_bx = new String[] { "", "cop", "sil", "goo", "meo", "dio", "ada", "anc" };
/*      */ 
/*      */   
/*   16 */   private static final ItemHorseArmor[] armors = new ItemHorseArmor[] { null, Item.horseArmorCopper, Item.horseArmorSilver, Item.horseArmorGold, Item.horseArmorIron, Item.horseArmorMithril, Item.horseArmorAdamantium, Item.horseArmorAncientMetal };
/*      */   private static final String[] horseTextures;
/*      */   
/*      */   static {
/*   20 */     horseArmorTextures = new String[armors.length];
/*      */     
/*   22 */     for (int i = 0; i < armors.length; i++) {
/*   23 */       horseArmorTextures[i] = (armors[i] == null) ? null : ("textures/entity/horse/armor/horse_armor_" + (armors[i]).effective_material.name + ".png");
/*      */     }
/*   25 */     if (field_110273_bx.length != armors.length) {
/*   26 */       Debug.setErrorMessage("EntityHorse: field_110273_bx must have same number of elements as armors");
/*      */     }
/*      */     
/*   29 */     horseTextures = new String[] { "textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png" };
/*   30 */     field_110269_bA = new String[] { "hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb" };
/*   31 */     horseMarkingTextures = new String[] { null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png" };
/*   32 */     field_110292_bC = new String[] { "", "wo_", "wmo", "wdo", "bdo" };
/*      */   }
/*      */   private static final String[] field_110269_bA;
/*      */   private static final String[] horseMarkingTextures;
/*      */   private static final String[] field_110292_bC;
/*      */   private int eatingHaystackCounter;
/*      */   private int openMouthCounter;
/*      */   private int jumpRearingCounter;
/*      */   public int field_110278_bp;
/*      */   public int field_110279_bq;
/*      */   protected boolean horseJumping;
/*      */   private AnimalChest horseChest;
/*      */   private boolean hasReproduced;
/*      */   protected int temper;
/*      */   protected float jumpPower;
/*      */   private boolean field_110294_bI;
/*      */   private float headLean;
/*      */   private float prevHeadLean;
/*      */   private float rearingAmount;
/*      */   private float prevRearingAmount;
/*      */   private float mouthOpenness;
/*      */   private float prevMouthOpenness;
/*      */   private int field_110285_bP;
/*      */   private String field_110286_bQ;
/*   56 */   private String[] field_110280_bR = new String[3];
/*      */   
/*      */   private int rebellious_for_eating_counter;
/*      */   
/*      */   private int data_object_id_is_rebellious_for_eating;
/*      */   
/*      */   private int rebellious_for_riding_counter;
/*      */   
/*      */   private int data_object_id_is_rebellious_for_riding;
/*      */ 
/*      */   
/*      */   public EntityHorse(World par1World) {
/*   68 */     super(par1World);
/*   69 */     setSize(1.4F, 1.6F);
/*      */     
/*   71 */     setChested(false);
/*   72 */     getNavigator().setAvoidsWater(true);
/*   73 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*      */     
/*   75 */     this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
/*   76 */     this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
/*      */     
/*   78 */     this.tasks.addTask(3, new EntityAIFollowParent(this, 1.0D));
/*      */     
/*   80 */     this.tasks.addTask(6, new EntityAIWander(this, 0.9D));
/*   81 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*   82 */     this.tasks.addTask(8, new EntityAILookIdle(this));
/*      */     
/*   84 */     this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.2F, 0.25F, true));
/*   85 */     this.tasks.addTask(2, new EntityAIAvoidPotentialPredators(this, 1.05F, true));
/*   86 */     this.tasks.addTask(4, new EntityAISeekShelterFromRain(this, 1.0F, true));
/*   87 */     this.tasks.addTask(4, new EntityAIGetOutOfWater(this, 1.0F));
/*      */     
/*   89 */     func_110226_cD();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*   94 */     super.entityInit();
/*   95 */     this.dataWatcher.addObject(16, Integer.valueOf(0));
/*   96 */     this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
/*   97 */     this.dataWatcher.addObject(20, Integer.valueOf(0));
/*   98 */     this.dataWatcher.addObject(21, String.valueOf(""));
/*   99 */     this.dataWatcher.addObject(22, Integer.valueOf(0));
/*      */     
/*  101 */     this.data_object_id_is_rebellious_for_eating = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*  102 */     this.data_object_id_is_rebellious_for_riding = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseType(int par1) {
/*  107 */     this.dataWatcher.updateObject(19, Byte.valueOf((byte)par1));
/*  108 */     func_110230_cF();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHorseType() {
/*  116 */     return this.dataWatcher.getWatchableObjectByte(19);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseVariant(int par1) {
/*  121 */     this.dataWatcher.updateObject(20, Integer.valueOf(par1));
/*  122 */     func_110230_cF();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHorseVariant() {
/*  127 */     return this.dataWatcher.getWatchableObjectInt(20);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRebelliousForEating() {
/*  132 */     if (this.worldObj.isRemote) {
/*  133 */       return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_rebellious_for_eating) != 0);
/*      */     }
/*  135 */     return (this.rebellious_for_eating_counter > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setRebelliousForEatingCounter(int rebellious_for_eating_counter) {
/*  141 */     boolean was_rebellious_for_eating = isRebelliousForEating();
/*      */     
/*  143 */     this.rebellious_for_eating_counter = rebellious_for_eating_counter;
/*      */     
/*  145 */     boolean is_rebellious_for_eating = isRebelliousForEating();
/*      */     
/*  147 */     if (is_rebellious_for_eating != was_rebellious_for_eating) {
/*  148 */       this.dataWatcher.updateObject(this.data_object_id_is_rebellious_for_eating, Byte.valueOf((byte)(is_rebellious_for_eating ? -1 : 0)));
/*      */     }
/*  150 */     return is_rebellious_for_eating;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRebelliousForRiding() {
/*  155 */     if (this.worldObj.isRemote) {
/*  156 */       return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_rebellious_for_riding) != 0);
/*      */     }
/*  158 */     return (this.rebellious_for_riding_counter > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setRebelliousForRidingCounter(int rebellious_for_riding_counter) {
/*  164 */     boolean was_rebellious_for_riding = isRebelliousForRiding();
/*      */     
/*  166 */     this.rebellious_for_riding_counter = rebellious_for_riding_counter;
/*      */     
/*  168 */     boolean is_rebellious_for_riding = isRebelliousForRiding();
/*      */     
/*  170 */     if (is_rebellious_for_riding != was_rebellious_for_riding) {
/*  171 */       this.dataWatcher.updateObject(this.data_object_id_is_rebellious_for_riding, Byte.valueOf((byte)(is_rebellious_for_riding ? -1 : 0)));
/*      */     }
/*  173 */     return is_rebellious_for_riding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityName() {
/*  181 */     if (hasCustomNameTag())
/*      */     {
/*  183 */       return getCustomNameTag();
/*      */     }
/*      */ 
/*      */     
/*  187 */     int var1 = getHorseType();
/*      */     
/*  189 */     switch (var1) {
/*      */ 
/*      */       
/*      */       default:
/*  193 */         return StatCollector.translateToLocal("entity.horse.name");
/*      */       
/*      */       case 1:
/*  196 */         return StatCollector.translateToLocal("entity.donkey.name");
/*      */       
/*      */       case 2:
/*  199 */         return StatCollector.translateToLocal("entity.mule.name");
/*      */       
/*      */       case 3:
/*  202 */         return StatCollector.translateToLocal("entity.zombiehorse.name");
/*      */       case 4:
/*      */         break;
/*  205 */     }  return StatCollector.translateToLocal("entity.skeletonhorse.name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean getHorseWatchableBoolean(int par1) {
/*  212 */     return ((this.dataWatcher.getWatchableObjectInt(16) & par1) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setHorseWatchableBoolean(int par1, boolean par2) {
/*  217 */     int var3 = this.dataWatcher.getWatchableObjectInt(16);
/*      */     
/*  219 */     if (par2) {
/*      */       
/*  221 */       this.dataWatcher.updateObject(16, Integer.valueOf(var3 | par1));
/*      */     }
/*      */     else {
/*      */       
/*  225 */       this.dataWatcher.updateObject(16, Integer.valueOf(var3 & (par1 ^ 0xFFFFFFFF)));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAdultHorse() {
/*  231 */     return !isChild();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTame() {
/*  236 */     return getHorseWatchableBoolean(2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOwnerName() {
/*  246 */     return this.dataWatcher.getWatchableObjectString(21);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOwnerName(String par1Str) {
/*  251 */     this.dataWatcher.updateObject(21, par1Str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getHorseSize() {
/*  262 */     int var1 = getGrowingAge();
/*  263 */     return (var1 >= 0) ? 1.0F : (0.5F + (EntityAgeable.getGrowingAgeOfNewborn() - var1) / EntityAgeable.getGrowingAgeOfNewborn() * 0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScaleForAge(boolean par1) {
/*  271 */     if (par1) {
/*      */       
/*  273 */       setScale(getHorseSize());
/*      */     }
/*      */     else {
/*      */       
/*  277 */       setScale(1.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHorseJumping() {
/*  283 */     return this.horseJumping;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseTamed(boolean par1) {
/*  288 */     setHorseWatchableBoolean(2, par1);
/*      */     
/*  290 */     if (par1 && onServer()) {
/*      */       
/*  292 */       setRebelliousForEatingCounter(0);
/*  293 */       setRebelliousForRidingCounter(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseJumping(boolean par1) {
/*  299 */     this.horseJumping = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean allowLeashing() {
/*  305 */     return (!isHorseUndead() && super.allowLeashing());
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_142017_o(float par1) {
/*  310 */     if (par1 > 6.0F && isEatingHaystack())
/*      */     {
/*  312 */       setEatingHaystack(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isChested() {
/*  318 */     return getHorseWatchableBoolean(8);
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_110241_cb() {
/*  323 */     return this.dataWatcher.getWatchableObjectInt(22);
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
/*      */   public int getHorseArmorIndex(ItemStack par1ItemStack) {
/*  337 */     if (par1ItemStack == null) {
/*  338 */       return 0;
/*      */     }
/*  340 */     Item item = Item.itemsList[par1ItemStack.itemID];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     for (int i = 0; i < armors.length; i++) {
/*      */       
/*  359 */       if (armors[i] == item) {
/*  360 */         return i;
/*      */       }
/*      */     } 
/*  363 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEatingHaystack() {
/*  370 */     return getHorseWatchableBoolean(32);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRearing() {
/*  375 */     return getHorseWatchableBoolean(64);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_110205_ce() {
/*  380 */     return getHorseWatchableBoolean(16);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getHasReproduced() {
/*  385 */     return this.hasReproduced;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110236_r(int par1) {
/*  390 */     this.dataWatcher.updateObject(22, Integer.valueOf(par1));
/*  391 */     func_110230_cF();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110242_l(boolean par1) {
/*  396 */     setHorseWatchableBoolean(16, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setChested(boolean par1) {
/*  401 */     setHorseWatchableBoolean(8, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHasReproduced(boolean par1) {
/*  406 */     this.hasReproduced = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseSaddled(boolean par1) {
/*  411 */     setHorseWatchableBoolean(4, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTemper() {
/*  416 */     return this.temper;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTemper(int par1) {
/*  421 */     this.temper = par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int increaseTemper(int par1) {
/*  426 */     int var2 = MathHelper.clamp_int(getTemper() + par1, 0, getMaxTemper());
/*  427 */     setTemper(var2);
/*  428 */     return var2;
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
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/*  472 */     if (this.riddenByEntity != null && this.riddenByEntity.equals(damage.getResponsibleEntity()))
/*      */     {
/*      */       
/*  475 */       return null;
/*      */     }
/*  477 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*      */     
/*  479 */     if (result == null || result.entityWasDestroyed()) {
/*  480 */       return result;
/*      */     }
/*  482 */     if (result.getAmountOfHealthLost() >= 4.0F && damage.getResponsibleEntity() instanceof EntityLiving && this.riddenByEntity != null && this.rand.nextInt(2) == 0) {
/*      */ 
/*      */       
/*  485 */       if (onServer()) {
/*      */         
/*  487 */         this.jumpRearingCounter = 1;
/*      */         
/*  489 */         setEatingHaystack(false);
/*      */         
/*  491 */         setHorseWatchableBoolean(64, true);
/*      */       } 
/*      */       
/*  494 */       String var1 = getAngrySoundName();
/*      */       
/*  496 */       if (var1 != null) {
/*  497 */         makeSound(var1);
/*      */       }
/*  499 */       if (this.riddenByEntity instanceof EntityLivingBase && this.rand.nextInt(3) == 0) {
/*  500 */         this.riddenByEntity.getAsEntityLivingBase().mountEntity(null);
/*      */       }
/*      */     } 
/*  503 */     if (result.entityWasNegativelyAffected() && damage.wasCausedByPlayer() && !isTame()) {
/*  504 */       increaseTemper(-10);
/*      */     }
/*  506 */     if (result.entityWasNegativelyAffected() && isEatingHaystack()) {
/*  507 */       setEatingHaystack(false);
/*      */     }
/*  509 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack[] getWornItems() {
/*  515 */     return new ItemStack[] { getSaddle(), getBarding() };
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getSaddle() {
/*  520 */     return isHorseSaddled() ? new ItemStack(Item.saddle) : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getBarding() {
/*  525 */     Item barding_item = getBardingItem();
/*      */     
/*  527 */     return (barding_item == null) ? null : new ItemStack(barding_item);
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
/*      */   public float getProtectionFromArmor(DamageSource damage_source, boolean include_enchantments) {
/*  540 */     return ItemArmor.getTotalArmorProtection(getWornItems(), damage_source, include_enchantments, this);
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
/*      */   public ItemHorseArmor getBardingItem() {
/*  557 */     return armors[func_110241_cb()];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/*  565 */     return (this.riddenByEntity == null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean prepareChunkForSpawn() {
/*  570 */     int var1 = MathHelper.floor_double(this.posX);
/*  571 */     int var2 = MathHelper.floor_double(this.posZ);
/*  572 */     this.worldObj.getBiomeGenForCoords(var1, var2);
/*  573 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropChests() {
/*  578 */     if (!this.worldObj.isRemote && isChested()) {
/*      */       
/*  580 */       dropItem(Block.chest.blockID, 1);
/*  581 */       setChested(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110266_cB() {
/*  587 */     openHorseMouth();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fall(float par1) {
/*  596 */     if (onClient()) {
/*      */       return;
/*      */     }
/*  599 */     if (par1 > 1.0F)
/*      */     {
/*  601 */       playSound("mob.horse.land", 0.4F, 1.0F);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  606 */     float[] damages = new float[2];
/*      */     
/*  608 */     calcFallDamage(par1, damages);
/*      */     
/*  610 */     float var2 = damages[1];
/*      */ 
/*      */     
/*  613 */     if (var2 >= 1.0F) {
/*      */ 
/*      */       
/*  616 */       attackEntityFrom(new Damage(DamageSource.fall, var2));
/*      */       
/*  618 */       if (this.riddenByEntity != null)
/*      */       {
/*      */ 
/*      */         
/*  622 */         this.riddenByEntity.attackEntityFrom(new Damage(DamageSource.fall, var2 * 0.5F));
/*      */       }
/*      */       
/*  625 */       int var3 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.2D - this.prevRotationYaw), MathHelper.floor_double(this.posZ));
/*      */       
/*  627 */       if (var3 > 0) {
/*      */         
/*  629 */         StepSound var4 = (Block.blocksList[var3]).stepSound;
/*  630 */         this.worldObj.playSoundAtEntity(this, var4.getStepSound(), var4.getVolume() * 0.5F, var4.getPitch() * 0.75F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int func_110225_cC() {
/*  637 */     int var1 = getHorseType();
/*  638 */     return (isChested() && (var1 == 1 || var1 == 2)) ? 17 : 2;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110226_cD() {
/*  643 */     AnimalChest var1 = this.horseChest;
/*  644 */     this.horseChest = new AnimalChest("HorseChest", func_110225_cC());
/*  645 */     this.horseChest.func_110133_a(getEntityName());
/*      */     
/*  647 */     if (var1 != null) {
/*      */       
/*  649 */       var1.func_110132_b(this);
/*  650 */       int var2 = Math.min(var1.getSizeInventory(), this.horseChest.getSizeInventory());
/*      */       
/*  652 */       for (int var3 = 0; var3 < var2; var3++) {
/*      */         
/*  654 */         ItemStack var4 = var1.getStackInSlot(var3);
/*      */         
/*  656 */         if (var4 != null)
/*      */         {
/*  658 */           this.horseChest.setInventorySlotContents(var3, var4.copy());
/*      */         }
/*      */       } 
/*      */       
/*  662 */       var1 = null;
/*      */     } 
/*      */     
/*  665 */     this.horseChest.func_110134_a(this);
/*  666 */     func_110232_cE();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_110232_cE() {
/*  672 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*      */       
/*  674 */       setHorseSaddled((this.horseChest.getStackInSlot(0) != null));
/*      */ 
/*      */       
/*  677 */       if (isNormalHorse())
/*      */       {
/*  679 */         func_110236_r(getHorseArmorIndex(this.horseChest.getStackInSlot(1)));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onInventoryChanged(InventoryBasic par1InventoryBasic) {
/*  689 */     int var2 = func_110241_cb();
/*  690 */     boolean var3 = isHorseSaddled();
/*  691 */     func_110232_cE();
/*      */     
/*  693 */     if (this.ticksExisted > 20) {
/*      */ 
/*      */       
/*  696 */       if (var2 != func_110241_cb() && func_110241_cb() != 0)
/*      */       {
/*  698 */         playSound("mob.horse.armor", 0.5F, 1.0F);
/*      */       }
/*      */       
/*  701 */       if (!var3 && isHorseSaddled())
/*      */       {
/*  703 */         playSound("mob.horse.leather", 0.5F, 1.0F);
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
/*      */   public boolean getCanSpawnHere(boolean perform_light_check) {
/*  719 */     prepareChunkForSpawn();
/*  720 */     return super.getCanSpawnHere(perform_light_check);
/*      */   }
/*      */ 
/*      */   
/*      */   protected EntityHorse getClosestHorse(Entity par1Entity, double par2) {
/*  725 */     double var4 = Double.MAX_VALUE;
/*  726 */     Entity var6 = null;
/*  727 */     List var7 = this.worldObj.getEntitiesWithinAABBExcludingEntity(par1Entity, par1Entity.boundingBox.addCoord(par2, par2, par2), horseBreedingSelector);
/*  728 */     Iterator<Entity> var8 = var7.iterator();
/*      */     
/*  730 */     while (var8.hasNext()) {
/*      */       
/*  732 */       Entity var9 = var8.next();
/*  733 */       double var10 = var9.getDistanceSq(par1Entity.posX, par1Entity.posY, par1Entity.posZ);
/*      */       
/*  735 */       if (var10 < var4) {
/*      */         
/*  737 */         var6 = var9;
/*  738 */         var4 = var10;
/*      */       } 
/*      */     } 
/*      */     
/*  742 */     return (EntityHorse)var6;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getHorseJumpStrength() {
/*  747 */     return getEntityAttribute(horseJumpStrength).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/*  755 */     openHorseMouth();
/*  756 */     int var1 = getHorseType();
/*  757 */     return (var1 == 3) ? "mob.horse.zombie.death" : ((var1 == 4) ? "mob.horse.skeleton.death" : ((var1 != 1 && var1 != 2) ? "mob.horse.death" : "mob.horse.donkey.death"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getDropItemId() {
/*  765 */     boolean var1 = (this.rand.nextInt(4) == 0);
/*  766 */     int var2 = getHorseType();
/*  767 */     return (var2 == 4) ? Item.bone.itemID : ((var2 == 3) ? (var1 ? 0 : Item.rottenFlesh.itemID) : Item.leather.itemID);
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
/*      */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/*  788 */     int num_drops = this.rand.nextInt(3) + 1;
/*      */     int i;
/*  790 */     for (i = 0; i < num_drops; i++) {
/*  791 */       dropItem(getDropItemId(), 1);
/*      */     }
/*  793 */     if (getHorseType() > 2) {
/*      */       return;
/*      */     }
/*  796 */     num_drops = 1 + this.rand.nextInt(1 + damage_source.getButcheringModifier());
/*      */     
/*  798 */     if (getHorseType() == 0) {
/*  799 */       num_drops += this.rand.nextInt(2);
/*      */     }
/*  801 */     for (i = 0; i < num_drops; i++) {
/*  802 */       dropItem(isBurning() ? Item.beefCooked.itemID : Item.beefRaw.itemID, 1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/*  810 */     openHorseMouth();
/*      */     
/*  812 */     if (this.rand.nextInt(3) == 0)
/*      */     {
/*  814 */       makeHorseRear();
/*      */     }
/*      */     
/*  817 */     int var1 = getHorseType();
/*  818 */     return (var1 == 3) ? "mob.horse.zombie.hit" : ((var1 == 4) ? "mob.horse.skeleton.hit" : ((var1 != 1 && var1 != 2) ? "mob.horse.hit" : "mob.horse.donkey.hit"));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHorseSaddled() {
/*  823 */     return getHorseWatchableBoolean(4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLivingSound() {
/*  831 */     openHorseMouth();
/*      */     
/*  833 */     if (this.rand.nextInt(10) == 0 && !isMovementBlocked())
/*      */     {
/*  835 */       makeHorseRear();
/*      */     }
/*      */     
/*  838 */     int var1 = getHorseType();
/*  839 */     return (var1 == 3) ? "mob.horse.zombie.idle" : ((var1 == 4) ? "mob.horse.skeleton.idle" : ((var1 != 1 && var1 != 2) ? "mob.horse.idle" : "mob.horse.donkey.idle"));
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getAngrySoundName() {
/*  844 */     openHorseMouth();
/*  845 */     makeHorseRear();
/*  846 */     int var1 = getHorseType();
/*  847 */     return (var1 != 3 && var1 != 4) ? ((var1 != 1 && var1 != 2) ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/*  855 */     StepSound var5 = (Block.blocksList[par4]).stepSound;
/*      */     
/*  857 */     if (this.worldObj.getBlockId(par1, par2 + 1, par3) == Block.snow.blockID)
/*      */     {
/*  859 */       var5 = Block.snow.stepSound;
/*      */     }
/*      */     
/*  862 */     if (!(Block.blocksList[par4]).blockMaterial.isLiquid()) {
/*      */       
/*  864 */       int var6 = getHorseType();
/*      */       
/*  866 */       if (this.riddenByEntity != null && var6 != 1 && var6 != 2) {
/*      */         
/*  868 */         this.field_110285_bP++;
/*      */         
/*  870 */         if (this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0)
/*      */         {
/*  872 */           playSound("mob.horse.gallop", var5.getVolume() * 0.15F, var5.getPitch());
/*      */           
/*  874 */           if (var6 == 0 && this.rand.nextInt(10) == 0)
/*      */           {
/*  876 */             playSound("mob.horse.breathe", var5.getVolume() * 0.6F, var5.getPitch());
/*      */           }
/*      */         }
/*  879 */         else if (this.field_110285_bP <= 5)
/*      */         {
/*  881 */           playSound("mob.horse.wood", var5.getVolume() * 0.15F, var5.getPitch());
/*      */         }
/*      */       
/*  884 */       } else if (var5 == Block.soundWoodFootstep) {
/*      */         
/*  886 */         playSound("mob.horse.soft", var5.getVolume() * 0.15F, var5.getPitch());
/*      */       }
/*      */       else {
/*      */         
/*  890 */         playSound("mob.horse.wood", var5.getVolume() * 0.15F, var5.getPitch());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  897 */     super.applyEntityAttributes();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  902 */     setEntityAttribute(horseJumpStrength);
/*  903 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 53.0D);
/*  904 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.22499999403953552D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSpawnedInChunk() {
/*  912 */     return 6;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxTemper() {
/*  917 */     return 100;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundVolume(String sound) {
/*  926 */     return 0.8F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTalkInterval() {
/*  934 */     return 400;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_110239_cn() {
/*  939 */     return (getHorseType() == 0 || func_110241_cb() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110230_cF() {
/*  944 */     this.field_110286_bQ = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void setHorseTexturePaths() {
/*  949 */     this.field_110286_bQ = "horse/";
/*  950 */     this.field_110280_bR[0] = null;
/*  951 */     this.field_110280_bR[1] = null;
/*  952 */     this.field_110280_bR[2] = null;
/*  953 */     int var1 = getHorseType();
/*  954 */     int var2 = getHorseVariant();
/*      */ 
/*      */     
/*  957 */     if (var1 == 0) {
/*      */       
/*  959 */       int i = var2 & 0xFF;
/*  960 */       int var4 = (var2 & 0xFF00) >> 8;
/*  961 */       this.field_110280_bR[0] = horseTextures[i];
/*  962 */       this.field_110286_bQ += field_110269_bA[i];
/*  963 */       this.field_110280_bR[1] = horseMarkingTextures[var4];
/*  964 */       this.field_110286_bQ += field_110292_bC[var4];
/*      */     }
/*      */     else {
/*      */       
/*  968 */       this.field_110280_bR[0] = "";
/*  969 */       this.field_110286_bQ += "_" + var1 + "_";
/*      */     } 
/*      */     
/*  972 */     int var3 = func_110241_cb();
/*  973 */     this.field_110280_bR[2] = horseArmorTextures[var3];
/*  974 */     this.field_110286_bQ += field_110273_bx[var3];
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHorseTexture() {
/*  979 */     if (this.field_110286_bQ == null)
/*      */     {
/*  981 */       setHorseTexturePaths();
/*      */     }
/*      */     
/*  984 */     return this.field_110286_bQ;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getVariantTexturePaths() {
/*  989 */     if (this.field_110286_bQ == null)
/*      */     {
/*  991 */       setHorseTexturePaths();
/*      */     }
/*      */     
/*  994 */     return this.field_110280_bR;
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
/*      */   public boolean tryOpenGUI(EntityPlayer player) {
/* 1008 */     if (!isTame()) {
/* 1009 */       return false;
/*      */     }
/* 1011 */     if (this.riddenByEntity == null || this.riddenByEntity == player) {
/*      */       
/* 1013 */       if (player.onServer()) {
/*      */         
/* 1015 */         this.horseChest.func_110133_a(getEntityName());
/* 1016 */         player.displayGUIHorse(this, this.horseChest);
/*      */       } 
/*      */       
/* 1019 */       return true;
/*      */     } 
/*      */     
/* 1022 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 1030 */     if (isTame()) {
/*      */       
/* 1032 */       if (isAdultHorse() && player.isSneaking() && tryOpenGUI(player)) {
/* 1033 */         return true;
/*      */       
/*      */       }
/*      */     }
/* 1037 */     else if (isHorseUndead()) {
/* 1038 */       return super.onEntityRightClicked(player, item_stack);
/*      */     } 
/*      */     
/* 1041 */     if (isAdultHorse() && this.riddenByEntity != null) {
/* 1042 */       return false;
/*      */     }
/* 1044 */     if (getLeashed() && getLeashedToEntity() == player) {
/*      */       
/* 1046 */       if (onServer()) {
/* 1047 */         clearLeashed(!player.inCreativeMode(), true);
/*      */       }
/* 1049 */       return true;
/*      */     } 
/*      */     
/* 1052 */     if (item_stack != null) {
/*      */       
/* 1054 */       boolean horse_accepted_item = false;
/* 1055 */       boolean horse_was_healed = false;
/*      */       
/* 1057 */       if (isNormalHorse())
/*      */       {
/* 1059 */         if (getHorseArmorIndex(item_stack) > 0) {
/*      */           
/* 1061 */           if (!isTame()) {
/*      */             
/* 1063 */             makeHorseRearWithSound();
/* 1064 */             return true;
/*      */           } 
/*      */           
/* 1067 */           if (tryOpenGUI(player)) {
/* 1068 */             return true;
/*      */           }
/*      */         } 
/*      */       }
/* 1072 */       if (!isHorseUndead()) {
/*      */         
/* 1074 */         float healing = 0.0F;
/* 1075 */         short growth = 0;
/* 1076 */         byte temper_modifier = 0;
/*      */         
/* 1078 */         Item item = item_stack.getItem();
/*      */         
/* 1080 */         if (item == Item.wheat) {
/*      */           
/* 1082 */           healing = 2.0F;
/* 1083 */           growth = 60;
/* 1084 */           temper_modifier = 3;
/*      */         }
/* 1086 */         else if (item == Item.sugar) {
/*      */           
/* 1088 */           healing = 1.0F;
/* 1089 */           growth = 30;
/* 1090 */           temper_modifier = 3;
/*      */         }
/* 1092 */         else if (item == Item.bread) {
/*      */           
/* 1094 */           healing = 7.0F;
/* 1095 */           growth = 180;
/* 1096 */           temper_modifier = 3;
/*      */         }
/* 1098 */         else if (item == Item.getItem(Block.hay)) {
/*      */           
/* 1100 */           healing = 20.0F;
/* 1101 */           growth = 180;
/*      */         }
/* 1103 */         else if (item == Item.appleRed) {
/*      */           
/* 1105 */           healing = 3.0F;
/* 1106 */           growth = 60;
/* 1107 */           temper_modifier = 3;
/*      */         }
/* 1109 */         else if (item == Item.goldenCarrot) {
/*      */           
/* 1111 */           healing = 4.0F;
/* 1112 */           growth = 60;
/* 1113 */           temper_modifier = 5;
/*      */           
/* 1115 */           if (isTame() && getGrowingAge() == 0)
/*      */           {
/* 1117 */             horse_accepted_item = true;
/* 1118 */             func_110196_bT();
/*      */           }
/*      */         
/* 1121 */         } else if (item == Item.appleGold) {
/*      */           
/* 1123 */           healing = 10.0F;
/* 1124 */           growth = 240;
/* 1125 */           temper_modifier = 10;
/*      */           
/* 1127 */           if (isTame() && getGrowingAge() == 0) {
/*      */             
/* 1129 */             horse_accepted_item = true;
/* 1130 */             func_110196_bT();
/*      */           } 
/*      */         } 
/*      */         
/* 1134 */         if (getHealth() < getMaxHealth() && healing > 0.0F) {
/*      */           
/* 1136 */           heal(healing);
/* 1137 */           horse_accepted_item = true;
/* 1138 */           horse_was_healed = true;
/*      */         }
/* 1140 */         else if (!isTame() && isRebelliousForEating()) {
/*      */           
/* 1142 */           makeHorseRearWithSound();
/* 1143 */           return true;
/*      */         } 
/*      */         
/* 1146 */         if (!isAdultHorse() && growth > 0) {
/*      */           
/* 1148 */           addGrowth(growth);
/* 1149 */           horse_accepted_item = true;
/*      */         } 
/*      */         
/* 1152 */         if (temper_modifier > 0 && (horse_accepted_item || !isTame()) && temper_modifier < getMaxTemper()) {
/*      */           
/* 1154 */           if (onServer()) {
/* 1155 */             entityFX(EnumEntityFX.heal);
/*      */           }
/* 1157 */           horse_accepted_item = true;
/* 1158 */           increaseTemper(temper_modifier);
/*      */         } 
/*      */         
/* 1161 */         if (horse_accepted_item) {
/* 1162 */           func_110266_cB();
/*      */         }
/* 1164 */         if (onServer() && !isTame() && horse_accepted_item && (!horse_was_healed || getHealth() >= getMaxHealth())) {
/* 1165 */           setRebelliousForEatingCounter(4000);
/*      */         }
/*      */       } 
/* 1168 */       if (!isTame() && !horse_accepted_item) {
/*      */         
/* 1170 */         makeHorseRearWithSound();
/* 1171 */         return true;
/*      */       } 
/*      */       
/* 1174 */       if (!horse_accepted_item && isPackHorse() && !isChested() && item_stack.itemID == Block.chest.blockID) {
/*      */         
/* 1176 */         setChested(true);
/*      */         
/* 1178 */         playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 1179 */         horse_accepted_item = true;
/* 1180 */         func_110226_cD();
/*      */       } 
/*      */       
/* 1183 */       if (!horse_accepted_item && isAdultHorse() && !isHorseSaddled() && item_stack.itemID == Item.saddle.itemID)
/*      */       {
/* 1185 */         if (tryOpenGUI(player)) {
/* 1186 */           return true;
/*      */         }
/*      */       }
/* 1189 */       if (horse_accepted_item) {
/*      */         
/* 1191 */         if (player.onServer() && !player.inCreativeMode()) {
/* 1192 */           player.convertOneOfHeldItem((ItemStack)null);
/*      */         }
/* 1194 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1198 */     if (isAdultHorse() && this.riddenByEntity == null) {
/*      */       
/* 1200 */       if (isRebelliousForRiding()) {
/*      */         
/* 1202 */         makeHorseRearWithSound();
/* 1203 */         return true;
/*      */       } 
/*      */       
/* 1206 */       func_110237_h(player);
/* 1207 */       return true;
/*      */     } 
/*      */     
/* 1210 */     return super.onEntityRightClicked(player, item_stack);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_110237_h(EntityPlayer par1EntityPlayer) {
/* 1433 */     par1EntityPlayer.setRotation(this.rotationYaw, this.rotationPitch);
/*      */     
/* 1435 */     setEatingHaystack(false);
/* 1436 */     setRearing(false);
/*      */     
/* 1438 */     if (!this.worldObj.isRemote)
/*      */     {
/* 1440 */       par1EntityPlayer.mountEntity(this);
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
/*      */   public boolean isNormalHorse() {
/* 1452 */     return (getHorseType() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPackHorse() {
/* 1463 */     return (getHorseType() == 1 || getHorseType() == 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMovementBlocked() {
/* 1471 */     return (this.riddenByEntity != null && isHorseSaddled()) ? true : ((isEatingHaystack() || isRearing()));
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
/*      */   public boolean isHorseUndead() {
/* 1483 */     return (getHorseType() == 3 || getHorseType() == 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canHorseNeverBreed() {
/* 1494 */     return (getHorseType() == 2 || isHorseUndead());
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
/*      */   private void func_110210_cH() {
/* 1508 */     this.field_110278_bp = 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource par1DamageSource) {
/* 1516 */     super.onDeath(par1DamageSource);
/*      */     
/* 1518 */     if (!this.worldObj.isRemote)
/*      */     {
/* 1520 */       dropChestItems();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/* 1530 */     if (this.rand.nextInt(200) == 0)
/*      */     {
/* 1532 */       func_110210_cH();
/*      */     }
/*      */     
/* 1535 */     super.onLivingUpdate();
/*      */     
/* 1537 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1539 */       if (this.rebellious_for_eating_counter > 0) {
/* 1540 */         setRebelliousForEatingCounter(this.rebellious_for_eating_counter - 1);
/*      */       }
/* 1542 */       if (this.rebellious_for_riding_counter > 0) {
/* 1543 */         setRebelliousForRidingCounter(this.rebellious_for_riding_counter - 1);
/*      */       }
/* 1545 */       if (this.rand.nextInt(900) == 0 && this.deathTime == 0)
/*      */       {
/* 1547 */         heal(1.0F);
/*      */       }
/*      */       
/* 1550 */       if (!isEatingHaystack() && this.riddenByEntity == null && this.rand.nextInt(300) == 0 && this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ)) == Block.grass.blockID)
/*      */       {
/* 1552 */         setEatingHaystack(true);
/*      */       }
/*      */       
/* 1555 */       if (isEatingHaystack() && ++this.eatingHaystackCounter > 50) {
/*      */         
/* 1557 */         this.eatingHaystackCounter = 0;
/* 1558 */         setEatingHaystack(false);
/*      */       } 
/*      */       
/* 1561 */       if (func_110205_ce() && !isAdultHorse() && !isEatingHaystack()) {
/*      */         
/* 1563 */         EntityHorse var1 = getClosestHorse(this, 16.0D);
/*      */         
/* 1565 */         if (var1 != null && getDistanceSqToEntity(var1) > 4.0D) {
/*      */           
/* 1567 */           PathEntity var2 = this.worldObj.getPathEntityToEntity(this, var1, 16.0F, true, false, false, true);
/* 1568 */           setPathToEntity(var2);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/* 1579 */     super.onUpdate();
/*      */     
/* 1581 */     if (this.worldObj.isRemote && this.dataWatcher.hasChanges()) {
/*      */       
/* 1583 */       this.dataWatcher.func_111144_e();
/* 1584 */       func_110230_cF();
/*      */     } 
/*      */     
/* 1587 */     if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
/*      */       
/* 1589 */       this.openMouthCounter = 0;
/* 1590 */       setHorseWatchableBoolean(128, false);
/*      */     } 
/*      */     
/* 1593 */     if (!this.worldObj.isRemote && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
/*      */       
/* 1595 */       this.jumpRearingCounter = 0;
/* 1596 */       setRearing(false);
/*      */     } 
/*      */     
/* 1599 */     if (this.field_110278_bp > 0 && ++this.field_110278_bp > 8)
/*      */     {
/* 1601 */       this.field_110278_bp = 0;
/*      */     }
/*      */     
/* 1604 */     if (this.field_110279_bq > 0) {
/*      */       
/* 1606 */       this.field_110279_bq++;
/*      */       
/* 1608 */       if (this.field_110279_bq > 300)
/*      */       {
/* 1610 */         this.field_110279_bq = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1614 */     this.prevHeadLean = this.headLean;
/*      */     
/* 1616 */     if (isEatingHaystack()) {
/*      */       
/* 1618 */       this.headLean += (1.0F - this.headLean) * 0.4F + 0.05F;
/*      */       
/* 1620 */       if (this.headLean > 1.0F)
/*      */       {
/* 1622 */         this.headLean = 1.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1627 */       this.headLean += (0.0F - this.headLean) * 0.4F - 0.05F;
/*      */       
/* 1629 */       if (this.headLean < 0.0F)
/*      */       {
/* 1631 */         this.headLean = 0.0F;
/*      */       }
/*      */     } 
/*      */     
/* 1635 */     this.prevRearingAmount = this.rearingAmount;
/*      */     
/* 1637 */     if (isRearing()) {
/*      */       
/* 1639 */       this.prevHeadLean = this.headLean = 0.0F;
/* 1640 */       this.rearingAmount += (1.0F - this.rearingAmount) * 0.4F + 0.05F;
/*      */       
/* 1642 */       if (this.rearingAmount > 1.0F)
/*      */       {
/* 1644 */         this.rearingAmount = 1.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1649 */       this.field_110294_bI = false;
/* 1650 */       this.rearingAmount += (0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F;
/*      */       
/* 1652 */       if (this.rearingAmount < 0.0F)
/*      */       {
/* 1654 */         this.rearingAmount = 0.0F;
/*      */       }
/*      */     } 
/*      */     
/* 1658 */     this.prevMouthOpenness = this.mouthOpenness;
/*      */     
/* 1660 */     if (getHorseWatchableBoolean(128)) {
/*      */       
/* 1662 */       this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;
/*      */       
/* 1664 */       if (this.mouthOpenness > 1.0F)
/*      */       {
/* 1666 */         this.mouthOpenness = 1.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1671 */       this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;
/*      */       
/* 1673 */       if (this.mouthOpenness < 0.0F)
/*      */       {
/* 1675 */         this.mouthOpenness = 0.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void openHorseMouth() {
/* 1682 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1684 */       this.openMouthCounter = 1;
/* 1685 */       setHorseWatchableBoolean(128, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean canHorseMateAtThisMoment() {
/* 1696 */     return (this.riddenByEntity == null && this.ridingEntity == null && isTame() && isAdultHorse() && !canHorseNeverBreed() && getHealth() >= getMaxHealth());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isShy() {
/* 1702 */     return (isAdultHorse() && !isTame() && getTemper() <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEating(boolean par1) {
/* 1707 */     if (par1 && (this.hurtTime > 0 || this.has_decided_to_flee)) {
/*      */       return;
/*      */     }
/* 1710 */     setHorseWatchableBoolean(32, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEatingHaystack(boolean par1) {
/* 1715 */     if (par1 && onClient()) {
/*      */       
/* 1717 */       Debug.setErrorMessage("setEatingHackstack: set to true on client");
/* 1718 */       Debug.printStackTrace();
/*      */     } 
/*      */     
/* 1721 */     if (par1 && (this.hurtTime > 0 || this.has_decided_to_flee)) {
/*      */       return;
/*      */     }
/* 1724 */     if (par1) {
/*      */       
/* 1726 */       if (isShy()) {
/*      */         
/* 1728 */         List nearby_players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(16.0D, 2.0D, 16.0D));
/*      */         
/* 1730 */         if (!nearby_players.isEmpty()) {
/*      */           return;
/*      */         }
/*      */       } 
/* 1734 */       if (this.tasks.isTaskExecuting(EntityAIAvoidPotentialPredators.class)) {
/*      */         return;
/*      */       }
/* 1737 */       if (this.tasks.isTaskExecuting(EntityAIFleeAttackerOrPanic.class)) {
/*      */         return;
/*      */       }
/* 1740 */       List predators = this.worldObj.getPredatorsWithinAABBForEntity(this, this.boundingBox.expand(16.0D, 2.0D, 16.0D));
/*      */       
/* 1742 */       if (!predators.isEmpty()) {
/*      */         return;
/*      */       }
/*      */     } 
/* 1746 */     setEating(par1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRearing(boolean par1) {
/* 1751 */     if (par1 && (this.hurtTime > 0 || this.has_decided_to_flee)) {
/*      */       return;
/*      */     }
/* 1754 */     if (par1)
/*      */     {
/* 1756 */       setEatingHaystack(false);
/*      */     }
/*      */     
/* 1759 */     setHorseWatchableBoolean(64, par1);
/*      */   }
/*      */ 
/*      */   
/*      */   private void makeHorseRear() {
/* 1764 */     if (this.hurtTime > 0 || this.has_decided_to_flee) {
/*      */       return;
/*      */     }
/* 1767 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1769 */       this.jumpRearingCounter = 1;
/* 1770 */       setRearing(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeHorseRearWithSound() {
/* 1776 */     if (this.hurtTime > 0 || this.has_decided_to_flee) {
/*      */       return;
/*      */     }
/* 1779 */     makeHorseRear();
/* 1780 */     String var1 = getAngrySoundName();
/*      */     
/* 1782 */     if (var1 != null)
/*      */     {
/*      */       
/* 1785 */       makeSound(var1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropChestItems() {
/* 1791 */     dropItemsInChest(this, this.horseChest);
/* 1792 */     dropChests();
/*      */   }
/*      */ 
/*      */   
/*      */   private void dropItemsInChest(Entity par1Entity, AnimalChest par2AnimalChest) {
/* 1797 */     if (par2AnimalChest != null && !this.worldObj.isRemote)
/*      */     {
/* 1799 */       for (int var3 = 0; var3 < par2AnimalChest.getSizeInventory(); var3++) {
/*      */         
/* 1801 */         ItemStack var4 = par2AnimalChest.getStackInSlot(var3);
/*      */         
/* 1803 */         if (var4 != null)
/*      */         {
/* 1805 */           dropItemStack(var4, 0.0F);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setTamedBy(EntityPlayer par1EntityPlayer) {
/* 1813 */     setOwnerName(par1EntityPlayer.getCommandSenderName());
/* 1814 */     setHorseTamed(true);
/* 1815 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntityWithHeading(float par1, float par2) {
/* 1823 */     if (this.riddenByEntity != null && isHorseSaddled()) {
/*      */       
/* 1825 */       this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
/* 1826 */       this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
/* 1827 */       setRotation(this.rotationYaw, this.rotationPitch);
/* 1828 */       this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
/* 1829 */       par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
/* 1830 */       par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;
/*      */       
/* 1832 */       if (par2 <= 0.0F) {
/*      */         
/* 1834 */         par2 *= 0.25F;
/* 1835 */         this.field_110285_bP = 0;
/*      */       } 
/*      */       
/* 1838 */       if (this.onGround && this.jumpPower == 0.0F && isRearing() && !this.field_110294_bI) {
/*      */         
/* 1840 */         par1 = 0.0F;
/* 1841 */         par2 = 0.0F;
/*      */       } 
/*      */       
/* 1844 */       if (this.jumpPower > 0.0F && !isHorseJumping() && this.onGround) {
/*      */         
/* 1846 */         this.motionY = getHorseJumpStrength() * this.jumpPower;
/*      */         
/* 1848 */         if (isPotionActive(Potion.jump))
/*      */         {
/* 1850 */           this.motionY += ((getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
/*      */         }
/*      */         
/* 1853 */         setHorseJumping(true);
/* 1854 */         this.isAirBorne = true;
/*      */         
/* 1856 */         if (par2 > 0.0F) {
/*      */           
/* 1858 */           float var3 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
/* 1859 */           float var4 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/* 1860 */           this.motionX += (-0.4F * var3 * this.jumpPower);
/* 1861 */           this.motionZ += (0.4F * var4 * this.jumpPower);
/* 1862 */           playSound("mob.horse.jump", 0.4F, 1.0F);
/*      */         } 
/*      */         
/* 1865 */         this.jumpPower = 0.0F;
/*      */       } 
/*      */       
/* 1868 */       this.stepHeight = 1.0F;
/* 1869 */       this.jumpMovementFactor = getAIMoveSpeed() * 0.1F;
/*      */       
/* 1871 */       if (!this.worldObj.isRemote) {
/*      */         
/* 1873 */         setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
/* 1874 */         super.moveEntityWithHeading(par1, par2);
/*      */       } 
/*      */       
/* 1877 */       if (this.onGround) {
/*      */         
/* 1879 */         this.jumpPower = 0.0F;
/* 1880 */         setHorseJumping(false);
/*      */       } 
/*      */       
/* 1883 */       this.prevLimbSwingAmount = this.limbSwingAmount;
/* 1884 */       double var8 = this.posX - this.prevPosX;
/* 1885 */       double var5 = this.posZ - this.prevPosZ;
/* 1886 */       float var7 = MathHelper.sqrt_double(var8 * var8 + var5 * var5) * 4.0F;
/*      */       
/* 1888 */       if (var7 > 1.0F)
/*      */       {
/* 1890 */         var7 = 1.0F;
/*      */       }
/*      */       
/* 1893 */       this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
/* 1894 */       this.limbSwing += this.limbSwingAmount;
/*      */     }
/*      */     else {
/*      */       
/* 1898 */       this.stepHeight = 0.5F;
/* 1899 */       this.jumpMovementFactor = 0.02F;
/* 1900 */       super.moveEntityWithHeading(par1, par2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 1909 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 1910 */     par1NBTTagCompound.setBoolean("EatingHaystack", isEatingHaystack());
/* 1911 */     par1NBTTagCompound.setBoolean("ChestedHorse", isChested());
/* 1912 */     par1NBTTagCompound.setBoolean("HasReproduced", getHasReproduced());
/* 1913 */     par1NBTTagCompound.setBoolean("Bred", func_110205_ce());
/* 1914 */     par1NBTTagCompound.setInteger("Type", getHorseType());
/* 1915 */     par1NBTTagCompound.setInteger("Variant", getHorseVariant());
/* 1916 */     par1NBTTagCompound.setInteger("Temper", getTemper());
/* 1917 */     par1NBTTagCompound.setBoolean("Tame", isTame());
/* 1918 */     par1NBTTagCompound.setString("OwnerName", getOwnerName());
/*      */     
/* 1920 */     if (isChested()) {
/*      */       
/* 1922 */       NBTTagList var2 = new NBTTagList();
/*      */       
/* 1924 */       for (int var3 = 2; var3 < this.horseChest.getSizeInventory(); var3++) {
/*      */         
/* 1926 */         ItemStack var4 = this.horseChest.getStackInSlot(var3);
/*      */         
/* 1928 */         if (var4 != null) {
/*      */           
/* 1930 */           NBTTagCompound var5 = new NBTTagCompound();
/* 1931 */           var5.setByte("Slot", (byte)var3);
/* 1932 */           var4.writeToNBT(var5);
/* 1933 */           var2.appendTag(var5);
/*      */         } 
/*      */       } 
/*      */       
/* 1937 */       par1NBTTagCompound.setTag("Items", var2);
/*      */     } 
/*      */     
/* 1940 */     if (this.horseChest.getStackInSlot(1) != null)
/*      */     {
/* 1942 */       par1NBTTagCompound.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound("ArmorItem")));
/*      */     }
/*      */     
/* 1945 */     if (this.horseChest.getStackInSlot(0) != null)
/*      */     {
/* 1947 */       par1NBTTagCompound.setTag("SaddleItem", this.horseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound("SaddleItem")));
/*      */     }
/*      */     
/* 1950 */     if (this.rebellious_for_eating_counter > 0) {
/* 1951 */       par1NBTTagCompound.setShort("rebellious_for_eating_counter", (short)this.rebellious_for_eating_counter);
/*      */     }
/* 1953 */     if (this.rebellious_for_riding_counter > 0) {
/* 1954 */       par1NBTTagCompound.setShort("rebellious_for_riding_counter", (short)this.rebellious_for_riding_counter);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 1962 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 1963 */     setEatingHaystack(par1NBTTagCompound.getBoolean("EatingHaystack"));
/* 1964 */     func_110242_l(par1NBTTagCompound.getBoolean("Bred"));
/* 1965 */     setChested(par1NBTTagCompound.getBoolean("ChestedHorse"));
/* 1966 */     setHasReproduced(par1NBTTagCompound.getBoolean("HasReproduced"));
/* 1967 */     setHorseType(par1NBTTagCompound.getInteger("Type"));
/* 1968 */     setHorseVariant(par1NBTTagCompound.getInteger("Variant"));
/* 1969 */     setTemper(par1NBTTagCompound.getInteger("Temper"));
/* 1970 */     setHorseTamed(par1NBTTagCompound.getBoolean("Tame"));
/*      */     
/* 1972 */     if (par1NBTTagCompound.hasKey("OwnerName"))
/*      */     {
/* 1974 */       setOwnerName(par1NBTTagCompound.getString("OwnerName"));
/*      */     }
/*      */     
/* 1977 */     AttributeInstance var2 = getAttributeMap().getAttributeInstanceByName("Speed");
/*      */     
/* 1979 */     if (var2 != null)
/*      */     {
/*      */       
/* 1982 */       setEntityAttribute(SharedMonsterAttributes.movementSpeed, var2.getBaseValue() * 0.25D);
/*      */     }
/*      */     
/* 1985 */     if (isChested()) {
/*      */       
/* 1987 */       NBTTagList var3 = par1NBTTagCompound.getTagList("Items");
/* 1988 */       func_110226_cD();
/*      */       
/* 1990 */       for (int var4 = 0; var4 < var3.tagCount(); var4++) {
/*      */         
/* 1992 */         NBTTagCompound var5 = (NBTTagCompound)var3.tagAt(var4);
/* 1993 */         int var6 = var5.getByte("Slot") & 0xFF;
/*      */         
/* 1995 */         if (var6 >= 2 && var6 < this.horseChest.getSizeInventory())
/*      */         {
/* 1997 */           this.horseChest.setInventorySlotContents(var6, ItemStack.loadItemStackFromNBT(var5));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2004 */     if (par1NBTTagCompound.hasKey("ArmorItem")) {
/*      */       
/* 2006 */       ItemStack var7 = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("ArmorItem"));
/*      */       
/* 2008 */       if (var7 != null && func_110211_v(var7.itemID))
/*      */       {
/* 2010 */         this.horseChest.setInventorySlotContents(1, var7);
/*      */       }
/*      */     } 
/*      */     
/* 2014 */     if (par1NBTTagCompound.hasKey("SaddleItem")) {
/*      */       
/* 2016 */       ItemStack var7 = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("SaddleItem"));
/*      */       
/* 2018 */       if (var7 != null && var7.itemID == Item.saddle.itemID)
/*      */       {
/* 2020 */         this.horseChest.setInventorySlotContents(0, var7);
/*      */       }
/*      */     }
/* 2023 */     else if (par1NBTTagCompound.getBoolean("Saddle")) {
/*      */       
/* 2025 */       this.horseChest.setInventorySlotContents(0, new ItemStack(Item.saddle));
/*      */     } 
/*      */     
/* 2028 */     func_110232_cE();
/*      */     
/* 2030 */     setRebelliousForEatingCounter(par1NBTTagCompound.getShort("rebellious_for_eating_counter"));
/* 2031 */     setRebelliousForRidingCounter(par1NBTTagCompound.getShort("rebellious_for_riding_counter"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(EntityAnimal par1EntityAnimal) {
/* 2039 */     if (par1EntityAnimal == this)
/*      */     {
/* 2041 */       return false;
/*      */     }
/* 2043 */     if (par1EntityAnimal.getClass() != getClass())
/*      */     {
/* 2045 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2049 */     EntityHorse var2 = (EntityHorse)par1EntityAnimal;
/*      */ 
/*      */     
/* 2052 */     if (canHorseMateAtThisMoment() && var2.canHorseMateAtThisMoment()) {
/*      */       
/* 2054 */       int var3 = getHorseType();
/* 2055 */       int var4 = var2.getHorseType();
/* 2056 */       return (var3 == var4 || (var3 == 0 && var4 == 1) || (var3 == 1 && var4 == 0));
/*      */     } 
/*      */ 
/*      */     
/* 2060 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 2067 */     EntityHorse var2 = (EntityHorse)par1EntityAgeable;
/* 2068 */     EntityHorse var3 = new EntityHorse(this.worldObj);
/* 2069 */     int var4 = getHorseType();
/* 2070 */     int var5 = var2.getHorseType();
/* 2071 */     int var6 = 0;
/*      */     
/* 2073 */     if (var4 == var5) {
/*      */       
/* 2075 */       var6 = var4;
/*      */     }
/* 2077 */     else if ((var4 == 0 && var5 == 1) || (var4 == 1 && var5 == 0)) {
/*      */       
/* 2079 */       var6 = 2;
/*      */     } 
/*      */     
/* 2082 */     if (var6 == 0) {
/*      */       
/* 2084 */       int var7, var8 = this.rand.nextInt(9);
/*      */ 
/*      */       
/* 2087 */       if (var8 < 4) {
/*      */         
/* 2089 */         var7 = getHorseVariant() & 0xFF;
/*      */       }
/* 2091 */       else if (var8 < 8) {
/*      */         
/* 2093 */         var7 = var2.getHorseVariant() & 0xFF;
/*      */       }
/*      */       else {
/*      */         
/* 2097 */         var7 = this.rand.nextInt(7);
/*      */       } 
/*      */       
/* 2100 */       int var9 = this.rand.nextInt(5);
/*      */       
/* 2102 */       if (var9 < 4) {
/*      */         
/* 2104 */         var7 |= getHorseVariant() & 0xFF00;
/*      */       }
/* 2106 */       else if (var9 < 8) {
/*      */         
/* 2108 */         var7 |= var2.getHorseVariant() & 0xFF00;
/*      */       }
/*      */       else {
/*      */         
/* 2112 */         var7 |= this.rand.nextInt(5) << 8 & 0xFF00;
/*      */       } 
/*      */       
/* 2115 */       var3.setHorseVariant(var7);
/*      */     } 
/*      */     
/* 2118 */     var3.setHorseType(var6);
/* 2119 */     double var14 = getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + par1EntityAgeable.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + func_110267_cL();
/*      */     
/* 2121 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, var14 / 3.0D);
/* 2122 */     double var13 = getEntityAttribute(horseJumpStrength).getBaseValue() + par1EntityAgeable.getEntityAttribute(horseJumpStrength).getBaseValue() + func_110245_cM();
/*      */     
/* 2124 */     setEntityAttribute(horseJumpStrength, var13 / 3.0D);
/* 2125 */     double var11 = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + par1EntityAgeable.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + func_110203_cN();
/*      */     
/* 2127 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, var11 / 3.0D);
/* 2128 */     return var3;
/*      */   }
/*      */   
/*      */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/*      */     int var7;
/* 2133 */     Object par1EntityLivingData1 = super.onSpawnWithEgg(par1EntityLivingData);
/* 2134 */     boolean var2 = false;
/* 2135 */     int var3 = 0;
/*      */ 
/*      */     
/* 2138 */     if (par1EntityLivingData1 instanceof EntityHorseGroupData) {
/*      */       
/* 2140 */       var7 = ((EntityHorseGroupData)par1EntityLivingData1).field_111107_a;
/* 2141 */       var3 = ((EntityHorseGroupData)par1EntityLivingData1).field_111106_b & 0xFF | this.rand.nextInt(5) << 8;
/*      */     }
/*      */     else {
/*      */       
/* 2145 */       if (this.rand.nextInt(10) == 0) {
/*      */         
/* 2147 */         var7 = 1;
/*      */       }
/*      */       else {
/*      */         
/* 2151 */         int var4 = this.rand.nextInt(7);
/* 2152 */         int var5 = this.rand.nextInt(5);
/* 2153 */         var7 = 0;
/* 2154 */         var3 = var4 | var5 << 8;
/*      */       } 
/*      */       
/* 2157 */       par1EntityLivingData1 = new EntityHorseGroupData(var7, var3);
/*      */     } 
/*      */     
/* 2160 */     setHorseType(var7);
/* 2161 */     setHorseVariant(var3);
/*      */     
/* 2163 */     if (this.rand.nextInt(5) == 0)
/*      */     {
/*      */       
/* 2166 */       setGrowingAgeToNewborn();
/*      */     }
/*      */     
/* 2169 */     if (var7 != 4 && var7 != 3) {
/*      */ 
/*      */       
/* 2172 */       setEntityAttribute(SharedMonsterAttributes.maxHealth, func_110267_cL());
/*      */       
/* 2174 */       if (var7 == 0)
/*      */       {
/*      */         
/* 2177 */         setEntityAttribute(SharedMonsterAttributes.movementSpeed, func_110203_cN());
/*      */       
/*      */       }
/*      */       else
/*      */       {
/* 2182 */         setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.17499999701976776D);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2188 */       setEntityAttribute(SharedMonsterAttributes.maxHealth, 15.0D);
/*      */       
/* 2190 */       setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.20000000298023224D);
/*      */     } 
/*      */     
/* 2193 */     if (var7 != 2 && var7 != 1) {
/*      */ 
/*      */       
/* 2196 */       setEntityAttribute(horseJumpStrength, func_110245_cM());
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2201 */       setEntityAttribute(horseJumpStrength, 0.5D);
/*      */     } 
/*      */     
/* 2204 */     setHealth(getMaxHealth());
/* 2205 */     return (EntityLivingData)par1EntityLivingData1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getGrassEatingAmount(float par1) {
/* 2210 */     return this.prevHeadLean + (this.headLean - this.prevHeadLean) * par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRearingAmount(float par1) {
/* 2215 */     return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * par1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float func_110201_q(float par1) {
/* 2220 */     return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * par1;
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
/*      */   public void setJumpPower(int par1) {
/* 2233 */     if (isHorseSaddled()) {
/*      */       
/* 2235 */       if (par1 < 0) {
/*      */         
/* 2237 */         par1 = 0;
/*      */       }
/*      */       else {
/*      */         
/* 2241 */         this.field_110294_bI = true;
/* 2242 */         makeHorseRear();
/*      */       } 
/*      */       
/* 2245 */       if (par1 >= 90) {
/*      */         
/* 2247 */         this.jumpPower = 1.0F;
/*      */       }
/*      */       else {
/*      */         
/* 2251 */         this.jumpPower = 0.4F + 0.4F * par1 / 90.0F;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void spawnHorseParticles(boolean par1) {
/* 2262 */     EnumParticle var2 = par1 ? EnumParticle.heart : EnumParticle.smoke;
/*      */     
/* 2264 */     for (int var3 = 0; var3 < 7; var3++) {
/*      */       
/* 2266 */       double var4 = this.rand.nextGaussian() * 0.02D;
/* 2267 */       double var6 = this.rand.nextGaussian() * 0.02D;
/* 2268 */       double var8 = this.rand.nextGaussian() * 0.02D;
/*      */       
/* 2270 */       this.worldObj.spawnParticle(var2, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var4, var6, var8);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(EnumEntityState par1) {
/* 2278 */     if (par1 == EnumEntityState.tame_success) {
/*      */       
/* 2280 */       spawnHorseParticles(true);
/*      */     
/*      */     }
/* 2283 */     else if (par1 == EnumEntityState.tame_failure) {
/*      */       
/* 2285 */       spawnHorseParticles(false);
/*      */     }
/*      */     else {
/*      */       
/* 2289 */       super.handleHealthUpdate(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateRiderPosition() {
/* 2295 */     super.updateRiderPosition();
/*      */     
/* 2297 */     if (this.prevRearingAmount > 0.0F) {
/*      */       
/* 2299 */       float var1 = MathHelper.sin(this.renderYawOffset * 3.1415927F / 180.0F);
/* 2300 */       float var2 = MathHelper.cos(this.renderYawOffset * 3.1415927F / 180.0F);
/* 2301 */       float var3 = 0.7F * this.prevRearingAmount;
/* 2302 */       float var4 = 0.15F * this.prevRearingAmount;
/* 2303 */       this.riddenByEntity.setPosition(this.posX + (var3 * var1), this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset() + var4, this.posZ - (var3 * var2));
/*      */       
/* 2305 */       if (this.riddenByEntity instanceof EntityLivingBase)
/*      */       {
/* 2307 */         ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private float func_110267_cL() {
/* 2314 */     return 15.0F + this.rand.nextInt(8) + this.rand.nextInt(9);
/*      */   }
/*      */ 
/*      */   
/*      */   private double func_110245_cM() {
/* 2319 */     return 0.4000000059604645D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D;
/*      */   }
/*      */ 
/*      */   
/*      */   private double func_110203_cN() {
/* 2324 */     return (0.44999998807907104D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D) * 0.25D;
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
/*      */   public static boolean func_110211_v(int par0) {
/* 2340 */     return Item.getItem(par0) instanceof ItemHorseArmor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnLadder() {
/* 2348 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean considerFleeing() {
/* 2353 */     Entity last_attacking_entity = getLastHarmingEntity();
/* 2354 */     this.has_decided_to_flee = (last_attacking_entity != null && getDistanceToEntity(last_attacking_entity) < 32.0F);
/*      */     
/* 2356 */     return this.has_decided_to_flee;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean considerStopFleeing() {
/* 2361 */     Entity last_attacking_entity = getLastHarmingEntity();
/*      */     
/* 2363 */     if (last_attacking_entity == null) {
/*      */       
/* 2365 */       this.has_decided_to_flee = false;
/* 2366 */       this.fleeing = false;
/* 2367 */       return true;
/*      */     } 
/*      */     
/* 2370 */     if (getDistanceToEntity(last_attacking_entity) > 40.0F) {
/*      */       
/* 2372 */       this.fleeing = false;
/* 2373 */       return true;
/*      */     } 
/*      */     
/* 2376 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAIMoveSpeed() {
/*      */     float speed;
/* 2383 */     if (this.riddenByEntity != null) {
/* 2384 */       return super.getAIMoveSpeed();
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
/* 2407 */     int type = getHorseType();
/*      */ 
/*      */ 
/*      */     
/* 2411 */     if (type == 0) {
/* 2412 */       speed = 0.5F;
/* 2413 */     } else if (type == 1) {
/* 2414 */       speed = 0.4F;
/* 2415 */     } else if (type == 2) {
/* 2416 */       speed = 0.4F;
/* 2417 */     } else if (type == 3) {
/* 2418 */       speed = 0.3F;
/* 2419 */     } else if (type == 4) {
/* 2420 */       speed = 0.5F;
/*      */     } else {
/* 2422 */       speed = 0.0F;
/*      */     } 
/* 2424 */     if (speed == 0.0F) {
/* 2425 */       Debug.setErrorMessage("getAIMoveSpeed: unrecognized horse type " + type);
/*      */     }
/* 2427 */     return isChild() ? (speed * 0.75F) : speed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHarmedByFire() {
/* 2434 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHarmedByLava() {
/* 2439 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExperienceValue() {
/* 2444 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void modifyEffectiveCollisionBoxForRaycastFromEntity(AxisAlignedBB effective_collision_box, Entity entity) {
/* 2449 */     if (entity == this.riddenByEntity)
/* 2450 */       effective_collision_box.scale(0.5D); 
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityHorse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */