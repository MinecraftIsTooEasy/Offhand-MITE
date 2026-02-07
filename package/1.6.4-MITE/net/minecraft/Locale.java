/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Locale
/*     */ {
/*  21 */   private static final Splitter splitter = Splitter.on('=').limit(2);
/*  22 */   private static final Pattern field_135031_c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
/*     */   
/*  24 */   Map field_135032_a = Maps.newHashMap();
/*     */   private boolean field_135029_d;
/*     */   
/*     */   public synchronized void loadLocaleDataFiles(ResourceManager resourceManager, List list) {
/*  28 */     this.field_135032_a.clear();
/*     */     
/*  30 */     for (String str1 : list) {
/*  31 */       String str2 = String.format("lang/%s.lang", new Object[] { str1 });
/*     */       
/*  33 */       for (String str : resourceManager.getResourceDomains()) {
/*     */         try {
/*  35 */           loadLocaleData(resourceManager.getAllResources(new ResourceLocation(str, str2)));
/*  36 */         } catch (IOException iOException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  41 */     checkUnicode();
/*     */   }
/*     */   
/*     */   public boolean isUnicode() {
/*  45 */     return this.field_135029_d;
/*     */   }
/*     */   
/*     */   private void checkUnicode() {
/*  49 */     this.field_135029_d = false;
/*  50 */     for (String str : this.field_135032_a.values()) {
/*  51 */       for (byte b = 0; b < str.length(); b++) {
/*  52 */         if (str.charAt(b) >= 'Ā') {
/*  53 */           this.field_135029_d = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadLocaleData(List list) {
/*  62 */     for (Resource resource : list) {
/*  63 */       loadLocaleData(resource.getInputStream());
/*     */     }
/*     */   }
/*     */   
/*     */   private void loadLocaleData(InputStream inputStream) {
/*  68 */     for (String str1 : IOUtils.readLines(inputStream, Charsets.UTF_8)) {
/*     */       
/*  70 */       if (str1.isEmpty() || str1.charAt(0) == '#')
/*     */         continue; 
/*  72 */       String[] arrayOfString = (String[])Iterables.toArray(splitter.split(str1), String.class);
/*     */ 
/*     */       
/*  75 */       if (arrayOfString == null || arrayOfString.length != 2) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/*  80 */       String str2 = arrayOfString[0];
/*  81 */       String str3 = field_135031_c.matcher(arrayOfString[1]).replaceAll("%$1s");
/*     */       
/*  83 */       this.field_135032_a.put(str2, str3);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String translateKeyPrivate(String string) {
/*  88 */     String str = (String)this.field_135032_a.get(string);
/*  89 */     return (str == null) ? string : str;
/*     */   }
/*     */   
/*     */   public String translateKey(String string) {
/*  93 */     return translateKeyPrivate(string);
/*     */   }
/*     */   
/*     */   public String formatMessage(String string, Object[] objects) {
/*  97 */     String str = translateKeyPrivate(string);
/*     */     try {
/*  99 */       return String.format(str, objects);
/* 100 */     } catch (IllegalFormatException illegalFormatException) {
/* 101 */       return "Format error: " + str;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Locale.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */