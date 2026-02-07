/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer {
/*    */   public PackMetadataSection func_110489_a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
/* 12 */     JsonObject jsonObject = jsonElement.getAsJsonObject();
/* 13 */     String str = func_110486_a(jsonObject.get("description"), "description", null, 1, 2147483647);
/* 14 */     int i = func_110485_a(jsonObject.get("pack_format"), "pack_format", null, 1, 2147483647);
/* 15 */     return new PackMetadataSection(str, i);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement func_110488_a(PackMetadataSection packMetadataSection, Type type, JsonSerializationContext jsonSerializationContext) {
/* 20 */     JsonObject jsonObject = new JsonObject();
/*    */     
/* 22 */     jsonObject.addProperty("pack_format", Integer.valueOf(packMetadataSection.getPackFormat()));
/* 23 */     jsonObject.addProperty("description", packMetadataSection.getPackDescription());
/*    */     
/* 25 */     return (JsonElement)jsonObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 30 */     return "pack";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PackMetadataSectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */