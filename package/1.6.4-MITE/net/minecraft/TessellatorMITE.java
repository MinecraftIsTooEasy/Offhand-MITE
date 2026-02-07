/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public final class TessellatorMITE
/*     */   extends Tessellator
/*     */ {
/*     */   public int draw() {
/* 177 */     if (RenderingScheme.current == 101) {
/* 178 */       return super.draw();
/*     */     }
/* 180 */     if (!this.isDrawing)
/*     */     {
/* 182 */       throw new IllegalStateException("Not tesselating!");
/*     */     }
/*     */ 
/*     */     
/* 186 */     this.isDrawing = false;
/*     */     
/* 188 */     if (this.vertexCount > 0) {
/*     */       
/* 190 */       this.intBuffer.clear();
/* 191 */       this.intBuffer.put(this.rawBuffer, 0, this.rawBufferIndex);
/* 192 */       this.byteBuffer.position(0);
/* 193 */       this.byteBuffer.limit(this.rawBufferIndex * 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       if (this.hasTexture) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 210 */         this.floatBuffer.position(3);
/* 211 */         GL11.glTexCoordPointer(2, 32, this.floatBuffer);
/*     */ 
/*     */         
/* 214 */         GL11.glEnableClientState(32888);
/*     */       } 
/*     */       
/* 217 */       if (this.hasBrightness) {
/*     */         
/* 219 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 227 */         this.shortBuffer.position(14);
/* 228 */         GL11.glTexCoordPointer(2, 32, this.shortBuffer);
/*     */ 
/*     */         
/* 231 */         GL11.glEnableClientState(32888);
/* 232 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */       } 
/*     */       
/* 235 */       if (this.hasColor) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 243 */         this.byteBuffer.position(20);
/* 244 */         GL11.glColorPointer(4, true, 32, this.byteBuffer);
/*     */ 
/*     */         
/* 247 */         GL11.glEnableClientState(32886);
/*     */       } 
/*     */       
/* 250 */       if (this.hasNormals) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 258 */         this.byteBuffer.position(24);
/* 259 */         GL11.glNormalPointer(32, this.byteBuffer);
/*     */ 
/*     */         
/* 262 */         GL11.glEnableClientState(32885);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 271 */       this.floatBuffer.position(0);
/* 272 */       GL11.glVertexPointer(3, 32, this.floatBuffer);
/*     */ 
/*     */       
/* 275 */       GL11.glEnableClientState(32884);
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
/* 291 */       if (this.draw_in_groups) {
/*     */         
/* 293 */         int group_size = 400;
/*     */         
/* 295 */         int i = 0;
/*     */         
/* 297 */         while (i < this.vertexCount - group_size) {
/*     */           
/* 299 */           GL11.glDrawArrays(this.drawMode, i, group_size);
/* 300 */           i += group_size;
/*     */         } 
/*     */         
/* 303 */         GL11.glDrawArrays(this.drawMode, i, this.vertexCount - i);
/*     */       }
/*     */       else {
/*     */         
/* 307 */         GL11.glDrawArrays(this.drawMode, 0, this.vertexCount);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 315 */       GL11.glDisableClientState(32884);
/*     */       
/* 317 */       if (this.hasTexture)
/*     */       {
/* 319 */         GL11.glDisableClientState(32888);
/*     */       }
/*     */       
/* 322 */       if (this.hasBrightness) {
/*     */         
/* 324 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 325 */         GL11.glDisableClientState(32888);
/* 326 */         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */       } 
/*     */       
/* 329 */       if (this.hasColor)
/*     */       {
/* 331 */         GL11.glDisableClientState(32886);
/*     */       }
/*     */       
/* 334 */       if (this.hasNormals)
/*     */       {
/* 336 */         GL11.glDisableClientState(32885);
/*     */       }
/*     */     } 
/*     */     
/* 340 */     int var1 = this.rawBufferIndex * 4;
/* 341 */     reset();
/* 342 */     return var1;
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
/*     */   public void setColorOpaque_F(float par1, float par2, float par3) {
/* 412 */     if (RenderingScheme.current == 102) {
/*     */       
/* 414 */       super.setColorOpaque_F(par1, par2, par3);
/*     */       
/*     */       return;
/*     */     } 
/* 418 */     if (!this.isColorDisabled) {
/*     */       
/* 420 */       this.hasColor = true;
/*     */ 
/*     */       
/* 423 */       if (little_endian) {
/*     */         
/* 425 */         this.color = 0xFF000000 | (int)(par3 * 255.0F) << 16 | (int)(par2 * 255.0F) << 8 | (int)(par1 * 255.0F);
/*     */       }
/*     */       else {
/*     */         
/* 429 */         this.color = (int)(par1 * 255.0F) << 24 | (int)(par2 * 255.0F) << 16 | (int)(par3 * 255.0F) << 8 | 0xFF;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA_F(float par1, float par2, float par3, float par4) {
/* 441 */     if (RenderingScheme.current == 102) {
/*     */       
/* 443 */       super.setColorRGBA_F(par1, par2, par3, par4);
/*     */       
/*     */       return;
/*     */     } 
/* 447 */     if (!this.isColorDisabled) {
/*     */       
/* 449 */       this.hasColor = true;
/*     */ 
/*     */       
/* 452 */       if (little_endian) {
/*     */         
/* 454 */         this.color = (int)(par4 * 255.0F) << 24 | (int)(par3 * 255.0F) << 16 | (int)(par2 * 255.0F) << 8 | (int)(par1 * 255.0F);
/*     */       }
/*     */       else {
/*     */         
/* 458 */         this.color = (int)(par1 * 255.0F) << 24 | (int)(par2 * 255.0F) << 16 | (int)(par3 * 255.0F) << 8 | (int)(par4 * 255.0F);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorRGBA(int par1, int par2, int par3, int par4) {
/* 476 */     if (RenderingScheme.current == 102) {
/*     */       
/* 478 */       super.setColorRGBA(par1, par2, par3, par4);
/*     */       
/*     */       return;
/*     */     } 
/* 482 */     if (!this.isColorDisabled) {
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
/* 524 */       this.hasColor = true;
/*     */ 
/*     */       
/* 527 */       if (little_endian) {
/*     */         
/* 529 */         this.color = par4 << 24 | par3 << 16 | par2 << 8 | par1;
/*     */       }
/*     */       else {
/*     */         
/* 533 */         this.color = par1 << 24 | par2 << 16 | par3 << 8 | par4;
/*     */       } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void add4VerticesWithUVandAO(double[] x, double[] y, double[] z, double[] u, double[] v, float[] r, float[] g, float[] b, int[] brightness) {
/* 549 */     this.hasTexture = true;
/* 550 */     this.hasBrightness = true;
/*     */     
/* 552 */     if (!this.isColorDisabled) {
/*     */       
/* 554 */       this.hasColor = true;
/*     */       
/* 556 */       if (little_endian) {
/*     */         
/* 558 */         this.rawBuffer[this.rawBufferIndex + 5] = 0xFF000000 | (int)(b[0] * 255.0F) << 16 | (int)(g[0] * 255.0F) << 8 | (int)(r[0] * 255.0F);
/* 559 */         this.rawBuffer[this.rawBufferIndex + 13] = 0xFF000000 | (int)(b[1] * 255.0F) << 16 | (int)(g[1] * 255.0F) << 8 | (int)(r[1] * 255.0F);
/* 560 */         this.rawBuffer[this.rawBufferIndex + 21] = 0xFF000000 | (int)(b[2] * 255.0F) << 16 | (int)(g[2] * 255.0F) << 8 | (int)(r[2] * 255.0F);
/* 561 */         this.rawBuffer[this.rawBufferIndex + 29] = 0xFF000000 | (int)(b[3] * 255.0F) << 16 | (int)(g[3] * 255.0F) << 8 | (int)(r[3] * 255.0F);
/*     */       }
/*     */       else {
/*     */         
/* 565 */         this.rawBuffer[this.rawBufferIndex + 5] = (int)(r[0] * 255.0F) << 24 | (int)(g[0] * 255.0F) << 16 | (int)(b[0] * 255.0F) << 8 | 0xFF;
/* 566 */         this.rawBuffer[this.rawBufferIndex + 13] = (int)(r[1] * 255.0F) << 24 | (int)(g[1] * 255.0F) << 16 | (int)(b[1] * 255.0F) << 8 | 0xFF;
/* 567 */         this.rawBuffer[this.rawBufferIndex + 21] = (int)(r[2] * 255.0F) << 24 | (int)(g[2] * 255.0F) << 16 | (int)(b[2] * 255.0F) << 8 | 0xFF;
/* 568 */         this.rawBuffer[this.rawBufferIndex + 29] = (int)(r[3] * 255.0F) << 24 | (int)(g[3] * 255.0F) << 16 | (int)(b[3] * 255.0F) << 8 | 0xFF;
/*     */       } 
/*     */     } 
/*     */     
/* 572 */     this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)u[0]);
/* 573 */     this.rawBuffer[this.rawBufferIndex + 11] = Float.floatToRawIntBits((float)u[1]);
/* 574 */     this.rawBuffer[this.rawBufferIndex + 19] = Float.floatToRawIntBits((float)u[2]);
/* 575 */     this.rawBuffer[this.rawBufferIndex + 27] = Float.floatToRawIntBits((float)u[3]);
/*     */     
/* 577 */     this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)v[0]);
/* 578 */     this.rawBuffer[this.rawBufferIndex + 12] = Float.floatToRawIntBits((float)v[1]);
/* 579 */     this.rawBuffer[this.rawBufferIndex + 20] = Float.floatToRawIntBits((float)v[2]);
/* 580 */     this.rawBuffer[this.rawBufferIndex + 28] = Float.floatToRawIntBits((float)v[3]);
/*     */     
/* 582 */     this.rawBuffer[this.rawBufferIndex + 7] = brightness[0];
/* 583 */     this.rawBuffer[this.rawBufferIndex + 15] = brightness[1];
/* 584 */     this.rawBuffer[this.rawBufferIndex + 23] = brightness[2];
/* 585 */     this.rawBuffer[this.rawBufferIndex + 31] = brightness[3];
/*     */     
/* 587 */     if (this.hasNormals) {
/*     */       
/* 589 */       this.rawBuffer[this.rawBufferIndex + 6] = this.normal;
/* 590 */       this.rawBuffer[this.rawBufferIndex + 14] = this.normal;
/* 591 */       this.rawBuffer[this.rawBufferIndex + 22] = this.normal;
/* 592 */       this.rawBuffer[this.rawBufferIndex + 30] = this.normal;
/*     */     } 
/*     */     
/* 595 */     this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float)(x[0] + this.xOffset));
/* 596 */     this.rawBuffer[this.rawBufferIndex + 8] = Float.floatToRawIntBits((float)(x[1] + this.xOffset));
/* 597 */     this.rawBuffer[this.rawBufferIndex + 16] = Float.floatToRawIntBits((float)(x[2] + this.xOffset));
/* 598 */     this.rawBuffer[this.rawBufferIndex + 24] = Float.floatToRawIntBits((float)(x[3] + this.xOffset));
/*     */     
/* 600 */     this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(y[0] + this.yOffset));
/* 601 */     this.rawBuffer[this.rawBufferIndex + 9] = Float.floatToRawIntBits((float)(y[1] + this.yOffset));
/* 602 */     this.rawBuffer[this.rawBufferIndex + 17] = Float.floatToRawIntBits((float)(y[2] + this.yOffset));
/* 603 */     this.rawBuffer[this.rawBufferIndex + 25] = Float.floatToRawIntBits((float)(y[3] + this.yOffset));
/*     */     
/* 605 */     this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(z[0] + this.zOffset));
/* 606 */     this.rawBuffer[this.rawBufferIndex + 10] = Float.floatToRawIntBits((float)(z[1] + this.zOffset));
/* 607 */     this.rawBuffer[this.rawBufferIndex + 18] = Float.floatToRawIntBits((float)(z[2] + this.zOffset));
/* 608 */     this.rawBuffer[this.rawBufferIndex + 26] = Float.floatToRawIntBits((float)(z[3] + this.zOffset));
/*     */     
/* 610 */     this.rawBufferIndex += 32;
/*     */     
/* 612 */     this.addedVertices += 4;
/* 613 */     this.vertexCount += 4;
/*     */     
/* 615 */     if (this.rawBufferIndex >= 2097120) {
/*     */       
/* 617 */       draw();
/* 618 */       this.isDrawing = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void add4VerticesWithUV(double[] x, double[] y, double[] z, double[] u, double[] v) {
/* 624 */     this.hasTexture = true;
/*     */     
/* 626 */     this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)u[0]);
/* 627 */     this.rawBuffer[this.rawBufferIndex + 11] = Float.floatToRawIntBits((float)u[1]);
/* 628 */     this.rawBuffer[this.rawBufferIndex + 19] = Float.floatToRawIntBits((float)u[2]);
/* 629 */     this.rawBuffer[this.rawBufferIndex + 27] = Float.floatToRawIntBits((float)u[3]);
/*     */     
/* 631 */     this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)v[0]);
/* 632 */     this.rawBuffer[this.rawBufferIndex + 12] = Float.floatToRawIntBits((float)v[1]);
/* 633 */     this.rawBuffer[this.rawBufferIndex + 20] = Float.floatToRawIntBits((float)v[2]);
/* 634 */     this.rawBuffer[this.rawBufferIndex + 28] = Float.floatToRawIntBits((float)v[3]);
/*     */     
/* 636 */     if (this.hasBrightness) {
/*     */       
/* 638 */       this.rawBuffer[this.rawBufferIndex + 7] = this.brightness;
/* 639 */       this.rawBuffer[this.rawBufferIndex + 15] = this.brightness;
/* 640 */       this.rawBuffer[this.rawBufferIndex + 23] = this.brightness;
/* 641 */       this.rawBuffer[this.rawBufferIndex + 31] = this.brightness;
/*     */     } 
/*     */     
/* 644 */     if (this.hasColor) {
/*     */       
/* 646 */       this.rawBuffer[this.rawBufferIndex + 5] = this.color;
/* 647 */       this.rawBuffer[this.rawBufferIndex + 13] = this.color;
/* 648 */       this.rawBuffer[this.rawBufferIndex + 21] = this.color;
/* 649 */       this.rawBuffer[this.rawBufferIndex + 29] = this.color;
/*     */     } 
/*     */     
/* 652 */     if (this.hasNormals) {
/*     */       
/* 654 */       this.rawBuffer[this.rawBufferIndex + 6] = this.normal;
/* 655 */       this.rawBuffer[this.rawBufferIndex + 14] = this.normal;
/* 656 */       this.rawBuffer[this.rawBufferIndex + 22] = this.normal;
/* 657 */       this.rawBuffer[this.rawBufferIndex + 30] = this.normal;
/*     */     } 
/*     */     
/* 660 */     this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float)(x[0] + this.xOffset));
/* 661 */     this.rawBuffer[this.rawBufferIndex + 8] = Float.floatToRawIntBits((float)(x[1] + this.xOffset));
/* 662 */     this.rawBuffer[this.rawBufferIndex + 16] = Float.floatToRawIntBits((float)(x[2] + this.xOffset));
/* 663 */     this.rawBuffer[this.rawBufferIndex + 24] = Float.floatToRawIntBits((float)(x[3] + this.xOffset));
/*     */     
/* 665 */     this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(y[0] + this.yOffset));
/* 666 */     this.rawBuffer[this.rawBufferIndex + 9] = Float.floatToRawIntBits((float)(y[1] + this.yOffset));
/* 667 */     this.rawBuffer[this.rawBufferIndex + 17] = Float.floatToRawIntBits((float)(y[2] + this.yOffset));
/* 668 */     this.rawBuffer[this.rawBufferIndex + 25] = Float.floatToRawIntBits((float)(y[3] + this.yOffset));
/*     */     
/* 670 */     this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(z[0] + this.zOffset));
/* 671 */     this.rawBuffer[this.rawBufferIndex + 10] = Float.floatToRawIntBits((float)(z[1] + this.zOffset));
/* 672 */     this.rawBuffer[this.rawBufferIndex + 18] = Float.floatToRawIntBits((float)(z[2] + this.zOffset));
/* 673 */     this.rawBuffer[this.rawBufferIndex + 26] = Float.floatToRawIntBits((float)(z[3] + this.zOffset));
/*     */     
/* 675 */     this.rawBufferIndex += 32;
/*     */     
/* 677 */     this.addedVertices += 4;
/* 678 */     this.vertexCount += 4;
/*     */     
/* 680 */     if (this.rawBufferIndex >= 2097120) {
/*     */       
/* 682 */       draw();
/* 683 */       this.isDrawing = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVertex(double par1, double par3, double par5) {
/* 693 */     if (RenderingScheme.current == 102) {
/*     */       
/* 695 */       super.addVertex(par1, par3, par5);
/*     */       
/*     */       return;
/*     */     } 
/* 699 */     this.addedVertices++;
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
/* 731 */     if (this.hasTexture) {
/*     */       
/* 733 */       this.rawBuffer[this.rawBufferIndex + 3] = Float.floatToRawIntBits((float)this.textureU);
/* 734 */       this.rawBuffer[this.rawBufferIndex + 4] = Float.floatToRawIntBits((float)this.textureV);
/*     */     } 
/*     */     
/* 737 */     if (this.hasBrightness)
/*     */     {
/* 739 */       this.rawBuffer[this.rawBufferIndex + 7] = this.brightness;
/*     */     }
/*     */     
/* 742 */     if (this.hasColor)
/*     */     {
/* 744 */       this.rawBuffer[this.rawBufferIndex + 5] = this.color;
/*     */     }
/*     */     
/* 747 */     if (this.hasNormals)
/*     */     {
/* 749 */       this.rawBuffer[this.rawBufferIndex + 6] = this.normal;
/*     */     }
/*     */     
/* 752 */     this.rawBuffer[this.rawBufferIndex + 0] = Float.floatToRawIntBits((float)(par1 + this.xOffset));
/* 753 */     this.rawBuffer[this.rawBufferIndex + 1] = Float.floatToRawIntBits((float)(par3 + this.yOffset));
/* 754 */     this.rawBuffer[this.rawBufferIndex + 2] = Float.floatToRawIntBits((float)(par5 + this.zOffset));
/* 755 */     this.rawBufferIndex += 8;
/* 756 */     this.vertexCount++;
/*     */ 
/*     */     
/* 759 */     this; if (this.vertexCount % 4 == 0 && this.rawBufferIndex >= 2097120) {
/*     */       
/* 761 */       draw();
/* 762 */       this.isDrawing = true;
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
/*     */ 
/*     */   
/*     */   public void setColorOpaque_I(int par1) {
/* 776 */     if (RenderingScheme.current == 102) {
/*     */       
/* 778 */       super.setColorOpaque_I(par1);
/*     */       
/*     */       return;
/*     */     } 
/* 782 */     setColorRGBA(par1 >> 16 & 0xFF, par1 >> 8 & 0xFF, par1 & 0xFF, 255);
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
/*     */   public void setColorRGBA_I(int par1, int par2) {
/* 795 */     if (RenderingScheme.current == 102) {
/*     */       
/* 797 */       super.setColorRGBA_I(par1, par2);
/*     */       
/*     */       return;
/*     */     } 
/* 801 */     setColorRGBA(par1 >> 16 & 0xFF, par1 >> 8 & 0xFF, par1 & 0xFF, par2);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TessellatorMITE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */