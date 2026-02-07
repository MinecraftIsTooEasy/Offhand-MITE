/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.client.main.Main;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiMITEDS
/*    */   extends GuiChat
/*    */ {
/*    */   public void initGui() {
/* 17 */     super.initGui();
/*    */     
/* 19 */     GuiButton button_leave = new GuiButton(1, this.width / 2 - 100, this.height - 40, I18n.getString("menu.returnToMenu"));
/* 20 */     button_leave.yPosition = 80;
/*    */     
/* 22 */     this.buttonList.add(button_leave);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char par1, int par2) {
/* 30 */     if (par2 != 1)
/*    */     {
/*    */ 
/*    */       
/* 34 */       if (par2 != 28 && par2 != 156) {
/*    */         
/* 36 */         super.keyTyped(par1, par2);
/*    */       }
/*    */       else {
/*    */         
/* 40 */         String var3 = this.inputField.getText().trim();
/*    */         
/* 42 */         if (var3.length() > 0)
/*    */         {
/* 44 */           this.mc.thePlayer.sendChatMessage(var3);
/*    */         }
/*    */         
/* 47 */         this.inputField.setText("");
/* 48 */         this.mc.ingameGUI.getChatGUI().resetScroll();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 57 */     if (par1GuiButton.id == 1) {
/*    */       
/* 59 */       par1GuiButton.enabled = false;
/* 60 */       this.mc.statFileWriter.readStat(StatList.leaveGameStat, 1);
/* 61 */       this.mc.theWorld.sendQuittingDisconnectingPacket();
/* 62 */       this.mc.loadWorld((WorldClient)null);
/* 63 */       this.mc.displayGuiScreen(new GuiMainMenu());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int par1, int par2, float par3) {
/* 73 */     drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
/*    */     
/* 75 */     this.mc.ingameGUI.drawChatForMITEDS();
/*    */     
/* 77 */     if (Main.is_MITE_DS && this.mc.getIntegratedServer().getPublic()) {
/* 78 */       drawCenteredString(this.fontRenderer, "Dedicated Server Is Now Running", this.width / 2, 30, 16777215);
/*    */     } else {
/* 80 */       drawCenteredString(this.fontRenderer, "Problem Starting Dedicated Server", this.width / 2, 30, 16777215);
/*    */     } 
/* 82 */     drawCenteredString(this.fontRenderer, "Players Connected: " + (MinecraftServer.getServer().getCurrentPlayerCount() - 1), this.width / 2, 50, 16777215);
/*    */     
/* 84 */     super.drawScreen(par1, par2, par3);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiMITEDS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */