/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiScreenConfigureWorld
/*     */   extends GuiScreen {
/*     */   private final GuiScreen field_96285_a;
/*     */   private McoServer field_96280_b;
/*     */   private SelectionListInvited field_96282_c;
/*     */   private int field_96277_d;
/*     */   private int field_96286_n;
/*     */   private int field_96287_o;
/*  14 */   private int field_96284_p = -1;
/*     */   
/*     */   private String field_96283_q;
/*     */   private GuiButton field_96281_r;
/*     */   private GuiButton field_96279_s;
/*     */   private GuiButton field_96278_t;
/*     */   private GuiButton field_96276_u;
/*     */   private GuiButton field_98128_v;
/*     */   private GuiButton field_98127_w;
/*     */   private GuiButton field_98129_x;
/*     */   private GuiButton field_110381_z;
/*     */   private boolean field_102020_y;
/*     */   
/*     */   public GuiScreenConfigureWorld(GuiScreen par1GuiScreen, McoServer par2McoServer) {
/*  28 */     this.field_96285_a = par1GuiScreen;
/*  29 */     this.field_96280_b = par2McoServer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  42 */     this.field_96277_d = this.width / 2 - 200;
/*  43 */     this.field_96286_n = 180;
/*  44 */     this.field_96287_o = this.width / 2;
/*  45 */     Keyboard.enableRepeatEvents(true);
/*  46 */     this.buttonList.clear();
/*     */     
/*  48 */     if (this.field_96280_b.field_96404_d.equals("CLOSED")) {
/*     */       
/*  50 */       this.buttonList.add(this.field_96281_r = new GuiButton(0, this.field_96277_d, func_96264_a(12), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.open")));
/*  51 */       this.field_96281_r.enabled = !this.field_96280_b.field_98166_h;
/*     */     }
/*     */     else {
/*     */       
/*  55 */       this.buttonList.add(this.field_96279_s = new GuiButton(1, this.field_96277_d, func_96264_a(12), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.close")));
/*  56 */       this.field_96279_s.enabled = !this.field_96280_b.field_98166_h;
/*     */     } 
/*     */     
/*  59 */     this.buttonList.add(this.field_98129_x = new GuiButton(7, this.field_96277_d + this.field_96286_n / 2 + 2, func_96264_a(12), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.subscription")));
/*  60 */     this.buttonList.add(this.field_96278_t = new GuiButton(5, this.field_96277_d, func_96264_a(10), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.edit")));
/*  61 */     this.buttonList.add(this.field_96276_u = new GuiButton(6, this.field_96277_d + this.field_96286_n / 2 + 2, func_96264_a(10), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.reset")));
/*  62 */     this.buttonList.add(this.field_98128_v = new GuiButton(4, this.field_96287_o, func_96264_a(10), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.invite")));
/*  63 */     this.buttonList.add(this.field_98127_w = new GuiButton(3, this.field_96287_o + this.field_96286_n / 2 + 2, func_96264_a(10), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.uninvite")));
/*  64 */     this.buttonList.add(this.field_110381_z = new GuiButton(8, this.field_96287_o, func_96264_a(12), this.field_96286_n / 2 - 2, 20, I18n.getString("mco.configure.world.buttons.backup")));
/*  65 */     this.buttonList.add(new GuiButton(10, this.field_96287_o + this.field_96286_n / 2 + 2, func_96264_a(12), this.field_96286_n / 2 - 2, 20, I18n.getString("gui.back")));
/*  66 */     this.field_96282_c = new SelectionListInvited(this);
/*  67 */     this.field_96278_t.enabled = !this.field_96280_b.field_98166_h;
/*  68 */     this.field_96276_u.enabled = !this.field_96280_b.field_98166_h;
/*  69 */     this.field_98128_v.enabled = !this.field_96280_b.field_98166_h;
/*  70 */     this.field_98127_w.enabled = !this.field_96280_b.field_98166_h;
/*  71 */     this.field_110381_z.enabled = !this.field_96280_b.field_98166_h;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_96264_a(int par1) {
/*  76 */     return 40 + par1 * 13;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  84 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  92 */     if (par1GuiButton.enabled)
/*     */     {
/*  94 */       if (par1GuiButton.id == 10) {
/*     */         
/*  96 */         if (this.field_102020_y)
/*     */         {
/*  98 */           ((GuiScreenOnlineServers)this.field_96285_a).func_102018_a(this.field_96280_b.field_96408_a);
/*     */         }
/*     */         
/* 101 */         this.mc.displayGuiScreen(this.field_96285_a);
/*     */       }
/* 103 */       else if (par1GuiButton.id == 5) {
/*     */         
/* 105 */         this.mc.displayGuiScreen(new GuiScreenEditOnlineWorld(this, this.field_96285_a, this.field_96280_b));
/*     */       }
/* 107 */       else if (par1GuiButton.id == 1) {
/*     */         
/* 109 */         String var2 = I18n.getString("mco.configure.world.close.question.line1");
/* 110 */         String var3 = I18n.getString("mco.configure.world.close.question.line2");
/* 111 */         this.mc.displayGuiScreen(new GuiScreenConfirmation(this, GuiScreenConfirmationType.Info, var2, var3, 1));
/*     */       }
/* 113 */       else if (par1GuiButton.id == 0) {
/*     */         
/* 115 */         func_96268_g();
/*     */       }
/* 117 */       else if (par1GuiButton.id == 4) {
/*     */         
/* 119 */         this.mc.displayGuiScreen(new GuiScreenInvite(this.field_96285_a, this, this.field_96280_b));
/*     */       }
/* 121 */       else if (par1GuiButton.id == 3) {
/*     */         
/* 123 */         func_96272_i();
/*     */       }
/* 125 */       else if (par1GuiButton.id == 6) {
/*     */         
/* 127 */         this.mc.displayGuiScreen(new GuiScreenResetWorld(this, this.field_96280_b));
/*     */       }
/* 129 */       else if (par1GuiButton.id == 7) {
/*     */         
/* 131 */         this.mc.displayGuiScreen(new GuiScreenSubscription(this, this.field_96280_b));
/*     */       }
/* 133 */       else if (par1GuiButton.id == 8) {
/*     */         
/* 135 */         this.mc.displayGuiScreen(new GuiScreenBackup(this, this.field_96280_b.field_96408_a));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_96268_g() {
/* 142 */     McoClient var1 = new McoClient(this.mc.getSession());
/*     */ 
/*     */     
/*     */     try {
/* 146 */       Boolean var2 = var1.func_96383_b(this.field_96280_b.field_96408_a);
/*     */       
/* 148 */       if (var2.booleanValue())
/*     */       {
/* 150 */         this.field_102020_y = true;
/* 151 */         this.field_96280_b.field_96404_d = "OPEN";
/* 152 */         initGui();
/*     */       }
/*     */     
/* 155 */     } catch (ExceptionMcoService var3) {
/*     */       
/* 157 */       this.mc.getLogAgent().logSevere(var3.toString());
/*     */     }
/* 159 */     catch (IOException var4) {
/*     */       
/* 161 */       this.mc.getLogAgent().logWarning("Realms: could not parse response");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_96275_h() {
/* 167 */     McoClient var1 = new McoClient(this.mc.getSession());
/*     */ 
/*     */     
/*     */     try {
/* 171 */       boolean var2 = var1.func_96378_c(this.field_96280_b.field_96408_a).booleanValue();
/*     */       
/* 173 */       if (var2)
/*     */       {
/* 175 */         this.field_102020_y = true;
/* 176 */         this.field_96280_b.field_96404_d = "CLOSED";
/* 177 */         initGui();
/*     */       }
/*     */     
/* 180 */     } catch (ExceptionMcoService var3) {
/*     */       
/* 182 */       this.mc.getLogAgent().logSevere(var3.toString());
/*     */     }
/* 184 */     catch (IOException var4) {
/*     */       
/* 186 */       this.mc.getLogAgent().logWarning("Realms: could not parse response");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_96272_i() {
/* 192 */     if (this.field_96284_p >= 0 && this.field_96284_p < this.field_96280_b.field_96402_f.size()) {
/*     */       
/* 194 */       this.field_96283_q = this.field_96280_b.field_96402_f.get(this.field_96284_p);
/* 195 */       GuiYesNoMITE var1 = new GuiYesNoMITE(this, "Warning!", I18n.getString("mco.configure.world.uninvite.question") + " '" + this.field_96283_q + "'", 3);
/* 196 */       this.mc.displayGuiScreen(var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean par1, int par2) {
/* 202 */     if (par2 == 3) {
/*     */       
/* 204 */       if (par1) {
/*     */         
/* 206 */         McoClient var3 = new McoClient(this.mc.getSession());
/*     */ 
/*     */         
/*     */         try {
/* 210 */           var3.func_96381_a(this.field_96280_b.field_96408_a, this.field_96283_q);
/*     */         }
/* 212 */         catch (ExceptionMcoService var5) {
/*     */           
/* 214 */           this.mc.getLogAgent().logSevere(var5.toString());
/*     */         } 
/*     */         
/* 217 */         func_96267_d(this.field_96284_p);
/*     */       } 
/*     */       
/* 220 */       this.mc.displayGuiScreen(new GuiScreenConfigureWorld(this.field_96285_a, this.field_96280_b));
/*     */     } 
/*     */     
/* 223 */     if (par2 == 1) {
/*     */       
/* 225 */       if (par1)
/*     */       {
/* 227 */         func_96275_h();
/*     */       }
/*     */       
/* 230 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_96267_d(int par1) {
/* 236 */     this.field_96280_b.field_96402_f.remove(par1);
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
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 249 */     super.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 257 */     drawDefaultBackground();
/* 258 */     this.field_96282_c.func_96612_a(par1, par2, par3);
/* 259 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.configure.world.title"), this.width / 2, 17, 16777215);
/* 260 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.name"), this.field_96277_d, func_96264_a(1), 10526880);
/* 261 */     drawString(this.fontRenderer, this.field_96280_b.func_96398_b(), this.field_96277_d, func_96264_a(2), 16777215);
/* 262 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.description"), this.field_96277_d, func_96264_a(4), 10526880);
/* 263 */     drawString(this.fontRenderer, this.field_96280_b.func_96397_a(), this.field_96277_d, func_96264_a(5), 16777215);
/* 264 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.status"), this.field_96277_d, func_96264_a(7), 10526880);
/* 265 */     drawString(this.fontRenderer, func_104045_j(), this.field_96277_d, func_96264_a(8), 16777215);
/* 266 */     drawString(this.fontRenderer, I18n.getString("mco.configure.world.invited"), this.field_96287_o, func_96264_a(1), 10526880);
/* 267 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_104045_j() {
/* 272 */     if (this.field_96280_b.field_98166_h)
/*     */     {
/* 274 */       return "Expired";
/*     */     }
/*     */ 
/*     */     
/* 278 */     String var1 = this.field_96280_b.field_96404_d.toLowerCase();
/* 279 */     return Character.toUpperCase(var1.charAt(0)) + var1.substring(1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static Minecraft getMinecraft(GuiScreenConfigureWorld par0GuiScreenConfigureWorld) {
/* 285 */     return par0GuiScreenConfigureWorld.mc;
/*     */   }
/*     */ 
/*     */   
/*     */   static int func_96271_b(GuiScreenConfigureWorld par0GuiScreenConfigureWorld) {
/* 290 */     return par0GuiScreenConfigureWorld.field_96287_o;
/*     */   }
/*     */ 
/*     */   
/*     */   static int func_96274_a(GuiScreenConfigureWorld par0GuiScreenConfigureWorld, int par1) {
/* 295 */     return par0GuiScreenConfigureWorld.func_96264_a(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   static int func_96269_c(GuiScreenConfigureWorld par0GuiScreenConfigureWorld) {
/* 300 */     return par0GuiScreenConfigureWorld.field_96286_n;
/*     */   }
/*     */ 
/*     */   
/*     */   static McoServer func_96266_d(GuiScreenConfigureWorld par0GuiScreenConfigureWorld) {
/* 305 */     return par0GuiScreenConfigureWorld.field_96280_b;
/*     */   }
/*     */ 
/*     */   
/*     */   static int func_96270_b(GuiScreenConfigureWorld par0GuiScreenConfigureWorld, int par1) {
/* 310 */     return par0GuiScreenConfigureWorld.field_96284_p = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   static int func_96263_e(GuiScreenConfigureWorld par0GuiScreenConfigureWorld) {
/* 315 */     return par0GuiScreenConfigureWorld.field_96284_p;
/*     */   }
/*     */ 
/*     */   
/*     */   static FontRenderer func_96273_f(GuiScreenConfigureWorld par0GuiScreenConfigureWorld) {
/* 320 */     return par0GuiScreenConfigureWorld.fontRenderer;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenConfigureWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */