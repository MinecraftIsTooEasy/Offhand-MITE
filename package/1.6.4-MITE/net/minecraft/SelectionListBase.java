/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SelectionListBase
/*     */ {
/*     */   private final Minecraft mc;
/*     */   private final int field_96619_e;
/*     */   private final int field_96616_f;
/*     */   private final int field_96617_g;
/*     */   private final int field_96627_h;
/*     */   protected final int field_96620_b;
/*     */   protected int field_96621_c;
/*     */   protected int field_96618_d;
/*  20 */   private float field_96628_i = -2.0F;
/*     */   
/*     */   private float field_96625_j;
/*     */   private float field_96626_k;
/*  24 */   private int field_96623_l = -1;
/*     */   private long field_96624_m;
/*     */   
/*     */   public SelectionListBase(Minecraft minecraft, int i, int j, int k, int l, int m) {
/*  28 */     this.mc = minecraft;
/*  29 */     this.field_96616_f = j;
/*  30 */     this.field_96627_h = j + l;
/*  31 */     this.field_96620_b = m;
/*  32 */     this.field_96619_e = i;
/*  33 */     this.field_96617_g = i + k;
/*     */   }
/*     */   
/*     */   protected abstract int func_96608_a();
/*     */   
/*     */   protected abstract void func_96615_a(int paramInt, boolean paramBoolean);
/*     */   
/*     */   protected abstract boolean func_96609_a(int paramInt);
/*     */   
/*     */   protected int func_96613_b() {
/*  43 */     return func_96608_a() * this.field_96620_b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void func_96611_c();
/*     */ 
/*     */   
/*     */   protected abstract void func_96610_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Tessellator paramTessellator);
/*     */ 
/*     */   
/*     */   private void func_96614_f() {
/*  54 */     int i = func_96607_d();
/*  55 */     if (i < 0) i = 0; 
/*  56 */     if (this.field_96626_k < 0.0F) this.field_96626_k = 0.0F; 
/*  57 */     if (this.field_96626_k > i) this.field_96626_k = i; 
/*     */   }
/*     */   
/*     */   public int func_96607_d() {
/*  61 */     return func_96613_b() - this.field_96627_h - this.field_96616_f - 4;
/*     */   }
/*     */   
/*     */   public void func_96612_a(int i, int j, float f) {
/*  65 */     this.field_96621_c = i;
/*  66 */     this.field_96618_d = j;
/*  67 */     func_96611_c();
/*     */     
/*  69 */     int k = func_96608_a();
/*     */     
/*  71 */     int m = func_96606_e();
/*  72 */     int n = m + 6;
/*     */     
/*  74 */     if (Mouse.isButtonDown(0)) {
/*  75 */       if (this.field_96628_i == -1.0F) {
/*  76 */         boolean bool = true;
/*  77 */         if (j >= this.field_96616_f && j <= this.field_96627_h) {
/*  78 */           int i4 = this.field_96619_e + 2;
/*  79 */           int i5 = this.field_96617_g - 2;
/*     */           
/*  81 */           int i6 = j - this.field_96616_f + (int)this.field_96626_k - 4;
/*  82 */           int i7 = i6 / this.field_96620_b;
/*  83 */           if (i >= i4 && i <= i5 && i7 >= 0 && i6 >= 0 && i7 < k) {
/*  84 */             boolean bool1 = (i7 == this.field_96623_l && Minecraft.getSystemTime() - this.field_96624_m < 250L) ? true : false;
/*  85 */             func_96615_a(i7, bool1);
/*  86 */             this.field_96623_l = i7;
/*  87 */             this.field_96624_m = Minecraft.getSystemTime();
/*  88 */           } else if (i >= i4 && i <= i5 && i6 < 0) {
/*  89 */             bool = false;
/*     */           } 
/*  91 */           if (i >= m && i <= n) {
/*  92 */             this.field_96625_j = -1.0F;
/*     */             
/*  94 */             int i8 = func_96607_d();
/*  95 */             if (i8 < 1) i8 = 1; 
/*  96 */             int i9 = (int)(((this.field_96627_h - this.field_96616_f) * (this.field_96627_h - this.field_96616_f)) / func_96613_b());
/*  97 */             if (i9 < 32) i9 = 32; 
/*  98 */             if (i9 > this.field_96627_h - this.field_96616_f - 8) i9 = this.field_96627_h - this.field_96616_f - 8;
/*     */             
/* 100 */             this.field_96625_j /= (this.field_96627_h - this.field_96616_f - i9) / i8;
/*     */           } else {
/*     */             
/* 103 */             this.field_96625_j = 1.0F;
/*     */           } 
/* 105 */           if (bool) {
/* 106 */             this.field_96628_i = j;
/*     */           } else {
/* 108 */             this.field_96628_i = -2.0F;
/*     */           } 
/*     */         } else {
/* 111 */           this.field_96628_i = -2.0F;
/*     */         } 
/* 113 */       } else if (this.field_96628_i >= 0.0F) {
/* 114 */         this.field_96626_k -= (j - this.field_96628_i) * this.field_96625_j;
/* 115 */         this.field_96628_i = j;
/*     */       } 
/*     */     } else {
/* 118 */       while (!this.mc.gameSettings.touchscreen && Mouse.next()) {
/* 119 */         int i4 = Mouse.getEventDWheel();
/* 120 */         if (i4 != 0) {
/* 121 */           if (i4 > 0) { i4 = -1; }
/* 122 */           else if (i4 < 0) { i4 = 1; }
/* 123 */            this.field_96626_k += (i4 * this.field_96620_b / 2);
/*     */         } 
/*     */       } 
/*     */       
/* 127 */       this.field_96628_i = -1.0F;
/*     */     } 
/*     */     
/* 130 */     func_96614_f();
/*     */     
/* 132 */     GL11.glDisable(2896);
/* 133 */     GL11.glDisable(2912);
/* 134 */     Tessellator tessellator = Tessellator.instance;
/*     */     
/* 136 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 137 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 138 */     float f1 = 32.0F;
/* 139 */     tessellator.startDrawingQuads();
/* 140 */     tessellator.setColorOpaque_I(2105376);
/* 141 */     tessellator.addVertexWithUV(this.field_96619_e, this.field_96627_h, 0.0D, (this.field_96619_e / f1), ((this.field_96627_h + (int)this.field_96626_k) / f1));
/* 142 */     tessellator.addVertexWithUV(this.field_96617_g, this.field_96627_h, 0.0D, (this.field_96617_g / f1), ((this.field_96627_h + (int)this.field_96626_k) / f1));
/* 143 */     tessellator.addVertexWithUV(this.field_96617_g, this.field_96616_f, 0.0D, (this.field_96617_g / f1), ((this.field_96616_f + (int)this.field_96626_k) / f1));
/* 144 */     tessellator.addVertexWithUV(this.field_96619_e, this.field_96616_f, 0.0D, (this.field_96619_e / f1), ((this.field_96616_f + (int)this.field_96626_k) / f1));
/* 145 */     tessellator.draw();
/*     */     
/* 147 */     int i1 = this.field_96619_e + 2;
/* 148 */     int i2 = this.field_96616_f + 4 - (int)this.field_96626_k; byte b;
/* 149 */     for (b = 0; b < k; b++) {
/*     */       
/* 151 */       int i4 = i2 + b * this.field_96620_b;
/* 152 */       int i5 = this.field_96620_b - 4;
/*     */       
/* 154 */       if (i4 + this.field_96620_b <= this.field_96627_h && i4 - 4 >= this.field_96616_f) {
/*     */ 
/*     */ 
/*     */         
/* 158 */         if (func_96609_a(b)) {
/* 159 */           int i6 = this.field_96619_e + 2;
/* 160 */           int i7 = this.field_96617_g - 2;
/* 161 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 162 */           GL11.glDisable(3553);
/* 163 */           tessellator.startDrawingQuads();
/* 164 */           tessellator.setColorOpaque_I(8421504);
/* 165 */           tessellator.addVertexWithUV(i6, (i4 + i5 + 2), 0.0D, 0.0D, 1.0D);
/* 166 */           tessellator.addVertexWithUV(i7, (i4 + i5 + 2), 0.0D, 1.0D, 1.0D);
/* 167 */           tessellator.addVertexWithUV(i7, (i4 - 2), 0.0D, 1.0D, 0.0D);
/* 168 */           tessellator.addVertexWithUV(i6, (i4 - 2), 0.0D, 0.0D, 0.0D);
/*     */           
/* 170 */           tessellator.setColorOpaque_I(0);
/* 171 */           tessellator.addVertexWithUV((i6 + 1), (i4 + i5 + 1), 0.0D, 0.0D, 1.0D);
/* 172 */           tessellator.addVertexWithUV((i7 - 1), (i4 + i5 + 1), 0.0D, 1.0D, 1.0D);
/* 173 */           tessellator.addVertexWithUV((i7 - 1), (i4 - 1), 0.0D, 1.0D, 0.0D);
/* 174 */           tessellator.addVertexWithUV((i6 + 1), (i4 - 1), 0.0D, 0.0D, 0.0D);
/*     */           
/* 176 */           tessellator.draw();
/* 177 */           GL11.glEnable(3553);
/*     */         } 
/*     */         
/* 180 */         func_96610_a(b, i1, i4, i5, tessellator);
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     GL11.glDisable(2929);
/*     */     
/* 186 */     b = 4;
/*     */     
/* 188 */     GL11.glEnable(3042);
/* 189 */     GL11.glBlendFunc(770, 771);
/* 190 */     GL11.glDisable(3008);
/* 191 */     GL11.glShadeModel(7425);
/*     */     
/* 193 */     GL11.glDisable(3553);
/*     */     
/* 195 */     tessellator.startDrawingQuads();
/* 196 */     tessellator.setColorRGBA_I(0, 0);
/* 197 */     tessellator.addVertexWithUV(this.field_96619_e, (this.field_96616_f + b), 0.0D, 0.0D, 1.0D);
/* 198 */     tessellator.addVertexWithUV(this.field_96617_g, (this.field_96616_f + b), 0.0D, 1.0D, 1.0D);
/* 199 */     tessellator.setColorRGBA_I(0, 255);
/* 200 */     tessellator.addVertexWithUV(this.field_96617_g, this.field_96616_f, 0.0D, 1.0D, 0.0D);
/* 201 */     tessellator.addVertexWithUV(this.field_96619_e, this.field_96616_f, 0.0D, 0.0D, 0.0D);
/* 202 */     tessellator.draw();
/*     */     
/* 204 */     tessellator.startDrawingQuads();
/* 205 */     tessellator.setColorRGBA_I(0, 255);
/* 206 */     tessellator.addVertexWithUV(this.field_96619_e, this.field_96627_h, 0.0D, 0.0D, 1.0D);
/* 207 */     tessellator.addVertexWithUV(this.field_96617_g, this.field_96627_h, 0.0D, 1.0D, 1.0D);
/* 208 */     tessellator.setColorRGBA_I(0, 0);
/* 209 */     tessellator.addVertexWithUV(this.field_96617_g, (this.field_96627_h - b), 0.0D, 1.0D, 0.0D);
/* 210 */     tessellator.addVertexWithUV(this.field_96619_e, (this.field_96627_h - b), 0.0D, 0.0D, 0.0D);
/* 211 */     tessellator.draw();
/*     */     
/* 213 */     int i3 = func_96607_d();
/* 214 */     if (i3 > 0) {
/* 215 */       int i4 = (this.field_96627_h - this.field_96616_f) * (this.field_96627_h - this.field_96616_f) / func_96613_b();
/* 216 */       if (i4 < 32) i4 = 32; 
/* 217 */       if (i4 > this.field_96627_h - this.field_96616_f - 8) i4 = this.field_96627_h - this.field_96616_f - 8;
/*     */       
/* 219 */       int i5 = (int)this.field_96626_k * (this.field_96627_h - this.field_96616_f - i4) / i3 + this.field_96616_f;
/* 220 */       if (i5 < this.field_96616_f) i5 = this.field_96616_f;
/*     */       
/* 222 */       tessellator.startDrawingQuads();
/* 223 */       tessellator.setColorRGBA_I(0, 255);
/* 224 */       tessellator.addVertexWithUV(m, this.field_96627_h, 0.0D, 0.0D, 1.0D);
/* 225 */       tessellator.addVertexWithUV(n, this.field_96627_h, 0.0D, 1.0D, 1.0D);
/* 226 */       tessellator.addVertexWithUV(n, this.field_96616_f, 0.0D, 1.0D, 0.0D);
/* 227 */       tessellator.addVertexWithUV(m, this.field_96616_f, 0.0D, 0.0D, 0.0D);
/* 228 */       tessellator.draw();
/*     */       
/* 230 */       tessellator.startDrawingQuads();
/* 231 */       tessellator.setColorRGBA_I(8421504, 255);
/* 232 */       tessellator.addVertexWithUV(m, (i5 + i4), 0.0D, 0.0D, 1.0D);
/* 233 */       tessellator.addVertexWithUV(n, (i5 + i4), 0.0D, 1.0D, 1.0D);
/* 234 */       tessellator.addVertexWithUV(n, i5, 0.0D, 1.0D, 0.0D);
/* 235 */       tessellator.addVertexWithUV(m, i5, 0.0D, 0.0D, 0.0D);
/* 236 */       tessellator.draw();
/*     */       
/* 238 */       tessellator.startDrawingQuads();
/* 239 */       tessellator.setColorRGBA_I(12632256, 255);
/* 240 */       tessellator.addVertexWithUV(m, (i5 + i4 - 1), 0.0D, 0.0D, 1.0D);
/* 241 */       tessellator.addVertexWithUV((n - 1), (i5 + i4 - 1), 0.0D, 1.0D, 1.0D);
/* 242 */       tessellator.addVertexWithUV((n - 1), i5, 0.0D, 1.0D, 0.0D);
/* 243 */       tessellator.addVertexWithUV(m, i5, 0.0D, 0.0D, 0.0D);
/* 244 */       tessellator.draw();
/*     */     } 
/*     */     
/* 247 */     GL11.glEnable(3553);
/*     */     
/* 249 */     GL11.glShadeModel(7424);
/* 250 */     GL11.glEnable(3008);
/* 251 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   protected int func_96606_e() {
/* 255 */     return this.field_96617_g - 8;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SelectionListBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */