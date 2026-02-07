/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseMetadataSectionSerializer
/*     */   implements MetadataSectionSerializer
/*     */ {
/*     */   protected float func_110487_a(JsonElement jsonElement, String string, Float float_, float f, float g) {
/*  14 */     string = getSectionName() + "->" + string;
/*     */     
/*  16 */     if (jsonElement == null) {
/*  17 */       if (float_ == null) {
/*  18 */         throw new JsonParseException("Missing " + string + ": expected float");
/*     */       }
/*  20 */       return float_.floatValue();
/*     */     } 
/*     */ 
/*     */     
/*  24 */     if (!jsonElement.isJsonPrimitive()) {
/*  25 */       throw new JsonParseException("Invalid " + string + ": expected float, was " + jsonElement);
/*     */     }
/*     */     
/*     */     try {
/*  29 */       float f1 = jsonElement.getAsFloat();
/*     */       
/*  31 */       if (f1 < f)
/*  32 */         throw new JsonParseException("Invalid " + string + ": expected float >= " + f + ", was " + f1); 
/*  33 */       if (f1 > g) {
/*  34 */         throw new JsonParseException("Invalid " + string + ": expected float <= " + g + ", was " + f1);
/*     */       }
/*     */       
/*  37 */       return f1;
/*  38 */     } catch (NumberFormatException numberFormatException) {
/*  39 */       throw new JsonParseException("Invalid " + string + ": expected float, was " + jsonElement, numberFormatException);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_110485_a(JsonElement jsonElement, String string, Integer integer, int i, int j) {
/*  49 */     string = getSectionName() + "->" + string;
/*     */     
/*  51 */     if (jsonElement == null) {
/*  52 */       if (integer == null) {
/*  53 */         throw new JsonParseException("Missing " + string + ": expected int");
/*     */       }
/*  55 */       return integer.intValue();
/*     */     } 
/*     */ 
/*     */     
/*  59 */     if (!jsonElement.isJsonPrimitive()) {
/*  60 */       throw new JsonParseException("Invalid " + string + ": expected int, was " + jsonElement);
/*     */     }
/*     */     
/*     */     try {
/*  64 */       int k = jsonElement.getAsInt();
/*     */       
/*  66 */       if (k < i)
/*  67 */         throw new JsonParseException("Invalid " + string + ": expected int >= " + i + ", was " + k); 
/*  68 */       if (k > j) {
/*  69 */         throw new JsonParseException("Invalid " + string + ": expected int <= " + j + ", was " + k);
/*     */       }
/*     */       
/*  72 */       return k;
/*  73 */     } catch (NumberFormatException numberFormatException) {
/*  74 */       throw new JsonParseException("Invalid " + string + ": expected int, was " + jsonElement, numberFormatException);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_110486_a(JsonElement jsonElement, String string, String string2, int i, int j) {
/*  84 */     string = getSectionName() + "->" + string;
/*     */     
/*  86 */     if (jsonElement == null) {
/*  87 */       if (string2 == null) {
/*  88 */         throw new JsonParseException("Missing " + string + ": expected string");
/*     */       }
/*  90 */       return string2;
/*     */     } 
/*     */ 
/*     */     
/*  94 */     if (!jsonElement.isJsonPrimitive()) {
/*  95 */       throw new JsonParseException("Invalid " + string + ": expected string, was " + jsonElement);
/*     */     }
/*     */     
/*  98 */     String str = jsonElement.getAsString();
/*     */     
/* 100 */     if (str.length() < i)
/* 101 */       throw new JsonParseException("Invalid " + string + ": expected string length >= " + i + ", was " + str); 
/* 102 */     if (str.length() > j) {
/* 103 */       throw new JsonParseException("Invalid " + string + ": expected string length <= " + j + ", was " + str);
/*     */     }
/*     */     
/* 106 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_110484_a(JsonElement jsonElement, String string, Boolean boolean_) {
/* 115 */     string = getSectionName() + "->" + string;
/*     */     
/* 117 */     if (jsonElement == null) {
/* 118 */       if (boolean_ == null) {
/* 119 */         throw new JsonParseException("Missing " + string + ": expected boolean");
/*     */       }
/* 121 */       return boolean_.booleanValue();
/*     */     } 
/*     */ 
/*     */     
/* 125 */     if (!jsonElement.isJsonPrimitive()) {
/* 126 */       throw new JsonParseException("Invalid " + string + ": expected boolean, was " + jsonElement);
/*     */     }
/*     */     
/* 129 */     return jsonElement.getAsBoolean();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BaseMetadataSectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */