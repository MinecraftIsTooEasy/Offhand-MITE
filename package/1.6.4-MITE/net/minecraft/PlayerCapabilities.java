/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerCapabilities
/*     */ {
/*     */   public boolean disableDamage;
/*     */   public boolean isFlying;
/*     */   public boolean allowFlying;
/*     */   public boolean isCreativeMode;
/*     */   public boolean allowEdit = true;
/*  21 */   private float flySpeed = 0.05F;
/*  22 */   private float walkSpeed = 0.1F;
/*     */   
/*     */   public EntityPlayer player;
/*     */ 
/*     */   
/*     */   public PlayerCapabilities(EntityPlayer player) {
/*  28 */     this.player = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeCapabilitiesToNBT(NBTTagCompound par1NBTTagCompound) {
/*  33 */     NBTTagCompound var2 = new NBTTagCompound();
/*  34 */     var2.setBoolean("invulnerable", this.disableDamage);
/*  35 */     var2.setBoolean("flying", this.isFlying);
/*  36 */     var2.setBoolean("mayfly", this.allowFlying);
/*  37 */     var2.setBoolean("instabuild", this.isCreativeMode);
/*  38 */     var2.setBoolean("mayBuild", this.allowEdit);
/*  39 */     var2.setFloat("flySpeed", this.flySpeed);
/*  40 */     var2.setFloat("walkSpeed", this.walkSpeed);
/*  41 */     par1NBTTagCompound.setTag("abilities", var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readCapabilitiesFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  46 */     if (par1NBTTagCompound.hasKey("abilities")) {
/*     */       
/*  48 */       NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("abilities");
/*  49 */       this.disableDamage = var2.getBoolean("invulnerable");
/*  50 */       this.isFlying = var2.getBoolean("flying");
/*  51 */       this.allowFlying = var2.getBoolean("mayfly");
/*  52 */       this.isCreativeMode = var2.getBoolean("instabuild");
/*     */       
/*  54 */       if (var2.hasKey("flySpeed")) {
/*     */         
/*  56 */         this.flySpeed = var2.getFloat("flySpeed");
/*  57 */         this.walkSpeed = var2.getFloat("walkSpeed");
/*     */       } 
/*     */       
/*  60 */       if (var2.hasKey("mayBuild"))
/*     */       {
/*  62 */         this.allowEdit = var2.getBoolean("mayBuild");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFlySpeed() {
/*  69 */     return this.flySpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlySpeed(float par1) {
/*  74 */     this.flySpeed = par1;
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
/*     */   public float getWalkSpeed() {
/* 103 */     float speed_boost_or_slow_down_factor = this.player.getSpeedBoostOrSlowDownFactor();
/*     */     
/* 105 */     return (this.player.hasFoodEnergy() ? this.walkSpeed : (this.walkSpeed * 0.75F)) * EnchantmentHelper.getSpeedModifier(this.player) * speed_boost_or_slow_down_factor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerWalkSpeed(float par1) {
/* 110 */     this.walkSpeed = par1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */