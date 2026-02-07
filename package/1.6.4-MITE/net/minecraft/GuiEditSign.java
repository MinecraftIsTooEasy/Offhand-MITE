/*     */ package net.minecraft;
/*     */ 
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiEditSign
/*     */   extends GuiScreen
/*     */ {
/*  16 */   private static final String allowedCharacters = ChatAllowedCharacters.allowedCharacters;
/*     */   
/*  18 */   protected String screenTitle = "Edit sign message:";
/*     */   
/*     */   private TileEntitySign entitySign;
/*     */   private int updateCounter;
/*     */   private int editLine;
/*     */   private GuiButton doneBtn;
/*     */   
/*     */   public GuiEditSign(TileEntitySign tileEntitySign) {
/*  26 */     this.entitySign = tileEntitySign;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  31 */     this.buttonList.clear();
/*  32 */     Keyboard.enableRepeatEvents(true);
/*  33 */     this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
/*  34 */     this.entitySign.setEditable(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  39 */     Keyboard.enableRepeatEvents(false);
/*  40 */     NetClientHandler netClientHandler = this.mc.getNetHandler();
/*  41 */     if (netClientHandler != null) netClientHandler.addToSendQueue(new Packet130UpdateSign(this.entitySign.xCoord, this.entitySign.yCoord, this.entitySign.zCoord, this.entitySign.signText)); 
/*  42 */     this.entitySign.setEditable(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  47 */     this.updateCounter++;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  52 */     if (!guiButton.enabled)
/*     */       return; 
/*  54 */     if (guiButton.id == 0) {
/*  55 */       this.entitySign.onInventoryChanged();
/*  56 */       this.mc.displayGuiScreen(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/*  62 */     if (i == 200) this.editLine = this.editLine - 1 & 0x3; 
/*  63 */     if (i == 208 || i == 28 || i == 156) this.editLine = this.editLine + 1 & 0x3; 
/*  64 */     if (i == 14 && this.entitySign.signText[this.editLine].length() > 0) {
/*  65 */       this.entitySign.signText[this.editLine] = this.entitySign.signText[this.editLine].substring(0, this.entitySign.signText[this.editLine].length() - 1);
/*     */     }
/*  67 */     if (allowedCharacters.indexOf(c) >= 0 && this.entitySign.signText[this.editLine].length() < 15) {
/*  68 */       this.entitySign.signText[this.editLine] = this.entitySign.signText[this.editLine] + c;
/*     */     }
/*  70 */     if (i == 1) {
/*  71 */       actionPerformed(this.doneBtn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/*  77 */     drawDefaultBackground();
/*     */     
/*  79 */     drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 40, 16777215);
/*     */     
/*  81 */     GL11.glPushMatrix();
/*  82 */     GL11.glTranslatef((this.width / 2), 0.0F, 50.0F);
/*  83 */     float f1 = 93.75F;
/*  84 */     GL11.glScalef(-f1, -f1, -f1);
/*  85 */     GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/*  87 */     Block block = this.entitySign.getBlockType();
/*     */     
/*  89 */     if (block == Block.signPost) {
/*  90 */       float f2 = (this.entitySign.getBlockMetadata() * 360) / 16.0F;
/*  91 */       GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
/*     */       
/*  93 */       GL11.glTranslatef(0.0F, -1.0625F, 0.0F);
/*     */     } else {
/*  95 */       int k = this.entitySign.getBlockMetadata();
/*  96 */       float f2 = 0.0F;
/*     */       
/*  98 */       if (k == 2) f2 = 180.0F; 
/*  99 */       if (k == 4) f2 = 90.0F; 
/* 100 */       if (k == 5) f2 = -90.0F; 
/* 101 */       GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
/* 102 */       GL11.glTranslatef(0.0F, -1.0625F, 0.0F);
/*     */     } 
/*     */     
/* 105 */     if (this.updateCounter / 6 % 2 == 0) this.entitySign.lineBeingEdited = this.editLine;
/*     */     
/* 107 */     TileEntityRenderer.instance.renderTileEntityAt(this.entitySign, -0.5D, -0.75D, -0.5D, 0.0F);
/* 108 */     this.entitySign.lineBeingEdited = -1;
/*     */     
/* 110 */     GL11.glPopMatrix();
/*     */     
/* 112 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiEditSign.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */