/*      */ package net.minecraft;
/*      */ 
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import org.lwjgl.opengl.ARBOcclusionQuery;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class RenderGlobal
/*      */   implements IWorldAccess
/*      */ {
/*   22 */   private static final ResourceLocation locationMoonPhasesPng = new ResourceLocation("textures/environment/moon_phases.png");
/*   23 */   private static final ResourceLocation locationSunPng = new ResourceLocation("textures/environment/sun.png");
/*   24 */   private static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
/*   25 */   private static final ResourceLocation locationEndSkyPng = new ResourceLocation("textures/environment/end_sky.png");
/*      */   
/*   27 */   private static final ResourceLocation moon_halo_256 = new ResourceLocation("textures/environment/halo_256.png");
/*   28 */   private static final ResourceLocation moon_ring_128 = new ResourceLocation("textures/environment/ring_128.png");
/*      */   
/*   30 */   public List tileEntities = new ArrayList();
/*      */   
/*      */   private WorldClient theWorld;
/*      */   
/*      */   private final TextureManager renderEngine;
/*   35 */   private List worldRenderersToUpdate = new ArrayList();
/*      */ 
/*      */   
/*      */   private WorldRenderer[] sortedWorldRenderers;
/*      */ 
/*      */   
/*      */   public WorldRenderer[] worldRenderers;
/*      */ 
/*      */   
/*      */   private int renderChunksWide;
/*      */ 
/*      */   
/*      */   private int renderChunksTall;
/*      */ 
/*      */   
/*      */   private int renderChunksDeep;
/*      */ 
/*      */   
/*      */   private int glRenderListBase;
/*      */ 
/*      */   
/*      */   private Minecraft mc;
/*      */ 
/*      */   
/*      */   private RenderBlocks globalRenderBlocks;
/*      */ 
/*      */   
/*      */   private IntBuffer glOcclusionQueryBase;
/*      */ 
/*      */   
/*      */   private boolean occlusionEnabled;
/*      */ 
/*      */   
/*      */   private int cloudTickCounter;
/*      */ 
/*      */   
/*      */   private int starGLCallList;
/*      */ 
/*      */   
/*      */   private int glSkyList;
/*      */ 
/*      */   
/*      */   private int glSkyList2;
/*      */ 
/*      */   
/*      */   private int minBlockX;
/*      */ 
/*      */   
/*      */   private int minBlockY;
/*      */ 
/*      */   
/*      */   private int minBlockZ;
/*      */   
/*      */   private int maxBlockX;
/*      */   
/*      */   private int maxBlockY;
/*      */   
/*      */   private int maxBlockZ;
/*      */   
/*   94 */   private Map damagedBlocks = new HashMap<Object, Object>();
/*      */   private Icon[] destroyBlockIcons;
/*   96 */   private int renderDistance = -1;
/*      */ 
/*      */   
/*   99 */   private int renderEntitiesStartupCounter = 2;
/*      */ 
/*      */   
/*      */   private int countEntitiesTotal;
/*      */ 
/*      */   
/*      */   private int countEntitiesRendered;
/*      */ 
/*      */   
/*      */   private int countEntitiesHidden;
/*      */ 
/*      */   
/*  111 */   IntBuffer occlusionResult = GLAllocation.createDirectIntBuffer(64);
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderersLoaded;
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderersBeingClipped;
/*      */ 
/*      */   
/*      */   private int renderersBeingOccluded;
/*      */ 
/*      */   
/*      */   private int renderersBeingRendered;
/*      */ 
/*      */   
/*      */   private int renderersSkippingRenderPass;
/*      */ 
/*      */   
/*      */   private int dummyRenderInt;
/*      */ 
/*      */   
/*      */   private int worldRenderersCheckIndex;
/*      */ 
/*      */   
/*  137 */   private List glRenderLists = new ArrayList();
/*      */ 
/*      */   
/*  140 */   private RenderList[] allRenderLists = new RenderList[] { new RenderList(), new RenderList(), new RenderList(), new RenderList() };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   double prevSortX = -9999.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   double prevSortY = -9999.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  158 */   double prevSortZ = -9999.0D;
/*      */ 
/*      */ 
/*      */   
/*      */   int frustumCheckOffset;
/*      */ 
/*      */   
/*  165 */   private long time_to_send_next_packet87 = System.currentTimeMillis();
/*      */ 
/*      */ 
/*      */   
/*  169 */   private double[] x = new double[4];
/*  170 */   private double[] y = new double[4];
/*  171 */   private double[] z = new double[4];
/*  172 */   private double[] u = new double[4];
/*  173 */   private double[] v = new double[4];
/*  174 */   private float[] r = new float[4];
/*  175 */   private float[] g = new float[4];
/*  176 */   private float[] b = new float[4];
/*  177 */   private int[] brightness = new int[4]; public double last_cloud_compile_x;
/*      */   public double last_cloud_compile_y;
/*      */   public double last_cloud_compile_z;
/*      */   private boolean last_cloud_compile_has_cloud_tops;
/*      */   private boolean last_cloud_compile_has_cloud_bottoms;
/*  182 */   private int clouds_recompiling_step = 0;
/*  183 */   private int[] clouds_display_list = new int[5];
/*  184 */   private boolean[] clouds_display_list_initialized = new boolean[5];
/*      */   
/*  186 */   public static final int SFX_2001_WAS_SILK_HARVESTED = BitHelper.getBitValue(16);
/*  187 */   public static final int SFX_2001_SUPPRESS_SOUND = BitHelper.getBitValue(17);
/*  188 */   public static final int SFX_2001_WAS_NOT_LEGAL = BitHelper.getBitValue(18);
/*  189 */   public static final int SFX_2001_FOR_AI_BREAK_DOOR = BitHelper.getBitValue(19);
/*  190 */   public static final int SFX_2001_FOR_PARTNER_BLOCK = BitHelper.getBitValue(20);
/*      */ 
/*      */   
/*      */   public boolean include_all_world_renderers_in_next_sorting;
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderGlobal(Minecraft par1Minecraft) {
/*  198 */     this.mc = par1Minecraft;
/*  199 */     this.renderEngine = par1Minecraft.getTextureManager();
/*  200 */     byte var2 = 34;
/*  201 */     byte var3 = 32;
/*  202 */     this.glRenderListBase = GLAllocation.generateDisplayLists(var2 * var2 * var3 * 3);
/*  203 */     this.occlusionEnabled = OpenGlCapsChecker.checkARBOcclusion();
/*      */     
/*  205 */     if (this.occlusionEnabled) {
/*      */       
/*  207 */       this.occlusionResult.clear();
/*  208 */       this.glOcclusionQueryBase = GLAllocation.createDirectIntBuffer(var2 * var2 * var3);
/*  209 */       this.glOcclusionQueryBase.clear();
/*  210 */       this.glOcclusionQueryBase.position(0);
/*  211 */       this.glOcclusionQueryBase.limit(var2 * var2 * var3);
/*  212 */       ARBOcclusionQuery.glGenQueriesARB(this.glOcclusionQueryBase);
/*      */     } 
/*      */     
/*  215 */     this.starGLCallList = GLAllocation.generateDisplayLists(3);
/*  216 */     GL11.glPushMatrix();
/*  217 */     GL11.glNewList(this.starGLCallList, 4864);
/*  218 */     renderStars();
/*  219 */     GL11.glEndList();
/*  220 */     GL11.glPopMatrix();
/*  221 */     Tessellator var4 = Tessellator.instance;
/*  222 */     this.glSkyList = this.starGLCallList + 1;
/*  223 */     GL11.glNewList(this.glSkyList, 4864);
/*  224 */     byte var6 = 64;
/*  225 */     int var7 = 256 / var6 + 2;
/*  226 */     float var5 = 16.0F;
/*      */     
/*      */     int var8;
/*      */     
/*  230 */     for (var8 = -var6 * var7; var8 <= var6 * var7; var8 += var6) {
/*      */       int var9;
/*  232 */       for (var9 = -var6 * var7; var9 <= var6 * var7; var9 += var6) {
/*      */         
/*  234 */         var4.startDrawingQuads();
/*  235 */         var4.addVertex((var8 + 0), var5, (var9 + 0));
/*  236 */         var4.addVertex((var8 + var6), var5, (var9 + 0));
/*  237 */         var4.addVertex((var8 + var6), var5, (var9 + var6));
/*  238 */         var4.addVertex((var8 + 0), var5, (var9 + var6));
/*  239 */         var4.draw();
/*      */       } 
/*      */     } 
/*      */     
/*  243 */     GL11.glEndList();
/*  244 */     this.glSkyList2 = this.starGLCallList + 2;
/*  245 */     GL11.glNewList(this.glSkyList2, 4864);
/*  246 */     var5 = -16.0F;
/*  247 */     var4.startDrawingQuads();
/*      */     
/*  249 */     for (var8 = -var6 * var7; var8 <= var6 * var7; var8 += var6) {
/*      */       int var9;
/*  251 */       for (var9 = -var6 * var7; var9 <= var6 * var7; var9 += var6) {
/*      */         
/*  253 */         var4.addVertex((var8 + var6), var5, (var9 + 0));
/*  254 */         var4.addVertex((var8 + 0), var5, (var9 + 0));
/*  255 */         var4.addVertex((var8 + 0), var5, (var9 + var6));
/*  256 */         var4.addVertex((var8 + var6), var5, (var9 + var6));
/*      */       } 
/*      */     } 
/*      */     
/*  260 */     var4.draw();
/*  261 */     GL11.glEndList();
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderStars() {
/*  266 */     Random var1 = new Random(10842L);
/*  267 */     Tessellator var2 = Tessellator.instance;
/*  268 */     var2.startDrawingQuads();
/*      */     
/*  270 */     for (int var3 = 0; var3 < 1500; var3++) {
/*      */       
/*  272 */       double var4 = (var1.nextFloat() * 2.0F - 1.0F);
/*  273 */       double var6 = (var1.nextFloat() * 2.0F - 1.0F);
/*  274 */       double var8 = (var1.nextFloat() * 2.0F - 1.0F);
/*  275 */       double var10 = (0.15F + var1.nextFloat() * 0.1F);
/*  276 */       double var12 = var4 * var4 + var6 * var6 + var8 * var8;
/*      */       
/*  278 */       if (var12 < 1.0D && var12 > 0.01D) {
/*      */         
/*  280 */         var12 = 1.0D / Math.sqrt(var12);
/*  281 */         var4 *= var12;
/*  282 */         var6 *= var12;
/*  283 */         var8 *= var12;
/*  284 */         double var14 = var4 * 100.0D;
/*  285 */         double var16 = var6 * 100.0D;
/*  286 */         double var18 = var8 * 100.0D;
/*  287 */         double var20 = Math.atan2(var4, var8);
/*  288 */         double var22 = Math.sin(var20);
/*  289 */         double var24 = Math.cos(var20);
/*  290 */         double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
/*  291 */         double var28 = Math.sin(var26);
/*  292 */         double var30 = Math.cos(var26);
/*  293 */         double var32 = var1.nextDouble() * Math.PI * 2.0D;
/*  294 */         double var34 = Math.sin(var32);
/*  295 */         double var36 = Math.cos(var32);
/*      */         
/*  297 */         for (int var38 = 0; var38 < 4; var38++) {
/*      */           
/*  299 */           double var39 = 0.0D;
/*  300 */           double var41 = ((var38 & 0x2) - 1) * var10;
/*  301 */           double var43 = ((var38 + 1 & 0x2) - 1) * var10;
/*  302 */           double var47 = var41 * var36 - var43 * var34;
/*  303 */           double var49 = var43 * var36 + var41 * var34;
/*  304 */           double var53 = var47 * var28 + var39 * var30;
/*  305 */           double var55 = var39 * var28 - var47 * var30;
/*  306 */           double var57 = var55 * var22 - var49 * var24;
/*  307 */           double var61 = var49 * var22 + var55 * var24;
/*  308 */           var2.addVertex(var14 + var57, var16 + var53, var18 + var61);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  313 */     var2.draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorldAndLoadRenderers(WorldClient par1WorldClient) {
/*  321 */     if (this.theWorld != null)
/*      */     {
/*  323 */       this.theWorld.removeWorldAccess(this);
/*      */     }
/*      */     
/*  326 */     this.prevSortX = -9999.0D;
/*  327 */     this.prevSortY = -9999.0D;
/*  328 */     this.prevSortZ = -9999.0D;
/*  329 */     RenderManager.instance.set(par1WorldClient);
/*  330 */     this.theWorld = par1WorldClient;
/*  331 */     this.globalRenderBlocks = new RenderBlocks(par1WorldClient);
/*      */     
/*  333 */     if (par1WorldClient != null) {
/*      */       
/*  335 */       par1WorldClient.addWorldAccess(this);
/*  336 */       loadRenderers();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void markAllRenderersDirty() {
/*  342 */     for (int i = 0; i < this.worldRenderers.length; i++) {
/*  343 */       (this.worldRenderers[i]).needsUpdate = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public void markAllRenderersUninitialized() {
/*  348 */     for (int i = 0; i < this.worldRenderers.length; i++) {
/*      */       
/*  350 */       WorldRenderer world_renderer = this.worldRenderers[i];
/*      */       
/*  352 */       world_renderer.isInitialized = false;
/*  353 */       world_renderer.needsUpdate = true;
/*      */       
/*  355 */       if (!this.worldRenderersToUpdate.contains(world_renderer)) {
/*  356 */         this.worldRenderersToUpdate.add(world_renderer);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldRenderer getWorldRendererFor(int x, int y, int z, Entity entity_for_caching_index) {
/*  363 */     for (int i = 0; i < this.worldRenderers.length; i++) {
/*      */       
/*  365 */       if (this.worldRenderers[i].isRenderingCoords(x, y, z)) {
/*      */         
/*  367 */         if (entity_for_caching_index != null) {
/*  368 */           entity_for_caching_index.index_of_last_applicable_world_renderer = i;
/*      */         }
/*  370 */         return this.worldRenderers[i];
/*      */       } 
/*      */     } 
/*      */     
/*  374 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldRenderer getWorldRendererFor(int x, int y, int z) {
/*  379 */     return getWorldRendererFor(x, y, z, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadRenderers() {
/*  387 */     if (this.theWorld != null) {
/*      */ 
/*      */       
/*  390 */       Block.leaves.setGraphicsLevel(this.mc.gameSettings.isFancyGraphicsEnabled());
/*      */       
/*  392 */       this.renderDistance = this.mc.gameSettings.getRenderDistance();
/*      */ 
/*      */       
/*  395 */       if (this.worldRenderers != null)
/*      */       {
/*  397 */         for (int i = 0; i < this.worldRenderers.length; i++)
/*      */         {
/*  399 */           this.worldRenderers[i].stopRendering();
/*      */         }
/*      */       }
/*      */       
/*  403 */       int var1 = 64 << 3 - this.renderDistance;
/*      */       
/*  405 */       if (var1 > 400)
/*      */       {
/*  407 */         var1 = 400;
/*      */       }
/*      */       
/*  410 */       this.renderChunksWide = var1 / 16 + 1;
/*  411 */       this.renderChunksTall = 16;
/*  412 */       this.renderChunksDeep = var1 / 16 + 1;
/*  413 */       this.worldRenderers = new WorldRenderer[this.renderChunksWide * this.renderChunksTall * this.renderChunksDeep];
/*  414 */       this.sortedWorldRenderers = new WorldRenderer[this.renderChunksWide * this.renderChunksTall * this.renderChunksDeep];
/*  415 */       int var2 = 0;
/*  416 */       int var3 = 0;
/*  417 */       this.minBlockX = 0;
/*  418 */       this.minBlockY = 0;
/*  419 */       this.minBlockZ = 0;
/*  420 */       this.maxBlockX = this.renderChunksWide;
/*  421 */       this.maxBlockY = this.renderChunksTall;
/*  422 */       this.maxBlockZ = this.renderChunksDeep;
/*      */       
/*      */       int var4;
/*  425 */       for (var4 = 0; var4 < this.worldRenderersToUpdate.size(); var4++)
/*      */       {
/*  427 */         ((WorldRenderer)this.worldRenderersToUpdate.get(var4)).needsUpdate = false;
/*      */       }
/*      */       
/*  430 */       this.worldRenderersToUpdate.clear();
/*  431 */       this.tileEntities.clear();
/*      */       
/*  433 */       for (var4 = 0; var4 < this.renderChunksWide; var4++) {
/*      */         
/*  435 */         for (int var5 = 0; var5 < this.renderChunksTall; var5++) {
/*      */           
/*  437 */           for (int var6 = 0; var6 < this.renderChunksDeep; var6++) {
/*      */             
/*  439 */             this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4] = new WorldRenderer(this.theWorld, this.tileEntities, var4 * 16, var5 * 16, var6 * 16, this.glRenderListBase + var2);
/*      */             
/*  441 */             if (this.occlusionEnabled)
/*      */             {
/*  443 */               (this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4]).glOcclusionQuery = this.glOcclusionQueryBase.get(var3);
/*      */             }
/*      */             
/*  446 */             (this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4]).isWaitingOnOcclusionQuery = false;
/*  447 */             (this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4]).isVisible = true;
/*  448 */             (this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4]).isInFrustum = true;
/*  449 */             (this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4]).chunkIndex = var3++;
/*  450 */             this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4].markDirty();
/*  451 */             this.sortedWorldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4] = this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4];
/*  452 */             this.worldRenderersToUpdate.add(this.worldRenderers[(var6 * this.renderChunksTall + var5) * this.renderChunksWide + var4]);
/*  453 */             var2 += 3;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  458 */       if (this.theWorld != null) {
/*      */         
/*  460 */         EntityLivingBase var7 = this.mc.renderViewEntity;
/*      */         
/*  462 */         if (var7 != null) {
/*      */           
/*  464 */           markRenderersForNewPosition(MathHelper.floor_double(var7.posX), MathHelper.floor_double(var7.posY), MathHelper.floor_double(var7.posZ));
/*  465 */           Arrays.sort(this.sortedWorldRenderers, new EntitySorter(var7));
/*      */         } 
/*      */       } 
/*      */       
/*  469 */       this.renderEntitiesStartupCounter = 2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderEntities(Vec3 par1Vec3, ICamera par2ICamera, float par3) {
/*  478 */     if (this.renderEntitiesStartupCounter > 0) {
/*      */       
/*  480 */       this.renderEntitiesStartupCounter--;
/*      */     }
/*      */     else {
/*      */       
/*  484 */       this.theWorld.theProfiler.startSection("prepare");
/*  485 */       TileEntityRenderer.instance.cacheActiveRenderInfo(this.theWorld, this.mc.getTextureManager(), this.mc.fontRenderer, this.mc.renderViewEntity, par3);
/*      */ 
/*      */ 
/*      */       
/*  489 */       RaycastCollision rc = ((EntityPlayer)this.mc.renderViewEntity).getSelectedObject(par3, false);
/*      */       
/*  491 */       EntityLiving pointed_entity_living = (rc == null) ? null : ((rc.getEntityHit() instanceof EntityLiving) ? (EntityLiving)rc.getEntityHit() : null);
/*  492 */       RenderManager.instance.cacheActiveRenderInfo(this.theWorld, this.mc.getTextureManager(), this.mc.fontRenderer, this.mc.renderViewEntity, pointed_entity_living, this.mc.gameSettings, par3);
/*  493 */       this.countEntitiesTotal = 0;
/*  494 */       this.countEntitiesRendered = 0;
/*  495 */       this.countEntitiesHidden = 0;
/*  496 */       EntityLivingBase var4 = this.mc.renderViewEntity;
/*  497 */       RenderManager.renderPosX = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * par3;
/*  498 */       RenderManager.renderPosY = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * par3;
/*  499 */       RenderManager.renderPosZ = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * par3;
/*  500 */       TileEntityRenderer.staticPlayerX = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * par3;
/*  501 */       TileEntityRenderer.staticPlayerY = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * par3;
/*  502 */       TileEntityRenderer.staticPlayerZ = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * par3;
/*  503 */       this.mc.entityRenderer.enableLightmap(par3);
/*  504 */       this.theWorld.theProfiler.endStartSection("global");
/*  505 */       List<Entity> var5 = this.theWorld.getLoadedEntityList();
/*  506 */       this.countEntitiesTotal = var5.size();
/*      */       
/*      */       int var6;
/*      */       
/*  510 */       for (var6 = 0; var6 < this.theWorld.weatherEffects.size(); var6++) {
/*      */         
/*  512 */         Entity var7 = this.theWorld.weatherEffects.get(var6);
/*  513 */         this.countEntitiesRendered++;
/*      */         
/*  515 */         if (var7.isInRangeToRenderVec3D(par1Vec3))
/*      */         {
/*  517 */           RenderManager.instance.renderEntity(var7, par3);
/*      */         }
/*      */       } 
/*      */       
/*  521 */       this.theWorld.theProfiler.endStartSection("entities");
/*      */       
/*  523 */       Packet87SetDespawnCounters packet87 = (System.currentTimeMillis() >= this.time_to_send_next_packet87) ? new Packet87SetDespawnCounters() : null;
/*      */       
/*  525 */       for (var6 = 0; var6 < var5.size(); var6++) {
/*      */         
/*  527 */         Entity var7 = var5.get(var6);
/*      */ 
/*      */ 
/*      */         
/*  531 */         boolean render_override = (var7 != this.mc.thePlayer && var7.getDistanceSqToEntity(this.mc.thePlayer) < 16.0D);
/*      */         
/*  533 */         if (var7 instanceof EntityDragon || var7 instanceof EntityDragonPart) {
/*  534 */           render_override = true;
/*      */         }
/*      */         
/*  537 */         boolean var8 = render_override ? true : ((var7.isInRangeToRenderVec3D(par1Vec3) && (var7.ignoreFrustumCheck || par2ICamera.isBoundingBoxInFrustum(var7.boundingBox) || var7.riddenByEntity == this.mc.thePlayer || (var7 instanceof EntityPlayer && var7 != this.mc.thePlayer))));
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  542 */         if (!var8 && var7 instanceof EntityLiving) {
/*      */           
/*  544 */           EntityLiving var9 = (EntityLiving)var7;
/*      */           
/*  546 */           if (var9.getLeashed() && var9.getLeashedToEntity() != null) {
/*      */             
/*  548 */             Entity var10 = var9.getLeashedToEntity();
/*  549 */             var8 = par2ICamera.isBoundingBoxInFrustum(var10.boundingBox);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  554 */         if (var8 && (var7 != this.mc.renderViewEntity || this.mc.gameSettings.thirdPersonView != 0 || this.mc.renderViewEntity.inBed()) && (this.theWorld.blockExists(MathHelper.floor_double(var7.posX), 0, MathHelper.floor_double(var7.posZ)) || render_override)) {
/*      */           
/*  556 */           this.countEntitiesRendered++;
/*  557 */           RenderManager.instance.renderEntity(var7, par3);
/*      */           
/*  559 */           if (packet87 != null && var7 instanceof EntityLiving)
/*      */           {
/*  561 */             if (var7.getAsEntityLiving().isConsideredInViewOfPlayerForDespawningPurposes(Minecraft.getClientPlayer()))
/*      */             {
/*      */ 
/*      */ 
/*      */               
/*  566 */               packet87.add(var7.entityId, (short)((var7 instanceof EntityWaterMob) ? -9600 : -400));
/*      */             }
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  572 */       if (packet87 != null) {
/*      */         
/*  574 */         this.time_to_send_next_packet87 = System.currentTimeMillis() + 1000L;
/*      */         
/*  576 */         if (packet87.entries > 0) {
/*  577 */           (Minecraft.getClientPlayer()).sendQueue.addToSendQueue(packet87);
/*      */         }
/*      */       } 
/*  580 */       this.theWorld.theProfiler.endStartSection("tileentities");
/*  581 */       RenderHelper.enableStandardItemLighting();
/*      */       
/*  583 */       for (var6 = 0; var6 < this.tileEntities.size(); var6++)
/*      */       {
/*  585 */         TileEntityRenderer.instance.renderTileEntity(this.tileEntities.get(var6), par3);
/*      */       }
/*      */       
/*  588 */       this.mc.entityRenderer.disableLightmap(par3);
/*  589 */       this.theWorld.theProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDebugInfoRenders() {
/*  598 */     return "C: " + this.renderersBeingRendered + "/" + this.renderersLoaded + ". F: " + this.renderersBeingClipped + ", O: " + this.renderersBeingOccluded + ", E: " + this.renderersSkippingRenderPass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDebugInfoEntities() {
/*  606 */     return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ". B: " + this.countEntitiesHidden + ", I: " + (this.countEntitiesTotal - this.countEntitiesHidden - this.countEntitiesRendered);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void markRenderersForNewPosition(int par1, int par2, int par3) {
/*  615 */     par1 -= 8;
/*  616 */     par2 -= 8;
/*  617 */     par3 -= 8;
/*  618 */     this.minBlockX = Integer.MAX_VALUE;
/*  619 */     this.minBlockY = Integer.MAX_VALUE;
/*  620 */     this.minBlockZ = Integer.MAX_VALUE;
/*  621 */     this.maxBlockX = Integer.MIN_VALUE;
/*  622 */     this.maxBlockY = Integer.MIN_VALUE;
/*  623 */     this.maxBlockZ = Integer.MIN_VALUE;
/*  624 */     int var4 = this.renderChunksWide * 16;
/*  625 */     int var5 = var4 / 2;
/*      */     
/*  627 */     for (int var6 = 0; var6 < this.renderChunksWide; var6++) {
/*      */       
/*  629 */       int var7 = var6 * 16;
/*  630 */       int var8 = var7 + var5 - par1;
/*      */       
/*  632 */       if (var8 < 0)
/*      */       {
/*  634 */         var8 -= var4 - 1;
/*      */       }
/*      */       
/*  637 */       var8 /= var4;
/*  638 */       var7 -= var8 * var4;
/*      */       
/*  640 */       if (var7 < this.minBlockX)
/*      */       {
/*  642 */         this.minBlockX = var7;
/*      */       }
/*      */       
/*  645 */       if (var7 > this.maxBlockX)
/*      */       {
/*  647 */         this.maxBlockX = var7;
/*      */       }
/*      */       
/*  650 */       for (int var9 = 0; var9 < this.renderChunksDeep; var9++) {
/*      */         
/*  652 */         int var10 = var9 * 16;
/*  653 */         int var11 = var10 + var5 - par3;
/*      */         
/*  655 */         if (var11 < 0)
/*      */         {
/*  657 */           var11 -= var4 - 1;
/*      */         }
/*      */         
/*  660 */         var11 /= var4;
/*  661 */         var10 -= var11 * var4;
/*      */         
/*  663 */         if (var10 < this.minBlockZ)
/*      */         {
/*  665 */           this.minBlockZ = var10;
/*      */         }
/*      */         
/*  668 */         if (var10 > this.maxBlockZ)
/*      */         {
/*  670 */           this.maxBlockZ = var10;
/*      */         }
/*      */         
/*  673 */         for (int var12 = 0; var12 < this.renderChunksTall; var12++) {
/*      */           
/*  675 */           int var13 = var12 * 16;
/*      */           
/*  677 */           if (var13 < this.minBlockY)
/*      */           {
/*  679 */             this.minBlockY = var13;
/*      */           }
/*      */           
/*  682 */           if (var13 > this.maxBlockY)
/*      */           {
/*  684 */             this.maxBlockY = var13;
/*      */           }
/*      */           
/*  687 */           WorldRenderer var14 = this.worldRenderers[(var9 * this.renderChunksTall + var12) * this.renderChunksWide + var6];
/*  688 */           boolean var15 = var14.needsUpdate;
/*  689 */           var14.setPosition(var7, var13, var10);
/*      */           
/*  691 */           if (!var15 && var14.needsUpdate)
/*      */           {
/*  693 */             this.worldRenderersToUpdate.add(var14);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int sortAndRender(EntityLivingBase par1EntityLivingBase, int par2, double par3) {
/*      */     int var34;
/*  705 */     this.theWorld.theProfiler.startSection("sortchunks");
/*      */ 
/*      */     
/*  708 */     for (int var5 = 0; var5 < (this.include_all_world_renderers_in_next_sorting ? this.worldRenderers.length : 10); var5++) {
/*      */       
/*  710 */       this.worldRenderersCheckIndex = (this.worldRenderersCheckIndex + 1) % this.worldRenderers.length;
/*  711 */       WorldRenderer var6 = this.worldRenderers[this.worldRenderersCheckIndex];
/*      */       
/*  713 */       if (var6.needsUpdate && !this.worldRenderersToUpdate.contains(var6))
/*      */       {
/*  715 */         this.worldRenderersToUpdate.add(var6);
/*      */       }
/*      */     } 
/*      */     
/*  719 */     if (this.include_all_world_renderers_in_next_sorting) {
/*  720 */       this.include_all_world_renderers_in_next_sorting = false;
/*      */     }
/*      */     
/*  723 */     if (this.mc.gameSettings.getRenderDistance() != this.renderDistance)
/*      */     {
/*  725 */       loadRenderers();
/*      */     }
/*      */     
/*  728 */     if (par2 == 0) {
/*      */       
/*  730 */       this.renderersLoaded = 0;
/*  731 */       this.dummyRenderInt = 0;
/*  732 */       this.renderersBeingClipped = 0;
/*  733 */       this.renderersBeingOccluded = 0;
/*  734 */       this.renderersBeingRendered = 0;
/*  735 */       this.renderersSkippingRenderPass = 0;
/*      */     } 
/*      */     
/*  738 */     double var33 = par1EntityLivingBase.lastTickPosX + (par1EntityLivingBase.posX - par1EntityLivingBase.lastTickPosX) * par3;
/*  739 */     double var7 = par1EntityLivingBase.lastTickPosY + (par1EntityLivingBase.posY - par1EntityLivingBase.lastTickPosY) * par3;
/*  740 */     double var9 = par1EntityLivingBase.lastTickPosZ + (par1EntityLivingBase.posZ - par1EntityLivingBase.lastTickPosZ) * par3;
/*  741 */     double var11 = par1EntityLivingBase.posX - this.prevSortX;
/*  742 */     double var13 = par1EntityLivingBase.posY - this.prevSortY;
/*  743 */     double var15 = par1EntityLivingBase.posZ - this.prevSortZ;
/*      */     
/*  745 */     if (var11 * var11 + var13 * var13 + var15 * var15 > 16.0D) {
/*      */       
/*  747 */       this.prevSortX = par1EntityLivingBase.posX;
/*  748 */       this.prevSortY = par1EntityLivingBase.posY;
/*  749 */       this.prevSortZ = par1EntityLivingBase.posZ;
/*  750 */       markRenderersForNewPosition(MathHelper.floor_double(par1EntityLivingBase.posX), MathHelper.floor_double(par1EntityLivingBase.posY), MathHelper.floor_double(par1EntityLivingBase.posZ));
/*  751 */       Arrays.sort(this.sortedWorldRenderers, new EntitySorter(par1EntityLivingBase));
/*      */     } 
/*      */     
/*  754 */     RenderHelper.disableStandardItemLighting();
/*  755 */     byte var17 = 0;
/*      */ 
/*      */     
/*  758 */     if (this.occlusionEnabled && this.mc.gameSettings.advancedOpengl && !this.mc.gameSettings.anaglyph && par2 == 0) {
/*      */       
/*  760 */       byte var18 = 0;
/*  761 */       int var19 = 16;
/*  762 */       checkOcclusionQueryResult(var18, var19);
/*      */       
/*  764 */       for (int var20 = var18; var20 < var19; var20++)
/*      */       {
/*  766 */         (this.sortedWorldRenderers[var20]).isVisible = true;
/*      */       }
/*      */       
/*  769 */       this.theWorld.theProfiler.endStartSection("render");
/*  770 */       var34 = var17 + renderSortedRenderers(var18, var19, par2, par3);
/*      */ 
/*      */       
/*      */       do {
/*  774 */         this.theWorld.theProfiler.endStartSection("occ");
/*  775 */         int var35 = var19;
/*  776 */         var19 *= 2;
/*      */         
/*  778 */         if (var19 > this.sortedWorldRenderers.length)
/*      */         {
/*  780 */           var19 = this.sortedWorldRenderers.length;
/*      */         }
/*      */         
/*  783 */         GL11.glDisable(3553);
/*  784 */         GL11.glDisable(2896);
/*  785 */         GL11.glDisable(3008);
/*  786 */         GL11.glDisable(2912);
/*  787 */         GL11.glColorMask(false, false, false, false);
/*  788 */         GL11.glDepthMask(false);
/*  789 */         this.theWorld.theProfiler.startSection("check");
/*  790 */         checkOcclusionQueryResult(var35, var19);
/*  791 */         this.theWorld.theProfiler.endSection();
/*  792 */         GL11.glPushMatrix();
/*  793 */         float var36 = 0.0F;
/*  794 */         float var21 = 0.0F;
/*  795 */         float var22 = 0.0F;
/*      */         
/*  797 */         for (int var23 = var35; var23 < var19; var23++) {
/*      */           
/*  799 */           if (this.sortedWorldRenderers[var23].skipAllRenderPasses()) {
/*      */             
/*  801 */             (this.sortedWorldRenderers[var23]).isInFrustum = false;
/*      */           }
/*      */           else {
/*      */             
/*  805 */             if (!(this.sortedWorldRenderers[var23]).isInFrustum)
/*      */             {
/*  807 */               (this.sortedWorldRenderers[var23]).isVisible = true;
/*      */             }
/*      */             
/*  810 */             if ((this.sortedWorldRenderers[var23]).isInFrustum && !(this.sortedWorldRenderers[var23]).isWaitingOnOcclusionQuery) {
/*      */               
/*  812 */               float var24 = MathHelper.sqrt_float(this.sortedWorldRenderers[var23].distanceToEntitySquared(par1EntityLivingBase));
/*  813 */               int var25 = (int)(1.0F + var24 / 128.0F);
/*      */               
/*  815 */               if (this.cloudTickCounter % var25 == var23 % var25) {
/*      */                 
/*  817 */                 WorldRenderer var26 = this.sortedWorldRenderers[var23];
/*  818 */                 float var27 = (float)(var26.posXMinus - var33);
/*  819 */                 float var28 = (float)(var26.posYMinus - var7);
/*  820 */                 float var29 = (float)(var26.posZMinus - var9);
/*  821 */                 float var30 = var27 - var36;
/*  822 */                 float var31 = var28 - var21;
/*  823 */                 float var32 = var29 - var22;
/*      */                 
/*  825 */                 if (var30 != 0.0F || var31 != 0.0F || var32 != 0.0F) {
/*      */                   
/*  827 */                   GL11.glTranslatef(var30, var31, var32);
/*  828 */                   var36 += var30;
/*  829 */                   var21 += var31;
/*  830 */                   var22 += var32;
/*      */                 } 
/*      */                 
/*  833 */                 this.theWorld.theProfiler.startSection("bb");
/*  834 */                 ARBOcclusionQuery.glBeginQueryARB(35092, (this.sortedWorldRenderers[var23]).glOcclusionQuery);
/*  835 */                 this.sortedWorldRenderers[var23].callOcclusionQueryList();
/*  836 */                 ARBOcclusionQuery.glEndQueryARB(35092);
/*  837 */                 this.theWorld.theProfiler.endSection();
/*  838 */                 (this.sortedWorldRenderers[var23]).isWaitingOnOcclusionQuery = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  844 */         GL11.glPopMatrix();
/*      */         
/*  846 */         if (this.mc.gameSettings.anaglyph) {
/*      */           
/*  848 */           if (EntityRenderer.anaglyphField == 0)
/*      */           {
/*  850 */             GL11.glColorMask(false, true, true, true);
/*      */           }
/*      */           else
/*      */           {
/*  854 */             GL11.glColorMask(true, false, false, true);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  859 */           GL11.glColorMask(true, true, true, true);
/*      */         } 
/*      */         
/*  862 */         GL11.glDepthMask(true);
/*  863 */         GL11.glEnable(3553);
/*  864 */         GL11.glEnable(3008);
/*  865 */         GL11.glEnable(2912);
/*  866 */         this.theWorld.theProfiler.endStartSection("render");
/*  867 */         var34 += renderSortedRenderers(var35, var19, par2, par3);
/*      */       }
/*  869 */       while (var19 < this.sortedWorldRenderers.length);
/*      */     }
/*      */     else {
/*      */       
/*  873 */       this.theWorld.theProfiler.endStartSection("render");
/*  874 */       var34 = var17 + renderSortedRenderers(0, this.sortedWorldRenderers.length, par2, par3);
/*      */     } 
/*      */     
/*  877 */     this.theWorld.theProfiler.endSection();
/*  878 */     return var34;
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkOcclusionQueryResult(int par1, int par2) {
/*  883 */     for (int var3 = par1; var3 < par2; var3++) {
/*      */       
/*  885 */       if ((this.sortedWorldRenderers[var3]).isWaitingOnOcclusionQuery) {
/*      */         
/*  887 */         this.occlusionResult.clear();
/*  888 */         ARBOcclusionQuery.glGetQueryObjectuARB((this.sortedWorldRenderers[var3]).glOcclusionQuery, 34919, this.occlusionResult);
/*      */         
/*  890 */         if (this.occlusionResult.get(0) != 0) {
/*      */           
/*  892 */           (this.sortedWorldRenderers[var3]).isWaitingOnOcclusionQuery = false;
/*  893 */           this.occlusionResult.clear();
/*  894 */           ARBOcclusionQuery.glGetQueryObjectuARB((this.sortedWorldRenderers[var3]).glOcclusionQuery, 34918, this.occlusionResult);
/*  895 */           (this.sortedWorldRenderers[var3]).isVisible = (this.occlusionResult.get(0) != 0);
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
/*      */   private int renderSortedRenderers(int par1, int par2, int par3, double par4) {
/*  907 */     this.glRenderLists.clear();
/*  908 */     int var6 = 0;
/*      */     
/*  910 */     for (int var7 = par1; var7 < par2; var7++) {
/*      */       
/*  912 */       if (par3 == 0) {
/*      */         
/*  914 */         this.renderersLoaded++;
/*      */         
/*  916 */         if ((this.sortedWorldRenderers[var7]).skipRenderPass[par3]) {
/*      */           
/*  918 */           this.renderersSkippingRenderPass++;
/*      */         }
/*  920 */         else if (!(this.sortedWorldRenderers[var7]).isInFrustum) {
/*      */           
/*  922 */           this.renderersBeingClipped++;
/*      */         }
/*  924 */         else if (this.occlusionEnabled && !(this.sortedWorldRenderers[var7]).isVisible) {
/*      */           
/*  926 */           this.renderersBeingOccluded++;
/*      */         }
/*      */         else {
/*      */           
/*  930 */           this.renderersBeingRendered++;
/*      */         } 
/*      */       } 
/*      */       
/*  934 */       if (!(this.sortedWorldRenderers[var7]).skipRenderPass[par3] && (this.sortedWorldRenderers[var7]).isInFrustum && (!this.occlusionEnabled || (this.sortedWorldRenderers[var7]).isVisible)) {
/*      */         
/*  936 */         int var8 = this.sortedWorldRenderers[var7].getGLCallListForPass(par3);
/*      */         
/*  938 */         if (var8 >= 0) {
/*      */           
/*  940 */           this.glRenderLists.add(this.sortedWorldRenderers[var7]);
/*  941 */           var6++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  946 */     EntityLivingBase var19 = this.mc.renderViewEntity;
/*  947 */     double var20 = var19.lastTickPosX + (var19.posX - var19.lastTickPosX) * par4;
/*  948 */     double var10 = var19.lastTickPosY + (var19.posY - var19.lastTickPosY) * par4;
/*  949 */     double var12 = var19.lastTickPosZ + (var19.posZ - var19.lastTickPosZ) * par4;
/*  950 */     int var14 = 0;
/*      */     
/*      */     int var15;
/*  953 */     for (var15 = 0; var15 < this.allRenderLists.length; var15++)
/*      */     {
/*  955 */       this.allRenderLists[var15].func_78421_b();
/*      */     }
/*      */     
/*  958 */     for (var15 = 0; var15 < this.glRenderLists.size(); var15++) {
/*      */       
/*  960 */       WorldRenderer var16 = this.glRenderLists.get(var15);
/*  961 */       int var17 = -1;
/*      */       
/*  963 */       for (int var18 = 0; var18 < var14; var18++) {
/*      */         
/*  965 */         if (this.allRenderLists[var18].func_78418_a(var16.posXMinus, var16.posYMinus, var16.posZMinus))
/*      */         {
/*  967 */           var17 = var18;
/*      */         }
/*      */       } 
/*      */       
/*  971 */       if (var17 < 0) {
/*      */         
/*  973 */         var17 = var14++;
/*  974 */         this.allRenderLists[var17].func_78422_a(var16.posXMinus, var16.posYMinus, var16.posZMinus, var20, var10, var12);
/*      */       } 
/*      */       
/*  977 */       this.allRenderLists[var17].func_78420_a(var16.getGLCallListForPass(par3));
/*      */     } 
/*      */     
/*  980 */     renderAllRenderLists(par3, par4);
/*  981 */     return var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderAllRenderLists(int par1, double par2) {
/*  989 */     this.mc.entityRenderer.enableLightmap(par2);
/*      */     
/*  991 */     for (int var4 = 0; var4 < this.allRenderLists.length; var4++)
/*      */     {
/*  993 */       this.allRenderLists[var4].func_78419_a();
/*      */     }
/*      */     
/*  996 */     this.mc.entityRenderer.disableLightmap(par2);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateClouds() {
/* 1001 */     this.cloudTickCounter++;
/*      */     
/* 1003 */     if (this.cloudTickCounter % 20 == 0) {
/*      */       
/* 1005 */       Iterator<DestroyBlockProgress> var1 = this.damagedBlocks.values().iterator();
/*      */       
/* 1007 */       while (var1.hasNext()) {
/*      */         
/* 1009 */         DestroyBlockProgress var2 = var1.next();
/* 1010 */         int var3 = var2.getCreationCloudUpdateTick();
/*      */         
/* 1012 */         if (this.cloudTickCounter - var3 > 400)
/*      */         {
/* 1014 */           var1.remove();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderSky(float par1) {
/* 1025 */     GL11.glDisable(2896);
/*      */     
/* 1027 */     if (this.mc.theWorld.provider.dimensionId == 1) {
/*      */       
/* 1029 */       GL11.glDisable(2912);
/* 1030 */       GL11.glDisable(3008);
/* 1031 */       GL11.glEnable(3042);
/* 1032 */       GL11.glBlendFunc(770, 771);
/* 1033 */       RenderHelper.disableStandardItemLighting();
/* 1034 */       GL11.glDepthMask(false);
/* 1035 */       this.renderEngine.bindTexture(locationEndSkyPng);
/* 1036 */       Tessellator var21 = Tessellator.instance;
/*      */       
/* 1038 */       for (int var22 = 0; var22 < 6; var22++) {
/*      */         
/* 1040 */         GL11.glPushMatrix();
/*      */         
/* 1042 */         if (var22 == 1)
/*      */         {
/* 1044 */           GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1047 */         if (var22 == 2)
/*      */         {
/* 1049 */           GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1052 */         if (var22 == 3)
/*      */         {
/* 1054 */           GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1057 */         if (var22 == 4)
/*      */         {
/* 1059 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*      */         }
/*      */         
/* 1062 */         if (var22 == 5)
/*      */         {
/* 1064 */           GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*      */         }
/*      */         
/* 1067 */         var21.startDrawingQuads();
/* 1068 */         var21.setColorOpaque_I(2631720);
/* 1069 */         var21.addVertexWithUV(-100.0D, -100.0D, -100.0D, 0.0D, 0.0D);
/* 1070 */         var21.addVertexWithUV(-100.0D, -100.0D, 100.0D, 0.0D, 16.0D);
/* 1071 */         var21.addVertexWithUV(100.0D, -100.0D, 100.0D, 16.0D, 16.0D);
/* 1072 */         var21.addVertexWithUV(100.0D, -100.0D, -100.0D, 16.0D, 0.0D);
/* 1073 */         var21.draw();
/* 1074 */         GL11.glPopMatrix();
/*      */       } 
/*      */       
/* 1077 */       GL11.glDepthMask(true);
/* 1078 */       GL11.glEnable(3553);
/* 1079 */       GL11.glEnable(3008);
/*      */     }
/* 1081 */     else if (this.mc.theWorld.provider.isSurfaceWorld()) {
/*      */       
/* 1083 */       GL11.glDisable(3553);
/* 1084 */       Vec3 var2 = this.theWorld.getSkyColor(this.mc.renderViewEntity, par1);
/* 1085 */       float var3 = (float)var2.xCoord;
/* 1086 */       float var4 = (float)var2.yCoord;
/* 1087 */       float var5 = (float)var2.zCoord;
/*      */ 
/*      */       
/* 1090 */       if (this.mc.gameSettings.anaglyph) {
/*      */         
/* 1092 */         float var6 = (var3 * 30.0F + var4 * 59.0F + var5 * 11.0F) / 100.0F;
/* 1093 */         float var7 = (var3 * 30.0F + var4 * 70.0F) / 100.0F;
/* 1094 */         float f1 = (var3 * 30.0F + var5 * 70.0F) / 100.0F;
/* 1095 */         var3 = var6;
/* 1096 */         var4 = var7;
/* 1097 */         var5 = f1;
/*      */       } 
/*      */       
/* 1100 */       GL11.glColor3f(var3, var4, var5);
/* 1101 */       Tessellator var23 = Tessellator.instance;
/* 1102 */       GL11.glDepthMask(false);
/* 1103 */       GL11.glEnable(2912);
/* 1104 */       GL11.glColor3f(var3, var4, var5);
/* 1105 */       GL11.glCallList(this.glSkyList);
/* 1106 */       GL11.glDisable(2912);
/* 1107 */       GL11.glDisable(3008);
/* 1108 */       GL11.glEnable(3042);
/* 1109 */       GL11.glBlendFunc(770, 771);
/* 1110 */       RenderHelper.disableStandardItemLighting();
/* 1111 */       float[] var24 = this.theWorld.provider.calcSunriseSunsetColors(this.theWorld.getCelestialAngle(par1), par1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1117 */       if (var24 != null) {
/*      */         
/* 1119 */         GL11.glDisable(3553);
/* 1120 */         GL11.glShadeModel(7425);
/* 1121 */         GL11.glPushMatrix();
/* 1122 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 1123 */         GL11.glRotatef((MathHelper.sin(this.theWorld.getCelestialAngleRadians(par1)) < 0.0F) ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
/* 1124 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 1125 */         float f1 = var24[0];
/* 1126 */         float f2 = var24[1];
/* 1127 */         float f3 = var24[2];
/*      */ 
/*      */         
/* 1130 */         if (this.mc.gameSettings.anaglyph) {
/*      */           
/* 1132 */           float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
/* 1133 */           float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
/* 1134 */           float var13 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
/* 1135 */           f1 = f4;
/* 1136 */           f2 = f5;
/* 1137 */           f3 = var13;
/*      */         } 
/*      */         
/* 1140 */         var23.startDrawing(6);
/* 1141 */         var23.setColorRGBA_F(f1, f2, f3, var24[3]);
/* 1142 */         var23.addVertex(0.0D, 100.0D, 0.0D);
/* 1143 */         byte var26 = 16;
/* 1144 */         var23.setColorRGBA_F(var24[0], var24[1], var24[2], 0.0F);
/*      */         
/* 1146 */         for (int var27 = 0; var27 <= var26; var27++) {
/*      */           
/* 1148 */           float var13 = var27 * 3.1415927F * 2.0F / var26;
/* 1149 */           float var14 = MathHelper.sin(var13);
/* 1150 */           float var15 = MathHelper.cos(var13);
/* 1151 */           var23.addVertex((var14 * 120.0F), (var15 * 120.0F), (-var15 * 40.0F * var24[3]));
/*      */         } 
/*      */         
/* 1154 */         var23.draw();
/* 1155 */         GL11.glPopMatrix();
/* 1156 */         GL11.glShadeModel(7424);
/*      */       } 
/*      */       
/* 1159 */       GL11.glEnable(3553);
/* 1160 */       GL11.glBlendFunc(770, 1);
/* 1161 */       GL11.glPushMatrix();
/* 1162 */       float var8 = 1.0F - this.theWorld.getRainStrength(par1);
/*      */       
/* 1164 */       float var9 = 0.0F;
/* 1165 */       float var10 = 0.0F;
/* 1166 */       float var11 = 0.0F;
/* 1167 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, var8);
/* 1168 */       GL11.glTranslatef(var9, var10, var11);
/* 1169 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 1170 */       GL11.glRotatef(this.theWorld.getCelestialAngle(par1) * 360.0F, 1.0F, 0.0F, 0.0F);
/* 1171 */       float var12 = 30.0F;
/* 1172 */       this.renderEngine.bindTexture(locationSunPng);
/* 1173 */       var23.startDrawingQuads();
/* 1174 */       var23.addVertexWithUV(-var12, 100.0D, -var12, 0.0D, 0.0D);
/* 1175 */       var23.addVertexWithUV(var12, 100.0D, -var12, 1.0D, 0.0D);
/* 1176 */       var23.addVertexWithUV(var12, 100.0D, var12, 1.0D, 1.0D);
/* 1177 */       var23.addVertexWithUV(-var12, 100.0D, var12, 0.0D, 1.0D);
/* 1178 */       var23.draw();
/*      */       
/* 1180 */       if (this.theWorld.isBloodMoon(false)) {
/*      */ 
/*      */ 
/*      */         
/* 1184 */         GL11.glColor4f(0.6F, 0.2F, 0.1F, var8);
/*      */       }
/* 1186 */       else if (this.theWorld.isHarvestMoon24HourPeriod()) {
/*      */         
/* 1188 */         GL11.glColor4f(1.0F, 0.8F, 0.45F, var8);
/*      */       }
/* 1190 */       else if (this.theWorld.isBlueMoon(false)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1196 */         GL11.glColor4f(0.66F, 0.74F, 1.0F, var8);
/*      */       }
/*      */       else {
/*      */         
/* 1200 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, var8);
/*      */       } 
/*      */       
/* 1203 */       var12 = 20.0F;
/* 1204 */       this.renderEngine.bindTexture(locationMoonPhasesPng);
/* 1205 */       int var28 = this.theWorld.getMoonPhase();
/* 1206 */       int var30 = var28 % 4;
/* 1207 */       int var29 = var28 / 4 % 2;
/* 1208 */       float var16 = (var30 + 0) / 4.0F;
/* 1209 */       float var17 = (var29 + 0) / 2.0F;
/* 1210 */       float var18 = (var30 + 1) / 4.0F;
/* 1211 */       float var19 = (var29 + 1) / 2.0F;
/* 1212 */       var23.startDrawingQuads();
/* 1213 */       var23.addVertexWithUV(-var12, -100.0D, var12, var18, var19);
/* 1214 */       var23.addVertexWithUV(var12, -100.0D, var12, var16, var19);
/* 1215 */       var23.addVertexWithUV(var12, -100.0D, -var12, var16, var17);
/* 1216 */       var23.addVertexWithUV(-var12, -100.0D, -var12, var18, var17);
/* 1217 */       var23.draw();
/*      */       
/* 1219 */       if (this.theWorld.isBloodMoon24HourPeriod() || this.theWorld.isBlueMoon24HourPeriod()) {
/*      */         
/* 1221 */         float alpha_factor = 0.25F;
/* 1222 */         float celestial_angle = this.theWorld.getCelestialAngle(par1);
/*      */         
/* 1224 */         float start_fading_at = 0.77F;
/*      */         
/* 1226 */         if (celestial_angle > start_fading_at) {
/* 1227 */           alpha_factor *= 1.0F - (celestial_angle - start_fading_at) / (0.785F - start_fading_at);
/*      */         }
/* 1229 */         if (this.theWorld.isBloodMoon24HourPeriod()) {
/* 1230 */           GL11.glColor4f(0.6F, 0.2F, 0.1F, var8 * alpha_factor);
/*      */         } else {
/* 1232 */           GL11.glColor4f(0.33F, 0.37F, 0.8F, var8 * alpha_factor);
/*      */         } 
/* 1234 */         this.renderEngine.bindTexture(moon_halo_256);
/*      */         
/* 1236 */         float halo_size = 160.0F;
/*      */         
/* 1238 */         var23.startDrawingQuads();
/* 1239 */         var23.addVertexWithUV(-halo_size, -100.0D, halo_size, 0.0D, 0.0D);
/* 1240 */         var23.addVertexWithUV(halo_size, -100.0D, halo_size, 1.0D, 0.0D);
/* 1241 */         var23.addVertexWithUV(halo_size, -100.0D, -halo_size, 1.0D, 1.0D);
/* 1242 */         var23.addVertexWithUV(-halo_size, -100.0D, -halo_size, 0.0D, 1.0D);
/* 1243 */         var23.draw();
/*      */       }
/* 1245 */       else if (this.theWorld.isMoonDog24HourPeriod()) {
/*      */         
/* 1247 */         float alpha_factor = (1.0F - this.theWorld.getRainStrength(par1)) * 0.5F;
/*      */         
/* 1249 */         float start_fading_in_at = 0.18F;
/* 1250 */         float stop_fading_in_at = 0.28F;
/*      */         
/* 1252 */         float start_fading_out_at = 0.6F;
/* 1253 */         float stop_fading_out_at = 0.785F;
/*      */         
/* 1255 */         float celestial_angle = this.theWorld.getCelestialAngle(par1);
/*      */         
/* 1257 */         if (celestial_angle < start_fading_in_at || celestial_angle > stop_fading_out_at) {
/* 1258 */           alpha_factor = 0.0F;
/* 1259 */         } else if (celestial_angle < stop_fading_in_at) {
/* 1260 */           alpha_factor *= (celestial_angle - start_fading_in_at) / (stop_fading_in_at - start_fading_in_at);
/* 1261 */         } else if (celestial_angle > start_fading_out_at) {
/* 1262 */           alpha_factor *= 1.0F - (celestial_angle - start_fading_out_at) / (stop_fading_out_at - start_fading_out_at);
/*      */         } 
/*      */ 
/*      */         
/* 1266 */         if (alpha_factor > 0.0F) {
/*      */           
/* 1268 */           GL11.glColor4f(0.4975F, 0.6275F, 0.85F, alpha_factor);
/*      */           
/* 1270 */           this.renderEngine.bindTexture(moon_ring_128);
/*      */           
/* 1272 */           float ring_size = 80.0F;
/*      */           
/* 1274 */           var23.startDrawingQuads();
/* 1275 */           var23.addVertexWithUV(-ring_size, -100.0D, ring_size, 0.0D, 0.0D);
/* 1276 */           var23.addVertexWithUV(ring_size, -100.0D, ring_size, 1.0D, 0.0D);
/* 1277 */           var23.addVertexWithUV(ring_size, -100.0D, -ring_size, 1.0D, 1.0D);
/* 1278 */           var23.addVertexWithUV(-ring_size, -100.0D, -ring_size, 0.0D, 1.0D);
/* 1279 */           var23.draw();
/*      */         } 
/*      */       } 
/*      */       
/* 1283 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, var8);
/*      */       
/* 1285 */       GL11.glDisable(3553);
/* 1286 */       float var20 = this.theWorld.getStarBrightness(par1) * var8;
/*      */       
/* 1288 */       if (var20 > 0.0F) {
/*      */         
/* 1290 */         GL11.glColor4f(var20, var20, var20, var20);
/* 1291 */         GL11.glCallList(this.starGLCallList);
/*      */       } 
/*      */       
/* 1294 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1295 */       GL11.glDisable(3042);
/* 1296 */       GL11.glEnable(3008);
/* 1297 */       GL11.glEnable(2912);
/* 1298 */       GL11.glPopMatrix();
/* 1299 */       GL11.glDisable(3553);
/* 1300 */       GL11.glColor3f(0.0F, 0.0F, 0.0F);
/* 1301 */       double var25 = (this.mc.thePlayer.getPosition(par1)).yCoord - this.theWorld.getHorizon();
/*      */       
/* 1303 */       if (var25 < 0.0D) {
/*      */         
/* 1305 */         GL11.glPushMatrix();
/* 1306 */         GL11.glTranslatef(0.0F, 12.0F, 0.0F);
/* 1307 */         GL11.glCallList(this.glSkyList2);
/* 1308 */         GL11.glPopMatrix();
/* 1309 */         var10 = 1.0F;
/* 1310 */         var11 = -((float)(var25 + 65.0D));
/* 1311 */         var12 = -var10;
/* 1312 */         var23.startDrawingQuads();
/* 1313 */         var23.setColorRGBA_I(0, 255);
/* 1314 */         var23.addVertex(-var10, var11, var10);
/* 1315 */         var23.addVertex(var10, var11, var10);
/* 1316 */         var23.addVertex(var10, var12, var10);
/* 1317 */         var23.addVertex(-var10, var12, var10);
/* 1318 */         var23.addVertex(-var10, var12, -var10);
/* 1319 */         var23.addVertex(var10, var12, -var10);
/* 1320 */         var23.addVertex(var10, var11, -var10);
/* 1321 */         var23.addVertex(-var10, var11, -var10);
/* 1322 */         var23.addVertex(var10, var12, -var10);
/* 1323 */         var23.addVertex(var10, var12, var10);
/* 1324 */         var23.addVertex(var10, var11, var10);
/* 1325 */         var23.addVertex(var10, var11, -var10);
/* 1326 */         var23.addVertex(-var10, var11, -var10);
/* 1327 */         var23.addVertex(-var10, var11, var10);
/* 1328 */         var23.addVertex(-var10, var12, var10);
/* 1329 */         var23.addVertex(-var10, var12, -var10);
/* 1330 */         var23.addVertex(-var10, var12, -var10);
/* 1331 */         var23.addVertex(-var10, var12, var10);
/* 1332 */         var23.addVertex(var10, var12, var10);
/* 1333 */         var23.addVertex(var10, var12, -var10);
/* 1334 */         var23.draw();
/*      */       } 
/*      */       
/* 1337 */       if (this.theWorld.provider.isSkyColored()) {
/*      */         
/* 1339 */         GL11.glColor3f(var3 * 0.2F + 0.04F, var4 * 0.2F + 0.04F, var5 * 0.6F + 0.1F);
/*      */       }
/*      */       else {
/*      */         
/* 1343 */         GL11.glColor3f(var3, var4, var5);
/*      */       } 
/*      */       
/* 1346 */       GL11.glPushMatrix();
/* 1347 */       GL11.glTranslatef(0.0F, -((float)(var25 - 16.0D)), 0.0F);
/* 1348 */       GL11.glCallList(this.glSkyList2);
/* 1349 */       GL11.glPopMatrix();
/* 1350 */       GL11.glEnable(3553);
/* 1351 */       GL11.glDepthMask(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void compileCloudsFancy(float par1, double var8, double var2, double var10) {
/* 1357 */     GL11.glEnable(2884);
/* 1358 */     GL11.glDisable(2912);
/* 1359 */     GL11.glDisable(3008);
/* 1360 */     GL11.glDisable(3553);
/*      */     
/* 1362 */     boolean player_can_see_cloud_tops = canViewerSeeCloudTopsFancy(var2);
/* 1363 */     boolean player_can_see_cloud_bottoms = canViewerSeeCloudBottomsFancy(var2);
/*      */     
/* 1365 */     this.last_cloud_compile_x = var8;
/* 1366 */     this.last_cloud_compile_y = var2;
/* 1367 */     this.last_cloud_compile_z = var10;
/*      */     
/* 1369 */     this.last_cloud_compile_has_cloud_tops = player_can_see_cloud_tops;
/* 1370 */     this.last_cloud_compile_has_cloud_bottoms = player_can_see_cloud_bottoms;
/*      */     
/* 1372 */     for (this.clouds_recompiling_step = 0; this.clouds_recompiling_step < this.clouds_display_list.length; this.clouds_recompiling_step++) {
/*      */       
/* 1374 */       if (this.clouds_recompiling_step != 1 || player_can_see_cloud_tops)
/*      */       {
/* 1376 */         if (this.clouds_recompiling_step != 2 || player_can_see_cloud_bottoms) {
/*      */ 
/*      */           
/* 1379 */           if (this.clouds_display_list_initialized[this.clouds_recompiling_step]) {
/* 1380 */             GL11.glDeleteLists(this.clouds_display_list[this.clouds_recompiling_step], 1);
/*      */           }
/* 1382 */           this.clouds_display_list[this.clouds_recompiling_step] = GL11.glGenLists(1);
/* 1383 */           GL11.glNewList(this.clouds_display_list[this.clouds_recompiling_step], 4864);
/* 1384 */           renderCloudSide(par1, this.clouds_recompiling_step - 1, this.last_cloud_compile_x, this.last_cloud_compile_z, player_can_see_cloud_tops, player_can_see_cloud_bottoms);
/* 1385 */           GL11.glEndList();
/*      */           
/* 1387 */           this.clouds_display_list_initialized[this.clouds_recompiling_step] = true;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1392 */     GL11.glEnable(2912);
/* 1393 */     GL11.glEnable(3008);
/* 1394 */     GL11.glEnable(3553);
/*      */   }
/*      */ 
/*      */   
/*      */   public static double[] getViewerPos(float par1, float horizontal_scaling) {
/* 1399 */     Minecraft mc = Minecraft.theMinecraft;
/* 1400 */     int cloud_tick_counter = mc.renderGlobal.cloudTickCounter;
/*      */     
/* 1402 */     double x = (mc.renderViewEntity.prevPosX + (mc.renderViewEntity.posX - mc.renderViewEntity.prevPosX) * par1 + (cloud_tick_counter + par1) * 0.03D) / horizontal_scaling;
/* 1403 */     double y = mc.renderViewEntity.lastTickPosY + (mc.renderViewEntity.posY - mc.renderViewEntity.lastTickPosY) * par1;
/* 1404 */     double z = (mc.renderViewEntity.prevPosZ + (mc.renderViewEntity.posZ - mc.renderViewEntity.prevPosZ) * par1) / horizontal_scaling + 0.33D;
/*      */     
/* 1406 */     return new double[] { x, y, z };
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean canViewerSeeCloudTopsFancy(double viewer_pos_y) {
/* 1411 */     return (this.theWorld.provider.getCloudHeight() - viewer_pos_y + 0.33000001311302185D <= 0.5D);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean canViewerSeeCloudBottomsFancy(double viewer_pos_y) {
/* 1416 */     return (this.theWorld.provider.getCloudHeight() - viewer_pos_y + 0.33000001311302185D > -4.5D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderCloudsFancy_MITE(float par1) {
/* 1427 */     GL11.glShadeModel(7424);
/*      */ 
/*      */     
/* 1430 */     Vec3 rgb = this.theWorld.getCloudColour(par1);
/* 1431 */     float r = (float)rgb.xCoord;
/* 1432 */     float g = (float)rgb.yCoord;
/* 1433 */     float b = (float)rgb.zCoord;
/*      */ 
/*      */     
/* 1436 */     float var4 = 12.0F;
/*      */     
/* 1438 */     double var6 = (this.cloudTickCounter + par1);
/*      */ 
/*      */ 
/*      */     
/* 1442 */     double[] viewer_pos = getViewerPos(par1, var4);
/*      */     
/* 1444 */     double var8 = viewer_pos[0];
/* 1445 */     float var2 = (float)viewer_pos[1];
/* 1446 */     double var10 = viewer_pos[2];
/*      */     
/* 1448 */     float var22 = (float)(var8 - MathHelper.floor_double(var8));
/* 1449 */     float var23 = (float)(var10 - MathHelper.floor_double(var10));
/*      */     
/* 1451 */     float var12 = this.theWorld.provider.getCloudHeight() - var2 + 0.33F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1456 */     boolean player_can_see_cloud_tops = canViewerSeeCloudTopsFancy(var2);
/* 1457 */     boolean player_can_see_cloud_bottoms = canViewerSeeCloudBottomsFancy(var2);
/*      */     
/* 1459 */     if (this.clouds_recompiling_step == 0) {
/*      */       
/* 1461 */       compileCloudsFancy(par1, var8, var2, var10);
/*      */       
/* 1463 */       this.clouds_recompiling_step = -1;
/*      */     }
/* 1465 */     else if (Math.abs(this.last_cloud_compile_x - var8) > 2.0D || Math.abs(this.last_cloud_compile_z - var10) > 2.0D || this.last_cloud_compile_has_cloud_tops != player_can_see_cloud_tops || this.last_cloud_compile_has_cloud_bottoms != player_can_see_cloud_bottoms) {
/*      */ 
/*      */       
/* 1468 */       this.clouds_recompiling_step = 0;
/*      */     } 
/*      */     
/* 1471 */     if (player_can_see_cloud_tops && player_can_see_cloud_bottoms) {
/* 1472 */       GL11.glDisable(2884);
/*      */     } else {
/* 1474 */       GL11.glEnable(2884);
/*      */     } 
/* 1476 */     GL11.glFogf(2915, -16.0F);
/* 1477 */     GL11.glFogf(2916, 384.0F);
/*      */     
/* 1479 */     this.mc.entityRenderer.setupCameraTransform(par1, 0, true);
/*      */     
/* 1481 */     GL11.glPushMatrix();
/* 1482 */     GL11.glScalef(var4, 1.0F, var4);
/* 1483 */     GL11.glTranslated(-(var8 - this.last_cloud_compile_x), -var2, -(var10 - this.last_cloud_compile_z));
/*      */     
/* 1485 */     GL11.glCallList(this.clouds_display_list[0]);
/*      */     
/* 1487 */     if (this.last_cloud_compile_has_cloud_tops) {
/*      */       
/* 1489 */       GL11.glColor4f(r, g, b, 0.8F);
/* 1490 */       GL11.glCallList(this.clouds_display_list[1]);
/*      */     } 
/*      */     
/* 1493 */     if (this.last_cloud_compile_has_cloud_bottoms) {
/*      */ 
/*      */       
/* 1496 */       GL11.glColor4f(r * 0.7F, g * 0.7F, b * 0.7F, 0.8F);
/* 1497 */       GL11.glCallList(this.clouds_display_list[2]);
/*      */     } 
/*      */     
/* 1500 */     GL11.glColor4f(r * 0.9F, g * 0.9F, b * 0.9F, 0.8F);
/* 1501 */     GL11.glCallList(this.clouds_display_list[3]);
/*      */     
/* 1503 */     GL11.glColor4f(r * 0.8F, g * 0.8F, b * 0.8F, 0.8F);
/* 1504 */     GL11.glCallList(this.clouds_display_list[4]);
/*      */     
/* 1506 */     GL11.glPopMatrix();
/*      */     
/* 1508 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/* 1510 */     this.mc.entityRenderer.setupCameraTransform(par1, 0, false);
/*      */     
/* 1512 */     GL11.glFogf(2915, 64.0F);
/* 1513 */     GL11.glFogf(2916, 256.0F);
/*      */     
/* 1515 */     GL11.glEnable(2884);
/* 1516 */     GL11.glShadeModel(7425);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderClouds(float par1) {
/* 1524 */     if (this.mc.theWorld.provider.isSurfaceWorld()) {
/*      */       
/* 1526 */       boolean force_fancy_clouds = true;
/*      */ 
/*      */ 
/*      */       
/* 1530 */       if (force_fancy_clouds || this.mc.gameSettings.isFancyGraphicsEnabled()) {
/*      */ 
/*      */ 
/*      */         
/* 1534 */         if (this.mc.gameSettings.anaglyph)
/*      */         {
/* 1536 */           renderCloudsFancy(par1);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/* 1541 */           renderCloudsFancy_MITE(par1);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1548 */         GL11.glEnable(2884);
/* 1549 */         float var2 = (float)(this.mc.renderViewEntity.lastTickPosY + (this.mc.renderViewEntity.posY - this.mc.renderViewEntity.lastTickPosY) * par1);
/* 1550 */         byte var3 = 32;
/* 1551 */         int var4 = 256 / var3;
/* 1552 */         Tessellator var5 = Tessellator.instance;
/* 1553 */         this.renderEngine.bindTexture(locationCloudsPng);
/* 1554 */         GL11.glEnable(3042);
/* 1555 */         GL11.glBlendFunc(770, 771);
/* 1556 */         Vec3 var6 = this.theWorld.getCloudColour(par1);
/* 1557 */         float var7 = (float)var6.xCoord;
/* 1558 */         float var8 = (float)var6.yCoord;
/* 1559 */         float var9 = (float)var6.zCoord;
/*      */ 
/*      */         
/* 1562 */         if (this.mc.gameSettings.anaglyph) {
/*      */           
/* 1564 */           float f1 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
/* 1565 */           float var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
/* 1566 */           float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
/* 1567 */           var7 = f1;
/* 1568 */           var8 = var11;
/* 1569 */           var9 = var12;
/*      */         } 
/*      */         
/* 1572 */         float var10 = 4.8828125E-4F;
/* 1573 */         double var24 = (this.cloudTickCounter + par1);
/* 1574 */         double var13 = this.mc.renderViewEntity.prevPosX + (this.mc.renderViewEntity.posX - this.mc.renderViewEntity.prevPosX) * par1 + var24 * 0.029999999329447746D;
/* 1575 */         double var15 = this.mc.renderViewEntity.prevPosZ + (this.mc.renderViewEntity.posZ - this.mc.renderViewEntity.prevPosZ) * par1;
/* 1576 */         int var17 = MathHelper.floor_double(var13 / 2048.0D);
/* 1577 */         int var18 = MathHelper.floor_double(var15 / 2048.0D);
/* 1578 */         var13 -= (var17 * 2048);
/* 1579 */         var15 -= (var18 * 2048);
/* 1580 */         float var19 = this.theWorld.provider.getCloudHeight() - var2 + 0.33F;
/* 1581 */         float var20 = (float)(var13 * var10);
/* 1582 */         float var21 = (float)(var15 * var10);
/*      */         
/* 1584 */         boolean player_can_see_cloud_bottoms = (var19 > -0.0F);
/* 1585 */         GL11.glCullFace(player_can_see_cloud_bottoms ? 1028 : 1029);
/*      */         
/* 1587 */         var5.startDrawingQuads();
/*      */ 
/*      */         
/* 1590 */         GL11.glColor4f(var7, var8, var9, 0.8F);
/*      */         
/* 1592 */         var5.hasTexture = true;
/*      */         
/* 1594 */         int[] rawBuffer = var5.rawBuffer;
/*      */         
/* 1596 */         int y0 = Float.floatToRawIntBits(var19);
/*      */         int var22;
/* 1598 */         for (var22 = -var3 * var4; var22 < var3 * var4; var22 += var3) {
/*      */           
/* 1600 */           int u0 = Float.floatToRawIntBits(var22 * var10 + var20);
/* 1601 */           int u1 = Float.floatToRawIntBits((var22 + var3) * var10 + var20);
/*      */           
/* 1603 */           int x0 = Float.floatToRawIntBits(var22);
/* 1604 */           int x1 = Float.floatToRawIntBits((var22 + var3));
/*      */           int var23;
/* 1606 */           for (var23 = -var3 * var4; var23 < var3 * var4; var23 += var3) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1641 */             if (RenderingScheme.current == 0) {
/*      */               
/* 1643 */               var5.addVertexWithUV((var22 + 0), var19, (var23 + var3), ((var22 + 0) * var10 + var20), ((var23 + var3) * var10 + var21));
/* 1644 */               var5.addVertexWithUV((var22 + var3), var19, (var23 + var3), ((var22 + var3) * var10 + var20), ((var23 + var3) * var10 + var21));
/* 1645 */               var5.addVertexWithUV((var22 + var3), var19, (var23 + 0), ((var22 + var3) * var10 + var20), ((var23 + 0) * var10 + var21));
/* 1646 */               var5.addVertexWithUV((var22 + 0), var19, (var23 + 0), ((var22 + 0) * var10 + var20), ((var23 + 0) * var10 + var21));
/*      */             }
/*      */             else {
/*      */               
/* 1650 */               int v0 = Float.floatToRawIntBits((var23 + var3) * var10 + var21);
/* 1651 */               int v2 = Float.floatToRawIntBits(var23 * var10 + var21);
/*      */               
/* 1653 */               int z0 = Float.floatToRawIntBits((var23 + var3));
/* 1654 */               int z1 = Float.floatToRawIntBits(var23);
/*      */               
/* 1656 */               rawBuffer[var5.rawBufferIndex + 3] = u0;
/* 1657 */               rawBuffer[var5.rawBufferIndex + 11] = u1;
/* 1658 */               rawBuffer[var5.rawBufferIndex + 19] = u1;
/* 1659 */               rawBuffer[var5.rawBufferIndex + 27] = u0;
/*      */               
/* 1661 */               rawBuffer[var5.rawBufferIndex + 4] = v0;
/* 1662 */               rawBuffer[var5.rawBufferIndex + 12] = v0;
/* 1663 */               rawBuffer[var5.rawBufferIndex + 20] = v2;
/* 1664 */               rawBuffer[var5.rawBufferIndex + 28] = v2;
/*      */               
/* 1666 */               rawBuffer[var5.rawBufferIndex + 0] = x0;
/* 1667 */               rawBuffer[var5.rawBufferIndex + 8] = x1;
/* 1668 */               rawBuffer[var5.rawBufferIndex + 16] = x1;
/* 1669 */               rawBuffer[var5.rawBufferIndex + 24] = x0;
/*      */               
/* 1671 */               rawBuffer[var5.rawBufferIndex + 1] = y0;
/* 1672 */               rawBuffer[var5.rawBufferIndex + 9] = y0;
/* 1673 */               rawBuffer[var5.rawBufferIndex + 17] = y0;
/* 1674 */               rawBuffer[var5.rawBufferIndex + 25] = y0;
/*      */               
/* 1676 */               rawBuffer[var5.rawBufferIndex + 2] = z0;
/* 1677 */               rawBuffer[var5.rawBufferIndex + 10] = z0;
/* 1678 */               rawBuffer[var5.rawBufferIndex + 18] = z1;
/* 1679 */               rawBuffer[var5.rawBufferIndex + 26] = z1;
/*      */               
/* 1681 */               var5.rawBufferIndex += 32;
/*      */               
/* 1683 */               var5.addedVertices += 4;
/* 1684 */               var5.vertexCount += 4;
/*      */               
/* 1686 */               if (var5.rawBufferIndex >= 2097120) {
/*      */                 
/* 1688 */                 var5.draw();
/* 1689 */                 var5.isDrawing = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1697 */         var5.draw();
/* 1698 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1699 */         GL11.glDisable(3042);
/*      */         
/* 1701 */         GL11.glCullFace(1029);
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
/*      */   public boolean hasCloudFog(double par1, double par3, double par5, float par7) {
/* 1716 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderCloudsFancy(float par1) {
/* 1724 */     GL11.glDisable(2884);
/* 1725 */     float var2 = (float)(this.mc.renderViewEntity.lastTickPosY + (this.mc.renderViewEntity.posY - this.mc.renderViewEntity.lastTickPosY) * par1);
/* 1726 */     Tessellator var3 = Tessellator.instance;
/* 1727 */     float var4 = 12.0F;
/* 1728 */     float var5 = 4.0F;
/* 1729 */     double var6 = (this.cloudTickCounter + par1);
/* 1730 */     double var8 = (this.mc.renderViewEntity.prevPosX + (this.mc.renderViewEntity.posX - this.mc.renderViewEntity.prevPosX) * par1 + var6 * 0.029999999329447746D) / var4;
/* 1731 */     double var10 = (this.mc.renderViewEntity.prevPosZ + (this.mc.renderViewEntity.posZ - this.mc.renderViewEntity.prevPosZ) * par1) / var4 + 0.33000001311302185D;
/* 1732 */     float var12 = this.theWorld.provider.getCloudHeight() - var2 + 0.33F;
/* 1733 */     int var13 = MathHelper.floor_double(var8 / 2048.0D);
/* 1734 */     int var14 = MathHelper.floor_double(var10 / 2048.0D);
/* 1735 */     var8 -= (var13 * 2048);
/* 1736 */     var10 -= (var14 * 2048);
/* 1737 */     this.renderEngine.bindTexture(locationCloudsPng);
/* 1738 */     GL11.glEnable(3042);
/* 1739 */     GL11.glBlendFunc(770, 771);
/* 1740 */     Vec3 var15 = this.theWorld.getCloudColour(par1);
/* 1741 */     float var16 = (float)var15.xCoord;
/* 1742 */     float var17 = (float)var15.yCoord;
/* 1743 */     float var18 = (float)var15.zCoord;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1748 */     if (this.mc.gameSettings.anaglyph) {
/*      */       
/* 1750 */       float f1 = (var16 * 30.0F + var17 * 59.0F + var18 * 11.0F) / 100.0F;
/* 1751 */       float f3 = (var16 * 30.0F + var17 * 70.0F) / 100.0F;
/* 1752 */       float f2 = (var16 * 30.0F + var18 * 70.0F) / 100.0F;
/* 1753 */       var16 = f1;
/* 1754 */       var17 = f3;
/* 1755 */       var18 = f2;
/*      */     } 
/*      */     
/* 1758 */     float var19 = (float)(var8 * 0.0D);
/* 1759 */     float var20 = (float)(var10 * 0.0D);
/* 1760 */     float var21 = 0.00390625F;
/* 1761 */     var19 = MathHelper.floor_double(var8) * var21;
/* 1762 */     var20 = MathHelper.floor_double(var10) * var21;
/* 1763 */     float var22 = (float)(var8 - MathHelper.floor_double(var8));
/* 1764 */     float var23 = (float)(var10 - MathHelper.floor_double(var10));
/* 1765 */     byte var24 = 8;
/* 1766 */     byte var25 = 4;
/* 1767 */     float var26 = 9.765625E-4F;
/* 1768 */     GL11.glScalef(var4, 1.0F, var4);
/*      */     
/* 1770 */     for (int var27 = 0; var27 < 2; var27++) {
/*      */       
/* 1772 */       if (var27 == 0) {
/*      */         
/* 1774 */         GL11.glColorMask(false, false, false, false);
/*      */       }
/* 1776 */       else if (this.mc.gameSettings.anaglyph) {
/*      */         
/* 1778 */         if (EntityRenderer.anaglyphField == 0)
/*      */         {
/* 1780 */           GL11.glColorMask(false, true, true, true);
/*      */         }
/*      */         else
/*      */         {
/* 1784 */           GL11.glColorMask(true, false, false, true);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1789 */         GL11.glColorMask(true, true, true, true);
/*      */       } 
/*      */       
/* 1792 */       for (int var28 = -var25 + 1; var28 <= var25; var28++) {
/*      */         
/* 1794 */         for (int var29 = -var25 + 1; var29 <= var25; var29++) {
/*      */           
/* 1796 */           var3.startDrawingQuads();
/* 1797 */           float var30 = (var28 * var24);
/* 1798 */           float var31 = (var29 * var24);
/* 1799 */           float var32 = var30 - var22;
/* 1800 */           float var33 = var31 - var23;
/*      */           
/* 1802 */           if (var12 > -var5 - 1.0F) {
/*      */             
/* 1804 */             var3.setColorRGBA_F(var16 * 0.7F, var17 * 0.7F, var18 * 0.7F, 0.8F);
/* 1805 */             var3.setNormal(0.0F, -1.0F, 0.0F);
/* 1806 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + var24), ((var30 + 0.0F) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1807 */             var3.addVertexWithUV((var32 + var24), (var12 + 0.0F), (var33 + var24), ((var30 + var24) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1808 */             var3.addVertexWithUV((var32 + var24), (var12 + 0.0F), (var33 + 0.0F), ((var30 + var24) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/* 1809 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + 0.0F), ((var30 + 0.0F) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/*      */           } 
/*      */           
/* 1812 */           if (var12 <= var5 + 1.0F) {
/*      */             
/* 1814 */             var3.setColorRGBA_F(var16, var17, var18, 0.8F);
/* 1815 */             var3.setNormal(0.0F, 1.0F, 0.0F);
/* 1816 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + var5 - var26), (var33 + var24), ((var30 + 0.0F) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1817 */             var3.addVertexWithUV((var32 + var24), (var12 + var5 - var26), (var33 + var24), ((var30 + var24) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1818 */             var3.addVertexWithUV((var32 + var24), (var12 + var5 - var26), (var33 + 0.0F), ((var30 + var24) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/* 1819 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + var5 - var26), (var33 + 0.0F), ((var30 + 0.0F) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/*      */           } 
/*      */           
/* 1822 */           var3.setColorRGBA_F(var16 * 0.9F, var17 * 0.9F, var18 * 0.9F, 0.8F);
/*      */ 
/*      */           
/* 1825 */           if (var28 > -1) {
/*      */             
/* 1827 */             var3.setNormal(-1.0F, 0.0F, 0.0F);
/*      */             
/* 1829 */             for (int var34 = 0; var34 < var24; var34++) {
/*      */               
/* 1831 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + 0.0F), (var33 + var24), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1832 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + var5), (var33 + var24), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1833 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + var5), (var33 + 0.0F), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/* 1834 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + 0.0F), (var33 + 0.0F), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/*      */             } 
/*      */           } 
/*      */           
/* 1838 */           if (var28 <= 1) {
/*      */             
/* 1840 */             var3.setNormal(1.0F, 0.0F, 0.0F);
/*      */             
/* 1842 */             for (int var34 = 0; var34 < var24; var34++) {
/*      */               
/* 1844 */               var3.addVertexWithUV((var32 + var34 + 1.0F - var26), (var12 + 0.0F), (var33 + var24), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1845 */               var3.addVertexWithUV((var32 + var34 + 1.0F - var26), (var12 + var5), (var33 + var24), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + var24) * var21 + var20));
/* 1846 */               var3.addVertexWithUV((var32 + var34 + 1.0F - var26), (var12 + var5), (var33 + 0.0F), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/* 1847 */               var3.addVertexWithUV((var32 + var34 + 1.0F - var26), (var12 + 0.0F), (var33 + 0.0F), ((var30 + var34 + 0.5F) * var21 + var19), ((var31 + 0.0F) * var21 + var20));
/*      */             } 
/*      */           } 
/*      */           
/* 1851 */           var3.setColorRGBA_F(var16 * 0.8F, var17 * 0.8F, var18 * 0.8F, 0.8F);
/*      */           
/* 1853 */           if (var29 > -1) {
/*      */             
/* 1855 */             var3.setNormal(0.0F, 0.0F, -1.0F);
/*      */             
/* 1857 */             for (int var34 = 0; var34 < var24; var34++) {
/*      */               
/* 1859 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + var5), (var33 + var34 + 0.0F), ((var30 + 0.0F) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/* 1860 */               var3.addVertexWithUV((var32 + var24), (var12 + var5), (var33 + var34 + 0.0F), ((var30 + var24) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/* 1861 */               var3.addVertexWithUV((var32 + var24), (var12 + 0.0F), (var33 + var34 + 0.0F), ((var30 + var24) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/* 1862 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + var34 + 0.0F), ((var30 + 0.0F) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/*      */             } 
/*      */           } 
/*      */           
/* 1866 */           if (var29 <= 1) {
/*      */             
/* 1868 */             var3.setNormal(0.0F, 0.0F, 1.0F);
/*      */             
/* 1870 */             for (int var34 = 0; var34 < var24; var34++) {
/*      */               
/* 1872 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + var5), (var33 + var34 + 1.0F - var26), ((var30 + 0.0F) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/* 1873 */               var3.addVertexWithUV((var32 + var24), (var12 + var5), (var33 + var34 + 1.0F - var26), ((var30 + var24) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/* 1874 */               var3.addVertexWithUV((var32 + var24), (var12 + 0.0F), (var33 + var34 + 1.0F - var26), ((var30 + var24) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/* 1875 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + var34 + 1.0F - var26), ((var30 + 0.0F) * var21 + var19), ((var31 + var34 + 0.5F) * var21 + var20));
/*      */             } 
/*      */           } 
/*      */           
/* 1879 */           var3.draw();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1884 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1885 */     GL11.glDisable(3042);
/* 1886 */     GL11.glEnable(2884);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderCloudSide(float par1, int side, double x, double z, boolean player_can_see_cloud_tops, boolean player_can_see_cloud_bottoms) {
/* 3247 */     float var2 = 0.0F;
/* 3248 */     Tessellator var3 = Tessellator.instance;
/* 3249 */     float var5 = 4.0F;
/* 3250 */     double var6 = (this.cloudTickCounter + par1);
/* 3251 */     double var8 = x;
/* 3252 */     double var10 = z;
/* 3253 */     float var12 = this.theWorld.provider.getCloudHeight() - 0.0F + 0.33F;
/* 3254 */     int var13 = MathHelper.floor_double(var8 / 2048.0D);
/* 3255 */     int var14 = MathHelper.floor_double(var10 / 2048.0D);
/* 3256 */     var8 -= (var13 * 2048);
/* 3257 */     var10 -= (var14 * 2048);
/* 3258 */     this.renderEngine.bindTexture(locationCloudsPng);
/*      */     
/* 3260 */     Vec3 var15 = this.theWorld.getCloudColour(par1);
/* 3261 */     float var16 = (float)var15.xCoord;
/* 3262 */     float var17 = (float)var15.yCoord;
/* 3263 */     float var18 = (float)var15.zCoord;
/*      */     
/* 3265 */     float var21 = 0.00390625F;
/* 3266 */     float var19 = MathHelper.floor_double(var8) * 0.00390625F;
/* 3267 */     float var20 = MathHelper.floor_double(var10) * 0.00390625F;
/* 3268 */     float var22 = (float)(var8 - MathHelper.floor_double(var8));
/* 3269 */     float var23 = (float)(var10 - MathHelper.floor_double(var10));
/* 3270 */     byte var24 = 8;
/* 3271 */     byte var25 = 5;
/* 3272 */     float var26 = 9.765625E-4F;
/*      */     
/* 3274 */     boolean first_pass = (side == -1);
/*      */     
/* 3276 */     if (first_pass) {
/*      */       
/* 3278 */       GL11.glDisable(3042);
/* 3279 */       GL11.glColorMask(false, false, false, false);
/*      */     }
/*      */     else {
/*      */       
/* 3283 */       GL11.glEnable(3042);
/* 3284 */       GL11.glBlendFunc(770, 771);
/*      */       
/* 3286 */       GL11.glColorMask(true, true, true, true);
/*      */     } 
/*      */     
/* 3289 */     var3.draw_in_groups = true;
/*      */     
/* 3291 */     int[] rawBuffer = var3.rawBuffer;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3296 */     int u0 = 0, u1 = 0;
/* 3297 */     int v0 = 0, v1 = 0, v2 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3302 */     int cloud_top_y = Float.floatToRawIntBits(var12 + 4.0F);
/* 3303 */     int cloud_bottom_y = Float.floatToRawIntBits(var12);
/* 3304 */     int cloud_bottom_y_spaced = (player_can_see_cloud_bottoms && player_can_see_cloud_tops) ? cloud_bottom_y : Float.floatToRawIntBits(var12 - 0.01F);
/*      */     
/* 3306 */     if (first_pass || side == 0) {
/*      */       
/* 3308 */       var3.startDrawingQuads();
/* 3309 */       var3.hasTexture = true;
/*      */       
/* 3311 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*      */       
/* 3313 */       int y1 = Float.floatToRawIntBits(var12 + 4.0F - 9.765625E-4F);
/*      */       
/* 3315 */       for (int var28 = -5; var28 <= 5; var28++) {
/*      */         
/* 3317 */         float var30 = (var28 * 8);
/*      */         
/* 3319 */         u0 = Float.floatToRawIntBits(var30 * 0.00390625F + var19);
/* 3320 */         u1 = Float.floatToRawIntBits((var30 + 8.0F) * 0.00390625F + var19);
/*      */         
/* 3322 */         for (int var29 = -5; var29 <= 5; var29++) {
/*      */           
/* 3324 */           float var31 = (var29 * 8);
/* 3325 */           float var32 = var30 - var22;
/* 3326 */           float var33 = var31 - var23;
/*      */           
/* 3328 */           v0 = Float.floatToRawIntBits((var31 + 8.0F) * 0.00390625F + var20);
/* 3329 */           v2 = Float.floatToRawIntBits(var31 * 0.00390625F + var20);
/*      */           
/* 3331 */           int x0 = Float.floatToRawIntBits(var32);
/* 3332 */           int x1 = Float.floatToRawIntBits(var32 + 8.0F);
/* 3333 */           int z0 = Float.floatToRawIntBits(var33 + 8.0F);
/* 3334 */           int z2 = Float.floatToRawIntBits(var33);
/*      */           
/* 3336 */           if (RenderingScheme.current == 0) {
/*      */             
/* 3338 */             var3.setColorRGBA_F(var16, var17, var18, 0.8F);
/* 3339 */             var3.setNormal(0.0F, 1.0F, 0.0F);
/* 3340 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + 4.0F - 9.765625E-4F), (var33 + 8.0F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/* 3341 */             var3.addVertexWithUV((var32 + 8.0F), (var12 + 4.0F - 9.765625E-4F), (var33 + 8.0F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/* 3342 */             var3.addVertexWithUV((var32 + 8.0F), (var12 + 4.0F - 9.765625E-4F), (var33 + 0.0F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/* 3343 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + 4.0F - 9.765625E-4F), (var33 + 0.0F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/*      */           }
/*      */           else {
/*      */             
/* 3347 */             rawBuffer[var3.rawBufferIndex + 3] = u0;
/* 3348 */             rawBuffer[var3.rawBufferIndex + 11] = u1;
/* 3349 */             rawBuffer[var3.rawBufferIndex + 19] = u1;
/* 3350 */             rawBuffer[var3.rawBufferIndex + 27] = u0;
/*      */             
/* 3352 */             rawBuffer[var3.rawBufferIndex + 4] = v0;
/* 3353 */             rawBuffer[var3.rawBufferIndex + 12] = v0;
/* 3354 */             rawBuffer[var3.rawBufferIndex + 20] = v2;
/* 3355 */             rawBuffer[var3.rawBufferIndex + 28] = v2;
/*      */             
/* 3357 */             rawBuffer[var3.rawBufferIndex + 0] = x0;
/* 3358 */             rawBuffer[var3.rawBufferIndex + 8] = x1;
/* 3359 */             rawBuffer[var3.rawBufferIndex + 16] = x1;
/* 3360 */             rawBuffer[var3.rawBufferIndex + 24] = x0;
/*      */             
/* 3362 */             rawBuffer[var3.rawBufferIndex + 1] = y1;
/* 3363 */             rawBuffer[var3.rawBufferIndex + 9] = y1;
/* 3364 */             rawBuffer[var3.rawBufferIndex + 17] = y1;
/* 3365 */             rawBuffer[var3.rawBufferIndex + 25] = y1;
/*      */             
/* 3367 */             rawBuffer[var3.rawBufferIndex + 2] = z0;
/* 3368 */             rawBuffer[var3.rawBufferIndex + 10] = z0;
/* 3369 */             rawBuffer[var3.rawBufferIndex + 18] = z2;
/* 3370 */             rawBuffer[var3.rawBufferIndex + 26] = z2;
/*      */             
/* 3372 */             var3.rawBufferIndex += 32;
/*      */             
/* 3374 */             var3.addedVertices += 4;
/* 3375 */             var3.vertexCount += 4;
/*      */             
/* 3377 */             if (var3.rawBufferIndex >= 2097120) {
/*      */               
/* 3379 */               var3.draw();
/* 3380 */               var3.isDrawing = true;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3386 */       var3.draw();
/*      */     } 
/*      */     
/* 3389 */     if (first_pass || side == 1) {
/*      */       
/* 3391 */       var3.startDrawingQuads();
/* 3392 */       var3.hasTexture = true;
/*      */       
/* 3394 */       GL11.glNormal3f(0.0F, -1.0F, 0.0F);
/*      */       
/* 3396 */       for (int var28 = -5; var28 <= 5; var28++) {
/*      */         
/* 3398 */         float var30 = (var28 * 8);
/*      */         
/* 3400 */         u0 = Float.floatToRawIntBits(var30 * 0.00390625F + var19);
/* 3401 */         u1 = Float.floatToRawIntBits((var30 + 8.0F) * 0.00390625F + var19);
/*      */         
/* 3403 */         for (int var29 = -5; var29 <= 5; var29++) {
/*      */           int y0;
/* 3405 */           boolean tight_bottom = (var28 <= -3 || var28 >= 3 || var29 <= -3 || var29 >= 3);
/*      */           
/* 3407 */           if (tight_bottom) {
/* 3408 */             y0 = cloud_bottom_y;
/*      */           } else {
/* 3410 */             y0 = cloud_bottom_y_spaced;
/*      */           } 
/* 3412 */           float var31 = (var29 * 8);
/* 3413 */           float var32 = var30 - var22;
/* 3414 */           float var33 = var31 - var23;
/*      */           
/* 3416 */           v0 = Float.floatToRawIntBits((var31 + 8.0F) * 0.00390625F + var20);
/* 3417 */           v2 = Float.floatToRawIntBits(var31 * 0.00390625F + var20);
/*      */           
/* 3419 */           int x0 = Float.floatToRawIntBits(var32);
/* 3420 */           int x1 = Float.floatToRawIntBits(var32 + 8.0F);
/* 3421 */           int z0 = Float.floatToRawIntBits(var33 + 8.0F);
/* 3422 */           int z2 = Float.floatToRawIntBits(var33);
/*      */           
/* 3424 */           if (RenderingScheme.current == 0) {
/*      */             
/* 3426 */             var3.setColorRGBA_F(var16 * 0.7F, var17 * 0.7F, var18 * 0.7F, 0.8F);
/* 3427 */             var3.setNormal(0.0F, -1.0F, 0.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3433 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + 0.0F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/* 3434 */             var3.addVertexWithUV((var32 + 8.0F), (var12 + 0.0F), (var33 + 0.0F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/* 3435 */             var3.addVertexWithUV((var32 + 8.0F), (var12 + 0.0F), (var33 + 8.0F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/* 3436 */             var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + 8.0F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */ 
/*      */             
/* 3445 */             rawBuffer[var3.rawBufferIndex + 3] = u0;
/* 3446 */             rawBuffer[var3.rawBufferIndex + 11] = u0;
/* 3447 */             rawBuffer[var3.rawBufferIndex + 19] = u1;
/* 3448 */             rawBuffer[var3.rawBufferIndex + 27] = u1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3455 */             rawBuffer[var3.rawBufferIndex + 4] = v0;
/* 3456 */             rawBuffer[var3.rawBufferIndex + 12] = v2;
/* 3457 */             rawBuffer[var3.rawBufferIndex + 20] = v2;
/* 3458 */             rawBuffer[var3.rawBufferIndex + 28] = v0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3465 */             rawBuffer[var3.rawBufferIndex + 0] = x0;
/* 3466 */             rawBuffer[var3.rawBufferIndex + 8] = x0;
/* 3467 */             rawBuffer[var3.rawBufferIndex + 16] = x1;
/* 3468 */             rawBuffer[var3.rawBufferIndex + 24] = x1;
/*      */             
/* 3470 */             rawBuffer[var3.rawBufferIndex + 1] = y0;
/* 3471 */             rawBuffer[var3.rawBufferIndex + 9] = y0;
/* 3472 */             rawBuffer[var3.rawBufferIndex + 17] = y0;
/* 3473 */             rawBuffer[var3.rawBufferIndex + 25] = y0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3480 */             rawBuffer[var3.rawBufferIndex + 2] = z0;
/* 3481 */             rawBuffer[var3.rawBufferIndex + 10] = z2;
/* 3482 */             rawBuffer[var3.rawBufferIndex + 18] = z2;
/* 3483 */             rawBuffer[var3.rawBufferIndex + 26] = z0;
/*      */             
/* 3485 */             var3.rawBufferIndex += 32;
/*      */             
/* 3487 */             var3.addedVertices += 4;
/* 3488 */             var3.vertexCount += 4;
/*      */             
/* 3490 */             if (var3.rawBufferIndex >= 2097120) {
/*      */               
/* 3492 */               var3.draw();
/* 3493 */               var3.isDrawing = true;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3499 */       var3.draw();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3574 */     if (first_pass || side == 2) {
/*      */       
/* 3576 */       var3.startDrawingQuads();
/* 3577 */       var3.hasTexture = true;
/*      */       
/* 3579 */       GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
/*      */       
/* 3581 */       for (int var28 = -1; var28 <= 5; var28++) {
/*      */ 
/*      */         
/* 3584 */         float var30 = (var28 * 8);
/*      */         
/* 3586 */         for (int var29 = -5; var29 <= 5; var29++) {
/*      */           
/* 3588 */           float var31 = (var29 * 8);
/* 3589 */           float var32 = var30 - var22;
/* 3590 */           float var33 = var31 - var23;
/*      */           
/* 3592 */           v0 = Float.floatToRawIntBits((var31 + 8.0F) * 0.00390625F + var20);
/* 3593 */           v2 = Float.floatToRawIntBits(var31 * 0.00390625F + var20);
/*      */           
/* 3595 */           int z0 = Float.floatToRawIntBits(var33 + 8.0F);
/* 3596 */           int z2 = Float.floatToRawIntBits(var33);
/*      */           
/* 3598 */           for (int var34 = 0; var34 < 8; var34++) {
/*      */             
/* 3600 */             float x_plane = var32 + var34;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3605 */             u0 = Float.floatToRawIntBits((var30 + var34 + 0.5F) * 0.00390625F + var19);
/*      */             
/* 3607 */             int x0 = Float.floatToRawIntBits(x_plane);
/*      */             
/* 3609 */             if (RenderingScheme.current == 0) {
/*      */               
/* 3611 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + 0.0F), (var33 + 8.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/* 3612 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + 4.0F), (var33 + 8.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/* 3613 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + 4.0F), (var33 + 0.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/* 3614 */               var3.addVertexWithUV((var32 + var34 + 0.0F), (var12 + 0.0F), (var33 + 0.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/*      */             }
/*      */             else {
/*      */               
/* 3618 */               rawBuffer[var3.rawBufferIndex + 3] = u0;
/* 3619 */               rawBuffer[var3.rawBufferIndex + 11] = u0;
/* 3620 */               rawBuffer[var3.rawBufferIndex + 19] = u0;
/* 3621 */               rawBuffer[var3.rawBufferIndex + 27] = u0;
/*      */               
/* 3623 */               rawBuffer[var3.rawBufferIndex + 4] = v0;
/* 3624 */               rawBuffer[var3.rawBufferIndex + 12] = v0;
/* 3625 */               rawBuffer[var3.rawBufferIndex + 20] = v2;
/* 3626 */               rawBuffer[var3.rawBufferIndex + 28] = v2;
/*      */               
/* 3628 */               rawBuffer[var3.rawBufferIndex + 0] = x0;
/* 3629 */               rawBuffer[var3.rawBufferIndex + 8] = x0;
/* 3630 */               rawBuffer[var3.rawBufferIndex + 16] = x0;
/* 3631 */               rawBuffer[var3.rawBufferIndex + 24] = x0;
/*      */               
/* 3633 */               rawBuffer[var3.rawBufferIndex + 1] = cloud_bottom_y;
/* 3634 */               rawBuffer[var3.rawBufferIndex + 9] = cloud_top_y;
/* 3635 */               rawBuffer[var3.rawBufferIndex + 17] = cloud_top_y;
/* 3636 */               rawBuffer[var3.rawBufferIndex + 25] = cloud_bottom_y;
/*      */               
/* 3638 */               rawBuffer[var3.rawBufferIndex + 2] = z0;
/* 3639 */               rawBuffer[var3.rawBufferIndex + 10] = z0;
/* 3640 */               rawBuffer[var3.rawBufferIndex + 18] = z2;
/* 3641 */               rawBuffer[var3.rawBufferIndex + 26] = z2;
/*      */               
/* 3643 */               var3.rawBufferIndex += 32;
/*      */               
/* 3645 */               var3.addedVertices += 4;
/* 3646 */               var3.vertexCount += 4;
/*      */               
/* 3648 */               if (var3.rawBufferIndex >= 2097120) {
/*      */                 
/* 3650 */                 var3.draw();
/* 3651 */                 var3.isDrawing = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3658 */       var3.draw();
/*      */     } 
/*      */     
/* 3661 */     if (first_pass || side == 2) {
/*      */       
/* 3663 */       var3.startDrawingQuads();
/* 3664 */       var3.hasTexture = true;
/*      */       
/* 3666 */       GL11.glNormal3f(1.0F, 0.0F, 0.0F);
/*      */       
/* 3668 */       for (int var28 = -5; var28 < 1; var28++) {
/*      */ 
/*      */         
/* 3671 */         float var30 = (var28 * 8);
/*      */         
/* 3673 */         for (int var29 = -5; var29 <= 5; var29++) {
/*      */           
/* 3675 */           float var31 = (var29 * 8);
/* 3676 */           float var32 = var30 - var22;
/* 3677 */           float var33 = var31 - var23;
/*      */           
/* 3679 */           v0 = Float.floatToRawIntBits((var31 + 8.0F) * 0.00390625F + var20);
/* 3680 */           v2 = Float.floatToRawIntBits(var31 * 0.00390625F + var20);
/*      */           
/* 3682 */           int z0 = Float.floatToRawIntBits(var33 + 8.0F);
/* 3683 */           int z2 = Float.floatToRawIntBits(var33);
/*      */           
/* 3685 */           for (int var34 = 0; var34 < 8; var34++) {
/*      */             
/* 3687 */             float x_plane = var32 + var34 + 1.0F - 9.765625E-4F;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3692 */             u0 = Float.floatToRawIntBits((var30 + var34 + 0.5F) * 0.00390625F + var19);
/*      */             
/* 3694 */             int x0 = Float.floatToRawIntBits(x_plane);
/*      */             
/* 3696 */             if (RenderingScheme.current == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3703 */               var3.addVertexWithUV((var32 + var34 + 1.0F - 9.765625E-4F), (var12 + 0.0F), (var33 + 0.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/* 3704 */               var3.addVertexWithUV((var32 + var34 + 1.0F - 9.765625E-4F), (var12 + 4.0F), (var33 + 0.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 0.0F) * 0.00390625F + var20));
/* 3705 */               var3.addVertexWithUV((var32 + var34 + 1.0F - 9.765625E-4F), (var12 + 4.0F), (var33 + 8.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/* 3706 */               var3.addVertexWithUV((var32 + var34 + 1.0F - 9.765625E-4F), (var12 + 0.0F), (var33 + 8.0F), ((var30 + var34 + 0.5F) * 0.00390625F + var19), ((var31 + 8.0F) * 0.00390625F + var20));
/*      */             }
/*      */             else {
/*      */               
/* 3710 */               rawBuffer[var3.rawBufferIndex + 3] = u0;
/* 3711 */               rawBuffer[var3.rawBufferIndex + 11] = u0;
/* 3712 */               rawBuffer[var3.rawBufferIndex + 19] = u0;
/* 3713 */               rawBuffer[var3.rawBufferIndex + 27] = u0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3720 */               rawBuffer[var3.rawBufferIndex + 4] = v0;
/* 3721 */               rawBuffer[var3.rawBufferIndex + 12] = v2;
/* 3722 */               rawBuffer[var3.rawBufferIndex + 20] = v2;
/* 3723 */               rawBuffer[var3.rawBufferIndex + 28] = v0;
/*      */               
/* 3725 */               rawBuffer[var3.rawBufferIndex + 0] = x0;
/* 3726 */               rawBuffer[var3.rawBufferIndex + 8] = x0;
/* 3727 */               rawBuffer[var3.rawBufferIndex + 16] = x0;
/* 3728 */               rawBuffer[var3.rawBufferIndex + 24] = x0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3735 */               rawBuffer[var3.rawBufferIndex + 1] = cloud_bottom_y;
/* 3736 */               rawBuffer[var3.rawBufferIndex + 9] = cloud_bottom_y;
/* 3737 */               rawBuffer[var3.rawBufferIndex + 17] = cloud_top_y;
/* 3738 */               rawBuffer[var3.rawBufferIndex + 25] = cloud_top_y;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3745 */               rawBuffer[var3.rawBufferIndex + 2] = z0;
/* 3746 */               rawBuffer[var3.rawBufferIndex + 10] = z2;
/* 3747 */               rawBuffer[var3.rawBufferIndex + 18] = z2;
/* 3748 */               rawBuffer[var3.rawBufferIndex + 26] = z0;
/*      */               
/* 3750 */               var3.rawBufferIndex += 32;
/*      */               
/* 3752 */               var3.addedVertices += 4;
/* 3753 */               var3.vertexCount += 4;
/*      */               
/* 3755 */               if (var3.rawBufferIndex >= 2097120) {
/*      */                 
/* 3757 */                 var3.draw();
/* 3758 */                 var3.isDrawing = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3765 */       var3.draw();
/*      */     } 
/*      */     
/* 3768 */     if (first_pass || side == 3) {
/*      */       
/* 3770 */       var3.startDrawingQuads();
/* 3771 */       var3.hasTexture = true;
/*      */       
/* 3773 */       GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/*      */       
/* 3775 */       for (int var28 = -5; var28 <= 5; var28++) {
/*      */         
/* 3777 */         float var30 = (var28 * 8);
/*      */         
/* 3779 */         u0 = Float.floatToRawIntBits(var30 * 0.00390625F + var19);
/* 3780 */         u1 = Float.floatToRawIntBits((var30 + 8.0F) * 0.00390625F + var19);
/*      */         
/* 3782 */         for (int var29 = -1; var29 <= 5; var29++) {
/*      */ 
/*      */           
/* 3785 */           float var31 = (var29 * 8);
/* 3786 */           float var32 = var30 - var22;
/* 3787 */           float var33 = var31 - var23;
/*      */           
/* 3789 */           int x0 = Float.floatToRawIntBits(var32);
/* 3790 */           int x1 = Float.floatToRawIntBits(var32 + 8.0F);
/*      */           
/* 3792 */           for (int var34 = 0; var34 < 8; var34++) {
/*      */             
/* 3794 */             float z_plane = var33 + var34;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3799 */             v0 = Float.floatToRawIntBits((var31 + var34 + 0.5F) * 0.00390625F + var20);
/*      */             
/* 3801 */             int z0 = Float.floatToRawIntBits(z_plane);
/*      */             
/* 3803 */             if (RenderingScheme.current == 0) {
/*      */               
/* 3805 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + 4.0F), (var33 + var34 + 0.0F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/* 3806 */               var3.addVertexWithUV((var32 + 8.0F), (var12 + 4.0F), (var33 + var34 + 0.0F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/* 3807 */               var3.addVertexWithUV((var32 + 8.0F), (var12 + 0.0F), (var33 + var34 + 0.0F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/* 3808 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + var34 + 0.0F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/*      */             }
/*      */             else {
/*      */               
/* 3812 */               rawBuffer[var3.rawBufferIndex + 3] = u0;
/* 3813 */               rawBuffer[var3.rawBufferIndex + 11] = u1;
/* 3814 */               rawBuffer[var3.rawBufferIndex + 19] = u1;
/* 3815 */               rawBuffer[var3.rawBufferIndex + 27] = u0;
/*      */               
/* 3817 */               rawBuffer[var3.rawBufferIndex + 4] = v0;
/* 3818 */               rawBuffer[var3.rawBufferIndex + 12] = v0;
/* 3819 */               rawBuffer[var3.rawBufferIndex + 20] = v0;
/* 3820 */               rawBuffer[var3.rawBufferIndex + 28] = v0;
/*      */               
/* 3822 */               rawBuffer[var3.rawBufferIndex + 0] = x0;
/* 3823 */               rawBuffer[var3.rawBufferIndex + 8] = x1;
/* 3824 */               rawBuffer[var3.rawBufferIndex + 16] = x1;
/* 3825 */               rawBuffer[var3.rawBufferIndex + 24] = x0;
/*      */               
/* 3827 */               rawBuffer[var3.rawBufferIndex + 1] = cloud_top_y;
/* 3828 */               rawBuffer[var3.rawBufferIndex + 9] = cloud_top_y;
/* 3829 */               rawBuffer[var3.rawBufferIndex + 17] = cloud_bottom_y;
/* 3830 */               rawBuffer[var3.rawBufferIndex + 25] = cloud_bottom_y;
/*      */               
/* 3832 */               rawBuffer[var3.rawBufferIndex + 2] = z0;
/* 3833 */               rawBuffer[var3.rawBufferIndex + 10] = z0;
/* 3834 */               rawBuffer[var3.rawBufferIndex + 18] = z0;
/* 3835 */               rawBuffer[var3.rawBufferIndex + 26] = z0;
/*      */               
/* 3837 */               var3.rawBufferIndex += 32;
/*      */               
/* 3839 */               var3.addedVertices += 4;
/* 3840 */               var3.vertexCount += 4;
/*      */               
/* 3842 */               if (var3.rawBufferIndex >= 2097120) {
/*      */                 
/* 3844 */                 var3.draw();
/* 3845 */                 var3.isDrawing = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3852 */       var3.draw();
/*      */     } 
/*      */     
/* 3855 */     if (first_pass || side == 3) {
/*      */       
/* 3857 */       var3.startDrawingQuads();
/* 3858 */       var3.hasTexture = true;
/*      */       
/* 3860 */       GL11.glNormal3f(0.0F, 0.0F, 1.0F);
/*      */       
/* 3862 */       for (int var28 = -5; var28 <= 5; var28++) {
/*      */         
/* 3864 */         float var30 = (var28 * 8);
/*      */         
/* 3866 */         u0 = Float.floatToRawIntBits(var30 * 0.00390625F + var19);
/* 3867 */         u1 = Float.floatToRawIntBits((var30 + 8.0F) * 0.00390625F + var19);
/*      */         
/* 3869 */         for (int var29 = -5; var29 < 1; var29++) {
/*      */ 
/*      */           
/* 3872 */           float var31 = (var29 * 8);
/* 3873 */           float var32 = var30 - var22;
/* 3874 */           float var33 = var31 - var23;
/*      */           
/* 3876 */           int x0 = Float.floatToRawIntBits(var32);
/* 3877 */           int x1 = Float.floatToRawIntBits(var32 + 8.0F);
/*      */           
/* 3879 */           for (int var34 = 0; var34 < 8; var34++) {
/*      */             
/* 3881 */             float z_plane = var33 + var34 + 1.0F - 9.765625E-4F;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3886 */             v0 = Float.floatToRawIntBits((var31 + var34 + 0.5F) * 0.00390625F + var20);
/*      */             
/* 3888 */             int z0 = Float.floatToRawIntBits(z_plane);
/*      */             
/* 3890 */             if (RenderingScheme.current == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3897 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + 0.0F), (var33 + var34 + 1.0F - 9.765625E-4F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/* 3898 */               var3.addVertexWithUV((var32 + 8.0F), (var12 + 0.0F), (var33 + var34 + 1.0F - 9.765625E-4F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/* 3899 */               var3.addVertexWithUV((var32 + 8.0F), (var12 + 4.0F), (var33 + var34 + 1.0F - 9.765625E-4F), ((var30 + 8.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/* 3900 */               var3.addVertexWithUV((var32 + 0.0F), (var12 + 4.0F), (var33 + var34 + 1.0F - 9.765625E-4F), ((var30 + 0.0F) * 0.00390625F + var19), ((var31 + var34 + 0.5F) * 0.00390625F + var20));
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */ 
/*      */               
/* 3909 */               rawBuffer[var3.rawBufferIndex + 3] = u0;
/* 3910 */               rawBuffer[var3.rawBufferIndex + 11] = u0;
/* 3911 */               rawBuffer[var3.rawBufferIndex + 19] = u1;
/* 3912 */               rawBuffer[var3.rawBufferIndex + 27] = u1;
/*      */               
/* 3914 */               rawBuffer[var3.rawBufferIndex + 4] = v0;
/* 3915 */               rawBuffer[var3.rawBufferIndex + 12] = v0;
/* 3916 */               rawBuffer[var3.rawBufferIndex + 20] = v0;
/* 3917 */               rawBuffer[var3.rawBufferIndex + 28] = v0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3924 */               rawBuffer[var3.rawBufferIndex + 0] = x0;
/* 3925 */               rawBuffer[var3.rawBufferIndex + 8] = x0;
/* 3926 */               rawBuffer[var3.rawBufferIndex + 16] = x1;
/* 3927 */               rawBuffer[var3.rawBufferIndex + 24] = x1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3934 */               rawBuffer[var3.rawBufferIndex + 1] = cloud_top_y;
/* 3935 */               rawBuffer[var3.rawBufferIndex + 9] = cloud_bottom_y;
/* 3936 */               rawBuffer[var3.rawBufferIndex + 17] = cloud_bottom_y;
/* 3937 */               rawBuffer[var3.rawBufferIndex + 25] = cloud_top_y;
/*      */               
/* 3939 */               rawBuffer[var3.rawBufferIndex + 2] = z0;
/* 3940 */               rawBuffer[var3.rawBufferIndex + 10] = z0;
/* 3941 */               rawBuffer[var3.rawBufferIndex + 18] = z0;
/* 3942 */               rawBuffer[var3.rawBufferIndex + 26] = z0;
/*      */               
/* 3944 */               var3.rawBufferIndex += 32;
/*      */               
/* 3946 */               var3.addedVertices += 4;
/* 3947 */               var3.vertexCount += 4;
/*      */               
/* 3949 */               if (var3.rawBufferIndex >= 2097120) {
/*      */                 
/* 3951 */                 var3.draw();
/* 3952 */                 var3.isDrawing = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3959 */       var3.draw();
/*      */     } 
/*      */     
/* 3962 */     var3.draw_in_groups = true;
/*      */     
/* 3964 */     GL11.glDisable(3042);
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
/*      */   public boolean updateRenderers(EntityLivingBase par1EntityLivingBase, boolean par2) {
/* 3981 */     if (this.mc.force_rendering_for_screenshot) {
/*      */       
/* 3983 */       par2 = true;
/* 3984 */       this.mc.force_rendering_for_screenshot = false;
/*      */     } 
/*      */     
/* 3987 */     byte var3 = 2;
/* 3988 */     RenderSorter var4 = new RenderSorter(par1EntityLivingBase);
/* 3989 */     WorldRenderer[] var5 = new WorldRenderer[var3];
/* 3990 */     ArrayList<WorldRenderer> var6 = null;
/* 3991 */     int var7 = this.worldRenderersToUpdate.size();
/* 3992 */     int var8 = 0;
/* 3993 */     this.theWorld.theProfiler.startSection("nearChunksSearch");
/*      */ 
/*      */ 
/*      */     
/*      */     int var9;
/*      */ 
/*      */     
/* 4000 */     for (var9 = 0; var9 < var7; var9++) {
/*      */       
/* 4002 */       WorldRenderer var10 = this.worldRenderersToUpdate.get(var9);
/*      */       
/* 4004 */       if (var10 != null) {
/*      */         
/* 4006 */         if (!par2) {
/*      */           
/* 4008 */           if (var10.distanceToEntitySquared(par1EntityLivingBase) > 256.0F) {
/*      */             int i;
/* 4010 */             for (i = 0; i < var3 && (var5[i] == null || var4.doCompare(var5[i], var10) <= 0); i++);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4015 */             i--;
/*      */             
/* 4017 */             if (i > 0) {
/*      */               
/* 4019 */               int j = i;
/*      */ 
/*      */               
/*      */               while (true) {
/* 4023 */                 j--;
/*      */                 
/* 4025 */                 if (j == 0) {
/*      */                   
/* 4027 */                   var5[i] = var10;
/*      */                   
/*      */                   break;
/*      */                 } 
/* 4031 */                 var5[j - 1] = var5[j];
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/* 4038 */         } else if (!var10.isInFrustum) {
/*      */           continue;
/*      */         } 
/*      */ 
/*      */         
/* 4043 */         if (var6 == null)
/*      */         {
/* 4045 */           var6 = new ArrayList();
/*      */         }
/*      */         
/* 4048 */         var8++;
/* 4049 */         var6.add(var10);
/* 4050 */         this.worldRenderersToUpdate.set(var9, null);
/*      */       } 
/*      */       continue;
/*      */     } 
/* 4054 */     this.theWorld.theProfiler.endSection();
/* 4055 */     this.theWorld.theProfiler.startSection("sort");
/*      */     
/* 4057 */     if (var6 != null) {
/*      */       
/* 4059 */       if (var6.size() > 1)
/*      */       {
/* 4061 */         Collections.sort(var6, var4);
/*      */       }
/*      */       
/* 4064 */       for (var9 = var6.size() - 1; var9 >= 0; var9--) {
/*      */         
/* 4066 */         WorldRenderer var10 = var6.get(var9);
/* 4067 */         var10.updateRenderer();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 4072 */     this.theWorld.theProfiler.endSection();
/* 4073 */     var9 = 0;
/* 4074 */     this.theWorld.theProfiler.startSection("rebuild");
/*      */     
/*      */     int var16;
/* 4077 */     for (var16 = var3 - 1; var16 >= 0; var16--) {
/*      */       
/* 4079 */       WorldRenderer var17 = var5[var16];
/*      */       
/* 4081 */       if (var17 != null) {
/*      */         
/* 4083 */         if (!var17.isInFrustum && var16 != var3 - 1) {
/*      */           
/* 4085 */           var5[var16] = null;
/* 4086 */           var5[0] = null;
/*      */           
/*      */           break;
/*      */         } 
/* 4090 */         var5[var16].updateRenderer();
/*      */         
/* 4092 */         var9++;
/*      */       } 
/*      */     } 
/*      */     
/* 4096 */     this.theWorld.theProfiler.endSection();
/* 4097 */     this.theWorld.theProfiler.startSection("cleanup");
/* 4098 */     var16 = 0;
/* 4099 */     int var11 = 0;
/*      */     
/* 4101 */     for (int var12 = this.worldRenderersToUpdate.size(); var16 != var12; var16++) {
/*      */       
/* 4103 */       WorldRenderer var13 = this.worldRenderersToUpdate.get(var16);
/*      */       
/* 4105 */       if (var13 != null) {
/*      */         
/* 4107 */         boolean var14 = false;
/*      */         
/* 4109 */         for (int var15 = 0; var15 < var3 && !var14; var15++) {
/*      */           
/* 4111 */           if (var13 == var5[var15])
/*      */           {
/* 4113 */             var14 = true;
/*      */           }
/*      */         } 
/*      */         
/* 4117 */         if (!var14) {
/*      */           
/* 4119 */           if (var11 != var16)
/*      */           {
/* 4121 */             this.worldRenderersToUpdate.set(var11, var13);
/*      */           }
/*      */           
/* 4124 */           var11++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 4129 */     this.theWorld.theProfiler.endSection();
/* 4130 */     this.theWorld.theProfiler.startSection("trim");
/*      */ 
/*      */     
/*      */     while (true) {
/* 4134 */       var16--;
/*      */       
/* 4136 */       if (var16 < var11) {
/*      */         
/* 4138 */         this.theWorld.theProfiler.endSection();
/* 4139 */         return (var7 == var8 + var9);
/*      */       } 
/*      */       
/* 4142 */       this.worldRenderersToUpdate.remove(var16);
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
/*      */   public boolean XXXupdateRenderersMITE(EntityLivingBase par1EntityLivingBase, boolean par2, int iteration) {
/*      */     float forced_chunk_rendering_distance_sq;
/* 4158 */     byte var3 = 2;
/* 4159 */     RenderSorter var4 = new RenderSorter(par1EntityLivingBase);
/* 4160 */     WorldRenderer[] var5 = new WorldRenderer[var3];
/* 4161 */     ArrayList<WorldRenderer> var6 = null;
/* 4162 */     int var7 = this.worldRenderersToUpdate.size();
/* 4163 */     int var8 = 0;
/* 4164 */     this.theWorld.theProfiler.startSection("nearChunksSearch");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4174 */     boolean use_forced_chunk_rendering = (ClientProperties.getForcedChunkRenderingDistance() > 1 && this.mc.thePlayer.ticksExisted >= 20 && iteration == 1);
/*      */     
/* 4176 */     if (use_forced_chunk_rendering) {
/* 4177 */       forced_chunk_rendering_distance_sq = (ClientProperties.getForcedChunkRenderingDistance() * 16 * ClientProperties.getForcedChunkRenderingDistance() * 16);
/*      */     } else {
/* 4179 */       forced_chunk_rendering_distance_sq = 0.0F;
/*      */     } 
/*      */     
/*      */     int var9;
/* 4183 */     for (var9 = 0; var9 < var7; var9++) {
/*      */       
/* 4185 */       WorldRenderer var10 = this.worldRenderersToUpdate.get(var9);
/*      */       
/* 4187 */       if (var10 != null) {
/*      */         
/* 4189 */         if (!par2) {
/*      */           
/* 4191 */           float distance_sq_from_viewer = var10.distanceToEntitySquared(par1EntityLivingBase);
/*      */           
/* 4193 */           if (!use_forced_chunk_rendering || !var10.isInFrustum || distance_sq_from_viewer > forced_chunk_rendering_distance_sq)
/*      */           {
/*      */ 
/*      */             
/* 4197 */             if (distance_sq_from_viewer > 256.0F) {
/*      */               int i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 4204 */               for (i = 0; i < var3 && (var5[i] == null || var4.doCompare(var5[i], var10) <= 0); i++);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 4209 */               i--;
/*      */               
/* 4211 */               if (i > 0) {
/*      */                 
/* 4213 */                 int j = i;
/*      */ 
/*      */                 
/*      */                 while (true) {
/* 4217 */                   j--;
/*      */                   
/* 4219 */                   if (j == 0) {
/*      */                     
/* 4221 */                     var5[i] = var10;
/*      */                     
/*      */                     break;
/*      */                   } 
/* 4225 */                   var5[j - 1] = var5[j];
/*      */                 } 
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */           }
/* 4232 */         } else if (!var10.isInFrustum) {
/*      */           continue;
/*      */         } 
/*      */ 
/*      */         
/* 4237 */         if (var6 == null)
/*      */         {
/* 4239 */           var6 = new ArrayList();
/*      */         }
/*      */         
/* 4242 */         var8++;
/* 4243 */         var6.add(var10);
/* 4244 */         this.worldRenderersToUpdate.set(var9, null);
/*      */       } 
/*      */       continue;
/*      */     } 
/* 4248 */     this.theWorld.theProfiler.endSection();
/* 4249 */     this.theWorld.theProfiler.startSection("sort");
/*      */     
/* 4251 */     if (var6 != null) {
/*      */       
/* 4253 */       if (var6.size() > 1)
/*      */       {
/* 4255 */         Collections.sort(var6, var4);
/*      */       }
/*      */       
/* 4258 */       for (var9 = var6.size() - 1; var9 >= 0; var9--) {
/*      */         
/* 4260 */         WorldRenderer var10 = var6.get(var9);
/* 4261 */         var10.updateRenderer();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 4266 */     this.theWorld.theProfiler.endSection();
/* 4267 */     var9 = 0;
/* 4268 */     this.theWorld.theProfiler.startSection("rebuild");
/*      */     
/*      */     int var16;
/* 4271 */     for (var16 = var3 - 1; var16 >= 0; var16--) {
/*      */       
/* 4273 */       WorldRenderer var17 = var5[var16];
/*      */       
/* 4275 */       if (var17 != null) {
/*      */         
/* 4277 */         if (!var17.isInFrustum && var16 != var3 - 1) {
/*      */           
/* 4279 */           var5[var16] = null;
/* 4280 */           var5[0] = null;
/*      */           
/*      */           break;
/*      */         } 
/* 4284 */         var5[var16].updateRenderer();
/*      */         
/* 4286 */         var9++;
/*      */       } 
/*      */     } 
/*      */     
/* 4290 */     this.theWorld.theProfiler.endSection();
/* 4291 */     this.theWorld.theProfiler.startSection("cleanup");
/* 4292 */     var16 = 0;
/* 4293 */     int var11 = 0;
/*      */     
/* 4295 */     for (int var12 = this.worldRenderersToUpdate.size(); var16 != var12; var16++) {
/*      */       
/* 4297 */       WorldRenderer var13 = this.worldRenderersToUpdate.get(var16);
/*      */       
/* 4299 */       if (var13 != null) {
/*      */         
/* 4301 */         boolean var14 = false;
/*      */         
/* 4303 */         for (int var15 = 0; var15 < var3 && !var14; var15++) {
/*      */           
/* 4305 */           if (var13 == var5[var15])
/*      */           {
/* 4307 */             var14 = true;
/*      */           }
/*      */         } 
/*      */         
/* 4311 */         if (!var14) {
/*      */           
/* 4313 */           if (var11 != var16)
/*      */           {
/* 4315 */             this.worldRenderersToUpdate.set(var11, var13);
/*      */           }
/*      */           
/* 4318 */           var11++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 4323 */     this.theWorld.theProfiler.endSection();
/* 4324 */     this.theWorld.theProfiler.startSection("trim");
/*      */ 
/*      */     
/*      */     while (true) {
/* 4328 */       var16--;
/*      */       
/* 4330 */       if (var16 < var11) {
/*      */         
/* 4332 */         this.theWorld.theProfiler.endSection();
/* 4333 */         return (var7 == var8 + var9);
/*      */       } 
/*      */       
/* 4336 */       this.worldRenderersToUpdate.remove(var16);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawBlockDamageTexture(Tessellator par1Tessellator, EntityPlayer par2EntityPlayer, float par3) {
/* 4342 */     double var4 = par2EntityPlayer.lastTickPosX + (par2EntityPlayer.posX - par2EntityPlayer.lastTickPosX) * par3;
/* 4343 */     double var6 = par2EntityPlayer.lastTickPosY + (par2EntityPlayer.posY - par2EntityPlayer.lastTickPosY) * par3;
/* 4344 */     double var8 = par2EntityPlayer.lastTickPosZ + (par2EntityPlayer.posZ - par2EntityPlayer.lastTickPosZ) * par3;
/*      */     
/* 4346 */     if (!this.damagedBlocks.isEmpty()) {
/*      */       
/* 4348 */       GL11.glBlendFunc(774, 768);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4355 */       this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/* 4356 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/* 4357 */       GL11.glPushMatrix();
/* 4358 */       GL11.glDisable(3008);
/* 4359 */       GL11.glPolygonOffset(-3.0F, -3.0F);
/* 4360 */       GL11.glEnable(32823);
/* 4361 */       GL11.glEnable(3008);
/* 4362 */       par1Tessellator.startDrawingQuads();
/* 4363 */       par1Tessellator.setTranslation(-var4, -var6, -var8);
/* 4364 */       GL11.glScalef(1.0F, 0.9999F, 1.0F);
/* 4365 */       par1Tessellator.disableColor();
/* 4366 */       Iterator<DestroyBlockProgress> var10 = this.damagedBlocks.values().iterator();
/*      */       
/* 4368 */       while (var10.hasNext()) {
/*      */         
/* 4370 */         DestroyBlockProgress var11 = var10.next();
/* 4371 */         double var12 = var11.getPartialBlockX() - var4;
/* 4372 */         double var14 = var11.getPartialBlockY() - var6;
/* 4373 */         double var16 = var11.getPartialBlockZ() - var8;
/*      */         
/* 4375 */         if (var12 * var12 + var14 * var14 + var16 * var16 > 1024.0D) {
/*      */           
/* 4377 */           var10.remove();
/*      */           
/*      */           continue;
/*      */         } 
/* 4381 */         int var18 = this.theWorld.getBlockId(var11.getPartialBlockX(), var11.getPartialBlockY(), var11.getPartialBlockZ());
/* 4382 */         Block var19 = (var18 > 0) ? Block.blocksList[var18] : null;
/*      */         
/* 4384 */         if (var19 == null)
/*      */         {
/* 4386 */           var19 = Block.stone;
/*      */         }
/*      */         
/* 4389 */         this.globalRenderBlocks.renderBlockUsingTexture(var19, var11.getPartialBlockX(), var11.getPartialBlockY(), var11.getPartialBlockZ(), this.destroyBlockIcons[var11.getPartialBlockDamage()]);
/*      */       } 
/*      */ 
/*      */       
/* 4393 */       par1Tessellator.draw();
/* 4394 */       par1Tessellator.setTranslation(0.0D, 0.0D, 0.0D);
/* 4395 */       GL11.glDisable(3008);
/* 4396 */       GL11.glPolygonOffset(0.0F, 0.0F);
/* 4397 */       GL11.glDisable(32823);
/* 4398 */       GL11.glEnable(3008);
/* 4399 */       GL11.glDepthMask(true);
/* 4400 */       GL11.glPopMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawSelectionBox(EntityPlayer par1EntityPlayer, RaycastCollision rc, int par3, float par4) {
/* 4410 */     if (par3 == 0 && rc.isBlock()) {
/*      */       
/* 4412 */       GL11.glEnable(3042);
/* 4413 */       GL11.glBlendFunc(770, 771);
/* 4414 */       GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
/* 4415 */       GL11.glLineWidth(2.0F);
/* 4416 */       GL11.glDisable(3553);
/* 4417 */       GL11.glDepthMask(false);
/* 4418 */       float var5 = 0.002F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4432 */       Block block = rc.getBlockHit();
/*      */ 
/*      */       
/* 4435 */       double var7 = par1EntityPlayer.lastTickPosX + (par1EntityPlayer.posX - par1EntityPlayer.lastTickPosX) * par4;
/* 4436 */       double var9 = par1EntityPlayer.lastTickPosY + (par1EntityPlayer.posY - par1EntityPlayer.lastTickPosY) * par4;
/* 4437 */       double var11 = par1EntityPlayer.lastTickPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.lastTickPosZ) * par4;
/* 4438 */       drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(this.theWorld, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z).expand(var5, var5, var5).getOffsetBoundingBox(-var7, -var9, -var11));
/*      */ 
/*      */ 
/*      */       
/* 4442 */       GL11.glDepthMask(true);
/* 4443 */       GL11.glEnable(3553);
/* 4444 */       GL11.glDisable(3042);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
/* 4453 */     Tessellator var2 = Tessellator.instance;
/* 4454 */     var2.startDrawing(3);
/* 4455 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
/* 4456 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
/* 4457 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
/* 4458 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
/* 4459 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
/* 4460 */     var2.draw();
/* 4461 */     var2.startDrawing(3);
/* 4462 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
/* 4463 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
/* 4464 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
/* 4465 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
/* 4466 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
/* 4467 */     var2.draw();
/* 4468 */     var2.startDrawing(1);
/* 4469 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
/* 4470 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
/* 4471 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
/* 4472 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
/* 4473 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
/* 4474 */     var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
/* 4475 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
/* 4476 */     var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
/* 4477 */     var2.draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlocksForUpdate(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 4485 */     int var7 = MathHelper.bucketInt(par1, 16);
/* 4486 */     int var8 = MathHelper.bucketInt(par2, 16);
/* 4487 */     int var9 = MathHelper.bucketInt(par3, 16);
/* 4488 */     int var10 = MathHelper.bucketInt(par4, 16);
/* 4489 */     int var11 = MathHelper.bucketInt(par5, 16);
/* 4490 */     int var12 = MathHelper.bucketInt(par6, 16);
/*      */     
/* 4492 */     for (int var13 = var7; var13 <= var10; var13++) {
/*      */       
/* 4494 */       int var14 = var13 % this.renderChunksWide;
/*      */       
/* 4496 */       if (var14 < 0)
/*      */       {
/* 4498 */         var14 += this.renderChunksWide;
/*      */       }
/*      */       
/* 4501 */       for (int var15 = var8; var15 <= var11; var15++) {
/*      */         
/* 4503 */         int var16 = var15 % this.renderChunksTall;
/*      */         
/* 4505 */         if (var16 < 0)
/*      */         {
/* 4507 */           var16 += this.renderChunksTall;
/*      */         }
/*      */         
/* 4510 */         for (int var17 = var9; var17 <= var12; var17++) {
/*      */           
/* 4512 */           int var18 = var17 % this.renderChunksDeep;
/*      */           
/* 4514 */           if (var18 < 0)
/*      */           {
/* 4516 */             var18 += this.renderChunksDeep;
/*      */           }
/*      */           
/* 4519 */           int var19 = (var18 * this.renderChunksTall + var16) * this.renderChunksWide + var14;
/* 4520 */           WorldRenderer var20 = this.worldRenderers[var19];
/*      */           
/* 4522 */           if (var20 != null && !var20.needsUpdate) {
/*      */             
/* 4524 */             this.worldRenderersToUpdate.add(var20);
/* 4525 */             var20.markDirty();
/*      */           } 
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
/*      */   public void markBlockForUpdate(int par1, int par2, int par3) {
/* 4538 */     markBlocksForUpdate(par1 - 1, par2 - 1, par3 - 1, par1 + 1, par2 + 1, par3 + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlockForRenderUpdate(int par1, int par2, int par3) {
/* 4546 */     markBlocksForUpdate(par1 - 1, par2 - 1, par3 - 1, par1 + 1, par2 + 1, par3 + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlockRangeForRenderUpdate(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 4556 */     markBlocksForUpdate(par1 - 1, par2 - 1, par3 - 1, par4 + 1, par5 + 1, par6 + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clipRenderersByFrustum(ICamera par1ICamera, float par2) {
/* 4565 */     for (int var3 = 0; var3 < this.worldRenderers.length; var3++) {
/*      */       
/* 4567 */       if (!this.worldRenderers[var3].skipAllRenderPasses() && (!(this.worldRenderers[var3]).isInFrustum || (var3 + this.frustumCheckOffset & 0xF) == 0))
/*      */       {
/* 4569 */         this.worldRenderers[var3].updateInFrustum(par1ICamera);
/*      */       }
/*      */     } 
/*      */     
/* 4573 */     this.frustumCheckOffset++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playRecord(String par1Str, int par2, int par3, int par4) {
/* 4581 */     ItemRecord var5 = ItemRecord.getRecord(par1Str);
/*      */     
/* 4583 */     if (par1Str != null && var5 != null)
/*      */     {
/* 4585 */       this.mc.ingameGUI.setRecordPlayingMessage(var5.getRecordTitle());
/*      */     }
/*      */     
/* 4588 */     this.mc.sndManager.playStreaming(par1Str, par2, par3, par4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(String par1Str, double par2, double par4, double par6, float par8, float par9) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playLongDistanceSound(String par1Str, double par2, double par4, double par6, float par8, float par9) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundToNearExcept(EntityPlayer par1EntityPlayer, String par2Str, double par3, double par5, double par7, float par9, float par10) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnParticle(EnumParticle enum_particle, double posX, double posY, double posZ, double velX_or_red, double velY_or_green, double velZ_or_blue) {
/*      */     try {
/* 4615 */       doSpawnParticle(enum_particle, 0, 0, posX, posY, posZ, velX_or_red, velY_or_green, velZ_or_blue);
/*      */     }
/* 4617 */     catch (Throwable var17) {
/*      */       
/* 4619 */       CrashReport var15 = CrashReport.makeCrashReport(var17, "Exception while adding particle");
/* 4620 */       CrashReportCategory var16 = var15.makeCategory("Particle being added");
/*      */       
/* 4622 */       var16.addCrashSection("Ordinal", Integer.valueOf(enum_particle.ordinal()));
/* 4623 */       var16.addCrashSectionCallable("Position", new CallableParticlePositionInfo(this, posX, posY, posZ));
/* 4624 */       throw new ReportedException(var15);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnParticleEx(EnumParticle enum_particle, int index, int data, double par2, double par4, double par6, double par8, double par10, double par12) {
/*      */     try {
/* 4632 */       doSpawnParticle(enum_particle, index, data, par2, par4, par6, par8, par10, par12);
/*      */     }
/* 4634 */     catch (Throwable var17) {
/*      */       
/* 4636 */       CrashReport var15 = CrashReport.makeCrashReport(var17, "Exception while adding particle");
/* 4637 */       CrashReportCategory var16 = var15.makeCategory("Particle being added");
/*      */       
/* 4639 */       var16.addCrashSection("Ordinal", Integer.valueOf(enum_particle.ordinal()));
/* 4640 */       var16.addCrashSectionCallable("Position", new CallableParticlePositionInfo(this, par2, par4, par6));
/* 4641 */       throw new ReportedException(var15);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityFX doSpawnParticle(EnumParticle enum_particle, int index, int metadata, double posX, double posY, double posZ, double par8, double par10, double par12) {
/* 4880 */     if (this.mc != null && this.mc.renderViewEntity != null && this.mc.effectRenderer != null) {
/*      */       
/* 4882 */       int var14 = this.mc.gameSettings.particleSetting;
/*      */       
/* 4884 */       if (var14 == 1 && this.theWorld.rand.nextInt(3) == 0)
/*      */       {
/* 4886 */         var14 = 2;
/*      */       }
/*      */       
/* 4889 */       double var15 = this.mc.renderViewEntity.posX - posX;
/* 4890 */       double var17 = this.mc.renderViewEntity.posY - posY;
/* 4891 */       double var19 = this.mc.renderViewEntity.posZ - posZ;
/* 4892 */       EntityFX var21 = null;
/*      */       
/* 4894 */       if (enum_particle == EnumParticle.hugeexplosion) {
/*      */         
/* 4896 */         this.mc.effectRenderer.addEffect(var21 = new EntityHugeExplodeFX(this.theWorld, posX, posY, posZ, par8, par10, par12));
/*      */       }
/* 4898 */       else if (enum_particle == EnumParticle.largeexplode) {
/*      */         
/* 4900 */         this.mc.effectRenderer.addEffect(var21 = new EntityLargeExplodeFX(this.renderEngine, this.theWorld, posX, posY, posZ, par8, par10, par12));
/*      */       }
/* 4902 */       else if (enum_particle == EnumParticle.fireworkSpark) {
/*      */         
/* 4904 */         this.mc.effectRenderer.addEffect(var21 = new EntityFireworkSparkFX(this.theWorld, posX, posY, posZ, par8, par10, par12, this.mc.effectRenderer));
/*      */       } 
/*      */       
/* 4907 */       if (var21 != null)
/*      */       {
/* 4909 */         return var21;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4915 */       double var22 = 24.0D;
/*      */       
/* 4917 */       if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
/*      */       {
/* 4919 */         return null;
/*      */       }
/* 4921 */       if (var14 > 1)
/*      */       {
/* 4923 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 4927 */       if (enum_particle == EnumParticle.bubble) {
/*      */         
/* 4929 */         var21 = new EntityBubbleFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 4931 */       else if (enum_particle == EnumParticle.suspended) {
/*      */         
/* 4933 */         var21 = new EntitySuspendFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */ 
/*      */         
/* 4936 */         var21.motionY = 5.000000237487257E-4D + Math.random() * 0.0010000000474974513D;
/*      */       }
/* 4938 */       else if (enum_particle == EnumParticle.depthsuspend) {
/*      */ 
/*      */         
/* 4941 */         var21 = EntityDepthSuspendParticle.getCachedOrCreate(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 4947 */       else if (enum_particle == EnumParticle.townaura) {
/*      */         
/* 4949 */         var21 = new EntityAuraFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 4951 */       else if (enum_particle == EnumParticle.crit) {
/*      */         
/* 4953 */         var21 = new EntityCritFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 4955 */       else if (enum_particle == EnumParticle.magicCrit) {
/*      */         
/* 4957 */         var21 = new EntityCritFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/* 4958 */         var21.setRBGColorF(var21.getRedColorF() * 0.3F, var21.getGreenColorF() * 0.8F, var21.getBlueColorF());
/* 4959 */         var21.nextTextureIndexX();
/*      */       }
/* 4961 */       else if (enum_particle == EnumParticle.smoke) {
/*      */         
/* 4963 */         var21 = new EntitySmokeFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 4965 */       else if (enum_particle == EnumParticle.mobSpell) {
/*      */         
/* 4967 */         var21 = new EntitySpellParticleFX(this.theWorld, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/* 4968 */         var21.setRBGColorF((float)par8, (float)par10, (float)par12);
/*      */       }
/* 4970 */       else if (enum_particle == EnumParticle.mobSpellAmbient) {
/*      */         
/* 4972 */         var21 = new EntitySpellParticleFX(this.theWorld, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/* 4973 */         var21.setAlphaF(0.15F);
/* 4974 */         var21.setRBGColorF((float)par8, (float)par10, (float)par12);
/*      */       }
/* 4976 */       else if (enum_particle == EnumParticle.spell) {
/*      */         
/* 4978 */         var21 = new EntitySpellParticleFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 4980 */       else if (enum_particle == EnumParticle.instantSpell) {
/*      */         
/* 4982 */         var21 = new EntitySpellParticleFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/* 4983 */         ((EntitySpellParticleFX)var21).setBaseSpellTextureIndex(144);
/*      */       }
/* 4985 */       else if (enum_particle == EnumParticle.witchMagic) {
/*      */         
/* 4987 */         var21 = new EntitySpellParticleFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/* 4988 */         ((EntitySpellParticleFX)var21).setBaseSpellTextureIndex(144);
/* 4989 */         float var24 = this.theWorld.rand.nextFloat() * 0.5F + 0.35F;
/* 4990 */         var21.setRBGColorF(1.0F * var24, 0.0F * var24, 1.0F * var24);
/*      */       }
/* 4992 */       else if (enum_particle == EnumParticle.note) {
/*      */         
/* 4994 */         var21 = new EntityNoteFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 4996 */       else if (enum_particle == EnumParticle.portal_underworld) {
/*      */ 
/*      */ 
/*      */         
/* 5000 */         var21 = new EntityPortalFX(this.theWorld, posX, posY, posZ, par8, par10, par12, 0);
/*      */       }
/* 5002 */       else if (enum_particle == EnumParticle.portal_nether) {
/*      */         
/* 5004 */         var21 = new EntityPortalFX(this.theWorld, posX, posY, posZ, par8, par10, par12, 2);
/*      */       }
/* 5006 */       else if (enum_particle == EnumParticle.runegate) {
/*      */ 
/*      */         
/* 5009 */         var21 = new EntityPortalFX(this.theWorld, posX, posY, posZ, par8, par10, par12, 1);
/*      */       }
/* 5011 */       else if (enum_particle == EnumParticle.enchantmenttable) {
/*      */         
/* 5013 */         var21 = new EntityEnchantmentTableParticleFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 5015 */       else if (enum_particle == EnumParticle.explode) {
/*      */         
/* 5017 */         var21 = new EntityExplodeFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 5019 */       else if (enum_particle == EnumParticle.flame) {
/*      */         
/* 5021 */         var21 = new EntityFlameFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 5023 */       else if (enum_particle == EnumParticle.lava) {
/*      */         
/* 5025 */         var21 = new EntityLavaFX(this.theWorld, posX, posY, posZ);
/*      */       }
/* 5027 */       else if (enum_particle == EnumParticle.footstep) {
/*      */         
/* 5029 */         var21 = new EntityFootStepFX(this.renderEngine, this.theWorld, posX, posY, posZ);
/*      */       }
/* 5031 */       else if (enum_particle == EnumParticle.splash) {
/*      */         
/* 5033 */         var21 = new EntitySplashFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 5039 */       else if (enum_particle == EnumParticle.largesmoke) {
/*      */         
/* 5041 */         var21 = new EntitySmokeFX(this.theWorld, posX, posY, posZ, par8, par10, par12, 2.5F);
/*      */       }
/* 5043 */       else if (enum_particle == EnumParticle.cloud) {
/*      */         
/* 5045 */         var21 = new EntityCloudFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 5047 */       else if (enum_particle == EnumParticle.reddust) {
/*      */         
/* 5049 */         var21 = new EntityReddustFX(this.theWorld, posX, posY, posZ, (float)par8, (float)par10, (float)par12);
/*      */       }
/* 5051 */       else if (enum_particle == EnumParticle.snowballpoof) {
/*      */         
/* 5053 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.snowball);
/*      */       }
/* 5055 */       else if (enum_particle == EnumParticle.brickpoof) {
/*      */         
/* 5057 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.brick);
/*      */       }
/* 5059 */       else if (enum_particle == EnumParticle.brickpoof_netherrack) {
/*      */         
/* 5061 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.netherrackBrick);
/*      */       }
/* 5063 */       else if (enum_particle == EnumParticle.dripWater) {
/*      */         
/* 5065 */         var21 = new EntityDropParticleFX(this.theWorld, posX, posY, posZ, Material.water);
/*      */       }
/* 5067 */       else if (enum_particle == EnumParticle.dripLava) {
/*      */         
/* 5069 */         var21 = new EntityDropParticleFX(this.theWorld, posX, posY, posZ, Material.lava);
/*      */       }
/* 5071 */       else if (enum_particle == EnumParticle.snowshovel) {
/*      */         
/* 5073 */         var21 = new EntitySnowShovelFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 5075 */       else if (enum_particle == EnumParticle.slime) {
/*      */         
/* 5077 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.slimeBall);
/*      */       }
/* 5079 */       else if (enum_particle == EnumParticle.ochre_jelly) {
/*      */         
/* 5081 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.slimeBall, 1);
/*      */       }
/* 5083 */       else if (enum_particle == EnumParticle.crimson_blob) {
/*      */         
/* 5085 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.slimeBall, 2);
/*      */       }
/* 5087 */       else if (enum_particle == EnumParticle.gray_ooze) {
/*      */         
/* 5089 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.slimeBall, 3);
/*      */       }
/* 5091 */       else if (enum_particle == EnumParticle.black_pudding) {
/*      */         
/* 5093 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, Item.slimeBall, 4);
/*      */       }
/* 5095 */       else if (enum_particle == EnumParticle.heart) {
/*      */         
/* 5097 */         var21 = new EntityHeartFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/*      */       }
/* 5099 */       else if (enum_particle == EnumParticle.vampiric_gain) {
/*      */         
/* 5101 */         var21 = new EntityHeartFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/* 5102 */         var21.setParticleTextureIndex(8);
/*      */       }
/* 5104 */       else if (enum_particle == EnumParticle.angryVillager) {
/*      */         
/* 5106 */         var21 = new EntityHeartFX(this.theWorld, posX, posY + 0.5D, posZ, par8, par10, par12);
/* 5107 */         var21.setParticleTextureIndex(81);
/* 5108 */         var21.setRBGColorF(1.0F, 1.0F, 1.0F);
/*      */       }
/* 5110 */       else if (enum_particle == EnumParticle.happyVillager) {
/*      */         
/* 5112 */         var21 = new EntityAuraFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/* 5113 */         var21.setParticleTextureIndex(82);
/* 5114 */         var21.setRBGColorF(1.0F, 1.0F, 1.0F);
/*      */       }
/* 5116 */       else if (enum_particle == EnumParticle.manure) {
/*      */         
/* 5118 */         var21 = new EntityAuraFX(this.theWorld, posX, posY, posZ, par8, par10, par12);
/* 5119 */         var21.setParticleTextureIndex(98);
/* 5120 */         var21.setRBGColorF(0.4F, 0.3F, 0.2F);
/*      */       }
/* 5122 */       else if (enum_particle == EnumParticle.repair) {
/*      */         
/* 5124 */         var21 = new EntityRepairFX(this.theWorld, posX, posY, posZ, 0.0D, 0.0024999999441206455D, 0.0D);
/* 5125 */         var21.setParticleTextureIndex(99);
/* 5126 */         var21.setRBGColorF((float)par8, (float)par10, (float)par12);
/*      */       }
/* 5128 */       else if (enum_particle == EnumParticle.sacred) {
/*      */         
/* 5130 */         var21 = new EntitySacredFX(this.theWorld, posX, posY, posZ);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 5138 */       else if (enum_particle == EnumParticle.iconcrack) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5155 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, par8, par10, par12, Item.itemsList[index], metadata);
/*      */       }
/* 5157 */       else if (enum_particle == EnumParticle.tilecrack) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5164 */         var21 = (new EntityDiggingFX(this.theWorld, posX, posY, posZ, par8, par10, par12, Block.blocksList[index], metadata)).applyRenderColor(metadata);
/*      */       }
/* 5166 */       else if (enum_particle == EnumParticle.crafting) {
/*      */         
/* 5168 */         var21 = new EntityBreakingFX(this.theWorld, posX, posY, posZ, par8, par10, par12, Item.itemsList[index], metadata);
/* 5169 */         var21.particleMaxAge += 20;
/*      */         
/* 5171 */         while (var21.particleScale > 0.7F) {
/* 5172 */           var21.particleScale = (RNG.float_1[++RNG.random_number_index & 0x7FFF] + 1.0F) * 0.5F;
/*      */         }
/*      */       } 
/*      */       
/* 5176 */       if (var21 != null)
/*      */       {
/* 5178 */         this.mc.effectRenderer.addEffect(var21);
/*      */       }
/*      */       
/* 5181 */       return var21;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5187 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityCreate(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityDestroy(Entity par1Entity) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteAllDisplayLists() {
/* 5208 */     GLAllocation.deleteDisplayLists(this.glRenderListBase);
/*      */   }
/*      */ 
/*      */   
/*      */   public void broadcastSound(int par1, int par2, int par3, int par4, int par5) {
/* 5213 */     Random var6 = this.theWorld.rand;
/*      */     
/* 5215 */     switch (par1) {
/*      */       
/*      */       case 1013:
/*      */       case 1018:
/* 5219 */         if (this.mc.renderViewEntity != null) {
/*      */           
/* 5221 */           double var7 = par2 - this.mc.renderViewEntity.posX;
/* 5222 */           double var9 = par3 - this.mc.renderViewEntity.posY;
/* 5223 */           double var11 = par4 - this.mc.renderViewEntity.posZ;
/* 5224 */           double var13 = Math.sqrt(var7 * var7 + var9 * var9 + var11 * var11);
/* 5225 */           double var15 = this.mc.renderViewEntity.posX;
/* 5226 */           double var17 = this.mc.renderViewEntity.posY;
/* 5227 */           double var19 = this.mc.renderViewEntity.posZ;
/*      */           
/* 5229 */           if (var13 > 0.0D) {
/*      */             
/* 5231 */             var15 += var7 / var13 * 2.0D;
/* 5232 */             var17 += var9 / var13 * 2.0D;
/* 5233 */             var19 += var11 / var13 * 2.0D;
/*      */           } 
/*      */           
/* 5236 */           if (par1 == 1013) {
/*      */             
/* 5238 */             this.theWorld.playSound(var15, var17, var19, "mob.wither.spawn", 1.0F, 1.0F, false); break;
/*      */           } 
/* 5240 */           if (par1 == 1018)
/*      */           {
/* 5242 */             this.theWorld.playSound(var15, var17, var19, "mob.enderdragon.end", 5.0F, 1.0F, false); } 
/*      */         } 
/*      */         break;
/*      */     } 
/*      */   } public void playAuxSFX(EntityPlayer player, int id, int x, int y, int z, int data) {
/*      */     double var8, var10, var12;
/*      */     int var15, var20, var33, var9;
/*      */     double var34;
/*      */     int var35;
/*      */     Block block;
/*      */     float var16, var17, var18;
/*      */     EnumParticle enum_particle;
/*      */     double var36;
/*      */     int var21;
/* 5256 */     Random var7 = this.theWorld.rand;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5269 */     switch (id) {
/*      */       
/*      */       case 1000:
/* 5272 */         this.theWorld.playSound(x, y, z, "random.click", 1.0F, 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1001:
/* 5276 */         this.theWorld.playSound(x, y, z, "random.click", 1.0F, 1.2F, false);
/*      */         return;
/*      */       
/*      */       case 1002:
/* 5280 */         this.theWorld.playSound(x, y, z, "random.bow", 1.0F, 1.2F, false);
/*      */         return;
/*      */       
/*      */       case 1003:
/* 5284 */         if (Math.random() < 0.5D) {
/*      */           
/* 5286 */           this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "random.door_open", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         }
/*      */         else {
/*      */           
/* 5290 */           this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "random.door_close", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 1004:
/* 5296 */         this.theWorld.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (var7.nextFloat() - var7.nextFloat()) * 0.8F, false);
/*      */         return;
/*      */       
/*      */       case 1005:
/* 5300 */         if (Item.itemsList[data] instanceof ItemRecord) {
/*      */           
/* 5302 */           this.theWorld.playRecord(((ItemRecord)Item.itemsList[data]).recordName, x, y, z);
/*      */         }
/*      */         else {
/*      */           
/* 5306 */           this.theWorld.playRecord((String)null, x, y, z);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 1007:
/* 5312 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.ghast.charge", 10.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1008:
/* 5316 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.ghast.fireball", 10.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1009:
/* 5320 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.ghast.fireball", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1010:
/* 5324 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.zombie.wood", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1011:
/* 5328 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.zombie.metal", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1012:
/* 5332 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.zombie.woodbreak", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1014:
/* 5336 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.wither.shoot", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1015:
/* 5340 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.bat.takeoff", 0.05F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1016:
/* 5344 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.zombie.infect", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1017:
/* 5348 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "mob.zombie.unfect", 2.0F, (var7.nextFloat() - var7.nextFloat()) * 0.2F + 1.0F, false);
/*      */         return;
/*      */       
/*      */       case 1020:
/* 5352 */         this.theWorld.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.anvil_break", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         return;
/*      */       
/*      */       case 1021:
/* 5356 */         this.theWorld.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.anvil_use", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         return;
/*      */       
/*      */       case 1022:
/* 5360 */         this.theWorld.playSound((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.anvil_land", 0.3F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         return;
/*      */       
/*      */       case 2000:
/* 5364 */         var33 = data % 3 - 1;
/* 5365 */         var9 = data / 3 % 3 - 1;
/* 5366 */         var10 = x + var33 * 0.6D + 0.5D;
/* 5367 */         var12 = y + 0.5D;
/* 5368 */         var34 = z + var9 * 0.6D + 0.5D;
/*      */         
/* 5370 */         for (var35 = 0; var35 < 10; var35++) {
/*      */           
/* 5372 */           double var37 = var7.nextDouble() * 0.2D + 0.01D;
/* 5373 */           double var38 = var10 + var33 * 0.01D + (var7.nextDouble() - 0.5D) * var9 * 0.5D;
/* 5374 */           double var39 = var12 + (var7.nextDouble() - 0.5D) * 0.5D;
/* 5375 */           double var23 = var34 + var9 * 0.01D + (var7.nextDouble() - 0.5D) * var33 * 0.5D;
/* 5376 */           double var25 = var33 * var37 + var7.nextGaussian() * 0.01D;
/* 5377 */           double var27 = -0.03D + var7.nextGaussian() * 0.01D;
/* 5378 */           double var29 = var9 * var37 + var7.nextGaussian() * 0.01D;
/*      */           
/* 5380 */           spawnParticle(EnumParticle.smoke, var38, var39, var23, var25, var27, var29);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2001:
/* 5457 */         block = Block.getBlock(data & 0xFFF);
/*      */         
/* 5459 */         if (block != null) {
/*      */           
/* 5461 */           int original_data = data;
/*      */           
/* 5463 */           boolean was_silk_harvested = BitHelper.isBitSet(data, SFX_2001_WAS_SILK_HARVESTED);
/* 5464 */           data = BitHelper.clearBit(data, SFX_2001_WAS_SILK_HARVESTED);
/*      */           
/* 5466 */           boolean suppress_sound = BitHelper.isBitSet(data, SFX_2001_SUPPRESS_SOUND);
/* 5467 */           data = BitHelper.clearBit(data, SFX_2001_SUPPRESS_SOUND);
/*      */           
/* 5469 */           boolean was_not_legal = BitHelper.isBitSet(data, SFX_2001_WAS_NOT_LEGAL);
/* 5470 */           data = BitHelper.clearBit(data, SFX_2001_WAS_NOT_LEGAL);
/*      */           
/* 5472 */           boolean use_special_snow_particles = (block == Block.snow && was_not_legal);
/*      */           
/* 5474 */           boolean use_special_portal_damage_particles = BitHelper.isBitSet(data, SFX_2001_FOR_AI_BREAK_DOOR);
/* 5475 */           data = BitHelper.clearBit(data, SFX_2001_FOR_AI_BREAK_DOOR);
/*      */           
/* 5477 */           StepSound step_sound = block.stepSound;
/* 5478 */           String break_sound = (step_sound == null) ? null : step_sound.getBreakSound();
/*      */           
/* 5480 */           if (was_silk_harvested && "random.glass".equals(break_sound)) {
/*      */             
/* 5482 */             step_sound = Block.stone.stepSound;
/* 5483 */             break_sound = step_sound.getBreakSound();
/*      */           } 
/*      */           
/* 5486 */           if (!suppress_sound) {
/* 5487 */             this.mc.sndManager.playSound(break_sound, x + 0.5F, y + 0.5F, z + 0.5F, (step_sound.getVolume() + 1.0F) / 2.0F, step_sound.getPitch() * 0.8F);
/*      */           }
/*      */           
/* 5490 */           if (!was_silk_harvested || (block.blockMaterial != Material.ice && block.blockMaterial != Material.glass)) {
/*      */ 
/*      */             
/* 5493 */             int metadata = data >> 12 & 0xF;
/*      */             
/* 5495 */             if (use_special_snow_particles) {
/* 5496 */               this.mc.effectRenderer.addBlockDestroyEffectsForSnow(x, y, z, block.blockID, metadata);
/* 5497 */             } else if (use_special_portal_damage_particles) {
/* 5498 */               this.mc.effectRenderer.addBlockDestroyEffectsForPortalDamage(x, y, z, block.blockID, metadata);
/*      */             } else {
/* 5500 */               this.mc.effectRenderer.addBlockDestroyEffects(x, y, z, block.blockID, metadata, original_data);
/*      */             } 
/* 5502 */             if (block instanceof IBlockWithPartner && !BitHelper.isBitSet(data, SFX_2001_FOR_PARTNER_BLOCK))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 5512 */               if (block.isPartnerPresent(this.theWorld, x, y, z)) {
/*      */                 
/* 5514 */                 x = block.getPartnerX(x, metadata);
/* 5515 */                 y = block.getPartnerY(y, metadata);
/* 5516 */                 z = block.getPartnerZ(z, metadata);
/*      */                 
/* 5518 */                 Block partner_block = this.theWorld.getBlock(x, y, z);
/*      */                 
/* 5520 */                 if (partner_block instanceof IBlockWithPartner) {
/*      */                   
/* 5522 */                   int partner_block_metadata = this.theWorld.getBlockMetadata(x, y, z);
/*      */                   
/* 5524 */                   if (((IBlockWithPartner)partner_block).requiresPartner(partner_block_metadata)) {
/*      */                     
/* 5526 */                     data = partner_block.blockID + (partner_block_metadata << 12) | BitHelper.clearBit(original_data, BitHelper.getBitValue(16) - 1);
/* 5527 */                     playAuxSFX(player, id, x, y, z, data | SFX_2001_SUPPRESS_SOUND | SFX_2001_FOR_PARTNER_BLOCK);
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2002:
/* 5553 */         var8 = x;
/* 5554 */         var10 = y;
/* 5555 */         var12 = z;
/*      */ 
/*      */         
/* 5558 */         for (var15 = 0; var15 < 8; var15++)
/*      */         {
/*      */           
/* 5561 */           spawnParticleEx(EnumParticle.iconcrack, Item.potion.itemID, data, var8, var10, var12, var7.nextGaussian() * 0.15D, var7.nextDouble() * 0.2D, var7.nextGaussian() * 0.15D);
/*      */         }
/*      */         
/* 5564 */         var15 = Item.potion.getColorFromDamage(data);
/* 5565 */         var16 = (var15 >> 16 & 0xFF) / 255.0F;
/* 5566 */         var17 = (var15 >> 8 & 0xFF) / 255.0F;
/* 5567 */         var18 = (var15 >> 0 & 0xFF) / 255.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5575 */         enum_particle = Item.potion.isEffectInstant(data) ? EnumParticle.instantSpell : EnumParticle.spell;
/*      */         
/* 5577 */         for (var20 = 0; var20 < 100; var20++) {
/*      */           
/* 5579 */           double var39 = var7.nextDouble() * 4.0D;
/* 5580 */           double var23 = var7.nextDouble() * Math.PI * 2.0D;
/* 5581 */           double var25 = Math.cos(var23) * var39;
/* 5582 */           double var27 = 0.01D + var7.nextDouble() * 0.5D;
/* 5583 */           double var29 = Math.sin(var23) * var39;
/*      */           
/* 5585 */           EntityFX var31 = doSpawnParticle(enum_particle, 0, 0, var8 + var25 * 0.1D, var10 + 0.3D, var12 + var29 * 0.1D, var25, var27, var29);
/*      */           
/* 5587 */           if (var31 != null) {
/*      */             
/* 5589 */             float var32 = 0.75F + var7.nextFloat() * 0.25F;
/* 5590 */             var31.setRBGColorF(var16 * var32, var17 * var32, var18 * var32);
/* 5591 */             var31.multiplyVelocity((float)var39);
/*      */           } 
/*      */         } 
/*      */         
/* 5595 */         this.theWorld.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "random.glass", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         return;
/*      */       
/*      */       case 2003:
/* 5599 */         var8 = x + 0.5D;
/* 5600 */         var10 = y;
/* 5601 */         var12 = z + 0.5D;
/*      */ 
/*      */         
/* 5604 */         for (var15 = 0; var15 < 8; var15++)
/*      */         {
/*      */           
/* 5607 */           spawnParticleEx(EnumParticle.iconcrack, Item.eyeOfEnder.itemID, 0, var8, var10, var12, var7.nextGaussian() * 0.15D, var7.nextDouble() * 0.2D, var7.nextGaussian() * 0.15D);
/*      */         }
/*      */         
/* 5610 */         for (var36 = 0.0D; var36 < 6.283185307179586D; var36 += 0.15707963267948966D) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5615 */           spawnParticle(EnumParticle.portal_underworld, var8 + Math.cos(var36) * 5.0D, var10 - 0.4D, var12 + Math.sin(var36) * 5.0D, Math.cos(var36) * -5.0D, 0.0D, Math.sin(var36) * -5.0D);
/* 5616 */           spawnParticle(EnumParticle.portal_underworld, var8 + Math.cos(var36) * 5.0D, var10 - 0.4D, var12 + Math.sin(var36) * 5.0D, Math.cos(var36) * -7.0D, 0.0D, Math.sin(var36) * -7.0D);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2004:
/* 5622 */         for (var21 = 0; var21 < 20; var21++) {
/*      */           
/* 5624 */           double var22 = x + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
/* 5625 */           double var24 = y + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
/* 5626 */           double var26 = z + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
/*      */ 
/*      */ 
/*      */           
/* 5630 */           this.theWorld.spawnParticle(EnumParticle.smoke, var22, var24, var26, 0.0D, 0.0D, 0.0D);
/* 5631 */           this.theWorld.spawnParticle(EnumParticle.flame, var22, var24, var26, 0.0D, 0.0D, 0.0D);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2005:
/* 5637 */         ItemDye.func_96603_a(this.theWorld, x, y, z, data);
/*      */         return;
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
/* 5656 */     Minecraft.setErrorMessage("playAuxSFX: id " + id + " not handled");
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
/*      */   public void destroyBlockPartially(int destroyer_entity_id, int x, int y, int z, int tenths_destroyed) {
/* 5689 */     if (tenths_destroyed == -2) {
/*      */       
/* 5691 */       Set set = this.damagedBlocks.entrySet();
/*      */       
/* 5693 */       Iterator<Map.Entry> i = set.iterator();
/*      */       
/* 5695 */       while (i.hasNext()) {
/*      */         
/* 5697 */         Map.Entry entry = i.next();
/*      */         
/* 5699 */         DestroyBlockProgress dbp = (DestroyBlockProgress)entry.getValue();
/*      */         
/* 5701 */         if (dbp.getPartialBlockX() == x && dbp.getPartialBlockY() == y && dbp.getPartialBlockZ() == z) {
/* 5702 */           i.remove();
/*      */         }
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/* 5708 */     if (tenths_destroyed >= 0 && tenths_destroyed < 10) {
/*      */       
/* 5710 */       DestroyBlockProgress destroy_block_progress = (DestroyBlockProgress)this.damagedBlocks.get(Integer.valueOf(destroyer_entity_id));
/*      */       
/* 5712 */       if (destroy_block_progress == null || destroy_block_progress.getPartialBlockX() != x || destroy_block_progress.getPartialBlockY() != y || destroy_block_progress.getPartialBlockZ() != z) {
/*      */         
/* 5714 */         destroy_block_progress = new DestroyBlockProgress(destroyer_entity_id, x, y, z);
/* 5715 */         this.damagedBlocks.put(Integer.valueOf(destroyer_entity_id), destroy_block_progress);
/*      */       } 
/*      */       
/* 5718 */       destroy_block_progress.setPartialBlockDamage(tenths_destroyed);
/* 5719 */       destroy_block_progress.setCloudUpdateTick(this.cloudTickCounter);
/*      */     }
/*      */     else {
/*      */       
/* 5723 */       this.damagedBlocks.remove(Integer.valueOf(destroyer_entity_id));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearPartialBlockDamage() {
/* 5729 */     this.damagedBlocks.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   public void registerDestroyBlockIcons(IconRegister par1IconRegister) {
/* 5734 */     this.destroyBlockIcons = new Icon[10];
/*      */     
/* 5736 */     for (int var2 = 0; var2 < this.destroyBlockIcons.length; var2++)
/*      */     {
/* 5738 */       this.destroyBlockIcons[var2] = par1IconRegister.registerIcon("destroy_stage_" + var2);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderGlobal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */