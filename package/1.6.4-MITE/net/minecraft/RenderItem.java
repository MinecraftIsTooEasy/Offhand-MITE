/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderItem
/*     */   extends Render
/*     */ {
/*   9 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*  10 */   private RenderBlocks itemRenderBlocks = new RenderBlocks();
/*     */ 
/*     */   
/*  13 */   private Random random = new Random();
/*     */   
/*     */   public boolean renderWithColor = true;
/*     */   
/*     */   public float zLevel;
/*     */   
/*     */   public static boolean renderInFrame;
/*     */   
/*     */   public RenderItem() {
/*  22 */     this.shadowSize = 0.15F;
/*  23 */     this.shadowOpaque = 0.75F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderItem(EntityItem par1EntityItem, double par2, double par4, double par6, float par8, float par9) {
/*  31 */     bindEntityTexture(par1EntityItem);
/*  32 */     this.random.setSeed(187L);
/*  33 */     ItemStack var10 = par1EntityItem.getEntityItem();
/*     */     
/*  35 */     if (var10.getItem() != null) {
/*     */       
/*  37 */       GL11.glPushMatrix();
/*  38 */       float var11 = MathHelper.sin((par1EntityItem.age + par9) / 10.0F + par1EntityItem.hoverStart) * 0.1F + 0.1F;
/*  39 */       float var12 = ((par1EntityItem.age + par9) / 20.0F + par1EntityItem.hoverStart) * 57.295776F;
/*  40 */       byte var13 = 1;
/*     */       
/*  42 */       if ((par1EntityItem.getEntityItem()).stackSize > 1)
/*     */       {
/*  44 */         var13 = 2;
/*     */       }
/*     */       
/*  47 */       if ((par1EntityItem.getEntityItem()).stackSize > 5)
/*     */       {
/*  49 */         var13 = 3;
/*     */       }
/*     */       
/*  52 */       if ((par1EntityItem.getEntityItem()).stackSize > 20)
/*     */       {
/*  54 */         var13 = 4;
/*     */       }
/*     */       
/*  57 */       if ((par1EntityItem.getEntityItem()).stackSize > 40)
/*     */       {
/*  59 */         var13 = 5;
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       GL11.glTranslatef((float)par2, (float)par4 + var11, (float)par6);
/* 108 */       GL11.glEnable(32826);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       if (var10.getItemSpriteNumber() == 0 && var10.itemID < Block.blocksList.length && Block.blocksList[var10.itemID] != null && RenderBlocks.renderItemIn3d(Block.blocksList[var10.itemID].getRenderType())) {
/*     */         
/* 116 */         Block var21 = Block.blocksList[var10.itemID];
/* 117 */         GL11.glRotatef(var12, 0.0F, 1.0F, 0.0F);
/*     */         
/* 119 */         if (renderInFrame) {
/*     */           
/* 121 */           GL11.glScalef(1.25F, 1.25F, 1.25F);
/* 122 */           GL11.glTranslatef(0.0F, 0.05F, 0.0F);
/* 123 */           GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */         } 
/*     */         
/* 126 */         float var25 = 0.25F;
/* 127 */         int var24 = var21.getRenderType();
/*     */         
/* 129 */         if (var24 == 1 || var24 == 19 || var24 == 12 || var24 == 2)
/*     */         {
/* 131 */           var25 = 0.5F;
/*     */         }
/*     */         
/* 134 */         GL11.glScalef(var25, var25, var25);
/*     */         
/* 136 */         for (int var26 = 0; var26 < var13; var26++)
/*     */         {
/* 138 */           GL11.glPushMatrix();
/*     */           
/* 140 */           if (var26 > 0) {
/*     */             
/* 142 */             float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var25;
/* 143 */             float var19 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var25;
/* 144 */             float var20 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var25;
/* 145 */             GL11.glTranslatef(f1, var19, var20);
/*     */           } 
/*     */           
/* 148 */           float var18 = 1.0F;
/* 149 */           this.itemRenderBlocks.renderBlockAsItem(var21, var10.getItemSubtype(), var18);
/* 150 */           GL11.glPopMatrix();
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 157 */       else if (var10.getItemSpriteNumber() == 1 && var10.getItem().requiresMultipleRenderPasses()) {
/*     */         
/* 159 */         if (renderInFrame) {
/*     */           
/* 161 */           GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
/* 162 */           GL11.glTranslatef(0.0F, -0.05F, 0.0F);
/*     */         }
/*     */         else {
/*     */           
/* 166 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */         } 
/*     */         
/* 169 */         for (int var23 = 0; var23 <= 1; var23++) {
/*     */           
/* 171 */           this.random.setSeed(187L);
/* 172 */           Icon var22 = var10.getItem().getIconFromSubtypeForRenderPass(var10.getItemSubtype(), var23);
/* 173 */           float var16 = 1.0F;
/*     */           
/* 175 */           if (this.renderWithColor)
/*     */           {
/* 177 */             int var26 = Item.itemsList[var10.itemID].getColorFromItemStack(var10, var23);
/* 178 */             float var18 = (var26 >> 16 & 0xFF) / 255.0F;
/* 179 */             float var19 = (var26 >> 8 & 0xFF) / 255.0F;
/* 180 */             float var20 = (var26 & 0xFF) / 255.0F;
/* 181 */             GL11.glColor4f(var18 * var16, var19 * var16, var20 * var16, 1.0F);
/* 182 */             renderDroppedItem(par1EntityItem, var22, var13, par9, var18 * var16, var19 * var16, var20 * var16);
/*     */           }
/*     */           else
/*     */           {
/* 186 */             renderDroppedItem(par1EntityItem, var22, var13, par9, 1.0F, 1.0F, 1.0F);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 192 */         if (renderInFrame) {
/*     */           
/* 194 */           GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
/* 195 */           GL11.glTranslatef(0.0F, -0.05F, 0.0F);
/*     */         }
/*     */         else {
/*     */           
/* 199 */           GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */         } 
/*     */         
/* 202 */         Icon var14 = var10.getIconIndex();
/*     */         
/* 204 */         if (this.renderWithColor) {
/*     */           
/* 206 */           int var15 = Item.itemsList[var10.itemID].getColorFromItemStack(var10, 0);
/* 207 */           float var16 = (var15 >> 16 & 0xFF) / 255.0F;
/* 208 */           float var17 = (var15 >> 8 & 0xFF) / 255.0F;
/* 209 */           float var18 = (var15 & 0xFF) / 255.0F;
/* 210 */           float var19 = 1.0F;
/* 211 */           renderDroppedItem(par1EntityItem, var14, var13, par9, var16 * var19, var17 * var19, var18 * var19);
/*     */         }
/*     */         else {
/*     */           
/* 215 */           renderDroppedItem(par1EntityItem, var14, var13, par9, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 220 */       GL11.glDisable(32826);
/* 221 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110796_a(EntityItem par1EntityItem) {
/* 227 */     return this.renderManager.renderEngine.getResourceLocation(par1EntityItem.getEntityItem().getItemSpriteNumber());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderDroppedItem(EntityItem par1EntityItem, Icon par2Icon, int par3, float par4, float par5, float par6, float par7) {
/* 235 */     Tessellator var8 = Tessellator.instance;
/*     */     
/* 237 */     if (par2Icon == null) {
/*     */       
/* 239 */       TextureManager var9 = Minecraft.getMinecraft().getTextureManager();
/* 240 */       ResourceLocation var10 = var9.getResourceLocation(par1EntityItem.getEntityItem().getItemSpriteNumber());
/* 241 */       par2Icon = ((TextureMap)var9.getTexture(var10)).getAtlasSprite("missingno");
/*     */     } 
/*     */     
/* 244 */     float var25 = par2Icon.getMinU();
/* 245 */     float var26 = par2Icon.getMaxU();
/* 246 */     float var11 = par2Icon.getMinV();
/* 247 */     float var12 = par2Icon.getMaxV();
/* 248 */     float var13 = 1.0F;
/* 249 */     float var14 = 0.5F;
/* 250 */     float var15 = 0.25F;
/*     */ 
/*     */ 
/*     */     
/* 254 */     if (this.renderManager.options.isFancyGraphicsEnabled()) {
/*     */       byte var24;
/* 256 */       GL11.glPushMatrix();
/*     */       
/* 258 */       if (renderInFrame) {
/*     */         
/* 260 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */       }
/*     */       else {
/*     */         
/* 264 */         GL11.glRotatef(((par1EntityItem.age + par4) / 20.0F + par1EntityItem.hoverStart) * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */       } 
/*     */       
/* 267 */       float var16 = 0.0625F;
/* 268 */       float var17 = 0.021875F;
/* 269 */       ItemStack var18 = par1EntityItem.getEntityItem();
/* 270 */       int var19 = var18.stackSize;
/*     */ 
/*     */       
/* 273 */       if (var19 < 2) {
/*     */         
/* 275 */         var24 = 1;
/*     */       }
/* 277 */       else if (var19 < 16) {
/*     */         
/* 279 */         var24 = 2;
/*     */       }
/* 281 */       else if (var19 < 32) {
/*     */         
/* 283 */         var24 = 3;
/*     */       }
/*     */       else {
/*     */         
/* 287 */         var24 = 4;
/*     */       } 
/*     */       
/* 290 */       GL11.glTranslatef(-var14, -var15, -((var16 + var17) * var24 / 2.0F));
/*     */       
/* 292 */       for (int var20 = 0; var20 < var24; var20++) {
/*     */         
/* 294 */         GL11.glTranslatef(0.0F, 0.0F, var16 + var17);
/*     */         
/* 296 */         if (var18.getItemSpriteNumber() == 0 && Block.blocksList[var18.itemID] != null) {
/*     */           
/* 298 */           bindTexture(TextureMap.locationBlocksTexture);
/*     */         }
/*     */         else {
/*     */           
/* 302 */           bindTexture(TextureMap.locationItemsTexture);
/*     */         } 
/*     */         
/* 305 */         GL11.glColor4f(par5, par6, par7, 1.0F);
/* 306 */         ItemRenderer.renderItemIn2D(var8, var26, var11, var25, var12, par2Icon.getIconWidth(), par2Icon.getIconHeight(), var16);
/*     */         
/* 308 */         if (var18.hasEffect()) {
/*     */           
/* 310 */           GL11.glDepthFunc(514);
/* 311 */           GL11.glDisable(2896);
/* 312 */           this.renderManager.renderEngine.bindTexture(RES_ITEM_GLINT);
/* 313 */           GL11.glEnable(3042);
/* 314 */           GL11.glBlendFunc(768, 1);
/* 315 */           float var21 = 0.76F;
/* 316 */           GL11.glColor4f(0.5F * var21, 0.25F * var21, 0.8F * var21, 1.0F);
/* 317 */           GL11.glMatrixMode(5890);
/* 318 */           GL11.glPushMatrix();
/* 319 */           float var22 = 0.125F;
/* 320 */           GL11.glScalef(var22, var22, var22);
/* 321 */           float var23 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
/* 322 */           GL11.glTranslatef(var23, 0.0F, 0.0F);
/* 323 */           GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 324 */           ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, var16);
/* 325 */           GL11.glPopMatrix();
/* 326 */           GL11.glPushMatrix();
/* 327 */           GL11.glScalef(var22, var22, var22);
/* 328 */           var23 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
/* 329 */           GL11.glTranslatef(-var23, 0.0F, 0.0F);
/* 330 */           GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 331 */           ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, var16);
/* 332 */           GL11.glPopMatrix();
/* 333 */           GL11.glMatrixMode(5888);
/* 334 */           GL11.glDisable(3042);
/* 335 */           GL11.glEnable(2896);
/* 336 */           GL11.glDepthFunc(515);
/*     */         } 
/*     */       } 
/*     */       
/* 340 */       GL11.glPopMatrix();
/*     */     }
/*     */     else {
/*     */       
/* 344 */       for (int var27 = 0; var27 < par3; var27++) {
/*     */         
/* 346 */         GL11.glPushMatrix();
/*     */         
/* 348 */         if (var27 > 0) {
/*     */           
/* 350 */           float var17 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
/* 351 */           float var29 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
/* 352 */           float var28 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
/* 353 */           GL11.glTranslatef(var17, var29, var28);
/*     */         } 
/*     */         
/* 356 */         if (!renderInFrame)
/*     */         {
/* 358 */           GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 361 */         GL11.glColor4f(par5, par6, par7, 1.0F);
/* 362 */         var8.startDrawingQuads();
/* 363 */         var8.setNormal(0.0F, 1.0F, 0.0F);
/* 364 */         var8.addVertexWithUV((0.0F - var14), (0.0F - var15), 0.0D, var25, var12);
/* 365 */         var8.addVertexWithUV((var13 - var14), (0.0F - var15), 0.0D, var26, var12);
/* 366 */         var8.addVertexWithUV((var13 - var14), (1.0F - var15), 0.0D, var26, var11);
/* 367 */         var8.addVertexWithUV((0.0F - var14), (1.0F - var15), 0.0D, var25, var11);
/* 368 */         var8.draw();
/* 369 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItemIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5) {
/* 379 */     int var6 = par3ItemStack.itemID;
/* 380 */     int var7 = par3ItemStack.getItemSubtype();
/* 381 */     Object var8 = par3ItemStack.getIconIndex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 387 */     if (par3ItemStack.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.blocksList[var6].getRenderType())) {
/*     */       
/* 389 */       par2TextureManager.bindTexture(TextureMap.locationBlocksTexture);
/* 390 */       Block var15 = Block.blocksList[var6];
/* 391 */       GL11.glPushMatrix();
/* 392 */       GL11.glTranslatef((par4 - 2), (par5 + 3), -3.0F + this.zLevel);
/* 393 */       GL11.glScalef(10.0F, 10.0F, 10.0F);
/* 394 */       GL11.glTranslatef(1.0F, 0.5F, 1.0F);
/* 395 */       GL11.glScalef(1.0F, 1.0F, -1.0F);
/* 396 */       GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
/* 397 */       GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 398 */       int var18 = Item.itemsList[var6].getColorFromItemStack(par3ItemStack, 0);
/* 399 */       float var17 = (var18 >> 16 & 0xFF) / 255.0F;
/* 400 */       float var12 = (var18 >> 8 & 0xFF) / 255.0F;
/* 401 */       float var13 = (var18 & 0xFF) / 255.0F;
/*     */       
/* 403 */       if (this.renderWithColor)
/*     */       {
/* 405 */         GL11.glColor4f(var17, var12, var13, 1.0F);
/*     */       }
/*     */       
/* 408 */       GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 409 */       this.itemRenderBlocks.useInventoryTint = this.renderWithColor;
/* 410 */       this.itemRenderBlocks.renderBlockAsItem(var15, var7, 1.0F);
/* 411 */       this.itemRenderBlocks.useInventoryTint = true;
/* 412 */       GL11.glPopMatrix();
/*     */     }
/* 414 */     else if (Item.itemsList[var6].requiresMultipleRenderPasses()) {
/*     */       
/* 416 */       GL11.glDisable(2896);
/* 417 */       par2TextureManager.bindTexture(TextureMap.locationItemsTexture);
/*     */       
/* 419 */       for (int var9 = 0; var9 <= 1; var9++) {
/*     */         
/* 421 */         Icon var10 = Item.itemsList[var6].getIconFromSubtypeForRenderPass(var7, var9);
/* 422 */         int var11 = Item.itemsList[var6].getColorFromItemStack(par3ItemStack, var9);
/* 423 */         float var12 = (var11 >> 16 & 0xFF) / 255.0F;
/* 424 */         float var13 = (var11 >> 8 & 0xFF) / 255.0F;
/* 425 */         float var14 = (var11 & 0xFF) / 255.0F;
/*     */         
/* 427 */         if (this.renderWithColor)
/*     */         {
/* 429 */           GL11.glColor4f(var12, var13, var14, 1.0F);
/*     */         }
/*     */         
/* 432 */         renderIcon(par4, par5, var10, 16, 16);
/*     */       } 
/*     */       
/* 435 */       GL11.glEnable(2896);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 441 */       GL11.glDisable(2896);
/* 442 */       ResourceLocation var16 = par2TextureManager.getResourceLocation(par3ItemStack.getItemSpriteNumber());
/* 443 */       par2TextureManager.bindTexture(var16);
/*     */       
/* 445 */       if (var8 == null)
/*     */       {
/* 447 */         var8 = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(var16)).getAtlasSprite("missingno");
/*     */       }
/*     */       
/* 450 */       int var18 = Item.itemsList[var6].getColorFromItemStack(par3ItemStack, 0);
/* 451 */       float var17 = (var18 >> 16 & 0xFF) / 255.0F;
/* 452 */       float var12 = (var18 >> 8 & 0xFF) / 255.0F;
/* 453 */       float var13 = (var18 & 0xFF) / 255.0F;
/*     */       
/* 455 */       if (this.renderWithColor)
/*     */       {
/* 457 */         GL11.glColor4f(var17, var12, var13, 1.0F);
/*     */       }
/*     */       
/* 460 */       renderIcon(par4, par5, (Icon)var8, 16, 16);
/* 461 */       GL11.glEnable(2896);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 467 */     GL11.glEnable(2884);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItemAndEffectIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5) {
/* 475 */     if (par3ItemStack != null) {
/*     */       
/* 477 */       renderItemIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5);
/*     */       
/* 479 */       if (par3ItemStack.hasEffect()) {
/*     */         
/* 481 */         GL11.glDepthFunc(516);
/* 482 */         GL11.glDisable(2896);
/* 483 */         GL11.glDepthMask(false);
/* 484 */         par2TextureManager.bindTexture(RES_ITEM_GLINT);
/* 485 */         this.zLevel -= 50.0F;
/* 486 */         GL11.glEnable(3042);
/* 487 */         GL11.glBlendFunc(774, 774);
/* 488 */         GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
/* 489 */         renderGlint(par4 * 431278612 + par5 * 32178161, par4 - 2, par5 - 2, 20, 20);
/* 490 */         GL11.glDisable(3042);
/* 491 */         GL11.glDepthMask(true);
/* 492 */         this.zLevel += 50.0F;
/* 493 */         GL11.glEnable(2896);
/* 494 */         GL11.glDepthFunc(515);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderGlint(int par1, int par2, int par3, int par4, int par5) {
/* 501 */     for (int var6 = 0; var6 < 2; var6++) {
/*     */       
/* 503 */       if (var6 == 0)
/*     */       {
/* 505 */         GL11.glBlendFunc(768, 1);
/*     */       }
/*     */       
/* 508 */       if (var6 == 1)
/*     */       {
/* 510 */         GL11.glBlendFunc(768, 1);
/*     */       }
/*     */       
/* 513 */       float var7 = 0.00390625F;
/* 514 */       float var8 = 0.00390625F;
/* 515 */       float var9 = (float)(Minecraft.getSystemTime() % (3000 + var6 * 1873)) / (3000.0F + (var6 * 1873)) * 256.0F;
/* 516 */       float var10 = 0.0F;
/* 517 */       Tessellator var11 = Tessellator.instance;
/* 518 */       float var12 = 4.0F;
/*     */       
/* 520 */       if (var6 == 1)
/*     */       {
/* 522 */         var12 = -1.0F;
/*     */       }
/*     */       
/* 525 */       var11.startDrawingQuads();
/* 526 */       var11.addVertexWithUV((par2 + 0), (par3 + par5), this.zLevel, ((var9 + par5 * var12) * var7), ((var10 + par5) * var8));
/* 527 */       var11.addVertexWithUV((par2 + par4), (par3 + par5), this.zLevel, ((var9 + par4 + par5 * var12) * var7), ((var10 + par5) * var8));
/* 528 */       var11.addVertexWithUV((par2 + par4), (par3 + 0), this.zLevel, ((var9 + par4) * var7), ((var10 + 0.0F) * var8));
/* 529 */       var11.addVertexWithUV((par2 + 0), (par3 + 0), this.zLevel, ((var9 + 0.0F) * var7), ((var10 + 0.0F) * var8));
/* 530 */       var11.draw();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5) {
/* 540 */     renderItemOverlayIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5, String par6Str) {
/* 545 */     if (par3ItemStack != null) {
/*     */       
/* 547 */       if (par3ItemStack.stackSize > 1 || par6Str != null) {
/*     */         
/* 549 */         String var7 = (par6Str == null) ? String.valueOf(par3ItemStack.stackSize) : par6Str;
/* 550 */         GL11.glDisable(2896);
/* 551 */         GL11.glDisable(2929);
/* 552 */         par1FontRenderer.drawStringWithShadow(var7, par4 + 19 - 2 - par1FontRenderer.getStringWidth(var7), par5 + 6 + 3, 16777215);
/* 553 */         GL11.glEnable(2896);
/* 554 */         GL11.glEnable(2929);
/*     */       } 
/*     */       
/* 557 */       if (par3ItemStack.isItemDamaged()) {
/*     */         
/* 559 */         int var12 = (int)Math.round(13.0D - par3ItemStack.getItemDamageForDisplay() * 13.0D / par3ItemStack.getMaxDamage());
/* 560 */         int var8 = (int)Math.round(255.0D - par3ItemStack.getItemDamageForDisplay() * 255.0D / par3ItemStack.getMaxDamage());
/* 561 */         GL11.glDisable(2896);
/* 562 */         GL11.glDisable(2929);
/* 563 */         GL11.glDisable(3553);
/* 564 */         Tessellator var9 = Tessellator.instance;
/* 565 */         int var10 = 255 - var8 << 16 | var8 << 8;
/* 566 */         int var11 = (255 - var8) / 4 << 16 | 0x3F00;
/* 567 */         renderQuad(var9, par4 + 2, par5 + 13, 13, 2, 0);
/* 568 */         renderQuad(var9, par4 + 2, par5 + 13, 12, 1, var11);
/* 569 */         renderQuad(var9, par4 + 2, par5 + 13, var12, 1, var10);
/* 570 */         GL11.glEnable(3553);
/* 571 */         GL11.glEnable(2896);
/* 572 */         GL11.glEnable(2929);
/* 573 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderQuad(Tessellator par1Tessellator, int par2, int par3, int par4, int par5, int par6) {
/* 584 */     par1Tessellator.startDrawingQuads();
/* 585 */     par1Tessellator.setColorOpaque_I(par6);
/* 586 */     par1Tessellator.addVertex((par2 + 0), (par3 + 0), 0.0D);
/* 587 */     par1Tessellator.addVertex((par2 + 0), (par3 + par5), 0.0D);
/* 588 */     par1Tessellator.addVertex((par2 + par4), (par3 + par5), 0.0D);
/* 589 */     par1Tessellator.addVertex((par2 + par4), (par3 + 0), 0.0D);
/* 590 */     par1Tessellator.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderIcon(int par1, int par2, Icon par3Icon, int par4, int par5) {
/* 595 */     Tessellator var6 = Tessellator.instance;
/* 596 */     var6.startDrawingQuads();
/* 597 */     var6.addVertexWithUV((par1 + 0), (par2 + par5), this.zLevel, par3Icon.getMinU(), par3Icon.getMaxV());
/* 598 */     var6.addVertexWithUV((par1 + par4), (par2 + par5), this.zLevel, par3Icon.getMaxU(), par3Icon.getMaxV());
/* 599 */     var6.addVertexWithUV((par1 + par4), (par2 + 0), this.zLevel, par3Icon.getMaxU(), par3Icon.getMinV());
/* 600 */     var6.addVertexWithUV((par1 + 0), (par2 + 0), this.zLevel, par3Icon.getMinU(), par3Icon.getMinV());
/* 601 */     var6.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 609 */     return func_110796_a((EntityItem)par1Entity);
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
/* 620 */     doRenderItem((EntityItem)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */