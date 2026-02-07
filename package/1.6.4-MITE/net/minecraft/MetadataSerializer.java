/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ public class MetadataSerializer
/*    */ {
/* 10 */   private final IRegistry metadataSectionSerializerRegistry = new RegistrySimple();
/* 11 */   private final GsonBuilder gsonBuilder = new GsonBuilder();
/*    */   private Gson gson;
/*    */   
/*    */   public void registerMetadataSectionType(MetadataSectionSerializer metadataSectionSerializer, Class class_) {
/* 15 */     this.metadataSectionSerializerRegistry.putObject(metadataSectionSerializer.getSectionName(), new MetadataSerializerRegistration(this, metadataSectionSerializer, class_, null));
/* 16 */     this.gsonBuilder.registerTypeAdapter(class_, metadataSectionSerializer);
/* 17 */     this.gson = null;
/*    */   }
/*    */   
/*    */   public MetadataSection parseMetadataSection(String string, JsonObject jsonObject) {
/* 21 */     if (string == null) {
/* 22 */       throw new IllegalArgumentException("Metadata section name cannot be null");
/*    */     }
/* 24 */     if (!jsonObject.has(string)) {
/* 25 */       return null;
/*    */     }
/* 27 */     if (!jsonObject.get(string).isJsonObject()) {
/* 28 */       throw new IllegalArgumentException("Invalid metadata for '" + string + "' - expected object, found " + jsonObject.get(string));
/*    */     }
/*    */     
/* 31 */     MetadataSerializerRegistration metadataSerializerRegistration = (MetadataSerializerRegistration)this.metadataSectionSerializerRegistry.getObject(string);
/* 32 */     if (metadataSerializerRegistration == null) {
/* 33 */       throw new IllegalArgumentException("Don't know how to handle metadata section '" + string + "'");
/*    */     }
/*    */     
/* 36 */     return (MetadataSection)getGson().fromJson((JsonElement)jsonObject.getAsJsonObject(string), metadataSerializerRegistration.field_110500_b);
/*    */   }
/*    */   
/*    */   private Gson getGson() {
/* 40 */     if (this.gson == null) {
/* 41 */       this.gson = this.gsonBuilder.create();
/*    */     }
/* 43 */     return this.gson;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MetadataSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */