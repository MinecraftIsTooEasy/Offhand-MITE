/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderItemFrame
/*     */   extends Render {
/*   7 */   private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
/*   8 */   private final RenderBlocks renderBlocksInstance = new RenderBlocks();
/*     */   
/*     */   private Icon field_94147_f;
/*     */   
/*     */   public void updateIcons(IconRegister par1IconRegister) {
/*  13 */     this.field_94147_f = par1IconRegister.registerIcon("itemframe_background");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82404_a(EntityItemFrame par1EntityItemFrame, double par2, double par4, double par6, float par8, float par9) {
/*  18 */     GL11.glPushMatrix();
/*  19 */     float var10 = (float)(par1EntityItemFrame.posX - par2) - 0.5F;
/*  20 */     float var11 = (float)(par1EntityItemFrame.posY - par4) - 0.5F;
/*  21 */     float var12 = (float)(par1EntityItemFrame.posZ - par6) - 0.5F;
/*  22 */     int var13 = par1EntityItemFrame.xPosition + Direction.offsetX[par1EntityItemFrame.hangingDirection];
/*  23 */     int var14 = par1EntityItemFrame.yPosition;
/*  24 */     int var15 = par1EntityItemFrame.zPosition + Direction.offsetZ[par1EntityItemFrame.hangingDirection];
/*  25 */     GL11.glTranslatef(var13 - var10, var14 - var11, var15 - var12);
/*  26 */     renderFrameItemAsBlock(par1EntityItemFrame);
/*  27 */     func_82402_b(par1EntityItemFrame);
/*  28 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110788_a(EntityItemFrame par1EntityItemFrame) {
/*  33 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFrameItemAsBlock(EntityItemFrame par1EntityItemFrame) {
/*  41 */     GL11.glPushMatrix();
/*  42 */     GL11.glRotatef(par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  43 */     this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/*  44 */     Block var2 = Block.planks;
/*  45 */     float var3 = 0.0625F;
/*  46 */     float var4 = 0.75F;
/*  47 */     float var5 = var4 / 2.0F;
/*  48 */     GL11.glPushMatrix();
/*  49 */     this.renderBlocksInstance.overrideBlockBounds(0.0D, (0.5F - var5 + 0.0625F), (0.5F - var5 + 0.0625F), (var3 * 0.5F), (0.5F + var5 - 0.0625F), (0.5F + var5 - 0.0625F));
/*  50 */     this.renderBlocksInstance.setOverrideBlockTexture(this.field_94147_f);
/*  51 */     this.renderBlocksInstance.renderBlockAsItem(var2, 0, 1.0F);
/*  52 */     this.renderBlocksInstance.clearOverrideBlockTexture();
/*  53 */     this.renderBlocksInstance.unlockBlockBounds();
/*  54 */     GL11.glPopMatrix();
/*  55 */     this.renderBlocksInstance.setOverrideBlockTexture(Block.planks.getIcon(1, 2));
/*  56 */     GL11.glPushMatrix();
/*  57 */     this.renderBlocksInstance.overrideBlockBounds(0.0D, (0.5F - var5), (0.5F - var5), (var3 + 1.0E-4F), (var3 + 0.5F - var5), (0.5F + var5));
/*  58 */     this.renderBlocksInstance.renderBlockAsItem(var2, 0, 1.0F);
/*  59 */     GL11.glPopMatrix();
/*  60 */     GL11.glPushMatrix();
/*  61 */     this.renderBlocksInstance.overrideBlockBounds(0.0D, (0.5F + var5 - var3), (0.5F - var5), (var3 + 1.0E-4F), (0.5F + var5), (0.5F + var5));
/*  62 */     this.renderBlocksInstance.renderBlockAsItem(var2, 0, 1.0F);
/*  63 */     GL11.glPopMatrix();
/*  64 */     GL11.glPushMatrix();
/*  65 */     this.renderBlocksInstance.overrideBlockBounds(0.0D, (0.5F - var5), (0.5F - var5), var3, (0.5F + var5), (var3 + 0.5F - var5));
/*  66 */     this.renderBlocksInstance.renderBlockAsItem(var2, 0, 1.0F);
/*  67 */     GL11.glPopMatrix();
/*  68 */     GL11.glPushMatrix();
/*  69 */     this.renderBlocksInstance.overrideBlockBounds(0.0D, (0.5F - var5), (0.5F + var5 - var3), var3, (0.5F + var5), (0.5F + var5));
/*  70 */     this.renderBlocksInstance.renderBlockAsItem(var2, 0, 1.0F);
/*  71 */     GL11.glPopMatrix();
/*  72 */     this.renderBlocksInstance.unlockBlockBounds();
/*  73 */     this.renderBlocksInstance.clearOverrideBlockTexture();
/*  74 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_82402_b(EntityItemFrame par1EntityItemFrame) {
/*  79 */     ItemStack var2 = par1EntityItemFrame.getDisplayedItem();
/*     */     
/*  81 */     if (var2 != null) {
/*     */       
/*  83 */       EntityItem var3 = new EntityItem(par1EntityItemFrame.worldObj, 0.0D, 0.0D, 0.0D, var2);
/*  84 */       (var3.getEntityItem()).stackSize = 1;
/*  85 */       var3.hoverStart = 0.0F;
/*  86 */       GL11.glPushMatrix();
/*  87 */       GL11.glTranslatef(-0.453125F * Direction.offsetX[par1EntityItemFrame.hangingDirection], -0.18F, -0.453125F * Direction.offsetZ[par1EntityItemFrame.hangingDirection]);
/*  88 */       GL11.glRotatef(180.0F + par1EntityItemFrame.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  89 */       GL11.glRotatef((-90 * par1EntityItemFrame.getRotation()), 0.0F, 0.0F, 1.0F);
/*     */       
/*  91 */       switch (par1EntityItemFrame.getRotation()) {
/*     */         
/*     */         case 1:
/*  94 */           GL11.glTranslatef(-0.16F, -0.16F, 0.0F);
/*     */           break;
/*     */         
/*     */         case 2:
/*  98 */           GL11.glTranslatef(0.0F, -0.32F, 0.0F);
/*     */           break;
/*     */         
/*     */         case 3:
/* 102 */           GL11.glTranslatef(0.16F, -0.16F, 0.0F);
/*     */           break;
/*     */       } 
/* 105 */       if (var3.getEntityItem().getItem() == Item.map) {
/*     */         
/* 107 */         this.renderManager.renderEngine.bindTexture(mapBackgroundTextures);
/* 108 */         Tessellator var4 = Tessellator.instance;
/* 109 */         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/* 110 */         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 111 */         GL11.glScalef(0.00390625F, 0.00390625F, 0.00390625F);
/* 112 */         GL11.glTranslatef(-65.0F, -107.0F, -3.0F);
/* 113 */         GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 114 */         var4.startDrawingQuads();
/* 115 */         byte var5 = 7;
/* 116 */         var4.addVertexWithUV((0 - var5), (128 + var5), 0.0D, 0.0D, 1.0D);
/* 117 */         var4.addVertexWithUV((128 + var5), (128 + var5), 0.0D, 1.0D, 1.0D);
/* 118 */         var4.addVertexWithUV((128 + var5), (0 - var5), 0.0D, 1.0D, 0.0D);
/* 119 */         var4.addVertexWithUV((0 - var5), (0 - var5), 0.0D, 0.0D, 0.0D);
/* 120 */         var4.draw();
/* 121 */         MapData var6 = Item.map.getMapData(var3.getEntityItem(), par1EntityItemFrame.worldObj);
/* 122 */         GL11.glTranslatef(0.0F, 0.0F, -1.0F);
/*     */         
/* 124 */         if (var6 != null)
/*     */         {
/* 126 */           this.renderManager.itemRenderer.mapItemRenderer.renderMap((EntityPlayer)null, this.renderManager.renderEngine, var6);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 131 */         if (var3.getEntityItem().getItem() == Item.compass) {
/*     */           
/* 133 */           TextureManager var11 = Minecraft.getMinecraft().getTextureManager();
/* 134 */           var11.bindTexture(TextureMap.locationItemsTexture);
/* 135 */           TextureAtlasSprite var13 = ((TextureMap)var11.getTexture(TextureMap.locationItemsTexture)).getAtlasSprite(Item.compass.getIconIndex(var3.getEntityItem()).getIconName());
/*     */           
/* 137 */           if (var13 instanceof TextureCompass) {
/*     */             
/* 139 */             TextureCompass var14 = (TextureCompass)var13;
/* 140 */             double var7 = var14.currentAngle;
/* 141 */             double var9 = var14.angleDelta;
/* 142 */             var14.currentAngle = 0.0D;
/* 143 */             var14.angleDelta = 0.0D;
/* 144 */             var14.updateCompass(par1EntityItemFrame.worldObj, par1EntityItemFrame.posX, par1EntityItemFrame.posZ, MathHelper.wrapAngleTo180_float((180 + par1EntityItemFrame.hangingDirection * 90)), false, true);
/* 145 */             var14.currentAngle = var7;
/* 146 */             var14.angleDelta = var9;
/*     */           } 
/*     */         } 
/*     */         
/* 150 */         RenderItem.renderInFrame = true;
/* 151 */         RenderManager.instance.renderEntityWithPosYaw(var3, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 152 */         RenderItem.renderInFrame = false;
/*     */         
/* 154 */         if (var3.getEntityItem().getItem() == Item.compass) {
/*     */           
/* 156 */           TextureAtlasSprite var12 = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationItemsTexture)).getAtlasSprite(Item.compass.getIconIndex(var3.getEntityItem()).getIconName());
/*     */           
/* 158 */           if (var12.getFrameCount() > 0)
/*     */           {
/* 160 */             var12.updateAnimation();
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 165 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 174 */     return func_110788_a((EntityItemFrame)par1Entity);
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
/* 185 */     func_82404_a((EntityItemFrame)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderItemFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */