/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoadingScreenRenderer
/*     */   implements IProgressUpdate
/*     */ {
/*  13 */   private String field_73727_a = "";
/*     */   private Minecraft mc;
/*  15 */   private String currentlyDisplayedText = "";
/*  16 */   private long field_73723_d = Minecraft.getSystemTime();
/*     */   private boolean field_73724_e;
/*     */   
/*     */   public LoadingScreenRenderer(Minecraft minecraft) {
/*  20 */     this.mc = minecraft;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetProgressAndMessage(String string) {
/*  25 */     this.field_73724_e = false;
/*  26 */     func_73722_d(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayProgressMessage(String string) {
/*  31 */     this.field_73724_e = true;
/*  32 */     func_73722_d(string);
/*     */   }
/*     */   
/*     */   public void func_73722_d(String string) {
/*  36 */     this.currentlyDisplayedText = string;
/*  37 */     if (!this.mc.running) {
/*  38 */       if (this.field_73724_e)
/*  39 */         return;  throw new MinecraftError();
/*     */     } 
/*     */     
/*  42 */     ScaledResolution scaledResolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/*     */     
/*  44 */     GL11.glClear(256);
/*  45 */     GL11.glMatrixMode(5889);
/*  46 */     GL11.glLoadIdentity();
/*  47 */     GL11.glOrtho(0.0D, scaledResolution.getScaledWidth_double(), scaledResolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
/*  48 */     GL11.glMatrixMode(5888);
/*  49 */     GL11.glLoadIdentity();
/*  50 */     GL11.glTranslatef(0.0F, 0.0F, -200.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetProgresAndWorkingMessage(String string) {
/*  55 */     if (!this.mc.running) {
/*  56 */       if (this.field_73724_e)
/*  57 */         return;  throw new MinecraftError();
/*     */     } 
/*     */     
/*  60 */     this.field_73723_d = 0L;
/*  61 */     this.field_73727_a = string;
/*  62 */     setLoadingProgress(-1);
/*  63 */     this.field_73723_d = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLoadingProgress(int i) {
/*  68 */     if (!this.mc.running) {
/*  69 */       if (this.field_73724_e)
/*  70 */         return;  throw new MinecraftError();
/*     */     } 
/*     */     
/*  73 */     long l = Minecraft.getSystemTime();
/*  74 */     if (l - this.field_73723_d < 100L)
/*  75 */       return;  this.field_73723_d = l;
/*     */     
/*  77 */     ScaledResolution scaledResolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
/*  78 */     int j = scaledResolution.getScaledWidth();
/*  79 */     int k = scaledResolution.getScaledHeight();
/*     */     
/*  81 */     GL11.glClear(256);
/*  82 */     GL11.glMatrixMode(5889);
/*  83 */     GL11.glLoadIdentity();
/*  84 */     GL11.glOrtho(0.0D, scaledResolution.getScaledWidth_double(), scaledResolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
/*  85 */     GL11.glMatrixMode(5888);
/*  86 */     GL11.glLoadIdentity();
/*  87 */     GL11.glTranslatef(0.0F, 0.0F, -200.0F);
/*     */     
/*  89 */     GL11.glClear(16640);
/*     */     
/*  91 */     Tessellator tessellator = Tessellator.instance;
/*  92 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/*  93 */     float f = 32.0F;
/*  94 */     tessellator.startDrawingQuads();
/*  95 */     tessellator.setColorOpaque_I(4210752);
/*  96 */     tessellator.addVertexWithUV(0.0D, k, 0.0D, 0.0D, (k / f));
/*  97 */     tessellator.addVertexWithUV(j, k, 0.0D, (j / f), (k / f));
/*  98 */     tessellator.addVertexWithUV(j, 0.0D, 0.0D, (j / f), 0.0D);
/*  99 */     tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 100 */     tessellator.draw();
/*     */     
/* 102 */     if (i >= 0) {
/* 103 */       byte b1 = 100;
/* 104 */       byte b2 = 2;
/* 105 */       int m = j / 2 - b1 / 2;
/* 106 */       int n = k / 2 + 16;
/*     */       
/* 108 */       GL11.glDisable(3553);
/* 109 */       tessellator.startDrawingQuads();
/* 110 */       tessellator.setColorOpaque_I(8421504);
/* 111 */       tessellator.addVertex(m, n, 0.0D);
/* 112 */       tessellator.addVertex(m, (n + b2), 0.0D);
/* 113 */       tessellator.addVertex((m + b1), (n + b2), 0.0D);
/* 114 */       tessellator.addVertex((m + b1), n, 0.0D);
/*     */       
/* 116 */       tessellator.setColorOpaque_I(8454016);
/* 117 */       tessellator.addVertex(m, n, 0.0D);
/* 118 */       tessellator.addVertex(m, (n + b2), 0.0D);
/* 119 */       tessellator.addVertex((m + i), (n + b2), 0.0D);
/* 120 */       tessellator.addVertex((m + i), n, 0.0D);
/* 121 */       tessellator.draw();
/* 122 */       GL11.glEnable(3553);
/*     */     } 
/*     */     
/* 125 */     this.mc.fontRenderer.drawStringWithShadow(this.currentlyDisplayedText, (j - this.mc.fontRenderer.getStringWidth(this.currentlyDisplayedText)) / 2, k / 2 - 4 - 16, 16777215);
/* 126 */     this.mc.fontRenderer.drawStringWithShadow(this.field_73727_a, (j - this.mc.fontRenderer.getStringWidth(this.field_73727_a)) / 2, k / 2 - 4 + 8, 16777215);
/* 127 */     Display.update();
/*     */     
/*     */     try {
/* 130 */       Thread.yield();
/* 131 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\LoadingScreenRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */