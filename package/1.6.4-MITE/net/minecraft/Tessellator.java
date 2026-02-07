/*     */ package net.minecraft;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.opengl.ARBVertexBufferObject;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
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
/*     */ public class Tessellator
/*     */ {
/*     */   private static boolean convertQuadsToTriangles;
/*     */   private static boolean tryVBO;
/*     */   public ByteBuffer byteBuffer;
/*     */   public IntBuffer intBuffer;
/*     */   public FloatBuffer floatBuffer;
/*     */   public ShortBuffer shortBuffer;
/*     */   public int[] rawBuffer;
/*     */   public int vertexCount;
/*     */   public double textureU;
/*     */   public double textureV;
/*     */   public int brightness;
/*     */   public int color;
/*     */   public boolean hasColor;
/*     */   public boolean hasTexture;
/*     */   public boolean hasBrightness;
/*     */   public boolean hasNormals;
/*     */   public int rawBufferIndex;
/*     */   public int addedVertices;
/*     */   public boolean isColorDisabled;
/*     */   public int drawMode;
/*     */   public double xOffset;
/*     */   public double yOffset;
/*     */   public double zOffset;
/*     */   public int normal;
/*     */   public static Tessellator instance;
/*     */   public boolean isDrawing;
/*     */   private boolean useVBO;
/*     */   public IntBuffer vertexBuffers;
/*     */   private int vboIndex;
/* 149 */   private int vboCount = 10;
/*     */ 
/*     */   
/*     */   public static final int bufferSize = 2097152;
/*     */ 
/*     */   
/* 155 */   public static final boolean little_endian = (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN);
/*     */   
/*     */   public static final int buffer_size_minus_32 = 2097120;
/*     */   
/*     */   public boolean draw_in_groups = true;
/*     */ 
/*     */   
/*     */   public Tessellator() {
/* 163 */     int par1 = 2097152;
/*     */     
/* 165 */     this.byteBuffer = GLAllocation.createDirectByteBuffer(par1 * 4);
/* 166 */     this.intBuffer = this.byteBuffer.asIntBuffer();
/* 167 */     this.floatBuffer = this.byteBuffer.asFloatBuffer();
/* 168 */     this.shortBuffer = this.byteBuffer.asShortBuffer();
/* 169 */     this.rawBuffer = new int[par1];
/* 170 */     this.useVBO = (tryVBO && (GLContext.getCapabilities()).GL_ARB_vertex_buffer_object);
/*     */     
/* 172 */     if (this.useVBO) {
/*     */       
/* 174 */       this.vertexBuffers = GLAllocation.createDirectIntBuffer(this.vboCount);
/* 175 */       ARBVertexBufferObject.glGenBuffersARB(this.vertexBuffers);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int draw() {
/* 184 */     if (!this.isDrawing)
/*     */     {
/* 186 */       throw new IllegalStateException("Not tesselating!");
/*     */     }
/*     */ 
/*     */     
/* 190 */     this.isDrawing = false;
/*     */     
/* 192 */     if (this.vertexCount > 0) {
/*     */       
/* 194 */       this.intBuffer.clear();
/* 195 */       this.intBuffer.put(this.rawBuffer, 0, this.rawBufferIndex);
/* 196 */       this.byteBuffer.position(0);
/* 197 */       this.byteBuffer.limit(this.rawBufferIndex * 4);
/*     */       
/* 199 */       if (this.useVBO) {
/*     */         
/* 201 */         this.vboIndex = (this.vboIndex + 1) % this.vboCount;
/* 202 */         ARBVertexBufferObject.glBindBufferARB(34962, this.vertexBuffers.get(this.vboIndex));
/* 203 */         ARBVertexBufferObject.glBufferDataARB(34962, this.byteBuffer, 35040);
/*     */       } 
/*     */       
/* 206 */       if (this.hasTexture) {
/*     */         
/* 208 */         if (this.useVBO) {
/*     */           
/* 210 */           GL11.glTexCoordPointer(2, 5126, 32, 12L);
/*     */         }
/*     */         else {
/*     */           
/* 214 */           this.floatBuffer.position(3);
/* 215 */           GL11.glTexCoordPointer(2, 32, this.floatBuffer);
/*     */         } 
/*     */         
/* 218 */         GL11.glEnableClientState(32888);
/*     */       } 
/*     */       
/* 221 */       if (this.hasBrightness) {
/*     */         
/* 223 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/*     */         
/* 225 */         if (this.useVBO) {
/*     */           
/* 227 */           GL11.glTexCoordPointer(2, 5122, 32, 28L);
/*     */         }
/*     */         else {
/*     */           
/* 231 */           this.shortBuffer.position(14);
/* 232 */           GL11.glTexCoordPointer(2, 32, this.shortBuffer);
/*     */         } 
/*     */         
/* 235 */         GL11.glEnableClientState(32888);
/* 236 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */       } 
/*     */       
/* 239 */       if (this.hasColor) {
/*     */         
/* 241 */         if (this.useVBO) {
/*     */           
/* 243 */           GL11.glColorPointer(4, 5121, 32, 20L);
/*     */         }
/*     */         else {
/*     */           
/* 247 */           this.byteBuffer.position(20);
/* 248 */           GL11.glColorPointer(4, true, 32, this.byteBuffer);
/*     */         } 
/*     */         
/* 251 */         GL11.glEnableClientState(32886);
/*     */       } 
/*     */       
/* 254 */       if (this.hasNormals) {
/*     */         
/* 256 */         if (this.useVBO) {
/*     */           
/* 258 */           GL11.glNormalPointer(5121, 32, 24L);
/*     */         }
/*     */         else {
/*     */           
/* 262 */           this.byteBuffer.position(24);
/* 263 */           GL11.glNormalPointer(32, this.byteBuffer);
/*     */         } 
/*     */         
/* 266 */         GL11.glEnableClientState(32885);
/*     */       } 
/*     */       
/* 269 */       if (this.useVBO) {
/*     */         
/* 271 */         GL11.glVertexPointer(3, 5126, 32, 0L);
/*     */       }
/*     */       else {
/*     */         
/* 275 */         this.floatBuffer.position(0);
/* 276 */         GL11.glVertexPointer(3, 32, this.floatBuffer);
/*     */       } 
/*     */       
/* 279 */       GL11.glEnableClientState(32884);
/*     */       
/* 281 */       if (this.drawMode == 7 && convertQuadsToTriangles) {
/*     */         
/* 283 */         GL11.glDrawArrays(4, 0, this.vertexCount);
/*     */       }
/*     */       else {
/*     */         
/* 287 */         GL11.glDrawArrays(this.drawMode, 0, this.vertexCount);
/*     */       } 
/*     */       
/* 290 */       GL11.glDisableClientState(32884);
/*     */       
/* 292 */       if (this.hasTexture)
/*     */       {
/* 294 */         GL11.glDisableClientState(32888);
/*     */       }
/*     */       
/* 297 */       if (this.hasBrightness) {
/*     */         
/* 299 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 300 */         GL11.glDisableClientState(32888);
/* 301 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */       } 
/*     */       
/* 304 */       if (this.hasColor)
/*     */       {
/* 306 */         GL11.glDisableClientState(32886);
/*     */       }
/*     */       
/* 309 */       if (this.hasNormals)
/*     */       {
/* 311 */         GL11.glDisableClientState(32885);
/*     */       }
/*     */     } 
/*     */     
/* 315 */     int var1 = this.rawBufferIndex * 4;
/* 316 */     reset();
/* 317 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void reset() {
/* 327 */     this.vertexCount = 0;
/* 328 */     this.byteBuffer.clear();
/* 329 */     this.rawBufferIndex = 0;
/* 330 */     this.addedVertices = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void startDrawingQuads() {
/* 339 */     startDrawing(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void startDrawing(int par1) {
/* 348 */     if (this.isDrawing)
/*     */     {
/* 350 */       throw new IllegalStateException("Already tesselating!");
/*     */     }
/*     */ 
/*     */     
/* 354 */     this.isDrawing = true;
/* 355 */     reset();
/* 356 */     this.drawMode = par1;
/* 357 */     this.hasNormals = false;
/* 358 */     this.hasColor = false;
/* 359 */     this.hasTexture = false;
/* 360 */     this.hasBrightness = false;
/* 361 */     this.isColorDisabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setTextureUV(double par1, double par3) {
/* 371 */     this.hasTexture = true;
/* 372 */     this.textureU = par1;
/* 373 */     this.textureV = par3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setBrightness(int par1) {
/* 379 */     this.hasBrightness = true;
/* 380 */     this.brightness = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorOpaque_F(float par1, float par2, float par3) {
/* 388 */     setColorOpaque((int)(par1 * 255.0F), (int)(par2 * 255.0F), (int)(par3 * 255.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA_F(float par1, float par2, float par3, float par4) {
/* 396 */     setColorRGBA((int)(par1 * 255.0F), (int)(par2 * 255.0F), (int)(par3 * 255.0F), (int)(par4 * 255.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorOpaque(int par1, int par2, int par3) {
/* 404 */     setColorRGBA(par1, par2, par3, 255);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA(int par1, int par2, int par3, int par4) {
/* 412 */     if (!this.isColorDisabled) {
/*     */       
/* 414 */       if (par1 > 255)
/*     */       {
/* 416 */         par1 = 255;
/*     */       }
/*     */       
/* 419 */       if (par2 > 255)
/*     */       {
/* 421 */         par2 = 255;
/*     */       }
/*     */       
/* 424 */       if (par3 > 255)
/*     */       {
/* 426 */         par3 = 255;
/*     */       }
/*     */       
/* 429 */       if (par4 > 255)
/*     */       {
/* 431 */         par4 = 255;
/*     */       }
/*     */       
/* 434 */       if (par1 < 0)
/*     */       {
/* 436 */         par1 = 0;
/*     */       }
/*     */       
/* 439 */       if (par2 < 0)
/*     */       {
/* 441 */         par2 = 0;
/*     */       }
/*     */       
/* 444 */       if (par3 < 0)
/*     */       {
/* 446 */         par3 = 0;
/*     */       }
/*     */       
/* 449 */       if (par4 < 0)
/*     */       {
/* 451 */         par4 = 0;
/*     */       }
/*     */       
/* 454 */       this.hasColor = true;
/*     */       
/* 456 */       if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*     */         
/* 458 */         this.color = par4 << 24 | par3 << 16 | par2 << 8 | par1;
/*     */       }
/*     */       else {
/*     */         
/* 462 */         this.color = par1 << 24 | par2 << 16 | par3 << 8 | par4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void addVertexWithUV(double par1, double par3, double par5, double par7, double par9) {
/* 473 */     setTextureUV(par7, par9);
/* 474 */     addVertex(par1, par3, par5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVertex(double par1, double par3, double par5) {
/* 483 */     this.addedVertices++;
/*     */     
/* 485 */     if (this.drawMode == 7 && convertQuadsToTriangles && this.addedVertices % 4 == 0)
/*     */     {
/* 487 */       for (int var7 = 0; var7 < 2; var7++) {
/*     */         
/* 489 */         int var8 = 8 * (3 - var7);
/*     */         
/* 491 */         if (this.hasTexture) {
/*     */           
/* 493 */           this.rawBuffer[this.rawBufferIndex + 3] = this.rawBuffer[this.rawBufferIndex - var8 + 3];
/* 494 */           this.rawBuffer[this.rawBufferIndex + 4] = this.rawBuffer[this.rawBufferIndex - var8 + 4];
/*     */         } 
/*     */         
/* 497 */         if (this.hasBrightness)
/*     */         {
/* 499 */           this.rawBuffer[this.rawBufferIndex + 7] = this.rawBuffer[this.rawBufferIndex - var8 + 7];
/*     */         }
/*     */         
/* 502 */         if (this.hasColor)
/*     */         {
/* 504 */           this.rawBuffer[this.rawBufferIndex + 5] = this.rawBuffer[this.rawBufferIndex - var8 + 5];
/*     */         }
/*     */         
/* 507 */         this.rawBuffer[this.rawBufferIndex + 0] = this.rawBuffer[this.rawBufferIndex - var8 + 0];
/* 508 */         this.rawBuffer[this.rawBufferIndex + 1] = this.rawBuffer[this.rawBufferIndex - var8 + 1];
/* 509 */         this.rawBuffer[this.rawBufferIndex + 2] = this.rawBuffer[this.rawBufferIndex - var8 + 2];
/* 510 */         this.vertexCount++;
/* 511 */         this.rawBufferIndex += 8;
/*     */       } 
/*     */     }
/*     */     
/* 515 */     if (this.hasTexture) {
/*     */       
/* 517 */       this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)this.textureU);
/* 518 */       this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)this.textureV);
/*     */     } 
/*     */     
/* 521 */     if (this.hasBrightness)
/*     */     {
/* 523 */       this.rawBuffer[this.rawBufferIndex + 7] = this.brightness;
/*     */     }
/*     */     
/* 526 */     if (this.hasColor)
/*     */     {
/* 528 */       this.rawBuffer[this.rawBufferIndex + 5] = this.color;
/*     */     }
/*     */     
/* 531 */     if (this.hasNormals)
/*     */     {
/* 533 */       this.rawBuffer[this.rawBufferIndex + 6] = this.normal;
/*     */     }
/*     */     
/* 536 */     this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float)(par1 + this.xOffset));
/* 537 */     this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(par3 + this.yOffset));
/* 538 */     this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(par5 + this.zOffset));
/* 539 */     this.rawBufferIndex += 8;
/* 540 */     this.vertexCount++;
/*     */     
/* 542 */     this; if (this.vertexCount % 4 == 0 && this.rawBufferIndex >= 2097152 - 32) {
/*     */       
/* 544 */       draw();
/* 545 */       this.isDrawing = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorOpaque_I(int par1) {
/* 554 */     int var2 = par1 >> 16 & 0xFF;
/* 555 */     int var3 = par1 >> 8 & 0xFF;
/* 556 */     int var4 = par1 & 0xFF;
/* 557 */     setColorOpaque(var2, var3, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA_I(int par1, int par2) {
/* 565 */     int var3 = par1 >> 16 & 0xFF;
/* 566 */     int var4 = par1 >> 8 & 0xFF;
/* 567 */     int var5 = par1 & 0xFF;
/* 568 */     setColorRGBA(var3, var4, var5, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void disableColor() {
/* 577 */     this.isColorDisabled = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNormal(float par1, float par2, float par3) {
/* 586 */     this.hasNormals = true;
/* 587 */     byte var4 = (byte)(int)(par1 * 127.0F);
/* 588 */     byte var5 = (byte)(int)(par2 * 127.0F);
/* 589 */     byte var6 = (byte)(int)(par3 * 127.0F);
/* 590 */     this.normal = var4 & 0xFF | (var5 & 0xFF) << 8 | (var6 & 0xFF) << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setTranslation(double par1, double par3, double par5) {
/* 599 */     this.xOffset = par1;
/* 600 */     this.yOffset = par3;
/* 601 */     this.zOffset = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void addTranslation(float par1, float par2, float par3) {
/* 610 */     this.xOffset += par1;
/* 611 */     this.yOffset += par2;
/* 612 */     this.zOffset += par3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add4VerticesWithUV(double[] x, double[] y, double[] z, double[] u, double[] v) {
/* 617 */     Minecraft.setErrorMessage("Tessellator: vanilla does not support add4VerticesWithUV");
/*     */   }
/*     */ 
/*     */   
/*     */   public void add4VerticesWithUVandAO(double[] x, double[] y, double[] z, double[] u, double[] v, float[] r, float[] g, float[] b, int[] brightness) {
/* 622 */     Minecraft.setErrorMessage("Tessellator: vanilla does not support add4VerticesWithUVandAO");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Tessellator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */