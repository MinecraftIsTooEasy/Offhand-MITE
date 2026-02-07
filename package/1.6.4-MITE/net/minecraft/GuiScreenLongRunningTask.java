/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiScreenLongRunningTask
/*    */   extends GuiScreen
/*    */ {
/* 12 */   private final int field_96213_b = 666;
/*    */   
/*    */   private final GuiScreen previousScreen;
/*    */   private final Thread taskThread;
/* 16 */   private volatile String message = "";
/*    */   
/*    */   private volatile boolean taskFailed;
/*    */   private volatile String failedMessage;
/*    */   private volatile boolean screenWasClosed;
/*    */   private int progressCounter;
/*    */   private TaskLongRunning task;
/*    */   
/*    */   public GuiScreenLongRunningTask(Minecraft minecraft, GuiScreen guiScreen, TaskLongRunning taskLongRunning) {
/* 25 */     this.buttonList = Collections.synchronizedList(new ArrayList());
/* 26 */     this.mc = minecraft;
/* 27 */     this.previousScreen = guiScreen;
/* 28 */     this.task = taskLongRunning;
/* 29 */     taskLongRunning.setGUI(this);
/* 30 */     this.taskThread = new Thread(taskLongRunning);
/*    */   }
/*    */   
/*    */   public void func_98117_g() {
/* 34 */     this.taskThread.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 39 */     super.updateScreen();
/*    */     
/* 41 */     this.progressCounter++;
/* 42 */     this.task.updateScreen();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char c, int i) {}
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 51 */     this.task.initGUI();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton guiButton) {
/* 56 */     if (guiButton.id == 666) {
/* 57 */       this.screenWasClosed = true;
/* 58 */       this.mc.displayGuiScreen(this.previousScreen);
/*    */     } 
/* 60 */     this.task.buttonClicked(guiButton);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int i, int j, float f) {
/* 65 */     drawDefaultBackground();
/*    */     
/* 67 */     drawCenteredString(this.fontRenderer, this.message, this.width / 2, this.height / 2 - 50, 16777215);
/* 68 */     drawCenteredString(this.fontRenderer, "", this.width / 2, this.height / 2 - 10, 16777215);
/*    */     
/* 70 */     if (!this.taskFailed) {
/* 71 */       drawCenteredString(this.fontRenderer, PROGRESS_TEXT[this.progressCounter % PROGRESS_TEXT.length], this.width / 2, this.height / 2 + 15, 8421504);
/*    */     }
/* 73 */     if (this.taskFailed) {
/* 74 */       drawCenteredString(this.fontRenderer, this.failedMessage, this.width / 2, this.height / 2 + 15, 16711680);
/*    */     }
/* 76 */     super.drawScreen(i, j, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFailedMessage(String string) {
/* 81 */     this.taskFailed = true;
/* 82 */     this.failedMessage = string;
/* 83 */     this.buttonList.clear();
/* 84 */     this.buttonList.add(new GuiButton(666, this.width / 2 - 100, this.height / 4 + 120 + 12, "Back"));
/*    */   }
/*    */   
/*    */   public Minecraft func_96208_g() {
/* 88 */     return this.mc;
/*    */   }
/*    */   
/*    */   public void setMessage(String string) {
/* 92 */     this.message = string;
/*    */   }
/*    */   
/*    */   public boolean wasScreenClosed() {
/* 96 */     return this.screenWasClosed;
/*    */   }
/*    */   
/* 99 */   public static final String[] PROGRESS_TEXT = new String[] { "â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ", "_ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„", "_ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–…", "_ _ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–†", "_ _ _ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡", "_ _ _ _ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ", "_ _ _ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡", "_ _ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–†", "_ _ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–…", "_ â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„", "â–ƒ â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ", "â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _", "â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _", "â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _ _", "â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _ _ _", "â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _ _ _ _", "â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _ _ _", "â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _ _", "â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _ _", "â–„ â–… â–† â–‡ â–ˆ â–‡ â–† â–… â–„ â–ƒ _" };
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GuiScreenLongRunningTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */