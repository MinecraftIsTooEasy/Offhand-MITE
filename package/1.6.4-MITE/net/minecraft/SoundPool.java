/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class SoundPool
/*     */ {
/*  15 */   private final Random rand = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private final Map nameToSoundPoolEntriesMapping = Maps.newHashMap();
/*     */   
/*     */   private final ResourceManager soundResourceManager;
/*     */   private final String soundType;
/*     */   private final boolean isGetRandomSound;
/*     */   
/*     */   public SoundPool(ResourceManager par1ResourceManager, String par2Str, boolean par3) {
/*  27 */     this.soundResourceManager = par1ResourceManager;
/*  28 */     this.soundType = par2Str;
/*  29 */     this.isGetRandomSound = par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSound(String par1Str) {
/*     */     try {
/*  39 */       String var2 = par1Str;
/*  40 */       par1Str = par1Str.substring(0, par1Str.indexOf("."));
/*     */       
/*  42 */       if (this.isGetRandomSound)
/*     */       {
/*  44 */         while (Character.isDigit(par1Str.charAt(par1Str.length() - 1)))
/*     */         {
/*  46 */           par1Str = par1Str.substring(0, par1Str.length() - 1);
/*     */         }
/*     */       }
/*     */       
/*  50 */       par1Str = par1Str.replaceAll("/", ".");
/*  51 */       Object var3 = this.nameToSoundPoolEntriesMapping.get(par1Str);
/*     */       
/*  53 */       if (var3 == null) {
/*     */         
/*  55 */         var3 = Lists.newArrayList();
/*  56 */         this.nameToSoundPoolEntriesMapping.put(par1Str, var3);
/*     */       } 
/*     */       
/*  59 */       ((List<SoundPoolEntry>)var3).add(new SoundPoolEntry(var2, func_110654_c(var2)));
/*     */     }
/*  61 */     catch (MalformedURLException var4) {
/*     */       
/*  63 */       var4.printStackTrace();
/*  64 */       throw new RuntimeException(var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private URL func_110654_c(String par1Str) throws MalformedURLException {
/*  73 */     ResourceLocation var2 = new ResourceLocation(par1Str, false);
/*  74 */     String var3 = String.format("%s:%s:%s/%s", new Object[] { "mcsounddomain", var2.getResourceDomain(), this.soundType, var2.getResourcePath() });
/*  75 */     SoundPoolProtocolHandler var4 = new SoundPoolProtocolHandler(this);
/*  76 */     return new URL((URL)null, var3, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundPoolEntry getRandomSoundFromSoundPool(String par1Str) {
/*  84 */     List<SoundPoolEntry> var2 = (List)this.nameToSoundPoolEntriesMapping.get(par1Str);
/*  85 */     return (var2 == null) ? null : var2.get(this.rand.nextInt(var2.size()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundPoolEntry getRandomSound() {
/*  93 */     if (this.nameToSoundPoolEntriesMapping.isEmpty())
/*     */     {
/*  95 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  99 */     ArrayList<String> var1 = Lists.newArrayList(this.nameToSoundPoolEntriesMapping.keySet());
/* 100 */     return getRandomSoundFromSoundPool(var1.get(this.rand.nextInt(var1.size())));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static ResourceManager func_110655_a(SoundPool par0SoundPool) {
/* 106 */     return par0SoundPool.soundResourceManager;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SoundPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */