/*     */ package net.minecraft;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public final class ClippingHelperImpl
/*     */   extends ClippingHelper
/*     */ {
/*   9 */   private static ClippingHelperImpl instance = new ClippingHelperImpl();
/*  10 */   private FloatBuffer projectionMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
/*  11 */   private FloatBuffer modelviewMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
/*  12 */   private FloatBuffer field_78564_h = GLAllocation.createDirectFloatBuffer(16);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ClippingHelper getInstance() {
/*  19 */     instance.init();
/*  20 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void normalize(float[][] par1ArrayOfFloat, int par2) {
/*  28 */     float var3 = MathHelper.sqrt_float(par1ArrayOfFloat[par2][0] * par1ArrayOfFloat[par2][0] + par1ArrayOfFloat[par2][1] * par1ArrayOfFloat[par2][1] + par1ArrayOfFloat[par2][2] * par1ArrayOfFloat[par2][2]);
/*  29 */     par1ArrayOfFloat[par2][0] = par1ArrayOfFloat[par2][0] / var3;
/*  30 */     par1ArrayOfFloat[par2][1] = par1ArrayOfFloat[par2][1] / var3;
/*  31 */     par1ArrayOfFloat[par2][2] = par1ArrayOfFloat[par2][2] / var3;
/*  32 */     par1ArrayOfFloat[par2][3] = par1ArrayOfFloat[par2][3] / var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private void init() {
/*  37 */     this.projectionMatrixBuffer.clear();
/*  38 */     this.modelviewMatrixBuffer.clear();
/*  39 */     this.field_78564_h.clear();
/*  40 */     GL11.glGetFloat(2983, this.projectionMatrixBuffer);
/*  41 */     GL11.glGetFloat(2982, this.modelviewMatrixBuffer);
/*  42 */     this.projectionMatrixBuffer.flip().limit(16);
/*  43 */     this.projectionMatrixBuffer.get(this.projectionMatrix);
/*  44 */     this.modelviewMatrixBuffer.flip().limit(16);
/*  45 */     this.modelviewMatrixBuffer.get(this.modelviewMatrix);
/*  46 */     this.clippingMatrix[0] = this.modelviewMatrix[0] * this.projectionMatrix[0] + this.modelviewMatrix[1] * this.projectionMatrix[4] + this.modelviewMatrix[2] * this.projectionMatrix[8] + this.modelviewMatrix[3] * this.projectionMatrix[12];
/*  47 */     this.clippingMatrix[1] = this.modelviewMatrix[0] * this.projectionMatrix[1] + this.modelviewMatrix[1] * this.projectionMatrix[5] + this.modelviewMatrix[2] * this.projectionMatrix[9] + this.modelviewMatrix[3] * this.projectionMatrix[13];
/*  48 */     this.clippingMatrix[2] = this.modelviewMatrix[0] * this.projectionMatrix[2] + this.modelviewMatrix[1] * this.projectionMatrix[6] + this.modelviewMatrix[2] * this.projectionMatrix[10] + this.modelviewMatrix[3] * this.projectionMatrix[14];
/*  49 */     this.clippingMatrix[3] = this.modelviewMatrix[0] * this.projectionMatrix[3] + this.modelviewMatrix[1] * this.projectionMatrix[7] + this.modelviewMatrix[2] * this.projectionMatrix[11] + this.modelviewMatrix[3] * this.projectionMatrix[15];
/*  50 */     this.clippingMatrix[4] = this.modelviewMatrix[4] * this.projectionMatrix[0] + this.modelviewMatrix[5] * this.projectionMatrix[4] + this.modelviewMatrix[6] * this.projectionMatrix[8] + this.modelviewMatrix[7] * this.projectionMatrix[12];
/*  51 */     this.clippingMatrix[5] = this.modelviewMatrix[4] * this.projectionMatrix[1] + this.modelviewMatrix[5] * this.projectionMatrix[5] + this.modelviewMatrix[6] * this.projectionMatrix[9] + this.modelviewMatrix[7] * this.projectionMatrix[13];
/*  52 */     this.clippingMatrix[6] = this.modelviewMatrix[4] * this.projectionMatrix[2] + this.modelviewMatrix[5] * this.projectionMatrix[6] + this.modelviewMatrix[6] * this.projectionMatrix[10] + this.modelviewMatrix[7] * this.projectionMatrix[14];
/*  53 */     this.clippingMatrix[7] = this.modelviewMatrix[4] * this.projectionMatrix[3] + this.modelviewMatrix[5] * this.projectionMatrix[7] + this.modelviewMatrix[6] * this.projectionMatrix[11] + this.modelviewMatrix[7] * this.projectionMatrix[15];
/*  54 */     this.clippingMatrix[8] = this.modelviewMatrix[8] * this.projectionMatrix[0] + this.modelviewMatrix[9] * this.projectionMatrix[4] + this.modelviewMatrix[10] * this.projectionMatrix[8] + this.modelviewMatrix[11] * this.projectionMatrix[12];
/*  55 */     this.clippingMatrix[9] = this.modelviewMatrix[8] * this.projectionMatrix[1] + this.modelviewMatrix[9] * this.projectionMatrix[5] + this.modelviewMatrix[10] * this.projectionMatrix[9] + this.modelviewMatrix[11] * this.projectionMatrix[13];
/*  56 */     this.clippingMatrix[10] = this.modelviewMatrix[8] * this.projectionMatrix[2] + this.modelviewMatrix[9] * this.projectionMatrix[6] + this.modelviewMatrix[10] * this.projectionMatrix[10] + this.modelviewMatrix[11] * this.projectionMatrix[14];
/*  57 */     this.clippingMatrix[11] = this.modelviewMatrix[8] * this.projectionMatrix[3] + this.modelviewMatrix[9] * this.projectionMatrix[7] + this.modelviewMatrix[10] * this.projectionMatrix[11] + this.modelviewMatrix[11] * this.projectionMatrix[15];
/*  58 */     this.clippingMatrix[12] = this.modelviewMatrix[12] * this.projectionMatrix[0] + this.modelviewMatrix[13] * this.projectionMatrix[4] + this.modelviewMatrix[14] * this.projectionMatrix[8] + this.modelviewMatrix[15] * this.projectionMatrix[12];
/*  59 */     this.clippingMatrix[13] = this.modelviewMatrix[12] * this.projectionMatrix[1] + this.modelviewMatrix[13] * this.projectionMatrix[5] + this.modelviewMatrix[14] * this.projectionMatrix[9] + this.modelviewMatrix[15] * this.projectionMatrix[13];
/*  60 */     this.clippingMatrix[14] = this.modelviewMatrix[12] * this.projectionMatrix[2] + this.modelviewMatrix[13] * this.projectionMatrix[6] + this.modelviewMatrix[14] * this.projectionMatrix[10] + this.modelviewMatrix[15] * this.projectionMatrix[14];
/*  61 */     this.clippingMatrix[15] = this.modelviewMatrix[12] * this.projectionMatrix[3] + this.modelviewMatrix[13] * this.projectionMatrix[7] + this.modelviewMatrix[14] * this.projectionMatrix[11] + this.modelviewMatrix[15] * this.projectionMatrix[15];
/*  62 */     this.frustum[0][0] = this.clippingMatrix[3] - this.clippingMatrix[0];
/*  63 */     this.frustum[0][1] = this.clippingMatrix[7] - this.clippingMatrix[4];
/*  64 */     this.frustum[0][2] = this.clippingMatrix[11] - this.clippingMatrix[8];
/*  65 */     this.frustum[0][3] = this.clippingMatrix[15] - this.clippingMatrix[12];
/*  66 */     normalize(this.frustum, 0);
/*  67 */     this.frustum[1][0] = this.clippingMatrix[3] + this.clippingMatrix[0];
/*  68 */     this.frustum[1][1] = this.clippingMatrix[7] + this.clippingMatrix[4];
/*  69 */     this.frustum[1][2] = this.clippingMatrix[11] + this.clippingMatrix[8];
/*  70 */     this.frustum[1][3] = this.clippingMatrix[15] + this.clippingMatrix[12];
/*  71 */     normalize(this.frustum, 1);
/*  72 */     this.frustum[2][0] = this.clippingMatrix[3] + this.clippingMatrix[1];
/*  73 */     this.frustum[2][1] = this.clippingMatrix[7] + this.clippingMatrix[5];
/*  74 */     this.frustum[2][2] = this.clippingMatrix[11] + this.clippingMatrix[9];
/*  75 */     this.frustum[2][3] = this.clippingMatrix[15] + this.clippingMatrix[13];
/*  76 */     normalize(this.frustum, 2);
/*  77 */     this.frustum[3][0] = this.clippingMatrix[3] - this.clippingMatrix[1];
/*  78 */     this.frustum[3][1] = this.clippingMatrix[7] - this.clippingMatrix[5];
/*  79 */     this.frustum[3][2] = this.clippingMatrix[11] - this.clippingMatrix[9];
/*  80 */     this.frustum[3][3] = this.clippingMatrix[15] - this.clippingMatrix[13];
/*  81 */     normalize(this.frustum, 3);
/*  82 */     this.frustum[4][0] = this.clippingMatrix[3] - this.clippingMatrix[2];
/*  83 */     this.frustum[4][1] = this.clippingMatrix[7] - this.clippingMatrix[6];
/*  84 */     this.frustum[4][2] = this.clippingMatrix[11] - this.clippingMatrix[10];
/*  85 */     this.frustum[4][3] = this.clippingMatrix[15] - this.clippingMatrix[14];
/*  86 */     normalize(this.frustum, 4);
/*  87 */     this.frustum[5][0] = this.clippingMatrix[3] + this.clippingMatrix[2];
/*  88 */     this.frustum[5][1] = this.clippingMatrix[7] + this.clippingMatrix[6];
/*  89 */     this.frustum[5][2] = this.clippingMatrix[11] + this.clippingMatrix[10];
/*  90 */     this.frustum[5][3] = this.clippingMatrix[15] + this.clippingMatrix[14];
/*  91 */     normalize(this.frustum, 5);
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.frustum_0_0 = this.frustum[0][0];
/*  96 */     this.frustum_0_1 = this.frustum[0][1];
/*  97 */     this.frustum_0_2 = this.frustum[0][2];
/*  98 */     this.frustum_0_3 = this.frustum[0][3];
/*     */     
/* 100 */     this.frustum_1_0 = this.frustum[1][0];
/* 101 */     this.frustum_1_1 = this.frustum[1][1];
/* 102 */     this.frustum_1_2 = this.frustum[1][2];
/* 103 */     this.frustum_1_3 = this.frustum[1][3];
/*     */     
/* 105 */     this.frustum_2_0 = this.frustum[2][0];
/* 106 */     this.frustum_2_1 = this.frustum[2][1];
/* 107 */     this.frustum_2_2 = this.frustum[2][2];
/* 108 */     this.frustum_2_3 = this.frustum[2][3];
/*     */     
/* 110 */     this.frustum_3_0 = this.frustum[3][0];
/* 111 */     this.frustum_3_1 = this.frustum[3][1];
/* 112 */     this.frustum_3_2 = this.frustum[3][2];
/* 113 */     this.frustum_3_3 = this.frustum[3][3];
/*     */     
/* 115 */     this.frustum_4_0 = this.frustum[4][0];
/* 116 */     this.frustum_4_1 = this.frustum[4][1];
/* 117 */     this.frustum_4_2 = this.frustum[4][2];
/* 118 */     this.frustum_4_3 = this.frustum[4][3];
/*     */     
/* 120 */     this.frustum_5_0 = this.frustum[5][0];
/* 121 */     this.frustum_5_1 = this.frustum[5][1];
/* 122 */     this.frustum_5_2 = this.frustum[5][2];
/* 123 */     this.frustum_5_3 = this.frustum[5][3];
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ClippingHelperImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */