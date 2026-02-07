/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleResource
/*    */   implements Resource
/*    */ {
/* 18 */   private final Map mapMetadataSections = Maps.newHashMap();
/*    */   private final ResourceLocation srResourceLocation;
/*    */   private final InputStream resourceInputStream;
/*    */   private final InputStream mcmetaInputStream;
/*    */   private final MetadataSerializer srMetadataSerializer;
/*    */   private boolean mcmetaJsonChecked;
/*    */   private JsonObject mcmetaJson;
/*    */   
/*    */   public SimpleResource(ResourceLocation resourceLocation, InputStream inputStream, InputStream inputStream2, MetadataSerializer metadataSerializer) {
/* 27 */     this.srResourceLocation = resourceLocation;
/* 28 */     this.resourceInputStream = inputStream;
/* 29 */     this.mcmetaInputStream = inputStream2;
/* 30 */     this.srMetadataSerializer = metadataSerializer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream getInputStream() {
/* 40 */     return this.resourceInputStream;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasMetadata() {
/* 45 */     return (this.mcmetaInputStream != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public MetadataSection getMetadata(String string) {
/* 50 */     if (!hasMetadata()) {
/* 51 */       return null;
/*    */     }
/*    */     
/* 54 */     if (this.mcmetaJson == null && !this.mcmetaJsonChecked) {
/* 55 */       this.mcmetaJsonChecked = true;
/*    */       
/* 57 */       BufferedReader bufferedReader = null;
/*    */       try {
/* 59 */         bufferedReader = new BufferedReader(new InputStreamReader(this.mcmetaInputStream));
/* 60 */         this.mcmetaJson = (new JsonParser()).parse(bufferedReader).getAsJsonObject();
/*    */       } finally {
/* 62 */         IOUtils.closeQuietly(bufferedReader);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 67 */     MetadataSection metadataSection = (MetadataSection)this.mapMetadataSections.get(string);
/* 68 */     if (metadataSection == null) {
/* 69 */       metadataSection = this.srMetadataSerializer.parseMetadataSection(string, this.mcmetaJson);
/*    */     }
/* 71 */     return metadataSection;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 76 */     if (this == object) return true;
/*    */     
/* 78 */     if (object instanceof SimpleResource) {
/* 79 */       SimpleResource simpleResource = (SimpleResource)object;
/*    */       
/* 81 */       return (this.srResourceLocation != null) ? this.srResourceLocation.equals(simpleResource.srResourceLocation) : ((simpleResource.srResourceLocation == null));
/*    */     } 
/*    */     
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 89 */     if (this.srResourceLocation == null) return 0;
/*    */     
/* 91 */     return this.srResourceLocation.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SimpleResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */