/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityTNTPrimed
/*     */   extends Entity
/*     */ {
/*     */   public int fuse;
/*     */   private EntityLivingBase tntPlacedBy;
/*     */   
/*     */   public EntityTNTPrimed(World par1World) {
/*  11 */     super(par1World);
/*  12 */     this.preventEntitySpawning = true;
/*  13 */     setSize(0.98F, 0.98F);
/*  14 */     this.yOffset = this.height / 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityTNTPrimed(World par1World, double par2, double par4, double par6, EntityLivingBase par8EntityLivingBase) {
/*  19 */     this(par1World);
/*  20 */     setPosition(par2, par4, par6);
/*  21 */     float var9 = (float)(Math.random() * Math.PI * 2.0D);
/*  22 */     this.motionX = (-((float)Math.sin(var9)) * 0.02F);
/*  23 */     this.motionY = 0.20000000298023224D;
/*  24 */     this.motionZ = (-((float)Math.cos(var9)) * 0.02F);
/*  25 */     this.motionX = this.motionZ = 0.0D;
/*  26 */     this.fuse = 80;
/*  27 */     this.prevPosX = par2;
/*  28 */     this.prevPosY = par4;
/*  29 */     this.prevPosZ = par6;
/*  30 */     this.tntPlacedBy = par8EntityLivingBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  49 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  57 */     this.prevPosX = this.posX;
/*  58 */     this.prevPosY = this.posY;
/*  59 */     this.prevPosZ = this.posZ;
/*  60 */     this.motionY -= 0.03999999910593033D;
/*  61 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  62 */     this.motionX *= 0.9800000190734863D;
/*  63 */     this.motionY *= 0.9800000190734863D;
/*  64 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/*  66 */     if (this.onGround) {
/*     */       
/*  68 */       this.motionX *= 0.699999988079071D;
/*  69 */       this.motionZ *= 0.699999988079071D;
/*  70 */       this.motionY *= -0.5D;
/*     */     } 
/*     */     
/*  73 */     if (this.fuse-- <= 0) {
/*     */       
/*  75 */       setDead();
/*     */       
/*  77 */       if (!this.worldObj.isRemote)
/*     */       {
/*  79 */         explode();
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  85 */       this.worldObj.spawnParticle(EnumParticle.smoke, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void explode() {
/*  91 */     float var1 = 4.0F;
/*     */     
/*  93 */     var1 = 2.0F;
/*     */     
/*  95 */     this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, var1, var1, true);
/*     */ 
/*     */     
/*  98 */     entityFX(EnumEntityFX.frags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 106 */     par1NBTTagCompound.setByte("Fuse", (byte)this.fuse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 114 */     this.fuse = par1NBTTagCompound.getByte("Fuse");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 119 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase getTntPlacedBy() {
/* 127 */     return this.tntPlacedBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFragParticle() {
/* 132 */     return Block.tnt.blockID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 147 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityTNTPrimed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */