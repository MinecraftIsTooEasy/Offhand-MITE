/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public abstract class Render
/*     */ {
/*   7 */   private static final ResourceLocation shadowTextures = new ResourceLocation("textures/misc/shadow.png");
/*     */   protected RenderManager renderManager;
/*   9 */   protected RenderBlocks renderBlocks = new RenderBlocks();
/*     */ 
/*     */   
/*     */   protected float shadowSize;
/*     */ 
/*     */   
/*  15 */   protected float shadowOpaque = 1.0F;
/*     */ 
/*     */ 
/*     */   
/*  19 */   double[] x = new double[4];
/*  20 */   double[] y = new double[4];
/*  21 */   double[] z = new double[4];
/*  22 */   double[] u = new double[4];
/*  23 */   double[] v = new double[4];
/*  24 */   float[] r = new float[4];
/*  25 */   float[] g = new float[4];
/*  26 */   float[] b = new float[4];
/*  27 */   int[] brightness = new int[4];
/*     */ 
/*     */   
/*     */   protected boolean use_glowing_texture;
/*     */   
/*  32 */   protected ResourceLocation[] textures = new ResourceLocation[16];
/*  33 */   protected ResourceLocation[] textures_glowing = new ResourceLocation[16];
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
/*     */   protected void setTexture(int index, String path) {
/*  55 */     setTexture(index, path, path);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTexture(int index, String path, String glow_path) {
/*  60 */     if (this.textures[index] != null) {
/*     */       
/*  62 */       Minecraft.setErrorMessage("setTexture: texture [" + index + "] has already been set for " + this);
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     ResourceLocation texture = new ResourceLocation(path + ".png");
/*     */     
/*  68 */     this.textures[index] = texture;
/*     */     
/*  70 */     ResourceLocation texture_glowing = new ResourceLocation(glow_path + "_glow.png", false);
/*     */     
/*  72 */     if (Minecraft.MITE_resource_pack != null && Minecraft.MITE_resource_pack.resourceExists(texture_glowing)) {
/*  73 */       this.textures_glowing[index] = texture_glowing;
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
/*     */   public abstract void doRender(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract ResourceLocation getEntityTexture(Entity paramEntity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void bindEntityTexture(Entity par1Entity) {
/* 104 */     bindTexture(getEntityTexture(par1Entity));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void bindTexture(ResourceLocation par1ResourceLocation) {
/* 109 */     this.renderManager.renderEngine.bindTexture(par1ResourceLocation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderEntityOnFire(Entity par1Entity, double par2, double par4, double par6, float par8) {
/* 117 */     GL11.glDisable(2896);
/* 118 */     Icon var9 = Block.fire.getFireIcon(0);
/* 119 */     Icon var10 = Block.fire.getFireIcon(1);
/* 120 */     GL11.glPushMatrix();
/* 121 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 122 */     float var11 = par1Entity.width * 1.4F;
/* 123 */     GL11.glScalef(var11, var11, var11);
/* 124 */     Tessellator var12 = Tessellator.instance;
/* 125 */     float var13 = 0.5F;
/* 126 */     float var14 = 0.0F;
/* 127 */     float var15 = par1Entity.height / var11;
/* 128 */     float var16 = (float)(par1Entity.posY - par1Entity.boundingBox.minY);
/* 129 */     GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 130 */     GL11.glTranslatef(0.0F, 0.0F, -0.3F + (int)var15 * 0.02F);
/* 131 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 132 */     float var17 = 0.0F;
/* 133 */     int var18 = 0;
/* 134 */     var12.startDrawingQuads();
/*     */     
/* 136 */     while (var15 > 0.0F) {
/*     */       
/* 138 */       Icon var19 = (var18 % 2 == 0) ? var9 : var10;
/* 139 */       bindTexture(TextureMap.locationBlocksTexture);
/* 140 */       float var20 = var19.getMinU();
/* 141 */       float var21 = var19.getMinV();
/* 142 */       float var22 = var19.getMaxU();
/* 143 */       float var23 = var19.getMaxV();
/*     */       
/* 145 */       if (var18 / 2 % 2 == 0) {
/*     */         
/* 147 */         float var24 = var22;
/* 148 */         var22 = var20;
/* 149 */         var20 = var24;
/*     */       } 
/*     */       
/* 152 */       var12.addVertexWithUV((var13 - var14), (0.0F - var16), var17, var22, var23);
/* 153 */       var12.addVertexWithUV((-var13 - var14), (0.0F - var16), var17, var20, var23);
/* 154 */       var12.addVertexWithUV((-var13 - var14), (1.4F - var16), var17, var20, var21);
/* 155 */       var12.addVertexWithUV((var13 - var14), (1.4F - var16), var17, var22, var21);
/* 156 */       var15 -= 0.45F;
/* 157 */       var16 -= 0.45F;
/* 158 */       var13 *= 0.9F;
/* 159 */       var17 += 0.03F;
/* 160 */       var18++;
/*     */     } 
/*     */     
/* 163 */     var12.draw();
/* 164 */     GL11.glPopMatrix();
/* 165 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderShadow(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 174 */     if (par1Entity.disable_shadow) {
/*     */       return;
/*     */     }
/* 177 */     par4 -= par1Entity.yOffset;
/*     */     
/* 179 */     GL11.glEnable(3042);
/* 180 */     GL11.glBlendFunc(770, 771);
/* 181 */     this.renderManager.renderEngine.bindTexture(shadowTextures);
/* 182 */     World var10 = getWorldFromRenderManager();
/* 183 */     GL11.glDepthMask(false);
/* 184 */     float var11 = this.shadowSize;
/*     */     
/* 186 */     if (par1Entity instanceof EntityLiving) {
/*     */       
/* 188 */       EntityLiving var12 = (EntityLiving)par1Entity;
/* 189 */       var11 *= var12.getRenderSizeModifier();
/*     */       
/* 191 */       if (var12.isChild())
/*     */       {
/* 193 */         var11 *= 0.5F;
/*     */       }
/*     */     } 
/*     */     
/* 197 */     double var35 = par1Entity.lastTickPosX + (par1Entity.posX - par1Entity.lastTickPosX) * par9;
/* 198 */     double var14 = par1Entity.lastTickPosY + (par1Entity.posY - par1Entity.lastTickPosY) * par9 + par1Entity.getShadowSize();
/* 199 */     var14 -= par1Entity.yOffset;
/* 200 */     double var16 = par1Entity.lastTickPosZ + (par1Entity.posZ - par1Entity.lastTickPosZ) * par9;
/* 201 */     int var18 = MathHelper.floor_double(var35 - var11);
/* 202 */     int var19 = MathHelper.floor_double(var35 + var11);
/*     */ 
/*     */ 
/*     */     
/* 206 */     int var22 = MathHelper.floor_double(var16 - var11);
/* 207 */     int var23 = MathHelper.floor_double(var16 + var11);
/* 208 */     double var24 = par2 - var35;
/* 209 */     double var26 = par4 - var14;
/* 210 */     double var28 = par6 - var16;
/* 211 */     Tessellator var30 = Tessellator.instance;
/* 212 */     var30.startDrawingQuads();
/*     */     
/* 214 */     float shadow_size = par1Entity.getShadowSize();
/* 215 */     float object_opacity = this.renderManager.getEntityRenderObject(par1Entity).getModelOpacity(par1Entity);
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
/* 237 */     GL11.glAlphaFunc(516, 0.001F);
/*     */     
/* 239 */     for (int x = var18; x <= var19; x++) {
/*     */       
/* 241 */       for (int z = var22; z <= var23; z++) {
/* 242 */         renderShadowOnBlockMITE(par2, par4 + shadow_size, par6, x, z, par8, var11, var24, var26 + shadow_size, var28, object_opacity, par1Entity);
/*     */       }
/*     */     } 
/* 245 */     var30.draw();
/* 246 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 247 */     GL11.glDisable(3042);
/* 248 */     GL11.glDepthMask(true);
/*     */     
/* 250 */     GL11.glAlphaFunc(516, 0.1F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private World getWorldFromRenderManager() {
/* 258 */     return this.renderManager.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderShadowOnBlock(Block par1Block, double par2, double par4, double par6, int par8, int par9, int par10, float par11, float par12, double par13, double par15, double par17, float opacity_of_object, Entity entity) {
/* 269 */     Minecraft.setErrorMessage("renderShadowOnBlock: This function is no longer in service");
/*     */ 
/*     */ 
/*     */     
/* 273 */     GL11.glAlphaFunc(516, 0.01F);
/*     */     
/* 275 */     Tessellator var19 = Tessellator.instance;
/*     */     
/* 277 */     if (par1Block.renderAsNormalBlock()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 284 */       double entity_min_y = entity.isEntityLivingBase() ? entity.getAsEntityLivingBase().getFootPosY() : entity.posY;
/*     */       
/* 286 */       double block_top_y = getWorldFromRenderManager().getBlockRenderTopY(par8, par9, par10);
/*     */ 
/*     */       
/* 289 */       double var20 = par11 * (1.0D - Math.abs(entity_min_y - block_top_y) * 0.75D) * 0.25D * getWorldFromRenderManager().getLightBrightness(par8, par9, par10);
/*     */ 
/*     */ 
/*     */       
/* 293 */       if (var20 >= 0.0D) {
/*     */         
/* 295 */         if (var20 > 1.0D)
/*     */         {
/* 297 */           var20 = 1.0D;
/*     */         }
/*     */ 
/*     */         
/* 301 */         var19.setColorRGBA_F(1.0F, 1.0F, 1.0F, (float)var20 * opacity_of_object);
/*     */ 
/*     */ 
/*     */         
/* 305 */         int index = Minecraft.getThreadIndex();
/*     */         
/* 307 */         double var22 = par8 + par1Block.getBlockBoundsMinX(index) + par13;
/* 308 */         double var24 = par8 + par1Block.getBlockBoundsMaxX(index) + par13;
/*     */         
/* 310 */         double var28 = par10 + par1Block.getBlockBoundsMinZ(index) + par17;
/* 311 */         double var30 = par10 + par1Block.getBlockBoundsMaxZ(index) + par17;
/* 312 */         float var32 = (float)((par2 - var22) / 2.0D / par12 + 0.5D);
/* 313 */         float var33 = (float)((par2 - var24) / 2.0D / par12 + 0.5D);
/* 314 */         float var34 = (float)((par6 - var28) / 2.0D / par12 + 0.5D);
/* 315 */         float var35 = (float)((par6 - var30) / 2.0D / par12 + 0.5D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 323 */         double var26 = getWorldFromRenderManager().getBlockRenderTopY(par8, par9, par10) + par15 + 5.000000237487257E-4D;
/*     */         
/* 325 */         if (RenderingScheme.current == 0) {
/*     */           
/* 327 */           var19.addVertexWithUV(var22, var26, var28, var32, var34);
/* 328 */           var19.addVertexWithUV(var22, var26, var30, var32, var35);
/* 329 */           var19.addVertexWithUV(var24, var26, var30, var33, var35);
/* 330 */           var19.addVertexWithUV(var24, var26, var28, var33, var34);
/*     */         }
/*     */         else {
/*     */           
/* 334 */           this.x[0] = var22;
/* 335 */           this.y[0] = var26;
/* 336 */           this.z[0] = var28;
/* 337 */           this.u[0] = var32;
/* 338 */           this.v[0] = var34;
/*     */           
/* 340 */           this.x[1] = var22;
/* 341 */           this.y[1] = var26;
/* 342 */           this.z[1] = var30;
/* 343 */           this.u[1] = var32;
/* 344 */           this.v[1] = var35;
/*     */           
/* 346 */           this.x[2] = var24;
/* 347 */           this.y[2] = var26;
/* 348 */           this.z[2] = var30;
/* 349 */           this.u[2] = var33;
/* 350 */           this.v[2] = var35;
/*     */           
/* 352 */           this.x[3] = var24;
/* 353 */           this.y[3] = var26;
/* 354 */           this.z[3] = var28;
/* 355 */           this.u[3] = var33;
/* 356 */           this.v[3] = var34;
/*     */           
/* 358 */           var19.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float applyShadowAttenuationForHeightDifference(float input_alpha, Entity entity, double entity_min_y, double block_top_y) {
/*     */     double attenuation;
/* 371 */     if (entity_min_y < block_top_y) {
/* 372 */       attenuation = (block_top_y - entity_min_y) / ((entity.getCenterPoint()).yCoord - entity_min_y);
/*     */     } else {
/* 374 */       attenuation = (entity_min_y - block_top_y) * 0.75D;
/*     */     } 
/* 376 */     return input_alpha * (1.0F - MathHelper.clamp_float((float)attenuation, 0.0F, 1.0F));
/*     */   }
/*     */   private void renderShadowOnBlockMITE(double par2, double par4, double par6, int block_x, int block_z, float par11, float par12, double par13, double par15, double par17, float opacity_of_object, Entity entity) {
/*     */     Block block;
/*     */     int metadata;
/* 381 */     World world = getWorldFromRenderManager();
/*     */     
/* 383 */     int block_y = MathHelper.floor_double((entity.getCenterPoint()).yCoord);
/*     */     
/* 385 */     double entity_min_y = entity.isEntityLivingBase() ? entity.getAsEntityLivingBase().getFootPosY() : entity.posY;
/*     */     
/* 387 */     int min_y = MathHelper.floor_double(entity_min_y) - 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 394 */       block = world.getBlock(block_x, block_y, block_z);
/*     */       
/* 396 */       if (block != null && block.canSupportEntityShadow(metadata = world.getBlockMetadata(block_x, block_y, block_z))) {
/*     */         break;
/*     */       }
/* 399 */       if (--block_y < min_y) {
/*     */         return;
/*     */       }
/*     */     } 
/* 403 */     if (block == null) {
/*     */       return;
/*     */     }
/* 406 */     if (world.getBlockLightValue(block_x, block_y + 1, block_z) < 4) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 411 */     Tessellator var19 = Tessellator.instance;
/*     */     
/* 413 */     double block_top_y = world.getBlockRenderTopY(block_x, block_y, block_z);
/*     */     
/* 415 */     if (block instanceof BlockStairs && !block.isTopFlatAndSolid(metadata)) {
/*     */       
/* 417 */       renderShadowOnTopOfStairs(par2, par4, par6, block_x, block_y, block_z, par11, par12, par13, par15, par17, opacity_of_object, entity_min_y, entity);
/*     */       
/* 419 */       block_top_y -= 0.5D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 429 */     double var20 = applyShadowAttenuationForHeightDifference(par11, entity, entity_min_y, block_top_y) * 0.25D * world.getLightBrightness(block_x, block_y + 1, block_z);
/*     */     
/* 431 */     if (var20 >= 0.0D) {
/*     */       
/* 433 */       if (var20 > 1.0D)
/*     */       {
/* 435 */         var20 = 1.0D;
/*     */       }
/*     */       
/* 438 */       var19.setColorRGBA_F(1.0F, 1.0F, 1.0F, (float)var20 * opacity_of_object);
/*     */       
/* 440 */       int index = Minecraft.getThreadIndex();
/*     */       
/* 442 */       double var22 = block_x + block.getBlockBoundsMinX(index) + par13;
/* 443 */       double var24 = block_x + block.getBlockBoundsMaxX(index) + par13;
/* 444 */       double var26 = block_top_y + par15 + 5.000000237487257E-4D;
/*     */       
/* 446 */       double var28 = block_z + block.getBlockBoundsMinZ(index) + par17;
/* 447 */       double var30 = block_z + block.getBlockBoundsMaxZ(index) + par17;
/*     */       
/* 449 */       if (block instanceof BlockStairs) {
/*     */         
/* 451 */         var22 = block_x + par13;
/* 452 */         var24 = (block_x + 1.0F) + par13;
/*     */         
/* 454 */         var28 = block_z + par17;
/* 455 */         var30 = (block_z + 1.0F) + par17;
/*     */       } 
/*     */       
/* 458 */       float var32 = (float)((par2 - var22) / 2.0D / par12 + 0.5D);
/* 459 */       float var33 = (float)((par2 - var24) / 2.0D / par12 + 0.5D);
/* 460 */       float var34 = (float)((par6 - var28) / 2.0D / par12 + 0.5D);
/* 461 */       float var35 = (float)((par6 - var30) / 2.0D / par12 + 0.5D);
/*     */       
/* 463 */       if (RenderingScheme.current == 0) {
/*     */         
/* 465 */         var19.addVertexWithUV(var22, var26, var28, var32, var34);
/* 466 */         var19.addVertexWithUV(var22, var26, var30, var32, var35);
/* 467 */         var19.addVertexWithUV(var24, var26, var30, var33, var35);
/* 468 */         var19.addVertexWithUV(var24, var26, var28, var33, var34);
/*     */       }
/*     */       else {
/*     */         
/* 472 */         this.x[0] = var22;
/* 473 */         this.y[0] = var26;
/* 474 */         this.z[0] = var28;
/* 475 */         this.u[0] = var32;
/* 476 */         this.v[0] = var34;
/*     */         
/* 478 */         this.x[1] = var22;
/* 479 */         this.y[1] = var26;
/* 480 */         this.z[1] = var30;
/* 481 */         this.u[1] = var32;
/* 482 */         this.v[1] = var35;
/*     */         
/* 484 */         this.x[2] = var24;
/* 485 */         this.y[2] = var26;
/* 486 */         this.z[2] = var30;
/* 487 */         this.u[2] = var33;
/* 488 */         this.v[2] = var35;
/*     */         
/* 490 */         this.x[3] = var24;
/* 491 */         this.y[3] = var26;
/* 492 */         this.z[3] = var28;
/* 493 */         this.u[3] = var33;
/* 494 */         this.v[3] = var34;
/*     */         
/* 496 */         var19.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderShadowOnTopOfStairs(double par2, double par4, double par6, int block_x, int block_y, int block_z, float par11, float par12, double par13, double par15, double par17, float opacity_of_object, double entity_min_y, Entity entity) {
/* 504 */     World world = getWorldFromRenderManager();
/*     */     
/* 506 */     BlockStairs block = (BlockStairs)world.getBlock(block_x, block_y, block_z);
/* 507 */     int metadata = world.getBlockMetadata(block_x, block_y, block_z);
/*     */     
/* 509 */     Tessellator var19 = Tessellator.instance;
/*     */     
/* 511 */     double block_top_y = world.getBlockRenderTopY(block_x, block_y, block_z);
/*     */ 
/*     */     
/* 514 */     double var20 = applyShadowAttenuationForHeightDifference(par11, entity, entity_min_y, block_top_y) * 0.25D * world.getLightBrightness(block_x, block_y + 1, block_z);
/*     */     
/* 516 */     if (var20 >= 0.0D) {
/*     */       
/* 518 */       if (var20 > 1.0D)
/*     */       {
/* 520 */         var20 = 1.0D;
/*     */       }
/*     */       
/* 523 */       var19.setColorRGBA_F(1.0F, 1.0F, 1.0F, (float)var20 * opacity_of_object);
/*     */       
/* 525 */       int index = Minecraft.getThreadIndex();
/*     */       
/* 527 */       double var26 = block_top_y + par15 + 5.000000237487257E-4D;
/*     */       
/* 529 */       for (int i = 0; i < 2; i++) {
/*     */         
/* 531 */         if (i == 0) {
/*     */           
/* 533 */           if (!block.func_82542_g(world, block_x, block_y, block_z)) {
/* 534 */             i = 1;
/*     */           
/*     */           }
/*     */         }
/* 538 */         else if (!block.func_82544_h(world, block_x, block_y, block_z)) {
/*     */           break;
/*     */         } 
/*     */         
/* 542 */         double var22 = block_x + block.getBlockBoundsMinX(index) + par13;
/* 543 */         double var24 = block_x + block.getBlockBoundsMaxX(index) + par13;
/*     */         
/* 545 */         double var28 = block_z + block.getBlockBoundsMinZ(index) + par17;
/* 546 */         double var30 = block_z + block.getBlockBoundsMaxZ(index) + par17;
/*     */         
/* 548 */         float var32 = (float)((par2 - var22) / 2.0D / par12 + 0.5D);
/* 549 */         float var33 = (float)((par2 - var24) / 2.0D / par12 + 0.5D);
/* 550 */         float var34 = (float)((par6 - var28) / 2.0D / par12 + 0.5D);
/* 551 */         float var35 = (float)((par6 - var30) / 2.0D / par12 + 0.5D);
/*     */         
/* 553 */         if (RenderingScheme.current == 0) {
/*     */           
/* 555 */           var19.addVertexWithUV(var22, var26, var28, var32, var34);
/* 556 */           var19.addVertexWithUV(var22, var26, var30, var32, var35);
/* 557 */           var19.addVertexWithUV(var24, var26, var30, var33, var35);
/* 558 */           var19.addVertexWithUV(var24, var26, var28, var33, var34);
/*     */         }
/*     */         else {
/*     */           
/* 562 */           this.x[0] = var22;
/* 563 */           this.y[0] = var26;
/* 564 */           this.z[0] = var28;
/* 565 */           this.u[0] = var32;
/* 566 */           this.v[0] = var34;
/*     */           
/* 568 */           this.x[1] = var22;
/* 569 */           this.y[1] = var26;
/* 570 */           this.z[1] = var30;
/* 571 */           this.u[1] = var32;
/* 572 */           this.v[1] = var35;
/*     */           
/* 574 */           this.x[2] = var24;
/* 575 */           this.y[2] = var26;
/* 576 */           this.z[2] = var30;
/* 577 */           this.u[2] = var33;
/* 578 */           this.v[2] = var35;
/*     */           
/* 580 */           this.x[3] = var24;
/* 581 */           this.y[3] = var26;
/* 582 */           this.z[3] = var28;
/* 583 */           this.u[3] = var33;
/* 584 */           this.v[3] = var34;
/*     */           
/* 586 */           var19.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderOffsetAABB(AxisAlignedBB par0AxisAlignedBB, double par1, double par3, double par5) {
/* 597 */     GL11.glDisable(3553);
/* 598 */     Tessellator var7 = Tessellator.instance;
/* 599 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 600 */     var7.startDrawingQuads();
/* 601 */     var7.setTranslation(par1, par3, par5);
/* 602 */     var7.setNormal(0.0F, 0.0F, -1.0F);
/* 603 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 604 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 605 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 606 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 607 */     var7.setNormal(0.0F, 0.0F, 1.0F);
/* 608 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 609 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 610 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 611 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 612 */     var7.setNormal(0.0F, -1.0F, 0.0F);
/* 613 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 614 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 615 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 616 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 617 */     var7.setNormal(0.0F, 1.0F, 0.0F);
/* 618 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 619 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 620 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 621 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 622 */     var7.setNormal(-1.0F, 0.0F, 0.0F);
/* 623 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 624 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 625 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 626 */     var7.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 627 */     var7.setNormal(1.0F, 0.0F, 0.0F);
/* 628 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 629 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 630 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 631 */     var7.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 632 */     var7.setTranslation(0.0D, 0.0D, 0.0D);
/* 633 */     var7.draw();
/* 634 */     GL11.glEnable(3553);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderAABB(AxisAlignedBB par0AxisAlignedBB) {
/* 642 */     Tessellator var1 = Tessellator.instance;
/* 643 */     var1.startDrawingQuads();
/* 644 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 645 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 646 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 647 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 648 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 649 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 650 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 651 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 652 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 653 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 654 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 655 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 656 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 657 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 658 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 659 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 660 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 661 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 662 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 663 */     var1.addVertex(par0AxisAlignedBB.minX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 664 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.minZ);
/* 665 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.minZ);
/* 666 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.maxY, par0AxisAlignedBB.maxZ);
/* 667 */     var1.addVertex(par0AxisAlignedBB.maxX, par0AxisAlignedBB.minY, par0AxisAlignedBB.maxZ);
/* 668 */     var1.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderManager(RenderManager par1RenderManager) {
/* 676 */     this.renderManager = par1RenderManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderShadowAndFire(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 684 */     int death_time = (par1Entity instanceof EntityLivingBase) ? ((EntityLivingBase)par1Entity).deathTime : 0;
/*     */ 
/*     */     
/* 687 */     if (this.renderManager.options.isFancyGraphicsEnabled() && this.shadowSize > 0.0F && !par1Entity.isInvisible() && death_time < 10) {
/*     */       
/* 689 */       double var10 = this.renderManager.getDistanceToCamera(par1Entity.posX, par1Entity.posY, par1Entity.posZ);
/* 690 */       float var12 = (float)((1.0D - var10 / 256.0D) * this.shadowOpaque);
/*     */       
/* 692 */       if (var12 > 0.0F)
/*     */       {
/* 694 */         renderShadow(par1Entity, par2, par4, par6, var12, par9);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 699 */     if (par1Entity.canRenderOnFire() && death_time < 5)
/*     */     {
/* 701 */       renderEntityOnFire(par1Entity, par2, par4, par6, par9);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderer getFontRendererFromRenderManager() {
/* 710 */     return this.renderManager.getFontRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateIcons(IconRegister par1IconRegister) {}
/*     */   
/*     */   public float getModelOpacity(Entity entity) {
/* 717 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getGlowingTextureCounterpart(ResourceLocation texture) {
/* 726 */     for (int i = 0; i < this.textures.length; i++) {
/*     */       
/* 728 */       if (this.textures[i] == texture) {
/* 729 */         return this.textures_glowing[i];
/*     */       }
/*     */     } 
/* 732 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Render.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */