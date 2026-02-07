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
/*     */ public abstract class GuiScreenSelectLocation
/*     */ {
/*     */   private final Minecraft field_104092_f;
/*     */   private int field_104093_g;
/*     */   private int field_104105_h;
/*     */   protected int field_104098_a;
/*     */   protected int field_104096_b;
/*     */   private int field_104106_i;
/*     */   private int field_104103_j;
/*     */   protected final int field_104097_c;
/*     */   private int field_104104_k;
/*     */   private int field_104101_l;
/*     */   protected int field_104094_d;
/*     */   protected int field_104095_e;
/*  30 */   private float field_104102_m = -2.0F;
/*     */   
/*     */   private float field_104099_n;
/*     */   private float field_104100_o;
/*  34 */   private int field_104111_p = -1;
/*     */   
/*     */   private long field_104110_q;
/*     */   private boolean field_104109_r = true;
/*     */   private boolean field_104108_s;
/*     */   private int field_104107_t;
/*     */   
/*     */   public GuiScreenSelectLocation(Minecraft minecraft, int i, int j, int k, int l, int m) {
/*  42 */     this.field_104092_f = minecraft;
/*  43 */     this.field_104093_g = i;
/*  44 */     this.field_104105_h = j;
/*  45 */     this.field_104098_a = k;
/*  46 */     this.field_104096_b = l;
/*  47 */     this.field_104097_c = m;
/*  48 */     this.field_104103_j = 0;
/*  49 */     this.field_104106_i = i;
/*     */   }
/*     */   
/*     */   public void func_104084_a(int i, int j, int k, int l) {
/*  53 */     this.field_104093_g = i;
/*  54 */     this.field_104105_h = j;
/*  55 */     this.field_104098_a = k;
/*  56 */     this.field_104096_b = l;
/*  57 */     this.field_104103_j = 0;
/*  58 */     this.field_104106_i = i;
/*     */   }
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
/*     */   protected abstract boolean func_104086_b(int paramInt);
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_130003_b() {
/*  83 */     return getSize() * this.field_104097_c + this.field_104107_t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void func_130004_c();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void drawSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Tessellator paramTessellator);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_104088_a(int i, int j, Tessellator tessellator) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_104089_a(int i, int j) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_104087_b(int i, int j) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_104091_h() {
/* 119 */     int i = func_104085_d();
/* 120 */     if (i < 0) i /= 2; 
/* 121 */     if (this.field_104100_o < 0.0F) this.field_104100_o = 0.0F; 
/* 122 */     if (this.field_104100_o > i) this.field_104100_o = i; 
/*     */   }
/*     */   
/*     */   public int func_104085_d() {
/* 126 */     return func_130003_b() - this.field_104096_b - this.field_104098_a - 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(GuiButton guiButton) {
/* 144 */     if (!guiButton.enabled)
/*     */       return; 
/* 146 */     if (guiButton.id == this.field_104104_k) {
/* 147 */       this.field_104100_o -= (this.field_104097_c * 2 / 3);
/* 148 */       this.field_104102_m = -2.0F;
/* 149 */       func_104091_h();
/* 150 */     } else if (guiButton.id == this.field_104101_l) {
/* 151 */       this.field_104100_o += (this.field_104097_c * 2 / 3);
/* 152 */       this.field_104102_m = -2.0F;
/* 153 */       func_104091_h();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 158 */     this.field_104094_d = i;
/* 159 */     this.field_104095_e = j;
/* 160 */     func_130004_c();
/*     */     
/* 162 */     int k = getSize();
/*     */     
/* 164 */     int m = func_104090_g();
/* 165 */     int n = m + 6;
/*     */     
/* 167 */     if (Mouse.isButtonDown(0)) {
/* 168 */       if (this.field_104102_m == -1.0F) {
/* 169 */         boolean bool = true;
/* 170 */         if (j >= this.field_104098_a && j <= this.field_104096_b) {
/* 171 */           int i4 = this.field_104093_g / 2 - 110;
/* 172 */           int i5 = this.field_104093_g / 2 + 110;
/*     */           
/* 174 */           int i6 = j - this.field_104098_a - this.field_104107_t + (int)this.field_104100_o - 4;
/* 175 */           int i7 = i6 / this.field_104097_c;
/* 176 */           if (i >= i4 && i <= i5 && i7 >= 0 && i6 >= 0 && i7 < k) {
/*     */             
/* 178 */             boolean bool1 = (i7 == this.field_104111_p && Minecraft.getSystemTime() - this.field_104110_q < 250L) ? true : false;
/*     */             
/* 180 */             elementClicked(i7, bool1);
/* 181 */             this.field_104111_p = i7;
/* 182 */             this.field_104110_q = Minecraft.getSystemTime();
/* 183 */           } else if (i >= i4 && i <= i5 && i6 < 0) {
/* 184 */             func_104089_a(i - i4, j - this.field_104098_a + (int)this.field_104100_o - 4);
/* 185 */             bool = false;
/*     */           } 
/* 187 */           if (i >= m && i <= n) {
/* 188 */             this.field_104099_n = -1.0F;
/*     */             
/* 190 */             int i8 = func_104085_d();
/* 191 */             if (i8 < 1) i8 = 1; 
/* 192 */             int i9 = (int)(((this.field_104096_b - this.field_104098_a) * (this.field_104096_b - this.field_104098_a)) / func_130003_b());
/* 193 */             if (i9 < 32) i9 = 32; 
/* 194 */             if (i9 > this.field_104096_b - this.field_104098_a - 8) i9 = this.field_104096_b - this.field_104098_a - 8;
/*     */             
/* 196 */             this.field_104099_n /= (this.field_104096_b - this.field_104098_a - i9) / i8;
/*     */           } else {
/*     */             
/* 199 */             this.field_104099_n = 1.0F;
/*     */           } 
/* 201 */           if (bool) {
/* 202 */             this.field_104102_m = j;
/*     */           } else {
/* 204 */             this.field_104102_m = -2.0F;
/*     */           } 
/*     */         } else {
/* 207 */           this.field_104102_m = -2.0F;
/*     */         } 
/* 209 */       } else if (this.field_104102_m >= 0.0F) {
/* 210 */         this.field_104100_o -= (j - this.field_104102_m) * this.field_104099_n;
/* 211 */         this.field_104102_m = j;
/*     */       } 
/*     */     } else {
/* 214 */       while (!this.field_104092_f.gameSettings.touchscreen && Mouse.next()) {
/* 215 */         int i4 = Mouse.getEventDWheel();
/* 216 */         if (i4 != 0) {
/* 217 */           if (i4 > 0) { i4 = -1; }
/* 218 */           else if (i4 < 0) { i4 = 1; }
/* 219 */            this.field_104100_o += (i4 * this.field_104097_c / 2);
/*     */         } 
/*     */       } 
/*     */       
/* 223 */       this.field_104102_m = -1.0F;
/*     */     } 
/*     */     
/* 226 */     func_104091_h();
/*     */     
/* 228 */     GL11.glDisable(2896);
/* 229 */     GL11.glDisable(2912);
/* 230 */     Tessellator tessellator = Tessellator.instance;
/*     */     
/* 232 */     this.field_104092_f.getTextureManager().bindTexture(Gui.optionsBackground);
/* 233 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 234 */     float f1 = 32.0F;
/* 235 */     tessellator.startDrawingQuads();
/* 236 */     tessellator.setColorOpaque_I(2105376);
/* 237 */     tessellator.addVertexWithUV(this.field_104103_j, this.field_104096_b, 0.0D, (this.field_104103_j / f1), ((this.field_104096_b + (int)this.field_104100_o) / f1));
/* 238 */     tessellator.addVertexWithUV(this.field_104106_i, this.field_104096_b, 0.0D, (this.field_104106_i / f1), ((this.field_104096_b + (int)this.field_104100_o) / f1));
/* 239 */     tessellator.addVertexWithUV(this.field_104106_i, this.field_104098_a, 0.0D, (this.field_104106_i / f1), ((this.field_104098_a + (int)this.field_104100_o) / f1));
/* 240 */     tessellator.addVertexWithUV(this.field_104103_j, this.field_104098_a, 0.0D, (this.field_104103_j / f1), ((this.field_104098_a + (int)this.field_104100_o) / f1));
/* 241 */     tessellator.draw();
/*     */     
/* 243 */     int i1 = this.field_104093_g / 2 - 92 - 16;
/* 244 */     int i2 = this.field_104098_a + 4 - (int)this.field_104100_o;
/*     */     
/* 246 */     if (this.field_104108_s) {
/* 247 */       func_104088_a(i1, i2, tessellator);
/*     */     }
/*     */     byte b;
/* 250 */     for (b = 0; b < k; b++) {
/*     */       
/* 252 */       int i4 = i2 + b * this.field_104097_c + this.field_104107_t;
/* 253 */       int i5 = this.field_104097_c - 4;
/*     */       
/* 255 */       if (i4 <= this.field_104096_b && i4 + i5 >= this.field_104098_a) {
/*     */ 
/*     */ 
/*     */         
/* 259 */         if (this.field_104109_r && func_104086_b(b)) {
/* 260 */           int i6 = this.field_104093_g / 2 - 110;
/* 261 */           int i7 = this.field_104093_g / 2 + 110;
/* 262 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 263 */           GL11.glDisable(3553);
/* 264 */           tessellator.startDrawingQuads();
/* 265 */           tessellator.setColorOpaque_I(0);
/* 266 */           tessellator.addVertexWithUV(i6, (i4 + i5 + 2), 0.0D, 0.0D, 1.0D);
/* 267 */           tessellator.addVertexWithUV(i7, (i4 + i5 + 2), 0.0D, 1.0D, 1.0D);
/* 268 */           tessellator.addVertexWithUV(i7, (i4 - 2), 0.0D, 1.0D, 0.0D);
/* 269 */           tessellator.addVertexWithUV(i6, (i4 - 2), 0.0D, 0.0D, 0.0D);
/*     */           
/* 271 */           tessellator.draw();
/* 272 */           GL11.glEnable(3553);
/*     */         } 
/*     */         
/* 275 */         if (this.field_104109_r && isSelected(b)) {
/* 276 */           int i6 = this.field_104093_g / 2 - 110;
/* 277 */           int i7 = this.field_104093_g / 2 + 110;
/* 278 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 279 */           GL11.glDisable(3553);
/* 280 */           tessellator.startDrawingQuads();
/* 281 */           tessellator.setColorOpaque_I(8421504);
/* 282 */           tessellator.addVertexWithUV(i6, (i4 + i5 + 2), 0.0D, 0.0D, 1.0D);
/* 283 */           tessellator.addVertexWithUV(i7, (i4 + i5 + 2), 0.0D, 1.0D, 1.0D);
/* 284 */           tessellator.addVertexWithUV(i7, (i4 - 2), 0.0D, 1.0D, 0.0D);
/* 285 */           tessellator.addVertexWithUV(i6, (i4 - 2), 0.0D, 0.0D, 0.0D);
/*     */           
/* 287 */           tessellator.setColorOpaque_I(0);
/* 288 */           tessellator.addVertexWithUV((i6 + 1), (i4 + i5 + 1), 0.0D, 0.0D, 1.0D);
/* 289 */           tessellator.addVertexWithUV((i7 - 1), (i4 + i5 + 1), 0.0D, 1.0D, 1.0D);
/* 290 */           tessellator.addVertexWithUV((i7 - 1), (i4 - 1), 0.0D, 1.0D, 0.0D);
/* 291 */           tessellator.addVertexWithUV((i6 + 1), (i4 - 1), 0.0D, 0.0D, 0.0D);
/*     */           
/* 293 */           tessellator.draw();
/* 294 */           GL11.glEnable(3553);
/*     */         } 
/*     */         
/* 297 */         drawSlot(b, i1, i4, i5, tessellator);
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     GL11.glDisable(2929);
/*     */     
/* 303 */     b = 4;
/*     */     
/* 305 */     func_104083_b(0, this.field_104098_a, 255, 255);
/* 306 */     func_104083_b(this.field_104096_b, this.field_104105_h, 255, 255);
/*     */     
/* 308 */     GL11.glEnable(3042);
/* 309 */     GL11.glBlendFunc(770, 771);
/* 310 */     GL11.glDisable(3008);
/* 311 */     GL11.glShadeModel(7425);
/*     */     
/* 313 */     GL11.glDisable(3553);
/*     */     
/* 315 */     tessellator.startDrawingQuads();
/* 316 */     tessellator.setColorRGBA_I(0, 0);
/* 317 */     tessellator.addVertexWithUV(this.field_104103_j, (this.field_104098_a + b), 0.0D, 0.0D, 1.0D);
/* 318 */     tessellator.addVertexWithUV(this.field_104106_i, (this.field_104098_a + b), 0.0D, 1.0D, 1.0D);
/* 319 */     tessellator.setColorRGBA_I(0, 255);
/* 320 */     tessellator.addVertexWithUV(this.field_104106_i, this.field_104098_a, 0.0D, 1.0D, 0.0D);
/* 321 */     tessellator.addVertexWithUV(this.field_104103_j, this.field_104098_a, 0.0D, 0.0D, 0.0D);
/* 322 */     tessellator.draw();
/*     */     
/* 324 */     tessellator.startDrawingQuads();
/* 325 */     tessellator.setColorRGBA_I(0, 255);
/* 326 */     tessellator.addVertexWithUV(this.field_104103_j, this.field_104096_b, 0.0D, 0.0D, 1.0D);
/* 327 */     tessellator.addVertexWithUV(this.field_104106_i, this.field_104096_b, 0.0D, 1.0D, 1.0D);
/* 328 */     tessellator.setColorRGBA_I(0, 0);
/* 329 */     tessellator.addVertexWithUV(this.field_104106_i, (this.field_104096_b - b), 0.0D, 1.0D, 0.0D);
/* 330 */     tessellator.addVertexWithUV(this.field_104103_j, (this.field_104096_b - b), 0.0D, 0.0D, 0.0D);
/* 331 */     tessellator.draw();
/*     */     
/* 333 */     int i3 = func_104085_d();
/* 334 */     if (i3 > 0) {
/* 335 */       int i4 = (this.field_104096_b - this.field_104098_a) * (this.field_104096_b - this.field_104098_a) / func_130003_b();
/* 336 */       if (i4 < 32) i4 = 32; 
/* 337 */       if (i4 > this.field_104096_b - this.field_104098_a - 8) i4 = this.field_104096_b - this.field_104098_a - 8;
/*     */       
/* 339 */       int i5 = (int)this.field_104100_o * (this.field_104096_b - this.field_104098_a - i4) / i3 + this.field_104098_a;
/* 340 */       if (i5 < this.field_104098_a) i5 = this.field_104098_a;
/*     */       
/* 342 */       tessellator.startDrawingQuads();
/* 343 */       tessellator.setColorRGBA_I(0, 255);
/* 344 */       tessellator.addVertexWithUV(m, this.field_104096_b, 0.0D, 0.0D, 1.0D);
/* 345 */       tessellator.addVertexWithUV(n, this.field_104096_b, 0.0D, 1.0D, 1.0D);
/* 346 */       tessellator.addVertexWithUV(n, this.field_104098_a, 0.0D, 1.0D, 0.0D);
/* 347 */       tessellator.addVertexWithUV(m, this.field_104098_a, 0.0D, 0.0D, 0.0D);
/* 348 */       tessellator.draw();
/*     */       
/* 350 */       tessellator.startDrawingQuads();
/* 351 */       tessellator.setColorRGBA_I(8421504, 255);
/* 352 */       tessellator.addVertexWithUV(m, (i5 + i4), 0.0D, 0.0D, 1.0D);
/* 353 */       tessellator.addVertexWithUV(n, (i5 + i4), 0.0D, 1.0D, 1.0D);
/* 354 */       tessellator.addVertexWithUV(n, i5, 0.0D, 1.0D, 0.0D);
/* 355 */       tessellator.addVertexWithUV(m, i5, 0.0D, 0.0D, 0.0D);
/* 356 */       tessellator.draw();
/*     */       
/* 358 */       tessellator.startDrawingQuads();
/* 359 */       tessellator.setColorRGBA_I(12632256, 255);
/* 360 */       tessellator.addVertexWithUV(m, (i5 + i4 - 1), 0.0D, 0.0D, 1.0D);
/* 361 */       tessellator.addVertexWithUV((n - 1), (i5 + i4 - 1), 0.0D, 1.0D, 1.0D);
/* 362 */       tessellator.addVertexWithUV((n - 1), i5, 0.0D, 1.0D, 0.0D);
/* 363 */       tessellator.addVertexWithUV(m, i5, 0.0D, 0.0D, 0.0D);
/* 364 */       tessellator.draw();
/*     */     } 
/*     */     
/* 367 */     func_104087_b(i, j);
/*     */     
/* 369 */     GL11.glEnable(3553);
/*     */     
/* 371 */     GL11.glShadeModel(7424);
/* 372 */     GL11.glEnable(3008);
/* 373 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   protected int func_104090_g() {
/* 377 */     return this.field_104093_g / 2 + 124;
/*     */   }
/*     */   
/*     */   private void func_104083_b(int i, int j, int k, int l) {
/* 381 */     Tessellator tessellator = Tessellator.instance;
/* 382 */     this.field_104092_f.getTextureManager().bindTexture(Gui.optionsBackground);
/* 383 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 384 */     float f = 32.0F;
/* 385 */     tessellator.startDrawingQuads();
/* 386 */     tessellator.setColorRGBA_I(4210752, l);
/* 387 */     tessellator.addVertexWithUV(0.0D, j, 0.0D, 0.0D, (j / f));
/* 388 */     tessellator.addVertexWithUV(this.field_104093_g, j, 0.0D, (this.field_104093_g / f), (j / f));
/* 389 */     tessellator.setColorRGBA_I(4210752, k);
/* 390 */     tessellator.addVertexWithUV(this.field_104093_g, i, 0.0D, (this.field_104093_g / f), (i / f));
/* 391 */     tessellator.addVertexWithUV(0.0D, i, 0.0D, 0.0D, (i / f));
/* 392 */     tessellator.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenSelectLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */