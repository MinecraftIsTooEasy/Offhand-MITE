/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumSpecialSplash
/*     */ {
/*   8 */   ronin_pawn("RoninPawn!", "textures/gui/title/ronin_pawn.png", "Check out the RoninPawn's MITE series on YouTube!", 70, "textures/gui/title/rp_videos.png", 1500, 720, 0.15F, "https://www.youtube.com/watch?v=UaSVsuklHjA"),
/*   9 */   mite_migos("MiTE 'Migos!", "textures/gui/title/mite_migos.png", "Visit the MiTE-Migos official forum thread and public fan server!", 70, "textures/gui/title/mite_migos.png", 1123, 256, 0.25F, "http://www.minecraftforum.net/forums/servers/pc-servers/survival-servers/2383945-mite-migos-fan-server-minecraft-is-too-easy"),
/*  10 */   guten_tag("Guten Tag!"),
/*  11 */   elite_dangerous("Elite: Dangerous!"),
/*  12 */   ice_cream("That ice cream though!"),
/*  13 */   cogmind("Also try Cogmind!", null, "Plot Twist: You are the Cogmind!", 70, "textures/gui/title/cogmind.png", 666, 234, 0.3F, "http://www.gridsagegames.com/cogmind/"),
/*  14 */   ludwig("Ludwig DeLarge!", null, "The Icy Desert Journey! This is how the way to MITE!", 76, "textures/gui/title/ludwig.png", 1280, 610, 0.18F, "http://imgur.com/a/YAzpR");
/*     */   
/*     */   private String splash_text;
/*     */   private ResourceLocation splash_texture;
/*     */   private String message_text;
/*     */   private ResourceLocation link_page_texture;
/*     */   private int width;
/*     */   private int height;
/*     */   private float scale;
/*     */   private int message_height;
/*     */   private String url;
/*     */   
/*     */   EnumSpecialSplash(String splash_text, String splash_texture, String message_text, int message_height, String link_page_texture, int width, int height, float scale, String url) {
/*  27 */     this.splash_text = splash_text;
/*  28 */     this.splash_texture = (splash_texture == null) ? null : new ResourceLocation(splash_texture);
/*  29 */     this.message_text = message_text;
/*  30 */     this.message_height = message_height;
/*  31 */     this.link_page_texture = (link_page_texture == null) ? null : new ResourceLocation(link_page_texture);
/*  32 */     this.width = width;
/*  33 */     this.height = height;
/*  34 */     this.scale = scale;
/*  35 */     this.url = url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static EnumSpecialSplash getSpecialSplash(String splash_text) {
/*  45 */     for (int i = 0; i < (values()).length; i++) {
/*     */       
/*  47 */       if (splash_text.equals((values()[i]).splash_text)) {
/*  48 */         return values()[i];
/*     */       }
/*     */     } 
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   ResourceLocation getSplashTexture() {
/*  56 */     return this.splash_texture;
/*     */   }
/*     */ 
/*     */   
/*     */   String getMessageText() {
/*  61 */     return this.message_text;
/*     */   }
/*     */ 
/*     */   
/*     */   int getMessageHeight() {
/*  66 */     return this.message_height;
/*     */   }
/*     */ 
/*     */   
/*     */   ResourceLocation getLinkPageTexture() {
/*  71 */     return this.link_page_texture;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean hasLinkPageTexture() {
/*  76 */     return (getLinkPageTexture() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   int getWidth() {
/*  81 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   int getHeight() {
/*  86 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   float getScale() {
/*  91 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   String getURL() {
/*  96 */     return this.url;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean hasURL() {
/* 101 */     return (getURL() != null);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumSpecialSplash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */