/*    */ package net.minecraft;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class FallbackResourceManager
/*    */   implements ResourceManager {
/* 14 */   protected final List resourcePacks = new ArrayList();
/*    */   
/*    */   private final MetadataSerializer frmMetadataSerializer;
/*    */   
/*    */   public FallbackResourceManager(MetadataSerializer par1MetadataSerializer) {
/* 19 */     this.frmMetadataSerializer = par1MetadataSerializer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addResourcePack(ResourcePack par1ResourcePack) {
/* 24 */     this.resourcePacks.add(par1ResourcePack);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getResourceDomains() {
/* 29 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Resource getResource(ResourceLocation par1ResourceLocation) throws IOException {
/* 34 */     ResourcePack var2 = null;
/* 35 */     ResourceLocation var3 = getLocationMcmeta(par1ResourceLocation);
/*    */     
/* 37 */     for (int var4 = this.resourcePacks.size() - 1; var4 >= 0; var4--) {
/*    */       
/* 39 */       ResourcePack var5 = this.resourcePacks.get(var4);
/*    */       
/* 41 */       if (var2 == null && var5.resourceExists(var3))
/*    */       {
/* 43 */         var2 = var5;
/*    */       }
/*    */       
/* 46 */       if (var5.resourceExists(par1ResourceLocation)) {
/*    */         
/* 48 */         InputStream var6 = null;
/*    */         
/* 50 */         if (var2 != null)
/*    */         {
/* 52 */           var6 = var2.getInputStream(var3);
/*    */         }
/*    */         
/* 55 */         return new SimpleResource(par1ResourceLocation, var5.getInputStream(par1ResourceLocation), var6, this.frmMetadataSerializer);
/*    */       } 
/*    */     } 
/*    */     
/* 59 */     throw new FileNotFoundException(par1ResourceLocation.toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public List getAllResources(ResourceLocation par1ResourceLocation) throws IOException {
/* 64 */     ArrayList<SimpleResource> var2 = Lists.newArrayList();
/* 65 */     ResourceLocation var3 = getLocationMcmeta(par1ResourceLocation);
/* 66 */     Iterator<ResourcePack> var4 = this.resourcePacks.iterator();
/*    */     
/* 68 */     while (var4.hasNext()) {
/*    */       
/* 70 */       ResourcePack var5 = var4.next();
/*    */       
/* 72 */       if (var5.resourceExists(par1ResourceLocation)) {
/*    */         
/* 74 */         InputStream var6 = var5.resourceExists(var3) ? var5.getInputStream(var3) : null;
/* 75 */         var2.add(new SimpleResource(par1ResourceLocation, var5.getInputStream(par1ResourceLocation), var6, this.frmMetadataSerializer));
/*    */       } 
/*    */     } 
/*    */     
/* 79 */     if (var2.isEmpty())
/*    */     {
/* 81 */       throw new FileNotFoundException(par1ResourceLocation.toString());
/*    */     }
/*    */ 
/*    */     
/* 85 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static ResourceLocation getLocationMcmeta(ResourceLocation par0ResourceLocation) {
/* 92 */     return new ResourceLocation(par0ResourceLocation.getResourceDomain(), par0ResourceLocation.getResourcePath() + ".mcmeta", false);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FallbackResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */