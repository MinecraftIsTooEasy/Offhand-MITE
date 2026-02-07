/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ResourceLocation
/*     */ {
/*     */   private final String resourceDomain;
/*     */   private final String resourcePath;
/*  14 */   private static List resources_to_verify = new ArrayList();
/*     */   
/*     */   public boolean generate_encoded_file;
/*     */ 
/*     */   
/*     */   public ResourceLocation(String par1Str, String par2Str) {
/*  20 */     this(par1Str, par2Str, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation(String par1Str, String par2Str, boolean verify) {
/*  26 */     Validate.notNull(par2Str);
/*     */     
/*  28 */     if (par1Str != null && par1Str.length() != 0) {
/*     */       
/*  30 */       this.resourceDomain = par1Str;
/*     */     }
/*     */     else {
/*     */       
/*  34 */       this.resourceDomain = "minecraft";
/*     */     } 
/*     */     
/*  37 */     this.resourcePath = par2Str;
/*     */     
/*  39 */     if (verify) {
/*  40 */       setVerificationPending();
/*     */     }
/*     */   }
/*     */   
/*     */   public ResourceLocation(String par1Str) {
/*  45 */     this(par1Str, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation(String par1Str, boolean verify) {
/*  51 */     String var2 = "minecraft";
/*  52 */     String var3 = par1Str;
/*  53 */     int var4 = par1Str.indexOf(':');
/*     */     
/*  55 */     if (var4 >= 0) {
/*     */       
/*  57 */       var3 = par1Str.substring(var4 + 1, par1Str.length());
/*     */       
/*  59 */       if (var4 > 1)
/*     */       {
/*  61 */         var2 = par1Str.substring(0, var4);
/*     */       }
/*     */     } 
/*     */     
/*  65 */     this.resourceDomain = var2.toLowerCase();
/*  66 */     this.resourcePath = var3;
/*     */     
/*  68 */     if (verify) {
/*  69 */       setVerificationPending();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setVerificationPending() {
/*  74 */     if (!getResourcePath().endsWith(".mcmeta")) {
/*  75 */       resources_to_verify.add(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void verifyExistence() {
/*  81 */     if (!exists()) {
/*  82 */       Minecraft.setErrorMessage("Resource not found: " + getResourcePath());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean exists() {
/*  87 */     if (Minecraft.theMinecraft == null) {
/*     */       
/*  89 */       Minecraft.setErrorMessage("ResourceLocation.exists: theMinecraft==null, checking too early");
/*  90 */       return false;
/*     */     } 
/*     */     
/*  93 */     if (Minecraft.theMinecraft.mcDefaultResourcePack == null) {
/*     */       
/*  95 */       Minecraft.setErrorMessage("ResourceLocation.exists: mcDefaultResourcePack==null, checking too early");
/*  96 */       return false;
/*     */     } 
/*     */     
/*  99 */     if (Minecraft.theMinecraft.mcDefaultResourcePack.resourceExists(this)) {
/* 100 */       return true;
/*     */     }
/* 102 */     return (Minecraft.MITE_resource_pack != null && Minecraft.MITE_resource_pack.resourceExists(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void verifyResourceLocations() {
/* 111 */     int num_resources = resources_to_verify.size();
/*     */     
/* 113 */     for (int i = 0; i < num_resources; i++) {
/*     */       
/* 115 */       ResourceLocation resource = resources_to_verify.get(i);
/* 116 */       resource.verifyExistence();
/*     */     } 
/*     */     
/* 119 */     resources_to_verify.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourcePath() {
/* 124 */     return this.resourcePath;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getResourceDomain() {
/* 129 */     return this.resourceDomain;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return this.resourceDomain + ":" + this.resourcePath;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object par1Obj) {
/* 139 */     if (this == par1Obj)
/*     */     {
/* 141 */       return true;
/*     */     }
/* 143 */     if (!(par1Obj instanceof ResourceLocation))
/*     */     {
/* 145 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 149 */     ResourceLocation var2 = (ResourceLocation)par1Obj;
/* 150 */     return (this.resourceDomain.equals(var2.resourceDomain) && this.resourcePath.equals(var2.resourcePath));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 156 */     return 31 * this.resourceDomain.hashCode() + this.resourcePath.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation setGenerateEncodedFile(boolean generate_encoded_file) {
/* 161 */     this.generate_encoded_file = generate_encoded_file;
/* 162 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ResourceLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */