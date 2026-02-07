/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LanguageMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer
/*    */ {
/*    */   public LanguageMetadataSection func_135020_a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
/* 18 */     JsonObject jsonObject = jsonElement.getAsJsonObject();
/* 19 */     HashSet<Language> hashSet = Sets.newHashSet();
/*    */     
/* 21 */     for (Map.Entry entry : jsonObject.entrySet()) {
/* 22 */       String str1 = (String)entry.getKey();
/* 23 */       JsonElement jsonElement1 = (JsonElement)entry.getValue();
/*    */       
/* 25 */       if (!jsonElement1.isJsonObject()) {
/* 26 */         throw new JsonParseException("Invalid language->'" + str1 + "': expected object, was " + jsonElement1);
/*    */       }
/*    */       
/* 29 */       JsonObject jsonObject1 = jsonElement1.getAsJsonObject();
/*    */ 
/*    */       
/* 32 */       String str2 = func_110486_a(jsonObject1.get("region"), "region", "", 0, 2147483647);
/* 33 */       String str3 = func_110486_a(jsonObject1.get("name"), "name", "", 0, 2147483647);
/* 34 */       boolean bool = func_110484_a(jsonObject1.get("bidirectional"), "bidirectional", Boolean.valueOf(false));
/*    */       
/* 36 */       if (str2.isEmpty()) {
/* 37 */         throw new JsonParseException("Invalid language->'" + str1 + "'->region: empty value");
/*    */       }
/*    */       
/* 40 */       if (str3.isEmpty()) {
/* 41 */         throw new JsonParseException("Invalid language->'" + str1 + "'->name: empty value");
/*    */       }
/*    */       
/* 44 */       if (!hashSet.add(new Language(str1, str2, str3, bool))) {
/* 45 */         throw new JsonParseException("Duplicate language->'" + str1 + "' defined");
/*    */       }
/*    */     } 
/*    */     
/* 49 */     return new LanguageMetadataSection(hashSet);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 54 */     return "language";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LanguageMetadataSectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */