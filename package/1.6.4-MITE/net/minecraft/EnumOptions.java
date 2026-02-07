/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumOptions
/*    */ {
/* 21 */   MUSIC("options.music", true, false),
/* 22 */   SOUND("options.sound", true, false),
/* 23 */   INVERT_MOUSE("options.invertMouse", false, true),
/* 24 */   SENSITIVITY("options.sensitivity", true, false),
/* 25 */   FOV("options.fov", true, false),
/* 26 */   GAMMA("options.gamma", true, false),
/* 27 */   RENDER_DISTANCE("options.renderDistance", false, false),
/* 28 */   VIEW_BOBBING("options.viewBobbing", false, true),
/* 29 */   ANAGLYPH("options.anaglyph", false, true),
/* 30 */   ADVANCED_OPENGL("options.advancedOpengl", false, true),
/* 31 */   FRAMERATE_LIMIT("options.framerateLimit", false, false),
/* 32 */   DIFFICULTY("options.difficulty", false, false),
/* 33 */   GRAPHICS("options.graphics", false, false),
/* 34 */   AMBIENT_OCCLUSION("options.ao", false, false),
/* 35 */   GUI_SCALE("options.guiScale", false, false),
/* 36 */   RENDER_CLOUDS("options.renderClouds", false, true),
/* 37 */   PARTICLES("options.particles", false, false),
/* 38 */   CHAT_VISIBILITY("options.chat.visibility", false, false),
/* 39 */   CHAT_COLOR("options.chat.color", false, true),
/* 40 */   CHAT_LINKS("options.chat.links", false, true),
/* 41 */   CHAT_OPACITY("options.chat.opacity", true, false),
/* 42 */   CHAT_LINKS_PROMPT("options.chat.links.prompt", false, true),
/* 43 */   USE_SERVER_TEXTURES("options.serverTextures", false, true),
/* 44 */   SNOOPER_ENABLED("options.snooper", false, true),
/* 45 */   USE_FULLSCREEN("options.fullscreen", false, true),
/* 46 */   ENABLE_VSYNC("options.vsync", false, true),
/* 47 */   SHOW_CAPE("options.showCape", false, true),
/* 48 */   TOUCHSCREEN("options.touchscreen", false, true),
/* 49 */   CHAT_SCALE("options.chat.scale", true, false),
/* 50 */   CHAT_WIDTH("options.chat.width", true, false),
/* 51 */   CHAT_HEIGHT_FOCUSED("options.chat.height.focused", true, false),
/* 52 */   CHAT_HEIGHT_UNFOCUSED("options.chat.height.unfocused", true, false);
/*    */   
/*    */   private final boolean enumFloat;
/*    */   
/*    */   private final boolean enumBoolean;
/*    */   private final String enumString;
/*    */   
/*    */   public static EnumOptions getEnumOptions(int i) {
/* 60 */     for (EnumOptions enumOptions : values()) {
/* 61 */       if (enumOptions.returnEnumOrdinal() == i) {
/* 62 */         return enumOptions;
/*    */       }
/*    */     } 
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   EnumOptions(String string2, boolean bl, boolean bl2) {
/* 69 */     this.enumString = string2;
/* 70 */     this.enumFloat = bl;
/* 71 */     this.enumBoolean = bl2;
/*    */   }
/*    */   
/*    */   public boolean getEnumFloat() {
/* 75 */     return this.enumFloat;
/*    */   }
/*    */   
/*    */   public boolean getEnumBoolean() {
/* 79 */     return this.enumBoolean;
/*    */   }
/*    */   
/*    */   public int returnEnumOrdinal() {
/* 83 */     return ordinal();
/*    */   }
/*    */   
/*    */   public String getEnumString() {
/* 87 */     return this.enumString;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */