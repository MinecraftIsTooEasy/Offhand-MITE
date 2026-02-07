/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityXPOrb
/*     */   extends Entity
/*     */ {
/*     */   public int xpColor;
/*     */   public int xpOrbAge;
/*     */   public int field_70532_c;
/*  17 */   private int xpOrbHealth = 5;
/*     */ 
/*     */   
/*     */   private int xpValue;
/*     */ 
/*     */   
/*     */   private EntityPlayer closestPlayer;
/*     */ 
/*     */   
/*     */   private int xpTargetColor;
/*     */   
/*     */   public String player_this_belongs_to;
/*     */   
/*     */   public boolean created_by_bottle_of_enchanting;
/*     */ 
/*     */   
/*     */   public EntityXPOrb(World par1World, double par2, double par4, double par6, int par8) {
/*  34 */     super(par1World);
/*     */     
/*  36 */     setSize(0.25F, 0.25F);
/*  37 */     this.yOffset = this.height / 2.0F;
/*  38 */     setPosition(par2, par4, par6);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.xpValue = par8;
/*     */     
/*  45 */     if (!this.worldObj.isRemote) {
/*     */       
/*  47 */       this.motionX = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  48 */       this.motionY = ((float)(Math.random() * 0.2D) * 2.0F);
/*  49 */       this.motionZ = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerThisBelongsTo(String player_this_belongs_to) {
/*  55 */     this.player_this_belongs_to = "".equals(player_this_belongs_to) ? null : player_this_belongs_to;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityXPOrb(World par1World) {
/*  69 */     super(par1World);
/*  70 */     setSize(0.25F, 0.25F);
/*  71 */     this.yOffset = this.height / 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/*  78 */     float var2 = 0.5F;
/*     */     
/*  80 */     if (var2 < 0.0F)
/*     */     {
/*  82 */       var2 = 0.0F;
/*     */     }
/*     */     
/*  85 */     if (var2 > 1.0F)
/*     */     {
/*  87 */       var2 = 1.0F;
/*     */     }
/*     */     
/*  90 */     int var3 = super.getBrightnessForRender(par1);
/*  91 */     int var4 = var3 & 0xFF;
/*  92 */     int var5 = var3 >> 16 & 0xFF;
/*  93 */     var4 += (int)(var2 * 15.0F * 16.0F);
/*     */     
/*  95 */     if (var4 > 240)
/*     */     {
/*  97 */       var4 = 240;
/*     */     }
/*     */     
/* 100 */     return var4 | var5 << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 108 */     super.onUpdate();
/*     */     
/* 110 */     if (this.field_70532_c > 0)
/*     */     {
/* 112 */       this.field_70532_c--;
/*     */     }
/*     */     
/* 115 */     this.prevPosX = this.posX;
/* 116 */     this.prevPosY = this.posY;
/* 117 */     this.prevPosZ = this.posZ;
/* 118 */     this.motionY -= 0.029999999329447746D;
/*     */ 
/*     */     
/* 121 */     if (this.worldObj.isFullLavaBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), true)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       this.motionY = 0.0D;
/* 129 */       this.motionX *= 0.949999988079071D;
/* 130 */       this.motionZ *= 0.949999988079071D;
/*     */     } 
/*     */ 
/*     */     
/* 134 */     pushOutOfBlocks();
/* 135 */     double var1 = 8.0D;
/*     */     
/* 137 */     if (this.xpTargetColor < this.xpColor - 20 + this.entityId % 100) {
/*     */       
/* 139 */       if (this.player_this_belongs_to != null) {
/*     */         
/* 141 */         Iterator<EntityPlayer> i = this.worldObj.playerEntities.iterator();
/*     */         
/* 143 */         while (i.hasNext()) {
/*     */           
/* 145 */           EntityPlayer player = i.next();
/*     */           
/* 147 */           if (this.player_this_belongs_to.equals(player.username)) {
/*     */             
/* 149 */             this.closestPlayer = player;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 155 */       } else if (this.closestPlayer == null || this.closestPlayer.getDistanceSqToEntity(this) > var1 * var1) {
/*     */ 
/*     */         
/* 158 */         this.closestPlayer = this.worldObj.getClosestPlayerToEntity(this, var1, true);
/*     */       } 
/*     */       
/* 161 */       this.xpTargetColor = this.xpColor;
/*     */     } 
/*     */     
/* 164 */     if (this.closestPlayer != null) {
/*     */       
/* 166 */       double var3 = (this.closestPlayer.posX - this.posX) / var1;
/* 167 */       double var5 = (this.closestPlayer.posY + this.closestPlayer.getEyeHeight() - this.posY) / var1;
/* 168 */       double var7 = (this.closestPlayer.posZ - this.posZ) / var1;
/* 169 */       double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/* 170 */       double var11 = 1.0D - var9;
/*     */       
/* 172 */       if (var11 > 0.0D) {
/*     */         
/* 174 */         var11 *= var11;
/* 175 */         this.motionX += var3 / var9 * var11 * 0.1D;
/* 176 */         this.motionY += var5 / var9 * var11 * 0.1D;
/* 177 */         this.motionZ += var7 / var9 * var11 * 0.1D;
/*     */       } 
/*     */     } 
/*     */     
/* 181 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 182 */     float var13 = 0.98F;
/*     */     
/* 184 */     if (this.onGround) {
/*     */       
/* 186 */       var13 = 0.58800006F;
/* 187 */       int var4 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
/*     */       
/* 189 */       if (var4 > 0)
/*     */       {
/* 191 */         var13 = (Block.blocksList[var4]).slipperiness * 0.98F;
/*     */       }
/*     */     } 
/*     */     
/* 195 */     this.motionX *= var13;
/* 196 */     this.motionY *= 0.9800000190734863D;
/* 197 */     this.motionZ *= var13;
/*     */     
/* 199 */     if (this.onGround)
/*     */     {
/* 201 */       this.motionY *= -0.8999999761581421D;
/*     */     }
/*     */     
/* 204 */     this.xpColor++;
/* 205 */     this.xpOrbAge++;
/*     */     
/* 207 */     if (this.xpOrbAge >= 6000)
/*     */     {
/* 209 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleWaterMovement() {
/* 218 */     return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dealFireDamage(int par1) {
/* 228 */     attackEntityFrom(new Damage(DamageSource.inFire, par1));
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 256 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */     
/* 260 */     if (result == null || result.entityWasDestroyed()) {
/* 261 */       return result;
/*     */     }
/* 263 */     setBeenAttacked();
/*     */     
/* 265 */     result.startTrackingHealth(this.xpOrbHealth);
/*     */     
/* 267 */     this.xpOrbHealth = (int)(this.xpOrbHealth - damage.getAmount());
/*     */     
/* 269 */     result.finishTrackingHealth(this.xpOrbHealth);
/*     */     
/* 271 */     if (this.xpOrbHealth <= 0) {
/*     */       
/* 273 */       setDead();
/* 274 */       result.setEntityWasDestroyed();
/*     */     } 
/*     */     
/* 277 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 285 */     par1NBTTagCompound.setShort("Health", (short)(byte)this.xpOrbHealth);
/* 286 */     par1NBTTagCompound.setShort("Age", (short)this.xpOrbAge);
/* 287 */     par1NBTTagCompound.setShort("Value", (short)this.xpValue);
/*     */     
/* 289 */     if (this.player_this_belongs_to != null) {
/* 290 */       par1NBTTagCompound.setString("player_this_belongs_to", this.player_this_belongs_to);
/*     */     }
/* 292 */     if (this.created_by_bottle_of_enchanting) {
/* 293 */       par1NBTTagCompound.setBoolean("created_by_bottle_of_enchanting", true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 301 */     this.xpOrbHealth = par1NBTTagCompound.getShort("Health") & 0xFF;
/* 302 */     this.xpOrbAge = par1NBTTagCompound.getShort("Age");
/* 303 */     this.xpValue = par1NBTTagCompound.getShort("Value");
/*     */     
/* 305 */     if (par1NBTTagCompound.hasKey("player_this_belongs_to")) {
/* 306 */       this.player_this_belongs_to = par1NBTTagCompound.getString("player_this_belongs_to");
/*     */     }
/* 308 */     this.created_by_bottle_of_enchanting = par1NBTTagCompound.getBoolean("created_by_bottle_of_enchanting");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
/* 316 */     if (!this.worldObj.isRemote) {
/*     */       
/* 318 */       if (!canRaycastToEntity(par1EntityPlayer)) {
/*     */         return;
/*     */       }
/* 321 */       if (this.field_70532_c == 0 && par1EntityPlayer.xpCooldown == 0) {
/*     */         
/* 323 */         if (this.player_this_belongs_to != null && !this.player_this_belongs_to.equals(par1EntityPlayer.username)) {
/*     */           return;
/*     */         }
/* 326 */         par1EntityPlayer.xpCooldown = 2;
/*     */         
/* 328 */         par1EntityPlayer.onItemPickup(this, 1);
/*     */         
/* 330 */         par1EntityPlayer.addExperience(this.xpValue, this.created_by_bottle_of_enchanting);
/* 331 */         setDead();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXpValue() {
/* 341 */     return this.xpValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTextureByXP() {
/* 350 */     return (this.xpValue >= 2477) ? 10 : ((this.xpValue >= 1237) ? 9 : ((this.xpValue >= 617) ? 8 : ((this.xpValue >= 307) ? 7 : ((this.xpValue >= 149) ? 6 : ((this.xpValue >= 73) ? 5 : ((this.xpValue >= 37) ? 4 : ((this.xpValue >= 17) ? 3 : ((this.xpValue >= 7) ? 2 : ((this.xpValue >= 3) ? 1 : 0)))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getXPSplit(int par0) {
/* 358 */     return (par0 >= 2477) ? 2477 : ((par0 >= 1237) ? 1237 : ((par0 >= 617) ? 617 : ((par0 >= 307) ? 307 : ((par0 >= 149) ? 149 : ((par0 >= 73) ? 73 : ((par0 >= 37) ? 37 : ((par0 >= 17) ? 17 : ((par0 >= 7) ? 7 : ((par0 >= 3) ? 3 : 1)))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 366 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 371 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 376 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 381 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityXPOrb setCreatedByBottleOfEnchanting() {
/* 386 */     this.created_by_bottle_of_enchanting = true;
/* 387 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canRaycastToEntity(EntityLivingBase elb) {
/* 392 */     Raycast raycast = (new Raycast(this.worldObj, getCenterPoint())).setPolicies(RaycastPolicies.for_physical_reach_narrow);
/*     */     
/* 394 */     if (raycast.checkForNoBlockCollision(elb.getFootPosPlusFractionOfHeight(0.25F))) {
/* 395 */       return true;
/*     */     }
/* 397 */     if (raycast.checkForNoBlockCollision(elb.getFootPosPlusFractionOfHeight(0.5F))) {
/* 398 */       return true;
/*     */     }
/* 400 */     return raycast.checkForNoBlockCollision(elb.getFootPosPlusFractionOfHeight(0.75F));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityXPOrb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */