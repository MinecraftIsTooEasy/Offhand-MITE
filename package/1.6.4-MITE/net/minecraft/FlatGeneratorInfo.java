/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatGeneratorInfo
/*     */ {
/*  21 */   private final List flatLayers = new ArrayList();
/*  22 */   private final Map worldFeatures = new HashMap<Object, Object>();
/*     */   private int biomeToUse;
/*     */   
/*     */   public int getBiome() {
/*  26 */     return this.biomeToUse;
/*     */   }
/*     */   
/*     */   public void setBiome(int i) {
/*  30 */     this.biomeToUse = i;
/*     */   }
/*     */   
/*     */   public Map getWorldFeatures() {
/*  34 */     return this.worldFeatures;
/*     */   }
/*     */   
/*     */   public List getFlatLayers() {
/*  38 */     return this.flatLayers;
/*     */   }
/*     */   
/*     */   public void func_82645_d() {
/*  42 */     int i = 0;
/*     */     
/*  44 */     for (FlatLayerInfo flatLayerInfo : this.flatLayers) {
/*  45 */       flatLayerInfo.setMinY(i);
/*  46 */       i += flatLayerInfo.getLayerCount();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  52 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*  54 */     stringBuilder.append(2);
/*  55 */     stringBuilder.append(";");
/*     */     byte b;
/*  57 */     for (b = 0; b < this.flatLayers.size(); b++) {
/*  58 */       if (b > 0) stringBuilder.append(","); 
/*  59 */       stringBuilder.append(((FlatLayerInfo)this.flatLayers.get(b)).toString());
/*     */     } 
/*     */     
/*  62 */     stringBuilder.append(";");
/*  63 */     stringBuilder.append(this.biomeToUse);
/*     */     
/*  65 */     if (!this.worldFeatures.isEmpty()) {
/*  66 */       stringBuilder.append(";");
/*  67 */       b = 0;
/*     */       
/*  69 */       for (Map.Entry entry : this.worldFeatures.entrySet()) {
/*  70 */         if (b++ > 0) stringBuilder.append(","); 
/*  71 */         stringBuilder.append(((String)entry.getKey()).toLowerCase());
/*     */         
/*  73 */         Map map = (Map)entry.getValue();
/*  74 */         if (!map.isEmpty()) {
/*  75 */           stringBuilder.append("(");
/*  76 */           byte b1 = 0;
/*     */           
/*  78 */           for (Map.Entry entry1 : map.entrySet()) {
/*  79 */             if (b1++ > 0) stringBuilder.append(" "); 
/*  80 */             stringBuilder.append((String)entry1.getKey());
/*  81 */             stringBuilder.append("=");
/*  82 */             stringBuilder.append((String)entry1.getValue());
/*     */           } 
/*     */           
/*  85 */           stringBuilder.append(")");
/*     */         } 
/*     */       } 
/*     */     } else {
/*  89 */       stringBuilder.append(";");
/*     */     } 
/*     */     
/*  92 */     return stringBuilder.toString();
/*     */   }
/*     */   private static FlatLayerInfo func_82646_a(String string, int i) {
/*     */     int k;
/*  96 */     String[] arrayOfString = string.split("x", 2);
/*  97 */     int j = 1;
/*     */     
/*  99 */     int m = 0;
/*     */     
/* 101 */     if (arrayOfString.length == 2) {
/*     */       try {
/* 103 */         j = Integer.parseInt(arrayOfString[0]);
/* 104 */         if (i + j >= 256) j = 256 - i; 
/* 105 */         if (j < 0) j = 0; 
/* 106 */       } catch (Throwable throwable) {
/* 107 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     try {
/* 112 */       String str = arrayOfString[arrayOfString.length - 1];
/* 113 */       arrayOfString = str.split(":", 2);
/* 114 */       k = Integer.parseInt(arrayOfString[0]);
/* 115 */       if (arrayOfString.length > 1) m = Integer.parseInt(arrayOfString[1]);
/*     */       
/* 117 */       if (Block.blocksList[k] == null) {
/* 118 */         k = 0;
/* 119 */         m = 0;
/*     */       } 
/*     */       
/* 122 */       if (m < 0 || m > 15) m = 0; 
/* 123 */     } catch (Throwable throwable) {
/* 124 */       return null;
/*     */     } 
/*     */     
/* 127 */     FlatLayerInfo flatLayerInfo = new FlatLayerInfo(j, k, m);
/* 128 */     flatLayerInfo.setMinY(i);
/* 129 */     return flatLayerInfo;
/*     */   }
/*     */   
/*     */   private static List func_82652_b(String string) {
/* 133 */     if (string == null || string.length() < 1) return null;
/*     */     
/* 135 */     ArrayList<FlatLayerInfo> arrayList = new ArrayList();
/* 136 */     String[] arrayOfString = string.split(",");
/* 137 */     int i = 0;
/*     */     
/* 139 */     for (String str : arrayOfString) {
/* 140 */       FlatLayerInfo flatLayerInfo = func_82646_a(str, i);
/* 141 */       if (flatLayerInfo == null) return null; 
/* 142 */       arrayList.add(flatLayerInfo);
/* 143 */       i += flatLayerInfo.getLayerCount();
/*     */     } 
/*     */     
/* 146 */     return arrayList;
/*     */   }
/*     */   
/*     */   public static FlatGeneratorInfo createFlatGeneratorFromString(String string) {
/* 150 */     if (string == null) return getDefaultFlatGenerator(); 
/* 151 */     String[] arrayOfString = string.split(";", -1);
/* 152 */     byte b1 = (arrayOfString.length == 1) ? 0 : MathHelper.parseIntWithDefault(arrayOfString[0], 0);
/* 153 */     if (!b1 || b1 > 2) return getDefaultFlatGenerator();
/*     */     
/* 155 */     FlatGeneratorInfo flatGeneratorInfo = new FlatGeneratorInfo();
/* 156 */     byte b2 = (arrayOfString.length == 1) ? 0 : 1;
/* 157 */     List list = func_82652_b(arrayOfString[b2++]);
/*     */     
/* 159 */     if (list == null || list.isEmpty()) {
/* 160 */       return getDefaultFlatGenerator();
/*     */     }
/*     */     
/* 163 */     flatGeneratorInfo.getFlatLayers().addAll(list);
/* 164 */     flatGeneratorInfo.func_82645_d();
/*     */     
/* 166 */     int i = BiomeGenBase.plains.biomeID;
/* 167 */     if (b1 > 0 && arrayOfString.length > b2) i = MathHelper.parseIntWithDefault(arrayOfString[b2++], i); 
/* 168 */     flatGeneratorInfo.setBiome(i);
/*     */     
/* 170 */     if (b1 > 0 && arrayOfString.length > b2) {
/* 171 */       String[] arrayOfString1 = arrayOfString[b2++].toLowerCase().split(",");
/*     */       
/* 173 */       for (String str : arrayOfString1) {
/* 174 */         String[] arrayOfString2 = str.split("\\(", 2);
/* 175 */         HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
/*     */         
/* 177 */         if (arrayOfString2[0].length() > 0) {
/* 178 */           flatGeneratorInfo.getWorldFeatures().put(arrayOfString2[0], hashMap);
/*     */           
/* 180 */           if (arrayOfString2.length > 1 && arrayOfString2[1].endsWith(")") && arrayOfString2[1].length() > 1) {
/* 181 */             String[] arrayOfString3 = arrayOfString2[1].substring(0, arrayOfString2[1].length() - 1).split(" ");
/*     */             
/* 183 */             for (byte b = 0; b < arrayOfString3.length; b++) {
/* 184 */               String[] arrayOfString4 = arrayOfString3[b].split("=", 2);
/* 185 */               if (arrayOfString4.length == 2) hashMap.put(arrayOfString4[0], arrayOfString4[1]); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 191 */       flatGeneratorInfo.getWorldFeatures().put("village", new HashMap<Object, Object>());
/*     */     } 
/*     */     
/* 194 */     return flatGeneratorInfo;
/*     */   }
/*     */   
/*     */   public static FlatGeneratorInfo getDefaultFlatGenerator() {
/* 198 */     FlatGeneratorInfo flatGeneratorInfo = new FlatGeneratorInfo();
/*     */     
/* 200 */     flatGeneratorInfo.setBiome(BiomeGenBase.plains.biomeID);
/* 201 */     flatGeneratorInfo.getFlatLayers().add(new FlatLayerInfo(1, Block.bedrock.blockID));
/* 202 */     flatGeneratorInfo.getFlatLayers().add(new FlatLayerInfo(2, Block.dirt.blockID));
/* 203 */     flatGeneratorInfo.getFlatLayers().add(new FlatLayerInfo(1, Block.grass.blockID));
/* 204 */     flatGeneratorInfo.func_82645_d();
/* 205 */     flatGeneratorInfo.getWorldFeatures().put("village", new HashMap<Object, Object>());
/*     */     
/* 207 */     return flatGeneratorInfo;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FlatGeneratorInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */