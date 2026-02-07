/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class FontMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer {
/*    */   public FontMetadataSection func_110490_a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
/* 12 */     JsonObject jsonObject = jsonElement.getAsJsonObject();
/* 13 */     float[] arrayOfFloat1 = new float[256];
/* 14 */     float[] arrayOfFloat2 = new float[256];
/* 15 */     float[] arrayOfFloat3 = new float[256];
/* 16 */     float f1 = 1.0F;
/* 17 */     float f2 = 0.0F;
/* 18 */     float f3 = 0.0F;
/*    */     
/* 20 */     if (jsonObject.has("characters")) {
/* 21 */       if (!jsonObject.get("characters").isJsonObject()) {
/* 22 */         throw new JsonParseException("Invalid font->characters: expected object, was " + jsonObject.get("characters"));
/*    */       }
/*    */       
/* 25 */       JsonObject jsonObject1 = jsonObject.getAsJsonObject("characters");
/*    */       
/* 27 */       if (jsonObject1.has("default")) {
/* 28 */         if (!jsonObject1.get("default").isJsonObject()) {
/* 29 */           throw new JsonParseException("Invalid font->characters->default: expected object, was " + jsonObject1.get("default"));
/*    */         }
/* 31 */         JsonObject jsonObject2 = jsonObject1.getAsJsonObject("default");
/*    */         
/* 33 */         f1 = func_110487_a(jsonObject2.get("width"), "characters->default->width", Float.valueOf(f1), 0.0F, 2.1474836E9F);
/* 34 */         f2 = func_110487_a(jsonObject2.get("spacing"), "characters->default->spacing", Float.valueOf(f2), 0.0F, 2.1474836E9F);
/* 35 */         f3 = func_110487_a(jsonObject2.get("left"), "characters->default->left", Float.valueOf(f3), 0.0F, 2.1474836E9F);
/*    */       } 
/*    */       
/* 38 */       for (byte b = 0; b < 'Ā'; b++) {
/* 39 */         JsonElement jsonElement1 = jsonObject1.get(Integer.toString(b));
/* 40 */         float f4 = f1;
/* 41 */         float f5 = f2;
/* 42 */         float f6 = f3;
/*    */         
/* 44 */         if (jsonElement1 != null) {
/* 45 */           if (jsonElement1.isJsonObject()) {
/* 46 */             JsonObject jsonObject2 = jsonElement1.getAsJsonObject();
/* 47 */             f4 = func_110487_a(jsonObject2.get("width"), "characters->" + b + "->width", Float.valueOf(f4), 0.0F, 2.1474836E9F);
/* 48 */             f5 = func_110487_a(jsonObject2.get("spacing"), "characters->" + b + "->spacing", Float.valueOf(f5), 0.0F, 2.1474836E9F);
/* 49 */             f6 = func_110487_a(jsonObject2.get("left"), "characters->" + b + "->left", Float.valueOf(f6), 0.0F, 2.1474836E9F);
/*    */           } else {
/* 51 */             throw new JsonParseException("Invalid font->characters->" + b + ": expected object, was " + jsonElement1);
/*    */           } 
/*    */         }
/*    */         
/* 55 */         arrayOfFloat1[b] = f4;
/* 56 */         arrayOfFloat2[b] = f5;
/* 57 */         arrayOfFloat3[b] = f6;
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     return new FontMetadataSection(arrayOfFloat1, arrayOfFloat3, arrayOfFloat2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 66 */     return "font";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FontMetadataSectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */