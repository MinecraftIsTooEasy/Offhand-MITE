/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public class TextureAtlasSprite
/*     */   implements Icon {
/*     */   private final String iconName;
/*  15 */   protected List framesTextureData = Lists.newArrayList();
/*     */   
/*     */   private AnimationMetadataSection animationMetadata;
/*     */   
/*     */   protected boolean rotated;
/*     */   protected int originX;
/*     */   protected int originY;
/*     */   protected int width;
/*     */   protected int height;
/*     */   private float minU;
/*     */   private float maxU;
/*     */   private float minV;
/*     */   private float maxV;
/*     */   protected int frameCounter;
/*     */   protected int tickCounter;
/*     */   private final boolean is_green_grass_side;
/*     */   
/*     */   protected TextureAtlasSprite(String par1Str) {
/*  33 */     this.iconName = par1Str;
/*     */     
/*  35 */     this.is_green_grass_side = "grass_side".equals(par1Str);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initSprite(int par1, int par2, int par3, int par4, boolean par5) {
/*  40 */     this.originX = par3;
/*  41 */     this.originY = par4;
/*  42 */     this.rotated = par5;
/*  43 */     float var6 = (float)(0.009999999776482582D / par1);
/*  44 */     float var7 = (float)(0.009999999776482582D / par2);
/*  45 */     this.minU = par3 / (float)par1 + var6;
/*  46 */     this.maxU = (par3 + this.width) / (float)par1 - var6;
/*  47 */     this.minV = par4 / par2 + var7;
/*  48 */     this.maxV = (par4 + this.height) / par2 - var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyFrom(TextureAtlasSprite par1TextureAtlasSprite) {
/*  53 */     this.originX = par1TextureAtlasSprite.originX;
/*  54 */     this.originY = par1TextureAtlasSprite.originY;
/*  55 */     this.width = par1TextureAtlasSprite.width;
/*  56 */     this.height = par1TextureAtlasSprite.height;
/*  57 */     this.rotated = par1TextureAtlasSprite.rotated;
/*  58 */     this.minU = par1TextureAtlasSprite.minU;
/*  59 */     this.maxU = par1TextureAtlasSprite.maxU;
/*  60 */     this.minV = par1TextureAtlasSprite.minV;
/*  61 */     this.maxV = par1TextureAtlasSprite.maxV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getOriginX() {
/*  70 */     return this.originX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getOriginY() {
/*  79 */     return this.originY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIconWidth() {
/*  88 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIconHeight() {
/*  97 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getMinU() {
/* 106 */     return this.minU;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getMaxU() {
/* 115 */     return this.maxU;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getInterpolatedU(double par1) {
/* 124 */     float var3 = this.maxU - this.minU;
/* 125 */     return this.minU + var3 * (float)par1 / 16.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getMinV() {
/* 134 */     return this.minV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getMaxV() {
/* 143 */     return this.maxV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getInterpolatedV(double par1) {
/* 152 */     float var3 = this.maxV - this.minV;
/* 153 */     return this.minV + var3 * (float)par1 / 16.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getIconName() {
/* 158 */     return this.iconName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/* 163 */     this.tickCounter++;
/*     */     
/* 165 */     if (this.tickCounter >= this.animationMetadata.getFrameTimeSingle(this.frameCounter)) {
/*     */       
/* 167 */       int var1 = this.animationMetadata.getFrameIndex(this.frameCounter);
/* 168 */       int var2 = (this.animationMetadata.getFrameCount() == 0) ? this.framesTextureData.size() : this.animationMetadata.getFrameCount();
/* 169 */       this.frameCounter = (this.frameCounter + 1) % var2;
/* 170 */       this.tickCounter = 0;
/* 171 */       int var3 = this.animationMetadata.getFrameIndex(this.frameCounter);
/*     */       
/* 173 */       if (var1 != var3 && var3 >= 0 && var3 < this.framesTextureData.size())
/*     */       {
/* 175 */         TextureUtil.uploadTextureSub(this.framesTextureData.get(var3), this.width, this.height, this.originX, this.originY, false, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int[] getFrameTextureData(int par1) {
/* 183 */     return this.framesTextureData.get(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getFrameCount() {
/* 189 */     return this.framesTextureData.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIconWidth(int par1) {
/* 195 */     this.width = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIconHeight(int par1) {
/* 201 */     this.height = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void loadSprite(Resource par1Resource) throws IOException {
/* 207 */     resetSprite();
/* 208 */     InputStream var2 = par1Resource.getInputStream();
/* 209 */     AnimationMetadataSection var3 = (AnimationMetadataSection)par1Resource.getMetadata("animation");
/* 210 */     BufferedImage var4 = ImageIO.read(var2);
/* 211 */     this.height = var4.getHeight();
/* 212 */     this.width = var4.getWidth();
/* 213 */     int[] var5 = new int[this.height * this.width];
/* 214 */     var4.getRGB(0, 0, this.width, this.height, var5, 0, this.width);
/*     */     
/* 216 */     if (var3 == null) {
/*     */       
/* 218 */       if (this.height != this.width)
/*     */       {
/* 220 */         throw new RuntimeException("broken aspect ratio and not an animation");
/*     */       }
/*     */       
/* 223 */       this.framesTextureData.add(var5);
/*     */     }
/*     */     else {
/*     */       
/* 227 */       int var6 = this.height / this.width;
/* 228 */       int var7 = this.width;
/* 229 */       int var8 = this.width;
/* 230 */       this.height = this.width;
/*     */ 
/*     */       
/* 233 */       if (var3.getFrameCount() > 0) {
/*     */         
/* 235 */         Iterator<Integer> var9 = var3.getFrameIndexSet().iterator();
/*     */         
/* 237 */         while (var9.hasNext()) {
/*     */           
/* 239 */           int var10 = ((Integer)var9.next()).intValue();
/*     */           
/* 241 */           if (var10 >= var6)
/*     */           {
/* 243 */             throw new RuntimeException("invalid frameindex " + var10);
/*     */           }
/*     */           
/* 246 */           allocateFrameTextureData(var10);
/* 247 */           this.framesTextureData.set(var10, getFrameTextureData(var5, var7, var8, var10));
/*     */         } 
/*     */         
/* 250 */         this.animationMetadata = var3;
/*     */       }
/*     */       else {
/*     */         
/* 254 */         ArrayList<AnimationFrame> var11 = Lists.newArrayList();
/*     */         
/* 256 */         for (int var10 = 0; var10 < var6; var10++) {
/*     */           
/* 258 */           this.framesTextureData.add(getFrameTextureData(var5, var7, var8, var10));
/* 259 */           var11.add(new AnimationFrame(var10, -1));
/*     */         } 
/*     */         
/* 262 */         this.animationMetadata = new AnimationMetadataSection(var11, this.width, this.height, var3.getFrameTime());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void allocateFrameTextureData(int par1) {
/* 270 */     if (this.framesTextureData.size() <= par1)
/*     */     {
/* 272 */       for (int var2 = this.framesTextureData.size(); var2 <= par1; var2++)
/*     */       {
/* 274 */         this.framesTextureData.add(null);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int[] getFrameTextureData(int[] par0ArrayOfInteger, int par1, int par2, int par3) {
/* 282 */     int[] var4 = new int[par1 * par2];
/* 283 */     System.arraycopy(par0ArrayOfInteger, par3 * var4.length, var4, 0, var4.length);
/* 284 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clearFramesTextureData() {
/* 290 */     this.framesTextureData.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean hasAnimationMetadata() {
/* 296 */     return (this.animationMetadata != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setFramesTextureData(List par1List) {
/* 302 */     this.framesTextureData = par1List;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void resetSprite() {
/* 308 */     this.animationMetadata = null;
/* 309 */     setFramesTextureData(Lists.newArrayList());
/* 310 */     this.frameCounter = 0;
/* 311 */     this.tickCounter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 316 */     return "TextureAtlasSprite{name='" + this.iconName + '\'' + ", frameCount=" + this.framesTextureData.size() + ", rotated=" + this.rotated + ", x=" + this.originX + ", y=" + this.originY + ", height=" + this.height + ", width=" + this.width + ", u0=" + this.minU + ", u1=" + this.maxU + ", v0=" + this.minV + ", v1=" + this.maxV + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isGreenGrassSide() {
/* 321 */     return this.is_green_grass_side;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureAtlasSprite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */