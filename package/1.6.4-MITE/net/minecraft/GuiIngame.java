/*      */ package net.minecraft;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GuiIngame
/*      */   extends Gui
/*      */ {
/*   19 */   private static final ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
/*   20 */   private static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");
/*   21 */   private static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("textures/misc/pumpkinblur.png");
/*   22 */   private static final RenderItem itemRenderer = new RenderItem();
/*   23 */   private final Random rand = new Random();
/*      */   
/*      */   private final Minecraft mc;
/*      */   
/*      */   private final GuiNewChat persistantChatGUI;
/*      */   
/*      */   private int updateCounter;
/*      */   
/*   31 */   private String recordPlaying = "";
/*      */ 
/*      */   
/*      */   private int recordPlayingUpFor;
/*      */   
/*      */   private boolean recordIsPlaying;
/*      */   
/*   38 */   public float prevVignetteBrightness = 1.0F;
/*      */ 
/*      */   
/*      */   private int remainingHighlightTicks;
/*      */ 
/*      */   
/*      */   private ItemStack highlightingItemStack;
/*      */   
/*   46 */   protected static final ResourceLocation MITE_icons = new ResourceLocation("textures/gui/MITE_icons.png");
/*      */   
/*      */   public static long display_overburdened_cpu_icon_until_ms;
/*      */   
/*      */   public int curse_notification_counter;
/*      */   
/*      */   private String last_highlighting_item_stack_text;
/*      */   
/*   54 */   public static int allotted_time = -1;
/*      */   
/*   56 */   public static int server_load = -1;
/*      */ 
/*      */   
/*      */   public GuiIngame(Minecraft par1Minecraft) {
/*   60 */     this.mc = par1Minecraft;
/*   61 */     this.persistantChatGUI = new GuiNewChat(par1Minecraft);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawChatForMITEDS() {
/*   66 */     GL11.glPushMatrix();
/*   67 */     GL11.glTranslatef(0.0F, 190.0F, 0.0F);
/*      */     
/*   69 */     this.persistantChatGUI.drawChat(this.updateCounter);
/*      */     
/*   71 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderGameOverlay(float par1, boolean par2, int par3, int par4) {
/*   79 */     ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/*   80 */     int var6 = var5.getScaledWidth();
/*   81 */     int var7 = var5.getScaledHeight();
/*   82 */     FontRenderer var8 = this.mc.fontRenderer;
/*   83 */     this.mc.entityRenderer.setupOverlayRendering();
/*      */     
/*   85 */     if (this.mc.thePlayer.isGhost()) {
/*      */       return;
/*      */     }
/*   88 */     GL11.glEnable(3042);
/*      */     
/*   90 */     int skylight_subtracted = this.mc.theWorld.skylightSubtracted;
/*   91 */     int skylight_subtracted_ignoring_rain_and_thunder = this.mc.theWorld.skylight_subtracted_ignoring_rain_and_thunder;
/*      */     
/*   93 */     this.mc.theWorld.skylightSubtracted = 0;
/*   94 */     this.mc.theWorld.skylight_subtracted_ignoring_rain_and_thunder = 0;
/*      */     
/*   96 */     float vignette_brightness = this.mc.thePlayer.getBrightness(par1);
/*      */     
/*   98 */     this.mc.theWorld.skylightSubtracted = skylight_subtracted;
/*   99 */     this.mc.theWorld.skylight_subtracted_ignoring_rain_and_thunder = skylight_subtracted_ignoring_rain_and_thunder;
/*      */     
/*  101 */     if (this.mc.thePlayer.is_cursed && vignette_brightness > 0.0F) {
/*  102 */       vignette_brightness = 0.0F;
/*      */     }
/*      */     
/*  105 */     if ((Minecraft.isFancyGraphicsEnabled() || this.mc.thePlayer.is_cursed) && this.mc.gameSettings.gui_mode != 2) {
/*      */ 
/*      */       
/*  108 */       renderVignette(vignette_brightness, var6, var7);
/*      */     }
/*      */     else {
/*      */       
/*  112 */       GL11.glBlendFunc(770, 771);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  118 */     if (this.mc.gameSettings.thirdPersonView == 0 && this.mc.thePlayer.isWearingPumpkinHelmet())
/*      */     {
/*  120 */       renderPumpkinBlur(var6, var7);
/*      */     }
/*      */     
/*  123 */     if (!this.mc.thePlayer.isPotionActive(Potion.confusion))
/*      */     {
/*  125 */       float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * par1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  138 */     float interpolated_vision_dimming = this.mc.thePlayer.vision_dimming - par1 * 0.01F;
/*      */     
/*  140 */     if (interpolated_vision_dimming > 0.01F) {
/*  141 */       renderVisionDim(var6, var7, Math.min(interpolated_vision_dimming, 0.9F));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  154 */     if (this.mc.thePlayer.runegate_counter > 0) {
/*  155 */       renderRunegateEffect(var6, var7);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  170 */     int eye_block_x = this.mc.thePlayer.getBlockPosX();
/*  171 */     int eye_block_y = MathHelper.floor_double(this.mc.thePlayer.getEyePosY() - 0.05000000074505806D);
/*  172 */     int eye_block_z = this.mc.thePlayer.getBlockPosZ();
/*      */     
/*  174 */     Block block = this.mc.theWorld.getBlock(eye_block_x, eye_block_y, eye_block_z);
/*      */ 
/*      */     
/*  177 */     boolean prevent_xray_vision = (this.mc.theWorld.isBlockSolid(block, eye_block_x, eye_block_y, eye_block_z) && block != Block.glass);
/*      */     
/*  179 */     if (prevent_xray_vision)
/*      */     {
/*      */ 
/*      */       
/*  183 */       if (!block.isOpaqueStandardFormCube(this.mc.theWorld, eye_block_x, eye_block_y, eye_block_z)) {
/*  184 */         prevent_xray_vision = false;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  189 */     if (prevent_xray_vision) {
/*      */       
/*  191 */       GL11.glDisable(2929);
/*  192 */       GL11.glDisable(3008);
/*  193 */       drawRect(0, 0, var6, var7, -16777216);
/*  194 */       GL11.glEnable(3008);
/*  195 */       GL11.glEnable(2929);
/*      */     } 
/*      */     
/*  198 */     int sleep_counter = this.mc.thePlayer.falling_asleep_counter;
/*      */     
/*  200 */     if (sleep_counter > 0) {
/*      */       
/*  202 */       int alpha = Math.min(255 * sleep_counter / 100, 255);
/*  203 */       this.mc.mcProfiler.startSection("sleep");
/*  204 */       GL11.glDisable(2929);
/*  205 */       GL11.glDisable(3008);
/*      */       
/*  207 */       drawRect(0, 0, var6, var7, alpha << 24);
/*  208 */       GL11.glEnable(3008);
/*  209 */       GL11.glEnable(2929);
/*  210 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  220 */     if (!this.mc.playerController.enableEverythingIsScrewedUpMode() && this.mc.gameSettings.gui_mode == 0) {
/*      */       
/*  222 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  224 */       if (display_overburdened_cpu_icon_until_ms >= System.currentTimeMillis()) {
/*      */         
/*  226 */         this.mc.getTextureManager().bindTexture(MITE_icons);
/*  227 */         drawTexturedModalRect(var6 - 17, 2, 0, 0, 16, 16);
/*      */       } 
/*      */       
/*  230 */       this.mc.getTextureManager().bindTexture(widgetsTexPath);
/*  231 */       InventoryPlayer var31 = this.mc.thePlayer.inventory;
/*  232 */       this.zLevel = -90.0F;
/*  233 */       drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
/*      */       
/*  235 */       if (this.mc.playerController.auto_use_mode_item != null) {
/*  236 */         GL11.glColor4f(0.8F, 1.0F, 0.7F, 1.0F);
/*      */       }
/*  238 */       drawTexturedModalRect(var6 / 2 - 91 - 1 + var31.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
/*      */       
/*  240 */       if (this.mc.playerController.auto_use_mode_item != null) {
/*  241 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       }
/*  243 */       this.mc.getTextureManager().bindTexture(icons);
/*  244 */       GL11.glEnable(3042);
/*  245 */       GL11.glBlendFunc(775, 769);
/*      */       
/*  247 */       drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
/*  248 */       GL11.glDisable(3042);
/*  249 */       this.mc.mcProfiler.startSection("bossHealth");
/*  250 */       renderBossHealth();
/*  251 */       this.mc.mcProfiler.endSection();
/*      */       
/*  253 */       if (this.mc.playerController.shouldDrawHUD())
/*      */       {
/*  255 */         func_110327_a(var6, var7);
/*      */       }
/*      */       
/*  258 */       GL11.glDisable(3042);
/*  259 */       this.mc.mcProfiler.startSection("actionBar");
/*  260 */       GL11.glEnable(32826);
/*  261 */       RenderHelper.enableGUIStandardItemLighting();
/*      */       
/*  263 */       for (int i = 0; i < 9; i++) {
/*      */         
/*  265 */         int var12 = var6 / 2 - 90 + i * 20 + 2;
/*  266 */         int var13 = var7 - 16 - 3;
/*  267 */         renderInventorySlot(i, var12, var13, par1);
/*      */       } 
/*      */       
/*  270 */       RenderHelper.disableStandardItemLighting();
/*  271 */       GL11.glDisable(32826);
/*  272 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  297 */     int var32 = 16777215;
/*  298 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  299 */     int var11 = var6 / 2 - 91;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  308 */     if (this.mc.thePlayer.isRidingHorse() && this.mc.gameSettings.gui_mode == 0) {
/*      */       
/*  310 */       this.mc.mcProfiler.startSection("jumpBar");
/*  311 */       this.mc.getTextureManager().bindTexture(Gui.icons);
/*  312 */       float var33 = this.mc.thePlayer.getHorseJumpPower();
/*  313 */       short var37 = 182;
/*  314 */       int var14 = (int)(var33 * (var37 + 1));
/*  315 */       int var15 = var7 - 32 + 3;
/*  316 */       drawTexturedModalRect(var11, var15, 0, 84, var37, 5);
/*      */       
/*  318 */       if (var14 > 0)
/*      */       {
/*  320 */         drawTexturedModalRect(var11, var15, 0, 89, var14, 5);
/*      */       }
/*      */       
/*  323 */       this.mc.mcProfiler.endSection();
/*      */     
/*      */     }
/*  326 */     else if (this.mc.playerController.func_78763_f() && this.mc.gameSettings.gui_mode == 0 && !(this.mc.currentScreen instanceof GuiScreenBook)) {
/*      */       
/*  328 */       this.mc.mcProfiler.startSection("expBar");
/*  329 */       this.mc.getTextureManager().bindTexture(Gui.icons);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  347 */       short var37 = 182;
/*  348 */       int var14 = (int)(this.mc.thePlayer.getLevelProgress() * (var37 + 1));
/*  349 */       int var15 = var7 - 32 + 3;
/*  350 */       drawTexturedModalRect(var11, var15, 0, 64, var37, 5);
/*      */       
/*  352 */       if (var14 > 0)
/*      */       {
/*  354 */         drawTexturedModalRect(var11, var15, 0, 69, var14, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  359 */       this.mc.mcProfiler.endSection();
/*      */ 
/*      */       
/*  362 */       if (this.mc.thePlayer.getExperienceLevel() != 0 && !(this.mc.currentScreen instanceof GuiScreenBook)) {
/*      */         
/*  364 */         this.mc.mcProfiler.startSection("expLevel");
/*  365 */         boolean var35 = false;
/*  366 */         var14 = var35 ? 16777215 : 8453920;
/*      */         
/*  368 */         if (this.mc.thePlayer.getExperienceLevel() < 0) {
/*  369 */           var14 = 16716563;
/*      */         }
/*      */         
/*  372 */         String var42 = "" + this.mc.thePlayer.getExperienceLevel();
/*  373 */         int var16 = (var6 - var8.getStringWidth(var42)) / 2;
/*  374 */         int var17 = var7 - 31 - 4;
/*  375 */         boolean var18 = false;
/*  376 */         var8.drawString(var42, var16 + 1, var17, 0);
/*  377 */         var8.drawString(var42, var16 - 1, var17, 0);
/*  378 */         var8.drawString(var42, var16, var17 + 1, 0);
/*  379 */         var8.drawString(var42, var16, var17 - 1, 0);
/*  380 */         var8.drawString(var42, var16, var17, var14);
/*  381 */         this.mc.mcProfiler.endSection();
/*      */       } 
/*      */     } 
/*      */     
/*  385 */     if (this.curse_notification_counter > 0 && this.mc.thePlayer.is_cursed) {
/*      */       
/*  387 */       FontRenderer fr = this.mc.fontRenderer;
/*  388 */       ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/*      */ 
/*      */       
/*  391 */       String text = Translator.get(this.mc.thePlayer.is_cursed ? "curse.notify" : "curse.lifted");
/*      */       
/*  393 */       int var14 = var7 - 59;
/*      */       
/*  395 */       if (!this.mc.playerController.shouldDrawHUD()) {
/*  396 */         var14 += 14;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  401 */       int alpha = (int)(this.curse_notification_counter * 256.0F / 10.0F);
/*      */       
/*  403 */       if (alpha > 255) {
/*  404 */         alpha = 255;
/*      */       }
/*  406 */       if (alpha > 0) {
/*      */         
/*  408 */         GL11.glEnable(3042);
/*  409 */         GL11.glBlendFunc(770, 771);
/*  410 */         fr.drawStringWithShadow(EnumChatFormatting.DARK_PURPLE + text, (sr.getScaledWidth() - fr.getStringWidth(text)) / 2, var14, 16777215 + (alpha << 24));
/*  411 */         GL11.glDisable(3042);
/*      */       } 
/*      */       
/*  414 */       this.remainingHighlightTicks = 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  420 */     if (this.mc.gameSettings.heldItemTooltips && this.mc.gameSettings.gui_mode == 0) {
/*      */       
/*  422 */       this.mc.mcProfiler.startSection("toolHighlight");
/*      */       
/*  424 */       if (this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
/*      */ 
/*      */         
/*  427 */         String var36 = this.highlightingItemStack.getMITEStyleDisplayName();
/*      */         
/*  429 */         int var13 = (var6 - var8.getStringWidth(var36)) / 2;
/*  430 */         int var14 = var7 - 59;
/*      */         
/*  432 */         if (!this.mc.playerController.shouldDrawHUD())
/*      */         {
/*  434 */           var14 += 14;
/*      */         }
/*      */         
/*  437 */         int var15 = (int)(this.remainingHighlightTicks * 256.0F / 10.0F);
/*      */         
/*  439 */         if (var15 > 255)
/*      */         {
/*  441 */           var15 = 255;
/*      */         }
/*      */         
/*  444 */         if (var15 > 0) {
/*      */           
/*  446 */           GL11.glPushMatrix();
/*  447 */           GL11.glEnable(3042);
/*  448 */           GL11.glBlendFunc(770, 771);
/*  449 */           var8.drawStringWithShadow(var36, var13, var14, 16777215 + (var15 << 24));
/*  450 */           GL11.glDisable(3042);
/*  451 */           GL11.glPopMatrix();
/*      */         } 
/*      */         
/*  454 */         this.last_highlighting_item_stack_text = var36;
/*      */       } 
/*      */       
/*  457 */       if (this.highlightingItemStack == null) {
/*  458 */         this.last_highlighting_item_stack_text = null;
/*      */       }
/*  460 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */     
/*  463 */     if (this.mc.isDemo()) {
/*      */       
/*  465 */       this.mc.mcProfiler.startSection("demo");
/*  466 */       String var36 = "";
/*      */       
/*  468 */       if (this.mc.theWorld.getTotalWorldTime() >= 120500L) {
/*      */         
/*  470 */         var36 = I18n.getString("demo.demoExpired");
/*      */       }
/*      */       else {
/*      */         
/*  474 */         var36 = I18n.getStringParams("demo.remainingTime", new Object[] { StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getTotalWorldTime())) });
/*      */       } 
/*      */       
/*  477 */       int var13 = var8.getStringWidth(var36);
/*  478 */       var8.drawStringWithShadow(var36, var6 - var13 - 10, 5, 16777215);
/*  479 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  486 */     if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*      */       
/*  488 */       this.mc.thePlayer.delta_tournament_score_opacity -= 2;
/*      */       
/*  490 */       if (this.mc.thePlayer.delta_tournament_score_opacity < 0) {
/*      */         
/*  492 */         this.mc.thePlayer.delta_tournament_score = 0;
/*  493 */         this.mc.thePlayer.delta_tournament_score_opacity = 0;
/*      */       } 
/*      */       
/*  496 */       this.mc.last_known_delta_tournament_score = this.mc.thePlayer.delta_tournament_score;
/*  497 */       this.mc.last_known_delta_tournament_score_opacity = this.mc.thePlayer.delta_tournament_score_opacity;
/*  498 */       this.mc.last_known_tournament_score = this.mc.thePlayer.tournament_score;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  515 */     int row = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  522 */     if (Minecraft.getErrorMessage() != null) {
/*      */       
/*  524 */       drawString(var8, Minecraft.getErrorMessage(), 2, 2 + 10 * row++, 16716563);
/*  525 */       drawString(var8, "Press [c] to clear error message.", 2, 2 + 10 * row++, 16716563);
/*      */     } 
/*      */     
/*  528 */     if (this.mc.gameSettings.showDebugInfo && this.mc.gameSettings.gui_mode == 0) {
/*      */       
/*  530 */       if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*  531 */         drawTournamentScore(row++, 2, var8);
/*      */       }
/*  533 */       if (allotted_time >= 0) {
/*  534 */         drawAllottedTime(row++, 2, var8);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  548 */     if (Minecraft.inDevMode() && this.mc.gameSettings.gui_mode == 0) {
/*      */       
/*  550 */       if (server_load >= 0) {
/*      */ 
/*      */ 
/*      */         
/*  554 */         ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/*      */         
/*  556 */         String text = server_load + "%";
/*      */         
/*  558 */         drawString(var8, text, sr.getScaledWidth() - var8.getStringWidth(text) - 2, 2, 14737632);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  565 */       drawString(var8, "Legs (" + MathHelper.floor_double(this.mc.thePlayer.posX) + ", " + MathHelper.floor_double(this.mc.thePlayer.posY - this.mc.thePlayer.yOffset) + ", " + MathHelper.floor_double(this.mc.thePlayer.posZ) + ")  yaw=" + StringHelper.formatFloat(this.mc.thePlayer.rotationYaw, 1, 1) + "  " + this.mc.thePlayer.getDirectionFromYaw() + " pitch=" + StringHelper.formatFloat(this.mc.thePlayer.rotationPitch, 1, 1) + "   FPS=" + Minecraft.last_fps + " (" + Minecraft.last_fp10s + ")", 2, 2 + 10 * row++, 14737632);
/*      */       
/*  567 */       if (Debug.flag) {
/*  568 */         drawString(var8, "FLAG", 320, 2, 16716563);
/*      */       }
/*      */     } 
/*  571 */     if (Debug.is_active && this.mc.gameSettings.gui_mode == 0) {
/*      */       String s;
/*      */       
/*  574 */       drawString(var8, "Counter: " + Debug.general_counter, 2, 2 + 10 * row++, 14737632);
/*      */       
/*  576 */       if (Debug.biome_info != null) {
/*  577 */         drawString(var8, Debug.biome_info, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*  579 */       if (Debug.selected_object_info != null) {
/*  580 */         drawString(var8, Debug.selected_object_info, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*  582 */       if (Debug.equipped_item_info != null) {
/*  583 */         drawString(var8, Debug.equipped_item_info, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*  585 */       if (Debug.general_info != null) {
/*  586 */         drawString(var8, Debug.general_info, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*  588 */       if (Debug.general_info_client != null) {
/*  589 */         drawString(var8, "[Client] " + Debug.general_info_client, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*  591 */       if (Debug.general_info_server != null) {
/*  592 */         drawString(var8, "[Server] " + Debug.general_info_server, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*  594 */       row += 2;
/*      */       
/*  596 */       drawString(var8, "Player entityId: " + this.mc.thePlayer.entityId + ", username: " + this.mc.thePlayer.username, 2, 2 + 10 * row++, 14737632);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  601 */       drawString(var8, "O:" + this.mc.theWorld.worldInfo.getWorldTotalTime(0) + " U:" + this.mc.theWorld.worldInfo.getWorldTotalTime(-2) + " N:" + this.mc.theWorld.worldInfo.getWorldTotalTime(-1) + " E:" + this.mc.theWorld.worldInfo.getWorldTotalTime(1), 2, 2 + 10 * row++, 14737632);
/*      */ 
/*      */ 
/*      */       
/*  605 */       WeatherEvent event = this.mc.theWorld.getCurrentWeatherEvent();
/*      */       
/*  607 */       if (event != null) {
/*      */         
/*  609 */         s = "Current rain: " + event.start + " to " + event.end;
/*      */       }
/*      */       else {
/*      */         
/*  613 */         event = this.mc.theWorld.getNextWeatherEvent(false);
/*      */         
/*  615 */         if (event != null) {
/*      */           
/*  617 */           s = "Next rain: " + event.start + " to " + event.end;
/*      */         }
/*      */         else {
/*      */           
/*  621 */           event = this.mc.theWorld.getPreviousWeatherEvent(false);
/*      */           
/*  623 */           s = (event == null) ? "No rain today" : ("Previous rain: " + event.start + " to " + event.end);
/*      */         } 
/*      */       } 
/*      */       
/*  627 */       drawString(var8, s, 2, 2 + 10 * row++, 14737632);
/*      */       
/*  629 */       event = this.mc.theWorld.getCurrentWeatherEvent(true, false);
/*      */       
/*  631 */       if (event != null) {
/*      */         
/*  633 */         s = "Current storm: " + event.start_of_storm + " to " + event.end_of_storm;
/*      */       }
/*      */       else {
/*      */         
/*  637 */         event = this.mc.theWorld.getNextWeatherEvent(true);
/*      */         
/*  639 */         if (event != null) {
/*      */           
/*  641 */           s = "Next storm: " + event.start_of_storm + " to " + event.end_of_storm;
/*      */         }
/*      */         else {
/*      */           
/*  645 */           event = this.mc.theWorld.getPreviousWeatherEvent(true);
/*      */           
/*  647 */           s = (event == null) ? "No storm today" : ("Previous storm: " + event.start_of_storm + " to " + event.end_of_storm);
/*      */         } 
/*      */       } 
/*      */       
/*  651 */       drawString(var8, s, 2, 2 + 10 * row++, 14737632);
/*      */ 
/*      */       
/*  654 */       drawString(var8, "Client Pools: " + AxisAlignedBB.getAABBPool().getlistAABBsize() + " | " + this.mc.theWorld.getWorldVec3Pool().getPoolSize(), 2, 2 + 10 * row++, 14737632);
/*  655 */       drawString(var8, "Server Pools: " + Minecraft.server_pools_string, 2, 2 + 10 * row++, 14737632);
/*      */       
/*  657 */       row++;
/*  658 */       drawString(var8, "Atk: " + StringHelper.formatFloat(this.mc.thePlayer.calcRawMeleeDamageVs((Entity)null), 1, 1) + "  Prt:" + StringHelper.formatFloat(this.mc.thePlayer.getTotalProtection((DamageSource)null), 1, 1), 2, 2 + 10 * row++, 14737632);
/*      */ 
/*      */       
/*  661 */       drawString(var8, "Look: " + MathHelper.getNormalizedVector(this.mc.thePlayer.rotationYaw, this.mc.thePlayer.rotationPitch, this.mc.theWorld.getWorldVec3Pool()).toStringCompact(), 2, 2 + 10 * row++, 14737632);
/*  662 */       drawString(var8, "fxLayers" + this.mc.effectRenderer.getStatsString(), 2, 2 + 10 * row++, 14737632);
/*      */       
/*  664 */       Chunk chunk = this.mc.thePlayer.getChunkFromPosition();
/*      */       
/*  666 */       drawString(var8, "Chunk: " + chunk.xPosition + "," + chunk.zPosition + " [" + (this.mc.thePlayer.getFootBlockPosY() >> 4) + "] FPP=" + StringHelper.formatDouble(EntityRenderer.getProximityToNearestFogPost(this.mc.thePlayer), 3, 3), 2, 2 + 10 * row++, 14737632);
/*      */       
/*  668 */       MinecraftServer mc_server = MinecraftServer.getServer();
/*      */       
/*  670 */       if (mc_server != null) {
/*      */         
/*  672 */         WorldServer world_server = mc_server.worldServerForDimension(this.mc.thePlayer.dimension);
/*      */ 
/*      */         
/*  675 */         drawString(var8, "Mobs high: " + world_server.countMobs(false, true) + " / " + world_server.last_mob_spawn_limit_at_60_or_higher, 2, 2 + 10 * row++, 14737632);
/*  676 */         drawString(var8, "Mobs low:  " + world_server.countMobs(true, false) + " / " + world_server.last_mob_spawn_limit_under_60, 2, 2 + 10 * row++, 14737632);
/*      */       } 
/*      */       
/*  679 */       drawString(var8, "Mem: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L) + " / " + (Runtime.getRuntime().totalMemory() / 1024L / 1024L), 2, 2 + 10 * row++, 14737632);
/*      */     
/*      */     }
/*  682 */     else if (this.mc.gameSettings.showDebugInfo && this.mc.gameSettings.gui_mode == 0) {
/*      */       
/*  684 */       this.mc.mcProfiler.startSection("debug");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  717 */       this.mc.mcProfiler.endSection();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  723 */       if (DedicatedServer.tournament_type != EnumTournamentType.score && allotted_time < 0) {
/*  724 */         drawString(var8, "" + Minecraft.last_fps, 2, 2 + 10 * row++, 14737632);
/*      */       }
/*      */     } 
/*  727 */     if (this.recordPlayingUpFor > 0) {
/*      */       
/*  729 */       this.mc.mcProfiler.startSection("overlayMessage");
/*  730 */       float var33 = this.recordPlayingUpFor - par1;
/*  731 */       int var13 = (int)(var33 * 255.0F / 20.0F);
/*      */       
/*  733 */       if (var13 > 255)
/*      */       {
/*  735 */         var13 = 255;
/*      */       }
/*      */       
/*  738 */       if (var13 > 8) {
/*      */         
/*  740 */         GL11.glPushMatrix();
/*  741 */         GL11.glTranslatef((var6 / 2), (var7 - 68), 0.0F);
/*  742 */         GL11.glEnable(3042);
/*  743 */         GL11.glBlendFunc(770, 771);
/*  744 */         int var14 = 16777215;
/*      */         
/*  746 */         if (this.recordIsPlaying)
/*      */         {
/*  748 */           var14 = Color.HSBtoRGB(var33 / 50.0F, 0.7F, 0.6F) & 0xFFFFFF;
/*      */         }
/*      */         
/*  751 */         var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var14 + (var13 << 24 & 0xFF000000));
/*  752 */         GL11.glDisable(3042);
/*  753 */         GL11.glPopMatrix();
/*      */       } 
/*      */       
/*  756 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */     
/*  759 */     ScoreObjective var43 = this.mc.theWorld.getScoreboard().func_96539_a(1);
/*      */     
/*  761 */     if (var43 != null)
/*      */     {
/*  763 */       func_96136_a(var43, var7, var6, var8);
/*      */     }
/*      */     
/*  766 */     GL11.glEnable(3042);
/*  767 */     GL11.glBlendFunc(770, 771);
/*  768 */     GL11.glDisable(3008);
/*  769 */     GL11.glPushMatrix();
/*  770 */     GL11.glTranslatef(0.0F, (var7 - 48), 0.0F);
/*  771 */     this.mc.mcProfiler.startSection("chat");
/*  772 */     this.persistantChatGUI.drawChat(this.updateCounter);
/*  773 */     this.mc.mcProfiler.endSection();
/*  774 */     GL11.glPopMatrix();
/*  775 */     var43 = this.mc.theWorld.getScoreboard().func_96539_a(0);
/*      */     
/*  777 */     if (this.mc.gameSettings.keyBindPlayerList.pressed && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1 || var43 != null)) {
/*      */       
/*  779 */       this.mc.mcProfiler.startSection("playerList");
/*  780 */       NetClientHandler var41 = this.mc.thePlayer.sendQueue;
/*  781 */       List<GuiPlayerInfo> var44 = var41.playerInfoList;
/*  782 */       int var15 = var41.currentServerMaxPlayers;
/*  783 */       int var16 = var15;
/*      */       int var17;
/*  785 */       for (var17 = 1; var16 > 20; var16 = (var15 + var17 - 1) / var17)
/*      */       {
/*  787 */         var17++;
/*      */       }
/*      */       
/*  790 */       int var45 = 300 / var17;
/*      */       
/*  792 */       if (var45 > 150)
/*      */       {
/*  794 */         var45 = 150;
/*      */       }
/*      */       
/*  797 */       int var19 = (var6 - var17 * var45) / 2;
/*  798 */       byte var47 = 10;
/*  799 */       drawRect(var19 - 1, var47 - 1, var19 + var45 * var17, var47 + 9 * var16, -2147483648);
/*      */       
/*  801 */       int players_skipped = 0;
/*      */       
/*  803 */       for (int var21 = 0; var21 < var15; var21++) {
/*      */         
/*  805 */         int var22 = var19 + var21 % var17 * var45;
/*  806 */         int var23 = var47 + var21 / var17 * 9;
/*  807 */         int var23_alt = var47 + (var21 - players_skipped) / var17 * 9;
/*  808 */         drawRect(var22, var23, var22 + var45 - 1, var23 + 8, 553648127);
/*  809 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  810 */         GL11.glEnable(3008);
/*      */         
/*  812 */         if (var21 < var44.size()) {
/*      */           
/*  814 */           GuiPlayerInfo var49 = var44.get(var21);
/*  815 */           ScorePlayerTeam var48 = this.mc.theWorld.getScoreboard().getPlayersTeam(var49.name);
/*  816 */           String var52 = ScorePlayerTeam.formatPlayerName(var48, var49.name);
/*      */           
/*  818 */           if ("avernite".equals(var52) && DedicatedServer.isTournament()) {
/*      */             
/*  820 */             players_skipped++;
/*      */           } else {
/*      */             byte var50;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  840 */             var8.drawStringWithShadow(var52, var22, var23_alt, 16777215);
/*      */             
/*  842 */             if (var43 != null) {
/*      */               
/*  844 */               int var27 = var22 + var8.getStringWidth(var52) + 5;
/*  845 */               int var28 = var22 + var45 - 12 - 5;
/*      */               
/*  847 */               if (var28 - var27 > 5)
/*      */               {
/*  849 */                 Score var29 = var43.getScoreboard().func_96529_a(var49.name, var43);
/*  850 */                 String var30 = EnumChatFormatting.YELLOW + "" + var29.getScorePoints();
/*      */                 
/*  852 */                 var8.drawStringWithShadow(var30, var28 - var8.getStringWidth(var30), var23_alt, 16777215);
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  857 */               int var27 = var22 + var8.getStringWidth(var52) + 5;
/*  858 */               int var28 = var22 + var45 - 12 - 5;
/*      */               
/*  860 */               if (var28 - var27 > 5) {
/*      */                 String level;
/*      */ 
/*      */                 
/*  864 */                 if (var49.level < 0) {
/*  865 */                   level = EnumChatFormatting.RED + "" + var49.level;
/*  866 */                 } else if (var49.level == 0) {
/*  867 */                   level = EnumChatFormatting.GRAY + "" + var49.level;
/*      */                 } else {
/*  869 */                   level = EnumChatFormatting.GREEN + "+" + var49.level;
/*      */                 } 
/*      */                 
/*  872 */                 var8.drawStringWithShadow(level, var28 - var8.getStringWidth(level), var23_alt, 16777215);
/*      */               } 
/*      */             } 
/*      */             
/*  876 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  877 */             this.mc.getTextureManager().bindTexture(icons);
/*  878 */             byte var53 = 0;
/*  879 */             boolean var51 = false;
/*      */ 
/*      */             
/*  882 */             if (var49.responseTime < 0) {
/*      */               
/*  884 */               var50 = 5;
/*      */             }
/*  886 */             else if (var49.responseTime < 150) {
/*      */               
/*  888 */               var50 = 0;
/*      */             }
/*  890 */             else if (var49.responseTime < 300) {
/*      */               
/*  892 */               var50 = 1;
/*      */             }
/*  894 */             else if (var49.responseTime < 600) {
/*      */               
/*  896 */               var50 = 2;
/*      */             }
/*  898 */             else if (var49.responseTime < 1000) {
/*      */               
/*  900 */               var50 = 3;
/*      */             }
/*      */             else {
/*      */               
/*  904 */               var50 = 4;
/*      */             } 
/*      */             
/*  907 */             this.zLevel += 100.0F;
/*      */             
/*  909 */             drawTexturedModalRect(var22 + var45 - 12, var23_alt, 0 + var53 * 10, 176 + var50 * 8, 10, 8);
/*  910 */             this.zLevel -= 100.0F;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  915 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  916 */     GL11.glDisable(2896);
/*  917 */     GL11.glEnable(3008);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_96136_a(ScoreObjective par1ScoreObjective, int par2, int par3, FontRenderer par4FontRenderer) {
/*  922 */     Scoreboard var5 = par1ScoreObjective.getScoreboard();
/*  923 */     Collection var6 = var5.func_96534_i(par1ScoreObjective);
/*      */     
/*  925 */     if (var6.size() <= 15) {
/*      */       
/*  927 */       int var7 = par4FontRenderer.getStringWidth(par1ScoreObjective.getDisplayName());
/*      */ 
/*      */       
/*  930 */       for (Iterator<Score> var8 = var6.iterator(); var8.hasNext(); var7 = Math.max(var7, par4FontRenderer.getStringWidth(var11))) {
/*      */         
/*  932 */         Score var9 = var8.next();
/*  933 */         ScorePlayerTeam var10 = var5.getPlayersTeam(var9.getPlayerName());
/*  934 */         String var11 = ScorePlayerTeam.formatPlayerName(var10, var9.getPlayerName()) + ": " + EnumChatFormatting.RED + var9.getScorePoints();
/*      */       } 
/*      */       
/*  937 */       int var22 = var6.size() * par4FontRenderer.FONT_HEIGHT;
/*  938 */       int var23 = par2 / 2 + var22 / 3;
/*  939 */       byte var25 = 3;
/*  940 */       int var24 = par3 - var7 - var25;
/*  941 */       int var12 = 0;
/*  942 */       Iterator<Score> var13 = var6.iterator();
/*      */       
/*  944 */       while (var13.hasNext()) {
/*      */         
/*  946 */         Score var14 = var13.next();
/*  947 */         var12++;
/*  948 */         ScorePlayerTeam var15 = var5.getPlayersTeam(var14.getPlayerName());
/*  949 */         String var16 = ScorePlayerTeam.formatPlayerName(var15, var14.getPlayerName());
/*  950 */         String var17 = EnumChatFormatting.RED + "" + var14.getScorePoints();
/*  951 */         int var19 = var23 - var12 * par4FontRenderer.FONT_HEIGHT;
/*  952 */         int var20 = par3 - var25 + 2;
/*  953 */         drawRect(var24 - 2, var19, var20, var19 + par4FontRenderer.FONT_HEIGHT, 1342177280);
/*  954 */         par4FontRenderer.drawString(var16, var24, var19, 553648127);
/*  955 */         par4FontRenderer.drawString(var17, var20 - par4FontRenderer.getStringWidth(var17), var19, 553648127);
/*      */         
/*  957 */         if (var12 == var6.size()) {
/*      */           
/*  959 */           String var21 = par1ScoreObjective.getDisplayName();
/*  960 */           drawRect(var24 - 2, var19 - par4FontRenderer.FONT_HEIGHT - 1, var20, var19 - 1, 1610612736);
/*  961 */           drawRect(var24 - 2, var19 - 1, var20, var19, 1342177280);
/*  962 */           par4FontRenderer.drawString(var21, var24 + var7 / 2 - par4FontRenderer.getStringWidth(var21) / 2, var19 - par4FontRenderer.FONT_HEIGHT, 553648127);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110327_a(int par1, int par2) {
/*  970 */     boolean var3 = (this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1);
/*      */     
/*  972 */     if (this.mc.thePlayer.hurtResistantTime < 10)
/*      */     {
/*  974 */       var3 = false;
/*      */     }
/*      */     
/*  977 */     int var4 = MathHelper.ceiling_float_int(this.mc.thePlayer.getHealth());
/*  978 */     int var5 = MathHelper.ceiling_float_int(this.mc.thePlayer.prevHealth);
/*  979 */     this.rand.setSeed((this.updateCounter * 312871));
/*      */     
/*  981 */     FoodStats var7 = this.mc.thePlayer.getFoodStats();
/*      */     
/*  983 */     int var8 = var7.getNutrition();
/*      */     
/*  985 */     AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
/*  986 */     int var11 = par1 / 2 - 91;
/*  987 */     int var12 = par1 / 2 + 91;
/*  988 */     int var13 = par2 - 39;
/*  989 */     float var14 = (float)var10.getAttributeValue();
/*  990 */     float var15 = this.mc.thePlayer.getAbsorptionAmount();
/*  991 */     int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
/*  992 */     int var17 = Math.max(10 - var16 - 2, 3);
/*  993 */     int var18 = var13 - (var16 - 1) * var17 - 10;
/*  994 */     float var19 = var15;
/*      */ 
/*      */     
/*  997 */     float total_protection = this.mc.thePlayer.getTotalProtection((DamageSource)null);
/*  998 */     int var20 = MathHelper.ceiling_float_int(total_protection);
/*  999 */     int var21 = -1;
/*      */     
/* 1001 */     if (this.mc.thePlayer.isPotionActive(Potion.regeneration))
/*      */     {
/* 1003 */       var21 = this.updateCounter % MathHelper.ceiling_float_int(var14 + 5.0F);
/*      */     }
/*      */     
/* 1006 */     this.mc.mcProfiler.startSection("armor");
/*      */     
/*      */     int var22;
/*      */     
/* 1010 */     for (var22 = 0; var22 < 10; var22++) {
/*      */ 
/*      */       
/* 1013 */       if (total_protection > 0.0F || this.mc.thePlayer.isWearingArmor()) {
/*      */         
/* 1015 */         int var23 = var11 + var22 * 8;
/*      */         
/* 1017 */         if (var22 * 2 + 1 < var20)
/*      */         {
/* 1019 */           drawTexturedModalRect(var23, var18, 34, 9, 9, 9);
/*      */         }
/*      */         
/* 1022 */         if (var22 * 2 + 1 == var20)
/*      */         {
/* 1024 */           drawTexturedModalRect(var23, var18, 25, 9, 9, 9);
/*      */         }
/*      */         
/* 1027 */         if (var22 * 2 + 1 > var20)
/*      */         {
/* 1029 */           drawTexturedModalRect(var23, var18, 16, 9, 9, 9);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1034 */     this.mc.mcProfiler.endStartSection("health");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1039 */     for (var22 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F) - 1; var22 >= 0; var22--) {
/*      */       
/* 1041 */       int var23 = 16;
/*      */       
/* 1043 */       if (this.mc.thePlayer.isPotionActive(Potion.poison)) {
/*      */         
/* 1045 */         var23 += 36;
/*      */       }
/* 1047 */       else if (this.mc.thePlayer.isPotionActive(Potion.wither)) {
/*      */         
/* 1049 */         var23 += 72;
/*      */       } 
/*      */       
/* 1052 */       byte var24 = 0;
/*      */       
/* 1054 */       if (var3)
/*      */       {
/* 1056 */         var24 = 1;
/*      */       }
/*      */       
/* 1059 */       int var25 = MathHelper.ceiling_float_int((var22 + 1) / 10.0F) - 1;
/* 1060 */       int var26 = var11 + var22 % 10 * 8;
/* 1061 */       int var27 = var13 - var25 * var17;
/*      */       
/* 1063 */       if (var4 <= 4)
/*      */       {
/* 1065 */         var27 += this.rand.nextInt(2);
/*      */       }
/*      */       
/* 1068 */       if (var22 == var21)
/*      */       {
/* 1070 */         var27 -= 2;
/*      */       }
/*      */       
/* 1073 */       byte var28 = 0;
/*      */       
/* 1075 */       if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
/*      */       {
/* 1077 */         var28 = 5;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1082 */       if (var22 < this.mc.thePlayer.getMaxHealth() / 2.0F) {
/* 1083 */         drawTexturedModalRect(var26, var27, 16 + var24 * 9, 9 * var28, 9, 9);
/*      */       }
/* 1085 */       if (var3) {
/*      */         
/* 1087 */         if (var22 * 2 + 1 < var5)
/*      */         {
/* 1089 */           drawTexturedModalRect(var26, var27, var23 + 54, 9 * var28, 9, 9);
/*      */         }
/*      */         
/* 1092 */         if (var22 * 2 + 1 == var5)
/*      */         {
/* 1094 */           drawTexturedModalRect(var26, var27, var23 + 63, 9 * var28, 9, 9);
/*      */         }
/*      */       } 
/*      */       
/* 1098 */       if (var19 > 0.0F) {
/*      */         
/* 1100 */         if (var19 == var15 && var15 % 2.0F == 1.0F) {
/*      */           
/* 1102 */           drawTexturedModalRect(var26, var27, var23 + 153, 9 * var28, 9, 9);
/*      */         }
/*      */         else {
/*      */           
/* 1106 */           drawTexturedModalRect(var26, var27, var23 + 144, 9 * var28, 9, 9);
/*      */         } 
/*      */         
/* 1109 */         var19 -= 2.0F;
/*      */       }
/*      */       else {
/*      */         
/* 1113 */         if (var22 * 2 + 1 < var4)
/*      */         {
/* 1115 */           drawTexturedModalRect(var26, var27, var23 + 36, 9 * var28, 9, 9);
/*      */         }
/*      */         
/* 1118 */         if (var22 * 2 + 1 == var4)
/*      */         {
/* 1120 */           drawTexturedModalRect(var26, var27, var23 + 45, 9 * var28, 9, 9);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1125 */     Entity var34 = this.mc.thePlayer.ridingEntity;
/*      */ 
/*      */ 
/*      */     
/* 1129 */     if (var34 == null || var34 instanceof EntityBoat) {
/*      */       
/* 1131 */       this.mc.mcProfiler.endStartSection("food");
/*      */       
/* 1133 */       for (int var23 = 0; var23 < 10; var23++)
/*      */       {
/* 1135 */         int var35 = var13;
/* 1136 */         int var25 = 16;
/* 1137 */         byte var36 = 0;
/*      */         
/* 1139 */         if (this.mc.thePlayer.isPotionActive(Potion.hunger)) {
/*      */           
/* 1141 */           var25 += 36;
/* 1142 */           var36 = 13;
/*      */         } 
/*      */ 
/*      */         
/* 1146 */         if (this.mc.thePlayer.isHungry() && this.updateCounter % (var8 * 3 + 1) == 0)
/*      */         {
/* 1148 */           var35 = var13 + this.rand.nextInt(3) - 1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1156 */         int var27 = var12 - var23 * 8 - 9;
/*      */ 
/*      */         
/* 1159 */         if (var23 < this.mc.thePlayer.foodStats.getNutritionLimit() / 2) {
/* 1160 */           drawTexturedModalRect(var27, var35, 16 + var36 * 9, 27, 9, 9);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1175 */         if (var23 * 2 + 1 < var8)
/*      */         {
/* 1177 */           drawTexturedModalRect(var27, var35, var25 + 36, 27, 9, 9);
/*      */         }
/*      */         
/* 1180 */         if (var23 * 2 + 1 == var8)
/*      */         {
/* 1182 */           drawTexturedModalRect(var27, var35, var25 + 45, 27, 9, 9);
/*      */         }
/*      */       }
/*      */     
/* 1186 */     } else if (var34 instanceof EntityLivingBase) {
/*      */       
/* 1188 */       this.mc.mcProfiler.endStartSection("mountHealth");
/* 1189 */       EntityLivingBase var38 = (EntityLivingBase)var34;
/* 1190 */       int var35 = (int)Math.ceil(var38.getHealth());
/* 1191 */       float var37 = var38.getMaxHealth();
/* 1192 */       int var26 = (int)(var37 + 0.5F) / 2;
/*      */       
/* 1194 */       if (var26 > 30)
/*      */       {
/* 1196 */         var26 = 30;
/*      */       }
/*      */       
/* 1199 */       int var27 = var13;
/*      */       
/* 1201 */       for (int var39 = 0; var26 > 0; var39 += 20) {
/*      */         
/* 1203 */         int var29 = Math.min(var26, 10);
/* 1204 */         var26 -= var29;
/*      */         
/* 1206 */         for (int var30 = 0; var30 < var29; var30++) {
/*      */           
/* 1208 */           byte var31 = 52;
/* 1209 */           byte var32 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1216 */           int var33 = var12 - var30 * 8 - 9;
/* 1217 */           drawTexturedModalRect(var33, var27, var31 + var32 * 9, 9, 9, 9);
/*      */           
/* 1219 */           if (var30 * 2 + 1 + var39 < var35)
/*      */           {
/* 1221 */             drawTexturedModalRect(var33, var27, var31 + 36, 9, 9, 9);
/*      */           }
/*      */           
/* 1224 */           if (var30 * 2 + 1 + var39 == var35)
/*      */           {
/* 1226 */             drawTexturedModalRect(var33, var27, var31 + 45, 9, 9, 9);
/*      */           }
/*      */         } 
/*      */         
/* 1230 */         var27 -= 10;
/*      */       } 
/*      */     } 
/*      */     
/* 1234 */     this.mc.mcProfiler.endStartSection("air");
/*      */     
/* 1236 */     if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
/*      */       
/* 1238 */       int var23 = this.mc.thePlayer.getAir();
/* 1239 */       int var35 = MathHelper.ceiling_double_int((var23 - 2) * 10.0D / 300.0D);
/* 1240 */       int var25 = MathHelper.ceiling_double_int(var23 * 10.0D / 300.0D) - var35;
/*      */       
/* 1242 */       for (int var26 = 0; var26 < var35 + var25; var26++) {
/*      */         
/* 1244 */         if (var26 < var35) {
/*      */           
/* 1246 */           drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 16, 18, 9, 9);
/*      */         }
/*      */         else {
/*      */           
/* 1250 */           drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 25, 18, 9, 9);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1255 */     this.mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderBossHealth() {
/* 1263 */     if (BossStatus.bossName != null && BossStatus.statusBarLength > 0) {
/*      */       
/* 1265 */       BossStatus.statusBarLength--;
/* 1266 */       FontRenderer var1 = this.mc.fontRenderer;
/* 1267 */       ScaledResolution var2 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/* 1268 */       int var3 = var2.getScaledWidth();
/* 1269 */       short var4 = 182;
/* 1270 */       int var5 = var3 / 2 - var4 / 2;
/* 1271 */       int var6 = (int)(BossStatus.healthScale * (var4 + 1));
/* 1272 */       byte var7 = 12;
/* 1273 */       drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
/* 1274 */       drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
/*      */       
/* 1276 */       if (var6 > 0)
/*      */       {
/* 1278 */         drawTexturedModalRect(var5, var7, 0, 79, var6, 5);
/*      */       }
/*      */       
/* 1281 */       String var8 = BossStatus.bossName;
/* 1282 */       var1.drawStringWithShadow(var8, var3 / 2 - var1.getStringWidth(var8) / 2, var7 - 10, 16777215);
/* 1283 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1284 */       this.mc.getTextureManager().bindTexture(icons);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderPumpkinBlur(int par1, int par2) {
/* 1290 */     GL11.glDisable(2929);
/* 1291 */     GL11.glDepthMask(false);
/* 1292 */     GL11.glBlendFunc(770, 771);
/* 1293 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1294 */     GL11.glDisable(3008);
/* 1295 */     this.mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
/* 1296 */     Tessellator var3 = Tessellator.instance;
/* 1297 */     var3.startDrawingQuads();
/* 1298 */     var3.addVertexWithUV(0.0D, par2, -90.0D, 0.0D, 1.0D);
/* 1299 */     var3.addVertexWithUV(par1, par2, -90.0D, 1.0D, 1.0D);
/* 1300 */     var3.addVertexWithUV(par1, 0.0D, -90.0D, 1.0D, 0.0D);
/* 1301 */     var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 1302 */     var3.draw();
/* 1303 */     GL11.glDepthMask(true);
/* 1304 */     GL11.glEnable(2929);
/* 1305 */     GL11.glEnable(3008);
/* 1306 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderVisionDim(int par1, int par2, float vision_dimming) {
/* 1311 */     boolean gl_texture_2d = GL11.glGetBoolean(3553);
/* 1312 */     GL11.glDisable(3553);
/*      */     
/* 1314 */     GL11.glDisable(2929);
/* 1315 */     GL11.glDepthMask(false);
/* 1316 */     GL11.glBlendFunc(770, 771);
/* 1317 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, Math.min(vision_dimming, 1.0F));
/* 1318 */     GL11.glDisable(3008);
/*      */     
/* 1320 */     Tessellator var3 = Tessellator.instance;
/* 1321 */     var3.startDrawingQuads();
/* 1322 */     var3.addVertexWithUV(0.0D, par2, -90.0D, 0.0D, 1.0D);
/* 1323 */     var3.addVertexWithUV(par1, par2, -90.0D, 1.0D, 1.0D);
/* 1324 */     var3.addVertexWithUV(par1, 0.0D, -90.0D, 1.0D, 0.0D);
/* 1325 */     var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 1326 */     var3.draw();
/* 1327 */     GL11.glDepthMask(true);
/* 1328 */     GL11.glEnable(2929);
/* 1329 */     GL11.glEnable(3008);
/* 1330 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/* 1332 */     if (gl_texture_2d)
/* 1333 */       GL11.glEnable(3553); 
/*      */   }
/*      */   
/*      */   private void renderRunegateEffect(int par1, int par2) {
/*      */     int r, g, b;
/* 1338 */     GL11.glDisable(2929);
/* 1339 */     GL11.glDepthMask(false);
/* 1340 */     GL11.glBlendFunc(770, 771);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1348 */     if (this.mc.theWorld.isOverworld()) {
/*      */       
/* 1350 */       r = 53;
/* 1351 */       g = 159;
/* 1352 */       b = 255;
/*      */     }
/* 1354 */     else if (this.mc.theWorld.isUnderworld()) {
/*      */       
/* 1356 */       r = 68;
/* 1357 */       g = 1;
/* 1358 */       b = 180;
/*      */     }
/* 1360 */     else if (this.mc.theWorld.isTheNether()) {
/*      */       
/* 1362 */       r = 228;
/* 1363 */       g = 123;
/* 1364 */       b = 78;
/*      */     }
/*      */     else {
/*      */       
/* 1368 */       r = 255;
/* 1369 */       g = 255;
/* 1370 */       b = 255;
/*      */     } 
/*      */     
/* 1373 */     GL11.glColor4f(r / 255.0F, g / 255.0F, b / 255.0F, Math.min(this.mc.thePlayer.runegate_counter / 20.0F, 1.0F));
/*      */ 
/*      */ 
/*      */     
/* 1377 */     GL11.glDisable(3008);
/*      */     
/* 1379 */     GL11.glDisable(3553);
/* 1380 */     Tessellator var3 = Tessellator.instance;
/* 1381 */     var3.startDrawingQuads();
/* 1382 */     var3.addVertexWithUV(0.0D, par2, -90.0D, 0.0D, 1.0D);
/* 1383 */     var3.addVertexWithUV(par1, par2, -90.0D, 1.0D, 1.0D);
/* 1384 */     var3.addVertexWithUV(par1, 0.0D, -90.0D, 1.0D, 0.0D);
/* 1385 */     var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 1386 */     var3.draw();
/* 1387 */     GL11.glDepthMask(true);
/* 1388 */     GL11.glEnable(2929);
/* 1389 */     GL11.glEnable(3008);
/* 1390 */     GL11.glEnable(3553);
/* 1391 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderVignette(float par1, int par2, int par3) {
/* 1401 */     par1 = 1.0F - par1;
/*      */     
/* 1403 */     if (par1 < 0.0F)
/*      */     {
/* 1405 */       par1 = 0.0F;
/*      */     }
/*      */     
/* 1408 */     if (par1 > 1.0F)
/*      */     {
/* 1410 */       par1 = 1.0F;
/*      */     }
/*      */     
/* 1413 */     this.prevVignetteBrightness = (float)(this.prevVignetteBrightness + (par1 - this.prevVignetteBrightness) * 0.01D);
/*      */     
/* 1415 */     if (this.mc.theWorld.provider.drawGuiVignette()) {
/*      */       
/* 1417 */       GL11.glDisable(2929);
/* 1418 */       GL11.glDepthMask(false);
/* 1419 */       GL11.glBlendFunc(0, 769);
/* 1420 */       GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
/* 1421 */       this.mc.getTextureManager().bindTexture(vignetteTexPath);
/* 1422 */       Tessellator var4 = Tessellator.instance;
/* 1423 */       var4.startDrawingQuads();
/* 1424 */       var4.addVertexWithUV(0.0D, par3, -90.0D, 0.0D, 1.0D);
/* 1425 */       var4.addVertexWithUV(par2, par3, -90.0D, 1.0D, 1.0D);
/* 1426 */       var4.addVertexWithUV(par2, 0.0D, -90.0D, 1.0D, 0.0D);
/* 1427 */       var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 1428 */       var4.draw();
/*      */     }
/*      */     else {
/*      */       
/* 1432 */       this.prevVignetteBrightness = 1.0F;
/*      */     } 
/*      */     
/* 1435 */     GL11.glDepthMask(true);
/* 1436 */     GL11.glEnable(2929);
/* 1437 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1438 */     GL11.glBlendFunc(770, 771);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_130015_b(float par1, int par2, int par3) {
/* 1443 */     if (par1 < 1.0F) {
/*      */       
/* 1445 */       par1 *= par1;
/* 1446 */       par1 *= par1;
/* 1447 */       par1 = par1 * 0.8F + 0.2F;
/*      */     } 
/*      */     
/* 1450 */     GL11.glDisable(3008);
/* 1451 */     GL11.glDisable(2929);
/* 1452 */     GL11.glDepthMask(false);
/* 1453 */     GL11.glBlendFunc(770, 771);
/* 1454 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, par1);
/* 1455 */     Icon var4 = Block.portal.getBlockTextureFromSide(1);
/* 1456 */     this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 1457 */     float var5 = var4.getMinU();
/* 1458 */     float var6 = var4.getMinV();
/* 1459 */     float var7 = var4.getMaxU();
/* 1460 */     float var8 = var4.getMaxV();
/* 1461 */     Tessellator var9 = Tessellator.instance;
/* 1462 */     var9.startDrawingQuads();
/* 1463 */     var9.addVertexWithUV(0.0D, par3, -90.0D, var5, var8);
/* 1464 */     var9.addVertexWithUV(par2, par3, -90.0D, var7, var8);
/* 1465 */     var9.addVertexWithUV(par2, 0.0D, -90.0D, var7, var6);
/* 1466 */     var9.addVertexWithUV(0.0D, 0.0D, -90.0D, var5, var6);
/* 1467 */     var9.draw();
/* 1468 */     GL11.glDepthMask(true);
/* 1469 */     GL11.glEnable(2929);
/* 1470 */     GL11.glEnable(3008);
/* 1471 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderInventorySlot(int par1, int par2, int par3, float par4) {
/* 1479 */     ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[par1];
/*      */     
/* 1481 */     if (var5 != null) {
/*      */       
/* 1483 */       float var6 = var5.animationsToGo - par4;
/*      */       
/* 1485 */       if (var6 > 0.0F) {
/*      */         
/* 1487 */         GL11.glPushMatrix();
/* 1488 */         float var7 = 1.0F + var6 / 5.0F;
/* 1489 */         GL11.glTranslatef((par2 + 8), (par3 + 12), 0.0F);
/* 1490 */         GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
/* 1491 */         GL11.glTranslatef(-(par2 + 8), -(par3 + 12), 0.0F);
/*      */       } 
/*      */       
/* 1494 */       itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), var5, par2, par3);
/*      */       
/* 1496 */       if (var6 > 0.0F)
/*      */       {
/* 1498 */         GL11.glPopMatrix();
/*      */       }
/*      */       
/* 1501 */       itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), var5, par2, par3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTick() {
/* 1510 */     if (this.recordPlayingUpFor > 0)
/*      */     {
/* 1512 */       this.recordPlayingUpFor--;
/*      */     }
/*      */     
/* 1515 */     this.updateCounter++;
/*      */     
/* 1517 */     if (this.mc.thePlayer != null) {
/*      */       
/* 1519 */       ItemStack var1 = this.mc.thePlayer.inventory.getCurrentItemStack();
/*      */       
/* 1521 */       if (var1 == null) {
/*      */         
/* 1523 */         this.remainingHighlightTicks = 0;
/*      */ 
/*      */       
/*      */       }
/* 1527 */       else if (var1.getMITEStyleDisplayName().equals(this.last_highlighting_item_stack_text)) {
/*      */         
/* 1529 */         if (this.remainingHighlightTicks > 0)
/*      */         {
/* 1531 */           this.remainingHighlightTicks--;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1536 */         this.remainingHighlightTicks = 40;
/*      */       } 
/*      */       
/* 1539 */       this.highlightingItemStack = var1;
/*      */       
/* 1541 */       if (this.curse_notification_counter > 0 && this.mc.currentScreen == null) {
/* 1542 */         this.curse_notification_counter--;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setRecordPlayingMessage(String par1Str) {
/* 1548 */     func_110326_a("Now playing: " + par1Str, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110326_a(String par1Str, boolean par2) {
/* 1553 */     this.recordPlaying = par1Str;
/* 1554 */     this.recordPlayingUpFor = 60;
/* 1555 */     this.recordIsPlaying = par2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GuiNewChat getChatGUI() {
/* 1563 */     return this.persistantChatGUI;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUpdateCounter() {
/* 1568 */     return this.updateCounter;
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawTournamentScore(int row, int col, FontRenderer var8) {
/* 1573 */     String tournament_score = "" + this.mc.thePlayer.tournament_score;
/*      */     
/* 1575 */     drawString(var8, tournament_score, col, 2 + 10 * row++, (this.mc.thePlayer.tournament_score < 0) ? 16716563 : 8453920);
/*      */     
/* 1577 */     if (this.mc.thePlayer.delta_tournament_score != 0 && this.mc.thePlayer.delta_tournament_score_opacity > 4) {
/*      */       
/* 1579 */       int effective_opacity = MathHelper.clamp_int(this.mc.thePlayer.delta_tournament_score_opacity, 0, 255) * 256 * 256 * 256;
/*      */       
/* 1581 */       GL11.glEnable(3042);
/* 1582 */       GL11.glBlendFunc(770, 771);
/*      */ 
/*      */       
/* 1585 */       String delta_tournament_score = ((this.mc.thePlayer.delta_tournament_score < 1) ? "" : "+") + this.mc.thePlayer.delta_tournament_score;
/*      */       
/* 1587 */       row--;
/* 1588 */       col += var8.getStringWidth(tournament_score);
/*      */       
/* 1590 */       drawString(var8, " (", col, 2 + 10 * row, 12632256 + effective_opacity);
/* 1591 */       col += var8.getStringWidth(" (");
/*      */       
/* 1593 */       drawString(var8, delta_tournament_score, col, 2 + 10 * row, (this.mc.thePlayer.delta_tournament_score < 0) ? (16716563 + effective_opacity) : (8453920 + effective_opacity));
/* 1594 */       col += var8.getStringWidth(delta_tournament_score);
/*      */       
/* 1596 */       drawString(var8, ")", col, 2 + 10 * row, 12632256 + effective_opacity);
/*      */       
/* 1598 */       row++;
/*      */       
/* 1600 */       GL11.glDisable(3042);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawAllottedTime(int row, int col, FontRenderer var8) {
/* 1613 */     int seconds = allotted_time / 20;
/*      */     
/* 1615 */     int hours = seconds / 3600;
/* 1616 */     seconds -= hours * 3600;
/*      */     
/* 1618 */     int minutes = seconds / 60;
/* 1619 */     seconds -= minutes * 60;
/*      */     
/* 1621 */     drawString(var8, hours + ":" + ((minutes < 10) ? ("0" + minutes) : (String)Integer.valueOf(minutes)) + ":" + ((seconds < 10) ? ("0" + seconds) : (String)Integer.valueOf(seconds)), col, 2 + 10 * row++, (this.mc.thePlayer.tournament_score < 0) ? 16716563 : 8453920);
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiIngame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */