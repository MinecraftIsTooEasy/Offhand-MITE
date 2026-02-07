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
/*     */ public class TileEntityRenderer
/*     */ {
/*  13 */   private Map specialRendererMap = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  16 */   public static TileEntityRenderer instance = new TileEntityRenderer();
/*     */ 
/*     */   
/*     */   private FontRenderer fontRenderer;
/*     */ 
/*     */   
/*     */   public static double staticPlayerX;
/*     */ 
/*     */   
/*     */   public static double staticPlayerY;
/*     */ 
/*     */   
/*     */   public static double staticPlayerZ;
/*     */ 
/*     */   
/*     */   public TextureManager renderEngine;
/*     */ 
/*     */   
/*     */   public World worldObj;
/*     */   
/*     */   public EntityLivingBase entityLivingPlayer;
/*     */   
/*     */   public float playerYaw;
/*     */   
/*     */   public float playerPitch;
/*     */   
/*     */   public double playerX;
/*     */   
/*     */   public double playerY;
/*     */   
/*     */   public double playerZ;
/*     */ 
/*     */   
/*     */   private TileEntityRenderer() {
/*  50 */     this.specialRendererMap.put(TileEntitySign.class, new TileEntitySignRenderer());
/*  51 */     this.specialRendererMap.put(TileEntityMobSpawner.class, new TileEntityMobSpawnerRenderer());
/*  52 */     this.specialRendererMap.put(TileEntityPiston.class, new TileEntityRendererPiston());
/*  53 */     this.specialRendererMap.put(TileEntityChest.class, new TileEntityChestRenderer());
/*  54 */     this.specialRendererMap.put(TileEntityEnderChest.class, new TileEntityEnderChestRenderer());
/*  55 */     this.specialRendererMap.put(TileEntityEnchantmentTable.class, new RenderEnchantmentTable());
/*  56 */     this.specialRendererMap.put(TileEntityEndPortal.class, new RenderEndPortal());
/*  57 */     this.specialRendererMap.put(TileEntityBeacon.class, new TileEntityBeaconRenderer());
/*  58 */     this.specialRendererMap.put(TileEntitySkull.class, new TileEntitySkullRenderer());
/*  59 */     Iterator<TileEntitySpecialRenderer> var1 = this.specialRendererMap.values().iterator();
/*     */     
/*  61 */     while (var1.hasNext()) {
/*     */       
/*  63 */       TileEntitySpecialRenderer var2 = var1.next();
/*  64 */       var2.setTileEntityRenderer(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntitySpecialRenderer getSpecialRendererForClass(Class<TileEntity> par1Class) {
/*  73 */     TileEntitySpecialRenderer var2 = (TileEntitySpecialRenderer)this.specialRendererMap.get(par1Class);
/*     */     
/*  75 */     if (var2 == null && par1Class != TileEntity.class) {
/*     */       
/*  77 */       var2 = getSpecialRendererForClass(par1Class.getSuperclass());
/*  78 */       this.specialRendererMap.put(par1Class, var2);
/*     */     } 
/*     */     
/*  81 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSpecialRenderer(TileEntity par1TileEntity) {
/*  89 */     return (getSpecialRendererForEntity(par1TileEntity) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntitySpecialRenderer getSpecialRendererForEntity(TileEntity par1TileEntity) {
/*  98 */     return (par1TileEntity == null) ? null : getSpecialRendererForClass(par1TileEntity.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cacheActiveRenderInfo(World par1World, TextureManager par2TextureManager, FontRenderer par3FontRenderer, EntityLivingBase par4EntityLivingBase, float par5) {
/* 108 */     if (this.worldObj != par1World)
/*     */     {
/* 110 */       setWorld(par1World);
/*     */     }
/*     */     
/* 113 */     this.renderEngine = par2TextureManager;
/* 114 */     this.entityLivingPlayer = par4EntityLivingBase;
/* 115 */     this.fontRenderer = par3FontRenderer;
/* 116 */     this.playerYaw = par4EntityLivingBase.prevRotationYaw + (par4EntityLivingBase.rotationYaw - par4EntityLivingBase.prevRotationYaw) * par5;
/* 117 */     this.playerPitch = par4EntityLivingBase.prevRotationPitch + (par4EntityLivingBase.rotationPitch - par4EntityLivingBase.prevRotationPitch) * par5;
/* 118 */     this.playerX = par4EntityLivingBase.lastTickPosX + (par4EntityLivingBase.posX - par4EntityLivingBase.lastTickPosX) * par5;
/* 119 */     this.playerY = par4EntityLivingBase.lastTickPosY + (par4EntityLivingBase.posY - par4EntityLivingBase.lastTickPosY) * par5;
/* 120 */     this.playerZ = par4EntityLivingBase.lastTickPosZ + (par4EntityLivingBase.posZ - par4EntityLivingBase.lastTickPosZ) * par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntity(TileEntity par1TileEntity, float par2) {
/* 128 */     if (par1TileEntity.getDistanceFrom(this.playerX, this.playerY, this.playerZ) < par1TileEntity.getMaxRenderDistanceSquared()) {
/*     */       
/* 130 */       int var3 = this.worldObj.getLightBrightnessForSkyBlocks(par1TileEntity.xCoord, par1TileEntity.yCoord, par1TileEntity.zCoord, 0);
/*     */       
/* 132 */       int var4 = var3 % 65536;
/* 133 */       int var5 = var3 / 65536;
/* 134 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var4 / 1.0F, var5 / 1.0F);
/* 135 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 136 */       renderTileEntityAt(par1TileEntity, par1TileEntity.xCoord - staticPlayerX, par1TileEntity.yCoord - staticPlayerY, par1TileEntity.zCoord - staticPlayerZ, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 145 */     if ((Minecraft.getClientPlayer()).ticksExisted < 10) {
/*     */       return;
/*     */     }
/* 148 */     TileEntitySpecialRenderer var9 = getSpecialRendererForEntity(par1TileEntity);
/*     */     
/* 150 */     if (var9 != null) {
/*     */       
/*     */       try {
/*     */         
/* 154 */         var9.renderTileEntityAt(par1TileEntity, par2, par4, par6, par8);
/*     */       }
/* 156 */       catch (Throwable var13) {
/*     */         
/* 158 */         CrashReport var11 = CrashReport.makeCrashReport(var13, "Rendering Tile Entity");
/* 159 */         CrashReportCategory var12 = var11.makeCategory("Tile Entity Details");
/* 160 */         par1TileEntity.func_85027_a(var12);
/* 161 */         throw new ReportedException(var11);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorld(World par1World) {
/* 171 */     this.worldObj = par1World;
/* 172 */     Iterator<TileEntitySpecialRenderer> var2 = this.specialRendererMap.values().iterator();
/*     */     
/* 174 */     while (var2.hasNext()) {
/*     */       
/* 176 */       TileEntitySpecialRenderer var3 = var2.next();
/*     */       
/* 178 */       if (var3 != null)
/*     */       {
/* 180 */         var3.onWorldChange(par1World);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public FontRenderer getFontRenderer() {
/* 187 */     return this.fontRenderer;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */