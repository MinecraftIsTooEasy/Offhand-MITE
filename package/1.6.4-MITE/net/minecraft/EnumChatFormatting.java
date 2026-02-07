/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public enum EnumChatFormatting
/*     */ {
/*  11 */   BLACK('0', 0, 0, 0),
/*  12 */   DARK_BLUE('1', 0, 0, 170),
/*  13 */   DARK_GREEN('2', 0, 170, 0),
/*  14 */   DARK_AQUA('3', 0, 170, 170),
/*  15 */   DARK_RED('4', 170, 0, 0),
/*  16 */   DARK_PURPLE('5', 170, 0, 170),
/*  17 */   GOLD('6', 255, 170, 0),
/*  18 */   GRAY('7', 170, 170, 170),
/*  19 */   DARK_GRAY('8', 85, 85, 85),
/*  20 */   BLUE('9', 85, 85, 255),
/*  21 */   GREEN('a', 85, 255, 85),
/*  22 */   AQUA('b', 85, 255, 255),
/*  23 */   RED('c', 255, 85, 85),
/*  24 */   LIGHT_PURPLE('d', 255, 85, 255),
/*  25 */   YELLOW('e', 255, 255, 85),
/*  26 */   WHITE('f', 255, 255, 255),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   BROWN('g', 192, 144, 96),
/*  35 */   LIGHT_GRAY('h', 192, 192, 192),
/*  36 */   OBFUSCATED('k', true),
/*  37 */   BOLD('l', true),
/*  38 */   STRIKETHROUGH('m', true),
/*  39 */   UNDERLINE('n', true),
/*  40 */   ITALIC('o', true),
/*     */   
/*  42 */   RESET('r', false); static {
/*  43 */     field_96321_w = new HashMap<Object, Object>();
/*  44 */     field_96331_x = new HashMap<Object, Object>();
/*  45 */     field_96330_y = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     EnumChatFormatting[] var0 = values();
/* 170 */     int var1 = var0.length;
/*     */     
/* 172 */     for (int var2 = 0; var2 < var1; var2++) {
/*     */       
/* 174 */       EnumChatFormatting var3 = var0[var2];
/* 175 */       field_96321_w.put(Character.valueOf(var3.func_96298_a()), var3);
/* 176 */       field_96331_x.put(var3.func_96297_d(), var3);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final Map field_96321_w;
/*     */   private static final Map field_96331_x;
/*     */   private static final Pattern field_96330_y;
/*     */   private final char field_96329_z;
/*     */   private final boolean field_96303_A;
/*     */   private final String field_96304_B;
/*     */   public int rgb;
/*     */   public int rgb_shadow;
/*     */   public int rgb_anaglyph;
/*     */   public int rgb_anaglyph_shadow;
/*     */   public int r;
/*     */   public int g;
/*     */   public int b;
/*     */   
/*     */   EnumChatFormatting(char par3, boolean par4) {
/*     */     this.field_96329_z = par3;
/*     */     this.field_96303_A = par4;
/*     */     this.field_96304_B = "§" + par3;
/*     */   }
/*     */   
/*     */   EnumChatFormatting(char c, int r, int g, int b) {
/*     */     this.r = r;
/*     */     this.g = g;
/*     */     this.b = b;
/*     */     this.rgb = (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
/*     */     this.rgb_shadow = (r / 4 & 0xFF) << 16 | (g / 4 & 0xFF) << 8 | b / 4 & 0xFF;
/*     */     int r_anaglyph = (r * 30 + g * 59 + b * 11) / 100;
/*     */     int g_anaglyph = (r * 30 + g * 70) / 100;
/*     */     int b_anaglyph = (r * 30 + b * 70) / 100;
/*     */     this.rgb_anaglyph = (r_anaglyph & 0xFF) << 16 | (g_anaglyph & 0xFF) << 8 | b_anaglyph & 0xFF;
/*     */     this.rgb_anaglyph_shadow = (r_anaglyph / 4 & 0xFF) << 16 | (g_anaglyph / 4 & 0xFF) << 8 | b_anaglyph / 4 & 0xFF;
/*     */   }
/*     */   
/*     */   public static EnumChatFormatting getByChar(char c) {
/*     */     for (int i = 0; i < (values()).length; i++) {
/*     */       if ((values()[i]).field_96329_z == c)
/*     */         return values()[i]; 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public char func_96298_a() {
/*     */     return this.field_96329_z;
/*     */   }
/*     */   
/*     */   public boolean func_96301_b() {
/*     */     return this.field_96303_A;
/*     */   }
/*     */   
/*     */   public boolean isColor() {
/*     */     return (!this.field_96303_A && this != RESET);
/*     */   }
/*     */   
/*     */   public String func_96297_d() {
/*     */     return name().toLowerCase();
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return this.field_96304_B;
/*     */   }
/*     */   
/*     */   public static String func_110646_a(String par0Str) {
/*     */     return (par0Str == null) ? null : field_96330_y.matcher(par0Str).replaceAll("");
/*     */   }
/*     */   
/*     */   public static EnumChatFormatting func_96300_b(String par0Str) {
/*     */     return (par0Str == null) ? null : (EnumChatFormatting)field_96331_x.get(par0Str.toLowerCase());
/*     */   }
/*     */   
/*     */   public static Collection func_96296_a(boolean par0, boolean par1) {
/*     */     ArrayList<String> var2 = new ArrayList();
/*     */     EnumChatFormatting[] var3 = values();
/*     */     int var4 = var3.length;
/*     */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       EnumChatFormatting var6 = var3[var5];
/*     */       if ((!var6.isColor() || par0) && (!var6.func_96301_b() || par1))
/*     */         var2.add(var6.func_96297_d()); 
/*     */     } 
/*     */     return var2;
/*     */   }
/*     */   
/*     */   public float getRedAsFloat() {
/*     */     return this.r / 255.0F;
/*     */   }
/*     */   
/*     */   public float getGreenAsFloat() {
/*     */     return this.g / 255.0F;
/*     */   }
/*     */   
/*     */   public float getBlueAsFloat() {
/*     */     return this.b / 255.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EnumChatFormatting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */