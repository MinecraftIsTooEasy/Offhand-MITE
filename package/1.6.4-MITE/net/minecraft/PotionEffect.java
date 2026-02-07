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
/*     */ 
/*     */ public class PotionEffect
/*     */ {
/*     */   private int potionID;
/*     */   private int duration;
/*     */   private int amplifier;
/*     */   private boolean isSplashPotion;
/*     */   private boolean isAmbient;
/*     */   private boolean isPotionDurationMax;
/*     */   
/*     */   public PotionEffect(int par1, int par2) {
/*  25 */     this(par1, par2, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect(int par1, int par2, int par3) {
/*  30 */     this(par1, par2, par3, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect(int par1, int par2, int par3, boolean par4) {
/*  35 */     this.potionID = par1;
/*  36 */     this.duration = par2;
/*  37 */     this.amplifier = par3;
/*  38 */     this.isAmbient = par4;
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect(PotionEffect par1PotionEffect) {
/*  43 */     this.potionID = par1PotionEffect.potionID;
/*  44 */     this.duration = par1PotionEffect.duration;
/*  45 */     this.amplifier = par1PotionEffect.amplifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void combine(PotionEffect par1PotionEffect) {
/*  54 */     if (this.potionID != par1PotionEffect.potionID)
/*     */     {
/*  56 */       System.err.println("This method should only be called for matching effects!");
/*     */     }
/*     */     
/*  59 */     if (par1PotionEffect.amplifier > this.amplifier) {
/*     */       
/*  61 */       this.amplifier = par1PotionEffect.amplifier;
/*  62 */       this.duration = par1PotionEffect.duration;
/*     */     }
/*  64 */     else if (par1PotionEffect.amplifier == this.amplifier && this.duration < par1PotionEffect.duration) {
/*     */       
/*  66 */       this.duration = par1PotionEffect.duration;
/*     */     }
/*  68 */     else if (!par1PotionEffect.isAmbient && this.isAmbient) {
/*     */       
/*  70 */       this.isAmbient = par1PotionEffect.isAmbient;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPotionID() {
/*  79 */     return this.potionID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuration() {
/*  84 */     return this.duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAmplifier() {
/*  89 */     return this.amplifier;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PotionEffect setDuration(int duration) {
/*  95 */     this.duration = duration;
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect setAmplifier(int amplifier) {
/* 101 */     this.amplifier = amplifier;
/* 102 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSplashPotion(boolean par1) {
/* 110 */     this.isSplashPotion = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsAmbient() {
/* 118 */     return this.isAmbient;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onUpdate(EntityLivingBase par1EntityLivingBase) {
/* 123 */     if (this.duration > 0) {
/*     */       
/* 125 */       if (Potion.potionTypes[this.potionID].isReady(this.duration, this.amplifier))
/*     */       {
/* 127 */         performEffect(par1EntityLivingBase);
/*     */       }
/*     */       
/* 130 */       deincrementDuration();
/*     */     } 
/*     */     
/* 133 */     return (this.duration > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private int deincrementDuration() {
/* 138 */     return --this.duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performEffect(EntityLivingBase par1EntityLivingBase) {
/* 143 */     if (this.duration > 0)
/*     */     {
/* 145 */       Potion.potionTypes[this.potionID].performEffect(par1EntityLivingBase, this.amplifier);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEffectName() {
/* 151 */     return Potion.potionTypes[this.potionID].getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Potion getPotion() {
/* 156 */     return Potion.potionTypes[this.potionID];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEffectInterval() {
/* 161 */     return getPotion().getEffectInterval(this.amplifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 166 */     return this.potionID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 171 */     String var1 = "";
/*     */     
/* 173 */     if (getAmplifier() > 0) {
/*     */       
/* 175 */       var1 = getEffectName() + " x " + (getAmplifier() + 1) + ", Duration: " + getDuration();
/*     */     }
/*     */     else {
/*     */       
/* 179 */       var1 = getEffectName() + ", Duration: " + getDuration();
/*     */     } 
/*     */     
/* 182 */     if (this.isSplashPotion)
/*     */     {
/* 184 */       var1 = var1 + ", Splash: true";
/*     */     }
/*     */     
/* 187 */     return Potion.potionTypes[this.potionID].isUsable() ? ("(" + var1 + ")") : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/* 192 */     if (!(par1Obj instanceof PotionEffect))
/*     */     {
/* 194 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 198 */     PotionEffect var2 = (PotionEffect)par1Obj;
/* 199 */     return (this.potionID == var2.potionID && this.amplifier == var2.amplifier && this.duration == var2.duration && this.isSplashPotion == var2.isSplashPotion && this.isAmbient == var2.isAmbient);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeCustomPotionEffectToNBT(NBTTagCompound par1NBTTagCompound) {
/* 208 */     par1NBTTagCompound.setByte("Id", (byte)getPotionID());
/* 209 */     par1NBTTagCompound.setByte("Amplifier", (byte)getAmplifier());
/* 210 */     par1NBTTagCompound.setInteger("Duration", getDuration());
/* 211 */     par1NBTTagCompound.setBoolean("Ambient", getIsAmbient());
/* 212 */     return par1NBTTagCompound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PotionEffect readCustomPotionEffectFromNBT(NBTTagCompound par0NBTTagCompound) {
/* 220 */     byte var1 = par0NBTTagCompound.getByte("Id");
/* 221 */     byte var2 = par0NBTTagCompound.getByte("Amplifier");
/* 222 */     int var3 = par0NBTTagCompound.getInteger("Duration");
/* 223 */     boolean var4 = par0NBTTagCompound.getBoolean("Ambient");
/* 224 */     return new PotionEffect(var1, var3, var2, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPotionDurationMax(boolean par1) {
/* 232 */     this.isPotionDurationMax = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsPotionDurationMax() {
/* 237 */     return this.isPotionDurationMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect scaleDuration(float factor) {
/* 242 */     if (factor < 0.0F) {
/* 243 */       Minecraft.setErrorMessage("scaleDuration: factor is less than 0.0F");
/*     */     }
/* 245 */     this.duration = (int)(this.duration * factor);
/* 246 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PotionEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */