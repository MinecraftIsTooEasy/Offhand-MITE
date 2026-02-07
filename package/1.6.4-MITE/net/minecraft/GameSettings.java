/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintWriter;
/*     */ import net.minecraft.client.main.Main;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.Display;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameSettings
/*     */ {
/*     */   public static final int PERFORMANCE_ENHANCED = 3;
/*  19 */   private static final String[] RENDER_DISTANCES = new String[] { "options.renderDistance.far", "options.renderDistance.normal", "options.renderDistance.short", "options.renderDistance.tiny" };
/*  20 */   private static final String[] DIFFICULTIES = new String[] { "options.difficulty.peaceful", "options.difficulty.easy", "options.difficulty.normal", "options.difficulty.hard" };
/*     */ 
/*     */   
/*  23 */   private static final String[] GUISCALES = new String[] { "options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large" };
/*  24 */   private static final String[] CHAT_VISIBILITIES = new String[] { "options.chat.visibility.full", "options.chat.visibility.system", "options.chat.visibility.hidden" };
/*  25 */   private static final String[] PARTICLES = new String[] { "options.particles.all", "options.particles.decreased", "options.particles.minimal" };
/*     */ 
/*     */   
/*  28 */   private static final String[] LIMIT_FRAMERATES = new String[] { "performance.max", "performance.balanced", "performance.powersaver", "performance.enhanced" };
/*  29 */   private static final String[] AMBIENT_OCCLUSIONS = new String[] { "options.ao.off", "options.ao.min", "options.ao.max" };
/*  30 */   public float musicVolume = 1.0F;
/*  31 */   public float soundVolume = 1.0F;
/*  32 */   public float mouseSensitivity = 0.5F;
/*     */   
/*     */   public boolean invertMouse;
/*     */   
/*     */   private int renderDistance;
/*     */   
/*     */   public boolean viewBobbing = true;
/*     */   public boolean anaglyph;
/*     */   public boolean advancedOpengl;
/*  41 */   public int limitFramerate = 3;
/*     */ 
/*     */   
/*     */   private boolean fancyGraphics = true;
/*     */   
/*  46 */   public int ambientOcclusion = 2;
/*     */ 
/*     */   
/*     */   public boolean clouds = true;
/*     */ 
/*     */   
/*  52 */   public String skin = "Default";
/*     */   public int chatVisibility;
/*     */   public boolean chatColours = true;
/*     */   public boolean chatLinks = true;
/*     */   public boolean chatLinksPrompt = true;
/*  57 */   public float chatOpacity = 1.0F;
/*     */   
/*     */   public boolean serverTextures = true;
/*     */   
/*     */   public boolean snooperEnabled = true;
/*     */   
/*     */   private boolean fullScreen;
/*     */   
/*     */   private boolean enableVsync = true;
/*     */   
/*     */   public boolean hideServerAddress;
/*     */   
/*     */   public boolean advancedItemTooltips;
/*     */   
/*     */   public boolean pauseOnLostFocus = true;
/*     */   
/*     */   public boolean showCape = true;
/*     */   
/*     */   public boolean touchscreen;
/*     */   
/*     */   public int overrideWidth;
/*     */   public int overrideHeight;
/*     */   public boolean heldItemTooltips = true;
/*  80 */   public float chatScale = 1.0F;
/*  81 */   public float chatWidth = 1.0F;
/*  82 */   public float chatHeightUnfocused = 0.44366196F;
/*  83 */   public float chatHeightFocused = 1.0F;
/*  84 */   public KeyBinding keyBindForward = new KeyBinding("key.forward", 17);
/*  85 */   public KeyBinding keyBindLeft = new KeyBinding("key.left", 30);
/*  86 */   public KeyBinding keyBindBack = new KeyBinding("key.back", 31);
/*  87 */   public KeyBinding keyBindRight = new KeyBinding("key.right", 32);
/*  88 */   public KeyBinding keyBindJump = new KeyBinding("key.jump", 57);
/*  89 */   public KeyBinding keyBindInventory = new KeyBinding("key.inventory", 18);
/*  90 */   public KeyBinding keyBindDrop = new KeyBinding("key.drop", 16);
/*  91 */   public KeyBinding keyBindChat = new KeyBinding("key.chat", 20);
/*  92 */   public KeyBinding keyBindSneak = new KeyBinding("key.sneak", 42);
/*  93 */   public KeyBinding keyBindAttack = new KeyBinding("key.attack", -100);
/*  94 */   public KeyBinding keyBindUseItem = new KeyBinding("key.use", -99);
/*     */   
/*  96 */   public KeyBinding keyBindPlayerList = new KeyBinding("key.playerlist", 41);
/*  97 */   public KeyBinding keyBindPickBlock = new KeyBinding("key.pickItem", -98);
/*  98 */   public KeyBinding keyBindCommand = new KeyBinding("key.command", 53);
/*  99 */   public KeyBinding keyBindToggleRun = new KeyBinding("key.toggleRun", 15);
/* 100 */   public KeyBinding keyBindZoom = new KeyBinding("key.zoom", 44);
/* 101 */   public KeyBinding keyBindRedrawChunks = new KeyBinding("key.redrawChunks", 19);
/*     */   
/*     */   public KeyBinding[] keyBindings;
/*     */   
/*     */   protected Minecraft mc;
/*     */   
/*     */   private File optionsFile;
/*     */   
/*     */   public int difficulty;
/*     */   
/*     */   public static final int forced_difficulty = 3;
/*     */   
/*     */   public static final float forced_gamma_setting = 0.0F;
/*     */   
/*     */   public int gui_mode;
/*     */   
/*     */   public int thirdPersonView;
/*     */   
/*     */   public boolean showDebugInfo;
/*     */   
/*     */   public boolean showDebugProfilerChart;
/*     */   
/*     */   public String lastServer;
/*     */   
/*     */   public boolean noclip;
/*     */   
/*     */   public boolean smoothCamera;
/*     */   
/*     */   public boolean debugCamEnable;
/*     */   
/*     */   public float noclipRate;
/*     */   
/*     */   public float debugCamRate;
/*     */   
/*     */   public float fovSetting;
/*     */   
/*     */   public float gammaSetting;
/*     */   
/*     */   public int guiScale;
/*     */   
/*     */   public int particleSetting;
/*     */   
/*     */   public String language;
/*     */ 
/*     */   
/*     */   public GameSettings(Minecraft par1Minecraft, File par2File) {
/* 147 */     initKeybindings();
/* 148 */     this.difficulty = 2;
/* 149 */     this.lastServer = "";
/* 150 */     this.noclipRate = 1.0F;
/* 151 */     this.debugCamRate = 1.0F;
/* 152 */     this.language = "en_US";
/* 153 */     this.mc = par1Minecraft;
/* 154 */     this.optionsFile = new File(par2File, "options.txt");
/* 155 */     loadOptions();
/*     */     
/* 157 */     this.gammaSetting = 0.0F;
/* 158 */     this.difficulty = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GameSettings() {
/* 164 */     initKeybindings();
/* 165 */     this.difficulty = 2;
/* 166 */     this.lastServer = "";
/* 167 */     this.noclipRate = 1.0F;
/* 168 */     this.debugCamRate = 1.0F;
/* 169 */     this.language = "en_US";
/* 170 */     this.difficulty = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initKeybindings() {
/* 175 */     this.keyBindings = new KeyBinding[] { this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindToggleRun, this.keyBindZoom, this.keyBindRedrawChunks };
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyBindingDescription(int par1) {
/* 180 */     return I18n.getString((this.keyBindings[par1]).keyDescription);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOptionDisplayString(int par1) {
/* 188 */     int var2 = (this.keyBindings[par1]).keyCode;
/* 189 */     return getKeyDisplayString(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getKeyDisplayString(int par0) {
/* 197 */     return (par0 < 0) ? I18n.getStringParams("key.mouseButton", new Object[] { Integer.valueOf(par0 + 101) }) : Keyboard.getKeyName(par0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isKeyDown(KeyBinding par0KeyBinding) {
/* 205 */     return (par0KeyBinding.keyCode < 0) ? Mouse.isButtonDown(par0KeyBinding.keyCode + 100) : Keyboard.isKeyDown(par0KeyBinding.keyCode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyBinding(int par1, int par2) {
/* 213 */     (this.keyBindings[par1]).keyCode = par2;
/* 214 */     saveOptions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptionFloatValue(EnumOptions par1EnumOptions, float par2) {
/* 222 */     if (par1EnumOptions == EnumOptions.MUSIC) {
/*     */       
/* 224 */       this.musicVolume = par2;
/* 225 */       this.mc.sndManager.onSoundOptionsChanged();
/*     */     } 
/*     */     
/* 228 */     if (par1EnumOptions == EnumOptions.SOUND) {
/*     */       
/* 230 */       this.soundVolume = par2;
/* 231 */       this.mc.sndManager.onSoundOptionsChanged();
/*     */     } 
/*     */     
/* 234 */     if (par1EnumOptions == EnumOptions.SENSITIVITY)
/*     */     {
/* 236 */       this.mouseSensitivity = par2;
/*     */     }
/*     */     
/* 239 */     if (par1EnumOptions == EnumOptions.FOV)
/*     */     {
/* 241 */       this.fovSetting = par2;
/*     */     }
/*     */     
/* 244 */     if (par1EnumOptions == EnumOptions.GAMMA)
/*     */     {
/*     */       
/* 247 */       this.gammaSetting = 0.0F;
/*     */     }
/*     */     
/* 250 */     if (par1EnumOptions == EnumOptions.CHAT_OPACITY) {
/*     */       
/* 252 */       this.chatOpacity = par2;
/* 253 */       this.mc.ingameGUI.getChatGUI().func_96132_b();
/*     */     } 
/*     */     
/* 256 */     if (par1EnumOptions == EnumOptions.CHAT_HEIGHT_FOCUSED) {
/*     */       
/* 258 */       this.chatHeightFocused = par2;
/* 259 */       this.mc.ingameGUI.getChatGUI().func_96132_b();
/*     */     } 
/*     */     
/* 262 */     if (par1EnumOptions == EnumOptions.CHAT_HEIGHT_UNFOCUSED) {
/*     */       
/* 264 */       this.chatHeightUnfocused = par2;
/* 265 */       this.mc.ingameGUI.getChatGUI().func_96132_b();
/*     */     } 
/*     */     
/* 268 */     if (par1EnumOptions == EnumOptions.CHAT_WIDTH) {
/*     */       
/* 270 */       this.chatWidth = par2;
/* 271 */       this.mc.ingameGUI.getChatGUI().func_96132_b();
/*     */     } 
/*     */     
/* 274 */     if (par1EnumOptions == EnumOptions.CHAT_SCALE) {
/*     */       
/* 276 */       this.chatScale = par2;
/* 277 */       this.mc.ingameGUI.getChatGUI().func_96132_b();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptionValue(EnumOptions par1EnumOptions, int par2) {
/* 286 */     if (par1EnumOptions == EnumOptions.INVERT_MOUSE)
/*     */     {
/* 288 */       this.invertMouse = !this.invertMouse;
/*     */     }
/*     */     
/* 291 */     if (par1EnumOptions == EnumOptions.RENDER_DISTANCE)
/*     */     {
/* 293 */       this.renderDistance = this.renderDistance + par2 & 0x3;
/*     */     }
/*     */     
/* 296 */     if (par1EnumOptions == EnumOptions.GUI_SCALE)
/*     */     {
/* 298 */       this.guiScale = this.guiScale + par2 & 0x3;
/*     */     }
/*     */     
/* 301 */     if (par1EnumOptions == EnumOptions.PARTICLES)
/*     */     {
/* 303 */       this.particleSetting = (this.particleSetting + par2) % 3;
/*     */     }
/*     */     
/* 306 */     if (par1EnumOptions == EnumOptions.VIEW_BOBBING)
/*     */     {
/* 308 */       this.viewBobbing = !this.viewBobbing;
/*     */     }
/*     */     
/* 311 */     if (par1EnumOptions == EnumOptions.RENDER_CLOUDS)
/*     */     {
/* 313 */       this.clouds = !this.clouds;
/*     */     }
/*     */     
/* 316 */     if (par1EnumOptions == EnumOptions.ADVANCED_OPENGL) {
/*     */       
/* 318 */       this.advancedOpengl = !this.advancedOpengl;
/* 319 */       this.mc.renderGlobal.loadRenderers();
/*     */     } 
/*     */     
/* 322 */     if (par1EnumOptions == EnumOptions.ANAGLYPH) {
/*     */       
/* 324 */       this.anaglyph = !this.anaglyph;
/* 325 */       this.mc.refreshResources();
/*     */     } 
/*     */     
/* 328 */     if (par1EnumOptions == EnumOptions.FRAMERATE_LIMIT) {
/*     */ 
/*     */ 
/*     */       
/* 332 */       this.limitFramerate = (this.limitFramerate + par2) % 4;
/*     */       
/* 334 */       if (this.limitFramerate == 0) {
/* 335 */         this.limitFramerate = 1;
/*     */       }
/*     */     } 
/* 338 */     if (par1EnumOptions == EnumOptions.DIFFICULTY)
/*     */     {
/*     */       
/* 341 */       this.difficulty = 3;
/*     */     }
/*     */     
/* 344 */     if (par1EnumOptions == EnumOptions.GRAPHICS) {
/*     */       
/* 346 */       this.fancyGraphics = !this.fancyGraphics;
/* 347 */       this.mc.renderGlobal.loadRenderers();
/*     */     } 
/*     */     
/* 350 */     if (par1EnumOptions == EnumOptions.AMBIENT_OCCLUSION) {
/*     */       
/* 352 */       this.ambientOcclusion = (this.ambientOcclusion + par2) % 3;
/* 353 */       this.mc.renderGlobal.loadRenderers();
/*     */     } 
/*     */     
/* 356 */     if (par1EnumOptions == EnumOptions.CHAT_VISIBILITY)
/*     */     {
/* 358 */       this.chatVisibility = (this.chatVisibility + par2) % 3;
/*     */     }
/*     */     
/* 361 */     if (par1EnumOptions == EnumOptions.CHAT_COLOR)
/*     */     {
/* 363 */       this.chatColours = !this.chatColours;
/*     */     }
/*     */     
/* 366 */     if (par1EnumOptions == EnumOptions.CHAT_LINKS)
/*     */     {
/* 368 */       this.chatLinks = !this.chatLinks;
/*     */     }
/*     */     
/* 371 */     if (par1EnumOptions == EnumOptions.CHAT_LINKS_PROMPT)
/*     */     {
/* 373 */       this.chatLinksPrompt = !this.chatLinksPrompt;
/*     */     }
/*     */     
/* 376 */     if (par1EnumOptions == EnumOptions.USE_SERVER_TEXTURES)
/*     */     {
/* 378 */       this.serverTextures = !this.serverTextures;
/*     */     }
/*     */     
/* 381 */     if (par1EnumOptions == EnumOptions.SNOOPER_ENABLED)
/*     */     {
/* 383 */       this.snooperEnabled = !this.snooperEnabled;
/*     */     }
/*     */     
/* 386 */     if (par1EnumOptions == EnumOptions.SHOW_CAPE)
/*     */     {
/* 388 */       this.showCape = !this.showCape;
/*     */     }
/*     */     
/* 391 */     if (par1EnumOptions == EnumOptions.TOUCHSCREEN)
/*     */     {
/* 393 */       this.touchscreen = !this.touchscreen;
/*     */     }
/*     */     
/* 396 */     if (par1EnumOptions == EnumOptions.USE_FULLSCREEN) {
/*     */       
/* 398 */       this.fullScreen = !this.fullScreen;
/*     */       
/* 400 */       if (this.mc.isFullScreen() != this.fullScreen)
/*     */       {
/* 402 */         this.mc.toggleFullscreen();
/*     */       }
/*     */     } 
/*     */     
/* 406 */     if (par1EnumOptions == EnumOptions.ENABLE_VSYNC) {
/*     */       
/* 408 */       this.enableVsync = !this.enableVsync;
/*     */ 
/*     */       
/* 411 */       Display.setVSyncEnabled(isVsyncEnabled());
/*     */     } 
/*     */     
/* 414 */     saveOptions();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOptionFloatValue(EnumOptions par1EnumOptions) {
/* 420 */     return (par1EnumOptions == EnumOptions.FOV) ? this.fovSetting : ((par1EnumOptions == EnumOptions.GAMMA) ? 0.0F : ((par1EnumOptions == EnumOptions.MUSIC) ? this.musicVolume : ((par1EnumOptions == EnumOptions.SOUND) ? this.soundVolume : ((par1EnumOptions == EnumOptions.SENSITIVITY) ? this.mouseSensitivity : ((par1EnumOptions == EnumOptions.CHAT_OPACITY) ? this.chatOpacity : ((par1EnumOptions == EnumOptions.CHAT_HEIGHT_FOCUSED) ? this.chatHeightFocused : ((par1EnumOptions == EnumOptions.CHAT_HEIGHT_UNFOCUSED) ? this.chatHeightUnfocused : ((par1EnumOptions == EnumOptions.CHAT_SCALE) ? this.chatScale : ((par1EnumOptions == EnumOptions.CHAT_WIDTH) ? this.chatWidth : 0.0F)))))))));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getOptionOrdinalValue(EnumOptions par1EnumOptions) {
/* 425 */     switch (EnumOptionsHelper.enumOptionsMappingHelperArray[par1EnumOptions.ordinal()]) {
/*     */       
/*     */       case 1:
/* 428 */         return this.invertMouse;
/*     */       
/*     */       case 2:
/* 431 */         return this.viewBobbing;
/*     */       
/*     */       case 3:
/* 434 */         return this.anaglyph;
/*     */       
/*     */       case 4:
/* 437 */         return this.advancedOpengl;
/*     */       
/*     */       case 5:
/* 440 */         return this.clouds;
/*     */       
/*     */       case 6:
/* 443 */         return this.chatColours;
/*     */       
/*     */       case 7:
/* 446 */         return this.chatLinks;
/*     */       
/*     */       case 8:
/* 449 */         return this.chatLinksPrompt;
/*     */       
/*     */       case 9:
/* 452 */         return this.serverTextures;
/*     */       
/*     */       case 10:
/* 455 */         return this.snooperEnabled;
/*     */       
/*     */       case 11:
/* 458 */         return this.fullScreen;
/*     */       
/*     */       case 12:
/* 461 */         return this.enableVsync;
/*     */       
/*     */       case 13:
/* 464 */         return this.showCape;
/*     */       
/*     */       case 14:
/* 467 */         return this.touchscreen;
/*     */     } 
/*     */     
/* 470 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getTranslation(String[] par0ArrayOfStr, int par1) {
/* 480 */     if (par1 < 0 || par1 >= par0ArrayOfStr.length)
/*     */     {
/* 482 */       par1 = 0;
/*     */     }
/*     */     
/* 485 */     return I18n.getString(par0ArrayOfStr[par1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKeyBinding(EnumOptions par1EnumOptions) {
/* 493 */     String var2 = I18n.getString(par1EnumOptions.getEnumString()) + ": ";
/*     */     
/* 495 */     if (par1EnumOptions.getEnumFloat()) {
/*     */       
/* 497 */       float var5 = getOptionFloatValue(par1EnumOptions);
/* 498 */       return (par1EnumOptions == EnumOptions.SENSITIVITY) ? ((var5 == 0.0F) ? (var2 + I18n.getString("options.sensitivity.min")) : ((var5 == 1.0F) ? (var2 + I18n.getString("options.sensitivity.max")) : (var2 + (int)(var5 * 200.0F) + "%"))) : ((par1EnumOptions == EnumOptions.FOV) ? ((var5 == 0.0F) ? (var2 + I18n.getString("options.fov.min")) : ((var5 == 1.0F) ? (var2 + I18n.getString("options.fov.max")) : (var2 + (int)(70.0F + var5 * 40.0F)))) : ((par1EnumOptions == EnumOptions.GAMMA) ? ((var5 == 0.0F) ? (var2 + I18n.getString("options.gamma.min")) : ((var5 == 1.0F) ? (var2 + I18n.getString("options.gamma.max")) : (var2 + "+" + (int)(var5 * 100.0F) + "%"))) : ((par1EnumOptions == EnumOptions.CHAT_OPACITY) ? (var2 + (int)(var5 * 90.0F + 10.0F) + "%") : ((par1EnumOptions == EnumOptions.CHAT_HEIGHT_UNFOCUSED) ? (var2 + GuiNewChat.func_96130_b(var5) + "px") : ((par1EnumOptions == EnumOptions.CHAT_HEIGHT_FOCUSED) ? (var2 + GuiNewChat.func_96130_b(var5) + "px") : ((par1EnumOptions == EnumOptions.CHAT_WIDTH) ? (var2 + GuiNewChat.func_96128_a(var5) + "px") : ((var5 == 0.0F) ? (var2 + I18n.getString("options.off")) : (var2 + (int)(var5 * 100.0F) + "%"))))))));
/*     */     } 
/* 500 */     if (par1EnumOptions.getEnumBoolean()) {
/*     */       
/* 502 */       boolean var4 = getOptionOrdinalValue(par1EnumOptions);
/* 503 */       return var4 ? (var2 + I18n.getString("options.on")) : (var2 + I18n.getString("options.off"));
/*     */     } 
/* 505 */     if (par1EnumOptions == EnumOptions.RENDER_DISTANCE)
/*     */     {
/* 507 */       return var2 + getTranslation(RENDER_DISTANCES, this.renderDistance);
/*     */     }
/* 509 */     if (par1EnumOptions == EnumOptions.DIFFICULTY)
/*     */     {
/*     */       
/* 512 */       return var2 + getTranslation(DIFFICULTIES, 3);
/*     */     }
/* 514 */     if (par1EnumOptions == EnumOptions.GUI_SCALE)
/*     */     {
/* 516 */       return var2 + getTranslation(GUISCALES, this.guiScale);
/*     */     }
/* 518 */     if (par1EnumOptions == EnumOptions.CHAT_VISIBILITY)
/*     */     {
/* 520 */       return var2 + getTranslation(CHAT_VISIBILITIES, this.chatVisibility);
/*     */     }
/* 522 */     if (par1EnumOptions == EnumOptions.PARTICLES)
/*     */     {
/* 524 */       return var2 + getTranslation(PARTICLES, this.particleSetting);
/*     */     }
/* 526 */     if (par1EnumOptions == EnumOptions.FRAMERATE_LIMIT)
/*     */     {
/* 528 */       return var2 + getTranslation(LIMIT_FRAMERATES, this.limitFramerate);
/*     */     }
/* 530 */     if (par1EnumOptions == EnumOptions.AMBIENT_OCCLUSION)
/*     */     {
/* 532 */       return var2 + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
/*     */     }
/* 534 */     if (par1EnumOptions == EnumOptions.GRAPHICS) {
/*     */       
/* 536 */       if (this.fancyGraphics)
/*     */       {
/* 538 */         return var2 + I18n.getString("options.graphics.fancy");
/*     */       }
/*     */ 
/*     */       
/* 542 */       String var3 = "options.graphics.fast";
/* 543 */       return var2 + I18n.getString("options.graphics.fast");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 548 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadOptions() {
/*     */     try {
/* 559 */       if (!this.optionsFile.exists()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 564 */       BufferedReader var1 = new BufferedReader(new FileReader(this.optionsFile));
/* 565 */       String var2 = "";
/*     */       
/* 567 */       while ((var2 = var1.readLine()) != null) {
/*     */ 
/*     */         
/*     */         try {
/* 571 */           String[] var3 = var2.split(":");
/*     */           
/* 573 */           if (var3[0].equals("music"))
/*     */           {
/* 575 */             this.musicVolume = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 578 */           if (var3[0].equals("sound"))
/*     */           {
/* 580 */             this.soundVolume = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 583 */           if (var3[0].equals("mouseSensitivity"))
/*     */           {
/* 585 */             this.mouseSensitivity = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 588 */           if (var3[0].equals("fov"))
/*     */           {
/* 590 */             this.fovSetting = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 593 */           if (var3[0].equals("gamma"))
/*     */           {
/*     */             
/* 596 */             this.gammaSetting = 0.0F;
/*     */           }
/*     */           
/* 599 */           if (var3[0].equals("invertYMouse"))
/*     */           {
/* 601 */             this.invertMouse = var3[1].equals("true");
/*     */           }
/*     */           
/* 604 */           if (var3[0].equals("viewDistance"))
/*     */           {
/* 606 */             this.renderDistance = Integer.parseInt(var3[1]);
/*     */           }
/*     */           
/* 609 */           if (var3[0].equals("guiScale"))
/*     */           {
/* 611 */             this.guiScale = Integer.parseInt(var3[1]);
/*     */           }
/*     */           
/* 614 */           if (var3[0].equals("particles"))
/*     */           {
/* 616 */             this.particleSetting = Integer.parseInt(var3[1]);
/*     */           }
/*     */           
/* 619 */           if (var3[0].equals("bobView"))
/*     */           {
/* 621 */             this.viewBobbing = var3[1].equals("true");
/*     */           }
/*     */           
/* 624 */           if (var3[0].equals("anaglyph3d"))
/*     */           {
/* 626 */             this.anaglyph = var3[1].equals("true");
/*     */           }
/*     */           
/* 629 */           if (var3[0].equals("advancedOpengl"))
/*     */           {
/* 631 */             this.advancedOpengl = var3[1].equals("true");
/*     */           }
/*     */           
/* 634 */           if (var3[0].equals("fpsLimit")) {
/*     */             
/* 636 */             this.limitFramerate = Integer.parseInt(var3[1]);
/*     */             
/* 638 */             if (this.limitFramerate == 0) {
/* 639 */               this.limitFramerate = 3;
/*     */             }
/*     */           } 
/* 642 */           if (var3[0].equals("difficulty"))
/*     */           {
/*     */             
/* 645 */             this.difficulty = 3;
/*     */           }
/*     */           
/* 648 */           if (var3[0].equals("fancyGraphics"))
/*     */           {
/* 650 */             this.fancyGraphics = var3[1].equals("true");
/*     */           }
/*     */           
/* 653 */           if (var3[0].equals("ao"))
/*     */           {
/* 655 */             if (var3[1].equals("true")) {
/*     */               
/* 657 */               this.ambientOcclusion = 2;
/*     */             }
/* 659 */             else if (var3[1].equals("false")) {
/*     */               
/* 661 */               this.ambientOcclusion = 0;
/*     */             }
/*     */             else {
/*     */               
/* 665 */               this.ambientOcclusion = Integer.parseInt(var3[1]);
/*     */             } 
/*     */           }
/*     */           
/* 669 */           if (var3[0].equals("clouds"))
/*     */           {
/* 671 */             this.clouds = var3[1].equals("true");
/*     */           }
/*     */           
/* 674 */           if (var3[0].equals("skin"))
/*     */           {
/* 676 */             this.skin = var3[1];
/*     */           }
/*     */           
/* 679 */           if (var3[0].equals("lastServer") && var3.length >= 2)
/*     */           {
/* 681 */             this.lastServer = var2.substring(var2.indexOf(':') + 1);
/*     */           }
/*     */           
/* 684 */           if (var3[0].equals("lang") && var3.length >= 2)
/*     */           {
/* 686 */             this.language = var3[1];
/*     */           }
/*     */           
/* 689 */           if (var3[0].equals("chatVisibility"))
/*     */           {
/* 691 */             this.chatVisibility = Integer.parseInt(var3[1]);
/*     */           }
/*     */           
/* 694 */           if (var3[0].equals("chatColors"))
/*     */           {
/* 696 */             this.chatColours = var3[1].equals("true");
/*     */           }
/*     */           
/* 699 */           if (var3[0].equals("chatLinks"))
/*     */           {
/* 701 */             this.chatLinks = var3[1].equals("true");
/*     */           }
/*     */           
/* 704 */           if (var3[0].equals("chatLinksPrompt"))
/*     */           {
/* 706 */             this.chatLinksPrompt = var3[1].equals("true");
/*     */           }
/*     */           
/* 709 */           if (var3[0].equals("chatOpacity"))
/*     */           {
/* 711 */             this.chatOpacity = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 714 */           if (var3[0].equals("serverTextures"))
/*     */           {
/* 716 */             this.serverTextures = var3[1].equals("true");
/*     */           }
/*     */           
/* 719 */           if (var3[0].equals("snooperEnabled"))
/*     */           {
/* 721 */             this.snooperEnabled = var3[1].equals("true");
/*     */           }
/*     */           
/* 724 */           if (var3[0].equals("fullscreen"))
/*     */           {
/* 726 */             this.fullScreen = var3[1].equals("true");
/*     */           }
/*     */           
/* 729 */           if (var3[0].equals("enableVsync"))
/*     */           {
/* 731 */             this.enableVsync = var3[1].equals("true");
/*     */           }
/*     */           
/* 734 */           if (var3[0].equals("hideServerAddress"))
/*     */           {
/* 736 */             this.hideServerAddress = var3[1].equals("true");
/*     */           }
/*     */           
/* 739 */           if (var3[0].equals("advancedItemTooltips"))
/*     */           {
/* 741 */             this.advancedItemTooltips = var3[1].equals("true");
/*     */           }
/*     */           
/* 744 */           if (var3[0].equals("pauseOnLostFocus"))
/*     */           {
/* 746 */             this.pauseOnLostFocus = var3[1].equals("true");
/*     */           }
/*     */           
/* 749 */           if (var3[0].equals("showCape"))
/*     */           {
/* 751 */             this.showCape = var3[1].equals("true");
/*     */           }
/*     */           
/* 754 */           if (var3[0].equals("touchscreen"))
/*     */           {
/* 756 */             this.touchscreen = var3[1].equals("true");
/*     */           }
/*     */           
/* 759 */           if (var3[0].equals("overrideHeight"))
/*     */           {
/* 761 */             this.overrideHeight = Integer.parseInt(var3[1]);
/*     */           }
/*     */           
/* 764 */           if (var3[0].equals("overrideWidth"))
/*     */           {
/* 766 */             this.overrideWidth = Integer.parseInt(var3[1]);
/*     */           }
/*     */           
/* 769 */           if (var3[0].equals("heldItemTooltips"))
/*     */           {
/* 771 */             this.heldItemTooltips = var3[1].equals("true");
/*     */           }
/*     */           
/* 774 */           if (var3[0].equals("chatHeightFocused"))
/*     */           {
/* 776 */             this.chatHeightFocused = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 779 */           if (var3[0].equals("chatHeightUnfocused"))
/*     */           {
/* 781 */             this.chatHeightUnfocused = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 784 */           if (var3[0].equals("chatScale"))
/*     */           {
/* 786 */             this.chatScale = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 789 */           if (var3[0].equals("chatWidth"))
/*     */           {
/* 791 */             this.chatWidth = parseFloat(var3[1]);
/*     */           }
/*     */           
/* 794 */           for (int var4 = 0; var4 < this.keyBindings.length; var4++)
/*     */           {
/* 796 */             if (var3[0].equals("key_" + (this.keyBindings[var4]).keyDescription))
/*     */             {
/* 798 */               (this.keyBindings[var4]).keyCode = Integer.parseInt(var3[1]);
/*     */             }
/*     */           }
/*     */         
/* 802 */         } catch (Exception var5) {
/*     */           
/* 804 */           this.mc.getLogAgent().logWarning("Skipping bad option: " + var2);
/*     */         } 
/*     */       } 
/*     */       
/* 808 */       KeyBinding.resetKeyBindingArrayAndHash();
/* 809 */       var1.close();
/*     */     }
/* 811 */     catch (Exception var6) {
/*     */       
/* 813 */       this.mc.getLogAgent().logWarning("Failed to load options");
/* 814 */       var6.printStackTrace();
/*     */     } 
/*     */     
/* 817 */     if (this.keyBindPlayerList.keyCode == 15 && this.keyBindToggleRun.keyCode == 15) {
/* 818 */       this.keyBindPlayerList.keyCode = 41;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float parseFloat(String par1Str) {
/* 826 */     return par1Str.equals("true") ? 1.0F : (par1Str.equals("false") ? 0.0F : Float.parseFloat(par1Str));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveOptions() {
/*     */     try {
/* 836 */       PrintWriter var1 = new PrintWriter(new FileWriter(this.optionsFile));
/* 837 */       var1.println("music:" + this.musicVolume);
/* 838 */       var1.println("sound:" + this.soundVolume);
/* 839 */       var1.println("invertYMouse:" + this.invertMouse);
/* 840 */       var1.println("mouseSensitivity:" + this.mouseSensitivity);
/* 841 */       var1.println("fov:" + this.fovSetting);
/*     */       
/* 843 */       var1.println("gamma:0.0");
/* 844 */       var1.println("viewDistance:" + this.renderDistance);
/* 845 */       var1.println("guiScale:" + this.guiScale);
/* 846 */       var1.println("particles:" + this.particleSetting);
/* 847 */       var1.println("bobView:" + this.viewBobbing);
/* 848 */       var1.println("anaglyph3d:" + this.anaglyph);
/* 849 */       var1.println("advancedOpengl:" + this.advancedOpengl);
/* 850 */       var1.println("fpsLimit:" + this.limitFramerate);
/* 851 */       var1.println("difficulty:" + this.difficulty);
/* 852 */       var1.println("fancyGraphics:" + this.fancyGraphics);
/* 853 */       var1.println("ao:" + this.ambientOcclusion);
/* 854 */       var1.println("clouds:" + this.clouds);
/* 855 */       var1.println("skin:" + this.skin);
/* 856 */       var1.println("lastServer:" + this.lastServer);
/* 857 */       var1.println("lang:" + this.language);
/* 858 */       var1.println("chatVisibility:" + this.chatVisibility);
/* 859 */       var1.println("chatColors:" + this.chatColours);
/* 860 */       var1.println("chatLinks:" + this.chatLinks);
/* 861 */       var1.println("chatLinksPrompt:" + this.chatLinksPrompt);
/* 862 */       var1.println("chatOpacity:" + this.chatOpacity);
/* 863 */       var1.println("serverTextures:" + this.serverTextures);
/* 864 */       var1.println("snooperEnabled:" + this.snooperEnabled);
/* 865 */       var1.println("fullscreen:" + this.fullScreen);
/* 866 */       var1.println("enableVsync:" + this.enableVsync);
/* 867 */       var1.println("hideServerAddress:" + this.hideServerAddress);
/* 868 */       var1.println("advancedItemTooltips:" + this.advancedItemTooltips);
/* 869 */       var1.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
/* 870 */       var1.println("showCape:" + this.showCape);
/* 871 */       var1.println("touchscreen:" + this.touchscreen);
/* 872 */       var1.println("overrideWidth:" + this.overrideWidth);
/* 873 */       var1.println("overrideHeight:" + this.overrideHeight);
/* 874 */       var1.println("heldItemTooltips:" + this.heldItemTooltips);
/* 875 */       var1.println("chatHeightFocused:" + this.chatHeightFocused);
/* 876 */       var1.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
/* 877 */       var1.println("chatScale:" + this.chatScale);
/* 878 */       var1.println("chatWidth:" + this.chatWidth);
/*     */       
/* 880 */       for (int var2 = 0; var2 < this.keyBindings.length; var2++)
/*     */       {
/* 882 */         var1.println("key_" + (this.keyBindings[var2]).keyDescription + ":" + (this.keyBindings[var2]).keyCode);
/*     */       }
/*     */       
/* 885 */       var1.close();
/*     */     }
/* 887 */     catch (Exception var3) {
/*     */       
/* 889 */       this.mc.getLogAgent().logWarning("Failed to save options");
/* 890 */       var3.printStackTrace();
/*     */     } 
/*     */     
/* 893 */     sendSettingsToServer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendSettingsToServer() {
/* 901 */     if (this.mc.thePlayer != null)
/*     */     {
/* 903 */       this.mc.thePlayer.sendQueue.addToSendQueue(new Packet204ClientInfo(this.language, this.renderDistance, this.chatVisibility, this.chatColours, this.difficulty, this.showCape));
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
/*     */   public boolean shouldRenderClouds() {
/* 917 */     return (this.clouds && this.renderDistance < 2 && !Main.is_MITE_DS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullscreen() {
/* 922 */     return (this.fullScreen && !Main.is_MITE_DS);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderDistance() {
/* 927 */     return Main.is_MITE_DS ? 3 : this.renderDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFancyGraphicsEnabled() {
/* 932 */     return (this.fancyGraphics && !Main.is_MITE_DS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVsyncEnabled() {
/* 937 */     return (this.enableVsync && !Main.is_MITE_DS);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GameSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */