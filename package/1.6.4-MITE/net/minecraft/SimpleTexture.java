/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SimpleTexture
/*     */   extends AbstractTexture
/*     */ {
/*     */   private final ResourceLocation textureLocation;
/*     */   
/*     */   public SimpleTexture(ResourceLocation par1ResourceLocation) {
/*  21 */     this.textureLocation = par1ResourceLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   private void rB(byte[] bytes) {
/*  26 */     for (int i = 0; i < bytes.length / 2; i++) {
/*     */       
/*  28 */       byte temp = bytes[i];
/*  29 */       bytes[i] = bytes[bytes.length - 1 - i];
/*  30 */       bytes[bytes.length - 1 - i] = temp;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadTexture(ResourceManager par1ResourceManager) throws IOException {
/*  36 */     InputStream var2 = null;
/*     */ 
/*     */     
/*     */     try {
/*  40 */       Resource var3 = par1ResourceManager.getResource(this.textureLocation);
/*  41 */       var2 = var3.getInputStream();
/*     */       
/*  43 */       if (this.textureLocation.generate_encoded_file && !Minecraft.inDevMode()) {
/*     */         
/*  45 */         this.textureLocation.generate_encoded_file = false;
/*  46 */         Minecraft.setErrorMessage("SimpleTexture: Error for " + this.textureLocation.getResourcePath());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  51 */       if (this.textureLocation.getResourcePath().endsWith(".enc")) {
/*     */         
/*  53 */         byte[] bytes = IOUtils.toByteArray(var2);
/*     */         
/*  55 */         rB(bytes);
/*     */ 
/*     */         
/*  58 */         bytes[1] = 80;
/*  59 */         bytes[2] = 78;
/*  60 */         bytes[3] = 71;
/*     */         
/*  62 */         var2 = new ByteArrayInputStream(bytes);
/*     */       }
/*  64 */       else if (this.textureLocation.generate_encoded_file) {
/*     */         
/*  66 */         byte[] bytes = IOUtils.toByteArray(var2);
/*     */         
/*  68 */         byte[] copy_of_bytes = new byte[bytes.length];
/*     */         
/*  70 */         for (int i = 0; i < bytes.length; i++) {
/*  71 */           copy_of_bytes[i] = bytes[i];
/*     */         }
/*     */         
/*  74 */         copy_of_bytes[1] = 0;
/*  75 */         copy_of_bytes[2] = 0;
/*  76 */         copy_of_bytes[3] = 0;
/*     */         
/*  78 */         rB(copy_of_bytes);
/*     */         
/*  80 */         String resource_path = this.textureLocation.getResourcePath();
/*     */         
/*  82 */         if (resource_path.endsWith(".png")) {
/*  83 */           resource_path = resource_path.substring(0, resource_path.length() - 4);
/*     */         }
/*  85 */         String output_path = "resourcepacks/MITE Resource Pack 1.6.4/assets/minecraft/" + resource_path + ".enc";
/*     */         
/*  87 */         System.out.print("Attempting to create encoded file (" + output_path + ")...");
/*     */ 
/*     */         
/*     */         try {
/*  91 */           FileOutputStream fos = new FileOutputStream(new File(output_path));
/*     */           
/*  93 */           fos.write(copy_of_bytes, 0, copy_of_bytes.length);
/*     */           
/*  95 */           fos.flush();
/*  96 */           fos.close();
/*     */           
/*  98 */           System.out.println("succeeded");
/*     */         }
/* 100 */         catch (Exception e) {
/*     */           
/* 102 */           System.out.println("failed");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         var2 = new ByteArrayInputStream(bytes);
/*     */       } 
/*     */       
/* 115 */       BufferedImage var4 = ImageIO.read(var2);
/* 116 */       boolean var5 = false;
/* 117 */       boolean var6 = false;
/*     */       
/* 119 */       if (var3.hasMetadata()) {
/*     */         
/*     */         try {
/*     */           
/* 123 */           TextureMetadataSection var7 = (TextureMetadataSection)var3.getMetadata("texture");
/*     */           
/* 125 */           if (var7 != null)
/*     */           {
/* 127 */             var5 = var7.getTextureBlur();
/* 128 */             var6 = var7.getTextureClamp();
/*     */           }
/*     */         
/* 131 */         } catch (RuntimeException var11) {
/*     */           
/* 133 */           Minecraft.getMinecraft().getLogAgent().logWarningException("Failed reading metadata of: " + this.textureLocation, var11);
/*     */         } 
/*     */       }
/*     */       
/* 137 */       TextureUtil.uploadTextureImageAllocate(getGlTextureId(), var4, var5, var6);
/*     */     }
/*     */     finally {
/*     */       
/* 141 */       if (var2 != null)
/*     */       {
/* 143 */         var2.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SimpleTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */