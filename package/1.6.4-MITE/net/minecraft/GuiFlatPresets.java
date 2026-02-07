/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiFlatPresets
/*     */   extends GuiScreen
/*     */ {
/*  13 */   private static RenderItem presetIconRenderer = new RenderItem();
/*     */ 
/*     */   
/*  16 */   private static final List presets = new ArrayList();
/*     */   
/*     */   private final GuiCreateFlatWorld createFlatWorldGui;
/*     */   private String field_82300_d;
/*     */   private String field_82308_m;
/*     */   private String field_82306_n;
/*     */   private GuiFlatPresetsListSlot theFlatPresetsListSlot;
/*     */   private GuiButton theButton;
/*     */   private GuiTextField theTextField;
/*     */   
/*     */   public GuiFlatPresets(GuiCreateFlatWorld par1GuiCreateFlatWorld) {
/*  27 */     this.createFlatWorldGui = par1GuiCreateFlatWorld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  35 */     this.buttonList.clear();
/*  36 */     Keyboard.enableRepeatEvents(true);
/*  37 */     this.field_82300_d = I18n.getString("createWorld.customize.presets.title");
/*  38 */     this.field_82308_m = I18n.getString("createWorld.customize.presets.share");
/*  39 */     this.field_82306_n = I18n.getString("createWorld.customize.presets.list");
/*  40 */     this.theTextField = new GuiTextField(this.fontRenderer, 50, 40, this.width - 100, 20);
/*  41 */     this.theFlatPresetsListSlot = new GuiFlatPresetsListSlot(this);
/*  42 */     this.theTextField.setMaxStringLength(1230);
/*  43 */     this.theTextField.setText(this.createFlatWorldGui.getFlatGeneratorInfo());
/*  44 */     this.buttonList.add(this.theButton = new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, I18n.getString("createWorld.customize.presets.select")));
/*  45 */     this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, I18n.getString("gui.cancel")));
/*  46 */     func_82296_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  54 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/*  62 */     this.theTextField.mouseClicked(par1, par2, par3);
/*  63 */     super.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/*  71 */     if (!this.theTextField.textboxKeyTyped(par1, par2))
/*     */     {
/*  73 */       super.keyTyped(par1, par2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  82 */     if (par1GuiButton.id == 0 && func_82293_j()) {
/*     */       
/*  84 */       this.createFlatWorldGui.setFlatGeneratorInfo(this.theTextField.getText());
/*  85 */       this.mc.displayGuiScreen(this.createFlatWorldGui);
/*     */     }
/*  87 */     else if (par1GuiButton.id == 1) {
/*     */       
/*  89 */       this.mc.displayGuiScreen(this.createFlatWorldGui);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  98 */     drawDefaultBackground();
/*  99 */     this.theFlatPresetsListSlot.drawScreen(par1, par2, par3);
/* 100 */     drawCenteredString(this.fontRenderer, this.field_82300_d, this.width / 2, 8, 16777215);
/* 101 */     drawString(this.fontRenderer, this.field_82308_m, 50, 30, 10526880);
/* 102 */     drawString(this.fontRenderer, this.field_82306_n, 50, 70, 10526880);
/* 103 */     this.theTextField.drawTextBox();
/* 104 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 112 */     this.theTextField.updateCursorCounter();
/* 113 */     super.updateScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82296_g() {
/* 118 */     boolean var1 = func_82293_j();
/* 119 */     this.theButton.enabled = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_82293_j() {
/* 124 */     return ((this.theFlatPresetsListSlot.field_82459_a > -1 && this.theFlatPresetsListSlot.field_82459_a < presets.size()) || this.theTextField.getText().length() > 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addPresetNoFeatures(String par0Str, int par1, BiomeGenBase par2BiomeGenBase, FlatLayerInfo... par3ArrayOfFlatLayerInfo) {
/* 132 */     addPreset(par0Str, par1, par2BiomeGenBase, (List)null, par3ArrayOfFlatLayerInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addPreset(String par0Str, int par1, BiomeGenBase par2BiomeGenBase, List par3List, FlatLayerInfo... par4ArrayOfFlatLayerInfo) {
/* 140 */     FlatGeneratorInfo var5 = new FlatGeneratorInfo();
/*     */     
/* 142 */     for (int var6 = par4ArrayOfFlatLayerInfo.length - 1; var6 >= 0; var6--)
/*     */     {
/* 144 */       var5.getFlatLayers().add(par4ArrayOfFlatLayerInfo[var6]);
/*     */     }
/*     */     
/* 147 */     var5.setBiome(par2BiomeGenBase.biomeID);
/* 148 */     var5.func_82645_d();
/*     */     
/* 150 */     if (par3List != null) {
/*     */       
/* 152 */       Iterator<String> var8 = par3List.iterator();
/*     */       
/* 154 */       while (var8.hasNext()) {
/*     */         
/* 156 */         String var7 = var8.next();
/* 157 */         var5.getWorldFeatures().put(var7, new HashMap<Object, Object>());
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     presets.add(new GuiFlatPresetsItem(par1, par0Str, var5.toString()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static RenderItem getPresetIconRenderer() {
/* 169 */     return presetIconRenderer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static List getPresets() {
/* 177 */     return presets;
/*     */   }
/*     */ 
/*     */   
/*     */   static GuiFlatPresetsListSlot func_82292_a(GuiFlatPresets par0GuiFlatPresets) {
/* 182 */     return par0GuiFlatPresets.theFlatPresetsListSlot;
/*     */   }
/*     */ 
/*     */   
/*     */   static GuiTextField func_82298_b(GuiFlatPresets par0GuiFlatPresets) {
/* 187 */     return par0GuiFlatPresets.theTextField;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 192 */     addPreset("Classic Flat", Block.grass.blockID, BiomeGenBase.plains, Arrays.asList(new String[] { "village" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, Block.grass.blockID), new FlatLayerInfo(2, Block.dirt.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/* 193 */     addPreset("Tunnelers' Dream", Block.stone.blockID, BiomeGenBase.extremeHills, Arrays.asList(new String[] { "biome_1", "dungeon", "decoration", "stronghold", "mineshaft" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, Block.grass.blockID), new FlatLayerInfo(5, Block.dirt.blockID), new FlatLayerInfo(230, Block.stone.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/* 194 */     addPreset("Water World", Block.waterMoving.blockID, BiomeGenBase.plains, Arrays.asList(new String[] { "village", "biome_1" }, ), new FlatLayerInfo[] { new FlatLayerInfo(90, Block.waterStill.blockID), new FlatLayerInfo(5, Block.sand.blockID), new FlatLayerInfo(5, Block.dirt.blockID), new FlatLayerInfo(5, Block.stone.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/* 195 */     addPreset("Overworld", Block.tallGrass.blockID, BiomeGenBase.plains, Arrays.asList(new String[] { "village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, Block.grass.blockID), new FlatLayerInfo(3, Block.dirt.blockID), new FlatLayerInfo(59, Block.stone.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/* 196 */     addPreset("Snowy Kingdom", Block.snow.blockID, BiomeGenBase.icePlains, Arrays.asList(new String[] { "village", "biome_1" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, Block.snow.blockID), new FlatLayerInfo(1, Block.grass.blockID), new FlatLayerInfo(3, Block.dirt.blockID), new FlatLayerInfo(59, Block.stone.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/* 197 */     addPreset("Bottomless Pit", Item.feather.itemID, BiomeGenBase.plains, Arrays.asList(new String[] { "village", "biome_1" }, ), new FlatLayerInfo[] { new FlatLayerInfo(1, Block.grass.blockID), new FlatLayerInfo(3, Block.dirt.blockID), new FlatLayerInfo(2, Block.cobblestone.blockID) });
/* 198 */     addPreset("Desert", Block.sand.blockID, BiomeGenBase.desert, Arrays.asList(new String[] { "village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon" }, ), new FlatLayerInfo[] { new FlatLayerInfo(8, Block.sand.blockID), new FlatLayerInfo(52, Block.sandStone.blockID), new FlatLayerInfo(3, Block.stone.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/* 199 */     addPresetNoFeatures("Redstone Ready", Item.redstone.itemID, BiomeGenBase.desert, new FlatLayerInfo[] { new FlatLayerInfo(52, Block.sandStone.blockID), new FlatLayerInfo(3, Block.stone.blockID), new FlatLayerInfo(1, Block.bedrock.blockID) });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiFlatPresets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */