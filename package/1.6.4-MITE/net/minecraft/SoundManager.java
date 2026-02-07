/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.main.Main;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import paulscode.sound.SoundSystem;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemException;
/*     */ import paulscode.sound.codecs.CodecJOrbis;
/*     */ import paulscode.sound.codecs.CodecWav;
/*     */ import paulscode.sound.libraries.LibraryLWJGLOpenAL;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoundManager
/*     */   implements ResourceManagerReloadListener
/*     */ {
/*  25 */   private static final String[] field_130084_a = new String[] { "ogg" };
/*     */ 
/*     */   
/*     */   private SoundSystem sndSystem;
/*     */ 
/*     */   
/*     */   private boolean loaded;
/*     */ 
/*     */   
/*     */   private final SoundPool soundPoolSounds;
/*     */ 
/*     */   
/*     */   private final SoundPool soundPoolStreaming;
/*     */ 
/*     */   
/*     */   private final SoundPool soundPoolMusic;
/*     */ 
/*     */   
/*     */   private int latestSoundID;
/*     */ 
/*     */   
/*     */   private final GameSettings options;
/*     */ 
/*     */   
/*     */   private final File fileAssets;
/*     */ 
/*     */   
/*  52 */   private final Set playingSounds = new HashSet();
/*  53 */   private final List field_92072_h = new ArrayList();
/*     */ 
/*     */   
/*  56 */   private Random rand = new Random();
/*     */   
/*     */   private int ticksBeforeMusic;
/*     */   
/*     */   public SoundsMITE sounds_MITE;
/*     */   
/*     */   public static boolean muted;
/*     */   
/*     */   public SoundManager(ResourceManager par1ResourceManager, GameSettings par2GameSettings, File par3File) {
/*  65 */     this.ticksBeforeMusic = this.rand.nextInt(12000);
/*  66 */     this.options = par2GameSettings;
/*  67 */     this.fileAssets = par3File;
/*  68 */     this.soundPoolSounds = new SoundPool(par1ResourceManager, "sound", true);
/*  69 */     this.soundPoolStreaming = new SoundPool(par1ResourceManager, "records", false);
/*  70 */     this.soundPoolMusic = new SoundPool(par1ResourceManager, "music", true);
/*     */ 
/*     */     
/*     */     try {
/*  74 */       SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
/*  75 */       SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
/*  76 */       SoundSystemConfig.setCodec("wav", CodecWav.class);
/*     */     }
/*  78 */     catch (SoundSystemException var5) {
/*     */       
/*  80 */       var5.printStackTrace();
/*  81 */       System.err.println("error linking with the LibraryJavaSound plug-in");
/*     */     } 
/*     */     
/*  84 */     this.sounds_MITE = new SoundsMITE(this);
/*     */     
/*  86 */     loadSounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(ResourceManager par1ResourceManager) {
/*  91 */     stopAllSounds();
/*  92 */     cleanup();
/*     */     
/*  94 */     this.sounds_MITE.load();
/*     */     
/*  96 */     tryToSetLibraryAndCodecs();
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadSounds() {
/* 101 */     if (this.fileAssets.isDirectory()) {
/*     */       
/* 103 */       Collection var1 = FileUtils.listFiles(this.fileAssets, field_130084_a, true);
/* 104 */       Iterator<File> var2 = var1.iterator();
/*     */       
/* 106 */       while (var2.hasNext()) {
/*     */         
/* 108 */         File var3 = var2.next();
/* 109 */         loadSoundFile(var3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadSoundFile(File par1File) {
/* 116 */     String var2 = this.fileAssets.toURI().relativize(par1File.toURI()).getPath();
/* 117 */     int var3 = var2.indexOf("/");
/*     */     
/* 119 */     if (var3 != -1) {
/*     */       
/* 121 */       String var4 = var2.substring(0, var3);
/* 122 */       var2 = var2.substring(var3 + 1);
/*     */       
/* 124 */       if ("sound".equalsIgnoreCase(var4)) {
/*     */         
/* 126 */         addSound(var2);
/*     */       }
/* 128 */       else if ("records".equalsIgnoreCase(var4)) {
/*     */         
/* 130 */         addStreaming(var2);
/*     */       }
/* 132 */       else if ("music".equalsIgnoreCase(var4)) {
/*     */         
/* 134 */         addMusic(var2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void tryToSetLibraryAndCodecs() {
/* 145 */     if (!this.loaded) {
/*     */       
/* 147 */       float var1 = this.options.soundVolume;
/* 148 */       float var2 = this.options.musicVolume;
/* 149 */       this.options.soundVolume = 0.0F;
/* 150 */       this.options.musicVolume = 0.0F;
/* 151 */       this.options.saveOptions();
/*     */ 
/*     */       
/*     */       try {
/* 155 */         (new Thread(new SoundManagerINNER1(this))).start();
/* 156 */         this.options.soundVolume = var1;
/* 157 */         this.options.musicVolume = var2;
/*     */       }
/* 159 */       catch (RuntimeException var4) {
/*     */         
/* 161 */         var4.printStackTrace();
/* 162 */         System.err.println("error starting SoundSystem turning off sounds & music");
/* 163 */         this.options.soundVolume = 0.0F;
/* 164 */         this.options.musicVolume = 0.0F;
/*     */       } 
/*     */       
/* 167 */       this.options.saveOptions();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSoundOptionsChanged() {
/* 176 */     if (this.loaded)
/*     */     {
/* 178 */       if (this.options.musicVolume == 0.0F) {
/*     */         
/* 180 */         this.sndSystem.stop("BgMusic");
/* 181 */         this.sndSystem.stop("streaming");
/*     */       }
/*     */       else {
/*     */         
/* 185 */         this.sndSystem.setVolume("BgMusic", this.options.musicVolume);
/* 186 */         this.sndSystem.setVolume("streaming", this.options.musicVolume);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanup() {
/* 196 */     if (this.loaded) {
/*     */       
/* 198 */       this.sndSystem.cleanup();
/* 199 */       this.loaded = false;
/*     */       
/* 201 */       this.sounds_MITE.loaded = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSound(String par1Str) {
/* 210 */     this.soundPoolSounds.addSound(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStreaming(String par1Str) {
/* 218 */     this.soundPoolStreaming.addSound(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMusic(String par1Str) {
/* 226 */     this.soundPoolMusic.addSound(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playRandomMusicIfReady() {
/* 234 */     if (Main.is_MITE_DS || muted) {
/*     */       return;
/*     */     }
/* 237 */     if (this.loaded && this.options.musicVolume != 0.0F)
/*     */     {
/* 239 */       if (!this.sndSystem.playing("BgMusic") && !this.sndSystem.playing("streaming"))
/*     */       {
/* 241 */         if (this.ticksBeforeMusic > 0) {
/*     */           
/* 243 */           this.ticksBeforeMusic--;
/*     */         }
/*     */         else {
/*     */           
/* 247 */           SoundPoolEntry var1 = this.soundPoolMusic.getRandomSound();
/*     */           
/* 249 */           if (var1 != null) {
/*     */             
/* 251 */             this.ticksBeforeMusic = this.rand.nextInt(12000) + 12000;
/* 252 */             this.sndSystem.backgroundMusic("BgMusic", var1.getSoundUrl(), var1.getSoundName(), false);
/* 253 */             this.sndSystem.setVolume("BgMusic", this.options.musicVolume);
/* 254 */             this.sndSystem.play("BgMusic");
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListener(EntityLivingBase par1EntityLivingBase, float par2) {
/* 266 */     if (this.loaded && this.options.soundVolume != 0.0F && par1EntityLivingBase != null) {
/*     */       
/* 268 */       float var3 = par1EntityLivingBase.prevRotationPitch + (par1EntityLivingBase.rotationPitch - par1EntityLivingBase.prevRotationPitch) * par2;
/* 269 */       float var4 = par1EntityLivingBase.prevRotationYaw + (par1EntityLivingBase.rotationYaw - par1EntityLivingBase.prevRotationYaw) * par2;
/* 270 */       double var5 = par1EntityLivingBase.prevPosX + (par1EntityLivingBase.posX - par1EntityLivingBase.prevPosX) * par2;
/* 271 */       double var7 = par1EntityLivingBase.prevPosY + (par1EntityLivingBase.posY - par1EntityLivingBase.prevPosY) * par2;
/* 272 */       double var9 = par1EntityLivingBase.prevPosZ + (par1EntityLivingBase.posZ - par1EntityLivingBase.prevPosZ) * par2;
/* 273 */       float var11 = MathHelper.cos(-var4 * 0.017453292F - 3.1415927F);
/* 274 */       float var12 = MathHelper.sin(-var4 * 0.017453292F - 3.1415927F);
/* 275 */       float var13 = -var12;
/* 276 */       float var14 = -MathHelper.sin(-var3 * 0.017453292F - 3.1415927F);
/* 277 */       float var15 = -var11;
/* 278 */       float var16 = 0.0F;
/* 279 */       float var17 = 1.0F;
/* 280 */       float var18 = 0.0F;
/* 281 */       this.sndSystem.setListenerPosition((float)var5, (float)var7, (float)var9);
/* 282 */       this.sndSystem.setListenerOrientation(var13, var14, var15, var16, var17, var18);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopAllSounds() {
/* 291 */     if (this.loaded) {
/*     */       
/* 293 */       Iterator<String> var1 = this.playingSounds.iterator();
/*     */       
/* 295 */       while (var1.hasNext()) {
/*     */         
/* 297 */         String var2 = var1.next();
/* 298 */         this.sndSystem.stop(var2);
/*     */       } 
/*     */       
/* 301 */       this.playingSounds.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playStreaming(String par1Str, float par2, float par3, float par4) {
/* 307 */     if (Main.is_MITE_DS || muted) {
/*     */       return;
/*     */     }
/* 310 */     if (this.loaded && (this.options.soundVolume != 0.0F || par1Str == null)) {
/*     */       
/* 312 */       String var5 = "streaming";
/*     */       
/* 314 */       if (this.sndSystem.playing(var5))
/*     */       {
/* 316 */         this.sndSystem.stop(var5);
/*     */       }
/*     */       
/* 319 */       if (par1Str != null) {
/*     */         
/* 321 */         SoundPoolEntry var6 = this.soundPoolStreaming.getRandomSoundFromSoundPool(par1Str);
/*     */         
/* 323 */         if (var6 != null) {
/*     */           
/* 325 */           if (this.sndSystem.playing("BgMusic"))
/*     */           {
/* 327 */             this.sndSystem.stop("BgMusic");
/*     */           }
/*     */           
/* 330 */           this.sndSystem.newStreamingSource(true, var5, var6.getSoundUrl(), var6.getSoundName(), false, par2, par3, par4, 2, 64.0F);
/* 331 */           this.sndSystem.setVolume(var5, 0.5F * this.options.soundVolume);
/* 332 */           this.sndSystem.play(var5);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSoundLocation(Entity par1Entity) {
/* 343 */     updateSoundLocation(par1Entity, par1Entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSoundLocation(Entity par1Entity, Entity par2Entity) {
/* 352 */     String var3 = "entity_" + par1Entity.entityId;
/*     */     
/* 354 */     if (this.playingSounds.contains(var3))
/*     */     {
/* 356 */       if (this.sndSystem.playing(var3)) {
/*     */         
/* 358 */         this.sndSystem.setPosition(var3, (float)par2Entity.posX, (float)par2Entity.posY, (float)par2Entity.posZ);
/* 359 */         this.sndSystem.setVelocity(var3, (float)par2Entity.motionX, (float)par2Entity.motionY, (float)par2Entity.motionZ);
/*     */       }
/*     */       else {
/*     */         
/* 363 */         this.playingSounds.remove(var3);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEntitySoundPlaying(Entity par1Entity) {
/* 373 */     if (par1Entity != null && this.loaded) {
/*     */       
/* 375 */       String var2 = "entity_" + par1Entity.entityId;
/* 376 */       return this.sndSystem.playing(var2);
/*     */     } 
/*     */ 
/*     */     
/* 380 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopEntitySound(Entity par1Entity) {
/* 389 */     if (par1Entity != null && this.loaded) {
/*     */       
/* 391 */       String var2 = "entity_" + par1Entity.entityId;
/*     */       
/* 393 */       if (this.playingSounds.contains(var2)) {
/*     */         
/* 395 */         if (this.sndSystem.playing(var2))
/*     */         {
/* 397 */           this.sndSystem.stop(var2);
/*     */         }
/*     */         
/* 400 */         this.playingSounds.remove(var2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntitySoundVolume(Entity par1Entity, float par2) {
/* 411 */     if (par1Entity != null && this.loaded && this.options.soundVolume != 0.0F) {
/*     */       
/* 413 */       String var3 = "entity_" + par1Entity.entityId;
/*     */       
/* 415 */       if (this.sndSystem.playing(var3))
/*     */       {
/* 417 */         this.sndSystem.setVolume(var3, par2 * this.options.soundVolume);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntitySoundPitch(Entity par1Entity, float par2) {
/* 427 */     if (par1Entity != null && this.loaded && this.options.soundVolume != 0.0F) {
/*     */       
/* 429 */       String var3 = "entity_" + par1Entity.entityId;
/*     */       
/* 431 */       if (this.sndSystem.playing(var3))
/*     */       {
/* 433 */         this.sndSystem.setPitch(var3, par2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void soundFailed(String path) {
/* 440 */     Minecraft.setErrorMessage("SoundManager: \"" + path + "\" not found in sound pool!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playEntitySound(String par1Str, Entity par2Entity, float par3, float par4, boolean par5) {
/* 450 */     if (Main.is_MITE_DS || muted) {
/*     */       return;
/*     */     }
/* 453 */     if (this.loaded && (this.options.soundVolume != 0.0F || par1Str == null) && par2Entity != null) {
/*     */       
/* 455 */       String var6 = "entity_" + par2Entity.entityId;
/*     */       
/* 457 */       if (this.playingSounds.contains(var6)) {
/*     */         
/* 459 */         updateSoundLocation(par2Entity);
/*     */       }
/*     */       else {
/*     */         
/* 463 */         if (this.sndSystem.playing(var6))
/*     */         {
/* 465 */           this.sndSystem.stop(var6);
/*     */         }
/*     */         
/* 468 */         if (par1Str != null) {
/*     */           
/* 470 */           SoundPoolEntry var7 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);
/*     */           
/* 472 */           if (var7 == null) {
/* 473 */             soundFailed(par1Str);
/*     */           }
/* 475 */           if (var7 != null && par3 > 0.0F) {
/*     */             
/* 477 */             float var8 = 16.0F;
/*     */             
/* 479 */             if (par3 > 1.0F)
/*     */             {
/* 481 */               var8 *= par3;
/*     */             }
/*     */             
/* 484 */             this.sndSystem.newSource(par5, var6, var7.getSoundUrl(), var7.getSoundName(), false, (float)par2Entity.posX, (float)par2Entity.posY, (float)par2Entity.posZ, 2, var8);
/* 485 */             this.sndSystem.setLooping(var6, true);
/* 486 */             this.sndSystem.setPitch(var6, par4);
/*     */             
/* 488 */             if (par3 > 1.0F)
/*     */             {
/* 490 */               par3 = 1.0F;
/*     */             }
/*     */             
/* 493 */             this.sndSystem.setVolume(var6, par3 * this.options.soundVolume);
/* 494 */             this.sndSystem.setVelocity(var6, (float)par2Entity.motionX, (float)par2Entity.motionY, (float)par2Entity.motionZ);
/* 495 */             this.sndSystem.play(var6);
/* 496 */             this.playingSounds.add(var6);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(String par1Str, float par2, float par3, float par4, float par5, float par6) {
/* 508 */     if (Main.is_MITE_DS || muted) {
/*     */       return;
/*     */     }
/* 511 */     if (this.loaded && this.options.soundVolume != 0.0F) {
/*     */       
/* 513 */       SoundPoolEntry var7 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);
/*     */       
/* 515 */       if (var7 == null) {
/* 516 */         soundFailed(par1Str);
/*     */       }
/* 518 */       if (var7 != null && par5 > 0.0F) {
/*     */         
/* 520 */         this.latestSoundID = (this.latestSoundID + 1) % 256;
/* 521 */         String var8 = "sound_" + this.latestSoundID;
/* 522 */         float var9 = 16.0F;
/*     */         
/* 524 */         if (par5 > 1.0F)
/*     */         {
/* 526 */           var9 *= par5;
/*     */         }
/*     */         
/* 529 */         this.sndSystem.newSource((par5 > 1.0F), var8, var7.getSoundUrl(), var7.getSoundName(), false, par2, par3, par4, 2, var9);
/*     */         
/* 531 */         if (par5 > 1.0F)
/*     */         {
/* 533 */           par5 = 1.0F;
/*     */         }
/*     */         
/* 536 */         this.sndSystem.setPitch(var8, par6);
/* 537 */         this.sndSystem.setVolume(var8, par5 * this.options.soundVolume);
/* 538 */         this.sndSystem.play(var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playLongDistanceSound(String par1Str, float par2, float par3, float par4, float par5, float par6) {
/* 545 */     if (Main.is_MITE_DS || muted) {
/*     */       return;
/*     */     }
/* 548 */     if (this.loaded && this.options.soundVolume != 0.0F) {
/*     */ 
/*     */       
/* 551 */       double distance_to_player = (Minecraft.getMinecraft()).thePlayer.getDistance(par2, par3, par4);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 556 */       float min_distance = 16.0F;
/* 557 */       float max_distance = 64.0F;
/*     */       
/* 559 */       if (distance_to_player < min_distance || distance_to_player > max_distance) {
/*     */         return;
/*     */       }
/* 562 */       SoundPoolEntry var7 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);
/*     */       
/* 564 */       if (var7 == null) {
/* 565 */         soundFailed(par1Str);
/*     */       }
/* 567 */       if (var7 != null && par5 > 0.0F) {
/*     */         
/* 569 */         this.latestSoundID = (this.latestSoundID + 1) % 256;
/* 570 */         String var8 = "sound_" + this.latestSoundID;
/*     */         
/* 572 */         float attenuation = 1.0F / ((float)distance_to_player - min_distance + 1.0F);
/* 573 */         float volume = par5 * attenuation;
/*     */ 
/*     */         
/* 576 */         this.sndSystem.newSource((par5 > 1.0F), var8, var7.getSoundUrl(), var7.getSoundName(), false, par2, par3, par4, 0, 0.0F);
/*     */         
/* 578 */         this.sndSystem.setPitch(var8, par6);
/* 579 */         this.sndSystem.setVolume(var8, volume * this.options.soundVolume);
/* 580 */         this.sndSystem.play(var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSoundFX(String par1Str, float par2, float par3) {
/* 591 */     if (Main.is_MITE_DS && !par1Str.equals("random.click")) {
/*     */       return;
/*     */     }
/* 594 */     if (muted) {
/*     */       return;
/*     */     }
/* 597 */     if (this.loaded && this.options.soundVolume != 0.0F) {
/*     */       
/* 599 */       SoundPoolEntry var4 = this.soundPoolSounds.getRandomSoundFromSoundPool(par1Str);
/*     */       
/* 601 */       if (var4 == null) {
/* 602 */         soundFailed(par1Str);
/*     */       }
/* 604 */       if (var4 != null && par2 > 0.0F) {
/*     */         
/* 606 */         this.latestSoundID = (this.latestSoundID + 1) % 256;
/* 607 */         String var5 = "sound_" + this.latestSoundID;
/* 608 */         this.sndSystem.newSource(false, var5, var4.getSoundUrl(), var4.getSoundName(), false, 0.0F, 0.0F, 0.0F, 0, 0.0F);
/*     */         
/* 610 */         if (par2 > 1.0F)
/*     */         {
/* 612 */           par2 = 1.0F;
/*     */         }
/*     */         
/* 615 */         par2 *= 0.25F;
/* 616 */         this.sndSystem.setPitch(var5, par3);
/* 617 */         this.sndSystem.setVolume(var5, par2 * this.options.soundVolume);
/* 618 */         this.sndSystem.play(var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseAllSounds() {
/* 628 */     Iterator<String> var1 = this.playingSounds.iterator();
/*     */     
/* 630 */     while (var1.hasNext()) {
/*     */       
/* 632 */       String var2 = var1.next();
/* 633 */       this.sndSystem.pause(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumeAllSounds() {
/* 642 */     Iterator<String> var1 = this.playingSounds.iterator();
/*     */     
/* 644 */     while (var1.hasNext()) {
/*     */       
/* 646 */       String var2 = var1.next();
/* 647 */       this.sndSystem.play(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_92071_g() {
/* 653 */     if (!this.field_92072_h.isEmpty()) {
/*     */       
/* 655 */       Iterator<ScheduledSound> var1 = this.field_92072_h.iterator();
/*     */       
/* 657 */       while (var1.hasNext()) {
/*     */         
/* 659 */         ScheduledSound var2 = var1.next();
/* 660 */         var2.field_92064_g--;
/*     */         
/* 662 */         if (var2.field_92064_g <= 0) {
/*     */           
/* 664 */           playSound(var2.field_92069_a, var2.field_92067_b, var2.field_92068_c, var2.field_92065_d, var2.field_92066_e, var2.field_92063_f);
/* 665 */           var1.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_92070_a(String par1Str, float par2, float par3, float par4, float par5, float par6, int par7) {
/* 673 */     this.field_92072_h.add(new ScheduledSound(par1Str, par2, par3, par4, par5, par6, par7));
/*     */   }
/*     */ 
/*     */   
/*     */   static SoundSystem func_130080_a(SoundManager par0SoundManager, SoundSystem par1SoundSystem) {
/* 678 */     return par0SoundManager.sndSystem = par1SoundSystem;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean func_130082_a(SoundManager par0SoundManager, boolean par1) {
/* 683 */     return par0SoundManager.loaded = par1;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SoundManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */