/*     */ package net.minecraft;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Random;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderEndPortal
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  17 */   private static final ResourceLocation enderPortalEndSkyTextures = new ResourceLocation("textures/environment/end_sky.png");
/*  18 */   private static final ResourceLocation endPortalTextures = new ResourceLocation("textures/entity/end_portal.png");
/*     */   
/*  20 */   private static final Random field_110644_e = new Random(31100L);
/*     */ 
/*     */   
/*     */   public void renderEndPortalTileEntity(TileEntityEndPortal tileEntityEndPortal, double d, double e, double f, float g) {
/*  24 */     float f1 = (float)this.tileEntityRenderer.playerX;
/*  25 */     float f2 = (float)this.tileEntityRenderer.playerY;
/*  26 */     float f3 = (float)this.tileEntityRenderer.playerZ;
/*     */     
/*  28 */     GL11.glDisable(2896);
/*     */     
/*  30 */     field_110644_e.setSeed(31100L);
/*     */     
/*  32 */     float f4 = 0.75F;
/*  33 */     for (byte b = 0; b < 16; b++) {
/*  34 */       GL11.glPushMatrix();
/*     */       
/*  36 */       float f5 = (16 - b);
/*  37 */       float f6 = 0.0625F;
/*     */       
/*  39 */       float f7 = 1.0F / (f5 + 1.0F);
/*  40 */       if (b == 0) {
/*  41 */         bindTexture(enderPortalEndSkyTextures);
/*  42 */         f7 = 0.1F;
/*  43 */         f5 = 65.0F;
/*  44 */         f6 = 0.125F;
/*  45 */         GL11.glEnable(3042);
/*  46 */         GL11.glBlendFunc(770, 771);
/*     */       } 
/*  48 */       if (b == 1) {
/*  49 */         bindTexture(endPortalTextures);
/*  50 */         GL11.glEnable(3042);
/*  51 */         GL11.glBlendFunc(1, 1);
/*  52 */         f6 = 0.5F;
/*     */       } 
/*     */       
/*  55 */       float f8 = (float)-(e + f4);
/*     */       
/*  57 */       float f9 = f8 + ActiveRenderInfo.objectY;
/*  58 */       float f10 = f8 + f5 + ActiveRenderInfo.objectY;
/*  59 */       float f11 = f9 / f10;
/*  60 */       f11 = (float)(e + f4) + f11;
/*     */       
/*  62 */       GL11.glTranslatef(f1, f11, f3);
/*     */       
/*  64 */       GL11.glTexGeni(8192, 9472, 9217);
/*  65 */       GL11.glTexGeni(8193, 9472, 9217);
/*  66 */       GL11.glTexGeni(8194, 9472, 9217);
/*  67 */       GL11.glTexGeni(8195, 9472, 9216);
/*  68 */       GL11.glTexGen(8192, 9473, func_76907_a(1.0F, 0.0F, 0.0F, 0.0F));
/*  69 */       GL11.glTexGen(8193, 9473, func_76907_a(0.0F, 0.0F, 1.0F, 0.0F));
/*  70 */       GL11.glTexGen(8194, 9473, func_76907_a(0.0F, 0.0F, 0.0F, 1.0F));
/*  71 */       GL11.glTexGen(8195, 9474, func_76907_a(0.0F, 1.0F, 0.0F, 0.0F));
/*  72 */       GL11.glEnable(3168);
/*  73 */       GL11.glEnable(3169);
/*  74 */       GL11.glEnable(3170);
/*  75 */       GL11.glEnable(3171);
/*     */       
/*  77 */       GL11.glPopMatrix();
/*  78 */       GL11.glMatrixMode(5890);
/*     */       
/*  80 */       GL11.glPushMatrix();
/*  81 */       GL11.glLoadIdentity();
/*     */       
/*  83 */       GL11.glTranslatef(0.0F, (float)(Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
/*  84 */       GL11.glScalef(f6, f6, f6);
/*  85 */       GL11.glTranslatef(0.5F, 0.5F, 0.0F);
/*  86 */       GL11.glRotatef((b * b * 4321 + b * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
/*  87 */       GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/*  88 */       GL11.glTranslatef(-f1, -f3, -f2);
/*  89 */       f9 = f8 + ActiveRenderInfo.objectY;
/*  90 */       GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
/*     */       
/*  92 */       Tessellator tessellator = Tessellator.instance;
/*  93 */       tessellator.startDrawingQuads();
/*     */       
/*  95 */       f11 = field_110644_e.nextFloat() * 0.5F + 0.1F;
/*  96 */       float f12 = field_110644_e.nextFloat() * 0.5F + 0.4F;
/*  97 */       float f13 = field_110644_e.nextFloat() * 0.5F + 0.5F;
/*  98 */       if (b == 0) f11 = f12 = f13 = 1.0F; 
/*  99 */       tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0F);
/* 100 */       tessellator.addVertex(d, e + f4, f);
/* 101 */       tessellator.addVertex(d, e + f4, f + 1.0D);
/* 102 */       tessellator.addVertex(d + 1.0D, e + f4, f + 1.0D);
/* 103 */       tessellator.addVertex(d + 1.0D, e + f4, f);
/* 104 */       tessellator.draw();
/*     */       
/* 106 */       GL11.glPopMatrix();
/* 107 */       GL11.glMatrixMode(5888);
/*     */     } 
/* 109 */     GL11.glDisable(3042);
/*     */     
/* 111 */     GL11.glDisable(3168);
/* 112 */     GL11.glDisable(3169);
/* 113 */     GL11.glDisable(3170);
/* 114 */     GL11.glDisable(3171);
/* 115 */     GL11.glEnable(2896);
/*     */   }
/*     */   
/* 118 */   FloatBuffer field_76908_a = GLAllocation.createDirectFloatBuffer(16);
/*     */   
/*     */   private FloatBuffer func_76907_a(float f, float g, float h, float i) {
/* 121 */     this.field_76908_a.clear();
/* 122 */     this.field_76908_a.put(f).put(g).put(h).put(i);
/* 123 */     this.field_76908_a.flip();
/* 124 */     return this.field_76908_a;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RenderEndPortal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */