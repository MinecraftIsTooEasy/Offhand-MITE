/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GuiSlotOnlineServerList
/*     */   extends GuiScreenSelectLocation
/*     */ {
/*     */   public GuiSlotOnlineServerList(GuiScreenOnlineServers guiScreenOnlineServers) {
/* 349 */     super(GuiScreenOnlineServers.func_140037_f(guiScreenOnlineServers), guiScreenOnlineServers.width, guiScreenOnlineServers.height, 32, guiScreenOnlineServers.height - 64, 36);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/* 354 */     return GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {
/* 360 */     if (i >= GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size()) {
/*     */       return;
/*     */     }
/*     */     
/* 364 */     McoServer mcoServer = GuiScreenOnlineServers.func_140013_c(this.field_96294_a).get(i);
/* 365 */     GuiScreenOnlineServers.func_140036_b(this.field_96294_a, mcoServer.field_96408_a);
/*     */     
/* 367 */     if (!GuiScreenOnlineServers.func_140015_g(this.field_96294_a).getSession().getUsername().equals(mcoServer.field_96405_e)) {
/* 368 */       (GuiScreenOnlineServers.func_140038_h(this.field_96294_a)).displayString = I18n.getString("mco.selectServer.leave");
/*     */     } else {
/* 370 */       (GuiScreenOnlineServers.func_140038_h(this.field_96294_a)).displayString = I18n.getString("mco.selectServer.configure");
/*     */     } 
/* 372 */     (GuiScreenOnlineServers.func_140033_i(this.field_96294_a)).enabled = (mcoServer.field_96404_d.equals("OPEN") && !mcoServer.field_98166_h);
/*     */     
/* 374 */     if (bl && (GuiScreenOnlineServers.func_140033_i(this.field_96294_a)).enabled) GuiScreenOnlineServers.func_140008_c(this.field_96294_a, GuiScreenOnlineServers.func_140041_a(this.field_96294_a));
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 380 */     return (i == GuiScreenOnlineServers.func_140027_d(this.field_96294_a, GuiScreenOnlineServers.func_140041_a(this.field_96294_a)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_104086_b(int i) {
/*     */     try {
/* 386 */       return (i >= 0 && i < GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size() && ((McoServer)GuiScreenOnlineServers.func_140013_c(this.field_96294_a).get(i)).field_96405_e.toLowerCase().equals(GuiScreenOnlineServers.func_104032_j(this.field_96294_a).getSession().getUsername()));
/* 387 */     } catch (Exception exception) {
/* 388 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_130003_b() {
/* 394 */     return getSize() * 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130004_c() {
/* 399 */     this.field_96294_a.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/* 404 */     if (i < GuiScreenOnlineServers.func_140013_c(this.field_96294_a).size()) {
/* 405 */       func_96292_b(i, j, k, l, tessellator);
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_96292_b(int i, int j, int k, int l, Tessellator tessellator) {
/* 410 */     McoServer mcoServer = GuiScreenOnlineServers.func_140013_c(this.field_96294_a).get(i);
/*     */     
/* 412 */     this.field_96294_a.drawString(GuiScreenOnlineServers.func_140023_k(this.field_96294_a), mcoServer.func_96398_b(), j + 2, k + 1, 16777215);
/*     */     
/* 414 */     char c = 'Ï';
/* 415 */     byte b = 1;
/*     */     
/* 417 */     if (mcoServer.field_98166_h) {
/* 418 */       GuiScreenOnlineServers.func_104031_c(this.field_96294_a, j + c, k + b, this.field_104094_d, this.field_104095_e);
/* 419 */     } else if (mcoServer.field_96404_d.equals("CLOSED")) {
/* 420 */       GuiScreenOnlineServers.func_140035_b(this.field_96294_a, j + c, k + b, this.field_104094_d, this.field_104095_e);
/* 421 */     } else if (mcoServer.field_96405_e.equals(GuiScreenOnlineServers.func_140014_l(this.field_96294_a).getSession().getUsername()) && mcoServer.field_104063_i < 7) {
/* 422 */       func_96293_a(i, j - 14, k, mcoServer);
/* 423 */       GuiScreenOnlineServers.func_140031_a(this.field_96294_a, j + c, k + b, this.field_104094_d, this.field_104095_e, mcoServer.field_104063_i);
/* 424 */     } else if (mcoServer.field_96404_d.equals("OPEN")) {
/* 425 */       GuiScreenOnlineServers.func_140020_c(this.field_96294_a, j + c, k + b, this.field_104094_d, this.field_104095_e);
/* 426 */       func_96293_a(i, j - 14, k, mcoServer);
/*     */     } 
/*     */     
/* 429 */     this.field_96294_a.drawString(GuiScreenOnlineServers.func_140039_m(this.field_96294_a), mcoServer.func_96397_a(), j + 2, k + 12, 7105644);
/* 430 */     this.field_96294_a.drawString(GuiScreenOnlineServers.func_98079_k(this.field_96294_a), mcoServer.field_96405_e, j + 2, k + 12 + 11, 5000268);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_96293_a(int i, int j, int k, McoServer mcoServer) {
/* 435 */     if (mcoServer.field_96403_g == null)
/*     */       return; 
/* 437 */     synchronized (GuiScreenOnlineServers.func_140029_i()) {
/* 438 */       if (GuiScreenOnlineServers.func_140018_j() < 5 && (
/* 439 */         !mcoServer.field_96411_l || mcoServer.field_102022_m)) {
/* 440 */         (new ThreadConnectToOnlineServer(this, mcoServer)).start();
/*     */       }
/*     */     } 
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
/* 478 */     if (mcoServer.field_96414_k != null) this.field_96294_a.drawString(GuiScreenOnlineServers.func_110402_q(this.field_96294_a), mcoServer.field_96414_k, j + 215 - GuiScreenOnlineServers.func_140010_p(this.field_96294_a).getStringWidth(mcoServer.field_96414_k), k + 1, 8421504);
/*     */     
/* 480 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 481 */     GuiScreenOnlineServers.func_142023_q(this.field_96294_a).getTextureManager().bindTexture(Gui.icons);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlotOnlineServerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */