/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Gui
/*     */ {
/*   7 */   public static final ResourceLocation optionsBackground = new ResourceLocation("textures/gui/options_background.png");
/*   8 */   public static final ResourceLocation statIcons = new ResourceLocation("textures/gui/container/stats_icons.png");
/*   9 */   public static final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
/*     */   
/*     */   protected float zLevel;
/*     */   
/*     */   protected void drawHorizontalLine(int par1, int par2, int par3, int par4) {
/*  14 */     if (par2 < par1) {
/*     */       
/*  16 */       int var5 = par1;
/*  17 */       par1 = par2;
/*  18 */       par2 = var5;
/*     */     } 
/*     */     
/*  21 */     drawRect(par1, par3, par2 + 1, par3 + 1, par4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawVerticalLine(int par1, int par2, int par3, int par4) {
/*  26 */     if (par3 < par2) {
/*     */       
/*  28 */       int var5 = par2;
/*  29 */       par2 = par3;
/*  30 */       par3 = var5;
/*     */     } 
/*     */     
/*  33 */     drawRect(par1, par2 + 1, par1 + 1, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawRect(int par0, int par1, int par2, int par3, int par4) {
/*  43 */     if (par0 < par2) {
/*     */       
/*  45 */       int var5 = par0;
/*  46 */       par0 = par2;
/*  47 */       par2 = var5;
/*     */     } 
/*     */     
/*  50 */     if (par1 < par3) {
/*     */       
/*  52 */       int var5 = par1;
/*  53 */       par1 = par3;
/*  54 */       par3 = var5;
/*     */     } 
/*     */     
/*  57 */     float var10 = (par4 >> 24 & 0xFF) / 255.0F;
/*  58 */     float var6 = (par4 >> 16 & 0xFF) / 255.0F;
/*  59 */     float var7 = (par4 >> 8 & 0xFF) / 255.0F;
/*  60 */     float var8 = (par4 & 0xFF) / 255.0F;
/*  61 */     Tessellator var9 = Tessellator.instance;
/*  62 */     GL11.glEnable(3042);
/*  63 */     GL11.glDisable(3553);
/*  64 */     GL11.glBlendFunc(770, 771);
/*  65 */     GL11.glColor4f(var6, var7, var8, var10);
/*  66 */     var9.startDrawingQuads();
/*  67 */     var9.addVertex(par0, par3, 0.0D);
/*  68 */     var9.addVertex(par2, par3, 0.0D);
/*  69 */     var9.addVertex(par2, par1, 0.0D);
/*  70 */     var9.addVertex(par0, par1, 0.0D);
/*  71 */     var9.draw();
/*  72 */     GL11.glEnable(3553);
/*  73 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6) {
/*  81 */     float var7 = (par5 >> 24 & 0xFF) / 255.0F;
/*  82 */     float var8 = (par5 >> 16 & 0xFF) / 255.0F;
/*  83 */     float var9 = (par5 >> 8 & 0xFF) / 255.0F;
/*  84 */     float var10 = (par5 & 0xFF) / 255.0F;
/*  85 */     float var11 = (par6 >> 24 & 0xFF) / 255.0F;
/*  86 */     float var12 = (par6 >> 16 & 0xFF) / 255.0F;
/*  87 */     float var13 = (par6 >> 8 & 0xFF) / 255.0F;
/*  88 */     float var14 = (par6 & 0xFF) / 255.0F;
/*  89 */     GL11.glDisable(3553);
/*  90 */     GL11.glEnable(3042);
/*  91 */     GL11.glDisable(3008);
/*  92 */     GL11.glBlendFunc(770, 771);
/*  93 */     GL11.glShadeModel(7425);
/*  94 */     Tessellator var15 = Tessellator.instance;
/*  95 */     var15.startDrawingQuads();
/*  96 */     var15.setColorRGBA_F(var8, var9, var10, var7);
/*  97 */     var15.addVertex(par3, par2, this.zLevel);
/*  98 */     var15.addVertex(par1, par2, this.zLevel);
/*  99 */     var15.setColorRGBA_F(var12, var13, var14, var11);
/* 100 */     var15.addVertex(par1, par4, this.zLevel);
/* 101 */     var15.addVertex(par3, par4, this.zLevel);
/* 102 */     var15.draw();
/* 103 */     GL11.glShadeModel(7424);
/* 104 */     GL11.glDisable(3042);
/* 105 */     GL11.glEnable(3008);
/* 106 */     GL11.glEnable(3553);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCenteredString(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5) {
/* 114 */     par1FontRenderer.drawStringWithShadow(par2Str, par3 - par1FontRenderer.getStringWidth(par2Str) / 2, par4, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawString(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5) {
/* 122 */     par1FontRenderer.drawStringWithShadow(par2Str, par3, par4, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 130 */     float var7 = 0.00390625F;
/* 131 */     float var8 = 0.00390625F;
/* 132 */     Tessellator var9 = Tessellator.instance;
/* 133 */     var9.startDrawingQuads();
/* 134 */     var9.addVertexWithUV((par1 + 0), (par2 + par6), this.zLevel, ((par3 + 0) * var7), ((par4 + par6) * var8));
/* 135 */     var9.addVertexWithUV((par1 + par5), (par2 + par6), this.zLevel, ((par3 + par5) * var7), ((par4 + par6) * var8));
/* 136 */     var9.addVertexWithUV((par1 + par5), (par2 + 0), this.zLevel, ((par3 + par5) * var7), ((par4 + 0) * var8));
/* 137 */     var9.addVertexWithUV((par1 + 0), (par2 + 0), this.zLevel, ((par3 + 0) * var7), ((par4 + 0) * var8));
/* 138 */     var9.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawTexturedModalRect2(int par1, int par2, int par5, int par6) {
/* 143 */     Tessellator var9 = Tessellator.instance;
/* 144 */     var9.startDrawingQuads();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     var9.addVertexWithUV(par1, (par2 + par6), this.zLevel, 0.0D, 1.0D);
/* 151 */     var9.addVertexWithUV((par1 + par5), (par2 + par6), this.zLevel, 1.0D, 1.0D);
/* 152 */     var9.addVertexWithUV((par1 + par5), par2, this.zLevel, 1.0D, 0.0D);
/* 153 */     var9.addVertexWithUV(par1, par2, this.zLevel, 0.0D, 0.0D);
/*     */     
/* 155 */     var9.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawTexturedModelRectFromIcon(int par1, int par2, Icon par3Icon, int par4, int par5) {
/* 160 */     Tessellator var6 = Tessellator.instance;
/* 161 */     var6.startDrawingQuads();
/* 162 */     var6.addVertexWithUV((par1 + 0), (par2 + par5), this.zLevel, par3Icon.getMinU(), par3Icon.getMaxV());
/* 163 */     var6.addVertexWithUV((par1 + par4), (par2 + par5), this.zLevel, par3Icon.getMaxU(), par3Icon.getMaxV());
/* 164 */     var6.addVertexWithUV((par1 + par4), (par2 + 0), this.zLevel, par3Icon.getMaxU(), par3Icon.getMinV());
/* 165 */     var6.addVertexWithUV((par1 + 0), (par2 + 0), this.zLevel, par3Icon.getMinU(), par3Icon.getMinV());
/* 166 */     var6.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Gui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */