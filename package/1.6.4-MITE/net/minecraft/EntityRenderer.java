/*      */ package net.minecraft;
/*      */ 
/*      */ import java.nio.FloatBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GLContext;
/*      */ import org.lwjgl.util.glu.Project;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class EntityRenderer
/*      */ {
/*   22 */   private static final ResourceLocation locationRainPng = new ResourceLocation("textures/environment/rain.png");
/*   23 */   private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");
/*      */   
/*      */   public static boolean anaglyphEnable;
/*      */   
/*      */   public static int anaglyphField;
/*      */   
/*      */   private Minecraft mc;
/*      */   
/*      */   private float farPlaneDistance;
/*      */   
/*      */   public ItemRenderer itemRenderer;
/*      */   
/*      */   private int rendererUpdateCount;
/*      */   
/*      */   private Entity pointedEntity;
/*      */   
/*   39 */   private MouseFilter mouseFilterXAxis = new MouseFilter();
/*   40 */   private MouseFilter mouseFilterYAxis = new MouseFilter();
/*      */ 
/*      */   
/*   43 */   private MouseFilter mouseFilterDummy1 = new MouseFilter();
/*      */ 
/*      */   
/*   46 */   private MouseFilter mouseFilterDummy2 = new MouseFilter();
/*      */ 
/*      */   
/*   49 */   private MouseFilter mouseFilterDummy3 = new MouseFilter();
/*      */ 
/*      */   
/*   52 */   private MouseFilter mouseFilterDummy4 = new MouseFilter();
/*   53 */   private float thirdPersonDistance = 4.0F;
/*      */ 
/*      */   
/*   56 */   private float thirdPersonDistanceTemp = 4.0F;
/*      */ 
/*      */   
/*      */   private float debugCamYaw;
/*      */ 
/*      */   
/*      */   private float prevDebugCamYaw;
/*      */ 
/*      */   
/*      */   private float debugCamPitch;
/*      */   
/*      */   private float prevDebugCamPitch;
/*      */   
/*      */   private float smoothCamYaw;
/*      */   
/*      */   private float smoothCamPitch;
/*      */   
/*      */   private float smoothCamFilterX;
/*      */   
/*      */   private float smoothCamFilterY;
/*      */   
/*      */   private float smoothCamPartialTicks;
/*      */   
/*      */   private float debugCamFOV;
/*      */   
/*      */   private float prevDebugCamFOV;
/*      */   
/*      */   private float camRoll;
/*      */   
/*      */   private float prevCamRoll;
/*      */   
/*      */   private final DynamicTexture lightmapTexture;
/*      */   
/*      */   private final int[] lightmapColors;
/*      */   
/*      */   private final ResourceLocation locationLightMap;
/*      */   
/*      */   private float fovModifierHand;
/*      */   
/*      */   private float fovModifierHandPrev;
/*      */   
/*      */   private float fovMultiplierTemp;
/*      */   
/*      */   private float field_82831_U;
/*      */   
/*      */   private float field_82832_V;
/*      */   
/*      */   private boolean cloudFog;
/*      */   
/*  105 */   private double cameraZoom = 1.0D;
/*      */   
/*      */   private double cameraYaw;
/*      */   
/*      */   private double cameraPitch;
/*  110 */   private long prevFrameTime = Minecraft.getSystemTime();
/*      */ 
/*      */   
/*      */   private long renderEndNanoTime;
/*      */ 
/*      */   
/*      */   private boolean lightmapUpdateNeeded;
/*      */ 
/*      */   
/*      */   float torchFlickerX;
/*      */ 
/*      */   
/*      */   float torchFlickerDX;
/*      */ 
/*      */   
/*      */   float torchFlickerY;
/*      */ 
/*      */   
/*      */   float torchFlickerDY;
/*      */ 
/*      */   
/*  131 */   private Random random = new Random();
/*      */ 
/*      */   
/*      */   private int rainSoundCounter;
/*      */ 
/*      */   
/*      */   float[] rainXCoords;
/*      */ 
/*      */   
/*      */   float[] rainYCoords;
/*      */ 
/*      */   
/*  143 */   FloatBuffer fogColorBuffer = GLAllocation.createDirectFloatBuffer(16);
/*      */ 
/*      */ 
/*      */   
/*      */   float fogColorRed;
/*      */ 
/*      */ 
/*      */   
/*      */   float fogColorGreen;
/*      */ 
/*      */ 
/*      */   
/*      */   float fogColorBlue;
/*      */ 
/*      */   
/*      */   private float fogColor2;
/*      */ 
/*      */   
/*      */   private float fogColor1;
/*      */ 
/*      */   
/*      */   public int debugViewDirection;
/*      */ 
/*      */   
/*  167 */   private double[] x = new double[4];
/*  168 */   private double[] y = new double[4];
/*  169 */   private double[] z = new double[4];
/*  170 */   private double[] u = new double[4];
/*  171 */   private double[] v = new double[4];
/*  172 */   private float[] r = new float[4];
/*  173 */   private float[] g = new float[4];
/*  174 */   private float[] b = new float[4];
/*  175 */   private int[] brightness = new int[4];
/*      */   
/*  177 */   private static final boolean capability_gl_nv_fog_distance = (GLContext.getCapabilities()).GL_NV_fog_distance;
/*      */   
/*  179 */   private long last_vsync_nanotime = -1L;
/*  180 */   private long fps_start_time = -1L;
/*      */   
/*      */   private int fps_counter;
/*  183 */   private long fp10s_start_time = -1L;
/*      */   
/*      */   private int fp10s_counter;
/*      */   
/*      */   private int last_precipitation_type;
/*      */   
/*      */   private float skylight_brightness_used_for_fog;
/*      */   
/*      */   private static final int fog_post_cutoff_distance = 1024;
/*      */   
/*      */   private static final int fog_post_field_chunk_range = 65;
/*      */   
/*      */   private static final int fog_post_field_size = 131;
/*      */   
/*      */   private static final int fog_post_field_num_chunks = 17161;
/*      */   
/*      */   private static List fog_post_list;
/*      */   
/*      */   private static World last_fog_post_field_generation_viewer_world;
/*      */   
/*      */   private static int last_fog_post_field_generation_viewer_chunk_x;
/*      */   
/*      */   private static int last_fog_post_field_generation_viewer_chunk_z;
/*      */   
/*      */   public static boolean disable_fog;
/*  208 */   private static Random random_for_fog_events = new Random();
/*      */   
/*      */   private static double distance_from_biome_that_can_be_foggy;
/*      */   
/*      */   private static long distance_from_biome_that_can_be_foggy_tick;
/*      */   private static double distance_from_biome_that_can_be_foggy_last_viewer_pos_x;
/*      */   private static double distance_from_biome_that_can_be_foggy_last_viewer_pos_z;
/*      */   private static int distance_from_biome_that_can_be_foggy_last_viewer_x;
/*      */   private static int distance_from_biome_that_can_be_foggy_last_viewer_z;
/*      */   private static World distance_from_biome_that_can_be_foggy_last_viewer_world;
/*  218 */   private static boolean[] is_fog_supporting_biome = new boolean[4225];
/*      */   
/*      */   private static boolean is_fog_supporting_biome_contains_at_least_one;
/*      */   
/*      */   public static void clearWorldSessionClientData() {
/*  223 */     distance_from_biome_that_can_be_foggy_tick = -1L;
/*  224 */     distance_from_biome_that_can_be_foggy_last_viewer_world = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityRenderer(Minecraft par1Minecraft) {
/*  233 */     this.mc = par1Minecraft;
/*  234 */     this.itemRenderer = new ItemRenderer(par1Minecraft);
/*  235 */     this.lightmapTexture = new DynamicTexture(16, 16);
/*  236 */     this.locationLightMap = par1Minecraft.getTextureManager().getDynamicTextureLocation("lightMap", this.lightmapTexture);
/*  237 */     this.lightmapColors = this.lightmapTexture.getTextureData();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRenderer() {
/*  245 */     updateFovModifierHand();
/*  246 */     updateTorchFlicker();
/*  247 */     this.fogColor2 = this.fogColor1;
/*  248 */     this.thirdPersonDistanceTemp = this.thirdPersonDistance;
/*  249 */     this.prevDebugCamYaw = this.debugCamYaw;
/*  250 */     this.prevDebugCamPitch = this.debugCamPitch;
/*  251 */     this.prevDebugCamFOV = this.debugCamFOV;
/*  252 */     this.prevCamRoll = this.camRoll;
/*      */ 
/*      */ 
/*      */     
/*  256 */     if (this.mc.gameSettings.smoothCamera) {
/*      */       
/*  258 */       float f1 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
/*  259 */       float f2 = f1 * f1 * f1 * 8.0F;
/*  260 */       this.smoothCamFilterX = this.mouseFilterXAxis.smooth(this.smoothCamYaw, 0.05F * f2);
/*  261 */       this.smoothCamFilterY = this.mouseFilterYAxis.smooth(this.smoothCamPitch, 0.05F * f2);
/*  262 */       this.smoothCamPartialTicks = 0.0F;
/*  263 */       this.smoothCamYaw = 0.0F;
/*  264 */       this.smoothCamPitch = 0.0F;
/*      */     } 
/*      */     
/*  267 */     if (this.mc.renderViewEntity == null)
/*      */     {
/*  269 */       this.mc.renderViewEntity = this.mc.thePlayer;
/*      */     }
/*      */     
/*  272 */     float var1 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(this.mc.renderViewEntity.posX), MathHelper.floor_double(this.mc.renderViewEntity.posY), MathHelper.floor_double(this.mc.renderViewEntity.posZ));
/*      */     
/*  274 */     float var2 = (3 - this.mc.gameSettings.getRenderDistance()) / 3.0F;
/*  275 */     float var3 = var1 * (1.0F - var2) + var2;
/*  276 */     this.fogColor1 += (var3 - this.fogColor1) * 0.1F;
/*  277 */     this.rendererUpdateCount++;
/*  278 */     this.itemRenderer.updateEquippedItem();
/*  279 */     addRainParticles();
/*  280 */     this.field_82832_V = this.field_82831_U;
/*      */     
/*  282 */     if (BossStatus.field_82825_d) {
/*      */       
/*  284 */       this.field_82831_U += 0.05F;
/*      */       
/*  286 */       if (this.field_82831_U > 1.0F)
/*      */       {
/*  288 */         this.field_82831_U = 1.0F;
/*      */       }
/*      */       
/*  291 */       BossStatus.field_82825_d = false;
/*      */     }
/*  293 */     else if (this.field_82831_U > 0.0F) {
/*      */       
/*  295 */       this.field_82831_U -= 0.0125F;
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
/*      */   public static void setDebugInfoForSelectedObject(RaycastCollision rc, EntityPlayer player) {
/*  398 */     if (rc != null) {
/*      */       
/*  400 */       if (rc.isEntity())
/*      */       {
/*  402 */         Entity entity = rc.getEntityHit();
/*      */         
/*  404 */         Debug.selected_object_info = "Entity Selected: " + entity.getEntityName() + ", id=" + entity.entityId;
/*      */       }
/*  406 */       else if (rc.isBlock())
/*      */       {
/*  408 */         Block block = Block.blocksList[player.worldObj.getBlockId(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z)];
/*  409 */         float block_hardness = player.worldObj.getBlockHardness(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
/*      */         
/*  411 */         if (Minecraft.inDevMode())
/*      */         {
/*  413 */           int metadata = player.worldObj.getBlockMetadata(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
/*      */           
/*  415 */           int min_harvest_level = block.getMinHarvestLevel(metadata);
/*      */           
/*  417 */           if (min_harvest_level == 0) {
/*  418 */             Debug.selected_object_info = block.getLocalizedName() + " (" + block.blockID + ":" + metadata + "), hardness=" + block_hardness + ", strVs=" + player.getCurrentPlayerStrVsBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, true);
/*      */           } else {
/*  420 */             Debug.selected_object_info = block.getLocalizedName() + " (" + block.blockID + ":" + metadata + "), hardness=" + block_hardness + ", MinHL=" + min_harvest_level + ", strVs=" + player.getCurrentPlayerStrVsBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, true);
/*      */           } 
/*  422 */           if (!rc.isNeighborAirBlock()) {
/*  423 */             Debug.selected_object_info += ", neighbor (" + rc.getNeighborOfBlockHitID() + ":" + rc.getNeighborOfBlockHitMetadata() + ")";
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  432 */       Debug.selected_object_info = "No object selected";
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void getMouseOver(float partial_tick) {
/*  438 */     if (this.mc.renderViewEntity == null || this.mc.theWorld == null) {
/*      */       return;
/*      */     }
/*  441 */     if (this.mc.renderViewEntity instanceof EntityPlayer && this.mc.theWorld != null) {
/*      */       
/*  443 */       EntityPlayer player = (EntityPlayer)this.mc.renderViewEntity;
/*      */       
/*  445 */       RaycastCollision rc = player.getSelectedObject(partial_tick, false);
/*      */       
/*  447 */       this.mc.objectMouseOver = rc;
/*  448 */       this.pointedEntity = null;
/*  449 */       this.mc.pointedEntityLiving = null;
/*      */ 
/*      */ 
/*      */       
/*  453 */       if (rc != null && rc.isEntity()) {
/*      */         
/*  455 */         this.pointedEntity = rc.getEntityHit();
/*      */         
/*  457 */         if (this.pointedEntity instanceof EntityLivingBase) {
/*  458 */           this.mc.pointedEntityLiving = (EntityLivingBase)this.pointedEntity;
/*      */         }
/*      */       } 
/*  461 */       if (Minecraft.inDevMode()) {
/*  462 */         setDebugInfoForSelectedObject(player.getSelectedObject(partial_tick, false, true, (EnumEntityReachContext)null), player);
/*      */       }
/*      */     } else {
/*      */       
/*  466 */       Minecraft.setErrorMessage("getMouseOver: cannot handle non EntityPlayer entities");
/*      */       
/*  468 */       this.mc.objectMouseOver = null;
/*  469 */       this.pointedEntity = null;
/*  470 */       this.mc.pointedEntityLiving = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateFovModifierHand() {
/*  479 */     ClientPlayer var1 = (ClientPlayer)this.mc.renderViewEntity;
/*  480 */     this.fovMultiplierTemp = var1.getFOVMultiplier();
/*  481 */     this.fovModifierHandPrev = this.fovModifierHand;
/*  482 */     this.fovModifierHand += (this.fovMultiplierTemp - this.fovModifierHand) * 0.5F;
/*      */     
/*  484 */     if (this.fovModifierHand > 1.5F)
/*      */     {
/*  486 */       this.fovModifierHand = 1.5F;
/*      */     }
/*      */     
/*  489 */     if (this.fovModifierHand < 0.1F)
/*      */     {
/*  491 */       this.fovModifierHand = 0.1F;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float getFOVModifier(float par1, boolean par2) {
/*  500 */     if (this.debugViewDirection > 0)
/*      */     {
/*  502 */       return 90.0F;
/*      */     }
/*      */ 
/*      */     
/*  506 */     EntityPlayer var3 = (EntityPlayer)this.mc.renderViewEntity;
/*  507 */     float var4 = 70.0F;
/*      */     
/*  509 */     if (par2) {
/*      */       
/*  511 */       var4 += this.mc.gameSettings.fovSetting * 40.0F;
/*  512 */       var4 *= this.fovModifierHandPrev + (this.fovModifierHand - this.fovModifierHandPrev) * par1;
/*      */     } 
/*      */     
/*  515 */     if (var3.getHealth() <= 0.0F) {
/*      */       
/*  517 */       float var5 = var3.deathTime + par1;
/*  518 */       var4 /= (1.0F - 500.0F / (var5 + 500.0F)) * 2.0F + 1.0F;
/*      */     } 
/*      */     
/*  521 */     int var6 = ActiveRenderInfo.getBlockIdAtEntityViewpoint(this.mc.theWorld, var3, par1);
/*      */     
/*  523 */     if (var6 != 0 && (Block.blocksList[var6]).blockMaterial == Material.water)
/*      */     {
/*  525 */       var4 = var4 * 60.0F / 70.0F;
/*      */     }
/*      */     
/*  528 */     return var4 + this.prevDebugCamFOV + (this.debugCamFOV - this.prevDebugCamFOV) * par1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void hurtCameraEffect(float par1) {
/*  534 */     EntityLivingBase var2 = this.mc.renderViewEntity;
/*  535 */     float var3 = var2.hurtTime - par1;
/*      */ 
/*      */     
/*  538 */     if (var2.getHealth() <= 0.0F) {
/*      */       
/*  540 */       float var4 = var2.deathTime + par1;
/*  541 */       GL11.glRotatef(40.0F - 8000.0F / (var4 + 200.0F), 0.0F, 0.0F, 1.0F);
/*      */     } 
/*      */     
/*  544 */     if (var3 >= 0.0F) {
/*      */       
/*  546 */       var3 /= var2.maxHurtTime;
/*  547 */       var3 = MathHelper.sin(var3 * var3 * var3 * var3 * 3.1415927F);
/*  548 */       float var4 = var2.attackedAtYaw;
/*  549 */       GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
/*  550 */       GL11.glRotatef(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
/*  551 */       GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setupViewBobbing(float par1) {
/*  560 */     if (this.mc.renderViewEntity instanceof EntityPlayer) {
/*      */       
/*  562 */       EntityPlayer var2 = (EntityPlayer)this.mc.renderViewEntity;
/*  563 */       float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
/*  564 */       float var4 = -(var2.distanceWalkedModified + var3 * par1);
/*  565 */       float var5 = var2.prevCameraYaw + (var2.cameraYaw - var2.prevCameraYaw) * par1;
/*  566 */       float var6 = var2.prevCameraPitch + (var2.cameraPitch - var2.prevCameraPitch) * par1;
/*  567 */       GL11.glTranslatef(MathHelper.sin(var4 * 3.1415927F) * var5 * 0.5F, -Math.abs(MathHelper.cos(var4 * 3.1415927F) * var5), 0.0F);
/*  568 */       GL11.glRotatef(MathHelper.sin(var4 * 3.1415927F) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
/*  569 */       GL11.glRotatef(Math.abs(MathHelper.cos(var4 * 3.1415927F - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
/*  570 */       GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
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
/*      */   private void orientCamera(float par1) {
/*  587 */     EntityLivingBase var2 = this.mc.renderViewEntity;
/*  588 */     float var3 = var2.yOffset - 1.62F;
/*      */     
/*  590 */     if (this.mc.thePlayer.isSneaking()) {
/*  591 */       var3 -= -0.2385F;
/*      */     }
/*  593 */     double var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * par1;
/*  594 */     double var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * par1 - var3;
/*  595 */     double var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * par1;
/*  596 */     GL11.glRotatef(this.prevCamRoll + (this.camRoll - this.prevCamRoll) * par1, 0.0F, 0.0F, 1.0F);
/*      */ 
/*      */     
/*  599 */     if (var2.inBed()) {
/*      */       
/*  601 */       var3 = (float)(var3 + 1.0D);
/*  602 */       GL11.glTranslatef(0.0F, 0.3F, 0.0F);
/*      */       
/*  604 */       if (!this.mc.gameSettings.debugCamEnable)
/*      */       {
/*  606 */         int var10 = this.mc.theWorld.getBlockId(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
/*      */         
/*  608 */         if (var10 == Block.bed.blockID) {
/*      */           
/*  610 */           int var11 = this.mc.theWorld.getBlockMetadata(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posY), MathHelper.floor_double(var2.posZ));
/*  611 */           int var12 = var11 & 0x3;
/*  612 */           GL11.glRotatef((var12 * 90), 0.0F, 1.0F, 0.0F);
/*      */         } 
/*      */         
/*  615 */         GL11.glRotatef(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * par1 + 180.0F, 0.0F, -1.0F, 0.0F);
/*  616 */         GL11.glRotatef(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * par1, -1.0F, 0.0F, 0.0F);
/*      */       }
/*      */     
/*  619 */     } else if (this.mc.gameSettings.thirdPersonView > 0) {
/*      */       
/*  621 */       double var27 = (this.thirdPersonDistanceTemp + (this.thirdPersonDistance - this.thirdPersonDistanceTemp) * par1);
/*      */ 
/*      */ 
/*      */       
/*  625 */       if (this.mc.gameSettings.debugCamEnable)
/*      */       {
/*  627 */         float var28 = this.prevDebugCamYaw + (this.debugCamYaw - this.prevDebugCamYaw) * par1;
/*  628 */         float var13 = this.prevDebugCamPitch + (this.debugCamPitch - this.prevDebugCamPitch) * par1;
/*  629 */         GL11.glTranslatef(0.0F, 0.0F, (float)-var27);
/*  630 */         GL11.glRotatef(var13, 1.0F, 0.0F, 0.0F);
/*  631 */         GL11.glRotatef(var28, 0.0F, 1.0F, 0.0F);
/*      */       }
/*      */       else
/*      */       {
/*  635 */         float var28 = var2.rotationYaw;
/*  636 */         float var13 = var2.rotationPitch;
/*      */         
/*  638 */         if (this.mc.gameSettings.thirdPersonView == 2)
/*      */         {
/*  640 */           var13 += 180.0F;
/*      */         }
/*      */         
/*  643 */         double var14 = (-MathHelper.sin(var28 / 180.0F * 3.1415927F) * MathHelper.cos(var13 / 180.0F * 3.1415927F)) * var27;
/*  644 */         double var16 = (MathHelper.cos(var28 / 180.0F * 3.1415927F) * MathHelper.cos(var13 / 180.0F * 3.1415927F)) * var27;
/*  645 */         double var18 = -MathHelper.sin(var13 / 180.0F * 3.1415927F) * var27;
/*      */         
/*  647 */         for (int var20 = 0; var20 < 8; var20++) {
/*      */           
/*  649 */           float var21 = ((var20 & 0x1) * 2 - 1);
/*  650 */           float var22 = ((var20 >> 1 & 0x1) * 2 - 1);
/*  651 */           float var23 = ((var20 >> 2 & 0x1) * 2 - 1);
/*  652 */           var21 *= 0.1F;
/*  653 */           var22 *= 0.1F;
/*  654 */           var23 *= 0.1F;
/*      */ 
/*      */           
/*  657 */           RaycastCollision var24 = this.mc.theWorld.getBlockCollisionForPolicies(this.mc.theWorld.getVec3(var4 + var21, var6 + var22, var8 + var23), this.mc.theWorld.getVec3(var4 - var14 + var21 + var23, var6 - var18 + var22, var8 - var16 + var23), RaycastPolicies.for_third_person_view);
/*      */           
/*  659 */           if (var24 != null) {
/*      */             
/*  661 */             double var25 = var24.position_hit.distanceTo(this.mc.theWorld.getWorldVec3Pool().getVecFromPool(var4, var6, var8));
/*      */             
/*  663 */             if (var25 < var27)
/*      */             {
/*  665 */               var27 = var25;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  670 */         if (this.mc.gameSettings.thirdPersonView == 2)
/*      */         {
/*  672 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*      */         }
/*      */         
/*  675 */         GL11.glRotatef(var2.rotationPitch - var13, 1.0F, 0.0F, 0.0F);
/*  676 */         GL11.glRotatef(var2.rotationYaw - var28, 0.0F, 1.0F, 0.0F);
/*  677 */         GL11.glTranslatef(0.0F, 0.0F, (float)-var27);
/*  678 */         GL11.glRotatef(var28 - var2.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  679 */         GL11.glRotatef(var13 - var2.rotationPitch, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  684 */       GL11.glTranslatef(0.0F, 0.0F, -0.1F);
/*      */     } 
/*      */     
/*  687 */     if (!this.mc.gameSettings.debugCamEnable) {
/*      */       
/*  689 */       GL11.glRotatef(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * par1, 1.0F, 0.0F, 0.0F);
/*  690 */       GL11.glRotatef(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * par1 + 180.0F, 0.0F, 1.0F, 0.0F);
/*      */     } 
/*      */     
/*  693 */     GL11.glTranslatef(0.0F, var3, 0.0F);
/*  694 */     var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * par1;
/*  695 */     var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * par1 - var3;
/*  696 */     var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * par1;
/*  697 */     this.cloudFog = this.mc.renderGlobal.hasCloudFog(var4, var6, var8, par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupCameraTransform(float par1, int par2, boolean extend_far_clipping_plane) {
/*  707 */     this.farPlaneDistance = (extend_far_clipping_plane ? 'ƀ' : (256 >> this.mc.gameSettings.getRenderDistance()));
/*  708 */     GL11.glMatrixMode(5889);
/*  709 */     GL11.glLoadIdentity();
/*  710 */     float var3 = 0.07F;
/*      */     
/*  712 */     if (this.mc.gameSettings.anaglyph)
/*      */     {
/*  714 */       GL11.glTranslatef(-(par2 * 2 - 1) * var3, 0.0F, 0.0F);
/*      */     }
/*      */     
/*  717 */     if (this.cameraZoom != 1.0D) {
/*      */       
/*  719 */       GL11.glTranslatef((float)this.cameraYaw, (float)-this.cameraPitch, 0.0F);
/*  720 */       GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
/*      */     } 
/*      */     
/*  723 */     Project.gluPerspective(getFOVModifier(par1, true), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
/*      */ 
/*      */     
/*  726 */     if (this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */       
/*  728 */       float f = 0.6666667F;
/*  729 */       GL11.glScalef(1.0F, f, 1.0F);
/*      */     } 
/*      */     
/*  732 */     GL11.glMatrixMode(5888);
/*  733 */     GL11.glLoadIdentity();
/*      */     
/*  735 */     if (this.mc.gameSettings.anaglyph)
/*      */     {
/*  737 */       GL11.glTranslatef((par2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
/*      */     }
/*      */     
/*  740 */     hurtCameraEffect(par1);
/*      */     
/*  742 */     if (this.mc.gameSettings.viewBobbing)
/*      */     {
/*  744 */       setupViewBobbing(par1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  749 */     float var4 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * par1;
/*      */     
/*  751 */     if (var4 > 0.0F) {
/*      */       
/*  753 */       byte var5 = 20;
/*      */ 
/*      */       
/*  756 */       if (this.mc.thePlayer.isPotionActive(Potion.confusion) && this.mc.thePlayer.getActivePotionEffect(Potion.confusion).getAmplifier() > 0) {
/*      */         
/*  758 */         var5 = 7;
/*      */         
/*  760 */         var4 *= 0.25F * this.mc.thePlayer.getActivePotionEffect(Potion.confusion).getAmplifier();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  765 */       float var6 = 5.0F / (var4 * var4 + 5.0F) - var4 * 0.04F;
/*  766 */       var6 *= var6;
/*  767 */       GL11.glRotatef((this.rendererUpdateCount + par1) * var5, 0.0F, 1.0F, 1.0F);
/*  768 */       GL11.glScalef(1.0F / var6, 1.0F, 1.0F);
/*  769 */       GL11.glRotatef(-(this.rendererUpdateCount + par1) * var5, 0.0F, 1.0F, 1.0F);
/*      */     } 
/*      */     
/*  772 */     orientCamera(par1);
/*      */     
/*  774 */     if (this.debugViewDirection > 0) {
/*      */       
/*  776 */       int var7 = this.debugViewDirection - 1;
/*      */       
/*  778 */       if (var7 == 1)
/*      */       {
/*  780 */         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*      */       }
/*      */       
/*  783 */       if (var7 == 2)
/*      */       {
/*  785 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*      */       }
/*      */       
/*  788 */       if (var7 == 3)
/*      */       {
/*  790 */         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*      */       }
/*      */       
/*  793 */       if (var7 == 4)
/*      */       {
/*  795 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */       
/*  798 */       if (var7 == 5)
/*      */       {
/*  800 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderHand(float par1, int par2) {
/*  810 */     if (this.debugViewDirection <= 0) {
/*      */       
/*  812 */       GL11.glMatrixMode(5889);
/*  813 */       GL11.glLoadIdentity();
/*  814 */       float var3 = 0.07F;
/*      */       
/*  816 */       if (this.mc.gameSettings.anaglyph)
/*      */       {
/*  818 */         GL11.glTranslatef(-(par2 * 2 - 1) * var3, 0.0F, 0.0F);
/*      */       }
/*      */       
/*  821 */       if (this.cameraZoom != 1.0D) {
/*      */         
/*  823 */         GL11.glTranslatef((float)this.cameraYaw, (float)-this.cameraPitch, 0.0F);
/*  824 */         GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
/*      */       } 
/*      */       
/*  827 */       Project.gluPerspective(getFOVModifier(par1, false), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
/*      */       
/*  829 */       if (this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */         
/*  831 */         float var4 = 0.6666667F;
/*  832 */         GL11.glScalef(1.0F, var4, 1.0F);
/*      */       } 
/*      */       
/*  835 */       GL11.glMatrixMode(5888);
/*  836 */       GL11.glLoadIdentity();
/*      */       
/*  838 */       if (this.mc.gameSettings.anaglyph)
/*      */       {
/*  840 */         GL11.glTranslatef((par2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
/*      */       }
/*      */       
/*  843 */       GL11.glPushMatrix();
/*  844 */       hurtCameraEffect(par1);
/*      */       
/*  846 */       if (this.mc.gameSettings.viewBobbing)
/*      */       {
/*  848 */         setupViewBobbing(par1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  853 */       if (this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.inBed() && this.mc.gameSettings.gui_mode != 2 && !this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */         
/*  855 */         enableLightmap(par1);
/*  856 */         this.itemRenderer.renderItemInFirstPerson(par1);
/*  857 */         disableLightmap(par1);
/*      */       } 
/*      */       
/*  860 */       GL11.glPopMatrix();
/*      */ 
/*      */       
/*  863 */       if (this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.inBed()) {
/*      */         
/*  865 */         this.itemRenderer.renderOverlays(par1);
/*  866 */         hurtCameraEffect(par1);
/*      */       } 
/*      */       
/*  869 */       if (this.mc.gameSettings.viewBobbing)
/*      */       {
/*  871 */         setupViewBobbing(par1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void disableLightmap(double par1) {
/*  881 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/*  882 */     GL11.glDisable(3553);
/*  883 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enableLightmap(double par1) {
/*  891 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/*  892 */     GL11.glMatrixMode(5890);
/*  893 */     GL11.glLoadIdentity();
/*  894 */     float var3 = 0.00390625F;
/*  895 */     GL11.glScalef(var3, var3, var3);
/*  896 */     GL11.glTranslatef(8.0F, 8.0F, 8.0F);
/*  897 */     GL11.glMatrixMode(5888);
/*  898 */     this.mc.getTextureManager().bindTexture(this.locationLightMap);
/*  899 */     GL11.glTexParameteri(3553, 10241, 9729);
/*  900 */     GL11.glTexParameteri(3553, 10240, 9729);
/*  901 */     GL11.glTexParameteri(3553, 10241, 9729);
/*  902 */     GL11.glTexParameteri(3553, 10240, 9729);
/*  903 */     GL11.glTexParameteri(3553, 10242, 10496);
/*  904 */     GL11.glTexParameteri(3553, 10243, 10496);
/*  905 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  906 */     GL11.glEnable(3553);
/*  907 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateTorchFlicker() {
/*  915 */     if (this.mc.thePlayer.torch_flicker_suppressed) {
/*      */       
/*  917 */       this.lightmapUpdateNeeded = true;
/*      */       
/*      */       return;
/*      */     } 
/*  921 */     this.torchFlickerDX = (float)(this.torchFlickerDX + (Math.random() - Math.random()) * Math.random() * Math.random());
/*  922 */     this.torchFlickerDY = (float)(this.torchFlickerDY + (Math.random() - Math.random()) * Math.random() * Math.random());
/*  923 */     this.torchFlickerDX = (float)(this.torchFlickerDX * 0.9D);
/*  924 */     this.torchFlickerDY = (float)(this.torchFlickerDY * 0.9D);
/*  925 */     this.torchFlickerX += (this.torchFlickerDX - this.torchFlickerX) * 1.0F;
/*  926 */     this.torchFlickerY += (this.torchFlickerDY - this.torchFlickerY) * 1.0F;
/*  927 */     this.lightmapUpdateNeeded = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateLightmap(float par1) {
/*  934 */     WorldClient var2 = this.mc.theWorld;
/*      */     
/*  936 */     if (var2 != null) {
/*      */       
/*  938 */       for (int var3 = 0; var3 < 256; var3++) {
/*      */         
/*  940 */         float skylight_brightness = (var3 / 16) / 15.0F;
/*  941 */         float blocklight_brightness = (var3 % 16) / 15.0F;
/*      */         
/*  943 */         float var4 = var2.getSunBrightness(1.0F) * 0.95F + 0.05F;
/*  944 */         float var5 = var2.provider.lightBrightnessTable[var3 / 16] * var4;
/*  945 */         float var6 = var2.provider.lightBrightnessTable[var3 % 16] * (this.torchFlickerX * 0.1F + 1.5F);
/*      */         
/*  947 */         if (var2.isOverworld() && var2.getMoonAscensionFactor() > 0.0F) {
/*      */           
/*  949 */           float moon_factor = MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F;
/*      */           
/*  951 */           var5 = var5 * (1.0F - moon_factor) + var5 * moon_factor * var2.getMoonBrightness(par1, true);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  959 */         if (var2.lastLightningBolt > 0) {
/*      */           
/*  961 */           float distance_factor = (float)Math.pow(this.mc.raining_strength_for_render_view_entity, 4.0D);
/*      */           
/*  963 */           var5 = var2.provider.lightBrightnessTable[var3 / 16] * distance_factor + var5 * (1.0F - distance_factor);
/*      */         } 
/*      */         
/*  966 */         float var7 = var5 * (var2.getSunBrightness(1.0F) * 0.65F + 0.35F);
/*  967 */         float var8 = var5 * (var2.getSunBrightness(1.0F) * 0.65F + 0.35F);
/*  968 */         float var11 = var6 * ((var6 * 0.6F + 0.4F) * 0.6F + 0.4F);
/*  969 */         float var12 = var6 * (var6 * var6 * 0.6F + 0.4F);
/*  970 */         float var13 = var7 + var6;
/*  971 */         float var14 = var8 + var11;
/*  972 */         float var15 = var5 + var12;
/*  973 */         var13 = var13 * 0.96F + 0.03F;
/*  974 */         var14 = var14 * 0.96F + 0.03F;
/*  975 */         var15 = var15 * 0.96F + 0.03F;
/*      */ 
/*      */         
/*  978 */         if (this.field_82831_U > 0.0F) {
/*      */           
/*  980 */           float f = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * par1;
/*  981 */           var13 = var13 * (1.0F - f) + var13 * 0.7F * f;
/*  982 */           var14 = var14 * (1.0F - f) + var14 * 0.6F * f;
/*  983 */           var15 = var15 * (1.0F - f) + var15 * 0.6F * f;
/*      */         } 
/*      */         
/*  986 */         if (var2.provider.dimensionId == 1) {
/*      */           
/*  988 */           var13 = 0.22F + var6 * 0.75F;
/*  989 */           var14 = 0.28F + var11 * 0.75F;
/*  990 */           var15 = 0.25F + var12 * 0.75F;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  996 */         if (this.mc.thePlayer.isPotionActive(Potion.nightVision) || Minecraft.night_vision_override) {
/*      */ 
/*      */ 
/*      */           
/* 1000 */           float f1 = Minecraft.night_vision_override ? 1.0F : getNightVisionBrightness(this.mc.thePlayer, par1);
/*      */           
/* 1002 */           float f2 = 1.0F / var13;
/*      */           
/* 1004 */           if (f2 > 1.0F / var14)
/*      */           {
/* 1006 */             f2 = 1.0F / var14;
/*      */           }
/*      */           
/* 1009 */           if (f2 > 1.0F / var15)
/*      */           {
/* 1011 */             f2 = 1.0F / var15;
/*      */           }
/*      */           
/* 1014 */           var13 = var13 * (1.0F - f1) + var13 * f2 * f1;
/* 1015 */           var14 = var14 * (1.0F - f1) + var14 * f2 * f1;
/* 1016 */           var15 = var15 * (1.0F - f1) + var15 * f2 * f1;
/*      */         } 
/*      */         
/* 1019 */         if (var13 > 1.0F)
/*      */         {
/* 1021 */           var13 = 1.0F;
/*      */         }
/*      */         
/* 1024 */         if (var14 > 1.0F)
/*      */         {
/* 1026 */           var14 = 1.0F;
/*      */         }
/*      */         
/* 1029 */         if (var15 > 1.0F)
/*      */         {
/* 1031 */           var15 = 1.0F;
/*      */         }
/*      */         
/* 1034 */         float var16 = this.mc.gameSettings.gammaSetting;
/* 1035 */         float var17 = 1.0F - var13;
/* 1036 */         float var18 = 1.0F - var14;
/* 1037 */         float var19 = 1.0F - var15;
/* 1038 */         var17 = 1.0F - var17 * var17 * var17 * var17;
/* 1039 */         var18 = 1.0F - var18 * var18 * var18 * var18;
/* 1040 */         var19 = 1.0F - var19 * var19 * var19 * var19;
/* 1041 */         var13 = var13 * (1.0F - var16) + var17 * var16;
/* 1042 */         var14 = var14 * (1.0F - var16) + var18 * var16;
/* 1043 */         var15 = var15 * (1.0F - var16) + var19 * var16;
/* 1044 */         var13 = var13 * 0.96F + 0.03F;
/* 1045 */         var14 = var14 * 0.96F + 0.03F;
/* 1046 */         var15 = var15 * 0.96F + 0.03F;
/*      */         
/* 1048 */         if (var2.isBloodMoonNight()) {
/*      */           
/* 1050 */           float blocklight_attenuation = MathHelper.clamp_float(1.0F - blocklight_brightness * 2.0F, 0.0F, 1.0F);
/*      */           
/* 1052 */           var13 *= 1.0F + 1.0F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F * skylight_brightness * blocklight_attenuation;
/* 1053 */           var14 *= 1.0F + 0.4F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F * skylight_brightness * blocklight_attenuation;
/*      */         }
/* 1055 */         else if (var2.isHarvestMoonNight()) {
/*      */           
/* 1057 */           float blocklight_attenuation = MathHelper.clamp_float(1.0F - blocklight_brightness * 2.0F, 0.0F, 1.0F);
/*      */           
/* 1059 */           var13 *= 1.0F + 0.6F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 4.0F * skylight_brightness * blocklight_attenuation;
/* 1060 */           var14 *= 1.0F + 0.4F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 4.0F * skylight_brightness * blocklight_attenuation;
/* 1061 */           var15 *= 1.0F - 0.2F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 4.0F * skylight_brightness * blocklight_attenuation;
/*      */         }
/* 1063 */         else if (var2.isBlueMoonNight()) {
/*      */           
/* 1065 */           float blocklight_attenuation = MathHelper.clamp_float(1.0F - blocklight_brightness * 2.0F, 0.0F, 1.0F);
/*      */           
/* 1067 */           var15 *= 1.0F + 0.5F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 2.5F * skylight_brightness * blocklight_attenuation;
/* 1068 */           var14 *= 1.0F + 0.2F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 2.5F * skylight_brightness * blocklight_attenuation;
/*      */         } 
/*      */         
/* 1071 */         if (var13 > 1.0F)
/*      */         {
/* 1073 */           var13 = 1.0F;
/*      */         }
/*      */         
/* 1076 */         if (var14 > 1.0F)
/*      */         {
/* 1078 */           var14 = 1.0F;
/*      */         }
/*      */         
/* 1081 */         if (var15 > 1.0F)
/*      */         {
/* 1083 */           var15 = 1.0F;
/*      */         }
/*      */         
/* 1086 */         if (var13 < 0.0F)
/*      */         {
/* 1088 */           var13 = 0.0F;
/*      */         }
/*      */         
/* 1091 */         if (var14 < 0.0F)
/*      */         {
/* 1093 */           var14 = 0.0F;
/*      */         }
/*      */         
/* 1096 */         if (var15 < 0.0F)
/*      */         {
/* 1098 */           var15 = 0.0F;
/*      */         }
/*      */         
/* 1101 */         if (var3 == 0) {
/*      */           
/* 1103 */           float min = 0.062F;
/*      */           
/* 1105 */           if (var13 < min) {
/* 1106 */             var13 = min;
/*      */           }
/* 1108 */           if (var14 < min) {
/* 1109 */             var14 = min;
/*      */           }
/* 1111 */           if (var15 < min) {
/* 1112 */             var15 = min;
/*      */           }
/*      */         } 
/* 1115 */         short var20 = 255;
/* 1116 */         int var21 = (int)(var13 * 255.0F);
/* 1117 */         int var22 = (int)(var14 * 255.0F);
/* 1118 */         int var23 = (int)(var15 * 255.0F);
/* 1119 */         this.lightmapColors[var3] = var20 << 24 | var21 << 16 | var22 << 8 | var23;
/*      */       } 
/*      */       
/* 1122 */       this.lightmapTexture.updateDynamicTexture();
/* 1123 */       this.lightmapUpdateNeeded = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float getNightVisionBrightness(EntityPlayer par1EntityPlayer, float par2) {
/* 1132 */     int var3 = par1EntityPlayer.getActivePotionEffect(Potion.nightVision).getDuration();
/* 1133 */     return (var3 > 200) ? 1.0F : (0.7F + MathHelper.sin((var3 - par2) * 3.1415927F * 0.2F) * 0.3F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateCameraAndRender(float par1) {
/* 1141 */     this.mc.mcProfiler.startSection("lightTex");
/*      */     
/* 1143 */     if (this.lightmapUpdateNeeded)
/*      */     {
/* 1145 */       updateLightmap(par1);
/*      */     }
/*      */     
/* 1148 */     this.mc.mcProfiler.endSection();
/* 1149 */     boolean var2 = Display.isActive();
/*      */     
/* 1151 */     if (!var2 && this.mc.gameSettings.pauseOnLostFocus && (!this.mc.gameSettings.touchscreen || !Mouse.isButtonDown(1))) {
/*      */       
/* 1153 */       if (Minecraft.getSystemTime() - this.prevFrameTime > 500L)
/*      */       {
/* 1155 */         this.mc.displayInGameMenu();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1160 */       this.prevFrameTime = Minecraft.getSystemTime();
/*      */     } 
/*      */     
/* 1163 */     this.mc.mcProfiler.startSection("mouse");
/*      */     
/* 1165 */     if (this.mc.inGameHasFocus && var2) {
/*      */       
/* 1167 */       this.mc.mouseHelper.mouseXYChange();
/* 1168 */       float var3 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
/* 1169 */       float var4 = var3 * var3 * var3 * 8.0F;
/*      */       
/* 1171 */       if (this.mc.thePlayer.zoomed) {
/* 1172 */         var4 /= 4.0F;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1178 */       if (this.mc.thePlayer.isPotionActive(Potion.moveSlowdown));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1208 */       float overall_speed_modifier = this.mc.thePlayer.getSpeedBoostVsSlowDown();
/*      */       
/* 1210 */       if (overall_speed_modifier < 0.0F) {
/*      */         
/* 1212 */         if (overall_speed_modifier < -0.8F) {
/* 1213 */           overall_speed_modifier = -0.8F;
/*      */         }
/* 1215 */         var4 /= 1.0F - overall_speed_modifier * 15.0F;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1220 */       float var5 = this.mc.mouseHelper.deltaX * var4;
/* 1221 */       float var6 = this.mc.mouseHelper.deltaY * var4;
/* 1222 */       byte var7 = 1;
/*      */       
/* 1224 */       if (this.mc.gameSettings.invertMouse)
/*      */       {
/* 1226 */         var7 = -1;
/*      */       }
/*      */       
/* 1229 */       if (this.mc.gameSettings.smoothCamera) {
/*      */         
/* 1231 */         this.smoothCamYaw += var5;
/* 1232 */         this.smoothCamPitch += var6;
/* 1233 */         float var8 = par1 - this.smoothCamPartialTicks;
/* 1234 */         this.smoothCamPartialTicks = par1;
/* 1235 */         var5 = this.smoothCamFilterX * var8;
/* 1236 */         var6 = this.smoothCamFilterY * var8;
/* 1237 */         this.mc.thePlayer.setAngles(var5, var6 * var7);
/*      */       }
/*      */       else {
/*      */         
/* 1241 */         this.mc.thePlayer.setAngles(var5, var6 * var7);
/*      */       } 
/*      */     } 
/*      */     
/* 1245 */     this.mc.mcProfiler.endSection();
/*      */     
/* 1247 */     if (!this.mc.skipRenderWorld) {
/*      */       
/* 1249 */       anaglyphEnable = this.mc.gameSettings.anaglyph;
/* 1250 */       ScaledResolution var13 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/* 1251 */       int var14 = var13.getScaledWidth();
/* 1252 */       int var15 = var13.getScaledHeight();
/* 1253 */       int var16 = Mouse.getX() * var14 / this.mc.displayWidth;
/* 1254 */       int var17 = var15 - Mouse.getY() * var15 / this.mc.displayHeight - 1;
/* 1255 */       int var18 = performanceToFps(this.mc.gameSettings.limitFramerate);
/*      */ 
/*      */       
/* 1258 */       if (this.mc.theWorld != null && this.mc.theWorld.tick_has_passed) {
/*      */         
/* 1260 */         this.mc.mcProfiler.startSection("level");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1271 */         if (this.mc.inGameHasFocus || !this.mc.thePlayer.isGhost())
/*      */         {
/* 1273 */           if (this.mc.gameSettings.limitFramerate == 0) {
/*      */             
/* 1275 */             renderWorld(par1, 0L);
/*      */           }
/* 1277 */           else if (this.mc.gameSettings.limitFramerate == 3) {
/*      */             
/* 1279 */             renderWorld(par1, this.renderEndNanoTime + 16666666L);
/*      */           }
/*      */           else {
/*      */             
/* 1283 */             renderWorld(par1, this.renderEndNanoTime + (1000000000 / var18));
/*      */           } 
/*      */         }
/*      */         
/* 1287 */         this.renderEndNanoTime = System.nanoTime();
/* 1288 */         this.mc.mcProfiler.endStartSection("gui");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1296 */         this.mc.ingameGUI.renderGameOverlay(par1, this.mc.isGuiOpen(), var16, var17);
/*      */         
/* 1298 */         this.mc.mcProfiler.endSection();
/*      */       }
/*      */       else {
/*      */         
/* 1302 */         GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/* 1303 */         GL11.glMatrixMode(5889);
/* 1304 */         GL11.glLoadIdentity();
/* 1305 */         GL11.glMatrixMode(5888);
/* 1306 */         GL11.glLoadIdentity();
/* 1307 */         setupOverlayRendering();
/* 1308 */         this.renderEndNanoTime = System.nanoTime();
/*      */       } 
/*      */ 
/*      */       
/* 1312 */       if (this.mc.isGuiOpen()) {
/*      */         
/* 1314 */         GL11.glClear(256);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1320 */           if (this.mc.currentScreen != null) {
/* 1321 */             this.mc.currentScreen.drawScreen(var16, var17, par1);
/*      */           }
/* 1323 */           if (this.mc.isChatImposed()) {
/* 1324 */             this.mc.imposed_gui_chat.drawScreen(var16, var17, par1);
/*      */           }
/* 1326 */         } catch (Throwable var12) {
/*      */           
/* 1328 */           CrashReport var10 = CrashReport.makeCrashReport(var12, "Rendering screen");
/* 1329 */           CrashReportCategory var11 = var10.makeCategory("Screen render details");
/* 1330 */           var11.addCrashSectionCallable("Screen name", new CallableScreenName(this));
/* 1331 */           var11.addCrashSectionCallable("Mouse location", new CallableMouseLocation(this, var16, var17));
/* 1332 */           var11.addCrashSectionCallable("Screen size", new CallableScreenSize(this, var13));
/* 1333 */           throw new ReportedException(var10);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void renderWorld(float par1, long par2) {
/* 1341 */     this.mc.mcProfiler.startSection("lightTex");
/*      */     
/* 1343 */     if (this.lightmapUpdateNeeded)
/*      */     {
/* 1345 */       updateLightmap(par1);
/*      */     }
/*      */     
/* 1348 */     GL11.glEnable(2884);
/* 1349 */     GL11.glEnable(2929);
/*      */     
/* 1351 */     if (this.mc.renderViewEntity == null)
/*      */     {
/* 1353 */       this.mc.renderViewEntity = this.mc.thePlayer;
/*      */     }
/*      */     
/* 1356 */     this.mc.mcProfiler.endStartSection("pick");
/* 1357 */     getMouseOver(par1);
/*      */     
/* 1359 */     EntityLivingBase var4 = this.mc.renderViewEntity;
/*      */     
/* 1361 */     if (var4 instanceof EntityPlayer) {
/*      */       
/* 1363 */       EntityPlayer entity_player = (EntityPlayer)var4;
/*      */       
/* 1365 */       if (entity_player.inBed()) {
/* 1366 */         entity_player.setPositionAndRotationInBed();
/*      */       }
/*      */     } 
/* 1369 */     RenderGlobal var5 = this.mc.renderGlobal;
/* 1370 */     EffectRenderer var6 = this.mc.effectRenderer;
/* 1371 */     double var7 = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * par1;
/* 1372 */     double var9 = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * par1;
/* 1373 */     double var11 = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * par1;
/*      */     
/* 1375 */     this.mc.mcProfiler.endStartSection("center");
/*      */     
/* 1377 */     for (int var13 = 0; var13 < 2; var13++) {
/*      */       
/* 1379 */       if (this.mc.gameSettings.anaglyph) {
/*      */         
/* 1381 */         anaglyphField = var13;
/*      */         
/* 1383 */         if (anaglyphField == 0) {
/*      */           
/* 1385 */           GL11.glColorMask(false, true, true, false);
/*      */         }
/*      */         else {
/*      */           
/* 1389 */           GL11.glColorMask(true, false, false, false);
/*      */         } 
/*      */       } 
/*      */       
/* 1393 */       this.mc.mcProfiler.endStartSection("clear");
/* 1394 */       GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/* 1395 */       updateFogColor(par1);
/* 1396 */       GL11.glClear(16640);
/* 1397 */       GL11.glEnable(2884);
/* 1398 */       this.mc.mcProfiler.endStartSection("camera");
/*      */       
/* 1400 */       setupCameraTransform(par1, var13, false);
/*      */ 
/*      */ 
/*      */       
/* 1404 */       if (this.last_vsync_nanotime != -1L) {
/*      */         
/* 1406 */         long milliseconds_since_last_vsync = (System.nanoTime() - this.last_vsync_nanotime) / 1000000L;
/* 1407 */         this.mc.downtimeProcessing(16L - milliseconds_since_last_vsync);
/*      */       } 
/*      */       
/* 1410 */       ActiveRenderInfo.updateRenderInfo(this.mc.thePlayer, (this.mc.gameSettings.thirdPersonView == 2));
/*      */       
/* 1412 */       this.last_vsync_nanotime = System.nanoTime();
/*      */       
/* 1414 */       if (this.last_vsync_nanotime > this.fps_start_time + 1000000000L) {
/*      */         
/* 1416 */         this.fps_start_time = this.last_vsync_nanotime;
/* 1417 */         Minecraft.last_fps = this.fps_counter;
/* 1418 */         this.fps_counter = 0;
/*      */       }
/*      */       else {
/*      */         
/* 1422 */         this.fps_counter++;
/*      */       } 
/*      */       
/* 1425 */       if (this.last_vsync_nanotime > this.fp10s_start_time + 10000000000L) {
/*      */         
/* 1427 */         this.fp10s_start_time = this.last_vsync_nanotime;
/* 1428 */         Minecraft.last_fp10s = this.fp10s_counter;
/* 1429 */         this.fp10s_counter = 0;
/*      */       }
/*      */       else {
/*      */         
/* 1433 */         this.fp10s_counter++;
/*      */       } 
/*      */       
/* 1436 */       this.mc.mcProfiler.endStartSection("frustrum");
/* 1437 */       ClippingHelperImpl.getInstance();
/*      */ 
/*      */       
/* 1440 */       if (this.mc.gameSettings.getRenderDistance() < 2) {
/*      */         
/* 1442 */         setupFog(-1, par1);
/* 1443 */         this.mc.mcProfiler.endStartSection("sky");
/* 1444 */         var5.renderSky(par1);
/*      */       } 
/*      */       
/* 1447 */       GL11.glEnable(2912);
/* 1448 */       setupFog(1, par1);
/*      */       
/* 1450 */       if (this.mc.gameSettings.ambientOcclusion != 0)
/*      */       {
/* 1452 */         GL11.glShadeModel(7425);
/*      */       }
/*      */       
/* 1455 */       this.mc.mcProfiler.endStartSection("culling");
/* 1456 */       Frustrum var14 = new Frustrum();
/* 1457 */       var14.setPosition(var7, var9, var11);
/* 1458 */       this.mc.renderGlobal.clipRenderersByFrustum(var14, par1);
/*      */       
/* 1460 */       if (var13 == 0) {
/*      */         
/* 1462 */         this.mc.mcProfiler.endStartSection("updatechunks");
/*      */         
/* 1464 */         while (!this.mc.renderGlobal.updateRenderers(var4, false) && par2 != 0L) {
/*      */           
/* 1466 */           long var15 = par2 - System.nanoTime();
/*      */           
/* 1468 */           if (this.mc.gameSettings.limitFramerate == 3) {
/*      */             
/* 1470 */             if (var15 < 1000000L || var15 > 1000000000L) {
/*      */               break;
/*      */             }
/*      */             
/*      */             continue;
/*      */           } 
/* 1476 */           if (var15 < 0L || var15 > 1000000000L) {
/*      */             break;
/*      */           }
/*      */         } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1511 */       if (var4.posY < 128.0D)
/*      */       {
/* 1513 */         renderCloudsCheck(var5, par1);
/*      */       }
/*      */       
/* 1516 */       this.mc.mcProfiler.endStartSection("prepareterrain");
/* 1517 */       setupFog(0, par1);
/* 1518 */       GL11.glEnable(2912);
/* 1519 */       this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 1520 */       RenderHelper.disableStandardItemLighting();
/* 1521 */       this.mc.mcProfiler.endStartSection("terrain");
/* 1522 */       var5.sortAndRender(var4, 0, par1);
/* 1523 */       GL11.glShadeModel(7424);
/*      */ 
/*      */       
/* 1526 */       if (this.debugViewDirection == 0) {
/*      */         
/* 1528 */         RenderHelper.enableStandardItemLighting();
/* 1529 */         this.mc.mcProfiler.endStartSection("entities");
/* 1530 */         var5.renderEntities(var4.getPosition(par1), var14, par1);
/* 1531 */         enableLightmap(par1);
/* 1532 */         this.mc.mcProfiler.endStartSection("litParticles");
/* 1533 */         var6.renderLitParticles(var4, par1);
/* 1534 */         RenderHelper.disableStandardItemLighting();
/* 1535 */         setupFog(0, par1);
/* 1536 */         this.mc.mcProfiler.endStartSection("particles");
/* 1537 */         var6.renderParticles(var4, par1);
/* 1538 */         disableLightmap(par1);
/*      */ 
/*      */         
/* 1541 */         if (this.mc.objectMouseOver != null && var4.isInsideOfMaterial(Material.water) && var4 instanceof EntityPlayer && this.mc.gameSettings.gui_mode == 0) {
/*      */           
/* 1543 */           EntityPlayer var17 = (EntityPlayer)var4;
/* 1544 */           GL11.glDisable(3008);
/* 1545 */           this.mc.mcProfiler.endStartSection("outline");
/* 1546 */           var5.drawSelectionBox(var17, this.mc.objectMouseOver, 0, par1);
/* 1547 */           GL11.glEnable(3008);
/*      */         } 
/*      */       } 
/*      */       
/* 1551 */       GL11.glDisable(3042);
/* 1552 */       GL11.glEnable(2884);
/* 1553 */       GL11.glBlendFunc(770, 771);
/* 1554 */       GL11.glDepthMask(true);
/* 1555 */       setupFog(0, par1);
/* 1556 */       GL11.glEnable(3042);
/* 1557 */       GL11.glDisable(2884);
/* 1558 */       this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/*      */ 
/*      */       
/* 1561 */       if (this.mc.gameSettings.isFancyGraphicsEnabled()) {
/*      */         
/* 1563 */         this.mc.mcProfiler.endStartSection("water");
/*      */         
/* 1565 */         if (this.mc.gameSettings.ambientOcclusion != 0)
/*      */         {
/* 1567 */           GL11.glShadeModel(7425);
/*      */         }
/*      */         
/* 1570 */         GL11.glColorMask(false, false, false, false);
/* 1571 */         int var18 = var5.sortAndRender(var4, 1, par1);
/*      */         
/* 1573 */         if (this.mc.gameSettings.anaglyph) {
/*      */           
/* 1575 */           if (anaglyphField == 0)
/*      */           {
/* 1577 */             GL11.glColorMask(false, true, true, true);
/*      */           }
/*      */           else
/*      */           {
/* 1581 */             GL11.glColorMask(true, false, false, true);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1586 */           GL11.glColorMask(true, true, true, true);
/*      */         } 
/*      */         
/* 1589 */         if (var18 > 0)
/*      */         {
/* 1591 */           var5.renderAllRenderLists(1, par1);
/*      */         }
/*      */         
/* 1594 */         GL11.glShadeModel(7424);
/*      */       }
/*      */       else {
/*      */         
/* 1598 */         this.mc.mcProfiler.endStartSection("water");
/* 1599 */         var5.sortAndRender(var4, 1, par1);
/*      */       } 
/*      */       
/* 1602 */       GL11.glDepthMask(true);
/* 1603 */       GL11.glEnable(2884);
/* 1604 */       GL11.glDisable(3042);
/*      */ 
/*      */       
/* 1607 */       if (this.cameraZoom == 1.0D && var4 instanceof EntityPlayer && this.mc.gameSettings.gui_mode == 0 && this.mc.objectMouseOver != null && !var4.isInsideOfMaterial(Material.water)) {
/*      */         
/* 1609 */         EntityPlayer var17 = (EntityPlayer)var4;
/* 1610 */         GL11.glDisable(3008);
/* 1611 */         this.mc.mcProfiler.endStartSection("outline");
/* 1612 */         var5.drawSelectionBox(var17, this.mc.objectMouseOver, 0, par1);
/* 1613 */         GL11.glEnable(3008);
/*      */       } 
/*      */       
/* 1616 */       this.mc.mcProfiler.endStartSection("destroyProgress");
/* 1617 */       GL11.glEnable(3042);
/* 1618 */       GL11.glBlendFunc(770, 1);
/* 1619 */       var5.drawBlockDamageTexture(Tessellator.instance, (EntityPlayer)var4, par1);
/* 1620 */       GL11.glDisable(3042);
/* 1621 */       this.mc.mcProfiler.endStartSection("weather");
/* 1622 */       renderRainSnow(par1);
/* 1623 */       GL11.glDisable(2912);
/*      */       
/* 1625 */       if (var4.posY >= 128.0D)
/*      */       {
/* 1627 */         renderCloudsCheck(var5, par1);
/*      */       }
/*      */       
/* 1630 */       this.mc.mcProfiler.endStartSection("hand");
/*      */       
/* 1632 */       if (this.cameraZoom == 1.0D) {
/*      */         
/* 1634 */         GL11.glClear(256);
/* 1635 */         renderHand(par1, var13);
/*      */       } 
/*      */       
/* 1638 */       if (!this.mc.gameSettings.anaglyph) {
/*      */         
/* 1640 */         this.mc.mcProfiler.endSection();
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 1645 */     GL11.glColorMask(true, true, true, false);
/* 1646 */     this.mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderCloudsCheck(RenderGlobal par1RenderGlobal, float par2) {
/* 1654 */     if (this.mc.gameSettings.shouldRenderClouds()) {
/*      */       
/* 1656 */       this.mc.mcProfiler.endStartSection("clouds");
/* 1657 */       GL11.glPushMatrix();
/* 1658 */       setupFog(0, par2);
/* 1659 */       GL11.glEnable(2912);
/*      */       
/* 1661 */       par1RenderGlobal.renderClouds(par2);
/*      */       
/* 1663 */       GL11.glDisable(2912);
/* 1664 */       setupFog(1, par2);
/* 1665 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addRainParticles() {
/* 1672 */     float var1 = this.mc.theWorld.getRainStrength(1.0F);
/*      */     
/* 1674 */     boolean is_blood_moon = this.mc.theWorld.isBloodMoon24HourPeriod();
/*      */ 
/*      */     
/* 1677 */     if (!this.mc.gameSettings.isFancyGraphicsEnabled())
/*      */     {
/* 1679 */       var1 /= 2.0F;
/*      */     }
/*      */     
/* 1682 */     if (var1 != 0.0F) {
/*      */       
/* 1684 */       this.random.setSeed(this.rendererUpdateCount * 312987231L);
/* 1685 */       EntityLivingBase var2 = this.mc.renderViewEntity;
/* 1686 */       WorldClient var3 = this.mc.theWorld;
/* 1687 */       int var4 = MathHelper.floor_double(var2.posX);
/* 1688 */       int var5 = MathHelper.floor_double(var2.posY);
/* 1689 */       int var6 = MathHelper.floor_double(var2.posZ);
/* 1690 */       byte var7 = 10;
/* 1691 */       double var8 = 0.0D;
/* 1692 */       double var10 = 0.0D;
/* 1693 */       double var12 = 0.0D;
/* 1694 */       int var14 = 0;
/* 1695 */       int var15 = (int)(100.0F * var1 * var1);
/*      */       
/* 1697 */       if (this.mc.gameSettings.particleSetting == 1) {
/*      */         
/* 1699 */         var15 >>= 1;
/*      */       }
/* 1701 */       else if (this.mc.gameSettings.particleSetting == 2) {
/*      */         
/* 1703 */         var15 = 0;
/*      */       } 
/*      */       
/* 1706 */       int index = Minecraft.getThreadIndex();
/*      */       
/* 1708 */       for (int var16 = 0; var16 < var15; var16++) {
/*      */         
/* 1710 */         int var17 = var4 + this.random.nextInt(var7) - this.random.nextInt(var7);
/* 1711 */         int var18 = var6 + this.random.nextInt(var7) - this.random.nextInt(var7);
/* 1712 */         int var19 = var3.getPrecipitationHeight(var17, var18);
/* 1713 */         int var20 = var3.getBlockId(var17, var19 - 1, var18);
/* 1714 */         BiomeGenBase var21 = var3.getBiomeGenForCoords(var17, var18);
/*      */ 
/*      */         
/* 1717 */         if (var19 <= var5 + var7 && var19 >= var5 - var7 && var21.canSpawnLightningBolt(is_blood_moon) && var21.getFloatTemperature() >= 0.2F)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 1722 */           if (var20 > 0) {
/*      */             double pos_y;
/* 1724 */             float var22 = this.random.nextFloat();
/* 1725 */             float var23 = this.random.nextFloat();
/*      */             
/* 1727 */             Block block = Block.getBlock(var20);
/*      */ 
/*      */             
/* 1730 */             if (block.blockMaterial == Material.lava) {
/*      */ 
/*      */ 
/*      */               
/* 1734 */               pos_y = (var19 - BlockFluid.getFluidHeightPercent(this.mc.theWorld.getBlockMetadata(var17, var19 - 1, var18)) + 0.1F + 0.125F);
/*      */               
/* 1736 */               this.mc.effectRenderer.addEffect(new EntitySmokeFX(var3, (var17 + var22), pos_y, (var18 + var23), 0.0D, 0.0D, 0.0D));
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/* 1742 */             if (block.blockMaterial.isLiquid()) {
/*      */               
/* 1744 */               pos_y = (var19 - BlockFluid.getFluidHeightPercent(this.mc.theWorld.getBlockMetadata(var17, var19 - 1, var18)) + 0.1F + 0.125F);
/*      */             }
/* 1746 */             else if (block.isAlwaysStandardFormCube()) {
/*      */               
/* 1748 */               pos_y = (var19 + 0.1F);
/*      */             }
/*      */             else {
/*      */               
/* 1752 */               if (block instanceof BlockTrapDoor && BlockTrapDoor.isTrapdoorOpen(this.mc.theWorld.getBlockMetadata(var17, var19 - 1, var18))) {
/*      */                 continue;
/*      */               }
/* 1755 */               block.setBlockBoundsBasedOnStateAndNeighbors(this.mc.theWorld, var17, var19 - 1, var18);
/*      */               
/* 1757 */               pos_y = (var19 - 1) + block.getBlockBoundsMaxY(index) + 0.10000000149011612D;
/*      */             } 
/*      */             
/* 1760 */             var14++;
/*      */             
/* 1762 */             if (this.random.nextInt(var14) == 0) {
/*      */               
/* 1764 */               var8 = (var17 + var22);
/* 1765 */               var10 = (var19 + 0.1F) - Block.blocksList[var20].getBlockBoundsMinY(index);
/* 1766 */               var12 = (var18 + var23);
/*      */             } 
/*      */ 
/*      */             
/* 1770 */             this.mc.effectRenderer.addEffect(new EntityRainFX(var3, (var17 + var22), pos_y, (var18 + var23)));
/*      */           } 
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/* 1776 */       boolean player_is_outdoors = this.mc.thePlayer.isOutdoors();
/* 1777 */       float sleep_factor = 1.0F - this.mc.thePlayer.falling_asleep_counter / 50.0F;
/* 1778 */       float distance_from_rain_factor = (float)Math.pow(this.mc.raining_strength_for_render_view_entity, 4.0D);
/*      */       
/* 1780 */       if (sleep_factor < 0.0F) {
/* 1781 */         sleep_factor = 0.0F;
/*      */       }
/* 1783 */       if (var14 > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
/*      */         
/* 1785 */         this.rainSoundCounter = 0;
/*      */         
/* 1787 */         if (var10 > var2.posY + 1.0D && var3.getPrecipitationHeight(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posZ)) > MathHelper.floor_double(var2.posY)) {
/*      */ 
/*      */ 
/*      */           
/* 1791 */           if (player_is_outdoors) {
/* 1792 */             this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.1F * sleep_factor * distance_from_rain_factor, 0.5F, false);
/*      */           } else {
/* 1794 */             this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.025F * sleep_factor * distance_from_rain_factor, 0.125F, false);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/* 1800 */         else if (player_is_outdoors) {
/* 1801 */           this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.2F * sleep_factor * distance_from_rain_factor, 1.0F, false);
/*      */         } else {
/* 1803 */           this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.05F * sleep_factor * distance_from_rain_factor, 0.25F, false);
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
/*      */   protected void renderRainSnow(float par1) {
/* 1815 */     if (this.mc.renderViewEntity.ticksExisted < 1) {
/*      */       return;
/*      */     }
/* 1818 */     float var2 = this.mc.theWorld.getRainStrength(par1);
/*      */     
/* 1820 */     if (var2 > 0.0F) {
/*      */       
/* 1822 */       boolean is_blood_moon = this.mc.theWorld.isBloodMoon24HourPeriod();
/*      */ 
/*      */ 
/*      */       
/* 1826 */       enableLightmap(par1);
/*      */       
/* 1828 */       if (this.rainXCoords == null) {
/*      */         
/* 1830 */         this.rainXCoords = new float[1024];
/* 1831 */         this.rainYCoords = new float[1024];
/*      */         
/* 1833 */         for (int var3 = 0; var3 < 32; var3++) {
/*      */           
/* 1835 */           for (int var4 = 0; var4 < 32; var4++) {
/*      */             
/* 1837 */             float var5 = (var4 - 16);
/* 1838 */             float var6 = (var3 - 16);
/* 1839 */             float var7 = MathHelper.sqrt_float(var5 * var5 + var6 * var6);
/* 1840 */             this.rainXCoords[var3 << 5 | var4] = -var6 / var7;
/* 1841 */             this.rainYCoords[var3 << 5 | var4] = var5 / var7;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1846 */       EntityLivingBase var41 = this.mc.renderViewEntity;
/* 1847 */       WorldClient var42 = this.mc.theWorld;
/* 1848 */       int var43 = MathHelper.floor_double(var41.posX);
/* 1849 */       int var44 = MathHelper.floor_double(var41.posY);
/* 1850 */       int var45 = MathHelper.floor_double(var41.posZ);
/* 1851 */       Tessellator var8 = Tessellator.instance;
/* 1852 */       GL11.glDisable(2884);
/* 1853 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 1854 */       GL11.glEnable(3042);
/* 1855 */       GL11.glBlendFunc(770, 771);
/* 1856 */       GL11.glAlphaFunc(516, 0.01F);
/*      */       
/* 1858 */       double var9 = var41.lastTickPosX + (var41.posX - var41.lastTickPosX) * par1;
/* 1859 */       double var11 = var41.lastTickPosY + (var41.posY - var41.lastTickPosY) * par1;
/* 1860 */       double var13 = var41.lastTickPosZ + (var41.posZ - var41.lastTickPosZ) * par1;
/* 1861 */       int var15 = MathHelper.floor_double(var11);
/* 1862 */       byte var16 = 5;
/*      */ 
/*      */       
/* 1865 */       if (this.mc.gameSettings.isFancyGraphicsEnabled())
/*      */       {
/* 1867 */         var16 = 10;
/*      */       }
/*      */       
/* 1870 */       boolean var17 = false;
/* 1871 */       byte var18 = -1;
/* 1872 */       float var19 = this.rendererUpdateCount + par1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1880 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1881 */       var17 = false;
/*      */       
/* 1883 */       int type = this.mc.theWorld.getPrecipitationType(this.last_precipitation_type);
/* 1884 */       this.last_precipitation_type = type;
/*      */       
/* 1886 */       for (int var20 = var45 - var16; var20 <= var45 + var16; var20++) {
/*      */         
/* 1888 */         for (int var21 = var43 - var16; var21 <= var43 + var16; var21++) {
/*      */           
/* 1890 */           if (var21 != var43 || var20 != var45)
/*      */           {
/*      */             
/* 1893 */             if (var42.chunkExistsAndIsNotEmptyFromBlockCoords(var21, var20)) {
/*      */ 
/*      */               
/* 1896 */               int dx = var21 - var43;
/* 1897 */               int dz = var20 - var45;
/*      */               
/* 1899 */               int index_wrapped = MathHelper.getWrappedIndex(var20 * 32 + var21, 32768);
/*      */               
/* 1901 */               int var22 = (var20 - var45 + 16) * 32 + var21 - var43 + 16;
/* 1902 */               float var23 = this.rainXCoords[var22] * 0.5F;
/* 1903 */               float var24 = this.rainYCoords[var22] * 0.5F;
/* 1904 */               BiomeGenBase var25 = var42.getBiomeGenForCoords(var21, var20);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1909 */               if (var25.canSpawnLightningBolt(is_blood_moon) || var25.getEnableSnow()) {
/*      */                 
/* 1911 */                 int var26 = var42.getPrecipitationHeight(var21, var20);
/*      */                 
/* 1913 */                 if (var26 < 0) {
/* 1914 */                   var26 = 0;
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1930 */                 int var27 = var44 - var16;
/* 1931 */                 int var28 = var44 + var16;
/*      */                 
/* 1933 */                 if (var27 < var26)
/*      */                 {
/* 1935 */                   var27 = var26;
/*      */                 }
/*      */                 
/* 1938 */                 if (var28 < var26)
/*      */                 {
/* 1940 */                   var28 = var26;
/*      */                 }
/*      */                 
/* 1943 */                 float var29 = 1.0F;
/* 1944 */                 int var30 = var26;
/*      */                 
/* 1946 */                 if (var26 < var15)
/*      */                 {
/* 1948 */                   var30 = var15;
/*      */                 }
/*      */                 
/* 1951 */                 if (var27 != var28) {
/*      */ 
/*      */                   
/* 1954 */                   double var27_adjusted = var42.getBlockRenderTopY(var21, var27 - 1, var20);
/*      */                   
/* 1956 */                   this.random.setSeed((var21 * var21 * 3121 + var21 * 45238971 ^ var20 * var20 * 418711 + var20 * 13761));
/* 1957 */                   float var31 = var25.getFloatTemperature();
/*      */ 
/*      */ 
/*      */                   
/* 1961 */                   if (var42.getWorldChunkManager().getTemperatureAtHeight(var31, var26) >= 0.15F) {
/*      */                     
/* 1963 */                     if (var18 != 0) {
/*      */                       
/* 1965 */                       if (var18 >= 0)
/*      */                       {
/* 1967 */                         var8.draw();
/*      */                       }
/*      */                       
/* 1970 */                       var18 = 0;
/* 1971 */                       this.mc.getTextureManager().bindTexture(locationRainPng);
/* 1972 */                       var8.startDrawingQuads();
/*      */                     } 
/*      */                     
/* 1975 */                     float var32 = ((this.rendererUpdateCount + var21 * var21 * 3121 + var21 * 45238971 + var20 * var20 * 418711 + var20 * 13761 & 0x1F) + par1) / 32.0F * (3.0F + this.random.nextFloat());
/* 1976 */                     double var33 = (var21 + 0.5F) - var41.posX;
/* 1977 */                     double var35 = (var20 + 0.5F) - var41.posZ;
/* 1978 */                     float var37 = MathHelper.sqrt_double(var33 * var33 + var35 * var35) / var16;
/* 1979 */                     float var38 = 1.0F;
/* 1980 */                     var8.setBrightness(var42.getLightBrightnessForSkyBlocks(var21, var30, var20, 0));
/*      */                     
/* 1982 */                     var8.setColorRGBA_F(var38, var38, var38, ((1.0F - var37 * var37) * 0.5F + 0.5F) * var2);
/* 1983 */                     var8.setTranslation(-var9 * 1.0D, -var11 * 1.0D, -var13 * 1.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1991 */                     if (RenderingScheme.current == 0) {
/*      */                       
/* 1993 */                       var8.addVertexWithUV((var21 - var23) + 0.5D, var27, (var20 - var24) + 0.5D, (0.0F * var29), (var27 * var29 / 4.0F + var32 * var29));
/* 1994 */                       var8.addVertexWithUV((var21 + var23) + 0.5D, var27, (var20 + var24) + 0.5D, (1.0F * var29), (var27 * var29 / 4.0F + var32 * var29));
/* 1995 */                       var8.addVertexWithUV((var21 + var23) + 0.5D, var28, (var20 + var24) + 0.5D, (1.0F * var29), (var28 * var29 / 4.0F + var32 * var29));
/* 1996 */                       var8.addVertexWithUV((var21 - var23) + 0.5D, var28, (var20 - var24) + 0.5D, (0.0F * var29), (var28 * var29 / 4.0F + var32 * var29));
/*      */                     }
/*      */                     else {
/*      */                       
/* 2000 */                       this.x[0] = (var21 - var23 + 0.5F);
/* 2001 */                       this.y[0] = var27_adjusted;
/* 2002 */                       this.z[0] = (var20 - var24 + 0.5F);
/* 2003 */                       this.u[0] = 0.0D;
/* 2004 */                       this.v[0] = var27_adjusted * var29 / 4.0D + (var32 * var29);
/*      */                       
/* 2006 */                       this.x[1] = (var21 + var23 + 0.5F);
/* 2007 */                       this.y[1] = var27_adjusted;
/* 2008 */                       this.z[1] = (var20 + var24 + 0.5F);
/* 2009 */                       this.u[1] = var29;
/* 2010 */                       this.v[1] = this.v[0];
/*      */                       
/* 2012 */                       this.x[2] = this.x[1];
/* 2013 */                       this.y[2] = var28;
/* 2014 */                       this.z[2] = this.z[1];
/* 2015 */                       this.u[2] = var29;
/* 2016 */                       this.v[2] = (var28 * var29 / 4.0F + var32 * var29);
/*      */                       
/* 2018 */                       this.x[3] = this.x[0];
/* 2019 */                       this.y[3] = var28;
/* 2020 */                       this.z[3] = this.z[0];
/* 2021 */                       this.u[3] = 0.0D;
/* 2022 */                       this.v[3] = this.v[2];
/*      */                       
/* 2024 */                       var8.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */                     } 
/*      */ 
/*      */ 
/*      */                     
/* 2029 */                     var8.setTranslation(0.0D, 0.0D, 0.0D);
/*      */                   } else {
/*      */                     float horizontal_offset, vertical_offset;
/*      */                     
/* 2033 */                     if (var18 != 1) {
/*      */                       
/* 2035 */                       if (var18 >= 0)
/*      */                       {
/* 2037 */                         var8.draw();
/*      */                       }
/*      */                       
/* 2040 */                       var18 = 1;
/*      */                       
/* 2042 */                       this.mc.getTextureManager().bindTexture(locationSnowPng);
/* 2043 */                       var8.startDrawingQuads();
/*      */                     } 
/*      */                     
/* 2046 */                     float var32 = ((this.rendererUpdateCount & 0x1FF) + par1) / 512.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 2058 */                     if (type == 0) {
/*      */                       
/* 2060 */                       horizontal_offset = RNG.int_32[index_wrapped] / 32.0F;
/*      */                       
/* 2062 */                       vertical_offset = var19 * 0.004F;
/*      */                     }
/* 2064 */                     else if (type == 1) {
/*      */                       
/* 2066 */                       horizontal_offset = 1.0F + var19 * RNG.float_1_minus_float_1[index_wrapped] * 0.004F;
/*      */                       
/* 2068 */                       vertical_offset = var19 * 0.004F;
/*      */                     }
/* 2070 */                     else if (type == 2) {
/*      */                       
/* 2072 */                       horizontal_offset = 1.0F + var19 * RNG.float_1_minus_float_1[index_wrapped] * 0.008F;
/*      */                       
/* 2074 */                       vertical_offset = var19 * 0.004F;
/*      */                     }
/* 2076 */                     else if (type == 3) {
/*      */                       
/* 2078 */                       horizontal_offset = 1.0F + var19 * RNG.float_1_minus_float_1[index_wrapped] * 0.012F;
/* 2079 */                       vertical_offset = var19 * 0.003F;
/*      */                     }
/*      */                     else {
/*      */                       
/* 2083 */                       horizontal_offset = this.random.nextFloat() + var19 * 0.01F * (float)this.random.nextGaussian();
/* 2084 */                       vertical_offset = this.random.nextFloat() + var19 * (float)this.random.nextGaussian() * 0.001F;
/*      */                     } 
/*      */                     
/* 2087 */                     float var46 = horizontal_offset;
/* 2088 */                     float var34 = vertical_offset;
/*      */                     
/* 2090 */                     var34 += RNG.float_1[index_wrapped];
/*      */ 
/*      */ 
/*      */                     
/* 2094 */                     double var35 = (var21 + 0.5F) - var41.posX;
/* 2095 */                     double var47 = (var20 + 0.5F) - var41.posZ;
/* 2096 */                     float var39 = MathHelper.sqrt_double(var35 * var35 + var47 * var47) / var16;
/* 2097 */                     float var40 = 1.0F;
/* 2098 */                     var8.setBrightness((var42.getLightBrightnessForSkyBlocks(var21, var30, var20, 0) * 3 + 15728880) / 4);
/*      */ 
/*      */                     
/* 2101 */                     var8.setColorRGBA_F(var40, var40, var40, MathHelper.clamp_float(0.85F - var39 * 0.35F, 0.0F, 1.0F) * var2);
/*      */                     
/* 2103 */                     var8.setTranslation(-var9 * 1.0D, -var11 * 1.0D, -var13 * 1.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 2111 */                     if (RenderingScheme.current == 0) {
/*      */                       
/* 2113 */                       var8.addVertexWithUV((var21 - var23) + 0.5D, var27, (var20 - var24) + 0.5D, (0.0F * var29 + var46), (var27 * var29 / 4.0F + var32 * var29 + var34));
/* 2114 */                       var8.addVertexWithUV((var21 + var23) + 0.5D, var27, (var20 + var24) + 0.5D, (1.0F * var29 + var46), (var27 * var29 / 4.0F + var32 * var29 + var34));
/* 2115 */                       var8.addVertexWithUV((var21 + var23) + 0.5D, var28, (var20 + var24) + 0.5D, (1.0F * var29 + var46), (var28 * var29 / 4.0F + var32 * var29 + var34));
/* 2116 */                       var8.addVertexWithUV((var21 - var23) + 0.5D, var28, (var20 - var24) + 0.5D, (0.0F * var29 + var46), (var28 * var29 / 4.0F + var32 * var29 + var34));
/*      */                     }
/*      */                     else {
/*      */                       
/* 2120 */                       this.x[0] = (var21 - var23 + 0.5F);
/* 2121 */                       this.y[0] = var27_adjusted;
/* 2122 */                       this.z[0] = (var20 - var24 + 0.5F);
/* 2123 */                       this.u[0] = var46;
/* 2124 */                       this.v[0] = var27_adjusted * var29 / 4.0D + (var32 * var29) + var34;
/*      */                       
/* 2126 */                       this.x[1] = (var21 + var23 + 0.5F);
/* 2127 */                       this.y[1] = var27_adjusted;
/* 2128 */                       this.z[1] = (var20 + var24 + 0.5F);
/* 2129 */                       this.u[1] = (var29 + var46);
/* 2130 */                       this.v[1] = this.v[0];
/*      */                       
/* 2132 */                       this.x[2] = this.x[1];
/* 2133 */                       this.y[2] = var28;
/* 2134 */                       this.z[2] = this.z[1];
/* 2135 */                       this.u[2] = this.u[1];
/* 2136 */                       this.v[2] = (var28 * var29 / 4.0F + var32 * var29 + var34);
/*      */                       
/* 2138 */                       this.x[3] = this.x[0];
/* 2139 */                       this.y[3] = var28;
/* 2140 */                       this.z[3] = this.z[0];
/* 2141 */                       this.u[3] = var46;
/* 2142 */                       this.v[3] = this.v[2];
/*      */                       
/* 2144 */                       if (dx * dx + dz * dz <= 9) {
/*      */                         
/* 2146 */                         float yaw = (float)MathHelper.getYawInDegrees(var21 + 0.5D, var20 + 0.5D, var9, var13);
/* 2147 */                         Vec3 right_side = MathHelper.getNormalizedVector(yaw + 90.0F, 0.0F, var42.getWorldVec3Pool());
/*      */                         
/* 2149 */                         this.x[0] = (var21 + 0.5F) + right_side.xCoord / 2.0D;
/* 2150 */                         this.x[1] = (var21 + 0.5F) - right_side.xCoord / 2.0D;
/* 2151 */                         this.x[2] = this.x[1];
/* 2152 */                         this.x[3] = this.x[0];
/*      */                         
/* 2154 */                         this.z[0] = (var20 + 0.5F) + right_side.zCoord / 2.0D;
/* 2155 */                         this.z[1] = (var20 + 0.5F) - right_side.zCoord / 2.0D;
/* 2156 */                         this.z[2] = this.z[1];
/* 2157 */                         this.z[3] = this.z[0];
/*      */                       } 
/*      */                       
/* 2160 */                       var8.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*      */                     } 
/*      */ 
/*      */ 
/*      */                     
/* 2165 */                     var8.setTranslation(0.0D, 0.0D, 0.0D);
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             }  } 
/*      */         } 
/*      */       } 
/* 2172 */       if (var18 >= 0)
/*      */       {
/* 2174 */         var8.draw();
/*      */       }
/*      */       
/* 2177 */       GL11.glEnable(2884);
/* 2178 */       GL11.glDisable(3042);
/* 2179 */       GL11.glAlphaFunc(516, 0.1F);
/* 2180 */       disableLightmap(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupOverlayRendering() {
/* 2189 */     ScaledResolution var1 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/* 2190 */     GL11.glClear(256);
/* 2191 */     GL11.glMatrixMode(5889);
/* 2192 */     GL11.glLoadIdentity();
/* 2193 */     GL11.glOrtho(0.0D, var1.getScaledWidth_double(), var1.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
/* 2194 */     GL11.glMatrixMode(5888);
/* 2195 */     GL11.glLoadIdentity();
/* 2196 */     GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private double getInterpolatedPosYForVoidFog(EntityLivingBase viewer, float partial_tick) {
/* 2201 */     return Math.max(MathHelper.getInterpolatedValue(viewer.lastTickPosY, viewer.posY, partial_tick), 0.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateFogColor(float par1) {
/* 2209 */     WorldClient var2 = this.mc.theWorld;
/* 2210 */     EntityLivingBase var3 = this.mc.renderViewEntity;
/*      */     
/* 2212 */     float var4 = 1.0F / (4 - this.mc.gameSettings.getRenderDistance());
/* 2213 */     var4 = 1.0F - (float)Math.pow(var4, 0.25D);
/* 2214 */     Vec3 var5 = var2.getSkyColor(this.mc.renderViewEntity, par1);
/* 2215 */     float var6 = (float)var5.xCoord;
/* 2216 */     float var7 = (float)var5.yCoord;
/* 2217 */     float var8 = (float)var5.zCoord;
/* 2218 */     Vec3 var9 = var2.getFogColor(par1, var3);
/* 2219 */     this.fogColorRed = (float)var9.xCoord;
/* 2220 */     this.fogColorGreen = (float)var9.yCoord;
/* 2221 */     this.fogColorBlue = (float)var9.zCoord;
/*      */ 
/*      */ 
/*      */     
/* 2225 */     if (this.mc.gameSettings.getRenderDistance() < 2) {
/*      */       
/* 2227 */       Vec3 var10 = (MathHelper.sin(var2.getCelestialAngleRadians(par1)) > 0.0F) ? var2.getWorldVec3Pool().getVecFromPool(-1.0D, 0.0D, 0.0D) : var2.getWorldVec3Pool().getVecFromPool(1.0D, 0.0D, 0.0D);
/* 2228 */       float f = (float)var3.getLook(par1).dotProduct(var10);
/*      */       
/* 2230 */       if (f < 0.0F)
/*      */       {
/* 2232 */         f = 0.0F;
/*      */       }
/*      */       
/* 2235 */       if (f > 0.0F) {
/*      */         
/* 2237 */         float[] var12 = var2.provider.calcSunriseSunsetColors(var2.getCelestialAngle(par1), par1);
/*      */         
/* 2239 */         if (var12 != null) {
/*      */           
/* 2241 */           f *= var12[3];
/* 2242 */           this.fogColorRed = this.fogColorRed * (1.0F - f) + var12[0] * f;
/* 2243 */           this.fogColorGreen = this.fogColorGreen * (1.0F - f) + var12[1] * f;
/* 2244 */           this.fogColorBlue = this.fogColorBlue * (1.0F - f) + var12[2] * f;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2249 */     this.fogColorRed += (var6 - this.fogColorRed) * var4;
/* 2250 */     this.fogColorGreen += (var7 - this.fogColorGreen) * var4;
/* 2251 */     this.fogColorBlue += (var8 - this.fogColorBlue) * var4;
/* 2252 */     float var19 = var2.getRainStrength(par1);
/*      */ 
/*      */ 
/*      */     
/* 2256 */     if (var19 > 0.0F) {
/*      */       
/* 2258 */       float f1 = 1.0F - var19 * 0.5F;
/* 2259 */       float var20 = 1.0F - var19 * 0.4F;
/* 2260 */       this.fogColorRed *= f1;
/* 2261 */       this.fogColorGreen *= f1;
/* 2262 */       this.fogColorBlue *= var20;
/*      */     } 
/*      */     
/* 2265 */     float var11 = var2.getWeightedThunderStrength(par1);
/*      */     
/* 2267 */     if (var11 > 0.0F) {
/*      */       
/* 2269 */       float var20 = 1.0F - var11 * 0.5F;
/* 2270 */       this.fogColorRed *= var20;
/* 2271 */       this.fogColorGreen *= var20;
/* 2272 */       this.fogColorBlue *= var20;
/*      */     } 
/*      */     
/* 2275 */     int var21 = ActiveRenderInfo.getBlockIdAtEntityViewpoint(this.mc.theWorld, var3, par1);
/*      */ 
/*      */     
/* 2278 */     if (this.cloudFog) {
/*      */       
/* 2280 */       Vec3 var13 = var2.getCloudColour(par1);
/* 2281 */       this.fogColorRed = (float)var13.xCoord;
/* 2282 */       this.fogColorGreen = (float)var13.yCoord;
/* 2283 */       this.fogColorBlue = (float)var13.zCoord;
/*      */     }
/* 2285 */     else if (var21 != 0 && (Block.blocksList[var21]).blockMaterial == Material.water) {
/*      */       
/* 2287 */       float var22 = EnchantmentHelper.getRespiration(var3) * 0.2F;
/* 2288 */       this.fogColorRed = 0.02F + var22;
/* 2289 */       this.fogColorGreen = 0.02F + var22;
/* 2290 */       this.fogColorBlue = 0.2F + var22;
/*      */     }
/* 2292 */     else if (var21 != 0 && (Block.blocksList[var21]).blockMaterial == Material.lava) {
/*      */       
/* 2294 */       this.fogColorRed = 0.6F;
/* 2295 */       this.fogColorGreen = 0.1F;
/* 2296 */       this.fogColorBlue = 0.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2305 */     double var14 = this.mc.theWorld.hasSkylight() ? (getInterpolatedPosYForVoidFog(var3, par1) * var2.provider.getVoidFogYFactor()) : 1.0D;
/*      */     
/* 2307 */     if (var3.isPotionActive(Potion.blindness)) {
/*      */       
/* 2309 */       int var16 = var3.getActivePotionEffect(Potion.blindness).getDuration();
/*      */       
/* 2311 */       if (var16 < 20) {
/*      */         
/* 2313 */         var14 *= (1.0F - var16 / 20.0F);
/*      */       }
/*      */       else {
/*      */         
/* 2317 */         var14 = 0.0D;
/*      */       } 
/*      */     } 
/*      */     
/* 2321 */     if (var14 < 1.0D) {
/*      */       
/* 2323 */       if (var14 < 0.0D)
/*      */       {
/* 2325 */         var14 = 0.0D;
/*      */       }
/*      */       
/* 2328 */       var14 *= var14;
/* 2329 */       this.fogColorRed = (float)(this.fogColorRed * var14);
/* 2330 */       this.fogColorGreen = (float)(this.fogColorGreen * var14);
/* 2331 */       this.fogColorBlue = (float)(this.fogColorBlue * var14);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2336 */     if (this.field_82831_U > 0.0F) {
/*      */       
/* 2338 */       float var23 = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * par1;
/* 2339 */       this.fogColorRed = this.fogColorRed * (1.0F - var23) + this.fogColorRed * 0.7F * var23;
/* 2340 */       this.fogColorGreen = this.fogColorGreen * (1.0F - var23) + this.fogColorGreen * 0.6F * var23;
/* 2341 */       this.fogColorBlue = this.fogColorBlue * (1.0F - var23) + this.fogColorBlue * 0.6F * var23;
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
/* 2366 */     if (this.mc.gameSettings.anaglyph) {
/*      */       
/* 2368 */       float var23 = (this.fogColorRed * 30.0F + this.fogColorGreen * 59.0F + this.fogColorBlue * 11.0F) / 100.0F;
/* 2369 */       float var17 = (this.fogColorRed * 30.0F + this.fogColorGreen * 70.0F) / 100.0F;
/* 2370 */       float var18 = (this.fogColorRed * 30.0F + this.fogColorBlue * 70.0F) / 100.0F;
/* 2371 */       this.fogColorRed = var23;
/* 2372 */       this.fogColorGreen = var17;
/* 2373 */       this.fogColorBlue = var18;
/*      */     } 
/*      */     
/* 2376 */     GL11.glClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean doesFogPostFieldRequireRegeneration(EntityLivingBase viewer) {
/* 2381 */     return (fog_post_list == null || last_fog_post_field_generation_viewer_world != viewer.worldObj || last_fog_post_field_generation_viewer_chunk_x != viewer.getChunkPosX() || last_fog_post_field_generation_viewer_chunk_z != viewer.getChunkPosZ());
/*      */   }
/*      */ 
/*      */   
/*      */   private static void generateFogPostFieldIfRequired(EntityLivingBase viewer) {
/* 2386 */     if (!doesFogPostFieldRequireRegeneration(viewer)) {
/*      */       return;
/*      */     }
/* 2389 */     last_fog_post_field_generation_viewer_world = viewer.worldObj;
/* 2390 */     last_fog_post_field_generation_viewer_chunk_x = viewer.getChunkPosX();
/* 2391 */     last_fog_post_field_generation_viewer_chunk_z = viewer.getChunkPosZ();
/*      */     
/* 2393 */     if (fog_post_list == null) {
/* 2394 */       fog_post_list = new ArrayList();
/*      */     } else {
/* 2396 */       fog_post_list.clear();
/*      */     } 
/* 2398 */     Random random = new Random();
/*      */     
/* 2400 */     random.setSeed(0L);
/*      */     
/* 2402 */     random.nextInt();
/*      */     
/* 2404 */     long hashed_type = random.nextLong();
/*      */     
/* 2406 */     for (int chunk_dx = -65; chunk_dx <= 65; chunk_dx++) {
/*      */       
/* 2408 */       for (int chunk_dz = -65; chunk_dz <= 65; chunk_dz++) {
/*      */         
/* 2410 */         int chunk_x = viewer.getChunkPosX() + chunk_dx;
/* 2411 */         int chunk_z = viewer.getChunkPosZ() + chunk_dz;
/*      */         
/* 2413 */         random.setSeed(Chunk.getChunkCoordsHash(chunk_x, chunk_z) * hashed_type * viewer.worldObj.getHashedSeed());
/*      */         
/* 2415 */         random.nextInt();
/*      */         
/* 2417 */         if (random.nextInt(4828) == 0) {
/* 2418 */           fog_post_list.add(new ChunkCoordIntPair(chunk_x, chunk_z));
/*      */         }
/*      */       } 
/*      */     } 
/* 2422 */     Debug.println("generateFogPostFieldIfRequired: regenerated fog post field");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float getProximityToNearestFogPost(EntityLivingBase viewer) {
/* 2430 */     generateFogPostFieldIfRequired(viewer);
/*      */     
/* 2432 */     float distance_to_nearest_fog_post = 1024.0F;
/*      */     
/* 2434 */     Iterator<ChunkCoordIntPair> i = fog_post_list.iterator();
/*      */     
/* 2436 */     while (i.hasNext()) {
/*      */       
/* 2438 */       ChunkCoordIntPair chunk_coords = i.next();
/*      */       
/* 2440 */       int chunk_x = chunk_coords.chunkXPos;
/* 2441 */       int chunk_z = chunk_coords.chunkZPos;
/*      */       
/* 2443 */       float distance_to_fog_post = World.getDistanceFromDeltas(viewer.posX - (chunk_x * 16), 0.0D, viewer.posZ - (chunk_z * 16));
/*      */       
/* 2445 */       if (distance_to_fog_post < distance_to_nearest_fog_post) {
/* 2446 */         distance_to_nearest_fog_post = distance_to_fog_post;
/*      */       }
/*      */     } 
/* 2449 */     return (distance_to_nearest_fog_post >= 1024.0F) ? 0.0F : (1.0F - distance_to_nearest_fog_post / 1024.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDistanceToNearestBiomeThatCanBeFoggy(double pos_x, double pos_z) {
/* 2454 */     World world = this.mc.theWorld;
/*      */     
/* 2456 */     int x = MathHelper.floor_double(pos_x);
/* 2457 */     int z = MathHelper.floor_double(pos_z);
/*      */     
/* 2459 */     BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
/*      */     
/* 2461 */     if (biome.rainfall > 0.0F && !biome.isFreezing()) {
/*      */       
/* 2463 */       distance_from_biome_that_can_be_foggy_last_viewer_world = world;
/* 2464 */       return Math.max(World.getDistanceFromDeltas(x + 0.5D - pos_x, z + 0.5D - pos_z) - 0.7071D, 0.0D);
/*      */     } 
/*      */     
/* 2467 */     boolean foggy_biome_found = false;
/* 2468 */     double distance_to_nearest_raining_coord_sq = 0.0D;
/*      */     
/* 2470 */     int falloff_distance = 32;
/*      */     
/* 2472 */     int index = -1;
/*      */     
/* 2474 */     if (distance_from_biome_that_can_be_foggy_last_viewer_world == world && distance_from_biome_that_can_be_foggy_last_viewer_x == x && distance_from_biome_that_can_be_foggy_last_viewer_z == z) {
/*      */       
/* 2476 */       if (!is_fog_supporting_biome_contains_at_least_one) {
/* 2477 */         return 10000.0D;
/*      */       }
/* 2479 */       for (int i = -falloff_distance; i <= falloff_distance; i++) {
/*      */         
/* 2481 */         for (int dz = -falloff_distance; dz <= falloff_distance; dz++) {
/*      */           
/* 2483 */           index++;
/*      */           
/* 2485 */           if (is_fog_supporting_biome[index]) {
/*      */ 
/*      */             
/* 2488 */             double dxd = ((x + i) + 0.5F) - pos_x;
/* 2489 */             double dzd = ((z + dz) + 0.5F) - pos_z;
/*      */             
/* 2491 */             double distance_sq = dxd * dxd + dzd * dzd;
/*      */             
/* 2493 */             if (!foggy_biome_found || distance_sq < distance_to_nearest_raining_coord_sq) {
/*      */               
/* 2495 */               foggy_biome_found = true;
/* 2496 */               distance_to_nearest_raining_coord_sq = distance_sq;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2501 */       return foggy_biome_found ? Math.max(MathHelper.sqrt_double(distance_to_nearest_raining_coord_sq) - 0.7071D, 0.0D) : 10000.0D;
/*      */     } 
/*      */     
/* 2504 */     distance_from_biome_that_can_be_foggy_last_viewer_x = x;
/* 2505 */     distance_from_biome_that_can_be_foggy_last_viewer_z = z;
/* 2506 */     distance_from_biome_that_can_be_foggy_last_viewer_world = world;
/*      */     
/* 2508 */     for (int dx = -falloff_distance; dx <= falloff_distance; dx++) {
/*      */       
/* 2510 */       for (int dz = -falloff_distance; dz <= falloff_distance; dz++) {
/*      */         
/* 2512 */         index++;
/*      */         
/* 2514 */         if (!world.chunkExistsAndIsNotEmptyFromBlockCoords(x + dx, z + dz)) {
/*      */           
/* 2516 */           is_fog_supporting_biome[index] = false;
/*      */         }
/*      */         else {
/*      */           
/* 2520 */           biome = world.getBiomeGenForCoords(x + dx, z + dz);
/*      */           
/* 2522 */           if (biome.rainfall > 0.0F && !biome.isFreezing()) {
/*      */             
/* 2524 */             double dxd = ((x + dx) + 0.5F) - pos_x;
/* 2525 */             double dzd = ((z + dz) + 0.5F) - pos_z;
/*      */             
/* 2527 */             double distance_sq = dxd * dxd + dzd * dzd;
/*      */             
/* 2529 */             if (!foggy_biome_found || distance_sq < distance_to_nearest_raining_coord_sq) {
/*      */               
/* 2531 */               foggy_biome_found = true;
/* 2532 */               distance_to_nearest_raining_coord_sq = distance_sq;
/*      */             } 
/*      */             
/* 2535 */             is_fog_supporting_biome[index] = true;
/*      */           }
/*      */           else {
/*      */             
/* 2539 */             is_fog_supporting_biome[index] = false;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2544 */     is_fog_supporting_biome_contains_at_least_one = foggy_biome_found;
/*      */     
/* 2546 */     return foggy_biome_found ? Math.max(MathHelper.sqrt_double(distance_to_nearest_raining_coord_sq) - 0.7071D, 0.0D) : 10000.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setupFog(int par1, float par2) {
/* 2555 */     EntityLivingBase var3 = this.mc.renderViewEntity;
/* 2556 */     boolean var4 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2566 */     if (disable_fog) {
/* 2567 */       var4 = true;
/*      */     }
/* 2569 */     if (par1 == 999) {
/*      */       
/* 2571 */       GL11.glFog(2918, setFogColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 2572 */       GL11.glFogi(2917, 9729);
/* 2573 */       GL11.glFogf(2915, 0.0F);
/* 2574 */       GL11.glFogf(2916, 8.0F);
/*      */ 
/*      */       
/* 2577 */       if (capability_gl_nv_fog_distance)
/*      */       {
/* 2579 */         GL11.glFogi(34138, 34139);
/*      */       }
/*      */       
/* 2582 */       GL11.glFogf(2915, 0.0F);
/*      */     }
/*      */     else {
/*      */       
/* 2586 */       GL11.glFog(2918, setFogColorBuffer(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 1.0F));
/* 2587 */       GL11.glNormal3f(0.0F, -1.0F, 0.0F);
/* 2588 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 2589 */       int var5 = ActiveRenderInfo.getBlockIdAtEntityViewpoint(this.mc.theWorld, var3, par2);
/*      */ 
/*      */       
/* 2592 */       if (var3.isPotionActive(Potion.blindness)) {
/*      */         
/* 2594 */         float var6 = 5.0F;
/* 2595 */         int var7 = var3.getActivePotionEffect(Potion.blindness).getDuration();
/*      */         
/* 2597 */         if (var7 < 20)
/*      */         {
/* 2599 */           var6 = 5.0F + (this.farPlaneDistance - 5.0F) * (1.0F - var7 / 20.0F);
/*      */         }
/*      */         
/* 2602 */         GL11.glFogi(2917, 9729);
/*      */         
/* 2604 */         if (par1 < 0) {
/*      */           
/* 2606 */           GL11.glFogf(2915, 0.0F);
/* 2607 */           GL11.glFogf(2916, var6 * 0.8F);
/*      */         }
/*      */         else {
/*      */           
/* 2611 */           GL11.glFogf(2915, var6 * 0.25F);
/* 2612 */           GL11.glFogf(2916, var6);
/*      */         } 
/*      */ 
/*      */         
/* 2616 */         if (capability_gl_nv_fog_distance)
/*      */         {
/* 2618 */           GL11.glFogi(34138, 34139);
/*      */         }
/*      */       }
/* 2621 */       else if (this.cloudFog) {
/*      */         
/* 2623 */         GL11.glFogi(2917, 2048);
/* 2624 */         GL11.glFogf(2914, 0.1F);
/*      */       }
/* 2626 */       else if (var5 > 0 && (Block.blocksList[var5]).blockMaterial == Material.water) {
/*      */         
/* 2628 */         GL11.glFogi(2917, 2048);
/*      */         
/* 2630 */         if (var3.isPotionActive(Potion.waterBreathing))
/*      */         {
/* 2632 */           GL11.glFogf(2914, 0.05F);
/*      */         }
/*      */         else
/*      */         {
/* 2636 */           GL11.glFogf(2914, 0.1F - EnchantmentHelper.getRespiration(var3) * 0.03F);
/*      */         }
/*      */       
/* 2639 */       } else if (var5 > 0 && (Block.blocksList[var5]).blockMaterial == Material.lava) {
/*      */         
/* 2641 */         GL11.glFogi(2917, 2048);
/* 2642 */         GL11.glFogf(2914, 2.0F);
/*      */       }
/*      */       else {
/*      */         
/* 2646 */         float var6 = this.farPlaneDistance;
/*      */         
/* 2648 */         if (!var4)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 2653 */           if (this.mc.theWorld.provider.getWorldHasVoidFog()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2659 */             Vec3 eye_pos = var3.getEyePos();
/*      */             
/* 2661 */             int skylight_brightness_at_eye_pos = this.mc.theWorld.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, eye_pos.getBlockX(), eye_pos.getBlockY(), eye_pos.getBlockZ());
/*      */             
/* 2663 */             int effective_fps = (Minecraft.last_fps < 1) ? 1 : Minecraft.last_fps;
/*      */             
/* 2665 */             if (this.skylight_brightness_used_for_fog < skylight_brightness_at_eye_pos) {
/*      */               
/* 2667 */               float delta = skylight_brightness_at_eye_pos - this.skylight_brightness_used_for_fog;
/* 2668 */               float max_change = (delta < 12.0F || eye_pos.getBlockY() < 0) ? (0.6F / effective_fps) : delta;
/*      */               
/* 2670 */               this.skylight_brightness_used_for_fog += Math.min(delta, max_change);
/*      */             }
/* 2672 */             else if (this.skylight_brightness_used_for_fog > skylight_brightness_at_eye_pos) {
/*      */               
/* 2674 */               float delta = this.skylight_brightness_used_for_fog - skylight_brightness_at_eye_pos;
/* 2675 */               float max_change = (delta < 12.0F || eye_pos.getBlockY() < 0) ? (0.6F / effective_fps) : delta;
/*      */               
/* 2677 */               this.skylight_brightness_used_for_fog -= Math.min(delta, max_change);
/*      */             } 
/*      */ 
/*      */             
/* 2681 */             double interpolated_pos_y = MathHelper.getInterpolatedValue(var3.lastTickPosY, var3.posY, par2);
/* 2682 */             double var10 = this.skylight_brightness_used_for_fog / 16.0D + (interpolated_pos_y + 4.0D) / 20.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2689 */             if (var10 < 0.0D)
/*      */             {
/* 2691 */               var10 = 0.0D;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 2696 */             double power = 1.0D + (16.0D - interpolated_pos_y) / 2.0D;
/*      */             
/* 2698 */             if (power > 1.0D) {
/* 2699 */               var10 = Math.pow(var10, power);
/*      */             }
/* 2701 */             float var9 = 100.0F * (float)var10;
/*      */             
/* 2703 */             if (var9 < 5.0F)
/*      */             {
/* 2705 */               var9 = 5.0F;
/*      */             }
/*      */             
/* 2708 */             if (var6 > var9)
/*      */             {
/* 2710 */               var6 = var9;
/*      */             }
/*      */ 
/*      */             
/* 2714 */             if (var6 < this.farPlaneDistance)
/*      */             {
/*      */               
/* 2717 */               var6 = (float)(var6 / Math.max(Math.sqrt(getProximityToNearestFogPost(var3)), 0.009999999776482582D));
/*      */             }
/* 2719 */             if (var6 < 24.0F) {
/* 2720 */               var6 = 24.0F;
/*      */             }
/* 2722 */             if (var6 > 96.0F && this.mc.theWorld.isOverworld()) {
/*      */               
/* 2724 */               long shifted_total_world_time = this.mc.theWorld.getTotalWorldTime() - 12000L;
/* 2725 */               int shifted_day_of_world = World.getDayOfWorld(shifted_total_world_time);
/*      */               
/* 2727 */               if (shifted_day_of_world > 7) {
/*      */                 
/* 2729 */                 random_for_fog_events.setSeed((shifted_day_of_world * 365024131) * this.mc.theWorld.getWorldCreationTime() * 672784657L);
/*      */                 
/* 2731 */                 random_for_fog_events.nextInt();
/*      */                 
/* 2733 */                 if (random_for_fog_events.nextInt(7) == 0) {
/*      */ 
/*      */                   
/* 2736 */                   float fog_max_strength = 96.0F + random_for_fog_events.nextFloat() * (var6 - 96.0F) * 0.75F;
/*      */                   
/* 2738 */                   long ticks_from_midnight = this.mc.theWorld.getAdjustedTimeOfDay();
/*      */                   
/* 2740 */                   boolean is_dusk = false;
/*      */                   
/* 2742 */                   if (ticks_from_midnight > 12000L) {
/*      */                     
/* 2744 */                     ticks_from_midnight = 24000L - ticks_from_midnight;
/* 2745 */                     is_dusk = true;
/*      */                   } 
/*      */                   
/* 2748 */                   float day_cycle_factor = MathHelper.clamp_float((float)(8000L - ticks_from_midnight) / (is_dusk ? 4000.0F : 2000.0F), 0.0F, 1.0F);
/*      */ 
/*      */ 
/*      */                   
/* 2752 */                   if (day_cycle_factor > 0.0F) {
/*      */                     
/* 2754 */                     if (distance_from_biome_that_can_be_foggy_last_viewer_world != this.mc.theWorld || distance_from_biome_that_can_be_foggy_tick != this.mc.theWorld.getTotalWorldTime()) {
/*      */                       
/* 2756 */                       boolean player_moved = (distance_from_biome_that_can_be_foggy_last_viewer_world != this.mc.theWorld || distance_from_biome_that_can_be_foggy_last_viewer_pos_x != var3.posX || distance_from_biome_that_can_be_foggy_last_viewer_pos_z != var3.posZ);
/*      */                       
/* 2758 */                       if (player_moved) {
/*      */                         
/* 2760 */                         distance_from_biome_that_can_be_foggy = getDistanceToNearestBiomeThatCanBeFoggy(var3.posX, var3.posZ);
/*      */                         
/* 2762 */                         distance_from_biome_that_can_be_foggy_last_viewer_pos_x = var3.posX;
/* 2763 */                         distance_from_biome_that_can_be_foggy_last_viewer_pos_z = var3.posZ;
/*      */                       } 
/*      */                       
/* 2766 */                       distance_from_biome_that_can_be_foggy_tick = this.mc.theWorld.getTotalWorldTime();
/*      */                     } 
/*      */                     
/* 2769 */                     float distance_from_biome_that_can_be_foggy_factor = Math.max(1.0F - (float)(distance_from_biome_that_can_be_foggy / 32.0D), 0.0F);
/*      */ 
/*      */ 
/*      */                     
/* 2773 */                     float final_factor = day_cycle_factor * distance_from_biome_that_can_be_foggy_factor;
/*      */                     
/* 2775 */                     if (final_factor > 0.0F) {
/*      */                       
/* 2777 */                       float fog_strength = fog_max_strength * final_factor + var6 * (1.0F - final_factor);
/*      */                       
/* 2779 */                       if (var6 > fog_strength) {
/* 2780 */                         var6 = fog_strength;
/*      */                       }
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/* 2787 */             if (var6 > this.farPlaneDistance) {
/* 2788 */               var6 = this.farPlaneDistance;
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/* 2795 */           else if (this.mc.theWorld.isUnderworld()) {
/* 2796 */             var6 = 128.0F;
/*      */           } 
/*      */         }
/* 2799 */         GL11.glFogi(2917, 9729);
/*      */         
/* 2801 */         if (par1 < 0) {
/*      */           
/* 2803 */           GL11.glFogf(2915, 0.0F);
/* 2804 */           GL11.glFogf(2916, var6 * 0.8F);
/*      */         }
/*      */         else {
/*      */           
/* 2808 */           GL11.glFogf(2915, var6 * 0.25F);
/* 2809 */           GL11.glFogf(2916, var6);
/*      */         } 
/*      */ 
/*      */         
/* 2813 */         if (capability_gl_nv_fog_distance)
/*      */         {
/* 2815 */           GL11.glFogi(34138, 34139);
/*      */         }
/*      */ 
/*      */         
/* 2819 */         if (this.mc.theWorld.provider.doesXZShowFog(var3.getBlockPosX(), var3.getEyeBlockPosY(), var3.getBlockPosZ())) {
/*      */           
/* 2821 */           GL11.glFogf(2915, var6 * 0.05F);
/* 2822 */           GL11.glFogf(2916, Math.min(var6, 192.0F) * 0.5F);
/*      */         } 
/*      */       } 
/*      */       
/* 2826 */       GL11.glEnable(2903);
/* 2827 */       GL11.glColorMaterial(1028, 4608);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private FloatBuffer setFogColorBuffer(float par1, float par2, float par3, float par4) {
/* 2836 */     this.fogColorBuffer.clear();
/* 2837 */     this.fogColorBuffer.put(par1).put(par2).put(par3).put(par4);
/* 2838 */     this.fogColorBuffer.flip();
/* 2839 */     return this.fogColorBuffer;
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
/*      */   public static int performanceToFps(int par0) {
/* 2864 */     if (par0 == 3) {
/* 2865 */       return 120;
/*      */     }
/* 2867 */     return (par0 == 1) ? 120 : ((par0 == 2) ? 35 : 200);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Minecraft getRendererMinecraft(EntityRenderer par0EntityRenderer) {
/* 2875 */     return par0EntityRenderer.mc;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */