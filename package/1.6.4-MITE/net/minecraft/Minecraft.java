/*      */ package net.minecraft;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.net.Proxy;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.imageio.ImageIO;
/*      */ import net.minecraft.client.ClientBrandRetriever;
/*      */ import net.minecraft.client.main.Main;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.Sys;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.ContextCapabilities;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import org.lwjgl.opengl.DisplayMode;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GLContext;
/*      */ import org.lwjgl.opengl.PixelFormat;
/*      */ import org.lwjgl.util.glu.GLU;
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
/*      */ 
/*      */ public final class Minecraft
/*      */   implements IPlayerUsage
/*      */ {
/*      */   private static boolean in_dev_mode = false;
/*      */   public static final short MITE_release_number = 196;
/*      */   public static final int earliest_allowable_MITE_release = 149;
/*  113 */   public static final int[] incompatible_releases = new int[] { 153, 155, 156, 157, 162, 163, 167, 168, 171, 173, 174, 179, 180, 181, 183, 186, 187, 190, 191, 194, 195 };
/*      */   
/*      */   public static final String MC_version = "1.6.4";
/*      */   
/*  117 */   private static char dev_mode_key_drive_letter = 'c';
/*  118 */   private static char dev_mode_key_colon = ':';
/*  119 */   private static char dev_mode_key_backslash = '\\';
/*      */   
/*  121 */   private static boolean dev_mode_key_exists = (in_dev_mode && doesDevModeKeyExist());
/*      */   
/*  123 */   private static final ResourceLocation locationMojangPng = new ResourceLocation("textures/gui/title/mojang.png");
/*  124 */   public static final boolean isRunningOnMac = (Util.getOSType() == EnumOS.MACOS);
/*      */ 
/*      */   
/*  127 */   public static byte[] memoryReserve = new byte[10485760];
/*  128 */   private static final List macDisplayModes = Lists.newArrayList((Object[])new DisplayMode[] { new DisplayMode(2560, 1600), new DisplayMode(2880, 1800) });
/*      */   
/*      */   private final ILogAgent mcLogAgent;
/*      */   
/*      */   public static ILogAgent MITE_log;
/*      */   
/*      */   private final File fileResourcepacks;
/*      */   
/*      */   private ServerData currentServerData;
/*      */   
/*      */   private TextureManager renderEngine;
/*      */   
/*      */   public static Minecraft theMinecraft;
/*      */   
/*      */   public PlayerControllerMP playerController;
/*      */   
/*      */   private boolean fullscreen;
/*      */   
/*      */   private boolean hasCrashed;
/*      */   private CrashReport crashReporter;
/*      */   public int displayWidth;
/*      */   public int displayHeight;
/*  150 */   private Timer timer = new Timer(20.0F);
/*      */ 
/*      */   
/*  153 */   private PlayerUsageSnooper usageSnooper = new PlayerUsageSnooper("client", this, MinecraftServer.getSystemTimeMillis());
/*      */ 
/*      */   
/*      */   public WorldClient theWorld;
/*      */ 
/*      */   
/*      */   public RenderGlobal renderGlobal;
/*      */ 
/*      */   
/*      */   public EntityClientPlayerMP thePlayer;
/*      */ 
/*      */   
/*      */   public EntityLivingBase renderViewEntity;
/*      */ 
/*      */   
/*      */   public float raining_strength_for_render_view_entity;
/*      */ 
/*      */   
/*      */   public float prev_raining_strength_for_render_view_entity;
/*      */ 
/*      */   
/*      */   public EntityLivingBase pointedEntityLiving;
/*      */ 
/*      */   
/*      */   public EffectRenderer effectRenderer;
/*      */ 
/*      */   
/*      */   private final Session session;
/*      */ 
/*      */   
/*      */   public boolean isGamePaused;
/*      */ 
/*      */   
/*      */   public FontRenderer fontRenderer;
/*      */ 
/*      */   
/*      */   public FontRenderer standardGalacticFontRenderer;
/*      */ 
/*      */   
/*      */   public GuiScreen currentScreen;
/*      */ 
/*      */   
/*      */   public GuiChat imposed_gui_chat;
/*      */ 
/*      */   
/*      */   public LoadingScreenRenderer loadingScreen;
/*      */   
/*      */   public EntityRenderer entityRenderer;
/*      */   
/*      */   private int leftClickCounter;
/*      */   
/*      */   public int right_click_counter;
/*      */   
/*      */   private int tempDisplayWidth;
/*      */   
/*      */   private int tempDisplayHeight;
/*      */   
/*      */   private IntegratedServer theIntegratedServer;
/*      */   
/*      */   public GuiAchievement guiAchievement;
/*      */   
/*      */   public GuiIngame ingameGUI;
/*      */   
/*      */   public boolean skipRenderWorld;
/*      */   
/*      */   public RaycastCollision objectMouseOver;
/*      */   
/*      */   public GameSettings gameSettings;
/*      */   
/*      */   public SoundManager sndManager;
/*      */   
/*      */   public MouseHelper mouseHelper;
/*      */   
/*      */   public final File mcDataDir;
/*      */   
/*      */   public final File saves_dir_MITE;
/*      */   
/*      */   private final File fileAssets;
/*      */   
/*      */   private final String launchedVersion;
/*      */   
/*      */   private final Proxy proxy;
/*      */   
/*      */   private ISaveFormat saveLoader;
/*      */   
/*      */   private static int debugFPS;
/*      */   
/*      */   private boolean refreshTexturePacksScheduled;
/*      */   
/*      */   public StatFileWriter statFileWriter;
/*      */   
/*      */   private String serverName;
/*      */   
/*      */   private int serverPort;
/*      */   
/*      */   boolean isTakingScreenshot;
/*      */   
/*      */   public boolean inGameHasFocus;
/*      */   
/*  252 */   long systemTime = getSystemTime();
/*      */   
/*      */   private int joinPlayerCounter;
/*      */   
/*      */   private final boolean isDemo;
/*      */   
/*      */   private INetworkManager myNetworkManager;
/*      */   
/*      */   private boolean integratedServerIsRunning;
/*  261 */   public final Profiler mcProfiler = new Profiler();
/*  262 */   private long field_83002_am = -1L;
/*      */   private ReloadableResourceManager mcResourceManager;
/*  264 */   private final MetadataSerializer metadataSerializer_ = new MetadataSerializer();
/*  265 */   private List defaultResourcePacks = Lists.newArrayList();
/*      */ 
/*      */   
/*      */   public DefaultResourcePack mcDefaultResourcePack;
/*      */ 
/*      */   
/*      */   private ResourcePackRepository mcResourcePackRepository;
/*      */   
/*      */   private LanguageManager mcLanguageManager;
/*      */   
/*      */   volatile boolean running = true;
/*      */   
/*  277 */   public String debug = "";
/*      */ 
/*      */   
/*  280 */   long debugUpdateTime = getSystemTime();
/*      */   
/*      */   int fpsCounter;
/*      */   
/*  284 */   long prevFrameTime = -1L;
/*      */ 
/*      */   
/*  287 */   private String debugProfilerName = "root";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  292 */   public static ResourcePack MITE_resource_pack = null;
/*  293 */   private static String error_message = null;
/*      */ 
/*      */   
/*      */   public static final int max_height = 255;
/*      */ 
/*      */   
/*      */   public static int last_fps;
/*      */ 
/*      */   
/*      */   public static int last_fp10s;
/*      */   
/*      */   public static boolean see_through_block_tops;
/*      */   
/*      */   protected static int adjusted_hour_of_disconnection;
/*      */   
/*      */   protected static long soonest_reconnection_time;
/*      */   
/*      */   public static String[] hit_list;
/*      */   
/*      */   public boolean take_screenshot_next_tick;
/*      */   
/*      */   public boolean force_rendering_for_screenshot;
/*      */   
/*      */   private float block_hit_effect_progress;
/*      */   
/*      */   public int last_known_delta_tournament_score;
/*      */   
/*      */   public int last_known_delta_tournament_score_opacity;
/*      */   
/*      */   public int last_known_tournament_score;
/*      */   
/*      */   public static boolean is_dedicated_server_running;
/*      */   
/*      */   private PropertyManager settings;
/*      */   
/*      */   public static String temp_debug;
/*      */   
/*      */   public static final boolean professions_suppressed = true;
/*      */   
/*      */   public static boolean allow_new_sand_physics = false;
/*      */   
/*      */   public boolean increment_startGameStat_asap;
/*      */   
/*      */   public boolean increment_loadWorldStat_asap;
/*      */   
/*      */   public boolean increment_joinMultiplayerStat_asap;
/*      */   
/*      */   public static ThreadMinecraftServer server_thread;
/*      */   
/*      */   public static Thread client_thread;
/*      */   
/*  344 */   public static String last_aborted_chat = "";
/*      */ 
/*      */   
/*      */   public static long disable_clicks_until;
/*      */ 
/*      */   
/*      */   public static boolean night_vision_override;
/*      */ 
/*      */   
/*      */   public static String server_pools_string;
/*      */   
/*  355 */   public static final String java_version = Runtime.class.getPackage().getSpecificationVersion();
/*  356 */   public static final boolean java_version_is_outdated = (java_version == null) ? false : ((java_version.equals("1.4") || java_version.equals("1.5") || java_version.equals("1.6")));
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setErrorMessage(String text) {
/*  362 */     setErrorMessage(text, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setErrorMessage(String text, boolean echo_to_err) {
/*  367 */     if (echo_to_err && (error_message == null || !error_message.equals(text))) {
/*  368 */       System.err.println(text);
/*      */     }
/*  370 */     if (error_message != null) {
/*      */       return;
/*      */     }
/*  373 */     error_message = text.replaceAll("\n", "");
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getErrorMessage() {
/*  378 */     return error_message;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clearErrorMessage() {
/*  383 */     error_message = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean doesDevModeKeyExist() {
/*  388 */     File file = new File("" + dev_mode_key_drive_letter + dev_mode_key_colon + dev_mode_key_backslash + "dm");
/*      */     
/*  390 */     if (file.exists() && file.canRead()) {
/*      */       
/*      */       try {
/*      */         
/*  394 */         BufferedReader bf = new BufferedReader(new FileReader(file));
/*  395 */         String contents = bf.readLine();
/*  396 */         bf.close();
/*      */         
/*  398 */         if ("dm".equals(contents)) {
/*  399 */           return true;
/*      */         }
/*  401 */       } catch (Exception e) {}
/*      */     }
/*      */     
/*  404 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean inDevMode() {
/*  409 */     if (in_dev_mode && !dev_mode_key_exists) {
/*      */       
/*  411 */       setErrorMessage(getSecretErrorMessage());
/*  412 */       in_dev_mode = false;
/*      */     } 
/*      */     
/*  415 */     return in_dev_mode;
/*      */   }
/*      */ 
/*      */   
/*      */   public Minecraft(Session par1Session, int par2, int par3, boolean par4, boolean par5, File par6File, File par7File, File par8File, Proxy par9Proxy, String par10Str) {
/*  420 */     theMinecraft = this;
/*  421 */     this.mcLogAgent = new LogAgent("Minecraft-Client", " [CLIENT]", (new File(par6File, "output-client.log")).getAbsolutePath());
/*  422 */     this; MITE_log = new LogAgent("MITE-Client", " [MITE-CLIENT]", (new File(par6File, "output-client-MITE.log")).getAbsolutePath());
/*  423 */     this.mcDataDir = par6File;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  428 */     this.saves_dir_MITE = new File(this.mcDataDir, "MITE/saves/1.6.4/");
/*      */ 
/*      */ 
/*      */     
/*  432 */     this.fileAssets = par7File;
/*  433 */     this.fileResourcepacks = par8File;
/*      */     
/*  435 */     File MITE_resource_pack_file = new File(this.fileResourcepacks, "MITE Resource Pack 1.6.4.zip");
/*      */     
/*  437 */     if (!MITE_resource_pack_file.exists()) {
/*  438 */       MITE_resource_pack_file = new File(this.fileResourcepacks, "MITE Resource Pack 1.6.4");
/*      */     }
/*  440 */     if (MITE_resource_pack_file.exists())
/*      */     {
/*  442 */       if (MITE_resource_pack_file.isFile()) {
/*  443 */         MITE_resource_pack = new FileResourcePack(MITE_resource_pack_file);
/*      */       } else {
/*  445 */         MITE_resource_pack = new FolderResourcePack(MITE_resource_pack_file);
/*      */       } 
/*      */     }
/*  448 */     this.launchedVersion = par10Str;
/*  449 */     this.mcDefaultResourcePack = new DefaultResourcePack(this.fileAssets);
/*  450 */     addDefaultResourcePack();
/*  451 */     this.proxy = par9Proxy;
/*      */     
/*  453 */     this.session = par1Session;
/*      */     
/*  455 */     this; this.mcLogAgent.logInfo("Starting minecraft client version 1.6.4-MITE (R" + 'Ä' + ")" + (inDevMode() ? " DEV" : ""));
/*  456 */     this.mcLogAgent.logInfo("Setting user: " + par1Session.getUsername());
/*  457 */     this.mcLogAgent.logInfo("(Session ID is " + par1Session.getSessionID() + ")");
/*  458 */     this.isDemo = par5;
/*  459 */     this.displayWidth = par2;
/*  460 */     this.displayHeight = par3;
/*  461 */     this.tempDisplayWidth = par2;
/*  462 */     this.tempDisplayHeight = par3;
/*  463 */     this.fullscreen = par4;
/*  464 */     ImageIO.setUseCache(false);
/*  465 */     StatList.nopInit();
/*      */     
/*  467 */     if (hit_list == null) {
/*  468 */       hit_list = getHitList();
/*      */     }
/*      */ 
/*      */     
/*  472 */     new ClientProperties("client.properties", getLogAgent());
/*      */   }
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
/*      */   public void crashed(CrashReport par1CrashReport) {
/*  526 */     this.hasCrashed = true;
/*  527 */     this.crashReporter = par1CrashReport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayCrashReport(CrashReport par1CrashReport) {
/*  535 */     File var2 = new File((getMinecraft()).mcDataDir, "crash-reports");
/*  536 */     File var3 = new File(var2, "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-client.txt");
/*  537 */     System.out.println(par1CrashReport.getCompleteReport());
/*      */     
/*  539 */     if (par1CrashReport.getFile() != null) {
/*      */       
/*  541 */       System.out.println("#@!@# Game crashed! Crash report saved to: #@!@# " + par1CrashReport.getFile());
/*  542 */       System.exit(-1);
/*      */     }
/*  544 */     else if (par1CrashReport.saveToFile(var3, getLogAgent())) {
/*      */       
/*  546 */       System.out.println("#@!@# Game crashed! Crash report saved to: #@!@# " + var3.getAbsolutePath());
/*  547 */       System.exit(-1);
/*      */     }
/*      */     else {
/*      */       
/*  551 */       System.out.println("#@?@# Game crashed! Crash report could not be saved. #@?@#");
/*  552 */       System.exit(-2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setServer(String par1Str, int par2) {
/*  558 */     this.serverName = par1Str;
/*  559 */     this.serverPort = par2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getVersionDescriptor(boolean include_formatting) {
/*  564 */     String green = include_formatting ? EnumChatFormatting.GREEN.toString() : "";
/*  565 */     String red = include_formatting ? EnumChatFormatting.RED.toString() : "";
/*      */ 
/*      */     
/*  568 */     return "1.6.4-MITE" + (Main.is_MITE_DS ? "-DS" : "") + (inDevMode() ? (red + " DEV") : "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void startGame() throws LWJGLException {
/*  576 */     this.gameSettings = new GameSettings(this, this.mcDataDir);
/*      */     
/*  578 */     if (this.gameSettings.overrideHeight > 0 && this.gameSettings.overrideWidth > 0) {
/*      */       
/*  580 */       this.displayWidth = this.gameSettings.overrideWidth;
/*  581 */       this.displayHeight = this.gameSettings.overrideHeight;
/*      */     } 
/*      */     
/*  584 */     if (this.fullscreen) {
/*      */ 
/*      */       
/*  587 */       Display.setFullscreen(true);
/*  588 */       this.displayWidth = Display.getDisplayMode().getWidth();
/*  589 */       this.displayHeight = Display.getDisplayMode().getHeight();
/*      */       
/*  591 */       if (this.displayWidth <= 0)
/*      */       {
/*  593 */         this.displayWidth = 1;
/*      */       }
/*      */       
/*  596 */       if (this.displayHeight <= 0)
/*      */       {
/*  598 */         this.displayHeight = 1;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  603 */       Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  611 */     Display.setTitle("Minecraft " + getVersionDescriptor(false));
/*  612 */     Display.setResizable(!Main.is_MITE_DS);
/*      */     
/*  614 */     getLogAgent().logInfo("LWJGL Version: " + Sys.getVersion());
/*      */     
/*  616 */     if (Util.getOSType() != EnumOS.MACOS) {
/*      */       
/*      */       try {
/*      */         
/*  620 */         Display.setIcon(new ByteBuffer[] { readImage(new File(this.fileAssets, "/icons/icon_16x16.png")), readImage(new File(this.fileAssets, "/icons/icon_32x32.png")) });
/*      */       }
/*  622 */       catch (IOException var5) {
/*      */         
/*  624 */         var5.printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  630 */       Display.create((new PixelFormat()).withDepthBits(24));
/*      */     }
/*  632 */     catch (LWJGLException var4) {
/*      */       
/*  634 */       var4.printStackTrace();
/*      */ 
/*      */       
/*      */       try {
/*  638 */         Thread.sleep(1000L);
/*      */       }
/*  640 */       catch (InterruptedException var3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  645 */       if (this.fullscreen)
/*      */       {
/*      */         
/*  648 */         updateDisplayMode();
/*      */       }
/*      */       
/*  651 */       Display.create();
/*      */     } 
/*      */     
/*  654 */     OpenGlHelper.initializeTextures();
/*  655 */     this.guiAchievement = new GuiAchievement(this);
/*  656 */     this.metadataSerializer_.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
/*  657 */     this.metadataSerializer_.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
/*  658 */     this.metadataSerializer_.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
/*  659 */     this.metadataSerializer_.registerMetadataSectionType(new PackMetadataSectionSerializer(), PackMetadataSection.class);
/*  660 */     this.metadataSerializer_.registerMetadataSectionType(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
/*      */     
/*  662 */     this.saveLoader = new AnvilSaveConverter(this.saves_dir_MITE);
/*  663 */     this.mcResourcePackRepository = new ResourcePackRepository(this.fileResourcepacks, this.mcDefaultResourcePack, this.metadataSerializer_, this.gameSettings);
/*  664 */     this.mcResourceManager = new SimpleReloadableResourceManager(this.metadataSerializer_);
/*  665 */     this.mcLanguageManager = new LanguageManager(this.metadataSerializer_, this.gameSettings.language);
/*  666 */     this.mcResourceManager.registerReloadListener(this.mcLanguageManager);
/*  667 */     refreshResources();
/*  668 */     this.renderEngine = new TextureManager(this.mcResourceManager);
/*  669 */     this.mcResourceManager.registerReloadListener(this.renderEngine);
/*  670 */     this.sndManager = new SoundManager(this.mcResourceManager, this.gameSettings, this.fileAssets);
/*  671 */     this.mcResourceManager.registerReloadListener(this.sndManager);
/*      */     
/*  673 */     loadScreen();
/*  674 */     this.fontRenderer = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii.png"), this.renderEngine, false);
/*      */     
/*  676 */     if (this.gameSettings.language != null) {
/*      */       
/*  678 */       this.fontRenderer.setUnicodeFlag(this.mcLanguageManager.isCurrentLocaleUnicode());
/*  679 */       this.fontRenderer.setBidiFlag(this.mcLanguageManager.isCurrentLanguageBidirectional());
/*      */     } 
/*      */     
/*  682 */     this.standardGalacticFontRenderer = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.renderEngine, false);
/*  683 */     this.mcResourceManager.registerReloadListener(this.fontRenderer);
/*  684 */     this.mcResourceManager.registerReloadListener(this.standardGalacticFontRenderer);
/*  685 */     this.mcResourceManager.registerReloadListener(new GrassColorReloadListener());
/*  686 */     this.mcResourceManager.registerReloadListener(new FoliageColorReloadListener());
/*  687 */     RenderManager.instance.itemRenderer = new ItemRenderer(this);
/*  688 */     this.entityRenderer = new EntityRenderer(this);
/*      */     
/*  690 */     this.statFileWriter = new StatFileWriter(this.session, new File(this.mcDataDir, "MITE"));
/*  691 */     AchievementList.openInventory.setStatStringFormatter(new StatStringFormatKeyInv(this));
/*  692 */     this.mouseHelper = new MouseHelper();
/*  693 */     checkGLError("Pre startup");
/*  694 */     GL11.glEnable(3553);
/*  695 */     GL11.glShadeModel(7425);
/*  696 */     GL11.glClearDepth(1.0D);
/*  697 */     GL11.glEnable(2929);
/*  698 */     GL11.glDepthFunc(515);
/*  699 */     GL11.glEnable(3008);
/*  700 */     GL11.glAlphaFunc(516, 0.1F);
/*  701 */     GL11.glCullFace(1029);
/*  702 */     GL11.glMatrixMode(5889);
/*  703 */     GL11.glLoadIdentity();
/*  704 */     GL11.glMatrixMode(5888);
/*  705 */     checkGLError("Startup");
/*  706 */     this.renderGlobal = new RenderGlobal(this);
/*  707 */     this.renderEngine.loadTextureMap(TextureMap.locationBlocksTexture, new TextureMap(0, "textures/blocks"));
/*  708 */     this.renderEngine.loadTextureMap(TextureMap.locationItemsTexture, new TextureMap(1, "textures/items"));
/*  709 */     GL11.glViewport(0, 0, this.displayWidth, this.displayHeight);
/*  710 */     this.effectRenderer = new EffectRenderer(this.theWorld, this.renderEngine);
/*  711 */     checkGLError("Post startup");
/*  712 */     this.ingameGUI = new GuiIngame(this);
/*      */     
/*  714 */     if (this.serverName != null) {
/*      */       
/*  716 */       displayGuiScreen(new GuiConnecting(new GuiMainMenu(), this, this.serverName, this.serverPort));
/*      */     }
/*      */     else {
/*      */       
/*  720 */       displayGuiScreen(new GuiMainMenu());
/*      */     } 
/*      */     
/*  723 */     this.loadingScreen = new LoadingScreenRenderer(this);
/*      */ 
/*      */     
/*  726 */     if (this.gameSettings.isFullscreen() && !this.fullscreen) {
/*      */       
/*  728 */       toggleFullscreen();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  733 */       Display.setVSyncEnabled(this.gameSettings.isVsyncEnabled());
/*      */     } 
/*      */     
/*  736 */     ReferenceFileWriter.write();
/*      */   }
/*      */ 
/*      */   
/*      */   public void refreshResources() {
/*  741 */     ArrayList<ResourcePack> var1 = Lists.newArrayList(this.defaultResourcePacks);
/*  742 */     Iterator<ResourcePackRepositoryEntry> var2 = this.mcResourcePackRepository.getRepositoryEntries().iterator();
/*      */     
/*  744 */     while (var2.hasNext()) {
/*      */       
/*  746 */       ResourcePackRepositoryEntry var3 = var2.next();
/*  747 */       var1.add(var3.getResourcePack());
/*      */     } 
/*      */     
/*  750 */     this.mcLanguageManager.parseLanguageMetadata(var1);
/*  751 */     this.mcResourceManager.reloadResources(var1);
/*      */     
/*  753 */     if (this.renderGlobal != null)
/*      */     {
/*  755 */       this.renderGlobal.loadRenderers();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void addDefaultResourcePack() {
/*  761 */     this.defaultResourcePacks.add(this.mcDefaultResourcePack);
/*      */   }
/*      */ 
/*      */   
/*      */   private ByteBuffer readImage(File par1File) throws IOException {
/*  766 */     BufferedImage var2 = ImageIO.read(par1File);
/*  767 */     int[] var3 = var2.getRGB(0, 0, var2.getWidth(), var2.getHeight(), (int[])null, 0, var2.getWidth());
/*  768 */     ByteBuffer var4 = ByteBuffer.allocate(4 * var3.length);
/*  769 */     int[] var5 = var3;
/*  770 */     int var6 = var3.length;
/*      */     
/*  772 */     for (int var7 = 0; var7 < var6; var7++) {
/*      */       
/*  774 */       int var8 = var5[var7];
/*  775 */       var4.putInt(var8 << 8 | var8 >> 24 & 0xFF);
/*      */     } 
/*      */     
/*  778 */     var4.flip();
/*  779 */     return var4;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateDisplayMode() throws LWJGLException {
/*  784 */     HashSet<? super DisplayMode> var1 = new HashSet();
/*  785 */     Collections.addAll(var1, Display.getAvailableDisplayModes());
/*  786 */     DisplayMode var2 = Display.getDesktopDisplayMode();
/*      */     
/*  788 */     if (!var1.contains(var2) && Util.getOSType() == EnumOS.MACOS) {
/*      */       
/*  790 */       Iterator<DisplayMode> var3 = macDisplayModes.iterator();
/*      */       
/*  792 */       while (var3.hasNext()) {
/*      */         
/*  794 */         DisplayMode var4 = var3.next();
/*  795 */         boolean var5 = true;
/*  796 */         Iterator<? super DisplayMode> var6 = var1.iterator();
/*      */ 
/*      */         
/*  799 */         while (var6.hasNext()) {
/*      */           
/*  801 */           DisplayMode var7 = var6.next();
/*      */           
/*  803 */           if (var7.getBitsPerPixel() == 32 && var7.getWidth() == var4.getWidth() && var7.getHeight() == var4.getHeight()) {
/*      */             
/*  805 */             var5 = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  810 */         if (!var5) {
/*      */           
/*  812 */           var6 = var1.iterator();
/*      */           
/*  814 */           while (var6.hasNext()) {
/*      */             
/*  816 */             DisplayMode var7 = var6.next();
/*      */             
/*  818 */             if (var7.getBitsPerPixel() == 32 && var7.getWidth() == var4.getWidth() / 2 && var7.getHeight() == var4.getHeight() / 2)
/*      */             {
/*  820 */               var2 = var7;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  828 */     Display.setDisplayMode(var2);
/*  829 */     this.displayWidth = var2.getWidth();
/*  830 */     this.displayHeight = var2.getHeight();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void loadScreen() throws LWJGLException {
/*  838 */     ScaledResolution var1 = new ScaledResolution(this.gameSettings, this.displayWidth, this.displayHeight);
/*  839 */     GL11.glClear(16640);
/*  840 */     GL11.glMatrixMode(5889);
/*  841 */     GL11.glLoadIdentity();
/*  842 */     GL11.glOrtho(0.0D, var1.getScaledWidth_double(), var1.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
/*  843 */     GL11.glMatrixMode(5888);
/*  844 */     GL11.glLoadIdentity();
/*  845 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*  846 */     GL11.glViewport(0, 0, this.displayWidth, this.displayHeight);
/*  847 */     GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/*  848 */     GL11.glDisable(2896);
/*  849 */     GL11.glEnable(3553);
/*  850 */     GL11.glDisable(2912);
/*  851 */     this.renderEngine.bindTexture(locationMojangPng);
/*  852 */     Tessellator var2 = Tessellator.instance;
/*  853 */     var2.startDrawingQuads();
/*  854 */     var2.setColorOpaque_I(16777215);
/*  855 */     var2.addVertexWithUV(0.0D, this.displayHeight, 0.0D, 0.0D, 0.0D);
/*  856 */     var2.addVertexWithUV(this.displayWidth, this.displayHeight, 0.0D, 0.0D, 0.0D);
/*  857 */     var2.addVertexWithUV(this.displayWidth, 0.0D, 0.0D, 0.0D, 0.0D);
/*  858 */     var2.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*  859 */     var2.draw();
/*  860 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  861 */     var2.setColorOpaque_I(16777215);
/*  862 */     short var3 = 256;
/*  863 */     short var4 = 256;
/*  864 */     scaledTessellator((var1.getScaledWidth() - var3) / 2, (var1.getScaledHeight() - var4) / 2, 0, 0, var3, var4);
/*  865 */     GL11.glDisable(2896);
/*  866 */     GL11.glDisable(2912);
/*  867 */     GL11.glEnable(3008);
/*  868 */     GL11.glAlphaFunc(516, 0.1F);
/*  869 */     Display.update();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scaledTessellator(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  877 */     float var7 = 0.00390625F;
/*  878 */     float var8 = 0.00390625F;
/*  879 */     Tessellator var9 = Tessellator.instance;
/*  880 */     var9.startDrawingQuads();
/*  881 */     var9.addVertexWithUV((par1 + 0), (par2 + par6), 0.0D, ((par3 + 0) * var7), ((par4 + par6) * var8));
/*  882 */     var9.addVertexWithUV((par1 + par5), (par2 + par6), 0.0D, ((par3 + par5) * var7), ((par4 + par6) * var8));
/*  883 */     var9.addVertexWithUV((par1 + par5), (par2 + 0), 0.0D, ((par3 + par5) * var7), ((par4 + 0) * var8));
/*  884 */     var9.addVertexWithUV((par1 + 0), (par2 + 0), 0.0D, ((par3 + 0) * var7), ((par4 + 0) * var8));
/*  885 */     var9.draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ISaveFormat getSaveLoader() {
/*  893 */     return this.saveLoader;
/*      */   }
/*      */ 
/*      */   
/*      */   public void openChat(GuiChat gui_chat) {
/*  898 */     this.imposed_gui_chat = gui_chat;
/*      */     
/*  900 */     ScaledResolution var2 = new ScaledResolution(this.gameSettings, this.displayWidth, this.displayHeight);
/*  901 */     int var3 = var2.getScaledWidth();
/*  902 */     int var4 = var2.getScaledHeight();
/*  903 */     gui_chat.setWorldAndResolution(this, var3, var4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeImposedChat() {
/*  909 */     if (this.imposed_gui_chat == null) {
/*      */       return;
/*      */     }
/*  912 */     this.imposed_gui_chat.onGuiClosed();
/*      */     
/*  914 */     this.imposed_gui_chat = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGuiScreen(GuiScreen par1GuiScreen) {
/*  922 */     if (this.thePlayer != null && this.thePlayer.isGhost() && par1GuiScreen instanceof GuiInventory) {
/*      */       return;
/*      */     }
/*  925 */     if (par1GuiScreen != null) {
/*  926 */       this.right_click_counter = 0;
/*      */     }
/*  928 */     if (this.currentScreen != null)
/*      */     {
/*  930 */       this.currentScreen.onGuiClosed();
/*      */     }
/*      */     
/*  933 */     this.statFileWriter.syncStats();
/*      */     
/*  935 */     if (par1GuiScreen == null && this.theWorld == null) {
/*      */       
/*  937 */       par1GuiScreen = new GuiMainMenu();
/*      */     }
/*  939 */     else if (par1GuiScreen == null && this.thePlayer.getHealth() <= 0.0F) {
/*      */       
/*  941 */       par1GuiScreen = new GuiGameOver();
/*      */     } 
/*      */     
/*  944 */     if (par1GuiScreen instanceof GuiMainMenu) {
/*      */       
/*  946 */       this.gameSettings.showDebugInfo = false;
/*  947 */       this.ingameGUI.getChatGUI().clearChatMessages();
/*      */     } 
/*      */     
/*  950 */     this.currentScreen = par1GuiScreen;
/*      */     
/*  952 */     if (par1GuiScreen != null) {
/*      */       
/*  954 */       setIngameNotInFocus();
/*  955 */       ScaledResolution var2 = new ScaledResolution(this.gameSettings, this.displayWidth, this.displayHeight);
/*  956 */       int var3 = var2.getScaledWidth();
/*  957 */       int var4 = var2.getScaledHeight();
/*  958 */       par1GuiScreen.setWorldAndResolution(this, var3, var4);
/*  959 */       this.skipRenderWorld = false;
/*      */       
/*  961 */       if (isChatImposed() && !par1GuiScreen.allowsImposedChat()) {
/*  962 */         closeImposedChat();
/*      */       }
/*      */     } else {
/*      */       
/*  966 */       setIngameFocus();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkGLError(String par1Str) {
/*  975 */     int var2 = GL11.glGetError();
/*      */     
/*  977 */     if (var2 != 0) {
/*      */       
/*  979 */       String var3 = GLU.gluErrorString(var2);
/*  980 */       getLogAgent().logSevere("########## GL ERROR ##########");
/*  981 */       getLogAgent().logSevere("@ " + par1Str);
/*  982 */       getLogAgent().logSevere(var2 + ": " + var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shutdownMinecraftApplet() {
/*      */     try {
/*  994 */       this.statFileWriter.syncStats();
/*  995 */       getLogAgent().logInfo("Stopping!");
/*      */ 
/*      */       
/*      */       try {
/*  999 */         loadWorld((WorldClient)null);
/*      */       }
/* 1001 */       catch (Throwable var7) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1008 */         GLAllocation.deleteTexturesAndDisplayLists();
/*      */       }
/* 1010 */       catch (Throwable var6) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1015 */       this.sndManager.cleanup();
/*      */     }
/*      */     finally {
/*      */       
/* 1019 */       Display.destroy();
/*      */       
/* 1021 */       if (!this.hasCrashed) {
/*      */         
/* 1023 */         ThreadedFileIOBase.reportErrorIfNotFinished();
/*      */         
/* 1025 */         System.exit(0);
/*      */       } 
/*      */     } 
/*      */     
/* 1029 */     System.gc();
/*      */   }
/*      */ 
/*      */   
/*      */   public void run() {
/* 1034 */     this.running = true;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1039 */       startGame();
/*      */     }
/* 1041 */     catch (Throwable var11) {
/*      */       
/* 1043 */       CrashReport var2 = CrashReport.makeCrashReport(var11, "Initializing game");
/* 1044 */       var2.makeCategory("Initialization");
/* 1045 */       displayCrashReport(addGraphicsAndWorldToCrashReport(var2));
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/* 1051 */       while (this.running)
/*      */       {
/* 1053 */         if (this.running)
/*      */         {
/* 1055 */           if (this.hasCrashed && this.crashReporter != null) {
/*      */             
/* 1057 */             displayCrashReport(this.crashReporter);
/*      */             
/*      */             return;
/*      */           } 
/* 1061 */           if (this.refreshTexturePacksScheduled) {
/*      */             
/* 1063 */             this.refreshTexturePacksScheduled = false;
/* 1064 */             refreshResources();
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/* 1069 */             runGameLoop();
/*      */           }
/* 1071 */           catch (OutOfMemoryError var10) {
/*      */             
/* 1073 */             freeMemory();
/* 1074 */             displayGuiScreen(new GuiMemoryErrorScreen());
/* 1075 */             System.gc();
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1082 */     } catch (MinecraftError var12) {
/*      */ 
/*      */     
/* 1085 */     } catch (ReportedException var13) {
/*      */       
/* 1087 */       addGraphicsAndWorldToCrashReport(var13.getCrashReport());
/* 1088 */       freeMemory();
/* 1089 */       var13.printStackTrace();
/* 1090 */       displayCrashReport(var13.getCrashReport());
/*      */     }
/* 1092 */     catch (Throwable var14) {
/*      */       
/* 1094 */       CrashReport var2 = addGraphicsAndWorldToCrashReport(new CrashReport("Unexpected error", var14));
/* 1095 */       freeMemory();
/* 1096 */       var14.printStackTrace();
/* 1097 */       displayCrashReport(var2);
/*      */     }
/*      */     finally {
/*      */       
/* 1101 */       shutdownMinecraftApplet();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void runGameLoop() {
/* 1110 */     this.gameSettings.difficulty = 3;
/* 1111 */     this.gameSettings.gammaSetting = 0.0F;
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
/* 1135 */     AxisAlignedBB.getAABBPool().cleanPool();
/*      */     
/* 1137 */     if (this.theWorld != null)
/*      */     {
/* 1139 */       this.theWorld.getWorldVec3Pool().clear();
/*      */     }
/*      */     
/* 1142 */     this.mcProfiler.startSection("root");
/*      */     
/* 1144 */     if (Display.isCloseRequested())
/*      */     {
/* 1146 */       shutdown();
/*      */     }
/*      */     
/* 1149 */     if (this.isGamePaused && this.theWorld != null) {
/*      */       
/* 1151 */       float var1 = this.timer.renderPartialTicks;
/* 1152 */       this.timer.updateTimer();
/* 1153 */       this.timer.renderPartialTicks = var1;
/*      */     }
/*      */     else {
/*      */       
/* 1157 */       this.timer.updateTimer();
/*      */     } 
/*      */     
/* 1160 */     long var6 = System.nanoTime();
/* 1161 */     this.mcProfiler.startSection("tick");
/*      */     
/* 1163 */     for (int var3 = 0; var3 < this.timer.elapsedTicks; var3++)
/*      */     {
/* 1165 */       runTick();
/*      */     }
/*      */     
/* 1168 */     this.mcProfiler.endStartSection("preRenderErrors");
/* 1169 */     long var7 = System.nanoTime() - var6;
/* 1170 */     checkGLError("Pre render");
/*      */     
/* 1172 */     RenderBlocks.fancyGrass = this.gameSettings.isFancyGraphicsEnabled();
/* 1173 */     this.mcProfiler.endStartSection("sound");
/* 1174 */     this.sndManager.setListener(this.thePlayer, this.timer.renderPartialTicks);
/*      */     
/* 1176 */     if (!this.isGamePaused)
/*      */     {
/* 1178 */       this.sndManager.func_92071_g();
/*      */     }
/*      */     
/* 1181 */     this.mcProfiler.endSection();
/* 1182 */     this.mcProfiler.startSection("render");
/* 1183 */     this.mcProfiler.startSection("display");
/* 1184 */     GL11.glEnable(3553);
/*      */     
/* 1186 */     if (!Keyboard.isKeyDown(65))
/*      */     {
/* 1188 */       Display.update();
/*      */     }
/*      */ 
/*      */     
/* 1192 */     if (this.thePlayer != null && (this.thePlayer.isEntityInsideOpaqueBlock() || this.thePlayer.isLockedInFirstPersonView()))
/*      */     {
/* 1194 */       this.gameSettings.thirdPersonView = 0;
/*      */     }
/*      */     
/* 1197 */     this.mcProfiler.endSection();
/*      */     
/* 1199 */     if (!this.skipRenderWorld) {
/*      */       
/* 1201 */       this.mcProfiler.endStartSection("gameRenderer");
/* 1202 */       this.entityRenderer.updateCameraAndRender(this.timer.renderPartialTicks);
/* 1203 */       this.mcProfiler.endSection();
/*      */     } 
/*      */     
/* 1206 */     GL11.glFlush();
/* 1207 */     this.mcProfiler.endSection();
/*      */     
/* 1209 */     if (!Display.isActive() && this.fullscreen)
/*      */     {
/* 1211 */       toggleFullscreen();
/*      */     }
/*      */     
/* 1214 */     if (this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart) {
/*      */       
/* 1216 */       if (!this.mcProfiler.profilingEnabled)
/*      */       {
/* 1218 */         this.mcProfiler.clearProfiling();
/*      */       }
/*      */       
/* 1221 */       this.mcProfiler.profilingEnabled = true;
/* 1222 */       displayDebugInfo(var7);
/*      */     }
/*      */     else {
/*      */       
/* 1226 */       this.mcProfiler.profilingEnabled = false;
/* 1227 */       this.prevFrameTime = System.nanoTime();
/*      */     } 
/*      */     
/* 1230 */     this.guiAchievement.updateAchievementWindow();
/* 1231 */     this.mcProfiler.startSection("root");
/* 1232 */     Thread.yield();
/*      */     
/* 1234 */     if (Keyboard.isKeyDown(65))
/*      */     {
/* 1236 */       Display.update();
/*      */     }
/*      */     
/* 1239 */     screenshotListener();
/*      */     
/* 1241 */     if (!this.fullscreen && Display.wasResized()) {
/*      */       
/* 1243 */       this.displayWidth = Display.getWidth();
/* 1244 */       this.displayHeight = Display.getHeight();
/*      */       
/* 1246 */       if (this.displayWidth <= 0)
/*      */       {
/* 1248 */         this.displayWidth = 1;
/*      */       }
/*      */       
/* 1251 */       if (this.displayHeight <= 0)
/*      */       {
/* 1253 */         this.displayHeight = 1;
/*      */       }
/*      */       
/* 1256 */       resize(this.displayWidth, this.displayHeight);
/*      */     } 
/*      */     
/* 1259 */     checkGLError("Post render");
/* 1260 */     this.fpsCounter++;
/* 1261 */     boolean var5 = this.isGamePaused;
/* 1262 */     this; this.isGamePaused = (isSingleplayer() && this.currentScreen != null && this.currentScreen.doesGuiPauseGame() && !this.theIntegratedServer.getPublic());
/*      */     
/* 1264 */     if (isIntegratedServerRunning() && this.thePlayer != null && this.thePlayer.sendQueue != null && this.isGamePaused != var5)
/*      */     {
/* 1266 */       ((MemoryConnection)this.thePlayer.sendQueue.getNetManager()).setGamePaused(this.isGamePaused);
/*      */     }
/*      */     
/* 1269 */     while (getSystemTime() >= this.debugUpdateTime + 1000L) {
/*      */       
/* 1271 */       debugFPS = this.fpsCounter;
/* 1272 */       this.debug = debugFPS + " fps, " + WorldRenderer.chunksUpdated + " chunk updates";
/* 1273 */       WorldRenderer.chunksUpdated = 0;
/* 1274 */       this.debugUpdateTime += 1000L;
/* 1275 */       this.fpsCounter = 0;
/* 1276 */       this.usageSnooper.addMemoryStatsToSnooper();
/*      */       
/* 1278 */       if (!this.usageSnooper.isSnooperRunning())
/*      */       {
/* 1280 */         this.usageSnooper.startSnooper();
/*      */       }
/*      */     } 
/*      */     
/* 1284 */     this.mcProfiler.endSection();
/*      */     
/* 1286 */     if (getLimitFramerate() > 0)
/*      */     {
/* 1288 */       Display.sync(EntityRenderer.performanceToFps(getLimitFramerate()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void downtimeProcessing(long milliseconds_of_downtime) {
/* 1297 */     if (milliseconds_of_downtime <= 0L || this.thePlayer.isGhost() || Main.no_downtime_processing) {
/*      */       return;
/*      */     }
/* 1300 */     long time_to_move_on = System.currentTimeMillis() + milliseconds_of_downtime;
/*      */ 
/*      */ 
/*      */     
/* 1304 */     if (!this.gameSettings.anaglyph && !this.gameSettings.isVsyncEnabled()) {
/*      */       
/* 1306 */       double[] viewer_pos = RenderGlobal.getViewerPos(this.timer.renderPartialTicks, 12.0F);
/* 1307 */       double cloud_recompile_urgency = Math.max(Math.abs(this.renderGlobal.last_cloud_compile_x - viewer_pos[0]), Math.abs(this.renderGlobal.last_cloud_compile_z - viewer_pos[2]));
/*      */ 
/*      */       
/* 1310 */       if (cloud_recompile_urgency > 0.5D && milliseconds_of_downtime > 8.0D - cloud_recompile_urgency * 4.0D) {
/*      */ 
/*      */ 
/*      */         
/* 1314 */         long t = System.nanoTime();
/* 1315 */         this.renderGlobal.compileCloudsFancy(this.timer.renderPartialTicks, viewer_pos[0], viewer_pos[1], viewer_pos[2]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1320 */     int i = 0;
/*      */ 
/*      */     
/* 1323 */     while (System.currentTimeMillis() < time_to_move_on && i < MITEConstant.maxRandomRaycastsPerTickForCorrectiveLightingUpdates(this.thePlayer.worldObj)) {
/*      */       
/* 1325 */       this.theWorld.checkLightingOfRandomBlockInView(false);
/* 1326 */       i++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getLimitFramerate() {
/* 1335 */     return (this.currentScreen != null && this.currentScreen instanceof GuiMainMenu) ? 2 : this.gameSettings.limitFramerate;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void freeMemory() {
/*      */     try {
/* 1342 */       memoryReserve = new byte[0];
/* 1343 */       this.renderGlobal.deleteAllDisplayLists();
/*      */     }
/* 1345 */     catch (Throwable var4) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1352 */       System.gc();
/* 1353 */       AxisAlignedBB.getAABBPool().clearPool();
/* 1354 */       this.theWorld.getWorldVec3Pool().clearAndFreeCache();
/*      */     }
/* 1356 */     catch (Throwable var3) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1363 */       System.gc();
/* 1364 */       loadWorld((WorldClient)null);
/*      */     }
/* 1366 */     catch (Throwable var2) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1371 */     System.gc();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void screenshotListener() {
/* 1379 */     if (inDevMode() && GuiScreen.isCtrlKeyDown()) {
/*      */       
/* 1381 */       screenshotListenerForForcedRendering();
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1386 */     if (Keyboard.isKeyDown(60) || this.take_screenshot_next_tick) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1391 */       if (!this.isTakingScreenshot) {
/*      */         
/* 1393 */         this.isTakingScreenshot = true;
/* 1394 */         this.ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(this.mcDataDir, this.displayWidth, this.displayHeight));
/* 1395 */         this.sndManager.playSoundFX("imported.random.camera", 1.0F, 1.0F);
/*      */       } 
/*      */       
/* 1398 */       if (this.take_screenshot_next_tick) {
/* 1399 */         this.take_screenshot_next_tick = false;
/*      */       }
/*      */     } else {
/*      */       
/* 1403 */       this.isTakingScreenshot = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void takeScreenshot() {
/* 1409 */     this.ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(this.mcDataDir, this.displayWidth, this.displayHeight));
/* 1410 */     this.sndManager.playSoundFX("imported.random.camera", 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private void screenshotListenerForForcedRendering() {
/* 1415 */     boolean is_screenshot_key_down = Keyboard.isKeyDown(60);
/*      */     
/* 1417 */     if (this.take_screenshot_next_tick) {
/*      */       
/* 1419 */       takeScreenshot();
/* 1420 */       this.take_screenshot_next_tick = false;
/*      */     }
/* 1422 */     else if (is_screenshot_key_down) {
/*      */       
/* 1424 */       if (!this.isTakingScreenshot) {
/*      */         
/* 1426 */         this.isTakingScreenshot = true;
/*      */         
/* 1428 */         if (GuiScreen.isCtrlKeyDown()) {
/* 1429 */           this.take_screenshot_next_tick = this.force_rendering_for_screenshot = true;
/*      */         } else {
/* 1431 */           takeScreenshot();
/*      */         } 
/*      */       } 
/*      */     } 
/* 1435 */     if (!is_screenshot_key_down) {
/* 1436 */       this.isTakingScreenshot = false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateDebugProfilerName(int par1) {
/* 1444 */     List<ProfilerResult> var2 = this.mcProfiler.getProfilingData(this.debugProfilerName);
/*      */     
/* 1446 */     if (var2 != null && !var2.isEmpty()) {
/*      */       
/* 1448 */       ProfilerResult var3 = var2.remove(0);
/*      */       
/* 1450 */       if (par1 == 0) {
/*      */         
/* 1452 */         if (var3.field_76331_c.length() > 0)
/*      */         {
/* 1454 */           int var4 = this.debugProfilerName.lastIndexOf(".");
/*      */           
/* 1456 */           if (var4 >= 0)
/*      */           {
/* 1458 */             this.debugProfilerName = this.debugProfilerName.substring(0, var4);
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1464 */         par1--;
/*      */         
/* 1466 */         if (par1 < var2.size() && !((ProfilerResult)var2.get(par1)).field_76331_c.equals("unspecified")) {
/*      */           
/* 1468 */           if (this.debugProfilerName.length() > 0)
/*      */           {
/* 1470 */             this.debugProfilerName += ".";
/*      */           }
/*      */           
/* 1473 */           this.debugProfilerName += ((ProfilerResult)var2.get(par1)).field_76331_c;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void displayDebugInfo(long par1) {
/* 1481 */     if (this.mcProfiler.profilingEnabled) {
/*      */       
/* 1483 */       List<ProfilerResult> var3 = this.mcProfiler.getProfilingData(this.debugProfilerName);
/* 1484 */       ProfilerResult var4 = var3.remove(0);
/* 1485 */       GL11.glClear(256);
/* 1486 */       GL11.glMatrixMode(5889);
/* 1487 */       GL11.glEnable(2903);
/* 1488 */       GL11.glLoadIdentity();
/* 1489 */       GL11.glOrtho(0.0D, this.displayWidth, this.displayHeight, 0.0D, 1000.0D, 3000.0D);
/* 1490 */       GL11.glMatrixMode(5888);
/* 1491 */       GL11.glLoadIdentity();
/* 1492 */       GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/* 1493 */       GL11.glLineWidth(1.0F);
/* 1494 */       GL11.glDisable(3553);
/* 1495 */       Tessellator var5 = Tessellator.instance;
/* 1496 */       short var6 = 160;
/* 1497 */       int var7 = this.displayWidth - var6 - 10;
/* 1498 */       int var8 = this.displayHeight - var6 * 2;
/* 1499 */       GL11.glEnable(3042);
/* 1500 */       var5.startDrawingQuads();
/* 1501 */       var5.setColorRGBA_I(0, 200);
/* 1502 */       var5.addVertex((var7 - var6 * 1.1F), (var8 - var6 * 0.6F - 16.0F), 0.0D);
/* 1503 */       var5.addVertex((var7 - var6 * 1.1F), (var8 + var6 * 2), 0.0D);
/* 1504 */       var5.addVertex((var7 + var6 * 1.1F), (var8 + var6 * 2), 0.0D);
/* 1505 */       var5.addVertex((var7 + var6 * 1.1F), (var8 - var6 * 0.6F - 16.0F), 0.0D);
/* 1506 */       var5.draw();
/* 1507 */       GL11.glDisable(3042);
/* 1508 */       double var9 = 0.0D;
/*      */ 
/*      */       
/* 1511 */       for (int var11 = 0; var11 < var3.size(); var11++) {
/*      */         
/* 1513 */         ProfilerResult var12 = var3.get(var11);
/* 1514 */         int i = MathHelper.floor_double(var12.field_76332_a / 4.0D) + 1;
/* 1515 */         var5.startDrawing(6);
/* 1516 */         var5.setColorOpaque_I(var12.func_76329_a());
/* 1517 */         var5.addVertex(var7, var8, 0.0D);
/*      */ 
/*      */         
/*      */         int var14;
/*      */ 
/*      */         
/* 1523 */         for (var14 = i; var14 >= 0; var14--) {
/*      */           
/* 1525 */           float var15 = (float)((var9 + var12.field_76332_a * var14 / i) * Math.PI * 2.0D / 100.0D);
/* 1526 */           float var16 = MathHelper.sin(var15) * var6;
/* 1527 */           float var17 = MathHelper.cos(var15) * var6 * 0.5F;
/* 1528 */           var5.addVertex((var7 + var16), (var8 - var17), 0.0D);
/*      */         } 
/*      */         
/* 1531 */         var5.draw();
/* 1532 */         var5.startDrawing(5);
/* 1533 */         var5.setColorOpaque_I((var12.func_76329_a() & 0xFEFEFE) >> 1);
/*      */         
/* 1535 */         for (var14 = i; var14 >= 0; var14--) {
/*      */           
/* 1537 */           float var15 = (float)((var9 + var12.field_76332_a * var14 / i) * Math.PI * 2.0D / 100.0D);
/* 1538 */           float var16 = MathHelper.sin(var15) * var6;
/* 1539 */           float var17 = MathHelper.cos(var15) * var6 * 0.5F;
/* 1540 */           var5.addVertex((var7 + var16), (var8 - var17), 0.0D);
/* 1541 */           var5.addVertex((var7 + var16), (var8 - var17 + 10.0F), 0.0D);
/*      */         } 
/*      */         
/* 1544 */         var5.draw();
/* 1545 */         var9 += var12.field_76332_a;
/*      */       } 
/*      */       
/* 1548 */       DecimalFormat var19 = new DecimalFormat("##0.00");
/* 1549 */       GL11.glEnable(3553);
/* 1550 */       String var18 = "";
/*      */       
/* 1552 */       if (!var4.field_76331_c.equals("unspecified"))
/*      */       {
/* 1554 */         var18 = var18 + "[0] ";
/*      */       }
/*      */       
/* 1557 */       if (var4.field_76331_c.length() == 0) {
/*      */         
/* 1559 */         var18 = var18 + "ROOT ";
/*      */       }
/*      */       else {
/*      */         
/* 1563 */         var18 = var18 + var4.field_76331_c + " ";
/*      */       } 
/*      */       
/* 1566 */       int var13 = 16777215;
/* 1567 */       this.fontRenderer.drawStringWithShadow(var18, var7 - var6, var8 - var6 / 2 - 16, var13);
/* 1568 */       this.fontRenderer.drawStringWithShadow(var18 = var19.format(var4.field_76330_b) + "%", var7 + var6 - this.fontRenderer.getStringWidth(var18), var8 - var6 / 2 - 16, var13);
/*      */       
/* 1570 */       for (int var21 = 0; var21 < var3.size(); var21++) {
/*      */         
/* 1572 */         ProfilerResult var20 = var3.get(var21);
/* 1573 */         String var22 = "";
/*      */         
/* 1575 */         if (var20.field_76331_c.equals("unspecified")) {
/*      */           
/* 1577 */           var22 = var22 + "[?] ";
/*      */         }
/*      */         else {
/*      */           
/* 1581 */           var22 = var22 + "[" + (var21 + 1) + "] ";
/*      */         } 
/*      */         
/* 1584 */         var22 = var22 + var20.field_76331_c;
/* 1585 */         this.fontRenderer.drawStringWithShadow(var22, var7 - var6, var8 + var6 / 2 + var21 * 8 + 20, var20.func_76329_a());
/* 1586 */         this.fontRenderer.drawStringWithShadow(var22 = var19.format(var20.field_76332_a) + "%", var7 + var6 - 50 - this.fontRenderer.getStringWidth(var22), var8 + var6 / 2 + var21 * 8 + 20, var20.func_76329_a());
/* 1587 */         this.fontRenderer.drawStringWithShadow(var22 = var19.format(var20.field_76330_b) + "%", var7 + var6 - this.fontRenderer.getStringWidth(var22), var8 + var6 / 2 + var21 * 8 + 20, var20.func_76329_a());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void shutdown() {
/* 1597 */     ThreadedFileIOBase.waitForFinish();
/* 1598 */     this.running = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIngameFocus() {
/* 1607 */     if (Display.isActive())
/*      */     {
/* 1609 */       if (!this.inGameHasFocus) {
/*      */         
/* 1611 */         this.inGameHasFocus = true;
/* 1612 */         this.mouseHelper.grabMouseCursor();
/* 1613 */         displayGuiScreen((GuiScreen)null);
/* 1614 */         closeImposedChat();
/* 1615 */         this.leftClickCounter = 10000;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIngameNotInFocus() {
/* 1625 */     if (this.inGameHasFocus) {
/*      */       
/* 1627 */       KeyBinding.unPressAllKeys();
/* 1628 */       this.inGameHasFocus = false;
/* 1629 */       this.mouseHelper.ungrabMouseCursor();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayInGameMenu() {
/* 1638 */     if (this.currentScreen == null) {
/*      */       
/* 1640 */       displayGuiScreen(new GuiIngameMenu());
/*      */       
/* 1642 */       this; if (isSingleplayer() && !this.theIntegratedServer.getPublic())
/*      */       {
/* 1644 */         this.sndManager.pauseAllSounds();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void sendClickBlockToController(int par1, boolean par2) {
/* 1651 */     if (this.thePlayer.isGhost() || this.thePlayer.isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 1654 */     if (this.thePlayer.fishEntity != null) {
/*      */       return;
/*      */     }
/* 1657 */     if (this.playerController.auto_use_mode_item != null) {
/*      */       return;
/*      */     }
/* 1660 */     if (par1 == 0 && par2) {
/* 1661 */       this.playerController.last_auto_harvest_ms = System.currentTimeMillis();
/*      */     }
/* 1663 */     if (!par2)
/*      */     {
/* 1665 */       this.leftClickCounter = 0;
/*      */     }
/*      */     
/* 1668 */     if (par1 != 0 || this.leftClickCounter <= 0)
/*      */     {
/*      */       
/* 1671 */       if (par2 && this.objectMouseOver != null && this.objectMouseOver.isBlock() && par1 == 0) {
/*      */         
/* 1673 */         int var3 = this.objectMouseOver.block_hit_x;
/* 1674 */         int var4 = this.objectMouseOver.block_hit_y;
/* 1675 */         int var5 = this.objectMouseOver.block_hit_z;
/*      */         
/* 1677 */         boolean player_damaged_block = (this.thePlayer.getDamageVsBlock(var3, var4, var5, true) > 0.0F);
/*      */         
/* 1679 */         this.playerController.onPlayerDamageBlock(var3, var4, var5, this.objectMouseOver.face_hit);
/*      */         
/* 1681 */         if (this.thePlayer.isCurrentToolAdventureModeExempt(var3, var4, var5))
/*      */         {
/* 1683 */           if (player_damaged_block) {
/*      */             
/* 1685 */             float damage = this.thePlayer.getDamageVsBlock(var3, var4, var5, true);
/*      */             
/* 1687 */             float block_hit_effect_progress_per_tick = damage * 80.0F;
/*      */             
/* 1689 */             this.block_hit_effect_progress += block_hit_effect_progress_per_tick;
/*      */             
/* 1691 */             if (this.block_hit_effect_progress >= 1.0F) {
/*      */ 
/*      */               
/* 1694 */               this.effectRenderer.addBlockHitEffects(var3, var4, var5, this.objectMouseOver.face_hit);
/*      */ 
/*      */               
/* 1697 */               this.thePlayer.sendPacketToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.block_hit_fx)).setBlockCoords(var3, var4, var5).setByte(this.objectMouseOver.face_hit.ordinal()), false);
/*      */               
/* 1699 */               if (block_hit_effect_progress_per_tick < 1.0F) {
/* 1700 */                 this.block_hit_effect_progress--;
/*      */               } else {
/* 1702 */                 this.block_hit_effect_progress = 0.0F;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 1707 */           this.thePlayer.swingArm();
/*      */         }
/*      */       
/*      */       }
/* 1711 */       else if (this.playerController.isHittingBlock || this.objectMouseOver == null || !this.playerController.sameToolAndBlock(this.objectMouseOver.block_hit_x, this.objectMouseOver.block_hit_y, this.objectMouseOver.block_hit_z)) {
/*      */         
/* 1713 */         this.playerController.resetBlockRemoving();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean tryClickEntity(int button) {
/* 1720 */     boolean done = false;
/*      */     
/* 1722 */     if (button == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1727 */       if (!this.thePlayer.canReachEntity(this.objectMouseOver, EnumEntityReachContext.FOR_MELEE_ATTACK)) {
/* 1728 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1733 */       if (this.objectMouseOver.getEntityHit().canBeAttackedBy(this.thePlayer)) {
/*      */         
/* 1735 */         this.playerController.leftClickEntity(this.objectMouseOver.getEntityHit());
/*      */       } else {
/* 1737 */         this.playerController.cancel_swing = true;
/*      */       } 
/* 1739 */       done = true;
/*      */     }
/* 1741 */     else if (button == 1) {
/*      */       
/* 1743 */       setErrorMessage("tryClickEntity: should no longer be called for right clicks");
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
/* 1765 */     return done;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean blockClicked(int button) {
/* 1770 */     boolean done = false;
/* 1771 */     ItemStack held_item_stack = this.thePlayer.inventory.getCurrentItemStack();
/* 1772 */     int held_item_slot_index = this.thePlayer.inventory.currentItem;
/*      */     
/* 1774 */     int x = this.objectMouseOver.block_hit_x;
/* 1775 */     int y = this.objectMouseOver.block_hit_y;
/* 1776 */     int z = this.objectMouseOver.block_hit_z;
/* 1777 */     EnumFace face = this.objectMouseOver.face_hit;
/*      */     
/* 1779 */     if (button == 0) {
/*      */       
/* 1781 */       this.playerController.clickBlock(x, y, z, face);
/* 1782 */       done = true;
/*      */     }
/* 1784 */     else if (button == 1) {
/*      */       
/* 1786 */       setErrorMessage("blockClicked: no longer used for right clicks");
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
/* 1809 */     return done;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean tryAutoSwitchOrRestock() {
/* 1814 */     ItemStack hotbar_selection = this.thePlayer.inventory.getCurrentItemStack();
/*      */     
/* 1816 */     if (hotbar_selection == null || hotbar_selection.stackSize == 0)
/*      */     {
/* 1818 */       if (this.playerController.last_used_item != null && this.playerController.autoStockEnabled()) {
/*      */         
/* 1820 */         this.playerController.item_switch_or_restock_pending = true;
/* 1821 */         return true;
/*      */       } 
/*      */     }
/*      */     
/* 1825 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void clickMouse(int button) {
/* 1830 */     if (disable_clicks_until >= System.currentTimeMillis()) {
/*      */       return;
/*      */     }
/* 1833 */     if (inDevMode() && button == 0 && GuiScreen.isCtrlKeyDown()) {
/*      */       
/* 1835 */       RaycastCollision rc = this.thePlayer.getSelectedObject(this.timer.renderPartialTicks, false, true, (EnumEntityReachContext)null);
/*      */       
/* 1837 */       if (rc != null && rc.isEntity() && rc.getEntityHit().isEntityLivingBase())
/*      */       {
/* 1839 */         if (Keyboard.isKeyDown(157)) {
/* 1840 */           getNetHandler().handleCreateFile(EntityStatsDump.generatePacketFor(rc.getEntityHit().getAsEntityLivingBase()));
/*      */         } else {
/* 1842 */           this.thePlayer.sendPacket((new Packet85SimpleSignal(EnumSignal.entity_stats_dump)).setEntityID(rc.getEntityHit()));
/*      */         } 
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1848 */     if (this.thePlayer.isGhost() || this.thePlayer.isZevimrgvInTournament()) {
/*      */       return;
/*      */     }
/* 1851 */     if (button == 0) {
/*      */       
/* 1853 */       if (this.leftClickCounter > 0) {
/*      */         return;
/*      */       }
/* 1856 */       if (this.thePlayer.fishEntity != null) {
/*      */         return;
/*      */       }
/* 1859 */       if (this.playerController.listening_for_auto_use_mode_click && this.thePlayer.getHeldItemStack() != null && this.playerController.isItemStackEligibleForAUM(this.thePlayer.getHeldItemStack())) {
/*      */         
/* 1861 */         this.leftClickCounter = 5;
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
/*      */         return;
/*      */       } 
/* 1876 */     } else if (button == 1) {
/*      */       
/* 1878 */       if (this.right_click_counter > 0) {
/*      */         return;
/*      */       }
/* 1881 */       if (this.gameSettings.keyBindAttack.pressed) {
/*      */         return;
/*      */       }
/* 1884 */       if (this.playerController.curBlockDamageMP > 0.0F) {
/*      */ 
/*      */         
/* 1887 */         if (tryAutoSwitchOrRestock())
/*      */         {
/* 1889 */           this.playerController.setUseButtonDelay();
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1901 */       this.playerController.clearAutoHarvestMode();
/*      */ 
/*      */       
/* 1904 */       if (!this.playerController.useButtonEnabled()) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1911 */     ItemStack hotbar_selection = this.thePlayer.inventory.getCurrentItemStack();
/* 1912 */     int hotbar_selection_index = this.thePlayer.inventory.currentItem;
/*      */     
/* 1914 */     if (button == 0) {
/*      */       
/* 1916 */       boolean done = false;
/*      */       
/* 1918 */       if (this.objectMouseOver == null) {
/*      */         
/* 1920 */         if (button == 0 && this.playerController.isNotCreative()) {
/* 1921 */           this.leftClickCounter = 10;
/*      */         }
/* 1923 */       } else if (this.objectMouseOver.isEntity()) {
/*      */         
/* 1925 */         done = tryClickEntity(button);
/*      */       }
/* 1927 */       else if (this.objectMouseOver.isBlock()) {
/*      */         
/* 1929 */         done = blockClicked(button);
/*      */       } 
/*      */       
/* 1932 */       if (button == 0 && !this.playerController.cancel_swing) {
/* 1933 */         this.thePlayer.swingArm();
/*      */       } else {
/* 1935 */         this.playerController.cancel_swing = false;
/*      */       } 
/* 1937 */       if (this.thePlayer.inventory.currentItem != hotbar_selection_index)
/*      */       {
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1956 */       RightClickFilter filter = new RightClickFilter();
/*      */       
/* 1958 */       boolean click_caused_by_auto_use_mode = (!this.gameSettings.keyBindUseItem.pressed && this.playerController.inAutoUseMode());
/*      */       
/* 1960 */       if (click_caused_by_auto_use_mode) {
/* 1961 */         filter.setExclusive(4);
/*      */       }
/* 1963 */       filter = this.thePlayer.onPlayerRightClickChecked(this.objectMouseOver, filter, this.timer.renderPartialTicks, GuiScreen.isCtrlKeyDown());
/*      */       
/* 1965 */       if (this.thePlayer.rightClickCancelled()) {
/*      */         
/* 1967 */         this.thePlayer.clearRightClickCancelled();
/*      */       }
/* 1969 */       else if (!filter.allowsNoActions()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1977 */         if (this.thePlayer.isBlocking()) {
/* 1978 */           this.playerController.setUseButtonDelayOverride(10);
/*      */         } else {
/* 1980 */           this.playerController.setUseButtonDelay();
/*      */         } 
/* 1982 */         if (filter.allowsEntityInteraction()) {
/*      */           
/* 1984 */           this.thePlayer.sendPacket(new Packet81RightClick(this.thePlayer, this.objectMouseOver.getEntityHit()));
/*      */         } else {
/* 1986 */           this.thePlayer.sendPacket(new Packet81RightClick(this.thePlayer, this.timer.renderPartialTicks, filter));
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 1993 */       else if (!this.thePlayer.hasHeldItem()) {
/*      */         
/* 1995 */         if (tryAutoSwitchOrRestock()) {
/* 1996 */           this.playerController.setUseButtonDelay();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
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
/*      */   public void toggleFullscreen() {
/* 2138 */     if (Main.is_MITE_DS) {
/*      */       
/* 2140 */       this.fullscreen = false;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/* 2146 */       this.fullscreen = !this.fullscreen;
/*      */       
/* 2148 */       if (this.fullscreen) {
/*      */         
/* 2150 */         updateDisplayMode();
/* 2151 */         this.displayWidth = Display.getDisplayMode().getWidth();
/* 2152 */         this.displayHeight = Display.getDisplayMode().getHeight();
/*      */         
/* 2154 */         if (this.displayWidth <= 0)
/*      */         {
/* 2156 */           this.displayWidth = 1;
/*      */         }
/*      */         
/* 2159 */         if (this.displayHeight <= 0)
/*      */         {
/* 2161 */           this.displayHeight = 1;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 2166 */         Display.setDisplayMode(new DisplayMode(this.tempDisplayWidth, this.tempDisplayHeight));
/* 2167 */         this.displayWidth = this.tempDisplayWidth;
/* 2168 */         this.displayHeight = this.tempDisplayHeight;
/*      */         
/* 2170 */         if (this.displayWidth <= 0)
/*      */         {
/* 2172 */           this.displayWidth = 1;
/*      */         }
/*      */         
/* 2175 */         if (this.displayHeight <= 0)
/*      */         {
/* 2177 */           this.displayHeight = 1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2182 */       if (isGuiOpen())
/*      */       {
/* 2184 */         resize(this.displayWidth, this.displayHeight);
/*      */       }
/*      */       
/* 2187 */       Display.setFullscreen(this.fullscreen);
/*      */       
/* 2189 */       Display.setVSyncEnabled(this.gameSettings.isVsyncEnabled());
/* 2190 */       Display.update();
/*      */     }
/* 2192 */     catch (Exception var2) {
/*      */       
/* 2194 */       var2.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGuiOpen(boolean that_handles_mouse_input) {
/* 2202 */     if (that_handles_mouse_input) {
/* 2203 */       return (this.currentScreen != null && this.currentScreen.handlesMouseInput());
/*      */     }
/* 2205 */     return (this.currentScreen != null || isChatImposed());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGuiOpen() {
/* 2211 */     return isGuiOpen(false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChatImposed() {
/* 2217 */     return (this.imposed_gui_chat != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAnyChatOpen() {
/* 2222 */     return (isChatImposed() || this.currentScreen instanceof GuiChat);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public GuiChat getOpenChatGui() {
/* 2228 */     if (isChatImposed())
/* 2229 */       return this.imposed_gui_chat; 
/* 2230 */     if (this.currentScreen instanceof GuiChat) {
/* 2231 */       return (GuiChat)this.currentScreen;
/*      */     }
/* 2233 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resize(int par1, int par2) {
/* 2241 */     this.displayWidth = (par1 <= 0) ? 1 : par1;
/* 2242 */     this.displayHeight = (par2 <= 0) ? 1 : par2;
/*      */ 
/*      */     
/* 2245 */     if (isGuiOpen()) {
/*      */       
/* 2247 */       ScaledResolution var3 = new ScaledResolution(this.gameSettings, par1, par2);
/* 2248 */       int var4 = var3.getScaledWidth();
/* 2249 */       int var5 = var3.getScaledHeight();
/*      */ 
/*      */       
/* 2252 */       if (this.currentScreen != null) {
/* 2253 */         this.currentScreen.setWorldAndResolution(this, var4, var5);
/*      */       }
/* 2255 */       if (this.imposed_gui_chat != null) {
/* 2256 */         this.imposed_gui_chat.setWorldAndResolution(this, var4, var5);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void sendInputToGui() {
/* 2262 */     if (isChatImposed()) {
/* 2263 */       this.imposed_gui_chat.handleInput();
/* 2264 */     } else if (this.currentScreen != null) {
/* 2265 */       this.currentScreen.handleInput();
/*      */     } 
/*      */   }
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
/*      */   public void runTick() {
/* 2290 */     if (this.thePlayer != null && this.theWorld != null && this.currentScreen instanceof GuiGameOver && !this.theWorld.worldInfo.isHardcoreModeEnabled()) {
/* 2291 */       this.thePlayer.sendPacket((new Packet85SimpleSignal(EnumSignal.respawn_screen)).setShort(0));
/*      */     }
/* 2293 */     boolean game_is_paused_or_player_is_sleeping = (this.isGamePaused || (this.thePlayer != null && this.thePlayer.isSleeping()));
/*      */     
/* 2295 */     this.mcProfiler.startSection("stats");
/* 2296 */     this.statFileWriter.func_77449_e();
/* 2297 */     this.mcProfiler.endStartSection("gui");
/*      */ 
/*      */     
/* 2300 */     if (!game_is_paused_or_player_is_sleeping)
/*      */     {
/* 2302 */       this.ingameGUI.updateTick();
/*      */     }
/*      */     
/* 2305 */     this.mcProfiler.endStartSection("pick");
/* 2306 */     this.entityRenderer.getMouseOver(1.0F);
/* 2307 */     this.mcProfiler.endStartSection("gameMode");
/*      */ 
/*      */     
/* 2310 */     if (!game_is_paused_or_player_is_sleeping && this.theWorld != null)
/*      */     {
/* 2312 */       this.playerController.updateController();
/*      */     }
/*      */     
/* 2315 */     this.mcProfiler.endStartSection("textures");
/*      */ 
/*      */     
/* 2318 */     if (!game_is_paused_or_player_is_sleeping)
/*      */     {
/* 2320 */       this.renderEngine.tick();
/*      */     }
/*      */     
/* 2323 */     if (this.currentScreen == null && this.thePlayer != null) {
/*      */       
/* 2325 */       if (this.thePlayer.getHealth() <= 0.0F)
/*      */       {
/* 2327 */         displayGuiScreen((GuiScreen)null);
/*      */       
/*      */       }
/* 2330 */       else if (this.thePlayer.inBed() && this.theWorld != null)
/*      */       {
/* 2332 */         displayGuiScreen(new GuiSleepMP());
/*      */       }
/*      */     
/*      */     }
/* 2336 */     else if (this.currentScreen != null && this.currentScreen instanceof GuiSleepMP && !this.thePlayer.inBed()) {
/*      */       
/* 2338 */       displayGuiScreen((GuiScreen)null);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2343 */     if (isGuiOpen(true))
/*      */     {
/* 2345 */       this.leftClickCounter = 10000;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2352 */     if (isGuiOpen()) {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */         
/* 2358 */         sendInputToGui();
/*      */       }
/* 2360 */       catch (Throwable var6) {
/*      */         
/* 2362 */         CrashReport var2 = CrashReport.makeCrashReport(var6, "Updating screen events");
/* 2363 */         CrashReportCategory var3 = var2.makeCategory("Affected screen");
/* 2364 */         var3.addCrashSectionCallable("Screen name", new CallableUpdatingScreenName(this));
/* 2365 */         throw new ReportedException(var2);
/*      */       } 
/*      */ 
/*      */       
/* 2369 */       if (isGuiOpen()) {
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */           
/* 2375 */           if (this.currentScreen != null) {
/* 2376 */             this.currentScreen.updateScreen();
/*      */           }
/* 2378 */           if (isChatImposed()) {
/* 2379 */             this.imposed_gui_chat.updateScreen();
/*      */           }
/* 2381 */         } catch (Throwable var5) {
/*      */           
/* 2383 */           CrashReport var2 = CrashReport.makeCrashReport(var5, "Ticking screen");
/* 2384 */           CrashReportCategory var3 = var2.makeCategory("Affected screen");
/* 2385 */           var3.addCrashSectionCallable("Screen name", new CallableParticleScreenName(this));
/* 2386 */           throw new ReportedException(var2);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2391 */     if (Main.is_MITE_DS) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2396 */       if (this.currentScreen == null) {
/* 2397 */         displayGuiScreen(new GuiMITEDS());
/*      */       }
/* 2399 */       if (getIntegratedServer() != null && !getIntegratedServer().getPublic()) {
/* 2400 */         GuiShareToLan.shareToLAN();
/*      */       }
/*      */     } 
/* 2403 */     boolean initializing_imposed_chat = false;
/*      */     
/* 2405 */     if (this.thePlayer != null && this.gameSettings.chatVisibility != 2 && !isChatImposed() && (this.currentScreen == null || this.currentScreen.allowsImposedChat()) && !GuiScreen.isCtrlKeyDown()) {
/*      */       
/* 2407 */       if (Keyboard.isKeyDown(this.gameSettings.keyBindChat.keyCode)) {
/*      */         
/* 2409 */         while (this.gameSettings.keyBindChat.isPressed());
/*      */ 
/*      */         
/* 2412 */         openChat(new GuiChat(last_aborted_chat));
/*      */         
/* 2414 */         initializing_imposed_chat = true;
/*      */       } 
/*      */       
/* 2417 */       if (Keyboard.isKeyDown(this.gameSettings.keyBindCommand.keyCode)) {
/*      */         
/* 2419 */         while (this.gameSettings.keyBindCommand.isPressed());
/*      */         
/* 2421 */         openChat(new GuiChat("/"));
/*      */         
/* 2423 */         initializing_imposed_chat = true;
/*      */       } 
/*      */     } 
/*      */     
/* 2427 */     if (initializing_imposed_chat) {
/* 2428 */       KeyBinding.unPressAllKeys();
/*      */     }
/* 2430 */     if (this.thePlayer != null && this.playerController.inAutoUseMode()) {
/*      */       
/* 2432 */       ItemStack current_item_stack = this.thePlayer.inventory.getCurrentItemStack();
/*      */       
/* 2434 */       if (current_item_stack == null) {
/*      */         
/* 2436 */         if (isGuiOpen(true)) {
/* 2437 */           this.playerController.clearAutoUseMode();
/*      */         }
/* 2439 */       } else if (current_item_stack.getItem() != this.playerController.auto_use_mode_item) {
/*      */         
/* 2441 */         this.playerController.clearAutoUseMode();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2446 */     if (this.currentScreen == null || this.currentScreen.allowUserInput || isChatImposed()) {
/*      */       
/* 2448 */       this.mcProfiler.endStartSection("mouse");
/*      */ 
/*      */       
/* 2451 */       while (Mouse.next()) {
/*      */         
/* 2453 */         int var1 = Mouse.getEventButton();
/*      */         
/* 2455 */         if (isRunningOnMac && var1 == 0 && (Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)))
/*      */         {
/* 2457 */           var1 = 1;
/*      */         }
/*      */         
/* 2460 */         KeyBinding.setKeyBindState(var1 - 100, Mouse.getEventButtonState());
/*      */         
/* 2462 */         if (Mouse.getEventButtonState())
/*      */         {
/* 2464 */           KeyBinding.onTick(var1 - 100);
/*      */         }
/*      */         
/* 2467 */         long var9 = getSystemTime() - this.systemTime;
/*      */         
/* 2469 */         if (var9 <= 200L) {
/*      */           
/* 2471 */           int var4 = Mouse.getEventDWheel();
/*      */           
/* 2473 */           if (var4 != 0) {
/*      */             
/* 2475 */             this.thePlayer.inventory.changeCurrentItem(var4);
/*      */             
/* 2477 */             if (this.gameSettings.noclip) {
/*      */               
/* 2479 */               if (var4 > 0)
/*      */               {
/* 2481 */                 var4 = 1;
/*      */               }
/*      */               
/* 2484 */               if (var4 < 0)
/*      */               {
/* 2486 */                 var4 = -1;
/*      */               }
/*      */               
/* 2489 */               this.gameSettings.noclipRate += var4 * 0.25F;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 2494 */           if (!isGuiOpen()) {
/*      */             
/* 2496 */             if (!this.inGameHasFocus && Mouse.getEventButtonState())
/*      */             {
/* 2498 */               setIngameFocus(); } 
/*      */             continue;
/*      */           } 
/* 2501 */           if (this.currentScreen != null)
/*      */           {
/* 2503 */             this.currentScreen.handleMouseInput();
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 2508 */       if (this.leftClickCounter > 0)
/*      */       {
/* 2510 */         this.leftClickCounter--;
/*      */       }
/*      */       
/* 2513 */       if (this.right_click_counter > 0) {
/* 2514 */         this.right_click_counter--;
/*      */       }
/* 2516 */       this.mcProfiler.endStartSection("keyboard");
/*      */ 
/*      */       
/* 2519 */       while (Keyboard.next()) {
/*      */         
/* 2521 */         KeyBinding.setKeyBindState(Keyboard.getEventKey(), Keyboard.getEventKeyState());
/*      */         
/* 2523 */         if (Keyboard.getEventKeyState())
/*      */         {
/* 2525 */           KeyBinding.onTick(Keyboard.getEventKey());
/*      */         }
/*      */         
/* 2528 */         if (this.field_83002_am > 0L) {
/*      */           
/* 2530 */           if (getSystemTime() - this.field_83002_am >= 6000L)
/*      */           {
/* 2532 */             throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
/*      */           }
/*      */           
/* 2535 */           if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61))
/*      */           {
/* 2537 */             this.field_83002_am = -1L;
/*      */           }
/*      */         }
/* 2540 */         else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
/*      */           
/* 2542 */           this.field_83002_am = getSystemTime();
/*      */         } 
/*      */         
/* 2545 */         if (Keyboard.getEventKeyState()) {
/*      */           
/* 2547 */           if (Main.is_MITE_DS) {
/*      */             
/* 2549 */             if (Keyboard.getEventKey() == 1)
/* 2550 */               displayInGameMenu(); 
/*      */             continue;
/*      */           } 
/* 2553 */           if (Keyboard.getEventKey() == 87) {
/*      */             
/* 2555 */             toggleFullscreen();
/*      */             
/*      */             continue;
/*      */           } 
/* 2559 */           if (isChatImposed()) {
/*      */             
/* 2561 */             if (!initializing_imposed_chat) {
/* 2562 */               this.imposed_gui_chat.handleKeyboardInput();
/*      */             }
/* 2564 */           } else if (this.currentScreen != null) {
/*      */             
/* 2566 */             this.currentScreen.handleKeyboardInput();
/*      */           }
/*      */           else {
/*      */             
/* 2570 */             if (Keyboard.getEventKey() == 1)
/*      */             {
/* 2572 */               displayInGameMenu();
/*      */             }
/*      */             
/* 2575 */             if (Keyboard.getEventKey() == 31 && Keyboard.isKeyDown(61))
/*      */             {
/* 2577 */               refreshResources();
/*      */             }
/*      */             
/* 2580 */             if (Keyboard.getEventKey() == 20 && Keyboard.isKeyDown(61))
/*      */             {
/* 2582 */               refreshResources();
/*      */             }
/*      */             
/* 2585 */             if (Keyboard.getEventKey() == 33 && Keyboard.isKeyDown(61)) {
/*      */               
/* 2587 */               int i = Keyboard.isKeyDown(42) | Keyboard.isKeyDown(54);
/* 2588 */               this.gameSettings.setOptionValue(EnumOptions.RENDER_DISTANCE, (i != 0) ? -1 : 1);
/*      */             } 
/*      */             
/* 2591 */             if (Keyboard.getEventKey() == 30 && Keyboard.isKeyDown(61))
/*      */             {
/* 2593 */               this.renderGlobal.loadRenderers();
/*      */             }
/*      */             
/* 2596 */             if (Keyboard.getEventKey() == 35 && Keyboard.isKeyDown(61)) {
/*      */               
/* 2598 */               this.gameSettings.advancedItemTooltips = !this.gameSettings.advancedItemTooltips;
/* 2599 */               this.gameSettings.saveOptions();
/*      */             } 
/*      */             
/* 2602 */             if (Keyboard.getEventKey() == 48 && Keyboard.isKeyDown(61))
/*      */             {
/* 2604 */               RenderManager.field_85095_o = !RenderManager.field_85095_o;
/*      */             }
/*      */             
/* 2607 */             if (Keyboard.getEventKey() == 25 && Keyboard.isKeyDown(61)) {
/*      */               
/* 2609 */               this.gameSettings.pauseOnLostFocus = !this.gameSettings.pauseOnLostFocus;
/* 2610 */               this.gameSettings.saveOptions();
/*      */             } 
/*      */ 
/*      */             
/* 2614 */             if (Keyboard.getEventKey() == 59)
/*      */             {
/*      */               
/* 2617 */               if (++this.gameSettings.gui_mode > 2) {
/* 2618 */                 this.gameSettings.gui_mode = 0;
/*      */               }
/*      */             }
/* 2621 */             if (Keyboard.getEventKey() == 61) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2626 */               if (!inDevMode()) {
/*      */                 
/* 2628 */                 this.gameSettings.showDebugInfo = !this.gameSettings.showDebugInfo;
/* 2629 */                 this.gameSettings.showDebugProfilerChart = false;
/*      */               } 
/*      */               
/* 2632 */               if (inDevMode() && Keyboard.isKeyDown(157)) {
/*      */                 
/* 2634 */                 this.gameSettings.showDebugInfo = !this.gameSettings.showDebugInfo;
/* 2635 */                 Debug.is_active = !Debug.is_active;
/*      */                 
/* 2637 */                 if (Debug.is_active) {
/*      */                   
/* 2639 */                   this.gameSettings.showDebugInfo = true;
/*      */                 }
/* 2641 */                 else if (this.thePlayer.capabilities.isCreativeMode) {
/*      */                   
/* 2643 */                   this.thePlayer.sendChatMessage("/gamemode survival", true);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/* 2649 */             if (this.gameSettings.keyBindRedrawChunks.isPressed())
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2658 */               this.renderGlobal.markAllRenderersUninitialized();
/*      */             }
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
/* 2671 */             if (this.gameSettings.keyBindZoom.isPressed()) {
/* 2672 */               this.thePlayer.zoomed = !this.thePlayer.zoomed;
/*      */             }
/* 2674 */             if (this.gameSettings.keyBindToggleRun.isPressed()) {
/* 2675 */               this.playerController.toggleRun(this.thePlayer);
/*      */             }
/* 2677 */             if (Keyboard.isKeyDown(46)) {
/* 2678 */               clearErrorMessage();
/*      */             }
/* 2680 */             if (inDevMode()) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2685 */               if (Keyboard.isKeyDown(19)) {
/* 2686 */                 Debug.general_counter = 0;
/*      */               }
/* 2688 */               if (Keyboard.isKeyDown(20) && GuiScreen.isShiftKeyDown()) {
/* 2689 */                 this.playerController.sendPacket(new Packet85SimpleSignal(EnumSignal.terraform));
/*      */               }
/* 2691 */               if (Keyboard.isKeyDown(20) && GuiScreen.isCtrlKeyDown()) {
/*      */                 
/* 2693 */                 RaycastCollision rc = this.thePlayer.getSelectedObject(this.timer.renderPartialTicks, false, true, (EnumEntityReachContext)null);
/*      */                 
/* 2695 */                 if (rc != null && rc.isEntity()) {
/*      */                   
/* 2697 */                   Entity entity = rc.getEntityHit();
/*      */                   
/* 2699 */                   entity.tagged = !entity.tagged;
/* 2700 */                   this.thePlayer.sendPacket((new Packet85SimpleSignal(EnumSignal.tag_entity)).setBoolean(entity.tagged).setEntityID(entity));
/*      */                 } 
/*      */               } 
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
/* 2722 */               if (Keyboard.isKeyDown(12)) {
/*      */                 
/* 2724 */                 int delta_ticks = GuiScreen.isCtrlKeyDown() ? -this.theWorld.getTimeOfDay() : -1000;
/*      */                 
/* 2726 */                 this.playerController.sendPacket((new Packet85SimpleSignal(EnumSignal.change_world_time)).setBoolean(false).setInteger(delta_ticks));
/*      */               } 
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
/* 2753 */               if (Keyboard.isKeyDown(78)) {
/*      */                 
/* 2755 */                 int delta_ticks = 1000;
/*      */                 
/* 2757 */                 this.playerController.sendPacket((new Packet85SimpleSignal(EnumSignal.change_world_time)).setBoolean(GuiScreen.isCtrlKeyDown()).setInteger(delta_ticks));
/*      */               } 
/*      */               
/* 2760 */               if (Keyboard.isKeyDown(33))
/*      */               {
/* 2762 */                 if (GuiScreen.isCtrlKeyDown()) {
/* 2763 */                   EntityRenderer.disable_fog = !EntityRenderer.disable_fog;
/*      */                 } else {
/* 2765 */                   Debug.flag = !Debug.flag;
/*      */                 } 
/*      */               }
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
/* 2794 */               if (Debug.is_active && Keyboard.getEventKey() == 46 && Keyboard.isKeyDown(157))
/*      */               {
/* 2796 */                 if (this.thePlayer.capabilities.isCreativeMode) {
/* 2797 */                   this.thePlayer.sendChatMessage("/gamemode survival", true);
/*      */                 } else {
/*      */                   
/* 2800 */                   this.thePlayer.sendChatMessage("/gamemode creative", true);
/*      */                 } 
/*      */               }
/* 2803 */               if (Keyboard.isKeyDown(52)) {
/*      */                 
/* 2805 */                 see_through_block_tops = !see_through_block_tops;
/*      */                 
/* 2807 */                 this.renderGlobal.loadRenderers();
/*      */               } 
/*      */               
/* 2810 */               if (Keyboard.isKeyDown(211)) {
/* 2811 */                 this.thePlayer.sendPacket(new Packet85SimpleSignal(EnumSignal.delete_selection));
/*      */               }
/* 2813 */               if (Keyboard.isKeyDown(49)) {
/* 2814 */                 night_vision_override = !night_vision_override;
/*      */               }
/* 2816 */               if (Keyboard.isKeyDown(34)) {
/* 2817 */                 System.gc();
/*      */               }
/*      */             } 
/*      */             
/* 2821 */             if (Keyboard.getEventKey() == 63 && !this.thePlayer.isLockedInFirstPersonView()) {
/*      */               
/* 2823 */               this.gameSettings.thirdPersonView++;
/*      */               
/* 2825 */               if (this.gameSettings.thirdPersonView > 2)
/*      */               {
/* 2827 */                 this.gameSettings.thirdPersonView = 0;
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 2833 */             if (Keyboard.getEventKey() == 66)
/*      */             {
/* 2835 */               this.gameSettings.smoothCamera = !this.gameSettings.smoothCamera;
/*      */             }
/*      */           } 
/*      */           int var1;
/* 2839 */           for (var1 = 0; var1 < 9; var1++) {
/*      */             
/* 2841 */             if (Keyboard.getEventKey() == 2 + var1)
/*      */             {
/* 2843 */               this.thePlayer.inventory.currentItem = var1;
/*      */             }
/*      */           } 
/*      */           
/* 2847 */           if (this.gameSettings.showDebugInfo && this.gameSettings.showDebugProfilerChart) {
/*      */             
/* 2849 */             if (Keyboard.getEventKey() == 11)
/*      */             {
/* 2851 */               updateDebugProfilerName(0);
/*      */             }
/*      */             
/* 2854 */             for (var1 = 0; var1 < 9; var1++) {
/*      */               
/* 2856 */               if (Keyboard.getEventKey() == 2 + var1)
/*      */               {
/* 2858 */                 updateDebugProfilerName(var1 + 1);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2866 */       boolean var8 = (this.gameSettings.chatVisibility != 2);
/*      */       
/* 2868 */       while (this.gameSettings.keyBindInventory.isPressed()) {
/*      */         
/* 2870 */         if (this.playerController.func_110738_j()) {
/*      */           
/* 2872 */           this.thePlayer.func_110322_i();
/*      */           
/*      */           continue;
/*      */         } 
/* 2876 */         displayGuiScreen(new GuiInventory(this.thePlayer));
/*      */       } 
/*      */ 
/*      */       
/* 2880 */       while (this.gameSettings.keyBindDrop.isPressed())
/*      */       {
/* 2882 */         this.thePlayer.dropOneItem(GuiScreen.isCtrlKeyDown());
/*      */       }
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
/* 2895 */       if (this.playerController.curBlockDamageMP > 0.0F && this.playerController.inAutoUseMode()) {
/* 2896 */         this.playerController.resetBlockRemoving();
/*      */       }
/* 2898 */       if (this.thePlayer.isUsingItem()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2904 */         if (isGuiOpen(true) || (!this.gameSettings.keyBindUseItem.pressed && !this.playerController.inAutoUseMode()))
/*      */         {
/*      */           
/* 2907 */           this.thePlayer.stopUsingItem();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         do {
/*      */         
/* 2914 */         } while (this.gameSettings.keyBindAttack.isPressed());
/*      */         
/* 2916 */         while (this.gameSettings.keyBindUseItem.isPressed());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2923 */         while (this.gameSettings.keyBindPickBlock.isPressed());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2935 */         while (this.gameSettings.keyBindAttack.isPressed())
/*      */         {
/* 2937 */           clickMouse(0);
/*      */         }
/*      */         
/* 2940 */         while (this.gameSettings.keyBindUseItem.isPressed())
/*      */         {
/* 2942 */           clickMouse(1);
/*      */         }
/*      */         
/* 2945 */         while (this.gameSettings.keyBindPickBlock.isPressed())
/*      */         {
/* 2947 */           clickMiddleMouseButton();
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2956 */       ItemStack current_item_stack = this.thePlayer.inventory.getCurrentItemStack();
/*      */ 
/*      */ 
/*      */       
/* 2960 */       if (!isGuiOpen(true) && (this.gameSettings.keyBindUseItem.pressed || this.playerController.inAutoUseMode()) && !this.thePlayer.isUsingItem()) {
/* 2961 */         clickMouse(1);
/*      */       }
/* 2963 */       if (this.thePlayer.swing_item_pending) {
/* 2964 */         this.thePlayer.swingArm(true);
/*      */       }
/*      */       
/* 2967 */       if (current_item_stack != null && current_item_stack.stackSize > 0 && current_item_stack.getItem() == this.playerController.last_used_item) {
/* 2968 */         this.playerController.item_switch_or_restock_pending = false;
/*      */       }
/* 2970 */       if (this.playerController.item_switch_or_restock_pending)
/*      */       {
/*      */         
/* 2973 */         if (!this.thePlayer.inventory.trySwitchItemOrRestock(this.playerController.last_used_item, this.playerController.last_used_item_subtype, true) && this.playerController.autoStockEnabled())
/*      */         {
/*      */           
/* 2976 */           this.playerController.clearAutoUseMode();
/*      */         }
/*      */       }
/*      */       
/* 2980 */       if (this.playerController.auto_harvest_block != null && this.playerController.autoHarvestModeHasExpired()) {
/* 2981 */         this.playerController.clearAutoHarvestMode();
/*      */       }
/*      */ 
/*      */       
/* 2985 */       if (!this.thePlayer.isUsingItem() && (this.gameSettings.keyBindAttack.pressed || !this.gameSettings.keyBindUseItem.pressed))
/*      */       {
/* 2987 */         sendClickBlockToController(0, (!isGuiOpen(true) && (this.gameSettings.keyBindAttack.pressed || (this.objectMouseOver != null && this.objectMouseOver.isBlock() && this.playerController.matchesAutoHarvestBlock(this.objectMouseOver.block_hit_x, this.objectMouseOver.block_hit_y, this.objectMouseOver.block_hit_z))) && this.inGameHasFocus));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2993 */       if (this.playerController.listening_for_auto_harvest_mode_click && this.playerController.listening_for_auto_use_mode_click) {
/* 2994 */         setErrorMessage("Listening for both AHM and AUM clicks");
/*      */       }
/* 2996 */       if (this.gameSettings.keyBindAttack.pressed && this.gameSettings.keyBindUseItem.pressed) {
/*      */         
/* 2998 */         if (this.playerController.listening_for_auto_harvest_mode_click && this.objectMouseOver != null && this.objectMouseOver.isBlock())
/*      */         {
/* 3000 */           this.playerController.setAutoHarvestMode(this.objectMouseOver.block_hit_x, this.objectMouseOver.block_hit_y, this.objectMouseOver.block_hit_z);
/*      */         }
/*      */         
/* 3003 */         if (this.playerController.listening_for_auto_use_mode_click && current_item_stack != null)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 3008 */           this.playerController.setAutoUseMode(current_item_stack);
/*      */         
/*      */         }
/*      */       }
/* 3012 */       else if (this.gameSettings.keyBindAttack.pressed) {
/*      */         
/* 3014 */         if (this.playerController.cancel_auto_harvest_on_next_click) {
/* 3015 */           this.playerController.clearAutoHarvestMode();
/*      */         } else {
/* 3017 */           this.playerController.setListeningForAutoHarvestMode();
/*      */         } 
/* 3019 */         if (this.playerController.cancel_auto_use_mode_on_next_click) {
/* 3020 */           this.playerController.clearAutoUseMode();
/*      */         }
/* 3022 */       } else if (this.gameSettings.keyBindUseItem.pressed) {
/*      */ 
/*      */         
/* 3025 */         if (current_item_stack != null && (!(current_item_stack.getItem() instanceof ItemBlock) || current_item_stack.getItemInUseAction(this.thePlayer) != null)) {
/* 3026 */           this.playerController.setListeningForAutoUseMode();
/*      */         
/*      */         }
/*      */       }
/* 3030 */       else if (this.playerController.inAutoUseMode()) {
/* 3031 */         this.playerController.cancel_auto_use_mode_on_next_click = true;
/*      */       } 
/*      */       
/* 3034 */       if (!this.gameSettings.keyBindAttack.pressed) {
/*      */         
/* 3036 */         this.leftClickCounter = 0;
/* 3037 */         this.playerController.cancel_auto_harvest_on_next_click = true;
/*      */       } 
/*      */ 
/*      */       
/* 3041 */       if (!this.gameSettings.keyBindUseItem.pressed)
/*      */       {
/* 3043 */         this.playerController.listening_for_auto_use_mode_click = false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3048 */     if (this.theWorld != null) {
/*      */       
/* 3050 */       if (this.thePlayer != null) {
/*      */         
/* 3052 */         this.joinPlayerCounter++;
/*      */         
/* 3054 */         if (this.joinPlayerCounter == 30) {
/*      */           
/* 3056 */           this.joinPlayerCounter = 0;
/* 3057 */           this.theWorld.joinEntityInSurroundings(this.thePlayer);
/*      */         } 
/*      */       } 
/*      */       
/* 3061 */       this.mcProfiler.endStartSection("gameRenderer");
/*      */ 
/*      */       
/* 3064 */       if (!game_is_paused_or_player_is_sleeping)
/*      */       {
/* 3066 */         this.entityRenderer.updateRenderer();
/*      */       }
/*      */       
/* 3069 */       this.mcProfiler.endStartSection("levelRenderer");
/*      */ 
/*      */       
/* 3072 */       if (!game_is_paused_or_player_is_sleeping)
/*      */       {
/* 3074 */         this.renderGlobal.updateClouds();
/*      */       }
/*      */       
/* 3077 */       this.mcProfiler.endStartSection("level");
/*      */ 
/*      */ 
/*      */       
/* 3081 */       if (!game_is_paused_or_player_is_sleeping) {
/*      */         
/* 3083 */         if (this.theWorld.lastLightningBolt > 0)
/*      */         {
/* 3085 */           this.theWorld.lastLightningBolt--;
/*      */         }
/*      */         
/* 3088 */         this.theWorld.updateEntities();
/*      */       } 
/*      */       
/* 3091 */       if (!this.isGamePaused) {
/*      */ 
/*      */         
/* 3094 */         this.theWorld.setAllowedSpawnTypes((this.theWorld.difficultySetting > 0), true);
/*      */ 
/*      */         
/*      */         try {
/* 3098 */           this.theWorld.tick();
/*      */         }
/* 3100 */         catch (Throwable var7) {
/*      */           
/* 3102 */           CrashReport var2 = CrashReport.makeCrashReport(var7, "Exception in world tick");
/*      */           
/* 3104 */           if (this.theWorld == null) {
/*      */             
/* 3106 */             CrashReportCategory var3 = var2.makeCategory("Affected level");
/* 3107 */             var3.addCrashSection("Problem", "Level is null!");
/*      */           }
/*      */           else {
/*      */             
/* 3111 */             this.theWorld.addWorldInfoToCrashReport(var2);
/*      */           } 
/*      */           
/* 3114 */           throw new ReportedException(var2);
/*      */         } 
/*      */       } 
/*      */       
/* 3118 */       this.mcProfiler.endStartSection("animateTick");
/*      */ 
/*      */       
/* 3121 */       if (!game_is_paused_or_player_is_sleeping && this.theWorld != null)
/*      */       {
/* 3123 */         this.theWorld.doVoidFogParticles(MathHelper.floor_double(this.thePlayer.posX), MathHelper.floor_double(this.thePlayer.posY), MathHelper.floor_double(this.thePlayer.posZ));
/*      */       }
/*      */       
/* 3126 */       this.mcProfiler.endStartSection("particles");
/*      */ 
/*      */       
/* 3129 */       if (!game_is_paused_or_player_is_sleeping)
/*      */       {
/* 3131 */         this.effectRenderer.updateEffects();
/*      */       }
/*      */     }
/* 3134 */     else if (this.myNetworkManager != null) {
/*      */       
/* 3136 */       this.mcProfiler.endStartSection("pendingConnection");
/* 3137 */       this.myNetworkManager.processReadPackets();
/*      */     } 
/*      */     
/* 3140 */     this.mcProfiler.endSection();
/* 3141 */     this.systemTime = getSystemTime();
/*      */   }
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
/*      */   public void launchIntegratedServer(String par1Str, String par2Str, WorldSettings par3WorldSettings) {
/* 3154 */     loadWorld((WorldClient)null);
/* 3155 */     System.gc();
/* 3156 */     ISaveHandler var4 = this.saveLoader.getSaveLoader(par1Str, false);
/* 3157 */     WorldInfo var5 = var4.loadWorldInfo();
/*      */     
/* 3159 */     if (var5 == null && par3WorldSettings != null) {
/*      */       
/* 3161 */       var5 = new WorldInfo(par3WorldSettings, par1Str);
/* 3162 */       var4.saveWorldInfo(var5);
/*      */     } 
/*      */     
/* 3165 */     if (par3WorldSettings == null)
/*      */     {
/* 3167 */       par3WorldSettings = new WorldSettings(var5);
/*      */     }
/*      */     
/* 3170 */     this.statFileWriter.readStat(StatList.startGameStat, 1);
/* 3171 */     this.theIntegratedServer = new IntegratedServer(this, par1Str, par2Str, par3WorldSettings);
/* 3172 */     this.theIntegratedServer.startServerThread();
/* 3173 */     this.integratedServerIsRunning = true;
/* 3174 */     this.loadingScreen.displayProgressMessage(I18n.getString("menu.loadingLevel"));
/*      */     
/* 3176 */     while (!this.theIntegratedServer.serverIsInRunLoop()) {
/*      */       
/* 3178 */       String var6 = this.theIntegratedServer.getUserMessage();
/*      */       
/* 3180 */       if (var6 != null) {
/*      */         
/* 3182 */         this.loadingScreen.resetProgresAndWorkingMessage(I18n.getString(var6));
/*      */       }
/*      */       else {
/*      */         
/* 3186 */         this.loadingScreen.resetProgresAndWorkingMessage("");
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 3191 */         Thread.sleep(200L);
/*      */       }
/* 3193 */       catch (InterruptedException var9) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3199 */     displayGuiScreen((GuiScreen)null);
/* 3200 */     closeImposedChat();
/*      */ 
/*      */     
/*      */     try {
/* 3204 */       NetClientHandler var10 = new NetClientHandler(this, this.theIntegratedServer);
/* 3205 */       this.myNetworkManager = var10.getNetManager();
/*      */     }
/* 3207 */     catch (IOException var8) {
/*      */       
/* 3209 */       displayCrashReport(addGraphicsAndWorldToCrashReport(new CrashReport("Connecting to integrated server", var8)));
/*      */     } 
/*      */     
/* 3212 */     this.increment_startGameStat_asap = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadWorld(WorldClient par1WorldClient) {
/* 3220 */     loadWorld(par1WorldClient, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadWorld(WorldClient par1WorldClient, String par2Str) {
/* 3228 */     this.statFileWriter.syncStats();
/*      */     
/* 3230 */     if (par1WorldClient == null) {
/*      */       
/* 3232 */       NetClientHandler var3 = getNetHandler();
/*      */       
/* 3234 */       if (var3 != null)
/*      */       {
/* 3236 */         var3.cleanup();
/*      */       }
/*      */       
/* 3239 */       if (this.myNetworkManager != null)
/*      */       {
/* 3241 */         this.myNetworkManager.closeConnections();
/*      */       }
/*      */       
/* 3244 */       if (this.theIntegratedServer != null)
/*      */       {
/* 3246 */         this.theIntegratedServer.initiateShutdown();
/*      */       }
/*      */       
/* 3249 */       this.theIntegratedServer = null;
/*      */     } 
/*      */     
/* 3252 */     this.renderViewEntity = null;
/* 3253 */     this.myNetworkManager = null;
/*      */     
/* 3255 */     if (this.loadingScreen != null) {
/*      */       
/* 3257 */       this.loadingScreen.resetProgressAndMessage(par2Str);
/* 3258 */       this.loadingScreen.resetProgresAndWorkingMessage("");
/*      */     } 
/*      */     
/* 3261 */     if (par1WorldClient == null && this.theWorld != null) {
/*      */       
/* 3263 */       setServerData((ServerData)null);
/* 3264 */       this.integratedServerIsRunning = false;
/*      */     } 
/*      */     
/* 3267 */     this.sndManager.playStreaming((String)null, 0.0F, 0.0F, 0.0F);
/* 3268 */     this.sndManager.stopAllSounds();
/*      */     
/* 3270 */     this.theWorld = par1WorldClient;
/*      */     
/* 3272 */     if (par1WorldClient != null) {
/*      */       
/* 3274 */       if (this.renderGlobal != null)
/*      */       {
/* 3276 */         this.renderGlobal.setWorldAndLoadRenderers(par1WorldClient);
/*      */       }
/*      */       
/* 3279 */       if (this.effectRenderer != null)
/*      */       {
/* 3281 */         this.effectRenderer.clearEffects(par1WorldClient);
/*      */       }
/*      */       
/* 3284 */       if (this.thePlayer == null) {
/*      */         
/* 3286 */         this.thePlayer = this.playerController.func_78754_a(par1WorldClient);
/* 3287 */         this.playerController.flipPlayer(this.thePlayer);
/*      */       } 
/*      */       
/* 3290 */       this.thePlayer.preparePlayerToSpawn();
/* 3291 */       par1WorldClient.spawnEntityInWorld(this.thePlayer);
/* 3292 */       this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
/* 3293 */       this.playerController.setPlayerCapabilities(this.thePlayer);
/* 3294 */       this.renderViewEntity = this.thePlayer;
/*      */     }
/*      */     else {
/*      */       
/* 3298 */       this.saveLoader.flushCache();
/* 3299 */       this.thePlayer = null;
/*      */     } 
/*      */     
/* 3302 */     System.gc();
/* 3303 */     this.systemTime = 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getSecretErrorMessage() {
/* 3308 */     String[] message = { "!", "d", "e", "r", "e", "t", "n", "e", " ", "e", "b", " ", "t", "o", "n", " ", "d", "l", "u", "o", "c", " ", "e", "d", "o", "m", " ", "v", "e", "D" };
/*      */     
/* 3310 */     StringBuffer sb = new StringBuffer();
/*      */     
/* 3312 */     for (int i = message.length - 1; i >= 0; i--) {
/* 3313 */       sb.append(message[i]);
/*      */     }
/* 3315 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String debugInfoRenders() {
/* 3323 */     return this.renderGlobal.getDebugInfoRenders();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEntityDebug() {
/* 3331 */     return this.renderGlobal.getDebugInfoEntities();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getWorldProviderName() {
/* 3339 */     return this.theWorld.getProviderName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String debugInfoEntities() {
/* 3347 */     return "P: " + this.effectRenderer.getStatistics() + ". T: " + this.theWorld.getDebugLoadedEntities();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDimensionAndSpawnPlayer(int par1) {
/* 3352 */     this.theWorld.setSpawnLocation();
/* 3353 */     this.theWorld.removeAllEntities();
/* 3354 */     int var2 = 0;
/* 3355 */     String var3 = null;
/*      */     
/* 3357 */     if (this.thePlayer != null) {
/*      */       
/* 3359 */       var2 = this.thePlayer.entityId;
/* 3360 */       this.theWorld.removeEntity(this.thePlayer);
/* 3361 */       var3 = this.thePlayer.func_142021_k();
/*      */     } 
/*      */     
/* 3364 */     this.renderViewEntity = null;
/* 3365 */     this.thePlayer = this.playerController.func_78754_a(this.theWorld);
/* 3366 */     this.thePlayer.dimension = par1;
/* 3367 */     this.renderViewEntity = this.thePlayer;
/* 3368 */     this.thePlayer.preparePlayerToSpawn();
/* 3369 */     this.thePlayer.func_142020_c(var3);
/* 3370 */     this.theWorld.spawnEntityInWorld(this.thePlayer);
/* 3371 */     this.playerController.flipPlayer(this.thePlayer);
/* 3372 */     this.thePlayer.movementInput = new MovementInputFromOptions(this.gameSettings);
/* 3373 */     this.thePlayer.entityId = var2;
/* 3374 */     this.playerController.setPlayerCapabilities(this.thePlayer);
/*      */     
/* 3376 */     if (this.currentScreen instanceof GuiGameOver)
/*      */     {
/* 3378 */       displayGuiScreen((GuiScreen)null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isDemo() {
/* 3387 */     return this.isDemo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NetClientHandler getNetHandler() {
/* 3395 */     return (this.thePlayer != null) ? this.thePlayer.sendQueue : null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGuiEnabled() {
/* 3401 */     return (theMinecraft == null || theMinecraft.gameSettings.gui_mode == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFancyGraphicsEnabled() {
/* 3407 */     return (theMinecraft != null && theMinecraft.gameSettings.isFancyGraphicsEnabled());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAmbientOcclusionEnabled() {
/* 3415 */     return (theMinecraft != null && theMinecraft.gameSettings.ambientOcclusion != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handleClientCommand(String par1Str) {
/* 3424 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clickMiddleMouseButton() {
/* 3432 */     if (this.objectMouseOver != null) {
/*      */       int var2;
/* 3434 */       boolean var1 = this.thePlayer.capabilities.isCreativeMode;
/* 3435 */       int var3 = 0;
/* 3436 */       boolean var4 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3441 */       if (this.objectMouseOver.isBlock()) {
/*      */         
/* 3443 */         int var5 = this.objectMouseOver.block_hit_x;
/* 3444 */         int var6 = this.objectMouseOver.block_hit_y;
/* 3445 */         int var7 = this.objectMouseOver.block_hit_z;
/* 3446 */         Block var8 = Block.blocksList[this.theWorld.getBlockId(var5, var6, var7)];
/*      */         
/* 3448 */         if (var8 == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 3453 */         var2 = var8.idPicked(this.theWorld, var5, var6, var7);
/*      */         
/* 3455 */         if (var2 == 0) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 3460 */         var4 = Item.itemsList[var2].getHasSubtypes();
/* 3461 */         int var9 = (var2 < 256 && !Block.blocksList[var8.blockID].isFlowerPot()) ? var2 : var8.blockID;
/*      */         
/* 3463 */         var3 = Block.blocksList[var9].getItemSubtype(this.theWorld.getBlockMetadata(var5, var6, var7));
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3468 */         if (!this.objectMouseOver.isEntity() || this.objectMouseOver.getEntityHit() == null || !var1) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 3473 */         Entity entity_hit = this.objectMouseOver.getEntityHit();
/*      */ 
/*      */         
/* 3476 */         if (entity_hit instanceof EntityPainting) {
/*      */           
/* 3478 */           var2 = Item.painting.itemID;
/*      */         
/*      */         }
/* 3481 */         else if (entity_hit instanceof EntityLeashKnot) {
/*      */           
/* 3483 */           var2 = Item.leash.itemID;
/*      */         
/*      */         }
/* 3486 */         else if (entity_hit instanceof EntityItemFrame) {
/*      */ 
/*      */           
/* 3489 */           EntityItemFrame var10 = (EntityItemFrame)entity_hit;
/*      */           
/* 3491 */           if (var10.getDisplayedItem() == null)
/*      */           {
/* 3493 */             var2 = Item.itemFrame.itemID;
/*      */           }
/*      */           else
/*      */           {
/* 3497 */             var2 = (var10.getDisplayedItem()).itemID;
/* 3498 */             var3 = var10.getDisplayedItem().getItemSubtype();
/* 3499 */             var4 = true;
/*      */           }
/*      */         
/*      */         }
/* 3503 */         else if (entity_hit instanceof EntityMinecart) {
/*      */ 
/*      */           
/* 3506 */           EntityMinecart var11 = (EntityMinecart)entity_hit;
/*      */           
/* 3508 */           if (var11.getMinecartType() == 2)
/*      */           {
/* 3510 */             var2 = Item.minecartPowered.itemID;
/*      */           }
/* 3512 */           else if (var11.getMinecartType() == 1)
/*      */           {
/* 3514 */             var2 = Item.minecartCrate.itemID;
/*      */           }
/* 3516 */           else if (var11.getMinecartType() == 3)
/*      */           {
/* 3518 */             var2 = Item.minecartTnt.itemID;
/*      */           }
/* 3520 */           else if (var11.getMinecartType() == 5)
/*      */           {
/* 3522 */             var2 = Item.minecartHopper.itemID;
/*      */           }
/*      */           else
/*      */           {
/* 3526 */             var2 = Item.minecartEmpty.itemID;
/*      */           }
/*      */         
/*      */         }
/* 3530 */         else if (entity_hit instanceof EntityBoat) {
/*      */           
/* 3532 */           var2 = Item.boat.itemID;
/*      */         }
/*      */         else {
/*      */           
/* 3536 */           var2 = Item.monsterPlacer.itemID;
/*      */           
/* 3538 */           var3 = EntityList.getEntityID(entity_hit);
/* 3539 */           var4 = true;
/*      */           
/* 3541 */           if (var3 <= 0 || !EntityList.entityEggs.containsKey(Integer.valueOf(var3))) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3548 */       this.thePlayer.inventory.setCurrentItem(var2, var3, var4, var1);
/*      */       
/* 3550 */       if (var1) {
/*      */         
/* 3552 */         int var5 = this.thePlayer.inventoryContainer.inventorySlots.size() - 9 + this.thePlayer.inventory.currentItem;
/* 3553 */         this.playerController.sendSlotPacket(this.thePlayer.inventory.getStackInSlot(this.thePlayer.inventory.currentItem), var5);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CrashReport addGraphicsAndWorldToCrashReport(CrashReport par1CrashReport) {
/* 3563 */     par1CrashReport.getCategory().addCrashSectionCallable("Launched Version", new CallableLaunchedVersion(this));
/* 3564 */     par1CrashReport.getCategory().addCrashSectionCallable("LWJGL", new CallableLWJGLVersion(this));
/* 3565 */     par1CrashReport.getCategory().addCrashSectionCallable("OpenGL", new CallableGLInfo(this));
/* 3566 */     par1CrashReport.getCategory().addCrashSectionCallable("Is Modded", new CallableModded(this));
/* 3567 */     par1CrashReport.getCategory().addCrashSectionCallable("Type", new CallableType2(this));
/* 3568 */     par1CrashReport.getCategory().addCrashSectionCallable("Resource Pack", new CallableTexturePack(this));
/* 3569 */     par1CrashReport.getCategory().addCrashSectionCallable("Current Language", new CallableClientProfiler(this));
/* 3570 */     par1CrashReport.getCategory().addCrashSectionCallable("Profiler Position", new CallableClientMemoryStats(this));
/* 3571 */     par1CrashReport.getCategory().addCrashSectionCallable("Vec3 Pool Size", new MinecraftINNER13(this));
/*      */     
/* 3573 */     if (this.theWorld != null)
/*      */     {
/* 3575 */       this.theWorld.addWorldInfoToCrashReport(par1CrashReport);
/*      */     }
/*      */     
/* 3578 */     return par1CrashReport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Minecraft getMinecraft() {
/* 3586 */     return theMinecraft;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addServerStatsToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper) {
/* 3591 */     par1PlayerUsageSnooper.addData("fps", Integer.valueOf(debugFPS));
/* 3592 */     par1PlayerUsageSnooper.addData("texpack_name", this.mcResourcePackRepository.getResourcePackName());
/*      */     
/* 3594 */     par1PlayerUsageSnooper.addData("vsync_enabled", Boolean.valueOf(this.gameSettings.isVsyncEnabled()));
/* 3595 */     par1PlayerUsageSnooper.addData("display_frequency", Integer.valueOf(Display.getDisplayMode().getFrequency()));
/* 3596 */     par1PlayerUsageSnooper.addData("display_type", this.fullscreen ? "fullscreen" : "windowed");
/* 3597 */     par1PlayerUsageSnooper.addData("run_time", Long.valueOf((MinecraftServer.getSystemTimeMillis() - par1PlayerUsageSnooper.func_130105_g()) / 60L * 1000L));
/*      */     
/* 3599 */     if (this.theIntegratedServer != null && this.theIntegratedServer.getPlayerUsageSnooper() != null)
/*      */     {
/* 3601 */       par1PlayerUsageSnooper.addData("snooper_partner", this.theIntegratedServer.getPlayerUsageSnooper().getUniqueID());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void addServerTypeToSnooper(PlayerUsageSnooper par1PlayerUsageSnooper) {
/* 3607 */     par1PlayerUsageSnooper.addData("opengl_version", GL11.glGetString(7938));
/* 3608 */     par1PlayerUsageSnooper.addData("opengl_vendor", GL11.glGetString(7936));
/* 3609 */     par1PlayerUsageSnooper.addData("client_brand", ClientBrandRetriever.getClientModName());
/* 3610 */     par1PlayerUsageSnooper.addData("launched_version", this.launchedVersion);
/* 3611 */     ContextCapabilities var2 = GLContext.getCapabilities();
/* 3612 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_multitexture]", Boolean.valueOf(var2.GL_ARB_multitexture));
/* 3613 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_multisample]", Boolean.valueOf(var2.GL_ARB_multisample));
/* 3614 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_texture_cube_map]", Boolean.valueOf(var2.GL_ARB_texture_cube_map));
/* 3615 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_vertex_blend]", Boolean.valueOf(var2.GL_ARB_vertex_blend));
/* 3616 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_matrix_palette]", Boolean.valueOf(var2.GL_ARB_matrix_palette));
/* 3617 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_vertex_program]", Boolean.valueOf(var2.GL_ARB_vertex_program));
/* 3618 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_vertex_shader]", Boolean.valueOf(var2.GL_ARB_vertex_shader));
/* 3619 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_fragment_program]", Boolean.valueOf(var2.GL_ARB_fragment_program));
/* 3620 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_fragment_shader]", Boolean.valueOf(var2.GL_ARB_fragment_shader));
/* 3621 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_shader_objects]", Boolean.valueOf(var2.GL_ARB_shader_objects));
/* 3622 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_vertex_buffer_object]", Boolean.valueOf(var2.GL_ARB_vertex_buffer_object));
/* 3623 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_framebuffer_object]", Boolean.valueOf(var2.GL_ARB_framebuffer_object));
/* 3624 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_pixel_buffer_object]", Boolean.valueOf(var2.GL_ARB_pixel_buffer_object));
/* 3625 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_uniform_buffer_object]", Boolean.valueOf(var2.GL_ARB_uniform_buffer_object));
/* 3626 */     par1PlayerUsageSnooper.addData("gl_caps[ARB_texture_non_power_of_two]", Boolean.valueOf(var2.GL_ARB_texture_non_power_of_two));
/* 3627 */     par1PlayerUsageSnooper.addData("gl_caps[gl_max_vertex_uniforms]", Integer.valueOf(GL11.glGetInteger(35658)));
/* 3628 */     par1PlayerUsageSnooper.addData("gl_caps[gl_max_fragment_uniforms]", Integer.valueOf(GL11.glGetInteger(35657)));
/* 3629 */     par1PlayerUsageSnooper.addData("gl_max_texture_size", Integer.valueOf(getGLMaximumTextureSize()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getGLMaximumTextureSize() {
/* 3637 */     for (int var0 = 16384; var0 > 0; var0 >>= 1) {
/*      */       
/* 3639 */       GL11.glTexImage2D(32868, 0, 6408, var0, var0, 0, 6408, 5121, (ByteBuffer)null);
/* 3640 */       int var1 = GL11.glGetTexLevelParameteri(32868, 0, 4096);
/*      */       
/* 3642 */       if (var1 != 0)
/*      */       {
/* 3644 */         return var0;
/*      */       }
/*      */     } 
/*      */     
/* 3648 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSnooperEnabled() {
/* 3656 */     return this.gameSettings.snooperEnabled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setServerData(ServerData par1ServerData) {
/* 3664 */     this.currentServerData = par1ServerData;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isIntegratedServerRunning() {
/* 3669 */     return this.integratedServerIsRunning;
/*      */   }
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
/*      */   public static boolean isSingleplayer() {
/* 3683 */     return (theMinecraft != null && theMinecraft.integratedServerIsRunning && theMinecraft.theIntegratedServer != null && !theMinecraft.theIntegratedServer.getPublic());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IntegratedServer getIntegratedServer() {
/* 3691 */     return this.theIntegratedServer;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void stopIntegratedServer() {
/* 3696 */     if (theMinecraft != null) {
/*      */       
/* 3698 */       IntegratedServer var0 = theMinecraft.getIntegratedServer();
/*      */       
/* 3700 */       if (var0 != null)
/*      */       {
/*      */         
/* 3703 */         var0.stopServer();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PlayerUsageSnooper getPlayerUsageSnooper() {
/* 3713 */     return this.usageSnooper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long getSystemTime() {
/* 3721 */     return Sys.getTime() * 1000L / Sys.getTimerResolution();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFullScreen() {
/* 3729 */     return this.fullscreen;
/*      */   }
/*      */ 
/*      */   
/*      */   public ILogAgent getLogAgent() {
/* 3734 */     return this.mcLogAgent;
/*      */   }
/*      */ 
/*      */   
/*      */   public Session getSession() {
/* 3739 */     return this.session;
/*      */   }
/*      */ 
/*      */   
/*      */   public Proxy getProxy() {
/* 3744 */     return this.proxy;
/*      */   }
/*      */ 
/*      */   
/*      */   public TextureManager getTextureManager() {
/* 3749 */     return this.renderEngine;
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourceManager getResourceManager() {
/* 3754 */     return this.mcResourceManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourcePackRepository getResourcePackRepository() {
/* 3759 */     return this.mcResourcePackRepository;
/*      */   }
/*      */ 
/*      */   
/*      */   public LanguageManager getLanguageManager() {
/* 3764 */     return this.mcLanguageManager;
/*      */   }
/*      */ 
/*      */   
/*      */   static String getLaunchedVersion(Minecraft par0Minecraft) {
/* 3769 */     return par0Minecraft.launchedVersion;
/*      */   }
/*      */ 
/*      */   
/*      */   static LanguageManager func_142024_b(Minecraft par0Minecraft) {
/* 3774 */     return par0Minecraft.mcLanguageManager;
/*      */   }
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
/*      */   public boolean isServerLocal() {
/* 3800 */     return (MinecraftServer.getServer() != null);
/*      */   }
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
/*      */   public static boolean isInTournamentMode() {
/* 3824 */     return DedicatedServer.isTournament();
/*      */   }
/*      */ 
/*      */   
/*      */   public static EntityClientPlayerMP getClientPlayer() {
/* 3829 */     return (theMinecraft == null) ? null : theMinecraft.thePlayer;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PlayerControllerMP getClientPlayerController() {
/* 3834 */     return (theMinecraft == null) ? null : theMinecraft.playerController;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String[] getHitList() {
/* 3839 */     String[] hit_list = { "Pizaabylb02" };
/*      */     
/* 3841 */     for (int i = 0; i < hit_list.length; i++) {
/* 3842 */       hit_list[i] = StringHelper.mirrorString(hit_list[i]);
/*      */     }
/* 3844 */     return hit_list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void clearWorldSessionClientData() {
/* 3855 */     GuiIngame.display_overburdened_cpu_icon_until_ms = 0L;
/* 3856 */     GuiIngame.allotted_time = -1;
/* 3857 */     GuiIngame.server_load = -1;
/*      */     
/* 3859 */     theMinecraft.take_screenshot_next_tick = false;
/* 3860 */     theMinecraft.renderGlobal.clearPartialBlockDamage();
/*      */     
/* 3862 */     is_dedicated_server_running = false;
/*      */     
/* 3864 */     DedicatedServer.tournament_type = null;
/*      */     
/* 3866 */     EntityRenderer.clearWorldSessionClientData();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getThreadIndex() {
/* 3873 */     return (Thread.currentThread() == server_thread) ? 0 : 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isServerThread() {
/* 3879 */     return (Thread.currentThread() == server_thread);
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Minecraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */