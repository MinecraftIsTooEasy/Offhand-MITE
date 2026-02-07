/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderPainting
/*     */   extends Render
/*     */ {
/*   8 */   private static final ResourceLocation field_110807_a = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");
/*     */ 
/*     */   
/*     */   public void renderThePainting(EntityPainting par1EntityPainting, double par2, double par4, double par6, float par8, float par9) {
/*  12 */     GL11.glPushMatrix();
/*  13 */     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/*  14 */     GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
/*  15 */     GL11.glEnable(32826);
/*     */     
/*  17 */     EnumArt var10 = par1EntityPainting.art;
/*  18 */     float var11 = 0.0625F;
/*  19 */     GL11.glScalef(var11, var11, var11);
/*  20 */     func_77010_a(par1EntityPainting, var10.sizeX, var10.sizeY, var10.offsetX, var10.offsetY);
/*  21 */     GL11.glDisable(32826);
/*  22 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110806_a(EntityPainting par1EntityPainting) {
/*  28 */     return (par1EntityPainting.art.special_texture == null) ? field_110807_a : par1EntityPainting.art.special_texture;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_77010_a(EntityPainting par1EntityPainting, int par2, int par3, int par4, int par5) {
/*  33 */     float var6 = -par2 / 2.0F;
/*  34 */     float var7 = -par3 / 2.0F;
/*  35 */     float var8 = 0.5F;
/*  36 */     float var9 = 0.75F;
/*  37 */     float var10 = 0.8125F;
/*  38 */     float var11 = 0.0F;
/*  39 */     float var12 = 0.0625F;
/*  40 */     float var13 = 0.75F;
/*  41 */     float var14 = 0.8125F;
/*  42 */     float var15 = 0.001953125F;
/*  43 */     float var16 = 0.001953125F;
/*  44 */     float var17 = 0.7519531F;
/*  45 */     float var18 = 0.7519531F;
/*  46 */     float var19 = 0.0F;
/*  47 */     float var20 = 0.0625F;
/*     */     
/*  49 */     for (int var21 = 0; var21 < par2 / 16; var21++) {
/*     */       
/*  51 */       for (int var22 = 0; var22 < par3 / 16; var22++) {
/*     */         
/*  53 */         float var23 = var6 + ((var21 + 1) * 16);
/*  54 */         float var24 = var6 + (var21 * 16);
/*  55 */         float var25 = var7 + ((var22 + 1) * 16);
/*  56 */         float var26 = var7 + (var22 * 16);
/*  57 */         func_77008_a(par1EntityPainting, (var23 + var24) / 2.0F, (var25 + var26) / 2.0F);
/*  58 */         float var27 = (par4 + par2 - var21 * 16) / 256.0F;
/*  59 */         float var28 = (par4 + par2 - (var21 + 1) * 16) / 256.0F;
/*  60 */         float var29 = (par5 + par3 - var22 * 16) / 256.0F;
/*  61 */         float var30 = (par5 + par3 - (var22 + 1) * 16) / 256.0F;
/*     */         
/*  63 */         if (par1EntityPainting.art.special_texture != null) {
/*     */           
/*  65 */           var27 = (par4 + par2 - var21 * 16) / par2;
/*  66 */           var28 = (par4 + par2 - (var21 + 1) * 16) / par2;
/*  67 */           var29 = (par5 + par3 - var22 * 16) / par3;
/*  68 */           var30 = (par5 + par3 - (var22 + 1) * 16) / par3;
/*     */         } 
/*     */         
/*  71 */         Tessellator var31 = Tessellator.instance;
/*  72 */         bindEntityTexture(par1EntityPainting);
/*  73 */         var31.startDrawingQuads();
/*  74 */         var31.setNormal(0.0F, 0.0F, -1.0F);
/*  75 */         var31.addVertexWithUV(var23, var26, -var8, var28, var29);
/*  76 */         var31.addVertexWithUV(var24, var26, -var8, var27, var29);
/*  77 */         var31.addVertexWithUV(var24, var25, -var8, var27, var30);
/*  78 */         var31.addVertexWithUV(var23, var25, -var8, var28, var30);
/*  79 */         var31.draw();
/*     */         
/*  81 */         bindTexture(field_110807_a);
/*  82 */         var31.startDrawingQuads();
/*  83 */         var31.setNormal(0.0F, 0.0F, 1.0F);
/*  84 */         var31.addVertexWithUV(var23, var25, var8, var9, var11);
/*  85 */         var31.addVertexWithUV(var24, var25, var8, var10, var11);
/*  86 */         var31.addVertexWithUV(var24, var26, var8, var10, var12);
/*  87 */         var31.addVertexWithUV(var23, var26, var8, var9, var12);
/*  88 */         var31.setNormal(0.0F, 1.0F, 0.0F);
/*  89 */         var31.addVertexWithUV(var23, var25, -var8, var13, var15);
/*  90 */         var31.addVertexWithUV(var24, var25, -var8, var14, var15);
/*  91 */         var31.addVertexWithUV(var24, var25, var8, var14, var16);
/*  92 */         var31.addVertexWithUV(var23, var25, var8, var13, var16);
/*  93 */         var31.setNormal(0.0F, -1.0F, 0.0F);
/*  94 */         var31.addVertexWithUV(var23, var26, var8, var13, var15);
/*  95 */         var31.addVertexWithUV(var24, var26, var8, var14, var15);
/*  96 */         var31.addVertexWithUV(var24, var26, -var8, var14, var16);
/*  97 */         var31.addVertexWithUV(var23, var26, -var8, var13, var16);
/*  98 */         var31.setNormal(-1.0F, 0.0F, 0.0F);
/*  99 */         var31.addVertexWithUV(var23, var25, var8, var18, var19);
/* 100 */         var31.addVertexWithUV(var23, var26, var8, var18, var20);
/* 101 */         var31.addVertexWithUV(var23, var26, -var8, var17, var20);
/* 102 */         var31.addVertexWithUV(var23, var25, -var8, var17, var19);
/* 103 */         var31.setNormal(1.0F, 0.0F, 0.0F);
/* 104 */         var31.addVertexWithUV(var24, var25, -var8, var18, var19);
/* 105 */         var31.addVertexWithUV(var24, var26, -var8, var18, var20);
/* 106 */         var31.addVertexWithUV(var24, var26, var8, var17, var20);
/* 107 */         var31.addVertexWithUV(var24, var25, var8, var17, var19);
/* 108 */         var31.draw();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_77008_a(EntityPainting par1EntityPainting, float par2, float par3) {
/* 115 */     int var4 = MathHelper.floor_double(par1EntityPainting.posX);
/* 116 */     int var5 = MathHelper.floor_double(par1EntityPainting.posY + (par3 / 16.0F));
/* 117 */     int var6 = MathHelper.floor_double(par1EntityPainting.posZ);
/*     */     
/* 119 */     if (par1EntityPainting.hangingDirection == 2)
/*     */     {
/* 121 */       var4 = MathHelper.floor_double(par1EntityPainting.posX + (par2 / 16.0F));
/*     */     }
/*     */     
/* 124 */     if (par1EntityPainting.hangingDirection == 1)
/*     */     {
/* 126 */       var6 = MathHelper.floor_double(par1EntityPainting.posZ - (par2 / 16.0F));
/*     */     }
/*     */     
/* 129 */     if (par1EntityPainting.hangingDirection == 0)
/*     */     {
/* 131 */       var4 = MathHelper.floor_double(par1EntityPainting.posX - (par2 / 16.0F));
/*     */     }
/*     */     
/* 134 */     if (par1EntityPainting.hangingDirection == 3)
/*     */     {
/* 136 */       var6 = MathHelper.floor_double(par1EntityPainting.posZ + (par2 / 16.0F));
/*     */     }
/*     */     
/* 139 */     int var7 = this.renderManager.worldObj.getLightBrightnessForSkyBlocks(var4, var5, var6, 0);
/*     */     
/* 141 */     int var8 = var7 % 65536;
/* 142 */     int var9 = var7 / 65536;
/* 143 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var8, var9);
/* 144 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity par1Entity) {
/* 152 */     return func_110806_a((EntityPainting)par1Entity);
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
/* 163 */     renderThePainting((EntityPainting)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderPainting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */