/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityLivestock
/*     */   extends EntityAnimal
/*     */ {
/*     */   private float food;
/*     */   private float water;
/*     */   private float freedom;
/*     */   protected int data_object_id_is_well;
/*     */   protected int data_object_id_is_thirsty;
/*     */   protected int production_counter;
/*     */   private int manure_period;
/*     */   private int manure_countdown;
/*     */   public int last_trampled_x;
/*     */   public int last_trampled_y;
/*     */   public int last_trampled_z;
/*     */   public boolean has_been_spooked_by_other_animal;
/*     */   
/*     */   public EntityLivestock(World world) {
/*  27 */     super(world);
/*     */     
/*  29 */     this.tasks.addTask(2, new EntityAISeekFoodIfHungry(this, 1.0F, true));
/*  30 */     this.tasks.addTask(2, new EntityAISeekWaterIfThirsty(this, 1.0F, false));
/*  31 */     this.tasks.addTask(3, new EntityAISeekOpenSpaceIfCrowded(this, 1.0F));
/*     */     
/*  33 */     this.tasks.addTask(2, new EntityAIAvoidPotentialPredators(this, 1.0F, true));
/*  34 */     this.tasks.addTask(4, new EntityAISeekShelterFromRain(this, 1.0F, true));
/*     */     
/*  36 */     this.tasks.addTask(4, new EntityAIGetOutOfWater(this, 1.0F));
/*     */     
/*  38 */     if (world != null && !world.isRemote) {
/*     */       
/*  40 */       setFood(0.8F + this.rand.nextFloat() * 0.2F);
/*  41 */       setWater(0.8F + this.rand.nextFloat() * 0.2F);
/*  42 */       setFreedom(0.8F + this.rand.nextFloat() * 0.2F);
/*     */       
/*  44 */       setManurePeriod(24000);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  52 */     super.entityInit();
/*     */     
/*  54 */     this.data_object_id_is_well = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)-1));
/*  55 */     this.data_object_id_is_thirsty = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setFood(float food) {
/*  60 */     if (this.worldObj.isRemote) {
/*     */       return;
/*     */     }
/*  63 */     this.food = MathHelper.clamp_float(food, 0.0F, 1.0F);
/*  64 */     setIsWell(isWell());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addFood(float food) {
/*  69 */     setFood(getFood() + food);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getFood() {
/*  74 */     return this.food;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setWater(float water) {
/*  79 */     if (this.worldObj.isRemote) {
/*     */       return;
/*     */     }
/*  82 */     this.water = MathHelper.clamp_float(water, 0.0F, 1.0F);
/*  83 */     setIsWell(isWell());
/*  84 */     setIsThirsty(isThirsty());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addWater(float water) {
/*  89 */     setWater(getWater() + water);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getWater() {
/*  94 */     return this.water;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setFreedom(float freedom) {
/*  99 */     if (this.worldObj.isRemote) {
/*     */       return;
/*     */     }
/* 102 */     this.freedom = MathHelper.clamp_float(freedom, 0.0F, 1.0F);
/* 103 */     setIsWell(isWell());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addFreedom(float freedom) {
/* 108 */     setFreedom(getFreedom() + freedom);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getFreedom() {
/* 113 */     return this.freedom;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 118 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/* 120 */     par1NBTTagCompound.setFloat("food", getFood());
/* 121 */     par1NBTTagCompound.setFloat("water", getWater());
/* 122 */     par1NBTTagCompound.setFloat("freedom", getFreedom());
/*     */     
/* 124 */     par1NBTTagCompound.setInteger("production_counter", this.production_counter);
/* 125 */     par1NBTTagCompound.setInteger("manure_countdown", this.manure_countdown);
/*     */     
/* 127 */     par1NBTTagCompound.setBoolean("has_been_spooked_by_other_animal", this.has_been_spooked_by_other_animal);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 132 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 134 */     setFood(par1NBTTagCompound.getFloat("food"));
/* 135 */     setWater(par1NBTTagCompound.getFloat("water"));
/* 136 */     setFreedom(par1NBTTagCompound.getFloat("freedom"));
/*     */     
/* 138 */     this.production_counter = par1NBTTagCompound.getInteger("production_counter");
/* 139 */     this.manure_countdown = par1NBTTagCompound.getInteger("manure_countdown");
/*     */     
/* 141 */     this.has_been_spooked_by_other_animal = par1NBTTagCompound.getBoolean("has_been_spooked_by_other_animal");
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
/*     */   protected boolean setIsWell(boolean is_well) {
/* 156 */     this.dataWatcher.updateObject(this.data_object_id_is_well, Byte.valueOf((byte)(is_well ? -1 : 0)));
/* 157 */     return is_well;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWell() {
/* 162 */     if (this.worldObj.isRemote) {
/* 163 */       return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_well) != 0);
/*     */     }
/* 165 */     return (Math.min(getFreedom(), Math.min(getFood(), getWater())) >= 0.25F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean setIsThirsty(boolean is_thirsty) {
/* 170 */     this.dataWatcher.updateObject(this.data_object_id_is_thirsty, Byte.valueOf((byte)(is_thirsty ? -1 : 0)));
/* 171 */     return is_thirsty;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isThirsty() {
/* 176 */     if (this.worldObj.isRemote) {
/* 177 */       return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_thirsty) != 0);
/*     */     }
/* 179 */     return (getWater() < 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHungry() {
/* 184 */     return (getFood() < 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVeryHungry() {
/* 189 */     return (getFood() < 0.25F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDesperateForFood() {
/* 194 */     return (getFood() < 0.05F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVeryThirsty() {
/* 204 */     return (getWater() < 0.25F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDesperateForWater() {
/* 209 */     return (getWater() < 0.05F);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isNearFood() {
/* 214 */     return isNearFood(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isNearFood(float chance_of_destroying_food) {
/* 219 */     return isNearFood(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), chance_of_destroying_food);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isNearFood(int x, int y, int z) {
/* 224 */     return isNearFood(x, y, z, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isNearFood(int x, int y, int z, float chance_of_destroying_food) {
/* 229 */     int height = MathHelper.floor_double(this.height);
/*     */     
/* 231 */     for (int dx = -1; dx <= 1; dx++) {
/*     */       
/* 233 */       for (int dy = -1; dy <= height; dy++) {
/*     */         
/* 235 */         for (int dz = -1; dz <= 1; dz++) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 240 */           if (isFoodSource(this.worldObj.getBlockId(x + dx, y + dy, z + dz))) {
/*     */             
/* 242 */             if (chance_of_destroying_food > 0.0F && this.rand.nextFloat() < chance_of_destroying_food) {
/* 243 */               this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, x + dx, y + dy, z + dz)).setEatenBy(this), false);
/*     */             }
/* 245 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNearWaterSource() {
/* 256 */     return isNearWaterSource(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNearWaterSource(int x, int y, int z) {
/* 261 */     int height = MathHelper.floor_double(this.height);
/*     */     
/* 263 */     for (int dx = -1; dx <= 1; dx++) {
/*     */       
/* 265 */       for (int dy = -1; dy <= height; dy++) {
/*     */         
/* 267 */         for (int dz = -1; dz <= 1; dz++) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 272 */           if (isWaterSource(x + dx, y + dy, z + dz) && !this.worldObj.isBlockFaceFlatAndSolid(x + dx, y + dy + 1, z + dz, EnumFace.BOTTOM)) {
/* 273 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 278 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrowded() {
/* 283 */     return (!isOutdoors() || this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.boundingBox.expand(2.0D, 0.5D, 2.0D)).size() > 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCrowded(int x, int y, int z) {
/* 288 */     if (!this.worldObj.isOutdoors(x, y, z)) {
/* 289 */       return true;
/*     */     }
/* 291 */     AxisAlignedBB bounding_box = new AxisAlignedBB((x - 2), (y - 0.5F), (z - 2), (x + 2), (y + 0.5F), (z + 2));
/* 292 */     return (this.worldObj.getEntitiesWithinAABB(EntityLiving.class, bounding_box).size() > 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updateWellness() {
/* 298 */     float benefit = 0.1F;
/* 299 */     float penalty = -0.005F;
/*     */ 
/*     */     
/* 302 */     if (isNearFood((this instanceof EntityCow) ? 0.01F : 0.0F)) {
/* 303 */       addFood(benefit);
/*     */     } else {
/* 305 */       addFood(penalty);
/*     */     } 
/* 307 */     if (isNearWaterSource()) {
/* 308 */       addWater(benefit);
/* 309 */     } else if (isInRain()) {
/* 310 */       addWater(benefit / 10.0F);
/*     */     } else {
/* 312 */       addWater(penalty);
/*     */     } 
/* 314 */     if (!isCrowded()) {
/* 315 */       addFreedom(benefit);
/*     */     } else {
/* 317 */       addFreedom(penalty);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     return isWell();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 330 */     super.onLivingUpdate();
/*     */     
/* 332 */     if (!this.worldObj.isRemote) {
/*     */       
/* 334 */       if (this.ticksExisted % 100 == 0) {
/*     */         
/* 336 */         if (this.rand.nextInt(10) > 0 && updateWellness() && !isChild()) {
/* 337 */           this.production_counter++;
/*     */         }
/* 339 */         produceGoods();
/*     */       } 
/*     */       
/* 342 */       if (!isChild()) {
/*     */         
/* 344 */         if (!isDesperateForFood() && --this.manure_countdown <= 0) {
/*     */           
/* 346 */           dropItem(Item.manure.itemID, 1);
/* 347 */           this.manure_countdown = this.manure_period / 2 + this.rand.nextInt(this.manure_period);
/*     */         } 
/*     */         
/* 350 */         if (this.onGround) {
/*     */           
/* 352 */           if (this.ticksExisted % 1000 == 0) {
/* 353 */             this.last_trampled_x = this.last_trampled_y = this.last_trampled_z = 0;
/*     */           }
/* 355 */           int x = MathHelper.floor_double(this.posX);
/* 356 */           int y = MathHelper.floor_double(this.posY) - 1;
/* 357 */           int z = MathHelper.floor_double(this.posZ);
/*     */           
/* 359 */           if (x != this.last_trampled_x || y != this.last_trampled_y || z != this.last_trampled_z) {
/*     */             
/* 361 */             this.last_trampled_x = x;
/* 362 */             this.last_trampled_y = y;
/* 363 */             this.last_trampled_z = z;
/*     */             
/* 365 */             Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
/*     */             
/* 367 */             if (block != null) {
/* 368 */               block.onTrampledBy(this.worldObj, x, y, z, this);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 373 */       if (this.has_been_spooked_by_other_animal && this.worldObj.total_time % 4000L == 0L) {
/* 374 */         this.has_been_spooked_by_other_animal = false;
/*     */       }
/* 376 */       if ((this.has_decided_to_flee || this.fleeing || isSpooked()) && getTicksExistedWithOffset() % 20 == 0) {
/*     */         
/* 378 */         List list = this.worldObj.getEntitiesWithinAABB(EntityLivestock.class, this.boundingBox.expand(8.0D, 4.0D, 8.0D));
/*     */         
/* 380 */         Iterator<EntityLivestock> i = list.iterator();
/*     */         
/* 382 */         while (i.hasNext()) {
/*     */           
/* 384 */           EntityLivestock livestock = i.next();
/*     */           
/* 386 */           if (livestock == this || livestock.has_decided_to_flee || livestock.fleeing || livestock.isSpooked() || livestock.has_been_spooked_by_other_animal || livestock.isDead) {
/*     */             continue;
/*     */           }
/* 389 */           if (livestock.canSeeEntity(this) && livestock.canPathTo(getBlockPosX(), getFootBlockPosY(), getBlockPosZ(), 8)) {
/*     */             
/* 391 */             livestock.spooked_until = this.worldObj.getTotalWorldTime() + 400L + this.worldObj.rand.nextInt(400);
/* 392 */             livestock.has_been_spooked_by_other_animal = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void produceGoods();
/*     */   
/*     */   protected boolean isFoodSource(int block_id) {
/* 403 */     Block block = Block.blocksList[block_id];
/*     */     
/* 405 */     return (block == Block.grass || block == Block.tallGrass);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getFoodBlockIDs() {
/* 410 */     int[] block_ids = { Block.grass.blockID, Block.tallGrass.blockID };
/* 411 */     return block_ids;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isWaterSource(int x, int y, int z) {
/* 416 */     Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
/*     */     
/* 418 */     if (block == Block.waterStill || block == Block.waterMoving || block == Block.snow || block == Block.blockSnow) {
/* 419 */       return true;
/*     */     }
/* 421 */     if (block == Block.cauldron && (y + 1) < getEyePosY() && this.worldObj.getBlockMetadata(x, y, z) > 0) {
/* 422 */       return true;
/*     */     }
/* 424 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getWaterBlockIDs() {
/* 429 */     int[] block_ids = { Block.waterStill.blockID, Block.waterMoving.blockID, Block.snow.blockID, Block.blockSnow.blockID, Block.cauldron.blockID };
/* 430 */     return block_ids;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int x, int y, int z) {
/* 435 */     int block_id = this.worldObj.getBlockId(x, y, z);
/* 436 */     int block_id_below = this.worldObj.getBlockId(x, y - 1, z);
/*     */     
/* 438 */     if (isFoodSource(block_id) || isFoodSource(block_id_below) || isNearFood(x, y, z)) {
/* 439 */       return 20.0F;
/*     */     }
/* 441 */     if (!isWaterSource(x, y, z) && !isWaterSource(x, y - 1, z) && isNearWaterSource(x, y, z)) {
/* 442 */       return 20.0F;
/*     */     }
/* 444 */     return super.getBlockPathWeight(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onFoodEaten(ItemStack item_stack) {
/* 454 */     if (!this.worldObj.isRemote) {
/* 455 */       addFood(0.5F);
/*     */     }
/* 457 */     super.onFoodEaten(item_stack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_110196_bT() {
/* 463 */     if (isWell()) {
/* 464 */       super.func_110196_bT();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean considerFleeing() {
/* 469 */     Entity last_attacking_entity = getLastHarmingEntity();
/* 470 */     this.has_decided_to_flee = (last_attacking_entity != null && getDistanceToEntity(last_attacking_entity) < 32.0F);
/*     */     
/* 472 */     return this.has_decided_to_flee;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean considerStopFleeing() {
/* 477 */     Entity last_attacking_entity = getLastHarmingEntity();
/*     */     
/* 479 */     if (last_attacking_entity == null) {
/*     */       
/* 481 */       this.has_decided_to_flee = false;
/* 482 */       this.fleeing = false;
/* 483 */       return true;
/*     */     } 
/*     */     
/* 486 */     if (getDistanceToEntity(last_attacking_entity) > 40.0F) {
/*     */       
/* 488 */       this.fleeing = false;
/* 489 */       return true;
/*     */     } 
/*     */     
/* 492 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getManurePeriod() {
/* 497 */     return this.manure_period;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setManurePeriod(int manure_period) {
/* 502 */     this.manure_period = manure_period;
/* 503 */     this.manure_countdown = (int)(Math.random() * manure_period);
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
/*     */   public int getExperienceValue() {
/* 559 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGrowingAge(int par1) {
/* 564 */     if (isChild() && par1 > getGrowingAge())
/*     */     {
/* 566 */       if (isDesperateForFood() || isDesperateForWater()) {
/*     */         return;
/*     */       }
/*     */     }
/* 570 */     super.setGrowingAge(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void adoptWellnessFromParents(EntityAnimal parent_a, EntityAnimal parent_b) {
/* 575 */     if (onClient()) {
/*     */       
/* 577 */       Minecraft.setErrorMessage("adoptWellnessFromParents: cannot be called on client");
/*     */       
/*     */       return;
/*     */     } 
/* 581 */     float food_parent_a = (parent_a instanceof EntityLivestock) ? ((EntityLivestock)parent_a).getFood() : 1.0F;
/* 582 */     float food_parent_b = (parent_b instanceof EntityLivestock) ? ((EntityLivestock)parent_b).getFood() : 1.0F;
/*     */     
/* 584 */     float water_parent_a = (parent_a instanceof EntityLivestock) ? ((EntityLivestock)parent_a).getWater() : 1.0F;
/* 585 */     float water_parent_b = (parent_b instanceof EntityLivestock) ? ((EntityLivestock)parent_b).getWater() : 1.0F;
/*     */     
/* 587 */     float freedom_parent_a = (parent_a instanceof EntityLivestock) ? ((EntityLivestock)parent_a).getFreedom() : 1.0F;
/* 588 */     float freedom_parent_b = (parent_b instanceof EntityLivestock) ? ((EntityLivestock)parent_b).getFreedom() : 1.0F;
/*     */     
/* 590 */     setFood(Math.min(food_parent_a, food_parent_b));
/* 591 */     setWater(Math.min(water_parent_a, water_parent_b));
/* 592 */     setFreedom(Math.min(freedom_parent_a, freedom_parent_b));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean willEat(ItemStack item_stack) {
/* 597 */     if (hasFullHealth())
/*     */     {
/* 599 */       if (!isWell() && !isHungry()) {
/* 600 */         return false;
/*     */       }
/*     */     }
/* 603 */     return super.willEat(item_stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityLivestock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */