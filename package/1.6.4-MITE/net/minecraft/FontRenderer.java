/*      */ package net.minecraft;
/*      */ 
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.text.Bidi;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import javax.imageio.ImageIO;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ public class FontRenderer
/*      */   implements ResourceManagerReloadListener {
/*   16 */   private static final ResourceLocation[] unicodePageLocations = new ResourceLocation[256];
/*      */ 
/*      */   
/*   19 */   private int[] charWidth = new int[256];
/*      */ 
/*      */   
/*   22 */   public int FONT_HEIGHT = 9;
/*   23 */   public Random fontRandom = new Random();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   28 */   private byte[] glyphWidth = new byte[65536];
/*      */ 
/*      */ 
/*      */   
/*      */   private final ResourceLocation locationFontTexture;
/*      */ 
/*      */ 
/*      */   
/*      */   private final TextureManager renderEngine;
/*      */ 
/*      */ 
/*      */   
/*      */   private float posX;
/*      */ 
/*      */ 
/*      */   
/*      */   private float posY;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean unicodeFlag;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean bidiFlag;
/*      */ 
/*      */ 
/*      */   
/*      */   private float red;
/*      */ 
/*      */ 
/*      */   
/*      */   private float blue;
/*      */ 
/*      */ 
/*      */   
/*      */   private float green;
/*      */ 
/*      */ 
/*      */   
/*      */   private float alpha;
/*      */ 
/*      */ 
/*      */   
/*      */   private int textColor;
/*      */ 
/*      */   
/*      */   private boolean randomStyle;
/*      */ 
/*      */   
/*      */   private boolean boldStyle;
/*      */ 
/*      */   
/*      */   private boolean italicStyle;
/*      */ 
/*      */   
/*      */   private boolean underlineStyle;
/*      */ 
/*      */   
/*      */   private boolean strikethroughStyle;
/*      */ 
/*      */ 
/*      */   
/*      */   public FontRenderer(GameSettings par1GameSettings, ResourceLocation par2ResourceLocation, TextureManager par3TextureManager, boolean par4) {
/*   92 */     this.locationFontTexture = par2ResourceLocation;
/*   93 */     this.renderEngine = par3TextureManager;
/*   94 */     this.unicodeFlag = par4;
/*   95 */     par3TextureManager.bindTexture(this.locationFontTexture);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  129 */     readGlyphSizes();
/*      */   }
/*      */ 
/*      */   
/*      */   public void onResourceManagerReload(ResourceManager par1ResourceManager) {
/*  134 */     readFontTexture();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readFontTexture() {
/*      */     BufferedImage var1;
/*      */     try {
/*  143 */       var1 = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(this.locationFontTexture).getInputStream());
/*      */     }
/*  145 */     catch (IOException var17) {
/*      */       
/*  147 */       throw new RuntimeException(var17);
/*      */     } 
/*      */     
/*  150 */     int var2 = var1.getWidth();
/*  151 */     int var3 = var1.getHeight();
/*  152 */     int[] var4 = new int[var2 * var3];
/*  153 */     var1.getRGB(0, 0, var2, var3, var4, 0, var2);
/*  154 */     int var5 = var3 / 16;
/*  155 */     int var6 = var2 / 16;
/*  156 */     byte var7 = 1;
/*  157 */     float var8 = 8.0F / var6;
/*  158 */     int var9 = 0;
/*      */     
/*  160 */     while (var9 < 256) {
/*      */       
/*  162 */       int var10 = var9 % 16;
/*  163 */       int var11 = var9 / 16;
/*      */       
/*  165 */       if (var9 == 32)
/*      */       {
/*  167 */         this.charWidth[var9] = 3 + var7;
/*      */       }
/*      */       
/*  170 */       int var12 = var6 - 1;
/*      */ 
/*      */ 
/*      */       
/*  174 */       while (var12 >= 0) {
/*      */         
/*  176 */         int var13 = var10 * var6 + var12;
/*  177 */         boolean var14 = true;
/*      */         
/*  179 */         for (int var15 = 0; var15 < var5 && var14; var15++) {
/*      */           
/*  181 */           int var16 = (var11 * var6 + var15) * var2;
/*      */           
/*  183 */           if ((var4[var13 + var16] >> 24 & 0xFF) != 0)
/*      */           {
/*  185 */             var14 = false;
/*      */           }
/*      */         } 
/*      */         
/*  189 */         if (var14)
/*      */         {
/*  191 */           var12--;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  196 */       var12++;
/*  197 */       this.charWidth[var9] = (int)(0.5D + (var12 * var8)) + var7;
/*  198 */       var9++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readGlyphSizes() {
/*      */     try {
/*  208 */       InputStream var1 = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/glyph_sizes.bin")).getInputStream();
/*  209 */       var1.read(this.glyphWidth);
/*      */     }
/*  211 */     catch (IOException var2) {
/*      */       
/*  213 */       throw new RuntimeException(var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderCharAtPos(int par1, char par2, boolean par3) {
/*  222 */     return (par2 == ' ') ? 4.0F : ((par1 > 0 && !this.unicodeFlag) ? renderDefaultChar(par1 + 32, par3) : renderUnicodeChar(par2, par3));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderDefaultChar(int par1, boolean par2) {
/*  230 */     float var3 = (par1 % 16 * 8);
/*  231 */     float var4 = (par1 / 16 * 8);
/*  232 */     float var5 = par2 ? 1.0F : 0.0F;
/*  233 */     this.renderEngine.bindTexture(this.locationFontTexture);
/*  234 */     float var6 = this.charWidth[par1] - 0.01F;
/*  235 */     GL11.glBegin(5);
/*  236 */     GL11.glTexCoord2f(var3 / 128.0F, var4 / 128.0F);
/*  237 */     GL11.glVertex3f(this.posX + var5, this.posY, 0.0F);
/*  238 */     GL11.glTexCoord2f(var3 / 128.0F, (var4 + 7.99F) / 128.0F);
/*  239 */     GL11.glVertex3f(this.posX - var5, this.posY + 7.99F, 0.0F);
/*  240 */     GL11.glTexCoord2f((var3 + var6 - 1.0F) / 128.0F, var4 / 128.0F);
/*  241 */     GL11.glVertex3f(this.posX + var6 - 1.0F + var5, this.posY, 0.0F);
/*  242 */     GL11.glTexCoord2f((var3 + var6 - 1.0F) / 128.0F, (var4 + 7.99F) / 128.0F);
/*  243 */     GL11.glVertex3f(this.posX + var6 - 1.0F - var5, this.posY + 7.99F, 0.0F);
/*  244 */     GL11.glEnd();
/*  245 */     return this.charWidth[par1];
/*      */   }
/*      */ 
/*      */   
/*      */   private ResourceLocation getUnicodePageLocation(int par1) {
/*  250 */     if (unicodePageLocations[par1] == null)
/*      */     {
/*  252 */       unicodePageLocations[par1] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", new Object[] { Integer.valueOf(par1) }));
/*      */     }
/*      */     
/*  255 */     return unicodePageLocations[par1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void loadGlyphTexture(int par1) {
/*  263 */     this.renderEngine.bindTexture(getUnicodePageLocation(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float renderUnicodeChar(char par1, boolean par2) {
/*  271 */     if (this.glyphWidth[par1] == 0)
/*      */     {
/*  273 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     
/*  277 */     int var3 = par1 / 256;
/*  278 */     loadGlyphTexture(var3);
/*  279 */     int var4 = this.glyphWidth[par1] >>> 4;
/*  280 */     int var5 = this.glyphWidth[par1] & 0xF;
/*  281 */     float var6 = var4;
/*  282 */     float var7 = (var5 + 1);
/*  283 */     float var8 = (par1 % 16 * 16) + var6;
/*  284 */     float var9 = ((par1 & 0xFF) / 16 * 16);
/*  285 */     float var10 = var7 - var6 - 0.02F;
/*  286 */     float var11 = par2 ? 1.0F : 0.0F;
/*  287 */     GL11.glBegin(5);
/*  288 */     GL11.glTexCoord2f(var8 / 256.0F, var9 / 256.0F);
/*  289 */     GL11.glVertex3f(this.posX + var11, this.posY, 0.0F);
/*  290 */     GL11.glTexCoord2f(var8 / 256.0F, (var9 + 15.98F) / 256.0F);
/*  291 */     GL11.glVertex3f(this.posX - var11, this.posY + 7.99F, 0.0F);
/*  292 */     GL11.glTexCoord2f((var8 + var10) / 256.0F, var9 / 256.0F);
/*  293 */     GL11.glVertex3f(this.posX + var10 / 2.0F + var11, this.posY, 0.0F);
/*  294 */     GL11.glTexCoord2f((var8 + var10) / 256.0F, (var9 + 15.98F) / 256.0F);
/*  295 */     GL11.glVertex3f(this.posX + var10 / 2.0F - var11, this.posY + 7.99F, 0.0F);
/*  296 */     GL11.glEnd();
/*  297 */     return (var7 - var6) / 2.0F + 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int drawStringWithShadow(String par1Str, int par2, int par3, int par4) {
/*  306 */     return drawString(par1Str, par2, par3, par4, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int drawString(String par1Str, int par2, int par3, int par4) {
/*  314 */     return drawString(par1Str, par2, par3, par4, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int drawString(String par1Str, int par2, int par3, int par4, boolean par5) {
/*      */     int var6;
/*  327 */     resetStyles();
/*      */     
/*  329 */     if (this.bidiFlag)
/*      */     {
/*  331 */       par1Str = bidiReorder(par1Str);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  336 */     if (par5) {
/*      */       
/*  338 */       var6 = renderString(par1Str, par2 + 1, par3 + 1, par4, true);
/*  339 */       var6 = Math.max(var6, renderString(par1Str, par2, par3, par4, false));
/*      */     }
/*      */     else {
/*      */       
/*  343 */       var6 = renderString(par1Str, par2, par3, par4, false);
/*      */     } 
/*      */     
/*  346 */     return var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String bidiReorder(String par1Str) {
/*  354 */     if (par1Str != null && Bidi.requiresBidi(par1Str.toCharArray(), 0, par1Str.length())) {
/*      */       
/*  356 */       Bidi var2 = new Bidi(par1Str, -2);
/*  357 */       byte[] var3 = new byte[var2.getRunCount()];
/*  358 */       String[] var4 = new String[var3.length];
/*      */ 
/*      */       
/*  361 */       for (int var5 = 0; var5 < var3.length; var5++) {
/*      */         
/*  363 */         int var6 = var2.getRunStart(var5);
/*  364 */         int i = var2.getRunLimit(var5);
/*  365 */         int var8 = var2.getRunLevel(var5);
/*  366 */         String var9 = par1Str.substring(var6, i);
/*  367 */         var3[var5] = (byte)var8;
/*  368 */         var4[var5] = var9;
/*      */       } 
/*      */       
/*  371 */       String[] var11 = (String[])var4.clone();
/*  372 */       Bidi.reorderVisually(var3, 0, (Object[])var4, 0, var3.length);
/*  373 */       StringBuilder var12 = new StringBuilder();
/*  374 */       int var7 = 0;
/*      */       
/*  376 */       while (var7 < var4.length) {
/*      */         
/*  378 */         byte var13 = var3[var7];
/*  379 */         int var14 = 0;
/*      */ 
/*      */ 
/*      */         
/*  383 */         while (var14 < var11.length) {
/*      */           
/*  385 */           if (!var11[var14].equals(var4[var7])) {
/*      */             
/*  387 */             var14++;
/*      */             
/*      */             continue;
/*      */           } 
/*  391 */           var13 = var3[var14];
/*      */         } 
/*      */         
/*  394 */         if ((var13 & 0x1) == 0) {
/*      */           
/*  396 */           var12.append(var4[var7]);
/*      */         }
/*      */         else {
/*      */           
/*  400 */           for (var14 = var4[var7].length() - 1; var14 >= 0; var14--) {
/*      */             
/*  402 */             char var10 = var4[var7].charAt(var14);
/*      */             
/*  404 */             if (var10 == '(') {
/*      */               
/*  406 */               var10 = ')';
/*      */             }
/*  408 */             else if (var10 == ')') {
/*      */               
/*  410 */               var10 = '(';
/*      */             } 
/*      */             
/*  413 */             var12.append(var10);
/*      */           } 
/*      */         } 
/*      */         
/*  417 */         var7++;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  422 */       return var12.toString();
/*      */     } 
/*      */ 
/*      */     
/*  426 */     return par1Str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetStyles() {
/*  435 */     this.randomStyle = false;
/*  436 */     this.boldStyle = false;
/*  437 */     this.italicStyle = false;
/*  438 */     this.underlineStyle = false;
/*  439 */     this.strikethroughStyle = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderStringAtPos(String par1Str, boolean par2) {
/*  447 */     for (int var3 = 0; var3 < par1Str.length(); var3++) {
/*      */       
/*  449 */       char var4 = par1Str.charAt(var3);
/*      */ 
/*      */ 
/*      */       
/*  453 */       if (var4 == '§' && var3 + 1 < par1Str.length()) {
/*      */ 
/*      */ 
/*      */         
/*  457 */         EnumChatFormatting enum_chat_formatting = EnumChatFormatting.getByChar(par1Str.toLowerCase().charAt(var3 + 1));
/*      */         
/*  459 */         if (enum_chat_formatting == null) {
/*  460 */           enum_chat_formatting = EnumChatFormatting.WHITE;
/*      */         }
/*      */         
/*  463 */         if (enum_chat_formatting.isColor()) {
/*      */           int var6;
/*  465 */           this.randomStyle = false;
/*  466 */           this.boldStyle = false;
/*  467 */           this.strikethroughStyle = false;
/*  468 */           this.underlineStyle = false;
/*  469 */           this.italicStyle = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  483 */           if (Minecraft.theMinecraft.gameSettings.anaglyph) {
/*  484 */             var6 = par2 ? enum_chat_formatting.rgb_anaglyph_shadow : enum_chat_formatting.rgb_anaglyph;
/*      */           } else {
/*  486 */             var6 = par2 ? enum_chat_formatting.rgb_shadow : enum_chat_formatting.rgb;
/*      */           } 
/*  488 */           this.textColor = var6;
/*  489 */           GL11.glColor4f((var6 >> 16) / 255.0F, (var6 >> 8 & 0xFF) / 255.0F, (var6 & 0xFF) / 255.0F, this.alpha);
/*      */         
/*      */         }
/*  492 */         else if (enum_chat_formatting == EnumChatFormatting.OBFUSCATED) {
/*      */           
/*  494 */           this.randomStyle = true;
/*      */         
/*      */         }
/*  497 */         else if (enum_chat_formatting == EnumChatFormatting.BOLD) {
/*      */           
/*  499 */           this.boldStyle = true;
/*      */         
/*      */         }
/*  502 */         else if (enum_chat_formatting == EnumChatFormatting.STRIKETHROUGH) {
/*      */           
/*  504 */           this.strikethroughStyle = true;
/*      */         
/*      */         }
/*  507 */         else if (enum_chat_formatting == EnumChatFormatting.UNDERLINE) {
/*      */           
/*  509 */           this.underlineStyle = true;
/*      */         
/*      */         }
/*  512 */         else if (enum_chat_formatting == EnumChatFormatting.ITALIC) {
/*      */           
/*  514 */           this.italicStyle = true;
/*      */         
/*      */         }
/*  517 */         else if (enum_chat_formatting == EnumChatFormatting.RESET) {
/*      */           
/*  519 */           this.randomStyle = false;
/*  520 */           this.boldStyle = false;
/*  521 */           this.strikethroughStyle = false;
/*  522 */           this.underlineStyle = false;
/*  523 */           this.italicStyle = false;
/*  524 */           GL11.glColor4f(this.red, this.blue, this.green, this.alpha);
/*      */         } 
/*      */         
/*  527 */         var3++;
/*      */       }
/*      */       else {
/*      */         
/*  531 */         int var5 = ChatAllowedCharacters.allowedCharacters.indexOf(var4);
/*      */         
/*  533 */         if (this.randomStyle && var5 > 0) {
/*      */           int var6;
/*      */           
/*      */           do {
/*  537 */             var6 = this.fontRandom.nextInt(ChatAllowedCharacters.allowedCharacters.length());
/*      */           }
/*  539 */           while (this.charWidth[var5 + 32] != this.charWidth[var6 + 32]);
/*      */           
/*  541 */           var5 = var6;
/*      */         } 
/*      */         
/*  544 */         float var11 = this.unicodeFlag ? 0.5F : 1.0F;
/*  545 */         boolean var7 = ((var5 <= 0 || this.unicodeFlag) && par2);
/*      */         
/*  547 */         if (var7) {
/*      */           
/*  549 */           this.posX -= var11;
/*  550 */           this.posY -= var11;
/*      */         } 
/*      */         
/*  553 */         float var8 = renderCharAtPos(var5, var4, this.italicStyle);
/*      */         
/*  555 */         if (var7) {
/*      */           
/*  557 */           this.posX += var11;
/*  558 */           this.posY += var11;
/*      */         } 
/*      */         
/*  561 */         if (this.boldStyle) {
/*      */           
/*  563 */           this.posX += var11;
/*      */           
/*  565 */           if (var7) {
/*      */             
/*  567 */             this.posX -= var11;
/*  568 */             this.posY -= var11;
/*      */           } 
/*      */           
/*  571 */           renderCharAtPos(var5, var4, this.italicStyle);
/*  572 */           this.posX -= var11;
/*      */           
/*  574 */           if (var7) {
/*      */             
/*  576 */             this.posX += var11;
/*  577 */             this.posY += var11;
/*      */           } 
/*      */           
/*  580 */           var8++;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  585 */         if (this.strikethroughStyle) {
/*      */           
/*  587 */           Tessellator var9 = Tessellator.instance;
/*  588 */           GL11.glDisable(3553);
/*  589 */           var9.startDrawingQuads();
/*  590 */           var9.addVertex(this.posX, (this.posY + (this.FONT_HEIGHT / 2)), 0.0D);
/*  591 */           var9.addVertex((this.posX + var8), (this.posY + (this.FONT_HEIGHT / 2)), 0.0D);
/*  592 */           var9.addVertex((this.posX + var8), (this.posY + (this.FONT_HEIGHT / 2) - 1.0F), 0.0D);
/*  593 */           var9.addVertex(this.posX, (this.posY + (this.FONT_HEIGHT / 2) - 1.0F), 0.0D);
/*  594 */           var9.draw();
/*  595 */           GL11.glEnable(3553);
/*      */         } 
/*      */         
/*  598 */         if (this.underlineStyle) {
/*      */           
/*  600 */           Tessellator var9 = Tessellator.instance;
/*  601 */           GL11.glDisable(3553);
/*  602 */           var9.startDrawingQuads();
/*  603 */           int var10 = this.underlineStyle ? -1 : 0;
/*  604 */           var9.addVertex((this.posX + var10), (this.posY + this.FONT_HEIGHT), 0.0D);
/*  605 */           var9.addVertex((this.posX + var8), (this.posY + this.FONT_HEIGHT), 0.0D);
/*  606 */           var9.addVertex((this.posX + var8), (this.posY + this.FONT_HEIGHT - 1.0F), 0.0D);
/*  607 */           var9.addVertex((this.posX + var10), (this.posY + this.FONT_HEIGHT - 1.0F), 0.0D);
/*  608 */           var9.draw();
/*  609 */           GL11.glEnable(3553);
/*      */         } 
/*      */         
/*  612 */         this.posX += (int)var8;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderStringAligned(String par1Str, int par2, int par3, int par4, int par5, boolean par6) {
/*  622 */     if (this.bidiFlag) {
/*      */       
/*  624 */       par1Str = bidiReorder(par1Str);
/*  625 */       int var7 = getStringWidth(par1Str);
/*  626 */       par2 = par2 + par4 - var7;
/*      */     } 
/*      */     
/*  629 */     return renderString(par1Str, par2, par3, par5, par6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int renderString(String par1Str, int par2, int par3, int par4, boolean par5) {
/*  637 */     if (par1Str == null)
/*      */     {
/*  639 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  643 */     if ((par4 & 0xFC000000) == 0)
/*      */     {
/*  645 */       par4 |= 0xFF000000;
/*      */     }
/*      */     
/*  648 */     if (par5)
/*      */     {
/*  650 */       par4 = (par4 & 0xFCFCFC) >> 2 | par4 & 0xFF000000;
/*      */     }
/*      */     
/*  653 */     this.red = (par4 >> 16 & 0xFF) / 255.0F;
/*  654 */     this.blue = (par4 >> 8 & 0xFF) / 255.0F;
/*  655 */     this.green = (par4 & 0xFF) / 255.0F;
/*  656 */     this.alpha = (par4 >> 24 & 0xFF) / 255.0F;
/*  657 */     GL11.glColor4f(this.red, this.blue, this.green, this.alpha);
/*  658 */     this.posX = par2;
/*  659 */     this.posY = par3;
/*  660 */     renderStringAtPos(par1Str, par5);
/*  661 */     return (int)this.posX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStringWidth(String par1Str) {
/*  670 */     if (par1Str == null)
/*      */     {
/*  672 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  676 */     int var2 = 0;
/*  677 */     boolean var3 = false;
/*      */     
/*  679 */     for (int var4 = 0; var4 < par1Str.length(); var4++) {
/*      */       
/*  681 */       char var5 = par1Str.charAt(var4);
/*  682 */       int var6 = getCharWidth(var5);
/*      */       
/*  684 */       if (var6 < 0 && var4 < par1Str.length() - 1) {
/*      */         
/*  686 */         var4++;
/*  687 */         var5 = par1Str.charAt(var4);
/*      */         
/*  689 */         if (var5 != 'l' && var5 != 'L') {
/*      */           
/*  691 */           if (var5 == 'r' || var5 == 'R')
/*      */           {
/*  693 */             var3 = false;
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  698 */           var3 = true;
/*      */         } 
/*      */         
/*  701 */         var6 = 0;
/*      */       } 
/*      */       
/*  704 */       var2 += var6;
/*      */       
/*  706 */       if (var3)
/*      */       {
/*  708 */         var2++;
/*      */       }
/*      */     } 
/*      */     
/*  712 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCharWidth(char par1) {
/*  721 */     if (par1 == '§')
/*      */     {
/*  723 */       return -1;
/*      */     }
/*  725 */     if (par1 == ' ')
/*      */     {
/*  727 */       return 4;
/*      */     }
/*      */ 
/*      */     
/*  731 */     int var2 = ChatAllowedCharacters.allowedCharacters.indexOf(par1);
/*      */     
/*  733 */     if (var2 >= 0 && !this.unicodeFlag)
/*      */     {
/*  735 */       return this.charWidth[var2 + 32];
/*      */     }
/*  737 */     if (this.glyphWidth[par1] != 0) {
/*      */       
/*  739 */       int var3 = this.glyphWidth[par1] >>> 4;
/*  740 */       int var4 = this.glyphWidth[par1] & 0xF;
/*      */       
/*  742 */       if (var4 > 7) {
/*      */         
/*  744 */         var4 = 15;
/*  745 */         var3 = 0;
/*      */       } 
/*      */       
/*  748 */       var4++;
/*  749 */       return (var4 - var3) / 2 + 1;
/*      */     } 
/*      */ 
/*      */     
/*  753 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String trimStringToWidth(String par1Str, int par2) {
/*  763 */     return trimStringToWidth(par1Str, par2, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String trimStringToWidth(String par1Str, int par2, boolean par3) {
/*  771 */     StringBuilder var4 = new StringBuilder();
/*  772 */     int var5 = 0;
/*  773 */     int var6 = par3 ? (par1Str.length() - 1) : 0;
/*  774 */     int var7 = par3 ? -1 : 1;
/*  775 */     boolean var8 = false;
/*  776 */     boolean var9 = false;
/*      */     int var10;
/*  778 */     for (var10 = var6; var10 >= 0 && var10 < par1Str.length() && var5 < par2; var10 += var7) {
/*      */       
/*  780 */       char var11 = par1Str.charAt(var10);
/*  781 */       int var12 = getCharWidth(var11);
/*      */       
/*  783 */       if (var8) {
/*      */         
/*  785 */         var8 = false;
/*      */         
/*  787 */         if (var11 != 'l' && var11 != 'L')
/*      */         {
/*  789 */           if (var11 == 'r' || var11 == 'R')
/*      */           {
/*  791 */             var9 = false;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  796 */           var9 = true;
/*      */         }
/*      */       
/*  799 */       } else if (var12 < 0) {
/*      */         
/*  801 */         var8 = true;
/*      */       }
/*      */       else {
/*      */         
/*  805 */         var5 += var12;
/*      */         
/*  807 */         if (var9)
/*      */         {
/*  809 */           var5++;
/*      */         }
/*      */       } 
/*      */       
/*  813 */       if (var5 > par2) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  818 */       if (par3) {
/*      */         
/*  820 */         var4.insert(0, var11);
/*      */       }
/*      */       else {
/*      */         
/*  824 */         var4.append(var11);
/*      */       } 
/*      */     } 
/*      */     
/*  828 */     return var4.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String trimStringNewline(String par1Str) {
/*  836 */     while (par1Str != null && par1Str.endsWith("\n"))
/*      */     {
/*  838 */       par1Str = par1Str.substring(0, par1Str.length() - 1);
/*      */     }
/*      */     
/*  841 */     return par1Str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawSplitString(String par1Str, int par2, int par3, int par4, int par5) {
/*  849 */     resetStyles();
/*  850 */     this.textColor = par5;
/*  851 */     par1Str = trimStringNewline(par1Str);
/*  852 */     renderSplitString(par1Str, par2, par3, par4, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderSplitString(String par1Str, int par2, int par3, int par4, boolean par5) {
/*  861 */     List var6 = listFormattedStringToWidth(par1Str, par4);
/*      */     
/*  863 */     for (Iterator<String> var7 = var6.iterator(); var7.hasNext(); par3 += this.FONT_HEIGHT) {
/*      */       
/*  865 */       String var8 = var7.next();
/*  866 */       renderStringAligned(var8, par2, par3, par4, this.textColor, par5);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int splitStringWidth(String par1Str, int par2) {
/*  875 */     return this.FONT_HEIGHT * listFormattedStringToWidth(par1Str, par2).size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnicodeFlag(boolean par1) {
/*  884 */     this.unicodeFlag = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUnicodeFlag() {
/*  893 */     return this.unicodeFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBidiFlag(boolean par1) {
/*  901 */     this.bidiFlag = par1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List listFormattedStringToWidth(String par1Str, int par2) {
/*  909 */     return Arrays.asList(wrapFormattedStringToWidth(par1Str, par2).split("\n"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String wrapFormattedStringToWidth(String par1Str, int par2) {
/*  917 */     int var3 = sizeStringToWidth(par1Str, par2);
/*      */     
/*  919 */     if (par1Str.length() <= var3)
/*      */     {
/*  921 */       return par1Str;
/*      */     }
/*      */ 
/*      */     
/*  925 */     String var4 = par1Str.substring(0, var3);
/*  926 */     char var5 = par1Str.charAt(var3);
/*  927 */     boolean var6 = (var5 == ' ' || var5 == '\n');
/*  928 */     String var7 = getFormatFromString(var4) + par1Str.substring(var3 + (var6 ? 1 : 0));
/*  929 */     return var4 + "\n" + wrapFormattedStringToWidth(var7, par2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int sizeStringToWidth(String par1Str, int par2) {
/*  938 */     int var3 = par1Str.length();
/*  939 */     int var4 = 0;
/*  940 */     int var5 = 0;
/*  941 */     int var6 = -1;
/*      */     
/*  943 */     for (boolean var7 = false; var5 < var3; var5++) {
/*      */       
/*  945 */       char var8 = par1Str.charAt(var5);
/*      */       
/*  947 */       switch (var8) {
/*      */         
/*      */         case '\n':
/*  950 */           var5--;
/*      */           break;
/*      */         
/*      */         case '§':
/*  954 */           if (var5 < var3 - 1) {
/*      */             
/*  956 */             var5++;
/*  957 */             char var9 = par1Str.charAt(var5);
/*      */             
/*  959 */             if (var9 != 'l' && var9 != 'L') {
/*      */               
/*  961 */               if (var9 == 'r' || var9 == 'R' || isFormatColor(var9))
/*      */               {
/*  963 */                 var7 = false;
/*      */               }
/*      */               
/*      */               break;
/*      */             } 
/*  968 */             var7 = true;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case ' ':
/*  975 */           var6 = var5;
/*      */         
/*      */         default:
/*  978 */           var4 += getCharWidth(var8);
/*      */           
/*  980 */           if (var7)
/*      */           {
/*  982 */             var4++;
/*      */           }
/*      */           break;
/*      */       } 
/*  986 */       if (var8 == '\n') {
/*      */ 
/*      */         
/*  989 */         var6 = ++var5;
/*      */         
/*      */         break;
/*      */       } 
/*  993 */       if (var4 > par2) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  999 */     return (var5 != var3 && var6 != -1 && var6 < var5) ? var6 : var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isFormatColor(char par0) {
/* 1007 */     if (par0 == 'g' || par0 == 'h') {
/* 1008 */       return true;
/*      */     }
/* 1010 */     return ((par0 >= '0' && par0 <= '9') || (par0 >= 'a' && par0 <= 'f') || (par0 >= 'A' && par0 <= 'F'));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isFormatSpecial(char par0) {
/* 1018 */     return ((par0 >= 'k' && par0 <= 'o') || (par0 >= 'K' && par0 <= 'O') || par0 == 'r' || par0 == 'R');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getFormatFromString(String par0Str) {
/* 1026 */     String var1 = "";
/* 1027 */     int var2 = -1;
/* 1028 */     int var3 = par0Str.length();
/*      */     
/* 1030 */     while ((var2 = par0Str.indexOf('§', var2 + 1)) != -1) {
/*      */       
/* 1032 */       if (var2 < var3 - 1) {
/*      */         
/* 1034 */         char var4 = par0Str.charAt(var2 + 1);
/*      */         
/* 1036 */         if (isFormatColor(var4)) {
/*      */           
/* 1038 */           var1 = "§" + var4; continue;
/*      */         } 
/* 1040 */         if (isFormatSpecial(var4))
/*      */         {
/* 1042 */           var1 = var1 + "§" + var4;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1047 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBidiFlag() {
/* 1055 */     return this.bidiFlag;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FontRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */