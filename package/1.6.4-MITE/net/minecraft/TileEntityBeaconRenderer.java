/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityBeaconRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  11 */   private static final ResourceLocation field_110629_a = new ResourceLocation("textures/entity/beacon_beam.png");
/*     */ 
/*     */   
/*     */   public void renderTileEntityBeaconAt(TileEntityBeacon tileEntityBeacon, double d, double e, double f, float g) {
/*  15 */     float f1 = tileEntityBeacon.func_82125_v_();
/*     */     
/*  17 */     if (f1 > 0.0F) {
/*  18 */       Tessellator tessellator = Tessellator.instance;
/*     */       
/*  20 */       bindTexture(field_110629_a);
/*  21 */       GL11.glTexParameterf(3553, 10242, 10497.0F);
/*  22 */       GL11.glTexParameterf(3553, 10243, 10497.0F);
/*  23 */       GL11.glDisable(2896);
/*  24 */       GL11.glDisable(2884);
/*  25 */       GL11.glDisable(3042);
/*  26 */       GL11.glDepthMask(true);
/*  27 */       GL11.glBlendFunc(770, 1);
/*     */       
/*  29 */       float f2 = (float)tileEntityBeacon.getWorldObj().getTotalWorldTime() + g;
/*  30 */       float f3 = -f2 * 0.2F - MathHelper.floor_float(-f2 * 0.1F);
/*     */ 
/*     */       
/*  33 */       boolean bool = true;
/*     */       
/*  35 */       double d2 = f2 * 0.025D * (1.0D - (bool & true) * 2.5D);
/*     */       
/*  37 */       tessellator.startDrawingQuads();
/*  38 */       tessellator.setColorRGBA(255, 255, 255, 32);
/*     */       
/*  40 */       double d4 = bool * 0.2D;
/*     */       
/*  42 */       double d6 = 0.5D + Math.cos(d2 + 2.356194490192345D) * d4;
/*  43 */       double d8 = 0.5D + Math.sin(d2 + 2.356194490192345D) * d4;
/*  44 */       double d10 = 0.5D + Math.cos(d2 + 0.7853981633974483D) * d4;
/*  45 */       double d12 = 0.5D + Math.sin(d2 + 0.7853981633974483D) * d4;
/*     */       
/*  47 */       double d14 = 0.5D + Math.cos(d2 + 3.9269908169872414D) * d4;
/*  48 */       double d16 = 0.5D + Math.sin(d2 + 3.9269908169872414D) * d4;
/*  49 */       double d18 = 0.5D + Math.cos(d2 + 5.497787143782138D) * d4;
/*  50 */       double d20 = 0.5D + Math.sin(d2 + 5.497787143782138D) * d4;
/*     */       
/*  52 */       double d22 = (256.0F * f1);
/*     */       
/*  54 */       double d24 = 0.0D;
/*  55 */       double d26 = 1.0D;
/*  56 */       double d27 = (-1.0F + f3);
/*  57 */       double d28 = (256.0F * f1) * 0.5D / d4 + d27;
/*     */       
/*  59 */       tessellator.addVertexWithUV(d + d6, e + d22, f + d8, d26, d28);
/*  60 */       tessellator.addVertexWithUV(d + d6, e, f + d8, d26, d27);
/*  61 */       tessellator.addVertexWithUV(d + d10, e, f + d12, d24, d27);
/*  62 */       tessellator.addVertexWithUV(d + d10, e + d22, f + d12, d24, d28);
/*     */       
/*  64 */       tessellator.addVertexWithUV(d + d18, e + d22, f + d20, d26, d28);
/*  65 */       tessellator.addVertexWithUV(d + d18, e, f + d20, d26, d27);
/*  66 */       tessellator.addVertexWithUV(d + d14, e, f + d16, d24, d27);
/*  67 */       tessellator.addVertexWithUV(d + d14, e + d22, f + d16, d24, d28);
/*     */       
/*  69 */       tessellator.addVertexWithUV(d + d10, e + d22, f + d12, d26, d28);
/*  70 */       tessellator.addVertexWithUV(d + d10, e, f + d12, d26, d27);
/*  71 */       tessellator.addVertexWithUV(d + d18, e, f + d20, d24, d27);
/*  72 */       tessellator.addVertexWithUV(d + d18, e + d22, f + d20, d24, d28);
/*     */       
/*  74 */       tessellator.addVertexWithUV(d + d14, e + d22, f + d16, d26, d28);
/*  75 */       tessellator.addVertexWithUV(d + d14, e, f + d16, d26, d27);
/*  76 */       tessellator.addVertexWithUV(d + d6, e, f + d8, d24, d27);
/*  77 */       tessellator.addVertexWithUV(d + d6, e + d22, f + d8, d24, d28);
/*     */       
/*  79 */       tessellator.draw();
/*     */ 
/*     */       
/*  82 */       GL11.glEnable(3042);
/*  83 */       GL11.glBlendFunc(770, 771);
/*  84 */       GL11.glDepthMask(false);
/*     */ 
/*     */       
/*  87 */       tessellator.startDrawingQuads();
/*  88 */       tessellator.setColorRGBA(255, 255, 255, 32);
/*     */       
/*  90 */       double d1 = 0.2D;
/*  91 */       double d3 = 0.2D;
/*  92 */       double d5 = 0.8D;
/*  93 */       double d7 = 0.2D;
/*     */       
/*  95 */       double d9 = 0.2D;
/*  96 */       double d11 = 0.8D;
/*  97 */       double d13 = 0.8D;
/*  98 */       double d15 = 0.8D;
/*     */       
/* 100 */       double d17 = (256.0F * f1);
/*     */       
/* 102 */       double d19 = 0.0D;
/* 103 */       double d21 = 1.0D;
/* 104 */       double d23 = (-1.0F + f3);
/* 105 */       double d25 = (256.0F * f1) + d23;
/*     */       
/* 107 */       tessellator.addVertexWithUV(d + d1, e + d17, f + d3, d21, d25);
/* 108 */       tessellator.addVertexWithUV(d + d1, e, f + d3, d21, d23);
/* 109 */       tessellator.addVertexWithUV(d + d5, e, f + d7, d19, d23);
/* 110 */       tessellator.addVertexWithUV(d + d5, e + d17, f + d7, d19, d25);
/*     */       
/* 112 */       tessellator.addVertexWithUV(d + d13, e + d17, f + d15, d21, d25);
/* 113 */       tessellator.addVertexWithUV(d + d13, e, f + d15, d21, d23);
/* 114 */       tessellator.addVertexWithUV(d + d9, e, f + d11, d19, d23);
/* 115 */       tessellator.addVertexWithUV(d + d9, e + d17, f + d11, d19, d25);
/*     */       
/* 117 */       tessellator.addVertexWithUV(d + d5, e + d17, f + d7, d21, d25);
/* 118 */       tessellator.addVertexWithUV(d + d5, e, f + d7, d21, d23);
/* 119 */       tessellator.addVertexWithUV(d + d13, e, f + d15, d19, d23);
/* 120 */       tessellator.addVertexWithUV(d + d13, e + d17, f + d15, d19, d25);
/*     */       
/* 122 */       tessellator.addVertexWithUV(d + d9, e + d17, f + d11, d21, d25);
/* 123 */       tessellator.addVertexWithUV(d + d9, e, f + d11, d21, d23);
/* 124 */       tessellator.addVertexWithUV(d + d1, e, f + d3, d19, d23);
/* 125 */       tessellator.addVertexWithUV(d + d1, e + d17, f + d3, d19, d25);
/*     */       
/* 127 */       tessellator.draw();
/*     */ 
/*     */       
/* 130 */       GL11.glEnable(2896);
/* 131 */       GL11.glEnable(3553);
/*     */       
/* 133 */       GL11.glDepthMask(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TileEntityBeaconRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */