/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public abstract class GuiSlot
/*     */ {
/*     */   private final Minecraft mc;
/*     */   private int width;
/*     */   private int height;
/*     */   protected int top;
/*     */   protected int bottom;
/*     */   private int right;
/*     */   private int left;
/*     */   protected final int slotHeight;
/*     */   private int scrollUpButtonID;
/*     */   private int scrollDownButtonID;
/*     */   protected int mouseX;
/*     */   protected int mouseY;
/*  44 */   private float initialClickY = -2.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   private float scrollMultiplier;
/*     */ 
/*     */ 
/*     */   
/*     */   private float amountScrolled;
/*     */ 
/*     */ 
/*     */   
/*  56 */   private int selectedElement = -1;
/*     */   
/*     */   private long lastClicked;
/*     */   
/*     */   private boolean showSelectionBox = true;
/*     */   
/*     */   private boolean field_77243_s;
/*     */   
/*     */   private int field_77242_t;
/*     */ 
/*     */   
/*     */   public GuiSlot(Minecraft par1Minecraft, int par2, int par3, int par4, int par5, int par6) {
/*  68 */     this.mc = par1Minecraft;
/*  69 */     this.width = par2;
/*  70 */     this.height = par3;
/*  71 */     this.top = par4;
/*  72 */     this.bottom = par5;
/*  73 */     this.slotHeight = par6;
/*  74 */     this.left = 0;
/*  75 */     this.right = par2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77207_a(int par1, int par2, int par3, int par4) {
/*  80 */     this.width = par1;
/*  81 */     this.height = par2;
/*  82 */     this.top = par3;
/*  83 */     this.bottom = par4;
/*  84 */     this.left = 0;
/*  85 */     this.right = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowSelectionBox(boolean par1) {
/*  90 */     this.showSelectionBox = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_77223_a(boolean par1, int par2) {
/*  95 */     this.field_77243_s = par1;
/*  96 */     this.field_77242_t = par2;
/*     */     
/*  98 */     if (!par1)
/*     */     {
/* 100 */       this.field_77242_t = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int getSize();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void elementClicked(int paramInt, boolean paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract boolean isSelected(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/* 124 */     return getSize() * this.slotHeight + this.field_77242_t;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void drawBackground();
/*     */   
/*     */   protected abstract void drawSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Tessellator paramTessellator);
/*     */   
/*     */   protected void func_77222_a(int par1, int par2, Tessellator par3Tessellator) {}
/*     */   
/*     */   protected void func_77224_a(int par1, int par2) {}
/*     */   
/*     */   protected void func_77215_b(int par1, int par2) {}
/*     */   
/*     */   public int func_77210_c(int par1, int par2) {
/* 139 */     int var3 = this.width / 2 - 110;
/* 140 */     int var4 = this.width / 2 + 110;
/* 141 */     int var5 = par2 - this.top - this.field_77242_t + (int)this.amountScrolled - 4;
/* 142 */     int var6 = var5 / this.slotHeight;
/* 143 */     return (par1 >= var3 && par1 <= var4 && var6 >= 0 && var5 >= 0 && var6 < getSize()) ? var6 : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerScrollButtons(int par1, int par2) {
/* 151 */     this.scrollUpButtonID = par1;
/* 152 */     this.scrollDownButtonID = par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void bindAmountScrolled() {
/* 160 */     int var1 = func_77209_d();
/*     */     
/* 162 */     if (var1 < 0)
/*     */     {
/* 164 */       var1 /= 2;
/*     */     }
/*     */     
/* 167 */     if (this.amountScrolled < 0.0F)
/*     */     {
/* 169 */       this.amountScrolled = 0.0F;
/*     */     }
/*     */     
/* 172 */     if (this.amountScrolled > var1)
/*     */     {
/* 174 */       this.amountScrolled = var1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_77209_d() {
/* 180 */     return getContentHeight() - this.bottom - this.top - 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77208_b(int par1) {
/* 185 */     this.amountScrolled += par1;
/* 186 */     bindAmountScrolled();
/* 187 */     this.initialClickY = -2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(GuiButton par1GuiButton) {
/* 192 */     if (par1GuiButton.enabled)
/*     */     {
/* 194 */       if (par1GuiButton.id == this.scrollUpButtonID) {
/*     */         
/* 196 */         this.amountScrolled -= (this.slotHeight * 2 / 3);
/* 197 */         this.initialClickY = -2.0F;
/* 198 */         bindAmountScrolled();
/*     */       }
/* 200 */       else if (par1GuiButton.id == this.scrollDownButtonID) {
/*     */         
/* 202 */         this.amountScrolled += (this.slotHeight * 2 / 3);
/* 203 */         this.initialClickY = -2.0F;
/* 204 */         bindAmountScrolled();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawDarkenedBackground(int layer) {
/* 211 */     GL11.glDisable(2896);
/* 212 */     GL11.glDisable(2912);
/* 213 */     Tessellator var18 = Tessellator.instance;
/* 214 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 215 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 216 */     float var17 = 32.0F;
/*     */     
/* 218 */     if (layer == 1) {
/*     */       
/* 220 */       var18.startDrawingQuads();
/* 221 */       var18.setColorOpaque_I(2105376);
/* 222 */       var18.addVertexWithUV(this.left, this.bottom, 0.0D, (this.left / var17), (this.bottom / var17));
/* 223 */       var18.addVertexWithUV(this.right, this.bottom, 0.0D, (this.right / var17), (this.bottom / var17));
/* 224 */       var18.addVertexWithUV(this.right, this.top, 0.0D, (this.right / var17), (this.top / var17));
/* 225 */       var18.addVertexWithUV(this.left, this.top, 0.0D, (this.left / var17), (this.top / var17));
/* 226 */       var18.draw();
/*     */     }
/* 228 */     else if (layer == 2) {
/*     */       
/* 230 */       GL11.glDisable(2929);
/* 231 */       byte var20 = 4;
/* 232 */       overlayBackground(0, this.top, 255, 255);
/* 233 */       overlayBackground(this.bottom, this.height, 255, 255);
/* 234 */       GL11.glEnable(3042);
/* 235 */       GL11.glBlendFunc(770, 771);
/* 236 */       GL11.glDisable(3008);
/* 237 */       GL11.glShadeModel(7425);
/* 238 */       GL11.glDisable(3553);
/*     */       
/* 240 */       var18.startDrawingQuads();
/* 241 */       var18.setColorRGBA_I(0, 0);
/* 242 */       var18.addVertexWithUV(this.left, (this.top + var20), 0.0D, 0.0D, 1.0D);
/* 243 */       var18.addVertexWithUV(this.right, (this.top + var20), 0.0D, 1.0D, 1.0D);
/* 244 */       var18.setColorRGBA_I(0, 255);
/* 245 */       var18.addVertexWithUV(this.right, this.top, 0.0D, 1.0D, 0.0D);
/* 246 */       var18.addVertexWithUV(this.left, this.top, 0.0D, 0.0D, 0.0D);
/* 247 */       var18.draw();
/* 248 */       var18.startDrawingQuads();
/* 249 */       var18.setColorRGBA_I(0, 255);
/* 250 */       var18.addVertexWithUV(this.left, this.bottom, 0.0D, 0.0D, 1.0D);
/* 251 */       var18.addVertexWithUV(this.right, this.bottom, 0.0D, 1.0D, 1.0D);
/* 252 */       var18.setColorRGBA_I(0, 0);
/* 253 */       var18.addVertexWithUV(this.right, (this.bottom - var20), 0.0D, 1.0D, 0.0D);
/* 254 */       var18.addVertexWithUV(this.left, (this.bottom - var20), 0.0D, 0.0D, 0.0D);
/* 255 */       var18.draw();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 264 */     this.mouseX = par1;
/* 265 */     this.mouseY = par2;
/* 266 */     drawBackground();
/* 267 */     int var4 = getSize();
/* 268 */     int var5 = getScrollBarX();
/* 269 */     int var6 = var5 + 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     if (Mouse.isButtonDown(0)) {
/*     */       
/* 278 */       if (this.initialClickY == -1.0F) {
/*     */         
/* 280 */         boolean var7 = true;
/*     */         
/* 282 */         if (par2 >= this.top && par2 <= this.bottom) {
/*     */           
/* 284 */           int var8 = this.width / 2 - 110;
/* 285 */           int i = this.width / 2 + 110;
/* 286 */           int j = par2 - this.top - this.field_77242_t + (int)this.amountScrolled - 4;
/* 287 */           int k = j / this.slotHeight;
/*     */           
/* 289 */           if (par1 >= var8 && par1 <= i && k >= 0 && j >= 0 && k < var4) {
/*     */             
/* 291 */             boolean var12 = (k == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L);
/* 292 */             elementClicked(k, var12);
/* 293 */             this.selectedElement = k;
/* 294 */             this.lastClicked = Minecraft.getSystemTime();
/*     */           }
/* 296 */           else if (par1 >= var8 && par1 <= i && j < 0) {
/*     */             
/* 298 */             func_77224_a(par1 - var8, par2 - this.top + (int)this.amountScrolled - 4);
/* 299 */             var7 = false;
/*     */           } 
/*     */           
/* 302 */           if (par1 >= var5 && par1 <= var6) {
/*     */             
/* 304 */             this.scrollMultiplier = -1.0F;
/* 305 */             int m = func_77209_d();
/*     */             
/* 307 */             if (m < 1)
/*     */             {
/* 309 */               m = 1;
/*     */             }
/*     */             
/* 312 */             int var13 = (int)(((this.bottom - this.top) * (this.bottom - this.top)) / getContentHeight());
/*     */             
/* 314 */             if (var13 < 32)
/*     */             {
/* 316 */               var13 = 32;
/*     */             }
/*     */             
/* 319 */             if (var13 > this.bottom - this.top - 8)
/*     */             {
/* 321 */               var13 = this.bottom - this.top - 8;
/*     */             }
/*     */             
/* 324 */             this.scrollMultiplier /= (this.bottom - this.top - var13) / m;
/*     */           }
/*     */           else {
/*     */             
/* 328 */             this.scrollMultiplier = 1.0F;
/*     */           } 
/*     */           
/* 331 */           if (var7)
/*     */           {
/* 333 */             this.initialClickY = par2;
/*     */           }
/*     */           else
/*     */           {
/* 337 */             this.initialClickY = -2.0F;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 342 */           this.initialClickY = -2.0F;
/*     */         }
/*     */       
/* 345 */       } else if (this.initialClickY >= 0.0F) {
/*     */         
/* 347 */         this.amountScrolled -= (par2 - this.initialClickY) * this.scrollMultiplier;
/* 348 */         this.initialClickY = par2;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 353 */       while (!this.mc.gameSettings.touchscreen && Mouse.next()) {
/*     */         
/* 355 */         int var16 = Mouse.getEventDWheel();
/*     */         
/* 357 */         if (var16 != 0) {
/*     */           
/* 359 */           if (var16 > 0) {
/*     */             
/* 361 */             var16 = -1;
/*     */           }
/* 363 */           else if (var16 < 0) {
/*     */             
/* 365 */             var16 = 1;
/*     */           } 
/*     */           
/* 368 */           this.amountScrolled += (var16 * this.slotHeight / 2);
/*     */         } 
/*     */       } 
/*     */       
/* 372 */       this.initialClickY = -1.0F;
/*     */     } 
/*     */     
/* 375 */     bindAmountScrolled();
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
/* 400 */     drawDarkenedBackground(1);
/*     */     
/* 402 */     Tessellator var18 = Tessellator.instance;
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
/* 428 */     int var9 = this.width / 2 - 92 - 16;
/* 429 */     int var10 = this.top + 4 - (int)this.amountScrolled;
/*     */     
/* 431 */     if (this.field_77243_s)
/*     */     {
/* 433 */       func_77222_a(var9, var10, var18);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 438 */     for (int var11 = 0; var11 < var4; var11++) {
/*     */       
/* 440 */       int i = var10 + var11 * this.slotHeight + this.field_77242_t;
/* 441 */       int var13 = this.slotHeight - 4;
/*     */       
/* 443 */       if (i <= this.bottom && i + var13 >= this.top) {
/*     */         
/* 445 */         if (this.showSelectionBox && isSelected(var11)) {
/*     */ 
/*     */ 
/*     */           
/* 449 */           int var14 = this.width / 2 - 110;
/* 450 */           int var15 = this.width / 2 + 110;
/* 451 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 452 */           GL11.glDisable(3553);
/* 453 */           var18.startDrawingQuads();
/* 454 */           var18.setColorOpaque_I(8421504);
/* 455 */           var18.addVertexWithUV(var14, (i + var13 + 2), 0.0D, 0.0D, 1.0D);
/* 456 */           var18.addVertexWithUV(var15, (i + var13 + 2), 0.0D, 1.0D, 1.0D);
/* 457 */           var18.addVertexWithUV(var15, (i - 2), 0.0D, 1.0D, 0.0D);
/* 458 */           var18.addVertexWithUV(var14, (i - 2), 0.0D, 0.0D, 0.0D);
/* 459 */           var18.setColorOpaque_I(0);
/* 460 */           var18.addVertexWithUV((var14 + 1), (i + var13 + 1), 0.0D, 0.0D, 1.0D);
/* 461 */           var18.addVertexWithUV((var15 - 1), (i + var13 + 1), 0.0D, 1.0D, 1.0D);
/* 462 */           var18.addVertexWithUV((var15 - 1), (i - 1), 0.0D, 1.0D, 0.0D);
/* 463 */           var18.addVertexWithUV((var14 + 1), (i - 1), 0.0D, 0.0D, 0.0D);
/* 464 */           var18.draw();
/* 465 */           GL11.glEnable(3553);
/*     */         } 
/*     */         
/* 468 */         drawSlot(var11, var9, i, var13, var18);
/*     */       } 
/*     */     } 
/*     */     
/* 472 */     drawDarkenedBackground(2);
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
/* 502 */     int var19 = func_77209_d();
/*     */     
/* 504 */     if (var19 > 0) {
/*     */       
/* 506 */       int var13 = (this.bottom - this.top) * (this.bottom - this.top) / getContentHeight();
/*     */       
/* 508 */       if (var13 < 32)
/*     */       {
/* 510 */         var13 = 32;
/*     */       }
/*     */       
/* 513 */       if (var13 > this.bottom - this.top - 8)
/*     */       {
/* 515 */         var13 = this.bottom - this.top - 8;
/*     */       }
/*     */       
/* 518 */       int var14 = (int)this.amountScrolled * (this.bottom - this.top - var13) / var19 + this.top;
/*     */       
/* 520 */       if (var14 < this.top)
/*     */       {
/* 522 */         var14 = this.top;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 527 */       var18.startDrawingQuads();
/* 528 */       var18.setColorRGBA_I(0, 255);
/* 529 */       var18.addVertexWithUV(var5, this.bottom, 0.0D, 0.0D, 1.0D);
/* 530 */       var18.addVertexWithUV(var6, this.bottom, 0.0D, 1.0D, 1.0D);
/* 531 */       var18.addVertexWithUV(var6, this.top, 0.0D, 1.0D, 0.0D);
/* 532 */       var18.addVertexWithUV(var5, this.top, 0.0D, 0.0D, 0.0D);
/* 533 */       var18.draw();
/* 534 */       var18.startDrawingQuads();
/* 535 */       var18.setColorRGBA_I(8421504, 255);
/* 536 */       var18.addVertexWithUV(var5, (var14 + var13), 0.0D, 0.0D, 1.0D);
/* 537 */       var18.addVertexWithUV(var6, (var14 + var13), 0.0D, 1.0D, 1.0D);
/* 538 */       var18.addVertexWithUV(var6, var14, 0.0D, 1.0D, 0.0D);
/* 539 */       var18.addVertexWithUV(var5, var14, 0.0D, 0.0D, 0.0D);
/* 540 */       var18.draw();
/* 541 */       var18.startDrawingQuads();
/* 542 */       var18.setColorRGBA_I(12632256, 255);
/* 543 */       var18.addVertexWithUV(var5, (var14 + var13 - 1), 0.0D, 0.0D, 1.0D);
/* 544 */       var18.addVertexWithUV((var6 - 1), (var14 + var13 - 1), 0.0D, 1.0D, 1.0D);
/* 545 */       var18.addVertexWithUV((var6 - 1), var14, 0.0D, 1.0D, 0.0D);
/* 546 */       var18.addVertexWithUV(var5, var14, 0.0D, 0.0D, 0.0D);
/* 547 */       var18.draw();
/*     */     } 
/*     */     
/* 550 */     func_77215_b(par1, par2);
/* 551 */     GL11.glEnable(3553);
/* 552 */     GL11.glShadeModel(7424);
/* 553 */     GL11.glEnable(3008);
/* 554 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/* 559 */     return this.width / 2 + 124;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void overlayBackground(int par1, int par2, int par3, int par4) {
/* 567 */     Tessellator var5 = Tessellator.instance;
/* 568 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 569 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 570 */     float var6 = 32.0F;
/* 571 */     var5.startDrawingQuads();
/* 572 */     var5.setColorRGBA_I(4210752, par4);
/* 573 */     var5.addVertexWithUV(0.0D, par2, 0.0D, 0.0D, (par2 / var6));
/* 574 */     var5.addVertexWithUV(this.width, par2, 0.0D, (this.width / var6), (par2 / var6));
/* 575 */     var5.setColorRGBA_I(4210752, par3);
/* 576 */     var5.addVertexWithUV(this.width, par1, 0.0D, (this.width / var6), (par1 / var6));
/* 577 */     var5.addVertexWithUV(0.0D, par1, 0.0D, 0.0D, (par1 / var6));
/* 578 */     var5.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiSlot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */