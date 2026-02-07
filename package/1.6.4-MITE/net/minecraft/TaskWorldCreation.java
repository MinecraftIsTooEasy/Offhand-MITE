/*    */ package net.minecraft;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ 
/*    */ 
/*    */ 
/*    */ class TaskWorldCreation
/*    */   extends TaskLongRunning
/*    */ {
/*    */   private final String field_96589_c;
/*    */   private final String field_96587_d;
/*    */   private final String field_104065_f;
/*    */   private final WorldTemplate field_111253_f;
/*    */   final GuiScreenCreateOnlineWorld field_96590_a;
/*    */   
/*    */   public TaskWorldCreation(GuiScreenCreateOnlineWorld par1GuiScreenCreateOnlineWorld, String par2Str, String par3Str, String par4Str, WorldTemplate par5WorldTemplate) {
/* 17 */     this.field_96590_a = par1GuiScreenCreateOnlineWorld;
/* 18 */     this.field_96589_c = par2Str;
/* 19 */     this.field_96587_d = par3Str;
/* 20 */     this.field_104065_f = par4Str;
/* 21 */     this.field_111253_f = par5WorldTemplate;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 26 */     String var1 = I18n.getString("mco.create.world.wait");
/* 27 */     setMessage(var1);
/* 28 */     McoClient var2 = new McoClient(GuiScreenCreateOnlineWorld.func_96248_a(this.field_96590_a).getSession());
/*    */ 
/*    */     
/*    */     try {
/* 32 */       if (this.field_111253_f != null) {
/*    */         
/* 34 */         var2.func_96386_a(this.field_96589_c, this.field_96587_d, this.field_104065_f, this.field_111253_f.field_110734_a);
/*    */       }
/*    */       else {
/*    */         
/* 38 */         var2.func_96386_a(this.field_96589_c, this.field_96587_d, this.field_104065_f, "-1");
/*    */       } 
/*    */       
/* 41 */       GuiScreenCreateOnlineWorld.func_96246_c(this.field_96590_a).displayGuiScreen(GuiScreenCreateOnlineWorld.func_96247_b(this.field_96590_a));
/*    */     }
/* 43 */     catch (ExceptionMcoService var4) {
/*    */       
/* 45 */       GuiScreenCreateOnlineWorld.func_130026_d(this.field_96590_a).getLogAgent().logSevere(var4.toString());
/* 46 */       setFailedMessage(var4.toString());
/*    */     }
/* 48 */     catch (UnsupportedEncodingException var5) {
/*    */       
/* 50 */       GuiScreenCreateOnlineWorld.func_130027_e(this.field_96590_a).getLogAgent().logWarning("Realms: " + var5.getLocalizedMessage());
/* 51 */       setFailedMessage(var5.getLocalizedMessage());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 58 */     catch (Exception var7) {
/*    */       
/* 60 */       setFailedMessage(var7.getLocalizedMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\TaskWorldCreation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */