/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityAnimal
/*     */   extends EntityAgeable
/*     */   implements IAnimals
/*     */ {
/*     */   private int inLove;
/*     */   private int breeding;
/*     */   
/*     */   public EntityAnimal(World par1World) {
/*  18 */     super(par1World);
/*     */     
/*  20 */     this.tasks.addTask(1, new EntityAIMoveToFoodItem(this, 1.0F, true));
/*  21 */     this.tasks.addTask(2, new EntityAIAvoidFire(this, 1.0F, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isAIEnabled() {
/*  26 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAITick() {
/*  34 */     if (getGrowingAge() != 0)
/*     */     {
/*  36 */       this.inLove = 0;
/*     */     }
/*     */     
/*  39 */     super.updateAITick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  48 */     super.onLivingUpdate();
/*     */     
/*  50 */     if (getGrowingAge() != 0)
/*     */     {
/*  52 */       this.inLove = 0;
/*     */     }
/*     */     
/*  55 */     if (this.inLove > 0) {
/*     */       
/*  57 */       this.inLove--;
/*  58 */       String var1 = "heart";
/*     */       
/*  60 */       if (this.inLove % 10 == 0)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  67 */         spawnInLoveHeartParticle();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  72 */       this.breeding = 0;
/*     */     } 
/*     */     
/*  75 */     if (this.inLove > 0 && this.inLove % 10 == 0 && this.worldObj instanceof WorldServer) {
/*     */       
/*  77 */       WorldServer world_server = (WorldServer)this.worldObj;
/*     */       
/*  79 */       if (world_server.playerEntities.size() > 0) {
/*     */         
/*  81 */         Iterator<EntityPlayer> i = world_server.playerEntities.iterator();
/*     */         
/*  83 */         while (i.hasNext()) {
/*     */           
/*  85 */           EntityPlayer player = i.next();
/*     */           
/*  87 */           if (player.getDistanceSqToEntity(this) < 256.0D) {
/*  88 */             world_server.getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet84EntityStateWithData(this.entityId, EnumEntityState.in_love, this.inLove));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {
/*  99 */     if (par1Entity instanceof EntityPlayer) {
/*     */       
/* 101 */       if (par2 < 3.0F) {
/*     */         
/* 103 */         double var3 = par1Entity.posX - this.posX;
/* 104 */         double var5 = par1Entity.posZ - this.posZ;
/* 105 */         this.rotationYaw = (float)(Math.atan2(var5, var3) * 180.0D / Math.PI) - 90.0F;
/* 106 */         this.hasAttacked = true;
/*     */       } 
/*     */       
/* 109 */       EntityPlayer var7 = (EntityPlayer)par1Entity;
/*     */ 
/*     */       
/* 112 */       if (var7.getHeldItemStack() == null || !willEat(var7.getHeldItemStack()))
/*     */       {
/* 114 */         this.entityToAttack = null;
/*     */       }
/*     */     }
/* 117 */     else if (par1Entity instanceof EntityAnimal) {
/*     */       
/* 119 */       EntityAnimal var8 = (EntityAnimal)par1Entity;
/*     */       
/* 121 */       if (getGrowingAge() > 0 && var8.getGrowingAge() < 0) {
/*     */         
/* 123 */         if (par2 < 2.5D)
/*     */         {
/* 125 */           this.hasAttacked = true;
/*     */         }
/*     */       }
/* 128 */       else if (this.inLove > 0 && var8.inLove > 0) {
/*     */         
/* 130 */         if (var8.entityToAttack == null)
/*     */         {
/* 132 */           var8.entityToAttack = this;
/*     */         }
/*     */         
/* 135 */         if (var8.entityToAttack == this && par2 < 3.5D)
/*     */         {
/* 137 */           var8.inLove++;
/* 138 */           this.inLove++;
/* 139 */           this.breeding++;
/*     */           
/* 141 */           if (this.breeding % 4 == 0)
/*     */           {
/*     */             
/* 144 */             this.worldObj.spawnParticle(EnumParticle.heart, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D);
/*     */           }
/*     */           
/* 147 */           if (this.breeding == 60)
/*     */           {
/* 149 */             procreate((EntityAnimal)par1Entity);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 154 */           this.breeding = 0;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 159 */         this.breeding = 0;
/* 160 */         this.entityToAttack = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void procreate(EntityAnimal par1EntityAnimal) {
/* 171 */     EntityAgeable var2 = createChild(par1EntityAnimal);
/*     */     
/* 173 */     if (var2 != null) {
/*     */ 
/*     */ 
/*     */       
/* 177 */       setGrowingAgeAfterBreeding();
/* 178 */       par1EntityAnimal.setGrowingAgeAfterBreeding();
/* 179 */       this.inLove = 0;
/* 180 */       this.breeding = 0;
/* 181 */       this.entityToAttack = null;
/* 182 */       par1EntityAnimal.entityToAttack = null;
/* 183 */       par1EntityAnimal.breeding = 0;
/* 184 */       par1EntityAnimal.inLove = 0;
/*     */       
/* 186 */       var2.setGrowingAgeToNewborn();
/* 187 */       var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*     */       
/* 189 */       if (onServer() && var2 instanceof EntityLivestock) {
/* 190 */         ((EntityLivestock)var2).adoptWellnessFromParents(this, par1EntityAnimal);
/*     */       }
/* 192 */       for (int var3 = 0; var3 < 7; var3++) {
/*     */         
/* 194 */         double var4 = this.rand.nextGaussian() * 0.02D;
/* 195 */         double var6 = this.rand.nextGaussian() * 0.02D;
/* 196 */         double var8 = this.rand.nextGaussian() * 0.02D;
/*     */         
/* 198 */         this.worldObj.spawnParticle(EnumParticle.heart, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var4, var6, var8);
/*     */       } 
/*     */       
/* 201 */       this.worldObj.spawnEntityInWorld(var2);
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 242 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     if (result == null) {
/* 251 */       return result;
/*     */     }
/* 253 */     if (result.entityWasNegativelyAffected()) {
/*     */       
/* 255 */       if (!(this instanceof EntityTameable) || !((EntityTameable)this).isTamed()) {
/* 256 */         warnPeersOfAttacker(getClass(), damage.getResponsibleEntity());
/*     */       }
/* 258 */       this.fleeingTick = 60;
/*     */       
/* 260 */       if (!isAIEnabled()) {
/*     */         
/* 262 */         AttributeInstance var3 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*     */         
/* 264 */         if (var3.getModifier(field_110179_h) == null) {
/* 265 */           var3.applyModifier(field_110181_i);
/*     */         }
/*     */       } 
/* 268 */       this.entityToAttack = null;
/*     */       
/* 270 */       setInLove(0, true);
/*     */     } 
/*     */     
/* 273 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int par1, int par2, int par3) {
/* 282 */     return (this.worldObj.getBlockId(par1, par2 - 1, par3) == Block.grass.blockID) ? 10.0F : (this.worldObj.getLightBrightness(par1, par2, par3) - 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 290 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 291 */     par1NBTTagCompound.setInteger("InLove", this.inLove);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 299 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 300 */     this.inLove = par1NBTTagCompound.getInteger("InLove");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInLove(int in_love) {
/* 306 */     setInLove(in_love, onServer());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInLove(int in_love, boolean update_client) {
/* 311 */     this.inLove = in_love;
/*     */     
/* 313 */     if (update_client)
/*     */     {
/* 315 */       if (onServer()) {
/* 316 */         sendPacketToAllPlayersTrackingEntity((new Packet85SimpleSignal(EnumSignal.in_love)).setShort(0).setEntityID(this));
/*     */       } else {
/* 318 */         Minecraft.setErrorMessage("setInLove: update_client is true but calling on client");
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected EntityPlayer findPlayerToAttack(float max_distance) {
/* 324 */     Minecraft.setErrorMessage("EntityAnimal using old AI system: " + getEntityName());
/* 325 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityPlayer findNonPlayerToAttack(float max_distance) {
/* 330 */     Minecraft.setErrorMessage("EntityAnimal using old AI system: " + getEntityName());
/* 331 */     return null;
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
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 408 */     int var1 = MathHelper.floor_double(this.posX);
/* 409 */     int var2 = MathHelper.floor_double(this.boundingBox.minY);
/* 410 */     int var3 = MathHelper.floor_double(this.posZ);
/*     */ 
/*     */ 
/*     */     
/* 414 */     if (this instanceof EntityHellhound)
/*     */     {
/* 416 */       return ((!perform_light_check || isValidLightLevel()) && super.getCanSpawnHere(perform_light_check));
/*     */     }
/* 418 */     return (this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && (!perform_light_check || this.worldObj.getFullBlockLightValue(var1, var2, var3) > 8) && super.getCanSpawnHere(perform_light_check));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 426 */     return 120;
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
/*     */   protected boolean canDespawn() {
/* 440 */     if (!(this instanceof EntityHellhound)) {
/*     */       
/* 442 */       if (this.despawn_counter < 800) {
/* 443 */         return false;
/*     */       }
/* 445 */       if (this.worldObj.getClosestPlayer(this.posX, this.posY, this.posZ, 1024.0D, false) != null || this.worldObj.blockTypeIsNearTo(Block.fence.blockID, this.posX, this.posY, this.posZ, 16, 1)) {
/*     */         
/* 447 */         this.despawn_counter = 0;
/* 448 */         return false;
/*     */       } 
/*     */       
/* 451 */       if (this.despawn_counter <= 2400) {
/* 452 */         return false;
/*     */       }
/*     */     } 
/* 455 */     return super.canDespawn();
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
/*     */   public boolean canEat() {
/* 477 */     return (!isInLove() && getGrowingAge() == 0 && super.canEat());
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 517 */     if (item_stack != null && willEat(item_stack)) {
/*     */       
/* 519 */       if (player.onServer()) {
/*     */         
/* 521 */         onFoodEaten(item_stack);
/*     */         
/* 523 */         if (!player.inCreativeMode()) {
/* 524 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 527 */       return true;
/*     */     } 
/*     */     
/* 530 */     return super.onEntityRightClicked(player, item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onFoodEaten(ItemStack item_stack) {
/* 535 */     if (item_stack == null) {
/*     */       return;
/*     */     }
/* 538 */     if (item_stack.getItem() != Item.rottenFlesh) {
/* 539 */       func_110196_bT();
/*     */     }
/* 541 */     super.onFoodEaten(item_stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxInLove() {
/* 546 */     return 600;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_110196_bT() {
/* 552 */     if (getHealth() < getMaxHealth()) {
/*     */       return;
/*     */     }
/*     */     
/* 556 */     this.inLove = getMaxInLove();
/* 557 */     this.entityToAttack = null;
/*     */ 
/*     */     
/* 560 */     if (!this.worldObj.isRemote) {
/* 561 */       this.worldObj.setEntityState(this, EnumEntityState.in_love);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInLove() {
/* 569 */     return (this.inLove > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetInLove() {
/* 574 */     this.inLove = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(EntityAnimal par1EntityAnimal) {
/* 582 */     return (par1EntityAnimal == this) ? false : ((par1EntityAnimal.getClass() != getClass()) ? false : ((isInLove() && par1EntityAnimal.isInLove())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 589 */     if (par1 == EnumEntityState.in_love) {
/*     */       
/* 591 */       for (int var2 = 0; var2 < 7; var2++) {
/*     */         
/* 593 */         double var3 = this.rand.nextGaussian() * 0.02D;
/* 594 */         double var5 = this.rand.nextGaussian() * 0.02D;
/* 595 */         double var7 = this.rand.nextGaussian() * 0.02D;
/*     */         
/* 597 */         this.worldObj.spawnParticle(EnumParticle.heart, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var3, var5, var7);
/*     */       } 
/*     */       
/* 600 */       func_110196_bT();
/*     */     }
/*     */     else {
/*     */       
/* 604 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
/* 610 */     if (isChild()) {
/* 611 */       par1EntityLivingBase = null;
/*     */     }
/* 613 */     super.setAttackTarget(par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 618 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAnimal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */