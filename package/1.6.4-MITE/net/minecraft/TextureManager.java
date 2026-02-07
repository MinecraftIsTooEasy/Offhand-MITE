/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TextureManager
/*     */   implements Tickable, ResourceManagerReloadListener
/*     */ {
/*  21 */   private final Map mapTextureObjects = Maps.newHashMap();
/*  22 */   private final Map mapResourceLocations = Maps.newHashMap();
/*  23 */   private final List listTickables = Lists.newArrayList();
/*  24 */   private final Map mapTextureCounters = Maps.newHashMap();
/*     */   
/*     */   private ResourceManager theResourceManager;
/*  27 */   private static final List registeredTextures = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public TextureManager(ResourceManager par1ResourceManager) {
/*  31 */     this.theResourceManager = par1ResourceManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void bindTexture(ResourceLocation par1ResourceLocation) {
/*  36 */     Object var2 = this.mapTextureObjects.get(par1ResourceLocation);
/*     */     
/*  38 */     if (var2 == null) {
/*     */       
/*  40 */       var2 = new SimpleTexture(par1ResourceLocation);
/*  41 */       loadTexture(par1ResourceLocation, (TextureObject)var2);
/*     */     } 
/*     */     
/*  44 */     TextureUtil.bindTexture(((TextureObject)var2).getGlTextureId());
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getResourceLocation(int par1) {
/*  49 */     return (ResourceLocation)this.mapResourceLocations.get(Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadTextureMap(ResourceLocation par1ResourceLocation, TextureMap par2TextureMap) {
/*  54 */     if (loadTickableTexture(par1ResourceLocation, par2TextureMap)) {
/*     */       
/*  56 */       this.mapResourceLocations.put(Integer.valueOf(par2TextureMap.getTextureType()), par1ResourceLocation);
/*  57 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean loadTickableTexture(ResourceLocation par1ResourceLocation, TickableTextureObject par2TickableTextureObject) {
/*  67 */     if (loadTexture(par1ResourceLocation, par2TickableTextureObject)) {
/*     */       
/*  69 */       this.listTickables.add(par2TickableTextureObject);
/*  70 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  80 */     TexturedQuad.init();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadTexture(ResourceLocation par1ResourceLocation, TextureObject par2TextureObject) {
/*  85 */     boolean var3 = true;
/*     */ 
/*     */     
/*     */     try {
/*  89 */       par2TextureObject.loadTexture(this.theResourceManager);
/*     */     }
/*  91 */     catch (IOException var8) {
/*     */       
/*  93 */       Minecraft.getMinecraft().getLogAgent().logWarningException("Failed to load texture: " + par1ResourceLocation, var8);
/*  94 */       par2TextureObject = TextureUtil.missingTexture;
/*  95 */       this.mapTextureObjects.put(par1ResourceLocation, par2TextureObject);
/*  96 */       var3 = false;
/*     */     }
/*  98 */     catch (Throwable var9) {
/*     */       
/* 100 */       CrashReport var5 = CrashReport.makeCrashReport(var9, "Registering texture");
/* 101 */       CrashReportCategory var6 = var5.makeCategory("Resource location being registered");
/* 102 */       var6.addCrashSection("Resource location", par1ResourceLocation);
/* 103 */       var6.addCrashSectionCallable("Texture object class", new TextureManagerINNER1(this, par2TextureObject));
/* 104 */       throw new ReportedException(var5);
/*     */     } 
/*     */     
/* 107 */     this.mapTextureObjects.put(par1ResourceLocation, par2TextureObject);
/* 108 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureObject getTexture(ResourceLocation par1ResourceLocation) {
/* 113 */     return (TextureObject)this.mapTextureObjects.get(par1ResourceLocation);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getDynamicTextureLocation(String par1Str, DynamicTexture par2DynamicTexture) {
/* 118 */     Integer var3 = (Integer)this.mapTextureCounters.get(par1Str);
/*     */     
/* 120 */     if (var3 == null) {
/*     */       
/* 122 */       var3 = Integer.valueOf(1);
/*     */     }
/*     */     else {
/*     */       
/* 126 */       var3 = Integer.valueOf(var3.intValue() + 1);
/*     */     } 
/*     */     
/* 129 */     this.mapTextureCounters.put(par1Str, var3);
/*     */     
/* 131 */     ResourceLocation var4 = new ResourceLocation(String.format("dynamic/%s_%d", new Object[] { par1Str, var3 }), false);
/* 132 */     loadTexture(var4, par2DynamicTexture);
/* 133 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 138 */     Iterator<Tickable> var1 = this.listTickables.iterator();
/*     */     
/* 140 */     while (var1.hasNext()) {
/*     */       
/* 142 */       Tickable var2 = var1.next();
/* 143 */       var2.tick();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(ResourceManager par1ResourceManager) {
/* 149 */     Iterator<Map.Entry> var2 = this.mapTextureObjects.entrySet().iterator();
/*     */     
/* 151 */     while (var2.hasNext()) {
/*     */       
/* 153 */       Map.Entry var3 = var2.next();
/* 154 */       loadTexture((ResourceLocation)var3.getKey(), (TextureObject)var3.getValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void preloadTextures() {
/* 160 */     Iterator i = registeredTextures.iterator();
/*     */     
/* 162 */     while (i.hasNext()) {
/*     */       
/* 164 */       Object object = i.next();
/*     */       
/* 166 */       if (object instanceof TextureObject) {
/* 167 */         ((TextureObject)object).getGlTextureId();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void unloadTextures() {
/* 173 */     registeredTextures.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */