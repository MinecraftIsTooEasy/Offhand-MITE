/*    */ package net.minecraft;
/*    */ 
/*    */ public abstract class AbstractClientPlayer
/*    */   extends EntityPlayer {
/*  5 */   public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");
/*    */   
/*    */   private ThreadDownloadImageData downloadImageSkin;
/*    */   private ThreadDownloadImageData downloadImageCape;
/*    */   private ResourceLocation locationSkin;
/*    */   private ResourceLocation locationCape;
/*    */   
/*    */   public AbstractClientPlayer(World par1World, String par2Str) {
/* 13 */     super(par1World, par2Str);
/* 14 */     setupCustomSkin();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void setupCustomSkin() {
/* 19 */     System.out.println("Setting up custom skins");
/*    */     
/* 21 */     if (this.username != null && !this.username.isEmpty()) {
/*    */       
/* 23 */       this.locationSkin = getLocationSkin(this.username);
/* 24 */       this.locationCape = getLocationCape(this.username);
/* 25 */       this.downloadImageSkin = getDownloadImageSkin(this.locationSkin, this.username);
/* 26 */       this.downloadImageCape = getDownloadImageCape(this.locationCape, this.username);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ThreadDownloadImageData getTextureSkin() {
/* 32 */     return this.downloadImageSkin;
/*    */   }
/*    */ 
/*    */   
/*    */   public ThreadDownloadImageData getTextureCape() {
/* 37 */     return this.downloadImageCape;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getLocationSkin() {
/* 42 */     return this.locationSkin;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getLocationCape() {
/* 47 */     return this.locationCape;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation par0ResourceLocation, String par1Str) {
/* 52 */     return getDownloadImage(par0ResourceLocation, getSkinUrl(par1Str), locationStevePng, new ImageBufferDownload());
/*    */   }
/*    */ 
/*    */   
/*    */   public static ThreadDownloadImageData getDownloadImageCape(ResourceLocation par0ResourceLocation, String par1Str) {
/* 57 */     return getDownloadImage(par0ResourceLocation, getCapeUrl(par1Str), (ResourceLocation)null, (IImageBuffer)null);
/*    */   }
/*    */ 
/*    */   
/*    */   private static ThreadDownloadImageData getDownloadImage(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer) {
/* 62 */     TextureManager var4 = Minecraft.getMinecraft().getTextureManager();
/* 63 */     Object var5 = var4.getTexture(par0ResourceLocation);
/*    */     
/* 65 */     if (var5 == null) {
/*    */       
/* 67 */       var5 = new ThreadDownloadImageData(par1Str, par2ResourceLocation, par3IImageBuffer);
/* 68 */       var4.loadTexture(par0ResourceLocation, (TextureObject)var5);
/*    */     } 
/*    */     
/* 71 */     return (ThreadDownloadImageData)var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getSkinUrl(String par0Str) {
/* 76 */     return String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { StringUtils.stripControlCodes(par0Str) });
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getCapeUrl(String par0Str) {
/* 81 */     return String.format("http://skins.minecraft.net/MinecraftCloaks/%s.png", new Object[] { StringUtils.stripControlCodes(par0Str) });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ResourceLocation getLocationSkin(String par0Str) {
/* 87 */     return new ResourceLocation("skins/" + StringUtils.stripControlCodes(par0Str), false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ResourceLocation getLocationCape(String par0Str) {
/* 93 */     return new ResourceLocation("cloaks/" + StringUtils.stripControlCodes(par0Str), false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ResourceLocation getLocationSkull(String par0Str) {
/* 99 */     return new ResourceLocation("skull/" + StringUtils.stripControlCodes(par0Str), false);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AbstractClientPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */