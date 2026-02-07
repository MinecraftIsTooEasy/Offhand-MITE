/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.SortedSet;
/*     */ 
/*     */ public class LanguageManager
/*     */   implements ResourceManagerReloadListener {
/*     */   private final MetadataSerializer field_135047_b;
/*     */   private String currentLanguage;
/*  17 */   protected static final Locale currentLocale = new Locale();
/*  18 */   private Map languageMap = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public LanguageManager(MetadataSerializer par1MetadataSerializer, String par2Str) {
/*  22 */     this.field_135047_b = par1MetadataSerializer;
/*  23 */     this.currentLanguage = par2Str;
/*  24 */     I18n.setLocale(currentLocale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void parseLanguageMetadata(List par1List) {
/*  29 */     this.languageMap.clear();
/*  30 */     Iterator<ResourcePack> var2 = par1List.iterator();
/*     */     
/*  32 */     while (var2.hasNext()) {
/*     */       
/*  34 */       ResourcePack var3 = var2.next();
/*     */ 
/*     */       
/*     */       try {
/*  38 */         LanguageMetadataSection var4 = (LanguageMetadataSection)var3.getPackMetadata(this.field_135047_b, "language");
/*     */         
/*  40 */         if (var4 != null) {
/*     */           
/*  42 */           Iterator<Language> var5 = var4.getLanguages().iterator();
/*     */           
/*  44 */           while (var5.hasNext())
/*     */           {
/*  46 */             Language var6 = var5.next();
/*     */             
/*  48 */             if (!this.languageMap.containsKey(var6.getLanguageCode()))
/*     */             {
/*  50 */               this.languageMap.put(var6.getLanguageCode(), var6);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*  55 */       } catch (RuntimeException var7) {
/*     */         
/*  57 */         Minecraft.getMinecraft().getLogAgent().logWarningException("Unable to parse metadata section of resourcepack: " + var3.getPackName(), var7);
/*     */       }
/*  59 */       catch (IOException var8) {
/*     */         
/*  61 */         Minecraft.getMinecraft().getLogAgent().logWarningException("Unable to parse metadata section of resourcepack: " + var3.getPackName(), var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(ResourceManager par1ResourceManager) {
/*  68 */     ArrayList<String> var2 = Lists.newArrayList((Object[])new String[] { "en_US" });
/*     */     
/*  70 */     if (!"en_US".equals(this.currentLanguage))
/*     */     {
/*  72 */       var2.add(this.currentLanguage);
/*     */     }
/*     */     
/*  75 */     var2.add("MITE");
/*     */     
/*  77 */     currentLocale.loadLocaleDataFiles(par1ResourceManager, var2);
/*  78 */     StringTranslate.func_135063_a(currentLocale.field_135032_a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCurrentLocaleUnicode() {
/*  83 */     return currentLocale.isUnicode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCurrentLanguageBidirectional() {
/*  88 */     return getCurrentLanguage().isBidirectional();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentLanguage(Language par1Language) {
/*  93 */     this.currentLanguage = par1Language.getLanguageCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public Language getCurrentLanguage() {
/*  98 */     return this.languageMap.containsKey(this.currentLanguage) ? (Language)this.languageMap.get(this.currentLanguage) : (Language)this.languageMap.get("en_US");
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedSet getLanguages() {
/* 103 */     return Sets.newTreeSet(this.languageMap.values());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LanguageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */