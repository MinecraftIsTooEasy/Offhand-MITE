/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GuiSlotLanguage
/*     */   extends GuiSlot
/*     */ {
/*  66 */   private final List field_77251_g = Lists.newArrayList();
/*  67 */   private final Map field_77253_h = Maps.newHashMap();
/*     */   
/*     */   public GuiSlotLanguage(GuiLanguage guiLanguage) {
/*  70 */     super(guiLanguage.mc, guiLanguage.width, guiLanguage.height, 32, guiLanguage.height - 65 + 4, 18);
/*     */     
/*  72 */     for (Language language : GuiLanguage.func_135011_a(guiLanguage).getLanguages()) {
/*  73 */       this.field_77253_h.put(language.getLanguageCode(), language);
/*  74 */       this.field_77251_g.add(language.getLanguageCode());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  80 */     return this.field_77251_g.size();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void elementClicked(int i, boolean bl) {
/*  85 */     Language language = (Language)this.field_77253_h.get(this.field_77251_g.get(i));
/*     */     
/*  87 */     GuiLanguage.func_135011_a(this.languageGui).setCurrentLanguage(language);
/*  88 */     (GuiLanguage.getGameSettings(this.languageGui)).language = language.getLanguageCode();
/*     */     
/*  90 */     this.languageGui.mc.refreshResources();
/*     */ 
/*     */     
/*  93 */     this.languageGui.fontRenderer.setUnicodeFlag(GuiLanguage.func_135011_a(this.languageGui).isCurrentLocaleUnicode());
/*  94 */     this.languageGui.fontRenderer.setBidiFlag(GuiLanguage.func_135011_a(this.languageGui).isCurrentLanguageBidirectional());
/*     */     
/*  96 */     (GuiLanguage.getDoneButton(this.languageGui)).displayString = I18n.getString("gui.done");
/*  97 */     GuiLanguage.getGameSettings(this.languageGui).saveOptions();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int i) {
/* 102 */     return ((String)this.field_77251_g.get(i)).equals(GuiLanguage.func_135011_a(this.languageGui).getCurrentLanguage().getLanguageCode());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/* 107 */     return getSize() * 18;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/* 112 */     this.languageGui.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
/* 117 */     this.languageGui.fontRenderer.setBidiFlag(true);
/* 118 */     this.languageGui.drawCenteredString(this.languageGui.fontRenderer, ((Language)this.field_77253_h.get(this.field_77251_g.get(i))).toString(), this.languageGui.width / 2, k + 1, 16777215);
/* 119 */     this.languageGui.fontRenderer.setBidiFlag(GuiLanguage.func_135011_a(this.languageGui).getCurrentLanguage().isBidirectional());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlotLanguage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */