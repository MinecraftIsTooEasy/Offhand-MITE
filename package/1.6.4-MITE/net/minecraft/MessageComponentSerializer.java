/*     */ package net.minecraft;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MessageComponentSerializer implements JsonDeserializer, JsonSerializer {
/*     */   public ChatMessageComponent deserializeComponent(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
/*  13 */     ChatMessageComponent chatMessageComponent = new ChatMessageComponent();
/*  14 */     JsonObject jsonObject = (JsonObject)jsonElement;
/*  15 */     JsonElement jsonElement1 = jsonObject.get("text");
/*  16 */     JsonElement jsonElement2 = jsonObject.get("translate");
/*  17 */     JsonElement jsonElement3 = jsonObject.get("color");
/*  18 */     JsonElement jsonElement4 = jsonObject.get("bold");
/*  19 */     JsonElement jsonElement5 = jsonObject.get("italic");
/*  20 */     JsonElement jsonElement6 = jsonObject.get("underlined");
/*  21 */     JsonElement jsonElement7 = jsonObject.get("obfuscated");
/*     */     
/*  23 */     if (jsonElement3 != null && jsonElement3.isJsonPrimitive()) {
/*  24 */       EnumChatFormatting enumChatFormatting = EnumChatFormatting.func_96300_b(jsonElement3.getAsString());
/*  25 */       if (enumChatFormatting == null || !enumChatFormatting.isColor()) {
/*  26 */         throw new JsonParseException("Given color (" + jsonElement3.getAsString() + ") is not a valid selection");
/*     */       }
/*  28 */       chatMessageComponent.setColor(enumChatFormatting);
/*     */     } 
/*  30 */     if (jsonElement4 != null && jsonElement4.isJsonPrimitive()) {
/*  31 */       chatMessageComponent.setBold(Boolean.valueOf(jsonElement4.getAsBoolean()));
/*     */     }
/*  33 */     if (jsonElement5 != null && jsonElement5.isJsonPrimitive()) {
/*  34 */       chatMessageComponent.setItalic(Boolean.valueOf(jsonElement5.getAsBoolean()));
/*     */     }
/*  36 */     if (jsonElement6 != null && jsonElement6.isJsonPrimitive()) {
/*  37 */       chatMessageComponent.setUnderline(Boolean.valueOf(jsonElement6.getAsBoolean()));
/*     */     }
/*  39 */     if (jsonElement7 != null && jsonElement7.isJsonPrimitive()) {
/*  40 */       chatMessageComponent.setObfuscated(Boolean.valueOf(jsonElement7.getAsBoolean()));
/*     */     }
/*     */     
/*  43 */     if (jsonElement1 != null) {
/*  44 */       if (jsonElement1.isJsonArray()) {
/*  45 */         JsonArray jsonArray = jsonElement1.getAsJsonArray();
/*     */         
/*  47 */         for (JsonElement jsonElement8 : jsonArray) {
/*  48 */           if (jsonElement8.isJsonPrimitive()) {
/*  49 */             chatMessageComponent.addText(jsonElement8.getAsString()); continue;
/*  50 */           }  if (jsonElement8.isJsonObject()) {
/*  51 */             chatMessageComponent.appendComponent(deserializeComponent(jsonElement8, type, jsonDeserializationContext));
/*     */           }
/*     */         } 
/*  54 */       } else if (jsonElement1.isJsonPrimitive()) {
/*  55 */         chatMessageComponent.addText(jsonElement1.getAsString());
/*     */       } 
/*  57 */     } else if (jsonElement2 != null && jsonElement2.isJsonPrimitive()) {
/*  58 */       JsonElement jsonElement8 = jsonObject.get("using");
/*     */       
/*  60 */       if (jsonElement8 != null) {
/*  61 */         if (jsonElement8.isJsonArray()) {
/*  62 */           ArrayList<String> arrayList = Lists.newArrayList();
/*     */           
/*  64 */           for (JsonElement jsonElement9 : jsonElement8.getAsJsonArray()) {
/*  65 */             if (jsonElement9.isJsonPrimitive()) {
/*  66 */               arrayList.add(jsonElement9.getAsString()); continue;
/*  67 */             }  if (jsonElement9.isJsonObject()) {
/*  68 */               arrayList.add(deserializeComponent(jsonElement9, type, jsonDeserializationContext));
/*     */             }
/*     */           } 
/*     */           
/*  72 */           chatMessageComponent.addFormatted(jsonElement2.getAsString(), arrayList.toArray());
/*  73 */         } else if (jsonElement8.isJsonPrimitive()) {
/*  74 */           chatMessageComponent.addFormatted(jsonElement2.getAsString(), new Object[] { jsonElement8.getAsString() });
/*     */         } 
/*     */       } else {
/*  77 */         chatMessageComponent.addKey(jsonElement2.getAsString());
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     return chatMessageComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement serializeComponent(ChatMessageComponent chatMessageComponent, Type type, JsonSerializationContext jsonSerializationContext) {
/*  86 */     JsonObject jsonObject = new JsonObject();
/*     */     
/*  88 */     if (chatMessageComponent.getColor() != null) {
/*  89 */       jsonObject.addProperty("color", chatMessageComponent.getColor().func_96297_d());
/*     */     }
/*  91 */     if (chatMessageComponent.isBold() != null) {
/*  92 */       jsonObject.addProperty("bold", chatMessageComponent.isBold());
/*     */     }
/*  94 */     if (chatMessageComponent.isItalic() != null) {
/*  95 */       jsonObject.addProperty("italic", chatMessageComponent.isItalic());
/*     */     }
/*  97 */     if (chatMessageComponent.isUnderline() != null) {
/*  98 */       jsonObject.addProperty("underlined", chatMessageComponent.isUnderline());
/*     */     }
/* 100 */     if (chatMessageComponent.isObfuscated() != null) {
/* 101 */       jsonObject.addProperty("obfuscated", chatMessageComponent.isObfuscated());
/*     */     }
/*     */     
/* 104 */     if (chatMessageComponent.getText() != null) {
/* 105 */       jsonObject.addProperty("text", chatMessageComponent.getText());
/* 106 */     } else if (chatMessageComponent.getTranslationKey() != null) {
/* 107 */       jsonObject.addProperty("translate", chatMessageComponent.getTranslationKey());
/*     */       
/* 109 */       if (chatMessageComponent.getSubComponents() != null && !chatMessageComponent.getSubComponents().isEmpty()) {
/* 110 */         jsonObject.add("using", (JsonElement)serializeComponentChildren(chatMessageComponent, type, jsonSerializationContext));
/*     */       }
/* 112 */     } else if (chatMessageComponent.getSubComponents() != null && !chatMessageComponent.getSubComponents().isEmpty()) {
/* 113 */       jsonObject.add("text", (JsonElement)serializeComponentChildren(chatMessageComponent, type, jsonSerializationContext));
/*     */     } 
/*     */     
/* 116 */     return (JsonElement)jsonObject;
/*     */   }
/*     */   
/*     */   private JsonArray serializeComponentChildren(ChatMessageComponent chatMessageComponent, Type type, JsonSerializationContext jsonSerializationContext) {
/* 120 */     JsonArray jsonArray = new JsonArray();
/*     */     
/* 122 */     for (ChatMessageComponent chatMessageComponent1 : chatMessageComponent.getSubComponents()) {
/* 123 */       if (chatMessageComponent1.getText() != null) {
/* 124 */         jsonArray.add((JsonElement)new JsonPrimitive(chatMessageComponent1.getText())); continue;
/*     */       } 
/* 126 */       jsonArray.add(serializeComponent(chatMessageComponent1, type, jsonSerializationContext));
/*     */     } 
/*     */     
/* 129 */     return jsonArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MessageComponentSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */