/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.URI;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiMultiplayer
/*     */   extends GuiScreen
/*     */ {
/*     */   private static int threadsPending;
/*  23 */   private static Object lock = new Object();
/*     */ 
/*     */   
/*     */   private GuiScreen parentScreen;
/*     */ 
/*     */   
/*     */   private GuiSlotServer serverSlotContainer;
/*     */ 
/*     */   
/*     */   private ServerList internetServerList;
/*     */ 
/*     */   
/*  35 */   private int selectedServer = -1;
/*     */ 
/*     */   
/*     */   private GuiButton field_96289_p;
/*     */ 
/*     */   
/*     */   private GuiButton buttonSelect;
/*     */ 
/*     */   
/*     */   private GuiButton buttonDelete;
/*     */ 
/*     */   
/*     */   private boolean deleteClicked;
/*     */   
/*     */   private boolean addClicked;
/*     */   
/*     */   private boolean editClicked;
/*     */   
/*     */   private boolean directClicked;
/*     */   
/*     */   private String lagTooltip;
/*     */   
/*     */   private ServerData theServerData;
/*     */   
/*     */   private LanServerList localNetworkServerList;
/*     */   
/*     */   private ThreadLanServerFind localServerFindThread;
/*     */   
/*     */   private int ticksOpened;
/*     */   
/*     */   private boolean field_74024_A;
/*     */   
/*  67 */   private List listofLanServers = Collections.emptyList();
/*     */   
/*     */   private GuiButton button_info;
/*     */   
/*     */   private GuiButton button_website;
/*     */   
/*     */   private boolean info_showing;
/*     */   
/*     */   private static ExternalTexture server_image_texture;
/*     */   
/*     */   public GuiMultiplayer(GuiScreen par1GuiScreen) {
/*  78 */     this.parentScreen = par1GuiScreen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  86 */     Keyboard.enableRepeatEvents(true);
/*  87 */     this.buttonList.clear();
/*     */     
/*  89 */     if (!this.field_74024_A) {
/*     */       
/*  91 */       this.field_74024_A = true;
/*  92 */       this.internetServerList = new ServerList(this.mc);
/*  93 */       this.internetServerList.loadServerList();
/*  94 */       this.localNetworkServerList = new LanServerList();
/*     */ 
/*     */       
/*     */       try {
/*  98 */         this.localServerFindThread = new ThreadLanServerFind(this.localNetworkServerList);
/*  99 */         this.localServerFindThread.start();
/*     */       }
/* 101 */       catch (Exception var2) {
/*     */         
/* 103 */         this.mc.getLogAgent().logWarning("Unable to start LAN server detection: " + var2.getMessage());
/*     */       } 
/*     */       
/* 106 */       this.serverSlotContainer = new GuiSlotServer(this);
/*     */     }
/*     */     else {
/*     */       
/* 110 */       this.serverSlotContainer.func_77207_a(this.width, this.height, 32, this.height - 64);
/*     */     } 
/*     */     
/* 113 */     initGuiControls();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGuiControls() {
/* 123 */     this.buttonList.add(this.buttonSelect = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.getString("selectServer.select")));
/* 124 */     this.buttonList.add(new GuiButton(4, this.width / 2 - 50, this.height - 52, 100, 20, I18n.getString("selectServer.direct")));
/* 125 */     this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, I18n.getString("selectServer.add")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     this.buttonList.add(this.field_96289_p = new GuiButton(7, this.width / 2 - 154, this.height - 28, 74, 20, I18n.getString("selectServer.edit")));
/* 132 */     this.buttonList.add(this.buttonDelete = new GuiButton(2, this.width / 2 - 74 - 2, this.height - 28, 74, 20, I18n.getString("selectServer.delete")));
/* 133 */     this.buttonList.add(new GuiButton(8, this.width / 2 + 4 - 2, this.height - 28, 74, 20, I18n.getString("selectServer.refresh")));
/* 134 */     this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 74, 20, I18n.getString("gui.cancel")));
/*     */     
/* 136 */     this.buttonList.add(this.button_info = new GuiButton(9, this.width / 2 - 154, this.height - 28, 74, 20, I18n.getString("selectServer.info")));
/* 137 */     this.buttonList.add(this.button_website = new GuiButton(10, this.width / 2 - 74 - 2, this.height - 28, 74, 20, I18n.getString("selectServer.website")));
/*     */ 
/*     */ 
/*     */     
/* 141 */     boolean var1 = (this.selectedServer >= 0 && this.selectedServer < this.serverSlotContainer.getSize());
/* 142 */     this.buttonSelect.enabled = var1;
/*     */ 
/*     */ 
/*     */     
/* 146 */     updateButtonsForSelection();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isServerSelected() {
/* 152 */     return (this.selectedServer >= 0 && this.selectedServer < this.serverSlotContainer.getSize());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isInternetServerSelected(boolean must_be_preset) {
/* 157 */     return (isServerSelected() && this.selectedServer < this.internetServerList.countServers() && (!must_be_preset || (this.internetServerList.getServerData(this.selectedServer)).is_preset));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isLanServerSelected() {
/* 162 */     return (isServerSelected() && !isInternetServerSelected(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateButtonsForSelection() {
/* 167 */     this.field_96289_p.enabled = false;
/* 168 */     this.buttonDelete.enabled = false;
/* 169 */     this.field_96289_p.drawButton = true;
/* 170 */     this.buttonDelete.drawButton = true;
/*     */     
/* 172 */     this.button_info.enabled = false;
/* 173 */     this.button_website.enabled = false;
/* 174 */     this.button_info.drawButton = false;
/* 175 */     this.button_website.drawButton = false;
/*     */     
/* 177 */     if (!isInternetServerSelected(false)) {
/*     */       return;
/*     */     }
/* 180 */     ServerData server_data = this.internetServerList.getServerData(this.selectedServer);
/*     */     
/* 182 */     if (server_data.is_preset) {
/*     */       
/* 184 */       this.field_96289_p.drawButton = false;
/* 185 */       this.buttonDelete.drawButton = false;
/*     */       
/* 187 */       this.button_info.enabled = server_data.hasInfo();
/* 188 */       this.button_website.enabled = server_data.hasWebsite();
/*     */       
/* 190 */       this.button_info.drawButton = true;
/* 191 */       this.button_website.drawButton = true;
/*     */     }
/*     */     else {
/*     */       
/* 195 */       this.field_96289_p.enabled = true;
/* 196 */       this.buttonDelete.enabled = true;
/*     */       
/* 198 */       this.button_info.drawButton = false;
/* 199 */       this.button_website.drawButton = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 208 */     this.button_info.displayString = I18n.getString(this.info_showing ? "selectServer.list" : "selectServer.info");
/*     */     
/* 210 */     super.updateScreen();
/* 211 */     this.ticksOpened++;
/*     */     
/* 213 */     if (this.localNetworkServerList.getWasUpdated()) {
/*     */       
/* 215 */       this.listofLanServers = this.localNetworkServerList.getLanServers();
/* 216 */       this.localNetworkServerList.setWasNotUpdated();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 225 */     Keyboard.enableRepeatEvents(false);
/*     */     
/* 227 */     if (this.localServerFindThread != null) {
/*     */       
/* 229 */       this.localServerFindThread.interrupt();
/* 230 */       this.localServerFindThread = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 239 */     if (par1GuiButton.enabled) {
/*     */       
/* 241 */       if (!isServerSelected() || isInternetServerSelected(true))
/*     */       {
/* 243 */         if (par1GuiButton.id == 2 || par1GuiButton.id == 7) {
/*     */           return;
/*     */         }
/*     */       }
/* 247 */       if (par1GuiButton.id == 2) {
/*     */         
/* 249 */         String var2 = (this.internetServerList.getServerData(this.selectedServer)).serverName;
/*     */         
/* 251 */         if (var2 != null)
/*     */         {
/* 253 */           this.deleteClicked = true;
/* 254 */           String var3 = I18n.getString("selectServer.deleteQuestion");
/* 255 */           String var4 = "'" + var2 + "' " + I18n.getString("selectServer.deleteWarning");
/* 256 */           String var5 = I18n.getString("selectServer.deleteButton");
/* 257 */           String var6 = I18n.getString("gui.cancel");
/* 258 */           GuiYesNoMITE var7 = new GuiYesNoMITE(this, var3, var4, var5, var6, this.selectedServer);
/* 259 */           this.mc.displayGuiScreen(var7);
/*     */         }
/*     */       
/* 262 */       } else if (par1GuiButton.id == 1) {
/*     */         
/* 264 */         joinServer(this.selectedServer);
/*     */       }
/* 266 */       else if (par1GuiButton.id == 4) {
/*     */         
/* 268 */         this.directClicked = true;
/* 269 */         this.mc.displayGuiScreen(new GuiScreenServerList(this, this.theServerData = new ServerData(I18n.getString("selectServer.defaultName"), "")));
/*     */       }
/* 271 */       else if (par1GuiButton.id == 3) {
/*     */         
/* 273 */         this.addClicked = true;
/*     */         
/* 275 */         this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.theServerData = new ServerData(I18n.getString("selectServer.defaultName"), ""), "add"));
/*     */       }
/* 277 */       else if (par1GuiButton.id == 7) {
/*     */         
/* 279 */         this.editClicked = true;
/* 280 */         ServerData var8 = this.internetServerList.getServerData(this.selectedServer);
/* 281 */         this.theServerData = new ServerData(var8.serverName, var8.serverIP);
/* 282 */         this.theServerData.setHideAddress(var8.isHidingAddress());
/*     */         
/* 284 */         this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.theServerData, "edit"));
/*     */       }
/* 286 */       else if (par1GuiButton.id == 0) {
/*     */         
/* 288 */         this.mc.displayGuiScreen(this.parentScreen);
/*     */       }
/* 290 */       else if (par1GuiButton.id == 8) {
/*     */         
/* 292 */         this.mc.displayGuiScreen(new GuiMultiplayer(this.parentScreen));
/*     */       }
/* 294 */       else if (par1GuiButton.id == 9) {
/*     */         
/* 296 */         this.info_showing = !this.info_showing;
/*     */       }
/* 298 */       else if (par1GuiButton.id == 10) {
/*     */         
/*     */         try
/*     */         {
/* 302 */           Class<?> var3 = Class.forName("java.awt.Desktop");
/* 303 */           Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 304 */           var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI((this.internetServerList.getServerData(this.selectedServer)).website) });
/*     */         }
/* 306 */         catch (Throwable var5)
/*     */         {
/* 308 */           var5.printStackTrace();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 313 */         this.serverSlotContainer.actionPerformed(par1GuiButton);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean par1, int par2) {
/* 320 */     if (this.deleteClicked) {
/*     */       
/* 322 */       this.deleteClicked = false;
/*     */       
/* 324 */       if (par1) {
/*     */         
/* 326 */         this.internetServerList.removeServerData(par2);
/* 327 */         this.internetServerList.saveServerList();
/* 328 */         this.selectedServer = -1;
/*     */       } 
/*     */       
/* 331 */       this.mc.displayGuiScreen(this);
/*     */     }
/* 333 */     else if (this.directClicked) {
/*     */       
/* 335 */       this.directClicked = false;
/*     */       
/* 337 */       if (par1)
/*     */       {
/* 339 */         connectToServer(this.theServerData);
/*     */       }
/*     */       else
/*     */       {
/* 343 */         this.mc.displayGuiScreen(this);
/*     */       }
/*     */     
/* 346 */     } else if (this.addClicked) {
/*     */       
/* 348 */       this.addClicked = false;
/*     */       
/* 350 */       if (par1) {
/*     */         
/* 352 */         this.internetServerList.addServerData(this.theServerData);
/* 353 */         this.internetServerList.saveServerList();
/* 354 */         this.selectedServer = -1;
/*     */       } 
/*     */       
/* 357 */       this.mc.displayGuiScreen(this);
/*     */     }
/* 359 */     else if (this.editClicked) {
/*     */       
/* 361 */       this.editClicked = false;
/*     */       
/* 363 */       if (par1) {
/*     */         
/* 365 */         ServerData var3 = this.internetServerList.getServerData(this.selectedServer);
/* 366 */         var3.serverName = this.theServerData.serverName;
/* 367 */         var3.serverIP = this.theServerData.serverIP;
/* 368 */         var3.setHideAddress(this.theServerData.isHidingAddress());
/* 369 */         this.internetServerList.saveServerList();
/*     */       } 
/*     */       
/* 372 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 381 */     int var3 = this.selectedServer;
/*     */     
/* 383 */     if (par2 == 1 && this.selectedServer >= 0) {
/*     */       
/* 385 */       if (this.info_showing) {
/*     */         
/* 387 */         actionPerformed(this.button_info);
/*     */       }
/*     */       else {
/*     */         
/* 391 */         this.selectedServer = -1;
/*     */         
/* 393 */         this.buttonSelect.enabled = false;
/* 394 */         this.field_96289_p.enabled = false;
/* 395 */         this.buttonDelete.enabled = false;
/*     */ 
/*     */ 
/*     */         
/* 399 */         this.button_info.enabled = false;
/* 400 */         this.button_website.enabled = false;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 406 */     if (par2 == 59) {
/*     */       
/* 408 */       this.mc.gameSettings.hideServerAddress = !this.mc.gameSettings.hideServerAddress;
/* 409 */       this.mc.gameSettings.saveOptions();
/*     */ 
/*     */     
/*     */     }
/* 413 */     else if (isShiftKeyDown() && par2 == 200) {
/*     */       
/* 415 */       if (var3 > 0 && var3 < this.internetServerList.countServers())
/*     */       {
/* 417 */         this.internetServerList.swapServers(var3, var3 - 1);
/* 418 */         this.selectedServer--;
/*     */         
/* 420 */         if (var3 < this.internetServerList.countServers() - 1)
/*     */         {
/* 422 */           this.serverSlotContainer.func_77208_b(-this.serverSlotContainer.slotHeight);
/*     */         }
/*     */       }
/*     */     
/* 426 */     } else if (isShiftKeyDown() && par2 == 208) {
/*     */       
/* 428 */       if ((((var3 >= 0) ? 1 : 0) & ((var3 < this.internetServerList.countServers() - 1) ? 1 : 0)) != 0)
/*     */       {
/* 430 */         this.internetServerList.swapServers(var3, var3 + 1);
/* 431 */         this.selectedServer++;
/*     */         
/* 433 */         if (var3 > 0)
/*     */         {
/* 435 */           this.serverSlotContainer.func_77208_b(this.serverSlotContainer.slotHeight);
/*     */         }
/*     */       }
/*     */     
/* 439 */     } else if (par2 != 28 && par2 != 156) {
/*     */       
/* 441 */       super.keyTyped(par1, par2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 447 */     else if (isServerSelected()) {
/* 448 */       actionPerformed(this.buttonSelect);
/*     */     } else {
/* 450 */       actionPerformed(this.buttonList.get(2));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int calcTextHeight(int lines, int padding) {
/* 457 */     return this.fontRenderer.FONT_HEIGHT * lines + padding * (lines - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawServerInfoText(int margin_x, ServerData sd) {
/* 462 */     String start_date = (sd.start_date == null) ? "Not available" : sd.start_date;
/* 463 */     String description = (sd.description == null) ? "Not available" : sd.description;
/*     */     
/* 465 */     int center_x = this.width / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 474 */     int heading_color = sd.theme_color;
/*     */ 
/*     */     
/* 477 */     int text_color = 13684944;
/*     */     
/* 479 */     int y = 50;
/*     */     
/* 481 */     int lowest_line_y = this.height - 91;
/*     */     
/* 483 */     int extra_vertical_spacing = this.fontRenderer.FONT_HEIGHT / 2;
/*     */     
/* 485 */     drawDarkenedArea(margin_x, y - 5, Math.max(this.fontRenderer.getStringWidth("World Name: " + sd.serverName), this.fontRenderer.getStringWidth("Started On: " + start_date)) + 10, calcTextHeight(2, extra_vertical_spacing) + 10 - 1, sd.backdrop_opacity);
/*     */     
/* 487 */     String heading = "World Name: ";
/*     */     
/* 489 */     drawString(this.fontRenderer, heading, margin_x + 5, y, heading_color);
/* 490 */     drawString(this.fontRenderer, sd.serverName, margin_x + 5 + this.fontRenderer.getStringWidth(heading), y, text_color);
/* 491 */     y += this.fontRenderer.FONT_HEIGHT + extra_vertical_spacing;
/*     */     
/* 493 */     heading = "Started On: ";
/* 494 */     drawString(this.fontRenderer, heading, margin_x + 5 + 1, y, heading_color);
/* 495 */     drawString(this.fontRenderer, start_date, margin_x + 5 + 1 + this.fontRenderer.getStringWidth(heading), y, text_color);
/* 496 */     y += this.fontRenderer.FONT_HEIGHT + extra_vertical_spacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 506 */     String[] description_lines = (description == null) ? null : description.split(Pattern.quote("/"));
/*     */     
/* 508 */     int text_height = calcTextHeight(description_lines.length, 2);
/*     */     
/* 510 */     drawDarkenedArea(margin_x, lowest_line_y - text_height + this.fontRenderer.FONT_HEIGHT - 5, this.width - margin_x * 2, text_height + 10 - 1, sd.backdrop_opacity);
/*     */     
/* 512 */     y = lowest_line_y;
/*     */     
/* 514 */     heading = "Description: ";
/*     */     
/* 516 */     for (int i = description_lines.length - 1; i >= 0; i--) {
/*     */       
/* 518 */       if (i == 0) {
/* 519 */         drawString(this.fontRenderer, heading, margin_x + 5, y, heading_color);
/*     */       }
/* 521 */       drawString(this.fontRenderer, description_lines[i], (i == 0) ? (margin_x + 5 + this.fontRenderer.getStringWidth(heading)) : (margin_x + 5), y, text_color);
/* 522 */       y -= this.fontRenderer.FONT_HEIGHT + 2;
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
/*     */   private void drawDarkenedArea(int min_x, int min_y, int width, int height, float opacity) {
/* 550 */     GLHelper.disable(3553);
/* 551 */     GLHelper.enable(3042);
/*     */     
/* 553 */     int max_x = min_x + width;
/* 554 */     int max_y = min_y + height;
/*     */     
/* 556 */     Tessellator.instance.startDrawingQuads();
/* 557 */     Tessellator.instance.setColorRGBA_F(0.0F, 0.0F, 0.0F, opacity);
/* 558 */     Tessellator.instance.addVertex(min_x, max_y, 0.0D);
/* 559 */     Tessellator.instance.addVertex(max_x, max_y, 0.0D);
/* 560 */     Tessellator.instance.addVertex(max_x, min_y, 0.0D);
/* 561 */     Tessellator.instance.addVertex(min_x, min_y, 0.0D);
/* 562 */     Tessellator.instance.draw();
/*     */     
/* 564 */     GLHelper.restore(3042);
/*     */     
/* 566 */     GLHelper.restore(3553);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 574 */     Minecraft.clearWorldSessionClientData();
/*     */     
/* 576 */     this.lagTooltip = null;
/* 577 */     drawDefaultBackground();
/*     */ 
/*     */     
/* 580 */     if (this.info_showing) {
/*     */       
/* 582 */       this.serverSlotContainer.drawDarkenedBackground(1);
/*     */       
/* 584 */       ServerData sd = this.internetServerList.getServerData(this.selectedServer);
/*     */       
/* 586 */       int margin_x = this.width / 2 - 154;
/*     */       
/* 588 */       if (server_image_texture != null) {
/*     */         
/* 590 */         TextureUtil.bindTexture(server_image_texture.getGlTextureId());
/*     */         
/* 592 */         Tessellator var18 = Tessellator.instance;
/*     */         
/* 594 */         int left = 0;
/* 595 */         int bottom = 0;
/* 596 */         int right = this.width;
/* 597 */         int top = this.height;
/*     */         
/* 599 */         var18.startDrawingQuads();
/* 600 */         var18.setColorOpaque_F(0.7F, 0.7F, 0.7F);
/* 601 */         var18.addVertexWithUV(left, top, 0.0D, 0.0D, 1.0D);
/* 602 */         var18.addVertexWithUV(right, top, 0.0D, 1.0D, 1.0D);
/* 603 */         var18.addVertexWithUV(right, bottom, 0.0D, 1.0D, 0.0D);
/* 604 */         var18.addVertexWithUV(left, bottom, 0.0D, 0.0D, 0.0D);
/* 605 */         var18.draw();
/*     */       } 
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
/* 622 */       drawServerInfoText(margin_x, sd);
/*     */       
/* 624 */       this.serverSlotContainer.drawDarkenedBackground(2);
/*     */       
/* 626 */       GL11.glEnable(3553);
/* 627 */       GL11.glShadeModel(7424);
/* 628 */       GL11.glEnable(3008);
/* 629 */       GL11.glDisable(3042);
/*     */     }
/*     */     else {
/*     */       
/* 633 */       this.serverSlotContainer.drawScreen(par1, par2, par3);
/*     */     } 
/*     */     
/* 636 */     drawCenteredString(this.fontRenderer, I18n.getString("multiplayer.title"), this.width / 2, 20, 16777215);
/* 637 */     super.drawScreen(par1, par2, par3);
/*     */     
/* 639 */     if (this.lagTooltip != null)
/*     */     {
/* 641 */       func_74007_a(this.lagTooltip, par1, par2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void joinServer(int par1) {
/* 650 */     Minecraft.theMinecraft.increment_joinMultiplayerStat_asap = true;
/*     */     
/* 652 */     if (par1 < this.internetServerList.countServers()) {
/*     */       
/* 654 */       connectToServer(this.internetServerList.getServerData(par1));
/*     */     }
/*     */     else {
/*     */       
/* 658 */       par1 -= this.internetServerList.countServers();
/*     */       
/* 660 */       if (par1 < this.listofLanServers.size()) {
/*     */         
/* 662 */         LanServer var2 = this.listofLanServers.get(par1);
/* 663 */         connectToServer(new ServerData(var2.getServerMotd(), var2.getServerIpPort()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void connectToServer(ServerData par1ServerData) {
/* 670 */     this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, par1ServerData));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void func_74017_b(ServerData par0ServerData) throws IOException {
/* 675 */     ServerAddress var1 = ServerAddress.func_78860_a(par0ServerData.serverIP);
/* 676 */     Socket var2 = null;
/* 677 */     DataInputStream var3 = null;
/* 678 */     DataOutputStream var4 = null;
/*     */ 
/*     */     
/*     */     try {
/* 682 */       var2 = new Socket();
/* 683 */       var2.setSoTimeout(3000);
/* 684 */       var2.setTcpNoDelay(true);
/* 685 */       var2.setTrafficClass(18);
/* 686 */       var2.connect(new InetSocketAddress(var1.getIP(), var1.getPort()), 3000);
/* 687 */       var3 = new DataInputStream(var2.getInputStream());
/* 688 */       var4 = new DataOutputStream(var2.getOutputStream());
/* 689 */       Packet254ServerPing var5 = new Packet254ServerPing(78, var1.getIP(), var1.getPort());
/* 690 */       var4.writeByte(var5.getPacketId());
/* 691 */       var5.writePacketData(var4);
/*     */       
/* 693 */       if (var3.read() != 255)
/*     */       {
/* 695 */         throw new IOException("Bad message");
/*     */       }
/*     */       
/* 698 */       String var6 = Packet.readString(var3, 256);
/* 699 */       char[] var7 = var6.toCharArray();
/*     */       
/* 701 */       for (int var8 = 0; var8 < var7.length; var8++) {
/*     */         
/* 703 */         if (var7[var8] != '§' && var7[var8] != '\000' && ChatAllowedCharacters.allowedCharacters.indexOf(var7[var8]) < 0)
/*     */         {
/* 705 */           var7[var8] = '?';
/*     */         }
/*     */       } 
/*     */       
/* 709 */       var6 = new String(var7);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 714 */       if (var6.startsWith("§") && var6.length() > 1) {
/*     */         
/* 716 */         String[] var27 = var6.substring(1).split("\000");
/*     */         
/* 718 */         if (MathHelper.parseIntWithDefault(var27[0], 0) == 1) {
/*     */           
/* 720 */           par0ServerData.serverMOTD = var27[3];
/* 721 */           par0ServerData.field_82821_f = MathHelper.parseIntWithDefault(var27[1], par0ServerData.field_82821_f);
/* 722 */           par0ServerData.gameVersion = var27[2];
/* 723 */           int var9 = MathHelper.parseIntWithDefault(var27[4], 0);
/* 724 */           int var10 = MathHelper.parseIntWithDefault(var27[5], 0);
/*     */           
/* 726 */           if (var9 >= 0 && var10 >= 0)
/*     */           {
/* 728 */             par0ServerData.populationInfo = EnumChatFormatting.GRAY + "" + var9 + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + var10;
/*     */           }
/*     */           else
/*     */           {
/* 732 */             par0ServerData.populationInfo = "" + EnumChatFormatting.DARK_GRAY + "???";
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 737 */           par0ServerData.gameVersion = "???";
/* 738 */           par0ServerData.serverMOTD = "" + EnumChatFormatting.DARK_GRAY + "???";
/* 739 */           par0ServerData.field_82821_f = 79;
/* 740 */           par0ServerData.populationInfo = "" + EnumChatFormatting.DARK_GRAY + "???";
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 745 */         String[] var27 = var6.split("§");
/* 746 */         var6 = var27[0];
/* 747 */         int var9 = -1;
/* 748 */         int var10 = -1;
/*     */ 
/*     */         
/*     */         try {
/* 752 */           var9 = Integer.parseInt(var27[1]);
/* 753 */           var10 = Integer.parseInt(var27[2]);
/*     */         }
/* 755 */         catch (Exception var25) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 760 */         par0ServerData.serverMOTD = EnumChatFormatting.GRAY + var6;
/*     */         
/* 762 */         if (var9 >= 0 && var10 > 0) {
/*     */           
/* 764 */           par0ServerData.populationInfo = EnumChatFormatting.GRAY + "" + var9 + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + var10;
/*     */         }
/*     */         else {
/*     */           
/* 768 */           par0ServerData.populationInfo = "" + EnumChatFormatting.DARK_GRAY + "???";
/*     */         } 
/*     */         
/* 771 */         par0ServerData.gameVersion = "1.3";
/* 772 */         par0ServerData.field_82821_f = 77;
/*     */       } 
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 779 */         if (var3 != null)
/*     */         {
/* 781 */           var3.close();
/*     */         }
/*     */       }
/* 784 */       catch (Throwable var24) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 791 */         if (var4 != null)
/*     */         {
/* 793 */           var4.close();
/*     */         }
/*     */       }
/* 796 */       catch (Throwable var23) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 803 */         if (var2 != null)
/*     */         {
/* 805 */           var2.close();
/*     */         }
/*     */       }
/* 808 */       catch (Throwable var22) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_74007_a(String par1Str, int par2, int par3) {
/* 817 */     if (par1Str != null) {
/*     */       
/* 819 */       int var4 = par2 + 12;
/* 820 */       int var5 = par3 - 12;
/* 821 */       int var6 = this.fontRenderer.getStringWidth(par1Str);
/* 822 */       drawGradientRect(var4 - 3, var5 - 3, var4 + var6 + 3, var5 + 8 + 3, -1073741824, -1073741824);
/* 823 */       this.fontRenderer.drawStringWithShadow(par1Str, var4, var5, -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static ServerList getInternetServerList(GuiMultiplayer par0GuiMultiplayer) {
/* 829 */     return par0GuiMultiplayer.internetServerList;
/*     */   }
/*     */ 
/*     */   
/*     */   static List getListOfLanServers(GuiMultiplayer par0GuiMultiplayer) {
/* 834 */     return par0GuiMultiplayer.listofLanServers;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getSelectedServer(GuiMultiplayer par0GuiMultiplayer) {
/* 839 */     return par0GuiMultiplayer.selectedServer;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getAndSetSelectedServer(GuiMultiplayer par0GuiMultiplayer, int par1) {
/* 844 */     return par0GuiMultiplayer.selectedServer = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GuiButton getButtonSelect(GuiMultiplayer par0GuiMultiplayer) {
/* 852 */     return par0GuiMultiplayer.buttonSelect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GuiButton getButtonEdit(GuiMultiplayer par0GuiMultiplayer) {
/* 860 */     return par0GuiMultiplayer.field_96289_p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GuiButton getButtonDelete(GuiMultiplayer par0GuiMultiplayer) {
/* 868 */     return par0GuiMultiplayer.buttonDelete;
/*     */   }
/*     */ 
/*     */   
/*     */   static void func_74008_b(GuiMultiplayer par0GuiMultiplayer, int par1) {
/* 873 */     par0GuiMultiplayer.joinServer(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   static int getTicksOpened(GuiMultiplayer par0GuiMultiplayer) {
/* 878 */     return par0GuiMultiplayer.ticksOpened;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object getLock() {
/* 886 */     return lock;
/*     */   }
/*     */ 
/*     */   
/*     */   static int getThreadsPending() {
/* 891 */     return threadsPending;
/*     */   }
/*     */ 
/*     */   
/*     */   static int increaseThreadsPending() {
/* 896 */     return threadsPending++;
/*     */   }
/*     */ 
/*     */   
/*     */   static void func_82291_a(ServerData par0ServerData) throws IOException {
/* 901 */     func_74017_b(par0ServerData);
/*     */   }
/*     */ 
/*     */   
/*     */   static int decreaseThreadsPending() {
/* 906 */     return threadsPending--;
/*     */   }
/*     */ 
/*     */   
/*     */   static String getAndSetLagTooltip(GuiMultiplayer par0GuiMultiplayer, String par1Str) {
/* 911 */     return par0GuiMultiplayer.lagTooltip = par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadServerImage(String filename) {
/* 917 */     if (filename == null) {
/*     */       
/* 919 */       server_image_texture = null;
/*     */       
/*     */       return;
/*     */     } 
/* 923 */     server_image_texture = new ExternalTexture(new File("MITE/public_servers/" + filename));
/*     */ 
/*     */     
/*     */     try {
/* 927 */       server_image_texture.loadTexture(null);
/*     */     }
/* 929 */     catch (Exception e) {
/*     */       
/* 931 */       server_image_texture = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiMultiplayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */