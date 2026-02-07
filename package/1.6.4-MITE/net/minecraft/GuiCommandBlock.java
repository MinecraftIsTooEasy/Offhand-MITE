/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiCommandBlock
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiTextField commandTextField;
/*     */   private final TileEntityCommandBlock commandBlock;
/*     */   private GuiButton doneBtn;
/*     */   private GuiButton cancelBtn;
/*     */   
/*     */   public GuiCommandBlock(TileEntityCommandBlock tileEntityCommandBlock) {
/*  23 */     this.commandBlock = tileEntityCommandBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  28 */     this.commandTextField.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  34 */     Keyboard.enableRepeatEvents(true);
/*  35 */     this.buttonList.clear();
/*  36 */     this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, I18n.getString("gui.done")));
/*  37 */     this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
/*     */     
/*  39 */     this.commandTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20);
/*  40 */     this.commandTextField.setMaxStringLength(32767);
/*  41 */     this.commandTextField.setFocused(true);
/*  42 */     this.commandTextField.setText(this.commandBlock.getCommand());
/*     */     
/*  44 */     this.doneBtn.enabled = (this.commandTextField.getText().trim().length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  49 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guiButton) {
/*  54 */     if (!guiButton.enabled)
/*  55 */       return;  if (guiButton.id == 1) {
/*  56 */       this.mc.displayGuiScreen(null);
/*  57 */     } else if (guiButton.id == 0) {
/*     */       
/*  59 */       String str = "MC|AdvCdm";
/*     */       
/*  61 */       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/*  62 */       DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/*     */       
/*     */       try {
/*  65 */         dataOutputStream.writeInt(this.commandBlock.xCoord);
/*  66 */         dataOutputStream.writeInt(this.commandBlock.yCoord);
/*  67 */         dataOutputStream.writeInt(this.commandBlock.zCoord);
/*  68 */         Packet.writeString(this.commandTextField.getText(), dataOutputStream);
/*  69 */         this.mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(str, byteArrayOutputStream.toByteArray()));
/*  70 */       } catch (Exception exception) {
/*  71 */         exception.printStackTrace();
/*     */       } 
/*     */ 
/*     */       
/*  75 */       this.mc.displayGuiScreen(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/*  81 */     this.commandTextField.textboxKeyTyped(c, i);
/*  82 */     this.doneBtn.enabled = (this.commandTextField.getText().trim().length() > 0);
/*     */     
/*  84 */     if (i == 28 || i == 156) {
/*  85 */       actionPerformed(this.doneBtn);
/*  86 */     } else if (i == 1) {
/*  87 */       actionPerformed(this.cancelBtn);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int i, int j, int k) {
/*  93 */     super.mouseClicked(i, j, k);
/*     */     
/*  95 */     this.commandTextField.mouseClicked(i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/* 101 */     drawDefaultBackground();
/*     */     
/* 103 */     drawCenteredString(this.fontRenderer, I18n.getString("advMode.setCommand"), this.width / 2, 20, 16777215);
/* 104 */     drawString(this.fontRenderer, I18n.getString("advMode.command"), this.width / 2 - 150, 47, 10526880);
/*     */     
/* 106 */     drawString(this.fontRenderer, I18n.getString("advMode.nearestPlayer"), this.width / 2 - 150, 97, 10526880);
/* 107 */     drawString(this.fontRenderer, I18n.getString("advMode.randomPlayer"), this.width / 2 - 150, 108, 10526880);
/* 108 */     drawString(this.fontRenderer, I18n.getString("advMode.allPlayers"), this.width / 2 - 150, 119, 10526880);
/*     */     
/* 110 */     this.commandTextField.drawTextBox();
/*     */     
/* 112 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiCommandBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */