/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class EntityPigZombie
/*     */   extends EntityZombie {
/*   9 */   private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
/*  10 */   private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).setSaved(false);
/*     */ 
/*     */   
/*     */   private int angerLevel;
/*     */   
/*     */   private int randomSoundDelay;
/*     */   
/*     */   private Entity field_110191_bu;
/*     */ 
/*     */   
/*     */   public EntityPigZombie(World par1World) {
/*  21 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  27 */     super.applyEntityAttributes();
/*  28 */     getEntityAttribute(field_110186_bp).setAttribute(0.0D);
/*  29 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.5D);
/*     */     
/*  31 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(8.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  48 */     if (this.field_110191_bu != this.entityToAttack && !this.worldObj.isRemote) {
/*     */       
/*  50 */       AttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*  51 */       var1.removeModifier(field_110190_br);
/*     */       
/*  53 */       if (this.entityToAttack != null)
/*     */       {
/*  55 */         var1.applyModifier(field_110190_br);
/*     */       }
/*     */     } 
/*     */     
/*  59 */     this.field_110191_bu = this.entityToAttack;
/*     */     
/*  61 */     if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
/*     */     {
/*     */       
/*  64 */       playSound("mob.zombiepig.zpigangry", getSoundVolume("mob.zombiepig.zpigangry") * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
/*     */     }
/*     */     
/*  67 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/*  76 */     return (this.worldObj.difficultySetting > 0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  84 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  85 */     par1NBTTagCompound.setShort("Anger", (short)this.angerLevel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  93 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  94 */     this.angerLevel = par1NBTTagCompound.getShort("Anger");
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
/*     */   protected EntityPlayer findPlayerToAttack(float max_distance) {
/* 108 */     if (this.angerLevel < 1) {
/* 109 */       max_distance /= 4.0F;
/*     */     }
/* 111 */     Entity previous_target = getEntityToAttack();
/*     */     
/* 113 */     EntityPlayer target = super.findPlayerToAttack(max_distance);
/*     */     
/* 115 */     if (target != null && target != previous_target) {
/* 116 */       becomeAngryAt(target);
/*     */     }
/* 118 */     return target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem findTargetEntityItem(float max_distance) {
/* 125 */     Iterator<EntityItem> i = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(max_distance, (max_distance * 0.25F), max_distance)).iterator();
/*     */     
/* 127 */     while (i.hasNext()) {
/*     */       
/* 129 */       EntityItem entity_item = i.next();
/*     */ 
/*     */       
/* 132 */       if (willPickupAsValuable(entity_item.getEntityItem())) {
/* 133 */         return entity_item;
/*     */       }
/*     */     } 
/* 136 */     return null;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 177 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */     
/* 179 */     if (result == null) {
/* 180 */       return result;
/*     */     }
/* 182 */     if (result.entityWasNegativelyAffected()) {
/*     */       
/* 184 */       Entity var3 = damage.getResponsibleEntity();
/*     */       
/* 186 */       if (var3 instanceof EntityPlayer) {
/*     */         
/* 188 */         List<Entity> var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
/*     */         
/* 190 */         for (int var5 = 0; var5 < var4.size(); var5++) {
/*     */           
/* 192 */           Entity var6 = var4.get(var5);
/*     */           
/* 194 */           if (var6 instanceof EntityPigZombie) {
/*     */             
/* 196 */             EntityPigZombie var7 = (EntityPigZombie)var6;
/* 197 */             var7.becomeAngryAt(var3);
/*     */           } 
/*     */         } 
/*     */         
/* 201 */         becomeAngryAt(var3);
/*     */       } 
/*     */     } 
/*     */     
/* 205 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void becomeAngryAt(Entity par1Entity) {
/* 213 */     this.entityToAttack = par1Entity;
/* 214 */     this.angerLevel = 400 + this.rand.nextInt(400);
/* 215 */     this.randomSoundDelay = this.rand.nextInt(40);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 223 */     return "mob.zombiepig.zpig";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 231 */     return "mob.zombiepig.zpighurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 239 */     return "mob.zombiepig.zpigdeath";
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 266 */     if (this.rand.nextFloat() < (recently_hit_by_player ? 0.5F : 0.25F)) {
/* 267 */       dropItem(Item.rottenFlesh);
/*     */     }
/* 269 */     int num_drops = this.rand.nextInt(2 + damage_source.getLootingModifier());
/*     */     
/* 271 */     for (int i = 0; i < num_drops; i++) {
/* 272 */       dropItem(Item.goldNugget);
/*     */     }
/* 274 */     if (recently_hit_by_player && !this.has_taken_massive_fall_damage)
/*     */     {
/* 276 */       if (this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + damage_source.getLootingModifier() * 2) {
/* 277 */         dropItem(Item.ingotGold);
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
/*     */   protected int getDropItemId() {
/* 299 */     return Item.rottenFlesh.itemID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRandomEquipment() {
/* 304 */     Item[] items = { Item.swordGold, Item.swordGold, Item.battleAxeGold, Item.pickaxeGold };
/*     */     
/* 306 */     setCurrentItemOrArmor(0, (new ItemStack(items[this.rand.nextInt(items.length)])).setQuality(EnumQuality.poor).randomizeForMob(this, false));
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
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 319 */     super.onSpawnWithEgg(par1EntityLivingData);
/* 320 */     setVillager(false, 0);
/* 321 */     return par1EntityLivingData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 326 */     return super.getExperienceValue() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 331 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 336 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean catchesFireInSunlight() {
/* 346 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean willPickupAsValuable(ItemStack item_stack) {
/* 351 */     return (item_stack.getItemSubtype() == 0 && item_stack.getItemDamage() == 0 && item_stack.hasMaterial(Material.gold));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityPigZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */