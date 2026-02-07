/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class TextureMap
/*     */   extends AbstractTexture
/*     */   implements TickableTextureObject, IconRegister
/*     */ {
/*  16 */   public static final ResourceLocation locationBlocksTexture = new ResourceLocation("textures/atlas/blocks.png", false);
/*  17 */   public static final ResourceLocation locationItemsTexture = new ResourceLocation("textures/atlas/items.png", false);
/*  18 */   private final List listAnimatedSprites = Lists.newArrayList();
/*  19 */   private final Map mapRegisteredSprites = Maps.newHashMap();
/*  20 */   private final Map mapUploadedSprites = Maps.newHashMap();
/*     */   
/*     */   private final int textureType;
/*     */   
/*     */   private final String basePath;
/*  25 */   private final TextureAtlasSprite missingImage = new TextureAtlasSprite("missingno");
/*     */ 
/*     */   
/*     */   public TextureMap(int par1, String par2Str) {
/*  29 */     this.textureType = par1;
/*  30 */     this.basePath = par2Str;
/*  31 */     registerIcons();
/*     */   }
/*     */ 
/*     */   
/*     */   private void initMissingImage() {
/*  36 */     this.missingImage.setFramesTextureData(Lists.newArrayList((Object[])new int[][] { TextureUtil.missingTextureData }));
/*  37 */     this.missingImage.setIconWidth(16);
/*  38 */     this.missingImage.setIconHeight(16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTexture(ResourceManager par1ResourceManager) throws IOException {
/*  43 */     initMissingImage();
/*  44 */     loadTextureAtlas(par1ResourceManager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTextureAtlas(ResourceManager par1ResourceManager) {
/*  49 */     int var2 = Minecraft.getGLMaximumTextureSize();
/*  50 */     Stitcher var3 = new Stitcher(var2, var2, true);
/*  51 */     this.mapUploadedSprites.clear();
/*  52 */     this.listAnimatedSprites.clear();
/*  53 */     Iterator<Map.Entry> var4 = this.mapRegisteredSprites.entrySet().iterator();
/*     */     
/*  55 */     while (var4.hasNext()) {
/*     */ 
/*     */ 
/*     */       
/*  59 */       Map.Entry var5 = var4.next();
/*     */       
/*  61 */       ResourceLocation var6 = new ResourceLocation((String)var5.getKey(), false);
/*  62 */       TextureAtlasSprite var7 = (TextureAtlasSprite)var5.getValue();
/*     */       
/*  64 */       ResourceLocation var8 = new ResourceLocation(var6.getResourceDomain(), String.format("%s/%s%s", new Object[] { this.basePath, var6.getResourcePath(), ".png" }), false);
/*     */ 
/*     */       
/*     */       try {
/*  68 */         var7.loadSprite(par1ResourceManager.getResource(var8));
/*     */       }
/*  70 */       catch (RuntimeException var13) {
/*     */         
/*  72 */         Minecraft.getMinecraft().getLogAgent().logSevere(String.format("Unable to parse animation metadata from %s: %s", new Object[] { var8, var13.getMessage() }));
/*     */         
/*     */         continue;
/*  75 */       } catch (IOException var14) {
/*     */ 
/*     */ 
/*     */         
/*  79 */         String error_message = "Missing resource: " + var8.getResourcePath();
/*     */         
/*  81 */         Minecraft.getMinecraft().getLogAgent().logSevere(error_message);
/*  82 */         Minecraft.setErrorMessage(error_message, false);
/*     */         
/*     */         continue;
/*     */       } 
/*  86 */       var3.addSprite(var7);
/*     */     } 
/*     */     
/*  89 */     var3.addSprite(this.missingImage);
/*     */ 
/*     */     
/*     */     try {
/*  93 */       var3.doStitch();
/*     */     }
/*  95 */     catch (StitcherException var12) {
/*     */       
/*  97 */       throw var12;
/*     */     } 
/*     */     
/* 100 */     TextureUtil.allocateTexture(getGlTextureId(), var3.getCurrentWidth(), var3.getCurrentHeight());
/* 101 */     HashMap var15 = Maps.newHashMap(this.mapRegisteredSprites);
/* 102 */     Iterator<TextureAtlasSprite> var16 = var3.getStichSlots().iterator();
/*     */ 
/*     */     
/* 105 */     while (var16.hasNext()) {
/*     */       
/* 107 */       TextureAtlasSprite var17 = var16.next();
/* 108 */       String var18 = var17.getIconName();
/* 109 */       var15.remove(var18);
/* 110 */       this.mapUploadedSprites.put(var18, var17);
/*     */ 
/*     */       
/*     */       try {
/* 114 */         TextureUtil.uploadTextureSub(var17.getFrameTextureData(0), var17.getIconWidth(), var17.getIconHeight(), var17.getOriginX(), var17.getOriginY(), false, false);
/*     */       }
/* 116 */       catch (Throwable var11) {
/*     */         
/* 118 */         CrashReport var9 = CrashReport.makeCrashReport(var11, "Stitching texture atlas");
/* 119 */         CrashReportCategory var10 = var9.makeCategory("Texture being stitched together");
/* 120 */         var10.addCrashSection("Atlas path", this.basePath);
/* 121 */         var10.addCrashSection("Sprite", var17);
/* 122 */         throw new ReportedException(var9);
/*     */       } 
/*     */       
/* 125 */       if (var17.hasAnimationMetadata()) {
/*     */         
/* 127 */         this.listAnimatedSprites.add(var17);
/*     */         
/*     */         continue;
/*     */       } 
/* 131 */       var17.clearFramesTextureData();
/*     */     } 
/*     */ 
/*     */     
/* 135 */     var16 = var15.values().iterator();
/*     */     
/* 137 */     while (var16.hasNext()) {
/*     */       
/* 139 */       TextureAtlasSprite var17 = var16.next();
/* 140 */       var17.copyFrom(this.missingImage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void registerIcons() {
/* 146 */     this.mapRegisteredSprites.clear();
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (this.textureType == 0) {
/*     */       
/* 152 */       Block[] var1 = Block.blocksList;
/* 153 */       int i = var1.length;
/*     */       
/* 155 */       for (int j = 0; j < i; j++) {
/*     */         
/* 157 */         Block var4 = var1[j];
/*     */         
/* 159 */         if (var4 != null)
/*     */         {
/* 161 */           var4.registerIcons(this);
/*     */         }
/*     */       } 
/*     */       
/* 165 */       (Minecraft.getMinecraft()).renderGlobal.registerDestroyBlockIcons(this);
/* 166 */       RenderManager.instance.updateIcons(this);
/*     */     } 
/*     */     
/* 169 */     Item[] var5 = Item.itemsList;
/* 170 */     int var2 = var5.length;
/*     */     
/* 172 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 174 */       Item var6 = var5[var3];
/*     */       
/* 176 */       if (var6 != null && var6.getSpriteNumber() == this.textureType)
/*     */       {
/* 178 */         var6.registerIcons(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getAtlasSprite(String par1Str) {
/* 185 */     TextureAtlasSprite var2 = (TextureAtlasSprite)this.mapUploadedSprites.get(par1Str);
/*     */     
/* 187 */     if (var2 == null)
/*     */     {
/* 189 */       var2 = this.missingImage;
/*     */     }
/*     */     
/* 192 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimations() {
/* 197 */     TextureUtil.bindTexture(getGlTextureId());
/* 198 */     Iterator<TextureAtlasSprite> var1 = this.listAnimatedSprites.iterator();
/*     */     
/* 200 */     while (var1.hasNext()) {
/*     */       
/* 202 */       TextureAtlasSprite var2 = var1.next();
/* 203 */       var2.updateAnimation();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon registerIcon(String par1Str) {
/* 209 */     if (par1Str == null)
/*     */     {
/* 211 */       (new RuntimeException("Don't register null!")).printStackTrace();
/*     */     }
/*     */     
/* 214 */     Object var2 = this.mapRegisteredSprites.get(par1Str);
/*     */     
/* 216 */     if (var2 == null) {
/*     */       
/* 218 */       if (this.textureType == 1) {
/*     */         
/* 220 */         if ("clock".equals(par1Str))
/*     */         {
/* 222 */           var2 = new TextureClock(par1Str);
/*     */         }
/* 224 */         else if ("compass".equals(par1Str))
/*     */         {
/* 226 */           var2 = new TextureCompass(par1Str);
/*     */         }
/*     */         else
/*     */         {
/* 230 */           var2 = new TextureAtlasSprite(par1Str);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 235 */         var2 = new TextureAtlasSprite(par1Str);
/*     */       } 
/*     */       
/* 238 */       this.mapRegisteredSprites.put(par1Str, var2);
/*     */     } 
/*     */     
/* 241 */     return (Icon)var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextureType() {
/* 246 */     return this.textureType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 251 */     updateAnimations();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */