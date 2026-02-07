/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import org.lwjgl.Sys;
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
/*     */ public class GuiScreenTemporaryResourcePackSelect
/*     */   extends GuiScreen
/*     */ {
/*     */   protected GuiScreen field_110347_a;
/*  27 */   private int refreshTimer = -1;
/*     */   
/*     */   private GuiScreenTemporaryResourcePackSelectSelectionList field_110346_c;
/*     */   private GameSettings field_96146_n;
/*     */   
/*     */   public GuiScreenTemporaryResourcePackSelect(GuiScreen guiScreen, GameSettings gameSettings) {
/*  33 */     this.field_110347_a = guiScreen;
/*  34 */     this.field_96146_n = gameSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  40 */     this.buttonList.add(new GuiSmallButton(5, this.width / 2 - 154, this.height - 48, I18n.getString("resourcePack.openFolder")));
/*  41 */     this.buttonList.add(new GuiSmallButton(6, this.width / 2 + 4, this.height - 48, I18n.getString("gui.done")));
/*     */     
/*  43 */     this.field_110346_c = new GuiScreenTemporaryResourcePackSelectSelectionList(this, this.mc.getResourcePackRepository());
/*  44 */     this.field_110346_c.registerScrollButtons(7, 8);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  49 */     if (!guiButton.enabled)
/*     */       return; 
/*  51 */     if (guiButton.id == 5) {
/*  52 */       File file = GuiScreenTemporaryResourcePackSelectSelectionList.func_110510_a(this.field_110346_c).getDirResourcepacks();
/*  53 */       String str = file.getAbsolutePath();
/*     */       
/*  55 */       if (Util.getOSType() == EnumOS.MACOS) {
/*     */         try {
/*  57 */           this.mc.getLogAgent().logInfo(str);
/*  58 */           Runtime.getRuntime().exec(new String[] { "/usr/bin/open", str });
/*     */ 
/*     */           
/*     */           return;
/*  62 */         } catch (IOException iOException) {
/*  63 */           iOException.printStackTrace();
/*     */         } 
/*  65 */       } else if (Util.getOSType() == EnumOS.WINDOWS) {
/*     */ 
/*     */         
/*  68 */         String str1 = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[] { str });
/*     */         try {
/*  70 */           Runtime.getRuntime().exec(str1);
/*     */           return;
/*  72 */         } catch (IOException iOException) {
/*  73 */           iOException.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/*  77 */       boolean bool = false;
/*     */       try {
/*  79 */         Class<?> clazz = Class.forName("java.awt.Desktop");
/*  80 */         Object object = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/*  81 */         clazz.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { file.toURI() });
/*  82 */       } catch (Throwable throwable) {
/*  83 */         throwable.printStackTrace();
/*  84 */         bool = true;
/*     */       } 
/*  86 */       if (bool) {
/*  87 */         this.mc.getLogAgent().logInfo("Opening via system class!");
/*  88 */         Sys.openURL("file://" + str);
/*     */       } 
/*  90 */     } else if (guiButton.id == 6) {
/*  91 */       this.mc.displayGuiScreen(this.field_110347_a);
/*     */     } else {
/*  93 */       this.field_110346_c.actionPerformed(guiButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/*  99 */     super.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseMovedOrUp(int i, int j, int k) {
/* 104 */     super.mouseMovedOrUp(i, j, k);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 109 */     this.field_110346_c.drawScreen(i, j, f);
/*     */     
/* 111 */     if (this.refreshTimer <= 0) {
/* 112 */       GuiScreenTemporaryResourcePackSelectSelectionList.func_110510_a(this.field_110346_c).updateRepositoryEntriesAll();
/* 113 */       this.refreshTimer = 20;
/*     */     } 
/*     */     
/* 116 */     drawCenteredString(this.fontRenderer, I18n.getString("resourcePack.title"), this.width / 2, 16, 16777215);
/* 117 */     drawCenteredString(this.fontRenderer, I18n.getString("resourcePack.folderInfo"), this.width / 2 - 77, this.height - 26, 8421504);
/*     */     
/* 119 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 124 */     super.updateScreen();
/* 125 */     this.refreshTimer--;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenTemporaryResourcePackSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */