/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.nio.IntBuffer;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureUtil
/*     */ {
/*  17 */   private static final IntBuffer dataBuffer = GLAllocation.createDirectIntBuffer(4194304);
/*     */ 
/*     */ 
/*     */   
/*  21 */   public static final DynamicTexture missingTexture = new DynamicTexture(16, 16);
/*  22 */   public static final int[] missingTextureData = missingTexture.getTextureData();
/*     */   
/*     */   static {
/*  25 */     int i = -16777216;
/*  26 */     int j = -524040;
/*  27 */     int[] arrayOfInt1 = { -524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040 };
/*  28 */     int[] arrayOfInt2 = { -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216 };
/*  29 */     int k = arrayOfInt1.length;
/*  30 */     for (byte b = 0; b < 16; b++) {
/*  31 */       System.arraycopy((b < k) ? arrayOfInt1 : arrayOfInt2, 0, missingTextureData, 16 * b, k);
/*  32 */       System.arraycopy((b < k) ? arrayOfInt2 : arrayOfInt1, 0, missingTextureData, 16 * b + k, k);
/*     */     } 
/*  34 */     missingTexture.updateDynamicTexture();
/*     */   }
/*     */   
/*     */   public static int glGenTextures() {
/*  38 */     return GL11.glGenTextures();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int uploadTextureImage(int i, BufferedImage bufferedImage) {
/*  46 */     return uploadTextureImageAllocate(i, bufferedImage, false, false);
/*     */   }
/*     */   
/*     */   public static void uploadTexture(int i, int[] is, int j, int k) {
/*  50 */     bindTexture(i);
/*     */     
/*  52 */     uploadTextureSub(is, j, k, 0, 0, false, false);
/*     */   }
/*     */   
/*     */   public static void uploadTextureSub(int[] is, int i, int j, int k, int l, boolean bl, boolean bl2) {
/*  56 */     int m = 4194304 / i;
/*     */     
/*  58 */     setTextureBlurred(bl);
/*  59 */     setTextureClamped(bl2);
/*     */     
/*  61 */     int n = 0;
/*  62 */     while (n < i * j) {
/*  63 */       int i1 = n / i;
/*  64 */       int i2 = Math.min(m, j - i1);
/*  65 */       int i3 = i * i2;
/*     */       
/*  67 */       copyToBufferPos(is, n, i3);
/*     */       
/*  69 */       GL11.glTexSubImage2D(3553, 0, k, l + i1, i, i2, 32993, 33639, dataBuffer);
/*     */       
/*  71 */       n += i * i2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int uploadTextureImageAllocate(int i, BufferedImage bufferedImage, boolean bl, boolean bl2) {
/*  76 */     allocateTexture(i, bufferedImage.getWidth(), bufferedImage.getHeight());
/*     */     
/*  78 */     return uploadTextureImageSub(i, bufferedImage, 0, 0, bl, bl2);
/*     */   }
/*     */   
/*     */   public static void allocateTexture(int i, int j, int k) {
/*  82 */     bindTexture(i);
/*     */     
/*  84 */     GL11.glTexImage2D(3553, 0, 6408, j, k, 0, 32993, 33639, (IntBuffer)null);
/*     */   }
/*     */   
/*     */   public static int uploadTextureImageSub(int i, BufferedImage bufferedImage, int j, int k, boolean bl, boolean bl2) {
/*  88 */     bindTexture(i);
/*     */     
/*  90 */     uploadTextureImageSubImpl(bufferedImage, j, k, bl, bl2);
/*     */     
/*  92 */     return i;
/*     */   }
/*     */   
/*     */   private static void uploadTextureImageSubImpl(BufferedImage bufferedImage, int i, int j, boolean bl, boolean bl2) {
/*  96 */     int k = bufferedImage.getWidth();
/*  97 */     int m = bufferedImage.getHeight();
/*     */     
/*  99 */     int n = 4194304 / k;
/* 100 */     int[] arrayOfInt = new int[n * k];
/*     */     
/* 102 */     setTextureBlurred(bl);
/* 103 */     setTextureClamped(bl2);
/*     */     
/* 105 */     int i1 = 0;
/* 106 */     while (i1 < k * m) {
/* 107 */       int i2 = i1 / k;
/* 108 */       int i3 = Math.min(n, m - i2);
/* 109 */       int i4 = k * i3;
/*     */       
/* 111 */       bufferedImage.getRGB(0, i2, k, i3, arrayOfInt, 0, k);
/*     */       
/* 113 */       copyToBuffer(arrayOfInt, i4);
/*     */       
/* 115 */       GL11.glTexSubImage2D(3553, 0, i, j + i2, k, i3, 32993, 33639, dataBuffer);
/*     */       
/* 117 */       i1 += k * n;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setTextureClamped(boolean bl) {
/* 122 */     if (bl) {
/* 123 */       GL11.glTexParameteri(3553, 10242, 10496);
/* 124 */       GL11.glTexParameteri(3553, 10243, 10496);
/*     */     } else {
/* 126 */       GL11.glTexParameteri(3553, 10242, 10497);
/* 127 */       GL11.glTexParameteri(3553, 10243, 10497);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setTextureBlurred(boolean bl) {
/* 132 */     if (bl) {
/* 133 */       GL11.glTexParameteri(3553, 10241, 9729);
/* 134 */       GL11.glTexParameteri(3553, 10240, 9729);
/*     */     } else {
/* 136 */       GL11.glTexParameteri(3553, 10241, 9728);
/* 137 */       GL11.glTexParameteri(3553, 10240, 9728);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void copyToBuffer(int[] is, int i) {
/* 142 */     copyToBufferPos(is, 0, i);
/*     */   }
/*     */   
/*     */   private static void copyToBufferPos(int[] is, int i, int j) {
/* 146 */     int[] arrayOfInt = is;
/*     */     
/* 148 */     if ((Minecraft.getMinecraft()).gameSettings.anaglyph) {
/* 149 */       arrayOfInt = updateAnaglyph(is);
/*     */     }
/*     */     
/* 152 */     dataBuffer.clear();
/* 153 */     dataBuffer.put(arrayOfInt, i, j);
/* 154 */     dataBuffer.position(0).limit(j);
/*     */   }
/*     */   
/*     */   static void bindTexture(int i) {
/* 158 */     GL11.glBindTexture(3553, i);
/*     */   }
/*     */   
/*     */   public static int[] readImageData(ResourceManager resourceManager, ResourceLocation resourceLocation) {
/* 162 */     BufferedImage bufferedImage = ImageIO.read(resourceManager.getResource(resourceLocation).getInputStream());
/*     */     
/* 164 */     int i = bufferedImage.getWidth();
/* 165 */     int j = bufferedImage.getHeight();
/*     */     
/* 167 */     int[] arrayOfInt = new int[i * j];
/* 168 */     bufferedImage.getRGB(0, 0, i, j, arrayOfInt, 0, i);
/*     */     
/* 170 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */   public static int[] updateAnaglyph(int[] is) {
/* 174 */     int[] arrayOfInt = new int[is.length];
/* 175 */     for (byte b = 0; b < is.length; b++) {
/* 176 */       int i = is[b] >> 24 & 0xFF;
/* 177 */       int j = is[b] >> 16 & 0xFF;
/* 178 */       int k = is[b] >> 8 & 0xFF;
/* 179 */       int m = is[b] & 0xFF;
/*     */       
/* 181 */       int n = (j * 30 + k * 59 + m * 11) / 100;
/* 182 */       int i1 = (j * 30 + k * 70) / 100;
/* 183 */       int i2 = (j * 30 + m * 70) / 100;
/*     */       
/* 185 */       arrayOfInt[b] = i << 24 | n << 16 | i1 << 8 | i2;
/*     */     } 
/*     */     
/* 188 */     return arrayOfInt;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */