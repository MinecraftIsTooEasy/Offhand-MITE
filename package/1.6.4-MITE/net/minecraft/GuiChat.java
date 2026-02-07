/*     */ package net.minecraft;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiChat
/*     */   extends GuiScreen
/*     */ {
/*  14 */   private String field_73898_b = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private int sentHistoryCursor = -1;
/*     */   private boolean field_73897_d;
/*     */   private boolean field_73905_m;
/*     */   private int field_73903_n;
/*  24 */   private List field_73904_o = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private URI clickedURI;
/*     */ 
/*     */ 
/*     */   
/*     */   protected GuiTextField inputField;
/*     */ 
/*     */   
/*  35 */   private String defaultInputFieldText = "";
/*     */ 
/*     */   
/*     */   public GuiChat() {}
/*     */   
/*     */   public GuiChat(String par1Str) {
/*  41 */     this.defaultInputFieldText = par1Str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  49 */     Keyboard.enableRepeatEvents(true);
/*  50 */     this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
/*  51 */     this.inputField = new GuiTextField(this.fontRenderer, 4, this.height - 12, this.width - 4, 12);
/*  52 */     this.inputField.setMaxStringLength(100);
/*  53 */     this.inputField.setEnableBackgroundDrawing(false);
/*  54 */     this.inputField.setFocused(true);
/*  55 */     this.inputField.setText(this.defaultInputFieldText);
/*  56 */     this.inputField.setCanLoseFocus(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  64 */     Keyboard.enableRepeatEvents(false);
/*  65 */     this.mc.ingameGUI.getChatGUI().resetScroll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  73 */     this.inputField.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/*  81 */     this.field_73905_m = false;
/*     */     
/*  83 */     if (par2 == 15) {
/*     */       
/*  85 */       completePlayerName();
/*     */     }
/*     */     else {
/*     */       
/*  89 */       this.field_73897_d = false;
/*     */     } 
/*     */     
/*  92 */     if (par2 == 1) {
/*     */       
/*  94 */       if (this.inputField.getText() == null || this.inputField.getText().isEmpty()) {
/*  95 */         Minecraft.last_aborted_chat = "";
/*  96 */       } else if (!this.inputField.getText().startsWith("/")) {
/*  97 */         Minecraft.last_aborted_chat = this.inputField.getText();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       if (this == this.mc.imposed_gui_chat) {
/* 107 */         this.mc.closeImposedChat();
/*     */       } else {
/* 109 */         this.mc.displayGuiScreen(null);
/*     */       } 
/* 111 */     } else if (par2 != 28 && par2 != 156) {
/*     */       
/* 113 */       if (par2 == 200)
/*     */       {
/* 115 */         getSentHistory(-1);
/*     */       }
/* 117 */       else if (par2 == 208)
/*     */       {
/* 119 */         getSentHistory(1);
/*     */       }
/* 121 */       else if (par2 == 201)
/*     */       {
/* 123 */         this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().func_96127_i() - 1);
/*     */       }
/* 125 */       else if (par2 == 209)
/*     */       {
/* 127 */         this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().func_96127_i() + 1);
/*     */       }
/*     */       else
/*     */       {
/* 131 */         this.inputField.textboxKeyTyped(par1, par2);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 136 */       if (this.inputField.getText() != null && !this.inputField.getText().startsWith("/")) {
/* 137 */         Minecraft.last_aborted_chat = "";
/*     */       }
/* 139 */       String var3 = this.inputField.getText().trim();
/*     */       
/* 141 */       if (var3.length() > 0) {
/*     */         
/* 143 */         this.mc.ingameGUI.getChatGUI().addToSentMessages(var3);
/*     */         
/* 145 */         if (!this.mc.handleClientCommand(var3))
/*     */         {
/* 147 */           this.mc.thePlayer.sendChatMessage(var3);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 158 */       if (this == this.mc.imposed_gui_chat) {
/* 159 */         this.mc.closeImposedChat();
/*     */       } else {
/* 161 */         this.mc.displayGuiScreen(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() {
/* 170 */     if (this == this.mc.imposed_gui_chat && this.mc.currentScreen != null) {
/*     */       
/* 172 */       this.mc.currentScreen.handleMouseInput();
/*     */       
/*     */       return;
/*     */     } 
/* 176 */     super.handleMouseInput();
/* 177 */     int var1 = Mouse.getEventDWheel();
/*     */     
/* 179 */     if (var1 != 0) {
/*     */       
/* 181 */       if (var1 > 1)
/*     */       {
/* 183 */         var1 = 1;
/*     */       }
/*     */       
/* 186 */       if (var1 < -1)
/*     */       {
/* 188 */         var1 = -1;
/*     */       }
/*     */       
/* 191 */       if (!isShiftKeyDown())
/*     */       {
/* 193 */         var1 *= 7;
/*     */       }
/*     */       
/* 196 */       this.mc.ingameGUI.getChatGUI().scroll(var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 206 */     if (par3 == 0 && this.mc.gameSettings.chatLinks && !Minecraft.getClientPlayer().inBed()) {
/*     */       
/* 208 */       ChatClickData var4 = this.mc.ingameGUI.getChatGUI().func_73766_a(Mouse.getX(), Mouse.getY());
/*     */       
/* 210 */       if (var4 != null) {
/*     */         
/* 212 */         URI var5 = var4.getURI();
/*     */         
/* 214 */         if (var5 != null) {
/*     */           
/* 216 */           if (this.mc.gameSettings.chatLinksPrompt) {
/*     */             
/* 218 */             this.clickedURI = var5;
/* 219 */             this.mc.displayGuiScreen(new GuiConfirmOpenLink(this, var4.getClickedUrl(), 0, false));
/*     */           }
/*     */           else {
/*     */             
/* 223 */             func_73896_a(var5);
/*     */           } 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 231 */     this.inputField.mouseClicked(par1, par2, par3);
/* 232 */     super.mouseClicked(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean par1, int par2) {
/* 237 */     if (par2 == 0) {
/*     */       
/* 239 */       if (par1)
/*     */       {
/* 241 */         func_73896_a(this.clickedURI);
/*     */       }
/*     */       
/* 244 */       this.clickedURI = null;
/* 245 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_73896_a(URI par1URI) {
/*     */     try {
/* 253 */       Class<?> var2 = Class.forName("java.awt.Desktop");
/* 254 */       Object var3 = var2.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 255 */       var2.getMethod("browse", new Class[] { URI.class }).invoke(var3, new Object[] { par1URI });
/*     */     }
/* 257 */     catch (Throwable var4) {
/*     */       
/* 259 */       var4.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void completePlayerName() {
/* 270 */     if (this.field_73897_d) {
/*     */       
/* 272 */       this.inputField.deleteFromCursor(this.inputField.func_73798_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
/*     */       
/* 274 */       if (this.field_73903_n >= this.field_73904_o.size())
/*     */       {
/* 276 */         this.field_73903_n = 0;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 281 */       int var1 = this.inputField.func_73798_a(-1, this.inputField.getCursorPosition(), false);
/* 282 */       this.field_73904_o.clear();
/* 283 */       this.field_73903_n = 0;
/* 284 */       String var2 = this.inputField.getText().substring(var1).toLowerCase();
/* 285 */       String var3 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
/* 286 */       func_73893_a(var3, var2);
/*     */       
/* 288 */       if (this.field_73904_o.isEmpty()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 293 */       this.field_73897_d = true;
/* 294 */       this.inputField.deleteFromCursor(var1 - this.inputField.getCursorPosition());
/*     */     } 
/*     */     
/* 297 */     if (this.field_73904_o.size() > 1) {
/*     */       
/* 299 */       StringBuilder var4 = new StringBuilder();
/*     */       
/* 301 */       for (Iterator<String> var5 = this.field_73904_o.iterator(); var5.hasNext(); var4.append(var3)) {
/*     */         
/* 303 */         String var3 = var5.next();
/*     */         
/* 305 */         if (var4.length() > 0)
/*     */         {
/* 307 */           var4.append(", ");
/*     */         }
/*     */       } 
/*     */       
/* 311 */       this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(var4.toString(), 1);
/*     */     } 
/*     */     
/* 314 */     this.inputField.writeText(this.field_73904_o.get(this.field_73903_n++));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_73893_a(String par1Str, String par2Str) {
/* 319 */     if (par1Str.length() >= 1) {
/*     */       
/* 321 */       this.mc.thePlayer.sendQueue.addToSendQueue(new Packet203AutoComplete(par1Str));
/* 322 */       this.field_73905_m = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSentHistory(int par1) {
/* 332 */     int var2 = this.sentHistoryCursor + par1;
/* 333 */     int var3 = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
/*     */     
/* 335 */     if (var2 < 0)
/*     */     {
/* 337 */       var2 = 0;
/*     */     }
/*     */     
/* 340 */     if (var2 > var3)
/*     */     {
/* 342 */       var2 = var3;
/*     */     }
/*     */     
/* 345 */     if (var2 != this.sentHistoryCursor)
/*     */     {
/* 347 */       if (var2 == var3) {
/*     */         
/* 349 */         this.sentHistoryCursor = var3;
/* 350 */         this.inputField.setText(this.field_73898_b);
/*     */       }
/*     */       else {
/*     */         
/* 354 */         if (this.sentHistoryCursor == var3)
/*     */         {
/* 356 */           this.field_73898_b = this.inputField.getText();
/*     */         }
/*     */         
/* 359 */         this.inputField.setText(this.mc.ingameGUI.getChatGUI().getSentMessages().get(var2));
/* 360 */         this.sentHistoryCursor = var2;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/* 370 */     GL11.glDisable(2896);
/* 371 */     drawRect(2, this.height - 14, this.width - 2, this.height - 2, -2147483648);
/* 372 */     this.inputField.drawTextBox();
/* 373 */     super.drawScreen(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73894_a(String[] par1ArrayOfStr) {
/* 378 */     if (this.field_73905_m) {
/*     */       
/* 380 */       this.field_73904_o.clear();
/* 381 */       String[] var2 = par1ArrayOfStr;
/* 382 */       int var3 = par1ArrayOfStr.length;
/*     */       
/* 384 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 386 */         String var5 = var2[var4];
/*     */         
/* 388 */         if (var5.length() > 0)
/*     */         {
/* 390 */           this.field_73904_o.add(var5);
/*     */         }
/*     */       } 
/*     */       
/* 394 */       if (this.field_73904_o.size() > 0) {
/*     */         
/* 396 */         this.field_73897_d = true;
/* 397 */         completePlayerName();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 407 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowsImposedChat() {
/* 412 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiChat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */