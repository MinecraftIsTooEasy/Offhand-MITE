/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiDisconnected
/*     */   extends GuiScreen
/*     */ {
/*     */   private String errorMessage;
/*     */   private String errorDetail;
/*     */   private Object[] field_74247_c;
/*     */   private List field_74245_d;
/*     */   private final GuiScreen field_98095_n;
/*     */   private long next_alarm_ms;
/*     */   public static int message_type;
/*     */   
/*     */   public GuiDisconnected(GuiScreen par1GuiScreen, String par2Str, String par3Str, Object... par4ArrayOfObj) {
/*  24 */     this.field_98095_n = par1GuiScreen;
/*  25 */     this.errorMessage = I18n.getString(par2Str);
/*  26 */     this.errorDetail = par3Str;
/*  27 */     this.field_74247_c = par4ArrayOfObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  40 */     this.buttonList.clear();
/*  41 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.toMenu")));
/*     */     
/*  43 */     if (this.field_74247_c != null) {
/*     */       
/*  45 */       this.field_74245_d = this.fontRenderer.listFormattedStringToWidth(I18n.getStringParams(this.errorDetail, this.field_74247_c), this.width - 50);
/*     */     }
/*     */     else {
/*     */       
/*  49 */       this.field_74245_d = this.fontRenderer.listFormattedStringToWidth(I18n.getString(this.errorDetail), this.width - 50);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  58 */     if (par1GuiButton.id == 0) {
/*     */       
/*  60 */       Minecraft.soonest_reconnection_time = 0L;
/*  61 */       this.mc.displayGuiScreen(this.field_98095_n);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  70 */     drawDefaultBackground();
/*  71 */     drawCenteredString(this.fontRenderer, this.errorMessage, this.width / 2, this.height / 2 - 50, 11184810);
/*  72 */     int var4 = this.height / 2 - 30;
/*     */     
/*  74 */     if (Minecraft.soonest_reconnection_time > 0L) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 118 */       drawCenteredString(this.fontRenderer, Translator.get("disconnect.disconnectionPenalty"), this.width / 2, var4, 16777215);
/*     */       
/* 120 */       var4 += this.fontRenderer.FONT_HEIGHT * 5;
/*     */       
/* 122 */       String am_pm = World.getHourOfDayAMPM(Minecraft.adjusted_hour_of_disconnection);
/*     */       
/* 124 */       int hour_of_latest_reconnection = World.getHourOfLatestReconnection();
/*     */       
/* 126 */       if (am_pm.equals("NOON")) {
/* 127 */         am_pm = "noon";
/*     */       }
/* 129 */       String msg = "";
/*     */       
/* 131 */       if (Minecraft.adjusted_hour_of_disconnection == hour_of_latest_reconnection) {
/*     */         
/* 133 */         msg = Translator.getFormatted("disconnect.reconnectAt", new Object[] { am_pm });
/* 134 */       } else if (message_type == 1) {
/*     */ 
/*     */         
/* 137 */         msg = Translator.getFormatted("disconnect.reconnectBetween", new Object[] { am_pm, World.getHourOfDayAMPM(hour_of_latest_reconnection) });
/* 138 */       } else if (message_type == 2) {
/*     */ 
/*     */         
/* 141 */         msg = Translator.getFormatted("disconnect.reconnectAtOrBetween", new Object[] { World.getHourOfDayAMPM(hour_of_latest_reconnection), am_pm, World.getHourOfDayAMPM(hour_of_latest_reconnection) });
/*     */       } 
/* 143 */       drawCenteredString(this.fontRenderer, msg + ".", this.width / 2, var4, 11184810);
/*     */     }
/* 145 */     else if (this.field_74245_d != null) {
/*     */       
/* 147 */       for (Iterator<String> var5 = this.field_74245_d.iterator(); var5.hasNext(); var4 += this.fontRenderer.FONT_HEIGHT) {
/*     */         
/* 149 */         String var6 = var5.next();
/* 150 */         drawCenteredString(this.fontRenderer, var6, this.width / 2, var4, 16777215);
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiDisconnected.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */