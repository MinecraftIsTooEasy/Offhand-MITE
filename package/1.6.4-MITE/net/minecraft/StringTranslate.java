/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class StringTranslate
/*     */ {
/*  17 */   private static final Pattern field_111053_a = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
/*  18 */   private static final Splitter field_135065_b = Splitter.on('=').limit(2);
/*     */ 
/*     */   
/*  21 */   private static StringTranslate instance = new StringTranslate();
/*  22 */   private Map languageList = Maps.newHashMap();
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
/*     */   public StringTranslate() {
/*     */     try {
/*  60 */       for (int i = 0; i < 2; i++) {
/*     */         
/*  62 */         InputStream var1 = StringTranslate.class.getResourceAsStream("/assets/minecraft/lang/" + ((i == 0) ? "MITE.lang" : "en_US.lang"));
/*     */         
/*  64 */         if (i != 0 || var1 != null) {
/*     */ 
/*     */           
/*  67 */           Iterator<String> var2 = IOUtils.readLines(var1, Charsets.UTF_8).iterator();
/*     */           
/*  69 */           while (var2.hasNext()) {
/*     */             
/*  71 */             String var3 = var2.next();
/*     */             
/*  73 */             if (!var3.isEmpty() && var3.charAt(0) != '#') {
/*     */               
/*  75 */               String[] var4 = (String[])Iterables.toArray(field_135065_b.split(var3), String.class);
/*     */               
/*  77 */               if (var4 != null && var4.length == 2) {
/*     */                 
/*  79 */                 String var5 = var4[0];
/*  80 */                 String var6 = field_111053_a.matcher(var4[1]).replaceAll("%$1s");
/*  81 */                 this.languageList.put(var5, var6);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*  87 */     } catch (IOException var7) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static StringTranslate getInstance() {
/*  98 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   public static synchronized void func_135063_a(Map par0Map) {
/* 103 */     instance.languageList.clear();
/* 104 */     instance.languageList.putAll(par0Map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String translateKey(String par1Str) {
/* 112 */     return func_135064_c(par1Str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String translateKeyFormat(String par1Str, Object... par2ArrayOfObj) {
/* 120 */     String var3 = func_135064_c(par1Str);
/*     */ 
/*     */     
/*     */     try {
/* 124 */       return String.format(var3, par2ArrayOfObj);
/*     */     }
/* 126 */     catch (IllegalFormatException var5) {
/*     */       
/* 128 */       return "Format error: " + var3;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_135064_c(String par1Str) {
/* 134 */     String var2 = (String)this.languageList.get(par1Str);
/* 135 */     return (var2 == null) ? par1Str : var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean containsTranslateKey(String par1Str) {
/* 140 */     return this.languageList.containsKey(par1Str);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\StringTranslate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */