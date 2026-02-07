/*     */ package net.minecraft;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class ResourcePackRepository
/*     */ {
/*  23 */   protected static final FileFilter resourcePackFilter = new ResourcePackRepositoryFilter();
/*     */ 
/*     */ 
/*     */   
/*     */   private final File dirResourcepacks;
/*     */ 
/*     */ 
/*     */   
/*     */   public final ResourcePack rprDefaultResourcePack;
/*     */ 
/*     */ 
/*     */   
/*     */   public final MetadataSerializer rprMetadataSerializer;
/*     */ 
/*     */ 
/*     */   
/*  39 */   private List repositoryEntriesAll = Lists.newArrayList();
/*  40 */   private List repositoryEntries = Lists.newArrayList();
/*     */   
/*     */   public ResourcePackRepository(File file, ResourcePack resourcePack, MetadataSerializer metadataSerializer, GameSettings gameSettings) {
/*  43 */     this.dirResourcepacks = file;
/*  44 */     this.rprDefaultResourcePack = resourcePack;
/*  45 */     this.rprMetadataSerializer = metadataSerializer;
/*     */     
/*  47 */     fixDirResourcepacks();
/*     */     
/*  49 */     updateRepositoryEntriesAll();
/*     */     
/*  51 */     for (ResourcePackRepositoryEntry resourcePackRepositoryEntry : this.repositoryEntriesAll) {
/*  52 */       if (resourcePackRepositoryEntry.getResourcePackName().equals(gameSettings.skin)) {
/*  53 */         this.repositoryEntries.add(resourcePackRepositoryEntry);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fixDirResourcepacks() {
/*  59 */     if (!this.dirResourcepacks.isDirectory()) {
/*  60 */       this.dirResourcepacks.delete();
/*  61 */       this.dirResourcepacks.mkdirs();
/*     */     } 
/*     */   }
/*     */   
/*     */   private List getResourcePackFiles() {
/*  66 */     if (this.dirResourcepacks.isDirectory()) {
/*  67 */       return Arrays.asList(this.dirResourcepacks.listFiles(resourcePackFilter));
/*     */     }
/*     */     
/*  70 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public void updateRepositoryEntriesAll() {
/*  74 */     ArrayList<ResourcePackRepositoryEntry> arrayList = Lists.newArrayList();
/*     */     
/*  76 */     for (File file : getResourcePackFiles()) {
/*  77 */       ResourcePackRepositoryEntry resourcePackRepositoryEntry = new ResourcePackRepositoryEntry(this, file, null);
/*     */       
/*  79 */       if (!this.repositoryEntriesAll.contains(resourcePackRepositoryEntry)) {
/*     */         try {
/*  81 */           resourcePackRepositoryEntry.updateResourcePack();
/*  82 */           arrayList.add(resourcePackRepositoryEntry);
/*  83 */         } catch (Exception exception) {
/*     */           
/*  85 */           arrayList.remove(resourcePackRepositoryEntry);
/*     */         }  continue;
/*     */       } 
/*  88 */       arrayList.add(this.repositoryEntriesAll.get(this.repositoryEntriesAll.indexOf(resourcePackRepositoryEntry)));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this.repositoryEntriesAll.removeAll(arrayList);
/*  94 */     for (ResourcePackRepositoryEntry resourcePackRepositoryEntry : this.repositoryEntriesAll) {
/*  95 */       resourcePackRepositoryEntry.closeResourcePack();
/*     */     }
/*     */     
/*  98 */     this.repositoryEntriesAll = arrayList;
/*     */   }
/*     */   
/*     */   public List getRepositoryEntriesAll() {
/* 102 */     return (List)ImmutableList.copyOf(this.repositoryEntriesAll);
/*     */   }
/*     */   
/*     */   public List getRepositoryEntries() {
/* 106 */     return (List)ImmutableList.copyOf(this.repositoryEntries);
/*     */   }
/*     */   
/*     */   public String getResourcePackName() {
/* 110 */     if (this.repositoryEntries.isEmpty()) {
/* 111 */       return "Default";
/*     */     }
/*     */     
/* 114 */     return ((ResourcePackRepositoryEntry)this.repositoryEntries.get(0)).getResourcePackName();
/*     */   }
/*     */   
/*     */   public void setRepositoryEntries(ResourcePackRepositoryEntry... resourcePackRepositoryEntrys) {
/* 118 */     this.repositoryEntries.clear();
/* 119 */     Collections.addAll(this.repositoryEntries, resourcePackRepositoryEntrys);
/*     */   }
/*     */   
/*     */   public File getDirResourcepacks() {
/* 123 */     return this.dirResourcepacks;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourcePackRepository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */