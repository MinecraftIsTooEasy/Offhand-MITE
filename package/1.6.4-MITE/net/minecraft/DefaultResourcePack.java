/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class DefaultResourcePack
/*    */   implements ResourcePack {
/* 16 */   public static final Set defaultResourceDomains = (Set)ImmutableSet.of("minecraft");
/* 17 */   private final Map mapResourceFiles = Maps.newHashMap();
/*    */   private final File fileAssets;
/*    */   
/*    */   public DefaultResourcePack(File file) {
/* 21 */     this.fileAssets = file;
/*    */     
/* 23 */     readAssetsDir(this.fileAssets);
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getInputStream(ResourceLocation resourceLocation) {
/* 28 */     InputStream inputStream = getResourceStream(resourceLocation);
/* 29 */     if (inputStream != null) {
/* 30 */       return inputStream;
/*    */     }
/*    */ 
/*    */     
/* 34 */     File file = (File)this.mapResourceFiles.get(resourceLocation.toString());
/* 35 */     if (file != null) {
/* 36 */       return new FileInputStream(file);
/*    */     }
/*    */     
/* 39 */     throw new FileNotFoundException(resourceLocation.getResourcePath());
/*    */   }
/*    */   
/*    */   private InputStream getResourceStream(ResourceLocation resourceLocation) {
/* 43 */     return DefaultResourcePack.class.getResourceAsStream("/assets/minecraft/" + resourceLocation.getResourcePath());
/*    */   }
/*    */   
/*    */   public void addResourceFile(String string, File file) {
/* 47 */     this.mapResourceFiles.put((new ResourceLocation(string)).toString(), file);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean resourceExists(ResourceLocation resourceLocation) {
/* 52 */     return (getResourceStream(resourceLocation) != null || this.mapResourceFiles.containsKey(resourceLocation.toString()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getResourceDomains() {
/* 57 */     return defaultResourceDomains;
/*    */   }
/*    */   
/*    */   public void readAssetsDir(File file) {
/* 61 */     if (file.isDirectory()) {
/* 62 */       for (File file1 : file.listFiles()) {
/* 63 */         readAssetsDir(file1);
/*    */       }
/*    */     } else {
/* 66 */       addResourceFile(AbstractResourcePack.getRelativeName(this.fileAssets, file), file);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MetadataSection getPackMetadata(MetadataSerializer metadataSerializer, String string) {
/* 73 */     return AbstractResourcePack.readMetadata(metadataSerializer, DefaultResourcePack.class.getResourceAsStream("/" + (new ResourceLocation("pack.mcmeta")).getResourcePath()), string);
/*    */   }
/*    */ 
/*    */   
/*    */   public BufferedImage getPackImage() {
/* 78 */     return ImageIO.read(DefaultResourcePack.class.getResourceAsStream("/" + (new ResourceLocation("pack.png")).getResourcePath()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPackName() {
/* 83 */     return "Default";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DefaultResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */