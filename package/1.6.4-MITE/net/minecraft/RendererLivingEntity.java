/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public abstract class RendererLivingEntity
/*     */   extends Render
/*     */ {
/*  10 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*     */   
/*     */   protected ModelBase mainModel;
/*     */   
/*     */   protected ModelBase renderPassModel;
/*     */ 
/*     */   
/*     */   public RendererLivingEntity(ModelBase par1ModelBase, float par2) {
/*  18 */     this.mainModel = par1ModelBase;
/*  19 */     this.shadowSize = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderPassModel(ModelBase par1ModelBase) {
/*  28 */     this.renderPassModel = par1ModelBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float interpolateRotation(float par1, float par2, float par3) {
/*     */     float var4;
/*  40 */     for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     while (var4 >= 180.0F)
/*     */     {
/*  47 */       var4 -= 360.0F;
/*     */     }
/*     */     
/*  50 */     return par1 + par3 * var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/*  55 */     GL11.glPushMatrix();
/*     */ 
/*     */     
/*  58 */     if (par1EntityLivingBase.drawBackFaces()) {
/*  59 */       GL11.glDisable(2884);
/*     */     } else {
/*  61 */       GL11.glEnable(2884);
/*     */     } 
/*  63 */     this.mainModel.onGround = renderSwingProgress(par1EntityLivingBase, par9);
/*     */     
/*  65 */     if (this.renderPassModel != null)
/*     */     {
/*  67 */       this.renderPassModel.onGround = this.mainModel.onGround;
/*     */     }
/*     */     
/*  70 */     this.mainModel.isRiding = par1EntityLivingBase.isRiding();
/*     */     
/*  72 */     if (this.renderPassModel != null)
/*     */     {
/*  74 */       this.renderPassModel.isRiding = this.mainModel.isRiding;
/*     */     }
/*     */     
/*  77 */     this.mainModel.isChild = par1EntityLivingBase.isChild();
/*     */     
/*  79 */     if (this.renderPassModel != null)
/*     */     {
/*  81 */       this.renderPassModel.isChild = this.mainModel.isChild;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  86 */       float var10 = interpolateRotation(par1EntityLivingBase.prevRenderYawOffset, par1EntityLivingBase.renderYawOffset, par9);
/*  87 */       float var11 = interpolateRotation(par1EntityLivingBase.prevRotationYawHead, par1EntityLivingBase.rotationYawHead, par9);
/*     */ 
/*     */       
/*  90 */       if (par1EntityLivingBase.isPotionActive(Potion.confusion)) {
/*  91 */         var11 = (float)(var11 - Math.cos((par1EntityLivingBase.ticksExisted * 0.15F)) * 3.0D);
/*     */       }
/*  93 */       if (par1EntityLivingBase.isRiding() && par1EntityLivingBase.ridingEntity instanceof EntityLivingBase) {
/*     */         
/*  95 */         EntityLivingBase var12 = (EntityLivingBase)par1EntityLivingBase.ridingEntity;
/*  96 */         var10 = interpolateRotation(var12.prevRenderYawOffset, var12.renderYawOffset, par9);
/*  97 */         float f = MathHelper.wrapAngleTo180_float(var11 - var10);
/*     */         
/*  99 */         if (f < -85.0F)
/*     */         {
/* 101 */           f = -85.0F;
/*     */         }
/*     */         
/* 104 */         if (f >= 85.0F)
/*     */         {
/* 106 */           f = 85.0F;
/*     */         }
/*     */         
/* 109 */         var10 = var11 - f;
/*     */         
/* 111 */         if (f * f > 2500.0F)
/*     */         {
/* 113 */           var10 += f * 0.2F;
/*     */         }
/*     */       } 
/*     */       
/* 117 */       float var26 = par1EntityLivingBase.prevRotationPitch + (par1EntityLivingBase.rotationPitch - par1EntityLivingBase.prevRotationPitch) * par9;
/*     */       
/* 119 */       if (par1EntityLivingBase.isPotionActive(Potion.confusion)) {
/* 120 */         var26 = (float)(var26 + Math.sin((par1EntityLivingBase.ticksExisted * 0.15F)) * 6.0D);
/*     */       }
/* 122 */       renderLivingAt(par1EntityLivingBase, par2, par4, par6);
/* 123 */       float var13 = handleRotationFloat(par1EntityLivingBase, par9);
/* 124 */       rotateCorpse(par1EntityLivingBase, var13, var10, par9);
/* 125 */       float var14 = 0.0625F;
/* 126 */       GL11.glEnable(32826);
/* 127 */       GL11.glScalef(-1.0F, -1.0F, 1.0F);
/* 128 */       preRenderCallback(par1EntityLivingBase, par9);
/* 129 */       GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
/* 130 */       float var15 = par1EntityLivingBase.prevLimbSwingAmount + (par1EntityLivingBase.limbSwingAmount - par1EntityLivingBase.prevLimbSwingAmount) * par9;
/* 131 */       float var16 = par1EntityLivingBase.limbSwing - par1EntityLivingBase.limbSwingAmount * (1.0F - par9);
/*     */       
/* 133 */       if (par1EntityLivingBase.isChild())
/*     */       {
/* 135 */         var16 *= 3.0F;
/*     */       }
/*     */       
/* 138 */       if (var15 > 1.0F)
/*     */       {
/* 140 */         var15 = 1.0F;
/*     */       }
/*     */       
/* 143 */       GL11.glEnable(3008);
/* 144 */       this.mainModel.setLivingAnimations(par1EntityLivingBase, var16, var15, par9);
/* 145 */       renderModel(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 151 */       for (int var17 = 0; var17 < 4; var17++) {
/*     */         
/* 153 */         int i = shouldRenderPass(par1EntityLivingBase, var17, par9);
/*     */         
/* 155 */         if (i > 0) {
/*     */           
/* 157 */           this.renderPassModel.setLivingAnimations(par1EntityLivingBase, var16, var15, par9);
/* 158 */           this.renderPassModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */           
/* 160 */           if ((i & 0xF0) == 16) {
/*     */             
/* 162 */             func_82408_c(par1EntityLivingBase, var17, par9);
/* 163 */             this.renderPassModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */           } 
/*     */           
/* 166 */           if ((i & 0xF) == 15) {
/*     */             
/* 168 */             float var19 = par1EntityLivingBase.ticksExisted + par9;
/* 169 */             bindTexture(RES_ITEM_GLINT);
/* 170 */             GL11.glEnable(3042);
/* 171 */             float var20 = 0.5F;
/* 172 */             GL11.glColor4f(var20, var20, var20, 1.0F);
/* 173 */             GL11.glDepthFunc(514);
/* 174 */             GL11.glDepthMask(false);
/*     */             
/* 176 */             for (int var21 = 0; var21 < 2; var21++) {
/*     */               
/* 178 */               GL11.glDisable(2896);
/* 179 */               float var22 = 0.76F;
/* 180 */               GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
/* 181 */               GL11.glBlendFunc(768, 1);
/* 182 */               GL11.glMatrixMode(5890);
/* 183 */               GL11.glLoadIdentity();
/* 184 */               float var23 = var19 * (0.001F + var21 * 0.003F) * 20.0F;
/* 185 */               float var24 = 0.33333334F;
/* 186 */               GL11.glScalef(var24, var24, var24);
/* 187 */               GL11.glRotatef(30.0F - var21 * 60.0F, 0.0F, 0.0F, 1.0F);
/* 188 */               GL11.glTranslatef(0.0F, var23, 0.0F);
/* 189 */               GL11.glMatrixMode(5888);
/* 190 */               this.renderPassModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */             } 
/*     */             
/* 193 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 194 */             GL11.glMatrixMode(5890);
/* 195 */             GL11.glDepthMask(true);
/* 196 */             GL11.glLoadIdentity();
/* 197 */             GL11.glMatrixMode(5888);
/* 198 */             GL11.glEnable(2896);
/* 199 */             GL11.glDisable(3042);
/* 200 */             GL11.glDepthFunc(515);
/*     */           } 
/*     */           
/* 203 */           GL11.glDisable(3042);
/* 204 */           GL11.glEnable(3008);
/*     */         } 
/*     */       } 
/*     */       
/* 208 */       GL11.glDepthMask(true);
/* 209 */       renderEquippedItems(par1EntityLivingBase, par9);
/* 210 */       float var27 = par1EntityLivingBase.getBrightness(par9);
/* 211 */       int var18 = getColorMultiplier(par1EntityLivingBase, var27, par9);
/* 212 */       OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 213 */       GL11.glDisable(3553);
/* 214 */       OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 219 */       if ((var18 >> 24 & 0xFF) > 0 || par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0 || par1EntityLivingBase.tagged) {
/*     */         
/* 221 */         GL11.glDisable(3553);
/* 222 */         GL11.glDisable(3008);
/* 223 */         GL11.glEnable(3042);
/* 224 */         GL11.glBlendFunc(770, 771);
/* 225 */         GL11.glDepthFunc(514);
/*     */         
/* 227 */         if (par1EntityLivingBase.tagged) {
/*     */           
/* 229 */           GL11.glPushAttrib(2929);
/* 230 */           GL11.glDisable(2929);
/*     */           
/* 232 */           GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
/* 233 */           this.mainModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */           
/* 235 */           GL11.glPopAttrib();
/*     */         } 
/*     */         
/* 238 */         if (par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0) {
/*     */           
/* 240 */           GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
/* 241 */           this.mainModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */           
/* 243 */           for (int var28 = 0; var28 < 4; var28++) {
/*     */             
/* 245 */             if (inheritRenderPass(par1EntityLivingBase, var28, par9) >= 0) {
/*     */               
/* 247 */               GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
/* 248 */               this.renderPassModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 253 */         if ((var18 >> 24 & 0xFF) > 0) {
/*     */           
/* 255 */           float var19 = (var18 >> 16 & 0xFF) / 255.0F;
/* 256 */           float var20 = (var18 >> 8 & 0xFF) / 255.0F;
/* 257 */           float var30 = (var18 & 0xFF) / 255.0F;
/* 258 */           float var22 = (var18 >> 24 & 0xFF) / 255.0F;
/* 259 */           GL11.glColor4f(var19, var20, var30, var22);
/* 260 */           this.mainModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */           
/* 262 */           for (int var29 = 0; var29 < 4; var29++) {
/*     */             
/* 264 */             if (inheritRenderPass(par1EntityLivingBase, var29, par9) >= 0) {
/*     */               
/* 266 */               GL11.glColor4f(var19, var20, var30, var22);
/* 267 */               this.renderPassModel.render(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 272 */         GL11.glDepthFunc(515);
/* 273 */         GL11.glDisable(3042);
/* 274 */         GL11.glEnable(3008);
/* 275 */         GL11.glEnable(3553);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 283 */       GL11.glDisable(32826);
/*     */     }
/* 285 */     catch (Exception var25) {
/*     */       
/* 287 */       var25.printStackTrace();
/*     */     } 
/*     */     
/* 290 */     OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 291 */     GL11.glEnable(3553);
/* 292 */     OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 293 */     GL11.glEnable(2884);
/* 294 */     GL11.glPopMatrix();
/* 295 */     passSpecialRender(par1EntityLivingBase, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderModelGlowing(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 300 */     ResourceLocation glowing_texture = getGlowingTextureCounterpart(getEntityTexture(par1EntityLivingBase));
/*     */     
/* 302 */     if (glowing_texture != null) {
/*     */       
/* 304 */       this.use_glowing_texture = true;
/* 305 */       bindTexture(glowing_texture);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 311 */       GL11.glDepthMask(!par1EntityLivingBase.isInvisible());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 321 */       int var5 = 15728880;
/* 322 */       int var6 = var5 % 65536;
/* 323 */       int var7 = var5 / 65536;
/* 324 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);
/*     */       
/* 326 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 328 */       this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/*     */       
/* 330 */       OpenGlHelper.restorePreviousLightmapTextureCoords();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 335 */       this.use_glowing_texture = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 346 */     bindEntityTexture(par1EntityLivingBase);
/*     */     
/* 348 */     if (!par1EntityLivingBase.isInvisible()) {
/*     */ 
/*     */ 
/*     */       
/* 352 */       float alpha = this.renderManager.getEntityRenderObject(par1EntityLivingBase).getModelOpacity(par1EntityLivingBase);
/*     */       
/* 354 */       if (alpha < 0.99F) {
/*     */         
/* 356 */         GL11.glPushMatrix();
/* 357 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/* 358 */         GL11.glDepthMask(false);
/* 359 */         GL11.glEnable(3042);
/* 360 */         GL11.glBlendFunc(770, 771);
/* 361 */         GL11.glAlphaFunc(516, 0.003921569F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 366 */       this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/*     */       
/* 368 */       renderModelGlowing(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/*     */       
/* 370 */       if (alpha < 0.99F)
/*     */       {
/* 372 */         GL11.glDisable(3042);
/* 373 */         GL11.glAlphaFunc(516, 0.1F);
/* 374 */         GL11.glPopMatrix();
/* 375 */         GL11.glDepthMask(true);
/*     */       }
/*     */     
/* 378 */     } else if (!par1EntityLivingBase.isInvisibleToPlayer((Minecraft.getMinecraft()).thePlayer)) {
/*     */       
/* 380 */       GL11.glPushMatrix();
/* 381 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
/* 382 */       GL11.glDepthMask(false);
/* 383 */       GL11.glEnable(3042);
/* 384 */       GL11.glBlendFunc(770, 771);
/* 385 */       GL11.glAlphaFunc(516, 0.003921569F);
/* 386 */       this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/* 387 */       renderModelGlowing(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/* 388 */       GL11.glDisable(3042);
/* 389 */       GL11.glAlphaFunc(516, 0.1F);
/* 390 */       GL11.glPopMatrix();
/* 391 */       GL11.glDepthMask(true);
/*     */     }
/*     */     else {
/*     */       
/* 395 */       this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
/* 404 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 409 */     GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);
/*     */     
/* 411 */     if (par1EntityLivingBase.deathTime > 0) {
/*     */       
/* 413 */       float var5 = (par1EntityLivingBase.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
/* 414 */       var5 = MathHelper.sqrt_float(var5);
/*     */       
/* 416 */       if (var5 > 1.0F)
/*     */       {
/* 418 */         var5 = 1.0F;
/*     */       }
/*     */       
/* 421 */       GL11.glRotatef(var5 * getDeathMaxRotation(par1EntityLivingBase), 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 425 */       String var6 = EnumChatFormatting.func_110646_a(par1EntityLivingBase.getEntityName());
/*     */       
/* 427 */       if ((var6.equals("Dinnerbone") || var6.equals("Grumm")) && (!(par1EntityLivingBase instanceof EntityPlayer) || !((EntityPlayer)par1EntityLivingBase).getHideCape())) {
/*     */         
/* 429 */         GL11.glTranslatef(0.0F, par1EntityLivingBase.height + 0.1F, 0.0F);
/* 430 */         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float renderSwingProgress(EntityLivingBase par1EntityLivingBase, float par2) {
/* 437 */     return par1EntityLivingBase.getSwingProgress(par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2) {
/* 445 */     return par1EntityLivingBase.ticksExisted + par2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderArrowsStuckInEntity(EntityLivingBase par1EntityLivingBase, float par2) {
/* 455 */     int var3 = par1EntityLivingBase.getArrowCountInEntity();
/*     */     
/* 457 */     if (var3 > 0) {
/*     */ 
/*     */       
/* 460 */       EntityArrow var4 = new EntityArrow(par1EntityLivingBase.worldObj, par1EntityLivingBase.posX, par1EntityLivingBase.posY, par1EntityLivingBase.posZ, Item.arrowIron, false);
/* 461 */       Random var5 = new Random(par1EntityLivingBase.entityId);
/* 462 */       RenderHelper.disableStandardItemLighting();
/*     */       
/* 464 */       for (int var6 = 0; var6 < var3; var6++) {
/*     */         
/* 466 */         GL11.glPushMatrix();
/* 467 */         ModelRenderer var7 = this.mainModel.getRandomModelBox(var5);
/* 468 */         ModelBox var8 = var7.cubeList.get(var5.nextInt(var7.cubeList.size()));
/* 469 */         var7.postRender(0.0625F);
/* 470 */         float var9 = var5.nextFloat();
/* 471 */         float var10 = var5.nextFloat();
/* 472 */         float var11 = var5.nextFloat();
/* 473 */         float var12 = (var8.posX1 + (var8.posX2 - var8.posX1) * var9) / 16.0F;
/* 474 */         float var13 = (var8.posY1 + (var8.posY2 - var8.posY1) * var10) / 16.0F;
/* 475 */         float var14 = (var8.posZ1 + (var8.posZ2 - var8.posZ1) * var11) / 16.0F;
/* 476 */         GL11.glTranslatef(var12, var13, var14);
/* 477 */         var9 = var9 * 2.0F - 1.0F;
/* 478 */         var10 = var10 * 2.0F - 1.0F;
/* 479 */         var11 = var11 * 2.0F - 1.0F;
/* 480 */         var9 *= -1.0F;
/* 481 */         var10 *= -1.0F;
/* 482 */         var11 *= -1.0F;
/* 483 */         float var15 = MathHelper.sqrt_float(var9 * var9 + var11 * var11);
/* 484 */         var4.prevRotationYaw = var4.rotationYaw = (float)(Math.atan2(var9, var11) * 180.0D / Math.PI);
/* 485 */         var4.prevRotationPitch = var4.rotationPitch = (float)(Math.atan2(var10, var15) * 180.0D / Math.PI);
/* 486 */         double var16 = 0.0D;
/* 487 */         double var18 = 0.0D;
/* 488 */         double var20 = 0.0D;
/* 489 */         float var22 = 0.0F;
/* 490 */         this.renderManager.renderEntityWithPosYaw(var4, var16, var18, var20, var22, par2);
/* 491 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/* 494 */       RenderHelper.enableStandardItemLighting();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 500 */     return shouldRenderPass(par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 508 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3) {}
/*     */   
/*     */   protected float getDeathMaxRotation(EntityLivingBase par1EntityLivingBase) {
/* 515 */     return 90.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3) {
/* 523 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void passSpecialRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
/* 537 */     if (func_110813_b(par1EntityLivingBase)) {
/*     */       
/* 539 */       float var8 = 1.6F;
/* 540 */       float var9 = 0.016666668F * var8;
/* 541 */       double var10 = par1EntityLivingBase.getDistanceSqToEntity(this.renderManager.livingPlayer);
/* 542 */       float var12 = par1EntityLivingBase.isSneaking() ? 32.0F : 64.0F;
/*     */       
/* 544 */       if (var10 < (var12 * var12)) {
/*     */         
/* 546 */         String var13 = par1EntityLivingBase.getTranslatedEntityName();
/*     */         
/* 548 */         if (par1EntityLivingBase.isSneaking()) {
/*     */           
/* 550 */           FontRenderer var14 = getFontRendererFromRenderManager();
/* 551 */           GL11.glPushMatrix();
/* 552 */           GL11.glTranslatef((float)par2 + 0.0F, (float)par4 + par1EntityLivingBase.height + 0.5F, (float)par6);
/* 553 */           GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 554 */           GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 555 */           GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 556 */           GL11.glScalef(-var9, -var9, var9);
/* 557 */           GL11.glDisable(2896);
/* 558 */           GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
/* 559 */           GL11.glDepthMask(false);
/* 560 */           GL11.glEnable(3042);
/* 561 */           GL11.glBlendFunc(770, 771);
/* 562 */           Tessellator var15 = Tessellator.instance;
/* 563 */           GL11.glDisable(3553);
/* 564 */           var15.startDrawingQuads();
/* 565 */           int var16 = var14.getStringWidth(var13) / 2;
/* 566 */           var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
/* 567 */           var15.addVertex((-var16 - 1), -1.0D, 0.0D);
/* 568 */           var15.addVertex((-var16 - 1), 8.0D, 0.0D);
/* 569 */           var15.addVertex((var16 + 1), 8.0D, 0.0D);
/* 570 */           var15.addVertex((var16 + 1), -1.0D, 0.0D);
/* 571 */           var15.draw();
/* 572 */           GL11.glEnable(3553);
/* 573 */           GL11.glDepthMask(true);
/* 574 */           var14.drawString(var13, -var14.getStringWidth(var13) / 2, 0, 553648127);
/* 575 */           GL11.glEnable(2896);
/* 576 */           GL11.glDisable(3042);
/* 577 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 578 */           GL11.glPopMatrix();
/*     */         }
/*     */         else {
/*     */           
/* 582 */           func_96449_a(par1EntityLivingBase, par2, par4, par6, var13, var9, var10);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_110813_b(EntityLivingBase par1EntityLivingBase) {
/* 590 */     return (Minecraft.isGuiEnabled() && par1EntityLivingBase != this.renderManager.livingPlayer && !par1EntityLivingBase.isInvisibleToPlayer((Minecraft.getMinecraft()).thePlayer) && par1EntityLivingBase.riddenByEntity == null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_96449_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, String par8Str, float par9, double par10) {
/* 596 */     if (par1EntityLivingBase.inBed()) {
/*     */       
/* 598 */       renderLivingLabel(par1EntityLivingBase, par8Str, par2, par4 - 1.5D, par6, 64);
/*     */     }
/*     */     else {
/*     */       
/* 602 */       renderLivingLabel(par1EntityLivingBase, par8Str, par2, par4, par6, 64);
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
/*     */   protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9) {
/* 614 */     if (par1EntityLivingBase.isEntityPlayer()) {
/*     */       
/* 616 */       EntityPlayer player = par1EntityLivingBase.getAsPlayer();
/*     */       
/* 618 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 622 */     double var10 = par1EntityLivingBase.getDistanceSqToEntity(this.renderManager.livingPlayer);
/*     */     
/* 624 */     if (var10 <= (par9 * par9)) {
/*     */       
/* 626 */       FontRenderer var12 = getFontRendererFromRenderManager();
/* 627 */       float var13 = 1.6F;
/* 628 */       float var14 = 0.016666668F * var13;
/* 629 */       GL11.glPushMatrix();
/* 630 */       GL11.glTranslatef((float)par3 + 0.0F, (float)par5 + par1EntityLivingBase.height + 0.5F, (float)par7);
/* 631 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 632 */       GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*     */       
/* 634 */       GL11.glRotatef(this.renderManager.playerViewX, (this.renderManager.options.thirdPersonView == 2) ? -1.0F : 1.0F, 0.0F, 0.0F);
/*     */       
/* 636 */       GL11.glScalef(-var14, -var14, var14);
/* 637 */       GL11.glDisable(2896);
/* 638 */       GL11.glDepthMask(false);
/* 639 */       GL11.glDisable(2929);
/* 640 */       GL11.glEnable(3042);
/* 641 */       GL11.glBlendFunc(770, 771);
/* 642 */       Tessellator var15 = Tessellator.instance;
/* 643 */       byte var16 = 0;
/*     */       
/* 645 */       if (par2Str.equals("deadmau5"))
/*     */       {
/* 647 */         var16 = -10;
/*     */       }
/*     */       
/* 650 */       GL11.glDisable(3553);
/* 651 */       var15.startDrawingQuads();
/* 652 */       int var17 = var12.getStringWidth(par2Str) / 2;
/* 653 */       var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
/* 654 */       var15.addVertex((-var17 - 1), (-1 + var16), 0.0D);
/* 655 */       var15.addVertex((-var17 - 1), (8 + var16), 0.0D);
/* 656 */       var15.addVertex((var17 + 1), (8 + var16), 0.0D);
/* 657 */       var15.addVertex((var17 + 1), (-1 + var16), 0.0D);
/* 658 */       var15.draw();
/* 659 */       GL11.glEnable(3553);
/* 660 */       var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
/* 661 */       GL11.glEnable(2929);
/* 662 */       GL11.glDepthMask(true);
/* 663 */       var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, -1);
/* 664 */       GL11.glEnable(2896);
/* 665 */       GL11.glDisable(3042);
/* 666 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 667 */       GL11.glPopMatrix();
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
/*     */   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
/* 679 */     doRenderLiving((EntityLivingBase)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RendererLivingEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */