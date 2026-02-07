/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderPlayer
/*     */   extends RendererLivingEntity {
/*   7 */   private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");
/*     */   
/*     */   private ModelBiped modelBipedMain;
/*     */   
/*     */   private ModelBiped modelArmorChestplate;
/*     */   private ModelBiped modelArmor;
/*     */   public static boolean see_zevimrgv_in_tournament;
/*     */   
/*     */   public RenderPlayer() {
/*  16 */     super(new ModelBiped(0.0F), 0.5F);
/*  17 */     this.modelBipedMain = (ModelBiped)this.mainModel;
/*  18 */     this.modelArmorChestplate = new ModelBiped(1.0F);
/*  19 */     this.modelArmor = new ModelBiped(0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int setArmorModel(AbstractClientPlayer par1AbstractClientPlayer, int par2, float par3) {
/*  27 */     ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3 - par2);
/*     */     
/*  29 */     if (var4 != null) {
/*     */       
/*  31 */       Item var5 = var4.getItem();
/*     */       
/*  33 */       if (var5 instanceof ItemArmor) {
/*     */         
/*  35 */         ItemArmor var6 = (ItemArmor)var5;
/*  36 */         bindTexture(RenderBiped.func_110857_a(var6, par2));
/*  37 */         ModelBiped var7 = (par2 == 2) ? this.modelArmor : this.modelArmorChestplate;
/*  38 */         var7.bipedHead.showModel = (par2 == 0);
/*  39 */         var7.bipedHeadwear.showModel = (par2 == 0);
/*  40 */         var7.bipedBody.showModel = (par2 == 1 || par2 == 2);
/*  41 */         var7.bipedRightArm.showModel = (par2 == 1);
/*  42 */         var7.bipedLeftArm.showModel = (par2 == 1);
/*  43 */         var7.bipedRightLeg.showModel = (par2 == 2 || par2 == 3);
/*  44 */         var7.bipedLeftLeg.showModel = (par2 == 2 || par2 == 3);
/*  45 */         setRenderPassModel(var7);
/*  46 */         var7.onGround = this.mainModel.onGround;
/*  47 */         var7.isRiding = this.mainModel.isRiding;
/*  48 */         var7.isChild = this.mainModel.isChild;
/*  49 */         float var8 = 1.0F;
/*     */ 
/*     */         
/*  52 */         if (var6.getArmorMaterial() == Material.leather) {
/*     */           
/*  54 */           int var9 = var6.getColor(var4);
/*  55 */           float var10 = (var9 >> 16 & 0xFF) / 255.0F;
/*  56 */           float var11 = (var9 >> 8 & 0xFF) / 255.0F;
/*  57 */           float var12 = (var9 & 0xFF) / 255.0F;
/*  58 */           GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);
/*     */           
/*  60 */           if (var4.isItemEnchanted())
/*     */           {
/*  62 */             return 31;
/*     */           }
/*     */           
/*  65 */           return 16;
/*     */         } 
/*     */         
/*  68 */         GL11.glColor3f(var8, var8, var8);
/*     */         
/*  70 */         if (var4.isItemEnchanted())
/*     */         {
/*  72 */           return 15;
/*     */         }
/*     */         
/*  75 */         return 1;
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_130220_b(AbstractClientPlayer par1AbstractClientPlayer, int par2, float par3) {
/*  84 */     ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3 - par2);
/*     */     
/*  86 */     if (var4 != null) {
/*     */       
/*  88 */       Item var5 = var4.getItem();
/*     */       
/*  90 */       if (var5 instanceof ItemArmor) {
/*     */         
/*  92 */         bindTexture(RenderBiped.func_110858_a((ItemArmor)var5, par2, "overlay"));
/*  93 */         float var6 = 1.0F;
/*  94 */         GL11.glColor3f(var6, var6, var6);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_130009_a(AbstractClientPlayer par1AbstractClientPlayer, double par2, double par4, double par6, float par8, float par9) {
/* 101 */     float var10 = 1.0F;
/* 102 */     GL11.glColor3f(var10, var10, var10);
/* 103 */     ItemStack var11 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
/* 104 */     this.modelBipedMain.heldItemRight = (var11 != null) ? 1 : 0;
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (var11 != null && par1AbstractClientPlayer.getItemInUseCount() > 0) {
/*     */ 
/*     */       
/* 111 */       EnumItemInUseAction var12 = var11.getItemInUseAction(par1AbstractClientPlayer);
/*     */       
/* 113 */       if (var12 == EnumItemInUseAction.BLOCK) {
/*     */         
/* 115 */         this.modelBipedMain.heldItemRight = 3;
/*     */       }
/* 117 */       else if (var12 == EnumItemInUseAction.BOW) {
/*     */         
/* 119 */         this.modelBipedMain.aimedBow = true;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     this.modelBipedMain.isSneak = par1AbstractClientPlayer.isSneaking();
/* 124 */     double var14 = par4 - par1AbstractClientPlayer.yOffset;
/*     */     
/* 126 */     if (par1AbstractClientPlayer.isSneaking() && !(par1AbstractClientPlayer instanceof ClientPlayer))
/*     */     {
/* 128 */       var14 -= 0.125D;
/*     */     }
/*     */     
/* 131 */     super.doRenderLiving(par1AbstractClientPlayer, par2, var14, par6, par8, par9);
/* 132 */     this.modelBipedMain.aimedBow = false;
/* 133 */     this.modelBipedMain.isSneak = false;
/* 134 */     this.modelBipedMain.heldItemRight = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110817_a(AbstractClientPlayer par1AbstractClientPlayer) {
/* 139 */     return par1AbstractClientPlayer.getLocationSkin();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderSpecials(AbstractClientPlayer par1AbstractClientPlayer, float par2) {
/* 147 */     float var3 = 1.0F;
/* 148 */     GL11.glColor3f(var3, var3, var3);
/* 149 */     super.renderEquippedItems(par1AbstractClientPlayer, par2);
/* 150 */     renderArrowsStuckInEntity(par1AbstractClientPlayer, par2);
/* 151 */     ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3);
/*     */     
/* 153 */     if (var4 != null) {
/*     */       
/* 155 */       GL11.glPushMatrix();
/* 156 */       this.modelBipedMain.bipedHead.postRender(0.0625F);
/*     */ 
/*     */       
/* 159 */       if ((var4.getItem()).itemID < 256) {
/*     */         
/* 161 */         if (RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
/*     */           
/* 163 */           float var5 = 0.625F;
/* 164 */           GL11.glTranslatef(0.0F, -0.25F, 0.0F);
/* 165 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 166 */           GL11.glScalef(var5, -var5, -var5);
/*     */         } 
/*     */         
/* 169 */         this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, var4, 0);
/*     */       }
/* 171 */       else if ((var4.getItem()).itemID == Item.skull.itemID) {
/*     */         
/* 173 */         float var5 = 1.0625F;
/* 174 */         GL11.glScalef(var5, -var5, -var5);
/* 175 */         String var6 = "";
/*     */         
/* 177 */         if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner"))
/*     */         {
/* 179 */           var6 = var4.getTagCompound().getString("SkullOwner");
/*     */         }
/*     */         
/* 182 */         TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemSubtype(), var6);
/*     */       } 
/*     */       
/* 185 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 188 */     if (par1AbstractClientPlayer.getCommandSenderName().equals("deadmau5") && par1AbstractClientPlayer.getTextureSkin().isTextureUploaded()) {
/*     */       
/* 190 */       bindTexture(par1AbstractClientPlayer.getLocationSkin());
/*     */       
/* 192 */       for (int var23 = 0; var23 < 2; var23++) {
/*     */         
/* 194 */         float var27 = par1AbstractClientPlayer.prevRotationYaw + (par1AbstractClientPlayer.rotationYaw - par1AbstractClientPlayer.prevRotationYaw) * par2 - par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2;
/* 195 */         float var7 = par1AbstractClientPlayer.prevRotationPitch + (par1AbstractClientPlayer.rotationPitch - par1AbstractClientPlayer.prevRotationPitch) * par2;
/* 196 */         GL11.glPushMatrix();
/* 197 */         GL11.glRotatef(var27, 0.0F, 1.0F, 0.0F);
/* 198 */         GL11.glRotatef(var7, 1.0F, 0.0F, 0.0F);
/* 199 */         GL11.glTranslatef(0.375F * (var23 * 2 - 1), 0.0F, 0.0F);
/* 200 */         GL11.glTranslatef(0.0F, -0.375F, 0.0F);
/* 201 */         GL11.glRotatef(-var7, 1.0F, 0.0F, 0.0F);
/* 202 */         GL11.glRotatef(-var27, 0.0F, 1.0F, 0.0F);
/* 203 */         float var8 = 1.3333334F;
/* 204 */         GL11.glScalef(var8, var8, var8);
/* 205 */         this.modelBipedMain.renderEars(0.0625F);
/* 206 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     boolean var24 = par1AbstractClientPlayer.getTextureCape().isTextureUploaded();
/* 211 */     boolean var25 = !par1AbstractClientPlayer.isInvisible();
/* 212 */     boolean var26 = !par1AbstractClientPlayer.getHideCape();
/*     */ 
/*     */     
/* 215 */     if (var24 && var25 && var26) {
/*     */       
/* 217 */       bindTexture(par1AbstractClientPlayer.getLocationCape());
/* 218 */       GL11.glPushMatrix();
/* 219 */       GL11.glTranslatef(0.0F, 0.0F, 0.125F);
/* 220 */       double var29 = par1AbstractClientPlayer.field_71091_bM + (par1AbstractClientPlayer.field_71094_bP - par1AbstractClientPlayer.field_71091_bM) * par2 - par1AbstractClientPlayer.prevPosX + (par1AbstractClientPlayer.posX - par1AbstractClientPlayer.prevPosX) * par2;
/* 221 */       double var10 = par1AbstractClientPlayer.field_71096_bN + (par1AbstractClientPlayer.field_71095_bQ - par1AbstractClientPlayer.field_71096_bN) * par2 - par1AbstractClientPlayer.prevPosY + (par1AbstractClientPlayer.posY - par1AbstractClientPlayer.prevPosY) * par2;
/* 222 */       double var12 = par1AbstractClientPlayer.field_71097_bO + (par1AbstractClientPlayer.field_71085_bR - par1AbstractClientPlayer.field_71097_bO) * par2 - par1AbstractClientPlayer.prevPosZ + (par1AbstractClientPlayer.posZ - par1AbstractClientPlayer.prevPosZ) * par2;
/* 223 */       float var14 = par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2;
/* 224 */       double var15 = MathHelper.sin(var14 * 3.1415927F / 180.0F);
/* 225 */       double var17 = -MathHelper.cos(var14 * 3.1415927F / 180.0F);
/* 226 */       float var19 = (float)var10 * 10.0F;
/*     */       
/* 228 */       if (var19 < -6.0F)
/*     */       {
/* 230 */         var19 = -6.0F;
/*     */       }
/*     */       
/* 233 */       if (var19 > 32.0F)
/*     */       {
/* 235 */         var19 = 32.0F;
/*     */       }
/*     */       
/* 238 */       float var20 = (float)(var29 * var15 + var12 * var17) * 100.0F;
/* 239 */       float var21 = (float)(var29 * var17 - var12 * var15) * 100.0F;
/*     */       
/* 241 */       if (var20 < 0.0F)
/*     */       {
/* 243 */         var20 = 0.0F;
/*     */       }
/*     */       
/* 246 */       float var22 = par1AbstractClientPlayer.prevCameraYaw + (par1AbstractClientPlayer.cameraYaw - par1AbstractClientPlayer.prevCameraYaw) * par2;
/* 247 */       var19 += MathHelper.sin((par1AbstractClientPlayer.prevDistanceWalkedModified + (par1AbstractClientPlayer.distanceWalkedModified - par1AbstractClientPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * var22;
/*     */       
/* 249 */       if (par1AbstractClientPlayer.isSneaking())
/*     */       {
/* 251 */         var19 += 25.0F;
/*     */       }
/*     */       
/* 254 */       GL11.glRotatef(6.0F + var20 / 2.0F + var19, 1.0F, 0.0F, 0.0F);
/* 255 */       GL11.glRotatef(var21 / 2.0F, 0.0F, 0.0F, 1.0F);
/* 256 */       GL11.glRotatef(-var21 / 2.0F, 0.0F, 1.0F, 0.0F);
/* 257 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 258 */       this.modelBipedMain.renderCloak(0.0625F);
/* 259 */       GL11.glPopMatrix();
/*     */     } 
/*     */     
/* 262 */     ItemStack var28 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
/*     */     
/* 264 */     if (var28 != null) {
/*     */       
/* 266 */       GL11.glPushMatrix();
/* 267 */       this.modelBipedMain.bipedRightArm.postRender(0.0625F);
/* 268 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 275 */       if (var28.getItem() instanceof ItemFishingRod && par1AbstractClientPlayer.fishEntity != null) {
/* 276 */         ItemRenderer.render_icon_override = ((ItemFishingRod)var28.getItem()).func_94597_g();
/*     */       }
/* 278 */       EnumItemInUseAction var9 = null;
/*     */       
/* 280 */       if (par1AbstractClientPlayer.getItemInUseCount() > 0)
/*     */       {
/*     */         
/* 283 */         var9 = var28.getItemInUseAction(par1AbstractClientPlayer);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 288 */       if (var28.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var28.itemID].getRenderType())) {
/*     */         
/* 290 */         float var31 = 0.5F;
/* 291 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/* 292 */         var31 *= 0.75F;
/* 293 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/* 294 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 295 */         GL11.glScalef(-var31, -var31, var31);
/*     */       
/*     */       }
/* 298 */       else if (var28.getItem() instanceof ItemBow) {
/*     */         
/* 300 */         float var31 = 0.625F;
/* 301 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/* 302 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/* 303 */         GL11.glScalef(var31, -var31, var31);
/* 304 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 305 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/* 307 */       else if (Item.itemsList[var28.itemID].isFull3D()) {
/*     */         
/* 309 */         float var31 = 0.625F;
/*     */         
/* 311 */         if (Item.itemsList[var28.itemID].shouldRotateAroundWhenRendering()) {
/*     */           
/* 313 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 314 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         } 
/*     */         
/* 317 */         if (par1AbstractClientPlayer.getItemInUseCount() > 0 && var9 == EnumItemInUseAction.BLOCK) {
/*     */           
/* 319 */           GL11.glTranslatef(0.05F, 0.0F, -0.1F);
/* 320 */           GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
/* 321 */           GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
/* 322 */           GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
/*     */         } 
/*     */         
/* 325 */         GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/* 326 */         GL11.glScalef(var31, -var31, var31);
/* 327 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 328 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else {
/*     */         
/* 332 */         float var31 = 0.375F;
/* 333 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 334 */         GL11.glScalef(var31, var31, var31);
/* 335 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 336 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 337 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 344 */       if (var28.getItem().requiresMultipleRenderPasses()) {
/*     */         
/* 346 */         for (int var33 = 0; var33 <= 1; var33++)
/*     */         {
/* 348 */           int var11 = var28.getItem().getColorFromItemStack(var28, var33);
/* 349 */           float var32 = (var11 >> 16 & 0xFF) / 255.0F;
/* 350 */           float var13 = (var11 >> 8 & 0xFF) / 255.0F;
/* 351 */           float var14 = (var11 & 0xFF) / 255.0F;
/* 352 */           GL11.glColor4f(var32, var13, var14, 1.0F);
/* 353 */           this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, var28, var33);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 358 */         int var33 = var28.getItem().getColorFromItemStack(var28, 0);
/* 359 */         float var30 = (var33 >> 16 & 0xFF) / 255.0F;
/* 360 */         float var32 = (var33 >> 8 & 0xFF) / 255.0F;
/* 361 */         float var13 = (var33 & 0xFF) / 255.0F;
/* 362 */         GL11.glColor4f(var30, var32, var13, 1.0F);
/* 363 */         this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, var28, 0);
/*     */       } 
/*     */       
/* 366 */       ItemRenderer.render_icon_override = null;
/*     */       
/* 368 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderPlayerScale(AbstractClientPlayer par1AbstractClientPlayer, float par2) {
/* 374 */     float var3 = 0.9375F;
/* 375 */     GL11.glScalef(var3, var3, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96450_a(AbstractClientPlayer par1AbstractClientPlayer, double par2, double par4, double par6, String par8Str, float par9, double par10) {
/* 380 */     if (par10 < 100.0D) {
/*     */       
/* 382 */       Scoreboard var12 = par1AbstractClientPlayer.getWorldScoreboard();
/* 383 */       ScoreObjective var13 = var12.func_96539_a(2);
/*     */       
/* 385 */       if (var13 != null) {
/*     */         
/* 387 */         Score var14 = var12.func_96529_a(par1AbstractClientPlayer.getEntityName(), var13);
/*     */ 
/*     */         
/* 390 */         if (par1AbstractClientPlayer.inBed()) {
/*     */           
/* 392 */           renderLivingLabel(par1AbstractClientPlayer, var14.getScorePoints() + " " + var13.getDisplayName(), par2, par4 - 1.5D, par6, 64);
/*     */         }
/*     */         else {
/*     */           
/* 396 */           renderLivingLabel(par1AbstractClientPlayer, var14.getScorePoints() + " " + var13.getDisplayName(), par2, par4, par6, 64);
/*     */         } 
/*     */         
/* 399 */         par4 += ((getFontRendererFromRenderManager()).FONT_HEIGHT * 1.15F * par9);
/*     */       } 
/*     */     } 
/*     */     
/* 403 */     super.func_96449_a(par1AbstractClientPlayer, par2, par4, par6, par8Str, par9, par10);
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
/*     */   public void renderFirstPersonArm(EntityPlayer player) {
/* 417 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */     
/* 419 */     float alpha = getModelOpacity(player);
/*     */     
/* 421 */     if (alpha < 0.99F) {
/*     */       
/* 423 */       GL11.glPushMatrix();
/* 424 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
/* 425 */       GL11.glDepthMask(false);
/* 426 */       GL11.glEnable(3042);
/* 427 */       GL11.glBlendFunc(770, 771);
/* 428 */       GL11.glAlphaFunc(516, 0.003921569F);
/*     */     } 
/*     */     
/* 431 */     this.modelBipedMain.onGround = 0.0F;
/* 432 */     this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
/* 433 */     this.modelBipedMain.bipedRightArm.render(0.0625F);
/*     */     
/* 435 */     if (alpha < 0.99F) {
/*     */       
/* 437 */       GL11.glDisable(3042);
/* 438 */       GL11.glAlphaFunc(516, 0.1F);
/* 439 */       GL11.glPopMatrix();
/* 440 */       GL11.glDepthMask(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderPlayerSleep(AbstractClientPlayer par1AbstractClientPlayer, double par2, double par4, double par6) {
/* 450 */     if (par1AbstractClientPlayer.isEntityAlive() && par1AbstractClientPlayer.inBed()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 460 */       super.renderLivingAt(par1AbstractClientPlayer, par2 + par1AbstractClientPlayer.field_71079_bU, par4 + par1AbstractClientPlayer.field_71082_cx, par6 + par1AbstractClientPlayer.field_71089_bV);
/*     */     }
/*     */     else {
/*     */       
/* 464 */       super.renderLivingAt(par1AbstractClientPlayer, par2, par4, par6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void rotatePlayer(AbstractClientPlayer par1AbstractClientPlayer, float par2, float par3, float par4) {
/* 474 */     if (par1AbstractClientPlayer.isEntityAlive() && par1AbstractClientPlayer.inBed()) {
/*     */       
/* 476 */       GL11.glRotatef(par1AbstractClientPlayer.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
/* 477 */       GL11.glRotatef(getDeathMaxRotation(par1AbstractClientPlayer), 0.0F, 0.0F, 1.0F);
/* 478 */       GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/* 482 */       super.rotateCorpse(par1AbstractClientPlayer, par2, par3, par4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96449_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, String par8Str, float par9, double par10) {
/* 488 */     func_96450_a((AbstractClientPlayer)par1EntityLivingBase, par2, par4, par6, par8Str, par9, par10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
/* 497 */     renderPlayerScale((AbstractClientPlayer)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 502 */     func_130220_b((AbstractClientPlayer)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/* 510 */     return setArmorModel((AbstractClientPlayer)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
/* 515 */     renderSpecials((AbstractClientPlayer)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 520 */     rotatePlayer((AbstractClientPlayer)par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingAt(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
/* 528 */     renderPlayerSleep((AbstractClientPlayer)par1EntityLivingBase, par2, par4, par6);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
/* 533 */     func_130009_a((AbstractClientPlayer)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 541 */     return func_110817_a((AbstractClientPlayer)par1Entity);
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
/* 552 */     if (par1Entity.isSneaking()) {
/* 553 */       par4 -= 0.18000000715255737D;
/*     */     }
/* 555 */     func_130009_a((AbstractClientPlayer)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getModelOpacity(Entity entity) {
/* 563 */     if (entity.isEntityPlayer()) {
/*     */       
/* 565 */       EntityPlayer player = entity.getAsPlayer();
/*     */       
/* 567 */       if (player.isGhost()) {
/* 568 */         return 0.0F;
/*     */       }
/* 570 */       if (player.isZevimrgvInTournament() && !see_zevimrgv_in_tournament) {
/* 571 */         return 0.0F;
/*     */       }
/*     */     } 
/* 574 */     return super.getModelOpacity(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */