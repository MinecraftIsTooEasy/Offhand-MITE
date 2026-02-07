/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiGameOver
/*     */   extends GuiScreen
/*     */ {
/*     */   private int cooldownTimer;
/*     */   private GuiButton respawn_button;
/*  14 */   private int respawn_countdown = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  21 */     this.buttonList.clear();
/*     */     
/*  23 */     if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
/*     */       
/*  25 */       if (this.mc.isIntegratedServerRunning())
/*     */       {
/*  27 */         this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.getString("deathScreen.deleteWorld")));
/*     */       }
/*     */       else
/*     */       {
/*  31 */         this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.getString("deathScreen.leaveServer")));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  36 */       this.respawn_button = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 72, I18n.getString("deathScreen.respawn"));
/*     */ 
/*     */       
/*  39 */       this.buttonList.add(this.respawn_button);
/*  40 */       this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 96, I18n.getString("deathScreen.titleScreen")));
/*     */       
/*  42 */       if (this.mc.getSession() == null)
/*     */       {
/*  44 */         ((GuiButton)this.buttonList.get(1)).enabled = false;
/*     */       }
/*     */     } 
/*     */     
/*  48 */     if (this.cooldownTimer >= 20) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  53 */     for (Iterator<GuiButton> var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = false)
/*     */     {
/*  55 */       GuiButton var2 = var1.next();
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
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  69 */     switch (par1GuiButton.id) {
/*     */       
/*     */       case 1:
/*  72 */         this.mc.thePlayer.respawnPlayer();
/*  73 */         this.mc.displayGuiScreen((GuiScreen)null);
/*     */         break;
/*     */       
/*     */       case 2:
/*  77 */         this.mc.theWorld.sendQuittingDisconnectingPacket();
/*  78 */         this.mc.loadWorld((WorldClient)null);
/*  79 */         this.mc.displayGuiScreen(new GuiMainMenu());
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  88 */     drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
/*  89 */     GL11.glPushMatrix();
/*  90 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  91 */     boolean var4 = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
/*  92 */     String var5 = var4 ? I18n.getString("deathScreen.title.hardcore") : I18n.getString("deathScreen.title");
/*  93 */     drawCenteredString(this.fontRenderer, var5, this.width / 2 / 2, 30, 16777215);
/*  94 */     GL11.glPopMatrix();
/*     */     
/*  96 */     if (var4) {
/*     */       
/*  98 */       drawCenteredString(this.fontRenderer, I18n.getString("deathScreen.hardcoreInfo"), this.width / 2, 144, 16777215);
/*     */     }
/* 100 */     else if (this.respawn_countdown > 0) {
/*     */       
/* 102 */       drawCenteredString(this.fontRenderer, I18n.getString("deathScreen.respawnCountdown") + " " + EnumChatFormatting.YELLOW + this.respawn_countdown, this.width / 2, 100, 16777215);
/*     */     
/*     */     }
/* 105 */     else if (this.respawn_countdown == 0) {
/*     */       
/* 107 */       actionPerformed(this.respawn_button);
/*     */     } 
/*     */ 
/*     */     
/* 111 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 127 */     super.updateScreen();
/*     */ 
/*     */     
/* 130 */     if (this.cooldownTimer < 20) {
/* 131 */       this.cooldownTimer++;
/*     */     }
/*     */ 
/*     */     
/* 135 */     if (this.cooldownTimer == 20)
/*     */     {
/* 137 */       for (Iterator<GuiButton> var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = true)
/*     */       {
/* 139 */         GuiButton var2 = var1.next();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRespawnCountdown(int respawn_countdown) {
/* 146 */     this.respawn_countdown = respawn_countdown;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiGameOver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */