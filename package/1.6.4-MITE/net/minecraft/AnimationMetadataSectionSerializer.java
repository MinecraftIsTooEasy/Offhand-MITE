/*    */ package net.minecraft;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class AnimationMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer {
/*    */   public AnimationMetadataSection func_110493_a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
/* 15 */     ArrayList<AnimationFrame> arrayList = Lists.newArrayList();
/* 16 */     JsonObject jsonObject = (JsonObject)jsonElement;
/* 17 */     int i = func_110485_a(jsonObject.get("frametime"), "frametime", Integer.valueOf(1), 1, 2147483647);
/*    */     
/* 19 */     if (jsonObject.has("frames")) {
/*    */       try {
/* 21 */         JsonArray jsonArray = jsonObject.getAsJsonArray("frames");
/*    */         
/* 23 */         for (byte b = 0; b < jsonArray.size(); b++) {
/* 24 */           JsonElement jsonElement1 = jsonArray.get(b);
/*    */           
/* 26 */           AnimationFrame animationFrame = parseAnimationFrame(b, jsonElement1);
/* 27 */           if (animationFrame != null) arrayList.add(animationFrame); 
/*    */         } 
/* 29 */       } catch (ClassCastException classCastException) {
/* 30 */         throw new JsonParseException("Invalid animation->frames: expected array, was " + jsonObject.get("frames"), classCastException);
/*    */       } 
/*    */     }
/*    */     
/* 34 */     int j = func_110485_a(jsonObject.get("width"), "width", Integer.valueOf(-1), 1, 2147483647);
/* 35 */     int k = func_110485_a(jsonObject.get("height"), "height", Integer.valueOf(-1), 1, 2147483647);
/*    */     
/* 37 */     return new AnimationMetadataSection(arrayList, j, k, i);
/*    */   }
/*    */   
/*    */   private AnimationFrame parseAnimationFrame(int i, JsonElement jsonElement) {
/* 41 */     if (jsonElement.isJsonPrimitive())
/*    */       try {
/* 43 */         return new AnimationFrame(jsonElement.getAsInt());
/* 44 */       } catch (NumberFormatException numberFormatException) {
/* 45 */         throw new JsonParseException("Invalid animation->frames->" + i + ": expected number, was " + jsonElement, numberFormatException);
/*    */       }  
/* 47 */     if (jsonElement.isJsonObject()) {
/* 48 */       JsonObject jsonObject = jsonElement.getAsJsonObject();
/* 49 */       int j = func_110485_a(jsonObject.get("time"), "frames->" + i + "->time", Integer.valueOf(-1), 1, 2147483647);
/* 50 */       int k = func_110485_a(jsonObject.get("index"), "frames->" + i + "->index", null, 0, 2147483647);
/* 51 */       return new AnimationFrame(k, j);
/*    */     } 
/*    */     
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement func_110491_a(AnimationMetadataSection animationMetadataSection, Type type, JsonSerializationContext jsonSerializationContext) {
/* 60 */     JsonObject jsonObject = new JsonObject();
/*    */     
/* 62 */     jsonObject.addProperty("frametime", Integer.valueOf(animationMetadataSection.getFrameTime()));
/* 63 */     if (animationMetadataSection.getFrameWidth() != -1) jsonObject.addProperty("width", Integer.valueOf(animationMetadataSection.getFrameWidth())); 
/* 64 */     if (animationMetadataSection.getFrameHeight() != -1) jsonObject.addProperty("height", Integer.valueOf(animationMetadataSection.getFrameHeight()));
/*    */     
/* 66 */     if (animationMetadataSection.getFrameCount() > 0) {
/* 67 */       JsonArray jsonArray = new JsonArray();
/* 68 */       for (byte b = 0; b < animationMetadataSection.getFrameCount(); b++) {
/* 69 */         if (animationMetadataSection.frameHasTime(b)) {
/* 70 */           JsonObject jsonObject1 = new JsonObject();
/*    */           
/* 72 */           jsonObject1.addProperty("index", Integer.valueOf(animationMetadataSection.getFrameIndex(b)));
/* 73 */           jsonObject1.addProperty("time", Integer.valueOf(animationMetadataSection.getFrameTimeSingle(b)));
/*    */           
/* 75 */           jsonArray.add((JsonElement)jsonObject1);
/*    */         } else {
/* 77 */           jsonArray.add((JsonElement)new JsonPrimitive(Integer.valueOf(animationMetadataSection.getFrameIndex(b))));
/*    */         } 
/*    */       } 
/* 80 */       jsonObject.add("frames", (JsonElement)jsonArray);
/*    */     } 
/*    */     
/* 83 */     return (JsonElement)jsonObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 88 */     return "animation";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AnimationMetadataSectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */