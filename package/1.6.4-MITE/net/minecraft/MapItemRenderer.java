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
/*     */ public class MapItemRenderer
/*     */ {
/*  16 */   private static final ResourceLocation field_111277_a = new ResourceLocation("textures/map/map_icons.png");
/*     */   
/*     */   private final DynamicTexture bufferedImage;
/*     */   
/*  20 */   private int[] intArray = new int[16384];
/*     */   private GameSettings gameSettings;
/*     */   private final ResourceLocation field_111276_e;
/*     */   
/*     */   public MapItemRenderer(GameSettings gameSettings, TextureManager textureManager) {
/*  25 */     this.gameSettings = gameSettings;
/*  26 */     TextureManager textureManager1 = textureManager;
/*     */     
/*  28 */     this.bufferedImage = new DynamicTexture(128, 128);
/*  29 */     this.field_111276_e = textureManager.getDynamicTextureLocation("map", this.bufferedImage);
/*  30 */     this.intArray = this.bufferedImage.getTextureData();
/*     */ 
/*     */     
/*  33 */     for (byte b = 0; b < this.intArray.length; b++)
/*  34 */       this.intArray[b] = 0; 
/*     */   }
/*     */   
/*     */   public void renderMap(EntityPlayer entityPlayer, TextureManager textureManager, MapData mapData) {
/*     */     byte b1;
/*  39 */     for (b1 = 0; b1 < '䀀'; b1++) {
/*  40 */       byte b = mapData.colors[b1];
/*  41 */       if (b / 4 == 0) {
/*  42 */         this.intArray[b1] = (b1 + b1 / 128 & 0x1) * 8 + 16 << 24;
/*     */       } else {
/*  44 */         int i = (MapColor.mapColorArray[b / 4]).colorValue;
/*  45 */         int j = b & 0x3;
/*     */         
/*  47 */         char c = 'Ü';
/*  48 */         if (j == 2) c = 'ÿ'; 
/*  49 */         if (j == 0) c = '´';
/*     */         
/*  51 */         int k = (i >> 16 & 0xFF) * c / 255;
/*  52 */         int m = (i >> 8 & 0xFF) * c / 255;
/*  53 */         int n = (i & 0xFF) * c / 255;
/*     */         
/*  55 */         this.intArray[b1] = 0xFF000000 | k << 16 | m << 8 | n;
/*     */       } 
/*     */     } 
/*     */     
/*  59 */     this.bufferedImage.updateDynamicTexture();
/*     */     
/*  61 */     b1 = 0;
/*  62 */     byte b2 = 0;
/*  63 */     Tessellator tessellator = Tessellator.instance;
/*     */     
/*  65 */     float f = 0.0F;
/*     */     
/*  67 */     textureManager.bindTexture(this.field_111276_e);
/*  68 */     GL11.glEnable(3042);
/*  69 */     GL11.glBlendFunc(1, 771);
/*  70 */     GL11.glDisable(3008);
/*  71 */     tessellator.startDrawingQuads();
/*  72 */     tessellator.addVertexWithUV(((b1 + 0) + f), ((b2 + 128) - f), -0.009999999776482582D, 0.0D, 1.0D);
/*  73 */     tessellator.addVertexWithUV(((b1 + 128) - f), ((b2 + 128) - f), -0.009999999776482582D, 1.0D, 1.0D);
/*  74 */     tessellator.addVertexWithUV(((b1 + 128) - f), ((b2 + 0) + f), -0.009999999776482582D, 1.0D, 0.0D);
/*  75 */     tessellator.addVertexWithUV(((b1 + 0) + f), ((b2 + 0) + f), -0.009999999776482582D, 0.0D, 0.0D);
/*  76 */     tessellator.draw();
/*  77 */     GL11.glEnable(3008);
/*  78 */     GL11.glDisable(3042);
/*     */     
/*  80 */     textureManager.bindTexture(field_111277_a);
/*  81 */     byte b3 = 0;
/*     */     
/*  83 */     for (MapCoord mapCoord : mapData.playersVisibleOnMap.values()) {
/*  84 */       GL11.glPushMatrix();
/*  85 */       GL11.glTranslatef(b1 + mapCoord.centerX / 2.0F + 64.0F, b2 + mapCoord.centerZ / 2.0F + 64.0F, -0.02F);
/*  86 */       GL11.glRotatef((mapCoord.iconRotation * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
/*  87 */       GL11.glScalef(4.0F, 4.0F, 3.0F);
/*  88 */       GL11.glTranslatef(-0.125F, 0.125F, 0.0F);
/*     */       
/*  90 */       float f1 = (mapCoord.iconSize % 4 + 0) / 4.0F;
/*  91 */       float f2 = (mapCoord.iconSize / 4 + 0) / 4.0F;
/*  92 */       float f3 = (mapCoord.iconSize % 4 + 1) / 4.0F;
/*  93 */       float f4 = (mapCoord.iconSize / 4 + 1) / 4.0F;
/*     */       
/*  95 */       tessellator.startDrawingQuads();
/*  96 */       tessellator.addVertexWithUV(-1.0D, 1.0D, (b3 * 0.001F), f1, f2);
/*  97 */       tessellator.addVertexWithUV(1.0D, 1.0D, (b3 * 0.001F), f3, f2);
/*  98 */       tessellator.addVertexWithUV(1.0D, -1.0D, (b3 * 0.001F), f3, f4);
/*  99 */       tessellator.addVertexWithUV(-1.0D, -1.0D, (b3 * 0.001F), f1, f4);
/* 100 */       tessellator.draw();
/* 101 */       GL11.glPopMatrix();
/* 102 */       b3++;
/*     */     } 
/*     */     
/* 105 */     GL11.glPushMatrix();
/* 106 */     GL11.glTranslatef(0.0F, 0.0F, -0.04F);
/* 107 */     GL11.glScalef(1.0F, 1.0F, 1.0F);
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapItemRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */