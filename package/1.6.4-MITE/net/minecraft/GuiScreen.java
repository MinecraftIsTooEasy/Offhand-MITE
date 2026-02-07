/*     */ package net.minecraft;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.ClipboardOwner;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.main.Main;
/*     */ import org.lwjgl.input.Keyboard;
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
/*     */ public class GuiScreen
/*     */   extends Gui
/*     */ {
/*     */   protected Minecraft mc;
/*     */   public int width;
/*     */   public int height;
/*  29 */   protected List buttonList = new ArrayList();
/*     */ 
/*     */   
/*     */   public boolean allowUserInput;
/*     */   
/*     */   protected FontRenderer fontRenderer;
/*     */   
/*     */   private GuiButton selectedButton;
/*     */   
/*     */   private int eventButton;
/*     */   
/*     */   private long lastMouseEvent;
/*     */   
/*     */   private int field_92018_d;
/*     */ 
/*     */   
/*     */   public void drawScreen(int par1, int par2, float par3) {
/*  46 */     for (int var4 = 0; var4 < this.buttonList.size(); var4++) {
/*     */       
/*  48 */       GuiButton var5 = this.buttonList.get(var4);
/*  49 */       var5.drawButton(this.mc, par1, par2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char par1, int par2) {
/*  58 */     if (par2 == 1) {
/*     */       
/*  60 */       this.mc.displayGuiScreen((GuiScreen)null);
/*  61 */       this.mc.setIngameFocus();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getClipboardString() {
/*     */     try {
/*  72 */       Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
/*     */       
/*  74 */       if (var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor))
/*     */       {
/*  76 */         return (String)var0.getTransferData(DataFlavor.stringFlavor);
/*     */       }
/*     */     }
/*  79 */     catch (Exception var1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setClipboardString(String par0Str) {
/*     */     try {
/*  94 */       StringSelection var1 = new StringSelection(par0Str);
/*  95 */       Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, (ClipboardOwner)null);
/*     */     }
/*  97 */     catch (Exception var2) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int par1, int par2, int par3) {
/* 108 */     if (par3 == 0)
/*     */     {
/* 110 */       for (int var4 = 0; var4 < this.buttonList.size(); var4++) {
/*     */         
/* 112 */         GuiButton var5 = this.buttonList.get(var4);
/*     */         
/* 114 */         if (var5.mousePressed(this.mc, par1, par2)) {
/*     */           
/* 116 */           this.selectedButton = var5;
/*     */           
/* 118 */           var5.playClickedSound(this.mc.sndManager);
/* 119 */           actionPerformed(var5);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseMovedOrUp(int par1, int par2, int par3) {
/* 131 */     if (this.selectedButton != null && par3 == 0) {
/*     */       
/* 133 */       this.selectedButton.mouseReleased(par1, par2);
/* 134 */       this.selectedButton = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClickMove(int par1, int par2, int par3, long par4) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton par1GuiButton) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldAndResolution(Minecraft par1Minecraft, int par2, int par3) {
/* 155 */     this.mc = par1Minecraft;
/* 156 */     this.fontRenderer = par1Minecraft.fontRenderer;
/* 157 */     this.width = par2;
/* 158 */     this.height = par3;
/* 159 */     this.buttonList.clear();
/* 160 */     initGui();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handlesMouseInput() {
/* 170 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInput() {
/* 179 */     while (this.mc.isGuiOpen(true) && Mouse.next())
/*     */     {
/* 181 */       handleMouseInput();
/*     */     }
/*     */     
/* 184 */     while (Keyboard.next())
/*     */     {
/* 186 */       handleKeyboardInput();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() {
/* 195 */     int var1 = Mouse.getEventX() * this.width / this.mc.displayWidth;
/* 196 */     int var2 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
/* 197 */     int var3 = Mouse.getEventButton();
/*     */     
/* 199 */     if (Minecraft.isRunningOnMac && var3 == 0 && (Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)))
/*     */     {
/* 201 */       var3 = 1;
/*     */     }
/*     */     
/* 204 */     if (Mouse.getEventButtonState()) {
/*     */       
/* 206 */       if (this.mc.gameSettings.touchscreen && this.field_92018_d++ > 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 211 */       this.eventButton = var3;
/* 212 */       this.lastMouseEvent = Minecraft.getSystemTime();
/* 213 */       mouseClicked(var1, var2, this.eventButton);
/*     */     }
/* 215 */     else if (var3 != -1) {
/*     */       
/* 217 */       if (this.mc.gameSettings.touchscreen && --this.field_92018_d > 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 222 */       this.eventButton = -1;
/* 223 */       mouseMovedOrUp(var1, var2, var3);
/*     */     }
/* 225 */     else if (this.eventButton != -1 && this.lastMouseEvent > 0L) {
/*     */       
/* 227 */       long var4 = Minecraft.getSystemTime() - this.lastMouseEvent;
/* 228 */       mouseClickMove(var1, var2, this.eventButton, var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleKeyboardInput() {
/* 237 */     if (Keyboard.getEventKeyState()) {
/*     */       
/* 239 */       int var1 = Keyboard.getEventKey();
/* 240 */       char var2 = Keyboard.getEventCharacter();
/*     */       
/* 242 */       if (var1 == 87) {
/*     */ 
/*     */ 
/*     */         
/* 246 */         if (!Main.is_MITE_DS) {
/* 247 */           this.mc.toggleFullscreen();
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/* 252 */       keyTyped(var2, var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawDefaultBackground() {
/* 271 */     drawWorldBackground(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawWorldBackground(int par1) {
/* 276 */     if (this.mc.theWorld != null) {
/*     */       
/* 278 */       drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 285 */       drawBackground(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawBackground(int par1) {
/* 294 */     GL11.glDisable(2896);
/* 295 */     GL11.glDisable(2912);
/* 296 */     Tessellator var2 = Tessellator.instance;
/* 297 */     this.mc.getTextureManager().bindTexture(optionsBackground);
/* 298 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 299 */     float var3 = 32.0F;
/* 300 */     var2.startDrawingQuads();
/* 301 */     var2.setColorOpaque_I(4210752);
/* 302 */     var2.addVertexWithUV(0.0D, this.height, 0.0D, 0.0D, (this.height / var3 + par1));
/* 303 */     var2.addVertexWithUV(this.width, this.height, 0.0D, (this.width / var3), (this.height / var3 + par1));
/* 304 */     var2.addVertexWithUV(this.width, 0.0D, 0.0D, (this.width / var3), par1);
/* 305 */     var2.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, par1);
/* 306 */     var2.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 314 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean par1, int par2) {}
/*     */   
/*     */   public static boolean isCtrlKeyDown() {
/* 321 */     return Minecraft.isRunningOnMac ? ((Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220))) : ((Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isShiftKeyDown() {
/* 326 */     return (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowsImposedChat() {
/* 331 */     return !doesGuiPauseGame();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */