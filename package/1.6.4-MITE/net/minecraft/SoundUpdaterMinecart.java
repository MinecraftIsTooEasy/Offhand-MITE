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
/*     */ public class SoundUpdaterMinecart
/*     */   implements IUpdatePlayerListBox
/*     */ {
/*     */   private final SoundManager theSoundManager;
/*     */   private final EntityMinecart theMinecart;
/*     */   private final ClientPlayer thePlayer;
/*     */   private boolean playerSPRidingMinecart;
/*     */   private boolean minecartIsDead;
/*     */   private boolean minecartIsMoving;
/*     */   private boolean silent;
/*     */   private float minecartSoundPitch;
/*     */   private float minecartMoveSoundVolume;
/*     */   private float minecartRideSoundVolume;
/*     */   private double minecartSpeed;
/*     */   
/*     */   public SoundUpdaterMinecart(SoundManager soundManager, EntityMinecart entityMinecart, ClientPlayer clientPlayer) {
/*  29 */     this.theSoundManager = soundManager;
/*  30 */     this.theMinecart = entityMinecart;
/*  31 */     this.thePlayer = clientPlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  36 */     boolean bool = false;
/*  37 */     boolean bool1 = this.playerSPRidingMinecart;
/*  38 */     boolean bool2 = this.minecartIsDead;
/*  39 */     boolean bool3 = this.minecartIsMoving;
/*  40 */     float f1 = this.minecartMoveSoundVolume;
/*  41 */     float f2 = this.minecartSoundPitch;
/*  42 */     float f3 = this.minecartRideSoundVolume;
/*  43 */     double d = this.minecartSpeed;
/*     */     
/*  45 */     this.playerSPRidingMinecart = (this.thePlayer != null && this.theMinecart.riddenByEntity == this.thePlayer);
/*  46 */     this.minecartIsDead = this.theMinecart.isDead;
/*  47 */     this.minecartSpeed = MathHelper.sqrt_double(this.theMinecart.motionX * this.theMinecart.motionX + this.theMinecart.motionZ * this.theMinecart.motionZ);
/*  48 */     this.minecartIsMoving = (this.minecartSpeed >= 0.01D);
/*     */     
/*  50 */     if (bool1 && !this.playerSPRidingMinecart) {
/*  51 */       this.theSoundManager.stopEntitySound(this.thePlayer);
/*     */     }
/*     */     
/*  54 */     if (this.minecartIsDead || (!this.silent && this.minecartMoveSoundVolume == 0.0F && this.minecartRideSoundVolume == 0.0F)) {
/*  55 */       if (!bool2) {
/*  56 */         this.theSoundManager.stopEntitySound(this.theMinecart);
/*  57 */         if (bool1 || this.playerSPRidingMinecart) this.theSoundManager.stopEntitySound(this.thePlayer); 
/*     */       } 
/*  59 */       this.silent = true;
/*  60 */       if (this.minecartIsDead)
/*     */         return; 
/*     */     } 
/*  63 */     if (!this.theSoundManager.isEntitySoundPlaying(this.theMinecart) && this.minecartMoveSoundVolume > 0.0F) {
/*  64 */       this.theSoundManager.playEntitySound("minecart.base", this.theMinecart, this.minecartMoveSoundVolume, this.minecartSoundPitch, false);
/*  65 */       this.silent = false;
/*  66 */       bool = true;
/*     */     } 
/*     */     
/*  69 */     if (this.playerSPRidingMinecart && !this.theSoundManager.isEntitySoundPlaying(this.thePlayer) && this.minecartRideSoundVolume > 0.0F) {
/*  70 */       this.theSoundManager.playEntitySound("minecart.inside", this.thePlayer, this.minecartRideSoundVolume, 1.0F, true);
/*  71 */       this.silent = false;
/*  72 */       bool = true;
/*     */     } 
/*     */     
/*  75 */     if (this.minecartIsMoving) {
/*  76 */       if (this.minecartSoundPitch < 1.0F) this.minecartSoundPitch += 0.0025F; 
/*  77 */       if (this.minecartSoundPitch > 1.0F) this.minecartSoundPitch = 1.0F;
/*     */       
/*  79 */       float f = MathHelper.clamp_float((float)this.minecartSpeed, 0.0F, 4.0F) / 4.0F;
/*  80 */       this.minecartRideSoundVolume = 0.0F + f * 0.75F;
/*     */       
/*  82 */       f = MathHelper.clamp_float(f * 2.0F, 0.0F, 1.0F);
/*  83 */       this.minecartMoveSoundVolume = 0.0F + f * 0.7F;
/*  84 */     } else if (bool3) {
/*  85 */       this.minecartMoveSoundVolume = 0.0F;
/*  86 */       this.minecartSoundPitch = 0.0F;
/*  87 */       this.minecartRideSoundVolume = 0.0F;
/*     */     } 
/*     */     
/*  90 */     if (!this.silent) {
/*  91 */       if (this.minecartSoundPitch != f2) this.theSoundManager.setEntitySoundPitch(this.theMinecart, this.minecartSoundPitch); 
/*  92 */       if (this.minecartMoveSoundVolume != f1) this.theSoundManager.setEntitySoundVolume(this.theMinecart, this.minecartMoveSoundVolume); 
/*  93 */       if (this.minecartRideSoundVolume != f3) this.theSoundManager.setEntitySoundVolume(this.thePlayer, this.minecartRideSoundVolume);
/*     */     
/*     */     } 
/*  96 */     if (!bool && (this.minecartMoveSoundVolume > 0.0F || this.minecartRideSoundVolume > 0.0F)) {
/*  97 */       this.theSoundManager.updateSoundLocation(this.theMinecart);
/*  98 */       if (this.playerSPRidingMinecart) {
/*  99 */         this.theSoundManager.updateSoundLocation(this.thePlayer, this.theMinecart);
/*     */       }
/*     */     } else {
/* 102 */       if (this.theSoundManager.isEntitySoundPlaying(this.theMinecart)) {
/* 103 */         this.theSoundManager.stopEntitySound(this.theMinecart);
/*     */       }
/* 105 */       if (this.playerSPRidingMinecart && this.theSoundManager.isEntitySoundPlaying(this.thePlayer))
/* 106 */         this.theSoundManager.stopEntitySound(this.thePlayer); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SoundUpdaterMinecart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */