/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.io.IOUtils;
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
/*     */ public class ResourcePackRepositoryEntry
/*     */ {
/*     */   private final File resourcePackFile;
/*     */   private ResourcePack reResourcePack;
/*     */   private PackMetadataSection rePackMetadataSection;
/*     */   private BufferedImage texturePackIcon;
/*     */   private ResourceLocation locationTexturePackIcon;
/*     */   
/*     */   private ResourcePackRepositoryEntry(ResourcePackRepository resourcePackRepository, File file) {
/* 136 */     this.resourcePackFile = file;
/*     */   }
/*     */   
/*     */   public void updateResourcePack() {
/* 140 */     this.reResourcePack = this.resourcePackFile.isDirectory() ? new FolderResourcePack(this.resourcePackFile) : new FileResourcePack(this.resourcePackFile);
/*     */     
/* 142 */     this.rePackMetadataSection = (PackMetadataSection)this.reResourcePack.getPackMetadata(this.reResourcePackRepository.rprMetadataSerializer, "pack");
/*     */     try {
/* 144 */       this.texturePackIcon = this.reResourcePack.getPackImage();
/* 145 */     } catch (IOException iOException) {}
/*     */     
/* 147 */     if (this.texturePackIcon == null) {
/* 148 */       this.texturePackIcon = this.reResourcePackRepository.rprDefaultResourcePack.getPackImage();
/*     */     }
/*     */     
/* 151 */     closeResourcePack();
/*     */   }
/*     */   
/*     */   public void bindTexturePackIcon(TextureManager textureManager) {
/* 155 */     if (this.locationTexturePackIcon == null) {
/* 156 */       this.locationTexturePackIcon = textureManager.getDynamicTextureLocation("texturepackicon", new DynamicTexture(this.texturePackIcon));
/*     */     }
/* 158 */     textureManager.bindTexture(this.locationTexturePackIcon);
/*     */   }
/*     */   
/*     */   public void closeResourcePack() {
/* 162 */     if (this.reResourcePack instanceof Closeable) {
/* 163 */       IOUtils.closeQuietly((Closeable)this.reResourcePack);
/*     */     }
/*     */   }
/*     */   
/*     */   public ResourcePack getResourcePack() {
/* 168 */     return this.reResourcePack;
/*     */   }
/*     */   
/*     */   public String getResourcePackName() {
/* 172 */     return this.reResourcePack.getPackName();
/*     */   }
/*     */   
/*     */   public String getTexturePackDescription() {
/* 176 */     return (this.rePackMetadataSection == null) ? (EnumChatFormatting.RED + "Invalid pack.mcmeta (or missing 'pack' section)") : this.rePackMetadataSection.getPackDescription();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 185 */     if (this == object) return true;
/*     */     
/* 187 */     if (object instanceof ResourcePackRepositoryEntry) {
/* 188 */       return toString().equals(object.toString());
/*     */     }
/*     */     
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 196 */     return toString().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 201 */     return String.format("%s:%s:%d", new Object[] { this.resourcePackFile.getName(), this.resourcePackFile.isDirectory() ? "folder" : "zip", Long.valueOf(this.resourcePackFile.lastModified()) });
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourcePackRepositoryEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */