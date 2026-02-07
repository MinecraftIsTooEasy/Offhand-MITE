/*     */ package net.minecraft;
/*     */ 
/*     */ public class EntityBlaze
/*     */   extends EntityMob
/*     */ {
/*   6 */   private float heightOffset = 0.5F;
/*     */   
/*     */   private int heightOffsetUpdateTime;
/*     */   
/*     */   private int field_70846_g;
/*     */ 
/*     */   
/*     */   public EntityBlaze(World par1World) {
/*  14 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  21 */     super.applyEntityAttributes();
/*  22 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(6.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  27 */     super.entityInit();
/*  28 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  36 */     return "mob.blaze.breathe";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  44 */     return "mob.blaze.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  52 */     return "mob.blaze.death";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/*  57 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/*  65 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  74 */     if (!this.worldObj.isRemote) {
/*     */       
/*  76 */       if (isWet())
/*     */       {
/*     */         
/*  79 */         attackEntityFrom(new Damage(DamageSource.water, 1.0F));
/*     */       }
/*     */       
/*  82 */       this.heightOffsetUpdateTime--;
/*     */       
/*  84 */       if (this.heightOffsetUpdateTime <= 0) {
/*     */         
/*  86 */         this.heightOffsetUpdateTime = 100;
/*  87 */         this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
/*     */       } 
/*     */       
/*  90 */       if (getEntityToAttack() != null && (getEntityToAttack()).posY + getEntityToAttack().getEyeHeight() > this.posY + getEyeHeight() + this.heightOffset)
/*     */       {
/*  92 */         this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     if (this.rand.nextInt(24) == 0)
/*     */     {
/*  98 */       this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F);
/*     */     }
/*     */     
/* 101 */     if (!this.onGround && this.motionY < 0.0D)
/*     */     {
/* 103 */       this.motionY *= 0.6D;
/*     */     }
/*     */     
/* 106 */     for (int var1 = 0; var1 < 2; var1++)
/*     */     {
/*     */       
/* 109 */       this.worldObj.spawnParticle(EnumParticle.largesmoke, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 112 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {
/* 120 */     if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
/*     */       
/* 122 */       this.attackTime = 20;
/* 123 */       attackEntityAsMob(par1Entity);
/*     */     }
/* 125 */     else if (par2 < 30.0F) {
/*     */       
/* 127 */       double var3 = par1Entity.posX - this.posX;
/* 128 */       double var5 = par1Entity.boundingBox.minY + (par1Entity.height / 2.0F) - this.posY + (this.height / 2.0F);
/* 129 */       double var7 = par1Entity.posZ - this.posZ;
/*     */ 
/*     */ 
/*     */       
/* 133 */       float lead = (float)Math.pow(World.getDistanceSqFromDeltas(var3, var5, var7), 0.42D) * 2.0F;
/*     */       
/* 135 */       lead *= Math.min(0.5F + this.rand.nextFloat(), 1.0F);
/*     */       
/* 137 */       var3 = par1Entity.getPredictedPosX(lead) - this.posX;
/* 138 */       var7 = par1Entity.getPredictedPosZ(lead) - this.posZ;
/*     */ 
/*     */ 
/*     */       
/* 142 */       if (this.attackTime == 0) {
/*     */         
/* 144 */         this.field_70846_g++;
/*     */         
/* 146 */         if (this.field_70846_g == 1) {
/*     */ 
/*     */           
/* 149 */           this.attackTime = 20;
/* 150 */           func_70844_e(true);
/*     */         }
/* 152 */         else if (this.field_70846_g <= 4) {
/*     */           
/* 154 */           this.attackTime = 6;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 159 */           this.attackTime = 20;
/* 160 */           this.field_70846_g = 0;
/* 161 */           func_70844_e(false);
/*     */         } 
/*     */         
/* 164 */         if (this.field_70846_g > 1) {
/*     */           
/* 166 */           float var9 = MathHelper.sqrt_float(par2) * 0.5F;
/* 167 */           var9 *= 0.5F;
/* 168 */           this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */           
/* 170 */           for (int var10 = 0; var10 < 1; var10++) {
/*     */             
/* 172 */             EntitySmallFireball var11 = new EntitySmallFireball(this.worldObj, this, var3 + this.rand.nextGaussian() * var9, var5, var7 + this.rand.nextGaussian() * var9);
/* 173 */             var11.posY = this.posY + (this.height / 2.0F) + 0.5D;
/* 174 */             this.worldObj.spawnEntityInWorld(var11);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 179 */       this.rotationYaw = (float)(Math.atan2(var7, var3) * 180.0D / Math.PI) - 90.0F;
/* 180 */       this.hasAttacked = true;
/*     */     } 
/*     */   }
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
/*     */   protected int getDropItemId() {
/* 194 */     return Item.blazeRod.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 202 */     return func_70845_n();
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 224 */     if (recently_hit_by_player) {
/*     */       
/* 226 */       int num_drops = this.rand.nextInt(2 + damage_source.getLootingModifier());
/*     */       
/* 228 */       for (int i = 0; i < num_drops; i++) {
/* 229 */         dropItem(Item.blazeRod.itemID, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_70845_n() {
/* 235 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70844_e(boolean par1) {
/* 240 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 242 */     if (par1) {
/*     */       
/* 244 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     else {
/*     */       
/* 248 */       var2 = (byte)(var2 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 251 */     this.dataWatcher.updateObject(16, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 259 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 264 */     return super.getExperienceValue() * 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComfortableInLava() {
/* 279 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 284 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 289 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/* 294 */     if (damage_source.isWater() || damage_source.getImmediateEntity() instanceof EntitySnowball) {
/* 295 */       return false;
/*     */     }
/* 297 */     if (damage_source.hasMagicAspect())
/*     */     {
/* 299 */       if (damage_source.isArrowDamage()) {
/*     */         
/* 301 */         EntityArrow arrow = (EntityArrow)damage_source.getImmediateEntity();
/*     */         
/* 303 */         if (arrow.getLauncher() == null || !arrow.getLauncher().hasEnchantment(Enchantment.flame, true)) {
/* 304 */           return false;
/*     */         }
/*     */       } else {
/*     */         
/* 308 */         ItemStack item_stack = damage_source.getItemAttackedWith();
/*     */         
/* 310 */         if (item_stack == null || !item_stack.hasEnchantment(Enchantment.fireAspect, true)) {
/* 311 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/* 315 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBlaze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */