/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiNewChat
/*     */   extends Gui
/*     */ {
/*     */   private final Minecraft mc;
/*  14 */   private final List sentMessages = new ArrayList();
/*     */ 
/*     */   
/*  17 */   private final List chatLines = new ArrayList();
/*  18 */   private final List field_96134_d = new ArrayList();
/*     */   
/*     */   private int field_73768_d;
/*     */   private boolean field_73769_e;
/*     */   
/*     */   public GuiNewChat(Minecraft par1Minecraft) {
/*  24 */     this.mc = par1Minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawChat(int par1) {
/*  29 */     if (this.mc.gameSettings.gui_mode != 0) {
/*     */       return;
/*     */     }
/*  32 */     if (this.mc.gameSettings.chatVisibility != 2) {
/*     */       
/*  34 */       int var2 = func_96127_i();
/*  35 */       boolean var3 = false;
/*  36 */       int var4 = 0;
/*  37 */       int var5 = this.field_96134_d.size();
/*  38 */       float var6 = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;
/*     */       
/*  40 */       if (var5 > 0) {
/*     */         
/*  42 */         if (getChatOpen())
/*     */         {
/*  44 */           var3 = true;
/*     */         }
/*     */         
/*  47 */         float var7 = func_96131_h();
/*  48 */         int var8 = MathHelper.ceiling_float_int(func_96126_f() / var7);
/*  49 */         GL11.glPushMatrix();
/*  50 */         GL11.glTranslatef(2.0F, 20.0F, 0.0F);
/*  51 */         GL11.glScalef(var7, var7, 1.0F);
/*     */ 
/*     */         
/*     */         int var9;
/*     */         
/*  56 */         for (var9 = 0; var9 + this.field_73768_d < this.field_96134_d.size() && var9 < var2; var9++) {
/*     */           
/*  58 */           ChatLine var10 = this.field_96134_d.get(var9 + this.field_73768_d);
/*     */           
/*  60 */           if (var10 != null) {
/*     */             
/*  62 */             int var11 = par1 - var10.getUpdatedCounter();
/*     */             
/*  64 */             if (var11 < 200 || var3) {
/*     */               
/*  66 */               double var12 = var11 / 200.0D;
/*  67 */               var12 = 1.0D - var12;
/*  68 */               var12 *= 10.0D;
/*     */               
/*  70 */               if (var12 < 0.0D)
/*     */               {
/*  72 */                 var12 = 0.0D;
/*     */               }
/*     */               
/*  75 */               if (var12 > 1.0D)
/*     */               {
/*  77 */                 var12 = 1.0D;
/*     */               }
/*     */               
/*  80 */               var12 *= var12;
/*  81 */               int var14 = (int)(255.0D * var12);
/*     */               
/*  83 */               if (var3)
/*     */               {
/*  85 */                 var14 = 255;
/*     */               }
/*     */               
/*  88 */               var14 = (int)(var14 * var6);
/*  89 */               var4++;
/*     */               
/*  91 */               if (var14 > 3) {
/*     */                 
/*  93 */                 byte var15 = 0;
/*  94 */                 int var16 = -var9 * 9;
/*     */ 
/*     */ 
/*     */                 
/*  98 */                 var16 -= 18;
/*     */                 
/* 100 */                 if (!(this.mc.currentScreen instanceof GuiMITEDS))
/*     */                 {
/* 102 */                   drawRect(var15, var16 - 9, var15 + var8 + 4, var16, var14 / 2 << 24);
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 111 */                 GL11.glEnable(3042);
/* 112 */                 String var17 = var10.getChatLineString();
/*     */                 
/* 114 */                 if (!this.mc.gameSettings.chatColours)
/*     */                 {
/* 116 */                   var17 = StringUtils.stripControlCodes(var17);
/*     */                 }
/*     */                 
/* 119 */                 this.mc.fontRenderer.drawStringWithShadow(var17, var15, var16 - 8, 16777215 + (var14 << 24));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 125 */         if (var3) {
/*     */           
/* 127 */           var9 = this.mc.fontRenderer.FONT_HEIGHT;
/* 128 */           GL11.glTranslatef(-3.0F, 0.0F, 0.0F);
/* 129 */           int var18 = var5 * var9 + var5;
/* 130 */           int var11 = var4 * var9 + var4;
/* 131 */           int var20 = this.field_73768_d * var11 / var5;
/* 132 */           int var13 = var11 * var11 / var18;
/*     */           
/* 134 */           var20 += var9 * 2;
/* 135 */           var13 -= var9 * 2 + 2;
/*     */           
/* 137 */           if (var18 != var11) {
/*     */             
/* 139 */             int var14 = (var20 > 0) ? 170 : 96;
/* 140 */             int var19 = this.field_73769_e ? 13382451 : 3355562;
/* 141 */             drawRect(0, -var20, 2, -var20 - var13, var19 + (var14 << 24));
/* 142 */             drawRect(2, -var20, 1, -var20 - var13, 13421772 + (var14 << 24));
/*     */           } 
/*     */         } 
/*     */         
/* 146 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearChatMessages() {
/* 156 */     this.field_96134_d.clear();
/* 157 */     this.chatLines.clear();
/* 158 */     this.sentMessages.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printChatMessage(String par1Str) {
/* 166 */     printChatMessageWithOptionalDeletion(par1Str, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printChatMessageWithOptionalDeletion(String par1Str, int par2) {
/* 174 */     func_96129_a(par1Str, par2, this.mc.ingameGUI.getUpdateCounter(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_96129_a(String par1Str, int par2, int par3, boolean par4) {
/* 180 */     boolean var5 = getChatOpen();
/* 181 */     boolean var6 = true;
/*     */     
/* 183 */     if (par2 != 0)
/*     */     {
/* 185 */       deleteChatLine(par2);
/*     */     }
/*     */     
/* 188 */     Iterator<String> var7 = this.mc.fontRenderer.listFormattedStringToWidth(par1Str, MathHelper.floor_float(func_96126_f() / func_96131_h())).iterator();
/*     */     
/* 190 */     while (var7.hasNext()) {
/*     */       
/* 192 */       String var8 = var7.next();
/*     */       
/* 194 */       if (var5 && this.field_73768_d > 0) {
/*     */         
/* 196 */         this.field_73769_e = true;
/* 197 */         scroll(1);
/*     */       } 
/*     */       
/* 200 */       if (!var6)
/*     */       {
/* 202 */         var8 = " " + var8;
/*     */       }
/*     */       
/* 205 */       var6 = false;
/* 206 */       this.field_96134_d.add(0, new ChatLine(par3, var8, par2));
/*     */     } 
/*     */     
/* 209 */     while (this.field_96134_d.size() > 100)
/*     */     {
/* 211 */       this.field_96134_d.remove(this.field_96134_d.size() - 1);
/*     */     }
/*     */     
/* 214 */     if (!par4) {
/*     */       
/* 216 */       this.chatLines.add(0, new ChatLine(par3, par1Str.trim(), par2));
/*     */       
/* 218 */       while (this.chatLines.size() > 100)
/*     */       {
/* 220 */         this.chatLines.remove(this.chatLines.size() - 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96132_b() {
/* 227 */     this.field_96134_d.clear();
/* 228 */     resetScroll();
/*     */     
/* 230 */     for (int var1 = this.chatLines.size() - 1; var1 >= 0; var1--) {
/*     */       
/* 232 */       ChatLine var2 = this.chatLines.get(var1);
/* 233 */       func_96129_a(var2.getChatLineString(), var2.getChatLineID(), var2.getUpdatedCounter(), true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSentMessages() {
/* 242 */     return this.sentMessages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSentMessages(String par1Str) {
/* 250 */     if (this.sentMessages.isEmpty() || !((String)this.sentMessages.get(this.sentMessages.size() - 1)).equals(par1Str))
/*     */     {
/* 252 */       this.sentMessages.add(par1Str);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetScroll() {
/* 261 */     this.field_73768_d = 0;
/* 262 */     this.field_73769_e = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scroll(int par1) {
/* 270 */     this.field_73768_d += par1;
/* 271 */     int var2 = this.field_96134_d.size();
/*     */     
/* 273 */     if (this.field_73768_d > var2 - func_96127_i())
/*     */     {
/* 275 */       this.field_73768_d = var2 - func_96127_i();
/*     */     }
/*     */     
/* 278 */     if (this.field_73768_d <= 0) {
/*     */       
/* 280 */       this.field_73768_d = 0;
/* 281 */       this.field_73769_e = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatClickData func_73766_a(int par1, int par2) {
/* 287 */     if (!getChatOpen())
/*     */     {
/* 289 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 293 */     ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/* 294 */     int var4 = var3.getScaleFactor();
/* 295 */     float var5 = func_96131_h();
/* 296 */     int var6 = par1 / var4 - 3;
/* 297 */     int var7 = par2 / var4 - 25;
/* 298 */     var6 = MathHelper.floor_float(var6 / var5);
/* 299 */     var7 = MathHelper.floor_float(var7 / var5);
/*     */     
/* 301 */     if (var6 >= 0 && var7 >= 0) {
/*     */       
/* 303 */       int var8 = Math.min(func_96127_i(), this.field_96134_d.size());
/*     */       
/* 305 */       if (var6 <= MathHelper.floor_float(func_96126_f() / func_96131_h()) && var7 < this.mc.fontRenderer.FONT_HEIGHT * var8 + var8) {
/*     */         
/* 307 */         int var9 = var7 / (this.mc.fontRenderer.FONT_HEIGHT + 1) + this.field_73768_d;
/* 308 */         return new ChatClickData(this.mc.fontRenderer, this.field_96134_d.get(var9), var6, var7 - (var9 - this.field_73768_d) * this.mc.fontRenderer.FONT_HEIGHT + var9);
/*     */       } 
/*     */ 
/*     */       
/* 312 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTranslatedMessage(String par1Str, Object... par2ArrayOfObj) {
/* 327 */     printChatMessage(I18n.getStringParams(par1Str, par2ArrayOfObj));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getChatOpen() {
/* 336 */     return this.mc.isAnyChatOpen();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteChatLine(int par1) {
/*     */     ChatLine var3;
/* 344 */     Iterator<ChatLine> var2 = this.field_96134_d.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 349 */       if (!var2.hasNext()) {
/*     */         ChatLine chatLine;
/* 351 */         var2 = this.chatLines.iterator();
/*     */ 
/*     */         
/*     */         do {
/* 355 */           if (!var2.hasNext()) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 360 */           chatLine = var2.next();
/*     */         }
/* 362 */         while (chatLine.getChatLineID() != par1);
/*     */         
/* 364 */         var2.remove();
/*     */         
/*     */         return;
/*     */       } 
/* 368 */       var3 = var2.next();
/*     */     }
/* 370 */     while (var3.getChatLineID() != par1);
/*     */     
/* 372 */     var2.remove();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_96126_f() {
/* 377 */     return func_96128_a(this.mc.gameSettings.chatWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_96133_g() {
/* 382 */     return func_96130_b(getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_96131_h() {
/* 387 */     return this.mc.gameSettings.chatScale;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final int func_96128_a(float par0) {
/* 392 */     short var1 = 320;
/* 393 */     byte var2 = 40;
/* 394 */     return MathHelper.floor_float(par0 * (var1 - var2) + var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final int func_96130_b(float par0) {
/* 399 */     short var1 = 180;
/* 400 */     byte var2 = 20;
/* 401 */     return MathHelper.floor_float(par0 * (var1 - var2) + var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_96127_i() {
/* 406 */     if (this.mc.currentScreen instanceof GuiMITEDS) {
/* 407 */       return 10;
/*     */     }
/* 409 */     return func_96133_g() / 9;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiNewChat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */