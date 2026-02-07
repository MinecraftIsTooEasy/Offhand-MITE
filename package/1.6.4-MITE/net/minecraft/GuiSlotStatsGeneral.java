/*    */ package net.minecraft;
/*    */ 
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ class GuiSlotStatsGeneral
/*    */   extends GuiSlot
/*    */ {
/*    */   final GuiStats statsGui;
/*    */   
/*    */   public GuiSlotStatsGeneral(GuiStats par1GuiStats) {
/* 11 */     super(GuiStats.getMinecraft(par1GuiStats), par1GuiStats.width, par1GuiStats.height, 32, par1GuiStats.height - 64, 10);
/* 12 */     this.statsGui = par1GuiStats;
/* 13 */     setShowSelectionBox(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getSize() {
/* 21 */     return StatList.generalStats.size();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void elementClicked(int par1, boolean par2) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isSelected(int par1) {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getContentHeight() {
/* 42 */     return getSize() * 10;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawBackground() {
/* 47 */     this.statsGui.drawDefaultBackground();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/* 52 */     StatBase var6 = StatList.generalStats.get(par1);
/* 53 */     this.statsGui.drawString(GuiStats.getFontRenderer1(this.statsGui), I18n.getString(var6.getName()), par2 + 2, par3 + 1, (par1 % 2 == 0) ? 16777215 : 9474192);
/* 54 */     String var7 = var6.func_75968_a(GuiStats.getStatsFileWriter(this.statsGui).writeStat(var6));
/*    */     
/* 56 */     if (GuiStats.this_world_only) {
/*    */       
/* 58 */       var7 = var6.func_75968_a(PlayerStatsHelper.getValueOnClient(var6));
/*    */       
/* 60 */       if (var6 == StatList.createWorldStat) {
/*    */         
/* 62 */         var7 = (MinecraftServer.getServer() == null) ? "0" : "1";
/* 63 */       } else if (var6 == StatList.leaveGameStat) {
/* 64 */         var7 = "" + (PlayerStatsHelper.getValueOnClient(StatList.startGameStat) - 1L);
/*    */       } 
/*    */     } 
/* 67 */     this.statsGui.drawString(GuiStats.getFontRenderer2(this.statsGui), var7, par2 + 2 + 213 - GuiStats.getFontRenderer3(this.statsGui).getStringWidth(var7), par3 + 1, (par1 % 2 == 0) ? 16777215 : 9474192);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlotStatsGeneral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */