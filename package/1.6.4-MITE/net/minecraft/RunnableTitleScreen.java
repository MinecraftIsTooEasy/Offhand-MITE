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
/*     */ class RunnableTitleScreen
/*     */   extends Thread
/*     */ {
/*     */   RunnableTitleScreen(GuiMainMenu guiMainMenu) {}
/*     */   
/*     */   public void run() {
/* 191 */     McoClient mcoClient = new McoClient(GuiMainMenu.func_110348_a(this.theMainMenu).getSession());
/* 192 */     boolean bool = false;
/* 193 */     for (byte b = 0; b < 3; ) {
/*     */       try {
/* 195 */         Boolean bool1 = mcoClient.func_96375_b();
/* 196 */         if (bool1.booleanValue()) {
/* 197 */           GuiMainMenu.func_130021_b(this.theMainMenu);
/*     */         }
/* 199 */         GuiMainMenu.func_110349_a(bool1.booleanValue());
/* 200 */       } catch (ExceptionRetryCall exceptionRetryCall) {
/* 201 */         bool = true;
/* 202 */       } catch (ExceptionMcoService exceptionMcoService) {
/* 203 */         GuiMainMenu.func_130018_c(this.theMainMenu).getLogAgent().logSevere(exceptionMcoService.toString());
/* 204 */       } catch (IOException iOException) {
/* 205 */         GuiMainMenu.func_130019_d(this.theMainMenu).getLogAgent().logWarning("Realms: could not parse response");
/*     */       } 
/* 207 */       if (bool) {
/*     */         try {
/* 209 */           Thread.sleep(10000L);
/* 210 */         } catch (InterruptedException interruptedException) {
/* 211 */           Thread.currentThread().interrupt();
/*     */         } 
/*     */         b++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\RunnableTitleScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */