/*     */ package net.minecraft;
/*     */ 
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
/*     */ public class GuiTextField
/*     */   extends Gui
/*     */ {
/*     */   private final FontRenderer fontRenderer;
/*     */   private final int xPos;
/*     */   private final int yPos;
/*     */   private final int width;
/*     */   private final int height;
/*  28 */   private String text = "";
/*  29 */   private int maxStringLength = 32;
/*     */   private int cursorCounter;
/*     */   private boolean enableBackgroundDrawing = true;
/*     */   private boolean canLoseFocus = true;
/*     */   private boolean isFocused;
/*     */   private boolean isEnabled = true;
/*     */   private int lineScrollOffset;
/*     */   private int cursorPosition;
/*     */   private int selectionEnd;
/*  38 */   private int enabledColor = 14737632;
/*  39 */   private int disabledColor = 7368816;
/*     */   private boolean visible = true;
/*     */   
/*     */   public GuiTextField(FontRenderer fontRenderer, int i, int j, int k, int l) {
/*  43 */     this.fontRenderer = fontRenderer;
/*  44 */     this.xPos = i;
/*  45 */     this.yPos = j;
/*  46 */     this.width = k;
/*  47 */     this.height = l;
/*     */   }
/*     */   
/*     */   public void updateCursorCounter() {
/*  51 */     this.cursorCounter++;
/*     */   }
/*     */   
/*     */   public void setText(String string) {
/*  55 */     if (string.length() > this.maxStringLength) {
/*  56 */       this.text = string.substring(0, this.maxStringLength);
/*     */     } else {
/*  58 */       this.text = string;
/*     */     } 
/*     */     
/*  61 */     setCursorPositionEnd();
/*     */   }
/*     */   
/*     */   public String getText() {
/*  65 */     return this.text;
/*     */   }
/*     */   
/*     */   public String getSelectedtext() {
/*  69 */     int i = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
/*  70 */     int j = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
/*     */     
/*  72 */     return this.text.substring(i, j);
/*     */   }
/*     */   
/*     */   public void writeText(String string) {
/*  76 */     String str1 = "";
/*  77 */     String str2 = ChatAllowedCharacters.filerAllowedCharacters(string);
/*  78 */     int i = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
/*  79 */     int j = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
/*  80 */     int k = this.maxStringLength - this.text.length() - i - this.selectionEnd;
/*  81 */     int m = 0;
/*     */     
/*  83 */     if (this.text.length() > 0) str1 = str1 + this.text.substring(0, i);
/*     */     
/*  85 */     if (k < str2.length()) {
/*  86 */       str1 = str1 + str2.substring(0, k);
/*  87 */       m = k;
/*     */     } else {
/*  89 */       str1 = str1 + str2;
/*  90 */       m = str2.length();
/*     */     } 
/*     */     
/*  93 */     if (this.text.length() > 0 && j < this.text.length()) str1 = str1 + this.text.substring(j);
/*     */     
/*  95 */     this.text = str1;
/*  96 */     moveCursorBy(i - this.selectionEnd + m);
/*     */   }
/*     */   
/*     */   public void deleteWords(int i) {
/* 100 */     if (this.text.length() == 0)
/*     */       return; 
/* 102 */     if (this.selectionEnd != this.cursorPosition) {
/* 103 */       writeText("");
/*     */       
/*     */       return;
/*     */     } 
/* 107 */     deleteFromCursor(getNthWordFromCursor(i) - this.cursorPosition);
/*     */   }
/*     */   
/*     */   public void deleteFromCursor(int i) {
/* 111 */     if (this.text.length() == 0)
/*     */       return; 
/* 113 */     if (this.selectionEnd != this.cursorPosition) {
/* 114 */       writeText("");
/*     */       
/*     */       return;
/*     */     } 
/* 118 */     boolean bool = (i < 0) ? true : false;
/* 119 */     int j = bool ? (this.cursorPosition + i) : this.cursorPosition;
/* 120 */     int k = bool ? this.cursorPosition : (this.cursorPosition + i);
/* 121 */     String str = "";
/*     */     
/* 123 */     if (j >= 0) str = this.text.substring(0, j);
/*     */     
/* 125 */     if (k < this.text.length()) str = str + this.text.substring(k);
/*     */     
/* 127 */     this.text = str;
/* 128 */     if (bool) moveCursorBy(i); 
/*     */   }
/*     */   
/*     */   public int getNthWordFromCursor(int i) {
/* 132 */     return getNthWordFromPos(i, getCursorPosition());
/*     */   }
/*     */   
/*     */   public int getNthWordFromPos(int i, int j) {
/* 136 */     return func_73798_a(i, getCursorPosition(), true);
/*     */   }
/*     */   
/*     */   public int func_73798_a(int i, int j, boolean bl) {
/* 140 */     int k = j;
/* 141 */     boolean bool = (i < 0) ? true : false;
/* 142 */     int m = Math.abs(i);
/*     */     
/* 144 */     for (byte b = 0; b < m; b++) {
/* 145 */       if (bool) {
/* 146 */         while (bl && k > 0 && this.text.charAt(k - 1) == ' ')
/* 147 */           k--; 
/* 148 */         while (k > 0 && this.text.charAt(k - 1) != ' ')
/* 149 */           k--; 
/*     */       } else {
/* 151 */         int n = this.text.length();
/*     */         
/* 153 */         k = this.text.indexOf(' ', k);
/* 154 */         if (k == -1) {
/* 155 */           k = n;
/*     */         } else {
/* 157 */           while (bl && k < n && this.text.charAt(k) == ' ') {
/* 158 */             k++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 163 */     return k;
/*     */   }
/*     */   
/*     */   public void moveCursorBy(int i) {
/* 167 */     setCursorPosition(this.selectionEnd + i);
/*     */   }
/*     */   
/*     */   public void setCursorPosition(int i) {
/* 171 */     this.cursorPosition = i;
/*     */     
/* 173 */     int j = this.text.length();
/* 174 */     if (this.cursorPosition < 0) this.cursorPosition = 0; 
/* 175 */     if (this.cursorPosition > j) this.cursorPosition = j;
/*     */     
/* 177 */     setSelectionPos(this.cursorPosition);
/*     */   }
/*     */   
/*     */   public void setCursorPositionZero() {
/* 181 */     setCursorPosition(0);
/*     */   }
/*     */   
/*     */   public void setCursorPositionEnd() {
/* 185 */     setCursorPosition(this.text.length());
/*     */   }
/*     */   
/*     */   public boolean textboxKeyTyped(char c, int i) {
/* 189 */     if (!this.isEnabled || !this.isFocused) {
/* 190 */       return false;
/*     */     }
/*     */     
/* 193 */     switch (c) {
/*     */       case '\001':
/* 195 */         setCursorPositionEnd();
/* 196 */         setSelectionPos(0);
/* 197 */         return true;
/*     */       case '\003':
/* 199 */         GuiScreen.setClipboardString(getSelectedtext());
/* 200 */         return true;
/*     */       case '\026':
/* 202 */         writeText(GuiScreen.getClipboardString());
/* 203 */         return true;
/*     */       case '\030':
/* 205 */         GuiScreen.setClipboardString(getSelectedtext());
/* 206 */         writeText("");
/* 207 */         return true;
/*     */     } 
/*     */     
/* 210 */     switch (i) {
/*     */       case 203:
/* 212 */         if (GuiScreen.isShiftKeyDown()) {
/* 213 */           if (GuiScreen.isCtrlKeyDown()) {
/* 214 */             setSelectionPos(getNthWordFromPos(-1, getSelectionEnd()));
/*     */           } else {
/* 216 */             setSelectionPos(getSelectionEnd() - 1);
/*     */           }
/*     */         
/* 219 */         } else if (GuiScreen.isCtrlKeyDown()) {
/* 220 */           setCursorPosition(getNthWordFromCursor(-1));
/*     */         } else {
/* 222 */           moveCursorBy(-1);
/*     */         } 
/*     */ 
/*     */         
/* 226 */         return true;
/*     */       case 205:
/* 228 */         if (GuiScreen.isShiftKeyDown()) {
/* 229 */           if (GuiScreen.isCtrlKeyDown()) {
/* 230 */             setSelectionPos(getNthWordFromPos(1, getSelectionEnd()));
/*     */           } else {
/* 232 */             setSelectionPos(getSelectionEnd() + 1);
/*     */           }
/*     */         
/* 235 */         } else if (GuiScreen.isCtrlKeyDown()) {
/* 236 */           setCursorPosition(getNthWordFromCursor(1));
/*     */         } else {
/* 238 */           moveCursorBy(1);
/*     */         } 
/*     */ 
/*     */         
/* 242 */         return true;
/*     */       case 14:
/* 244 */         if (GuiScreen.isCtrlKeyDown()) {
/* 245 */           deleteWords(-1);
/*     */         } else {
/* 247 */           deleteFromCursor(-1);
/*     */         } 
/*     */         
/* 250 */         return true;
/*     */       
/*     */       case 211:
/* 253 */         if (GuiScreen.isCtrlKeyDown()) {
/* 254 */           deleteWords(1);
/*     */         } else {
/* 256 */           deleteFromCursor(1);
/*     */         } 
/*     */         
/* 259 */         return true;
/*     */       
/*     */       case 199:
/* 262 */         if (GuiScreen.isShiftKeyDown()) {
/* 263 */           setSelectionPos(0);
/*     */         } else {
/* 265 */           setCursorPositionZero();
/*     */         } 
/*     */         
/* 268 */         return true;
/*     */       
/*     */       case 207:
/* 271 */         if (GuiScreen.isShiftKeyDown()) {
/* 272 */           setSelectionPos(this.text.length());
/*     */         } else {
/* 274 */           setCursorPositionEnd();
/*     */         } 
/*     */         
/* 277 */         return true;
/*     */     } 
/*     */     
/* 280 */     if (ChatAllowedCharacters.isAllowedCharacter(c)) {
/* 281 */       writeText(Character.toString(c));
/*     */       
/* 283 */       return true;
/*     */     } 
/*     */     
/* 286 */     return false;
/*     */   }
/*     */   
/*     */   public void mouseClicked(int i, int j, int k) {
/* 290 */     boolean bool = (i >= this.xPos && i < this.xPos + this.width && j >= this.yPos && j < this.yPos + this.height) ? true : false;
/*     */     
/* 292 */     if (this.canLoseFocus) {
/* 293 */       setFocused((this.isEnabled && bool));
/*     */     }
/*     */     
/* 296 */     if (this.isFocused && k == 0) {
/* 297 */       int m = i - this.xPos;
/*     */       
/* 299 */       if (this.enableBackgroundDrawing) {
/* 300 */         m -= 4;
/*     */       }
/*     */       
/* 303 */       String str = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), getWidth());
/* 304 */       setCursorPosition(this.fontRenderer.trimStringToWidth(str, m).length() + this.lineScrollOffset);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawTextBox() {
/* 309 */     if (!getVisible())
/*     */       return; 
/* 311 */     if (getEnableBackgroundDrawing()) {
/* 312 */       drawRect(this.xPos - 1, this.yPos - 1, this.xPos + this.width + 1, this.yPos + this.height + 1, -6250336);
/* 313 */       drawRect(this.xPos, this.yPos, this.xPos + this.width, this.yPos + this.height, -16777216);
/*     */     } 
/*     */     
/* 316 */     int i = this.isEnabled ? this.enabledColor : this.disabledColor;
/* 317 */     int j = this.cursorPosition - this.lineScrollOffset;
/* 318 */     int k = this.selectionEnd - this.lineScrollOffset;
/* 319 */     String str = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), getWidth());
/* 320 */     boolean bool1 = (j >= 0 && j <= str.length()) ? true : false;
/* 321 */     boolean bool2 = (this.isFocused && this.cursorCounter / 6 % 2 == 0 && bool1) ? true : false;
/* 322 */     int m = this.enableBackgroundDrawing ? (this.xPos + 4) : this.xPos;
/* 323 */     int n = this.enableBackgroundDrawing ? (this.yPos + (this.height - 8) / 2) : this.yPos;
/* 324 */     int i1 = m;
/*     */     
/* 326 */     if (k > str.length()) k = str.length();
/*     */     
/* 328 */     if (str.length() > 0) {
/* 329 */       String str1 = bool1 ? str.substring(0, j) : str;
/* 330 */       i1 = this.fontRenderer.drawStringWithShadow(str1, i1, n, i);
/*     */     } 
/*     */     
/* 333 */     boolean bool3 = (this.cursorPosition < this.text.length() || this.text.length() >= getMaxStringLength()) ? true : false;
/* 334 */     int i2 = i1;
/*     */     
/* 336 */     if (!bool1) {
/* 337 */       i2 = (j > 0) ? (m + this.width) : m;
/* 338 */     } else if (bool3) {
/* 339 */       i2--;
/* 340 */       i1--;
/*     */     } 
/*     */     
/* 343 */     if (str.length() > 0 && bool1 && j < str.length()) {
/* 344 */       i1 = this.fontRenderer.drawStringWithShadow(str.substring(j), i1, n, i);
/*     */     }
/*     */     
/* 347 */     if (bool2) {
/* 348 */       if (bool3) {
/* 349 */         Gui.drawRect(i2, n - 1, i2 + 1, n + 1 + this.fontRenderer.FONT_HEIGHT, -3092272);
/*     */       } else {
/* 351 */         this.fontRenderer.drawStringWithShadow("_", i2, n, i);
/*     */       } 
/*     */     }
/*     */     
/* 355 */     if (k != j) {
/* 356 */       int i3 = m + this.fontRenderer.getStringWidth(str.substring(0, k));
/* 357 */       drawCursorVertical(i2, n - 1, i3 - 1, n + 1 + this.fontRenderer.FONT_HEIGHT);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawCursorVertical(int i, int j, int k, int l) {
/* 362 */     if (i < k) {
/* 363 */       int m = i;
/* 364 */       i = k;
/* 365 */       k = m;
/*     */     } 
/* 367 */     if (j < l) {
/* 368 */       int m = j;
/* 369 */       j = l;
/* 370 */       l = m;
/*     */     } 
/*     */     
/* 373 */     Tessellator tessellator = Tessellator.instance;
/*     */     
/* 375 */     GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
/* 376 */     GL11.glDisable(3553);
/* 377 */     GL11.glEnable(3058);
/* 378 */     GL11.glLogicOp(5387);
/*     */     
/* 380 */     tessellator.startDrawingQuads();
/* 381 */     tessellator.addVertex(i, l, 0.0D);
/* 382 */     tessellator.addVertex(k, l, 0.0D);
/* 383 */     tessellator.addVertex(k, j, 0.0D);
/* 384 */     tessellator.addVertex(i, j, 0.0D);
/* 385 */     tessellator.draw();
/*     */     
/* 387 */     GL11.glDisable(3058);
/* 388 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   public void setMaxStringLength(int i) {
/* 392 */     this.maxStringLength = i;
/*     */     
/* 394 */     if (this.text.length() > i) {
/* 395 */       this.text = this.text.substring(0, i);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMaxStringLength() {
/* 400 */     return this.maxStringLength;
/*     */   }
/*     */   
/*     */   public int getCursorPosition() {
/* 404 */     return this.cursorPosition;
/*     */   }
/*     */   
/*     */   public boolean getEnableBackgroundDrawing() {
/* 408 */     return this.enableBackgroundDrawing;
/*     */   }
/*     */   
/*     */   public void setEnableBackgroundDrawing(boolean bl) {
/* 412 */     this.enableBackgroundDrawing = bl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextColor(int i) {
/* 420 */     this.enabledColor = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisabledTextColour(int i) {
/* 428 */     this.disabledColor = i;
/*     */   }
/*     */   
/*     */   public void setFocused(boolean bl) {
/* 432 */     if (bl && !this.isFocused)
/*     */     {
/* 434 */       this.cursorCounter = 0;
/*     */     }
/* 436 */     this.isFocused = bl;
/*     */   }
/*     */   
/*     */   public boolean isFocused() {
/* 440 */     return this.isFocused;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean bl) {
/* 448 */     this.isEnabled = bl;
/*     */   }
/*     */   
/*     */   public int getSelectionEnd() {
/* 452 */     return this.selectionEnd;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 456 */     return getEnableBackgroundDrawing() ? (this.width - 8) : this.width;
/*     */   }
/*     */   
/*     */   public void setSelectionPos(int i) {
/* 460 */     int j = this.text.length();
/*     */     
/* 462 */     if (i > j) i = j; 
/* 463 */     if (i < 0) i = 0;
/*     */     
/* 465 */     this.selectionEnd = i;
/*     */     
/* 467 */     if (this.fontRenderer != null) {
/* 468 */       if (this.lineScrollOffset > j) this.lineScrollOffset = j; 
/* 469 */       int k = getWidth();
/* 470 */       String str = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), k);
/* 471 */       int m = str.length() + this.lineScrollOffset;
/*     */       
/* 473 */       if (i == this.lineScrollOffset) {
/* 474 */         this.lineScrollOffset -= this.fontRenderer.trimStringToWidth(this.text, k, true).length();
/*     */       }
/* 476 */       if (i > m) {
/* 477 */         this.lineScrollOffset += i - m;
/* 478 */       } else if (i <= this.lineScrollOffset) {
/* 479 */         this.lineScrollOffset -= this.lineScrollOffset - i;
/*     */       } 
/*     */       
/* 482 */       if (this.lineScrollOffset < 0) this.lineScrollOffset = 0; 
/* 483 */       if (this.lineScrollOffset > j) this.lineScrollOffset = j;
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanLoseFocus(boolean bl) {
/* 492 */     this.canLoseFocus = bl;
/*     */   }
/*     */   
/*     */   public boolean getVisible() {
/* 496 */     return this.visible;
/*     */   }
/*     */   
/*     */   public void setVisible(boolean bl) {
/* 500 */     this.visible = bl;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiTextField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */