/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RenderManager
/*     */ {
/*  13 */   private Map entityRenderMap = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  16 */   public static RenderManager instance = new RenderManager();
/*     */   
/*     */   private FontRenderer fontRenderer;
/*     */   
/*     */   public static double renderPosX;
/*     */   
/*     */   public static double renderPosY;
/*     */   
/*     */   public static double renderPosZ;
/*     */   
/*     */   public TextureManager renderEngine;
/*     */   
/*     */   public ItemRenderer itemRenderer;
/*     */   
/*     */   public World worldObj;
/*     */   
/*     */   public EntityLivingBase livingPlayer;
/*     */   
/*     */   public EntityLivingBase field_96451_i;
/*     */   public float playerViewY;
/*     */   public float playerViewX;
/*     */   public GameSettings options;
/*     */   public double viewerPosX;
/*     */   public double viewerPosY;
/*     */   public double viewerPosZ;
/*     */   public static boolean field_85095_o;
/*     */   
/*     */   private RenderManager() {
/*  44 */     this.entityRenderMap.put(EntityCaveSpider.class, new RenderCaveSpider());
/*  45 */     this.entityRenderMap.put(EntitySpider.class, new RenderSpider());
/*  46 */     this.entityRenderMap.put(EntityPig.class, new RenderPig(new ModelPig(), new ModelPig(0.5F), 0.7F));
/*  47 */     this.entityRenderMap.put(EntitySheep.class, new RenderSheep(new ModelSheep2(), new ModelSheep1(), 0.7F));
/*  48 */     this.entityRenderMap.put(EntityCow.class, new RenderCow(new ModelCow(), 0.7F));
/*  49 */     this.entityRenderMap.put(EntityMooshroom.class, new RenderMooshroom(new ModelCow(), 0.7F));
/*  50 */     this.entityRenderMap.put(EntityWolf.class, new RenderWolf(new ModelWolf(), new ModelWolf(), 0.5F));
/*  51 */     this.entityRenderMap.put(EntityChicken.class, new RenderChicken(new ModelChicken(), 0.3F));
/*  52 */     this.entityRenderMap.put(EntityOcelot.class, new RenderOcelot(new ModelOcelot(), 0.4F));
/*  53 */     this.entityRenderMap.put(EntitySilverfish.class, new RenderSilverfish());
/*  54 */     this.entityRenderMap.put(EntityCreeper.class, new RenderCreeper());
/*  55 */     this.entityRenderMap.put(EntityEnderman.class, new RenderEnderman());
/*  56 */     this.entityRenderMap.put(EntitySnowman.class, new RenderSnowMan());
/*  57 */     this.entityRenderMap.put(EntitySkeleton.class, new RenderSkeleton());
/*  58 */     this.entityRenderMap.put(EntityWitch.class, new RenderWitch());
/*  59 */     this.entityRenderMap.put(EntityBlaze.class, new RenderBlaze());
/*  60 */     this.entityRenderMap.put(EntityZombie.class, new RenderZombie());
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.entityRenderMap.put(EntityGhoul.class, new RenderGhoul());
/*  65 */     this.entityRenderMap.put(EntityWight.class, new RenderWight());
/*  66 */     this.entityRenderMap.put(EntityInvisibleStalker.class, new RenderInvisibleStalker());
/*  67 */     this.entityRenderMap.put(EntityDemonSpider.class, new RenderDemonSpider());
/*  68 */     this.entityRenderMap.put(EntityHellhound.class, new RenderHellhound(new ModelWolf(), new ModelWolf(), 0.5F));
/*  69 */     this.entityRenderMap.put(EntityDireWolf.class, new RenderDireWolf(new ModelWolf(), new ModelWolf(), 0.5F, 1.1F));
/*  70 */     this.entityRenderMap.put(EntityWoodSpider.class, new RenderWoodSpider(0.6F));
/*  71 */     this.entityRenderMap.put(EntityInfernalCreeper.class, new RenderInfernalCreeper());
/*  72 */     this.entityRenderMap.put(EntityShadow.class, new RenderShadow());
/*  73 */     this.entityRenderMap.put(EntityFireElemental.class, new RenderFireElemental());
/*  74 */     this.entityRenderMap.put(EntityBlackWidowSpider.class, new RenderBlackWidowSpider(0.6F));
/*     */     
/*  76 */     this.entityRenderMap.put(EntityRevenant.class, new RenderZombie());
/*  77 */     this.entityRenderMap.put(EntityEarthElemental.class, new RenderEarthElemental());
/*  78 */     this.entityRenderMap.put(EntityJelly.class, new RenderGelatinousCube(new ModelGelatinousCube(16), new ModelGelatinousCube(0), 0.25F));
/*  79 */     this.entityRenderMap.put(EntityBlob.class, new RenderGelatinousCube(new ModelGelatinousCube(16), new ModelGelatinousCube(0), 0.25F));
/*  80 */     this.entityRenderMap.put(EntityOoze.class, new RenderGelatinousCube(new ModelGelatinousCube(16), new ModelGelatinousCube(0), 0.25F));
/*  81 */     this.entityRenderMap.put(EntityPudding.class, new RenderGelatinousCube(new ModelGelatinousCube(16), new ModelGelatinousCube(0), 0.25F));
/*  82 */     this.entityRenderMap.put(EntityVampireBat.class, new RenderBat());
/*  83 */     this.entityRenderMap.put(EntityGiantVampireBat.class, new RenderBat());
/*  84 */     this.entityRenderMap.put(EntityLongdead.class, new RenderSkeleton());
/*  85 */     this.entityRenderMap.put(EntityLongdeadGuardian.class, new RenderSkeleton());
/*  86 */     this.entityRenderMap.put(EntityNightwing.class, new RenderBat());
/*  87 */     this.entityRenderMap.put(EntityNetherspawn.class, new RenderSilverfish());
/*  88 */     this.entityRenderMap.put(EntityCopperspine.class, new RenderSilverfish());
/*  89 */     this.entityRenderMap.put(EntityHoarySilverfish.class, new RenderSilverfish());
/*  90 */     this.entityRenderMap.put(EntityClayGolem.class, new RenderEarthElemental());
/*  91 */     this.entityRenderMap.put(EntityBoneLord.class, new RenderSkeleton());
/*  92 */     this.entityRenderMap.put(EntityAncientBoneLord.class, new RenderSkeleton());
/*  93 */     this.entityRenderMap.put(EntityPhaseSpider.class, new RenderPhaseSpider());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.entityRenderMap.put(EntitySlime.class, new RenderGelatinousCube(new ModelGelatinousCube(16), new ModelGelatinousCube(0), 0.25F));
/*  99 */     this.entityRenderMap.put(EntityMagmaCube.class, new RenderMagmaCube());
/* 100 */     this.entityRenderMap.put(EntityPlayer.class, new RenderPlayer());
/* 101 */     this.entityRenderMap.put(EntityGiantZombie.class, new RenderGiantZombie(new ModelZombie(), 0.5F, 6.0F));
/* 102 */     this.entityRenderMap.put(EntityGhast.class, new RenderGhast());
/* 103 */     this.entityRenderMap.put(EntitySquid.class, new RenderSquid(new ModelSquid(), 0.7F));
/* 104 */     this.entityRenderMap.put(EntityVillager.class, new RenderVillager());
/* 105 */     this.entityRenderMap.put(EntityIronGolem.class, new RenderIronGolem());
/* 106 */     this.entityRenderMap.put(EntityBat.class, new RenderBat());
/* 107 */     this.entityRenderMap.put(EntityDragon.class, new RenderDragon());
/* 108 */     this.entityRenderMap.put(EntityEnderCrystal.class, new RenderEnderCrystal());
/* 109 */     this.entityRenderMap.put(EntityWither.class, new RenderWither());
/* 110 */     this.entityRenderMap.put(Entity.class, new RenderEntity());
/* 111 */     this.entityRenderMap.put(EntityPainting.class, new RenderPainting());
/* 112 */     this.entityRenderMap.put(EntityItemFrame.class, new RenderItemFrame());
/* 113 */     this.entityRenderMap.put(EntityLeashKnot.class, new RenderLeashKnot());
/* 114 */     this.entityRenderMap.put(EntityArrow.class, new RenderArrow());
/* 115 */     this.entityRenderMap.put(EntitySnowball.class, new RenderSnowball(Item.snowball));
/* 116 */     this.entityRenderMap.put(EntityEnderPearl.class, new RenderSnowball(Item.enderPearl));
/* 117 */     this.entityRenderMap.put(EntityEnderEye.class, new RenderSnowball(Item.eyeOfEnder));
/* 118 */     this.entityRenderMap.put(EntityEgg.class, new RenderSnowball(Item.egg));
/* 119 */     this.entityRenderMap.put(EntityBrick.class, new RenderSnowball(null));
/* 120 */     this.entityRenderMap.put(EntityGelatinousSphere.class, new RenderSnowball(Item.slimeBall));
/* 121 */     this.entityRenderMap.put(EntityWeb.class, new RenderSnowball(Item.thrownWeb));
/* 122 */     this.entityRenderMap.put(EntityPotion.class, new RenderSnowball(Item.potion, 16384));
/* 123 */     this.entityRenderMap.put(EntityExpBottle.class, new RenderSnowball(Item.expBottle));
/* 124 */     this.entityRenderMap.put(EntityFireworkRocket.class, new RenderSnowball(Item.firework));
/* 125 */     this.entityRenderMap.put(EntityLargeFireball.class, new RenderFireball(2.0F));
/* 126 */     this.entityRenderMap.put(EntitySmallFireball.class, new RenderFireball(0.5F));
/* 127 */     this.entityRenderMap.put(EntityWitherSkull.class, new RenderWitherSkull());
/* 128 */     this.entityRenderMap.put(EntityItem.class, new RenderItem());
/* 129 */     this.entityRenderMap.put(EntityXPOrb.class, new RenderXPOrb());
/* 130 */     this.entityRenderMap.put(EntityTNTPrimed.class, new RenderTNTPrimed());
/* 131 */     this.entityRenderMap.put(EntityFallingSand.class, new RenderFallingSand());
/* 132 */     this.entityRenderMap.put(EntityMinecartTNT.class, new RenderTntMinecart());
/* 133 */     this.entityRenderMap.put(EntityMinecartMobSpawner.class, new RenderMinecartMobSpawner());
/* 134 */     this.entityRenderMap.put(EntityMinecart.class, new RenderMinecart());
/* 135 */     this.entityRenderMap.put(EntityBoat.class, new RenderBoat());
/* 136 */     this.entityRenderMap.put(EntityFishHook.class, new RenderFish());
/* 137 */     this.entityRenderMap.put(EntityHorse.class, new RenderHorse(new ModelHorse(), 0.75F));
/* 138 */     this.entityRenderMap.put(EntityLightningBolt.class, new RenderLightningBolt());
/* 139 */     Iterator<Render> var1 = this.entityRenderMap.values().iterator();
/*     */     
/* 141 */     while (var1.hasNext()) {
/*     */       
/* 143 */       Render var2 = var1.next();
/* 144 */       var2.setRenderManager(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Render getEntityClassRenderObject(Class<Entity> par1Class) {
/* 150 */     Render var2 = (Render)this.entityRenderMap.get(par1Class);
/*     */     
/* 152 */     if (var2 == null && par1Class != Entity.class) {
/*     */       
/* 154 */       var2 = getEntityClassRenderObject(par1Class.getSuperclass());
/* 155 */       this.entityRenderMap.put(par1Class, var2);
/*     */     } 
/*     */     
/* 158 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Render getEntityRenderObject(Entity par1Entity) {
/* 163 */     return getEntityClassRenderObject(par1Entity.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cacheActiveRenderInfo(World par1World, TextureManager par2TextureManager, FontRenderer par3FontRenderer, EntityLivingBase par4EntityLivingBase, EntityLivingBase par5EntityLivingBase, GameSettings par6GameSettings, float par7) {
/* 172 */     this.worldObj = par1World;
/* 173 */     this.renderEngine = par2TextureManager;
/* 174 */     this.options = par6GameSettings;
/* 175 */     this.livingPlayer = par4EntityLivingBase;
/* 176 */     this.field_96451_i = par5EntityLivingBase;
/* 177 */     this.fontRenderer = par3FontRenderer;
/*     */ 
/*     */     
/* 180 */     if (par4EntityLivingBase.inBed()) {
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
/* 194 */       this.playerViewX = par4EntityLivingBase.rotationPitch;
/* 195 */       this.playerViewY = par4EntityLivingBase.rotationYaw;
/*     */     }
/*     */     else {
/*     */       
/* 199 */       this.playerViewY = par4EntityLivingBase.prevRotationYaw + (par4EntityLivingBase.rotationYaw - par4EntityLivingBase.prevRotationYaw) * par7;
/* 200 */       this.playerViewX = par4EntityLivingBase.prevRotationPitch + (par4EntityLivingBase.rotationPitch - par4EntityLivingBase.prevRotationPitch) * par7;
/*     */     } 
/*     */     
/* 203 */     if (par6GameSettings.thirdPersonView == 2)
/*     */     {
/* 205 */       this.playerViewY += 180.0F;
/*     */     }
/*     */     
/* 208 */     this.viewerPosX = par4EntityLivingBase.lastTickPosX + (par4EntityLivingBase.posX - par4EntityLivingBase.lastTickPosX) * par7;
/* 209 */     this.viewerPosY = par4EntityLivingBase.lastTickPosY + (par4EntityLivingBase.posY - par4EntityLivingBase.lastTickPosY) * par7;
/* 210 */     this.viewerPosZ = par4EntityLivingBase.lastTickPosZ + (par4EntityLivingBase.posZ - par4EntityLivingBase.lastTickPosZ) * par7;
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
/*     */   public void renderEntity(Entity par1Entity, float par2) {
/* 225 */     World world = par1Entity.worldObj;
/*     */     
/* 227 */     if (par1Entity instanceof EntityItem) {
/*     */       
/* 229 */       if (par1Entity.ticksExisted < 1) {
/*     */         return;
/*     */       }
/* 232 */       if (par1Entity.ticksExisted < 5 && world.doBlockCollisionBoundsIntersectWithBB(par1Entity.getBlockPosX(), par1Entity.getBlockPosY(), par1Entity.getBlockPosZ(), par1Entity.boundingBox)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 239 */     Chunk chunk = world.getChunkFromBlockCoords(par1Entity.getBlockPosX(), par1Entity.getBlockPosZ());
/*     */ 
/*     */     
/* 242 */     if (chunk == null || chunk.isEmpty()) {
/*     */       return;
/*     */     }
/* 245 */     int x = par1Entity.getBlockPosX();
/* 246 */     int y = par1Entity.getBlockPosY();
/* 247 */     int z = par1Entity.getBlockPosZ();
/*     */     
/* 249 */     RenderGlobal render_global = Minecraft.theMinecraft.renderGlobal;
/*     */     
/* 251 */     WorldRenderer world_renderer = null;
/*     */     
/* 253 */     if (par1Entity.index_of_last_applicable_world_renderer < render_global.worldRenderers.length) {
/*     */       
/* 255 */       world_renderer = render_global.worldRenderers[par1Entity.index_of_last_applicable_world_renderer];
/*     */       
/* 257 */       if (world_renderer == null || !world_renderer.isRenderingCoords(x, y, z)) {
/* 258 */         world_renderer = null;
/*     */       }
/*     */     } 
/* 261 */     if (world_renderer == null) {
/* 262 */       world_renderer = Minecraft.theMinecraft.renderGlobal.getWorldRendererFor(x, y, z, par1Entity);
/*     */     }
/*     */     
/* 265 */     if (world_renderer == null || !world_renderer.isInitialized) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 270 */     if (par1Entity.ticksExisted == 0) {
/*     */       
/* 272 */       par1Entity.lastTickPosX = par1Entity.posX;
/* 273 */       par1Entity.lastTickPosY = par1Entity.posY;
/* 274 */       par1Entity.lastTickPosZ = par1Entity.posZ;
/*     */     } 
/*     */     
/* 277 */     double var3 = par1Entity.lastTickPosX + (par1Entity.posX - par1Entity.lastTickPosX) * par2;
/* 278 */     double var5 = par1Entity.lastTickPosY + (par1Entity.posY - par1Entity.lastTickPosY) * par2;
/* 279 */     double var7 = par1Entity.lastTickPosZ + (par1Entity.posZ - par1Entity.lastTickPosZ) * par2;
/* 280 */     float var9 = par1Entity.prevRotationYaw + (par1Entity.rotationYaw - par1Entity.prevRotationYaw) * par2;
/* 281 */     int var10 = par1Entity.getBrightnessForRender(par2);
/*     */     
/* 283 */     if (par1Entity.isBurning())
/*     */     {
/* 285 */       var10 = 15728880;
/*     */     }
/*     */     
/* 288 */     int var11 = var10 % 65536;
/* 289 */     int var12 = var10 / 65536;
/* 290 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var11 / 1.0F, var12 / 1.0F);
/* 291 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 292 */     renderEntityWithPosYaw(par1Entity, var3 - renderPosX, var5 - renderPosY, var7 - renderPosZ, var9, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderEntityWithPosYaw(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 301 */     Render var10 = null;
/*     */ 
/*     */     
/*     */     try {
/* 305 */       var10 = getEntityRenderObject(par1Entity);
/*     */       
/* 307 */       if (var10 != null && this.renderEngine != null) {
/*     */         
/* 309 */         if (field_85095_o && !par1Entity.isInvisible()) {
/*     */           
/*     */           try {
/*     */             
/* 313 */             func_85094_b(par1Entity, par2, par4, par6, par8, par9);
/*     */           }
/* 315 */           catch (Throwable var17) {
/*     */             
/* 317 */             throw new ReportedException(CrashReport.makeCrashReport(var17, "Rendering entity hitbox in world"));
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 323 */           var10.doRender(par1Entity, par2, par4, par6, par8, par9);
/*     */         }
/* 325 */         catch (Throwable var16) {
/*     */           
/* 327 */           throw new ReportedException(CrashReport.makeCrashReport(var16, "Rendering entity in world"));
/*     */         } 
/*     */ 
/*     */         
/*     */         try {
/* 332 */           var10.doRenderShadowAndFire(par1Entity, par2, par4, par6, par8, par9);
/*     */         }
/* 334 */         catch (Throwable var15) {
/*     */           
/* 336 */           throw new ReportedException(CrashReport.makeCrashReport(var15, "Post-rendering entity in world"));
/*     */         }
/*     */       
/*     */       } 
/* 340 */     } catch (Throwable var18) {
/*     */       
/* 342 */       CrashReport var12 = CrashReport.makeCrashReport(var18, "Rendering entity in world");
/* 343 */       CrashReportCategory var13 = var12.makeCategory("Entity being rendered");
/* 344 */       par1Entity.addEntityCrashInfo(var13);
/* 345 */       CrashReportCategory var14 = var12.makeCategory("Renderer details");
/* 346 */       var14.addCrashSection("Assigned renderer", var10);
/* 347 */       var14.addCrashSection("Location", CrashReportCategory.func_85074_a(par2, par4, par6));
/* 348 */       var14.addCrashSection("Rotation", Float.valueOf(par8));
/* 349 */       var14.addCrashSection("Delta", Float.valueOf(par9));
/* 350 */       throw new ReportedException(var12);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_85094_b(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 357 */     if (par1Entity instanceof EntityClientPlayerMP) {
/* 358 */       par4 -= par1Entity.yOffset;
/*     */     }
/* 360 */     GL11.glDepthMask(false);
/* 361 */     GL11.glDisable(3553);
/* 362 */     GL11.glDisable(2896);
/* 363 */     GL11.glDisable(2884);
/* 364 */     GL11.glDisable(3042);
/* 365 */     GL11.glPushMatrix();
/* 366 */     Tessellator var10 = Tessellator.instance;
/* 367 */     var10.startDrawingQuads();
/* 368 */     var10.setColorRGBA(255, 255, 255, 32);
/* 369 */     double var11 = (-par1Entity.width / 2.0F);
/* 370 */     double var13 = (-par1Entity.width / 2.0F);
/* 371 */     double var15 = (par1Entity.width / 2.0F);
/* 372 */     double var17 = (-par1Entity.width / 2.0F);
/* 373 */     double var19 = (-par1Entity.width / 2.0F);
/* 374 */     double var21 = (par1Entity.width / 2.0F);
/* 375 */     double var23 = (par1Entity.width / 2.0F);
/* 376 */     double var25 = (par1Entity.width / 2.0F);
/* 377 */     double var27 = par1Entity.height;
/* 378 */     var10.addVertex(par2 + var11, par4 + var27, par6 + var13);
/* 379 */     var10.addVertex(par2 + var11, par4, par6 + var13);
/* 380 */     var10.addVertex(par2 + var15, par4, par6 + var17);
/* 381 */     var10.addVertex(par2 + var15, par4 + var27, par6 + var17);
/* 382 */     var10.addVertex(par2 + var23, par4 + var27, par6 + var25);
/* 383 */     var10.addVertex(par2 + var23, par4, par6 + var25);
/* 384 */     var10.addVertex(par2 + var19, par4, par6 + var21);
/* 385 */     var10.addVertex(par2 + var19, par4 + var27, par6 + var21);
/* 386 */     var10.addVertex(par2 + var15, par4 + var27, par6 + var17);
/* 387 */     var10.addVertex(par2 + var15, par4, par6 + var17);
/* 388 */     var10.addVertex(par2 + var23, par4, par6 + var25);
/* 389 */     var10.addVertex(par2 + var23, par4 + var27, par6 + var25);
/* 390 */     var10.addVertex(par2 + var19, par4 + var27, par6 + var21);
/* 391 */     var10.addVertex(par2 + var19, par4, par6 + var21);
/* 392 */     var10.addVertex(par2 + var11, par4, par6 + var13);
/* 393 */     var10.addVertex(par2 + var11, par4 + var27, par6 + var13);
/* 394 */     var10.draw();
/* 395 */     GL11.glPopMatrix();
/* 396 */     GL11.glEnable(3553);
/* 397 */     GL11.glEnable(2896);
/* 398 */     GL11.glEnable(2884);
/* 399 */     GL11.glDisable(3042);
/* 400 */     GL11.glDepthMask(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(World par1World) {
/* 408 */     this.worldObj = par1World;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDistanceToCamera(double par1, double par3, double par5) {
/* 413 */     double var7 = par1 - this.viewerPosX;
/* 414 */     double var9 = par3 - this.viewerPosY;
/* 415 */     double var11 = par5 - this.viewerPosZ;
/* 416 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderer getFontRenderer() {
/* 424 */     return this.fontRenderer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateIcons(IconRegister par1IconRegister) {
/* 429 */     Iterator<Render> var2 = this.entityRenderMap.values().iterator();
/*     */     
/* 431 */     while (var2.hasNext()) {
/*     */       
/* 433 */       Render var3 = var2.next();
/* 434 */       var3.updateIcons(par1IconRegister);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */