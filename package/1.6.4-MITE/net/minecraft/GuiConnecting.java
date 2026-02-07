/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiConnecting
/*     */   extends GuiScreen
/*     */ {
/*     */   private NetClientHandler clientHandler;
/*     */   private boolean cancelled;
/*     */   private final GuiScreen field_98098_c;
/*     */   
/*     */   public GuiConnecting(GuiScreen par1GuiScreen, Minecraft par2Minecraft, ServerData par3ServerData) {
/*  14 */     this.mc = par2Minecraft;
/*  15 */     this.field_98098_c = par1GuiScreen;
/*  16 */     ServerAddress var4 = ServerAddress.func_78860_a(par3ServerData.serverIP);
/*  17 */     par2Minecraft.loadWorld((WorldClient)null);
/*  18 */     par2Minecraft.setServerData(par3ServerData);
/*  19 */     spawnNewServerThread(var4.getIP(), var4.getPort());
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiConnecting(GuiScreen par1GuiScreen, Minecraft par2Minecraft, String par3Str, int par4) {
/*  24 */     this.mc = par2Minecraft;
/*  25 */     this.field_98098_c = par1GuiScreen;
/*  26 */     par2Minecraft.loadWorld((WorldClient)null);
/*  27 */     spawnNewServerThread(par3Str, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnNewServerThread(String par1Str, int par2) {
/*  32 */     this.mc.getLogAgent().logInfo("Connecting to " + par1Str + ", " + par2);
/*  33 */     (new ThreadConnectToServer(this, par1Str, par2)).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  41 */     if (this.clientHandler != null)
/*     */     {
/*  43 */       this.clientHandler.processReadPackets();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  57 */     this.buttonList.clear();
/*  58 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  66 */     if (par1GuiButton.id == 0) {
/*     */       
/*  68 */       Minecraft.theMinecraft.increment_joinMultiplayerStat_asap = false;
/*     */       
/*  70 */       this.cancelled = true;
/*     */       
/*  72 */       if (this.clientHandler != null)
/*     */       {
/*  74 */         this.clientHandler.disconnect();
/*     */       }
/*     */       
/*  77 */       this.mc.displayGuiScreen(this.field_98098_c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  86 */     drawDefaultBackground();
/*     */     
/*  88 */     if (this.clientHandler == null) {
/*     */       
/*  90 */       drawCenteredString(this.fontRenderer, I18n.getString("connect.connecting"), this.width / 2, this.height / 2 - 50, 16777215);
/*  91 */       drawCenteredString(this.fontRenderer, "", this.width / 2, this.height / 2 - 10, 16777215);
/*     */     }
/*     */     else {
/*     */       
/*  95 */       drawCenteredString(this.fontRenderer, I18n.getString("connect.authorizing"), this.width / 2, this.height / 2 - 50, 16777215);
/*  96 */       drawCenteredString(this.fontRenderer, this.clientHandler.field_72560_a, this.width / 2, this.height / 2 - 10, 16777215);
/*     */     } 
/*     */     
/*  99 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static NetClientHandler setNetClientHandler(GuiConnecting par0GuiConnecting, NetClientHandler par1NetClientHandler) {
/* 107 */     return par0GuiConnecting.clientHandler = par1NetClientHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft func_74256_a(GuiConnecting par0GuiConnecting) {
/* 112 */     return par0GuiConnecting.mc;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean isCancelled(GuiConnecting par0GuiConnecting) {
/* 117 */     return par0GuiConnecting.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft func_74254_c(GuiConnecting par0GuiConnecting) {
/* 122 */     return par0GuiConnecting.mc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static NetClientHandler getNetClientHandler(GuiConnecting par0GuiConnecting) {
/* 130 */     return par0GuiConnecting.clientHandler;
/*     */   }
/*     */ 
/*     */   
/*     */   static GuiScreen func_98097_e(GuiConnecting par0GuiConnecting) {
/* 135 */     return par0GuiConnecting.field_98098_c;
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft func_74250_f(GuiConnecting par0GuiConnecting) {
/* 140 */     return par0GuiConnecting.mc;
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft func_74251_g(GuiConnecting par0GuiConnecting) {
/* 145 */     return par0GuiConnecting.mc;
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft func_98096_h(GuiConnecting par0GuiConnecting) {
/* 150 */     return par0GuiConnecting.mc;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiConnecting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */