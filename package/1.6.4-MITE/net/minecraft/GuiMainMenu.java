/*      */ package net.minecraft;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.net.URI;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Random;
/*      */ import net.minecraft.client.main.Main;
/*      */ import org.apache.commons.io.Charsets;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.util.glu.Project;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GuiMainMenu
/*      */   extends GuiScreen
/*      */ {
/*   21 */   private static final Random rand = new Random();
/*      */ 
/*      */   
/*      */   private float updateCounter;
/*      */ 
/*      */   
/*   27 */   private String splashText = "missingno";
/*      */   
/*      */   private GuiButton buttonResetDemo;
/*      */   
/*      */   private int panoramaTimer;
/*      */   
/*      */   private DynamicTexture viewportTexture;
/*      */   
/*      */   private boolean field_96141_q = true;
/*      */   
/*      */   private static boolean field_96140_r;
/*      */   
/*      */   private static boolean field_96139_s;
/*   40 */   private final Object field_104025_t = new Object();
/*   41 */   private final Object field_104025_t_MITE = new Object();
/*      */   
/*      */   private String field_92025_p;
/*      */   private String field_104024_v;
/*   45 */   public final String field_92025_p_MITE = "MITE Resource Pack 1.6.4 needs to be installed!";
/*   46 */   public final String field_104024_v_MITE = "http://minecraft-is-too-easy.com";
/*      */   
/*   48 */   private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
/*   49 */   private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
/*      */   
/*   51 */   private static final ResourceLocation clickMeTexture = new ResourceLocation("textures/gui/title/click_me_angled.png");
/*      */ 
/*      */ 
/*      */   
/*   55 */   private static final ResourceLocation[] gutenTagTextures = getAnimatedTextures(7, "textures/gui/title/hans_grosse/", false);
/*   56 */   private static final ResourceLocation bulletHoleTexture = new ResourceLocation("textures/gui/title/bullet_hole.png");
/*      */   
/*   58 */   private static final ResourceLocation ice_cream = new ResourceLocation("textures/items/bowls/ice_cream.png");
/*      */ 
/*      */   
/*   61 */   private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] { new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png") };
/*   62 */   public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
/*   63 */   public static final String field_96138_a_MITE = "Visit " + EnumChatFormatting.UNDERLINE + "minecraft-is-too-easy.com" + EnumChatFormatting.RESET + " for more information.";
/*      */   
/*      */   private int field_92024_r;
/*      */   
/*      */   private int field_92023_s;
/*      */   
/*      */   private int field_92022_t;
/*      */   
/*      */   private int field_92021_u;
/*      */   
/*      */   private int field_92020_v;
/*      */   private int field_92019_w;
/*      */   private ResourceLocation field_110351_G;
/*      */   private GuiButton minecraftRealmsButton;
/*      */   private int field_92024_r_MITE;
/*      */   private int field_92023_s_MITE;
/*      */   private int field_92022_t_MITE;
/*      */   private int field_92021_u_MITE;
/*      */   private int field_92020_v_MITE;
/*      */   private int field_92019_w_MITE;
/*      */   private ResourceLocation field_110351_G_MITE;
/*      */   private EnumSpecialSplash enum_special_splash;
/*   85 */   private final String ronin_pawn_url = "https://www.youtube.com/watch?v=UaSVsuklHjA";
/*   86 */   private final String mite_migos_url = "http://www.minecraftforum.net/forums/servers/pc-servers/survival-servers/2383945-mite-migos-fan-server-minecraft-is-too-easy";
/*   87 */   private final String cogmind_url = "http://www.gridsagegames.com/cogmind/";
/*   88 */   private final String ludwig_url = "http://imgur.com/a/YAzpR";
/*      */   
/*      */   private int animated_texture_index;
/*   91 */   private long next_animated_texture_ms = System.currentTimeMillis();
/*      */   
/*      */   private boolean gunshot_sound_preloaded;
/*      */   
/*   95 */   private final int max_bullet_holes = 16;
/*   96 */   private int[] bullet_hole_x = new int[16];
/*   97 */   private int[] bullet_hole_y = new int[16];
/*   98 */   private float[] bullet_hole_rotation = new float[16];
/*   99 */   private long[] bullet_hole_created_ms = new long[16];
/*      */ 
/*      */   
/*      */   private int minimum_firing_loops;
/*      */ 
/*      */ 
/*      */   
/*      */   public GuiMainMenu() {
/*  107 */     BufferedReader var1 = null;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  112 */       ArrayList<String> var2 = new ArrayList();
/*  113 */       var1 = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8));
/*      */       String var3;
/*  115 */       while ((var3 = var1.readLine()) != null) {
/*      */         
/*  117 */         var3 = var3.trim();
/*      */         
/*  119 */         if (!var3.isEmpty())
/*      */         {
/*  121 */           var2.add(var3);
/*      */         }
/*      */       } 
/*      */       
/*  125 */       Random random = new Random(System.currentTimeMillis() / 1000L / 60L);
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/*  130 */         this.splashText = var2.get(random.nextInt(var2.size()));
/*      */       }
/*  132 */       while (this.splashText.hashCode() == 125780783);
/*      */     }
/*  134 */     catch (IOException var12) {
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  140 */       if (var1 != null) {
/*      */         
/*      */         try {
/*      */           
/*  144 */           var1.close();
/*      */         }
/*  146 */         catch (IOException var11) {}
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  153 */     this.updateCounter = rand.nextFloat();
/*  154 */     this.field_92025_p = "";
/*      */     
/*  156 */     String var14 = System.getProperty("os_architecture");
/*  157 */     String str1 = System.getProperty("java_version");
/*      */     
/*  159 */     if ("ppc".equalsIgnoreCase(var14)) {
/*      */       
/*  161 */       this.field_92025_p = "" + EnumChatFormatting.BOLD + "Notice!" + EnumChatFormatting.RESET + " PowerPC compatibility will be dropped in Minecraft 1.6";
/*  162 */       this.field_104024_v = "http://tinyurl.com/javappc";
/*      */     }
/*  164 */     else if (str1 != null && str1.startsWith("1.5")) {
/*      */       
/*  166 */       this.field_92025_p = "" + EnumChatFormatting.BOLD + "Notice!" + EnumChatFormatting.RESET + " Java 1.5 compatibility will be dropped in Minecraft 1.6";
/*  167 */       this.field_104024_v = "http://tinyurl.com/javappc";
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateScreen() {
/*  176 */     this.panoramaTimer++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean doesGuiPauseGame() {
/*  184 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyTyped(char par1, int par2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initGui() {
/*  197 */     this.viewportTexture = new DynamicTexture(256, 256);
/*  198 */     this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
/*  199 */     Calendar var1 = Calendar.getInstance();
/*  200 */     var1.setTime(new Date());
/*      */     
/*  202 */     if (var1.get(2) + 1 == 11 && var1.get(5) == 9) {
/*      */       
/*  204 */       this.splashText = "Happy birthday, ez!";
/*      */     }
/*  206 */     else if (var1.get(2) + 1 == 6 && var1.get(5) == 1) {
/*      */       
/*  208 */       this.splashText = "Happy birthday, Notch!";
/*      */     }
/*  210 */     else if (var1.get(2) + 1 == 12 && var1.get(5) == 24) {
/*      */       
/*  212 */       this.splashText = "Merry X-mas!";
/*      */     }
/*  214 */     else if (var1.get(2) + 1 == 1 && var1.get(5) == 1) {
/*      */       
/*  216 */       this.splashText = "Happy new year!";
/*      */     }
/*  218 */     else if (var1.get(2) + 1 == 10 && var1.get(5) == 31) {
/*      */       
/*  220 */       this.splashText = "OOoooOOOoooo! Spooky!";
/*      */     } 
/*      */     
/*  223 */     boolean var2 = true;
/*  224 */     int var3 = this.height / 4 + 48;
/*      */     
/*  226 */     if (this.mc.isDemo()) {
/*      */       
/*  228 */       addDemoButtons(var3, 24);
/*      */     }
/*      */     else {
/*      */       
/*  232 */       addSingleplayerMultiplayerButtons(var3, 24);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  237 */     GuiButton button_options = new GuiButton(0, this.width / 2 - 100, var3 + 72 + 12, 98, 20, I18n.getString("menu.options"));
/*      */     
/*  239 */     if (Main.is_MITE_DS) {
/*  240 */       button_options.enabled = false;
/*      */     }
/*  242 */     this.buttonList.add(button_options);
/*      */ 
/*      */ 
/*      */     
/*  246 */     func_130020_g();
/*      */     
/*  248 */     this.buttonList.add(new GuiButton(4, this.width / 2 + 2, var3 + 72 + 12, 98, 20, I18n.getString("menu.quit")));
/*  249 */     this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, var3 + 72 + 12));
/*      */     
/*  251 */     if (Minecraft.MITE_resource_pack != null) {
/*  252 */       this.buttonList.add(new GuiButtonForum(6, this.width / 2 + 124 - 20, var3 + 72 + 12));
/*      */     }
/*  254 */     Object var4 = this.field_104025_t;
/*      */     
/*  256 */     synchronized (this.field_104025_t) {
/*      */       
/*  258 */       this.field_92023_s = this.fontRenderer.getStringWidth(this.field_92025_p);
/*  259 */       this.field_92024_r = this.fontRenderer.getStringWidth(field_96138_a);
/*  260 */       int var5 = Math.max(this.field_92023_s, this.field_92024_r);
/*  261 */       this.field_92022_t = (this.width - var5) / 2;
/*  262 */       this.field_92021_u = ((GuiButton)this.buttonList.get(0)).yPosition - 24;
/*  263 */       this.field_92020_v = this.field_92022_t + var5;
/*  264 */       this.field_92019_w = this.field_92021_u + 24;
/*      */     } 
/*      */     
/*  267 */     synchronized (this.field_104025_t_MITE) {
/*      */       
/*  269 */       getClass(); this.field_92023_s_MITE = this.fontRenderer.getStringWidth("MITE Resource Pack 1.6.4 needs to be installed!");
/*  270 */       this.field_92024_r_MITE = this.fontRenderer.getStringWidth(field_96138_a_MITE);
/*  271 */       int var5 = Math.max(this.field_92023_s_MITE, this.field_92024_r_MITE) + 4;
/*  272 */       this.field_92022_t_MITE = (this.width - var5) / 2;
/*  273 */       this.field_92021_u_MITE = ((GuiButton)this.buttonList.get(0)).yPosition - 24 + 78;
/*  274 */       this.field_92020_v_MITE = this.field_92022_t_MITE + var5;
/*  275 */       this.field_92019_w_MITE = this.field_92021_u_MITE + 24;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_130020_g() {
/*  281 */     if (this.field_96141_q)
/*      */     {
/*  283 */       if (!field_96140_r) {
/*      */         
/*  285 */         field_96140_r = true;
/*  286 */         (new RunnableTitleScreen(this)).start();
/*      */       }
/*  288 */       else if (field_96139_s) {
/*      */         
/*  290 */         func_130022_h();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_130022_h() {
/*  298 */     this.minecraftRealmsButton.drawButton = !Main.is_MITE_DS;
/*      */     
/*  300 */     if (Minecraft.MITE_resource_pack == null) {
/*  301 */       this.minecraftRealmsButton.drawButton = false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addSingleplayerMultiplayerButtons(int par1, int par2) {
/*  309 */     GuiButton button_singleplayer = new GuiButton(1, this.width / 2 - 100, par1, I18n.getString("menu.singleplayer" + (Main.is_MITE_DS ? "DS" : "")));
/*  310 */     GuiButton button_multiplayer = new GuiButton(2, this.width / 2 - 100, par1 + par2 * 1, I18n.getString("menu.multiplayer"));
/*      */     
/*  312 */     if (Main.is_MITE_DS) {
/*      */       
/*  314 */       button_singleplayer.yPosition = (button_singleplayer.yPosition + button_multiplayer.yPosition) / 2;
/*      */       
/*  316 */       button_multiplayer.enabled = false;
/*  317 */       button_multiplayer.drawButton = false;
/*      */     } 
/*      */     
/*  320 */     if (Minecraft.MITE_resource_pack == null) {
/*      */       
/*  322 */       button_singleplayer.enabled = false;
/*  323 */       button_multiplayer.enabled = false;
/*      */     }
/*  325 */     else if (Minecraft.java_version_is_outdated) {
/*      */       
/*  327 */       button_singleplayer.enabled = false;
/*  328 */       button_multiplayer.enabled = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  333 */     this.buttonList.add(button_singleplayer);
/*  334 */     this.buttonList.add(button_multiplayer);
/*  335 */     this.buttonList.add(this.minecraftRealmsButton = new GuiButton(14, this.width / 2 - 100, par1 + par2 * 2, I18n.getString("menu.online")));
/*  336 */     this.minecraftRealmsButton.drawButton = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addDemoButtons(int par1, int par2) {
/*  344 */     this.buttonList.add(new GuiButton(11, this.width / 2 - 100, par1, I18n.getString("menu.playdemo")));
/*  345 */     this.buttonList.add(this.buttonResetDemo = new GuiButton(12, this.width / 2 - 100, par1 + par2 * 1, I18n.getString("menu.resetdemo")));
/*  346 */     ISaveFormat var3 = this.mc.getSaveLoader();
/*  347 */     WorldInfo var4 = var3.getWorldInfo("Demo_World");
/*      */     
/*  349 */     if (var4 == null)
/*      */     {
/*  351 */       this.buttonResetDemo.enabled = false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void actionPerformed(GuiButton par1GuiButton) {
/*  360 */     if (par1GuiButton.id == 0)
/*      */     {
/*  362 */       this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
/*      */     }
/*      */     
/*  365 */     if (par1GuiButton.id == 5)
/*      */     {
/*  367 */       this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
/*      */     }
/*      */     
/*  370 */     if (par1GuiButton.id == 1)
/*      */     {
/*  372 */       this.mc.displayGuiScreen(new GuiSelectWorld(this));
/*      */     }
/*      */     
/*  375 */     if (par1GuiButton.id == 2)
/*      */     {
/*  377 */       this.mc.displayGuiScreen(new GuiMultiplayer(this));
/*      */     }
/*      */     
/*  380 */     if (par1GuiButton.id == 14 && this.minecraftRealmsButton.drawButton)
/*      */     {
/*  382 */       func_140005_i();
/*      */     }
/*      */     
/*  385 */     if (par1GuiButton.id == 4)
/*      */     {
/*  387 */       this.mc.shutdown();
/*      */     }
/*      */     
/*  390 */     if (par1GuiButton.id == 6) {
/*      */       
/*      */       try {
/*      */         
/*  394 */         Class<?> var3 = Class.forName("java.awt.Desktop");
/*  395 */         Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/*  396 */         var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1294284-minecraft-is-too-easy-mite-mod") });
/*      */       }
/*  398 */       catch (Throwable var5) {
/*      */         
/*  400 */         var5.printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  405 */     if (par1GuiButton.id == 11)
/*      */     {
/*  407 */       this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
/*      */     }
/*      */     
/*  410 */     if (par1GuiButton.id == 12) {
/*      */       
/*  412 */       ISaveFormat var2 = this.mc.getSaveLoader();
/*  413 */       WorldInfo var3 = var2.getWorldInfo("Demo_World");
/*      */       
/*  415 */       if (var3 != null) {
/*      */         
/*  417 */         GuiYesNoMITE var4 = GuiSelectWorld.getDeleteWorldScreen(this, var3.getWorldName(), 12);
/*  418 */         this.mc.displayGuiScreen(var4);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_140005_i() {
/*  425 */     McoClient var1 = new McoClient(this.mc.getSession());
/*      */ 
/*      */     
/*      */     try {
/*  429 */       if (var1.func_140054_c().booleanValue())
/*      */       {
/*  431 */         this.mc.displayGuiScreen(new GuiScreenClientOutdated(this));
/*      */       }
/*      */       else
/*      */       {
/*  435 */         this.mc.displayGuiScreen(new GuiScreenOnlineServers(this));
/*      */       }
/*      */     
/*  438 */     } catch (ExceptionMcoService var3) {
/*      */       
/*  440 */       this.mc.getLogAgent().logSevere(var3.toString());
/*      */     }
/*  442 */     catch (IOException var4) {
/*      */       
/*  444 */       this.mc.getLogAgent().logSevere(var4.getLocalizedMessage());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void confirmClicked(boolean par1, int par2) {
/*  450 */     if (par1 && par2 == 12) {
/*      */       
/*  452 */       ISaveFormat var6 = this.mc.getSaveLoader();
/*  453 */       var6.flushCache();
/*  454 */       var6.deleteWorldDirectory("Demo_World");
/*  455 */       this.mc.displayGuiScreen(this);
/*      */     }
/*  457 */     else if (par2 == 13) {
/*      */       
/*  459 */       if (par1) {
/*      */         
/*      */         try {
/*      */           
/*  463 */           Class<?> var3 = Class.forName("java.awt.Desktop");
/*  464 */           Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/*  465 */           var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI(this.field_104024_v) });
/*      */         }
/*  467 */         catch (Throwable var5) {
/*      */           
/*  469 */           var5.printStackTrace();
/*      */         } 
/*      */       }
/*      */       
/*  473 */       this.mc.displayGuiScreen(this);
/*      */     }
/*  475 */     else if (par2 == 14) {
/*      */       
/*  477 */       if (par1) {
/*      */         
/*      */         try {
/*      */           
/*  481 */           Class<?> var3 = Class.forName("java.awt.Desktop");
/*  482 */           Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/*  483 */           getClass(); var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI("http://minecraft-is-too-easy.com") });
/*      */         }
/*  485 */         catch (Throwable var5) {
/*      */           
/*  487 */           var5.printStackTrace();
/*      */         } 
/*      */       }
/*      */       
/*  491 */       this.mc.displayGuiScreen(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  565 */     else if (MathHelper.isInRange(par2, 15, 15 + (EnumSpecialSplash.values()).length - 1)) {
/*      */       
/*  567 */       if (par1) {
/*      */         
/*      */         try {
/*      */           
/*  571 */           Class<?> var3 = Class.forName("java.awt.Desktop");
/*  572 */           Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/*  573 */           var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI(EnumSpecialSplash.values()[par2 - 15].getURL()) });
/*      */         }
/*  575 */         catch (Throwable var5) {
/*      */           
/*  577 */           var5.printStackTrace();
/*      */         } 
/*      */       }
/*      */       
/*  581 */       this.mc.displayGuiScreen(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawPanorama(int par1, int par2, float par3) {
/*  590 */     Tessellator var4 = Tessellator.instance;
/*  591 */     GL11.glMatrixMode(5889);
/*  592 */     GL11.glPushMatrix();
/*  593 */     GL11.glLoadIdentity();
/*  594 */     Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
/*  595 */     GL11.glMatrixMode(5888);
/*  596 */     GL11.glPushMatrix();
/*  597 */     GL11.glLoadIdentity();
/*  598 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  599 */     GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*  600 */     GL11.glEnable(3042);
/*  601 */     GL11.glDisable(3008);
/*  602 */     GL11.glDisable(2884);
/*  603 */     GL11.glDepthMask(false);
/*  604 */     GL11.glBlendFunc(770, 771);
/*  605 */     byte var5 = 8;
/*      */     
/*  607 */     for (int var6 = 0; var6 < var5 * var5; var6++) {
/*      */       
/*  609 */       GL11.glPushMatrix();
/*  610 */       float var7 = ((var6 % var5) / var5 - 0.5F) / 64.0F;
/*  611 */       float var8 = ((var6 / var5) / var5 - 0.5F) / 64.0F;
/*  612 */       float var9 = 0.0F;
/*  613 */       GL11.glTranslatef(var7, var8, var9);
/*  614 */       GL11.glRotatef(MathHelper.sin((this.panoramaTimer + par3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
/*  615 */       GL11.glRotatef(-(this.panoramaTimer + par3) * 0.1F, 0.0F, 1.0F, 0.0F);
/*      */       
/*  617 */       for (int var10 = 0; var10 < 6; var10++) {
/*      */         
/*  619 */         GL11.glPushMatrix();
/*      */         
/*  621 */         if (var10 == 1)
/*      */         {
/*  623 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*      */         }
/*      */         
/*  626 */         if (var10 == 2)
/*      */         {
/*  628 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*      */         }
/*      */         
/*  631 */         if (var10 == 3)
/*      */         {
/*  633 */           GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*      */         }
/*      */         
/*  636 */         if (var10 == 4)
/*      */         {
/*  638 */           GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/*  641 */         if (var10 == 5)
/*      */         {
/*  643 */           GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/*  646 */         this.mc.getTextureManager().bindTexture(titlePanoramaPaths[var10]);
/*  647 */         var4.startDrawingQuads();
/*  648 */         var4.setColorRGBA_I(16777215, 255 / (var6 + 1));
/*  649 */         float var11 = 0.0F;
/*  650 */         var4.addVertexWithUV(-1.0D, -1.0D, 1.0D, (0.0F + var11), (0.0F + var11));
/*  651 */         var4.addVertexWithUV(1.0D, -1.0D, 1.0D, (1.0F - var11), (0.0F + var11));
/*  652 */         var4.addVertexWithUV(1.0D, 1.0D, 1.0D, (1.0F - var11), (1.0F - var11));
/*  653 */         var4.addVertexWithUV(-1.0D, 1.0D, 1.0D, (0.0F + var11), (1.0F - var11));
/*  654 */         var4.draw();
/*  655 */         GL11.glPopMatrix();
/*      */       } 
/*      */       
/*  658 */       GL11.glPopMatrix();
/*  659 */       GL11.glColorMask(true, true, true, false);
/*      */     } 
/*      */     
/*  662 */     var4.setTranslation(0.0D, 0.0D, 0.0D);
/*  663 */     GL11.glColorMask(true, true, true, true);
/*  664 */     GL11.glMatrixMode(5889);
/*  665 */     GL11.glPopMatrix();
/*  666 */     GL11.glMatrixMode(5888);
/*  667 */     GL11.glPopMatrix();
/*  668 */     GL11.glDepthMask(true);
/*  669 */     GL11.glEnable(2884);
/*  670 */     GL11.glEnable(3008);
/*  671 */     GL11.glEnable(2929);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void rotateAndBlurSkybox(float par1) {
/*  679 */     this.mc.getTextureManager().bindTexture(this.field_110351_G);
/*  680 */     GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
/*  681 */     GL11.glEnable(3042);
/*  682 */     GL11.glBlendFunc(770, 771);
/*  683 */     GL11.glColorMask(true, true, true, false);
/*  684 */     Tessellator var2 = Tessellator.instance;
/*  685 */     var2.startDrawingQuads();
/*  686 */     byte var3 = 3;
/*      */     
/*  688 */     for (int var4 = 0; var4 < var3; var4++) {
/*      */       
/*  690 */       var2.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (var4 + 1));
/*  691 */       int var5 = this.width;
/*  692 */       int var6 = this.height;
/*  693 */       float var7 = (var4 - var3 / 2) / 256.0F;
/*  694 */       var2.addVertexWithUV(var5, var6, this.zLevel, (0.0F + var7), 0.0D);
/*  695 */       var2.addVertexWithUV(var5, 0.0D, this.zLevel, (1.0F + var7), 0.0D);
/*  696 */       var2.addVertexWithUV(0.0D, 0.0D, this.zLevel, (1.0F + var7), 1.0D);
/*  697 */       var2.addVertexWithUV(0.0D, var6, this.zLevel, (0.0F + var7), 1.0D);
/*      */     } 
/*      */     
/*  700 */     var2.draw();
/*  701 */     GL11.glColorMask(true, true, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderSkybox(int par1, int par2, float par3) {
/*  709 */     GL11.glViewport(0, 0, 256, 256);
/*  710 */     drawPanorama(par1, par2, par3);
/*  711 */     GL11.glDisable(3553);
/*  712 */     GL11.glEnable(3553);
/*  713 */     rotateAndBlurSkybox(par3);
/*  714 */     rotateAndBlurSkybox(par3);
/*  715 */     rotateAndBlurSkybox(par3);
/*  716 */     rotateAndBlurSkybox(par3);
/*  717 */     rotateAndBlurSkybox(par3);
/*  718 */     rotateAndBlurSkybox(par3);
/*  719 */     rotateAndBlurSkybox(par3);
/*  720 */     rotateAndBlurSkybox(par3);
/*  721 */     GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/*  722 */     Tessellator var4 = Tessellator.instance;
/*  723 */     var4.startDrawingQuads();
/*  724 */     float var5 = (this.width > this.height) ? (120.0F / this.width) : (120.0F / this.height);
/*  725 */     float var6 = this.height * var5 / 256.0F;
/*  726 */     float var7 = this.width * var5 / 256.0F;
/*  727 */     GL11.glTexParameteri(3553, 10241, 9729);
/*  728 */     GL11.glTexParameteri(3553, 10240, 9729);
/*  729 */     var4.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
/*  730 */     int var8 = this.width;
/*  731 */     int var9 = this.height;
/*  732 */     var4.addVertexWithUV(0.0D, var9, this.zLevel, (0.5F - var6), (0.5F + var7));
/*  733 */     var4.addVertexWithUV(var8, var9, this.zLevel, (0.5F - var6), (0.5F - var7));
/*  734 */     var4.addVertexWithUV(var8, 0.0D, this.zLevel, (0.5F + var6), (0.5F - var7));
/*  735 */     var4.addVertexWithUV(0.0D, 0.0D, this.zLevel, (0.5F + var6), (0.5F + var7));
/*  736 */     var4.draw();
/*      */   }
/*      */ 
/*      */   
/*      */   private String getModifiedSplashText() {
/*  741 */     if (this.enum_special_splash == EnumSpecialSplash.ronin_pawn)
/*  742 */       return "Ronin        Pawn!"; 
/*  743 */     if (this.enum_special_splash == EnumSpecialSplash.mite_migos)
/*  744 */       return ""; 
/*  745 */     if (this.enum_special_splash == EnumSpecialSplash.guten_tag) {
/*  746 */       return "Guten        Tag!";
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  751 */     if (this.splashText.equals("Attackington's Aspect!")) {
/*      */       
/*  753 */       Calendar var1 = Calendar.getInstance();
/*  754 */       var1.setTime(new Date());
/*      */       
/*  756 */       int ran = var1.get(5) % 2;
/*      */       
/*  758 */       if (ran == 0) {
/*  759 */         return "Attackington's Aspect!";
/*      */       }
/*  761 */       return "Withstandington's Aspect!";
/*      */     } 
/*      */     
/*  764 */     return this.splashText;
/*      */   }
/*      */ 
/*      */   
/*      */   private void addBulletHole() {
/*  769 */     for (int i = 0; i < 16; ) {
/*      */       
/*  771 */       if (this.bullet_hole_created_ms[i] != 0L) {
/*      */         i++; continue;
/*      */       } 
/*  774 */       this.bullet_hole_x[i] = rand.nextInt(this.width);
/*  775 */       this.bullet_hole_y[i] = rand.nextInt(this.height);
/*      */       
/*  777 */       for (int j = 0; j < 16; j++) {
/*      */         
/*  779 */         if (j != i && this.bullet_hole_created_ms[j] != 0L) {
/*      */ 
/*      */           
/*  782 */           int delta_x = this.bullet_hole_x[j] - this.bullet_hole_x[i];
/*  783 */           int delta_y = this.bullet_hole_y[j] - this.bullet_hole_y[i];
/*      */           
/*  785 */           if (delta_x * delta_x + delta_y * delta_y < 512)
/*      */             return; 
/*      */         } 
/*      */       } 
/*  789 */       this.bullet_hole_rotation[i] = rand.nextFloat() * 360.0F;
/*      */       
/*  791 */       this.bullet_hole_created_ms[i] = System.currentTimeMillis();
/*      */       
/*  793 */       this.mc.sndManager.playSoundFX("random.glass", 1.0F, rand.nextFloat() * 0.2F + 0.8F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void fireBullet() {
/*  801 */     this.mc.sndManager.playSoundFX("imported.random.gunshot", 0.1F, 0.5F);
/*      */     
/*  803 */     if (rand.nextInt(8) == 0) {
/*  804 */       addBulletHole();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void makeHeavyStepSound() {}
/*      */ 
/*      */   
/*      */   private void drawBulletHoles() {
/*  814 */     for (int i = 0; i < 16; i++) {
/*      */       
/*  816 */       if (this.bullet_hole_created_ms[i] != 0L) {
/*      */ 
/*      */         
/*  819 */         float alpha = 5.0F - (float)(System.currentTimeMillis() - this.bullet_hole_created_ms[i]) * 5.0E-4F;
/*      */         
/*  821 */         if (alpha < 0.0F) {
/*      */           
/*  823 */           this.bullet_hole_created_ms[i] = 0L;
/*      */         }
/*      */         else {
/*      */           
/*  827 */           drawBulletHole(this.bullet_hole_x[i], this.bullet_hole_y[i], this.bullet_hole_rotation[i], alpha);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   private void drawBulletHole(int x, int y, float rotation, float alpha) {
/*  833 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/*      */     
/*  835 */     this.mc.getTextureManager().bindTexture(bulletHoleTexture);
/*      */     
/*  837 */     GL11.glTexParameteri(3553, 10241, 9729);
/*  838 */     GL11.glTexParameteri(3553, 10240, 9729);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  846 */     GL11.glAlphaFunc(516, 0.01F);
/*      */     
/*  848 */     int image_actual_width = 288;
/*  849 */     int image_actual_height = 250;
/*      */     
/*  851 */     float image_scaling = 0.2F;
/*      */     
/*  853 */     int image_scaled_width = (int)(image_actual_width * image_scaling);
/*  854 */     int image_scaled_height = (int)(image_actual_height * image_scaling);
/*      */     
/*  856 */     GL11.glPushMatrix();
/*      */     
/*  858 */     GL11.glTranslatef(x, y, 0.0F);
/*  859 */     GL11.glRotatef(rotation, 0.0F, 0.0F, 1.0F);
/*      */     
/*  861 */     drawTexturedModalRect2(-image_scaled_width / 2, -image_scaled_height / 2, image_scaled_width, image_scaled_height);
/*      */     
/*  863 */     GL11.glPopMatrix();
/*  864 */     GL11.glAlphaFunc(516, 0.1F);
/*      */ 
/*      */ 
/*      */     
/*  868 */     GL11.glTexParameteri(3553, 10241, 9728);
/*  869 */     GL11.glTexParameteri(3553, 10240, 9728);
/*      */     
/*  871 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawScreen(int par1, int par2, float par3) {
/*  879 */     Minecraft.clearWorldSessionClientData();
/*      */     
/*  881 */     renderSkybox(par1, par2, par3);
/*  882 */     Tessellator var4 = Tessellator.instance;
/*  883 */     short var5 = 274;
/*  884 */     int var6 = this.width / 2 - var5 / 2;
/*  885 */     byte var7 = 30;
/*      */     
/*  887 */     drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
/*  888 */     drawGradientRect(0, 0, this.width, this.height, 0, -2147483648);
/*  889 */     this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
/*  890 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/*  892 */     if (this.updateCounter < 1.0E-4D) {
/*      */       
/*  894 */       drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 99, 44);
/*  895 */       drawTexturedModalRect(var6 + 99, var7 + 0, 129, 0, 27, 44);
/*  896 */       drawTexturedModalRect(var6 + 99 + 26, var7 + 0, 126, 0, 3, 44);
/*  897 */       drawTexturedModalRect(var6 + 99 + 26 + 3, var7 + 0, 99, 0, 26, 44);
/*  898 */       drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
/*      */     }
/*      */     else {
/*      */       
/*  902 */       drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 155, 44);
/*  903 */       drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  917 */     this.enum_special_splash = EnumSpecialSplash.getSpecialSplash(this.splashText);
/*      */     
/*  919 */     var4.setColorOpaque_I(16777215);
/*  920 */     GL11.glPushMatrix();
/*  921 */     GL11.glTranslatef((this.width / 2 + 90), 70.0F, 0.0F);
/*  922 */     GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
/*  923 */     float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
/*      */ 
/*      */ 
/*      */     
/*  927 */     if (this.enum_special_splash == EnumSpecialSplash.mite_migos) {
/*  928 */       var8 *= 0.86206895F;
/*      */     } else {
/*  930 */       var8 = var8 * 100.0F / (this.fontRenderer.getStringWidth(getModifiedSplashText()) + 32);
/*      */     } 
/*  932 */     GL11.glScalef(var8, var8, var8);
/*      */     
/*  934 */     drawCenteredString(this.fontRenderer, getModifiedSplashText(), 0, -8, 16776960);
/*      */ 
/*      */     
/*  937 */     if (this.enum_special_splash == EnumSpecialSplash.elite_dangerous) {
/*      */       
/*  939 */       GL11.glPushMatrix();
/*      */       
/*  941 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  942 */       drawCenteredString(this.fontRenderer, "It's a different kind of game", 28, 4, 16776960);
/*      */       
/*  944 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*  945 */       drawCenteredString(this.fontRenderer, "TM", 204, 6, 16776960);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  950 */       GL11.glPopMatrix();
/*      */     
/*      */     }
/*  953 */     else if (this.enum_special_splash == EnumSpecialSplash.ice_cream) {
/*      */       
/*  955 */       GL11.glEnable(3042);
/*      */ 
/*      */       
/*  958 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  959 */       this.mc.getTextureManager().bindTexture(ice_cream);
/*      */       
/*  961 */       drawTexturedModalRect2(-3, -2, 20, 20);
/*      */ 
/*      */     
/*      */     }
/*  965 */     else if (this.enum_special_splash == EnumSpecialSplash.ronin_pawn) {
/*      */       
/*  967 */       GL11.glEnable(3042);
/*      */ 
/*      */       
/*  970 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  972 */       this.mc.getTextureManager().bindTexture(this.enum_special_splash.getSplashTexture());
/*  973 */       drawTexturedModalRect2(-14, -18, 28, 28);
/*      */       
/*  975 */       this.mc.getTextureManager().bindTexture(clickMeTexture);
/*  976 */       drawTexturedModalRect2(-1, 7, 8, 8);
/*      */     }
/*  978 */     else if (this.enum_special_splash == EnumSpecialSplash.mite_migos) {
/*      */       
/*  980 */       GL11.glEnable(3042);
/*      */ 
/*      */       
/*  983 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  985 */       this.mc.getTextureManager().bindTexture(this.enum_special_splash.getSplashTexture());
/*  986 */       drawTexturedModalRect2(-48, -18, 96, 24);
/*      */       
/*  988 */       this.mc.getTextureManager().bindTexture(clickMeTexture);
/*  989 */       drawTexturedModalRect2(-8, 0, 8, 8);
/*      */     }
/*  991 */     else if (this.enum_special_splash == EnumSpecialSplash.guten_tag) {
/*      */       
/*  993 */       if (!this.gunshot_sound_preloaded) {
/*      */         
/*  995 */         this.mc.sndManager.playSoundFX("imported.random.gunshot", 0.001F, 0.5F);
/*  996 */         this.gunshot_sound_preloaded = true;
/*      */       } 
/*      */       
/*  999 */       GL11.glEnable(3042);
/*      */ 
/*      */       
/* 1002 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1003 */       this.mc.getTextureManager().bindTexture(gutenTagTextures[this.animated_texture_index]);
/* 1004 */       drawTexturedModalRect2(-10, -16, 28, 28);
/*      */       
/* 1006 */       if (System.currentTimeMillis() >= this.next_animated_texture_ms)
/*      */       {
/* 1008 */         int delay_ms = 500;
/*      */         
/* 1010 */         if (this.animated_texture_index < 3) {
/*      */           
/* 1012 */           this.animated_texture_index++;
/* 1013 */           delay_ms = 250;
/*      */           
/* 1015 */           if (this.animated_texture_index == 2) {
/* 1016 */             makeHeavyStepSound();
/*      */           }
/* 1018 */         } else if (this.animated_texture_index == 3) {
/*      */           
/* 1020 */           if (rand.nextInt(5) == 0)
/*      */           {
/* 1022 */             this.animated_texture_index++;
/* 1023 */             delay_ms = 500;
/*      */             
/* 1025 */             this.minimum_firing_loops = 4;
/*      */           }
/*      */           else
/*      */           {
/* 1029 */             this.animated_texture_index = 0;
/* 1030 */             delay_ms = 250;
/*      */             
/* 1032 */             makeHeavyStepSound();
/*      */           }
/*      */         
/* 1035 */         } else if (this.animated_texture_index == 4) {
/*      */           
/* 1037 */           this.animated_texture_index++;
/* 1038 */           delay_ms = 125;
/*      */           
/* 1040 */           fireBullet();
/*      */         }
/* 1042 */         else if (this.animated_texture_index == 5) {
/*      */           
/* 1044 */           this.animated_texture_index++;
/* 1045 */           delay_ms = 125;
/*      */           
/* 1047 */           fireBullet();
/*      */ 
/*      */         
/*      */         }
/* 1051 */         else if (--this.minimum_firing_loops > 0 || rand.nextFloat() < 0.8F) {
/*      */           
/* 1053 */           this.animated_texture_index = 5;
/* 1054 */           delay_ms = 125;
/*      */           
/* 1056 */           fireBullet();
/*      */ 
/*      */         
/*      */         }
/* 1060 */         else if (rand.nextFloat() < 0.5F) {
/*      */           
/* 1062 */           this.animated_texture_index = 0;
/* 1063 */           delay_ms = 250;
/*      */         }
/*      */         else {
/*      */           
/* 1067 */           this.animated_texture_index = 4;
/*      */           
/* 1069 */           if (rand.nextInt(2) == 0) {
/* 1070 */             delay_ms = 250;
/*      */           } else {
/* 1072 */             delay_ms = 500;
/*      */           } 
/* 1074 */           this.minimum_firing_loops = 2;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1079 */         this.next_animated_texture_ms = System.currentTimeMillis() + delay_ms;
/*      */       }
/*      */     
/* 1082 */     } else if (this.enum_special_splash == EnumSpecialSplash.cogmind) {
/*      */       
/* 1084 */       GL11.glEnable(3042);
/*      */ 
/*      */       
/* 1087 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*      */ 
/*      */       
/* 1091 */       this.mc.getTextureManager().bindTexture(clickMeTexture);
/* 1092 */       drawTexturedModalRect2(-1, 0, 8, 8);
/*      */     }
/* 1094 */     else if (this.enum_special_splash == EnumSpecialSplash.ludwig) {
/*      */       
/* 1096 */       GL11.glEnable(3042);
/*      */       
/* 1098 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/* 1100 */       this.mc.getTextureManager().bindTexture(clickMeTexture);
/* 1101 */       drawTexturedModalRect2(-1, 0, 8, 8);
/*      */     } 
/*      */     
/* 1104 */     GL11.glPopMatrix();
/*      */     
/* 1106 */     if ("Double whammy!".equals(this.splashText)) {
/*      */       
/* 1108 */       GL11.glPushMatrix();
/* 1109 */       GL11.glTranslatef((this.width / 2 - 90), 70.0F, 0.0F);
/* 1110 */       GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
/* 1111 */       var8 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
/*      */       
/* 1113 */       var8 = var8 * 100.0F / (this.fontRenderer.getStringWidth(getModifiedSplashText()) + 32);
/* 1114 */       GL11.glScalef(var8, var8, var8);
/*      */       
/* 1116 */       drawCenteredString(this.fontRenderer, getModifiedSplashText(), 0, -8, 16776960);
/* 1117 */       GL11.glPopMatrix();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1128 */     drawString(this.fontRenderer, "Minecraft " + Minecraft.getVersionDescriptor(true), 2, this.height - 10, 16777215);
/* 1129 */     String var10 = "Copyright Mojang AB. Do not distribute!";
/* 1130 */     drawString(this.fontRenderer, var10, this.width - this.fontRenderer.getStringWidth(var10) - 2, this.height - 10, 16777215);
/*      */     
/* 1132 */     if (this.field_92025_p != null && this.field_92025_p.length() > 0) {
/*      */       
/* 1134 */       drawRect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1428160512);
/* 1135 */       drawString(this.fontRenderer, this.field_92025_p, this.field_92022_t, this.field_92021_u, 16777215);
/* 1136 */       drawString(this.fontRenderer, field_96138_a, (this.width - this.field_92024_r) / 2, ((GuiButton)this.buttonList.get(0)).yPosition - 12, 16777215);
/*      */     } 
/*      */     
/* 1139 */     if (Minecraft.MITE_resource_pack == null && "MITE Resource Pack 1.6.4 needs to be installed!" != null && "MITE Resource Pack 1.6.4 needs to be installed!".length() > 0) {
/*      */       
/* 1141 */       drawRect(this.field_92022_t_MITE - 2, this.field_92021_u_MITE - 2, this.field_92020_v_MITE + 2, this.field_92019_w_MITE - 1, 1428160512);
/* 1142 */       drawString(this.fontRenderer, "MITE Resource Pack 1.6.4 needs to be installed!", (this.width - this.field_92023_s_MITE) / 2, this.field_92021_u_MITE, 16777215);
/* 1143 */       drawString(this.fontRenderer, field_96138_a_MITE, (this.width - this.field_92024_r_MITE) / 2, this.field_92021_u_MITE + 12, 16777215);
/*      */     }
/* 1145 */     else if (Minecraft.java_version_is_outdated) {
/*      */       
/* 1147 */       String msg = "Minecraft Is Too Easy requires Java 1.7 or later!";
/*      */       
/* 1149 */       drawRect(this.field_92022_t_MITE - 2, this.field_92021_u_MITE - 2, this.field_92020_v_MITE + 2, this.field_92019_w_MITE - 1 - 13, 1428160512);
/* 1150 */       drawString(this.fontRenderer, msg, (this.width - this.fontRenderer.getStringWidth(msg)) / 2, this.field_92021_u_MITE, 16777215);
/*      */     } 
/*      */     
/* 1153 */     super.drawScreen(par1, par2, par3);
/*      */     
/* 1155 */     drawBulletHoles();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mouseClicked(int par1, int par2, int par3) {
/* 1163 */     super.mouseClicked(par1, par2, par3);
/*      */ 
/*      */     
/* 1166 */     synchronized (this.field_104025_t) {
/*      */       
/* 1168 */       if (this.field_92025_p.length() > 0 && par1 >= this.field_92022_t && par1 <= this.field_92020_v && par2 >= this.field_92021_u && par2 <= this.field_92019_w) {
/*      */         
/* 1170 */         GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
/* 1171 */         var5.func_92026_h();
/* 1172 */         this.mc.displayGuiScreen(var5);
/*      */       } 
/*      */     } 
/*      */     
/* 1176 */     if (Minecraft.MITE_resource_pack == null)
/*      */     {
/* 1178 */       synchronized (this.field_104025_t_MITE) {
/*      */         
/* 1180 */         getClass(); if ("MITE Resource Pack 1.6.4 needs to be installed!".length() > 0 && par1 >= this.field_92022_t_MITE && par1 <= this.field_92020_v_MITE && par2 >= this.field_92021_u_MITE && par2 <= this.field_92019_w_MITE) {
/*      */           
/* 1182 */           getClass(); GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, "http://minecraft-is-too-easy.com", 14, true);
/* 1183 */           var5.func_92026_h();
/* 1184 */           this.mc.displayGuiScreen(var5);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1226 */     if (this.enum_special_splash != null && this.enum_special_splash.hasURL())
/*      */     {
/* 1228 */       if (par1 >= (this.width + 42) / 2 && par1 <= (this.width + 302) / 2 && par2 >= 35 && par2 <= 90) {
/*      */         
/* 1230 */         GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.enum_special_splash);
/* 1231 */         var5.func_92026_h();
/* 1232 */         this.mc.displayGuiScreen(var5);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static Minecraft func_110348_a(GuiMainMenu par0GuiMainMenu) {
/* 1239 */     return par0GuiMainMenu.mc;
/*      */   }
/*      */ 
/*      */   
/*      */   static void func_130021_b(GuiMainMenu par0GuiMainMenu) {
/* 1244 */     par0GuiMainMenu.func_130022_h();
/*      */   }
/*      */ 
/*      */   
/*      */   static boolean func_110349_a(boolean par0) {
/* 1249 */     field_96139_s = par0;
/* 1250 */     return par0;
/*      */   }
/*      */ 
/*      */   
/*      */   static Minecraft func_130018_c(GuiMainMenu par0GuiMainMenu) {
/* 1255 */     return par0GuiMainMenu.mc;
/*      */   }
/*      */ 
/*      */   
/*      */   static Minecraft func_130019_d(GuiMainMenu par0GuiMainMenu) {
/* 1260 */     return par0GuiMainMenu.mc;
/*      */   }
/*      */ 
/*      */   
/*      */   static ResourceLocation[] getAnimatedTextures(int num_textures, String path, boolean generate_encoded_file) {
/* 1265 */     if (generate_encoded_file && !Minecraft.inDevMode()) {
/*      */       
/* 1267 */       Minecraft.setErrorMessage("getAnimatedTextures: Error occurred");
/* 1268 */       generate_encoded_file = false;
/*      */     } 
/*      */     
/* 1271 */     ResourceLocation[] RLs = new ResourceLocation[num_textures];
/*      */     
/* 1273 */     for (int i = 0; i < num_textures; i++) {
/*      */       
/* 1275 */       RLs[i] = (new ResourceLocation(path + i + ".png", false)).setGenerateEncodedFile(generate_encoded_file);
/*      */       
/* 1277 */       if (!RLs[i].exists()) {
/* 1278 */         RLs[i] = new ResourceLocation(path + i + ".enc");
/*      */       }
/*      */     } 
/* 1281 */     return RLs;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiMainMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */