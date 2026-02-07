/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiDownloadTerrain
/*    */   extends GuiScreen
/*    */ {
/*    */   private NetClientHandler netHandler;
/*    */   private int updateCounter;
/*    */   
/*    */   public GuiDownloadTerrain(NetClientHandler netClientHandler) {
/* 12 */     this.netHandler = netClientHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {}
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 21 */     this.buttonList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 26 */     this.updateCounter++;
/* 27 */     if (this.updateCounter % 20 == 0) {
/* 28 */       this.netHandler.addToSendQueue(new Packet0KeepAlive());
/*    */     }
/* 30 */     if (this.netHandler != null) {
/* 31 */       this.netHandler.processReadPackets();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 37 */     drawBackground(0);
/*    */     
/* 39 */     drawCenteredString(this.fontRenderer, I18n.getString("multiplayer.downloadingTerrain"), this.width / 2, this.height / 2 - 50, 16777215);
/*    */     
/* 41 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiDownloadTerrain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */