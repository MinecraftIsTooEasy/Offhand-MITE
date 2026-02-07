/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ class GuiSlotStatsItem
/*     */   extends GuiSlotStats
/*     */ {
/*     */   final GuiStats slotGuiStats;
/*     */   
/*     */   public GuiSlotStatsItem(GuiStats par1GuiStats) {
/*  13 */     super(par1GuiStats);
/*  14 */     this.slotGuiStats = par1GuiStats;
/*  15 */     this.field_77266_h = new ArrayList();
/*  16 */     Iterator<StatCrafting> var2 = StatList.itemStats.iterator();
/*     */     
/*  18 */     while (var2.hasNext()) {
/*     */       
/*  20 */       StatCrafting var3 = var2.next();
/*  21 */       boolean var4 = false;
/*  22 */       int var5 = var3.getItemID();
/*     */       
/*  24 */       if (GuiStats.getStatsFileWriter(par1GuiStats).writeStat(var3) > 0) {
/*     */         
/*  26 */         var4 = true;
/*     */       }
/*  28 */       else if (StatList.objectBreakStats[var5] != null && GuiStats.getStatsFileWriter(par1GuiStats).writeStat(StatList.objectBreakStats[var5]) > 0) {
/*     */         
/*  30 */         var4 = true;
/*     */       }
/*  32 */       else if (StatList.objectCraftStats[var5] != null && GuiStats.getStatsFileWriter(par1GuiStats).writeStat(StatList.objectCraftStats[var5]) > 0) {
/*     */         
/*  34 */         var4 = true;
/*     */       } 
/*     */       
/*  37 */       if (GuiStats.this_world_only)
/*     */       {
/*  39 */         if (PlayerStatsHelper.getValueOnClient(var3) > 0L) {
/*  40 */           var4 = true;
/*  41 */         } else if (StatList.objectBreakStats[var5] != null && PlayerStatsHelper.getValueOnClient(StatList.objectBreakStats[var5]) > 0L) {
/*  42 */           var4 = true;
/*  43 */         } else if (StatList.objectCraftStats[var5] != null && PlayerStatsHelper.getValueOnClient(StatList.objectCraftStats[var5]) > 0L) {
/*  44 */           var4 = true;
/*     */         } else {
/*  46 */           var4 = false;
/*     */         } 
/*     */       }
/*  49 */       if (var4)
/*     */       {
/*  51 */         this.field_77266_h.add(var3);
/*     */       }
/*     */     } 
/*     */     
/*  55 */     this.field_77267_i = new SorterStatsItem(this, par1GuiStats);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77222_a(int par1, int par2, Tessellator par3Tessellator) {
/*  60 */     super.func_77222_a(par1, par2, par3Tessellator);
/*     */     
/*  62 */     if (this.field_77262_g == 0) {
/*     */       
/*  64 */       GuiStats.drawSprite(this.slotGuiStats, par1 + 115 - 18 + 1, par2 + 1 + 1, 72, 18);
/*     */     }
/*     */     else {
/*     */       
/*  68 */       GuiStats.drawSprite(this.slotGuiStats, par1 + 115 - 18, par2 + 1, 72, 18);
/*     */     } 
/*     */     
/*  71 */     if (this.field_77262_g == 1) {
/*     */       
/*  73 */       GuiStats.drawSprite(this.slotGuiStats, par1 + 165 - 18 + 1, par2 + 1 + 1, 18, 18);
/*     */     }
/*     */     else {
/*     */       
/*  77 */       GuiStats.drawSprite(this.slotGuiStats, par1 + 165 - 18, par2 + 1, 18, 18);
/*     */     } 
/*     */     
/*  80 */     if (this.field_77262_g == 2) {
/*     */       
/*  82 */       GuiStats.drawSprite(this.slotGuiStats, par1 + 215 - 18 + 1, par2 + 1 + 1, 36, 18);
/*     */     }
/*     */     else {
/*     */       
/*  86 */       GuiStats.drawSprite(this.slotGuiStats, par1 + 215 - 18, par2 + 1, 36, 18);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
/*  92 */     StatCrafting var6 = func_77257_d(par1);
/*  93 */     int var7 = var6.getItemID();
/*  94 */     GuiStats.drawItemSprite(this.slotGuiStats, par2 + 40, par3, var7);
/*  95 */     func_77260_a((StatCrafting)StatList.objectBreakStats[var7], par2 + 115, par3, (par1 % 2 == 0));
/*  96 */     func_77260_a((StatCrafting)StatList.objectCraftStats[var7], par2 + 165, par3, (par1 % 2 == 0));
/*  97 */     func_77260_a(var6, par2 + 215, par3, (par1 % 2 == 0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected String func_77258_c(int par1) {
/* 102 */     return (par1 == 1) ? "stat.crafted" : ((par1 == 2) ? "stat.used" : "stat.depleted");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlotStatsItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */