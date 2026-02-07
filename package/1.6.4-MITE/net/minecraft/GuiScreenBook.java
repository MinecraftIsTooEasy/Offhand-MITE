/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiScreenBook
/*     */   extends GuiScreen {
/*  10 */   private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
/*     */   
/*     */   private final EntityPlayer editingPlayer;
/*     */   
/*     */   private final ItemStack itemstackBook;
/*     */   
/*     */   private final boolean bookIsUnsigned;
/*     */   
/*     */   private boolean bookModified;
/*     */   
/*     */   private boolean editingTitle;
/*     */   
/*     */   private int updateCount;
/*  23 */   private int bookImageWidth = 192;
/*  24 */   private int bookImageHeight = 192;
/*  25 */   private int bookTotalPages = 1;
/*     */   private int currPage;
/*     */   private NBTTagList bookPages;
/*  28 */   private String bookTitle = "";
/*     */ 
/*     */   
/*     */   private GuiButtonNextPage buttonNextPage;
/*     */ 
/*     */   
/*     */   private GuiButtonNextPage buttonPreviousPage;
/*     */ 
/*     */   
/*     */   private GuiButton buttonDone;
/*     */ 
/*     */   
/*     */   private GuiButton buttonSign;
/*     */ 
/*     */   
/*     */   private GuiButton buttonFinalize;
/*     */ 
/*     */   
/*     */   private GuiButton buttonCancel;
/*     */ 
/*     */   
/*     */   public GuiScreenBook(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack, boolean par3) {
/*  50 */     this.editingPlayer = par1EntityPlayer;
/*  51 */     this.itemstackBook = par2ItemStack;
/*  52 */     this.bookIsUnsigned = par3;
/*     */     
/*  54 */     if (par2ItemStack.hasTagCompound()) {
/*     */       
/*  56 */       NBTTagCompound var4 = par2ItemStack.getTagCompound();
/*  57 */       this.bookPages = var4.getTagList("pages");
/*     */       
/*  59 */       if (this.bookPages != null) {
/*     */         
/*  61 */         this.bookPages = (NBTTagList)this.bookPages.copy();
/*  62 */         this.bookTotalPages = this.bookPages.tagCount();
/*     */         
/*  64 */         if (this.bookTotalPages < 1)
/*     */         {
/*  66 */           this.bookTotalPages = 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     if (this.bookPages == null && par3) {
/*     */       
/*  73 */       this.bookPages = new NBTTagList("pages");
/*  74 */       this.bookPages.appendTag(new NBTTagString("1", ""));
/*  75 */       this.bookTotalPages = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  84 */     super.updateScreen();
/*  85 */     this.updateCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  93 */     this.mc.sndManager.playSoundFX("imported.random.book_open", 0.5F, 1.0F);
/*     */     
/*  95 */     this.buttonList.clear();
/*  96 */     Keyboard.enableRepeatEvents(true);
/*     */     
/*  98 */     if (this.bookIsUnsigned) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       int center_x = this.width / 2;
/*     */       
/* 108 */       this.buttonList.add(this.buttonSign = new GuiButton(3, center_x - 91, 4 + this.bookImageHeight, 89, 20, I18n.getString("book.signButton")));
/* 109 */       this.buttonList.add(this.buttonDone = new GuiButton(0, center_x + 2, 4 + this.bookImageHeight, 89, 20, I18n.getString("gui.done")));
/* 110 */       this.buttonList.add(this.buttonFinalize = new GuiButton(5, center_x - 91, 4 + this.bookImageHeight, 89, 20, I18n.getString("book.finalizeButton")));
/* 111 */       this.buttonList.add(this.buttonCancel = new GuiButton(4, center_x + 2, 4 + this.bookImageHeight, 89, 20, I18n.getString("gui.cancel")));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 116 */       this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 91, 4 + this.bookImageHeight, 182, 20, I18n.getString("gui.done")));
/*     */     } 
/*     */     
/* 119 */     int var1 = (this.width - this.bookImageWidth) / 2;
/* 120 */     byte var2 = 2;
/* 121 */     this.buttonList.add(this.buttonNextPage = new GuiButtonNextPage(1, var1 + 120, var2 + 154, true));
/* 122 */     this.buttonList.add(this.buttonPreviousPage = new GuiButtonNextPage(2, var1 + 38, var2 + 154, false));
/* 123 */     updateButtons();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 131 */     Keyboard.enableRepeatEvents(false);
/*     */     
/* 133 */     this.mc.sndManager.playSoundFX("imported.random.book_close", 2.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateButtons() {
/* 138 */     this.buttonNextPage.drawButton = (!this.editingTitle && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned));
/* 139 */     this.buttonPreviousPage.drawButton = (!this.editingTitle && this.currPage > 0);
/* 140 */     this.buttonDone.drawButton = (!this.bookIsUnsigned || !this.editingTitle);
/*     */     
/* 142 */     if (this.bookIsUnsigned) {
/*     */       
/* 144 */       this.buttonSign.drawButton = !this.editingTitle;
/* 145 */       this.buttonCancel.drawButton = this.editingTitle;
/* 146 */       this.buttonFinalize.drawButton = this.editingTitle;
/* 147 */       this.buttonFinalize.enabled = (this.bookTitle.trim().length() > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendBookToServer(boolean par1) {
/* 153 */     if (this.itemstackBook.getItem() instanceof ItemReferencedBook) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (this.bookIsUnsigned && this.bookModified)
/*     */     {
/* 163 */       if (this.bookPages != null) {
/*     */         
/* 165 */         while (this.bookPages.tagCount() > 1) {
/*     */           
/* 167 */           NBTTagString var2 = (NBTTagString)this.bookPages.tagAt(this.bookPages.tagCount() - 1);
/*     */           
/* 169 */           if (var2.data != null && var2.data.length() != 0) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 174 */           this.bookPages.removeTag(this.bookPages.tagCount() - 1);
/*     */         } 
/*     */         
/* 177 */         if (this.itemstackBook.hasTagCompound()) {
/*     */           
/* 179 */           NBTTagCompound var7 = this.itemstackBook.getTagCompound();
/* 180 */           var7.setTag("pages", this.bookPages);
/*     */         }
/*     */         else {
/*     */           
/* 184 */           this.itemstackBook.setTagInfo("pages", this.bookPages);
/*     */         } 
/*     */         
/* 187 */         String var8 = "MC|BEdit";
/*     */         
/* 189 */         if (par1) {
/*     */           
/* 191 */           var8 = "MC|BSign";
/* 192 */           this.itemstackBook.setTagInfo("author", new NBTTagString("author", this.editingPlayer.getCommandSenderName()));
/* 193 */           this.itemstackBook.setTagInfo("title", new NBTTagString("title", this.bookTitle.trim()));
/* 194 */           this.itemstackBook.itemID = Item.writtenBook.itemID;
/*     */         } 
/*     */         
/* 197 */         ByteArrayOutputStream var3 = new ByteArrayOutputStream();
/* 198 */         DataOutputStream var4 = new DataOutputStream(var3);
/*     */ 
/*     */         
/*     */         try {
/* 202 */           Packet.writeItemStack(this.itemstackBook, var4);
/* 203 */           this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(var8, var3.toByteArray()));
/*     */         }
/* 205 */         catch (Exception var6) {
/*     */           
/* 207 */           var6.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {
/* 218 */     int previous_page = this.currPage;
/*     */     
/* 220 */     if (par1GuiButton.enabled) {
/*     */       
/* 222 */       if (par1GuiButton.id == 0) {
/*     */         
/* 224 */         this.mc.displayGuiScreen((GuiScreen)null);
/* 225 */         sendBookToServer(false);
/*     */       }
/* 227 */       else if (par1GuiButton.id == 3 && this.bookIsUnsigned) {
/*     */         
/* 229 */         this.editingTitle = true;
/*     */       }
/* 231 */       else if (par1GuiButton.id == 1) {
/*     */         
/* 233 */         if (this.currPage < this.bookTotalPages - 1)
/*     */         {
/* 235 */           this.currPage++;
/*     */         }
/* 237 */         else if (this.bookIsUnsigned)
/*     */         {
/* 239 */           addNewPage();
/*     */           
/* 241 */           if (this.currPage < this.bookTotalPages - 1)
/*     */           {
/* 243 */             this.currPage++;
/*     */           }
/*     */         }
/*     */       
/* 247 */       } else if (par1GuiButton.id == 2) {
/*     */         
/* 249 */         if (this.currPage > 0)
/*     */         {
/* 251 */           this.currPage--;
/*     */         }
/*     */       }
/* 254 */       else if (par1GuiButton.id == 5 && this.editingTitle) {
/*     */         
/* 256 */         sendBookToServer(true);
/* 257 */         this.mc.displayGuiScreen((GuiScreen)null);
/*     */       }
/* 259 */       else if (par1GuiButton.id == 4 && this.editingTitle) {
/*     */         
/* 261 */         this.editingTitle = false;
/*     */       } 
/*     */       
/* 264 */       updateButtons();
/*     */     } 
/*     */     
/* 267 */     if (this.currPage != previous_page) {
/* 268 */       this.mc.sndManager.playSoundFX("imported.random.book_page", 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addNewPage() {
/* 273 */     if (this.bookPages != null && this.bookPages.tagCount() < 50) {
/*     */       
/* 275 */       this.bookPages.appendTag(new NBTTagString("" + (this.bookTotalPages + 1), ""));
/* 276 */       this.bookTotalPages++;
/* 277 */       this.bookModified = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/* 286 */     super.keyTyped(par1, par2);
/*     */     
/* 288 */     if (par2 == 203) {
/*     */       
/* 290 */       actionPerformed(this.buttonPreviousPage);
/*     */       return;
/*     */     } 
/* 293 */     if (par2 == 205) {
/*     */       
/* 295 */       actionPerformed(this.buttonNextPage);
/*     */       
/*     */       return;
/*     */     } 
/* 299 */     if (this.bookIsUnsigned)
/*     */     {
/* 301 */       if (this.editingTitle) {
/*     */         
/* 303 */         func_74162_c(par1, par2);
/*     */       }
/*     */       else {
/*     */         
/* 307 */         keyTypedInBook(par1, par2);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void keyTypedInBook(char par1, int par2) {
/*     */     String var3;
/* 317 */     switch (par1) {
/*     */       
/*     */       case '\026':
/* 320 */         func_74160_b(GuiScreen.getClipboardString());
/*     */         return;
/*     */     } 
/*     */     
/* 324 */     switch (par2) {
/*     */       
/*     */       case 14:
/* 327 */         var3 = func_74158_i();
/*     */         
/* 329 */         if (var3.length() > 0)
/*     */         {
/* 331 */           func_74159_a(var3.substring(0, var3.length() - 1));
/*     */         }
/*     */         return;
/*     */ 
/*     */       
/*     */       case 28:
/*     */       case 156:
/* 338 */         func_74160_b("\n");
/*     */         return;
/*     */     } 
/*     */     
/* 342 */     if (ChatAllowedCharacters.isAllowedCharacter(par1))
/*     */     {
/* 344 */       func_74160_b(Character.toString(par1));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_74162_c(char par1, int par2) {
/* 352 */     if (par1 == '*') {
/*     */       return;
/*     */     }
/* 355 */     if (this.editingTitle && par1 == '"') {
/*     */       return;
/*     */     }
/* 358 */     switch (par2) {
/*     */       
/*     */       case 14:
/* 361 */         if (!this.bookTitle.isEmpty()) {
/*     */           
/* 363 */           this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
/* 364 */           updateButtons();
/*     */         } 
/*     */         return;
/*     */ 
/*     */       
/*     */       case 28:
/*     */       case 156:
/* 371 */         if (!this.bookTitle.isEmpty()) {
/*     */           
/* 373 */           sendBookToServer(true);
/* 374 */           this.mc.displayGuiScreen((GuiScreen)null);
/*     */         } 
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 380 */     if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(par1)) {
/*     */       
/* 382 */       this.bookTitle += Character.toString(par1);
/* 383 */       updateButtons();
/* 384 */       this.bookModified = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String func_74158_i() {
/* 391 */     if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
/*     */       
/* 393 */       NBTTagString var1 = (NBTTagString)this.bookPages.tagAt(this.currPage);
/* 394 */       return var1.toString();
/*     */     } 
/*     */ 
/*     */     
/* 398 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_74159_a(String par1Str) {
/* 404 */     if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
/*     */       
/* 406 */       NBTTagString var2 = (NBTTagString)this.bookPages.tagAt(this.currPage);
/* 407 */       var2.data = par1Str;
/* 408 */       this.bookModified = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_74160_b(String par1Str) {
/* 414 */     String var2 = func_74158_i();
/* 415 */     String var3 = var2 + par1Str;
/* 416 */     int var4 = this.fontRenderer.splitStringWidth(var3 + "" + EnumChatFormatting.BLACK + "_", 118);
/*     */     
/* 418 */     if (var4 <= 118 && var3.length() < 256)
/*     */     {
/* 420 */       func_74159_a(var3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 429 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 430 */     this.mc.getTextureManager().bindTexture(bookGuiTextures);
/* 431 */     int var4 = (this.width - this.bookImageWidth) / 2;
/* 432 */     byte var5 = 2;
/* 433 */     var5 = 10;
/* 434 */     drawTexturedModalRect(var4, var5, 0, 0, this.bookImageWidth, this.bookImageHeight);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 439 */     if (this.editingTitle) {
/*     */       
/* 441 */       String var6 = this.bookTitle;
/*     */       
/* 443 */       if (this.bookIsUnsigned)
/*     */       {
/* 445 */         if (this.updateCount / 6 % 2 == 0) {
/*     */           
/* 447 */           var6 = var6 + "" + EnumChatFormatting.BLACK + "_";
/*     */         }
/*     */         else {
/*     */           
/* 451 */           var6 = var6 + "" + EnumChatFormatting.GRAY + "_";
/*     */         } 
/*     */       }
/*     */       
/* 455 */       String var7 = I18n.getString("book.editTitle");
/* 456 */       int var8 = this.fontRenderer.getStringWidth(var7);
/* 457 */       this.fontRenderer.drawString(var7, var4 + 36 + (116 - var8) / 2, var5 + 16 + 16, 0);
/* 458 */       int var9 = this.fontRenderer.getStringWidth(var6);
/* 459 */       this.fontRenderer.drawString(var6, var4 + 36 + (116 - var9) / 2, var5 + 48, 0);
/* 460 */       String var10 = String.format(I18n.getString("book.byAuthor"), new Object[] { this.editingPlayer.getCommandSenderName() });
/* 461 */       int var11 = this.fontRenderer.getStringWidth(var10);
/* 462 */       this.fontRenderer.drawString(EnumChatFormatting.DARK_GRAY + var10, var4 + 36 + (116 - var11) / 2, var5 + 48 + 10, 0);
/* 463 */       String var12 = I18n.getString("book.finalizeWarning");
/* 464 */       this.fontRenderer.drawSplitString(var12, var4 + 36, var5 + 80, 116, 0);
/*     */     }
/*     */     else {
/*     */       
/* 468 */       String var6 = String.format(I18n.getString("book.pageIndicator"), new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages) });
/* 469 */       String var7 = "";
/*     */       
/* 471 */       if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
/*     */         
/* 473 */         NBTTagString var13 = (NBTTagString)this.bookPages.tagAt(this.currPage);
/* 474 */         var7 = var13.toString();
/*     */       } 
/*     */       
/* 477 */       if (this.bookIsUnsigned)
/*     */       {
/* 479 */         if (this.fontRenderer.getBidiFlag()) {
/*     */           
/* 481 */           var7 = var7 + "_";
/*     */         }
/* 483 */         else if (this.updateCount / 6 % 2 == 0) {
/*     */           
/* 485 */           var7 = var7 + "" + EnumChatFormatting.BLACK + "_";
/*     */         }
/*     */         else {
/*     */           
/* 489 */           var7 = var7 + "" + EnumChatFormatting.GRAY + "_";
/*     */         } 
/*     */       }
/*     */       
/* 493 */       int var8 = this.fontRenderer.getStringWidth(var6);
/* 494 */       this.fontRenderer.drawString(var6, var4 - var8 + this.bookImageWidth - 44, var5 + 16, 0);
/* 495 */       this.fontRenderer.drawSplitString(var7, var4 + 36, var5 + 16 + 16, 116, 0);
/*     */     } 
/*     */     
/* 498 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   static ResourceLocation func_110404_g() {
/* 503 */     return bookGuiTextures;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 508 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowsImposedChat() {
/* 513 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenBook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */