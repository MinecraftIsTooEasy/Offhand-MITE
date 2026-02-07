/*     */ package net.minecraft;
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
/*     */ class TaskResetWorld
/*     */   extends TaskLongRunning
/*     */ {
/*     */   private final long field_96591_c;
/*     */   private final String field_104066_d;
/*     */   private final WorldTemplate field_111252_e;
/*     */   
/*     */   public TaskResetWorld(GuiScreenResetWorld guiScreenResetWorld, long l, String string, WorldTemplate worldTemplate) {
/* 142 */     this.field_96591_c = l;
/* 143 */     this.field_104066_d = string;
/* 144 */     this.field_111252_e = worldTemplate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 149 */     McoClient mcoClient = new McoClient(getMinecraft().getSession());
/* 150 */     String str = I18n.getString("mco.reset.world.resetting.screen.title");
/* 151 */     setMessage(str);
/*     */     try {
/* 153 */       if (this.field_111252_e != null) { mcoClient.func_111233_e(this.field_96591_c, this.field_111252_e.field_110734_a); }
/* 154 */       else { mcoClient.func_96376_d(this.field_96591_c, this.field_104066_d); }
/* 155 */        GuiScreenResetWorld.func_96147_b(this.field_96592_a).displayGuiScreen(GuiScreenResetWorld.func_96148_a(this.field_96592_a));
/* 156 */     } catch (ExceptionMcoService exceptionMcoService) {
/* 157 */       GuiScreenResetWorld.func_130025_c(this.field_96592_a).getLogAgent().logSevere(exceptionMcoService.toString());
/* 158 */       setFailedMessage(exceptionMcoService.toString());
/* 159 */     } catch (Exception exception) {
/* 160 */       GuiScreenResetWorld.func_130024_d(this.field_96592_a).getLogAgent().logWarning("Realms: ");
/* 161 */       setFailedMessage(exception.toString());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TaskResetWorld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */