/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityMagmaCube
/*     */   extends EntityCubic
/*     */ {
/*     */   private int ticks_until_next_fizz_sound;
/*     */   
/*     */   public EntityMagmaCube(World par1World) {
/*  10 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  16 */     super.applyEntityAttributes();
/*  17 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.20000000298023224D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/*  26 */     return (this.worldObj.difficultySetting > 0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox));
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
/*     */   public float getNaturalDefense(DamageSource damage_source) {
/*  40 */     return damage_source.bypassesMundaneArmor() ? 0.0F : (getSize() * 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/*  45 */     if (damage_source.isWater() || damage_source.isSnowball()) {
/*  46 */       return false;
/*     */     }
/*  48 */     ItemStack item_stack = damage_source.getItemAttackedWith();
/*     */     
/*  50 */     if (item_stack != null && item_stack.getItem() instanceof ItemTool && item_stack.getItemAsTool().isEffectiveAgainstBlock(Block.stone, 0)) {
/*  51 */       return false;
/*     */     }
/*  53 */     return !damage_source.isExplosion();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/*  58 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/*  66 */     return 1.0F;
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
/*     */   public EnumParticle getSquishParticle() {
/*  79 */     return EnumParticle.flame;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityCubic createInstance() {
/*  85 */     return new EntityMagmaCube(this.worldObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/*  93 */     return Item.magmaCream.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 122 */     int item_id = getDropItemId();
/*     */     
/* 124 */     if (item_id > 0 && getSize() > 1) {
/*     */       
/* 126 */       int num_drops = this.rand.nextInt(4 + damage_source.getLootingModifier()) - 2;
/*     */       
/* 128 */       for (int i = 0; i < num_drops; i++) {
/* 129 */         dropItem(item_id, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 138 */     return false;
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
/*     */   public int getJumpDelay(Entity target) {
/* 151 */     if (target == null) {
/* 152 */       return this.rand.nextInt(81) + 40;
/*     */     }
/* 154 */     return 20;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void alterSquishAmount() {
/* 159 */     this.squishAmount *= 0.9F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 167 */     this.motionY = (0.42F + getSize() * 0.1F);
/* 168 */     this.isAirBorne = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAttackStrengthMultiplierForType() {
/* 186 */     return 2;
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
/*     */   protected String getHurtSound() {
/* 202 */     return "mob.slime." + ((getSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 210 */     return "mob.slime." + ((getSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getJumpSound() {
/* 218 */     return (getSize() > 1) ? "mob.magmacube.big" : "mob.magmacube.small";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleLavaMovement() {
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean makesSoundOnLand() {
/* 234 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 239 */     if (this.worldObj.isRemote)
/*     */     {
/* 241 */       if (isWet()) {
/* 242 */         spawnSteamParticles(this.inWater ? 10 : 1);
/*     */       }
/*     */     }
/* 245 */     if (!this.worldObj.isRemote)
/*     */     {
/* 247 */       if (isWet())
/*     */       {
/* 249 */         if (--this.ticks_until_next_fizz_sound <= 0) {
/*     */           
/* 251 */           playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 252 */           this.ticks_until_next_fizz_sound = this.rand.nextInt(7) + 2;
/*     */           
/* 254 */           if (this.rand.nextInt(this.inWater ? 4 : 16) == 0)
/*     */           {
/* 256 */             attackEntityFrom(new Damage(DamageSource.water, 1.0F));
/*     */           }
/*     */         } 
/*     */       }
/*     */     }
/* 261 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepelledByCollisionWithPlayer() {
/* 266 */     return (getSize() > 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean slowsPlayerOnContact() {
/* 271 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 277 */     return getSize() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityBiologicallyAlive() {
/* 282 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 292 */     return false;
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
/*     */   public void onCollidedWithBlock(World world, Block block, int x, int y, int z) {
/* 327 */     if (onServer() && block.doRenderBoundsIntersectWith(world, x, y, z, this.boundingBox)) {
/*     */       
/* 329 */       if (block instanceof BlockTorch) {
/*     */         
/* 331 */         world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setCollidedWith(this), true);
/*     */         return;
/*     */       } 
/* 334 */       if (instantlyBurnsBlock(block)) {
/*     */         
/* 336 */         world.destroyBlockWithoutDroppingItem(x, y, z, EnumBlockFX.smoke_and_steam);
/*     */         return;
/*     */       } 
/* 339 */       if (block.blockMaterial.isFreezing()) {
/*     */         
/* 341 */         if (world.tryToMeltBlock(x, y, z)) {
/* 342 */           world.blockFX(EnumBlockFX.steam, x, y, z);
/*     */         }
/* 344 */         attackEntityFrom(new Damage(DamageSource.absolute, 1.0F));
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 350 */     super.onCollidedWithBlock(world, block, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean instantlyBurnsBlock(Block block) {
/* 356 */     if (block == Block.tallGrass) {
/* 357 */       return true;
/*     */     }
/* 359 */     Material material = block.blockMaterial;
/*     */     
/* 361 */     return (material == Material.plants || material == Material.web);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attacksAnimals() {
/* 366 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attacksVillagers() {
/* 371 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityMagmaCube.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */