/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ 
/*    */ public class GuiSnooper
/*    */   extends GuiScreen
/*    */ {
/*    */   private final GuiScreen snooperGuiScreen;
/*    */   private final GameSettings snooperGameSettings;
/* 14 */   private final List field_74098_c = new ArrayList();
/* 15 */   private final List field_74096_d = new ArrayList();
/*    */   private String snooperTitle;
/*    */   private String[] field_74101_n;
/*    */   private GuiSnooperList snooperList;
/*    */   private GuiButton buttonAllowSnooping;
/*    */   
/*    */   public GuiSnooper(GuiScreen guiScreen, GameSettings gameSettings) {
/* 22 */     this.snooperGuiScreen = guiScreen;
/* 23 */     this.snooperGameSettings = gameSettings;
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 28 */     this.snooperTitle = I18n.getString("options.snooper.title");
/* 29 */     String str = I18n.getString("options.snooper.desc");
/* 30 */     ArrayList<String> arrayList = new ArrayList();
/*    */     
/* 32 */     for (String str1 : this.fontRenderer.listFormattedStringToWidth(str, this.width - 30)) {
/* 33 */       arrayList.add(str1);
/*    */     }
/*    */     
/* 36 */     this.field_74101_n = arrayList.<String>toArray(new String[0]);
/*    */     
/* 38 */     this.field_74098_c.clear();
/* 39 */     this.field_74096_d.clear();
/*    */     
/* 41 */     this.buttonList.add(this.buttonAllowSnooping = new GuiButton(1, this.width / 2 - 152, this.height - 30, 150, 20, this.snooperGameSettings.getKeyBinding(EnumOptions.SNOOPER_ENABLED)));
/* 42 */     this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height - 30, 150, 20, I18n.getString("gui.done")));
/*    */     
/* 44 */     boolean bool = (this.mc.getIntegratedServer() != null && this.mc.getIntegratedServer().getPlayerUsageSnooper() != null) ? true : false;
/*    */     
/* 46 */     for (Map.Entry<?, ?> entry : (new TreeMap<Object, Object>(this.mc.getPlayerUsageSnooper().getCurrentStats())).entrySet()) {
/* 47 */       this.field_74098_c.add((bool ? "C " : "") + (String)entry.getKey());
/* 48 */       this.field_74096_d.add(this.fontRenderer.trimStringToWidth((String)entry.getValue(), this.width - 220));
/*    */     } 
/*    */     
/* 51 */     if (bool) {
/* 52 */       for (Map.Entry<?, ?> entry : (new TreeMap<Object, Object>(this.mc.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats())).entrySet()) {
/* 53 */         this.field_74098_c.add("S " + (String)entry.getKey());
/* 54 */         this.field_74096_d.add(this.fontRenderer.trimStringToWidth((String)entry.getValue(), this.width - 220));
/*    */       } 
/*    */     }
/*    */     
/* 58 */     this.snooperList = new GuiSnooperList(this);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 63 */     if (!guiButton.enabled)
/*    */       return; 
/* 65 */     if (guiButton.id == 2) {
/* 66 */       this.snooperGameSettings.saveOptions();
/* 67 */       this.snooperGameSettings.saveOptions();
/* 68 */       this.mc.displayGuiScreen(this.snooperGuiScreen);
/*    */     } 
/*    */     
/* 71 */     if (guiButton.id == 1) {
/* 72 */       this.snooperGameSettings.setOptionValue(EnumOptions.SNOOPER_ENABLED, 1);
/* 73 */       this.buttonAllowSnooping.displayString = this.snooperGameSettings.getKeyBinding(EnumOptions.SNOOPER_ENABLED);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 79 */     drawDefaultBackground();
/*    */     
/* 81 */     this.snooperList.drawScreen(i, j, f);
/* 82 */     drawCenteredString(this.fontRenderer, this.snooperTitle, this.width / 2, 8, 16777215);
/*    */     
/* 84 */     int k = 22;
/* 85 */     for (String str : this.field_74101_n) {
/* 86 */       drawCenteredString(this.fontRenderer, str, this.width / 2, k, 8421504);
/* 87 */       k += this.fontRenderer.FONT_HEIGHT;
/*    */     } 
/*    */     
/* 90 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSnooper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */