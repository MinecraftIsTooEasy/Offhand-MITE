/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SimpleReloadableResourceManager
/*     */   implements ReloadableResourceManager
/*     */ {
/*  18 */   private static final Joiner joinerResourcePacks = Joiner.on(", ");
/*  19 */   private final Map domainResourceManagers = Maps.newHashMap();
/*  20 */   private final List reloadListeners = Lists.newArrayList();
/*  21 */   private final Set setResourceDomains = Sets.newLinkedHashSet();
/*     */   
/*     */   private final MetadataSerializer rmMetadataSerializer;
/*     */   
/*     */   public SimpleReloadableResourceManager(MetadataSerializer par1MetadataSerializer) {
/*  26 */     this.rmMetadataSerializer = par1MetadataSerializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reloadResourcePack(ResourcePack par1ResourcePack) {
/*  33 */     for (Iterator<String> var2 = par1ResourcePack.getResourceDomains().iterator(); var2.hasNext(); var4.addResourcePack(par1ResourcePack)) {
/*     */       
/*  35 */       String var3 = var2.next();
/*  36 */       this.setResourceDomains.add(var3);
/*  37 */       FallbackResourceManager var4 = (FallbackResourceManager)this.domainResourceManagers.get(var3);
/*     */       
/*  39 */       if (var4 == null) {
/*     */         
/*  41 */         var4 = new FallbackResourceManager(this.rmMetadataSerializer);
/*  42 */         this.domainResourceManagers.put(var3, var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set getResourceDomains() {
/*  49 */     return this.setResourceDomains;
/*     */   }
/*     */ 
/*     */   
/*     */   public Resource getResource(ResourceLocation par1ResourceLocation) throws IOException {
/*  54 */     ResourceManager var2 = (ResourceManager)this.domainResourceManagers.get(par1ResourceLocation.getResourceDomain());
/*     */     
/*  56 */     if (var2 != null)
/*     */     {
/*  58 */       return var2.getResource(par1ResourceLocation);
/*     */     }
/*     */ 
/*     */     
/*  62 */     throw new FileNotFoundException(par1ResourceLocation.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllResources(ResourceLocation par1ResourceLocation) throws IOException {
/*  68 */     ResourceManager var2 = (ResourceManager)this.domainResourceManagers.get(par1ResourceLocation.getResourceDomain());
/*     */     
/*  70 */     if (var2 != null)
/*     */     {
/*  72 */       return var2.getAllResources(par1ResourceLocation);
/*     */     }
/*     */ 
/*     */     
/*  76 */     throw new FileNotFoundException(par1ResourceLocation.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearResources() {
/*  82 */     this.domainResourceManagers.clear();
/*  83 */     this.setResourceDomains.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadResources(List<DefaultResourcePack> par1List) {
/*  88 */     par1List.clear();
/*     */     
/*  90 */     par1List.add(Minecraft.theMinecraft.mcDefaultResourcePack);
/*     */     
/*  92 */     if (Minecraft.MITE_resource_pack != null) {
/*  93 */       par1List.add(Minecraft.MITE_resource_pack);
/*     */     }
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
/* 109 */     clearResources();
/* 110 */     Minecraft.getMinecraft().getLogAgent().logInfo("Reloading ResourceManager: " + joinerResourcePacks.join(Iterables.transform(par1List, new SimpleReloadableResourceManagerINNER1(this))));
/* 111 */     Iterator<DefaultResourcePack> var2 = par1List.iterator();
/*     */     
/* 113 */     while (var2.hasNext()) {
/*     */       
/* 115 */       ResourcePack var3 = var2.next();
/* 116 */       reloadResourcePack(var3);
/*     */     } 
/*     */     
/* 119 */     notifyReloadListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerReloadListener(ResourceManagerReloadListener par1ResourceManagerReloadListener) {
/* 124 */     this.reloadListeners.add(par1ResourceManagerReloadListener);
/* 125 */     par1ResourceManagerReloadListener.onResourceManagerReload(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void notifyReloadListeners() {
/* 130 */     Iterator<ResourceManagerReloadListener> var1 = this.reloadListeners.iterator();
/*     */     
/* 132 */     while (var1.hasNext()) {
/*     */       
/* 134 */       ResourceManagerReloadListener var2 = var1.next();
/* 135 */       var2.onResourceManagerReload(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SimpleReloadableResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */