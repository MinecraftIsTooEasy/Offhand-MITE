/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiCreateFlatWorld
/*     */   extends GuiScreen
/*     */ {
/*  16 */   private static RenderItem theRenderItem = new RenderItem();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final GuiCreateWorld createWorldGui;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   private FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
/*     */   private String customizationTitle;
/*     */   private String layerMaterialLabel;
/*     */   private String heightLabel;
/*     */   private GuiCreateFlatWorldListSlot createFlatWorldListSlotGui;
/*     */   private GuiButton buttonAddLayer;
/*     */   private GuiButton buttonEditLayer;
/*     */   private GuiButton buttonRemoveLayer;
/*     */   
/*     */   public GuiCreateFlatWorld(GuiCreateWorld guiCreateWorld, String string) {
/*  44 */     this.createWorldGui = guiCreateWorld;
/*     */     
/*  46 */     setFlatGeneratorInfo(string);
/*     */   }
/*     */   
/*     */   public String getFlatGeneratorInfo() {
/*  50 */     return this.theFlatGeneratorInfo.toString();
/*     */   }
/*     */   
/*     */   public void setFlatGeneratorInfo(String string) {
/*  54 */     this.theFlatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  59 */     this.buttonList.clear();
/*     */     
/*  61 */     this.customizationTitle = I18n.getString("createWorld.customize.flat.title");
/*  62 */     this.layerMaterialLabel = I18n.getString("createWorld.customize.flat.tile");
/*  63 */     this.heightLabel = I18n.getString("createWorld.customize.flat.height");
/*     */     
/*  65 */     this.createFlatWorldListSlotGui = new GuiCreateFlatWorldListSlot(this);
/*     */     
/*  67 */     this.buttonList.add(this.buttonAddLayer = new GuiButton(2, this.width / 2 - 154, this.height - 52, 100, 20, I18n.getString("createWorld.customize.flat.addLayer") + " (NYI)"));
/*  68 */     this.buttonList.add(this.buttonEditLayer = new GuiButton(3, this.width / 2 - 50, this.height - 52, 100, 20, I18n.getString("createWorld.customize.flat.editLayer") + " (NYI)"));
/*  69 */     this.buttonList.add(this.buttonRemoveLayer = new GuiButton(4, this.width / 2 - 155, this.height - 52, 150, 20, I18n.getString("createWorld.customize.flat.removeLayer")));
/*     */     
/*  71 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, I18n.getString("gui.done")));
/*  72 */     this.buttonList.add(new GuiButton(5, this.width / 2 + 5, this.height - 52, 150, 20, I18n.getString("createWorld.customize.presets")));
/*  73 */     this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, I18n.getString("gui.cancel")));
/*     */     
/*  75 */     this.buttonEditLayer.drawButton = false;
/*     */     
/*  77 */     this.theFlatGeneratorInfo.func_82645_d();
/*  78 */     func_82270_g();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  83 */     int i = this.theFlatGeneratorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.field_82454_a - 1;
/*     */     
/*  85 */     if (guiButton.id == 1) {
/*  86 */       this.mc.displayGuiScreen(this.createWorldGui);
/*  87 */     } else if (guiButton.id == 0) {
/*  88 */       this.createWorldGui.generatorOptionsToUse = getFlatGeneratorInfo();
/*  89 */       this.mc.displayGuiScreen(this.createWorldGui);
/*  90 */     } else if (guiButton.id == 5) {
/*  91 */       this.mc.displayGuiScreen(new GuiFlatPresets(this));
/*  92 */     } else if (guiButton.id == 4 && func_82272_i()) {
/*  93 */       this.theFlatGeneratorInfo.getFlatLayers().remove(i);
/*  94 */       this.createFlatWorldListSlotGui.field_82454_a = Math.min(this.createFlatWorldListSlotGui.field_82454_a, this.theFlatGeneratorInfo.getFlatLayers().size() - 1);
/*     */     } 
/*     */     
/*  97 */     this.theFlatGeneratorInfo.func_82645_d();
/*  98 */     func_82270_g();
/*     */   }
/*     */   
/*     */   public void func_82270_g() {
/* 102 */     boolean bool = func_82272_i();
/* 103 */     this.buttonRemoveLayer.enabled = bool;
/* 104 */     this.buttonEditLayer.enabled = bool;
/*     */ 
/*     */     
/* 107 */     this.buttonEditLayer.enabled = false;
/* 108 */     this.buttonAddLayer.enabled = false;
/*     */   }
/*     */   
/*     */   private boolean func_82272_i() {
/* 112 */     return (this.createFlatWorldListSlotGui.field_82454_a > -1 && this.createFlatWorldListSlotGui.field_82454_a < this.theFlatGeneratorInfo.getFlatLayers().size());
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 117 */     drawDefaultBackground();
/*     */     
/* 119 */     this.createFlatWorldListSlotGui.drawScreen(i, j, f);
/* 120 */     drawCenteredString(this.fontRenderer, this.customizationTitle, this.width / 2, 8, 16777215);
/*     */     
/* 122 */     int k = this.width / 2 - 92 - 16;
/* 123 */     drawString(this.fontRenderer, this.layerMaterialLabel, k, 32, 16777215);
/* 124 */     drawString(this.fontRenderer, this.heightLabel, k + 2 + 213 - this.fontRenderer.getStringWidth(this.heightLabel), 32, 16777215);
/*     */     
/* 126 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiCreateFlatWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */