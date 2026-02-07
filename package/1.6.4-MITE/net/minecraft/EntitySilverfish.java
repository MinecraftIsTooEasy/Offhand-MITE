/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntitySilverfish
/*     */   extends EntityMob
/*     */ {
/*     */   private int allySummonCooldown;
/*     */   
/*     */   public EntitySilverfish(World par1World) {
/*  12 */     super(par1World);
/*  13 */     setSize(0.3F, 0.7F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  18 */     super.applyEntityAttributes();
/*  19 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(8.0D);
/*  20 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.6000000238418579D);
/*     */     
/*  22 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/*  27 */     return isNormalSilverfish() ? 5 : 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EntityPlayer findPlayerToAttack(float max_distance) {
/*  46 */     double var1 = 8.0D;
/*     */ 
/*     */     
/*  49 */     return super.findPlayerToAttack(8.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Entity findNonPlayerToAttack(float max_distance) {
/*  54 */     return this.worldObj.getClosestPrey(this, max_distance, true, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean preysUpon(Entity entity) {
/*  59 */     return (entity.isTrueAnimal() || entity instanceof EntityVillager);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  67 */     return "mob.silverfish.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  75 */     return "mob.silverfish.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  83 */     return "mob.silverfish.kill";
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 108 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */     
/* 110 */     if (result == null) {
/* 111 */       return result;
/*     */     }
/* 113 */     if (result.entityWasNegativelyAffected())
/*     */     {
/* 115 */       if (this.allySummonCooldown <= 0 && (damage.getSource() instanceof EntityDamageSource || damage.getSource() == DamageSource.magic)) {
/* 116 */         this.allySummonCooldown = 20;
/*     */       }
/*     */     }
/* 119 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {
/* 127 */     if (this.attackTime <= 0 && par2 < 1.2F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
/*     */       
/* 129 */       this.attackTime = 20;
/* 130 */       attackEntityAsMob(par1Entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 136 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/* 138 */     if (result == null || result.entityWasDestroyed()) {
/* 139 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (result.entityLostHealth() && target instanceof EntityLivingBase)
/*     */     {
/*     */       
/* 147 */       if (isHoarySilverfish()) {
/* 148 */         target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
/* 149 */       } else if (isCopperspine()) {
/* 150 */         target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 480, 0));
/*     */       } 
/*     */     }
/* 153 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 162 */     makeSound("mob.silverfish.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 170 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 178 */     this.renderYawOffset = this.rotationYaw;
/* 179 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {
/* 184 */     super.updateEntityActionState();
/*     */     
/* 186 */     if (!this.worldObj.isRemote) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 193 */       if (this.allySummonCooldown > 0) {
/*     */         
/* 195 */         this.allySummonCooldown--;
/*     */         
/* 197 */         if (this.allySummonCooldown == 0) {
/*     */           
/* 199 */           int var1 = MathHelper.floor_double(this.posX);
/* 200 */           int var2 = MathHelper.floor_double(this.posY);
/* 201 */           int var3 = MathHelper.floor_double(this.posZ);
/* 202 */           boolean var4 = false;
/*     */           int var5;
/* 204 */           for (var5 = 0; !var4 && var5 <= 5 && var5 >= -5; var5 = (var5 <= 0) ? (1 - var5) : (0 - var5)) {
/*     */             int var6;
/* 206 */             for (var6 = 0; !var4 && var6 <= 10 && var6 >= -10; var6 = (var6 <= 0) ? (1 - var6) : (0 - var6)) {
/*     */               int var7;
/* 208 */               for (var7 = 0; !var4 && var7 <= 10 && var7 >= -10; var7 = (var7 <= 0) ? (1 - var7) : (0 - var7)) {
/*     */                 
/* 210 */                 int var8 = this.worldObj.getBlockId(var1 + var6, var2 + var5, var3 + var7);
/*     */                 
/* 212 */                 if (var8 == Block.silverfish.blockID) {
/*     */                   
/* 214 */                   if (!this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */                     
/* 216 */                     int var9 = this.worldObj.getBlockMetadata(var1 + var6, var2 + var5, var3 + var7);
/* 217 */                     Block var10 = Block.stone;
/*     */                     
/* 219 */                     if (var9 == 1)
/*     */                     {
/* 221 */                       var10 = Block.cobblestone;
/*     */                     }
/*     */                     
/* 224 */                     if (var9 == 2)
/*     */                     {
/* 226 */                       var10 = Block.stoneBrick;
/*     */                     }
/*     */                     
/* 229 */                     this.worldObj.setBlock(var1 + var6, var2 + var5, var3 + var7, var10.blockID, 0, 3);
/*     */                   
/*     */                   }
/*     */                   else {
/*     */                     
/* 234 */                     this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, var1 + var6, var2 + var5, var3 + var7)).setDestroyedBy(this), false);
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 241 */                   Block.silverfish.dropBlockAsEntityItem((new BlockBreakInfo(this.worldObj, var1 + var6, var2 + var5, var3 + var7)).setSilverfish(this));
/*     */ 
/*     */                   
/* 244 */                   if (this.rand.nextInt(4) == 0) {
/*     */                     
/* 246 */                     var4 = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       if (!hasPath())
/*     */       {
/* 282 */         if (this.entityToAttack == null) {
/* 283 */           updateWanderPath();
/*     */         } else {
/* 285 */           this.entityToAttack = null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int par1, int par2, int par3) {
/* 296 */     return (this.worldObj.getBlockId(par1, par2 - 1, par3) == Block.stone.blockID) ? 10.0F : super.getBlockPathWeight(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 304 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 314 */     if (super.getCanSpawnHere(perform_light_check)) {
/*     */ 
/*     */       
/* 317 */       EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity(this, 5.0D, true);
/* 318 */       return (var1 == null);
/*     */     } 
/*     */ 
/*     */     
/* 322 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 331 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNormalSilverfish() {
/* 336 */     return (getClass() == EntitySilverfish.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNetherspawn() {
/* 341 */     return this instanceof EntityNetherspawn;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCopperspine() {
/* 346 */     return this instanceof EntityCopperspine;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHoarySilverfish() {
/* 351 */     return (getClass() == EntityHoarySilverfish.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySilverfish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */