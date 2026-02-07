/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class GuiAchievements
/*     */   extends GuiScreen
/*     */ {
/*  11 */   private static final int guiMapTop = AchievementList.minDisplayColumn * 24 - 112;
/*     */ 
/*     */   
/*  14 */   private static final int guiMapLeft = AchievementList.minDisplayRow * 24 - 112;
/*     */ 
/*     */   
/*  17 */   private static final int guiMapBottom = AchievementList.maxDisplayColumn * 24 - 77;
/*     */ 
/*     */   
/*  20 */   private static final int guiMapRight = AchievementList.maxDisplayRow * 24 - 77;
/*  21 */   private static final ResourceLocation achievementTextures = new ResourceLocation("textures/gui/achievement/achievement_background.png");
/*  22 */   protected int achievementsPaneWidth = 256;
/*  23 */   protected int achievementsPaneHeight = 202;
/*     */   
/*     */   protected int mouseX;
/*     */   
/*     */   protected int mouseY;
/*     */   
/*     */   protected double field_74117_m;
/*     */   
/*     */   protected double field_74115_n;
/*     */   
/*     */   protected double guiMapX;
/*     */   
/*     */   protected double guiMapY;
/*     */   
/*     */   protected double field_74124_q;
/*     */   
/*     */   protected double field_74123_r;
/*     */   
/*     */   private int isMouseButtonDown;
/*     */   
/*     */   private StatFileWriter statFileWriter;
/*     */   
/*     */   public static final int view_mode_player = 0;
/*     */   
/*     */   public static final int view_mode_this_world = 1;
/*     */   public static final int view_mode_all_worlds = 2;
/*  49 */   public static int view_mode = 0;
/*     */   
/*     */   GuiButton button_cycle_view_mode;
/*     */ 
/*     */   
/*     */   public GuiAchievements(StatFileWriter par1StatFileWriter) {
/*  55 */     this.statFileWriter = par1StatFileWriter;
/*  56 */     short var2 = 141;
/*  57 */     short var3 = 141;
/*  58 */     this.field_74117_m = this.guiMapX = this.field_74124_q = (AchievementList.openInventory.displayColumn * 24 - var2 / 2 - 12);
/*  59 */     this.field_74115_n = this.guiMapY = this.field_74123_r = (AchievementList.openInventory.displayRow * 24 - var3 / 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateViewModeButtonState() {
/*  64 */     if (Minecraft.theMinecraft.thePlayer.haveAchievementsBeenUnlockedByOtherPlayers()) {
/*     */       
/*  66 */       this.button_cycle_view_mode.drawButton = true;
/*     */     }
/*     */     else {
/*     */       
/*  70 */       this.button_cycle_view_mode.drawButton = false;
/*  71 */       view_mode = 0;
/*     */     } 
/*     */     
/*  74 */     this.button_cycle_view_mode.displayString = I18n.getString((view_mode == 0) ? "gui.stats.thisWorld" : ((view_mode == 1) ? (Minecraft.inDevMode() ? "gui.stats.allWorlds" : "gui.stats.player") : "gui.stats.player"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  82 */     view_mode = 0;
/*     */     
/*  84 */     this.buttonList.clear();
/*  85 */     this.buttonList.add(new GuiSmallButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, I18n.getString("gui.done")));
/*     */ 
/*     */     
/*  88 */     this.buttonList.add(this.button_cycle_view_mode = new GuiSmallButton(2, this.width / 2 - 24 - 80, this.height / 2 + 74, 80, 20, ""));
/*  89 */     updateViewModeButtonState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  97 */     if (par1GuiButton.id == 1) {
/*     */       
/*  99 */       this.mc.displayGuiScreen((GuiScreen)null);
/* 100 */       this.mc.setIngameFocus();
/*     */     }
/* 102 */     else if (par1GuiButton.id == 2) {
/*     */       
/* 104 */       if (++view_mode > (Minecraft.inDevMode() ? 2 : 1)) {
/* 105 */         view_mode = 0;
/*     */       }
/* 107 */       updateViewModeButtonState();
/*     */     } 
/*     */     
/* 110 */     super.actionPerformed(par1GuiButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 118 */     if (par2 == this.mc.gameSettings.keyBindInventory.keyCode) {
/*     */       
/* 120 */       this.mc.displayGuiScreen((GuiScreen)null);
/* 121 */       this.mc.setIngameFocus();
/*     */     }
/*     */     else {
/*     */       
/* 125 */       super.keyTyped(par1, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 134 */     updateViewModeButtonState();
/*     */     
/* 136 */     if (Mouse.isButtonDown(0)) {
/*     */       
/* 138 */       int var4 = (this.width - this.achievementsPaneWidth) / 2;
/* 139 */       int var5 = (this.height - this.achievementsPaneHeight) / 2;
/* 140 */       int var6 = var4 + 8;
/* 141 */       int var7 = var5 + 17;
/*     */       
/* 143 */       if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && par1 >= var6 && par1 < var6 + 224 && par2 >= var7 && par2 < var7 + 155) {
/*     */         
/* 145 */         if (this.isMouseButtonDown == 0) {
/*     */           
/* 147 */           this.isMouseButtonDown = 1;
/*     */         }
/*     */         else {
/*     */           
/* 151 */           this.guiMapX -= (par1 - this.mouseX);
/* 152 */           this.guiMapY -= (par2 - this.mouseY);
/* 153 */           this.field_74124_q = this.field_74117_m = this.guiMapX;
/* 154 */           this.field_74123_r = this.field_74115_n = this.guiMapY;
/*     */         } 
/*     */         
/* 157 */         this.mouseX = par1;
/* 158 */         this.mouseY = par2;
/*     */       } 
/*     */       
/* 161 */       if (this.field_74124_q < guiMapTop)
/*     */       {
/* 163 */         this.field_74124_q = guiMapTop;
/*     */       }
/*     */       
/* 166 */       if (this.field_74123_r < guiMapLeft)
/*     */       {
/* 168 */         this.field_74123_r = guiMapLeft;
/*     */       }
/*     */       
/* 171 */       if (this.field_74124_q >= guiMapBottom)
/*     */       {
/* 173 */         this.field_74124_q = (guiMapBottom - 1);
/*     */       }
/*     */       
/* 176 */       if (this.field_74123_r >= guiMapRight)
/*     */       {
/* 178 */         this.field_74123_r = (guiMapRight - 1);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 183 */       this.isMouseButtonDown = 0;
/*     */     } 
/*     */     
/* 186 */     drawDefaultBackground();
/* 187 */     genAchievementBackground(par1, par2, par3);
/* 188 */     GL11.glDisable(2896);
/* 189 */     GL11.glDisable(2929);
/* 190 */     drawTitle();
/* 191 */     GL11.glEnable(2896);
/* 192 */     GL11.glEnable(2929);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 200 */     this.field_74117_m = this.guiMapX;
/* 201 */     this.field_74115_n = this.guiMapY;
/* 202 */     double var1 = this.field_74124_q - this.guiMapX;
/* 203 */     double var3 = this.field_74123_r - this.guiMapY;
/*     */     
/* 205 */     if (var1 * var1 + var3 * var3 < 4.0D) {
/*     */       
/* 207 */       this.guiMapX += var1;
/* 208 */       this.guiMapY += var3;
/*     */     }
/*     */     else {
/*     */       
/* 212 */       this.guiMapX += var1 * 0.85D;
/* 213 */       this.guiMapY += var3 * 0.85D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawTitle() {
/* 222 */     int var1 = (this.width - this.achievementsPaneWidth) / 2;
/* 223 */     int var2 = (this.height - this.achievementsPaneHeight) / 2;
/* 224 */     this.fontRenderer.drawString("Achievements", var1 + 15, var2 + 5, 4210752);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasAchievementUnlocked(Achievement achievement) {
/* 229 */     if (view_mode == 0)
/* 230 */       return PlayerStatsHelper.hasAchievementUnlocked(achievement); 
/* 231 */     if (view_mode == 1) {
/* 232 */       return this.mc.theWorld.worldInfo.hasAchievementUnlocked(achievement);
/*     */     }
/* 234 */     return this.statFileWriter.hasAchievementUnlocked(achievement);
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
/*     */   private boolean canUnlockAchievement(Achievement achievement) {
/* 247 */     return haveAllParentAchievementsBeenUnlocked(achievement, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean haveAllParentAchievementsBeenUnlocked(Achievement achievement, boolean include_second_parents) {
/* 255 */     if (include_second_parents && achievement.hasSecondParent() && hasAchievementUnlocked(achievement.getSecondParent()) && haveAllParentAchievementsBeenUnlocked(achievement.getSecondParent(), true)) {
/* 256 */       return true;
/*     */     }
/* 258 */     while (achievement.parentAchievement != null) {
/*     */       
/* 260 */       achievement = achievement.parentAchievement;
/*     */       
/* 262 */       if (!hasAchievementUnlocked(achievement)) {
/* 263 */         return false;
/*     */       }
/* 265 */       if (include_second_parents && achievement.hasSecondParent() && hasAchievementUnlocked(achievement.getSecondParent()) && haveAllParentAchievementsBeenUnlocked(achievement.getSecondParent(), true)) {
/* 266 */         return true;
/*     */       }
/*     */     } 
/* 269 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void genAchievementBackground(int par1, int par2, float par3) {
/* 274 */     int var4 = MathHelper.floor_double(this.field_74117_m + (this.guiMapX - this.field_74117_m) * par3);
/* 275 */     int var5 = MathHelper.floor_double(this.field_74115_n + (this.guiMapY - this.field_74115_n) * par3);
/*     */     
/* 277 */     if (var4 < guiMapTop)
/*     */     {
/* 279 */       var4 = guiMapTop;
/*     */     }
/*     */     
/* 282 */     if (var5 < guiMapLeft)
/*     */     {
/* 284 */       var5 = guiMapLeft;
/*     */     }
/*     */     
/* 287 */     if (var4 >= guiMapBottom)
/*     */     {
/* 289 */       var4 = guiMapBottom - 1;
/*     */     }
/*     */     
/* 292 */     if (var5 >= guiMapRight)
/*     */     {
/* 294 */       var5 = guiMapRight - 1;
/*     */     }
/*     */     
/* 297 */     int var6 = (this.width - this.achievementsPaneWidth) / 2;
/* 298 */     int var7 = (this.height - this.achievementsPaneHeight) / 2;
/* 299 */     int var8 = var6 + 16;
/* 300 */     int var9 = var7 + 17;
/* 301 */     this.zLevel = 0.0F;
/* 302 */     GL11.glDepthFunc(518);
/* 303 */     GL11.glPushMatrix();
/* 304 */     GL11.glTranslatef(0.0F, 0.0F, -200.0F);
/* 305 */     GL11.glEnable(3553);
/* 306 */     GL11.glDisable(2896);
/* 307 */     GL11.glEnable(32826);
/* 308 */     GL11.glEnable(2903);
/* 309 */     int var10 = var4 + 288 >> 4;
/* 310 */     int var11 = var5 + 288 >> 4;
/* 311 */     int var12 = (var4 + 288) % 16;
/* 312 */     int var13 = (var5 + 288) % 16;
/* 313 */     boolean var14 = true;
/* 314 */     boolean var15 = true;
/* 315 */     boolean var16 = true;
/* 316 */     boolean var17 = true;
/* 317 */     boolean var18 = true;
/* 318 */     Random var19 = new Random();
/*     */ 
/*     */     
/*     */     int var20;
/*     */     
/* 323 */     for (var20 = 0; var20 * 16 - var13 < 155; var20++) {
/*     */       
/* 325 */       float var21 = 0.6F - (var11 + var20) / 25.0F * 0.3F;
/* 326 */       GL11.glColor4f(var21, var21, var21, 1.0F);
/*     */       
/* 328 */       for (int i = 0; i * 16 - var12 < 224; i++) {
/*     */         
/* 330 */         var19.setSeed((1234 + var10 + i));
/* 331 */         var19.nextInt();
/* 332 */         int var23 = var19.nextInt(1 + var11 + var20) + (var11 + var20) / 2;
/* 333 */         Icon var24 = Block.sand.getIcon(0, 0);
/*     */         
/* 335 */         if (var23 <= 37 && var11 + var20 != 35) {
/*     */           
/* 337 */           if (var23 == 22) {
/*     */             
/* 339 */             if (var19.nextInt(2) == 0)
/*     */             {
/* 341 */               var24 = Block.oreDiamond.getIcon(0, 0);
/*     */             }
/*     */             else
/*     */             {
/* 345 */               var24 = Block.oreRedstone.getIcon(0, 0);
/*     */             }
/*     */           
/* 348 */           } else if (var23 == 10) {
/*     */             
/* 350 */             var24 = Block.oreIron.getIcon(0, 0);
/*     */           }
/* 352 */           else if (var23 == 8) {
/*     */             
/* 354 */             var24 = Block.oreCoal.getIcon(0, 0);
/*     */           }
/* 356 */           else if (var23 > 4) {
/*     */             
/* 358 */             var24 = Block.stone.getIcon(0, 0);
/*     */           }
/* 360 */           else if (var23 > 0) {
/*     */             
/* 362 */             var24 = Block.dirt.getIcon(0, 0);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 367 */           var24 = Block.bedrock.getIcon(0, 0);
/*     */         } 
/*     */         
/* 370 */         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 371 */         drawTexturedModelRectFromIcon(var8 + i * 16 - var12, var9 + var20 * 16 - var13, var24, 16, 16);
/*     */       } 
/*     */     } 
/*     */     
/* 375 */     GL11.glEnable(2929);
/* 376 */     GL11.glDepthFunc(515);
/* 377 */     GL11.glDisable(3553);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 382 */     for (int pass = 0; pass < 2; pass++) {
/* 383 */       for (var20 = 0; var20 < AchievementList.achievementList.size(); var20++) {
/*     */         
/* 385 */         Achievement var31 = AchievementList.achievementList.get(var20);
/*     */         
/* 387 */         if (var31.parentAchievement != null) {
/*     */           
/* 389 */           int i = var31.displayColumn * 24 - var4 + 11 + var8;
/* 390 */           int var23 = var31.displayRow * 24 - var5 + 11 + var9;
/* 391 */           int var41 = var31.parentAchievement.displayColumn * 24 - var4 + 11 + var8;
/* 392 */           int var25 = var31.parentAchievement.displayRow * 24 - var5 + 11 + var9;
/*     */ 
/*     */           
/* 395 */           if (var31.isFlipped()) {
/*     */             
/* 397 */             i = var31.parentAchievement.displayColumn * 24 - var4 + 11 + var8;
/* 398 */             var23 = var31.parentAchievement.displayRow * 24 - var5 + 11 + var9;
/* 399 */             var41 = var31.displayColumn * 24 - var4 + 11 + var8;
/* 400 */             var25 = var31.displayRow * 24 - var5 + 11 + var9;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 405 */           boolean var26 = hasAchievementUnlocked(var31);
/* 406 */           boolean var27 = canUnlockAchievement(var31);
/*     */           
/* 408 */           if (!haveAllParentAchievementsBeenUnlocked(var31, false)) {
/*     */             
/* 410 */             if (!hasAchievementUnlocked(var31.parentAchievement)) {
/* 411 */               var26 = false;
/*     */             }
/* 413 */             var27 = false;
/*     */           } 
/*     */           
/* 416 */           int var28 = (Math.sin((Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D) ? 255 : 130;
/* 417 */           int var29 = -16777216;
/*     */           
/* 419 */           if (var26) {
/*     */             
/* 421 */             if (pass == 0) {
/*     */               continue;
/*     */             }
/* 424 */             var29 = -9408400;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 430 */           else if (var27) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 435 */             var29 = (Math.sin((Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D) ? -16728064 : -16744448;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 441 */           if (pass == 1 && !var26) {
/*     */             continue;
/*     */           }
/* 444 */           if (var31.hasSecondParent() && hasAchievementUnlocked(var31.parentAchievement)) {
/* 445 */             drawLines(var4, var5, var8, var9, var31, var31.getSecondParent());
/*     */           }
/* 447 */           drawHorizontalLine(i, var41, var23, var29);
/* 448 */           drawVerticalLine(var41, var23, var25, var29);
/*     */         } 
/*     */         
/* 451 */         if (var31.hasSecondParent() && !hasAchievementUnlocked(var31.parentAchievement))
/* 452 */           drawLines(var4, var5, var8, var9, var31, var31.getSecondParent());  continue;
/*     */       } 
/*     */     } 
/* 455 */     Achievement var30 = null;
/* 456 */     RenderItem var32 = new RenderItem();
/* 457 */     RenderHelper.enableGUIStandardItemLighting();
/* 458 */     GL11.glDisable(2896);
/* 459 */     GL11.glEnable(32826);
/* 460 */     GL11.glEnable(2903);
/*     */ 
/*     */ 
/*     */     
/* 464 */     for (int var22 = 0; var22 < AchievementList.achievementList.size(); var22++) {
/*     */       
/* 466 */       Achievement var34 = AchievementList.achievementList.get(var22);
/* 467 */       int var41 = var34.displayColumn * 24 - var4;
/* 468 */       int var25 = var34.displayRow * 24 - var5;
/*     */       
/* 470 */       if (var41 >= -24 && var25 >= -24 && var41 <= 224 && var25 <= 155) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 475 */         if (hasAchievementUnlocked(var34)) {
/*     */           
/* 477 */           float var38 = 1.0F;
/* 478 */           GL11.glColor4f(var38, var38, var38, 1.0F);
/*     */         
/*     */         }
/* 481 */         else if (canUnlockAchievement(var34)) {
/*     */           
/* 483 */           float var38 = (Math.sin((Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) < 0.6D) ? 0.6F : 0.8F;
/* 484 */           GL11.glColor4f(var38, var38, var38, 1.0F);
/*     */         }
/*     */         else {
/*     */           
/* 488 */           float var38 = 0.3F;
/* 489 */           GL11.glColor4f(var38, var38, var38, 1.0F);
/*     */         } 
/*     */         
/* 492 */         this.mc.getTextureManager().bindTexture(achievementTextures);
/* 493 */         int var40 = var8 + var41;
/* 494 */         int var39 = var9 + var25;
/*     */         
/* 496 */         if (var34.getSpecial()) {
/*     */           
/* 498 */           drawTexturedModalRect(var40 - 2, var39 - 2, 26, 202, 26, 26);
/*     */         }
/*     */         else {
/*     */           
/* 502 */           drawTexturedModalRect(var40 - 2, var39 - 2, 0, 202, 26, 26);
/*     */         } 
/*     */ 
/*     */         
/* 506 */         if (!canUnlockAchievement(var34) && !hasAchievementUnlocked(var34)) {
/*     */           
/* 508 */           float var37 = 0.1F;
/* 509 */           GL11.glColor4f(var37, var37, var37, 1.0F);
/* 510 */           var32.renderWithColor = false;
/*     */         } 
/*     */         
/* 513 */         GL11.glEnable(2896);
/* 514 */         GL11.glEnable(2884);
/* 515 */         var32.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), var34.theItemStack, var40 + 3, var39 + 3);
/* 516 */         GL11.glDisable(2896);
/*     */ 
/*     */         
/* 519 */         if (!canUnlockAchievement(var34))
/*     */         {
/* 521 */           var32.renderWithColor = true;
/*     */         }
/*     */         
/* 524 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/* 526 */         if (par1 >= var8 && par2 >= var9 && par1 < var8 + 224 && par2 < var9 + 155 && par1 >= var40 && par1 <= var40 + 22 && par2 >= var39 && par2 <= var39 + 22)
/*     */         {
/* 528 */           var30 = var34;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 533 */     GL11.glDisable(2929);
/* 534 */     GL11.glEnable(3042);
/* 535 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 536 */     this.mc.getTextureManager().bindTexture(achievementTextures);
/* 537 */     drawTexturedModalRect(var6, var7, 0, 0, this.achievementsPaneWidth, this.achievementsPaneHeight);
/* 538 */     GL11.glPopMatrix();
/* 539 */     this.zLevel = 0.0F;
/* 540 */     GL11.glDepthFunc(515);
/* 541 */     GL11.glDisable(2929);
/* 542 */     GL11.glEnable(3553);
/* 543 */     super.drawScreen(par1, par2, par3);
/*     */     
/* 545 */     if (var30 != null) {
/*     */       
/* 547 */       String var33 = I18n.getString(var30.getName());
/*     */       
/* 549 */       if (Minecraft.inDevMode()) {
/* 550 */         var33 = var33 + " (" + (var30.statId - 5242880) + ")";
/*     */       }
/* 552 */       String var35 = var30.getDescription();
/* 553 */       int var41 = par1 + 12;
/* 554 */       int var25 = par2 - 4;
/*     */ 
/*     */       
/* 557 */       if (canUnlockAchievement(var30) || hasAchievementUnlocked(var30)) {
/*     */ 
/*     */         
/* 560 */         int var40 = Math.max(this.fontRenderer.getStringWidth(var33), var30.getTooltipWidth());
/*     */         
/* 562 */         if (hasAchievementUnlocked(var30) && view_mode == 1) {
/* 563 */           var40 = Math.max(var40, this.fontRenderer.getStringWidth(getTakenByText(var30)));
/*     */         }
/* 565 */         int var39 = this.fontRenderer.splitStringWidth(var35, var40);
/*     */ 
/*     */         
/* 568 */         if (hasAchievementUnlocked(var30))
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 573 */           var39 += 12;
/*     */         }
/*     */         
/* 576 */         if (var30 == AchievementList.buildBetterPickaxe) {
/* 577 */           var39 += 12;
/*     */         }
/* 579 */         drawGradientRect(var41 - 3, var25 - 3, var41 + var40 + 3, var25 + var39 + 3 + 12, -1073741824, -1073741824);
/*     */         
/* 581 */         this.fontRenderer.drawSplitString(var35, var41, var25 + 12, var40, (canUnlockAchievement(var30) || hasAchievementUnlocked(var30)) ? -6250336 : 5263440);
/*     */         
/* 583 */         if (var30 == AchievementList.buildBetterPickaxe) {
/* 584 */           this.fontRenderer.drawStringWithShadow("Unlocks villages", var41, var25 + var39 + 4 - (hasAchievementUnlocked(var30) ? 12 : 0), 12619872);
/*     */         }
/*     */         
/* 587 */         if (hasAchievementUnlocked(var30))
/*     */         {
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
/* 601 */           this.fontRenderer.drawStringWithShadow((view_mode == 1) ? getTakenByText(var30) : I18n.getString("achievement.taken"), var41, var25 + var39 + 4, -7302913);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 606 */         int var40 = Math.max(this.fontRenderer.getStringWidth(var33), 120);
/*     */         
/* 608 */         Achievement refer_to = var30;
/*     */         
/* 610 */         while (!haveAllParentAchievementsBeenUnlocked(refer_to, true)) {
/* 611 */           refer_to = refer_to.parentAchievement;
/*     */         }
/*     */         
/* 614 */         String var36 = I18n.getStringParams("gui.achievement.see", new Object[] { I18n.getString(refer_to.getName()) });
/* 615 */         int var28 = this.fontRenderer.splitStringWidth(var36, var40);
/* 616 */         drawGradientRect(var41 - 3, var25 - 3, var41 + var40 + 3, var25 + var28 + 12 + 3, -1073741824, -1073741824);
/* 617 */         this.fontRenderer.drawSplitString(var36, var41, var25 + 12, var40, -9416624);
/*     */       } 
/*     */ 
/*     */       
/* 621 */       this.fontRenderer.drawStringWithShadow(var33, var41, var25, (canUnlockAchievement(var30) || hasAchievementUnlocked(var30)) ? (var30.getSpecial() ? -128 : -1) : (var30.getSpecial() ? -8355776 : -8355712));
/*     */     } 
/*     */     
/* 624 */     GL11.glEnable(2929);
/* 625 */     GL11.glEnable(2896);
/* 626 */     RenderHelper.disableStandardItemLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawLines(int var4, int var5, int var8, int var9, Achievement child, Achievement parent) {
/* 632 */     for (int pass = 0; pass < 2; pass++) {
/*     */ 
/*     */ 
/*     */       
/* 636 */       Achievement var31 = child;
/*     */       
/* 638 */       if (parent != null) {
/*     */         
/* 640 */         int var22 = var31.displayColumn * 24 - var4 + 11 + var8;
/* 641 */         int var23 = var31.displayRow * 24 - var5 + 11 + var9;
/* 642 */         int var41 = parent.displayColumn * 24 - var4 + 11 + var8;
/* 643 */         int var25 = parent.displayRow * 24 - var5 + 11 + var9;
/*     */ 
/*     */         
/* 646 */         if (var31.isFlipped()) {
/*     */           
/* 648 */           var22 = parent.displayColumn * 24 - var4 + 11 + var8;
/* 649 */           var23 = parent.displayRow * 24 - var5 + 11 + var9;
/* 650 */           var41 = var31.displayColumn * 24 - var4 + 11 + var8;
/* 651 */           var25 = var31.displayRow * 24 - var5 + 11 + var9;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 656 */         boolean var26 = hasAchievementUnlocked(var31);
/* 657 */         boolean var27 = canUnlockAchievement(var31);
/*     */ 
/*     */         
/* 660 */         if (!hasAchievementUnlocked(var31.getSecondParent()) || !haveAllParentAchievementsBeenUnlocked(var31.getSecondParent(), true)) {
/*     */           
/* 662 */           if (!hasAchievementUnlocked(parent)) {
/* 663 */             var26 = false;
/*     */           }
/* 665 */           var27 = false;
/*     */         } 
/*     */         
/* 668 */         int var28 = (Math.sin((Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D) ? 255 : 130;
/* 669 */         int var29 = -16777216;
/*     */         
/* 671 */         if (var26) {
/*     */           
/* 673 */           if (pass == 0) {
/*     */             continue;
/*     */           }
/* 676 */           var29 = -9408400;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 682 */         else if (var27) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 687 */           var29 = (Math.sin((Minecraft.getSystemTime() % 600L) / 600.0D * Math.PI * 2.0D) > 0.6D) ? -16728064 : -16744448;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 693 */         if (pass != 1 || var26) {
/*     */ 
/*     */           
/* 696 */           drawHorizontalLine(var22, var41, var23, var29);
/* 697 */           drawVerticalLine(var41, var23, var25, var29);
/*     */         } 
/*     */       } 
/*     */       continue;
/*     */     } 
/*     */   }
/*     */   public String getTakenByText(Achievement achievement) {
/* 704 */     WorldAchievement wa = this.mc.theWorld.worldInfo.getWorldAchievement(achievement);
/*     */     
/* 706 */     return I18n.getStringParams("gui.achievement.takenBy", new Object[] { wa.username });
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
/*     */   public boolean doesGuiPauseGame() {
/* 721 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiAchievements.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */