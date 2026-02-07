/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiStats
/*     */   extends GuiScreen
/*     */ {
/*   8 */   private static RenderItem renderItem = new RenderItem();
/*     */   
/*     */   protected GuiScreen parentGui;
/*     */   
/*  12 */   protected String statsTitle = "Select world";
/*     */ 
/*     */   
/*     */   private GuiSlotStatsGeneral slotGeneral;
/*     */ 
/*     */   
/*     */   private GuiSlotStatsItem slotItem;
/*     */   
/*     */   private GuiSlotStatsBlock slotBlock;
/*     */   
/*     */   private StatFileWriter statFileWriter;
/*     */   
/*     */   private GuiSlot selectedSlot;
/*     */   
/*     */   public static boolean this_world_only = true;
/*     */   
/*     */   GuiButton button_toggle_vanilla_view;
/*     */   
/*     */   GuiButton button_blocks;
/*     */   
/*     */   GuiButton button_items;
/*     */ 
/*     */   
/*     */   public GuiStats(GuiScreen par1GuiScreen, StatFileWriter par2StatFileWriter) {
/*  36 */     this.parentGui = par1GuiScreen;
/*  37 */     this.statFileWriter = par2StatFileWriter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  45 */     this_world_only = true;
/*     */     
/*  47 */     this.statsTitle = I18n.getString("gui.stats");
/*  48 */     this.slotGeneral = new GuiSlotStatsGeneral(this);
/*  49 */     this.slotGeneral.registerScrollButtons(1, 1);
/*  50 */     this.slotItem = new GuiSlotStatsItem(this);
/*  51 */     this.slotItem.registerScrollButtons(1, 1);
/*  52 */     this.slotBlock = new GuiSlotStatsBlock(this);
/*  53 */     this.slotBlock.registerScrollButtons(1, 1);
/*  54 */     this.selectedSlot = this.slotGeneral;
/*  55 */     addHeaderButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkButtonStates() {
/*  60 */     this.button_blocks.enabled = (this.slotBlock.getSize() > 0);
/*  61 */     this.button_items.enabled = (this.slotItem.getSize() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addHeaderButtons() {
/*  69 */     this.buttonList.add(new GuiButton(0, this.width / 2 + 4, this.height - 28, 150, 20, I18n.getString("gui.done")));
/*  70 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.getString("stat.generalButton")));
/*     */     
/*     */     GuiButton var1;
/*  73 */     this.buttonList.add(this.button_blocks = var1 = new GuiButton(2, this.width / 2 - 46, this.height - 52, 100, 20, I18n.getString("stat.blocksButton")));
/*     */     
/*     */     GuiButton var2;
/*  76 */     this.buttonList.add(this.button_items = var2 = new GuiButton(3, this.width / 2 + 62, this.height - 52, 100, 20, I18n.getString("stat.itemsButton")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     checkButtonStates();
/*     */     
/*  90 */     if (Minecraft.inDevMode()) {
/*  91 */       this.buttonList.add(this.button_toggle_vanilla_view = new GuiButton(4, this.width / 2 - 4 - 150, this.height - 28, 150, 20, I18n.getString(this_world_only ? "gui.stats.allWorlds" : "gui.stats.thisWorld")));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  99 */     if (par1GuiButton.enabled)
/*     */     {
/* 101 */       if (par1GuiButton.id == 0) {
/*     */         
/* 103 */         this.mc.displayGuiScreen(this.parentGui);
/*     */       }
/* 105 */       else if (par1GuiButton.id == 1) {
/*     */         
/* 107 */         this.selectedSlot = this.slotGeneral;
/*     */       }
/* 109 */       else if (par1GuiButton.id == 3) {
/*     */         
/* 111 */         this.selectedSlot = this.slotItem;
/*     */       }
/* 113 */       else if (par1GuiButton.id == 2) {
/*     */         
/* 115 */         this.selectedSlot = this.slotBlock;
/*     */       }
/* 117 */       else if (par1GuiButton.id == 4) {
/*     */         
/* 119 */         this_world_only = !this_world_only;
/*     */         
/* 121 */         this.button_toggle_vanilla_view.displayString = I18n.getString(this_world_only ? "gui.stats.allWorlds" : "gui.stats.thisWorld");
/*     */         
/* 123 */         this.slotBlock = new GuiSlotStatsBlock(this);
/* 124 */         this.slotBlock.registerScrollButtons(1, 1);
/*     */         
/* 126 */         this.slotItem = new GuiSlotStatsItem(this);
/* 127 */         this.slotItem.registerScrollButtons(1, 1);
/*     */         
/* 129 */         checkButtonStates();
/*     */         
/* 131 */         if (this.selectedSlot instanceof GuiSlotStatsBlock) {
/* 132 */           this.selectedSlot = this.button_blocks.enabled ? this.slotBlock : this.slotGeneral;
/* 133 */         } else if (this.selectedSlot instanceof GuiSlotStatsItem) {
/* 134 */           this.selectedSlot = this.button_items.enabled ? this.slotItem : this.slotGeneral;
/*     */         } 
/*     */       } else {
/*     */         
/* 138 */         this.selectedSlot.actionPerformed(par1GuiButton);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 148 */     this.selectedSlot.drawScreen(par1, par2, par3);
/* 149 */     drawCenteredString(this.fontRenderer, this.statsTitle, this.width / 2, 20, 16777215);
/* 150 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawItemSprite(int par1, int par2, int par3) {
/* 158 */     drawButtonBackground(par1 + 1, par2 + 1);
/* 159 */     GL11.glEnable(32826);
/* 160 */     RenderHelper.enableGUIStandardItemLighting();
/*     */     
/* 162 */     renderItem.renderItemIntoGUI(this.fontRenderer, this.mc.getTextureManager(), Item.getItem(par3).getItemStackForStatsIcon(), par1 + 2, par2 + 2);
/*     */     
/* 164 */     RenderHelper.disableStandardItemLighting();
/* 165 */     GL11.glDisable(32826);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawButtonBackground(int par1, int par2) {
/* 173 */     drawSprite(par1, par2, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSprite(int par1, int par2, int par3, int par4) {
/* 181 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 182 */     this.mc.getTextureManager().bindTexture(statIcons);
/* 183 */     float var5 = 0.0078125F;
/* 184 */     float var6 = 0.0078125F;
/* 185 */     boolean var7 = true;
/* 186 */     boolean var8 = true;
/* 187 */     Tessellator var9 = Tessellator.instance;
/* 188 */     var9.startDrawingQuads();
/* 189 */     var9.addVertexWithUV((par1 + 0), (par2 + 18), this.zLevel, ((par3 + 0) * 0.0078125F), ((par4 + 18) * 0.0078125F));
/* 190 */     var9.addVertexWithUV((par1 + 18), (par2 + 18), this.zLevel, ((par3 + 18) * 0.0078125F), ((par4 + 18) * 0.0078125F));
/* 191 */     var9.addVertexWithUV((par1 + 18), (par2 + 0), this.zLevel, ((par3 + 18) * 0.0078125F), ((par4 + 0) * 0.0078125F));
/* 192 */     var9.addVertexWithUV((par1 + 0), (par2 + 0), this.zLevel, ((par3 + 0) * 0.0078125F), ((par4 + 0) * 0.0078125F));
/* 193 */     var9.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   static Minecraft getMinecraft(GuiStats par0GuiStats) {
/* 198 */     return par0GuiStats.mc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer1(GuiStats par0GuiStats) {
/* 206 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */   
/*     */   static StatFileWriter getStatsFileWriter(GuiStats par0GuiStats) {
/* 211 */     return par0GuiStats.statFileWriter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer2(GuiStats par0GuiStats) {
/* 219 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer3(GuiStats par0GuiStats) {
/* 227 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Minecraft getMinecraft1(GuiStats par0GuiStats) {
/* 235 */     return par0GuiStats.mc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void drawSprite(GuiStats par0GuiStats, int par1, int par2, int par3, int par4) {
/* 243 */     par0GuiStats.drawSprite(par1, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Minecraft getMinecraft2(GuiStats par0GuiStats) {
/* 251 */     return par0GuiStats.mc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer4(GuiStats par0GuiStats) {
/* 259 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer5(GuiStats par0GuiStats) {
/* 267 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer6(GuiStats par0GuiStats) {
/* 275 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer7(GuiStats par0GuiStats) {
/* 283 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer8(GuiStats par0GuiStats) {
/* 291 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */   
/*     */   static void drawGradientRect(GuiStats par0GuiStats, int par1, int par2, int par3, int par4, int par5, int par6) {
/* 296 */     par0GuiStats.drawGradientRect(par1, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer9(GuiStats par0GuiStats) {
/* 304 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer10(GuiStats par0GuiStats) {
/* 312 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void drawGradientRect1(GuiStats par0GuiStats, int par1, int par2, int par3, int par4, int par5, int par6) {
/* 320 */     par0GuiStats.drawGradientRect(par1, par2, par3, par4, par5, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FontRenderer getFontRenderer11(GuiStats par0GuiStats) {
/* 328 */     return par0GuiStats.fontRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void drawItemSprite(GuiStats par0GuiStats, int par1, int par2, int par3) {
/* 336 */     par0GuiStats.drawItemSprite(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */