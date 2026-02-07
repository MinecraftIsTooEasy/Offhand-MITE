/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import javax.imageio.ImageIO;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractResourcePack
/*    */   implements ResourcePack
/*    */ {
/* 17 */   protected static final ILogAgent resourceLog = Minecraft.getMinecraft().getLogAgent();
/*    */   protected final File resourcePackFile;
/*    */   
/*    */   public AbstractResourcePack(File file) {
/* 21 */     this.resourcePackFile = file;
/*    */   }
/*    */   
/*    */   private static String locationToName(ResourceLocation resourceLocation) {
/* 25 */     return String.format("%s/%s/%s", new Object[] { "assets", resourceLocation.getResourceDomain(), resourceLocation.getResourcePath() });
/*    */   }
/*    */   
/*    */   protected static String getRelativeName(File file, File file2) {
/* 29 */     return file.toURI().relativize(file2.toURI()).getPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getInputStream(ResourceLocation resourceLocation) {
/* 34 */     return getInputStreamByName(locationToName(resourceLocation));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean resourceExists(ResourceLocation resourceLocation) {
/* 39 */     return hasResourceName(locationToName(resourceLocation));
/*    */   }
/*    */   
/*    */   protected abstract InputStream getInputStreamByName(String paramString);
/*    */   
/*    */   protected abstract boolean hasResourceName(String paramString);
/*    */   
/*    */   protected void logNameNotLowercase(String string) {
/* 47 */     resourceLog.logWarningFormatted("ResourcePack: ignored non-lowercase namespace: %s in %s", new Object[] { string, this.resourcePackFile });
/*    */   }
/*    */ 
/*    */   
/*    */   public MetadataSection getPackMetadata(MetadataSerializer metadataSerializer, String string) {
/* 52 */     return readMetadata(metadataSerializer, getInputStreamByName("pack.mcmeta"), string);
/*    */   }
/*    */   
/*    */   static MetadataSection readMetadata(MetadataSerializer metadataSerializer, InputStream inputStream, String string) {
/* 56 */     JsonObject jsonObject = null;
/* 57 */     BufferedReader bufferedReader = null;
/*    */     try {
/* 59 */       bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
/* 60 */       jsonObject = (new JsonParser()).parse(bufferedReader).getAsJsonObject();
/*    */     } finally {
/* 62 */       IOUtils.closeQuietly(bufferedReader);
/*    */     } 
/*    */     
/* 65 */     return metadataSerializer.parseMetadataSection(string, jsonObject);
/*    */   }
/*    */ 
/*    */   
/*    */   public BufferedImage getPackImage() {
/* 70 */     return ImageIO.read(getInputStreamByName("pack.png"));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPackName() {
/* 75 */     return this.resourcePackFile.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AbstractResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */