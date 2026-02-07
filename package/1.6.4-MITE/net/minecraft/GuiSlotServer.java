/*     */ package net.minecraft;
/*     */ 
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GuiSlotServer
/*     */   extends GuiSlot
/*     */ {
/*     */   final GuiMultiplayer parentGui;
/*     */   
/*     */   public GuiSlotServer(GuiMultiplayer par1GuiMultiplayer) {
/*  16 */     super(par1GuiMultiplayer.mc, par1GuiMultiplayer.width, par1GuiMultiplayer.height, 32, par1GuiMultiplayer.height - 64, 36);
/*  17 */     this.parentGui = par1GuiMultiplayer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  25 */     return GuiMultiplayer.getInternetServerList(this.parentGui).countServers() + GuiMultiplayer.getListOfLanServers(this.parentGui).size() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int par1, boolean par2) {
/*  33 */     if (par1 < GuiMultiplayer.getInternetServerList(this.parentGui).countServers() + GuiMultiplayer.getListOfLanServers(this.parentGui).size()) {
/*     */       
/*  35 */       int var3 = GuiMultiplayer.getSelectedServer(this.parentGui);
/*  36 */       GuiMultiplayer.getAndSetSelectedServer(this.parentGui, par1);
/*  37 */       ServerData var4 = (GuiMultiplayer.getInternetServerList(this.parentGui).countServers() > par1) ? GuiMultiplayer.getInternetServerList(this.parentGui).getServerData(par1) : null;
/*  38 */       boolean var5 = (GuiMultiplayer.getSelectedServer(this.parentGui) >= 0 && GuiMultiplayer.getSelectedServer(this.parentGui) < getSize() && (var4 == null || var4.field_82821_f == 78));
/*  39 */       boolean var6 = (GuiMultiplayer.getSelectedServer(this.parentGui) < GuiMultiplayer.getInternetServerList(this.parentGui).countServers());
/*  40 */       (GuiMultiplayer.getButtonSelect(this.parentGui)).enabled = var5;
/*  41 */       (GuiMultiplayer.getButtonEdit(this.parentGui)).enabled = var6;
/*  42 */       (GuiMultiplayer.getButtonDelete(this.parentGui)).enabled = var6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  52 */       if (this.parentGui instanceof GuiMultiplayer) {
/*     */         
/*  54 */         this.parentGui.updateButtonsForSelection();
/*  55 */         GuiMultiplayer.loadServerImage((var4 == null || var4.image_url == null) ? null : FilenameUtils.getName(var4.image_url));
/*     */       } 
/*     */       
/*  58 */       if (par2 && var5) {
/*     */         
/*  60 */         GuiMultiplayer.func_74008_b(this.parentGui, par1);
/*     */       }
/*  62 */       else if (var6 && GuiScreen.isShiftKeyDown() && var3 >= 0 && var3 < GuiMultiplayer.getInternetServerList(this.parentGui).countServers()) {
/*     */         
/*  64 */         GuiMultiplayer.getInternetServerList(this.parentGui).swapServers(var3, GuiMultiplayer.getSelectedServer(this.parentGui));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int par1) {
/*  74 */     return (par1 == GuiMultiplayer.getSelectedServer(this.parentGui));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  82 */     return getSize() * 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/*  87 */     this.parentGui.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/*  92 */     if (par1 < GuiMultiplayer.getInternetServerList(this.parentGui).countServers()) {
/*     */       
/*  94 */       func_77247_d(par1, par2, par3, par4, par5Tessellator);
/*     */     }
/*  96 */     else if (par1 < GuiMultiplayer.getInternetServerList(this.parentGui).countServers() + GuiMultiplayer.getListOfLanServers(this.parentGui).size()) {
/*     */       
/*  98 */       func_77248_b(par1, par2, par3, par4, par5Tessellator);
/*     */     }
/*     */     else {
/*     */       
/* 102 */       func_77249_c(par1, par2, par3, par4, par5Tessellator);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_77248_b(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/* 108 */     LanServer var6 = GuiMultiplayer.getListOfLanServers(this.parentGui).get(par1 - GuiMultiplayer.getInternetServerList(this.parentGui).countServers());
/* 109 */     this.parentGui.drawString(this.parentGui.fontRenderer, I18n.getString("lanServer.title"), par2 + 2, par3 + 1, 16777215);
/* 110 */     this.parentGui.drawString(this.parentGui.fontRenderer, var6.getServerMotd(), par2 + 2, par3 + 12, 8421504);
/*     */     
/* 112 */     if (this.parentGui.mc.gameSettings.hideServerAddress) {
/*     */       
/* 114 */       this.parentGui.drawString(this.parentGui.fontRenderer, I18n.getString("selectServer.hiddenAddress"), par2 + 2, par3 + 12 + 11, 3158064);
/*     */     }
/*     */     else {
/*     */       
/* 118 */       this.parentGui.drawString(this.parentGui.fontRenderer, var6.getServerIpPort(), par2 + 2, par3 + 12 + 11, 3158064);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_77249_c(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/*     */     String var6;
/* 124 */     this.parentGui.drawCenteredString(this.parentGui.fontRenderer, I18n.getString("lanServer.scanning"), this.parentGui.width / 2, par3 + 1, 16777215);
/*     */ 
/*     */     
/* 127 */     switch (GuiMultiplayer.getTicksOpened(this.parentGui) / 3 % 4) {
/*     */ 
/*     */       
/*     */       default:
/* 131 */         var6 = "O o o";
/*     */         break;
/*     */       
/*     */       case 1:
/*     */       case 3:
/* 136 */         var6 = "o O o";
/*     */         break;
/*     */       
/*     */       case 2:
/* 140 */         var6 = "o o O";
/*     */         break;
/*     */     } 
/* 143 */     this.parentGui.drawCenteredString(this.parentGui.fontRenderer, var6, this.parentGui.width / 2, par3 + 12, 8421504);
/*     */   }
/*     */   
/*     */   private void func_77247_d(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/*     */     int var15;
/* 148 */     ServerData var6 = GuiMultiplayer.getInternetServerList(this.parentGui).getServerData(par1);
/*     */     
/* 150 */     synchronized (GuiMultiplayer.getLock()) {
/*     */       
/* 152 */       if (GuiMultiplayer.getThreadsPending() < 5 && !var6.field_78841_f) {
/*     */         
/* 154 */         var6.field_78841_f = true;
/* 155 */         var6.pingToServer = -2L;
/* 156 */         var6.serverMOTD = "";
/* 157 */         var6.populationInfo = "";
/* 158 */         GuiMultiplayer.increaseThreadsPending();
/* 159 */         (new ThreadPollServers(this, var6)).start();
/*     */       } 
/*     */     } 
/*     */     
/* 163 */     boolean var7 = (var6.field_82821_f > 78);
/* 164 */     boolean var8 = (var6.field_82821_f < 78);
/* 165 */     boolean var9 = (var7 || var8);
/* 166 */     this.parentGui.drawString(this.parentGui.fontRenderer, var6.serverName, par2 + 2, par3 + 1, 16777215);
/* 167 */     this.parentGui.drawString(this.parentGui.fontRenderer, var6.serverMOTD, par2 + 2, par3 + 12, 8421504);
/* 168 */     this.parentGui.drawString(this.parentGui.fontRenderer, var6.populationInfo, par2 + 215 - this.parentGui.fontRenderer.getStringWidth(var6.populationInfo), par3 + 12, 8421504);
/*     */     
/* 170 */     if (var9) {
/*     */       
/* 172 */       String var10 = EnumChatFormatting.DARK_RED + var6.gameVersion;
/* 173 */       this.parentGui.drawString(this.parentGui.fontRenderer, var10, par2 + 200 - this.parentGui.fontRenderer.getStringWidth(var10), par3 + 1, 8421504);
/*     */     } 
/*     */     
/* 176 */     if (!this.parentGui.mc.gameSettings.hideServerAddress && !var6.isHidingAddress()) {
/*     */       
/* 178 */       this.parentGui.drawString(this.parentGui.fontRenderer, var6.serverIP, par2 + 2, par3 + 12 + 11, 3158064);
/*     */     }
/*     */     else {
/*     */       
/* 182 */       this.parentGui.drawString(this.parentGui.fontRenderer, I18n.getString("selectServer.hiddenAddress"), par2 + 2, par3 + 12 + 11, 3158064);
/*     */     } 
/*     */     
/* 185 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 186 */     this.parentGui.mc.getTextureManager().bindTexture(Gui.icons);
/* 187 */     byte var16 = 0;
/* 188 */     boolean var11 = false;
/* 189 */     String var12 = "";
/*     */ 
/*     */     
/* 192 */     if (var9) {
/*     */       
/* 194 */       var12 = var7 ? "Client out of date!" : "Server out of date!";
/* 195 */       var15 = 5;
/*     */     }
/* 197 */     else if (var6.field_78841_f && var6.pingToServer != -2L) {
/*     */       
/* 199 */       if (var6.pingToServer < 0L) {
/*     */         
/* 201 */         var15 = 5;
/*     */       }
/* 203 */       else if (var6.pingToServer < 150L) {
/*     */         
/* 205 */         var15 = 0;
/*     */       }
/* 207 */       else if (var6.pingToServer < 300L) {
/*     */         
/* 209 */         var15 = 1;
/*     */       }
/* 211 */       else if (var6.pingToServer < 600L) {
/*     */         
/* 213 */         var15 = 2;
/*     */       }
/* 215 */       else if (var6.pingToServer < 1000L) {
/*     */         
/* 217 */         var15 = 3;
/*     */       }
/*     */       else {
/*     */         
/* 221 */         var15 = 4;
/*     */       } 
/*     */       
/* 224 */       if (var6.pingToServer < 0L)
/*     */       {
/* 226 */         var12 = "(no connection)";
/*     */       }
/*     */       else
/*     */       {
/* 230 */         var12 = var6.pingToServer + "ms";
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 235 */       var16 = 1;
/* 236 */       var15 = (int)(Minecraft.getSystemTime() / 100L + (par1 * 2) & 0x7L);
/*     */       
/* 238 */       if (var15 > 4)
/*     */       {
/* 240 */         var15 = 8 - var15;
/*     */       }
/*     */       
/* 243 */       var12 = "Polling..";
/*     */     } 
/*     */     
/* 246 */     this.parentGui.drawTexturedModalRect(par2 + 205, par3, 0 + var16 * 10, 176 + var15 * 8, 10, 8);
/* 247 */     byte var13 = 4;
/*     */     
/* 249 */     if (this.mouseX >= par2 + 205 - var13 && this.mouseY >= par3 - var13 && this.mouseX <= par2 + 205 + 10 + var13 && this.mouseY <= par3 + 8 + var13)
/*     */     {
/* 251 */       GuiMultiplayer.getAndSetLagTooltip(this.parentGui, var12);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlotServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */