/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class ItemRenderer
/*     */ {
/*   8 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*   9 */   private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
/*  10 */   private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
/*     */ 
/*     */   
/*     */   private Minecraft mc;
/*     */ 
/*     */   
/*     */   private ItemStack itemToRender;
/*     */ 
/*     */   
/*     */   private float equippedProgress;
/*     */   
/*     */   private float prevEquippedProgress;
/*     */   
/*  23 */   private RenderBlocks renderBlocksInstance = new RenderBlocks();
/*     */   
/*     */   public final MapItemRenderer mapItemRenderer;
/*     */   
/*  27 */   private int equippedItemSlot = -1;
/*     */ 
/*     */ 
/*     */   
/*  31 */   static double[] x = new double[4];
/*  32 */   static double[] y = new double[4];
/*  33 */   static double[] z = new double[4];
/*  34 */   static double[] u = new double[4];
/*  35 */   static double[] v = new double[4];
/*  36 */   static float[] r = new float[4];
/*  37 */   static float[] g = new float[4];
/*  38 */   static float[] b = new float[4];
/*  39 */   static int[] brightness = new int[4];
/*     */ 
/*     */   
/*     */   public static Icon render_icon_override;
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemRenderer(Minecraft par1Minecraft) {
/*  47 */     this.mc = par1Minecraft;
/*  48 */     this.mapItemRenderer = new MapItemRenderer(par1Minecraft.gameSettings, par1Minecraft.getTextureManager());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItem(EntityLivingBase par1EntityLivingBase, ItemStack par2ItemStack, int par3) {
/*  56 */     GL11.glPushMatrix();
/*  57 */     TextureManager var4 = this.mc.getTextureManager();
/*     */     
/*  59 */     if (par2ItemStack.getItemSpriteNumber() == 0 && par2ItemStack.itemID < Block.blocksList.length && Block.blocksList[par2ItemStack.itemID] != null && RenderBlocks.renderItemIn3d(Block.blocksList[par2ItemStack.itemID].getRenderType())) {
/*     */       
/*  61 */       var4.bindTexture(var4.getResourceLocation(0));
/*  62 */       this.renderBlocksInstance.renderBlockAsItem(Block.blocksList[par2ItemStack.itemID], par2ItemStack.getItemSubtype(), 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  66 */       Icon var5 = par1EntityLivingBase.getItemIcon(par2ItemStack, par3);
/*     */       
/*  68 */       if (render_icon_override != null) {
/*  69 */         var5 = render_icon_override;
/*     */       }
/*  71 */       if (var5 == null) {
/*     */         
/*  73 */         GL11.glPopMatrix();
/*     */         
/*     */         return;
/*     */       } 
/*  77 */       var4.bindTexture(var4.getResourceLocation(par2ItemStack.getItemSpriteNumber()));
/*  78 */       Tessellator var6 = Tessellator.instance;
/*  79 */       float var7 = var5.getMinU();
/*  80 */       float var8 = var5.getMaxU();
/*  81 */       float var9 = var5.getMinV();
/*  82 */       float var10 = var5.getMaxV();
/*  83 */       float var11 = 0.0F;
/*  84 */       float var12 = 0.3F;
/*  85 */       GL11.glEnable(32826);
/*  86 */       GL11.glTranslatef(-var11, -var12, 0.0F);
/*  87 */       float var13 = 1.5F;
/*  88 */       GL11.glScalef(var13, var13, var13);
/*  89 */       GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
/*  90 */       GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
/*  91 */       GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
/*  92 */       renderItemIn2D(var6, var8, var9, var7, var10, var5.getIconWidth(), var5.getIconHeight(), 0.0625F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  97 */       if (par2ItemStack.hasEffect() && par3 == 0) {
/*     */         
/*  99 */         GL11.glDepthFunc(514);
/* 100 */         GL11.glDisable(2896);
/* 101 */         var4.bindTexture(RES_ITEM_GLINT);
/* 102 */         GL11.glEnable(3042);
/* 103 */         GL11.glBlendFunc(768, 1);
/* 104 */         float var14 = 0.76F;
/* 105 */         GL11.glColor4f(0.5F * var14, 0.25F * var14, 0.8F * var14, 1.0F);
/* 106 */         GL11.glMatrixMode(5890);
/* 107 */         GL11.glPushMatrix();
/* 108 */         float var15 = 0.125F;
/* 109 */         GL11.glScalef(var15, var15, var15);
/* 110 */         float var16 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
/* 111 */         GL11.glTranslatef(var16, 0.0F, 0.0F);
/* 112 */         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 113 */         renderItemIn2D(var6, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/* 114 */         GL11.glPopMatrix();
/* 115 */         GL11.glPushMatrix();
/* 116 */         GL11.glScalef(var15, var15, var15);
/* 117 */         var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
/* 118 */         GL11.glTranslatef(-var16, 0.0F, 0.0F);
/* 119 */         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 120 */         renderItemIn2D(var6, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/* 121 */         GL11.glPopMatrix();
/* 122 */         GL11.glMatrixMode(5888);
/* 123 */         GL11.glDisable(3042);
/* 124 */         GL11.glEnable(2896);
/* 125 */         GL11.glDepthFunc(515);
/*     */       } 
/*     */       
/* 128 */       GL11.glDisable(32826);
/*     */     } 
/*     */     
/* 131 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderItemIn2D(Tessellator par0Tessellator, float par1, float par2, float par3, float par4, int par5, int par6, float par7) {
/* 139 */     par0Tessellator.startDrawingQuads();
/* 140 */     par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
/* 141 */     par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, par1, par4);
/* 142 */     par0Tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, par3, par4);
/* 143 */     par0Tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, par3, par2);
/* 144 */     par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, par1, par2);
/* 145 */     par0Tessellator.draw();
/* 146 */     par0Tessellator.startDrawingQuads();
/* 147 */     par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
/* 148 */     par0Tessellator.addVertexWithUV(0.0D, 1.0D, (0.0F - par7), par1, par2);
/* 149 */     par0Tessellator.addVertexWithUV(1.0D, 1.0D, (0.0F - par7), par3, par2);
/* 150 */     par0Tessellator.addVertexWithUV(1.0D, 0.0D, (0.0F - par7), par3, par4);
/* 151 */     par0Tessellator.addVertexWithUV(0.0D, 0.0D, (0.0F - par7), par1, par4);
/* 152 */     par0Tessellator.draw();
/* 153 */     float var8 = 0.5F * (par1 - par3) / par5;
/* 154 */     float var9 = 0.5F * (par4 - par2) / par6;
/* 155 */     par0Tessellator.startDrawingQuads();
/* 156 */     par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*     */     int var10;
/*     */     
/* 161 */     for (var10 = 0; var10 < par5; var10++) {
/*     */       
/* 163 */       float var11 = var10 / par5;
/* 164 */       float var12 = par1 + (par3 - par1) * var11 - var8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       if (RenderingScheme.current == 0) {
/*     */         
/* 174 */         par0Tessellator.addVertexWithUV(var11, 0.0D, (0.0F - par7), var12, par4);
/* 175 */         par0Tessellator.addVertexWithUV(var11, 0.0D, 0.0D, var12, par4);
/* 176 */         par0Tessellator.addVertexWithUV(var11, 1.0D, 0.0D, var12, par2);
/* 177 */         par0Tessellator.addVertexWithUV(var11, 1.0D, (0.0F - par7), var12, par2);
/*     */       }
/*     */       else {
/*     */         
/* 181 */         x[0] = var11;
/* 182 */         y[0] = 0.0D;
/* 183 */         z[0] = -par7;
/* 184 */         u[0] = var12;
/* 185 */         v[0] = par4;
/*     */         
/* 187 */         x[1] = var11;
/* 188 */         y[1] = 0.0D;
/* 189 */         z[1] = 0.0D;
/* 190 */         u[1] = var12;
/* 191 */         v[1] = par4;
/*     */         
/* 193 */         x[2] = var11;
/* 194 */         y[2] = 1.0D;
/* 195 */         z[2] = 0.0D;
/* 196 */         u[2] = var12;
/* 197 */         v[2] = par2;
/*     */         
/* 199 */         x[3] = var11;
/* 200 */         y[3] = 1.0D;
/* 201 */         z[3] = -par7;
/* 202 */         u[3] = var12;
/* 203 */         v[3] = par2;
/*     */         
/* 205 */         par0Tessellator.add4VerticesWithUV(x, y, z, u, v);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 211 */     par0Tessellator.draw();
/* 212 */     par0Tessellator.startDrawingQuads();
/* 213 */     par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 216 */     for (var10 = 0; var10 < par5; var10++) {
/*     */       
/* 218 */       float var11 = var10 / par5;
/* 219 */       float var12 = par1 + (par3 - par1) * var11 - var8;
/* 220 */       float var13 = var11 + 1.0F / par5;
/* 221 */       par0Tessellator.addVertexWithUV(var13, 1.0D, (0.0F - par7), var12, par2);
/* 222 */       par0Tessellator.addVertexWithUV(var13, 1.0D, 0.0D, var12, par2);
/* 223 */       par0Tessellator.addVertexWithUV(var13, 0.0D, 0.0D, var12, par4);
/* 224 */       par0Tessellator.addVertexWithUV(var13, 0.0D, (0.0F - par7), var12, par4);
/*     */     } 
/*     */     
/* 227 */     par0Tessellator.draw();
/* 228 */     par0Tessellator.startDrawingQuads();
/* 229 */     par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);
/*     */     
/* 231 */     for (var10 = 0; var10 < par6; var10++) {
/*     */       
/* 233 */       float var11 = var10 / par6;
/* 234 */       float var12 = par4 + (par2 - par4) * var11 - var9;
/* 235 */       float var13 = var11 + 1.0F / par6;
/* 236 */       par0Tessellator.addVertexWithUV(0.0D, var13, 0.0D, par1, var12);
/* 237 */       par0Tessellator.addVertexWithUV(1.0D, var13, 0.0D, par3, var12);
/* 238 */       par0Tessellator.addVertexWithUV(1.0D, var13, (0.0F - par7), par3, var12);
/* 239 */       par0Tessellator.addVertexWithUV(0.0D, var13, (0.0F - par7), par1, var12);
/*     */     } 
/*     */     
/* 242 */     par0Tessellator.draw();
/* 243 */     par0Tessellator.startDrawingQuads();
/* 244 */     par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);
/*     */     
/* 246 */     for (var10 = 0; var10 < par6; var10++) {
/*     */       
/* 248 */       float var11 = var10 / par6;
/* 249 */       float var12 = par4 + (par2 - par4) * var11 - var9;
/* 250 */       par0Tessellator.addVertexWithUV(1.0D, var11, 0.0D, par3, var12);
/* 251 */       par0Tessellator.addVertexWithUV(0.0D, var11, 0.0D, par1, var12);
/* 252 */       par0Tessellator.addVertexWithUV(0.0D, var11, (0.0F - par7), par1, var12);
/* 253 */       par0Tessellator.addVertexWithUV(1.0D, var11, (0.0F - par7), par3, var12);
/*     */     } 
/*     */     
/* 256 */     par0Tessellator.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItemInFirstPerson(float par1) {
/* 264 */     EntityClientPlayerMP player = this.mc.thePlayer;
/*     */     
/* 266 */     if (this.itemToRender != null && this.itemToRender.getItem() instanceof ItemFishingRod && player.zoomed) {
/*     */       return;
/*     */     }
/* 269 */     if (player.ticksExisted < 1) {
/*     */       
/* 271 */       player.prevRenderArmYaw = player.renderArmYaw = player.rotationYaw;
/* 272 */       player.prevRenderArmPitch = player.renderArmPitch = player.rotationPitch;
/*     */     } 
/*     */     
/* 275 */     if (!this.mc.theWorld.doesChunkAndAllNeighborsExist(player.getChunkPosX(), player.getChunkPosZ(), 0, false)) {
/*     */       return;
/*     */     }
/* 278 */     float var2 = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * par1;
/* 279 */     EntityClientPlayerMP var3 = this.mc.thePlayer;
/* 280 */     float var4 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * par1;
/* 281 */     GL11.glPushMatrix();
/* 282 */     GL11.glRotatef(var4, 1.0F, 0.0F, 0.0F);
/* 283 */     GL11.glRotatef(var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * par1, 0.0F, 1.0F, 0.0F);
/* 284 */     RenderHelper.enableStandardItemLighting();
/* 285 */     GL11.glPopMatrix();
/* 286 */     ClientPlayer var5 = var3;
/* 287 */     float var6 = var5.prevRenderArmPitch + (var5.renderArmPitch - var5.prevRenderArmPitch) * par1;
/* 288 */     float var7 = var5.prevRenderArmYaw + (var5.renderArmYaw - var5.prevRenderArmYaw) * par1;
/* 289 */     GL11.glRotatef((var3.rotationPitch - var6) * 0.1F, 1.0F, 0.0F, 0.0F);
/* 290 */     GL11.glRotatef((var3.rotationYaw - var7) * 0.1F, 0.0F, 1.0F, 0.0F);
/* 291 */     ItemStack var8 = this.itemToRender;
/* 292 */     float var9 = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ));
/* 293 */     var9 = 1.0F;
/* 294 */     int var10 = this.mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ), 0);
/* 295 */     int var11 = var10 % 65536;
/* 296 */     int var12 = var10 / 65536;
/* 297 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var11 / 1.0F, var12 / 1.0F);
/* 298 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     if (var8 != null) {
/*     */       
/* 305 */       var10 = Item.itemsList[var8.itemID].getColorFromItemStack(var8, 0);
/* 306 */       float var20 = (var10 >> 16 & 0xFF) / 255.0F;
/* 307 */       float var22 = (var10 >> 8 & 0xFF) / 255.0F;
/* 308 */       float var13 = (var10 & 0xFF) / 255.0F;
/* 309 */       GL11.glColor4f(var9 * var20, var9 * var22, var9 * var13, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 313 */       GL11.glColor4f(var9, var9, var9, 1.0F);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     if (var8 != null && var8.itemID == Item.map.itemID) {
/*     */       
/* 325 */       GL11.glPushMatrix();
/* 326 */       float var21 = 0.8F;
/* 327 */       float var20 = var3.getSwingProgress(par1);
/* 328 */       float var22 = MathHelper.sin(var20 * 3.1415927F);
/* 329 */       float var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
/* 330 */       GL11.glTranslatef(-var13 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F * 2.0F) * 0.2F, -var22 * 0.2F);
/* 331 */       var20 = 1.0F - var4 / 45.0F + 0.1F;
/*     */       
/* 333 */       if (var20 < 0.0F)
/*     */       {
/* 335 */         var20 = 0.0F;
/*     */       }
/*     */       
/* 338 */       if (var20 > 1.0F)
/*     */       {
/* 340 */         var20 = 1.0F;
/*     */       }
/*     */       
/* 343 */       var20 = -MathHelper.cos(var20 * 3.1415927F) * 0.5F + 0.5F;
/* 344 */       GL11.glTranslatef(0.0F, 0.0F * var21 - (1.0F - var2) * 1.2F - var20 * 0.5F + 0.04F, -0.9F * var21);
/* 345 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 346 */       GL11.glRotatef(var20 * -85.0F, 0.0F, 0.0F, 1.0F);
/* 347 */       GL11.glEnable(32826);
/* 348 */       this.mc.getTextureManager().bindTexture(var3.getLocationSkin());
/*     */       
/* 350 */       for (var12 = 0; var12 < 2; var12++) {
/*     */         
/* 352 */         int var24 = var12 * 2 - 1;
/* 353 */         GL11.glPushMatrix();
/* 354 */         GL11.glTranslatef(-0.0F, -0.6F, 1.1F * var24);
/* 355 */         GL11.glRotatef((-45 * var24), 1.0F, 0.0F, 0.0F);
/* 356 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/* 357 */         GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
/* 358 */         GL11.glRotatef((-65 * var24), 0.0F, 1.0F, 0.0F);
/* 359 */         Render var27 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
/* 360 */         RenderPlayer var26 = (RenderPlayer)var27;
/* 361 */         float f = 1.0F;
/* 362 */         GL11.glScalef(f, f, f);
/* 363 */         var26.renderFirstPersonArm(this.mc.thePlayer);
/* 364 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/* 367 */       var22 = var3.getSwingProgress(par1);
/* 368 */       var13 = MathHelper.sin(var22 * var22 * 3.1415927F);
/* 369 */       float var14 = MathHelper.sin(MathHelper.sqrt_float(var22) * 3.1415927F);
/* 370 */       GL11.glRotatef(-var13 * 20.0F, 0.0F, 1.0F, 0.0F);
/* 371 */       GL11.glRotatef(-var14 * 20.0F, 0.0F, 0.0F, 1.0F);
/* 372 */       GL11.glRotatef(-var14 * 80.0F, 1.0F, 0.0F, 0.0F);
/* 373 */       float var15 = 0.38F;
/* 374 */       GL11.glScalef(var15, var15, var15);
/* 375 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 376 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 377 */       GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
/* 378 */       float var16 = 0.015625F;
/* 379 */       GL11.glScalef(var16, var16, var16);
/* 380 */       this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
/* 381 */       Tessellator var30 = Tessellator.instance;
/* 382 */       GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 383 */       var30.startDrawingQuads();
/* 384 */       byte var29 = 7;
/* 385 */       var30.addVertexWithUV((0 - var29), (128 + var29), 0.0D, 0.0D, 1.0D);
/* 386 */       var30.addVertexWithUV((128 + var29), (128 + var29), 0.0D, 1.0D, 1.0D);
/* 387 */       var30.addVertexWithUV((128 + var29), (0 - var29), 0.0D, 1.0D, 0.0D);
/* 388 */       var30.addVertexWithUV((0 - var29), (0 - var29), 0.0D, 0.0D, 0.0D);
/* 389 */       var30.draw();
/* 390 */       MapData var19 = Item.map.getMapData(var8, this.mc.theWorld);
/*     */       
/* 392 */       if (var19 != null)
/*     */       {
/* 394 */         this.mapItemRenderer.renderMap(this.mc.thePlayer, this.mc.getTextureManager(), var19);
/*     */       }
/*     */       
/* 397 */       GL11.glPopMatrix();
/*     */     }
/* 399 */     else if (var8 != null) {
/*     */       
/* 401 */       GL11.glPushMatrix();
/* 402 */       float var21 = 0.8F;
/*     */       
/* 404 */       if (var3.getItemInUseCount() > 0) {
/*     */ 
/*     */         
/* 407 */         EnumItemInUseAction var23 = var8.getItemInUseAction(this.mc.thePlayer);
/*     */         
/* 409 */         if (var23 == EnumItemInUseAction.EAT || var23 == EnumItemInUseAction.DRINK)
/*     */         {
/* 411 */           float f2 = var3.getItemInUseCount() - par1 + 1.0F;
/* 412 */           float f1 = 1.0F - f2 / var8.getMaxItemUseDuration();
/* 413 */           float f3 = 1.0F - f1;
/* 414 */           f3 = f3 * f3 * f3;
/* 415 */           f3 = f3 * f3 * f3;
/* 416 */           f3 = f3 * f3 * f3;
/* 417 */           float var15 = 1.0F - f3;
/* 418 */           GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(f2 / 4.0F * 3.1415927F) * 0.1F) * ((f1 > 0.2D) ? true : false), 0.0F);
/* 419 */           GL11.glTranslatef(var15 * 0.6F, -var15 * 0.5F, 0.0F);
/* 420 */           GL11.glRotatef(var15 * 90.0F, 0.0F, 1.0F, 0.0F);
/* 421 */           GL11.glRotatef(var15 * 10.0F, 1.0F, 0.0F, 0.0F);
/* 422 */           GL11.glRotatef(var15 * 30.0F, 0.0F, 0.0F, 1.0F);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 427 */         float f2 = var3.getSwingProgress(par1);
/* 428 */         float f3 = MathHelper.sin(f2 * 3.1415927F);
/* 429 */         float f1 = MathHelper.sin(MathHelper.sqrt_float(f2) * 3.1415927F);
/* 430 */         GL11.glTranslatef(-f1 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(f2) * 3.1415927F * 2.0F) * 0.2F, -f3 * 0.2F);
/*     */       } 
/*     */       
/* 433 */       GL11.glTranslatef(0.7F * var21, -0.65F * var21 - (1.0F - var2) * 0.6F, -0.9F * var21);
/* 434 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 435 */       GL11.glEnable(32826);
/* 436 */       float var20 = var3.getSwingProgress(par1);
/* 437 */       float var22 = MathHelper.sin(var20 * var20 * 3.1415927F);
/* 438 */       float var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
/* 439 */       GL11.glRotatef(-var22 * 20.0F, 0.0F, 1.0F, 0.0F);
/* 440 */       GL11.glRotatef(-var13 * 20.0F, 0.0F, 0.0F, 1.0F);
/* 441 */       GL11.glRotatef(-var13 * 80.0F, 1.0F, 0.0F, 0.0F);
/* 442 */       float var14 = 0.4F;
/* 443 */       GL11.glScalef(var14, var14, var14);
/*     */ 
/*     */ 
/*     */       
/* 447 */       if (var3.getItemInUseCount() > 0) {
/*     */ 
/*     */         
/* 450 */         EnumItemInUseAction var25 = var8.getItemInUseAction(this.mc.thePlayer);
/*     */         
/* 452 */         if (var25 == EnumItemInUseAction.BLOCK) {
/*     */           
/* 454 */           GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
/* 455 */           GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
/* 456 */           GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
/* 457 */           GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/*     */         }
/* 459 */         else if (var25 == EnumItemInUseAction.BOW) {
/*     */           
/* 461 */           GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
/* 462 */           GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
/* 463 */           GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
/* 464 */           GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
/* 465 */           float var16 = var8.getMaxItemUseDuration() - var3.getItemInUseCount() - par1 + 1.0F;
/*     */           
/* 467 */           float var17 = var16 / ItemBow.getTicksForMaxPull(var8);
/* 468 */           var17 = (var17 * var17 + var17 * 2.0F) / 3.0F;
/*     */           
/* 470 */           if (var17 > 1.0F)
/*     */           {
/* 472 */             var17 = 1.0F;
/*     */           }
/*     */           
/* 475 */           if (var17 > 0.1F)
/*     */           {
/* 477 */             GL11.glTranslatef(0.0F, MathHelper.sin((var16 - 0.1F) * 1.3F) * 0.01F * (var17 - 0.1F), 0.0F);
/*     */           }
/*     */           
/* 480 */           GL11.glTranslatef(0.0F, 0.0F, var17 * 0.1F);
/* 481 */           GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
/* 482 */           GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
/* 483 */           GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/* 484 */           float var18 = 1.0F + var17 * 0.2F;
/* 485 */           GL11.glScalef(1.0F, 1.0F, var18);
/* 486 */           GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/* 487 */           GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
/* 488 */           GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */       
/* 492 */       if (var8.getItem().shouldRotateAroundWhenRendering())
/*     */       {
/* 494 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       
/* 497 */       if (var8.getItem().requiresMultipleRenderPasses()) {
/*     */         
/* 499 */         renderItem(var3, var8, 0);
/* 500 */         int var28 = Item.itemsList[var8.itemID].getColorFromItemStack(var8, 1);
/* 501 */         float var16 = (var28 >> 16 & 0xFF) / 255.0F;
/* 502 */         float var17 = (var28 >> 8 & 0xFF) / 255.0F;
/* 503 */         float var18 = (var28 & 0xFF) / 255.0F;
/* 504 */         GL11.glColor4f(var9 * var16, var9 * var17, var9 * var18, 1.0F);
/* 505 */         renderItem(var3, var8, 1);
/*     */       }
/*     */       else {
/*     */         
/* 509 */         renderItem(var3, var8, 0);
/*     */       } 
/*     */       
/* 512 */       GL11.glPopMatrix();
/*     */     }
/* 514 */     else if (!var3.isInvisible()) {
/*     */       
/* 516 */       GL11.glPushMatrix();
/* 517 */       float var21 = 0.8F;
/* 518 */       float var20 = var3.getSwingProgress(par1);
/* 519 */       float var22 = MathHelper.sin(var20 * 3.1415927F);
/* 520 */       float var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
/* 521 */       GL11.glTranslatef(-var13 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F * 2.0F) * 0.4F, -var22 * 0.4F);
/* 522 */       GL11.glTranslatef(0.8F * var21, -0.75F * var21 - (1.0F - var2) * 0.6F, -0.9F * var21);
/* 523 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 524 */       GL11.glEnable(32826);
/* 525 */       var20 = var3.getSwingProgress(par1);
/* 526 */       var22 = MathHelper.sin(var20 * var20 * 3.1415927F);
/* 527 */       var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
/* 528 */       GL11.glRotatef(var13 * 70.0F, 0.0F, 1.0F, 0.0F);
/* 529 */       GL11.glRotatef(-var22 * 20.0F, 0.0F, 0.0F, 1.0F);
/* 530 */       this.mc.getTextureManager().bindTexture(var3.getLocationSkin());
/* 531 */       GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
/* 532 */       GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
/* 533 */       GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
/* 534 */       GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/* 535 */       GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 536 */       GL11.glTranslatef(5.6F, 0.0F, 0.0F);
/* 537 */       Render var27 = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
/* 538 */       RenderPlayer var26 = (RenderPlayer)var27;
/* 539 */       float var16 = 1.0F;
/* 540 */       GL11.glScalef(var16, var16, var16);
/* 541 */       var26.renderFirstPersonArm(this.mc.thePlayer);
/* 542 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 545 */     GL11.glDisable(32826);
/* 546 */     RenderHelper.disableStandardItemLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderOverlays(float par1) {
/* 554 */     GL11.glDisable(3008);
/*     */     
/* 556 */     if (this.mc.thePlayer.isBurning())
/*     */     {
/* 558 */       renderFireInFirstPerson(par1);
/*     */     }
/*     */     
/* 561 */     if (this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
/*     */       
/* 563 */       int var2 = MathHelper.floor_double(this.mc.thePlayer.posX);
/* 564 */       int var3 = MathHelper.floor_double(this.mc.thePlayer.posY);
/* 565 */       int var4 = MathHelper.floor_double(this.mc.thePlayer.posZ);
/* 566 */       int var5 = this.mc.theWorld.getBlockId(var2, var3, var4);
/*     */       
/* 568 */       if (this.mc.theWorld.isBlockNormalCube(var2, var3, var4)) {
/*     */         
/* 570 */         renderInsideOfBlock(par1, Block.blocksList[var5].getBlockTextureFromSide(2));
/*     */       }
/*     */       else {
/*     */         
/* 574 */         for (int var6 = 0; var6 < 8; var6++) {
/*     */           
/* 576 */           float var7 = (((var6 >> 0) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
/* 577 */           float var8 = (((var6 >> 1) % 2) - 0.5F) * this.mc.thePlayer.height * 0.2F;
/* 578 */           float var9 = (((var6 >> 2) % 2) - 0.5F) * this.mc.thePlayer.width * 0.9F;
/* 579 */           int var10 = MathHelper.floor_float(var2 + var7);
/* 580 */           int var11 = MathHelper.floor_float(var3 + var8);
/* 581 */           int var12 = MathHelper.floor_float(var4 + var9);
/*     */           
/* 583 */           if (this.mc.theWorld.isBlockNormalCube(var10, var11, var12))
/*     */           {
/* 585 */             var5 = this.mc.theWorld.getBlockId(var10, var11, var12);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 590 */       if (Block.blocksList[var5] != null)
/*     */       {
/* 592 */         renderInsideOfBlock(par1, Block.blocksList[var5].getBlockTextureFromSide(2));
/*     */       }
/*     */     } 
/*     */     
/* 596 */     if (this.mc.thePlayer.isInsideOfMaterial(Material.water))
/*     */     {
/* 598 */       renderWarpedTextureOverlay(par1);
/*     */     }
/*     */     
/* 601 */     GL11.glEnable(3008);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderInsideOfBlock(float par1, Icon par2Icon) {
/* 609 */     this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 610 */     Tessellator var3 = Tessellator.instance;
/* 611 */     float var4 = 0.1F;
/* 612 */     GL11.glColor4f(var4, var4, var4, 0.5F);
/* 613 */     GL11.glPushMatrix();
/* 614 */     float var5 = -1.0F;
/* 615 */     float var6 = 1.0F;
/* 616 */     float var7 = -1.0F;
/* 617 */     float var8 = 1.0F;
/* 618 */     float var9 = -0.5F;
/* 619 */     float var10 = par2Icon.getMinU();
/* 620 */     float var11 = par2Icon.getMaxU();
/* 621 */     float var12 = par2Icon.getMinV();
/* 622 */     float var13 = par2Icon.getMaxV();
/* 623 */     var3.startDrawingQuads();
/* 624 */     var3.addVertexWithUV(var5, var7, var9, var11, var13);
/* 625 */     var3.addVertexWithUV(var6, var7, var9, var10, var13);
/* 626 */     var3.addVertexWithUV(var6, var8, var9, var10, var12);
/* 627 */     var3.addVertexWithUV(var5, var8, var9, var11, var12);
/* 628 */     var3.draw();
/* 629 */     GL11.glPopMatrix();
/* 630 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderWarpedTextureOverlay(float par1) {
/* 639 */     this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
/* 640 */     Tessellator var2 = Tessellator.instance;
/* 641 */     float var3 = this.mc.thePlayer.getBrightness(par1);
/* 642 */     GL11.glColor4f(var3, var3, var3, 0.5F);
/* 643 */     GL11.glEnable(3042);
/* 644 */     GL11.glBlendFunc(770, 771);
/* 645 */     GL11.glPushMatrix();
/* 646 */     float var4 = 4.0F;
/* 647 */     float var5 = -1.0F;
/* 648 */     float var6 = 1.0F;
/* 649 */     float var7 = -1.0F;
/* 650 */     float var8 = 1.0F;
/* 651 */     float var9 = -0.5F;
/* 652 */     float var10 = -this.mc.thePlayer.rotationYaw / 64.0F;
/* 653 */     float var11 = this.mc.thePlayer.rotationPitch / 64.0F;
/* 654 */     var2.startDrawingQuads();
/* 655 */     var2.addVertexWithUV(var5, var7, var9, (var4 + var10), (var4 + var11));
/* 656 */     var2.addVertexWithUV(var6, var7, var9, (0.0F + var10), (var4 + var11));
/* 657 */     var2.addVertexWithUV(var6, var8, var9, (0.0F + var10), (0.0F + var11));
/* 658 */     var2.addVertexWithUV(var5, var8, var9, (var4 + var10), (0.0F + var11));
/* 659 */     var2.draw();
/* 660 */     GL11.glPopMatrix();
/* 661 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 662 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFireInFirstPerson(float par1) {
/* 670 */     Tessellator var2 = Tessellator.instance;
/* 671 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
/* 672 */     GL11.glEnable(3042);
/* 673 */     GL11.glBlendFunc(770, 771);
/* 674 */     float var3 = 1.0F;
/*     */     
/* 676 */     for (int var4 = 0; var4 < 2; var4++) {
/*     */       
/* 678 */       GL11.glPushMatrix();
/* 679 */       Icon var5 = Block.fire.getFireIcon(1);
/* 680 */       this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 681 */       float var6 = var5.getMinU();
/* 682 */       float var7 = var5.getMaxU();
/* 683 */       float var8 = var5.getMinV();
/* 684 */       float var9 = var5.getMaxV();
/* 685 */       float var10 = (0.0F - var3) / 2.0F;
/* 686 */       float var11 = var10 + var3;
/* 687 */       float var12 = 0.0F - var3 / 2.0F;
/* 688 */       float var13 = var12 + var3;
/* 689 */       float var14 = -0.5F;
/* 690 */       GL11.glTranslatef(-(var4 * 2 - 1) * 0.24F, -0.3F, 0.0F);
/* 691 */       GL11.glRotatef((var4 * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
/* 692 */       var2.startDrawingQuads();
/* 693 */       var2.addVertexWithUV(var10, var12, var14, var7, var9);
/* 694 */       var2.addVertexWithUV(var11, var12, var14, var6, var9);
/* 695 */       var2.addVertexWithUV(var11, var13, var14, var6, var8);
/* 696 */       var2.addVertexWithUV(var10, var13, var14, var7, var8);
/* 697 */       var2.draw();
/* 698 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 701 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 702 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEquippedItem() {
/* 707 */     if (this.mc.thePlayer.fishEntity != null) {
/* 708 */       this.equippedProgress = 1.0F;
/*     */     }
/* 710 */     this.prevEquippedProgress = this.equippedProgress;
/* 711 */     EntityClientPlayerMP var1 = this.mc.thePlayer;
/* 712 */     ItemStack var2 = var1.inventory.getCurrentItemStack();
/* 713 */     boolean var3 = (this.equippedItemSlot == var1.inventory.currentItem && var2 == this.itemToRender);
/*     */     
/* 715 */     if (this.itemToRender == null && var2 == null)
/*     */     {
/* 717 */       var3 = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 722 */     if (var2 != null && this.itemToRender != null && var2 != this.itemToRender && var2.itemID == this.itemToRender.itemID && (!var2.getHasSubtypes() || var2.getItemSubtype() == this.itemToRender.getItemSubtype()) && !this.mc.thePlayer.change_rendering_for_item_equipping) {
/*     */       
/* 724 */       this.itemToRender = var2;
/* 725 */       var3 = true;
/*     */     } 
/*     */     
/* 728 */     float var4 = 0.4F;
/* 729 */     float var5 = var3 ? 1.0F : 0.0F;
/* 730 */     float var6 = var5 - this.equippedProgress;
/*     */     
/* 732 */     if (var6 < -var4)
/*     */     {
/* 734 */       var6 = -var4;
/*     */     }
/*     */     
/* 737 */     if (var6 > var4)
/*     */     {
/* 739 */       var6 = var4;
/*     */     }
/*     */     
/* 742 */     this.equippedProgress += var6;
/*     */     
/* 744 */     if (this.mc.thePlayer.fishEntity != null) {
/* 745 */       this.equippedProgress = 1.0F;
/*     */     }
/* 747 */     if (this.equippedProgress < 0.1F) {
/*     */       
/* 749 */       this.itemToRender = var2;
/* 750 */       this.equippedItemSlot = var1.inventory.currentItem;
/*     */       
/* 752 */       this.mc.thePlayer.change_rendering_for_item_equipping = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetEquippedProgress() {
/* 763 */     this.equippedProgress = 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ItemRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */