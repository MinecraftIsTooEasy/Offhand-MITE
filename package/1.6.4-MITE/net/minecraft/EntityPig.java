/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPig
/*     */   extends EntityLivestock
/*     */ {
/*     */   private final EntityAIControlledByPlayer aiControlledByPlayer;
/*     */   
/*     */   public EntityPig(World par1World) {
/*  11 */     super(par1World);
/*  12 */     setSize(0.9F, 0.9F);
/*  13 */     getNavigator().setAvoidsWater(true);
/*  14 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*     */ 
/*     */     
/*  17 */     this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
/*  18 */     this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
/*     */     
/*  20 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickFlint.itemID, false));
/*  21 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickObsidian.itemID, false));
/*  22 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickCopper.itemID, false));
/*  23 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickSilver.itemID, false));
/*  24 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickGold.itemID, false));
/*  25 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickIron.itemID, false));
/*  26 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickMithril.itemID, false));
/*  27 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickAdamantium.itemID, false));
/*  28 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrotOnAStickAncientMetal.itemID, false));
/*  29 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Item.carrot.itemID, false));
/*  30 */     this.tasks.addTask(4, new EntityAITempt(this, 1.2D, (Item.getItem(Block.mushroomBrown)).itemID, false));
/*  31 */     this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
/*  32 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  33 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  34 */     this.tasks.addTask(8, new EntityAILookIdle(this));
/*     */ 
/*     */     
/*  37 */     this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.4F, 0.5F, true));
/*     */     
/*  39 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*  40 */       setManurePeriod(getManurePeriod() * 2);
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
/*     */   protected void applyEntityAttributes() {
/*  53 */     super.applyEntityAttributes();
/*  54 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
/*  55 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/*  60 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeSteered() {
/*  69 */     ItemStack var1 = ((EntityPlayer)this.riddenByEntity).getHeldItemStack();
/*     */     
/*  71 */     return (var1 != null && var1.getItem() instanceof ItemCarrotOnAStick);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  76 */     super.entityInit();
/*  77 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  85 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  86 */     par1NBTTagCompound.setBoolean("Saddle", getSaddled());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  94 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  95 */     setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 103 */     return "mob.pig.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 111 */     return "mob.pig.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 119 */     return "mob.pig.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 128 */     makeSound("mob.pig.step", 0.15F, 1.0F);
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 153 */     if (super.onEntityRightClicked(player, item_stack)) {
/* 154 */       return true;
/*     */     }
/* 156 */     if (getSaddled() && this.riddenByEntity == null) {
/*     */       
/* 158 */       if (player.onServer()) {
/* 159 */         player.mountEntity(this);
/*     */       }
/* 161 */       return true;
/*     */     } 
/*     */     
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 172 */     return isBurning() ? Item.porkCooked.itemID : Item.porkRaw.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 203 */     if (getSaddled()) {
/* 204 */       dropItem(Item.saddle);
/*     */     }
/* 206 */     if (isWell()) {
/*     */       
/* 208 */       int num_drops = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + damage_source.getButcheringModifier());
/*     */       
/* 210 */       for (int i = 0; i < num_drops; i++) {
/* 211 */         dropItem(isBurning() ? Item.porkCooked : Item.porkRaw);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSaddled() {
/* 220 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSaddled(boolean par1) {
/* 228 */     if (par1) {
/*     */       
/* 230 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
/*     */     }
/*     */     else {
/*     */       
/* 234 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {
/* 243 */     if (!this.worldObj.isRemote) {
/*     */       
/* 245 */       EntityPigZombie var2 = new EntityPigZombie(this.worldObj);
/* 246 */       var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/* 247 */       this.worldObj.spawnEntityInWorld(var2);
/* 248 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {
/* 257 */     super.fall(par1);
/*     */     
/* 259 */     if (par1 > 5.0F && this.riddenByEntity instanceof EntityPlayer)
/*     */     {
/* 261 */       ((EntityPlayer)this.riddenByEntity).triggerAchievement(AchievementList.flyPig);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityPig spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 270 */     return new EntityPig(this.worldObj);
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
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 286 */     if (item_stack != null) {
/*     */       
/* 288 */       Item item = item_stack.getItem();
/*     */       
/* 290 */       if (item == Item.carrot) {
/* 291 */         return true;
/*     */       }
/* 293 */       if (item instanceof ItemBlock) {
/*     */         
/* 295 */         ItemBlock item_block = (ItemBlock)item;
/*     */         
/* 297 */         if (item_block.getBlock() == Block.mushroomBrown) {
/* 298 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 302 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAIControlledByPlayer getAIControlledByPlayer() {
/* 310 */     return this.aiControlledByPlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 315 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void produceGoods() {
/* 320 */     this.production_counter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 325 */     if (this.riddenByEntity == null) {
/* 326 */       return super.getAIMoveSpeed();
/*     */     }
/* 328 */     return 0.25F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */