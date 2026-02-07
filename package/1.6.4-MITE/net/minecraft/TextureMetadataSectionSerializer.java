/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class TextureMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer
/*    */ {
/*    */   public TextureMetadataSection func_110494_a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
/* 12 */     JsonObject jsonObject = jsonElement.getAsJsonObject();
/* 13 */     boolean bool1 = func_110484_a(jsonObject.get("blur"), "blur", Boolean.valueOf(false));
/* 14 */     boolean bool2 = func_110484_a(jsonObject.get("clamp"), "clamp", Boolean.valueOf(false));
/* 15 */     return new TextureMetadataSection(bool1, bool2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 20 */     return "texture";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TextureMetadataSectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */