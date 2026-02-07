/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiScreenSubscription
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen field_98067_a;
/*     */   private final McoServer field_98065_b;
/*  23 */   private final int field_98066_c = 0;
/*  24 */   private final int field_98064_d = 1;
/*     */   
/*     */   private int field_98068_n;
/*     */   private String field_98069_o;
/*     */   
/*     */   public GuiScreenSubscription(GuiScreen guiScreen, McoServer mcoServer) {
/*  30 */     this.field_98067_a = guiScreen;
/*  31 */     this.field_98065_b = mcoServer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {}
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  40 */     func_98063_a(this.field_98065_b.field_96408_a);
/*     */     
/*  42 */     Keyboard.enableRepeatEvents(true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_98063_a(long l) {
/*  52 */     McoClient mcoClient = new McoClient(this.mc.getSession());
/*     */     try {
/*  54 */       ValueObjectSubscription valueObjectSubscription = mcoClient.func_98177_f(l);
/*  55 */       this.field_98068_n = valueObjectSubscription.field_98170_b;
/*  56 */       this.field_98069_o = func_98062_b(valueObjectSubscription.field_98171_a);
/*  57 */     } catch (ExceptionMcoService exceptionMcoService) {
/*  58 */       Minecraft.getMinecraft().getLogAgent().logSevere(exceptionMcoService.toString());
/*  59 */     } catch (IOException iOException) {
/*  60 */       Minecraft.getMinecraft().getLogAgent().logWarning("Realms: could not parse response");
/*     */     } 
/*     */   }
/*     */   
/*     */   private String func_98062_b(long l) {
/*  65 */     GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getDefault());
/*  66 */     gregorianCalendar.setTimeInMillis(l);
/*  67 */     return SimpleDateFormat.getDateTimeInstance().format(gregorianCalendar.getTime());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  72 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  77 */     if (!guiButton.enabled)
/*  78 */       return;  if (guiButton.id == 0) {
/*  79 */       this.mc.displayGuiScreen(this.field_98067_a);
/*  80 */     } else if (guiButton.id == 1) {
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {}
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/*  91 */     super.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/*  97 */     drawDefaultBackground();
/*     */     
/*  99 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.configure.world.subscription.title"), this.width / 2, 17, 16777215);
/* 100 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.subscription.start"), this.width / 2 - 100, 53, 10526880);
/* 101 */     drawString(this.fontRenderer, this.field_98069_o, this.width / 2 - 100, 66, 16777215);
/* 102 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.subscription.daysleft"), this.width / 2 - 100, 85, 10526880);
/* 103 */     drawString(this.fontRenderer, String.valueOf(this.field_98068_n), this.width / 2 - 100, 98, 16777215);
/*     */     
/* 105 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */