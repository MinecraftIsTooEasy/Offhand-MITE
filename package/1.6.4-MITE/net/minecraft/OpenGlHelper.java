/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.ARBMultitexture;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.lwjgl.opengl.GLContext;
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
/*     */ public class OpenGlHelper
/*     */ {
/*     */   public static int defaultTexUnit;
/*     */   public static int lightmapTexUnit;
/*     */   private static boolean useMultitextureARB;
/*     */   private static int current_lightmap_texture_coords_param_1;
/*     */   private static float current_lightmap_texture_coords_param_2;
/*     */   private static float current_lightmap_texture_coords_param_3;
/*     */   private static int previous_lightmap_texture_coords_param_1;
/*     */   private static float previous_lightmap_texture_coords_param_2;
/*     */   private static float previous_lightmap_texture_coords_param_3;
/*     */   
/*     */   public static void initializeTextures() {
/*  43 */     useMultitextureARB = ((GLContext.getCapabilities()).GL_ARB_multitexture && !(GLContext.getCapabilities()).OpenGL13);
/*     */     
/*  45 */     if (useMultitextureARB) {
/*     */       
/*  47 */       defaultTexUnit = 33984;
/*  48 */       lightmapTexUnit = 33985;
/*     */     }
/*     */     else {
/*     */       
/*  52 */       defaultTexUnit = 33984;
/*  53 */       lightmapTexUnit = 33985;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setActiveTexture(int par0) {
/*  65 */     if (useMultitextureARB) {
/*     */       
/*  67 */       ARBMultitexture.glActiveTextureARB(par0);
/*     */     }
/*     */     else {
/*     */       
/*  71 */       GL13.glActiveTexture(par0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setClientActiveTexture(int par0) {
/*  80 */     if (useMultitextureARB) {
/*     */       
/*  82 */       ARBMultitexture.glClientActiveTextureARB(par0);
/*     */     }
/*     */     else {
/*     */       
/*  86 */       GL13.glClientActiveTexture(par0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setLightmapTextureCoords(int par0, float par1, float par2) {
/*  95 */     if (useMultitextureARB) {
/*     */       
/*  97 */       ARBMultitexture.glMultiTexCoord2fARB(par0, par1, par2);
/*     */     }
/*     */     else {
/*     */       
/* 101 */       GL13.glMultiTexCoord2f(par0, par1, par2);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     previous_lightmap_texture_coords_param_1 = current_lightmap_texture_coords_param_1;
/* 107 */     previous_lightmap_texture_coords_param_2 = current_lightmap_texture_coords_param_2;
/* 108 */     previous_lightmap_texture_coords_param_3 = current_lightmap_texture_coords_param_3;
/*     */     
/* 110 */     current_lightmap_texture_coords_param_1 = par0;
/* 111 */     current_lightmap_texture_coords_param_2 = par1;
/* 112 */     current_lightmap_texture_coords_param_3 = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void restorePreviousLightmapTextureCoords() {
/* 119 */     setLightmapTextureCoords(previous_lightmap_texture_coords_param_1, previous_lightmap_texture_coords_param_2, previous_lightmap_texture_coords_param_3);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\OpenGlHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */