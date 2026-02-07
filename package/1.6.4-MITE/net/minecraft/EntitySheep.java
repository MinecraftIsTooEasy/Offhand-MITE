/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class EntitySheep
/*     */   extends EntityLivestock
/*     */ {
/*   8 */   private final InventoryCrafting field_90016_e = new InventoryCrafting(new ContainerSheep(this), 2, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  13 */   public static final float[][] fleeceColorTable = new float[][] { { 1.0F, 1.0F, 1.0F }, { 0.85F, 0.5F, 0.2F }, { 0.7F, 0.3F, 0.85F }, { 0.4F, 0.6F, 0.85F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.5F, 0.65F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.5F, 0.6F }, { 0.5F, 0.25F, 0.7F }, { 0.2F, 0.3F, 0.7F }, { 0.4F, 0.3F, 0.2F }, { 0.4F, 0.5F, 0.2F }, { 0.6F, 0.2F, 0.2F }, { 0.1F, 0.1F, 0.1F } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int sheepTimer;
/*     */ 
/*     */ 
/*     */   
/*  22 */   private EntityAIEatGrass aiEatGrass = new EntityAIEatGrass(this);
/*     */   
/*     */   private int fire_damage_to_wool;
/*     */ 
/*     */   
/*     */   public EntitySheep(World par1World) {
/*  28 */     super(par1World);
/*  29 */     setSize(0.9F, 1.3F);
/*  30 */     getNavigator().setAvoidsWater(true);
/*  31 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*     */ 
/*     */     
/*  34 */     this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
/*  35 */     this.tasks.addTask(3, new EntityAITempt(this, 1.1D, Item.wheat.itemID, false));
/*  36 */     this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
/*  37 */     this.tasks.addTask(5, this.aiEatGrass);
/*  38 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  39 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  40 */     this.tasks.addTask(8, new EntityAILookIdle(this));
/*  41 */     this.field_90016_e.setInventorySlotContents(0, new ItemStack(Item.dyePowder, 1, 0));
/*  42 */     this.field_90016_e.setInventorySlotContents(1, new ItemStack(Item.dyePowder, 1, 0));
/*     */ 
/*     */     
/*  45 */     this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.4F, 0.5F, true));
/*     */     
/*  47 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*  48 */       setManurePeriod(getManurePeriod() * 2);
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
/*     */   protected void updateAITasks() {
/*  61 */     this.sheepTimer = this.aiEatGrass.getEatGrassTick();
/*  62 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  71 */     if (this.worldObj.isRemote)
/*     */     {
/*  73 */       this.sheepTimer = Math.max(0, this.sheepTimer - 1);
/*     */     }
/*     */     
/*  76 */     if (this.fire_damage_to_wool > 0 && onServer() && !isBurning() && this.ticksExisted % 100 == 0) {
/*  77 */       this.fire_damage_to_wool--;
/*     */     }
/*  79 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEntityDamaged(DamageSource damage_source, float amount) {
/*  84 */     if (damage_source.isFireDamage() && !getSheared()) {
/*     */       
/*  86 */       if (++this.fire_damage_to_wool > 5)
/*     */       {
/*  88 */         setSheared(true);
/*  89 */         this.fire_damage_to_wool = 0;
/*     */         
/*  91 */         extinguish();
/*     */       }
/*     */     
/*  94 */     } else if (!getSheared()) {
/*     */       
/*  96 */       if (damage_source.getResponsibleEntity() instanceof EntityGelatinousCube) {
/*     */         
/*  98 */         setSheared(true);
/*  99 */         entityFX(EnumEntityFX.steam_with_hiss);
/*     */       }
/* 101 */       else if (damage_source.isGelatinousSphereDamage()) {
/*     */         
/* 103 */         setSheared(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 110 */     super.applyEntityAttributes();
/* 111 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
/* 112 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 117 */     super.entityInit();
/* 118 */     this.dataWatcher.addObject(16, new Byte((byte)0));
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 160 */     if (!getSheared() && !isBurning() && this.rand.nextInt(2) == 0) {
/* 161 */       dropItemStack(new ItemStack(Block.cloth.blockID, 1, getFleeceColor()));
/*     */     }
/* 163 */     if (isWell()) {
/*     */       
/* 165 */       int num_drops = this.rand.nextInt(2) + this.rand.nextInt(1 + damage_source.getButcheringModifier());
/*     */       
/* 167 */       if (num_drops < 1) {
/* 168 */         num_drops = 1;
/*     */       }
/* 170 */       for (int i = 0; i < num_drops; i++) {
/* 171 */         dropItem(isBurning() ? Item.lambchopCooked.itemID : Item.lambchopRaw.itemID, 1);
/*     */       }
/*     */     } 
/* 174 */     if (this.rand.nextInt(2) == 0) {
/* 175 */       dropItem(Item.leather.itemID, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 183 */     return Block.cloth.blockID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 190 */     if (par1 == EnumEntityState.tnt_ignite_or_eating_grass) {
/*     */       
/* 192 */       this.sheepTimer = 40;
/*     */     }
/*     */     else {
/*     */       
/* 196 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_70894_j(float par1) {
/* 202 */     return (this.sheepTimer <= 0) ? 0.0F : ((this.sheepTimer >= 4 && this.sheepTimer <= 36) ? 1.0F : ((this.sheepTimer < 4) ? ((this.sheepTimer - par1) / 4.0F) : (-((this.sheepTimer - 40) - par1) / 4.0F)));
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_70890_k(float par1) {
/* 207 */     if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
/*     */       
/* 209 */       float var2 = ((this.sheepTimer - 4) - par1) / 32.0F;
/* 210 */       return 0.62831855F + 0.2199115F * MathHelper.sin(var2 * 28.7F);
/*     */     } 
/*     */ 
/*     */     
/* 214 */     return (this.sheepTimer > 0) ? 0.62831855F : (this.rotationPitch / 57.295776F);
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
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 255 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 256 */     par1NBTTagCompound.setBoolean("Sheared", getSheared());
/* 257 */     par1NBTTagCompound.setByte("Color", (byte)getFleeceColor());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 265 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 266 */     setSheared(par1NBTTagCompound.getBoolean("Sheared"));
/* 267 */     setFleeceColor(par1NBTTagCompound.getByte("Color"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 275 */     return "mob.sheep.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 283 */     return "mob.sheep.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 291 */     return "mob.sheep.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 300 */     makeSound("mob.sheep.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFleeceColor() {
/* 305 */     return this.dataWatcher.getWatchableObjectByte(16) & 0xF;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFleeceColor(int par1) {
/* 310 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/* 311 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xF0 | par1 & 0xF)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSheared() {
/* 319 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x10) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSheared(boolean par1) {
/* 327 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 329 */     if (par1) {
/*     */       
/* 331 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x10)));
/*     */     }
/*     */     else {
/*     */       
/* 335 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFEF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRandomFleeceColor(Random par0Random) {
/* 344 */     int var1 = par0Random.nextInt(100);
/* 345 */     return (var1 < 5) ? 15 : ((var1 < 10) ? 7 : ((var1 < 15) ? 8 : ((var1 < 18) ? 12 : ((par0Random.nextInt(500) == 0) ? 6 : 0))));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySheep func_90015_b(EntityAgeable par1EntityAgeable) {
/* 350 */     EntitySheep var2 = (EntitySheep)par1EntityAgeable;
/* 351 */     EntitySheep var3 = new EntitySheep(this.worldObj);
/* 352 */     int var4 = func_90014_a(this, var2);
/* 353 */     var3.setFleeceColor(15 - var4);
/* 354 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eatGrassBonus() {
/* 363 */     setSheared(false);
/*     */     
/* 365 */     if (isChild())
/*     */     {
/* 367 */       addGrowth(60);
/*     */     }
/*     */     
/* 370 */     addFood(0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 375 */     par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
/* 376 */     setFleeceColor(getRandomFleeceColor(this.worldObj.rand));
/* 377 */     return par1EntityLivingData;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_90014_a(EntityAnimal par1EntityAnimal, EntityAnimal par2EntityAnimal) {
/* 382 */     int var6, var3 = func_90013_b(par1EntityAnimal);
/* 383 */     int var4 = func_90013_b(par2EntityAnimal);
/* 384 */     this.field_90016_e.getStackInSlot(0).setItemSubtype(var3);
/* 385 */     this.field_90016_e.getStackInSlot(1).setItemSubtype(var4);
/*     */ 
/*     */     
/* 388 */     CraftingResult crafting_result = CraftingManager.getInstance().findMatchingRecipe(this.field_90016_e, ((EntitySheep)par1EntityAnimal).worldObj, null);
/* 389 */     ItemStack var5 = (crafting_result == null) ? null : crafting_result.item_stack;
/*     */ 
/*     */ 
/*     */     
/* 393 */     if (var5 != null && (var5.getItem()).itemID == Item.dyePowder.itemID) {
/*     */       
/* 395 */       var6 = var5.getItemSubtype();
/*     */     }
/*     */     else {
/*     */       
/* 399 */       var6 = this.worldObj.rand.nextBoolean() ? var3 : var4;
/*     */     } 
/*     */     
/* 402 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_90013_b(EntityAnimal par1EntityAnimal) {
/* 407 */     return 15 - ((EntitySheep)par1EntityAnimal).getFleeceColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 412 */     return func_90015_b(par1EntityAgeable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void produceGoods() {
/* 417 */     this.production_counter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 422 */     return (item_stack != null && item_stack.getItem() == Item.wheat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryDyeing(ItemStack item_stack) {
/* 427 */     if (item_stack == null || item_stack.getItem() != Item.dyePowder) {
/* 428 */       return false;
/*     */     }
/* 430 */     int color = BlockColored.getBlockFromDye(item_stack.getItemSubtype());
/*     */     
/* 432 */     if (!getSheared() && getFleeceColor() != color) {
/*     */       
/* 434 */       if (onServer()) {
/* 435 */         setFleeceColor(color);
/*     */       }
/* 437 */       return true;
/*     */     } 
/*     */     
/* 440 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySheep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */