/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.Socket;
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class GuiScreenOnlineServers
/*     */   extends GuiScreen
/*     */ {
/*  31 */   private static final ResourceLocation field_130039_a = new ResourceLocation("textures/gui/widgets.png");
/*     */   
/*     */   private GuiScreen field_96188_a;
/*     */   
/*     */   private GuiSlotOnlineServerList field_96186_b;
/*     */   private static int field_96187_c;
/*  37 */   private static final Object field_96185_d = new Object();
/*     */ 
/*     */   
/*  40 */   private long field_96189_n = -1L;
/*     */   
/*     */   private GuiButton field_96190_o;
/*     */   
/*     */   private GuiButton field_96198_p;
/*     */   
/*     */   private GuiButtonLink field_96197_q;
/*     */   private GuiButton field_96196_r;
/*     */   private String field_96195_s;
/*  49 */   private static McoServerList field_96194_t = new McoServerList();
/*     */   private boolean field_96193_u;
/*  51 */   private List field_96192_v = Lists.newArrayList();
/*     */ 
/*     */   
/*  54 */   private volatile int field_96199_x = 0;
/*     */   
/*     */   private Long field_102019_y;
/*     */   
/*     */   private int field_104044_y;
/*     */   
/*     */   public GuiScreenOnlineServers(GuiScreen guiScreen) {
/*  61 */     this.field_96188_a = guiScreen;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  66 */     Keyboard.enableRepeatEvents(true);
/*  67 */     this.buttonList.clear();
/*     */     
/*  69 */     field_96194_t.func_130129_a(this.mc.getSession());
/*  70 */     if (!this.field_96193_u) {
/*  71 */       this.field_96193_u = true;
/*  72 */       this.field_96186_b = new GuiSlotOnlineServerList(this);
/*     */     } else {
/*  74 */       this.field_96186_b.func_104084_a(this.width, this.height, 32, this.height - 64);
/*     */     } 
/*     */     
/*  77 */     func_96178_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96178_g() {
/*  82 */     this.buttonList.add(this.field_96196_r = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.getString("mco.selectServer.play")));
/*  83 */     this.buttonList.add(this.field_96198_p = new GuiButton(2, this.width / 2 - 48, this.height - 52, 100, 20, I18n.getString("mco.selectServer.create")));
/*  84 */     this.buttonList.add(this.field_96190_o = new GuiButton(3, this.width / 2 + 58, this.height - 52, 100, 20, I18n.getString("mco.selectServer.configure")));
/*     */     
/*  86 */     this.buttonList.add(this.field_96197_q = new GuiButtonLink(4, this.width / 2 - 154, this.height - 28, 154, 20, I18n.getString("mco.selectServer.moreinfo")));
/*  87 */     this.buttonList.add(new GuiButton(0, this.width / 2 + 6, this.height - 28, 153, 20, I18n.getString("gui.cancel")));
/*     */     
/*  89 */     McoServer mcoServer = func_140030_b(this.field_96189_n);
/*  90 */     this.field_96196_r.enabled = (mcoServer != null && mcoServer.field_96404_d.equals("OPEN") && !mcoServer.field_98166_h);
/*  91 */     this.field_96198_p.enabled = (this.field_96199_x > 0);
/*     */     
/*  93 */     if (mcoServer != null && 
/*  94 */       !mcoServer.field_96405_e.equals(this.mc.getSession().getUsername())) {
/*  95 */       this.field_96190_o.displayString = I18n.getString("mco.selectServer.leave");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 102 */     super.updateScreen();
/*     */     
/* 104 */     this.field_104044_y++;
/*     */     
/* 106 */     if (field_96194_t.func_130127_a()) {
/* 107 */       List list = field_96194_t.func_98252_c();
/* 108 */       for (McoServer mcoServer : list) {
/* 109 */         for (McoServer mcoServer1 : this.field_96192_v) {
/* 110 */           if (mcoServer.field_96408_a == mcoServer1.field_96408_a) {
/* 111 */             mcoServer.func_96401_a(mcoServer1);
/* 112 */             if (this.field_102019_y != null && this.field_102019_y.longValue() == mcoServer.field_96408_a) {
/* 113 */               this.field_102019_y = null;
/* 114 */               mcoServer.field_96411_l = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 120 */       this.field_96199_x = field_96194_t.func_140056_e();
/* 121 */       this.field_96192_v = list;
/* 122 */       field_96194_t.func_98250_b();
/*     */     } 
/*     */     
/* 125 */     this.field_96198_p.enabled = (this.field_96199_x > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 130 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/* 135 */     if (!guiButton.enabled)
/*     */       return; 
/* 137 */     if (guiButton.id == 1) {
/* 138 */       func_140032_e(this.field_96189_n);
/* 139 */     } else if (guiButton.id == 3) {
/* 140 */       func_140019_s();
/* 141 */     } else if (guiButton.id == 0) {
/* 142 */       field_96194_t.func_98248_d();
/* 143 */       this.mc.displayGuiScreen(this.field_96188_a);
/* 144 */     } else if (guiButton.id == 2) {
/* 145 */       field_96194_t.func_98248_d();
/* 146 */       this.mc.displayGuiScreen(new GuiScreenCreateOnlineWorld(this));
/* 147 */     } else if (guiButton.id == 4) {
/* 148 */       this.field_96197_q.func_96135_a("http://realms.minecraft.net/");
/*     */     } else {
/* 150 */       this.field_96186_b.actionPerformed(guiButton);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_140019_s() {
/* 155 */     McoServer mcoServer = func_140030_b(this.field_96189_n);
/* 156 */     if (mcoServer != null) {
/* 157 */       if (this.mc.getSession().getUsername().equals(mcoServer.field_96405_e)) {
/* 158 */         McoServer mcoServer1 = func_98086_a(mcoServer.field_96408_a);
/* 159 */         if (mcoServer1 != null) {
/* 160 */           field_96194_t.func_98248_d();
/* 161 */           this.mc.displayGuiScreen(new GuiScreenConfigureWorld(this, mcoServer1));
/*     */         } 
/*     */       } else {
/* 164 */         String str1 = I18n.getString("mco.configure.world.leave.question.line1");
/* 165 */         String str2 = I18n.getString("mco.configure.world.leave.question.line2");
/* 166 */         this.mc.displayGuiScreen(new GuiScreenConfirmation(this, GuiScreenConfirmationType.Info, str1, str2, 3));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private McoServer func_140030_b(long l) {
/* 172 */     for (McoServer mcoServer : this.field_96192_v) {
/* 173 */       if (mcoServer.field_96408_a == l) {
/* 174 */         return mcoServer;
/*     */       }
/*     */     } 
/* 177 */     return null;
/*     */   }
/*     */   
/*     */   private int func_140009_c(long l) {
/* 181 */     for (byte b = 0; b < this.field_96192_v.size(); b++) {
/* 182 */       if (((McoServer)this.field_96192_v.get(b)).field_96408_a == l) {
/* 183 */         return b;
/*     */       }
/*     */     } 
/* 186 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean bl, int i) {
/* 191 */     if (i == 3 && bl) {
/* 192 */       (new ThreadOnlineScreen(this)).start();
/*     */     }
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
/* 213 */     this.mc.displayGuiScreen(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_140012_t() {
/* 218 */     int i = func_140009_c(this.field_96189_n);
/*     */     
/* 220 */     if (this.field_96192_v.size() - 1 == i) {
/* 221 */       i--;
/*     */     }
/*     */     
/* 224 */     if (this.field_96192_v.size() == 0) {
/* 225 */       i = -1;
/*     */     }
/*     */     
/* 228 */     if (i >= 0 && i < this.field_96192_v.size()) {
/* 229 */       this.field_96189_n = ((McoServer)this.field_96192_v.get(i)).field_96408_a;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_102018_a(long l) {
/* 235 */     this.field_96189_n = -1L;
/* 236 */     this.field_102019_y = Long.valueOf(l);
/*     */   }
/*     */   
/*     */   private McoServer func_98086_a(long l) {
/* 240 */     McoClient mcoClient = new McoClient(this.mc.getSession());
/*     */     try {
/* 242 */       return mcoClient.func_98176_a(l);
/* 243 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 244 */       this.mc.getLogAgent().logSevere(exceptionMcoService.toString());
/* 245 */     } catch (IOException iOException) {
/* 246 */       this.mc.getLogAgent().logWarning("Realms: could not parse response");
/*     */     } 
/* 248 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/* 253 */     if (i == 59) {
/* 254 */       this.mc.gameSettings.hideServerAddress = !this.mc.gameSettings.hideServerAddress;
/* 255 */       this.mc.gameSettings.saveOptions(); return;
/*     */     } 
/* 257 */     if (i == 28 || i == 156) {
/* 258 */       actionPerformed(this.buttonList.get(0));
/*     */     } else {
/* 260 */       super.keyTyped(c, i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 266 */     this.field_96195_s = null;
/*     */     
/* 268 */     drawDefaultBackground();
/*     */     
/* 270 */     this.field_96186_b.drawScreen(i, j, f);
/* 271 */     drawCenteredString(this.fontRenderer, I18n.getString("mco.title"), this.width / 2, 20, 16777215);
/*     */     
/* 273 */     super.drawScreen(i, j, f);
/* 274 */     if (this.field_96195_s != null) {
/* 275 */       func_96165_a(this.field_96195_s, i, j);
/*     */     }
/*     */     
/* 278 */     func_130038_b(i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/* 283 */     super.mouseClicked(i, j, k);
/*     */     
/* 285 */     if (func_130037_c(i, j) && field_96194_t.func_130124_d() != 0) {
/* 286 */       GuiScreenPendingInvitation guiScreenPendingInvitation = new GuiScreenPendingInvitation(this);
/* 287 */       this.mc.displayGuiScreen(guiScreenPendingInvitation);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_130038_b(int i, int j) {
/* 294 */     int k = field_96194_t.func_130124_d();
/* 295 */     boolean bool = func_130037_c(i, j);
/*     */ 
/*     */     
/* 298 */     this.mc.getTextureManager().bindTexture(field_130039_a);
/* 299 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 300 */     GL11.glPushMatrix();
/* 301 */     drawTexturedModalRect(this.width / 2 + 58, 15, bool ? 166 : 182, 22, 16, 16);
/* 302 */     GL11.glPopMatrix();
/*     */ 
/*     */     
/* 305 */     if (k != 0) {
/* 306 */       int m = 198 + (Math.min(k, 6) - 1) * 8;
/*     */       
/* 308 */       int n = (int)(Math.max(0.0F, Math.max(MathHelper.sin((10 + this.field_104044_y) * 0.57F), MathHelper.cos(this.field_104044_y * 0.35F))) * -6.0F);
/*     */       
/* 310 */       this.mc.getTextureManager().bindTexture(field_130039_a);
/* 311 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 312 */       GL11.glPushMatrix();
/* 313 */       drawTexturedModalRect(this.width / 2 + 58 + 4, 19 + n, m, 22, 8, 8);
/* 314 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 317 */     if (bool && k != 0) {
/* 318 */       int m = i + 12;
/* 319 */       int n = j - 12;
/* 320 */       String str = I18n.getString("mco.invites.pending");
/* 321 */       int i1 = this.fontRenderer.getStringWidth(str);
/* 322 */       drawGradientRect(m - 3, n - 3, m + i1 + 3, n + 8 + 3, -1073741824, -1073741824);
/*     */       
/* 324 */       this.fontRenderer.drawStringWithShadow(str, m, n, -1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean func_130037_c(int i, int j) {
/* 329 */     int k = this.width / 2 + 56;
/* 330 */     int m = this.width / 2 + 78;
/* 331 */     byte b1 = 13;
/* 332 */     byte b2 = 27;
/*     */     
/* 334 */     return (k <= i && i <= m && b1 <= j && j <= b2);
/*     */   }
/*     */   
/*     */   private void func_140032_e(long l) {
/* 338 */     McoServer mcoServer = func_140030_b(l);
/* 339 */     if (mcoServer != null) {
/* 340 */       field_96194_t.func_98248_d();
/* 341 */       GuiScreenLongRunningTask guiScreenLongRunningTask = new GuiScreenLongRunningTask(this.mc, this, new TaskOnlineConnect(this, mcoServer));
/* 342 */       guiScreenLongRunningTask.func_98117_g();
/* 343 */       this.mc.displayGuiScreen(guiScreenLongRunningTask);
/*     */     } 
/*     */   }
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
/*     */   private void func_101008_c(int i, int j, int k, int l) {
/* 486 */     this.mc.getTextureManager().bindTexture(field_130039_a);
/* 487 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 488 */     GL11.glPushMatrix();
/* 489 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 490 */     drawTexturedModalRect(i * 2, j * 2, 191, 0, 16, 15);
/* 491 */     GL11.glPopMatrix();
/* 492 */     if (k >= i && k <= i + 9 && l >= j && l <= j + 9) this.field_96195_s = I18n.getString("mco.selectServer.expired"); 
/*     */   }
/*     */   
/*     */   private void func_104039_b(int i, int j, int k, int l, int m) {
/* 496 */     if (this.field_104044_y % 20 < 10) {
/* 497 */       this.mc.getTextureManager().bindTexture(field_130039_a);
/* 498 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 499 */       GL11.glPushMatrix();
/* 500 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 501 */       drawTexturedModalRect(i * 2, j * 2, 207, 0, 16, 15);
/* 502 */       GL11.glPopMatrix();
/*     */     } 
/* 504 */     if (k >= i && k <= i + 9 && l >= j && l <= j + 9) {
/* 505 */       if (m == 0) {
/* 506 */         this.field_96195_s = I18n.getString("mco.selectServer.expires.soon");
/* 507 */       } else if (m == 1) {
/* 508 */         this.field_96195_s = I18n.getString("mco.selectServer.expires.day");
/*     */       } else {
/* 510 */         this.field_96195_s = I18n.getStringParams("mco.selectServer.expires.days", new Object[] { Integer.valueOf(m) });
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void func_101006_d(int i, int j, int k, int l) {
/* 516 */     this.mc.getTextureManager().bindTexture(field_130039_a);
/* 517 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 518 */     GL11.glPushMatrix();
/* 519 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 520 */     drawTexturedModalRect(i * 2, j * 2, 207, 0, 16, 15);
/* 521 */     GL11.glPopMatrix();
/* 522 */     if (k >= i && k <= i + 9 && l >= j && l <= j + 9) this.field_96195_s = I18n.getString("mco.selectServer.open"); 
/*     */   }
/*     */   
/*     */   private void func_101001_e(int i, int j, int k, int l) {
/* 526 */     this.mc.getTextureManager().bindTexture(field_130039_a);
/* 527 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 528 */     GL11.glPushMatrix();
/* 529 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 530 */     drawTexturedModalRect(i * 2, j * 2, 223, 0, 16, 15);
/* 531 */     GL11.glPopMatrix();
/* 532 */     if (k >= i && k <= i + 9 && l >= j && l <= j + 9) this.field_96195_s = I18n.getString("mco.selectServer.closed"); 
/*     */   }
/*     */   
/*     */   protected void func_96165_a(String string, int i, int j) {
/* 536 */     if (string == null) {
/*     */       return;
/*     */     }
/*     */     
/* 540 */     int k = i + 12;
/* 541 */     int m = j - 12;
/* 542 */     int n = this.fontRenderer.getStringWidth(string);
/* 543 */     drawGradientRect(k - 3, m - 3, k + n + 3, m + 8 + 3, -1073741824, -1073741824);
/*     */     
/* 545 */     this.fontRenderer.drawStringWithShadow(string, k, m, -1);
/*     */   }
/*     */   
/*     */   private void func_96174_a(McoServer mcoServer) {
/* 549 */     if (mcoServer.field_96414_k.equals(""))
/* 550 */       mcoServer.field_96414_k = EnumChatFormatting.GRAY + "" + Character.MIN_VALUE; 
/* 551 */     mcoServer.field_96415_h = 78;
/*     */     
/* 553 */     ServerAddress serverAddress = ServerAddress.func_78860_a(mcoServer.field_96403_g);
/* 554 */     Socket socket = null;
/* 555 */     DataInputStream dataInputStream = null;
/* 556 */     DataOutputStream dataOutputStream = null;
/*     */     
/*     */     try {
/* 559 */       socket = new Socket();
/* 560 */       socket.setSoTimeout(3000);
/* 561 */       socket.setTcpNoDelay(true);
/* 562 */       socket.setTrafficClass(18);
/* 563 */       socket.connect(new InetSocketAddress(serverAddress.getIP(), serverAddress.getPort()), 3000);
/* 564 */       dataInputStream = new DataInputStream(socket.getInputStream());
/* 565 */       dataOutputStream = new DataOutputStream(socket.getOutputStream());
/* 566 */       dataOutputStream.write(254);
/* 567 */       dataOutputStream.write(1);
/* 568 */       if (dataInputStream.read() != 255) throw new IOException("Bad message"); 
/* 569 */       String str = Packet.readString(dataInputStream, 256);
/* 570 */       char[] arrayOfChar = str.toCharArray();
/* 571 */       for (byte b = 0; b < arrayOfChar.length; b++) {
/* 572 */         if (arrayOfChar[b] != '§' && arrayOfChar[b] != '\000' && ChatAllowedCharacters.allowedCharacters.indexOf(arrayOfChar[b]) < 0) {
/* 573 */           arrayOfChar[b] = '?';
/*     */         }
/*     */       } 
/* 576 */       str = new String(arrayOfChar);
/*     */       
/* 578 */       if (str.startsWith("§") && str.length() > 1) {
/* 579 */         String[] arrayOfString = str.substring(1).split("\000");
/*     */         
/* 581 */         if (MathHelper.parseIntWithDefault(arrayOfString[0], 0) == 1) {
/* 582 */           mcoServer.field_96415_h = MathHelper.parseIntWithDefault(arrayOfString[1], mcoServer.field_96415_h);
/*     */           
/* 584 */           int i = MathHelper.parseIntWithDefault(arrayOfString[4], 0);
/* 585 */           int j = MathHelper.parseIntWithDefault(arrayOfString[5], 0);
/* 586 */           if (i >= 0 && j >= 0) {
/* 587 */             mcoServer.field_96414_k = EnumChatFormatting.GRAY + "" + i;
/*     */           } else {
/* 589 */             mcoServer.field_96414_k = "" + EnumChatFormatting.DARK_GRAY + "???";
/*     */           } 
/*     */         } else {
/* 592 */           mcoServer.field_96415_h = 79;
/* 593 */           mcoServer.field_96414_k = "" + EnumChatFormatting.DARK_GRAY + "???";
/*     */         } 
/*     */       } else {
/* 596 */         String[] arrayOfString = str.split("§");
/* 597 */         str = arrayOfString[0];
/* 598 */         int i = -1;
/* 599 */         int j = -1;
/*     */         try {
/* 601 */           i = Integer.parseInt(arrayOfString[1]);
/* 602 */           j = Integer.parseInt(arrayOfString[2]);
/* 603 */         } catch (Exception exception) {}
/*     */ 
/*     */         
/* 606 */         mcoServer.field_96407_c = EnumChatFormatting.GRAY + str;
/* 607 */         if (i >= 0 && j > 0) {
/* 608 */           mcoServer.field_96414_k = EnumChatFormatting.GRAY + "" + i;
/*     */         } else {
/* 610 */           mcoServer.field_96414_k = "" + EnumChatFormatting.DARK_GRAY + "???";
/*     */         } 
/* 612 */         mcoServer.field_96415_h = 77;
/*     */       } 
/*     */     } finally {
/*     */       
/*     */       try {
/* 617 */         if (dataInputStream != null) dataInputStream.close(); 
/* 618 */       } catch (Throwable throwable) {}
/*     */       
/*     */       try {
/* 621 */         if (dataOutputStream != null) dataOutputStream.close(); 
/* 622 */       } catch (Throwable throwable) {}
/*     */       
/*     */       try {
/* 625 */         if (socket != null) socket.close(); 
/* 626 */       } catch (Throwable throwable) {}
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenOnlineServers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */