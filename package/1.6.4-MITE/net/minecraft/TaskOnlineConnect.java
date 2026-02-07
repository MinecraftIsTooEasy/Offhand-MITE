/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class TaskOnlineConnect
/*     */   extends TaskLongRunning
/*     */ {
/*     */   private NetClientHandler field_96586_a;
/*     */   private final McoServer field_96585_c;
/*     */   private final GuiScreen field_96584_d;
/*     */   
/*     */   public TaskOnlineConnect(GuiScreen guiScreen, McoServer mcoServer) {
/*  26 */     this.field_96584_d = guiScreen;
/*  27 */     this.field_96585_c = mcoServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  32 */     setMessage(I18n.getString("mco.connect.connecting"));
/*     */     
/*  34 */     McoClient mcoClient = new McoClient(getMinecraft().getSession());
/*  35 */     boolean bool1 = false;
/*  36 */     boolean bool2 = false;
/*  37 */     int i = 5;
/*  38 */     McoServerAddress mcoServerAddress = null;
/*  39 */     for (byte b = 0; b < 10 && 
/*  40 */       !wasScreenClosed(); b++) {
/*     */       try {
/*  42 */         mcoServerAddress = mcoClient.func_96374_a(this.field_96585_c.field_96408_a);
/*  43 */         bool1 = true;
/*  44 */       } catch (ExceptionRetryCall exceptionRetryCall) {
/*     */         
/*  46 */         i = exceptionRetryCall.field_96393_c;
/*  47 */       } catch (ExceptionMcoService exceptionMcoService) {
/*  48 */         bool2 = true;
/*  49 */         setFailedMessage(exceptionMcoService.toString());
/*  50 */         Minecraft.getMinecraft().getLogAgent().logSevere(exceptionMcoService.toString());
/*     */         break;
/*  52 */       } catch (IOException iOException) {
/*  53 */         Minecraft.getMinecraft().getLogAgent().logWarning("Realms: could not parse response");
/*  54 */       } catch (Exception exception) {
/*  55 */         bool2 = true;
/*  56 */         setFailedMessage(exception.getLocalizedMessage());
/*     */       } 
/*  58 */       if (bool1) {
/*     */         break;
/*     */       }
/*  61 */       func_111251_a(i);
/*     */     } 
/*  63 */     if (!wasScreenClosed() && !bool2)
/*  64 */       if (bool1) {
/*  65 */         ServerAddress serverAddress = ServerAddress.func_78860_a(mcoServerAddress.field_96417_a);
/*  66 */         func_96582_a(serverAddress.getIP(), serverAddress.getPort());
/*     */       } else {
/*  68 */         getMinecraft().displayGuiScreen(this.field_96584_d);
/*     */       }  
/*     */   }
/*     */   
/*     */   private void func_111251_a(int i) {
/*     */     try {
/*  74 */       Thread.sleep((i * 1000));
/*  75 */     } catch (InterruptedException interruptedException) {
/*  76 */       Minecraft.getMinecraft().getLogAgent().logWarning(interruptedException.getLocalizedMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_96582_a(String string, int i) {
/*  81 */     (new ThreadOnlineConnect(this, string, i)).start();
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 106 */     if (this.field_96586_a != null)
/* 107 */       this.field_96586_a.processReadPackets(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TaskOnlineConnect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */