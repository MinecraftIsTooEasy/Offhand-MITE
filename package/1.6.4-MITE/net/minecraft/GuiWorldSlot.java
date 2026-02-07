/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ class GuiWorldSlot
/*     */   extends GuiSlot
/*     */ {
/*     */   final GuiSelectWorld parentWorldGui;
/*     */   
/*     */   public GuiWorldSlot(GuiSelectWorld par1GuiSelectWorld) {
/*  11 */     super(par1GuiSelectWorld.mc, par1GuiSelectWorld.width, par1GuiSelectWorld.height, 32, par1GuiSelectWorld.height - 64, 36);
/*  12 */     this.parentWorldGui = par1GuiSelectWorld;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  20 */     return GuiSelectWorld.getSize(this.parentWorldGui).size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int par1, boolean par2) {
/*  28 */     GuiSelectWorld.onElementSelected(this.parentWorldGui, par1);
/*  29 */     boolean var3 = (GuiSelectWorld.getSelectedWorld(this.parentWorldGui) >= 0 && GuiSelectWorld.getSelectedWorld(this.parentWorldGui) < getSize());
/*     */     
/*  31 */     (GuiSelectWorld.getSelectButton(this.parentWorldGui)).enabled = this.parentWorldGui.selectedWorldPassedValidation();
/*  32 */     (GuiSelectWorld.getRenameButton(this.parentWorldGui)).enabled = var3;
/*  33 */     (GuiSelectWorld.getDeleteButton(this.parentWorldGui)).enabled = var3;
/*     */     
/*  35 */     (GuiSelectWorld.func_82312_f(this.parentWorldGui)).enabled = this.parentWorldGui.selectedWorldPassedValidation();
/*     */     
/*  37 */     if (par2 && var3)
/*     */     {
/*  39 */       this.parentWorldGui.selectWorld(par1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int par1) {
/*  48 */     return (par1 == GuiSelectWorld.getSelectedWorld(this.parentWorldGui));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  56 */     return GuiSelectWorld.getSize(this.parentWorldGui).size() * 36;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/*  61 */     this.parentWorldGui.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/*  66 */     SaveFormatComparator var6 = GuiSelectWorld.getSize(this.parentWorldGui).get(par1);
/*  67 */     String var7 = var6.getDisplayName();
/*     */     
/*  69 */     if (var7 == null || MathHelper.stringNullOrLengthZero(var7))
/*     */     {
/*  71 */       var7 = GuiSelectWorld.func_82313_g(this.parentWorldGui) + " " + (par1 + 1);
/*     */     }
/*     */     
/*  74 */     if (!var6.passed_validation) {
/*  75 */       var7 = var7 + EnumChatFormatting.DARK_GRAY + " | " + EnumChatFormatting.RED + ((var6.failed_validation_reason == null) ? "INVALID WORLD" : var6.failed_validation_reason);
/*     */     }
/*  77 */     String var8 = var6.getFileName();
/*  78 */     var8 = var8 + " (" + GuiSelectWorld.func_82315_h(this.parentWorldGui).format(new Date(var6.getLastTimePlayed()));
/*  79 */     var8 = var8 + ")";
/*  80 */     String var9 = "";
/*     */     
/*  82 */     if (var6.requiresConversion()) {
/*     */       
/*  84 */       var9 = GuiSelectWorld.func_82311_i(this.parentWorldGui) + " " + var9;
/*     */     }
/*     */     else {
/*     */       
/*  88 */       var9 = GuiSelectWorld.func_82314_j(this.parentWorldGui)[var6.getEnumGameType().getID()];
/*     */       
/*  90 */       if (var6.isHardcoreModeEnabled())
/*     */       {
/*  92 */         var9 = EnumChatFormatting.DARK_RED + I18n.getString("gameMode.hardcore") + EnumChatFormatting.RESET;
/*     */       }
/*     */       
/*  95 */       if (var6.areSkillsEnabled()) {
/*  96 */         var9 = var9 + ", " + I18n.getString("selectWorld.skills");
/*     */       }
/*  98 */       if (var6.getCheatsEnabled())
/*     */       {
/* 100 */         var9 = var9 + ", " + I18n.getString("selectWorld.cheats");
/*     */       }
/*     */     } 
/*     */     
/* 104 */     this.parentWorldGui.drawString(this.parentWorldGui.fontRenderer, var7, par2 + 2, par3 + 1, 16777215);
/* 105 */     this.parentWorldGui.drawString(this.parentWorldGui.fontRenderer, var8, par2 + 2, par3 + 12, 8421504);
/* 106 */     this.parentWorldGui.drawString(this.parentWorldGui.fontRenderer, var9, par2 + 2, par3 + 12 + 10, 8421504);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiWorldSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */