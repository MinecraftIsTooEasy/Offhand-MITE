/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityCow
/*     */   extends EntityLivestock
/*     */ {
/*     */   int data_object_id_milk;
/*     */   
/*     */   public EntityCow(World par1World) {
/*  10 */     super(par1World);
/*  11 */     setSize(0.9F, 1.3F);
/*  12 */     getNavigator().setAvoidsWater(true);
/*  13 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*     */ 
/*     */     
/*  16 */     this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
/*  17 */     this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Item.wheat.itemID, false));
/*  18 */     this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
/*  19 */     this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
/*  20 */     this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
/*  21 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*     */ 
/*     */     
/*  24 */     this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.6F, 0.5F, true));
/*     */     
/*  26 */     setMilk(100);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  31 */     super.entityInit();
/*  32 */     this.data_object_id_milk = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Integer(0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setMilk(int milk) {
/*  37 */     this.dataWatcher.updateObject(this.data_object_id_milk, Integer.valueOf(MathHelper.clamp_int(milk, 0, 100)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMilk() {
/*  42 */     if (isChild()) {
/*  43 */       return 0;
/*     */     }
/*  45 */     return this.dataWatcher.getWatchableObjectInt(this.data_object_id_milk);
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
/*     */   protected void applyEntityAttributes() {
/*  58 */     super.applyEntityAttributes();
/*     */     
/*  60 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0D);
/*  61 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.20000000298023224D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  66 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  67 */     par1NBTTagCompound.setInteger("milk", getMilk());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  72 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  73 */     setMilk(par1NBTTagCompound.getInteger("milk"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  81 */     return "mob.cow.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  89 */     return "mob.cow.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  97 */     return "mob.cow.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 106 */     makeSound("mob.cow.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 115 */     if (sound.equals("mob.cow.step")) {
/* 116 */       return 0.8F;
/*     */     }
/* 118 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void produceGoods() {
/* 123 */     setMilk(getMilk() + this.production_counter);
/*     */     
/* 125 */     this.production_counter = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 133 */     return Item.leather.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 172 */     int num_drops = this.rand.nextInt(3) + 1;
/*     */     int i;
/* 174 */     for (i = 0; i < num_drops; i++) {
/* 175 */       dropItem(Item.leather.itemID, 1);
/*     */     }
/* 177 */     if (isWell()) {
/*     */       
/* 179 */       num_drops = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + damage_source.getButcheringModifier());
/*     */       
/* 181 */       if (num_drops == 1 && this.rand.nextInt(2) == 0) {
/* 182 */         num_drops++;
/*     */       }
/* 184 */       for (i = 0; i < num_drops; i++) {
/* 185 */         dropItem(isBurning() ? Item.beefCooked.itemID : Item.beefRaw.itemID, 1);
/*     */       }
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
/*     */   public EntityCow spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 267 */     return new EntityCow(this.worldObj);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 272 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 277 */     return (item_stack != null && item_stack.getItem() == Item.wheat);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFoodSource(int block_id) {
/* 282 */     Block block = Block.blocksList[block_id];
/*     */     
/* 284 */     return (block == Block.tallGrass || block == Block.plantYellow);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getFoodBlockIDs() {
/* 289 */     int[] block_ids = { Block.tallGrass.blockID, Block.plantYellow.blockID };
/* 290 */     return block_ids;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */