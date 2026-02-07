/*    */ package net.minecraft;
/*    */ 
/*    */ import java.net.URI;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenDemo
/*    */   extends GuiScreen
/*    */ {
/* 15 */   private static final ResourceLocation field_110407_a = new ResourceLocation("textures/gui/demo_background.png");
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 19 */     this.buttonList.clear();
/*    */     
/* 21 */     byte b = -16;
/*    */     
/* 23 */     this.buttonList.add(new GuiButton(1, this.width / 2 - 116, this.height / 2 + 62 + b, 114, 20, I18n.getString("demo.help.buy")));
/* 24 */     this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 2 + 62 + b, 114, 20, I18n.getString("demo.help.later")));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 29 */     switch (guiButton.id) {
/*    */       case 2:
/* 31 */         this.mc.displayGuiScreen(null);
/* 32 */         this.mc.setIngameFocus();
/*    */         break;
/*    */       case 1:
/* 35 */         guiButton.enabled = false;
/*    */         try {
/* 37 */           Class<?> clazz = Class.forName("java.awt.Desktop");
/* 38 */           Object object = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 39 */           clazz.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI("http://www.minecraft.net/store?source=demo") });
/* 40 */         } catch (Throwable throwable) {
/* 41 */           throwable.printStackTrace();
/*    */         } 
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 49 */     super.updateScreen();
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawDefaultBackground() {
/* 54 */     super.drawDefaultBackground();
/*    */     
/* 56 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 57 */     this.mc.getTextureManager().bindTexture(field_110407_a);
/* 58 */     int i = (this.width - 248) / 2;
/* 59 */     int j = (this.height - 166) / 2;
/* 60 */     drawTexturedModalRect(i, j, 0, 0, 248, 166);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 65 */     drawDefaultBackground();
/*    */     
/* 67 */     int k = (this.width - 248) / 2 + 10;
/*    */     
/* 69 */     int m = (this.height - 166) / 2 + 8;
/*    */     
/* 71 */     this.fontRenderer.drawString(I18n.getString("demo.help.title"), k, m, 2039583);
/* 72 */     m += 12;
/*    */     
/* 74 */     GameSettings gameSettings = this.mc.gameSettings;
/*    */     
/* 76 */     this.fontRenderer.drawString(I18n.getStringParams("demo.help.movementShort", new Object[] { GameSettings.getKeyDisplayString(gameSettings.keyBindForward.keyCode), GameSettings.getKeyDisplayString(gameSettings.keyBindLeft.keyCode), GameSettings.getKeyDisplayString(gameSettings.keyBindBack.keyCode), GameSettings.getKeyDisplayString(gameSettings.keyBindRight.keyCode) }), k, m, 5197647);
/*    */     
/* 78 */     this.fontRenderer.drawString(I18n.getString("demo.help.movementMouse"), k, m + 12, 5197647);
/*    */     
/* 80 */     this.fontRenderer.drawString(I18n.getStringParams("demo.help.jump", new Object[] { GameSettings.getKeyDisplayString(gameSettings.keyBindJump.keyCode) }), k, m + 24, 5197647);
/*    */     
/* 82 */     this.fontRenderer.drawString(I18n.getStringParams("demo.help.inventory", new Object[] { GameSettings.getKeyDisplayString(gameSettings.keyBindInventory.keyCode) }), k, m + 36, 5197647);
/*    */     
/* 84 */     this.fontRenderer.drawSplitString(I18n.getString("demo.help.fullWrapped"), k, m + 68, 218, 2039583);
/*    */     
/* 86 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenDemo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */