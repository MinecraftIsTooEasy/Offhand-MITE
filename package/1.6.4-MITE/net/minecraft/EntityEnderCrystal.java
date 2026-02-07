/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityEnderCrystal
/*     */   extends Entity
/*     */ {
/*     */   public int innerRotation;
/*     */   public int health;
/*     */   
/*     */   public EntityEnderCrystal(World par1World) {
/*  11 */     super(par1World);
/*  12 */     this.preventEntitySpawning = true;
/*  13 */     setSize(2.0F, 2.0F);
/*  14 */     this.yOffset = this.height / 2.0F;
/*  15 */     this.health = 5;
/*  16 */     this.innerRotation = this.rand.nextInt(100000);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityEnderCrystal(World par1World, double par2, double par4, double par6) {
/*  21 */     this(par1World);
/*  22 */     setPosition(par2, par4, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  31 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  36 */     this.dataWatcher.addObject(8, Integer.valueOf(this.health));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  44 */     this.prevPosX = this.posX;
/*  45 */     this.prevPosY = this.posY;
/*  46 */     this.prevPosZ = this.posZ;
/*  47 */     this.innerRotation++;
/*  48 */     this.dataWatcher.updateObject(8, Integer.valueOf(this.health));
/*  49 */     int var1 = MathHelper.floor_double(this.posX);
/*  50 */     int var2 = MathHelper.floor_double(this.posY);
/*  51 */     int var3 = MathHelper.floor_double(this.posZ);
/*     */     
/*  53 */     if (this.worldObj.getBlockId(var1, var2, var3) != Block.fire.blockID)
/*     */     {
/*  55 */       this.worldObj.setBlock(var1, var2, var3, Block.fire.blockID);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  71 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  79 */     return true;
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
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/* 115 */     if (super.isImmuneTo(damage_source)) {
/* 116 */       return true;
/*     */     }
/* 118 */     if (damage_source.isMelee()) {
/*     */       
/* 120 */       ItemStack item_stack = damage_source.getItemAttackedWith();
/*     */       
/* 122 */       if (item_stack != null && item_stack.getItem().isTool()) {
/*     */         
/* 124 */         ItemTool item_tool = item_stack.getItemAsTool();
/*     */         
/* 126 */         if (item_tool.isEffectiveAgainstBlock(Block.blockMithril, 0)) {
/* 127 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 137 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (result == null) {
/* 143 */       return null;
/*     */     }
/* 145 */     this.health = 0;
/*     */     
/* 147 */     if (this.health <= 0) {
/*     */       
/* 149 */       setDead();
/* 150 */       result.setEntityWasDestroyed();
/* 151 */       this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, 6.0F, 6.0F, true);
/*     */     } 
/*     */     
/* 154 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 169 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEnderCrystal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */